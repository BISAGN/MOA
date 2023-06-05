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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Counseling_Authority_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Counseling_Authority_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Counseling_Authority_MasterDao hdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	ValidationController validation;

//	@Autowired
//	counseling_authority_Master_CD_Dao  DM_dirdao;
	
	
	private Date date;
	// ==========================================OPEN PAGE counseling_authority_MasterUrl==========================================//

				@RequestMapping(value = "/Counseling_Authority_MasterUrl", method = RequestMethod.GET)
					
					 	public ModelAndView Counseling_Authority_MasterUrl(ModelMap Mmap, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
					
					
					
					
					
					//SECURITY RAHUL
					
					if(request.getHeader("Referer") == null ) { 
				//		 session.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Counseling_Authority_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
						  Mmap.put("msg", msg);
						  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						return new ModelAndView("counseling_authority_MasterTiles");

					}

				//==========================================SAVE counseling_authority_MasterUrl ========================================== 	

				
				@PostMapping(value = "/counseling_authority_masterUrlaction")
				public ModelAndView counseling_authority_masterUrlaction(@Validated @ModelAttribute("hms3") EDU_LMS_COUNSELING_AUTHORITY_MSTR td, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra)  {
					
					String username = session.getAttribute("username").toString();
					System.err.println("hellllllllloooooooooooo");
					String counseling_authority = request.getParameter("counseling_authority");
					String status = request.getParameter("status");
					
			//	
					if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Counseling_Authority_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}


					if (counseling_authority == null || counseling_authority.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Counseling Authority.");
						return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
					}
					
					
					if (validation.isOnlyAlphabetDASH(counseling_authority) == false) {
						ra.addAttribute("msg","Counseling Authority "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
					}
					
					if (counseling_authority == null || counseling_authority.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Counseling Authority.");
						return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
					}
					if (validation.maxlengthcheck50(counseling_authority) == false) {
						ra.addAttribute("msg","Counseling Authority "+ validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
					}
					
					
					
					//int id = td.getId() > 0 ? td.getId() : 0;

					

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					//try {
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_LMS_COUNSELING_AUTHORITY_MSTR where upper(counseling_authority)=:counseling_authority and status=:status  ")
								//.setParameter("id", td.getId())
								.setParameter("counseling_authority",counseling_authority.toUpperCase())
								.setParameter("status",Integer.parseInt(status))
							    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
								.uniqueResult();
					
						int idd =0;	
						if (c == 0) {
							td.setCounseling_authority(counseling_authority);
							td.setStatus(1);
							
							td.setCreated_by(username);
							td.setCreated_date(new Date());
							td.setModified_by(username);
							td.setModified_date(date);
//							if (c == 0) {
							idd = (int)sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();
								ra.addAttribute("msg", "Data Saved Successfully.");
//							} 
//							
						}else {
						ra.addAttribute("msg", "Data already Exist.");
				}

						tx.commit();
						//For Core Directory
//						DM_dirdao.Insert_counseling_authority_Mstr_Data(idd);
						/*
						 * } catch (RuntimeException e) { try {
						 * 
						 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
						 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
						 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
						 */

						sessionHQL.close();
					return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
				}

			/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
			@PostMapping("/getFiltercounseling_authority_Master_data")

				public @ResponseBody List<Map<String, Object>> DataTablecounseling_authority_MasterDataList(int startPage, int pageLength, String Search,
					String orderColunm, String orderType,String counseling_authority,String status)
			{
					System.err.println("--ccc----counseling_authority----aa-----"+counseling_authority);
					return hdao.DataTablecounseling_authority_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,counseling_authority,status);

			}

			@PostMapping("/getTotalcounseling_authority_Master_dataCount")
			public @ResponseBody long DataTablecounseling_authority_MasterDataTotalCount(HttpSession sessionUserId, String Search, String counseling_authority,String status) {
					System.err.println("--ccc----counseling_authority---------"+counseling_authority);
					return hdao.DataTablecounseling_authority_MasterDataTotalCount(Search, counseling_authority,status);
				}

			/////////////////////////////////EDIT counseling_authority_Master///////////////////////////////////////////
			@RequestMapping(value = "/Edit_Counseling_Authority_MasterUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Counseling_Authority_MasterUrl(String id3, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				
				
				
				
				System.out.println("welcome=====================================");
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
				EDU_LMS_COUNSELING_AUTHORITY_MSTR counseling_authority_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
						Mmap.addAttribute("msg", msg);
						  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						System.out.println("counseling_authority_Master_details "+counseling_authority_Master_details.getId());
						
						Mmap.put("counseling_authority_Master_details", counseling_authority_Master_details);
						Mmap.put("counseling_authority_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
						Mmap.put("updateid",id3);
//						tx.commit();
//						sessionHQL.close();
					
						return new ModelAndView("editcounseling_authority_MasterTiles");
			}
			

			@RequestMapping(value = "/edit_counseling_authority_Master_action", method = RequestMethod.POST)
			public ModelAndView edit_counseling_authority_Master_action(@ModelAttribute("edit_counseling_authority_MasterCMD") EDU_LMS_COUNSELING_AUTHORITY_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			{
				
				
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Counseling_Authority_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				
				
				String status = request.getParameter("status");
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("updateid")); 
				String counseling_authority = request.getParameter("counseling_authority");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (counseling_authority == null || counseling_authority.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Counseling Authority.");
					return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
				}
				if (validation.maxlengthcheck50(counseling_authority) == false) {
					ra.addAttribute("msg","Counseling Authority "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
				}
				if (validation.isOnlyAlphabetDASH(counseling_authority) == false) {
					ra.addAttribute("msg","Counseling Authority "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
				}
				
				if (counseling_authority == null || counseling_authority.trim().equals("0")) {
					ra.addAttribute("msg", "Please enter Counseling Authority.");
					return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
				}
				
				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_LMS_COUNSELING_AUTHORITY_MSTR where counseling_authority=:counseling_authority and status=:status and id !=:id");
					q0.setParameter("counseling_authority", msg);
					q0.setParameter("counseling_authority", counseling_authority);
					q0.setParameter("status",Integer.parseInt(status));
					
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
					
					if (c == 0) {
						String hql = "update EDU_LMS_COUNSELING_AUTHORITY_MSTR set counseling_authority=:counseling_authority ,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

						Query query = session1.createQuery(hql).setParameter("counseling_authority", counseling_authority)
								.setParameter("modified_by", username)
								.setParameter("id", id)
								.setParameter("status",Integer.parseInt( status))
								.setParameter("modified_date", new Date());
							
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						 //For Core Directory
//	    				DM_dirdao.Update_counseling_authority_Mstr_Data( id,counseling_authority , Integer.parseInt(status),  username,  new Date());
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
				
				return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
			}
			}


			// -------------------------SEARCH Battalion  -------------------------------------//

			@RequestMapping(value = "/admin/getSearch_counseling_authority_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_counseling_authority_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "counseling_authority", required = false) String counseling_authority ,String counseling_authority1)  {
				
				
						Mmap.put("counseling_authority", counseling_authority1);
				

						return new ModelAndView("counseling_authority_MasterTiles","SystemCMD2",new EDU_LMS_COUNSELING_AUTHORITY_MSTR());
				   
			}
				
			////////////////////////////////delete counseling_authority//////////////////////////////////////


			@RequestMapping(value = "/delete_counseling_authority_Action", method = RequestMethod.POST)
			public ModelAndView delete_counseling_authority_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

				if(request.getHeader("Referer") == null ) { 
					session1.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Counseling_Authority_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
//				try {
		  
					int app = session.createQuery(
							"update EDU_LMS_COUNSELING_AUTHORITY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

					
					tx.commit();
					//For Core Directory
//					DM_dirdao.Delete_counseling_authority_Mstr_Data(id);
					session.close();
					if (app > 0) {
						liststr.add("Data Deleted Successfully.");
					} else {
						liststr.add("Data not Deleted.");
					}
					ra.addAttribute("msg", liststr.get(0));
//				} 
//				catch (Exception e) {
//					e.printStackTrace();
//					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//					ra.addAttribute("msg", liststr.get(0));
					
//				}
					
				return new ModelAndView("redirect:Counseling_Authority_MasterUrl");
			}
}
