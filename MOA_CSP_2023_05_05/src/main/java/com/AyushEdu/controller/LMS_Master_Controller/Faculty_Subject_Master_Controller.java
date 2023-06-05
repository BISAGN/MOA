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
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Faculty_Subject_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Faculty_Subject_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Faculty_Subject_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Faculty_Subject_MasterUrl==========================================//

				@RequestMapping(value = "/Faculty_Subject_MasterUrl", method = RequestMethod.GET)
					
					 	public ModelAndView Faculty_Subject_MasterUrl(ModelMap Mmap, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
					
					//SECURITY RAHUL
					
					if(request.getHeader("Referer") == null ) { 
						// session.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Faculty_Subject_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
						  Mmap.put("msg", msg);
						  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						  Mmap.put("course_name", common.getfaculty_course_id(sessionFactory));
						return new ModelAndView("Faculty_Subject_MasterTiles");

					}

				//==========================================SAVE Faculty_Subject_MasterUrl ========================================== 	

				
				@PostMapping(value = "/Faculty_Subject_masterUrlaction")
				public ModelAndView Faculty_Subject_masterUrlaction(@Validated @ModelAttribute("hms3") EDU_FACULTY_SUBJECT_MSTR td, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra,  String username  )  {
					
					
					
					if(request.getHeader("Referer") == null ) { 
						// session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
							
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Faculty_Subject_MasterUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					
					
					System.err.println("hellllllllloooooooooooo");
					String Faculty_Subject = request.getParameter("Faculty_Subject");
					String fac_course_id = request.getParameter("fac_course_id");
					String status = request.getParameter("status");
					
			//	


					if (Faculty_Subject == null || Faculty_Subject.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Faculty Subject.");
						return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
					}
					
					
					if (validation.isOnlyAlphabetDASH(Faculty_Subject) == false) {
						ra.addAttribute("msg","Faculty Subject "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
					}
					
					if (Faculty_Subject == null || Faculty_Subject.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Faculty Subject.");
						return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
					}
					if (validation.maxlengthcheck50(Faculty_Subject) == false) {
						ra.addAttribute("msg","Faculty Subject "+ validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
					}
					
					if (fac_course_id == null || fac_course_id.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Faculty Course.");
						return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
					}
					
					//int id = td.getId() > 0 ? td.getId() : 0;

					

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					//try {
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_FACULTY_SUBJECT_MSTR where upper(subject_name)=:subject_name and status=:status")
								//.setParameter("id", td.getId())
								.setParameter("subject_name",Faculty_Subject.toUpperCase())
								.setParameter("status",Integer.parseInt(status))
							    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
								.uniqueResult();
					
								
						if (c == 0) {
							td.setSubject_name(Faculty_Subject);
							td.setFac_course_id(Integer.parseInt(fac_course_id));
							td.setStatus(1);
							
							td.setCreated_by(username);
							td.setCreated_date(date);
							td.setModified_by(username);
							td.setModified_date(date);
//							if (c == 0) {
								sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();
								ra.addAttribute("msg", "Data Saved Successfully.");
//							} 
//							
						}else {
						ra.addAttribute("msg", "Data already Exist.");
				}

						tx.commit();

						/*
						 * } catch (RuntimeException e) { try {
						 * 
						 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
						 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
						 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
						 */

						sessionHQL.close();
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}

			/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
			@PostMapping("/getFilterFaculty_Subject_Master_data")

				public @ResponseBody List<Map<String, Object>> DataTableFaculty_Subject_MasterDataList(int startPage, int pageLength, String Search,
					String orderColunm, String orderType,String Faculty_Subject,String fac_course_id,String status)
			{
					System.err.println("--ccc----Faculty_Subject----aa-----"+Faculty_Subject);
					return hdao.DataTableFaculty_Subject_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Faculty_Subject,fac_course_id,status);

			}

			@PostMapping("/getTotalFaculty_Subject_Master_dataCount")
			public @ResponseBody long DataTableFaculty_Subject_MasterDataTotalCount(HttpSession sessionUserId, String Search, String Faculty_Subject,String fac_course_id,String status) {
					System.err.println("--ccc----Faculty_Subject---------"+Faculty_Subject);
					return hdao.DataTableFaculty_Subject_MasterDataTotalCount(Search, Faculty_Subject,fac_course_id,status);
				}

			/////////////////////////////////EDIT Faculty_Subject_Master///////////////////////////////////////////
			@RequestMapping(value = "/Edit_Faculty_Subject_MasterUrl", method = RequestMethod.POST)
			public ModelAndView Edit_Faculty_Subject_MasterUrl(String id3, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				System.out.println("welcome=====================================");
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
				EDU_FACULTY_SUBJECT_MSTR Faculty_Subject_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
						Mmap.addAttribute("msg", msg);
						System.out.println("Faculty_Subject_Master_details "+Faculty_Subject_Master_details.getId());
						
						Mmap.put("Faculty_Subject_Master_details", Faculty_Subject_Master_details);
						Mmap.put("Faculty_Subject_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
						Mmap.put("updateid",id3);
						 Mmap.put("course_name", common.getfaculty_course_id(sessionFactory));
						  
//						tx.commit();
//						sessionHQL.close();
					
						return new ModelAndView("editFaculty_Subject_MasterTiles");
			}

			@RequestMapping(value = "/edit_Faculty_Subject_Master_action", method = RequestMethod.POST)
			public ModelAndView edit_Faculty_Subject_Master_action(@ModelAttribute("edit_Faculty_Subject_MasterCMD") EDU_FACULTY_SUBJECT_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			{
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Faculty_Subject_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("updateid")); 
				String Faculty_Subject = request.getParameter("Faculty_Subject");
				String fac_course_id = request.getParameter("fac_course_id");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (Faculty_Subject == null || Faculty_Subject.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Faculty Subject.");
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				if (validation.maxlengthcheck50(Faculty_Subject) == false) {
					ra.addAttribute("msg","Faculty Subject "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				if (validation.isOnlyAlphabetDASH(Faculty_Subject) == false) {
					ra.addAttribute("msg","Faculty Subject "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				
				if (Faculty_Subject == null || Faculty_Subject.trim().equals("0")) {
					ra.addAttribute("msg", "Please enter Faculty Subject.");
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				if (validation.maxlengthcheck50(Faculty_Subject) == false) {
					ra.addAttribute("msg","Faculty Subject "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				if (fac_course_id == null || fac_course_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Faculty Course.");
					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
				}
				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_FACULTY_SUBJECT_MSTR where subject_name=:subject_name and fac_course_id=:fac_course_id and id !=:id");
					q0.setParameter("subject_name", msg);
					q0.setParameter("subject_name", Faculty_Subject);
					q0.setParameter("fac_course_id",Integer.parseInt(fac_course_id));
					
					
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
					
					if (c == 0) {
						String hql = "update EDU_FACULTY_SUBJECT_MSTR set subject_name=:subject_name,fac_course_id=:fac_course_id ,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

						Query query = session1.createQuery(hql).setParameter("subject_name", Faculty_Subject).setParameter("fac_course_id",Integer.parseInt(fac_course_id))
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
				}
				
				finally {
					if (session1 != null) {
						session1.close();
					}
				}
				
				return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
			}
			}


			// -------------------------SEARCH Battalion  -------------------------------------//

			@RequestMapping(value = "/admin/getSearch_Faculty_Subject_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_Faculty_Subject_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Faculty_Subject", required = false) String Faculty_Subject ,String Faculty_Subject1)
			{
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("getSearch_Faculty_Subject_Master", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
						Mmap.put("Faculty_Subject", Faculty_Subject1);
						
				

						return new ModelAndView("Faculty_Subject_MasterTiles","SystemCMD2",new EDU_LMS_DEGREE_CATE_MSTR());
				   
			}
				
			////////////////////////////////delete Faculty_Subject//////////////////////////////////////

//			@RequestMapping(value = "/delete_Faculty_Subject_Action", method = RequestMethod.POST)
//			public ModelAndView delete_Faculty_Subject_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
//					HttpServletRequest request, ModelMap model, HttpSession session1) {
//				
//						List<String> liststr = new ArrayList<String>();
//					
//						Session session = this.sessionFactory.openSession();
//						Transaction tx = session.beginTransaction();
//					
//						String username = session1.getAttribute("username").toString();
//						
		//
//						EDU_LMS_DEGREE_CATE_MSTR INF = (EDU_LMS_DEGREE_CATE_MSTR) session.get(EDU_LMS_DEGREE_CATE_MSTR.class, id);
		//
//						session.delete(INF);
//						session.flush();
//						session.clear();
//						
//						tx.commit();
//						session.close();
////						if (app > 0) {
//							liststr.add("Data Deleted Successfully.");
////						} else {
////							liststr.add("Data not Deleted.");
////						}
//						ra.addAttribute("msg", liststr.get(0));
//					/*} catch (Exception e) {
//						e.printStackTrace();
//						liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//						ra.addAttribute("msg", liststr.get(0));
//						
//					}*/
//					return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
//				}
			@RequestMapping(value = "/delete_Faculty_Subject_Action", method = RequestMethod.POST)
			public ModelAndView delete_Faculty_Subject_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

				if(request.getHeader("Referer") == null ) { 
				//	session1.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Faculty_Subject_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
//				try {
		  
					int app = session.createQuery(
							"update EDU_FACULTY_SUBJECT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
					
				return new ModelAndView("redirect:Faculty_Subject_MasterUrl");
			}
}
