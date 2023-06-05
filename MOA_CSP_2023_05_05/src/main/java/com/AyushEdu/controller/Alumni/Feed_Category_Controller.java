package com.AyushEdu.controller.Alumni;


import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Feed_Category_Dao;

//import com.AyushEdu.controller.LMS_Master_Controller.EDU_ALUM_ALUMNI_FEED_CATEGORY;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Feed_Category_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Feed_Category_Dao fcdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/Feed_Category_Url", method = RequestMethod.GET)
	public ModelAndView Feed_Category_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		try {
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			 
			 Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}

			return new ModelAndView("Feed_Category_Tiles");
		}

		
	//==========================================SAVE/view//Edit Professional========================================== 	

	
	@PostMapping(value = "/feed_categoryAction")
	public ModelAndView feed_categoryAction(@Validated @ModelAttribute("feed_categoryCMD") EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}		
		
		String feed_category = request.getParameter("feed_category");
		String status = request.getParameter("status");

		if (feed_category == null || feed_category.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Feed Category");
			return new ModelAndView("redirect:Feed_Category_Url");
		}
		
		if (validation.maxlengthcheck100(feed_category) == false) {
			ra.addAttribute("msg","Feed Category "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Feed_Category_Url");
		}
		if (validation.isOnlyAlphabetDASH(feed_category) == false) {
			ra.addAttribute("msg","Feed Category "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Feed_Category_Url");
		}
		

		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Feed_Category_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR where upper(feed_category)=:feed_category  and status =:status and id !=:id")
					.setParameter("feed_category", td.getFeed_category().toUpperCase())
					.setParameter("status",Integer.parseInt( status))
					.setParameter("id", id).uniqueResult();
			
		
				if (id == 0) {
					td.setFeed_category(feed_category);
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
					td.setFeed_category(feed_category);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = fcdao.updatecategory(td);
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
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
			
			return new ModelAndView("redirect:Feed_Category_Url");
		}
		
		
	
	@PostMapping("/getFilterfeed_category_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String feed_category, String status) {
		return fcdao.DataTableFeedCategoryDataList(startPage, pageLength, Search, orderColunm, orderType, feed_category,status);

	}

	@PostMapping("/getTotalfeed_category_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String feed_category) {
		return fcdao.DataTableFeedCategoryDataTotalCount(Search, feed_category);
		
	}
	
		@RequestMapping(value = "/Edit_feed_category_Url", method = RequestMethod.POST)
		public ModelAndView Edit_feed_category_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "feed_category", required = false) String feed_category,
				@RequestParam(value = "status", required = false) String status,
				HttpServletRequest request, HttpSession sessionEdit) {
			
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

//			if(request.getHeader("Referer") == null ) { 
//				sessionEdit.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR feedcategory_Details = fcdao.getfeedcategoryByid(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);	
			Mmap.put("feed_category", feed_category);
			Mmap.put("status", status);
			Mmap.put("feedcategoryinfo", fcdao.getfeedcategoryByid(Integer.parseInt(updateid)));
			Mmap.addAttribute("feedcategory_Details", feedcategory_Details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			tx.commit();
			sessionHQL.close();

			return new ModelAndView("Feed_Category_Tiles");
		}
		
		//edit action
				@RequestMapping(value = "/edit_feedcategory_Action", method = RequestMethod.POST)
				public ModelAndView edit_feedcategory_Action(@ModelAttribute("edit_feedcategoryCMD") EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

					//SECURITY -- RIDDHI
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					String feed_category = request.getParameter("feed_category").trim();
					String status = request.getParameter("status");
					
					
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					
					if (feed_category == null || feed_category.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Feed Category");
						return new ModelAndView("redirect:Feed_Category_Url");
					}
					if (validation.maxlengthcheck100(feed_category) == false) {
						ra.addAttribute("msg"," Feed Category "+ validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Feed_Category_Url");
					}
					if (validation.isOnlyAlphabetDASH(feed_category) == false) {
						ra.addAttribute("msg"," Feed Category "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Feed_Category_Url");
					}
					
					
					
					if (status == null || status.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Status.");
						return new ModelAndView("redirect:Feed_Category_Url");
					}
					if (status == "2" || status.trim().equals("2")) {
						ra.addAttribute("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Feed_Category_Url");
					}
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR where feed_category=:feed_category  and status=:status and id !=:id");
						q0.setParameter("feed_category", feed_category);
				
						q0.setParameter("status", Integer.parseInt(status));

						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c == 0) {
							String hql = "update EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR set feed_category=:feed_category,status=:status,modified_by=:modified_by,modified_date=:modified_date"
									+ " where id=:id";

							Query query = session1.createQuery(hql).setParameter("feed_category", feed_category)
									.setParameter("status", Integer.parseInt(status))
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
					
					return new ModelAndView("redirect:Feed_Category_Url");
				}
		
				// -------------------------SEARCH Battalion  -------------------------------------//
				 
				 @RequestMapping(value = "/admin/getSearch_feed_category", method = RequestMethod.POST)
					public ModelAndView getSearch_feed_category(ModelMap Mmap,HttpSession session,HttpServletRequest request,
							@RequestParam(value = "msg", required = false) String msg,
							@RequestParam(value = "feed_category", required = false) String feed_category ,String feed_category1,@ModelAttribute("status1") String status)  {
					
					//SECURITY -- RIDDHI
					 if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					 
				
							Mmap.put("feed_category", feed_category);
							Mmap.put("status", status);
							Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
							

						   return new ModelAndView("Feed_Category_Tiles","feed_categoryCMD",new EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR());
						   
					}
		//=============================================DELETE
		
				 @RequestMapping(value = "/delete_FC_Url", method = RequestMethod.POST)
					public ModelAndView delete_FC_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
							HttpServletRequest request, ModelMap model, HttpSession session1) {
					 
					//SECURITY -- RIDDHI
					 if(request.getHeader("Referer") == null ) { 
//						 session1.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Feed_Category_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
						List<String> liststr = new ArrayList<String>();

						Session session = this.sessionFactory.openSession();
						Transaction tx = session.beginTransaction();

						String username = session1.getAttribute("username").toString();
						try {
				  
							int app = session.createQuery(
									"update EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
									.setParameter("id", id).setParameter("modified_by", username)
									.setParameter("modified_date", new Date()).setParameter("status",2).executeUpdate();

							
							tx.commit();
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
						return new ModelAndView("redirect:Feed_Category_Url");
					}
	
}			