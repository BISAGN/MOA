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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_EVALUATION_METHOD_PA;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_EVALUATION_METHOD_PA_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Evaluation_method_PADao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Evaluation_method_PA_Controller {

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
	private Evaluation_method_PADao EMPDAO;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Evalu_Method_PA_Url", method = RequestMethod.GET)
	public ModelAndView Evalu_Method_PA_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Evalu_Method_PA_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
//			Mmap.put("getEval_Method_PA", common.getEval_Method_PA(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Evalu_Method_PA_Tiles");
	}
	@RequestMapping(value = "/getEval_Method_PA", method = RequestMethod.POST)
	public @ResponseBody List<EDU_CC_TB_EVALUATION_METHOD_PA_MSTR> getEval_Method_PA(String system_id) {
		List<EDU_CC_TB_EVALUATION_METHOD_PA_MSTR> list = common.getEval_Method_PA_bySystem(sessionFactory, system_id);
		return list;
	}
	@PostMapping(value = "/evalu_method_paAction")
	public ModelAndView evalu_method_paAction(
			@Validated @ModelAttribute("evalu_method_paCMD") EDU_CC_TB_EVALUATION_METHOD_PA td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Evalu_Method_PA_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String evaluation_method_pa_id = request.getParameter("evaluation_method_pa_id");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Evaluation_method_PA.jsp");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (evaluation_method_pa_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Evaluation Method PA");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Evalu_Method_PA_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_EVALUATION_METHOD_PA where system_id=:system_id and degree_id=:degree_id and "
							+ "professional_id=:professional_id and course_id=:course_id and evaluation_method_pa_id=:evaluation_method_pa_id"
							+ " and status=:status and id !=:id")

					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("evaluation_method_pa_id", Integer.parseInt(evaluation_method_pa_id))
					.setParameter("status", td.getStatus()).setParameter("id", id).uniqueResult();

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
				td.setEvaluation_method_pa_id(Integer.parseInt(evaluation_method_pa_id));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = EMPDAO.updateEvalu_Method_PA(td);

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
		return new ModelAndView("redirect:Evalu_Method_PA_Url");
	}
		

	@PostMapping("/getFilterEvalu_Method_PA_data")
	public @ResponseBody List<Map<String, Object>> getFilterEvalu_Method_PA_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String evaluation_method_pa_id, String status,
			HttpSession session) {
		String role = session.getAttribute("role").toString();
		return EMPDAO.DataTableEvalu_Method_PADataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, evaluation_method_pa_id, status, role);

	}

	@PostMapping("/getTotalEvalu_Method_PA_dataCount")
	public @ResponseBody long getTotalEvalu_Method_PA_dataCount(HttpSession sessionUserId, String Search,
			String system_id, String degree_id, String professional_id, String course_id,
			String evaluation_method_pa_id,String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return EMPDAO.DataTableEvalu_Method_PADataTotalCount(Search, system_id, degree_id, professional_id, course_id,
				evaluation_method_pa_id,status, role);
	}

	@RequestMapping(value = "/Evalu_Method_PA_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Evalu_Method_PA_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Evalu_Method_PA_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_CC_TB_EVALUATION_METHOD_PA set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:Evalu_Method_PA_Url");
	}

	@RequestMapping(value = "/getView_Evaluation_PA_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getView_Evaluation_PA_data(String course_id) {
		List<ArrayList<String>> Table6Dnlac_list = PARDAO
				.Table6D_Evaluation_Methods_For_Periodical_Assessment(course_id);

		return Table6Dnlac_list;
	}

}
