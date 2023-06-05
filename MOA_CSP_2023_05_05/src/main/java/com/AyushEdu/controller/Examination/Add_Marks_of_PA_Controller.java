package com.AyushEdu.controller.Examination;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_EXAM_AND_COURSE;
import com.AyushEdu.Models.Examination.CC_EXAM_TB_ADD_MARKS_OF_PA_CHILD;
import com.AyushEdu.Models.Examination.CC_EXAM_TB_ADD_MARKS_OF_PA_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Examination.Add_Marks_of_PA_Dao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Add_Marks_of_PA_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Add_Marks_of_PA_Dao AMDAO;
	
	@Autowired
	private Commondao cd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/Add_Marks_of_PA_url", method = RequestMethod.GET)
	public ModelAndView Add_Marks_of_PA_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Add_Marks_of_PA_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		Mmap.put("msg", msg);
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
//		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
		Mmap.put("getAttemptList", common.getAttemptList(sessionFactory));
		Mmap.put("getExam_SerialList", common.getExam_SerialList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Add_Marks_of_PA_Tiles");
	}
	
	@RequestMapping(value = "/getstu_name", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getstu_name(String degree_id,HttpSession session, String professional_id) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		List<ArrayList<String>> list = AMDAO.getStudent_Name(degree_id,institute_id,role,professional_id);
		return list;
	}
	
	@RequestMapping(value = "/getia_marks", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getia_marks(String degree_id,String professional_id,String course_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		List<ArrayList<String>> list = AMDAO.getIA_Marks(degree_id,professional_id,course_id,role);
		return list;
	}
	
	@RequestMapping(value = "/getDegreeListFromInstituteExam", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeListFromInstituteExam(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		 institute_id = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {
			list = AMDAO.getDegreeListFromInstituteExam(institute_id, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@PostMapping(value = "/Add_Marks_of_PAAction")
	public ModelAndView Add_Marks_of_PAAction(@Validated @ModelAttribute("add_marks_of_pa_CMD") CC_EXAM_TB_ADD_MARKS_OF_PA_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {

		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Add_Marks_of_PA_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String term_id = request.getParameter("term_id");
		String exam_type_id = request.getParameter("exam_type_id");
		String mon_year = request.getParameter("mon_year");
		String exam_serial = request.getParameter("exam_serial");
		String courses = request.getParameter("new_course_topic");
		String stuIds = request.getParameter("student_check_list");
		String coursearr [] = courses.split(",");
		String stuIdsarr [] = stuIds.split(",");
		
//		String course_code = request.getParameter("course_code");
		
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (professional_id == null || professional_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Professional.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (term_id == null || term_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Term.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (exam_serial == null || exam_serial.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Exam Serial.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (exam_type_id == null || exam_type_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Exam Type.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (mon_year == null || mon_year.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Month Year.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		if (courses == null || courses.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:Add_Marks_of_PA_url");
		}
		
		try {

			CC_EXAM_TB_ADD_MARKS_OF_PA_CHILD od = new CC_EXAM_TB_ADD_MARKS_OF_PA_CHILD();
			
					int parent_id = 0;
					
					for(int j=0; j < coursearr.length; j++) {
						
						String ia_marks = request.getParameter("ia_marks_hid"+coursearr[j]);
						
						td.setCreated_by(username);
						td.setCreated_date(date);
						td.setInstitute_id(Integer.parseInt(institute_id));
						td.setDegree_id(Integer.parseInt(degree_id));
						td.setProfessional_id(Integer.parseInt(professional_id));
						td.setTerm_id(Integer.parseInt(term_id));
						td.setExam_type_id(Integer.parseInt(exam_type_id));
						td.setCourse_id(Integer.parseInt(coursearr[j]));
						td.setExam_serial(Integer.parseInt(exam_serial));
						td.setMon_year(mon_year);
//						td.setCourse_code(course_code);
							
						parent_id = (int) sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
							
							for(int i=0; i < stuIdsarr.length; i++) {
								
								String student_name_id = request.getParameter("student_name_id"+coursearr[j]+"_"+stuIdsarr[i]);
								String marks = request.getParameter("marks"+coursearr[j]+"_"+stuIdsarr[i]);
								String attempt_id = request.getParameter("attempt_id"+coursearr[j]+"_"+stuIdsarr[i]);
								
								
								Long c = (Long) sessionHQL.createQuery(
										"select count(c.id) \n"
										+ "from CC_EXAM_TB_ADD_MARKS_OF_PA_PARENT p,CC_EXAM_TB_ADD_MARKS_OF_PA_CHILD c \n"
										+ "where c.p_id=p.id and p.degree_id=:degree_id and p.professional_id=:professional_id and p.term_id=:term_id\n"
										+ "and p.exam_serial=:exam_serial and p.exam_type_id=:exam_type_id and p.course_id=:course_id and c.student_name_id=:student_name_id ")
										.setParameter("degree_id", Integer.parseInt(degree_id))
										.setParameter("professional_id", Integer.parseInt(professional_id))
										.setParameter("term_id", Integer.parseInt(term_id))
										.setParameter("exam_serial", Integer.parseInt(exam_serial))
										.setParameter("exam_type_id", Integer.parseInt(exam_type_id))
										.setParameter("course_id", Integer.parseInt(coursearr[j]))
										.setParameter("student_name_id", Integer.parseInt(student_name_id)).uniqueResult();
//										.setParameter("course_code",String(course_code));
										
									if(attempt_id.equals("ATTEMPT")) {
										if(c == 0) {
											
										if (marks == null || marks.trim().equals("")) {
											ra.addAttribute("msg", "Please Enter marks.");
											return new ModelAndView("redirect:Add_Marks_of_PA_url");
										}
										if(exam_type_id == "PERIODIC ASSESSMENT PA") {
											
											if (Integer.parseInt(marks) > Integer.parseInt(ia_marks)) {
												ra.addAttribute("msg", "Please Enter Valid marks.");
												return new ModelAndView("redirect:Add_Marks_of_PA_url");
											}
										}
										od.setP_id(parent_id);
										od.setStudent_name_id(Integer.parseInt(student_name_id));
										od.setMarks(Integer.parseInt(marks));
										od.setCreated_by(username);
										od.setCreated_date(date);
										
											sessionHQL.save(od);
											sessionHQL.flush();
											sessionHQL.clear();
											
									}else {
										parent_id = 0;
										break;
									}
								}
							}
				 	}
					
					if(parent_id != 0) {
						tx.commit();
						ra.addAttribute("msg", "Data Saved Successfully.");
					}else {
						tx.rollback();
						ra.addAttribute("msg", "Data already Exist.");
					}
					
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
		return new ModelAndView("redirect:Add_Marks_of_PA_url");
	}

	private void setParameter(String string, int parseInt) {
		// TODO Auto-generated method stub
		
	}
}