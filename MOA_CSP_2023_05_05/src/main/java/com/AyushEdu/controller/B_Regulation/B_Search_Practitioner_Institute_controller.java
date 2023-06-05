package com.AyushEdu.controller.B_Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
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
import com.AyushEdu.dao.B_Regulation.b_Search_Insti_PracDAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class B_Search_Practitioner_Institute_controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	b_Search_Insti_PracDAO BSIPdao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@GetMapping(value = "/edu_search_b_reg_url")
	public ModelAndView edu_search_b_reg_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("edu_search_b_reg_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		model.put("MedStateName", common.getMedStateName(sessionFactory));
		model.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		model.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		model.addAttribute("msg", msg);
		model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("edu_search_b_reg_Tiles");

	}
	
	@PostMapping("/getFilter_Edu_b_Reg_master_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Edu_b_Reg_master_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,  String first_name,
			String institute_status, String gender, String reg_no, String registration_state, String dob,
			String date_of_reg) {
		return BSIPdao.DataTableEdu_b_Reg_masterDataList(startPage, pageLength, Search, orderColunm, orderType, first_name,
				institute_status, gender, reg_no, registration_state, dob, date_of_reg);

	}

	@PostMapping("/getTotalEdu_b_Reg_master_dataCount")

	public @ResponseBody long getTotalEdu_b_Reg_master_dataCount(HttpSession sessionUserId, String Search,
		  String first_name, String institute_status, String gender, String reg_no, String registration_state, String dob, String date_of_reg) {
		return BSIPdao.DataTableEdu_b_Reg_masterDataTotalCount(Search, first_name, institute_status, gender, reg_no, registration_state, dob, date_of_reg);

	}
	

	@RequestMapping(value = "/Approve_b_regulation_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_b_regulation_Data(String a,String status,HttpSession session) {	
		 
		List<String> list2 = new ArrayList<String>();
		list2.add(BSIPdao.approve_INS_b_regData(a,status));
		return list2;
	}

	 
	@RequestMapping(value = "/Reject_b_regulation_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_b_regulation_Data(String a,String status,HttpSession session) {	
		 
		List<String> list2 = new ArrayList<String>();
		list2.add(BSIPdao.reject_INS_b_regData(a,status));
		return list2;
	}


}
