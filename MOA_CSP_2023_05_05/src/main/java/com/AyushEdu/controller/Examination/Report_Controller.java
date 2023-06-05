package com.AyushEdu.controller.Examination;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYS_DEG_MAP_INST;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Examination.No_Of_Attempt_Mstr_Dao;
import com.AyushEdu.dao.Examination.Report_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Report_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	private Report_Dao rdao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	No_Of_Attempt_Mstr_Dao sdao;
	
	@Autowired
	Report_Dao srdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Commondao cd;
	
	@RequestMapping(value = "admin/Report_URL", method = RequestMethod.GET)
	public ModelAndView Report_URL
	(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Report_URL", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println("userid==============="+userid);
			Mmap.put("msg", msg);
			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
			Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));
			Mmap.put("getTerm", common.getTerm());
			Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory, userid));
//			Mmap.put("getuniversity_listbyState", common.getuniversity_listbyState(sessionFactory, userid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Report_tiles");
	}
//	@RequestMapping(value = "/getuniversity_listbyState1", method = RequestMethod.POST)
//	public @ResponseBody List<UserLogin> getuniversity_listbyState1(String state_id, HttpSession session) {
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		List<UserLogin> list = common.getuniversity_listbyState(sessionFactory, state_id,userid);
//		return list;
//	}
	
	@RequestMapping(value = "/getuniversity_list", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getuniversity_list( String state_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
	 ArrayList<ArrayList<String>> list = srdao.getuniversity_list(state_id,role);
		return list;
	}
	
	@RequestMapping(value = "/getinstitute_listbyuniversity_exam1", method = RequestMethod.POST)
	public @ResponseBody List<UserLogin> getinstitute_listbyuniversity_exam1(String university_id) {
		List<UserLogin> list = common.getinstitute_listbyuniversity_exam(sessionFactory, university_id);
		return list;
	}
	
	@RequestMapping(value = "/getDegreeListFromInstitute2", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_INST> getDegreeListFromInstitute2(String institute_id) {
		List<EDU_LMS_SYS_DEG_MAP_INST> list = common.getDegreeListFromInstitute_exam(sessionFactory, institute_id);
		return list;
	}
	
	@PostMapping("/getFilter_report")
	public @ResponseBody List<Map<String, Object>> getFilter_report(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, HttpSession session) throws ParseException {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		if(role.toLowerCase().contains("university")){
			university_id = common.getUniIdfromUserid(sessionFactory,userid).get(0);
		}
		return rdao.get_report(startPage, pageLength, Search, orderColunm, orderType, state_id,
				university_id, institute_id, degree_id, professional_id, role,userid);
	}
	
	@PostMapping("/getTotal_report_dataCount")
	public @ResponseBody long getTotal_report_dataCount(HttpSession sessionUserId, String Search,
			 String state_id, String university_id,String institute_id, String degree_id, String professional_id, HttpSession session) throws ParseException {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		if(role.toLowerCase().contains("university")){
			university_id = common.getUniIdfromUserid(sessionFactory,userid).get(0);
		}
		return rdao.getTotal_report_dataCount(Search, state_id,university_id, institute_id, degree_id, professional_id,role);
	}
	
	@RequestMapping(value = "/get_Student_Report_Excel", method = RequestMethod.POST)
	public ModelAndView get_Student_Report_Excel(HttpSession session,ModelMap Mmap, HttpServletRequest request,RedirectAttributes ra)
			throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Report_URL", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String role = session.getAttribute("role").toString();

		String state_id = request.getParameter("state_id1");
		String university_id = request.getParameter("university_id1");
		String institute_id = request.getParameter("institute_id1");
		String degree_id = request.getParameter("degree_id1");
		String professional_id = request.getParameter("professional_id1");

		ArrayList<ArrayList<String>> listofdata = rdao.getStudent_Report_Excel(state_id, university_id,
				institute_id, degree_id, professional_id, role, "");

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		if(!role.toLowerCase().contains("university")) {
		TH.add("State");
		TH.add("University");
		}
		TH.add("Institute");
		TH.add("Degree");
		TH.add("Promote");
		TH.add("Detain");
		TH.add("Supplementary");
		
		if(listofdata.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Report_URL");
		}
		
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new All_Student_Data_Excel_Report("L", TH, listofdata, Heading, username, role), "userList",
				listexport);
	}
	@RequestMapping(value = "/get_Student_Report_PDF", method = RequestMethod.POST)
	public ModelAndView get_Student_Report_PDF(HttpSession session, ModelMap Mmap,HttpServletRequest request,RedirectAttributes ra)
			throws ParseException {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Report_URL", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		String username = session.getAttribute("username").toString();

		String role = session.getAttribute("role").toString();
		String state_id = request.getParameter("state_id2");
		String university_id = request.getParameter("university_id2");
		String institute_id = request.getParameter("institute_id2");
		String degree_id = request.getParameter("degree_id2");
		String professional_id = request.getParameter("professional_id2");

		List<ArrayList<String>> nonlecact1 = rdao.getStudent_Report_Excel(state_id, university_id,
				institute_id, degree_id, professional_id, role, "");

		int total = nonlecact1.size();
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		if(!role.toLowerCase().contains("university")) {
		TH.add("State");
		TH.add("University");
		}
		TH.add("Institute");
		TH.add("Degree");
		TH.add("Promote");
		TH.add("Detain");
		TH.add("Supplementary");
		
		if(nonlecact1.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Report_URL");
		}
		

		String Heading = "\nIn Inspection";
		return new ModelAndView(new All_Student_Data_PDF_Report("L", TH, Heading, username, total, role), "userList", nonlecact1);
	}
}
