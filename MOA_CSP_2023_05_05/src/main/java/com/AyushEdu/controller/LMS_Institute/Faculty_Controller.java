package com.AyushEdu.controller.LMS_Institute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_FACULTY_MSTR;

import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.FacultyDAO;

import freemarker.core.ParseException;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Faculty_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

//	
	@Autowired
	CommonController common;

	@Autowired
	private FacultyDAO fcl_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	


	@RequestMapping(value = "FacultyUrl", method = RequestMethod.GET)
	public ModelAndView FacultyUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		//SECURITY RAHUL
//		
		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		Mmap.put("msg", msg);
		Mmap.put("getsysList", common.getsysList(sessionFactory));
		Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
		Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
		return new ModelAndView("Faculty_Tiles");
	}

	
	@RequestMapping(value = "/FacultyAction", method = RequestMethod.POST)
	public ModelAndView FacultyAction(@ModelAttribute("FacultyCMD") EDU_LMS_FACULTY_MSTR rm, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
		
		
		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		

		System.err.println(result.hasErrors());
		String roleid = session.getAttribute("roleid").toString();
		//Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid);

		int faculty_id = rm.getId() > 0 ? rm.getId() : 0;

		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String faculty_name = request.getParameter("faculty_name").trim();
		String ayush_id = request.getParameter("ayush_id").trim();
		String status = rm.getStatus();

		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

				 
		if (rm.getSystem_id() == "0") {
			model.put("msg", "Please Select System.");

			return new ModelAndView("redirect:FacultyUrl");
		}

		if (rm.getCourse_id() == "0") {
			model.put("msg", "Please Select Course.");
			return new ModelAndView("redirect:FacultyUrl");
		}

		if (faculty_name.equals("") || faculty_name.equals("null") || faculty_name.equals(null)) {
			model.put("msg", "Please Enter Faculty.");

			return new ModelAndView("redirect:FacultyUrl");
		}
		if (ayush_id.equals("") || ayush_id.equals("null") || ayush_id.equals(null)) {
			model.put("msg", "Please Enter Ayush Id.");

			return new ModelAndView("redirect:FacultyUrl");
		}
		if (rm.getStatus() == "2" || rm.getStatus().equals("2")) {

			model.put("msg", "Only Select Active Status.");

			return new ModelAndView("redirect:FacultyUrl");

		}

		try {

			@SuppressWarnings("deprecation")
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from EDU_LMS_FACULTY_MSTR where faculty_name=:faculty_name and ayush_id=:ayush_id and status=:status  and id!=:id")
					.setString("faculty_name", faculty_name).setString("ayush_id", ayush_id).setString("status", status)
					.setInteger("id", faculty_id).uniqueResult();
			

			if (faculty_id == 0) {
				rm.setCreated_by(username);
				rm.setCreated_date(date);
				rm.setFaculty_name(faculty_name);
				rm.setAyush_id(ayush_id);
				if (c == 0) {
					sessionHQL.save(rm);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			} else {

				if (c == 0) {
					// String msg = rankdao.updaterankcode(rm);
					// model.put("msg", msg);
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:FacultyUrl");
	}

	// -------------------------SEARCH Faculty -------------------------------------

	@RequestMapping(value = "/admin/getSearch_Faculty_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_Faculty_Master(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "system_id1", required = false) int system_id1,
			@RequestParam(value = "course_id1", required = false) int course_id1,
			@RequestParam(value = "Faculty_name1", required = false) String Faculty_name1,
			@RequestParam(value = "Ayush_id1", required = false) String Ayush_id1,
			@RequestParam(value = "status1", required = false) String status1) {

		String roleid = session.getAttribute("roleid").toString();
		//Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid);
		
		
		
		if (request.getHeader("Referer") == null) {
			//	session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

		Mmap.put("system_id1", system_id1);
		Mmap.put("course_id1", course_id1);
		Mmap.put("Faculty_name1", Faculty_name1);
		Mmap.put("Ayush_id1", Ayush_id1);
		Mmap.put("status1", status1);

		Mmap.put("system_id", common.getsysList(sessionFactory));
		Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
		Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));

		return new ModelAndView("Faculty_Tiles");
	}

	@PostMapping("/getFilterfaculty")
	public @ResponseBody List<Map<String, Object>> getFilterfaculty(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system, String course, String faculty, String ayush_id,
			String status) {
		return fcl_Dao.search_Faculty_name(startPage, pageLength, Search, orderColunm, orderType, system, course,
				faculty, ayush_id, status);
	}

	@PostMapping("/getTotalfaculty_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String system,
			String course, String faculty, String ayush_id, String status) {
		return fcl_Dao.DataTablefacultyDataTotalCount(sessionUserId, Search, system, course, faculty, ayush_id);

	}


	@RequestMapping(value = "Edit_Faculty", method = RequestMethod.POST)
	public ModelAndView Edit_Faculty(@ModelAttribute("id2") String updateid, ModelMap Mmap, HttpSession sessionEdit,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {

		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid);
		EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid(Integer.parseInt(updateid));
		Mmap.put("Edit_FacultyCMD", disDetails);
		Mmap.put("system_id", common.getsysList(sessionFactory));
		Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
		Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_FacultyTiles");
	}


	@RequestMapping(value = "/Edit_Faculty_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Faculty_Action(@ModelAttribute("Edit_FacultyCMD") EDU_LMS_FACULTY_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		
		if (request.getHeader("Referer") == null) {
			//	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

		String roleid = session.getAttribute("roleid").toString();
	//	Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid);
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String course_id = request.getParameter("course_id");
		String system_id = request.getParameter("system_id");
		String faculty_name = request.getParameter("faculty_name").trim();
		String ayush_id = request.getParameter("ayush_id").trim();
		String status = request.getParameter("status");

		if (rs.getSystem_id() == "0") {
			EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid((id));
			model.put("Edit_FacultyCMD", disDetails);

			model.put("system_id", common.getsysList(sessionFactory));
			model.put("course_id", common.getCourseNamelist(sessionFactory));
			model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			model.put("msg", "Please Select System");
			// model.put("id2", id);
			return new ModelAndView("Edit_FacultyTiles");
		}

		if (rs.getCourse_id() == "0") {
			EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid((id));
			model.put("Edit_FacultyCMD", disDetails);

			model.put("system_id", common.getsysList(sessionFactory));
			model.put("course_id", common.getCourseNamelist(sessionFactory));
			model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			model.put("getMedcourseName", common.getCourseNamelist(sessionFactory));

			model.put("msg", "Please Select Course.");
//									model.put("id2", id);
			return new ModelAndView("Edit_FacultyTiles");
		}

		if (faculty_name.equals("") || faculty_name.equals("null") || faculty_name.equals(null)) {
			EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid((id));
			model.put("Edit_FacultyCMD", disDetails);
			model.put("system_id", common.getsysList(sessionFactory));
			model.put("course_id", common.getCourseNamelist(sessionFactory));
			model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			model.put("msg", "Please Enter Faculty.");
			// model.put("id2", id);
			return new ModelAndView("Edit_FacultyTiles");
		}

		if (ayush_id.equals("") || ayush_id.equals("null") || ayush_id.equals(null)) {
			EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid((id));
			model.put("Edit_FacultyCMD", disDetails);
			model.put("system_id", common.getsysList(sessionFactory));
			model.put("course_id", common.getCourseNamelist(sessionFactory));
			model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			model.put("msg", "Please Enter Ayush Id.");
			// model.put("id2", id);
			return new ModelAndView("Edit_FacultyTiles");
		}
		if (rs.getStatus() == "2" || rs.getStatus().equals("2")) {
			EDU_LMS_FACULTY_MSTR disDetails = fcl_Dao.getFacultyByid((id));
			model.put("Edit_FacultyCMD", disDetails);
			model.put("system_id", common.getsysList(sessionFactory));
			model.put("course_id", common.getCourseNamelist(sessionFactory));
			model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			model.put("msg", "Only Select Active Status.");
			return new ModelAndView("Edit_FacultyTiles");

		}

		Session session1 = sessionFactory.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {

			Query q0 = session1.createQuery("select count(id) from EDU_LMS_FACULTY_MSTR where system_id=:system_id and "
					+ "course_id=:course_id and faculty_name=:faculty_name and ayush_id=:ayush_id  and status=:status and id !=:id");

			q0.setParameter("faculty_name", faculty_name);
			q0.setParameter("ayush_id", ayush_id);
			q0.setParameter("system_id", system_id);
			q0.setParameter("course_id", course_id);
			q0.setParameter("status", status);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();
			System.err.println(q0);

			if (c == 0) {
				String hql = "update EDU_LMS_FACULTY_MSTR set system_id=:system_id,course_id=:course_id,faculty_name=:faculty_name,ayush_id=:ayush_id,modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("system_id", rs.getSystem_id())
						.setString("course_id", course_id).setString("status", status)
						.setString("faculty_name", faculty_name).setString("ayush_id", ayush_id)
						.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}
			} else {
				model.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:FacultyUrl");
	}


	@RequestMapping(value = "/delete_Faculty", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Faculty(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {


		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FacultyUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<String> liststr = new ArrayList<String>();

		String username = session.getAttribute("username").toString();

		try {
			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "update EDU_LMS_FACULTY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status"
					+ " where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setString("status", "2").setString("modified_by", username)
					.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete Unsuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:FacultyUrl");
	}
				 
}
