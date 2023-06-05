package com.AyushEdu.controller.Curriculum;

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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Distribution_Practical_ExamDao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Distribution_Practical_Exam_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	private Distribution_Practical_ExamDao Pdao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Distribution_practical_exam_Url", method = RequestMethod.GET)
	public ModelAndView Distribution_practical_exam_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Distribution_practical_exam_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Distribution_practical_exam_Tiles");
	}

	// ==========================================SAVE/view//Edit
	// Professional==========================================

	@PostMapping(value = "/Distribute_practical_examAction")
	public ModelAndView Distribute_practical_examAction(
			@Validated @ModelAttribute("Distribute_practical_examCMD") EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Distribution_practical_exam_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String head = request.getParameter("head");
		String mark = request.getParameter("mark");
		String status = request.getParameter("status");
		
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (head == null || head.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Heads.");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (validation.maxlengthcheck100(head) == false) {
			ra.addAttribute("msg","Instructions "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (mark == null || mark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Marks.");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (validation.isOnlyNumer(mark) == true) {
			ra.addAttribute("msg", " Mark " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (validation.maxlengthcheckneetmark(mark) == false) {
			ra.addAttribute("msg", "No. Of Activities " + validation.MaxlengthcheckMSGneetmark3);
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Distribution_practical_exam_Url");
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM where  system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id and course_id=:course_id and upper(head)=:head and mark=:mark and status=:status and id !=:id")

					.setParameter("system_id", td.getSystem_id()).setParameter("degree_id", td.getDegree_id())
					.setParameter("professional_id", td.getProfessional_id())
					.setParameter("course_id", td.getCourse_id()).setParameter("head", td.getHead())
					.setParameter("mark", td.getMark()).setParameter("status", td.getStatus()).setParameter("id", id)
					.uniqueResult();

			if (id == 0) {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setCourse_id(Integer.parseInt(course_id));
				td.setHead(head);
				td.setMark(Integer.parseInt(mark));
				td.setCreated_by(userid);
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
				td.setHead(head);
				td.setMark(Integer.parseInt(mark));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Pdao.updateDistribution_practical_exam(td);
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
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Distribution_practical_exam_Url");
	}

	@PostMapping("/getFilterDistribution_practical_exam_data")
	public @ResponseBody List<Map<String, Object>> getFilterDistribution_practical_exam_data(int startPage,
			int pageLength, String orderColunm, String orderType, String Search, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTableDistribution_practical_examDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_id, degree_id, professional_id, course_id, head, mark, status, role);
	}

	@PostMapping("/getTotalDistribution_practical_exam_dataCount")
	public @ResponseBody long getTotalDistribution_practical_exam_dataCount(HttpSession sessionUserId, String Search,
			String system_id, String degree_id, String professional_id, String course_id, String head, String mark,
			String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTableDistribution_practical_examDataTotalCount(Search, system_id, degree_id, professional_id,
				course_id, head, mark, status, role);
	}

	@RequestMapping(value = "/getdegreelistbysystem", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getdegreelistbysystem(String system_id) {
		ArrayList<ArrayList<String>> list = Pdao.getdegreelistbysystem(system_id);
		return list;
	}

	@RequestMapping(value = "/deleteDistribution_practical_exam_Url", method = RequestMethod.POST)
	public ModelAndView deleteDistribution_practical_exam_Url(@ModelAttribute("id2") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Distribution_practical_exam_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:Distribution_practical_exam_Url");
	}

	@RequestMapping(value = "/gettable6HDistributionofPracticalExamviewdata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> gettable6HDistributionofPracticalExamviewdata(String course_id) {
		List<ArrayList<String>> table_6H1 = PARDAO.table_6H_I_Distribution_of_Practical_Exam(course_id);
		return table_6H1;
	}

	@RequestMapping(value = "/gettable6HDistributionofPracticalExam1viewdata", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> gettable6HDistributionofPracticalExam1viewdata(String course_id) {
		List<Map<String, Object>> examination_list = PARDAO.examination_list(course_id);
		return examination_list;
	}
}
