package com.AyushEdu.controller.B_Regulation;

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
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.B_Regulation.b_Search_State_PracDAO;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class b_Search_Practitioner_State_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	b_Search_State_PracDAO  BSSdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/b_Search_State_pracUrl", method = RequestMethod.GET)
	public ModelAndView b_Search_State_pracUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("b_Search_State_pracUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		 System.err.println(common.getMedNationality(sessionFactory));
		 Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));

		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES

		return new ModelAndView("b_Search_State_Prac_Tiles");
	}
	
	@PostMapping("/getFilter_b_State_Prac_data")
	public @ResponseBody List<Map<String, Object>> getFilter_b_State_Prac_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String institute_status ,String gender,
			String reg_no,String registration_state ,String per_state, String per_district,String type_of_degree,String  degree_name ,String place_of_working,
			String  registration_for_type,String dob,String date_of_reg) {

		return BSSdao.DataTable_b_Seacrh_State_PracDataList(startPage, pageLength, Search, orderColunm, orderType, first_name, institute_status ,gender,reg_no,
				 registration_state,  per_state,  per_district,  type_of_degree,   degree_name ,place_of_working, registration_for_type,dob,date_of_reg);

	}
	
	@PostMapping("/getTotal_b_State_Prac_dataCount")
	public @ResponseBody long getTotal_b_State_Prac_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String institute_status,String gender,String reg_no,
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			String  registration_for_type,String dob,String date_of_reg) {
		return BSSdao.DataTable_b_Seacrh_State_PracDataTotalCount(Search, nrh_en_no,first_name,institute_status,gender,reg_no,  registration_state ,  per_state,per_district,  type_of_degree
				,   degree_name ,  place_of_working ,registration_for_type,dob,date_of_reg);

	}
	
	
	@RequestMapping(value = "/Approve_From_b_State_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_From_b_State_Data(String a,String status,HttpSession session) throws ParseException {	
		String username = session.getAttribute("username").toString();
		String[] id_list = a.split(":");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			list2.add(BSSdao.approve_b_StatePracData(Integer.toString(id),status,username));
		}
		return list2;
	}
	@RequestMapping(value = "/Reject_From_b_State_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_From_b_State_Data(String a,String status,HttpSession session) throws ParseException {	
		String username = session.getAttribute("username").toString();
		String[] id_list = a.split(":");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			list2.add(BSSdao.reject_b_StatePracData(Integer.toString(id),status,username));
		}
		return list2;
	}
	
}
