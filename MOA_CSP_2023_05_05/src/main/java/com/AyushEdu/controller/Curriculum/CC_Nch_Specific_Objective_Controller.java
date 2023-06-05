package com.AyushEdu.controller.Curriculum;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.tomcat.jni.Mmap;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_SPECIFIC_OBJECTIVE;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Nch_Specific_Objective_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Nch_Specific_Objective_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	
	@Autowired
	CommonController common;
	
	@Autowired
	Nch_Specific_Objective_Dao NSPDao;
	
	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	EmailConfig email;
	
	@Autowired
	private RoleBaseMenuDAO roledao;


	@RequestMapping(value = "admin/nch_specific_objectives_url", method = RequestMethod.GET)
	public ModelAndView nch_specific_objectives_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("nch_specific_objectives_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();		
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("NCH_Specific_Objectives_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	
	
	@PostMapping(value = "/Specific_ObjectiveAction")
	public ModelAndView Specific_ObjectiveAction(@Validated @ModelAttribute("Specific_ObjectiveCMD") CC_TB_NCH_SPECIFIC_OBJECTIVE td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra
			)throws IOException, ParseException {

//		SECURDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("nch_specific_objectives_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String specific_objective = request.getParameter("specific_objective");

		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:nch_specific_objectives_url");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:nch_specific_objectives_url");
		}
		if (professional_id == null || professional_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Professional");
			return new ModelAndView("redirect:nch_specific_objectives_url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:nch_specific_objectives_url");
		}
		if (specific_objective == null || specific_objective.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Specific Objective");
			return new ModelAndView("redirect:nch_specific_objectives_url");
		}
		int id = td.getId() > 0 ? td.getId() : 0;

		Date date = new Date();
		String username = principal.getName();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_NCH_SPECIFIC_OBJECTIVE where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id and course_id=:course_id and  upper(specific_objective)=:specific_objective and status=:status and id !=:id ")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("specific_objective", td.getSpecific_objective().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setCourse_id(Integer.parseInt(course_id));
				td.setSpecific_objective(specific_objective.trim());
				td.setCreated_by(username);
				td.setCreated_date(date);
//				td.setStatus(0);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setSpecific_objective(specific_objective.trim());
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = NSPDao.updateNch_Specific_Objective(td);
					if (msg == "Data Updated Successfully") {
						model.put("msg", msg);
						model.put("Nmsg", "0");
					} else {
						model.put("msg", msg);
						model.put("Nmsg", "1");
					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data already Exist");
					model.put("Nmsg", "1");
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
		
		return new ModelAndView("redirect:nch_specific_objectives_url");
	}
	
	@PostMapping("/getFilter_Nch_Specific_Objective")
	public @ResponseBody List<Map<String, Object>> getFilter_Nch_Specific_Objective(int startPage, int pageLength,
			String Search,String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String specific_objective, String status,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return NSPDao.DataTableNch_Specific_ObjectiveDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id, professional_id, course_id, specific_objective, status, role);
	}

	@PostMapping("/getTotalNch_Specific_ObjectivedataCount")
	public @ResponseBody long getTotalNch_Specific_ObjectivedataCount(String Search, String system_id, String degree_id, String professional_id,
			String course_id, String specific_objective, String status,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return NSPDao.DataTableNch_Specific_ObjectiveDataTotalCount(Search, system_id, degree_id, professional_id, course_id, specific_objective, status, role);
	}
	
			@RequestMapping(value = "/Nch_Specific_Objective_Delete_Url", method = RequestMethod.POST)
			public ModelAndView Nch_Specific_Objective_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
//				SECURITY -- RIDDHI 	
				if(request.getHeader("Referer") == null ) { 
//					session1.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("nch_specific_objectives_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update CC_TB_NCH_SPECIFIC_OBJECTIVE set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("status", 2).executeUpdate();

					
					tx.commit();
					session.close();
					if (app > 0) {
						System.err.println("check delete"+(app > 0));
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
				return new ModelAndView("redirect:nch_specific_objectives_url");
			}
}

	

