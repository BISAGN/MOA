package com.AyushEdu.controller.Regulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
//import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;
import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Regulation.REG_NCH_WORKING_PLACE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Regulation_ReportDao;
import com.AyushEdu.dao.Regulation.Search_PracDtlDAO;
import com.AyushEdu.dao.Regulation.Search_State_PracDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class track_detailsController {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Search_PracDtlDAO PRdao;

	@Autowired
	CommonController common;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/track_practUrl", method = RequestMethod.GET)
	public ModelAndView track_practUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		org.hibernate.Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		String role = session.getAttribute("role").toString();
//		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("track_practUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}

//		System.err.println("username============" + username);
//		System.err.println("role============" + role);
		
		try {
			int user_id = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
//			System.err.println("userId============" + user_id);
			 
			int state_id = (int) sessionHQ.createQuery("select state_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();		
					
//			System.err.println("state_id============" + state_id);
			int university_id = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();		
//			System.err.println("university_id============" + university_id);
			
			Mmap.put("msg", msg);
			Mmap.put("userId", user_id);
			Mmap.put("state_id", state_id);
			Mmap.put("university_id", university_id);
			
			Mmap.put("role", role);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));

		return new ModelAndView("track_practUrl_Tiles");
	}

	 
	// end

}
