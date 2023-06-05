package com.AyushEdu.controller.LMS_Master_Controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.LMS_Master.Document_mst;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.DocumentDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Doc_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DocumentDAO doc;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/DocUrl")
	public ModelAndView DocUrl(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("DocUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			model.put("msg", msg);
			model.put("list", doc.search_Documentmaster(""));
		} catch (Exception e) {

		}
		return new ModelAndView("DocTiles", "Doc_cmd", new Document_mst());

	}

	@RequestMapping(value = "/Doc_Action", method = RequestMethod.POST)
	public ModelAndView Doc_Action(@Valid @ModelAttribute("Doc_cmd") Document_mst dt, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DocUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		try {
			int id = dt.getId() > 0 ? dt.getId() : 0;
			String username = session.getAttribute("username").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();

			if (dt.getDoc_name().trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Document Name ");
				return new ModelAndView("redirect:DocUrl");
			}
			
			if (validation.isOnlyAlphabet(request.getParameter("doc_name")) == false) {
				ra.addAttribute("msg","Document Name " + validation.isOnlyAlphabetMSG );
				return new ModelAndView("redirect:DocUrl");
			}
			
			if (validation.maxlengthcheck(request.getParameter("doc_name")) == false) {
				ra.addAttribute("msg","Document Name" + validation.MaxlengthcheckMSG );
				return new ModelAndView("redirect:DocUrl");
			}
			

			if (dt.getDoc_type() == null) {

				ra.addAttribute("msg", "Please Select Document Type ");
				return new ModelAndView("redirect:DocUrl");
			}

			try {

				Query q0 = sessionHQL
						.createQuery("select count(id) from Document_mst where upper(doc_name)=:doc_name and id!=:id");
				q0.setParameter("doc_name", dt.getDoc_name().toUpperCase());
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					dt.setCreated_by(username);
					dt.setCreated_dt(date);
					dt.setDoc_type(request.getParameter("doc_type_hid"));
					if (c == 0) {
						sessionHQL.save(dt);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");

					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					dt.setModified_by(username);
					dt.setModified_dt(date);
					if (c == 0) {
						String msg = doc.updatedoc(dt);
						ra.addAttribute("msg", msg);
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
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

		} catch (Exception e) {

		}

		return new ModelAndView("redirect:DocUrl");
	}

//========================Delete========================

	@RequestMapping(value = "/delete_Document", method = RequestMethod.POST)
	public ModelAndView delete_Document(@ModelAttribute("doc_name11") int did, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DocUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query qry = session.createQuery("delete from Document_mst where id=:id");
			int app = qry.setInteger("id", did).executeUpdate();
			session.getTransaction().commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:DocUrl");
	}

}
