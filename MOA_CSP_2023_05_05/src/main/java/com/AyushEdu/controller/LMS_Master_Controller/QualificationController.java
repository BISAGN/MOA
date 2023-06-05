package com.AyushEdu.controller.LMS_Master_Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Models.LMS_Master.recr_qualification_mst;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.QualificationDAO;



@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class QualificationController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private QualificationDAO addDAO1;
	@Autowired
	private RoleBaseMenuDAO roledao;
	// ------------------------------- Qualification For page Open -------------------------------------
	@RequestMapping(value = "/QualificationUrl")
	public ModelAndView QualificationUrl(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		

		try {
			
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("QualificationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		//Boolean val = roledao.ScreenRedirect("QualificationUrl", session.getAttribute("roleid").toString());
		Mmap.put("msg", msg);
		ArrayList<ArrayList<String>> list = addDAO1.getReportQualification();
		Mmap.put("list", list);
		//Mmap.put("list.size()", list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("QualificationTiles", "Mqualification_cmd", new recr_qualification_mst());
	}


	// ------------------------------- Qualification save -------------------------------------
	@RequestMapping(value = "/Mqualification_Action")
	public ModelAndView Mqualification_Action(
			@Valid @Validated @ModelAttribute("Mqualification_cmd") recr_qualification_mst rs, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session)throws ParseException  {
		
		if(request.getHeader("Referer") == null ) { 
	//		 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("QualificationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		if (rs.getQualification().equals("")) {
			model.put("msg", "Please Enter Qualificatuion.");
			return new ModelAndView("redirect:QualificationUrl");
		}
		
		int id = rs.getQualification_id() > 0 ? rs.getQualification_id() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from recr_qualification_mst where  qualification=:qualification  and id!=:id ");

			q0.setParameter("qualification", rs.getQualification());
			
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				rs.setStatus("A");
				rs.setQualification_createdby(username);
				rs.setQualification_createddate(date);
				if (c == 0) {

					sessionHQL.save(rs);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			}

			else {

				rs.setQualification_updatedby(username);
				rs.setQualification_updateddate(date);
				if (c == 0) {
					String msg = addDAO1.updateQualification(rs);
					model.put("msg", msg);
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:QualificationUrl");
		
		
			
		
	
		
	}


	
//	// -------------------------------for Delete Qualification-------------------------------------
	@RequestMapping(value = "/deleteQualificationUrlAdd", method = RequestMethod.POST)
	public ModelAndView deleteQualificationUrlAdd(int deleteid, HttpSession session, HttpServletRequest request, ModelMap model) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("QualificationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(addDAO1.deleteQualificationUrlAdd(deleteid, username));
		model.put("msg", list2);
		return new ModelAndView("redirect:QualificationUrl");
	}
	

}
