package com.AyushEdu.controller.Curriculum;

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
import com.AyushEdu.Models.Curriculum.EDU_CC_LINK_REFERENCE_RESOURCES_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.Reference_Resours_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Reference_Resourses_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;
	@Autowired
	Reference_Resours_Dao sdao;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Reference_Resourses_Url", method = RequestMethod.GET)
	public ModelAndView Reference_Resourses_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Reference_Resourses_Tiles");
	}

//==========================================SAVE ========================================== 	

	@PostMapping(value = "/Reference_Resourses_Action")
	public ModelAndView Reference_Resourses_Action(
			@Validated @ModelAttribute("Reference_Resourses_CMD") EDU_CC_LINK_REFERENCE_RESOURCES_MSTR td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String resource = request.getParameter("resource");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject.");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (resource == null || resource.equals("")) {
			ra.addAttribute("msg", "Please Enter Reference Resourses.");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (validation.checkDescriptionLengthHelpdeskLength(resource) == false) {
			ra.addAttribute("msg","Reference_Resourses "+ validation.DescriptionLengthHelpdeskMSG);
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Reference_Resourses_Url");
		}


		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_LINK_REFERENCE_RESOURCES_MSTR where system_id=:system_id and degree_id=:degree_id \n"
							+ " and professional_id=:professional_id and resource=:resource and course_id=:course_id and status=1\n"
							+ " and id!=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id)).setParameter("resource", resource)
					.setParameter("id", id).uniqueResult();

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
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setStatus(Integer.parseInt(status));
				td.setCourse_id(Integer.parseInt(course_id));
				td.setResource(resource);

				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = sdao.updateReferenceResouces(td);
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

		return new ModelAndView("redirect:Reference_Resourses_Url");
	}

	@PostMapping("/getFilterr_data")
	public @ResponseBody List<Map<String, Object>> getFilterr_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String status, String resource, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.ReferenceResoursesDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, status, resource,role );

	}

	@PostMapping("/getFilterr_dataCount")
	public @ResponseBody long getFilterr(HttpSession sessionUserId, String Search, String system_id, String degree_id,
			String professional_id, String course_id, String status, String resource, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTableReferenceResourcesTotalCount(Search, system_id, degree_id, professional_id, course_id,
				status, resource, role);

	}

//-----edit

	@RequestMapping(value = "/EditReference_Resourses_Url", method = RequestMethod.POST)
	public ModelAndView EditReference_Resourses_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "system_id", required = false) String system,
			@RequestParam(value = "degree_id", required = false) String degree,
			@RequestParam(value = "professional_id", required = false) String professional,
			@RequestParam(value = "course_id", required = false) String course,
			@RequestParam(value = "resource", required = false) String resource,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String role = sessionEdit.getAttribute("role").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_CC_LINK_REFERENCE_RESOURCES_MSTR system_Details = sdao
				.getreferenceresoursekdata(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Reference_Resourses_Tiles");
	}

	// edit action
	@RequestMapping(value = "/Edit_Reference_Resourses_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Reference_Resourses_Action(
			@ModelAttribute("edit_referenceCMD") EDU_CC_LINK_REFERENCE_RESOURCES_MSTR rs, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			RedirectAttributes ra) throws ParseException {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();

		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String status = request.getParameter("status");
		String resource = request.getParameter("resource");
		System.out.println("resource=====>>" + resource);

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
					"select count(id) from EDU_CC_LINK_REFERENCE_RESOURCES_MSTR where system_id=:system_id and professional_id=:professional_id and course_id=:course_id and resource=:resource and status=:status and id !=:id");
			q0.setParameter("system_id", system_id);
			q0.setParameter("professional_id", professional_id);

			q0.setParameter("course_id", course_id);
			q0.setParameter("status", status);

			q0.setParameter("resource", resource);

//						q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_CC_LINK_REFERENCE_RESOURCES_MSTR set system_id=:system_id,status=:status,professional_id=:professional_id,course_id=:course_id,resource=:resource,modified_by=:modified_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("system_id", system_id)
						.setParameter("status", status).setParameter("course_id", course_id)
						.setParameter("professional_id", professional_id).setParameter("resource", resource)
						.setParameter("modified_by", username).setParameter("modified_dt", new Date());
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

		return new ModelAndView("redirect:Reference_Resourses_Url");
	}

	// -------------------------SEARCH Battalion
	// -------------------------------------//

	@RequestMapping(value = "/admin/getSearch_Reference_Resourses_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_Reference_Resourses_Master(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "System_name1", required = false) String System_name1, String System_name,
			@ModelAttribute("status1") String status) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
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

		return new ModelAndView("Reference_Resourses_Tiles", "edit_referenceCMD",
				new EDU_CC_LINK_REFERENCE_RESOURCES_MSTR());
	}

	@RequestMapping(value = "/delete_Re_Url", method = RequestMethod.POST)
	public ModelAndView delete_Re_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reference_Resourses_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_CC_LINK_REFERENCE_RESOURCES_MSTR set modified_by=:modified_by,modified_date=:modified_dt,status=:status where id=:id")
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
		return new ModelAndView("redirect:Reference_Resourses_Url");
	}

	@RequestMapping(value = "/getView_Resource_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getView_Resource_data(String course_id) {
		ArrayList<ArrayList<String>> reference_resourses_list = PARDAO.tablereference_resourses(course_id);
		return reference_resourses_list;
	}

}
