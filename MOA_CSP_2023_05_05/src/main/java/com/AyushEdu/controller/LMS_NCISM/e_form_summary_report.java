package com.AyushEdu.controller.LMS_NCISM;

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

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_NCISM.E_Form_Summary_reportDaoImpl;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class e_form_summary_report {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private E_Form_Summary_reportDaoImpl eform;
	@Autowired
	CommonController common;
	@Autowired
	private Commondao cd;
	
	//1
	@RequestMapping(value = "/e_form_summaryReportUrl", method = RequestMethod.GET)
	public ModelAndView e_form_summaryReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
	
		String role = session.getAttribute("role").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryReport("0","0","0","0",role,roleStaff_lvl));
		return new ModelAndView("E_Form_Report_Summary_Tiles");

	}
	@RequestMapping(value = "/Search_e_form_summaryReportUrl", method = RequestMethod.POST)

	public ModelAndView Search_e_form_summaryReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute1,String category1,String authority1,String quota1) {



//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		Mmap.put("institute1", institute1);
		Mmap.put("category1", category1);
		Mmap.put("authority1",authority1);
		Mmap.put("quota1", quota1);
		
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryReport(institute1,category1,authority1,quota1,role,roleStaff_lvl));
		return new ModelAndView("E_Form_Report_Summary_Tiles");

	}
	
	
	
	//2
	@RequestMapping(value = "/e_form_summaryVacantReportUrl", method = RequestMethod.GET)
	public ModelAndView e_form_summaryVacantReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String role = session.getAttribute("role").toString();
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryVacantReport("0","0","0","0",role,roleStaff_lvl));
		return new ModelAndView("E_Form_Vacant_Seat_Report_Tiles");

	}
	@RequestMapping(value = "/Search_e_form_summaryVacantReportUrl", method = RequestMethod.POST)

	public ModelAndView Search_e_form_summaryVacantReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute1,String category1,String authority1,String quota1) {

		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("institute1", institute1);
		Mmap.put("category1", category1);
		Mmap.put("authority1",authority1);
		Mmap.put("quota1", quota1);
		
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryVacantReport(institute1,category1,authority1,quota1,role,roleStaff_lvl));
		return new ModelAndView("E_Form_Vacant_Seat_Report_Tiles");

	}
	
	@RequestMapping(value = "/getSummaryVaccantReport_Eform_Excel", method = RequestMethod.POST)
	public ModelAndView getstudentdetails_Report_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute2,String category2,String authority2,String quota2)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryVacantReport(institute2,category2,authority2,quota2,role,roleStaff_lvl);


//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("100% Filled College");
		TH.add("0% Filled College");
		TH.add("Greater than 0 and Less then 100");
		
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Student_E_Form_Report_Excel_Download("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}
	
	
	@RequestMapping(value = "/getSummaryReport_Eform_Excel", method = RequestMethod.POST)
	public ModelAndView getSummaryReport_Eform_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute2,String category2,String authority2,String quota2)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();

		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryReport(institute2,category2,authority2,quota2,role,roleStaff_lvl);


//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");
		
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("Sanction Seats");
		TH.add("Alloted Seats");
		TH.add("Vacant Seats");
		
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Student_E_Form_Report_Excel_Download("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}
	@RequestMapping(value = "/getSummaryReport_Eform_Pdf", method = RequestMethod.POST)
	public ModelAndView getSummaryReport_Eform_Pdf(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute3,String category3,String authority3,String quota3)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryReport(institute3,category3,authority3,quota3,role,roleStaff_lvl);




		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();


		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("Sanction Seats");
		TH.add("Alloted Seats");
		TH.add("Vacant Seats");
		
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nCope Code";
		
	
		return new ModelAndView(new Student_E_Form_Report_Pdf_Download("L", TH, Heading, eform.getEformSummaryReport(institute3,category3,authority3,quota3,role,roleStaff_lvl).size()
				, username1), "userList", listofdata);
	}
	@RequestMapping(value = "/getSummaryVacantReport_Eform_Pdf", method = RequestMethod.POST)
	public ModelAndView getSummaryVacantReport_Eform_Pdf(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute3,String category3,String authority3,String quota3)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryVacantReport(institute3,category3,authority3,quota3,role,roleStaff_lvl);




		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();


		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("100% Filled College");
		TH.add("0% Filled College");
		TH.add("Greater than 0 and Less then 100");
		
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nCope Code";
		
	
		return new ModelAndView(new Student_E_Form_Report_Pdf_Download("L", TH, Heading, eform.getEformSummaryReport(institute3,category3,authority3,quota3,role,roleStaff_lvl).size()
				, username1), "userList", listofdata);
	}
	
	@RequestMapping(value = "/e_form_PG_summaryReportUrl", method = RequestMethod.GET)
	public ModelAndView e_form_PG_summaryReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
	
		String role = session.getAttribute("role").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("getDegreePG", cd.getALLPGDegreeList());
		Mmap.put("getintake_typelist", common.getintake_typelist());
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryReportPG("0","0","0","0",role,roleStaff_lvl,"0","0","0"));
		return new ModelAndView("E_Form_PG_Report_Summary_Tiles");

	}
	
	@PostMapping("/getPGSubjectbyDegree")
	public @ResponseBody ArrayList<ArrayList<String>> getPGSubjectbyDegree(String degree) {
		return cd.getALLPGSubjectbyDegree(degree);
	}
	
	@RequestMapping(value = "/Search_e_form_PG_summaryReportUrl", method = RequestMethod.POST)

	public ModelAndView Search_e_form_PG_summaryReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			String institute1,String category1,String authority1,String quota1,
			String degree1,String subject1,String intake1) {



//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		Mmap.put("institute1", institute1);
		Mmap.put("category1", category1);
		Mmap.put("authority1",authority1);
		Mmap.put("quota1", quota1);
		Mmap.put("degree1", degree1);
		Mmap.put("subject1",subject1);
		Mmap.put("intake1", intake1);
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("getDegreePG", cd.getALLPGDegreeList());
		Mmap.put("getintake_typelist", common.getintake_typelist());
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryReportPG(institute1,category1,authority1,quota1,role,roleStaff_lvl,degree1,subject1,intake1));
		return new ModelAndView("E_Form_PG_Report_Summary_Tiles");

	}
	
	@RequestMapping(value = "/getSummaryReport_Eform_PG_Excel", method = RequestMethod.POST)
	public ModelAndView getSummaryReport_Eform_PG_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute2,String category2,String authority2,String quota2,String degree2,String subject2,String intake2)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();

		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryReportPG(institute2,category2,authority2,quota2,role,roleStaff_lvl,degree2,subject2,intake2);


