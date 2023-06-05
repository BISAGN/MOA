package com.AyushEdu.controller.Curriculum_Mstr;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_EVALUATION_METHOD_PA_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.Evaluation_method_pa_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Evaluation_method_pa_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Evaluation_method_pa_Mstr_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Evaluation_method_pa_Url", method = RequestMethod.GET)
	public ModelAndView Evaluation_method_pa_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Evaluation_method_pa_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		String role = session.getAttribute("role").toString();	
		 Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Evaluation_method_pa_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/Evaluation_method_paAction")
		public ModelAndView Evaluation_method_paAction(@Validated @ModelAttribute("Evaluation_method_paCMD") EDU_CC_TB_EVALUATION_METHOD_PA_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Evaluation_method_pa_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String evaluation_method_pa = request.getParameter("evaluation_method_pa");
			String status = request.getParameter("status");
			String system_id = request.getParameter("system_id");
			
			if (system_id.equals("0")) {
				ra.addAttribute("msg", "Please Select System");
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			if (evaluation_method_pa == null || evaluation_method_pa.equals("")) {
				ra.addAttribute("msg", "Please Enter Evaluation Method .");
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			if (validation.isAlphabetCDASH(evaluation_method_pa) == false) {
				ra.addAttribute("msg", "Evaluation Method " + validation.isAlphabetWSCDASH);
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			
			if (validation.isNumerickavi(evaluation_method_pa) == true) {
				ra.addAttribute("msg", "Enter Valid Evaluation Method. ");
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			
			if (validation.maxlengthcheck500(evaluation_method_pa) == false) {
				ra.addAttribute("msg","Evaluation Method  "+ validation.MaxlengthcheckMSG500);
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			if (status == "2"  || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Evaluation_method_pa_Url");
			}
			int id =Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_CC_TB_EVALUATION_METHOD_PA_MSTR where system_id=:system_id and upper(evaluation_method_pa)=:evaluation_method_pa and status=:status and id !=:id")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("evaluation_method_pa", td.getEvaluation_method_pa().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					td.setSystem_id(Integer.parseInt(system_id));
					td.setEvaluation_method_pa(evaluation_method_pa);
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
					td.setSystem_id(Integer.parseInt(system_id));
					td.setEvaluation_method_pa(evaluation_method_pa.trim());
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Pdao.updateEvaluation_method_pa(td);
						if (msg == "Data Updated Successfully") {
							model.put("msg", msg);
							model.put("Nmsg", "0");
						} else {
							model.put("msg", msg);
							model.put("Nmsg", "1");
						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						model.put("msg", "Data already Exist");
						model.put("Nmsg", "1");
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
			
			return new ModelAndView("redirect:Evaluation_method_pa_Url");
		}
		
		@PostMapping("/getFilterEvaluation_method_pa_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String system_id,String evaluation_method_pa, String status,HttpSession session) {
			String role = session.getAttribute("role").toString();
			return Pdao.DataTableEvaluation_method_paDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,evaluation_method_pa,status,role);

		}

		@PostMapping("/getTotalEvaluation_method_pa_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,String system_id, String evaluation_method_pa,String status,HttpSession session) {
			String role = session.getAttribute("role").toString();
			return Pdao.DataTableEvaluation_method_paDataTotalCount(Search,system_id, evaluation_method_pa,status,role);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_Evaluation_method_pa_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Evaluation_method_pa_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "system_id", required = false) String system_id,
						@RequestParam(value = "evaluation_method_pa", required = false) String evaluation_method_pa,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						sessionEdit.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Evaluation_method_pa_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_CC_TB_EVALUATION_METHOD_PA_MSTR Evaluation_method_pa_Details = Pdao.getEvaluation_method_paByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("evaluation_method_pa", evaluation_method_pa);
					Mmap.put("status", status);
					Mmap.put("Evaluation_method_pa_Details", Evaluation_method_pa_Details);
					Mmap.put("getSystemList", common.getSystemList(sessionFactory,roleid1));
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("Evaluation_method_painfo", Pdao.getEvaluation_method_paByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("Evaluation_method_pa_Tiles");
				}
				
				@RequestMapping(value = "/Evaluation_method_pa_Mstr_Delete_Url", method = RequestMethod.POST)
				public ModelAndView Evaluation_method_pa_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {

					if(request.getHeader("Referer") == null ) { 
//						session1.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Evaluation_method_pa_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"update EDU_CC_TB_EVALUATION_METHOD_PA_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", id).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();

						
						tx.commit();
						session.close();
						if (app > 0) {
							System.err.println("check delete"+(app > 0));
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
					return new ModelAndView("redirect:Evaluation_method_pa_Url");
				}

}
