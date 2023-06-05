package com.AyushEdu.controller.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_LINK;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_NCISM.CourseContentDetailDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CourseContentDetailsReportController {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;

	@Autowired
	CourseContentDetailDAO ccddao;

	// ==========================================OPEN PAGE
	// CourseContentDetailReport==========================================

	@RequestMapping(value = "/CourseContentDetailUrl", method = RequestMethod.GET)
	public ModelAndView CourseContentDetailUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CourseContentDetailUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String role = session.getAttribute("role").toString();
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemListbyrole(sessionFactory, role));
		Mmap.put("getTypeOfcontent", common.getTypeOfcontent(sessionFactory));

		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		return new ModelAndView("CourseContentDetail_Tiles");

	}

	@PostMapping("/getFilterCourseContentDetail_data")
	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_name, String degree, String course_name,
			String setname, String module_name, String start_date, String end_date, String type_of_content) {
		
		String role = session.getAttribute("role").toString();
		System.err.println("type_of_content" + type_of_content);
		System.err.println("role=====" +role);

		return ccddao.DataTableCourseContentDetailDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_name, degree, course_name, setname, module_name, start_date, end_date, type_of_content,role);

	}

	@PostMapping("/getTotalCourseContentDetail_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String system_name, String degree, String course_name, String setname, String module_name,
			String start_date, String end_date, String type_of_content) {
		String role = sessionUserId.getAttribute("role").toString();
		System.err.println("role=====" +role);
		return ccddao.DataTableCourseContentDetailDataTotalCount(Search, system_name, degree, course_name, setname,
				module_name, start_date, end_date, type_of_content,role);

	}

	@RequestMapping(value = "/getcourseBYSystem_course", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_ELE_COURSE_LINK> getcourseBYSystem_course(String system_id) {
		System.err.println("sys id----" + system_id);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select l.elec_course_id,c.course_name \r\n"
				+ "from EDU_LMS_SYSTEM_ELE_COURSE_LINK l , EDU_LMS_ELECTIVE_COURSE_MASTER c \r\n"
				+ "where system_id=:system_id and c.status='1' and c.id=cast(l.elec_course_id as integer)");

		q.setParameter("system_id", system_id);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_ELE_COURSE_LINK> clist = (List<EDU_LMS_SYSTEM_ELE_COURSE_LINK>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	@RequestMapping(value = "/getDegreeListBySystem", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeListBySystem(String system_name) {

		ArrayList<ArrayList<String>> list = ccddao.getDegreelistFromsystem(system_name);

		return list;
	}

	@RequestMapping(value = "/getSetListByDegree", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getSetListByDegree(String system_name, String degree) {

		ArrayList<ArrayList<String>> list = ccddao.getSetlistFromDegree(system_name, degree);

		return list;
	}

	@RequestMapping(value = "/getCourseListBySet", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getCourseListBySet(String system_name, String degree,
			String setname) {

		ArrayList<ArrayList<String>> list = ccddao.getCourselistFromSet(system_name, degree, setname);

		return list;
	}

	@RequestMapping(value = "/getModuleListByCourse", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getModuleListByCourse(String course_name) {

		ArrayList<ArrayList<String>> list = ccddao.getCourselistFromSet(course_name);
		

		return list;
	}

}
