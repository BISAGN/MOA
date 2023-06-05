package com.AyushEdu.controller.Ug_Pg_Fee_Collection;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_INSTITUTE_FEES_DATA;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_SET_STU_FEES_CHILD;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_SET_STU_FEES_PARENT;
import com.AyushEdu.controller.Collaboration.Collaboration_Excel_Report;
import com.AyushEdu.controller.Collaboration.Collaboration_PDF_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Fees_Data_Dao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Fees_Data_Report_Dao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Set_Student_Fees_Amount_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Ug_Pg_Fee_Data_Report_Controller {
	@Autowired
	CommonController common;

	@Autowired
	Commondao commondao;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Fees_Data_Report_Dao fdrDao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	private Date date;

	@RequestMapping(value = "/Fees_Data_Report_Url", method = RequestMethod.GET)
	public ModelAndView Fees_Data_Report_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String role = session.getAttribute("role").toString();
		String userId = session.getAttribute("userId").toString();
		String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		try {
			if (request.getHeader("Referer") == null) {
				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Fees_Data_Report_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			ArrayList<ArrayList<String>> getSystemListFromInstituteExam = fdrDao
					.getSystemListFromInstituteExam(institute_id1, userId, role);
			String system = fdrDao.getSystemListFromInstituteExam(institute_id1, userId, role).get(0).get(1);
			Mmap.put("msg", msg);
			Mmap.put("gettermList", common.gettermListFee(sessionFactory));
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("d_name", common.getDegreeList(sessionFactory));
			Mmap.put("list", fdrDao.DataTable_CMEAttend_DataList(userId, "0", "0", "0", "0", role));

		} catch (Exception e) {
			e.printStackTrace();
		}
//		
		return new ModelAndView("Fees_Data_Report_Tiles");
	}

	@RequestMapping(value = "/getSystemListFromInstituteExam1", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getSystemListFromInstituteExam1(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {
			list = fdrDao.getSystemListFromInstituteExam(institute_id1, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getDegreeListbysystem111", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem111(String system_name) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = common.getDegreeListbysystem(sessionFactory, system_name);
		return list;
	}

	@RequestMapping(value = "/Search_fees_data_Report_Url", method = RequestMethod.POST)
	public ModelAndView Search_fees_data_Report_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			RedirectAttributes ra, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "system_id1", required = false) String system_id,
			@RequestParam(value = "degree_name1", required = false) String degree_name,
			@RequestParam(value = "prof_id1", required = false) String prof_id,
			@RequestParam(value = "url1", required = false) String url1) {
		String userId = session.getAttribute("userId").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (degree_name.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (prof_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		try {

			if (request.getHeader("Referer") == null) {
				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Fees_Data_Report_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);
			Mmap.put("gettermList", common.gettermListFee(sessionFactory));
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("d_name", common.getDegreeList(sessionFactory));
			Mmap.put("system_id1", system_id);
			Mmap.put("degree_name1", degree_name);
			Mmap.put("prof_id1", prof_id);
			Mmap.put("institute_id", institute_id);
			Mmap.put("role", role);
			Mmap.put("url1", url1);
			Mmap.put("list",
					fdrDao.DataTable_CMEAttend_DataList(userId, system_id, degree_name, prof_id, institute_id, role));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Fees_Data_Report_Tiles");
	}

	@RequestMapping(value = "/getFees_Data_Report_Excel", method = RequestMethod.POST)
	public ModelAndView getFees_Data_Report_Excel(HttpSession session, HttpServletRequest request,
			RedirectAttributes ra) throws ParseException {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String inst_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
//			System.err.println("roleid================" + role.split("_")[1].toString());
		String name = request.getParameter("name1");
		String total_fees = request.getParameter("total_fees1");
		String paid_fees = request.getParameter("paid_fees1");
		String degree_name = request.getParameter("degree_name2");
		String system_id = request.getParameter("system_id2");
		String prof_id = request.getParameter("prof_id2");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (degree_name.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (prof_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Fees_Data_Url");
		}

		List<ArrayList<String>> listofdata = fdrDao.getFees_Data_Report_Excel(name, total_fees, paid_fees, role,
				system_id, degree_name, prof_id, inst_id);
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		TH.add("Student Name");
		TH.add("Total Fees");
		TH.add("Paid Fees");
		if (listofdata.size() == 0) {
			ra.addAttribute("msg", "Data Not Available");
			return new ModelAndView("redirect:Fees_Data_Report_Url");
		}
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Fees_Data_Excel_Report("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}

	@RequestMapping(value = "/Fees_Data_Report_PDF", method = RequestMethod.POST)
	public ModelAndView Fees_Data_Report_PDF(HttpSession session, HttpServletRequest request, String role,
			RedirectAttributes ra, @RequestParam(value = "url1", required = false) String url1) throws ParseException {

		String username = session.getAttribute("username").toString();
		String role1 = session.getAttribute("role").toString();
		String name = request.getParameter("name2");
		String total_fees = request.getParameter("total_fees2");
		String paid_fees = request.getParameter("paid_fees2");
		String degree_name = request.getParameter("degree_name3");
		String system_id = request.getParameter("system_id3");
		String prof_id = request.getParameter("prof_id3");
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String inst_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (degree_name.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (prof_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Fees_Data_Url");
		}

		List<ArrayList<String>> nonlecact1 = fdrDao.getFees_Data_Report_Excel(name, total_fees, paid_fees, role1,
				system_id, degree_name, prof_id, inst_id);

		int total = nonlecact1.size();
		List<String> TH = new ArrayList<String>();
		TH.add("Ser No.");
		TH.add("Student Name");
		TH.add("Total Fees");
		TH.add("Paid Fees");
		if (nonlecact1.size() == 0) {
			ra.addAttribute("msg", "Data Not Available");
			return new ModelAndView("redirect:Fees_Data_Report_Url");
		}
		String Heading = "\nIn Inspection";
		return new ModelAndView(new Fees_Data_PDF_Report("L", TH, Heading, username, total, role1), "userList",
				nonlecact1);
	}
}
