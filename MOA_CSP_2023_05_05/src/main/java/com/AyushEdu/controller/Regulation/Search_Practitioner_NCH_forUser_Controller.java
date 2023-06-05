package com.AyushEdu.controller.Regulation;

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
import com.AyushEdu.dao.Regulation.Search_NCH_PracforUserDAO;

@Controller
//@RequestMapping(value = {"admin","/","user"})
public class Search_Practitioner_NCH_forUser_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Search_NCH_PracforUserDAO  NRdao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/Search_NCH_forUser_Url", method = RequestMethod.GET)
	public ModelAndView Search_NCH_forUser_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Search_NCH_forUser_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
		
		Mmap.put("msg", msg);
		Mmap.addAttribute("langugae", "english");
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));

		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES
		} catch (Exception e) {
			e.printStackTrace();
 	    }
		return new ModelAndView("Search_nch_Prac_forUser_Tiles");
	}
	
	@PostMapping("/getFilter_NCH_Prac_foruserdata")
	public @ResponseBody List<Map<String, Object>> getFilter_NCH_Prac_foruserdata(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name ,String per_state, String registration_state) {
		return NRdao.DataTableSeacrh_NCH_PracforUserDataList(startPage, pageLength, Search, orderColunm, orderType, nrh_en_no, first_name , per_state,  registration_state);

	}
	
	@PostMapping("/getTotalNCH_Prac_forUserdataCount")
	public @ResponseBody long getTotalNCH_Prac_forUserdataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name ,String per_state, String registration_state) {
		return NRdao.DataTableSeacrh_NCH_PracforUserDataTotalCount(Search, nrh_en_no, first_name , per_state,  registration_state);

	}
	
	
}
