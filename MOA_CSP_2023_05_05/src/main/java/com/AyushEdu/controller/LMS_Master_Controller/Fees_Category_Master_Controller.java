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
import com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Fees_Category_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Fees_Category_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Fees_Category_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Fees_Category_MasterUrl==========================================//

			@RequestMapping(value = "/Fees_Category_MasterUrl", method = RequestMethod.GET)
				
				 	public ModelAndView Fees_Category_MasterUrl(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				
				//SECURITY RAHUL
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Fees_Category_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

					  Mmap.put("msg", msg);
					  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					return new ModelAndView("Fees_Category_MasterTiles");

				}

			//==========================================SAVE Fees_Category_MasterUrl ========================================== 	

			
			@PostMapping(value = "/Fees_Category_masterUrlaction")
			public ModelAndView Fees_Category_masterUrlaction(@Validated @ModelAttribute("hms3") CoFeescategorytype td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra,  String username  )  {
				
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Fees_Category_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				
				System.err.println("hellllllllloooooooooooo");
				String Fees_Category = request.getParameter("Fees_Category");
				String status = request.getParameter("status");
			
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());

				if (Fees_Category == null || Fees_Category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Fees_Category.");
					return new ModelAndView("redirect:Fees_Category_MasterUrl");
				}
				
				
				if (validation.isOnlyAlphabetDASH(Fees_Category) == false) {
					ra.addAttribute("msg","Fees Category "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Fees_Category_MasterUrl");
				}
				
				if (Fees_Category == null || Fees_Category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Fees Category.");
					return new ModelAndView("redirect:Fees_Category_MasterUrl");
				}
				if (validation.maxlengthcheck50(Fees_Category) == false) {
					ra.addAttribute("msg","Faculty Category "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Fees_Category_MasterUrl");
				}
				
				
				
				//int id = td.getId() > 0 ? td.getId() : 0;

				

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				//try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  CoFeescategorytype where upper(feestype)=:feestype and status=:status ")
							//.setParameter("id", td.getId())
							.setParameter("feestype",Fees_Category.toUpperCase())
							.setParameter("status",status.toUpperCase())
						    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
							.uniqueResult();
				
							
					if (c == 0) {
						td.setFeestype(Fees_Category);
						td.setStatus("1");
						
						td.setCreateby(userid);
						td.setCreatedate(new Date());
						td.setModifyby(userid);
						td.setModifydate(date);
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
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}

		/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
		@PostMapping("/getFilterFees_Category_Master_data")

			public @ResponseBody List<Map<String, Object>> DataTableFees_Category_MasterDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String Fees_Category,String status)
		{
				System.err.println("--ccc----Fees_Category----aa-----"+Fees_Category);
				return hdao.DataTableFees_Category_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Fees_Category,status);

		}

		@PostMapping("/getTotalFees_Category_Master_dataCount")
		public @ResponseBody long DataTableFees_Category_MasterDataTotalCount(HttpSession sessionUserId, String Search, String Fees_Category,String status) {
				System.err.println("--ccc----Fees_Category---------"+Fees_Category);
				return hdao.DataTableFees_Category_MasterDataTotalCount(Search, Fees_Category,status);
			}

		/////////////////////////////////EDIT Fees_Category_Master///////////////////////////////////////////
		@RequestMapping(value = "/Edit_Fees_Category_MasterUrl", method = RequestMethod.POST)
		public ModelAndView Edit_Fees_Category_MasterUrl(String id3, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {
			System.out.println("welcome=====================================");
//					Session sessionHQL = this.sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
				CoFeescategorytype Fees_Category_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
					Mmap.addAttribute("msg", msg);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					System.out.println("Fees_Category_Master_details "+Fees_Category_Master_details.getFtid());
					
					Mmap.put("Fees_Category_Master_details", Fees_Category_Master_details);
					Mmap.put("Fees_Category_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
					Mmap.put("updateid",id3);
//					tx.commit();
//					sessionHQL.close();
				
					return new ModelAndView("editFees_Category_MasterTiles");
		}

		@RequestMapping(value = "/edit_Fees_Category_Master_action", method = RequestMethod.POST)
		public ModelAndView edit_Fees_Category_Master_action(@ModelAttribute("edit_Fees_Category_MasterCMD") CoFeescategorytype rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		{
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Fees_Category_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			String status = request.getParameter("status");
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("updateid")); 
			String Fees_Category = request.getParameter("Fees_Category");
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			if (Fees_Category == null || Fees_Category.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Fees Category.");
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}
			if (validation.maxlengthcheck50(Fees_Category) == false) {
				ra.addAttribute("msg","Fees Category "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}
			if (validation.isOnlyAlphabetDASH(Fees_Category) == false) {
				ra.addAttribute("msg","Fees Category "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}
			
			if (Fees_Category == null || Fees_Category.trim().equals("0")) {
				ra.addAttribute("msg", "Please enter Fees Category.");
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}
			if (validation.maxlengthcheck50(Fees_Category) == false) {
				ra.addAttribute("msg","Faculty Category "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Fees_Category_MasterUrl");
			}
			
			
			try {
				Query q0 = session1.createQuery(
						"select count(id) from CoFeescategorytype where feestype=:feestype and status=:status and id !=:id");
				q0.setParameter("feestype", msg);
				q0.setParameter("feestype", Fees_Category);
				q0.setParameter("status", status);
				
				
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
					String hql = "update CoFeescategorytype set feestype=:feestype ,status=:status, modifyby=:modifyby , modifydate=:modifydate where ftid=:ftid";

					Query query = session1.createQuery(hql).setParameter("feestype", Fees_Category)
							.setParameter("modifyby", userid)
							.setParameter("ftid", id)
							.setParameter("status", status)
							.setParameter("modifydate", new Date());
						
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
			
			return new ModelAndView("redirect:Fees_Category_MasterUrl");
		}
		}


		// -------------------------SEARCH Battalion  -------------------------------------//

		@RequestMapping(value = "/admin/getSearch_Fees_Category_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Fees_Category_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "Fees_Category", required = false) String Fees_Category ,String Fees_Category1)  {
			
			
					Mmap.put("Fees_Category", Fees_Category1);
					
					if(request.getHeader("Referer") == null ) { 
						// session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("getSearch_Fees_Category_Master", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
			

					return new ModelAndView("Fees_Category_MasterTiles","SystemCMD2",new CoFeescategorytype());
			   
		}
			
		////////////////////////////////delete Fees_Category//////////////////////////////////////

//		@RequestMapping(value = "/delete_Fees_Category_Action", method = RequestMethod.POST)
//		public ModelAndView delete_Fees_Category_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
//				HttpServletRequest request, ModelMap model, HttpSession session1) {
//			
//					List<String> liststr = new ArrayList<String>();
//				
//					Session session = this.sessionFactory.openSession();
//					Transaction tx = session.beginTransaction();
//				
//					String username = session1.getAttribute("username").toString();
//					
	//
//					CoFeescategorytype INF = (CoFeescategorytype) session.get(CoFeescategorytype.class, id);
	//
//					session.delete(INF);
//					session.flush();
//					session.clear();
//					
//					tx.commit();
//					session.close();
////					if (app > 0) {
//						liststr.add("Data Deleted Successfully.");
////					} else {
////						liststr.add("Data not Deleted.");
////					}
//					ra.addAttribute("msg", liststr.get(0));
//				/*} catch (Exception e) {
//					e.printStackTrace();
//					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//					ra.addAttribute("msg", liststr.get(0));
//					
//				}*/
//				return new ModelAndView("redirect:Fees_Category_MasterUrl");
//			}
		@RequestMapping(value = "/delete_Fees_Category_Action", method = RequestMethod.POST)
		public ModelAndView delete_Fees_Category_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

//			if(request.getHeader("Referer") == null ) { 
//				session1.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
//			String roleid1 = session1.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());	
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
//			try {
	  
				int app = session.createQuery(
						"update CoFeescategorytype set modifyby=:modifyby,modifydate=:modifydate,status=:status where ftid=:ftid")
						.setParameter("ftid", id).setParameter("modifyby", userid)
						.setParameter("modifydate", new Date()).setParameter("status","2").executeUpdate();

				
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
				
			return new ModelAndView("redirect:Fees_Category_MasterUrl");
		}

}
