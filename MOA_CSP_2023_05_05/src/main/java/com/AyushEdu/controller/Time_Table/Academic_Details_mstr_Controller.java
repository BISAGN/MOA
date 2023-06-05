package com.AyushEdu.controller.Time_Table;

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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Time_Table.Academic_MSTR_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Academic_Details_mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;

	@Autowired
	Academic_MSTR_DAO amdao;
	//==========================================OPEN PAGE SYSTEM========================================== 
	
		@RequestMapping(value = "/Academic_mstrUrl", method = RequestMethod.GET)
		public ModelAndView SystemUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			try {
				//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
			
			Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("Academic_MSTR_Tiles");

		}
		
		//==========================================SAVE Academic NAME========================================== 	

		
		@PostMapping(value = "/Academic_Master_Action")
		public ModelAndView Academic_Master_Action(@Validated @ModelAttribute("Academic_MasterCMD") EDU_TT_ACADEMIC_DETAILS_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {

			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String role = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", role);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String academic_details_name = request.getParameter("academic_details_name");
			String refer_code = request.getParameter("refer_code");
//			Session sessiont = sessionFactory.openSession();
//			 String userid = session.getAttribute("userId_for_jnlp").toString();
//			 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
//			 List<UserLogin> institute_idList = q1.list();
//			 sessiont.close();
//			 int institute_id = institute_idList.get(0).getInstitute_id();

			if (academic_details_name == null || academic_details_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Academic Details Name.");
				return new ModelAndView("redirect:Academic_mstrUrl");
			}
			
			if (validation.maxlengthcheck100(academic_details_name) == false) {
				ra.addAttribute("msg","Academic Details Name "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Academic_mstrUrl");
			}
			if (validation.isOnlyAlphabet(academic_details_name) == false) {
				ra.addAttribute("msg","Academic Details Name "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Academic_mstrUrl");
			}
			if (refer_code.equals("") || refer_code.equals("null") || refer_code.equals(null)) {
				ra.addAttribute("msg", "Please Enter Refer code.");
				return new ModelAndView("redirect:Edit_Academic_mstrUrl");
			}
			if (validation.maxlengthcheck3Digit(refer_code) == false) {
				ra.addAttribute("msg","Refer Code "+ validation.MaxlengthcheckMSG3Digit);
				return new ModelAndView("redirect:Academic_mstrUrl");
			}
//			if (validation.maxlengthcheck3(refer_code) == false) {
//				System.err.println("INSIDE VALIDATION");
//				ra.addAttribute("msg", "Refer Code " + validation.MaxlengthcheckMSG3);
//				return new ModelAndView("redirect:Academic_mstrUrl");
//			}
//			if (validation.isOnlyNumer(refer_code) == false) {
//				ra.addAttribute("msg", "Refer Code " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Academic_mstrUrl");
//			}
			
			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
//			String system_name = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_TT_ACADEMIC_DETAILS_MSTR where refer_code=:refer_code and id !=:id ")
//						.setParameter("academic_details_name", td.getAcademic_details_name().toUpperCase())
						.setParameter("refer_code", td.getRefer_code())
//						.setParameter("institute_id", institute_id)
						.setParameter("id", id).uniqueResult();
				System.err.println("C----------------"+c);
				if (id == 0) {
					td.setAcademic_details_name(academic_details_name);
					td.setRefer_code(refer_code);
					td.setCreated_by(username);
					td.setCreated_role(role);
					td.setCreated_dt(date);
//					td.setInstitute_id(institute_id);
//					td.setCreated_dt(created_dt);
					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
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

			return new ModelAndView("redirect:Academic_mstrUrl");
		}
		

		@PostMapping("/getFilterAcademic_Details_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String academic_details_name,String refer_code,HttpSession session) {
				
//			Session sessiont = sessionFactory.openSession();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
//			 List<UserLogin> institute_idList = q1.list();
//			 sessiont.close();
//			int institute_id = institute_idList.get(0).getInstitute_id();
			return amdao.DataTableAcademic_Details_DataList(startPage, pageLength, Search, orderColunm, orderType, academic_details_name,refer_code);

		}

		@PostMapping("/getTotalAcademic_Details_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String academic_details_name,String refer_code,HttpSession session) {
			
//			Session sessiont = sessionFactory.openSession();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
//			 List<UserLogin> institute_idList = q1.list();
//			 sessiont.close();
//			int institute_id = institute_idList.get(0).getInstitute_id();
			return amdao.DataTableAcademic_Details_DataTotalCount(Search, academic_details_name,refer_code);
			
		}
		
		
		// -------------------------SEARCH Battalion  -------------------------------------//
		 
		 @RequestMapping(value = "/admin/getSearch_Academic_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_Academic_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Academic_details_name1", required = false) String Academic_details_name1 ,String Academic_details_name,@ModelAttribute("Refer_code1") String Refer_code)  {
			
			//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

			        Mmap.put("Academic_details_name1", Academic_details_name);
					Mmap.put("Refer_code1", Refer_code);
					
					
					
					

				   return new ModelAndView("Academic_MSTR_Tiles","Academic_MasterCMD",new EDU_TT_ACADEMIC_DETAILS_MSTR());
				   
			}
		 
		 
		 
		//==========================================EDIT Academic NAME========================================== 	
		
		
			@RequestMapping(value = "/Edit_Academic_mstrUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Academic_mstrUrl(@ModelAttribute("id14") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				
				//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				String role = sessionEdit.getAttribute("role").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_TT_ACADEMIC_DETAILS_MSTR academic_Details = amdao.getacademicByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
				Mmap.put("academic_details_name", common.getAcademicList(sessionFactory));
				Mmap.put("refer_code", common.getAcademicList(sessionFactory));
				Mmap.put("academic_Details", academic_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				Mmap.put("academic_Detailsinfo", amdao.getacademicByid(Integer.parseInt(updateid)));
				Mmap.put("updateid", updateid);

				tx.commit();
				sessionHQL.close();

				return new ModelAndView("edit_Academic_Details_Tiles");
			}
			//edit action
			@RequestMapping(value = "/edit_Academic_Details_Action", method = RequestMethod.POST)
			public ModelAndView edit_Academic_Details_Action(@ModelAttribute("edit_Academic_DetailsCMD") EDU_TT_ACADEMIC_DETAILS_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

				//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("updateid"));
				String academic_details_name = request.getParameter("academic_details_name");
				String refer_code = request.getParameter("refer_code");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				
				if (academic_details_name == null || academic_details_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Academic Details Name.");
					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
				}
				
				if (validation.maxlengthcheck100(academic_details_name) == false) {
					ra.addAttribute("msg","Academic Details Name "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
				}
				if (validation.isOnlyAlphabetDASH(academic_details_name) == false) {
					ra.addAttribute("msg","Academic Details Name "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
				}
				if (refer_code.equals("") || refer_code.equals("null") || refer_code.equals(null)) {
					ra.addAttribute("msg", "Please Enter Refer code.");
					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
				}
//				if (validation.isOnlyNumer(refer_code) == false) {
//					ra.addAttribute("msg", "Refer code " + validation.isOnlyNumerMSG1to9);
//					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
//				}
				if (validation.maxlengthcheck3Digit(refer_code) == false) {
					ra.addAttribute("msg", "Refer code " + validation.MaxlengthcheckMSG3Digit);
					return new ModelAndView("redirect:Edit_Academic_mstrUrl");
				}
				
				
				
				try {
					Query q0 = session1.createQuery(
							
							"select count(id) from EDU_TT_ACADEMIC_DETAILS_MSTR where refer_code=:refer_code and id!=:id");
							
//					q0.setParameter("academic_details_name", academic_details_name);
					q0.setParameter("refer_code", refer_code);
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
						System.err.println("C----------------"+c);
					if (c == 0) {
						String hql = "update EDU_TT_ACADEMIC_DETAILS_MSTR set academic_details_name=:academic_details_name,refer_code=:refer_code,modified_by=:modified_by,modified_dt=:modified_dt"
								+ " where id=:id";

						Query query = session1.createQuery(hql)
								.setParameter("academic_details_name", academic_details_name)
								.setParameter("refer_code", refer_code)
								.setParameter("modified_by", username)
								.setParameter("modified_dt", new Date())
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

				} catch (Exception e) {
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
				
				return new ModelAndView("redirect:Academic_mstrUrl");
			}	
			
			//==========================================DELETE SYSTEM NAME========================================== 	
			 
			 
			@RequestMapping(value = "/delete_Url21", method = RequestMethod.POST)
			public ModelAndView delete_Url21(@ModelAttribute("id21") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
				
				//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"delete from EDU_TT_ACADEMIC_DETAILS_MSTR where id=:id")
							.setParameter("id", id).executeUpdate();

					
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
				return new ModelAndView("redirect:Academic_mstrUrl");
			}


}
