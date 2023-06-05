package com.AyushEdu.controller.LMS_Master_Controller;

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
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_SUBCATEGORY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Feedback_Category_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Feedback_SubCategory_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Feedback_Category_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Feedback_SubCategory_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Feedback_SubCategory_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			
	//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Feedback_SubCategory_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			Mmap.put("msg", msg);
			Mmap.put("getfeedbackcat", common.getFeedBackCat(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Feedback_SubCategory_Tiles");
	}

	// ==========================================SAVE/view//Edit
	// Professional==========================================

	@PostMapping(value = "/SubCategoryAction")
	public ModelAndView SubCategoryAction(@Validated @ModelAttribute("SubCategoryCMD") TB_FEEDBACK_SUBCATEGORY_MSTR td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Feedback_SubCategory_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String category = request.getParameter("category");
		String subcategory = request.getParameter("subcategory");
		String feedback_detail = request.getParameter("feedback_detail");
		String status = request.getParameter("status");

		if (category == null || category.equals("0")) {
			ra.addAttribute("msg", "Please Enter Category Name.");
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}

		if (subcategory == null || subcategory.equals("")) {
			ra.addAttribute("msg", "Please Enter Subcategory Name.");
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}

		if (validation.maxlengthcheck100(subcategory) == false) {
			ra.addAttribute("msg", "Feedback Detail " + validation.MaxlengthcheckMSG100);

			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");

		}
		if (validation.isOnlyAlphabetDASH(subcategory) == false) {
			ra.addAttribute("msg","SubCategory "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}
		if (category == null || feedback_detail.equals("")) {
			ra.addAttribute("msg", "Please Enter Feedback Detail.");
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}
		if (validation.maxlengthcheck100(feedback_detail) == false) {
			ra.addAttribute("msg", "Feedback Detail " + validation.MaxlengthcheckMSG100);

			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");

		}
		if (validation.isOnlyAlphabetDASH(feedback_detail) == false) {
			ra.addAttribute("msg","Feedback Detail "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.out.println(userid);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_FEEDBACK_SUBCATEGORY_MSTR where category=:category and upper(subcategory)=:subcategory and feedback_detail=:feedback_detail and status=:status and id !=:id")
					.setParameter("category", td.getCategory())
					.setParameter("subcategory", td.getSubcategory().toUpperCase())
					.setParameter("feedback_detail", td.getFeedback_detail().toUpperCase())
					.setParameter("status", td.getStatus()).setParameter("id", id).uniqueResult();

			if (id == 0) {
				td.setCategory(Integer.parseInt(category));
				td.setSubcategory(subcategory);
				td.setCreated_by(userid);
//					td.setCreated_role(role);
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
				td.setCategory(Integer.parseInt(category));
				td.setSubcategory(subcategory);
				td.setFeedback_detail(feedback_detail);
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Pdao.updateSubCategory(td);

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

		return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
	}

	@PostMapping("/getFilterFeedBack_SubCategory_data")

	public @ResponseBody List<Map<String, Object>> getFilterFeedBack_SubCategory_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String category, String subcategory,
			String feedback_detail, String status) {

		return Pdao.DataTableSubCategoryDataList(startPage, pageLength, Search, orderColunm, orderType, category,
				subcategory, feedback_detail, status);

	}

	@PostMapping("/getTotalFeedback_SubCategory_dataCount")
	public @ResponseBody long getTotalFeedback_SubCategory_dataCount(HttpSession sessionUserId, String Search,
			String category, String subcategory, String feedback_detail, String status) {
		return Pdao.DataTableSubCategoryDataTotalCount(Search, category, subcategory, feedback_detail, status);

	}

	// -----edit

	@RequestMapping(value = "/Edit_Feedback_SubCategory_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Feedback_SubCategory_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "subcategory", required = false) String subcategory,
			@RequestParam(value = "feedback_detail", required = false) String feedback_detail,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_FEEDBACK_CATEGORY_MSTR Category_Details = Pdao.getCategoryByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("category", category);
		Mmap.put("subcategory", subcategory);
		Mmap.put("feedback_detail", feedback_detail);
		Mmap.put("status", status);
		Mmap.put("Category_Details", Category_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("Categoryinfo", Pdao.getCategoryByid(Integer.parseInt(updateid)));

		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Feedback_Category_Tiles");
	}

	@RequestMapping(value = "/Feedback_SubCategory_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Feedback_SubCategory_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update TB_FEEDBACK_SUBCATEGORY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:Feedback_SubCategory_Mstr_Url");
	}

}
