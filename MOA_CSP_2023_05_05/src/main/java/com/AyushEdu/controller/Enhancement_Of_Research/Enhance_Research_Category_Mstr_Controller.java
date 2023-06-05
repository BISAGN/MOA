package com.AyushEdu.controller.Enhancement_Of_Research;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_CATEGORY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Enhancement_Of_Research.Enhance_Research_Category_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Enhance_Research_Category_Mstr_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ValidationController validation;
	@Autowired
	private Enhance_Research_Category_Mstr_DAO ARC;
	
	@RequestMapping(value = "/Enhance_Research_Category_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Enhance_Research_Category_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Enhance_Research_Category_Mstr_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		 String role = session.getAttribute("role").toString();
		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));	
		
		return new ModelAndView("Enhance_Research_Category_MstrTiles");
	}
	
	@PostMapping(value = "/Enhance_Research_Category_MstrAction")
	public ModelAndView Enhance_Research_Category_MstrAction(
			@Validated @ModelAttribute("Enhance_Research_Category_MstrCMD") TB_ENHANCE_RESEARCH_CATEGORY_MSTR CD,
			 BindingResult result, HttpServletRequest request,MultipartHttpServletRequest mul,
			ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra) throws IOException, ParseException {
		
//		  if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		  }
//		  String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Enhance_Research_Category_Mstr_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		  }
			
	     Date date = new Date();
	     String username = principal.getName();
	
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		String category_name = request.getParameter("category_name");
		String status = request.getParameter("status");
								
		if (category_name.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Category Name");
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
		}
		if (validation.maxlengthcheck50(category_name) == false) {
			ra.addAttribute("msg","Category Name "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
		}
		
			int id = CD.getId() > 0 ? CD.getId() : 0;
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  TB_ENHANCE_RESEARCH_CATEGORY_MSTR where upper(category_name)=:category_name and status=:status and id !=:id")
						.setParameter("category_name", CD.getCategory_name())
						.setParameter("status", CD.getStatus())
						.setParameter("id", id).uniqueResult();
				
//				int idd =0;
				if (id == 0) {
					CD.setCreated_by(username);
					CD.setCreated_date(date);
					if (c == 0) {
						 id = (int)sessionHQL.save(CD);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");

					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
				tx.commit();
				//For Core Directory
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
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
}
	
	
	@PostMapping("/getFilterSearch_Enhance_Research_Category_Mstr_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterSearch_Enhance_Research_Category_Mstr_data(HttpSession session, int startPage,
			             int pageLength,String Search, String orderColunm, String orderType, String category_name, String status) {
		
		return (ArrayList<Map<String, Object>>) ARC.Enhance_Research_Category_Mstr_DataList(startPage, pageLength, Search,
				orderColunm, orderType, category_name, status);
	}

	@PostMapping("/getTotalSearch_Enhance_Research_Category_Mstr_Count")
	public @ResponseBody long getTotalSearch_Enhance_Research_Category_Mstr_Count(HttpSession sessionUserId, String Search,String category_name, String status) {

		return ARC.DataTotalEnhance_Research_Category_MstrCount(Search, category_name, status);

	}	
	
	@RequestMapping(value = "/delete_Enhance_Research_Category_MstrUrl", method = RequestMethod.POST)
	public ModelAndView delete_Enhance_Research_Category_MstrUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

//		if(request.getHeader("Referer") == null ) { 
//	//		 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update TB_ENHANCE_RESEARCH_CATEGORY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			
			tx.commit();
//			gen_dirdao.Delete_Gender_Master_Data(id);  
			session.close();
			if (app > 0) {
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
		return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
	}
	
	

	@RequestMapping(value = "/Edit_Enhance_Research_Category_MstrUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Enhance_Research_Category_MstrUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {
//
//		if(request.getHeader("Referer") == null ) { 
//	//		 sessionEdit.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = sessionEdit.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String role = sessionEdit.getAttribute("role").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_ENHANCE_RESEARCH_CATEGORY_MSTR Enhance_Research_Mstr = ARC.getEnhance_Research_Category_MstrByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
		Mmap.put("Enhance_Research_Mstr", Enhance_Research_Mstr);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("Enhance_Research_Mstr", ARC.getEnhance_Research_Category_MstrByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Edit_Enhance_Research_Category_Mstr_Tiles");
	}
	
	
	//edit action
		@RequestMapping(value = "/EditEnhance_Research_Category_MstrAction", method = RequestMethod.POST)
		public ModelAndView EditEnhance_Research_Category_MstrAction(@ModelAttribute("EditEnhance_Research_Category_MstrCMD") TB_ENHANCE_RESEARCH_CATEGORY_MSTR rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

//			if(request.getHeader("Referer") == null ) { 
//			//	 session.invalidate();
//				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			String username = session.getAttribute("username").toString();
			String category_name = request.getParameter("category_name");
			String status = request.getParameter("status");
			int id = Integer.parseInt(request.getParameter("id"));
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			
									
			if (category_name.trim().equals("") ) {
				ra.addAttribute("msg", "Please Enter Category Name");
				return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
			}
			if (validation.maxlengthcheck50(category_name) == false) {
				ra.addAttribute("msg","Category Name "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
			}
			
			try {
				Query q0 = session1.createQuery("select count(id) from  TB_ENHANCE_RESEARCH_CATEGORY_MSTR where upper(category_name)=:category_name and status=:status and id !=:id ");
				q0.setParameter("category_name", category_name);
				q0.setParameter("status", Integer.parseInt(status));
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update TB_ENHANCE_RESEARCH_CATEGORY_MSTR set category_name=:category_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
							+ " where id=:id";

					Query query = session1.createQuery(hql)
							.setParameter("category_name", category_name)
							.setParameter("status", Integer.parseInt(status))
							.setParameter("modified_by", username).setParameter("modified_date", new Date())
							.setParameter("id", id);
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();
					//For Core Directory
//					gen_dirdao.Update_Gender_Master_Data( id,gender_name,status,new Date(),username);
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
			
			return new ModelAndView("redirect:Enhance_Research_Category_Mstr_Url");
		}
		

}
