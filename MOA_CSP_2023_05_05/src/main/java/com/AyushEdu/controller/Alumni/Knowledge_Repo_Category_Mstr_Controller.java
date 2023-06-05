package com.AyushEdu.controller.Alumni;

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
import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Knowledge_Repo_Category_Mstr_DAO;
//import com.AyushEdu.dao.Practice.Department_Mstr_DAO;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Knowledge_Repo_Category_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Knowledge_Repo_Category_Mstr_DAO Kdao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Knowledge_Repo_Category_Url", method = RequestMethod.GET)
	public ModelAndView Department_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Category_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Knowledge_Repo_Category_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	


	@PostMapping(value = "/RepoCategoryAction")
	public ModelAndView RepoCategoryAction(@Validated @ModelAttribute("RepoCategoryCMD") TB_KNOWLEDGE_REPO_CATEGORY_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Category_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String category_repo = request.getParameter("category_repo");
		String status = request.getParameter("status");

		if (category_repo == null || category_repo.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Category Repository");
			return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
		}
		
		if (validation.maxlengthcheck100(category_repo) == false) {
			ra.addAttribute("msg","Category Repository "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
			
		}
		
		if (validation.isOnlyAlphabetDASH(category_repo) == false) {
			ra.addAttribute("msg","Category Repository "+ validation.isOnlyAlphabetMSGDASH);
			
			return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
		}
		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_KNOWLEDGE_REPO_CATEGORY_MSTR where upper(category_repo)=:category_repo and status=:status and id !=:id")
					.setParameter("category_repo", td.getCategory_repo().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
//				td.setDepartment(department);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} 
			else {
				td.setCategory_repo(category_repo);
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Kdao.updateRepoCategory(td);
//					if (msg == "Data Updated Successfully") {
//						model.put("msg", msg);
//						model.put("Nmsg", "0");
//					} else {
//						model.put("msg", msg);
//						model.put("Nmsg", "1");
//					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
//					model.put("msg", "Data already Exist");
//					model.put("Nmsg", "1");
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
		
		return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
	}
	
	
	@PostMapping("/getFilterRepoCategory_data")

	public @ResponseBody List<Map<String, Object>> getFilterRepoCategory_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String category_repo, String status) {
		return Kdao.DataTableRepoCategoryDataList(startPage, pageLength, Search, orderColunm, orderType, category_repo,status);

	}

	@PostMapping("/getTotalRepoCategory_dataCount")
	public @ResponseBody long getTotalRepoCategory_dataCount(HttpSession sessionUserId, String Search, String category_repo, String status) {
		return Kdao.DataTableRepoCategoryDataTotalCount(Search, category_repo,status);
		
	}
	
	@RequestMapping(value = "/Edit_RepoCategory_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_RepoCategory_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "category_repo", required = false) String category_repo,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		//SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Category_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_KNOWLEDGE_REPO_CATEGORY_MSTR Repo_Category_Details = Kdao.getRepoCategoryByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("category_repo", category_repo);
		Mmap.put("status", status);
		Mmap.put("Repo_Category_Details", Repo_Category_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("RepoCategoryinfo", Kdao.getRepoCategoryByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Knowledge_Repo_Category_Tiles");
	}
	
	@RequestMapping(value = "/RepoCategory_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView RepoCategory_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		//SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Category_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String userid = session1.getAttribute("userId_for_jnlp").toString();
		try {
  
			int app = session.createQuery(
					"update TB_KNOWLEDGE_REPO_CATEGORY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", userid)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
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
		return new ModelAndView("redirect:Knowledge_Repo_Category_Url");
	}
	
}