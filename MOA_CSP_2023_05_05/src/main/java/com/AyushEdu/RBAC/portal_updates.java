package com.AyushEdu.RBAC;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_MAERQUE;
import com.AyushEdu.dao.Marque_MasterDao;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class portal_updates {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Marque_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Marque_MasterUrl==========================================//

				@RequestMapping(value = "/portal_update_MasterUrl", method = RequestMethod.GET)
					
					 	public ModelAndView portal_update_MasterUrl(ModelMap Mmap, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
					
//					if(request.getHeader("Referer") == null ) { 
//						//		 session.invalidate();
//								 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//								 return new ModelAndView("redirect:/landingpage");
//							 }
//									
//							String roleid1 = session.getAttribute("roleid").toString();
//							 Boolean val = roledao.ScreenRedirect("Marque_MasterUrl", roleid1);		
//								if(val == false) {
//									return new ModelAndView("AccessTiles");
//							}
						
						  Mmap.put("msg", msg);
						  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						return new ModelAndView("Latest_Update_MasterTiles");

					}

				//==========================================SAVE Marque_MasterUrl ========================================== 	

				
				@PostMapping(value = "/portal_updateaction")
				public ModelAndView Marque_masterUrlaction(@Validated @ModelAttribute("marqcmd") TB_MAERQUE td, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra,  String username ) throws ParseException  {
					
					
					if(request.getHeader("Referer") == null ) { 
						//		 session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								 return new ModelAndView("redirect:/landingpage");
							 }
									
							String roleid1 = session.getAttribute("roleid").toString();
							 Boolean val = roledao.ScreenRedirect("Marque_MasterUrl", roleid1);		
								if(val == false) {
									return new ModelAndView("AccessTiles");
							}
					
					SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
					String Marque = request.getParameter("Marque");
					String from_date = request.getParameter("from_date");
					String to_date = request.getParameter("to_date");
					String ayushPort = request.getParameter("checkbox-1");
					String nchPort = request.getParameter("checkbox-2");
					String ncismPort = request.getParameter("checkbox-3");
					String status = request.getParameter("status");
					
					if(ayushPort == null && ncismPort == null && nchPort == null) {
						ra.addAttribute("msg", "Please Select atleast one Port.");
						return new ModelAndView("redirect:Marque_MasterUrl");
					}
					
					String marquefor = "";
					if(ayushPort != null) {
						if(marquefor.equals(""))marquefor +=ayushPort;
						else marquefor +=","+ ayushPort;
						
					}
//					if(ayushPort == null &&  ncismPort == null && nchPort != null) {
//						marquefor +=""+ nchPort;
//					}
//					if(ayushPort == null && ncismPort != null && nchPort == null) {
//						marquefor += ""+ncismPort;
//					}
					if(nchPort != null) {
						if(marquefor.equals(""))marquefor +=nchPort;
						else marquefor +=","+ nchPort;
						
					}
					
					if(ncismPort != null) {
						
						if(marquefor.equals(""))marquefor +=ncismPort;
						else marquefor +=","+ ncismPort;
					}
					
//					if(  ncismPort == null && nchPort == null && ayushPort != null) {
//						marquefor += ayushPort;
//					}
					if (Marque == null || Marque.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Marque.");
						return new ModelAndView("redirect:Marque_MasterUrl");
					}
//					if (validation.isOnlyAlphabetDASH(Marque) == false) {
//						ra.addAttribute("msg","Marque "+ validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Marque_MasterUrl");
//					}
					if (Marque == null || Marque.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Marque.");
						return new ModelAndView("redirect:Marque_MasterUrl");
					}
//					if (validation.maxlengthcheck50(Marque) == false) {
//						ra.addAttribute("msg","Marque "+ validation.MaxlengthcheckMSG50);
//						return new ModelAndView("redirect:Marque_MasterUrl");
//					}
				
					if (from_date == null || from_date.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select From Date.");
						return new ModelAndView("redirect:Marque_MasterUrl");
					}
					if (to_date == null || to_date.trim().equals("0")) {
						ra.addAttribute("msg", "Please Selcet To Date.");
						return new ModelAndView("redirect:Marque_MasterUrl");
					}
					
					int id = td.getId() > 0 ? td.getId() : 0;

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

//					try {
						Long c = (Long) sessionHQL.createQuery("select count(id) from  TB_MAERQUE where upper(msg)=:msg and from_date=:from_date and to_date=:to_date and marque_for=:marque_for and status=:status and id!=:id ")
								.setParameter("msg",Marque.toUpperCase())
								.setParameter("from_date",formate.parse(from_date))
								.setParameter("to_date",formate.parse(to_date))
								.setParameter("marque_for",marquefor)
								.setParameter("status",Integer.parseInt(status))
								.setParameter("id",id)
								.uniqueResult();
					if(id == 0) {
						if (c == 0) {
							td.setMsg(Marque);
							td.setFrom_date(formate.parse(from_date));
							td.setTo_date(formate.parse(to_date));
							td.setMarque_for(marquefor);
					
							td.setStatus(1);
							
							td.setCreated_by(username);
							td.setCreated_date(date);
								sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();
								ra.addAttribute("msg", "Data Saved Successfully.");
						}else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
						tx.commit();

						
//						 } catch (RuntimeException e) { 
//							 try {
//								 ra.addAttribute("msg", "roll back transaction"); 
//							 }catch (RuntimeException rbe) { 
//								 ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); 
//							}throw e; 
//							
//						 } finally { 
//							 if (sessionHQL != null) { 
//								 sessionHQL.close(); 
//								 } 
//						}

						sessionHQL.close();
					return new ModelAndView("redirect:Marque_MasterUrl");
				}

			/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
//			@PostMapping("/getFilterMarque_Master_data")
//
//				public @ResponseBody List<Map<String, Object>> DataTableportal_updateDataList(int startPage, int pageLength, String Search,
//					String orderColunm, String orderType,String Marque,String from_date,String to_date,String ayu_port,String nch_port,String ncism_port,String status)
//			{
//					System.err.println("--ccc----Marque----aa-----"+Marque);
//					return hdao.DataTableMarque_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Marque,from_date,to_date,ayu_port,nch_port,ncism_port,status);
//
//			}
//
//			@PostMapping("/getTotalMarque_Master_dataCount")
//			public @ResponseBody long DataTableportal_updateTotalCount(HttpSession sessionUserId, String Search, String Marque,String from_date,String to_date,String ayu_port,String nch_port,String ncism_port,String status) {
//					System.err.println("--ccc----Marque---------"+Marque);
//					return hdao.DataTableMarque_MasterDataTotalCount(Search, Marque,from_date,to_date,ayu_port,nch_port,ncism_port,status);
//				}

			/////////////////////////////////EDIT Marque_Master///////////////////////////////////////////
			@RequestMapping(value = "/edit_portal_update_MasterUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Marque_MasterUrl(String id3, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				System.out.println("welcome=====================================");
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
				TB_MAERQUE Marque_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
						Mmap.addAttribute("msg", msg);
						System.out.println("Marque_Master_details "+Marque_Master_details.getId());
						
						Mmap.put("Marque_Master_details", Marque_Master_details);
						Mmap.put("Marque_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
						Mmap.put("updateid",id3);
//						tx.commit();
//						sessionHQL.close();
					
						return new ModelAndView("editMarque_MasterTiles");
			}

			@RequestMapping(value = "/editportal_update_action", method = RequestMethod.POST)
			public ModelAndView edit_Marque_Master_action(@ModelAttribute("edit_Marque_MasterCMD") TB_MAERQUE rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			{
				
				if(request.getHeader("Referer") == null ) { 
					//		 session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
								
						String roleid1 = session.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Marque_MasterUrl", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
				
				
				
				DateFormat formate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//				SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("updateid")); 
				String Marque = request.getParameter("Marque");
				String from_date = request.getParameter("from_date");
				String to_date = request.getParameter("to_date");
				String ayushPort = request.getParameter("checkbox-1");
				String nchPort = request.getParameter("checkbox-2");
				String ncismPort = request.getParameter("checkbox-3");
				String status = request.getParameter("status");
				
				System.err.println(ayushPort+"-"+nchPort+"-"+ncismPort);
				
				if(ayushPort == null && ncismPort == null && nchPort == null) {
					ra.addAttribute("msg", "Please Select atleast one Port.");
					return new ModelAndView("redirect:Marque_MasterUrl");
				}
				
				String marquefor = "";
				if(ayushPort != null) {
					marquefor += ayushPort;
				}
				if(nchPort != null) {
					marquefor +=","+ nchPort;
				}
				if(ncismPort != null) {
					marquefor += ","+ncismPort;
				}
				
				System.err.println(marquefor);

				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (Marque == null || Marque.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Marque.");
					return new ModelAndView("redirect:Marque_MasterUrl");
				}
//				if (validation.maxlengthcheck50(Marque) == false) {
//					ra.addAttribute("msg","Marque "+ validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Marque_MasterUrl");
//				}
//				if (validation.isOnlyAlphabetDASH(Marque) == false) {
//					ra.addAttribute("msg","Marque "+ validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Marque_MasterUrl");
//				}
				
				if (Marque == null || Marque.trim().equals("0")) {
					ra.addAttribute("msg", "Please enter Marque.");
					return new ModelAndView("redirect:Marque_MasterUrl");
				}
				if (from_date == null || from_date.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select From Date.");
					return new ModelAndView("redirect:Marque_MasterUrl");
				}
				if (to_date == null || to_date.trim().equals("0")) {
					ra.addAttribute("msg", "Please Selcet To Date.");
					return new ModelAndView("redirect:Marque_MasterUrl");
				}
//				try {
					Query q0 = session1.createQuery(
							"select count(id) from TB_MAERQUE where msg=:msg and id !=:id");
					q0.setParameter("msg", msg);
					q0.setParameter("msg", Marque);
					
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
					
					if (c == 0) {
						String hql = "update TB_MAERQUE set msg=:msg ,from_date=:from_date,to_date=:to_date,marque_for=:marque_for,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

						Query query = session1.createQuery(hql).setParameter("msg", Marque)
								.setParameter("from_date",formate.parse(from_date))
								.setParameter("to_date", formate.parse(to_date))
								.setParameter("marque_for", marquefor)
								.setParameter("modified_by", username)
								.setParameter("id", id)
								.setParameter("status", 1)
								.setParameter("modified_date", new Date());
							
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
//				}
//				
//				finally {
//					if (session1 != null) {
//						session1.close();
//					}
//				}
				
				return new ModelAndView("redirect:Marque_MasterUrl");
			}
			}


			// -------------------------SEARCH Battalion  -------------------------------------//

			@RequestMapping(value = "/admin/getSearch_portal_update_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_Marque_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Marque", required = false) String Marque ,String Marque1)  {
				
				
						Mmap.put("Marque", Marque1);
				

						return new ModelAndView("Marque_MasterTiles","SystemCMD2",new TB_MAERQUE());
				   
			}
				
			////////////////////////////////delete Marque//////////////////////////////////////


			@RequestMapping(value = "/delete_portal_update_Action", method = RequestMethod.POST)
			public ModelAndView delete_Marque_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

				if(request.getHeader("Referer") == null ) { 
					//		 session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
								
						String roleid1 = session1.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Marque_MasterUrl", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
					
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
//				try {
		  
					int app = session.createQuery(
							"update TB_MAERQUE set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

					
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
					
				return new ModelAndView("redirect:Marque_MasterUrl");
			}
}
