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
import com.AyushEdu.Core_Directory.Marital_Status_CD_DAO;
import com.AyushEdu.Core_Directory.Organization_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ORGANIZATION_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.organization_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Organization_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	organization_MasterDao hdao;

	@Autowired
	ValidationController validation;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Organization_Master_CD_DAO ORG_dirdao;
	private Date date;
	// ==========================================OPEN PAGE organization_MasterUrl==========================================//

				@RequestMapping(value = "/Organization_MasterUrl", method = RequestMethod.GET)
					
					 	public ModelAndView organization_MasterUrl(ModelMap Mmap, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
						
					
					//SECURITY RAHUL
					
					if(request.getHeader("Referer") == null ) { 
						// session.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}

					
						  Mmap.put("msg", msg);
						  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						return new ModelAndView("organization_MasterTiles");

					}

				//==========================================SAVE organization_MasterUrl ========================================== 	

				
				@PostMapping(value = "/organization_masterUrlaction")
				public ModelAndView organization_masterUrlaction(@Validated @ModelAttribute("hms3") EDU_LMS_ORGANIZATION_MSTR td, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra,  String username  )  {
					
					
					if(request.getHeader("Referer") == null ) { 
						// session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					
					
					System.err.println("hellllllllloooooooooooo");
					String organization = request.getParameter("organization");
					String status = request.getParameter("status");
			//	


					if (organization == null || organization.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter organization.");
						return new ModelAndView("redirect:Organization_MasterUrl");
					}
					
					
					if (validation.isOnlyAlphabetDASH(organization) == false) {
						ra.addAttribute("msg","organization "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Organization_MasterUrl");
					}
					
					if (organization == null || organization.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter organization.");
						return new ModelAndView("redirect:Organization_MasterUrl");
					}
					
					if (validation.maxlengthcheck50(organization) == false) {
						ra.addAttribute("msg","Organization "+ validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:Organization_MasterUrl");
					}
					
					
					
					//int id = td.getId() > 0 ? td.getId() : 0;

					

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					//try {
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_LMS_ORGANIZATION_MSTR where upper(organization)=:organization  and status=:status")
								//.setParameter("id", td.getId())
								.setParameter("organization",organization.toUpperCase())
								.setParameter("status",status.toUpperCase())
							    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
								.uniqueResult();
					
						int idd =0;		
						if (c == 0) {
							td.setOrganization(organization);
							td.setStatus("1");
							
							td.setCreated_by(username);
							td.setCreated_date(date);
							td.setModified_by(username);
							td.setModified_date(date);
//							if (c == 0) {
							 idd = (int)sessionHQL.save(td);
								sessionHQL.save(td);
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
						ORG_dirdao.Insert_Organization_Master_Data(idd);
						/*
						 * } catch (RuntimeException e) { try {
						 * 
						 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
						 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
						 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
						 */

						sessionHQL.close();
					return new ModelAndView("redirect:Organization_MasterUrl");
				}

			/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
			@PostMapping("/getFilterorganization_Master_data")

				public @ResponseBody List<Map<String, Object>> DataTableorganization_MasterDataList(int startPage, int pageLength, String Search,
					String orderColunm, String orderType,String organization,String status)
			{
					System.err.println("--ccc----organization----aa-----"+organization);
					return hdao.DataTableorganization_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,organization,status);

			}

			@PostMapping("/getTotalorganization_Master_dataCount")
			public @ResponseBody long DataTableorganization_MasterDataTotalCount(HttpSession sessionUserId, String Search, String organization,String status) {
					System.err.println("--ccc----organization---------"+organization);
					return hdao.DataTableorganization_MasterDataTotalCount(Search, organization,status);
				}

			/////////////////////////////////EDIT organization_Master///////////////////////////////////////////
			@RequestMapping(value = "/Edit_organization_MasterUrl", method = RequestMethod.POST)
			public ModelAndView Edit_organization_MasterUrl(String id3, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = Mmap.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				
				
				System.out.println("welcome=====================================");
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
				EDU_LMS_ORGANIZATION_MSTR organization_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
						Mmap.addAttribute("msg", msg);
						System.out.println("organization_Master_details "+organization_Master_details.getId());
						 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						Mmap.put("organization_Master_details", organization_Master_details);
						Mmap.put("organization_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
						Mmap.put("updateid",id3);
//						tx.commit();
//						sessionHQL.close();
					
						return new ModelAndView("editorganization_MasterTiles");
			}

			@RequestMapping(value = "/edit_organization_Master_action", method = RequestMethod.POST)
			public ModelAndView edit_organization_Master_action(@ModelAttribute("edit_organization_MasterCMD") EDU_LMS_ORGANIZATION_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			{
				
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("updateid")); 
				String organization = request.getParameter("organization");
				String status = request.getParameter("status");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (organization == null || organization.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Organization.");
					return new ModelAndView("redirect:Organization_MasterUrl");
				}
				if (validation.maxlengthcheck50(organization) == false) {
					ra.addAttribute("msg","Organization "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Organization_MasterUrl");
				}
				if (validation.isOnlyAlphabetDASH(organization) == false) {
					ra.addAttribute("msg","Organization "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Organization_MasterUrl");
				}
				
				if (organization == null || organization.trim().equals("")) {
					ra.addAttribute("msg", "Please enter organization.");
					return new ModelAndView("redirect:Organization_MasterUrl");
				}
				if (validation.maxlengthcheck50(organization) == false) {
					ra.addAttribute("msg","Organization "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Organization_MasterUrl");
				}
				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_LMS_ORGANIZATION_MSTR where organization=:organization and status =: status and id !=:id");
					q0.setParameter("organization", msg);
					q0.setParameter("organization", organization);
					q0.setParameter("status", status);
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
					
					if (c == 0) {
						String hql = "update EDU_LMS_ORGANIZATION_MSTR set organization=:organization,status=:status,modified_by=:modified_by,modified_date=:modified_date where id=:id";

						Query query = session1.createQuery(hql).setParameter("organization", organization).setParameter("status", status)
								.setParameter("modified_by", username).setParameter("modified_date", new Date())
								.setParameter("id", id);
							
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						//For Core Directory
						ORG_dirdao.Update_Organization_Master_Data(id,organization,username,new Date(),status);
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
				
				return new ModelAndView("redirect:Organization_MasterUrl");
			}
			}


			// -------------------------SEARCH Battalion  -------------------------------------//

			@RequestMapping(value = "/admin/getSearch_organization_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_organization_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "organization", required = false) String organization ,String organization1)  {
				
				
						Mmap.put("organization", organization1);
						
						if(request.getHeader("Referer") == null ) { 
							// session.invalidate();
							Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
								
						String roleid1 = session.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
				

						return new ModelAndView("organization_MasterTiles","SystemCMD2",new EDU_LMS_ORGANIZATION_MSTR());
				   
			}
				
			////////////////////////////////delete organization//////////////////////////////////////
			@RequestMapping(value = "/delete_organization_Action", method = RequestMethod.POST)
			public ModelAndView delete_organization_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Organization_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}	
				
				
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
//				try {
		  
					int app = session.createQuery(
							"update EDU_LMS_ORGANIZATION_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status","2").executeUpdate();

					
					tx.commit();
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
					
				return new ModelAndView("redirect:Organization_MasterUrl");
			}
}
