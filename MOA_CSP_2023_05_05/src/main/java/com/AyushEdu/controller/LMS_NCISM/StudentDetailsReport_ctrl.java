package com.AyushEdu.controller.LMS_NCISM;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_NCISM.StudentDetailsReport_DAO;
import com.AyushEdu.dao.Registration.E_Form_Student_Dtl_DAO;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;

@Controller 
@RequestMapping(value = { "admin", "/", "user" })
public class StudentDetailsReport_ctrl {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Commondao cd;

	@Autowired
	AyushId_Directory_Controller dir;
	@Autowired
	EmailConfig emailsend;
	@Autowired
	E_Form_Student_Dtl_DAO edao;
	@Autowired
	Search_Student_RegistrationDao SSRnchDao;
	@Autowired
	Search_Ncism_Student_RegistrationDao SSRncismDao;
	@Autowired
	StudentDetailsReport_DAO  sddao;
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	UserLoginDAO userLoginDAO;
	
	
	@GetMapping(value = "/StudentDetailsReport_Url")
	public ModelAndView StudentDetailsReport_Url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra) {
		
			
		try {

			
//			if (request.getHeader("Referer") == null) {
//				//session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
		String role = session.getAttribute("roleid").toString();
		String rolename = session.getAttribute("role").toString();
		
		
		 model.addAttribute("msg", msg);
		 String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
			model.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

//			----------------------------------------------------------------------------------------------
			
//			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("userid========================="+userid);
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
//			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
//			model.put("list", edao.E_Form_Student_DtlData(role,institute_id));
//			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			model.put("id", institute_id);

			model.put("getMedStateName", common.getMedStateName(sessionFactory));
			model.put("getcategorylist", common.getcategoryList(sessionFactory));
			model.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			model.put("getQuotaList", common.getQuotaList(sessionFactory));
			model.put("getInsCodeListbyInsName", common.getInsCodeListbyInsName(sessionFactory,institute_id));
//			model.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
//			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

			model.put("getDegreePG", cd.getDegreeListPG(Integer.parseInt(institute_id)));
			model.put("getintake_typelist", common.getintake_typelist());
			
//			----------------------------------------------------------------------------------------------
			
//			String role = session.getAttribute("rolename").toString();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("userid========================="+userid);
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
//			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
//			model.put("list", edao.E_Form_Student_DtlData(role,institute_id));
//			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			model.put("id", institute_id);
			model.put("getMedStateName", common.getMedStateName(sessionFactory));
			model.put("getcategorylist", common.getcategoryList(sessionFactory));
			model.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			model.put("getQuotaList", common.getQuotaList(sessionFactory));
			model.put("getInsCodeListbyInsName", common.getInsCodeListbyInsName(sessionFactory,institute_id));
//			model.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
			
			model.put("getUniverCityList", cd.getUniversityNcismlist());
			model.put("getapp_instituteNameList", cd.getinstituteNcismlist());
			
			model.put("getSystemForAll", common.getSystemForAll(sessionFactory));
			
			if (role.equals("20")) {//uni ncism
				String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
				model.put("uni_id",uni_id);
			}
			
			if (role.equals("22") || role.equals("67")) {//inst ncism
				String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
				model.put("uni_id",uni_id);
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id------>   "+inst_id);
				model.put("inst_id",inst_id);
			}
			
			if (role.equals("61") || role.equals("62") || role.equals("63") || role.equals("64")) {// ayu_BOARD
				Role roleList = userLoginDAO.findRole_url(rolename);
				model.put("system_id",String.valueOf(roleList.getSystem_id()));
			}
			
		} catch (RuntimeException e) {
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		}
		return new ModelAndView("studentdetailsreport_Tiles");
}	
	
	@PostMapping("/getFilterstudentdetails_data")
	public @ResponseBody ArrayList<ArrayList<String>> getFilterstudentdetails_data(ModelMap model, Principal principal,HttpSession session,
			int startPage, int pageLength,String Search, String orderColunm, String orderType,
			String institute_id, String institute_code,String state_id,
			String name,String last_name,String mother_name,String father_name,
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority, String category_id,
			String system,String university_id,String type_of_degree,String pg_degree,String pg_subject,String pg_intake) {
//		 String instid = session.getAttribute("userId_for_jnlp").toString();
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 String role = session.getAttribute("role").toString();
		 
		 
		 System.err.println("institute_id---ctrl---->   "+institute_id);
		 
		 
		 
		return sddao.DataTablestudentdetailsDataList(startPage, pageLength, Search, orderColunm, orderType,userid,
				institute_id,  institute_code, state_id,
				name,last_name, mother_name,father_name,dob,email,
				neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks, quota_id,
				 counselling_authority,  category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
	}

	@PostMapping("/getTotalstudentdetailsCount")
	public @ResponseBody long getTotalstudentdetailsCount(ModelMap model, Principal principal,
			HttpSession sessionUserId, String Search,String institute_id, String institute_code,String state_id,
			String name,String last_name,String mother_name,String father_name,
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority, String category_id,
			String system,String university_id,String type_of_degree,String pg_degree,String pg_subject,String pg_intake) {
//		 String instid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		 String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		 String role = sessionUserId.getAttribute("role").toString();
		 
		 
		 System.err.println("institute_id---ctrl--count-->   "+institute_id);
		 
		 
		return sddao.DataTablestudentdetailsTotalCount(Search, userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name,
				 dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				 quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);

	}
	
	@RequestMapping(value = "/getdegreelistby_system1", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegreelistby_system1(String system_name)  {
		
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list =null;
		try {
			list =  common.getDegreeListbysystem(sessionFactory,system_name);
		} catch (RuntimeException e) {
			// e.printStackTrace();
		}
		return list;
	}
	
	

@RequestMapping(value = "/getstudentdetails_Report_Excel", method = RequestMethod.POST)
	public ModelAndView getstudentdetails_Report_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,ModelMap model, String Search)
			throws ParseException {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//		String role = session.getAttribute("role").toString();
//		System.err.println("roleid================" + role.split("_")[1].toString());
//		String userid2 = sessionUserId.getAttribute("userId_for_jnlp").toString();
//		String name_of_institute = request.getParameter("name_of_institute1");
		String institute_id = request.getParameter("institute_id1");
		String institute_code = request.getParameter("institute_code1");
		String state_id = request.getParameter("state_id1");
		String name = request.getParameter("name1");
		String last_name = request.getParameter("last_name1");
		String mother_name = request.getParameter("mother_name1");
		String father_name = request.getParameter("father_name1");
		String dob = request.getParameter("dob1");
		String email = request.getParameter("email1");
		String system = request.getParameter("system1");
//		String degree = request.getParameter("degree1");
		String neet_application_no = request.getParameter("neet_application_no1");
		String neet_roll_no = request.getParameter("neet_roll_no1");
		String neet_rank = request.getParameter("neet_rank1");
		String neet_percentile = request.getParameter("neet_percentile1");
		String neet_marks = request.getParameter("neet_marks1");
		String quota_id = request.getParameter("quota_id1");
		String counselling_authority = request.getParameter("counselling_authority1");
		String category_id = request.getParameter("category_id1");
		String university_id = request.getParameter("university_id1");
		String type_of_degree = request.getParameter("type_of_degree1");
		String pg_degree = request.getParameter("pg_degree1");
		String pg_subject = request.getParameter("pg_subject1");
		String pg_intake = request.getParameter("pg_intake1");
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		
		List<ArrayList<String>> listofdata = sddao.getstudentdetails_Report_Excel(Search, userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name,
				 dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				 quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);


//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("ser No.");
		TH.add("Name of Institute");
		TH.add("Institute Id");
		TH.add("State");
		TH.add("First Name");
		TH.add("Last Name");
		TH.add("Mother's Name");
		TH.add("Father's Name");
		TH.add("Date Of Birth");
		TH.add("Email Id");
		
//		if(pg_intake.equals("1")) {
//			TH.add("NEET Application No");
//			TH.add("NEET Roll No");
//			TH.add("ALL INDIA NEET Rank");
//			TH.add("NEET Percentile");
//		}
		
		if(pg_intake.equals("2")) {
			TH.add("AIAPGET Application No");
			TH.add("AIAPGET Roll No");
			TH.add("ALL INDIA AIAPGET Rank");
			TH.add("AIAPGET Percentile");
		}else {
			TH.add("NEET Application No");
			TH.add("NEET Roll No");
			TH.add("ALL INDIA NEET Rank");
			TH.add("NEET Percentile");
		}
		
		
		TH.add("Marks Obtained");
		TH.add("Quota");
		TH.add("Counselling Authority");
		TH.add("Category");
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Studentdetails_Report_Excell_Report("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}





@RequestMapping(value = "/Admission_Student_Details_PDF", method = RequestMethod.POST)
	public ModelAndView Admission_Student_Details_PDF(String Search,HttpSession session,HttpSession sessionUserId, HttpServletRequest request,ModelMap Mmap,RedirectAttributes ra)
			throws ParseException {
		
			
		
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			 String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
			 String role = sessionUserId.getAttribute("role").toString();
			String institute_id = request.getParameter("institute_id2");
			String institute_code = request.getParameter("institute_code2");
			String state_id = request.getParameter("state_id2");
			String name = request.getParameter("name2");
			String last_name = request.getParameter("last_name2");
			String mother_name = request.getParameter("mother_name2");
			String father_name = request.getParameter("father_name2");
			String dob = request.getParameter("dob2");
			String email = request.getParameter("email2");
			String system = request.getParameter("system2");
//			String degree = request.getParameter("degree2");
			String neet_application_no = request.getParameter("neet_application_no2");
			String neet_roll_no = request.getParameter("neet_roll_no2");
			String neet_rank = request.getParameter("neet_rank2");
			String neet_percentile = request.getParameter("neet_percentile2");
			String neet_marks = request.getParameter("neet_marks2");
			String quota_id = request.getParameter("quota_id2");
			String counselling_authority = request.getParameter("counselling_authority2");
			String category_id = request.getParameter("category_id2");
			String university_id = request.getParameter("university_id2");
			String type_of_degree = request.getParameter("type_of_degree2");
			String pg_degree = request.getParameter("pg_degree2");
			String pg_subject = request.getParameter("pg_subject2");
			String pg_intake = request.getParameter("pg_intake2");
			
			List<ArrayList<String>> nonlecact1 = sddao.getstudentdetails_Report_Excel(Search, userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name,
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
			

				int total = nonlecact1.size();
				List<String> TH1 = new ArrayList<String>();

		TH1.add("Ser No.");
		TH1.add("Name of Institute");
		TH1.add("Institute Id");
		TH1.add("State");
		TH1.add("First Name");
		TH1.add("Last Name");
		TH1.add("Mother's Name");
		TH1.add("Father's Name");
		TH1.add("Date of Birth");
		TH1.add("Eamil Id");
		
//		if(pg_intake.equals("1")) {
//			TH1.add("NEET Application No");
//			TH1.add("NEET Roll No");
//			TH1.add("ALL INDIA NEET Rank");
//			TH1.add("NEET Percentile");
//		}
		
		if(pg_intake.equals("2")) {
			TH1.add("AIAPGET Application No");
			TH1.add("AIAPGET Roll No");
			TH1.add("ALL INDIA AIAPGET Rank");
			TH1.add("AIAPGET Percentile");
		}else {
			TH1.add("NEET Application No");
			TH1.add("NEET Roll No");
			TH1.add("ALL INDIA NEET Rank");
			TH1.add("NEET Percentile");
		}
		
		TH1.add("Marks Obtained");
		TH1.add("Quota");
		TH1.add("Counselling Authority");
		TH1.add("Category");
        
		
		if(nonlecact1.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:StudentDetailsReport_Url");
		}
		
      String Heading = "\nCope Code";
		
		String username1 = session.getAttribute("username").toString();
		
		return new ModelAndView(new Admission_Student_Report_PDF("L", TH1, Heading, total, username1), "userList", nonlecact1);
		
	}
	
	
}
