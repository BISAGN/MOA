package com.AyushEdu.controller.LMS_Institute;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_CLASSROOM_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.ClassroomDAO1;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Classroom_Controller1 {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	ClassroomDAO1 cls_dao;

	// ==========================================OPEN PAGE
	// Classroom==========================================

	@RequestMapping(value = "ClassroomUrl", method = RequestMethod.GET)
	public ModelAndView ClassroomUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
	//SECURITY RAHUL	
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ClassroomUrl", roleid1);
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		Mmap.put("msg", msg);

		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		return new ModelAndView("Classroom_Tiles");

	}

	// ==========================================SAVE Classroom
	// NAME==========================================

	@PostMapping(value = "/ClassroomAction1")
	public ModelAndView ClassroomAction1(@Validated @ModelAttribute("ClassroomCMD") EDU_LMS_CLASSROOM_MSTR td,
			BindingResult result, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ClassroomUrl", roleid1);
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		String classroom_name = request.getParameter("classroom_name");
		String block_name = request.getParameter("block_name");
		String strength = request.getParameter("strength");
		String status = request.getParameter("status");

		if (classroom_name == null || classroom_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Classroom Name.");
			return new ModelAndView("redirect:ClassroomUrl");
		}
		if (block_name == null || block_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Block Name.");
			return new ModelAndView("redirect:ClassroomUrl");
		}
		if (strength == null || strength.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Strength.");
			return new ModelAndView("redirect:ClassroomUrl");
		}
		if (td.getStatus() == "2" || td.getStatus().equals("2")) {

			model.put("msg", "Only Select Active Status.");

			return new ModelAndView("redirect:ClassroomUrl");

		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
//			String Classroom_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_CLASSROOM_MSTR where upper(classroom_name)=:classroom_name and upper(block_name)=:block_name and upper(strength)=:strength and upper(status)=:status and id !=:id")
					.setParameter("classroom_name", td.getClassroom_name().toUpperCase())
					.setParameter("block_name", td.getBlock_name().toUpperCase())
					.setParameter("strength", td.getStrength().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase()).setParameter("id", id).uniqueResult();

			if (id == 0) {
				td.setClassroom_name(classroom_name);
				td.setBlock_name(block_name);
				td.setStrength(strength);
				td.setCreated_by(username);
				td.setCreated_date(date);
//					td.setCreated_dt(created_dt);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

			tx.commit();

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:ClassroomUrl");
	}

	@PostMapping("/getFilterclassroom_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String classroom_name, String block_name,
			String strength, String status) {

		return cls_dao.DataTableclassroomDataList(startPage, pageLength, Search, orderColunm, orderType, classroom_name,
				block_name, strength, status);

	}

	@PostMapping("/getTotalclassroom_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String classroom_name, String block_name, String strength) {
		return cls_dao.DataTableclassroomDataTotalCount(Search, classroom_name, block_name, strength);

	}

	// -----edit

	@RequestMapping(value = "/Edit_classroomUrl", method = RequestMethod.POST)
	public ModelAndView Edit_classroomUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {
		



		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_LMS_CLASSROOM_MSTR classroom_Details = cls_dao.getclassroomByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		// Mmap.put("classroom_name", common.getClassroomList(sessionFactory));
		Mmap.put("classroom_Details", classroom_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("classroominfo", cls_dao.getclassroomByid(Integer.parseInt(updateid)));

		tx.commit();
		sessionHQL.close();

		return new ModelAndView("editClassroom_Tiles");
	}

	// edit action
	@RequestMapping(value = "/edit_classroom_Action", method = RequestMethod.POST)
	public ModelAndView edit_classroom_Action(@ModelAttribute("edit_classroomCMD") EDU_LMS_CLASSROOM_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ClassroomUrl", roleid1);
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		

		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String classroom_name = request.getParameter("classroom_name").trim();
		String block_name = request.getParameter("block_name").trim();
		String strength = request.getParameter("strength").trim();
		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_CLASSROOM_MSTR where classroom_name=:classroom_name and block_name=:block_name and strength=:strength and status=:status and id !=:id");
			q0.setParameter("classroom_name", classroom_name);
			q0.setParameter("block_name", block_name);
			q0.setParameter("strength", strength);

			q0.setParameter("status", status);

			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_CLASSROOM_MSTR set classroom_name=:classroom_name,block_name=:block_name,strength=:strength,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("classroom_name", classroom_name)
						.setParameter("block_name", block_name).setParameter("strength", strength)
						.setParameter("status", status).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("id", id);
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

		return new ModelAndView("redirect:ClassroomUrl");
	}

	// -------------------------Delete District
	// -------------------------------------

	@RequestMapping(value = "delete_Url3", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Url3(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Classroom", roleid);
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ClassroomUrl", roleid1);
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		
		
		

		ArrayList<String> liststr = new ArrayList<String>();

		String username = session.getAttribute("username").toString();

		try {
			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "update EDU_LMS_CLASSROOM_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
		return new ModelAndView("redirect:ClassroomUrl");
	}

}
