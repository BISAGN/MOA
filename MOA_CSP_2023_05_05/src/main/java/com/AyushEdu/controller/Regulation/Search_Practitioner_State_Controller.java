package com.AyushEdu.controller.Regulation;
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

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_Practitioner_State_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Search_State_PracDAO  Rdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Search_State_pracUrl", method = RequestMethod.GET)
	public ModelAndView Search_State_pracUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_State_pracUrl", roleid1);		
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
	    Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES
		} catch (Exception e) {
				e.printStackTrace();
		}
		return new ModelAndView("Search_State_Prac_Tiles");
	}
	
	@PostMapping("/getFilter_State_Prac_data")
	public @ResponseBody List<Map<String, Object>> getFilter_State_Prac_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String institute_status ,String gender,
			 String registration_state ,String per_state, String per_district,String type_of_degree,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status) {

		return Rdao.DataTableSeacrh_State_PracDataList(startPage, pageLength, Search, orderColunm, orderType, first_name, institute_status ,gender, 
				 registration_state,  per_state,  per_district,  type_of_degree,   degree_name ,place_of_working,dob,date_of_reg,
				 institute_name,  type_status);
	}
	
	@PostMapping("/getTotalState_Prac_dataCount")
	public @ResponseBody long getTotalState_Prac_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String institute_status,
			String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
		 String dob,String date_of_reg,String institute_name,String type_status) {
		return Rdao.DataTableSeacrh_State_PracDataTotalCount(Search, nrh_en_no,first_name,institute_status,gender,  registration_state ,  per_state,per_district,  type_of_degree
				,degree_name ,  place_of_working  ,dob,date_of_reg,institute_name,  type_status);
	}
	
//	@RequestMapping(value = "/Approve_FromState_Data" , method = RequestMethod.POST)
//	public @ResponseBody List<String> Approve_FromState_Data(String a,String status,HttpSession session) throws ParseException {	
//		String username = session.getAttribute("username").toString();
//		String[] id_list = a.split(":");
//		List<String> list2 = new ArrayList<String>();
//		int id = 0;
//		for (int i = 0; i < id_list.length; i++) {
//			id = Integer.parseInt(id_list[i]);
//			list2.add(Rdao.approve_StatePracData(Integer.toString(id),status,username));
//		}
//		return list2;
//	}
	@RequestMapping(value = "/Approve_FromState_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_FromState_Data(String a,String upto,String status,String per_state,HttpSession session) throws ParseException {	
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();

		String valid_upto1 = upto;
//		Date dob = null;
//		if (!upto.equals("DD/MMM/YYYY") && !upto.equals("")) {
//			dob = formate.parse(upto);
//		}
		
		//start by lucifer
		
		String state_reg_no ="";
		String[] id_list = a.split(":");
		String[] id_list2 = upto.split(":");

		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			state_reg_no  = Rdao.getMaxAID();
			
			date = id_list2[i];
			list2.add(Rdao.approve_StatePracData(Integer.toString(id),date,status,username,state_reg_no,per_state));
			//end
		}
		return list2;
	}
	@RequestMapping(value = "/Reject_FromState_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_FromState_Data(String a,String upto2,String status,String tempSt,HttpSession session) throws ParseException {	
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
			list2.add(Rdao.reject_StatePracData(Integer.toString(id),status,username,tempSt2[i]));
		}
		return list2;
	}
	
//	@RequestMapping(value = "/getFilter_State_Prac_data_Reject_name" , method = RequestMethod.POST)
//	public @ResponseBody List<String> getFilter_State_Prac_data_Reject_name(String a,String first_name,HttpSession session) throws ParseException {	
//		System.err.println("dao------------cont"+first_name);
//		String username = session.getAttribute("username").toString();
//		String[] id_list = a.split(":");
//		String[] id_list2 = first_name.split(":");
//		List<String> list2 = new ArrayList<String>();
//		int id = 0;
//		String date;
//		System.err.println("first_name----------------"+first_name);
//		for (int i = 0; i < id_list.length; i++) {
//			id = Integer.parseInt(id_list[i]);
//			date = id_list2[i];
//			list2.add(Rdao.reject_name_StatePracData(Integer.toString(id),first_name,username));
//			
//		}
//		return list2;
//	}
	
