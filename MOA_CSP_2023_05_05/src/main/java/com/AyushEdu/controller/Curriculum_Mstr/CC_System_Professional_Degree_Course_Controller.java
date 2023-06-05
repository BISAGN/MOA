package com.AyushEdu.controller.Curriculum_Mstr;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_System_Professional_Degree_Course_Dao;
import com.AyushEdu.dao.LMS_Master.SystemDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_System_Professional_Degree_Course_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	CC_System_Professional_Degree_Course_Dao sdao;

	@RequestMapping(value = "admin/System_Professional_Degree_Course_Url", method = RequestMethod.GET)
	public ModelAndView System_Professional_Degree_Course_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemListbyrole(sessionFactory, role));
			Mmap.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("System_Professional_Degree_Course_Tiles");
	}
	
	@RequestMapping(value = "/getDegreelistbySystem_type_Degree_Curri", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreelistbySystem_type_Degree_Curri(String system_id,String type_of_degree) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = common.getDegreelistbySystem_type_Degree_Curri1( system_id,type_of_degree);
		return list;
	}
	
//==========================================SAVE SYSTEM NAME========================================== 	

	@PostMapping(value = "/System_Professional_Degree_Course_Action")
	public ModelAndView System_Professional_Degree_Course_Action(
			@Validated @ModelAttribute("System_Professional_Degree_Course_CMD") EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String system_id = request.getParameter("system_id");
		String type_of_degree = request.getParameter("type_of_degree");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (type_of_degree.equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Degree");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject.");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE where system_id=:system_id and type_of_degree=:type_of_degree and "
					+ "degree_id=:degree_id \n"
					+ " and professional_id=:professional_id and course_id=:course_id and status=1\n"
					+ " and id!=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("type_of_degree", Integer.parseInt(type_of_degree))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id)).setParameter("id", id).uniqueResult();

			String eid = request.getParameter("eid");

			if (!eid.equals("0")) {
				id = Integer.parseInt(eid);
			}

			if (id == 0) {

				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setType_of_degree(Integer.parseInt(type_of_degree));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setStatus(Integer.parseInt(status));
				td.setCourse_id(Integer.parseInt(course_id));

				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = sdao.updateSystem_Professional_Degree_Course(td);
					ra.addAttribute("msg", msg);
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

			tx.commit();

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
	}

	@PostMapping("/getFiltesdpcl_data")
	public @ResponseBody List<Map<String, Object>> getFiltesdpcl_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String type_of_degree,String degree_id,
			String professional_id, String course_id, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTablesysDegProCourselinkDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_id,type_of_degree, degree_id, professional_id, course_id, status, role);

	}

	@PostMapping("/getFiltesdpcl_dataCount")
	public @ResponseBody long getFiltesdpcl(HttpSession sessionUserId, String Search, String system_id,String type_of_degree,
			String degree_id, String professional_id, String course_id, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTablesysDegProCourselinkDataListTotalCount(Search, system_id,type_of_degree, degree_id, professional_id,
				course_id, status, role);

	}
//-----edit

	@RequestMapping(value = "/EditSysDegProfCourselink_Url", method = RequestMethod.POST)
	public ModelAndView EditSysDegProfCourselink_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "system_id", required = false) String system,
			@RequestParam(value = "degree_id", required = false) String degree,
			@RequestParam(value = "professional_id", required = false) String professional,
			@RequestParam(value = "course_id", required = false) String course,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		

		String role = sessionEdit.getAttribute("role").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE system_Details = sdao
				.getsysdegprocourselinkdata(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("System_Professional_Degree_Course_Tiles");
	}

	// edit action
	@RequestMapping(value = "/edit_sdpcl_Action", method = RequestMethod.POST)
	public ModelAndView edit_sdpcl_Action(
			@ModelAttribute("edit_systemCMD") EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();

		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String status = request.getParameter("status");

		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Course");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
		}

		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE where system_id=:system_id and professional_id=:professional_id and course_id=:course_id and status=:status and id !=:id");
			q0.setParameter("system_id", system_id);
			q0.setParameter("professional_id", professional_id);

			q0.setParameter("course_id", course_id);

			q0.setParameter("status", status);

//						q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE set system_id=:system_id,status=:status,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("system_id", system_id)
						.setParameter("status", status).setParameter("course_id", course_id)
						.setParameter("professional_id", professional_id).setParameter("modified_by", username)
						.setParameter("modified_dt", new Date());
//									.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
	}

	// -------------------------SEARCH Battalion
	// -------------------------------------//

	@RequestMapping(value = "/admin/getSearch_sdpcl_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_sdpcl_Master(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "System_name1", required = false) String System_name1, String System_name,
			@ModelAttribute("status1") String status) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String role = session.getAttribute("role").toString();
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));
		Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));

//						Mmap.put("System_name1", System_name1);
//						Mmap.put("status1", status);
//						Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));

		return new ModelAndView("System_Professional_Degree_Course_Tiles", "System_Professional_Degree_Course_CMD",
				new EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE());
	}

	@RequestMapping(value = "/delete_sdpcl_Url", method = RequestMethod.POST)
	public ModelAndView delete_sdpcl_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE set modified_by=:modified_by,modified_date=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));

		}
		return new ModelAndView("redirect:System_Professional_Degree_Course_Url");
	}

}
