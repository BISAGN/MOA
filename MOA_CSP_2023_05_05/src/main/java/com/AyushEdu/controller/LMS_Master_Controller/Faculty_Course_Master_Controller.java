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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_COURSE_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Faculty_Course_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Faculty_Course_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Faculty_Course_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Faculty_Course_MasterUrl==========================================//

			@RequestMapping(value = "/Faculty_Course_MasterUrl", method = RequestMethod.GET)
				
				 	public ModelAndView Faculty_Course_MasterUrl(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Faculty_Course_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
					  Mmap.put("msg", msg);
					  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					  Mmap.put("type_degree_id", common.gettype_degree_id(sessionFactory));
					return new ModelAndView("Faculty_Course_MasterTiles");

				}

			//==========================================SAVE Faculty_Course_MasterUrl ========================================== 	

			
			@PostMapping(value = "/Faculty_Course_masterUrlaction")
			public ModelAndView Faculty_Course_masterUrlaction(@Validated @ModelAttribute("hms3") EDU_FACULTY_COURSE_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra,  String username  )  {
				
				
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Faculty_Course_MasterUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				
				System.err.println("hellllllllloooooooooooo");
				String Faculty_Course = request.getParameter("Faculty_Course");
				String type_of_degree = request.getParameter("type_of_degree");
				String status = request.getParameter("status");
				
		//	


				if (Faculty_Course == null || Faculty_Course.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Faculty_Course.");
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				}
				
				
				if (validation.isOnlyAlphabetDASH(Faculty_Course) == false) {
					ra.addAttribute("msg","Faculty Course "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				}
				
				if (Faculty_Course == null || Faculty_Course.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Faculty Course.");
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				}
				if (validation.maxlengthcheck50(Faculty_Course) == false) {
					ra.addAttribute("msg","Faculty Course "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				}
				if (type_of_degree == null || type_of_degree.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter type of degree.");
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				}
			
				if (type_of_degree == null || Faculty_Course.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter type of degree.");
					return new ModelAndView("redirect:Faculty_Course_MasterUrl");
				
				}
				
				//int id = td.getId() > 0 ? td.getId() : 0;

				

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				//try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_FACULTY_COURSE_MSTR where upper(course_name)=:course_name and status=:status")
							//.setParameter("id", td.getId())
							.setParameter("course_name",Faculty_Course.toUpperCase())
							.setParameter("status",Integer.parseInt(status))
							
						    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
							.uniqueResult();
				
							
					if (c == 0) {
						td.setCourse_name(Faculty_Course);
						td.setType_degree_id(Integer.parseInt(type_of_degree));
						td.setStatus(1);
						
						td.setCreated_by(username);
						td.setCreated_date(date);
						td.setModified_by(username);
						td.setModified_date(date);
//						if (c == 0) {
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");
//						} 
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
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}

		/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
		@PostMapping("/getFilterFaculty_Course_Master_data")

			public @ResponseBody List<Map<String, Object>> DataTableFaculty_Course_MasterDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String Faculty_Course,String type_of_degree,String status)
		{
				System.err.println("--ccc----Faculty_Course----aa-----"+Faculty_Course);
				return hdao.DataTableFaculty_Course_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Faculty_Course,type_of_degree,status);

		}

		@PostMapping("/getTotalFaculty_Course_Master_dataCount")
		public @ResponseBody long DataTableFaculty_Course_MasterDataTotalCount(HttpSession sessionUserId, String Search, String Faculty_Course,String type_of_degree,String status) {
				System.err.println("--ccc----Faculty_Course---------"+Faculty_Course);
				return hdao.DataTableFaculty_Course_MasterDataTotalCount(Search, Faculty_Course,type_of_degree,status);
			}

		/////////////////////////////////EDIT Faculty_Course_Master///////////////////////////////////////////
		@RequestMapping(value = "/Edit_Faculty_Course_MasterUrl", method = RequestMethod.POST)
		public ModelAndView Edit_Faculty_Course_MasterUrl(String id3, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {
			
			
			System.out.println("welcome=====================================");
//					Session sessionHQL = this.sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
			EDU_FACULTY_COURSE_MSTR Faculty_Course_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
					Mmap.addAttribute("msg", msg);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
					System.out.println("Faculty_Course_Master_details "+Faculty_Course_Master_details.getId());
					
					Mmap.put("Faculty_Course_Master_details", Faculty_Course_Master_details);
					Mmap.put("Faculty_Course_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
					Mmap.put("updateid",id3);
					  Mmap.put("type_degree_id", common.gettype_degree_id(sessionFactory));
//					tx.commit();
//					sessionHQL.close();
				
					return new ModelAndView("editFaculty_Course_MasterTiles");
		}

		@RequestMapping(value = "/edit_Faculty_Course_Master_action", method = RequestMethod.POST)
		public ModelAndView edit_Faculty_Course_Master_action(@ModelAttribute("edit_Faculty_Course_MasterCMD") EDU_FACULTY_COURSE_MSTR rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		{
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Faculty_Course_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String status = request.getParameter("status");
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("updateid")); 
			String Faculty_Course = request.getParameter("Faculty_Course");
			String type_of_degree = request.getParameter("type_of_degree");
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			if (Faculty_Course == null || Faculty_Course.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Faculty Course.");
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}
			if (validation.maxlengthcheck50(Faculty_Course) == false) {
				ra.addAttribute("msg","Faculty Course "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}
			if (validation.isOnlyAlphabetDASH(Faculty_Course) == false) {
				ra.addAttribute("msg","Faculty Course "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}
			
			if (type_of_degree == null || type_of_degree.trim().equals("0")) {
				ra.addAttribute("msg", "Please enter type of degree.");
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}
			if (validation.maxlengthcheck50(Faculty_Course) == false) {
				ra.addAttribute("msg","Faculty Course "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
			}
			try {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_FACULTY_COURSE_MSTR where course_name=:course_name and type_degree_id=:type_degree_id  and status=:status and  id !=:id");
				q0.setParameter("course_name", msg);
				q0.setParameter("course_name", Faculty_Course);
				q0.setParameter("type_degree_id",Integer.parseInt(type_of_degree));
				q0.setParameter("status",Integer.parseInt(status));
				
				
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
					String hql = "update EDU_FACULTY_COURSE_MSTR set course_name=:course_name,type_degree_id=:type_degree_id ,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

					Query query = session1.createQuery(hql).setParameter("course_name", Faculty_Course).setParameter("type_degree_id",Integer.parseInt(type_of_degree))
							.setParameter("modified_by", username)
							.setParameter("id", id)
							.setParameter("status", Integer.parseInt(status))
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
			
			return new ModelAndView("redirect:Faculty_Course_MasterUrl");
		}
		}


		// -------------------------SEARCH Battalion  -------------------------------------//

		@RequestMapping(value = "/admin/getSearch_Faculty_Course_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Faculty_Course_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "Faculty_Course", required = false) String Faculty_Course ,String Faculty_Course1)
		{
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("getSearch_Faculty_Course_Master", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
					Mmap.put("Faculty_Course", Faculty_Course1);
					
			

					return new ModelAndView("Faculty_Course_MasterTiles","SystemCMD2",new EDU_LMS_DEGREE_CATE_MSTR());
			   
		}
			
		////////////////////////////////delete Faculty_Course//////////////////////////////////////

//		@RequestMapping(value = "/delete_Faculty_Course_Action", method = RequestMethod.POST)
//		public ModelAndView delete_Faculty_Course_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
//				HttpServletRequest request, ModelMap model, HttpSession session1) {
//			
//					List<String> liststr = new ArrayList<String>();
//				
//					Session session = this.sessionFactory.openSession();
//					Transaction tx = session.beginTransaction();
//				
//					String username = session1.getAttribute("username").toString();
//					
	//
//					EDU_LMS_DEGREE_CATE_MSTR INF = (EDU_LMS_DEGREE_CATE_MSTR) session.get(EDU_LMS_DEGREE_CATE_MSTR.class, id);
	//
//					session.delete(INF);
//					session.flush();
//					session.clear();
//					
//					tx.commit();
//					session.close();
////					if (app > 0) {
//						liststr.add("Data Deleted Successfully.");
////					} else {
////						liststr.add("Data not Deleted.");
////					}
//					ra.addAttribute("msg", liststr.get(0));
//				/*} catch (Exception e) {
//					e.printStackTrace();
//					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//					ra.addAttribute("msg", liststr.get(0));
//					
//				}*/
//				return new ModelAndView("redirect:Faculty_Course_MasterUrl");
//			}
		@RequestMapping(value = "/delete_Faculty_Course_Action", method = RequestMethod.POST)
		public ModelAndView delete_Faculty_Course_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			if(request.getHeader("Referer") == null ) { 
			//	session1.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Faculty_Course_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
//			try {
	  
				int app = session.createQuery(
						"update EDU_FACULTY_COURSE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
//			} 
//			catch (Exception e) {
//				e.printStackTrace();
//				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//				ra.addAttribute("msg", liststr.get(0));
				
//			}
				
			return new ModelAndView("redirect:Faculty_Course_MasterUrl");
		}
}
