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
import com.AyushEdu.Core_Directory.Course_Publisher_Act_Inact_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_PUBLISHER_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Course_Publisher_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Course_Publisher_Mstr_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	Course_Publisher_Mstr_DAO CPdao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Course_Publisher_Act_Inact_CD_DAO CP_dir_dao;
	
	//==========================================OPEN PAGE GENDER========================================== 
	
		@RequestMapping(value = "/course_publisher_url", method = RequestMethod.GET)
		public ModelAndView course_publisher_url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			//SECURITY RAHUL
			
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("course_publisher_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		   Mmap.put("msg", msg);

			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("course_publisher_Tiles");
		}
		
		//==========================================SAVE SYSTEM NAME========================================== 	
		
			@PostMapping(value = "/course_publisherAction")
			public ModelAndView course_publisherAction(@Validated @ModelAttribute("course_publisherCMD") EDU_LMS_COURSE_PUBLISHER_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) {

				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("course_publisher_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String name = request.getParameter("name");
				String status = request.getParameter("status");

				if (name == null || name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Publisher.");
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (validation.maxlengthcheck50(name) == false) {
					ra.addAttribute("msg","Publisher "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (validation.isOnlyAlphabetDASH(name) == false) {
					ra.addAttribute("msg","Publisher "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:course_publisher_url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:course_publisher_url");
				}

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				String username = principal.getName();
//				String system_name = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_LMS_COURSE_PUBLISHER_MSTR where upper(name)=:name and upper(status)=:status and id !=:id")
							.setParameter("name", td.getName().toUpperCase())
							.setParameter("status", td.getStatus().toUpperCase())
							.setParameter("id", id).uniqueResult();
					int idd =0;
					if (id == 0) {
						td.setName(name);
						td.setCreated_by(username);
						td.setCreated_date(date);
						if (c == 0) {
							 idd = (int)sessionHQL.save(td);
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}

					tx.commit();
					//For Core Directory
					CP_dir_dao.Insert_Course_Publisher_Act_Inact_Data(idd);
					System.err.println("---===idd"+idd);
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

				return new ModelAndView("redirect:course_publisher_url");
			}
			
			
			@PostMapping("/getFiltercourse_publisher_data")
			public @ResponseBody List<Map<String, Object>> getFiltercourse_publisher_data(int startPage, int pageLength,

					String Search, String orderColunm, String orderType, String name, String status) {

				return CPdao.DataTablecourse_publisherDataList(startPage, pageLength, Search, orderColunm, orderType,name,status);

			}

			@PostMapping("/getTotalcourse_publisher_dataCount")
			public @ResponseBody long getTotalcourse_publisher_dataCount(HttpSession sessionUserId, String Search, String name,String status) {
				return CPdao.DataTablecourse_publisherDataTotalCount(Search, name, status);
				
			}
			
			//-----edit

			@RequestMapping(value = "/Edit_course_publisherUrl", method = RequestMethod.POST)
			public ModelAndView Edit_course_publisherUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_LMS_COURSE_PUBLISHER_MSTR Course_Publisher_Details = CPdao.getCourse_PublisherByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
				Mmap.put("Course_Publisher_Details", Course_Publisher_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				Mmap.put("systeminfo", CPdao.getCourse_PublisherByid(Integer.parseInt(updateid)));
				
				tx.commit();
				sessionHQL.close();

				return new ModelAndView("Edit_Course_Publisher_Tiles");
			}
			
			//edit action
			@RequestMapping(value = "/Edit_course_publisherAction", method = RequestMethod.POST)
			public ModelAndView Edit_course_publisherAction(@ModelAttribute("Edit_course_publisherCMD") EDU_LMS_COURSE_PUBLISHER_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("course_publisher_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String username = session.getAttribute("username").toString();

				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name").trim();
				String status = request.getParameter("status");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (name == null || name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Publisher.");
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (validation.maxlengthcheck50(name) == false) {
					ra.addAttribute("msg","Publisher "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (validation.isOnlyAlphabetDASH(name) == false) {
					ra.addAttribute("msg","Publisher "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:course_publisher_url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:course_publisher_url");
				}
				
				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_LMS_COURSE_PUBLISHER_MSTR where name=:name and status=:status and id !=:id");
					q0.setParameter("name", name);

					q0.setParameter("status", status);

					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						String hql = "update EDU_LMS_COURSE_PUBLISHER_MSTR set name=:name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";

						Query query = session1.createQuery(hql).setParameter("name",name).setParameter("status", status)
								.setParameter("modified_by", username).setParameter("modified_date", new Date())
								.setParameter("id", id);
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						//For Core Directory
						CP_dir_dao.  Update_Course_Publisher_Act_Inact_Data( id, name,status,username,new Date());
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
				
				return new ModelAndView("redirect:course_publisher_url");
			}
	
///////////////////////////////////Delete
			@PostMapping(value = "/deleteCourse_publisherUrl")
			public ModelAndView deleteCourse_publisherUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {


				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update EDU_LMS_COURSE_PUBLISHER_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

					tx.commit();
					CP_dir_dao. Delete_Course_Publisher_Act_Inact_Data(id);  
					session.close();
					if (app > 0) {
						liststr.add("Data Deleted Successfully.");
					} else {
						liststr.add("Data not Deleted.");
					}
					ra.addAttribute("msg", liststr.get(0));
				} catch (Exception e) {
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					ra.addAttribute("msg", liststr.get(0));
				}
				return new ModelAndView("redirect:course_publisher_url");
			}
			
}
