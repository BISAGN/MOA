package com.AyushEdu.controller.Time_Table;

import java.math.BigInteger;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.Time_Table.EDU_TT_FACULTY_TO_COURSE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.Time_Table.FacultytoCourseDAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Faculty_to_Course_Master_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private FacultytoCourseDAO ftcdao;
	@Autowired
	CommonController common;
	
	@Autowired
	Sys_Deg_Map_Inst_DAO  dmdao;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;


	//==========================================OPEN PAGE TIMETABLE========================================== 
	
	@RequestMapping(value = "/Faculty_to_CourseUrl", method = RequestMethod.GET)
	public ModelAndView Faculty_to_CourseUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			/* String roleid1 = session.getAttribute("roleid").toString(); */
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("system", dmdao.Getsytemid_fetch(userid));
			Mmap.put("msg", msg);
			Mmap.put("getDegreeList", getDegreeList());
	//		 Mmap.put("getCourseList", getCourseList());
	////		 Mmap.put("getFacultyList", getFacultyList(sessionFactory));
			String userId = session.getAttribute("userId").toString();
			Mmap.put("getFacultyList",  ftcdao.getFacultyData(userId));
			Mmap.put("getProfessionalList", getProfessionalList());
		

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("faculty_to_course_master_Tiles");

	}
	
	//==========================================SAVE TIMETABLE NAME========================================== 	

	
	@PostMapping(value = "/Faculty_to_Course_Master_action")
	public ModelAndView Faculty_to_Course_Master_action(@Validated @ModelAttribute("Faculty_to_Course_Master_action") EDU_TT_FACULTY_TO_COURSE_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		
		String degree = request.getParameter("degree");
		String course = request.getParameter("course");
		String faculty = request.getParameter("faculty");
		String professional = request.getParameter("professional");

		if (degree == null || degree.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Degree.");
			return new ModelAndView("redirect:Faculty_to_CourseUrl");
		}
		if (validation.maxlengthcheck100( request.getParameter("degree")) == false) {
			ra.addAttribute("msg","Degree Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Faculty_to_CourseUrl");	
		}	
		if (course == null || course.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Course.");
			return new ModelAndView("redirect:Faculty_to_CourseUrl");
		}
		
		if (validation.maxlengthcheck100( request.getParameter("course")) == false) {
			ra.addAttribute("msg","Course Name "+ validation.MaxlengthcheckMSG100);			
			return new ModelAndView("redirect:Faculty_to_CourseUrl");
			
		}		
		if (faculty == null || faculty.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Faculty.");
			return new ModelAndView("redirect:Faculty_to_CourseUrl");
		}
		
		if (validation.maxlengthcheck100( request.getParameter("faculty")) == false) {
			ra.addAttribute("msg","Faculty Name "+ validation.MaxlengthcheckMSG100);			
			return new ModelAndView("redirect:Faculty_to_CourseUrl");
			
		}	
		BigInteger id = td.getId().compareTo(BigInteger.ONE) > 0 ? td.getId() : BigInteger.ONE;
		Date date = new Date();
		String username = principal.getName();
//		String system_name = principal.getName();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(*) from EDU_TT_FACULTY_TO_COURSE_MASTER where degree=:degree and faculty=:faculty and course=:course and id !=:id and professional=:professional")
					  .setParameter("degree",Integer.parseInt(degree)) 
					  .setParameter("course",Integer.parseInt(course)) 
					  .setParameter("faculty", Integer.parseInt(faculty))
					  .setParameter("professional",Integer.parseInt(professional))
					  .setParameter("id", id).uniqueResult();
			

			
			if (id.compareTo(BigInteger.ONE) == 0) {
				
				td.setCreated_by(username);
				td.setCreated_date(date);
				td.setInstitute_id(institute_id);
//				td.setCreated_dt(created_dt);
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

		return new ModelAndView("redirect:Faculty_to_CourseUrl");
	}
	
	//==========================================SEARCH TIMETABLE ========================================== 	
	
	@PostMapping("/getFilterFacultytoCourse_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String degree, String professional, String course, String faculty,HttpSession session) {
		 Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();

		return ftcdao.DataTableFacultytoCourseDataList(startPage, pageLength, Search, orderColunm, orderType, degree, professional, course, faculty, institute_id);

	}

	@PostMapping("/getTotalFacultytoCourse_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String degree, String professional, String course, String faculty, HttpSession session) {
		 Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();

		return ftcdao.DataTableFacultytoCourseDataTotalCount(Search, degree, professional, course, faculty, institute_id);
		
	}
	
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
			 @RequestMapping(value = "/admin/getSearch_Faculty_to_Course_Master", method = RequestMethod.POST)
				public ModelAndView getSearch_TimeTable_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg) {
				 try {
//						SECURITY -- RIDDHI 
						if(request.getHeader("Referer") == null ) { 
//							session.invalidate();
							Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							return new ModelAndView("redirect:/landingpage");
						 }
						String roleid1 = session.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
//				 String  roleid = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid);	

						Mmap.addAttribute("msg", msg);
						 
						} catch (Exception e) {
								e.printStackTrace();
								}
						return new ModelAndView("faculty_to_course_master_Tiles");
						
			 }
			 
			// -------------------------Edit Battalion  -------------------------------------//

				@RequestMapping(value = "/Edit_Faculty_to_CourseUrl", method = RequestMethod.POST)
				public ModelAndView Edit_Faculty_to_CourseUrl(@ModelAttribute("id2") BigInteger updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {
					
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					
					String userid = sessionEdit.getAttribute("userId_for_jnlp").toString();
					Mmap.put("system", dmdao.Getsytemid_fetch(userid));
					System.err.println("INSIDE==============");
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_TT_FACULTY_TO_COURSE_MASTER timetable_Details = ftcdao.getFacultytoCourseByid(updateid);

					Mmap.addAttribute("msg", msg);
					Mmap.put("Edit_faculty_to_course_master_details", timetable_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					 Mmap.put("getDegreeList", getDegreeList());
//					 Mmap.put("getCourseList", getCourseDetails());
//					 Mmap.put("getFacultyList", getFacultyList(sessionFactory));
					 String userId = sessionEdit.getAttribute("userId").toString();
					 Mmap.put("getFacultyList",  ftcdao.getFacultyData(userId));
						Mmap.put("getProfessionalList", getProfessionalList());
//					Mmap.put("timetableinfo", tdao.grtftcByid(new BigInteger(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("edit_faculty_to_course_master_Tiles");
				}
				//edit action
				@RequestMapping(value = "/Edit_Faculty_to_Course_Master_action", method = RequestMethod.POST)
				public ModelAndView Edit_Faculty_to_Course_Master_action(@ModelAttribute("Edit_Faculty_to_Course_Master_cmd") EDU_TT_FACULTY_TO_COURSE_MASTER rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
//					String roleid1 = session.getAttribute("roleid").toString();

					String username = session.getAttribute("username").toString();

					BigInteger id = BigInteger.ZERO;					
					id = new BigInteger(request.getParameter("id"));
					String degree = request.getParameter("degree");
					String course = request.getParameter("course");
					String professional = request.getParameter("professional");
					String faculty = request.getParameter("faculty");
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					if (degree == null || degree.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Degree.");
						return new ModelAndView("redirect:Edit_Faculty_to_CourseUrl");
					}
					if (professional == null || professional.trim().equals("")) {
						ra.addAttribute("msg", "Please Select professional.");
						return new ModelAndView("redirect:Edit_Faculty_to_CourseUrl");
					}
					if (course == null || course.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Course.");
						return new ModelAndView("redirect:Edit_Faculty_to_CourseUrl");
					}
					if (faculty == null || faculty.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Faculty.");
						return new ModelAndView("redirect:Edit_Faculty_to_CourseUrl");
					}
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_TT_FACULTY_TO_COURSE_MASTER where degree=:degree and professional=:professional and course=:course and faculty=:faculty and id !=:id");
						q0.setParameter("degree", Integer.parseInt(degree));

						q0.setParameter("professional", Integer.parseInt(professional));
						
						q0.setParameter("course", Integer.parseInt(course));
						
						q0.setParameter("faculty", Integer.parseInt(faculty));

						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c ==  0) {
							String hql = "update EDU_TT_FACULTY_TO_COURSE_MASTER set degree=:degree,professional=:professional, course=:course,faculty=:faculty,modified_by=:modified_by,modified_date=:modified_date"
									+ " where id=:id";

							Query query = session1.createQuery(hql)
									.setParameter("degree", Integer.parseInt(degree))
									.setParameter("professional", Integer.parseInt(professional))
									.setParameter("course", Integer.parseInt(course))
									.setParameter("faculty", Integer.parseInt(faculty))
									.setParameter("modified_by", username)
									.setParameter("modified_date", new Date())
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
					
					return new ModelAndView("redirect:Faculty_to_CourseUrl");
				}
			 
				// -------------------------Delete Battalion  -------------------------------------//
				
				@RequestMapping(value = "/Delete_Url2", method = RequestMethod.POST)
				public ModelAndView Delete_Url2(@ModelAttribute("id7") BigInteger id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					/*
					 * if(request.getHeader("Referer") == null ) { session1.invalidate();
					 * model.put("msg",
					 * "Suspicious Activity Detected,You have been logged out by Administrator");
					 * return new ModelAndView("redirect:/login"); }
					 */
//					String roleid1 = session1.getAttribute("roleid").toString();
					/*
					 * Boolean val = roledao.ScreenRedirect("TimeTableUrl", roleid1); if(val ==
					 * false) { return new ModelAndView("AccessTiles"); }
					 */
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from EDU_TT_FACULTY_TO_COURSE_MASTER where id=:id")
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
					return new ModelAndView("redirect:Faculty_to_CourseUrl");
				}
						
	public List<EDU_LMS_DEGREE_MASTER> getDegreeList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_LMS_DEGREE_MASTER where status='1'");
		 
		 List<EDU_LMS_DEGREE_MASTER> DegreeList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return DegreeList;
	}
	
//	public List<EDU_LMS_COURSE_MASTER> getCourseDetails() {
//		 Session session = sessionFactory.openSession();
//		 Transaction tx = session.beginTransaction();
//		 Query q0 = session.createQuery("from EDU_LMS_COURSE_MASTER where degree_id=:degree_id status='1'").setParameter("degree_id", );
//		 
//		 List<EDU_LMS_COURSE_MASTER> CourseList = q0.list();
//	        session.getTransaction().commit();
//	        session.close();                
//	       return CourseList;
//	}
	
	
	@RequestMapping(value = "/getCourseDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getCourseDetails(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String degree_id) {
	       return ftcdao.getCourseDetailsDao(degree_id);
	
}
	
	
	@RequestMapping(value = "/getFacutlyDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getFacutlyDetails(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String course_id) {
              
	       return ftcdao.getFacultyDetailsDao(course_id);
	
}
	
	 public List<CC_TB_PROFESSIONAL_MSTR> getProfessionalList() {
  		 Session session = sessionFactory.openSession();
  		 Transaction tx = session.beginTransaction();
  		 Query q0 = session.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1' order by id");
  		 
  		 List<CC_TB_PROFESSIONAL_MSTR> DegreeList = q0.list();
  	      session.getTransaction().commit();
  	      session.close();                
  	     return DegreeList;
  	}
	
	
	
	
}
	