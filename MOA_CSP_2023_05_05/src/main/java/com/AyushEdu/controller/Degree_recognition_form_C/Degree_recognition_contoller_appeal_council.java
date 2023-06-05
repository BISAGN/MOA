package com.AyushEdu.controller.Degree_recognition_form_C;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Admitted_Students_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Examiners_Appointed_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Institute_Wise_Regarding_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Intern_Student_Report_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Migrated_From_Other_College_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Migrated_To_Other_College_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Undergraduate_Course_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Degree_reco_council_Dao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Degree_recognition_contoller_appeal_council {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Degree_reco_council_Dao drdao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	//==========================================OPEN PAGE DEGREE RECOGNITION========================================== 
	
		@RequestMapping(value = "/Degree_reco_appeal_council", method = RequestMethod.GET)
		public ModelAndView Degree_reco_appeal_council(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//			SECURITY ABHISHEK
//			if(request.getHeader("Referer") == null ) { 
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Degree_reco_appeal_council", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String roleid = session.getAttribute("roleid").toString();
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
			return new ModelAndView("Degree_reco_appeal_council_Tiles");
		}
		
		//-------------------------------------------View table UG Form A-------------------------------------------

		 @PostMapping("/getFilter_appeal_formc")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_alists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
				String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_alist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status, name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
		    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
		     
	    }
}	