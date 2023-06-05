package com.AyushEdu.controller.Policy_controller_Master;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.Policy_dao_Master.SubcategoryDAO;
import com.AyushEdu.Models.Policy_Model_Master.TB_POLICYCATEGORY_MASTER;
import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class SubCategoryController {
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	SubcategoryDAO  Scdao;
	
//	@Autowired
//	private SessionFactory sessionFactory2;
	
	@Autowired
	CommonController common;
	
	
	
	@GetMapping
	(value = "/subcategory_url")
	public ModelAndView subcategory_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg ) {
		try {
			model.put("msg", msg);
			List<TB_POLICYCATEGORY_MASTER> list=	common.getPolicyCategoryList(sessionFactory);
			model.put("policycat", common.getPolicyCategoryList(sessionFactory));
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			model.put("PoicyCategoryList",common.getPolicyCategoryList(sessionFactory) );
			model.put("SubPoicyCategoryList",common.getSubPolicyCategoryList(sessionFactory) );
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return new ModelAndView("SubcategoryTiles");
	
}
	
	
	@PostMapping(value = "/Categorysub_action")
	public ModelAndView Categorysub_action(@Validated @ModelAttribute("Categorysub_cmd") TB_SUBPOLICYCATEGORY td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		String subcategory = request.getParameter("subcategory");
		String policycategory = request.getParameter("policycategory");
		String status = request.getParameter("status");

		if (subcategory == null || subcategory.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter sub policy category Name :");

			return new ModelAndView("redirect:subcategory_url");

		}
		
		if (policycategory == null || policycategory.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter policy category Name :");

			return new ModelAndView("redirect:subcategory_url");

		}
		


		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status :");

			return new ModelAndView("redirect:subcategory_url");

		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status :");
			return new ModelAndView("redirect:subcategory_url");
		}
		
		
		
		
		
		int id = td.getId() > 0 ? td.getId() : 0;
//		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_SUBPOLICYCATEGORY where upper(status)=:status and upper(subcategory)=:subcategory and upper(policycategory)=:policycategory and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("policycategory", td.getPolicycategory().toUpperCase())
					.setParameter("subcategory", td.getSubcategory().toUpperCase()).setParameter("id", id).uniqueResult();
			if (id == 0) {
				td.setCreated_by(username);
//				td.setCreated_date(date);
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

		return new ModelAndView("redirect:subcategory_url");
	}
	

	
	@PostMapping("/getFiltersubcategory_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String subcategory, String status,String policycategory) {

		return Scdao.DataTablesubcategoryDataList(startPage, pageLength, Search, orderColunm, orderType, subcategory, status,policycategory);

	}

	@PostMapping("/getTotalsubcategory_dataCount")

	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String subcategory,String status,String policycategory) {
		return Scdao.DataTablesubcategoryDataTotalCount(Search, subcategory,status,policycategory);

	}

	//-----edit
	@RequestMapping(value = "/Edit_subcategoryUrl")
	public ModelAndView Edit_subcategoryUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, 
			HttpSession sessionEdit) {

		TB_SUBPOLICYCATEGORY Subcategory_Details = Scdao.getsubpolicyByid(Integer.parseInt(updateid));

		Mmap.addAttribute("msg", msg);
		Mmap.put("policycat", common.getPolicyCategoryList(sessionFactory));
		Mmap.put("Subcategory_Details", Subcategory_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("subpolicyinfo", Scdao.getpolicycategory(Integer.parseInt(updateid)));
		//end
		
		return new ModelAndView("editsubcategoryTiles");
	}
	
	@RequestMapping(value = "/edit_subcategory_Action", method = RequestMethod.POST)
	public ModelAndView edit_subcategory_Action(@ModelAttribute("edit_SubcategoryCMD") TB_SUBPOLICYCATEGORY rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String subcategory = request.getParameter("subcategory").trim();
		String status = request.getParameter("status");
		String policycategory = request.getParameter("policycategory").trim();
	System.err.println("policycategory ===tttttttttttt "+policycategory);
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		try {
			Query<?> q0 = session1.createQuery(
					"select count(id) from TB_SUBPOLICYCATEGORY where subcategory=:subcategory and status=:status and policycategory=:policycategory and id !=:id");
			q0.setParameter("subcategory", subcategory);
			
			q0.setParameter("status", status);
			
			q0.setParameter("policycategory", policycategory);

			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_SUBPOLICYCATEGORY set subcategory=:subcategory,status=:status,policycategory=:policycategory,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("subcategory", subcategory).setParameter("status", status)
						.setParameter("policycategory", policycategory)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg.equals("1")) {
                                  
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		
		return new ModelAndView("redirect:subcategory_url");
	}
	
	@PostMapping(value = "/deletesubcategory_Url")
	public ModelAndView deletesubcategory_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update TB_SUBPOLICYCATEGORY set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

		
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:subcategory_url");
	}
	
}
