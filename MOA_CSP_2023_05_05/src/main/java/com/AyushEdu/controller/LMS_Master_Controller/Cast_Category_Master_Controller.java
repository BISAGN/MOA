package com.AyushEdu.controller.LMS_Master_Controller;
import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Masters.TB_CASTE_CATEGORY_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Cast_Category_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Cast_Category_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Cast_Category_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Cast_Category_MasterUrl==========================================//

			@RequestMapping(value = "/Cast_Category_MasterUrl", method = RequestMethod.GET)
				
				 	public ModelAndView Cast_Category_MasterUrl(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				
				
				//SECURITY RAHUL
				
				if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
					
					  Mmap.put("msg", msg);
					  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					return new ModelAndView("Cast_Category_MasterTiles");

				}

			//==========================================SAVE Cast_Category_MasterUrl ========================================== 	

			
			@PostMapping(value = "/Cast_Category_masterUrlaction")
			public ModelAndView Cast_Category_masterUrlaction(@Validated @ModelAttribute("hms3") TB_CASTE_CATEGORY_MASTER td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra,  String username  )  {
				
				
				System.err.println("hellllllllloooooooooooo");
				String Cast_Category = request.getParameter("Cast_Category");
				String status = request.getParameter("status");
		//	
				if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}


				if (Cast_Category == null || Cast_Category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Cast Category.");
					return new ModelAndView("redirect:Cast_Category_MasterUrl");
				}
				
				
				if (validation.isOnlyAlphabetDASH(Cast_Category) == false) {
					ra.addAttribute("msg","Cast Category "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Cast_Category_MasterUrl");
				}
				
				if (Cast_Category == null || Cast_Category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Cast_Category.");
					return new ModelAndView("redirect:Cast_Category_MasterUrl");
				}
				if (validation.maxlengthcheck50(Cast_Category) == false) {
					ra.addAttribute("msg","Cast Category "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Cast_Category_MasterUrl");
				}
				
				
				
				
				//int id = td.getId() > 0 ? td.getId() : 0;

				

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				//try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  TB_CASTE_CATEGORY_MASTER where upper(category)=:category and status=:status ")
							//.setParameter("id", td.getId())
							.setParameter("category",Cast_Category.toUpperCase())
							.setParameter("status",status.toUpperCase())
						    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
							.uniqueResult();
			
		
					if (c == 0) {
						td.setCategory(Cast_Category);
						td.setStatus("1");
						
						td.setCategory_createdby(username);
						td.setCategory_createddate(date);
						td.setCategory_updatedby(username);
						td.setCategory_updateddate(date);
//						if (c == 0) {
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");
//						} 
//						
					}else {
					ra.addAttribute("msg", "Data already Exist.");
			}

					tx.commit();

					/*
					 * } catch (RuntimeException e) { try {
					 * 
					 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
					 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
					 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
					 */

					sessionHQL.close();
				return new ModelAndView("redirect:Cast_Category_MasterUrl");
			}

		/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
		@PostMapping("/getFilterCast_Category_Master_data")

			public @ResponseBody List<Map<String, Object>> DataTableCast_Category_MasterDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String Cast_Category,String status)
		{
				System.err.println("--ccc----Cast_Category----aa-----"+Cast_Category);
				return hdao.DataTableCast_Category_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Cast_Category,status);

		}

		@PostMapping("/getTotalCast_Category_Master_dataCount")
		public @ResponseBody long DataTableCast_Category_MasterDataTotalCount(HttpSession sessionUserId, String Search, String Cast_Category,String status) {
				System.err.println("--ccc----Cast_Category---------"+Cast_Category);
				return hdao.DataTableCast_Category_MasterDataTotalCount(Search, Cast_Category,status);
			}

		/////////////////////////////////EDIT Cast_Category_Master///////////////////////////////////////////
		@RequestMapping(value = "/Edit_Cast_Category_MasterUrl", method = RequestMethod.POST)
		public ModelAndView Edit_Cast_Category_MasterUrl(String id3, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {
			System.out.println("welcome=====================================");
//					Session sessionHQL = this.sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
			TB_CASTE_CATEGORY_MASTER Cast_Category_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
					Mmap.addAttribute("msg", msg);
					  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					System.out.println("Cast_Category_Master_details "+Cast_Category_Master_details.getId());
					
					Mmap.put("Cast_Category_Master_details", Cast_Category_Master_details);
					Mmap.put("Cast_Category_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
					Mmap.put("updateid",id3);
//					tx.commit();
//					sessionHQL.close();
				
					return new ModelAndView("editCast_Category_MasterTiles");
		}

		@RequestMapping(value = "/edit_Cast_Category_Master_action", method = RequestMethod.POST)
		public ModelAndView edit_Cast_Category_Master_action(@ModelAttribute("edit_Cast_Category_MasterCMD") TB_CASTE_CATEGORY_MASTER rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			
			
		{
			
			
			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("updateid")); 
			String Cast_Category = request.getParameter("Cast_Category");
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			String status = request.getParameter("status");
			
			if (Cast_Category == null || Cast_Category.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Cast Category.");
				return new ModelAndView("redirect:Cast_Category_MasterUrl");
			}
			if (validation.maxlengthcheck50(Cast_Category) == false) {
				ra.addAttribute("msg","Cast Category "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Cast_Category_MasterUrl");
			}
			if (validation.isOnlyAlphabetDASH(Cast_Category) == false) {
				ra.addAttribute("msg","Cast Category "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Cast_Category_MasterUrl");
			}
			
			if (Cast_Category == null || Cast_Category.trim().equals("0")) {
				ra.addAttribute("msg", "Please enter Cast Category.");
				return new ModelAndView("redirect:Cast_Category_MasterUrl");
			}
			
			try {
				Query q0 = session1.createQuery(
						"select count(id) from TB_CASTE_CATEGORY_MASTER where category=:category and  status=:status and  id !=:id");
				q0.setParameter("category", msg);
				q0.setParameter("category", Cast_Category);
				q0.setParameter("status", status);
				
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
					String hql = "update TB_CASTE_CATEGORY_MASTER set category=:category ,status=:status, category_updatedby=:category_updatedby , category_updateddate=:category_updateddate where id=:id";

					Query query = session1.createQuery(hql).setParameter("category", Cast_Category)
							.setParameter("category_updatedby", username)
							.setParameter("id", id)
							.setParameter("status",status)
							.setParameter("category_updateddate", new Date());
						
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
			}
			
			finally {
				if (session1 != null) {
					session1.close();
				}
			}
			
			return new ModelAndView("redirect:Cast_Category_MasterUrl");
		}
		}


		// -------------------------SEARCH Battalion  -------------------------------------//

		@RequestMapping(value = "/admin/getSearch_Cast_Category_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Cast_Category_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "Cast_Category", required = false) String Cast_Category ,String Cast_Category1)  {
			
			
					Mmap.put("Cast_Category", Cast_Category1);
					
					
					if(request.getHeader("Referer") == null ) { 
						//		 session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								 return new ModelAndView("redirect:/landingpage");
							 }
									
							String roleid1 = session.getAttribute("roleid").toString();
							 Boolean val = roledao.ScreenRedirect("Cast_Category_MasterUrl", roleid1);		
								if(val == false) {
									return new ModelAndView("AccessTiles");
							}
			

					return new ModelAndView("Cast_Category_MasterTiles","SystemCMD2",new TB_CASTE_CATEGORY_MASTER());
			   
		}
			
		////////////////////////////////delete Cast_Category//////////////////////////////////////
		@RequestMapping(value = "/delete_Cast_Category_Action", method = RequestMethod.POST)
		public ModelAndView delete_Cast_Category_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
//			try {
	  
				int app = session.createQuery(
						"update TB_CASTE_CATEGORY_MASTER set category_updatedby=:category_updatedby,category_updateddate=:category_updateddate,status=:status where id=:id")
						.setParameter("id", id).setParameter("category_updatedby", username)
						.setParameter("category_updateddate", new Date()).setParameter("status","2").executeUpdate();

				
				tx.commit();
				session.close();
				if (app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
				ra.addAttribute("msg", liststr.get(0));
//			} 
//			catch (Exception e) {
//				e.printStackTrace();
//				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//				ra.addAttribute("msg", liststr.get(0));
				
//			}
				
			return new ModelAndView("redirect:Cast_Category_MasterUrl");
		}
}
