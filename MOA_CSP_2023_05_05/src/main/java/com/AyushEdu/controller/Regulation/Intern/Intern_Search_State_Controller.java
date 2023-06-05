package com.AyushEdu.controller.Regulation.Intern;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Intern.internSearch_State_PracDAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Intern_Search_State_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	internSearch_State_PracDAO  Rdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/intern_Search_State_pracUrl", method = RequestMethod.GET)
	public ModelAndView intern_Search_State_pracUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {

			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("intern_Search_State_pracUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		
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

		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES
		} catch (Exception e) {
				e.printStackTrace();
		}
		return new ModelAndView("intern_Search_State_Prac_Tiles");
	}
	 
	
	@PostMapping("/getFilter_State_intern_data")
	public @ResponseBody List<Map<String, Object>> getFilter_State_Prac_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String institute_status ,String gender,
			 String registration_state ,String per_state, String per_district,String type_of_degree,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status) {
					try { 
					}catch (RuntimeException e) {
						e.printStackTrace();
					}
		return Rdao.DataTableSeacrh_State_internDataList(startPage, pageLength, Search, orderColunm, orderType, first_name, institute_status ,gender, 
				 registration_state,  per_state,  per_district,  type_of_degree,   degree_name ,place_of_working,dob,date_of_reg,
				 institute_name,  type_status);
	} 
	
	@PostMapping("/getTotalState_intern_dataCount")
	public @ResponseBody long getTotalState_intern_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String institute_status,
			String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
		 String dob,String date_of_reg,String institute_name,String type_status) {
		return Rdao.DataTableSeacrh_State_internDataTotalCount(Search, nrh_en_no,first_name,institute_status,gender,  registration_state ,  per_state,per_district,  type_of_degree
				,degree_name ,  place_of_working  ,dob,date_of_reg,institute_name,  type_status);
	}
 
	@RequestMapping(value = "/internApprove_FromState_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> internApprove_FromState_Data(String a ,String status,String per_state,HttpSession session) throws ParseException {	
		
		
	  String username = session.getAttribute("username").toString();
	  	org.hibernate.Session sessionHQ = this.sessionFactory.openSession();
 		 
//		int user_id = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
//				.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
//	 System.err.println("user_id--ss-"+user_id);
		String state_reg_no ="";
		String[] id_list = a.split(":");
		//String[] id_list2 = upto.split(":");

		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			state_reg_no  = Rdao.getMaxAID();
			
			//date = id_list2[i];
			 list2.add(Rdao.approve_StateinternData(Integer.toString(id), status,username,state_reg_no,per_state ));
			//end
		}
		return list2;
	}
	@RequestMapping(value = "/internReject_FromState_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> internReject_FromState_Data(String a,String upto2,String status,String tempSt,HttpSession session) throws ParseException {	
		System.err.println("dao------------cont");
		String username = session.getAttribute("username").toString();
		String[] id_list = a.split(":");
		String[] id_list2 = upto2.split(":");
		String[] tempSt2 = tempSt.split(",");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		System.out.println("id_list============= "+id_list);
		String date;
		
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			date = id_list2[i];
			System.out.println("id_list============= "+id_list[i]);
			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(Rdao.reject_StateinternData(Integer.toString(id),status,username,tempSt2[i]));
		}
		return list2;
	}
	
 	
	
	 @RequestMapping(value = "/get_internname_by_Reject_id", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_internname_by_Reject_id(String id) {
	    	ArrayList<ArrayList<String>> data = Rdao.get_internname_by_Reject_idata(id);
	    	System.err.println("hiiiiiii");
	    	return data;
	 	}
 
//	 @RequestMapping(value = "/getstatusfrommedDegree", method = RequestMethod.POST)
//	 	public @ResponseBody ArrayList<ArrayList<String>> getstatusfrommedDegree(String regulation_p_id) {
//	    	ArrayList<ArrayList<String>> data = Rdao.getstatusfrommedDegree(regulation_p_id);
//	    	System.err.println("hiiiiiii----"+data);
//	    	return data;
//	 	}
	 
	 
}
