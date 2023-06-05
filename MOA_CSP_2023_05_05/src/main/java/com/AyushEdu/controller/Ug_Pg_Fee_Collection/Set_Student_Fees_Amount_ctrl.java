package com.AyushEdu.controller.Ug_Pg_Fee_Collection;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_SET_STU_FEES_CHILD;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_SET_STU_FEES_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Set_Student_Fees_Amount_Dao;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Set_Student_Fees_Amount_ctrl {

	@Autowired
	CommonController common;
	@Autowired
	Commondao commondao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired 
	Set_Student_Fees_Amount_Dao feeRDao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation;
		
	 
	
	
		@RequestMapping(value = "/set_student_fees_amount_Url", method = RequestMethod.GET)
		public ModelAndView set_student_fees_amount_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request){
		
				String role = session.getAttribute("role").toString();
				try {
					//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("set_student_fees_amount_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				Mmap.put("msg", msg);
				Mmap.put("gettermList",common.gettermListFee(sessionFactory));
				Mmap.put("d_name", common.getDegreeList(sessionFactory));
				Mmap.put("list", feeRDao.DataTable_CMEAttend_DataList("0", "0", "0","0",role));
				 } catch (Exception e) {
						e.printStackTrace();
					}
		
			return new ModelAndView("set_student_fees_amount_Tiles");
		}
	
		//-------------------------  Search Set Student Fees Amount   -------------------------------------//

		@RequestMapping(value = "/Search_set_student_fees_amount_Url", method = RequestMethod.POST)
			public ModelAndView Search_set_student_fees_amount_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "degree_name1", required = false) String degree_name,
					@RequestParam(value = "prof_id1", required = false) String prof_id,
					@RequestParam(value = "url1", required = false) String url1) 
		{
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("set_student_fees_amount_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}			
			String userId = session.getAttribute("userId").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
			String role = session.getAttribute("role").toString();
			
			 try {
			
			
			 		Mmap.put("msg", msg);
			 		Mmap.put("gettermList",common.gettermListFee(sessionFactory));
					Mmap.put("d_name", common.getDegreeList(sessionFactory));
					Mmap.put("degree_name1", degree_name);
					Mmap.put("prof_id1", prof_id);
					Mmap.put("institute_id",institute_id);
					Mmap.put("url1",url1);
					Mmap.put("list", feeRDao.DataTable_CMEAttend_DataList(userId, degree_name, prof_id,institute_id,role));
			 } catch (Exception e) {
					e.printStackTrace();
				}
				   return new ModelAndView ("set_student_fees_amount_Tiles");
		}	
	
		@PostMapping(value = "/ug_pg_retcollect_Action1")
		public ModelAndView ug_pg_retcollect_Action1(@Validated @ModelAttribute("ug_pg_Feecollect_CMD") EDU_LMS_SET_STU_FEES_PARENT td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra
				)throws IOException, ParseException {

			
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("set_student_fees_amount_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
		
			
			String degree = request.getParameter("degree_name");
			String prof_id= request.getParameter("prof_id");
			String institute_id= request.getParameter("institute_id");
			String nor = request.getParameter("no_of_record_hid");
			int parent_id=0;
			int child_id=0;

				
			
//			String status = request.getParameter("status");

//			if (system_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select System");
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
//			if (degree_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select Degree");
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
//			if (professional_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select professional");
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
//			if (course_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select Course");
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
			try {

				if (id == 0) {
					
					for(int i=1; i <= Integer.parseInt(nor); i++) {
					
						String stu_id = request.getParameter("stu_hid"+i);
						String system= request.getParameter("system_hid"+i);
						String part = request.getParameter("no_of_part_hid"+i);
						String total_fees = request.getParameter("total_fees"+i);
						if ((total_fees == null || total_fees.trim().equals("")) || validation.maxlengthcheck50(total_fees) == false) {
							ra.addAttribute("msg", "Please Enter Amount");
							System.err.println("435");
							return new ModelAndView("redirect:set_student_fees_amount_Url");
						}
						
						double fees = Math.round(Double.parseDouble(total_fees)/Double.parseDouble(part));
						
						td.setStu_id(Integer.parseInt(stu_id));
						td.setInstitute_id(Integer.parseInt(institute_id));
						td.setDegree(Integer.parseInt(degree));
						td.setProf_id(Integer.parseInt(prof_id));
						td.setSystem(Integer.parseInt(system));
						td.setTotal_fees(Double.parseDouble(total_fees));
						td.setStatus(0);
						td.setCreated_by(username);
						td.setCreated_date(date);
						
						parent_id = (int) sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						
						for(int j=1;j<=Integer.parseInt(part);j++) {
							
							EDU_LMS_SET_STU_FEES_CHILD od = new EDU_LMS_SET_STU_FEES_CHILD();
							
							od.setP_id(parent_id);
							od.setPart_ser(j);
							od.setAmount(fees);
							od.setStatus(0);
						 	od.setCreated_by(username);
						 	od.setCreated_date(date);
							
							child_id = (int) sessionHQL.save(od);
							sessionHQL.flush();
							sessionHQL.clear();
							
						}
						
				}
					if(parent_id > 0 && child_id > 0) {
						tx.commit();
						ra.addAttribute("msg", "Data Saved Successfully.");
					}
					
				}
			} catch (RuntimeException e) {
				try {
					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			 return new ModelAndView ("redirect:set_student_fees_amount_Url");
		}
		
		@PostMapping("/checkisdatasaved")
		public @ResponseBody long checkisdatasaved(String institute_id,String degree,String prof) {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Long count = (Long) sessionHQL.createQuery(
					"select count(*) from EDU_LMS_SET_STU_FEES_PARENT where institute_id=:institute_id and degree=:degree and prof_id=:prof_id")
					.setParameter("institute_id", Integer.parseInt(institute_id))
					.setParameter("degree", Integer.parseInt(degree))
					.setParameter("prof_id", Integer.parseInt(prof)).uniqueResult();
			tx.commit();
//			System.err.println("\n\n--------"+institute_id+"---"+degree+"---"+prof+"---"+count);
			return count;
		}
		
		@PostMapping("/updatefeesdata")
		public @ResponseBody String updatefeesdata(String stuid,String total_fees,String part,Principal principal) {
			
			String username = principal.getName();
			String msg = "";
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			Query q1 = sessionHQL.createQuery(
					"select cast(id as text) from EDU_LMS_SET_STU_FEES_PARENT where stu_id=:stu_id");
			q1.setParameter("stu_id", Integer.parseInt(stuid));
			List<String> list = (List<String>) q1.list();
			
			double fees = Math.round(Double.parseDouble(total_fees)/Double.parseDouble(part));
			if ((fees == 0)) {
				System.err.println("435");
				msg= "Please Enter Total Fees Amount";
				 return msg;
			}
			
			String hql = "update EDU_LMS_SET_STU_FEES_PARENT set total_fees=:total_fees,modified_by=:modified_by, \n"
					+" modified_date=:modified_date \n"
					+" where stu_id=:stu_id ";
			
			 Query query = sessionHQL.createQuery(hql)
					 	.setParameter("total_fees",Double.parseDouble(total_fees))
					 	.setParameter("modified_by", username)
					 	.setParameter("modified_date", new Date())
						.setParameter("stu_id", Integer.parseInt(stuid));
			 
			int tf = query.executeUpdate() > 0 ? 1 : 0;
			
			String hql2 = "update EDU_LMS_SET_STU_FEES_CHILD  set amount=:amount,modified_by=:modified_by, \n"
					+" modified_date=:modified_date \n"
					+" where p_id=:p_id ";
			
			Query query2 = sessionHQL.createQuery(hql2)
				 	.setParameter("amount",fees)
				 	.setParameter("modified_by", username)
				 	.setParameter("modified_date", new Date())
					.setParameter("p_id", Integer.parseInt(list.get(0)));
			
			int tf2 = query2.executeUpdate() > 0 ? 1 : 0;
			
			if(tf > 0 && tf2 >0) {
				tx.commit();
				msg = "Data Updated Successfully";
				
			}else {
				msg="Something went wrong !!!";
			}
			return msg;
		}
		
		@RequestMapping(value = "/getStuFees_ChildUrl", method = RequestMethod.POST)
		public @ResponseBody   List<Map<String, Object>> getStuFees_ChildUrl(String studentid,HttpServletRequest request, HttpSession session) {
			String userid1 = session.getAttribute("userId").toString();

			String role = session.getAttribute("rolename").toString();
			String role_id = session.getAttribute("roleid").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
			System.err.println("course_namegetStu_ChildUrl============"+studentid);
		 List<Map<String, Object>> list = feeRDao.getPopup_FeesChildDatalist(studentid,role,inst_id);
		 System.err.println("userid====================="+userid1);
			return list;
		}
		
		
		@PostMapping("/updaterowfeesdata")
		public @ResponseBody String updaterowfeesdata(String id,String amount,Principal principal) {
			
			String username = principal.getName();
			String msg = "";
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			
			double amountfees = Math.round(Double.parseDouble(amount));
			if ((amountfees == 0)) {
				System.err.println("435");
				msg= "Please Enter Amount";
				 return msg;
			}
			
			String hql2 = "update EDU_LMS_SET_STU_FEES_CHILD  set amount=:amount,modified_by=:modified_by, \n"
					+" modified_date=:modified_date \n"
					+" where id=:id ";
			
			Query query2 = sessionHQL.createQuery(hql2)
				 	.setParameter("amount",amountfees)
				 	.setParameter("modified_by", username)
				 	.setParameter("modified_date", new Date())
					.setParameter("id", Integer.parseInt(id));
			
			int tf2 = query2.executeUpdate() > 0 ? 1 : 0;
			
			if(tf2 ==1) {
				msg = "Data Updated Successfully";
				tx.commit();
			}else {
				msg="Something went wrong !!!";
			}
			return msg;
		}
		
		@PostMapping("/saverowfeesdata")
		public @ResponseBody String saverowfeesdata(String id,String amount,
				Principal principal,HttpServletRequest request, ModelMap model, HttpSession session, RedirectAttributes ra,
				@RequestParam(value = "msg", required = false) String msg) {
			
			String username = principal.getName();
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			
			String degree = request.getParameter("degree_hid");
			String prof_id= request.getParameter("prof_hid");
			String institute_id= request.getParameter("institute_id");
			System.err.println("institute_id=============="+institute_id);
			System.err.println("amount=============="+degree);
			String nor = request.getParameter("no_of_record_hid");
			int parent_id=0;
			int child_id=0;
			
//			double amountfees = Math.round(Double.parseDouble(amount));
			
//			String hql2 = "update EDU_LMS_SET_STU_FEES_PARENT  set amount=:amount,created_by=:created_by, \n"
//					+" created_date=:created_date \n"
//					+" where id=:id ";
//			
//			Query query2 = sessionHQL.createQuery(hql2)
//				 	.setParameter("amount",amountfees)
//				 	.setParameter("created_by", username)
//				 	.setParameter("created_date", new Date())
//					.setParameter("id", Integer.parseInt(id));
//			
//			int tf2 = query2.executeUpdate() > 0 ? 1 : 0;
//			
//			if(tf2 ==1) {
//				msg = "Data Saved Successfully";
//				tx.commit();
//			}else {
//				msg="Something went wrong !!!";
//			}
			
			
			try {
				EDU_LMS_SET_STU_FEES_PARENT td = new EDU_LMS_SET_STU_FEES_PARENT();
				
				int id1 = td.getId() > 0 ? td.getId() : 0;

				if (id1 == 0) {
					
					
					
						String stu_id = request.getParameter("id");
						String system= request.getParameter("system_hid");
						String part = request.getParameter("no_of_part_hid");
						String total_fees = request.getParameter("total_fees");
						System.err.println("system==================="+prof_id);
						Date date = new Date();
						
						double fees = Math.round(Double.parseDouble(total_fees)/Double.parseDouble(part));
						
						td.setStu_id(Integer.parseInt(stu_id));
						td.setInstitute_id(Integer.parseInt(institute_id));
						td.setDegree(Integer.parseInt(degree));
						td.setProf_id(Integer.parseInt(prof_id));
						td.setSystem(Integer.parseInt(system));
						td.setTotal_fees(Double.parseDouble(total_fees));
						td.setStatus(0);
						td.setCreated_by(username);
						td.setCreated_date(date);
						
						parent_id = (int) sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						
						for(int j=1;j<=Integer.parseInt(part);j++) {
							
							EDU_LMS_SET_STU_FEES_CHILD od = new EDU_LMS_SET_STU_FEES_CHILD();
							
							od.setP_id(parent_id);
							od.setPart_ser(j);
							od.setAmount(fees);
							od.setStatus(0);
						 	od.setCreated_by(username);
						 	od.setCreated_date(date);
							
							child_id = (int) sessionHQL.save(od);
							sessionHQL.flush();
							sessionHQL.clear();
							
						}
						
					if(parent_id > 0 && child_id > 0) {
						tx.commit();
						msg="Data Saved Successfully.";
//						ra.addAttribute("msg", "Data Saved Successfully.");
					}
					
				}
			} catch (RuntimeException e) {
				try {
					msg="roll back transaction";
//					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					msg="Couldn't roll back transaction"+rbe;
//					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return msg;
		}
	
}
