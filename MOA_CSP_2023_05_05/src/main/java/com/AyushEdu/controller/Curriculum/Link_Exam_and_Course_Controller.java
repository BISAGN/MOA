package com.AyushEdu.controller.Curriculum;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_EXAM_AND_COURSE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Link_Exam_and_CourseDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Link_Exam_and_Course_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;

	@Autowired
	private Link_Exam_and_CourseDao LECDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Link_Exam_and_Course_Url", method = RequestMethod.GET)
	public ModelAndView Link_Exam_and_Course_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Link_Exam_and_Course_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Link_Exam_and_Course_Url_Tiles");
	}

	@PostMapping(value = "/link_exam_and_courseAction")
	public ModelAndView link_exam_and_courseAction(
			@Validated @ModelAttribute("link_exam_and_courseCMD") CC_TB_LINK_EXAM_AND_COURSE td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Exam_and_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String no_of_exam = request.getParameter("no_of_exam");
		String exam_type_id = request.getParameter("exam_type_id");
		String term_id = request.getParameter("term_id");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (term_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Term");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (exam_type_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Exam Type");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (no_of_exam == null || no_of_exam.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter No_of Exam.");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (validation.isOnlyNumer(no_of_exam) == true) {
			ra.addAttribute("msg", "No of Exam " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (validation.maxlengthcheck2(no_of_exam) == false) {
			ra.addAttribute("msg", "No of Exam " + validation.MaxlengthcheckMSG2);
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Link_Exam_and_Course_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_LINK_EXAM_AND_COURSE where system_id=:system_id and degree_id=:degree_id and "
							+ "professional_id=:professional_id and course_id=:course_id and term_id=:term_id and exam_type_id=:exam_type_id "
							+ " and no_of_exam=:no_of_exam and status=:status and id !=:id")

					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("term_id", Integer.parseInt(term_id))
					.setParameter("exam_type_id", Integer.parseInt(exam_type_id))
					.setParameter("no_of_exam", Integer.parseInt(no_of_exam)).setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();

			if (id == 0) {
//				td.setCourse_id(Integer.parseInt(course_id));
//				td.setTopic(topic);
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
				td.setCourse_id(Integer.parseInt(course_id));
				td.setTerm_id(Integer.parseInt(term_id));
				td.setExam_type_id(Integer.parseInt(exam_type_id));
				td.setNo_of_exam(Integer.parseInt(no_of_exam));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = LECDAO.updateLink_Exam_and_Course(td);

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
		return new ModelAndView("redirect:Link_Exam_and_Course_Url");
	}

	@PostMapping("/getFilterLink_Exam_and_Course_data")
	public @ResponseBody List<Map<String, Object>> getFilterLink_Exam_and_Course_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String term_id, String exam_type_id, String no_of_exam,
			String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return LECDAO.DataTableLink_Exam_and_CourseDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_id, degree_id, professional_id, course_id, term_id, exam_type_id, no_of_exam, status, role);

	}

	@PostMapping("/getTotalLink_Exam_and_Course_dataCount")
	public @ResponseBody long getTotalLink_Exam_and_Course_dataCount(HttpSession sessionUserId, String Search,
			String system_id, String degree_id, String professional_id, String course_id, String term_id,
			String exam_type_id, String no_of_exam,String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return LECDAO.DataTableLink_Exam_and_CourseDataTotalCount(Search, system_id, degree_id, professional_id,
				course_id, term_id, exam_type_id, no_of_exam,status, role);
	}

	@RequestMapping(value = "/Link_Exam_and_Course_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Topic_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Exam_and_Course_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update CC_TB_LINK_EXAM_AND_COURSE set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete" + (app > 0));
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
		return new ModelAndView("redirect:Link_Exam_and_Course_Url");
	}
}
