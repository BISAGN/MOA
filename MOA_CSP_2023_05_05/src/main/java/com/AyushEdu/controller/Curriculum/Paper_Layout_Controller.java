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
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;
//import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_PAPER_LAYOUT;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Paper_Layout_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum_Mstr.Question_type_Mstr_DAO;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Paper_Layout_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Paper_Layout_Dao Pdao;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	/*
	 * @Autowired EDU_CC_TB_QUESTION_TYPE_MSTR qdao;
	 */

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Paper_Layout_Url", method = RequestMethod.GET)
	public ModelAndView Paper_Layout_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Paper_Layout_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getPaperList", common.getPaperList(sessionFactory));
			Mmap.put("getQuestionList", common.getQuestionList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Paper_Layout_Tiles");
	}

//	==========================================SAVE/view//Edit Question_Type========================================== 	

	@PostMapping(value = "/PaperLayoutAction")
	public ModelAndView PaperLayoutAction(@Validated @ModelAttribute("PaperLayoutCMD") EDU_CC_TB_PAPER_LAYOUT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Paper_Layout_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String paper_id = request.getParameter("paper_id");
		String time = request.getParameter("time");
		String instructions = request.getParameter("instructions");
		String question_type_id = request.getParameter("question_type_id");
		String num_questions = request.getParameter("num_questions");
		String marks_questions = request.getParameter("marks_questions");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (paper_id == null || paper_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Paper.");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		
		if (time == null || time.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Time");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (validation.maxlengthcheckneetmark(time) == false) {
			ra.addAttribute("msg", "Time" + validation.MaxlengthcheckMSGneetmark3);
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (instructions == null || instructions.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Instructions");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (validation.maxlengthcheck100(instructions) == false) {
			ra.addAttribute("msg","Instructions "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (question_type_id == null || question_type_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Question Type.");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (num_questions == null || num_questions.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Number of Question");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (validation.maxlengthcheckneetmark(num_questions) == false) {
			ra.addAttribute("msg", "Number of Questions " + validation.MaxlengthcheckMSGneetmark3);
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (marks_questions == null || marks_questions.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Question Marks");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (validation.maxlengthcheckneetmark(marks_questions) == false) {
			ra.addAttribute("msg", "Marks per Question " + validation.MaxlengthcheckMSGneetmark3);
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Paper_Layout_Url");
		}
		
//		System.err.print("jay-----------------------------");
		int id = td.getId() > 0 ? td.getId() : 0;
//		int id =Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		// String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.print("chirag-----------------------------");
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_PAPER_LAYOUT where system_id=:system_id and degree_id=:degree_id and "
							+ "professional_id=:professional_id and course_id=:course_id and upper(instructions)=:instructions and paper_id=:paper_id and time=:time and question_type_id=:question_type_id and num_questions=:num_questions and marks_questions=:marks_questions and status=:status and id !=:id")

					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("instructions", td.getInstructions().toUpperCase())
					.setParameter("paper_id", td.getPaper_id()).setParameter("time", td.getTime())
					.setParameter("question_type_id", td.getQuestion_type_id())
					.setParameter("num_questions", td.getNum_questions())
					.setParameter("marks_questions", td.getMarks_questions()).setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();

			if (id == 0) {
				td.setInstructions(instructions);
				td.setPaper_id(Integer.parseInt(paper_id));
				td.setTime(Integer.parseInt(time));
				td.setQuestion_type_id(Integer.parseInt(question_type_id));
				td.setNum_questions(Integer.parseInt(num_questions));
				td.setMarks_questions(Integer.parseInt(marks_questions));
				// td.setFac_course_id(fac_course_id);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
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
				td.setInstructions(instructions);
//				td.setFac_course_id(Integer.parseInt(fac_course_id));
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);

					String msg = Pdao.updatePaperLayout(td);
//					if (msg == "Data Updated Successfully") {
//						model.put("msg", msg);
//						model.put("Nmsg", "0");
//					} else {
//						model.put("msg", msg);
//						model.put("Nmsg", "1");
//					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
//					model.put("msg", "Data already Exist");
//					model.put("Nmsg", "1");
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

		return new ModelAndView("redirect:Paper_Layout_Url");
	}

	@PostMapping("/getFilterpaper_layout_data")

	public @ResponseBody List<Map<String, Object>> getFilterpaper_layout_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id, String time, String instructions,
			String question_type_id, String num_questions, String marks_questions, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTablePaperLayoutDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, paper_id, time, instructions, question_type_id, num_questions,
				marks_questions, status, role);

	}

	@PostMapping("/getTotalpaper_layout_dataCount")
	public @ResponseBody long getTotalpaper_layout_dataCount(HttpSession sessionUserId, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String paper_id, String time,
			String instructions, String question_type_id, String num_questions, String marks_questions, String status,
			HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTablePaperLayoutDataTotalCount(Search, system_id, degree_id, professional_id, course_id,
				paper_id, time, instructions, question_type_id, num_questions, marks_questions, status, role);

	}

	@RequestMapping(value = "/Edit_Paper_Layout_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Question_Type_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "paper_id", required = false) String paper_id,
			@RequestParam(value = "time", required = false) String time,
			@RequestParam(value = "instructions", required = false) String instructions,
			@RequestParam(value = "question_type_id", required = false) String question_type_id,
			@RequestParam(value = "num_questions", required = false) String num_questions,
			@RequestParam(value = "marks_questions", required = false) String marks_questions,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Paper_Layout_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_CC_TB_PAPER_LAYOUT Paper_Layout_Details = Pdao.getPaperLayoutByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("paper_id", paper_id);
		Mmap.put("time", time);
		Mmap.put("instructions", instructions);
		Mmap.put("question_type_id", question_type_id);
		Mmap.put("num_questions", num_questions);
		Mmap.put("marks_questions", marks_questions);
		Mmap.put("status", status);
		Mmap.put("Paper_Layout_Details", Paper_Layout_Details);
		Mmap.put("PaperLayoutinfo", Pdao.getPaperLayoutByid(Integer.parseInt(updateid)));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Paper_Layout_Tiles");
	}

	@RequestMapping(value = "/Paper_Layout_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Question_Type_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Paper_Layout_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_CC_TB_PAPER_LAYOUT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:Paper_Layout_Url");
	}

	@RequestMapping(value = "/getPaperLayout0viewdata", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPaperLayout0viewdata(String course_id) {
		List<Map<String, Object>> list = PARDAO.DataTableEdu_Reg_Report_masterDataList_pdf(course_id);
		return list;
	}

	@RequestMapping(value = "/getPaperLayoutviewdata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getPaperLayoutviewdata(String course_id) {
		ArrayList<ArrayList<String>> table_6E1 = PARDAO.table_6E_paper_layout(course_id);
		return table_6E1;
	}

	@RequestMapping(value = "/getPaperLayout2viewdata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getPaperLayout2viewdata(String course_id) {
		ArrayList<ArrayList<String>> table_6E2 = PARDAO.table_6E_paper_layout2(course_id);
		return table_6E2;
	}
}
