package com.AyushEdu.controller.Examination;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Examination.EXAM_TB_DETAIN_STUDENT;
import com.AyushEdu.Models.Examination.EXAM_TB_PASS_STUDENT;
import com.AyushEdu.Models.Examination.EXAM_TB_SUPPLEMENTARY_STUDENT;
import com.AyushEdu.Models.Examination.HISTORY_EXAM_TB_DETAIN_STUDENT;
import com.AyushEdu.Models.Examination.HISTORY_EXAM_TB_PASS_STUDENT;
import com.AyushEdu.Models.Examination.HISTORY_EXAM_TB_SUPPLEMENTARY_STUDENT;
import com.AyushEdu.Models.Library_mgmt.TB_BOOK_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Examination.Students_Result_DetailsDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Students_Result_Details_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private Students_Result_DetailsDao SRDDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Student_Result_Declare_url", method = RequestMethod.GET)
	public ModelAndView Student_Result_Declare_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Student_Result_Declare_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
			String role = session.getAttribute("role").toString();	
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String uni_id = SRDDao.getuserid_listby_university(Integer.parseInt(userid)).get(0).get(0);
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory,roleStaff_lvl));	
			Mmap.put("getTerm", common.getTerm());
			Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory,uni_id));
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Student_Result_Declare_Tiles");
	}
	
	@RequestMapping(value = "/getstu_res_declare_data", method = RequestMethod.POST)
	public @ResponseBody   List<ArrayList<String>> getstu_res_declare_data(String system_id,String degree_id,String professional_id,String institute_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String uni_id = SRDDao.getuserid_listby_university(Integer.parseInt(userid)).get(0).get(0);
		List<ArrayList<String>> list = SRDDao.getstu_res_declare_data(system_id,degree_id,professional_id,institute_id,role,uni_id);
		return list;
	}
	
	@PostMapping(value = "/Pass_Student_Data")
	public @ResponseBody String Pass_Student_Data(String a,String system_id,String degree_id,String professional_id,String institute_id,String date_of_exam,String b,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		String msg="";
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Student_Result_Declare_url", roleid1);		
			if(val == false) {
				return null;
		}
		
		EXAM_TB_PASS_STUDENT td =new  EXAM_TB_PASS_STUDENT();
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if (system_id == null || system_id.trim().equals("0")) {
			return "Please Select system.";
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			return "Please Select Degree.";
		}
		if (professional_id == null || professional_id.trim().equals("0")) {
			return "Please Select Professional.";
		}
		if (institute_id == null || institute_id.trim().equals("0")) {
			return "Please Select Institute.";
		}
		if (date_of_exam == null || date_of_exam.trim().equals("") || date_of_exam.equals("DD/MM/YYYY")) {
			return "Please Select Date of Exam.";
		}
		if (date_of_exam == "DD/MM/YYYY" || date_of_exam.trim().equals("")) {
			return "Please Select Date of Exam.";
		}
		if (a == null || a.trim().equals("")) {
			return "Please Select Student.";
		}
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String university_id = session.getAttribute("university_id").toString();
		String[] stids = a.split(":");
		String[] stids1 = b.split(",");

		try {
			
			for(int i=0;i<stids.length;i++) {
//				for(int j=0;j<stids1.length;j++) {
					
				int stuId = Integer.parseInt(stids[i]);
				String stuId1 = stids1[i];
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EXAM_TB_PASS_STUDENT where system_id=:system_id and degree_id=:degree_id and"
						+ " professional_id=:professional_id and institute_id=:institute_id and date_of_exam=:date_of_exam and student_id=:student_id "
						+ "and ayush_id=:ayush_id and id =:id")
						.setParameter("system_id", td.getSystem_id())
						.setParameter("degree_id", td.getDegree_id())
						.setParameter("professional_id", td.getProfessional_id())
						.setParameter("institute_id", td.getInstitute_id())
						.setParameter("date_of_exam", td.getDate_of_exam())
						.setParameter("student_id", td.getStudent_id())
						.setParameter("ayush_id", td.getAyush_id())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					td.setSystem_id(Integer.parseInt(system_id));
					td.setDegree_id(Integer.parseInt(degree_id));
				    td.setProfessional_id(Integer.parseInt(professional_id));
				    td.setInstitute_id(Integer.parseInt(institute_id));
				    td.setDate_of_exam(format.parse(date_of_exam));
				    td.setStudent_id(stuId);
				    td.setAyush_id(stuId1);
				    td.setStatus(1);
				    td.setUniversity_id(Integer.parseInt(university_id));
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					if (c == 0) {
						
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						
						HISTORY_EXAM_TB_PASS_STUDENT od = new HISTORY_EXAM_TB_PASS_STUDENT();
						int id1 = od.getId() > 0 ? od.getId() : 0;
						
						od.setSystem_id(Integer.parseInt(system_id));
						od.setDegree_id(Integer.parseInt(degree_id));
						od.setProfessional_id(Integer.parseInt(professional_id));
						od.setInstitute_id(Integer.parseInt(institute_id));
						od.setDate_of_exam(format.parse(date_of_exam));
						od.setStudent_id(stuId);
						od.setAyush_id(stuId1);
						od.setStatus(1);
						od.setUniversity_id(Integer.parseInt(university_id));
						od.setCreated_by(username);
						od.setCreated_date(date);
						
						sessionHQL.save(od);
						sessionHQL.flush();
						sessionHQL.clear();
						
						String role = session.getAttribute("role").toString();
						int currentTerm =   Integer.parseInt(SRDDao.currentTerm(stuId,role).get(0).get(0));
						currentTerm = currentTerm + 1;
						
						if (role.trim().equals("University_NCISM")) {
						int app = sessionHQL.createQuery("update EDU_LMS_STUDENT_DETAILS set semester=:semester,fee_paid_status=0,modified_by=:modified_by,modified_date=:modified_date where id=:id")
								.setParameter("semester", String.valueOf(currentTerm))
								.setParameter("id",stuId)
								.setParameter("modified_by", username)
								.setParameter("modified_date", new Date()).executeUpdate();
						}
						if (role.trim().equals("University_NCH")) {
						int app = sessionHQL.createQuery("update EDU_LMS_NCH_STUDENT_DETAILS set semester=:semester,fee_paid_status=0,modified_by=:modified_by,modified_date=:modified_date where id=:id")
								.setParameter("semester", String.valueOf(currentTerm))
								.setParameter("id",stuId)
								.setParameter("modified_by", username)
								.setParameter("modified_date", new Date()).executeUpdate();
						}
						msg =  "Students Data Saved Successfully.";
					} else {
						msg =  "Data already Exist.";
					}
				} 
//			}
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
		return msg;
	}
	
	@PostMapping(value = "/Supprimentry_Student_Data")
	public @ResponseBody String Supprimentry_Student_Data(String a,String system_id,String degree_id,String professional_id,String institute_id,String date_of_exam,
			String b,String course,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		String msg="";
		//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Student_Result_Declare_url", roleid1);		
				if(val == false) {
					return null;
			}
		EXAM_TB_SUPPLEMENTARY_STUDENT ss =new  EXAM_TB_SUPPLEMENTARY_STUDENT();
		
		int id = ss.getId() > 0 ? ss.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String university_id = session.getAttribute("university_id").toString();
		String[] stids = a.split(",");
		String[] stids1 = b.split(",");
		String[] crs1 = course.split(",");

		try {
			if (system_id == null || system_id.trim().equals("0")) {
				return "Please Select system.";
			}
			if (degree_id == null || degree_id.trim().equals("0")) {
				return "Please Select Degree.";
			}
			if (professional_id == null || professional_id.trim().equals("0")) {
				return "Please Select Professional.";
			}
			if (institute_id == null || institute_id.trim().equals("0")) {
				return "Please Select Institute.";
			}
			if (date_of_exam == null || date_of_exam.trim().equals("") || date_of_exam.equals("DD/MM/YYYY")) {
				return "Please Select Date of Exam.";
			}
			if (course == null || course.trim().equals("")) {
				return "Please Select Subject.";
			}
			if (a == null || a.trim().equals("")) {
				return "Please Select Student.";
			}
			
			for(int j=0;j<crs1.length;j++) {
				String corse1 = crs1[j];
			for(int i=0;i<stids.length;i++) {
//				for(int j=0;j<stids1.length;j++) {
					
				int stuId = Integer.parseInt(stids[i]);
				String stuId1 = stids1[i];
				
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EXAM_TB_SUPPLEMENTARY_STUDENT where system_id=:system_id and degree_id=:degree_id "
						+ " and professional_id=:professional_id and institute_id=:institute_id and date_of_exam=:date_of_exam and student_id=:student_id "
						+ " and ayush_id=:ayush_id and subject=:subject ")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("institute_id", Integer.parseInt(institute_id))
						.setParameter("date_of_exam",format.parse(date_of_exam))
						.setParameter("student_id", stuId)
						.setParameter("ayush_id", stuId1)
						.setParameter("subject", corse1)
						.uniqueResult();
				
				if (id == 0) {
					ss.setSystem_id(Integer.parseInt(system_id));
					ss.setDegree_id(Integer.parseInt(degree_id));
					ss.setProfessional_id(Integer.parseInt(professional_id));
					ss.setInstitute_id(Integer.parseInt(institute_id));
					ss.setDate_of_exam(format.parse(date_of_exam));
					ss.setStudent_id(stuId);
					ss.setAyush_id(stuId1);
					ss.setStatus(1);
					ss.setUniversity_id(Integer.parseInt(university_id));
					ss.setCreated_by(username);
					ss.setSubject(corse1);
					ss.setCreated_date(date);
					if (c == 0) {
						
						sessionHQL.save(ss);
						sessionHQL.flush();
						sessionHQL.clear();
						
						HISTORY_EXAM_TB_SUPPLEMENTARY_STUDENT hs = new HISTORY_EXAM_TB_SUPPLEMENTARY_STUDENT();
						int id1 = hs.getId() > 0 ? hs.getId() : 0;
						
						hs.setSystem_id(Integer.parseInt(system_id));
						hs.setDegree_id(Integer.parseInt(degree_id));
						hs.setProfessional_id(Integer.parseInt(professional_id));
						hs.setInstitute_id(Integer.parseInt(institute_id));
						hs.setDate_of_exam(format.parse(date_of_exam));
						hs.setStudent_id(stuId);
						hs.setAyush_id(stuId1);
						hs.setStatus(1);
						hs.setUniversity_id(Integer.parseInt(university_id));
						hs.setSubject(corse1);
						hs.setCreated_by(username);
						hs.setCreated_date(date);
						
						sessionHQL.save(hs);
						sessionHQL.flush();
						sessionHQL.clear();
//						String role = session.getAttribute("role").toString();
//						int currentTerm =   Integer.parseInt(SRDDao.currentTerm(stuId,role).get(0).get(0));
//						currentTerm = currentTerm + 1;
//						
//						if (role.trim().equals("University_NCISM")) {
//						int app = sessionHQL.createQuery("update EDU_LMS_STUDENT_DETAILS set semester=:semester,fee_paid_status='0',modified_by=:modified_by,modified_date=:modified_date where id=:id")
//								.setParameter("semester", String.valueOf(currentTerm))
//								.setParameter("id",stuId)
//								.setParameter("modified_by", username)
//								.setParameter("modified_date", new Date()).executeUpdate();
//						}
//						
//						if (role.trim().equals("University_NCH")) {
//							int app = sessionHQL.createQuery("update EDU_LMS_NCH_STUDENT_DETAILS set semester=:semester,fee_paid_status='0',modified_by=:modified_by,modified_date=:modified_date where id=:id")
//									.setParameter("semester", String.valueOf(currentTerm))
//									.setParameter("id",stuId)
//									.setParameter("modified_by", username)
//									.setParameter("modified_date", new Date()).executeUpdate();
//							}
						
						msg =  "Students Data Saved Successfully.";
					} else {
						msg =  "Data already Exist.";
					}
				} 
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
		return msg;
	}
	
	@PostMapping(value = "/Detain_Student_Data")
	public @ResponseBody String Detain_Student_Data(String a,String system_id,String degree_id,String professional_id,String institute_id,String date_of_exam,String b,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		String msg="";
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Student_Result_Declare_url", roleid1);		
				if(val == false) {
					return null;
			}
		EXAM_TB_DETAIN_STUDENT ts =new  EXAM_TB_DETAIN_STUDENT();
		
		int id = ts.getId() > 0 ? ts.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String university_id = session.getAttribute("university_id").toString();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String[] stids = a.split(":");
		String[] stids1 = b.split(",");
		try {
			if (system_id == null || system_id.trim().equals("0")) {
				return "Please Select system.";
			}
			if (degree_id == null || degree_id.trim().equals("0")) {
				return "Please Select Degree.";
			}
			if (professional_id == null || professional_id.trim().equals("0")) {
				return "Please Select Professional.";
			}
			if (institute_id == null || institute_id.trim().equals("0")) {
				return "Please Select Institute.";
			}
			if (date_of_exam == null || date_of_exam.trim().equals("") || date_of_exam.equals("DD/MM/YYYY")) {
				return "Please Select Date of Exam.";
			}
			if (a == null || a.trim().equals("")) {
				return "Please Select Student.";
			}
			for(int i=0;i<stids.length;i++) {
//				for(int j=0;j<stids1.length;j++) {
					
				int stuId = Integer.parseInt(stids[i]);
				String stuId1 = stids1[i];
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EXAM_TB_DETAIN_STUDENT where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id"
						+ " and institute_id=:institute_id and date_of_exam=:date_of_exam and student_id=:student_id and ayush_id=:ayush_id and id=:id ")
						.setParameter("system_id", ts.getSystem_id())
						.setParameter("degree_id", ts.getDegree_id())
						.setParameter("professional_id", ts.getProfessional_id())
						.setParameter("institute_id", ts.getInstitute_id())
						.setParameter("date_of_exam", ts.getDate_of_exam())
						.setParameter("student_id", ts.getStudent_id())
						.setParameter("ayush_id", ts.getAyush_id())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					ts.setSystem_id(Integer.parseInt(system_id));
					ts.setDegree_id(Integer.parseInt(degree_id));
					ts.setProfessional_id(Integer.parseInt(professional_id));
					ts.setInstitute_id(Integer.parseInt(institute_id));
					ts.setDate_of_exam(format.parse(date_of_exam));
					ts.setStudent_id(stuId);
					ts.setAyush_id(stuId1);
					ts.setStatus(1);
					ts.setUniversity_id(Integer.parseInt(university_id));
					ts.setCreated_by(username);
					ts.setCreated_date(date);
					if (c == 0) {

						sessionHQL.save(ts);
						sessionHQL.flush();
						sessionHQL.clear();
						
						HISTORY_EXAM_TB_DETAIN_STUDENT ds = new HISTORY_EXAM_TB_DETAIN_STUDENT();
						int id1 = ds.getId() > 0 ? ds.getId() : 0;
						
						ds.setSystem_id(Integer.parseInt(system_id));
						ds.setDegree_id(Integer.parseInt(degree_id));
						ds.setProfessional_id(Integer.parseInt(professional_id));
						ds.setInstitute_id(Integer.parseInt(institute_id));
						ds.setDate_of_exam(format.parse(date_of_exam));
						ds.setStudent_id(Integer.parseInt(stids[i]));
						ds.setStatus(1);
						ds.setUniversity_id(Integer.parseInt(university_id));
						ds.setCreated_by(username);
						ds.setCreated_date(date);
						
						sessionHQL.save(ds);
						sessionHQL.flush();
						sessionHQL.clear();
						
						msg =  "Students Data Saved Successfully.";
					} else {
						msg =  "Data already Exist.";
					}
				} 
		  }
				tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
}
