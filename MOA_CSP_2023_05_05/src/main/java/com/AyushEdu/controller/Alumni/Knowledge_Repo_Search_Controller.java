package com.AyushEdu.controller.Alumni;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Knowledge_Repo_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Knowledge_Repo_Search_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Knowledge_Repo_DAO knowdao;
	
//	@Autowired
//	Department_Mstr_DAO Ddao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Knowledge_Repo_Search_Url", method = RequestMethod.GET)
	public ModelAndView Department_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Search_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			Mmap.put("msg", msg);

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Knowledge_Repo_Search_Tiles");
	}
	
	
	
	@RequestMapping(value = "/getSearchRepo", method = RequestMethod.POST) 
	public @ResponseBody List<Map<String, Object>> getSearchRepo(String a) {
		
	        Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
		       String qry ="";

		       System.err.println("============="+a);

	       if (a != null && !a.trim().equals("")) {
	    return knowdao.getSearchDetails(a);
	       }else {
	    	   return null;
	       }
	}

	
	//==========================================SAVE/view//Edit Professional========================================== 	

}