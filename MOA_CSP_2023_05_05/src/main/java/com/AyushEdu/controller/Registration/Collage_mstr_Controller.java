package com.AyushEdu.controller.Registration;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.Registration.TB_COLLAGE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.CollageMstrDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Collage_mstr_Controller {
	
	@Autowired
	CollageMstrDao  Scdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@GetMapping(value = "Collage_MstrUrl")
	public ModelAndView Collage_MstrUrl(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Collage_MstrUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		model.addAttribute("msg", msg);
		return new ModelAndView("Collage_MstrTiles");
	}
	
	@PostMapping(value = "collageMstr_action")
	public ModelAndView collageMstr_action(@Validated @ModelAttribute("collageMstr_cmd") TB_COLLAGE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Collage_MstrUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String collage_name = request.getParameter("collage_name");
		String collage_code = request.getParameter("collage_code");
		String status = request.getParameter("status");

//		if (collage_name == null || collage_name.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter sub policy category Name :");
//
//			return new ModelAndView("redirect:subcategory_url");
//
//		}
//		
//		if (collage_code == null || collage_code.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter policy category Name :");
//
//			return new ModelAndView("redirect:subcategory_url");
//
//		}
//		if (status == null || status.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Status :");
//
//			return new ModelAndView("redirect:subcategory_url");
//
//		}
//
//		if (status == "2" || status.trim().equals("2")) {
//			ra.addAttribute("msg", "Only Select Active Status :");
//			return new ModelAndView("redirect:subcategory_url");
//		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		int id1 = Integer.parseInt(request.getParameter("id_org"));

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_COLLAGE_MSTR where upper(status)=:status and upper(collage_name)=:collage_name and collage_code=:collage_code and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("collage_name", td.getCollage_name().toUpperCase())
					.setParameter("collage_code", td.getCollage_code())
					.setParameter("id", id).uniqueResult();
			if (id1 == 0) {
				td.setCreated_by(username);
				
				td.setCollage_name(collage_name);
				td.setCollage_code(collage_code);
				td.setStatus(status);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					ra.addAttribute("msg", "Data Saved Successfully.");

				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}else {
				
				td.setModified_by(username);
				td.setModified_date(date);
			
				td.setId(id1);
				String msg = Scdao.updateCollagedata(td);
				if (msg == "Data Updated Successfully") {
					ra.addAttribute("msg", msg);
				//	model.put("msg", msg);
				} else {
					model.put("msg", msg);
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

		return new ModelAndView("redirect:Collage_MstrUrl");
	}
	
////SEARCH////////////////
	
	@PostMapping("/getFiltercollage_data")

	public @ResponseBody ArrayList<ArrayList<String>> DataTablecollageDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String collage_name,String collage_code,String status) {

		return Scdao.DataTablecollageDataList(startPage, pageLength, Search, orderColunm, orderType,collage_name, collage_code,status);

	}

	@PostMapping("/getTotalcollage_dataCount")

	public @ResponseBody long getTotalcollageMstr_dataCount(HttpSession sessionUserId, String Search, String collage_name,String collage_code,String status) {

		return Scdao.DataTablecollageDataTotalCount(Search, collage_name, collage_code,status);

	}
	
	////////////////////////////delete
	
	@PostMapping(value = "/delete_collage")
	public @ResponseBody ModelAndView delete_document(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Collage_MstrUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();
		
		String username = session.getAttribute("username").toString();
		
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "delete from TB_COLLAGE_MSTR where id=:id";
			 
		@SuppressWarnings({ "rawtypes", "deprecation" })
			int app = sessionHQL.createQuery(hqlUpdate)
			.setParameter("id", id).executeUpdate();
//			.setString("modified_by", username)
//			.setDate("modified_date", new Date()).
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete Unsuccessfull");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Collage_MstrUrl");
	}

}
