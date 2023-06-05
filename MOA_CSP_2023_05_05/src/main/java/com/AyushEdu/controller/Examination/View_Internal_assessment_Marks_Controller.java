package com.AyushEdu.controller.Examination;

import java.util.ArrayList;
import java.util.List;

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
import com.AyushEdu.dao.Examination.View_Internal_assessment_MarksDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class View_Internal_assessment_Marks_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	@Autowired
	CommonController common;

	@Autowired
	View_Internal_assessment_MarksDao IAM;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/View_Internal_assessment_Marks_Url", method = RequestMethod.GET)
	public ModelAndView View_Internal_assessment_Marks_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String userid = session.getAttribute("userId_for_jnlp").toString();

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("View_Internal_assessment_Marks_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			String degree_id = request.getParameter("degree_hid");
			Mmap.put("msg", msg);
			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("degree", IAM.Getdegreeid_fetch(userid));

//			Mmap.put("CourseList", common.getMainCourseList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("View_Internal_assessment_Marks_Tiles");
	}

//	@RequestMapping(value = "/getviewInternal_ass_marks_data", method = RequestMethod.POST)
//	public @ResponseBody List<ArrayList<String>> getviewInternal_ass_marks_data(String course_id,String professional_id,HttpSession session ) {
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//		List<ArrayList<String>> list = AMDAO.getStudent_Name(degree_id,institute_id);
//		return list;
//	}
	@RequestMapping(value = "/getviewInternal_ass_marks_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getviewInternal_ass_marks_data(String course_id,
			String professional_id, String exam_type, String exam_seral, HttpSession session) {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<ArrayList<String>> list = IAM.GetviewInternal_ass_marks(course_id, professional_id, userid, exam_type,
				exam_seral, role);
		return list;
	}

//	@RequestMapping(value = "/getGenerate_degree_id", method = RequestMethod.POST)
//	public @ResponseBody List<ArrayList<String>> getGenerate_degree_id(HttpSession session) {
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		
//		List<ArrayList<String>> list = IAM.Getdegreeid_fetch(userid);
//		return list;
//	}
}
