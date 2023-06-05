package com.AyushEdu.controller.Curriculum_Mstr;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Mmap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_Add_course_Outcome_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_Add_Course_Outcome_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CC_Add_course_Outcome_Mstr_Dao POdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Add_Course_Outcome_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Add_Course_Outcome_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Add_Course_Outcome_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				String role = session.getAttribute("role").toString();	
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
//		 Mmap.put("CourseList", common.getMainCourseList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Add_Course_Outcome_Mstr_Tiles");
	}
	
	//==========================================SAVE/view/Edit Program Outcome========================================== 	

	
		@PostMapping(value = "/Course_OutcomeAction")
		public ModelAndView Course_OutcomeAction(@Validated @ModelAttribute("Course_OutcomeCMD") CC_TB_ADD_COURSE_OUTCOME_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Add_Course_Outcome_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String system_id = request.getParameter("system_id");
			String degree_id = request.getParameter("degree_id");
			String professional_id = request.getParameter("professional_id");
			String course_outcome = request.getParameter("course_outcome");
			String status = request.getParameter("status");
			String course_id = request.getParameter("course_id");
			String co_code  = request.getParameter("co_code");
			
			if (system_id.equals("0")) {
				ra.addAttribute("msg", "Please Select System");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (degree_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (professional_id.equals("0")) {
				ra.addAttribute("msg", "Please Select professional");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (course_id == null || course_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Subject.");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (course_outcome == null || course_outcome.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Course Outcome.");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (validation.isNumerickavi(course_outcome) == true) {
				ra.addAttribute("msg", "Enter Valid Course Outcome. ");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (validation.isAlphabetCDASH(course_outcome) == false) {
				ra.addAttribute("msg", "Course Outcome " + validation.isAlphabetWSCDASH);
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (validation.maxlengthcheck500(course_outcome) == false) {
				ra.addAttribute("msg","Course Outcome "+ validation.MaxlengthcheckMSG500);
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (co_code == null || co_code.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Code.");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (validation.Currmaxlengthcheck5Digit(co_code) == false) {
				ra.addAttribute("msg","Code "+ validation.CurrMaxlengthcheckMSG5Digit);
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (validation.isOnlyAlphabetNumeric(co_code) == false) {
				ra.addAttribute("msg","Code "+ validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}
			if (status == "2"  || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
			}			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  CC_TB_ADD_COURSE_OUTCOME_MSTR where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id "
						+ " and upper(course_outcome)=:course_outcome and course_id=:course_id and co_code=:co_code and status=:status and id !=:id ")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_outcome", td.getCourse_outcome().toUpperCase())
						.setParameter("course_id", td.getCourse_id())
						.setParameter("co_code", td.getCo_code())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					td.setSystem_id(Integer.parseInt(system_id));
					td.setDegree_id(Integer.parseInt(degree_id));
					td.setProfessional_id(Integer.parseInt(professional_id));
					td.setCourse_outcome(course_outcome);
					td.setCourse_id(Integer.parseInt(course_id));
					td.setCo_code(co_code);
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
					td.setCourse_outcome(course_outcome.trim());
					td.setCourse_id(Integer.parseInt(course_id));
					td.setCo_code(co_code);
					td.setModified_by(username);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = POdao.updateCourse_Outcome(td);
						
						ra.addAttribute("msg", "Data Updated Successfully.");
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
			return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
		}
		
		@PostMapping("/getFilterCourse_Outcome_data")
		public @ResponseBody List<Map<String, Object>> getFilterCourse_Outcome_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String course_id,String course_outcome,String co_code, String status,String system_id,String degree_id,
				String professional_id,HttpSession session) {
			String role = session.getAttribute("role").toString();	
			return POdao.DataTableCourse_OutcomeDataList(startPage, pageLength, Search, orderColunm, orderType, course_id,course_outcome,co_code,status,system_id,degree_id, professional_id,role);

		}

		@PostMapping("/getTotalCourse_Outcome_dataCount")
		public @ResponseBody long getTotalCourse_Outcome_dataCount(HttpSession sessionUserId, String Search,String course_id,String course_outcome,String co_code,String status,String system_id,String degree_id,
				String professional_id,HttpSession session) {
			String role = session.getAttribute("role").toString();	
			return POdao.DataTableCourse_OutcomeDataTotalCount(Search,course_id, course_outcome,co_code,status,system_id,degree_id, professional_id,role);
		}
		
		@RequestMapping(value = "/Course_Outcome_Mstr_Delete_Url", method = RequestMethod.POST)
		public ModelAndView Course_Outcome_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
//				SECURITY -- RIDDHI 	
				if(request.getHeader("Referer") == null ) { 
//					session1.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Add_Course_Outcome_Mstr_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				int app = session.createQuery(
						"update CC_TB_ADD_COURSE_OUTCOME_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
			return new ModelAndView("redirect:Add_Course_Outcome_Mstr_Url");
		}
}