//	
	
	 @RequestMapping(value = "/get_Parctname_by_Reject_id", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_Parctname_by_Reject_id(String id) {
	    	ArrayList<ArrayList<String>> data = Rdao.get_Parctname_by_Reject_idata(id);
	    	System.err.println("hiiiiiii");
	    	return data;
	 	}
	
//	 @RequestMapping(value = "/getstatusfrommedDegree", method = RequestMethod.POST)
//		public @ResponseBody List<String> getstatusfrommedDegree(SessionFactory sessionFactory, String regulation_p_id) {
//			//Session sessionHQL = sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			System.err.println("iin---");
//			Query q = sessionHQL.createQuery( "select count(id) from REG_NCH_MED_DEGREE_DTL_A_CH where status=1 and regulation_p_id =: regulation_p_id");
//			System.err.println("qqq---"+q); 
//			q.setParameter("regulation_p_id", Integer.parseInt(regulation_p_id));
//			@SuppressWarnings("unchecked")
//			// List<UserLogin> clist = (List<UserLogin>) q.list();
//			List<String> list = (List<String>) q.list();
//			tx.commit();
//			System.err.println("listtttttttt---"+list);
//			sessionHQL.close();
//			return list;
//		}
	 @RequestMapping(value = "/getstatusfrommedDegree", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getstatusfrommedDegree(String regulation_p_id) {
	    	ArrayList<ArrayList<String>> data = Rdao.getstatusfrommedDegree(regulation_p_id);
	    	System.err.println("hiiiiiii----"+data);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/Search_Pract_State_Report_Excel", method = RequestMethod.POST)
		public ModelAndView Search_Pract_State_Report_Excel(HttpSession session, HttpServletRequest request)
				throws ParseException {
			String first_name =request.getParameter("first_name2");
			String gender =request.getParameter("gender2");
//			String photo_path =request.getParameter("photo_path");
			String father_name =request.getParameter("father_name2");
			String pre_address =request.getParameter("pre_address2");
			String pre_district =request.getParameter("pre_district2");
			String pre_state =request.getParameter("pre_state2");
			String pre_pincode =request.getParameter("pre_pincode2");
			String per_address =request.getParameter("per_address2");
			String per_district =request.getParameter("per_district2");
			String per_state =request.getParameter("per_state2");
			 
			String per_pincode =request.getParameter("per_pincode2");
			String aadhaar_no =request.getParameter("aadhaar_no2");
			String fax_no =request.getParameter("fax_no2");
			String mo_no =request.getParameter("mo_no2");
			String alt_mo_no =request.getParameter("alt_mo_no2");
			String email_id =request.getParameter("email_id2");
			String dob =request.getParameter("dob2");
			String nationality =request.getParameter("nationality2");
			//String reg_no =request.getParameter("reg_no");
			String date_of_reg =request.getParameter("date_of_reg2");
			String name_reg =request.getParameter("name_reg2");
			String reg_renew_permanent =request.getParameter("reg_renew_permanent2");
			String name_of_patient =request.getParameter("name_of_patient2");
			String institute_status =request.getParameter("institute_status2");
			 
			String type_of_degree =request.getParameter("type_of_degree2");
		//	String reg_no =request.getParameter("reg_no");
			
			String degree_name =request.getParameter("degree_name2");
			String place_of_working =request.getParameter("place_of_working2");
		 	String registration_for_type = request.getParameter("registration_for_type2");
		 	 
//		 	String registration_state =request.getParameter("st_id"); 
		 	String registration_state =request.getParameter("registration_state2");
		 	String institute_name =request.getParameter("institute_name2"); 
		 	String type_status =request.getParameter("type_status2"); 
		 	String nrh_en_no =request.getParameter("nrh_en_no2");
		 	
			String Search =request.getParameter("Search2");
			
			List<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
			
			list=Rdao.getSearch_Pract_State_Excel(Search, nrh_en_no,first_name,institute_status,gender,  registration_state ,  per_state,per_district,  type_of_degree
					,degree_name ,  place_of_working  ,dob,date_of_reg,institute_name,  type_status);
			int total = list.size();
			List<String> TH = new ArrayList<String>();
			
			
			TH.add("Ser No.");
			TH.add("Ayush Id/ABHA No.");
			TH.add("State Register No.");
			TH.add("NRH No.");
			TH.add("Valid Upto");
			TH.add("Name");
			TH.add("Gender");
			TH.add("Father's Name");
			TH.add("Permanent District");
			TH.add("Permanent State");
			TH.add("Permanent Pincode");
			TH.add("Present District");
			TH.add("Present State");
			TH.add("Present Pincode");
			TH.add("Email Id");
			TH.add("Date Of Birth");
			TH.add("Nationality");
			
			
			String Heading = "\nIn Inspection";
			
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Search_Pract_State_Excel("L", TH, list, Heading, username), "userList",
					list);
		}
	 
	 
}
