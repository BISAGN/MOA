package com.AyushEdu.controller.Curriculum;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
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
import com.AyushEdu.dao.Curriculum.sixA_paper_mark_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Six_A_Paper_Marks_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	sixA_paper_mark_Dao daoo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/SixA_url", method = RequestMethod.GET)
	public ModelAndView SixA_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SixA_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {
		String role = session.getAttribute("role").toString();			
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("View_Six_A_Paper_Marks_Tiles");
	}
	
	@RequestMapping(value = "/get6Aviewdata", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> get6Aviewdata(String course_id) {
	 ArrayList<ArrayList<String>> list = daoo.get6AViewdatabyCourse(course_id);
		return list;
	}
}