package com.AyushEdu.controller.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
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
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;
import com.AyushEdu.dao.LMS_NCISM.University_Institute_Stud_ReportDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class University_Institute_Stud_Report_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;
	@Autowired
	University_Institute_Stud_ReportDAO UISRD;
	@Autowired
	Stud_Elect_Courses_Dao sdc;
	

	@RequestMapping(value = "/UniversityInstituteStudReportUrl", method = RequestMethod.GET)
	public ModelAndView UniversityInstituteStudReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			//SECURITY RAHUL
			String userId = session.getAttribute("userId_for_jnlp").toString();

			if (request.getHeader("Referer") == null) {
			//	session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("UniversityInstituteStudReportUrl", roleid1);

			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			Mmap.put("msg", msg);
			Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("UniversityInstituteStudReport_Tiles");

	}

	@RequestMapping(value = "/getInstituteListFromUniversity", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getInstituteListFromUniversity(HttpSession session,
			String university_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		ArrayList<ArrayList<String>> list = null;
		try {
			list = UISRD.getInstituteListFromUniversity(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getDegreeListFromInstitute", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeListFromInstitute(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {
			list = UISRD.getDegreeListFromInstitute(institute_id, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getCoursesListFromDegree", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getCoursesListFromDegree(HttpSession session, String degree_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		ArrayList<ArrayList<String>> list = null;
		try {
			list = UISRD.getCoursesListFromDegree(degree_id, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getModuleListFromCourses", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getModuleListFromCourses(HttpSession session, String course_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		ArrayList<ArrayList<String>> list = null;
		try {
			list = UISRD.getModuleListFromCourses(course_id, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	@RequestMapping(value = "/University_Institute_Stud_Result_View_Url", method = RequestMethod.POST)
//	public @ResponseBody ArrayList<ArrayList<String>> University_Institute_Stud_Result_View_Url(String uid) {
//		ArrayList<ArrayList<String>> list = null;
//		try {
//			list = UISRD.getPopup_Data(uid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	//change on 23/11/2022
	@RequestMapping(value = "/University_Institute_Stud_Result_View_Url", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> University_Institute_Stud_Result_View_Url(String uid) {
		ArrayList<ArrayList<String>> list = null;
		try {
			String degree_id = sdc.getdegree_list(uid).get(0).get(0);
			String term_id = sdc.getterm_list(uid).get(0).get(0);
			list = UISRD.getPopup_Data(uid,degree_id,term_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping("/getFilterUniversityInstituteStudReportDetails_data")
	public @ResponseBody List<Map<String, Object>> getFilterUniversityInstituteStudReportDetails_data(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String degree_id, String course_id,
			String module_id, HttpSession session) {
		String role = session.getAttribute("role").toString();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		ArrayList<ArrayList<String>> list = UISRD.getInstituteListFromUniversity(userId);
		return UISRD.DataTableUniversityInstituteStudReportDetailsDataList(startPage, pageLength, Search, orderColunm,
				orderType, userId, degree_id, course_id, module_id, role);

	}

	@PostMapping("/getTotalUniversityInstituteStudReport_dataCount")
	public @ResponseBody long getTotalUniversityInstituteStudReport_dataCount(HttpSession sessionUserId, String Search,
			String degree_id, String course_id, String module_id) {
		String role = sessionUserId.getAttribute("role").toString();
		String userId = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return UISRD.DataTableUniversityInstituteStudReportDetailsDataTotalCount(Search, userId, degree_id, course_id,
				module_id, role);

	}

}
