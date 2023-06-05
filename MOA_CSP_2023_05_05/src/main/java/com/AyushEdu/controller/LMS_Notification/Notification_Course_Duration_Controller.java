package com.AyushEdu.controller.LMS_Notification;

import java.util.ArrayList;
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
import com.AyushEdu.dao.LMS_Notification.NotiFicationCourseDurationDAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Notification_Course_Duration_Controller {
	
	@Autowired
	private NotiFicationCourseDurationDAO CDDAO;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@RequestMapping(value = "/admin/SendNotiCourseDuration", method = RequestMethod.GET)
	public ModelAndView SendNotiCourseDuration(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request){
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SendNotiCourseDuration", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		
		String role = session.getAttribute("role").toString();
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList", common.getSystemListbyrole(sessionFactory,role));
		 Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		 Mmap.put("course_name_list", common.getCourseNamelist(sessionFactory));
		return new ModelAndView("SendNotiCourseDuration_Tiles");
	}
	
	@RequestMapping(value = "/getDaysofcourseDuration", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDaysofcourseDuration(String system,String course) {
		ArrayList<ArrayList<String>> days = CDDAO.GetDays(system,course);	 
		return days;
	}
	
	@PostMapping("/getFilterNoticourse_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterNoticourse_data(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String course,String system,String degree,String ayushid,String name) {
		return (ArrayList<Map<String, Object>>) CDDAO.getFilterNoticourse_data(startPage, pageLength, Search, orderColunm,orderType,course,system,degree,ayushid,name);
	}

	@PostMapping("/getTotalNoticourse_dataCount")
	public @ResponseBody long getTotalNoticourse_dataCount(HttpSession sessionUserId, String Search,String course,String system,String degree,String ayushid,String name) {
		return CDDAO.DataTotalCountNotiCourseData(Search,course,system,degree,ayushid,name);
	}

}
