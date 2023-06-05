package com.AyushEdu.controller.LMS_Student;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDENT_PAYMENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;
import com.AyushEdu.dao.LMS_Student.PaymentDao;
import com.AyushEdu.dao.Question_Bank.Exam_Paper_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class PaymentController {
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	PaymentDao pdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Payment_Url", method = RequestMethod.GET)
	public ModelAndView Payment_Url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "id", required = false) String id ,@RequestParam(value = "course_fee", required = false) String course_fee ,
			HttpServletRequest request) {
		
		
		//SECURITY RAHUL
		
//		if(request.getHeader("Referer") == null ) { 
//			// session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//				
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Payment_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		
		 Mmap.put("msg", msg);
		 Mmap.put("id", id);
		 Mmap.put("course_fee", course_fee);
//		 Mmap.put("cid", cid);
		 Mmap.put("getSetList", common.getSetList(sessionFactory));
		 Mmap.put("course_name_list", common.getCourseNamelist(sessionFactory));
		 Mmap.put("module_name_list", common.getModuleName(sessionFactory));
		 
		return new ModelAndView("Payment_Tiles");
	}
	
	 @RequestMapping(value = "/PaymentAction", method = RequestMethod.POST)
	 public @ResponseBody ModelAndView PaymentAction(@Validated @ModelAttribute("PaymentCMD") EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT td  , BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {
		 
		 
		 
		//SECURITY RAHUL
			
//			if(request.getHeader("Referer") == null ) { 
//				// session.invalidate();
//				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//					
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Payment_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
		 
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 List<String> liststr = new ArrayList<String>();
		 Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			String id=request.getParameter("enroll_pid");
			
		 try {
			 
				String hqlUpdatechild ="update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT set payment_status='1' where id=:id";
				int appchild = sessionHQL.createQuery(hqlUpdatechild)
//						.setInteger("userid",Integer.parseInt(userid))
						.setInteger("id",Integer.parseInt(id))
						.executeUpdate();
				
				tx.commit();
				if ( appchild > 0) {
					liststr.add("Your payment has been successfully done.");
//					ra.addAttribute("msg", " Payment  Successfully.");
				}
//				else {
//					liststr.add("Exit from Course UnSuccessfully");
//				}
				ra.addAttribute("msg", liststr.get(0));
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					ra.addAttribute("msg", "Roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		 return new ModelAndView("redirect:Stud_Elect_Courses_Url");
	}

	
	 @RequestMapping(value = "/getsetByModule", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getsetByModule(String set_id) {
		   ArrayList<ArrayList<String>> list = pdao.getsetByModule(set_id);
			
			return list;
		}
	 
	 
	 @RequestMapping(value = "/getCourse_Set_payment", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getCourse_Set_payment(String p_id,HttpSession session) {
		
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 List<ArrayList<String>> list = pdao.GetCourse_Set_payment(p_id,userid);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getModule_fetch_payment", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getModule_fetch_payment(String course_id) {
	    	
		 List<ArrayList<String>> list = pdao.GetModule_fetch_payment(course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getcourse_fee", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getcourse_fee(String course_id) {
	    
		 List<ArrayList<String>> list = pdao.Getcourse_fee(course_id);	 
			return list;
		}
	 
	 
	 
}