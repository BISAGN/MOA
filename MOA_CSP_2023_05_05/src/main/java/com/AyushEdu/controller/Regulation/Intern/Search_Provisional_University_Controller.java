package com.AyushEdu.controller.Regulation.Intern;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Regulation_University_pre_DAO;
import com.AyushEdu.dao.Regulation.Intern.Search_Unive_ProvionalDAO;


	@Controller
	@RequestMapping(value = { "admin", "/", "user" })
	public class Search_Provisional_University_Controller {

		@Autowired
		private SessionFactory sessionFactory;

		@Autowired
		private DataSource dataSource;

		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

		@Autowired
		Search_Unive_ProvionalDAO Rdao;
	
		@Autowired
		private RoleBaseMenuDAO roledao;

		@Autowired
		CommonController common;
	
		@Autowired
		Regulation_University_pre_DAO UPdao;
		
		@Autowired
		Search_Unive_ProvionalDAO Udao;
	
	
		//janki search 
		@GetMapping(value = "/edu_search_pro_uni_reg_url")
		public ModelAndView edu_search_pro_uni_reg_url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			try {
				
				//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("edu_search_pro_uni_reg_url", roleid1);		
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
			return new ModelAndView("edu_search_uni_prov_Tiles");

		}

		
		@RequestMapping(value = "admin/provisional_University_previewUrl" )
		public ModelAndView provisional_University_previewUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, 	@RequestParam(value = "u_id", required = false) String id, HttpServletRequest request){
		
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("edu_search_pro_uni_reg_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			System.err.println("pprovisional_University_previewUrlro === ");
			
			Calendar calendar = Calendar.getInstance();
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("u_id", id);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
 		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();

		int d  = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
				.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
		Mmap.put("ud", d);
		try {
			 
			
 			int data = (int) sessionHQ.createQuery("select user_id from REG_NCH_FORM_A_P where id=:id")
 					         .setParameter("id",Integer.parseInt(id)).setMaxResults(1).uniqueResult();
			Mmap.put("userId", data);
			if (data != 0) {
				Mmap.put("setDataCMD", UPdao.getDataByUserNameForDraftPreview(data));
			//janki
				Mmap.put("setAddMoreMedicalCMD", Udao.medicalDataPreviewUniProvisional(data , d));
				Mmap.put("setAddMoreHospCMD", UPdao.HospitalDataPreview(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", UPdao.medicalDataChildAttachmentPreview(data));
 				Mmap.put("setRegAuth", UPdao.RegAuthPreview(data));
				String pid = UPdao.getUserIdPreview(data);
				Mmap.put("p_id", pid);
 				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
					Mmap.put("parentid", data2);
					Mmap.put("valid_dt", INF.getValid_up_to());

					if (INF.getStatus() == 0) {
						Mmap.put("hid", "2");
 					}
					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
						Mmap.put("hid", "1");
					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1 && INF.getNrh_status() == 1) {
						Mmap.put("hid", "3");
 					} else {
						Mmap.put("hid", "0");
					}
				} else {
					Mmap.put("p_id", "0");
				}

			} else {
				Mmap.put("setData", "0");
				Mmap.put("hid", "0");
				Mmap.put("p_id", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
  	return new ModelAndView("provisional_university_preview_Tiles");

	}
		
		
		@PostMapping("/getFilter_Edu_Reg_provisional_data")
		public @ResponseBody List<Map<String, Object>> getFilter_Edu_Reg_provisional_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType,  String first_name,
				String institute_status, String gender , String registration_state, String dob,
				String date_of_reg ,String institute_name) {
			return Rdao.DataTablegetFilter_Edu_Reg_provisional_data(startPage, pageLength, Search, orderColunm, orderType, first_name,
					institute_status, gender, registration_state, dob, date_of_reg , institute_name);

		}

		@PostMapping("/getTotalEdu_provisional_dataCount")

		public @ResponseBody long getTotalEdu_provisional_dataCount(HttpSession sessionUserId, String Search,
			  String first_name, String institute_status, String gender , String registration_state, String dob, String date_of_reg,String institute_name) {
			return Rdao.DataTablegetTotalEdu_provisional_dataCount(Search, first_name, institute_status, gender, registration_state, dob, date_of_reg,institute_name);

		}

	
	 
		 
		 
	
}