//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");
		
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("System Name");
		TH.add("Total College");
		TH.add("Sanction Seats");
		TH.add("Alloted Seats");
		TH.add("Vacant Seats");
		
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Student_E_Form_Report_Excel_Download("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}
	
	@RequestMapping(value = "/getSummaryReport_Eform_PG_Pdf", method = RequestMethod.POST)
	public ModelAndView getSummaryReport_Eform_PG_Pdf(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute3,String category3,String authority3,String quota3,String degree3,String subject3,String intake3)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryReportPG(institute3,category3,authority3,quota3,role,roleStaff_lvl,degree3,subject3,intake3);




		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();


		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("Sanction Seats");
		TH.add("Alloted Seats");
		TH.add("Vacant Seats");
		
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nCope Code";
		
	
		return new ModelAndView(new Student_E_Form_Report_Pdf_Download("L", TH, Heading, listofdata.size()
				, username1), "userList", listofdata);
	}
	
	@RequestMapping(value = "/e_form_summary_PG_VacantReportUrl", method = RequestMethod.GET)
	public ModelAndView e_form_summary_PG_VacantReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String role = session.getAttribute("role").toString();
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("getDegreePG", cd.getALLPGDegreeList());
		Mmap.put("getintake_typelist", common.getintake_typelist());
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryVacantReportPG("0","0","0","0",role,roleStaff_lvl,"0","0","0"));
		return new ModelAndView("E_Form_PG_Vacant_Seat_Report_Tiles");

	}
	
	@RequestMapping(value = "/Search_e_form_PG_summaryVacantReportUrl", method = RequestMethod.POST)

	public ModelAndView Search_e_form_PG_summaryVacantReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute1,String category1,
			String authority1,String quota1,
			String degree1,String subject1,String intake1) {

		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
//		if (request.getHeader("Referer") == null) {
////		 session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		Mmap.put("institute1", institute1);
		Mmap.put("category1", category1);
		Mmap.put("authority1",authority1);
		Mmap.put("quota1", quota1);
		Mmap.put("degree1", degree1);
		Mmap.put("subject1",subject1);
		Mmap.put("intake1", intake1);
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getcategorylist", common.getcastcategorylist(sessionFactory));
		Mmap.put("getCounselingAuthoList", common.getCounsellingAuthoList(sessionFactory));
		Mmap.put("getQuotaList", common.getQuotaListDropDown(sessionFactory));
		Mmap.put("getDegreePG", cd.getALLPGDegreeList());
		Mmap.put("getintake_typelist", common.getintake_typelist());
		Mmap.put("msg", msg);
		Mmap.put("summaryReport", eform.getEformSummaryVacantReportPG(institute1,category1,authority1,quota1,role,roleStaff_lvl,degree1,subject1,intake1));
		return new ModelAndView("E_Form_PG_Vacant_Seat_Report_Tiles");

	}
	
	@RequestMapping(value = "/getSummaryVaccantReport_Eform_PG_Excel", method = RequestMethod.POST)
	public ModelAndView getSummaryVaccantReport_Eform_PG_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute2,String category2,String authority2,String quota2,String degree2,String subject2,String intake2)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryVacantReportPG(institute2,category2,authority2,quota2,role,roleStaff_lvl,degree2,subject2,intake2);


//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("100% Filled College");
		TH.add("0% Filled College");
		TH.add("Greater than 0 and Less then 100");
		
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Student_E_Form_Report_Excel_Download("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}
	
	@RequestMapping(value = "/getSummaryVacantReport_Eform_PG_Pdf", method = RequestMethod.POST)
	public ModelAndView getSummaryVacantReport_Eform_PG_Pdf(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,
			ModelMap model, String institute3,String category3,String authority3,String quota3,String degree3,String subject3,String intake3)
			throws ParseException {
//		SECURITY -- RIDDHI 
//		 if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> listofdata = eform.getEformSummaryVacantReportPG(institute3,category3,authority3,quota3,role,roleStaff_lvl,degree3,subject3,intake3);

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Syatem Name");
		TH.add("Total College");
		TH.add("100% Filled College");
		TH.add("0% Filled College");
		TH.add("Greater than 0 and Less then 100");
		
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nCope Code";
		
	
		return new ModelAndView(new Student_E_Form_Report_Pdf_Download("L", TH, Heading, listofdata.size()
				,username1), "userList", listofdata);
	}

}
