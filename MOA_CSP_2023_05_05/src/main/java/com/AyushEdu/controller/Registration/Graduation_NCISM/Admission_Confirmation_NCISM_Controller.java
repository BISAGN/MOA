package com.AyushEdu.controller.Registration.Graduation_NCISM;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Admission_Confirmation_NCISMDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Admission_Confirmation_NCISM_Controller {
	
	@Autowired
	private personaldetailsDAO da;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public Admission_Confirmation_NCISMDao ACNDao;


/////////////////////////////////////////////////Admission Confirmation OPEN /////////////////////////////////////////////////////////////////////
	
//	@RequestMapping(value = "/Admission_Confirmation_Url",method = RequestMethod.GET)
//	public ModelAndView Admission_Confirmation_Url(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,Principal principal)  {
//		return new ModelAndView("Admission_Confirmation_Tiles");
//
//	}
	

		@RequestMapping(value = "/Admission_Confirmation_NCISM_Url", method = RequestMethod.GET)
		public ModelAndView Admission_Confirmation_NCISM_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Admission_Confirmation_NCISM_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
//			String userid = session.getAttribute("userId").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			
			System.err.println("userid------->    "+userid);
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			 List<Map<String, Object>> list =   ACNDao.AdmissionConfirmationSearchReport1(userid, "", "", "","");
				Mmap.put("list", list);
		
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				Mmap.put("msg", msg);
				return new ModelAndView("Admission_Confirmation_NCISM_Tiles");
//			}
		}
		
		@RequestMapping(value = "/getSearch_StatusVise1", method = RequestMethod.POST)
		public ModelAndView getSearch_StatusVise1(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
		
		@RequestParam(value = "userid1", required = false) String userid1,
		@RequestParam(value = "dob1", required = false) String dob,
		@RequestParam(value = "name1", required = false) String name,
		@RequestParam(value = "aadhar_card1", required = false) String aadhar_card,
		@RequestParam(value = "verified_status1", required = false) String verified_status) throws SQLException{
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Admission_Confirmation_NCISM_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
		 List<Map<String, Object>> list =   ACNDao.AdmissionConfirmationSearchReport1(userid,  dob,  name,  aadhar_card, verified_status);
			Mmap.put("list", list);
				
				Mmap.put("userid", userid);
				Mmap.put("dob", dob);
				Mmap.put("name", name);
				Mmap.put("aadhar_card", aadhar_card);
				Mmap.put("verified_status", verified_status);
				
		
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				Mmap.put("msg", msg);
				return new ModelAndView("Admission_Confirmation_NCISM_Tiles");
//			}
		}

		@RequestMapping(value = "/Approve_From_Admission_Confirmation_Student_Data1" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_From_Admission_Confirmation_Student_Data1(String a, String email_id ,String notified,HttpSession session) throws ParseException {	
		//	SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
			String username = session.getAttribute("username").toString();
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println("-------------------.useri"+userid);
			String[] id_list = a.split(":");
//			String[] email_list = email_id.split(":");
//			String[] notif_list = notified.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			String email = "" ;
			String notif = "" ;
			String date;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
//				email  =  (email_list[i]);
//				notif  =  (notif_list[i]);
				 
				list2.add(ACNDao.Approve_From_Admission_Confirmation_Student_Data1(Integer.toString(id),username, session));
				
//				if(notified.equals("1")) {
//					SendRegMail(email);
//				}
				
				//end
			}
			
			
			return list2;
		}
	

		@RequestMapping(value = "/Reject_From_Admission_Confirmation_Student_Data1" , method = RequestMethod.POST)
		public @ResponseBody List<String> Reject_From_Admission_Confirmation_Student_Data1(String a,HttpSession session) throws ParseException {	
			String username = session.getAttribute("username").toString();
			String userId_reject = session.getAttribute("userId").toString();
			
			String[] id_list = a.split(":");
//			String[] id_list2 = upto2.split(":");
//			String[] tempSt2 = tempSt.split(",");
			List<String> list2 = new ArrayList<String>();
			int id = 0;
			String date;
			
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
//				date = id_list2[i];
//				System.out.println("remarks======================== "+tempSt2[i]);
				list2.add(ACNDao.Reject_From_Admission_Confirmation_Student_Data1(Integer.toString(id),username,userId_reject, session));
			}
			return list2;
		}

	
		
		@RequestMapping(value = "/Enable_to_Edit_Admission_Student_NCISM_ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Enable_to_Edit_Admission_Student_NCISM_ctrl(String a,HttpSession session){	
			
			System.err.println("   a------------>    "+a);
			
			
			
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH----------------ID-------------------------"+id);
				list2.add(ACNDao.Enable_to_Edit_Admission_Student_NCISM_Data(String.valueOf(id),"", session));
			}
			return list2;
		}
		
		
		
		
		
		@RequestMapping(value = "/Late_Admission_Confirmation_NCISM_Url", method = RequestMethod.GET)
		public ModelAndView Late_Admission_Confirmation_NCISM_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Late_Admission_Confirmation_NCISM_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String role = session.getAttribute("role").toString();
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			System.err.println("String roleid = session.getAttribute(\"roleid\").toString();------------>    "+roleid);
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			
			
		if (role.equals("NCISM")) {
			List<Map<String, Object>> list =  ACNDao.Late_AdmissionConfirmation_NCISMSearchReport(userid, "", "", "","","6",role);
			 Mmap.put("list", list);
			 Mmap.put("late_admission_status", "6");
			}	
				else {
					List<Map<String, Object>> list =  ACNDao.Late_AdmissionConfirmation_NCISMSearchReport(userid, "", "", "","","",role);	
					Mmap.put("list", list);
					 Mmap.put("late_admission_status", "1");
				}
		
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				Mmap.put("msg", msg);
				return new ModelAndView("Late_Admission_Confirmation_NCISM_Tiles");
