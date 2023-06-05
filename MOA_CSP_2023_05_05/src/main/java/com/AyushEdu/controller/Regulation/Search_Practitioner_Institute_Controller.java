package com.AyushEdu.controller.Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.EduRegDao;
import com.AyushEdu.dao.Regulation.Search_Insti_PracDAO;


	@Controller
	@RequestMapping(value = { "admin", "/", "user" })
	public class Search_Practitioner_Institute_Controller {

		@Autowired
		private SessionFactory sessionFactory;

		@Autowired
		private DataSource dataSource;

		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

		@Autowired
		Search_Insti_PracDAO Rdao;
	
		@Autowired
		private RoleBaseMenuDAO roledao;

		@Autowired
		CommonController common;
	
	
	
		//janki search 
		@GetMapping(value = "/edu_search_reg_url")
		public ModelAndView edu_search_reg_url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			try {
				
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("edu_search_reg_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

			Session sessionHQ = this.sessionFactory.openSession();
			String username = session.getAttribute("username").toString();
			int data = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			System.err.println("data---------" + data);
			model.put("institute_id", data);

			model.put("MedStateName", common.getMedStateName(sessionFactory));
			model.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
			model.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
			model.addAttribute("msg", msg);
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("edu_search_reg_Tiles");

		}

		@PostMapping("/getFilter_Edu_Reg_master_data")
		public @ResponseBody List<Map<String, Object>> getFilter_Edu_Reg_master_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType,  String first_name,
				String institute_status, String gender , String registration_state, String dob,
				String date_of_reg ,String institute_name,HttpSession session) {
			return Rdao.DataTableEdu_Reg_masterDataList(startPage, pageLength, Search, orderColunm, orderType, first_name,
					institute_status, gender, registration_state, dob, date_of_reg , institute_name,session);

		}

		@PostMapping("/getTotalEdu_Reg_master_dataCount")

		public @ResponseBody long getTotalEdu_Reg_master_dataCount(HttpSession sessionUserId, String Search,
			  String first_name, String institute_status, String gender , String registration_state, String dob, String date_of_reg,String institute_name) {
			return Rdao.DataTableEdu_Reg_masterDataTotalCount(Search, first_name, institute_status, gender, registration_state, dob, date_of_reg,institute_name);

		}

	
	
		@RequestMapping(value = "/Approve_regulation_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_regulation_Data(String a,String status,String b,HttpSession session) {	
//			ArrayList<ArrayList<String>> data = Rdao.get_medicalcildID(a);
//	    	System.err.println("data------"+data);
			List<String> list2 = new ArrayList<String>();
			list2.add(Rdao.approve_INSregData(a,status,b));
			return list2;
		}

		 
		@RequestMapping(value = "/Reject_regulation_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> Reject_regulation_Data(String a,String status,String tempSt,HttpSession session) {	
			 
			String[] id_list = a.split(":");
			String[] tempSt2 = tempSt.split(",");
			int id = 0;

			List<String> list2 = new ArrayList<String>();
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				System.out.println("id_list============= "+id_list[i]);
				System.out.println("remarks======================== "+tempSt2[i]);
			
			list2.add(Rdao.reject_INSregData(Integer.toString(id),status,tempSt2[i]));
			}
			return list2;
		}
		
		 @RequestMapping(value = "/get_Parctname_by_InstiReject_id", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_Parctname_by_InstiReject_id(String id) {
		    	ArrayList<ArrayList<String>> data = Rdao.get_Parctname_by_InstiReject_idata(id);
 		    	System.err.println("hiiiiiii");
 		    	return data;
		 	}
//		 @RequestMapping(value = "/get_medicalcildIDUrl", method = RequestMethod.POST)
//		 	public @ResponseBody ArrayList<ArrayList<String>> get_medicalcildIDUrl(String a) {
//		    	ArrayList<ArrayList<String>> data = Rdao.get_medicalcildID(a);
//		    	System.err.println("data----------"+data);
//		    	return data;
//		 	}
		
		 
		 
	
}
