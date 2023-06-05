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
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Examination.Student_Result_Declaration_Report_Dao;
import com.AyushEdu.dao.Examination.Students_Result_DetailsDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Student_Result_Declaration_Report_Controller {

	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Student_Result_Declaration_Report_Dao SRD;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Commondao cd;
	
	@Autowired
	private Students_Result_DetailsDao SRDDao;

	@RequestMapping(value = "admin/Student_Result_Declaration_Report", method = RequestMethod.GET)
	public ModelAndView Student_Result_Declaration_Report

	(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {

		try {
			
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Student_Result_Declaration_Report", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String uni_id = SRDDao.getuserid_listby_university(Integer.parseInt(userid)).get(0).get(0);
			Mmap.put("msg", msg);
			String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
			Mmap.put("getSystemList", common.getSystemList(sessionFactory,roleStaff_lvl));	
//			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("getTerm", common.getTerm());
			Mmap.put("getstudentresultstatuslist", common.getstudentresultstatuslist(sessionFactory));
			Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory, uni_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Student_Rsult_Report_tiles");
	}

	@PostMapping("/getFilter_Student_result_report")
	public @ResponseBody List<Map<String, Object>> getFilter_Student_result_report(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, HttpSession session) throws ParseException {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		System.err.println();
		return SRD.getFilter_Student_result_report(startPage, pageLength, Search, orderColunm, orderType, degree_id,
				professional_id, date_of_exam, institute_id, result_status, role,institute_uid);
	}

	@PostMapping("/getTotal_Student_result_report_dataCount")
	public @ResponseBody long getTotal_Student_result_report_dataCount(HttpSession sessionUserId, String Search,
			String degree_id, String professional_id, String date_of_exam, String institute_id, String result_status,
			HttpSession session) throws ParseException {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return SRD.getTotal_Student_result_report_dataCount(Search, degree_id, professional_id, date_of_exam, institute_id,
				result_status, role,institute_id1);
	}

	@RequestMapping(value = "/getDegreeLFromInstituteExam", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeLFromInstituteExam(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		institute_id = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {

			list = SRD.getDegreeLFromInstituteExam(institute_id, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getStudent_Result_Report_Excel", method = RequestMethod.POST)
	public ModelAndView getStudent_Result_Report_Excel(HttpSession session, HttpServletRequest request,ModelMap Mmap,RedirectAttributes ra)
			throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Student_Result_Declaration_Report", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();

		String degree_id = request.getParameter("degree_id1");
		String professional_id = request.getParameter("professional_id1");
		String date_of_exam = request.getParameter("date_of_exam1");
		String result_status = request.getParameter("result_status1");
		String institute_id1 = request.getParameter("institute_id1");
		
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);

		ArrayList<ArrayList<String>> listofdata = SRD.getStudent_Result_Report_Excel(degree_id, professional_id,
				date_of_exam, institute_id1, result_status, role, "",institute_uid);

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Degree");
		TH.add("Professional");
		if(!role.toLowerCase().contains("institute")) {
		TH.add("Institute");
		}
		TH.add("Ayush Id");
		TH.add("Student Name");
		TH.add("Date of Exam");
		
		if(listofdata.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Student_Result_Declaration_Report");
		}
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Student_Excel_Report("L", TH, listofdata, Heading, username,role), "userList",
				listexport);
	}

	@RequestMapping(value = "/Student_Result_Report_PDF", method = RequestMethod.POST)
	public ModelAndView Student_Result_Report_PDF(ModelMap Mmap,HttpSession session, HttpServletRequest request,String role,RedirectAttributes ra)
			throws ParseException {
		
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Student_Result_Declaration_Report", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();

		String role1 = session.getAttribute("role").toString();
		String degree_id = request.getParameter("degree_id2");
		String professional_id = request.getParameter("professional_id2");
		String date_of_exam = request.getParameter("date_of_exam2");
		String result_status = request.getParameter("result_status2");
		String institute_id2 = request.getParameter("institute_id2");
		
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);


		List<ArrayList<String>> nonlecact1 = SRD.getStudent_Result_Report_Excel(degree_id, professional_id,
				date_of_exam, institute_id2, result_status, role1, "",institute_uid);

//				ArrayList<ArrayList<String>> t1copolink2 = Edao.table1_co_po_link(course_id);
//				System.err.println("new_list2-------------"+t1copolink2);

		int total = nonlecact1.size();
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		TH.add("Degree");
		TH.add("Professional");
		if(!role1.toLowerCase().contains("institute")) {
		TH.add("Institute");
		}
		TH.add("Ayush Id");
		TH.add("Student Name");
		TH.add("Date of Exam");
        
		
		if(nonlecact1.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Student_Result_Declaration_Report");
		}
		
		String Heading = "\nIn Inspection";
		
		return new ModelAndView(new Student_PDF_Report("L", TH, Heading, username, total,role1), "userList", nonlecact1);
		
	}
}