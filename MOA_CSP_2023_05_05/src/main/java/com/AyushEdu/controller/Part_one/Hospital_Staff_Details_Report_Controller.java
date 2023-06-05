package com.AyushEdu.controller.Part_one;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Hosp_Staff_Detail.HOSP_EQUIPMENTES_DETAIL;
import com.AyushEdu.Models.Hosp_Staff_Detail.HOSP_STAFF_DETAIL;
import com.AyushEdu.Models.Hosp_Staff_Detail.HOSP_STAFF_DETAIL_UP_DOCUMENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Hospital_Equipment_Report_Dao;
import com.AyushEdu.dao.Part_One.Hospital_Equipments_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Staff_Details_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Staff_Details_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Hospital_Staff_Details_Report_Controller {
	
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Hospital_Staff_Details_Report_Dao HSDDao;
	
	@Autowired
	Hospital_Staff_Details_DAO HSDao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	// ----------------search-------------------------
//	 
//		 @GetMapping(value = "/Search_Hospital_Equipment_Report_url")
//			public ModelAndView Search_College_Infrastructure_url(ModelMap model, HttpSession session,
//					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//			 
//				String userid = session.getAttribute("userId_for_jnlp").toString();
//				String role = session.getAttribute("role").toString();
//				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			    model.put("getarticaldata", HEDao.GetArticlesdata());
//			    model.put("getMedInstituteName", common.getMedInstituteName(sessionFactory));
//				
//				model.put("msg", msg);
//				return new ModelAndView("hospital_equipments_reportTiles");
//			}
//		 
//			@PostMapping("/getFilterSearch_Hospital_Equipment_Report_data")
//			public @ResponseBody List<Map<String, Object>> getFilterSearch_Hospital_Equipment_Report_data(HttpSession sessionUserId, int startPage,
//					int pageLength, String Search, String orderColunm, String orderType,String articles, String instId) {
//				
//				String role = sessionUserId.getAttribute("role").toString();
//				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
//				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//				System.err.println("instId----------------------------"+instId);
//				return HERDao.DataTableSearch_Hospital_Equipment_ReportDataList(startPage, pageLength, Search, orderColunm, orderType,  articles,instId, role, userid,institute_id);
//
//			}
//			
//			@PostMapping("/getTotalSearch_Hospital_Equipment_Report_dataCount")
//			public @ResponseBody long getTotalSearch_Hospital_Equipment_Report_dataCount(HttpSession sessionUserId, String Search,String articles,String instId) {
//				String role = sessionUserId.getAttribute("role").toString();
//				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
//				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//				return HERDao.DataTableSearch_Hospital_Equipment_ReportDataTotalCount(Search,  articles, instId, role, userid,institute_id);
//			}
			
			
			
//////////////////View_url

@RequestMapping(value = "/hospital_staffdetails_view", method = RequestMethod.POST)
public ModelAndView hospital_staffdetails_view(@RequestParam(value = "hos_staff_dtl_id", required = false) String id, ModelMap Mmap,
HttpSession session, @RequestParam(value = "msg", required = false) String msg,
HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		System.err.println("iddddddddddddddddddddddddddd"+id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
				Integer.parseInt(id), role, userid,"hosp_staff_details_upload_document","institute_id");
		
		if(getPidfromInstidReport.isEmpty()) {
			Mmap.put("msg", "Please Save Hospital Staff Details First");
			return new ModelAndView("redirect:hospital_staffdetails");
		}
		
				System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
				String p_id = getPidfromInstidReport.get(0).get("id").toString();
		Mmap.put("getPidfromInstidReport", p_id); 

					System.err.println("22/2/23----------------id------>" + id);

						System.err.println("institute_id----------------id------>" + institute_id);

//						HOSP_STAFF_DETAIL_UP_DOCUMENT viewid = (HOSP_STAFF_DETAIL_UP_DOCUMENT) sessionHQL
//						.get(HOSP_STAFF_DETAIL_UP_DOCUMENT.class, Integer.parseInt(p_id));
//
//
//				Mmap.put("View_Hospital_Equipmentrrrr", viewid);


					List<Map<String, Object>> View_Hospital_Staff_Details2 = HSDDao.View_Hospital_Staff_Details(Integer.parseInt(id),
								Integer.parseInt(institute_id), role,userid);

					System.err.println("View_Hospital_Staff_Details2--------------"+View_Hospital_Staff_Details2);
					Mmap.put("View_Hospital_Staff_Details2", View_Hospital_Staff_Details2);
					Mmap.put("hsddocdata", HSDDao.getHosptal_staff_detl_UploadDocumentsFetch(id));
					Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
					Mmap.put("inst_id", id);


Mmap.addAttribute("msg", msg);
return new ModelAndView("hospital_staffdetails_view");
}

}
