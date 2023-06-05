package com.AyushEdu.controller.Regulation.Intern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Search_State_PracDAO;
import com.AyushEdu.dao.Regulation.Intern.Search_FormAADAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_FormAA_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Search_FormAADAO  Rdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Search_formaa_Url", method = RequestMethod.GET)
	public ModelAndView Search_formaa_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_formaa_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		 
			int data = (int) sessionHQ.createQuery("select state_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
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
		return new ModelAndView("Search_formaa_Tiles");
	}
	
	@PostMapping("/getFilter_formaa_data")
	public @ResponseBody List<Map<String, Object>> getFilter_formaa_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String institute_status ,String gender,
			 String registration_state ,String per_state, String per_district,String type_of_degree,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status) {

		return Rdao.DataTableSeacrh_formaaDataList(startPage, pageLength, Search, orderColunm, orderType, first_name, institute_status ,gender, 
				 registration_state,  per_state,  per_district,  type_of_degree,   degree_name ,place_of_working,dob,date_of_reg,
				 institute_name,  type_status);
	}
	
	@PostMapping("/getTotalformaa_dataCount")
	public @ResponseBody long getTotalformaa_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String institute_status,
			String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
		 String dob,String date_of_reg,String institute_name,String type_status) {
		return Rdao.DataTableSeacrh_formaaDataTotalCount(Search, nrh_en_no,first_name,institute_status,gender,  registration_state ,  per_state,per_district,  type_of_degree
				,degree_name ,  place_of_working  ,dob,date_of_reg,institute_name,  type_status);
	}
	
 
	@RequestMapping(value = "/Approve_Fromaa_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_Fromaa_Data(String aa, String a,String upto,String status,String state,String from_date, String to_date, HttpSession session) throws ParseException {	
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();
		System.err.println("status==="+status);
		String valid_upto1 = upto;
		Date dob = null;
		if (!upto.equals("DD/MMM/YYYY") && !upto.equals("")) {
			dob = formate.parse(upto);
		}
		String from_date1 = from_date;
		Date frm_dt = null;
		if (!from_date.equals("DD/MMM/YYYY") && !from_date.equals("")) {
			frm_dt = formate.parse(from_date);
		}
	 
		String to_date1 = to_date;
		Date to_dt = null;
		if (!to_date.equals("DD/MMM/YYYY") && !to_date.equals("")) {
			to_dt = formate.parse(to_date);
		}
		String state_reg_no ="";
		String[] id_list = a.split(":");
		String[] pid_list = aa.split(":");
		String[] id_list2 = upto.split(":");

		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		String pid;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			state_reg_no  = Rdao.getMaxAID();
			
			date = id_list2[i];
			pid = pid_list[i];
			list2.add(Rdao.approve_formaaData(Integer.toString(id),date,status,username,state_reg_no,state ,from_date ,to_date ,pid));
			//end
		}
		return list2;
	}
	@RequestMapping(value = "/Reject_Fromaa_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_Fromaa_Data(String aa, String a,String upto2,String status,String tempSt,HttpSession session) throws ParseException {	
		System.err.println("dao------------cont");
		String username = session.getAttribute("username").toString();
		String[] id_list = a.split(":");
		String[] id_list2 = upto2.split(":");
		String[] tempSt2 = tempSt.split(",");
		String[] pid_list = aa.split(":");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String pid;
		System.out.println("id_list============= "+id_list);
		String date;
		
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			date = id_list2[i];
			pid = pid_list[i];
			System.out.println("id_list============= "+id_list[i]);
			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(Rdao.reject_formaaData(Integer.toString(id),status,username,tempSt2[i] ,pid));
		}
		return list2;
	}
	
 
//	 @RequestMapping(value = "/get_Parctname_by_Reject_id", method = RequestMethod.POST)
//	 	public @ResponseBody ArrayList<ArrayList<String>> get_Parctname_by_Reject_id(String id) {
//	    	ArrayList<ArrayList<String>> data = Rdao.get_Parctname_by_Reject_idata(id);
//	    	System.err.println("hiiiiiii");
//	    	return data;
//	 	}
	
// 
//	 @RequestMapping(value = "/getstatusfrommedDegree", method = RequestMethod.POST)
//	 	public @ResponseBody ArrayList<ArrayList<String>> getstatusfrommedDegree(String regulation_p_id) {
//	    	ArrayList<ArrayList<String>> data = Rdao.getstatusfrommedDegree(regulation_p_id);
//	    	System.err.println("hiiiiiii----"+data);
//	    	return data;
//	 	}
//	 
	 
}
