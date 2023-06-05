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
import com.AyushEdu.dao.Curriculum.View_SixF_Distribution_theoryDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_View_SixF_Distribution_Theory_Controller {

	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	View_SixF_Distribution_theoryDao sdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@RequestMapping(value = "admin/SixF_View_Url", method = RequestMethod.GET)
	public ModelAndView SixF_View_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SixF_View_Url", roleid1);		
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
		return new ModelAndView("SixF_View_Tiles");
	}
	
	@RequestMapping(value = "/get_SixF_viewdata", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> get_SixF_viewdata(String course_id) {
	 ArrayList<ArrayList<String>> list = sdao.getSixFViewdata(course_id);
		return list;
	}
}
