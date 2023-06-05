package com.AyushEdu.controller.Registration;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Registration.EDU_UNIVERSITY_ENROLLMENT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.UserLoginDAOImpl;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.University_Enrollment_Dao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class University_Enrollment_Controller {
	
	@Autowired
	University_Enrollment_Dao  udao;
	
	@Autowired
	UserLoginDAO userLoginDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	CommonController common;

	@Autowired
	Search_Student_RegistrationDao SSRnchDao;
	
	@Autowired
	Search_Ncism_Student_RegistrationDao SSRncismDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/University_Enrollment_Formate_Url", method = RequestMethod.GET)
	public ModelAndView University_Enrollment_Formate_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) throws NumberFormatException {
		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("University_Enrollment_Formate_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		
		 String uniid="";
		 String instid="";
		
		System.err.println("role---------->    "+role);
		
		UserLogin addData = userLoginDAO.findByRoleId(Integer.parseInt(userid)); 
		  uniid = String.valueOf(addData.getUniversity_id());
		  
		  if (role.toUpperCase().contains("INSTITUTE") || role.toUpperCase().contains("PRINCIPAL")) {
			  instid = String.valueOf(addData.getInstitute_id());
			  Mmap.put("institute_id", instid);
		  	}
	
		 String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
		if (Staff_lvl.toUpperCase().equals("NCH")) {
			Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
			Mmap.put("getinstitutelist", SSRnchDao.getinstitutelist(userid));
		}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
			Mmap.put("getUniverCityList", commondao.getUniversityNcismlist());
			Mmap.put("getinstitutelist", SSRncismDao.getinstitute_Ncismlist(userid));
		}
		
		Mmap.put("getSystemList", common.getSystemListbyrole(sessionFactory,role));
		String MaxUEID = udao.getMaxUniEnrollmentID(userid, Staff_lvl);
		Mmap.put("MaxUEID", MaxUEID);
		
		 Mmap.put("university_id", uniid);
		 
		 List<Map<String, Object>> list = udao.getAdmissionFeepaidstudentListForunienrollReport("", "", "", instid,uniid,Staff_lvl);
		 Mmap.put("list", list);
		 
			Mmap.addAttribute("udata", udao.getUniversityEnrollmentDetails(Integer.parseInt(userid)));
			Mmap.put("msg", msg);
			return new ModelAndView("University_Enrollment_Formate_Tiles");
			
	}
	
	@RequestMapping(value = "/University_Enrollment_Formate_Search", method = RequestMethod.POST)
	public ModelAndView University_Enrollment_Formate_Search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
	
	@RequestParam(value = "academic_year1", required = false) String academic_year,
	@RequestParam(value = "system_id1", required = false) String system_id,
	@RequestParam(value = "degree_id1", required = false) String degree_id,
	@RequestParam(value = "institute_id1", required = false) String institute_id,
	@RequestParam(value = "university_id1", required = false) String university_id) throws SQLException{
		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("University_Enrollment_Formate_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("screen_mstUrl", roleid);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		
		 String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
			if (Staff_lvl.toUpperCase().equals("NCH")) {
				Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
				Mmap.put("getinstitutelist", SSRnchDao.getinstitutelist(userid));
			}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
				Mmap.put("getUniverCityList", commondao.getUniversityNcismlist());
				Mmap.put("getinstitutelist", SSRncismDao.getinstitute_Ncismlist(userid));
			}
			
			Mmap.addAttribute("udata", udao.getUniversityEnrollmentDetails(Integer.parseInt(userid)));
			Mmap.put("getSystemList", common.getSystemListbyrole(sessionFactory,role));
			String MaxUEID = udao.getMaxUniEnrollmentID(userid, Staff_lvl);
			Mmap.put("MaxUEID", MaxUEID);
			
			
			 if (role.toUpperCase().contains("INSTITUTE") || role.toUpperCase().contains("PRINCIPAL")) {
				 UserLogin addData = userLoginDAO.findByRoleId(Integer.parseInt(userid)); 
				 institute_id = String.valueOf(addData.getInstitute_id());
				  Mmap.put("institute_id", institute_id);
			  	}
		
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		} else {
		
		 List<Map<String, Object>> list =   udao.getAdmissionFeepaidstudentListForunienrollReport(academic_year,  system_id,  degree_id,  institute_id, university_id,Staff_lvl);
			Mmap.put("list", list);
			Mmap.put("academic_year", academic_year);
			Mmap.put("system_id", system_id);
			Mmap.put("degree_id", degree_id);
			Mmap.put("institute_id", institute_id);
			Mmap.put("university_id", university_id);
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("University_Enrollment_Formate_Tiles");
//		}
	}
	
	@RequestMapping(value = "/UniversityEnrollmentAction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView UniversityEnrollmentAction(SessionFactory sessionFactory,RedirectAttributes ra,EDU_UNIVERSITY_ENROLLMENT_MSTR td,BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession Session, Principal principal) {
		
		String msg="";
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) {
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = Session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("University_Enrollment_Formate_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		String userid = Session.getAttribute("userId_for_jnlp").toString();
		String Staff_lvl = Session.getAttribute("roleStaff_lvl").toString();
		
		System.err.println("userid------>     " +userid);
		
		int id = td.getId() > 0 ? td.getId() : 0;
		String university_enrollment_formate = request.getParameter("university_enrollment_formate");
		
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_UNIVERSITY_ENROLLMENT_MSTR where university_enrollment_formate=:university_enrollment_formate and userid=:userid and  id !=:id")
					.setParameter("university_enrollment_formate", (university_enrollment_formate))
					.setParameter("userid",(Integer.parseInt(userid)))
					.setParameter("id", id).uniqueResult();
				
			if (id == 0) {
				td.setUniversity_enrollment_formate(university_enrollment_formate);
				td.setUserid(Integer.parseInt(userid));
				td.setCreated_by(userid);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
//					msg="Data Saved Successfully.";
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
//					msg="Data already exist";
					ra.addAttribute("msg", "Data already exist.");
				}
			}

//			university_enrollment_formate
			String stu_ids = request.getParameter("stu_ids");
			String MaxUEID="";
			
			if (university_enrollment_formate.toUpperCase().equals("AUTO_GENERATE_ID")) {
				
				String[] id_list = stu_ids.split(",");

				List<String> list2 = new ArrayList<String>();
				String sd_id = "";
				for (int i = 0; i < id_list.length; i++) {
					sd_id = id_list[i];
					MaxUEID = udao.getMaxUniEnrollmentID(userid, Staff_lvl);
					System.err.println(" Rajdip ---ID------->   "+sd_id);
					list2.add(udao.getUpdateuniversityenrolmentno(sd_id,MaxUEID,Staff_lvl));
				}
				System.err.println(" list2.get(0)--------->   "+list2.get(0));
				ra.addAttribute("msg", list2.get(0));
				
			}else if (university_enrollment_formate.toUpperCase().equals("AYUSH_ID")) {
				
							String[] id_listayu = stu_ids.split(",");
			
							List<String> list2 = new ArrayList<String>();
							String sd_idayu = "";
							for (int i = 0; i < id_listayu.length; i++) {
								sd_idayu = id_listayu[i];
								System.err.println("Rajdip-------ID-------->   "+sd_idayu);
								list2.add(udao.getUpdateuniversityenrolmentnoasaayushid(sd_idayu,Staff_lvl));
							}
							System.err.println("list2.get(0)--------->   "+list2.get(0));
							ra.addAttribute("msg", list2.get(0));
				
			} else if (university_enrollment_formate.toUpperCase().equals("MANUALLY_ID")) {
				
				String[] id_listman = stu_ids.split(",");
				List<String> list3 = new ArrayList<String>();
				String sd_idman = "";
				String sd_idr = "";
				for (int r = 0; r < id_listman.length; r++) {
					sd_idr = id_listman[r];
					String university_enrollment_r = request.getParameter("university_enrollment"+sd_idr);
					System.err.println("university_enrollment_r-------->    "+university_enrollment_r);
					if (university_enrollment_r.trim() == "" || university_enrollment_r.trim() == "null" || university_enrollment_r.trim() == null || university_enrollment_r.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter University Enrollment No.");
						return new ModelAndView("redirect:University_Enrollment_Formate_Url");
					}
				}
				for (int i = 0; i < id_listman.length; i++) {
					sd_idman = id_listman[i];
					String university_enrollment = request.getParameter("university_enrollment"+sd_idman);
					System.err.println("Rajdip ------ ID --------->   "+sd_idman);
					list3.add(udao.getUpdateuniversityenrolmentnomanualid(sd_idman,university_enrollment,Staff_lvl));
				}
				System.err.println("list3.get(0)--------->   "+list3.get(0));
				ra.addAttribute("msg", list3.get(0));
			}
			tx.commit();
			sessionHQL.close();
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
		return new ModelAndView("redirect:University_Enrollment_Formate_Url");
	}
}
