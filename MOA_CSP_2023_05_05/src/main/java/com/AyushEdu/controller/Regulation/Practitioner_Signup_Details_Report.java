package com.AyushEdu.controller.Regulation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_DETAILS_A;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_PRACTITIONER_Signup_Details;
import com.AyushEdu.config.DynamicMailFormatGen;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.config.MailDefine;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Pract_Signup_Details_ReportDao;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Practitioner_Signup_Details_Report {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Pract_Signup_Details_ReportDao  PSdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@Autowired
	EmailConfig emailsend;

	@RequestMapping(value = "admin/Pract_Signup_Details_ReportUrl", method = RequestMethod.GET)
	public ModelAndView Pract_Signup_Details_ReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {

			//SECURITY - POOJA
//			if(request.getHeader("Referer") == null ) { 
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("intern_Search_State_pracUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		System.err.println("username-------"+username);
			int data = (int) sessionHQ.createQuery("select state_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			
			
			System.err.println("dataaaaaaaa-------"+data);
		Mmap.put("state_id", data);
 		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
 		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
	    Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
	    Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES
		} catch (Exception e) {
				e.printStackTrace();
		}
		return new ModelAndView("Pract_Signup_Details_Report_Tiles");
	}
	
	@PostMapping("/getFilter_Pract_Signup_Details_Report_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Pract_Signup_Details_Report_data(int startPage, int pageLength, 
			String orderColunm, String orderType, String Search,String name,String dob, String aadhar_card ,String email,
			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String institute_status)
	{
		return PSdao.DataTablePract_Signup_Details_ReportDataList(startPage, pageLength, orderColunm, orderType, Search,name, 
				dob ,aadhar_card,email,mobile_no,upload_date,gender, internship_completion_date,reg_state,state_reg_num, institute_status);
	}

	@PostMapping("/getPract_Signup_Details_ReportCount")
	public @ResponseBody long getPract_Signup_Details_ReportCount( String orderColunm, String orderType,
			String Search,String name,String dob, String aadhar_card ,String email,String mobile_no ,String upload_date,
			String gender,String internship_completion_date,String reg_state ,String state_reg_num,String institute_status) 
    {
		return PSdao.DataTablePract_Signup_Details_ReportTotalCount( orderColunm , orderType,Search, name, dob ,aadhar_card,email,mobile_no,upload_date,gender,
				internship_completion_date,reg_state,state_reg_num, institute_status);
	}
	
	@RequestMapping(value = "/Approve_Pract_SignUp_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String>  Approve_Pract_SignUp_Data(String status,HttpSession session,String a,HttpServletRequest request) throws ParseException {	
		System.err.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+a);
		String username = session.getAttribute("username").toString();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String role_type=request.getParameter("role_type");
		String[] id_list = a.split(":");
		System.err.println("check id list"+id_list);
		int id =0;
		
		List<String> list2 = new ArrayList<String>();
		
		for (int i = 0; i < id_list.length; i++) {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			id =Integer.parseInt(id_list[i]);
//			reg r = = (reg) sessionHQL.get(reg.class, 6);
			
			REG_NCH_PRACTITIONER_Signup_Details td2 = (REG_NCH_PRACTITIONER_Signup_Details) sessionHQL.get(REG_NCH_PRACTITIONER_Signup_Details.class,(id));
			UserLogin p = new UserLogin();
			
			REG_NCH_DETAILS_A reg_a= new REG_NCH_DETAILS_A();
			Date date_of_admission = td2.getDate_of_admission();
			Calendar caladm = Calendar.getInstance();
			caladm.setTime(date_of_admission);
			String formatedDateadmission = (String.format("%2s", caladm.get(Calendar.DATE)) + "/" + String.format("%2s", (caladm.get(Calendar.MONTH) + 1)) + "/"
			+ String.format("%4s", caladm.get(Calendar.YEAR))).replace(" ", "0");
			reg_a.setAdmission_date(formatedDateadmission);
		 
			reg_a.setName(td2.getName()) ;
			p.setUserName(td2.getName());
			p.setLogin_name(td2.getName());
			
			Date date = td2.getDob();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = (String.format("%2s", cal.get(Calendar.DATE)) + "/" + String.format("%2s", (cal.get(Calendar.MONTH) + 1)) + "/"
			+ String.format("%4s", cal.get(Calendar.YEAR))).replace(" ", "0");
			
			reg_a.setDob(formatedDate);
			
			reg_a.setEmail(td2.getEmail());
			p.setEmail_id(td2.getEmail());
			
			reg_a.setMobile_no(td2.getMobile_no());
			p.setMobile_no(td2.getMobile_no());
			
			reg_a.setGender(td2.getGender());
			
			reg_a.setAadhar_card(td2.getAadhar_card());
			p.setAadhar_no(td2.getAadhar_card());
			p.setUserName(td2.getAadhar_card());
			
//			---addmission date,degree,Enrollment_No, column not in practitionner signup detalis
			
			Date date2 = td2.getInternship_completion_date();
			if(date2!=null) {
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(date2);
			String formatedDate3 = (String.format("%2s", cal.get(Calendar.DATE)) + "/" + String.format("%2s", (cal.get(Calendar.MONTH) + 1)) + "/"
			+ String.format("%4s", cal.get(Calendar.YEAR))).replace(" ", "0");
			
			reg_a.setInternship_completion_date(formatedDate3);
			}else { 
				reg_a.setInternship_completion_date("");
			}
			
			reg_a.setSystem(td2.getSystem_id());
			
			reg_a.setUniversity_userid(td2.getUniversity_id());
			p.setUniversity_id(td2.getUniversity_id());
			
			reg_a.setInstitude_id(td2.getInstitute_id());
			p.setInstitute_id(td2.getInstitute_id());
			
			reg_a.setReg_state(td2.getReg_state());
			p.setState_id(td2.getReg_state());
			
			reg_a.setState_reg_num(td2.getState_reg_num());
			
				sessionHQL.save(reg_a);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode("Bisag@123");
				
				p.setPassword(hashedPassword);
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
//				p.setInstitute_id(Integer.parseInt(userid));
//				p.setCountry_id(Integer.parseInt("5"));
//				p.setState_id(Integer.parseInt("12"));
				p.setCreated_on(new Date());
				p.setCreated_by(username);
				UserRole role_tbl = new UserRole();

				int did = (Integer) sessionHQL.save(p);
				role_tbl.setRoleId(27);
 
				role_tbl.setUserId(did);
				sessionHQL.save(role_tbl);
				sessionHQL.flush();
				sessionHQL.clear();
			 
			tx.commit();			
			System.err.println("----------------ID----------->    "+id);
			list2.add(PSdao.Approve_Pract_SignUp_ReportData( a, username, userId, id));
			DynamicMailFormatGen DB = new DynamicMailFormatGen();
////		System.err.println("====HET======"+details.get(i).get("email_id").toString());
//
		MailDefine ml = new MailDefine();
		DB.setEmail(td2.getEmail());
		ml.setRegistration_header_NCH_practitioner(td2.getAadhar_card(), "Bisag@123");
		DB.setMainBody(ml.getRegistration_header_NCH_practitioner());
		DB.setFooter(ml.getRegistration_body_NCH_practitioner());
		DB.setRole("NCH");
		DB.setName("Practitioner");
		DB.setSubject("Registration Successfull");
//		emailsend.SendMailForAyushEdu(request, DB);

		}
		
		return list2;
	}
	
	
	 
	
//	@RequestMapping(value = "/Reject_Pract_SignUp_Data" , method = RequestMethod.POST)
//	public @ResponseBody List<String>  Reject_Pract_SignUp_Data(String status,HttpSession session,String a,HttpServletRequest request) throws ParseException {	
//		
//		String username = session.getAttribute("username").toString();
//		String userId = session.getAttribute("userId_for_jnlp").toString();
//		String[] id_list = a.split(":");
//		int id =0;
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		
////		==22-02-2022
//		List<String> list3 = new ArrayList<String>();
//		for (int i = 0; i < id_list.length; i++) {
//			id =Integer.parseInt(id_list[i]);
//			System.err.println("----------------ID----------->    "+id);
//			list3.add(PSdao.Reject_Pract_SignUp_ReportData( a, username, userId, id));
//			
//			REG_NCH_PRACTITIONER_Signup_Details td2 = (REG_NCH_PRACTITIONER_Signup_Details) sessionHQL.get(REG_NCH_PRACTITIONER_Signup_Details.class,(id));
////			DynamicMailFormatGen DB = new DynamicMailFormatGen();
//////			System.err.println("====HET======"+details.get(i).get("email_id").toString());
////			MailDefine ml = new MailDefine();
////			DB.setEmail(td2.getEmail());
////			DB.setMainBody(ml.getRegistration_Reject_header_NCH_practitioner());
//////			getRegistration_Reject_body_NCH_practitioner
////			DB.setFooter(ml.getRegistration_Reject_body_NCH_practitioner());
////			DB.setRole("NCH");
////			DB.setName("Practitioner");
////			DB.setSubject("Registration Successfull");
////			emailsend.SendMailNew(request, DB);
//		}
//		return list3;
//	}
	
	@RequestMapping(value = "/Reject_Fromsignupdetails_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_Fromsignupdetails_Data(String a,String status,String tempSt,HttpSession session) throws ParseException {	
		System.err.println("dao------------cont");
		String username = session.getAttribute("username").toString();
		String[] id_list = a.split(":");
//		String[] id_list2 = upto2.split(":");
		String[] tempSt2 = tempSt.split(",");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		System.out.println("id_list============= "+id_list);
		
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			System.out.println("id_list============= "+id_list[i]);
			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(PSdao.Approve_Pract_SignUp_ReportData33(Integer.toString(id),status,username,tempSt2[i]));
		}
		return list2;
	}
	 @RequestMapping(value = "/Pract_Signup_Details_Report_Excel", method = RequestMethod.POST)
	public ModelAndView Pract_Signup_Details_Report_Excel(HttpSession session, HttpServletRequest request)
			throws ParseException {

		String name = request.getParameter("name2");
		String dob = request.getParameter("dob2");
		String aadhar_card = request.getParameter("aadhar_card2");
		String email = request.getParameter("email2");
		String mobile_no = request.getParameter("mobile_no2");
		String upload_date = request.getParameter("upload_date2");
		String gender = request.getParameter("gender2");
		String internship_completion_date = request.getParameter("internship_completion_date2");
		String reg_state = request.getParameter("reg_state2");
		String state_reg_num =request.getParameter("state_reg_num2");
		String institute_status = request.getParameter("institute_status2");
		String Search = request.getParameter("Search2");
		
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
		
		
		list=PSdao.getPract_Signup_Details_Report_Excel(Search,name, dob ,aadhar_card,email,mobile_no,upload_date,gender,
				internship_completion_date,reg_state,state_reg_num,institute_status);
		

		int total = list.size();
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No."); //0
		TH.add("Name");//1
		TH.add("Date of Birth");//2
		TH.add("Aadhar Card");//3
		TH.add("Email");//4
		TH.add("Mobile No.");//5
		TH.add("Upload Date");//6
		TH.add("Gender");//8
		TH.add("Internship Completion Date");//9
		TH.add("Registered State");//10
		TH.add("State Register No.");//11
		
		String Heading = "\nIn Inspection";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Prac_Signup_Details_Excel("L", TH, list, Heading, username), "userList",
				list);
	}
	 
	 
	 @RequestMapping(value = "/get_Parctname_signup_by_Reject_id", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_Parctname_by_Reject_id(String id) {
	    	ArrayList<ArrayList<String>> data = PSdao.get_Parctname_reports_by_Reject_idata(id);
	    	System.err.println("hiiiiiii");
	    	return data;
	 	}
	
	
}