//			}
		}
		
		@RequestMapping(value = "/admin/Late_Admission_Confirmation_NCISM_Search", method = RequestMethod.POST)
		public ModelAndView Late_Admission_Confirmation_NCISM_Search(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
		
		@RequestParam(value = "userid1", required = false) String userid2,
		@RequestParam(value = "dob1", required = false) String dob,
		@RequestParam(value = "name1", required = false) String name,
		@RequestParam(value = "aadhar_card1", required = false) String aadhar_card,
		@RequestParam(value = "verified_status1", required = false) String verified_status,
		@RequestParam(value = "late_admission_status1", required = false) String late_admission_status) throws SQLException{
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Late_Admission_Confirmation_NCISM_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String role = session.getAttribute("role").toString();
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			if (role.equals("NCISM") && late_admission_status =="") {
				late_admission_status ="6";
			}
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			 List<Map<String, Object>> list =   ACNDao.Late_AdmissionConfirmation_NCISMSearchReport(userid,  dob,  name,  aadhar_card, verified_status,late_admission_status,role);
				Mmap.put("list", list);
				Mmap.put("userid", userid);
				Mmap.put("dob", dob);
				Mmap.put("name", name);
				Mmap.put("aadhar_card", aadhar_card);
				Mmap.put("verified_status", verified_status);
				Mmap.put("late_admission_status", late_admission_status);
				
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				
				Mmap.put("msg", msg);
				return new ModelAndView("Late_Admission_Confirmation_NCISM_Tiles");
//			}
		}
		
		@RequestMapping(value = "/Approve_Student_Admission_By_Commission_NCISM_Ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_Student_Admission_By_Commission_NCISM_Ctrl(String a,HttpSession session){
			
			System.err.println("a------------>      "+a);
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH-----------ID----------->    "+id);
				list2.add(ACNDao.Approve_Student_Admission_By_Commission_NCISM_DATA(String.valueOf(id), session));
			}
			return list2;
		}
		
		
		@RequestMapping(value = "/Reject_Approve_Student_Admission_By_Commission_NCISM_Ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Reject_Approve_Student_Admission_By_Commission_NCISM_Ctrl(String a,String tempSt,HttpSession session){
			
			System.err.println("a------------>      "+a);
			
			String[] id_list = a.split(":");
			String[] tempSt2 = tempSt.split(",");
			
			

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			String remark="";
			
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				remark= tempSt2[i];
				
				System.err.println("HARSH----------------ID----------->    "+id);
				System.out.println("remarks======================== "+remark);
				
				list2.add(ACNDao.Reject_Approve_Student_Admission_By_Commission_NCISM_Data(Integer.toString(id),remark, session));
			}
			return list2;
		}
		
		@RequestMapping(value = "/Forward_Student_To_Commission_NCISM_ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Forward_Student_To_Commission_NCISM_ctrl(String a,HttpSession session){
			
			System.err.println("a------------>      "+a);
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH-----------ID----------->    "+id);
				list2.add(ACNDao.Forward_Student_To_Commission_NCISM_Data(String.valueOf(id), session));
			}
			return list2;
		}
		
		
//		get_StudentName_by_commReject_id
		 @RequestMapping(value = "/get_StudentName_by_NCISM_Reject_id", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_StudentName_by_NCISMReject_id(String id) {
		    	ArrayList<ArrayList<String>> data = ACNDao.get_StudentName_by_NCISM_Reject_DATA(id);
		    	return data;
		 	}
		

	
}
