package com.AyushEdu.controller.LMS_Master_Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.CommondaoImpl;
import com.AyushEdu.dao.LMS_Master.NationalityDAO;
import com.AyushEdu.validation.ValidationController;



@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Nationality_mst_controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CommondaoImpl comdao;

	@Autowired
	private NationalityDAO natDAO;

	@Autowired
	private CommonController edu_com = new CommonController();

@Autowired
	private RoleBaseMenuDAO roledao;
@Autowired
private ValidationController validation2 = new ValidationController();
	// ------------------------------- Nationality For page Open-------------------------------------
	@RequestMapping(value = "/NationalityUrl", method = RequestMethod.GET)
	public ModelAndView NationalityUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NationalityUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
		//	Boolean val = roledao.ScreenRedirect("NationalityUrl", session.getAttribute("roleid").toString());

			Mmap.put("msg", msg);
			List<Map<String, Object>> list = getReportNationality();
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		} catch (Exception e) {

		}
		return new ModelAndView("NationalityTiles", "MNationality_cmd", new recr_nationality_mst());
	}

	// ------------------------------- NationalityReport Query -------------------------------------
	public List<Map<String, Object>> getReportNationality() {
		String qry = "select distinct Nationality_id as id,nationality from recr_nationality_mst  where status='A'";
		List<Map<String, Object>> list = comdao.getAllReportListJdbc(qry, "");
		return list;
	}

	// -------------------------------Nationality save -------------------------------------
	@RequestMapping(value = "/MNationality_Action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView MNationality_Action(
			@Valid @Validated @ModelAttribute("MNationality_cmd") recr_nationality_mst rs, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		List<Map<String, Object>> list = getReportNationality();
		
		
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NationalityUrl", session.getAttribute("roleid").toString());		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		if (rs.getNationality().equals("")) {
			model.put("msg", "Please Enter Nationality.");
			return new ModelAndView("redirect:NationalityUrl");
		}
		if (validation2.isOnlyAlphabet(rs.getNationality()) == false) {
			model.put("msg","Nationality" + validation2.isOnlyAlphabetMSG );
			return new ModelAndView("redirect:NationalityUrl");
		}
		
		if (validation2.maxlengthcheck50(rs.getNationality()) == false) {
			model.put("msg","Nationality "+ validation2.MaxlengthcheckMSG50 );
			
			return new ModelAndView("redirect:NationalityUrl");
			
		}
		
		if (result.hasErrors()) {
			model.put("list", list);
			model.put("list.size()", list.size());
			return new ModelAndView("NationalityTiles");
		} else {
			String username = session.getAttribute("username").toString();
			rs.setNationality_createdby(username);
			Date entry_dt = new Date();
			rs.setNationality_createddate(entry_dt);
			rs.setStatus("A");
			Boolean e = checkExistingNationality(rs.getNationality());
			if (e.equals(false) || e.equals(null)) {
				edu_com.saveFunction(rs);
				model.put("msg", "Data Saved Successfully");
				return new ModelAndView("redirect:NationalityUrl");
			} else {
				model.put("list", list);
				model.put("list.size()", list.size());
				model.put("msg", "Data Already Exists!");
				return new ModelAndView("NationalityTiles");
			}
		}
	}

	// ------------------------------- if existing for Nationality-------------------------------------
	@SuppressWarnings("unchecked")
	public Boolean checkExistingNationality(String nationality) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<recr_nationality_mst> users = null;
		try {
			Query q = session
					.createQuery("FROM recr_nationality_mst where Lower(nationality)=:nationality  and status='A' ");
			q.setParameter("nationality", nationality.toLowerCase());
			users = (List<recr_nationality_mst>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	// -------------------------------for update Nationality -------------------------------------
	@RequestMapping(value = "/updateNationalityUrl", method = RequestMethod.POST)
	public ModelAndView updateNationalityUrl(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//Boolean val = roledao.ScreenRedirect("NationalityUrl", session.getAttribute("roleid").toString());

		try {
			model.put("Edit_Nationality_cmd", natDAO.getNationalityByid(updateid));
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			model.put("msg", msg);
		} catch (Exception e) {

		}
		return new ModelAndView("EditNationalityTiles");
	}

	// -------------------------------for Edit Nationality---------------------------------------
	@RequestMapping(value = "/edit_Nationality_Action", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView edit_Nationality_Action(
			@Valid @ModelAttribute("Edit_Nationality_cmd") recr_nationality_mst updateid, BindingResult result,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session) {
		//Boolean val = roledao.ScreenRedirect("NationalityUrl", session.getAttribute("roleid").toString());
		
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NationalityUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		if (request.getParameter("nationality").equals("")) {
			model.put("msg", "Please Enter Nationality.");
			return new ModelAndView("EditNationalityTiles");
		}

		
		

		if (validation2.isOnlyAlphabet(request.getParameter("nationality")) == false) {
			model.put("msg","Nationality" + validation2.isOnlyAlphabetMSG );
			return new ModelAndView("EditNationalityTiles");
		}
		
		if (validation2.maxlengthcheck50(request.getParameter("nationality")) == false) {
			model.put("msg","Nationality "+ validation2.MaxlengthcheckMSG50 );
			
			return new ModelAndView("EditNationalityTiles");
			
		}
		
		if (result.hasErrors()) {
			return new ModelAndView("EditNationalityTiles");
		} else {
			Boolean e = checkExistingEditNationality(updateid.getNationality(), updateid.getNationality_id());
			if (e.equals(false) || e.equals(null)) {
				String username = session.getAttribute("username").toString();
				model.put("msg", natDAO.UpdateNationalityValue(updateid, username));
				return new ModelAndView("redirect:NationalityUrl");
			} else {
				model.put("msg", "Data Already Exists!");
				return new ModelAndView("EditNationalityTiles");
			}
		}
	}

	// ------------------------------- if existing for Edit Nationality-------------------------------------
	@SuppressWarnings("unchecked")
	public Boolean checkExistingEditNationality(String nationality, int nationality_id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<recr_nationality_mst> list = null;
		try {
			Query q = session.createQuery(
					"select distinct nationality from recr_nationality_mst where Lower(nationality)=:nationality and nationality_id !=:nationality_id  and status='A' order by nationality ");
			q.setParameter("nationality", nationality.toLowerCase());
			q.setParameter("nationality_id", nationality_id);
			list = (List<recr_nationality_mst>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	// -------------------------------for Delete Nationality-------------------------------------
	@RequestMapping(value = "/deleteNationalityUrlAdd", method = RequestMethod.POST)
	public ModelAndView deleteNationalityUrlAdd(int deleteid, HttpSession session, ModelMap model) {
		Boolean val = roledao.ScreenRedirect("NationalityUrl", session.getAttribute("roleid").toString());

		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(natDAO.deleteNationalityUrlAdd(deleteid, username));
		model.put("msg", list2);
		return new ModelAndView("redirect:NationalityUrl");
	}

}
