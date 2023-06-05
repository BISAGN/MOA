package com.AyushEdu.controller.Time_Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.Time_Table.AcademicDAO;
import com.AyushEdu.dao.Time_Table.Academic_Schedule_DAO;
import com.AyushEdu.dao.Time_Table.TIME_TABLE_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Academic_Schedule_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Academic_Schedule_DAO asdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Sys_Deg_Map_Inst_DAO  dmdao;
	@Autowired
	AcademicDAO adao;
	
	@RequestMapping(value = "admin/Academic_Schedule_Url", method = RequestMethod.GET)
	public ModelAndView Academic_Schedule_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Academic_Schedule_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			//===========by Parth Rathod======================
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
			String role = session.getAttribute("rolename").toString();
			List<Map<String, Object>> nonlecact1 = adao.GetTermFromNoofpart(role,inst_id);
			System.err.println("nonlecact1============------------"+nonlecact1);
			Mmap.put("nonlecact1", nonlecact1);
			//=================================
			Mmap.put("system", dmdao.Getsytemid_fetch(userid));
			Mmap.put("getSystemForAll", common.getSystemForAll(sessionFactory));
			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("msg", msg);

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Academic_Schedule_Tiles");
	}
	
	@RequestMapping(value = "/GetviewDate_Academic_Prof", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> GetviewDate_Academic_Prof(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String professional_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
	       return asdao.GetviewDate_Academic_Prof(professional_id,institute_id);
	}
	
	@RequestMapping(value = "/GetviewDate_Examination_Prof", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> GetviewDate_Examination_Prof(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String professional_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
	       return asdao.GetviewDate_Examination_Prof(professional_id,institute_id);
	}
	
	@RequestMapping(value = "/GetviewDate_Fee_Prof", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> GetviewDate_Fee_Prof(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String professional_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
	       return asdao.GetviewDate_Fee_Prof(professional_id,institute_id);
	}
	
	@RequestMapping(value = "/GetviewDate_Tran_Curr_Prof", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> GetviewDate_Tran_Curr_Prof(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String professional_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
	       return asdao.GetviewDate_Tran_Curr_Prof(professional_id,institute_id);
	}
	@RequestMapping(value = "/getWorkLoadCalData", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getWorkLoadCalData(String degree,String prof,HttpSession session) {
		String role = session.getAttribute("role").toString();
	       return asdao.GetWorkLoadCalData(degree,prof,role);
	}
	
	@RequestMapping(value = "/getAddedHoursbyCourse", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAddedHoursbyCourse(String course_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		String inst_id = String.valueOf(institute_idList.get(0).getInstitute_id());
	       return asdao.getAddedHoursbyCourseandInst(course_id,inst_id);
	}
}
