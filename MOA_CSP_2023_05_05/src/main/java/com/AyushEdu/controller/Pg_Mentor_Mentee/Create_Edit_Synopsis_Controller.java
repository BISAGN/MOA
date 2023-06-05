package com.AyushEdu.controller.Pg_Mentor_Mentee;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

import com.AyushEdu.Models.Pg_Mentor_Mentee.PG_CREATE_EDIT_SYNOPSIS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Pg_Mentor_Mentee.Create_Edit_Synopsis_Dao;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Create_Edit_Synopsis_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Create_Edit_Synopsis_Dao CDdao;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/CreateEditSynopsisUrl", method = RequestMethod.GET)
	public ModelAndView CreateEditSynopsisUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			
			
	//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("CreateEditSynopsisUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			Mmap.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Create_Edit_Synopsis_Tiles");
	}
	
	@PostMapping(value = "/synopsisAction")
	public ModelAndView synopsisAction(@Validated @ModelAttribute("synopsisCMD") PG_CREATE_EDIT_SYNOPSIS td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CreateEditSynopsisUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String title = request.getParameter("title");
		String synopsis = request.getParameter("synopsis");
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		synopsis = synopsis.replaceAll("'","|");

		if (title == null || title.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Title");
			return new ModelAndView("redirect:CreateEditSynopsisUrl");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String msg="";

		try {
				
				td.setTitle(title);
				td.setSynopsis(synopsis);
				td.setUserid(Integer.parseInt(userid));
				
				if (id == 0) {
					td.setCreated_by(username);
					td.setCreated_date(date);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Saved Succefully";
				}else {
					td.setId(id);
					td.setModified_by(username);
					td.setModified_date(date);
					msg = CDdao.updateSynopsis(td);
				}
				
				ra.addAttribute("msg", msg);

			tx.commit();

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
		
		return new ModelAndView("redirect:CreateEditSynopsisUrl");
	}
	
	@PostMapping("/getFilterSynopsis_data")
	public @ResponseBody List<Map<String, Object>> getFilterSynopsis_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String title, HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return CDdao.DataTableSynopsisDataList(startPage, pageLength, Search, orderColunm, orderType,title,userid);
	}

	@PostMapping("/getTotalSynopsis_dataCount")
	public @ResponseBody long getTotalSynopsis_dataCount(HttpSession session, String Search, String title) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return CDdao.DataTableSynopsisDataTotalCount(Search, title,userid);
		
	}
	
	@RequestMapping(value = "/SynopsisDelete_Url", method = RequestMethod.POST)
	public ModelAndView SynopsisDelete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {

			int app = session.createQuery(
					"delete from PG_CREATE_EDIT_SYNOPSIS where id=:id")
					.setParameter("id", id).executeUpdate();
			
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
		return new ModelAndView("redirect:CreateEditSynopsisUrl");
	}

}
