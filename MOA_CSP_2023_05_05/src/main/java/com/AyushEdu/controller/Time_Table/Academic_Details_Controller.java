package com.AyushEdu.controller.Time_Table;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS;
import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Time_Table.AcademicDAO;
import com.AyushEdu.validation.ValidationController;

@RequestMapping(value = { "admin", "/", "user" })
@Controller
public class Academic_Details_Controller {

	

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@Autowired
	 ValidationController validation;


	@Autowired
	AcademicDAO adao;
	
	
	
	@RequestMapping(value = "/academic_detailsUrl", method = RequestMethod.GET)
	public ModelAndView academic_detailsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

		Session sessiont = sessionFactory.openSession();
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getAcmDetList", getAcmDetList());
		 Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		 Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
		 Mmap.put("getExam_SerialList", common.getExam_SerialList(sessionFactory));
		 Mmap.put("getProfessionalList", getProfessionalList());
		 Mmap.put("getAcademicList", getAcademicList(institute_id));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Academic_Details_Tiles");

	}


	@PostMapping(value = "/AcademicAction")
	public ModelAndView AcademicAction(@Validated @ModelAttribute("AcademicCMD") EDU_TT_ACADEMIC_DETAILS td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {
		
		//SECURITY - POOJA
		
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		
		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
		String role = session.getAttribute("role").toString();
//		System.out.println("Dr=======================>>>"+role);
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		 Date date = new Date();
		 
		 String username = principal.getName();		 
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);	     	     
	    String professional = request.getParameter("professional");
		String academic_details = request.getParameter("academic_details");
		String term = request.getParameter("term");
		String exam_type = request.getParameter("exam_type");
		String exam_serial = request.getParameter("exam_serial");
		String starting_date = request.getParameter("starting_date");
		String ending_date = request.getParameter("ending_date");
		
				
		if (professional == null || professional.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Professional.");
			return new ModelAndView("redirect:academic_detailsUrl");
		}
		if (academic_details == null || academic_details.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Academic Details.");
			return new ModelAndView("redirect:academic_detailsUrl");
		}
		if (academic_details == null || academic_details.equals("001")) {
			if (term == null || term.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Term.");
				return new ModelAndView("redirect:academic_detailsUrl");
			}
		}
		if (academic_details == null || academic_details.equals("002")) {
			if (exam_type == null || exam_type.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Exam Type.");
				return new ModelAndView("redirect:academic_detailsUrl");
			}
			if (exam_type == null || exam_type.trim().equals("8")) {
				if (exam_serial == null || exam_serial.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Exam Serial.");
					return new ModelAndView("redirect:academic_detailsUrl");
				}
			}
			if (term == null || term.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Term.");
				return new ModelAndView("redirect:academic_detailsUrl");
			}
		}
		
		
		if (starting_date.trim().equals("") || starting_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select Start Date");
			return new ModelAndView("redirect:academic_detailsUrl");
		}
		if (ending_date.trim().equals("") ||  ending_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select  End Date");
			return new ModelAndView("redirect:academic_detailsUrl");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date s_to = null;
			s_to = format.parse(starting_date);
			Date e_to = null;
			e_to = format.parse(ending_date);
			SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_TT_ACADEMIC_DETAILS where Upper(academic_details)=:academic_details and professional=:professional and term=:term and exam_type=:exam_type and exam_serial=:exam_serial and institute_id=:institute_id")
					.setParameter("academic_details", academic_details)
					.setParameter("professional", Integer.parseInt(professional))
					.setParameter("term", Integer.parseInt(term))
					.setParameter("exam_type", Integer.parseInt(exam_type))
					.setParameter("institute_id", institute_id)
					.setParameter("exam_serial", Integer.parseInt(exam_serial))
					.uniqueResult();
			System.err.println("ccccccccccccccccccc---------cccccc"+starting_date);
			System.err.println("cccccccc--------------ccccccccccccccccc"+ending_date);
			
			Long ea = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and ((:s_to between starting_date and ending_date) or (:e_to between starting_date and ending_date)) and (academic_details='001' or academic_details='004') and institute_id=:institute_id")
					  .setParameter("s_to", format.parse(starting_date))
					  .setParameter("e_to", format.parse(ending_date)) 
					  .setParameter("professional", Integer.parseInt(professional))
					  .setParameter("institute_id", institute_id). uniqueResult();
			System.err.println("ea - "+ea);
			
			Long et = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and ((:s_to between starting_date and ending_date) or (:e_to between starting_date and ending_date)) and academic_details='001' and institute_id=:institute_id and ((term='10') or (term='11') or (term='12'))")
					  .setParameter("s_to", format.parse(starting_date)) 
					  .setParameter("e_to", format.parse(ending_date))
					  .setParameter("professional", Integer.parseInt(professional))
					  .setParameter("institute_id", institute_id). uniqueResult();
			System.err.println("et - "+et);
			
			System.err.println("C----------------"+td.getAcademic_details() +" ===="+td.getProfessional());
			System.err.println("ccccccccccccccccccccccccc"+starting_date);
			System.err.println("-------------------"+ending_date);
			td.setStarting_date(s_to);
			td.setEnding_date(e_to);
			
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
				td.setInstitute_id(institute_id);
				if (ea == 0 && et == 0) {
//					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
//					} else {
//						ra.addAttribute("msg", "Data already Exist.");
//					}
				}else {
					ra.addAttribute("msg", "This Date Range is already Exist for The Academic Details.");
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
				
			}
		}

		return new ModelAndView("redirect:academic_detailsUrl");
	}
	
	@PostMapping("/getFilteracademic_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(HttpSession sessionUserId, int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String professional, String academic_details, String term, String exam_type, String exam_serial, String starting_date, String ending_date) {
		Session sessiont = sessionFactory.openSession();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		return adao.DataTableacademicDataList(startPage, pageLength, Search, orderColunm, orderType, professional, academic_details, term, exam_type, exam_serial, starting_date, ending_date, institute_id);

	}

	@PostMapping("/getTotalacademic_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId,String Search, String professional, String academic_details, String term, String exam_type, String exam_serial, String starting_date, String ending_date) 
    {
		Session sessiont = sessionFactory.openSession();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		return adao.DataTableacademicDataTotalCount(Search, professional, academic_details, term, exam_type, exam_serial, starting_date, ending_date, institute_id);
		
	}
	
	
	 public List<EDU_TT_ACADEMIC_DETAILS_MSTR> getAcmDetList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_TT_ACADEMIC_DETAILS_MSTR order by refer_code");
		 
		 List<EDU_TT_ACADEMIC_DETAILS_MSTR> AcmDetList = (List<EDU_TT_ACADEMIC_DETAILS_MSTR>) q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return AcmDetList;
	}
	 public List<CC_TB_PROFESSIONAL_MSTR> getProfessionalList() {
  		 Session session = sessionFactory.openSession();
  		 Transaction tx = session.beginTransaction();
  		 Query q0 = session.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1'order by id");
  		 
  		 List<CC_TB_PROFESSIONAL_MSTR> DegreeList = q0.list();
  	      session.getTransaction().commit();
  	      session.close();                
  	     return DegreeList;
  	}
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
			 @RequestMapping(value = "/admin/getSearch_Academic", method = RequestMethod.POST)
				public ModelAndView getSearch_Academic(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "Academic_details1", required = false) String Academic_details1 ,String Academic_details,@ModelAttribute("Professional1") String Professional,@ModelAttribute("Starting_date1") String Starting_date,@ModelAttribute("Ending_date1") String Ending_date) {
				try {
					
					//SECURITY - POOJA
					
					if(request.getHeader("Referer") == null ) { 
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}

				Mmap.addAttribute("msg", msg);
				 
				} catch (Exception e) {
						e.printStackTrace();
						}
				return new ModelAndView("Academic_Details_Tiles");
			   	   
				}
			 
			//==========================================EDIT Academic NAME========================================== 	
				
				
				@RequestMapping(value = "/Edit_Academic_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Academic_Url(@ModelAttribute("id6") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {
					
					//SECURITY - POOJA
					
					if(request.getHeader("Referer") == null ) { 
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					
					String role = sessionEdit.getAttribute("role").toString();
					Session sessiont = sessionFactory.openSession();
					 String userid = sessionEdit.getAttribute("userId_for_jnlp").toString();
					 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					 int institute_id = institute_idList.get(0).getInstitute_id();

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_TT_ACADEMIC_DETAILS academic_Details = adao.getacademicByid(Integer.parseInt(updateid));
					
					Mmap.addAttribute("msg", msg);
					Mmap.put("getProfessionalList", getProfessionalList());
					Mmap.put("academic_details", common.getAcademicList(sessionFactory));
					Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
					Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
					Mmap.put("getExam_SerialList", common.getExam_SerialList(sessionFactory));
					Mmap.put("starting_date", common.getAcademicList(sessionFactory));
					Mmap.put("ending_date", common.getAcademicList(sessionFactory));
					Mmap.put("academic_Details", academic_Details);						
					Mmap.put("academic_Detailsinfo", adao.getacademicByid(Integer.parseInt(updateid)));
					Mmap.put("updateid", updateid);
					Mmap.put("getAcademicList", getAcademicList(institute_id));
					 

					tx.commit();
					sessionHQL.close();

					return new ModelAndView("edit_Academic_Tiles");
				}
				//edit action
				@RequestMapping(value = "/edit_Academic_Action", method = RequestMethod.POST)
				public ModelAndView edit_Academic_Details_Action(@ModelAttribute("edit_Academic_CMD") EDU_TT_ACADEMIC_DETAILS rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
					SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

					//SECURITY - POOJA
					if(request.getHeader("Referer") == null ) { 
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					String username = session.getAttribute("username").toString();

					int id = Integer.parseInt(request.getParameter("updateid"));
					String professional= request.getParameter("professional");
					String academic_details= request.getParameter("refer_code");
					String term= request.getParameter("term");
					String exam_type= request.getParameter("exam_type");
					String exam_serial= request.getParameter("exam_serial");
					String starting_date = request.getParameter("starting_date");
					String ending_date = request.getParameter("ending_date");
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					
					if (professional == null || professional.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Professional.");
						return new ModelAndView("redirect:academic_detailsUrl");
					}
					if (academic_details == null || academic_details.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Academic Details.");
						return new ModelAndView("redirect:academic_detailsUrl");
					}
					if (academic_details == null || academic_details.equals("002")) {
						if (exam_type == null || exam_type.trim().equals("0")) {
							ra.addAttribute("msg", "Please Select Exam Type.");
							return new ModelAndView("redirect:academic_detailsUrl");
						}
						if (exam_type == null || exam_type.trim().equals("8")) {
						if (exam_serial == null || exam_serial.trim().equals("0")) {
							ra.addAttribute("msg", "Please Select Exam Serial.");
							return new ModelAndView("redirect:academic_detailsUrl");
						}
						}
					}
					if (term == null || term.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Term.");
						return new ModelAndView("redirect:academic_detailsUrl");
					}
					
					if (starting_date.trim().equals("") || starting_date.trim().equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Select Start Date");
						return new ModelAndView("redirect:academic_detailsUrl");
					}
					if (ending_date.trim().equals("") ||  ending_date.trim().equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Select  End Date");
						return new ModelAndView("redirect:academic_detailsUrl");
					}
					
//					if (professional == null || professional.trim().equals("0")) {
//						ra.addAttribute("msg", "Please Select Professional.");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
//					
//					if (academic_details == null || academic_details.trim().equals("")) {
//						ra.addAttribute("msg", "Please Select Academic Details.");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
//					
//					if (term == null || term.trim().equals("0")) {
//						ra.addAttribute("msg", "Please Select Term.");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
//					if (academic_details == null || academic_details.equals("002")) {
//						if (exam_type == null || exam_type.trim().equals("0")) {
//							ra.addAttribute("msg", "Please Select Exam Type.");
//							return new ModelAndView("redirect:Edit_Academic_Url");
//						}
//						if (exam_serial == null || exam_serial.trim().equals("0")) {
//							ra.addAttribute("msg", "Please Select Exam Serial.");
//							return new ModelAndView("redirect:Edit_Academic_Url");
//						}
//					}
//					if (starting_date.equals("") || starting_date.equals("null") || starting_date.equals(null)) {
//						ra.addAttribute("msg", "Please Enter Starting Date.");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
//					if (ending_date.equals("") || ending_date.equals("null") || ending_date.equals(null)) {
//						ra.addAttribute("msg", "Please Enter Ending Date.");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
//					if (formate.parse(ending_date).compareTo(formate.parse(starting_date)) > 0) {
//						ra.addAttribute("msg", "Ending Date Should Be Greater Than Starting Date In Row ");
//						return new ModelAndView("redirect:Edit_Academic_Url");
//					}
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and upper(academic_details)=:academic_details and term=:term and exam_type=:exam_type and exam_serial=:exam_serial and starting_date=:starting_date and ending_date=:ending_date and id !=:id");
						q0.setParameter("professional", Integer.parseInt(professional));
						q0.setParameter("academic_details", academic_details);
						q0.setParameter("term", Integer.parseInt(term));
						q0.setParameter("exam_type", Integer.parseInt(exam_type));
						q0.setParameter("exam_serial", Integer.parseInt(exam_serial));
						q0.setParameter("starting_date", formate.parse(starting_date));
						q0.setParameter("ending_date", formate.parse(ending_date));
						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c == 0) {
							String hql = "update EDU_TT_ACADEMIC_DETAILS set professional=:professional,academic_details=:academic_details,term=:term,exam_type=:exam_type,exam_serial=:exam_serial,starting_date=:starting_date,ending_date=:ending_date,modified_by=:modified_by,modified_dt=:modified_dt"
									+ " where id=:id";

							Query query = session1.createQuery(hql)
									.setParameter("professional", Integer.parseInt(professional))
									.setParameter("academic_details",academic_details)
									.setParameter("term", Integer.parseInt(term))
									.setParameter("exam_type", Integer.parseInt(exam_type))
									.setParameter("exam_serial", Integer.parseInt(exam_serial))
									.setParameter("starting_date", formate.parse(starting_date))
									.setParameter("ending_date",formate.parse(ending_date))
									.setParameter("modified_by", username)
									.setParameter("modified_dt", new Date())
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
					
					return new ModelAndView("redirect:academic_detailsUrl");
				}	
				@SuppressWarnings("deprecation")
				public List<EDU_TT_ACADEMIC_DETAILS_MSTR> getAcademicList(int institute_id) {
					 Session session = sessionFactory.openSession();
					 Transaction tx = session.beginTransaction();
					 Query q0 = session.createQuery("from EDU_TT_ACADEMIC_DETAILS_MSTR ");
//					 	q0.setParameter("institute_id", institute_id);
					 	
					 @SuppressWarnings("unchecked")
					 List<EDU_TT_ACADEMIC_DETAILS_MSTR> AcademicList = (List<EDU_TT_ACADEMIC_DETAILS_MSTR>) q0.list();
				        session.getTransaction().commit();
				        session.close();                
				       return AcademicList;
				}
				
				
				//==========================================DELETE Academic NAME========================================== 	
				 
				 
				@RequestMapping(value = "/delete_Url22", method = RequestMethod.POST)
				public ModelAndView delete_Url21(@ModelAttribute("id22") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
	
					//SECURITY - POOJA
					if(request.getHeader("Referer") == null ) { 
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from EDU_TT_ACADEMIC_DETAILS where id=:id")
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
					return new ModelAndView("redirect:academic_detailsUrl");
				}
				
				@RequestMapping(value = "/GetTermFromNoofpartUrl", method = RequestMethod.POST)
				public @ResponseBody   List<Map<String, Object>> GetTermFromNoofpartUrl(HttpServletRequest request, HttpSession session) {
					String userid1 = session.getAttribute("userId").toString();

					String role = session.getAttribute("rolename").toString();
					String role_id = session.getAttribute("roleid").toString();
					String userid = session.getAttribute("userId_for_jnlp").toString();
					String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
					String nop = request.getParameter("no_of_part");
				 List<Map<String, Object>> list = adao.GetTermFromNoofpart(role,inst_id);
				 System.err.println("userid====================="+userid1);
					return list;
				}

  }
  
