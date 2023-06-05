package com.AyushEdu.controller.Registration;

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
import com.AyushEdu.dao.Registration.Admission_ConfirmationDao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Admission_confirmation_Controller {
	
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
	Admission_ConfirmationDao ACDao;

/////////////////////////////////////////////////Admission Confirmation OPEN /////////////////////////////////////////////////////////////////////
	
		@RequestMapping(value = "/Admission_Confirmation_Url", method = RequestMethod.GET)
		public ModelAndView Admission_Confirmation_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Admission_Confirmation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			 List<Map<String, Object>> list =   ACDao.AdmissionConfirmationSearchReport(userid, "", "", "","");
				Mmap.put("list", list);
		
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				Mmap.put("msg", msg);
				return new ModelAndView("Admission_Confirmation_Tiles");
//			}
		}
		
		@RequestMapping(value = "/admin/getSearch_StatusVise", method = RequestMethod.POST)
		public ModelAndView getSearch_StatusVise(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
		
		@RequestParam(value = "userid1", required = false) String userid2,
		@RequestParam(value = "dob1", required = false) String dob,
		@RequestParam(value = "name1", required = false) String name,
		@RequestParam(value = "aadhar_card1", required = false) String aadhar_card,
		@RequestParam(value = "verified_status1", required = false) String verified_status) throws SQLException{
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Admission_Confirmation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			 List<Map<String, Object>> list =   ACDao.AdmissionConfirmationSearchReport(userid,  dob,  name,  aadhar_card, verified_status);
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
				return new ModelAndView("Admission_Confirmation_Tiles");
//			}
		}
		
		
		@RequestMapping(value = "/Approve_From_Admission_Confirmation_Student_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_From_Admission_Confirmation_Student_Data(String a,HttpSession session){
			
			System.err.println("a------------>      "+a);
			
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH----------------ID----------->    "+id);
				list2.add(ACDao.Approve_From_Admission_Confirmation_Student_Data(Integer.toString(id), session));
			}
			return list2;
		}
		@RequestMapping(value = "/Reject_From_Admission_Confirmation_Student_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> Reject_From_Admission_Confirmation_Student_Data(String a,HttpSession session) throws ParseException {	
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
				list2.add(ACDao.Reject_From_Admission_Confirmation_Student_Data(Integer.toString(id),username,userId_reject, session));
			}
			return list2;
		}
	
		@RequestMapping(value = "/Enable_to_Edit_Admission_Student_ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Enable_to_Edit_Admission_Student_ctrl(String a,HttpSession session){
			System.err.println("a------------"+a);
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH----------------ID-------------------------"+id);
				list2.add(ACDao.Enable_to_Edit_Admission_Student_Data(String.valueOf(id),"", session));
			}
			return list2;
		}
		
		@RequestMapping(value = "/Late_Admission_Confirmation_Url", method = RequestMethod.GET)
		public ModelAndView Late_Admission_Confirmation_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String role = session.getAttribute("role").toString();
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			System.err.println("String roleid = session.getAttribute(\"roleid\").toString();------------>    "+roleid);
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Late_Admission_Confirmation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		if (role.equals("NCH")) {
			List<Map<String, Object>> list =  ACDao.Late_AdmissionConfirmationSearchReport(userid, "", "", "","","6",role);
			 Mmap.put("list", list);
			 Mmap.put("late_admission_status", "6");
			}	
				else {
					List<Map<String, Object>> list =  ACDao.Late_AdmissionConfirmationSearchReport(userid, "", "", "","","",role);	
					Mmap.put("list", list);
					 Mmap.put("late_admission_status", "1");
				}
		
				if (request.getHeader("Referer") == null) {
					msg = "";
				}
				Mmap.put("msg", msg);
				return new ModelAndView("Late_Admission_Confirmation_Tiles");
//			}
		}
		
		@RequestMapping(value = "/admin/Late_Admission_Confirmation_Search", method = RequestMethod.POST)
		public ModelAndView Late_Admission_Confirmation_Search(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
		
		@RequestParam(value = "userid1", required = false) String userid2,
		@RequestParam(value = "dob1", required = false) String dob,
		@RequestParam(value = "name1", required = false) String name,
		@RequestParam(value = "aadhar_card1", required = false) String aadhar_card,
		@RequestParam(value = "verified_status1", required = false) String verified_status,
		@RequestParam(value = "late_admission_status1", required = false) String late_admission_status) throws SQLException{
			String role = session.getAttribute("role").toString();
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Late_Admission_Confirmation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			if (role.equals("NCH") && late_admission_status =="") {
				late_admission_status ="6";
			}
			
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			} else {
			 List<Map<String, Object>> list =   ACDao.Late_AdmissionConfirmationSearchReport(userid,  dob,  name,  aadhar_card, verified_status,late_admission_status,role);
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
				return new ModelAndView("Late_Admission_Confirmation_Tiles");
//			}
		}
		
		@RequestMapping(value = "/Approve_Student_Admission_By_Commission_NCH_Ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_Student_Admission_By_Commission_NCH_Ctrl(String a,HttpSession session){
			
			System.err.println("a------------>      "+a);
			String[] id_list = a.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH-----------ID----------->    "+id);
				list2.add(ACDao.Approve_Student_Admission_By_Commission_NCH_DATA(String.valueOf(id), session));
			}
			return list2;
		}
		
		
		@RequestMapping(value = "/Reject_Approve_Student_Admission_By_Commission_NCH_Ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Reject_Approve_Student_Admission_By_Commission_NCH_Ctrl(String a,String tempSt,HttpSession session){
			System.err.println("a------------>      "+a);
			String[] id_list = a.split(":");
			String[] tempSt2 = tempSt.split(",");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH----------------ID----------->    "+id);
				System.out.println("remarks======================== "+tempSt2[i]);
				list2.add(ACDao.Reject_Approve_Student_Admission_By_Commission_NCH_Data(Integer.toString(id),tempSt2[i], session));
			}
			return list2;
		}
		
		@RequestMapping(value = "/Forward_Student_To_Commission_NCH_ctrl" , method = RequestMethod.POST)
		public @ResponseBody List<String> Forward_Student_To_Commission_NCH_ctrl(String a,HttpSession session){
			
			System.err.println("a------------>      "+a);
			String[] id_list = a.split(":");
			List<String> list2 = new ArrayList<String>();
			int id = 0;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.err.println("HARSH-----------ID----------->    "+id);
				list2.add(ACDao.Forward_Student_To_Commission_NCH_Data(String.valueOf(id), session));
			}
			return list2;
		}
		
//		get_StudentName_by_commReject_id
		 @RequestMapping(value = "/get_StudentName_by_commReject_id", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_StudentName_by_commReject_id(String id) {
		    	ArrayList<ArrayList<String>> data = ACDao.get_StudentName_by_commReject_DATA(id);
		    	return data;
		 	}
}