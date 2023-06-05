package com.AyushEdu.controller.Question_Bank;
import java.security.Principal;
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
import com.AyushEdu.Models.QuizBank.EDU_LMS_PAPER_GENERATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Master.Module_Mstr_Dao;
import com.AyushEdu.dao.Question_Bank.Paper_GenerationDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Paper_GenerationController {
	
	@Autowired
	Paper_GenerationDao  Mdao;
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Commondao cd;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/Paper_GenerationUrl" , method = RequestMethod.GET)
	public ModelAndView Paper_GenerationUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Paper_GenerationUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			Mmap.put("getSystemList", cd.getinstitute_system(Integer.parseInt(institute_id)));
			Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
			Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory));
			Mmap.put("getSetList",common.getSetList(sessionFactory));
			Mmap.put("getModuleList",common.getModuleList(sessionFactory));
			Mmap.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Paper_GenerationTiles");
	}
	
	@PostMapping(value = "/papergenerationAction")
	public ModelAndView papergenerationAction(@Validated @ModelAttribute("papergenerationCMD") EDU_LMS_PAPER_GENERATION td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {


		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Paper_GenerationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
		
		String system_id= request.getParameter("system_id");
		String degree_id= request.getParameter("degree_id");
		String course_id=request.getParameter("course_id");
		String set_id= request.getParameter("set_id");
		String total_que= request.getParameter("total_que");
		String total_marks=request.getParameter("total_marks");
		String easy= request.getParameter("easy");
		String medium= request.getParameter("medium");
		String hard=request.getParameter("hard");
		String passing_marks=request.getParameter("passing_marks");
		String module_id=request.getParameter("module_id");
		String exam_name=request.getParameter("exam_name");
		

		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (set_id == null || set_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Set.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (module_id == null || module_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Module.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		
		if (exam_name == null || exam_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Exam Name.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (total_que == null || total_que.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Total Question.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (validation.maxlengthcheck2(total_que) == false) {
			ra.addAttribute("msg", "Total Question " + validation.MaxlengthcheckMSG2);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (total_marks == null || total_marks.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (validation.isOnlyNumer(total_marks) == true) {
			ra.addAttribute("msg", "Total Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
//		if (validation.isOnlyNumer1to9(total_marks) == true) {
//			ra.addAttribute("msg", "Total Marks " + validation.isOnlyNumerMSG1to9);
//			return new ModelAndView("redirect:Paper_GenerationUrl");
//		}
		if (easy == null || easy.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Easy Question Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (validation.isOnlyNumer(easy) == true) {
			ra.addAttribute("msg", "Easy Question Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (medium == null || medium.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Medium Question Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (validation.isOnlyNumer(medium) == true) {
			ra.addAttribute("msg", "Medium Question Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (hard == null || hard.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Hard Question Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (validation.isOnlyNumer(hard) == true) {
			ra.addAttribute("msg", "Hard Question Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (passing_marks == null || passing_marks.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Passing Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
//		if (validation.isOnlyNumer1to9(passing_marks) == true) {
//			ra.addAttribute("msg", "Passing Marks " + validation.isOnlyNumerMSG1to9);
//			return new ModelAndView("redirect:Paper_GenerationUrl");
//		}
		if (validation.isOnlyNumer(passing_marks) == true) {
			ra.addAttribute("msg", "Passing Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		if (td.getTotal_marks() < td.getPassing_marks() ) {
			ra.addAttribute("msg", "Passing Marks Should Be Less Than Total Marks.");
			return new ModelAndView("redirect:Paper_GenerationUrl");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_PAPER_GENERATION where system_id=:system_id and course_id=:course_id and set_id=:set_id "
					+ "and total_que=:total_que and total_marks=:total_marks and easy=:easy and medium=:medium and hard=:hard and passing_marks=:passing_marks and module_id=:module_id"
					+ " and exam_name=:exam_name and id!=:id")
					
					.setParameter("system_id", td.getSystem_id())
					.setParameter("course_id", td.getCourse_id())
					.setParameter("set_id", td.getSet_id())
					.setParameter("total_que", td.getTotal_que())
					.setParameter("total_marks", td.getTotal_marks())
					.setParameter("easy", td.getEasy())
					.setParameter("medium", td.getMedium())
					.setParameter("hard", td.getHard())
					.setParameter("passing_marks", td.getPassing_marks())
					.setParameter("module_id", td.getModule_id())
					.setParameter("exam_name", td.getExam_name())
					.setParameter("id", id).uniqueResult();
			
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
		return new ModelAndView("redirect:Paper_GenerationUrl");
	}
	
	@PostMapping("/getFilterPaper_Gen_data")
	public @ResponseBody List<Map<String, Object>> getFilterPaper_Gen_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,HttpSession session, String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks) {
		
		String role = session.getAttribute("role").toString();
		return Mdao.DataTablePaper_GenDataList(startPage, pageLength, Search, orderColunm, orderType,session,system_id, course_id,
				set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks,role);

	}
	@PostMapping("/getTotalPaper_Gen_dataCount")
	public @ResponseBody long getTotalPaper_Gen_dataCount(HttpSession sessionUserId, String Search, String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks) {
		String role = sessionUserId.getAttribute("role").toString();
		System.err.println("role=====" +role);
		return Mdao.DataTablePaper_GenDataTotalCount(Search,system_id, course_id,
				set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks,role);
	}
	
}
