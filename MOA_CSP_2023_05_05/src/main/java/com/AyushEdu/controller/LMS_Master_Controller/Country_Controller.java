package com.AyushEdu.controller.LMS_Master_Controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Country_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Course_Master_CD_DAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.CountryDao;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Country_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	CountryDao Country_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Country_Master_CD_DAO  Coun_dirdao;
	
	// -------------------------------Country For page Open -------------------------------------//
	
	 @RequestMapping(value = "/admin/Country", method = RequestMethod.GET)
	 public ModelAndView Country(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 try {
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 return new ModelAndView("CountryTiles");
	 }
	 
	// -------------------------------Country save -------------------------------------//
	 
	 @RequestMapping(value = "/CountryAction",method=RequestMethod.POST)
		public ModelAndView CountryAction(@ModelAttribute("CountryCMD") TB_COUNTRY com,
				HttpServletRequest request,ModelMap model,HttpSession session, @RequestParam(value = "msg", required = false) String msg,RedirectAttributes ra) {
			
		 if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		 	 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String name = request.getParameter("name").trim();
				String status = request.getParameter("status");
				
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
//				 	if (name.equals("") || name.equals("null")|| name.equals(null)) {
//				 		model.put("msg", "Please Enter Country.");
//				 		return new ModelAndView("redirect:Country");
//					}
				 	if (name == null || name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Country.");
						return new ModelAndView("redirect:Country");
					}
				 	if (validation.maxlengthcheck100(name) == false) {
				 		ra.addAttribute("msg","Country "+ validation.MaxlengthcheckMSG100);
				 		return new ModelAndView("redirect:Country");
					 }
					 if (validation.isOnlyAlphabetDASH(name) == false) {
						ra.addAttribute("msg","Country "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Country");
					 }
					 if (status == null || status.trim().equals("0")) {
							ra.addAttribute("msg", "Please Select Status.");
							return new ModelAndView("redirect:Country");
						}

						if (status == "2" || status.trim().equals("2")) {
							ra.addAttribute("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Country");
						}
				 
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_COUNTRY where name=:name and status=:status and id !=:id");
					q0.setParameter("name", com.getName());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();
					
					int idd =0;
					if (id == 0) {
						
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setName(name);
						if (c == 0) {
							 idd = (int)sessionHQL.save(com);
							sessionHQL.save(com);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
				
					tx.commit();
					//For Core Directory
					Coun_dirdao.Insert_Country_Mstr_Data(idd);
				}catch(RuntimeException e){
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Country");
			}
	 
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
	 @RequestMapping(value = "/admin/getSearch_Country_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Country_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "Country_name1", required = false) String Country_name1 ,String Country_name,@ModelAttribute("status1") String status)  {
		
		 if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

				Mmap.put("Country_name1", Country_name1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				

			   return new ModelAndView("CountryTiles","CountryCMD",new TB_COUNTRY());
			   
		}
	
				// -------------------------Edit Country For page Open -------------------------------------	
				
	 @RequestMapping(value = "/Edit_Country",method=RequestMethod.POST)
		public ModelAndView Edit_Country(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
				HttpSession sessionEdit) {
		
		 if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		 
		 		TB_COUNTRY countryDetails = Country_dao.getCountryByid(Integer.parseInt(updateid));
				Mmap.put("countryDetails", countryDetails);	
				Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_CountryTiles");
		}
		
	// -------------------------Edit Country Action -------------------------------------
	 
		@RequestMapping(value = "/Edit_Country_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Country_Action(@ModelAttribute("Edit_CountryCMD") TB_COUNTRY rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg,RedirectAttributes ra) throws ParseException {

			 if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String username = session.getAttribute("username").toString();

		//	String id = request.getParameter("id");
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name").trim();
			String status = request.getParameter("status");
			
//			if (name.equals("") || name.equals("null")|| name.equals(null)) {
//		 		model.put("msg", "Please Enter Country.");
//		 		return new ModelAndView("redirect:Country");
//			}
			if (name == null || name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Country.");
				return new ModelAndView("redirect:Country");
			}
		 	if (validation.maxlengthcheck100(name) == false) {
		 		ra.addAttribute("msg","Country "+ validation.MaxlengthcheckMSG100);
		 		return new ModelAndView("redirect:Country");
			 }
			 if (validation.isOnlyAlphabetDASH(name) == false) {
				ra.addAttribute("msg","Country "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Country");
			 }
			 if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:Country");
				}

				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Country");
				}
			Session session1 = this.sessionFactory.openSession();
	        Transaction tx = session1.beginTransaction();
	        
				 try {
					
					Query q0 = session1.createQuery(
								"select count(id) from TB_COUNTRY where name=:name and status=:status and id !=:id");
					 
						q0.setParameter("name", name);
						q0.setParameter("status", status);
						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();
						
						if (c == 0) {
							String hql = "update TB_COUNTRY set name=:name,status=:status,modify_by=:modify_by,modify_date=:modify_date"
									+ " where id=:id";

							Query query = session1.createQuery(hql)
									.setParameter("name", name)
									.setParameter("status", status)
									.setParameter("modify_by", username)
									.setParameter("modify_date", new Date())
									.setParameter("id", id);
							msg = query.executeUpdate() > 0 ? "1" : "0";
							tx.commit();
							//For Core Directory
							Coun_dirdao.Update_Country_Mstr_Data(id,name,username,new Date(),status);      
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
			return new ModelAndView("redirect:Country");
		}
		
		@PostMapping("/getFiltercountry_data")
		public @ResponseBody List<Map<String, Object>> getFiltercountry_data(int startPage, int pageLength,String Search, 
				String orderColunm, String orderType, String country, String status) {
			return Country_dao.DataTableCountryDataList(startPage, pageLength, Search, orderColunm, orderType, country, status);

		}
		
//		@RequestMapping(value = "/getFiltercountry_data", method = RequestMethod.POST)
//		public @ResponseBody ArrayList<ArrayList<String>> getFiltercountry_data(int startPage, int pageLength, String Search,
//				String orderColunm, String orderType,String country,String status,HttpSession sessionUserId, Principal principal) {
//			
//			return Country_dao.DataTableCountryDataList(startPage, pageLength, Search,orderColunm, orderType,country,status,sessionUserId);
//
//		}

		@RequestMapping(value = "/getTotalcountry_dataCount", method = RequestMethod.POST)
		public @ResponseBody long getTotalcountry_dataCount(HttpSession sessionUserId, String Search,String country) {
			return Country_dao.DataTableCountryDataTotalCount(Search,sessionUserId,country);
		}
		
		
		
		

		// -------------------------Delete Country  -------------------------------------//

		@RequestMapping(value = "/delete_Country", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Country(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Country", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}	

			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_COUNTRY set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate)
						 .setParameter("status","inactive")
						.setParameter("modify_by", username)
						.setParameter("modify_date", new Date())
						.setParameter("id", id).executeUpdate();
				tx.commit();
				//For Directory
				Coun_dirdao.Delete_Country_Mstr_Data(id); 
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:Country");
		}
	
}
