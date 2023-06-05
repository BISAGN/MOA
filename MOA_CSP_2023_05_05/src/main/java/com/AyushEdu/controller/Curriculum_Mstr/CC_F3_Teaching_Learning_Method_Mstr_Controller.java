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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_F3_Teaching_Learning_Method_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_F3_Teaching_Learning_Method_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CC_F3_Teaching_Learning_Method_Mstr_Dao TLMdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/F3_Teaching_Learning_Method_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView F3_Teaching_Learning_Method_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("F3_Teaching_Learning_Method_Mstr_Url", roleid1);		
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

		return new ModelAndView("F3_Teaching_Learning_Method_Mstr_Tiles");
	}
	
	//==========================================SAVE/view/Edit Teaching Learning Method========================================== 	

	
			@PostMapping(value = "/F3_Teaching_Learning_MethodAction")
			public ModelAndView F3_Teaching_Learning_MethodAction(@Validated @ModelAttribute("F3_Teaching_Learning_MethodCMD") CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) {
//				SECURITY -- RIDDHI 	
				if(request.getHeader("Referer") == null ) { 
//					 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("F3_Teaching_Learning_Method_Mstr_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String status = request.getParameter("status");
				String method = request.getParameter("method");
				String system_id = request.getParameter("system_id");
				
				if (system_id.equals("0")) {
					ra.addAttribute("msg", "Please Select System");
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (method == null || method.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Method.");
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (validation.isAlphabetCDASH(method) == false) {
					ra.addAttribute("msg", " Method " + validation.isAlphabetWSCDASH);
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (validation.isNumerickavi(method) == true) {
					ra.addAttribute("msg", "Enter Valid Method. ");
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (validation.maxlengthcheck500(method) == false) {
					ra.addAttribute("msg","Method "+ validation.MaxlengthcheckMSG500);
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}
				if (status == "2"  || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
				}

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				String username = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR where system_id=:system_id and upper(method)=:method and status=:status and id !=:id")
							.setParameter("system_id", Integer.parseInt(system_id))
							.setParameter("method", td.getMethod().toUpperCase())
							.setParameter("status", td.getStatus())
							.setParameter("id", id).uniqueResult();
					
					if (id == 0) {
						td.setSystem_id(Integer.parseInt(system_id));
						td.setMethod(method);
						td.setCreated_by(username);
//						td.setCreated_role(role);
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
						td.setMethod(method.trim());
						td.setModified_by(username);
						td.setModified_date(date);
						if (c == 0) {
							td.setId(id);
							String msg = TLMdao.updateF3_Teaching_Learning_Method(td);
							
							ra.addAttribute("msg", "Data Updated Successfully.");
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
				
				return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
			}
			
			@PostMapping("/getFilterF3_Teaching_Learning_Method_data")

			public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String system_id, String method, String status,HttpSession session) {
				String role = session.getAttribute("role").toString();
				return TLMdao.DataTableF3_Teaching_Learning_MethodDataList(startPage, pageLength, Search, orderColunm, orderType,system_id, method,status,role);

			}

			@PostMapping("/getTotalF3_Teaching_Learning_Method_dataCount")
			public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,String system_id, String method, String status,HttpSession session) {
				String role = session.getAttribute("role").toString();
				return TLMdao.DataTableF3_Teaching_Learning_MethodDataTotalCount(Search, system_id,method,status,role);
				
			}
			
			@RequestMapping(value = "/F3_Teaching_Learning_Method_Mstr_Delete_Url", method = RequestMethod.POST)
			public ModelAndView F3_Teaching_Learning_Method_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
//				SECURITY -- RIDDHI 	
				if(request.getHeader("Referer") == null ) { 
//					session1.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("F3_Teaching_Learning_Method_Mstr_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
				return new ModelAndView("redirect:F3_Teaching_Learning_Method_Mstr_Url");
			}
}
