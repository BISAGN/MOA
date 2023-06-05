package com.AyushEdu.controller.LMS_Student;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Student_QueryController {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	@Autowired
	Commondao cdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private ValidationController validation;
	
		@GetMapping(value = "/Student_Query_Url")
		public ModelAndView Student_Query_Url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			try {
			
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Student_Query_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			model.addAttribute("msg", msg);
			model.put("getteacher_list", cdao.getteacher_list(userid));
			model.put("getTeacherList", common.getTeacherList(sessionFactory));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("Student_Query_Tiles");
		}
  
		@PostMapping(value = "/StudentQuery_action")
		public ModelAndView StudentQuery_action(@Validated @ModelAttribute("StudentQuery_cmd") TB_NOTIFICATION td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {
			
			Map<String,String> mObj=new HashMap<>();
			
			String query = request.getParameter("query");
		
			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			String userId = session.getAttribute("userId_for_jnlp").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			if (query == null || query.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Query");
				return new ModelAndView("redirect:Student_Query_Url");
			}
			if (validation.maxlengthcheck100(query) == false) {
		 		ra.addAttribute("msg","Query  "+ validation.MaxlengthcheckMSG100);
		 		return new ModelAndView("redirect:Student_Query_Url");
			 }
			if (request.getParameter("in_teacher_id_hid_ch") == null || request.getParameter("in_teacher_id_hid_ch").trim().equals("")) {
				ra.addAttribute("msg", "Please Select Teacher");
				return new ModelAndView("redirect:Student_Query_Url");
			}
			try {
	
				String in_teacher_id_hid_ch[] = request.getParameter("in_teacher_id_hid_ch").split(",");
				
				for (int k = 0; k < in_teacher_id_hid_ch.length; k++) {
					
					TB_NOTIFICATION add = new TB_NOTIFICATION();
					add.setMessage(query);
					add.setCreated_by(username);
					add.setCreated_date(date);
					add.setFrom_name_send(userId);
					add.setTo_name_sent(in_teacher_id_hid_ch[k]);
					add.setStatus("1");
				
					sessionHQL.save(add);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
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
			return new ModelAndView("redirect:Student_Query_Url");
		}
}
