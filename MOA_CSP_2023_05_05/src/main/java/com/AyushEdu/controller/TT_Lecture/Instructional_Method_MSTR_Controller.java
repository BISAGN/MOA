package com.AyushEdu.controller.TT_Lecture;

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
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.TT_Lecture.Instructional_Method_MSTRDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Instructional_Method_MSTR_Controller {
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
	Instructional_Method_MSTRDAO idao;
	//==========================================OPEN PAGE INSTRUCTIONAL METHODS========================================== 
	
		@RequestMapping(value = "/Instructional_MasterUrl", method = RequestMethod.GET)
		public ModelAndView Instructional_MasterUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			try {
				
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
				
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
			
			Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("Instructional_Master_Tiles");

		}


//==========================================SAVE Academic NAME========================================== 	


		@PostMapping(value = "/Instruction_Master_Action")
		public ModelAndView Instruction_Master_Action(@Validated @ModelAttribute("Instruction_MasterCMD") EDU_LEC_INSTRUCTION_METHOD_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {

//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String role = session.getAttribute("role").toString();
			
			String instructional_method_name = request.getParameter("instructional_method_name");
			
			
			if (instructional_method_name == null || instructional_method_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Instruction Method.");
				return new ModelAndView("redirect:Instructional_MasterUrl");
			}

			if (validation.isOnlyAlphabet(instructional_method_name) == false) {
				ra.addAttribute("msg","Instructional Methods "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Instructional_MasterUrl");
			}
			if (validation.maxlengthcheck100(instructional_method_name) == false) {
				ra.addAttribute("msg","Instructional Methods "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Instructional_MasterUrl");
			}
			if (validation.isOnlyAlphabetDASH(instructional_method_name) == false) {
				ra.addAttribute("msg","Instruction Method"+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Instructional_MasterUrl");
			}

			
			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
//			String system_name = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				
//				System.err.println("td.getRefer_code()"+td.getRefer_code());
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LEC_INSTRUCTION_METHOD_MSTR where upper(instructional_method_name)=:instructional_method_name and id !=:id")
						.setParameter("instructional_method_name", td.getInstructional_method_name().toUpperCase())
//						.setParameter("refer_code", td.getRefer_code())
						.setParameter("id", id).uniqueResult();
				System.err.println("C----------------"+c);
				if (id == 0) {
					td.setInstructional_method_name(instructional_method_name);
//					td.setRefer_code(refer_code);
					td.setCreated_by(username);
					td.setCreated_role(role);
					td.setCreated_dt(date);
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

			return new ModelAndView("redirect:Instructional_MasterUrl");
		}
		

		@PostMapping("/getFilter_Instrucational_Method_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String instructional_method_name) {

			return idao.DataTable_Instrucational_Method_DataList(startPage, pageLength, Search, orderColunm, orderType, instructional_method_name);

		}

		@PostMapping("/getTotal_Instrucational_Method_dataCount")
		
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String instructional_method_name ) {
			
			return idao.DataTable_Instrucational_Method_DataTotalCount(Search, instructional_method_name);
			
		}
		
		
		// -------------------------SEARCH Battalion  -------------------------------------//
		 
		 @RequestMapping(value = "/admin/getSearch_Instructional_Method_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_Instructional_Method_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Instructional_method_name1", required = false) String Instructional_method_name1 ,String Instructional_method_name)  {
			
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
//			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
//			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid);	

			        Mmap.put("Instructional_method_name1", Instructional_method_name);
//					Mmap.put("Refer_code1", Refer_code);
					
					
					
					

				   return new ModelAndView("Instructional_Master_Tiles","Instructional_MasterCMD",new EDU_LEC_INSTRUCTION_METHOD_MSTR());
				   
			}
		 
		 
		 
		//==========================================EDIT Academic NAME========================================== 	
		
		
			@RequestMapping(value = "/Edit_Instructional_Methods_mstrUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Instructional_Methods_mstrUrl(@ModelAttribute("id15") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				/*
				 * if(request.getHeader("Referer") == null ) { sessionEdit.invalidate();
				 * Mmap.put("msg",
				 * "Suspicious Activity Detected,You have been logged out by Administrator");
				 * return new ModelAndView("redirect:/login"); }
				 */
//				String roleid1 = sessionEdit.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Edit_Instructional_Methods_mstrUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
				String role = sessionEdit.getAttribute("role").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_LEC_INSTRUCTION_METHOD_MSTR instructional_Method = idao.getinstructionalByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
//				Mmap.put("instructional_method_name", common.getInstructionalList(sessionFactory));
//				Mmap.put("refer_code", common.getAcademicList(sessionFactory,role));
				Mmap.put("instructional_Method", instructional_Method);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				Mmap.put("instructional_Methodinfo", idao.getinstructionalByid(Integer.parseInt(updateid)));
				Mmap.put("updateid", updateid);

				tx.commit();
				sessionHQL.close();

				return new ModelAndView("edit_Instructional_Master_Tiles");
			}
			//edit action
			@RequestMapping(value = "/edit_Instructional_Master_Action", method = RequestMethod.POST)
			public ModelAndView edit_Instructional_Master_Action(@ModelAttribute("edit_Instructional_MasterCMD") EDU_LEC_INSTRUCTION_METHOD_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
				
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				/*
				 * if(request.getHeader("Referer") == null ) { session.invalidate();
				 * model.put("msg",
				 * "Suspicious Activity Detected,You have been logged out by Administrator");
				 * return new ModelAndView("redirect:/login"); }
				 */
//				String roleid1 = session.getAttribute("roleid").toString();
				/*
				 * Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1); if(val ==
				 * false) { return new ModelAndView("AccessTiles"); }
				 */
					
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("updateid"));
				String instructional_method_name = request.getParameter("instructional_method_name");
//				String refer_code = request.getParameter("refer_code");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				
				if (instructional_method_name == null || instructional_method_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Instructional Method Name.");
					return new ModelAndView("redirect:Instructional_MasterUrl");
				}
				if (validation.maxlengthcheck100(instructional_method_name) == false) {
					ra.addAttribute("msg","Instructional Method Name "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Instructional_MasterUrl");
				}
				if (validation.isOnlyAlphabetDASH(instructional_method_name) == false) {
					ra.addAttribute("msg","Instructional Method Name "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Instructional_MasterUrl");
				}
//				if (refer_code.equals("") || refer_code.equals("null") || refer_code.equals(null)) {
//					ra.addAttribute("msg", "Please Enter Refer code.");
//					return new ModelAndView("redirect:Instructional_MasterUrl");
//				}
//				if (validation.maxlengthcheck3(refer_code) == false) {
//					ra.addAttribute("msg", "Refer code " + validation.MaxlengthcheckMSG3);
//					return new ModelAndView("redirect:Instructional_MasterUrl");
//				}
			
				
				
				try {
					Query q0 = session1.createQuery(
							
							"select count(id) from EDU_LEC_INSTRUCTION_METHOD_MSTR where instructional_method_name=:instructional_method_name and id!=:id");
							
					q0.setParameter("instructional_method_name", instructional_method_name);
//					q0.setParameter("refer_code", refer_code);
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
						
					if (c == 0) {
						String hql = "update EDU_LEC_INSTRUCTION_METHOD_MSTR set instructional_method_name=:instructional_method_name,modified_by=:modified_by,modified_dt=:modified_dt"
								+ " where id=:id";

						Query query = session1.createQuery(hql).setParameter("instructional_method_name", instructional_method_name).setParameter("modified_by", username).setParameter("modified_dt", new Date())
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
				return new ModelAndView("redirect:Instructional_MasterUrl");
				}
			
			//==========================================DELETE SYSTEM NAME========================================== 	
			 
			 
			@RequestMapping(value = "/delete_Url23", method = RequestMethod.POST)
			public ModelAndView delete_Url23(@ModelAttribute("id23") String id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Instructional_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				//				if(request.getHeader("Referer") == null ) { 
//					session1.invalidate();
//					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/login");
//				 }
//				String roleid1 = session1.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
					
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"delete from EDU_LEC_INSTRUCTION_METHOD_MSTR where id=:id")
							.setParameter("id", Integer.parseInt(id)).executeUpdate();

					
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
				return new ModelAndView("redirect:Instructional_MasterUrl");
			}


}

