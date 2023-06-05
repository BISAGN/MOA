package com.AyushEdu.controller.Regulation;
import java.text.ParseException;
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
import com.AyushEdu.dao.Regulation.Search_Pra_RegistrationDAO;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_Pra_Registration_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Search_Pra_RegistrationDAO  SPRdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	
	@GetMapping(value = "/Search_pra_RegistrationUrl")
	public ModelAndView Search_pra_RegistrationUrl(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Search_pra_RegistrationUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		 model.put("state_id", common.getMedStateName(sessionFactory));


		 model.put("getMedStateName", common.getMedStateName(sessionFactory));
		
		 model.addAttribute("msg", msg);
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("Search_pra_Registration_Tiles");

	}
	
	@PostMapping("/getFilter_Pra_Registration_master_data")

	public @ResponseBody List<Map<String, Object>> getFilter_Pra_Registration_master_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType,String institute_state, String status) throws ParseException {
		

		return SPRdao.DataTablePra_Registration_masterDataList(startPage, pageLength, Search, orderColunm, orderType ,institute_state, status);

	}

	@PostMapping("/getTotalPra_Registration_master_dataCount")

	public @ResponseBody long getTotalPra_Registration_master_dataCount(HttpSession sessionUserId, String Search, String institute_state,String status) throws ParseException {
		return SPRdao.DataTablePra_Registration_masterDataTotalCount(Search, institute_state,status);

	}
	
	
	@RequestMapping(value = "/Approve_User_Data_prac" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_User_Data_prac(String a,String status,HttpSession session) throws ParseException {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(SPRdao.approve_reject_reg(a,status,session));
		return list2;
	}
}
