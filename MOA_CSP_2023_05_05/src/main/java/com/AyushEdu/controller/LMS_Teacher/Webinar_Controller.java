package com.AyushEdu.controller.LMS_Teacher;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mvel2.sh.Command;
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
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_WEBINAR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Teacher.TeacherDetailsDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Webinar_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	TeacherDetailsDao tdao;
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping
	(value = "/webinar_url")
	public ModelAndView webinar_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		try {
			
//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("webinar_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			model.put("msg", msg);
//			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//			model.put("getcoursenameList",common.getcoursenameList(sessionFactory) );
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("webinarTiles");
 }
	
	@PostMapping(value = "/Webinar_action")
	public ModelAndView Webinar_action(@Validated @ModelAttribute("Webinar_cmd") EDU_LMS_WEBINAR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("webinar_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		Map<String,String> mObj=new HashMap<>();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String webinar = request.getParameter("webinar");
		String url = request.getParameter("url");
		String webinar_date = request.getParameter("webinar_date");
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			TB_NOTIFICATION noti = new TB_NOTIFICATION();
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_WEBINAR where  upper(webinar)=:webinar and upper(url)=:url and id !=:id")
					.setParameter("webinar", td.getWebinar().toUpperCase())
					.setParameter("url", td.getUrl().toUpperCase())
					.setParameter("webinar_date", td.getWebinar_date().toUpperCase())
					.setParameter("id", id).uniqueResult();
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					
					
					String temp="";
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					noti.setCreated_by(username);
					noti.setCreated_date(date);
					noti.setStatus("0");
					noti.setMessage("WEBINAR ON SOMETHING\n"+td.getUrl().toUpperCase());
					noti.setFrom_name_send(username);
					for(int i=0;i<tdao.getinstitutelistbyStudent(Integer.parseInt(common.getInstIdfromUserid(sessionFactory, userId).get(0))).size();i++) {
						if(i==0) {
							temp+=tdao.getinstitutelistbyStudent(Integer.parseInt(common.getInstIdfromUserid(sessionFactory, userId).get(0))).get(i).get(0);
						}else {
							temp+=","+tdao.getinstitutelistbyStudent(Integer.parseInt(common.getInstIdfromUserid(sessionFactory, userId).get(0))).get(i).get(0);
						}
						
					}
					noti.setTo_name_sent(temp);
				//	noti.setTo_name_receive("-1");
					sessionHQL.save(noti);
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
		return new ModelAndView("redirect:webinar_url");
	}
	
}
