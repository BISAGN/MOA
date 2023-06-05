package com.AyushEdu.controller.LMS_Master_Controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Subject_CD_DAO;
import com.AyushEdu.Core_Directory.System_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_STATE_LOGO_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_STUDENT_INTERN_RULES;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.SystemDAO;
import com.AyushEdu.dao.LMS_Master.TermDao;
import com.AyushEdu.dao.LMS_Master.studentintern_ruleDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Student_Intern_Rules_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	studentintern_ruleDAO sidao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory2;

	@Autowired
	 ValidationController validation;
	
	
	@RequestMapping(value = "/student_intern_Rule_URL", method = RequestMethod.GET)
	public ModelAndView student_intern_Rule_URL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
//		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("student_intern_tiles");

	}
	
	
	 @RequestMapping(value = "/student_intern_rule_Action",method=RequestMethod.POST)
		public ModelAndView student_intern_rule_Action(@ModelAttribute("studentinternCMD") EDU_LMS_STUDENT_INTERN_RULES tt, 
				BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra) throws IOException,ParseException {
			
//		 if(request.getHeader("Referer") == null ) { 
//			// session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("statelogo_mstr", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
	 
				int id = tt.getId() > 0 ? tt.getId() : 0;
				
				System.err.println("\n\n id------------------------------"+id);
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String role_name = request.getParameter("role_name").trim();
				String year = request.getParameter("year").trim();
				String month = request.getParameter("month").trim();
				
				 
				System.err.println("rolde name"+role_name);

				System.err.println("year"+year);

				System.err.println("month"+month);
				
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
							 
				 	if (role_name == null || role_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Role Name.");
							return new ModelAndView("redirect:student_intern_Rule_URL");
					}
					
					
					if (validation.isOnlyAlphabetDASH(role_name) == false) {
						ra.addAttribute("msg","role_name "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:student_intern_Rule_URL");
					}
					
					
					if (validation.maxlengthcheck50(role_name) == false) {
						ra.addAttribute("msg","role_name "+ validation.MaxlengthcheckMSG50);
						return new ModelAndView("");
					}
					if (year == null || year.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter year.");
						return new ModelAndView("redirect:student_intern_Rule_URL");
					}
//					if (validation.isOnlyNumer(year) == false) {
//						ra.addAttribute("msg","year "+ validation.isOnlyNumerMSG);
//						return new ModelAndView("redirect:student_intern_Rule_URL");
//					}
					if (month == null || month.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter month.");
						return new ModelAndView("redirect:student_intern_Rule_URL");
					}
//					if (validation.isOnlyMonth(month) == false) {
//						ra.addAttribute("msg","month "+ validation.isOnlyMonthMSG);
//						return new ModelAndView("redirect:student_intern_Rule_URL");
//					}
					try{
					
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from EDU_LMS_STUDENT_INTERN_RULES where role_name=:role_name and year=:year and month=:month and status=:status  and id !=:id")
								.setParameter("role_name", tt.getRole_name())
								.setParameter("year", tt.getYear())
								.setParameter("month", tt.getMonth())
								.setParameter("status", tt.getStatus())
								.setParameter("id", id).uniqueResult();

					if (id == 0) {
						if (year != null && !year.equals("")) {
							tt.setYear(Integer.parseInt(year));
						}
						
						if (month != null && !month.equals("")) {
							tt.setMonth(Integer.parseInt(month));
						}
						tt.setCreated_by(username);
						tt.setCreated_date(date);
						if (c == 0) {
							sessionHQL.save(tt);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					} else {
						 
						tt.setModified_by(username);
						tt.setModified_date(date);
					 
						if (c == 0) {
//							tt.setId(id);
//							String msg = Cdao.updateStudentLogo(tt);
							ra.addAttribute("msg","Data Updated Succesfully.");
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					tx.commit();
				}catch(RuntimeException e){
					e.printStackTrace();
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:student_intern_Rule_URL");
			}
	 
 
	 
		
		@PostMapping("/getFiltersystem_rules_data3")
		public @ResponseBody List<Map<String, Object>> getFiltersystem_rules_data3(int startPage, int pageLength,

				 String orderColunm, String orderType,String Search, String role_name,String year,String month, String status) {
			return sidao.DataTablesystemDataListRule(startPage, pageLength, orderColunm, orderType,Search,  role_name, year, month,status);
		}
		@PostMapping("/getTotalsystem_rule_dataCount")
		public @ResponseBody long getTotalsystem_rule_dataCount(HttpSession sessionUserId, String Search, String role_name,String year,String month) {
			return sidao.DataTablesystemDataTotalCountRule(Search,role_name, year, month);
			
		}
		
		/////////////////////////////////EDIT Master///////////////////////////////////////////
		@RequestMapping(value = "/Edit_student_intern_rule", method = RequestMethod.POST)
		public ModelAndView Edit_student_intern_rule(String id2, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {
			System.out.println("welcome=====================================");
			
			
		
			
			
//					Session sessionHQL = this.sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
					EDU_LMS_STUDENT_INTERN_RULES student_intern_Rule_details = sidao.getsystemByid(Integer.parseInt(id2));
					Mmap.addAttribute("msg", msg);
					  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					System.out.println("student_intern_Rule_details "+student_intern_Rule_details.getId());
					
					Mmap.put("student_intern_Rule_details", student_intern_Rule_details);
					Mmap.put("student_intern_Ruleinfo", sidao.getsystemByid(Integer.parseInt(id2)));
					Mmap.put("updateid",id2);
//					tx.commit();
//					sessionHQL.close();
				
					return new ModelAndView("editstudent_internTiles");
		}

		@RequestMapping(value = "/edit_student_intern_rule_action", method = RequestMethod.POST)
		public ModelAndView edit_student_intern_rule_action(@ModelAttribute("edit_student_intern_ruleCMD") EDU_LMS_STUDENT_INTERN_RULES rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		{
			
//			if(request.getHeader("Referer") == null ) { 
//			//	session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//				
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Attachment_MasterUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			int id = Integer.parseInt(request.getParameter("updateid")); 
			String status = request.getParameter("status");
			String username = session.getAttribute("username").toString();
			String role_name = request.getParameter("role_name").trim();
			String year = request.getParameter("year").trim();
			String month = request.getParameter("month").trim();

			
			
			if (role_name == null || role_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Role Name.");
					return new ModelAndView("redirect:student_intern_Rule_URL");
			}
			
			
			if (validation.isOnlyAlphabetDASH(role_name) == false) {
				ra.addAttribute("msg","role_name "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:student_intern_Rule_URL");
			}
			
			
			if (validation.maxlengthcheck50(role_name) == false) {
				ra.addAttribute("msg","role_name "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("");
			}
//			if (year == null || year.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter year.");
//				return new ModelAndView("redirect:student_intern_Rule_URL");
//			}
//			if (validation.isOnlyNumer(year) == false) {
//				ra.addAttribute("msg","year "+ validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:student_intern_Rule_URL");
//			}
//			if (month == null || month.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter month.");
//				return new ModelAndView("redirect:student_intern_Rule_URL");
//			}
//			if (validation.isOnlyMonth(month) == false) {
//				ra.addAttribute("msg","month "+ validation.isOnlyMonthMSG);
//				return new ModelAndView("redirect:student_intern_Rule_URL");
//			}
			
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			try {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_LMS_STUDENT_INTERN_RULES where role_name=:role_name and year=:year and month=:month and status=:status and id !=:id");
				q0.setParameter("role_name", msg);
				q0.setParameter("role_name", role_name);
				q0.setParameter("year",Integer.parseInt(year) );
				q0.setParameter("month", Integer.parseInt(month));
				q0.setParameter("status",status);
				
				
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
					String hql = "update EDU_LMS_STUDENT_INTERN_RULES set role_name=:role_name , year=:year, month=:month,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

					Query query = session1.createQuery(hql).setParameter("role_name", role_name).setParameter("year", Integer.parseInt(year)).setParameter("month",  Integer.parseInt(month))
							.setParameter("status", status)
							.setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("id", id);
						
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();
					 //For Core Directory
//    				sidao.Update_Attachment_Mstr_Data( id,role_name , Integer.parseInt(status),  username,  new Date());
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
			
			return new ModelAndView("redirect:student_intern_Rule_URL");
		}
		}
		////////////////////////////////delete //////////////////////////////////////


		@RequestMapping(value = "/delete_student_intern_rule", method = RequestMethod.POST)
		public ModelAndView delete_student_intern_rule(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
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
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
//			try {
	  
				int app = session.createQuery(
						"update EDU_LMS_STUDENT_INTERN_RULES set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

				
				tx.commit();
				//For Core Directory
//				sidao.Delete_Attachment_Mstr_Data(id);
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
				
			return new ModelAndView("redirect:student_intern_Rule_URL");
		}



	 
}
