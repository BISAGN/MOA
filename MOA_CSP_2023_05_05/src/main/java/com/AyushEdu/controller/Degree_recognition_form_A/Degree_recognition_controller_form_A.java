package com.AyushEdu.controller.Degree_recognition_form_A;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_CLARIFICATION_DATA_HISTORY;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ENCLOSURE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_EXAMINERS_APPOINTED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_INTERN_STUDENT_COURSE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_FROM;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_SUB_CHILD;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_A.Student_admitted_dao;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_controller_form_A {
	
	private static final Session HibernateUtil = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	 ValidationController validation;
	@Autowired
	Form_a_ug_Dao UDdao;
	@Autowired
	Gender_DAO sdao;
	@Autowired
	Form_a_ug_Dao Form_a_ug_DAO;
	@Autowired
	Form_b_pg_Dao PDdao;
	@Autowired
	Commondao commondao;
	@Autowired
	Student_admitted_dao SDdao;
	//==========================================OPEN PAGE DEGREE RECOGNITION========================================== 
	
	@RequestMapping(value = "/Degree_rec_Url", method = RequestMethod.GET)
	public ModelAndView Degree_rec_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Degree_rec_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		Mmap.put("date_without_time", date_without_time);
		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
//		Mmap.put("getNCHstudentList", common.getNCHstudentList(sessionFactory, roleid));
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getMedStateName",common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName",common.getMedDistrictName(sessionFactory));
		Mmap.put("getinstitutelist", UDdao.getinstitutelist(userid));
		Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
		Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory, userid));
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
		Mmap.put("getuni_dtl", UDdao.getUni_detail(Integer.parseInt(userid)));
		 
		String role = session.getAttribute("roleid").toString();
		if (role.equals("19")) {//uni nch
			String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
			System.err.println("uni_id------>   "+uni_id);
			Mmap.put("uni_id",uni_id);
			}
			
			if (role.equals("21")) {//inst nch
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id------>   "+inst_id);
				Mmap.put("inst_id",inst_id);
			}
		return new ModelAndView("Degree_rec_Tiles");
		
		
	}

	@RequestMapping(value = "/get_inst_by_uni_ctrl", method = RequestMethod.POST)
 	public @ResponseBody ArrayList<ArrayList<String>> get_inst_by_uni_ctrl(String university_id) {
    	ArrayList<ArrayList<String>> data = UDdao.get_inst_uni_data(university_id);
    	return data;
 	}
	
	
	//==========================================SAVE Form A====UNIVERSITY====================================== 	

//	@RequestMapping(value = "Degree_rec_Action", method = RequestMethod.POST)
//
//	public @ResponseBody Map<String, String> Degree_rec_Action(
//			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,RedirectAttributes ra) 
//			throws ParseException, IOException {
//		
//
//		Map<String, String> data = new HashMap<>();
//		if(request.getHeader("Referer") == null ) 
//        { 
//             data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
//             return data;
//        }
//
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//
//		Date date = new Date();
//
//		  String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//	      String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
//	        
//	      String current_mm_yy = month+"-"+year;
//	      
//		String username = session.getAttribute("username").toString();
//		String name_of_applicant_university = request.getParameter("name_of_applicant_university");
//		System.out.println("-------------name_of_applicant_university"+name_of_applicant_university);
//		String name_ug_course = request.getParameter("name_ug_course");
//		String abbre_undergraduate_course = request.getParameter("abbre_undergraduate_course");
//		String university_id = session.getAttribute("university_id").toString();
//		String form_a_id = request.getParameter("form_a_id");
//		String userId = request.getParameter("userId");	
//		String current_month_year = request.getParameter("Calendar");
//		
//		try {
//
//			if (Integer.parseInt(form_a_id) == 0) {
//
//				DG_REC_UG td = new DG_REC_UG();
//
//				td.setName_of_applicant_university(name_of_applicant_university);
//				td.setName_ug_course(name_ug_course);
//				td.setAbbre_undergraduate_course(abbre_undergraduate_course);
//				td.setUniversity_id(Integer.parseInt(university_id));
//				td.setCurrent_month_year(current_mm_yy);
//				td.setCreated_by(username);
//				td.setCreated_date(date);
//				td.setUser_id(Integer.parseInt(userId));
//				
//				int id = (int) sessionHQL.save(td);
//
//				data.put("form_a_id", String.valueOf(id));
//
//				data.put("saved","Data Saved as Draft Successfully.") ;
//					 
//			}
//				else 
//				{
//
//				String hql = "update DG_REC_UG set modified_by=:modified_by ,modified_date=:modified_date ,name_of_applicant_university=:name_of_applicant_university, "
//						+ "name_ug_course=:name_ug_course,abbre_undergraduate_course=:abbre_undergraduate_course,abbre_undergraduate_course=:abbre_undergraduate_course"
//						+ " where  id=:id";
//
//						 Query query = sessionHQL.createQuery(hql)
//							.setTimestamp("modified_date", date)
//							.setString("modified_by",username)
//							.setString("name_of_applicant_university", name_of_applicant_university)
//							.setString("name_ug_course", name_ug_course)
//							.setString("abbre_undergraduate_course",abbre_undergraduate_course)
//							
//							.setInteger("status",1)
//						    .setInteger("id",Integer.parseInt(form_a_id));
//
//						 int update = query.executeUpdate() > 0 ? 1 : 0;
//
//							if (update == 1)
//								data.put("updated", "Data Updated Successfully");
//							else
//								data.put("error", "Data Not Updated");
//
//			}
//			tx.commit();
//
//		} catch (RuntimeException e) {
//
//			try {
//
//				tx.rollback();
//
//				data.put("error", "Data Not Updated");
//
//			} catch (RuntimeException rbe) {
//
//				data.put("error", "Data Not Updated");
//
//			}
//
//		} finally {
//
//			if (sessionHQL != null) {
//
//				sessionHQL.close();
//
//			}
//
//		}
//		return data;
//	}	

	@RequestMapping(value = "/getDegreeList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeList(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {
			list = UDdao.getDegreeListFromInstitute(institute_id, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	 @PostMapping(value = "/form_a_action")
	public ModelAndView form_a_action(@Validated @ModelAttribute("UG_FormCMD") DG_REC_UG_FORM_A td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
		
			System.out.println("----in action--formmmm-");
			
			@SuppressWarnings("unused")
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		        
			String username = session.getAttribute("username").toString();
			System.out.println("--------- username-----------"+username);
			String userId = session.getAttribute("userId").toString();
			String name_of_applicant_university = request.getParameter("name_of_applicant_university");
			System.out.println("-------------name_of_applicant_university"+name_of_applicant_university);
			String name_ug_course = request.getParameter("name_ug_course");
			String abbre_undergraduate_course = request.getParameter("abbre_undergraduate_course");
			String institute_name=request.getParameter("institute_name");
			String academic_year_applied_for =request.getParameter("academic_year_applied_for");
			String academic_file_upload = gd(request, mul, session, "academic_file_upload");
			String hid_upload_file = request.getParameter("hid_upload_file");
			String permission_from_central_gov = gd(request, mul, session, "permission_from_central_gov");
			String hid_upload_file2 = request.getParameter("hid_upload_file2");
			String admission_intake = request.getParameter("admission_intake");
			String num_of_student_admitted = request.getParameter("num_of_student_admitted");
			String postal_address = request.getParameter("postal_address");
			String email = request.getParameter("email");
			String website = request.getParameter("website");
			String remarks = request.getParameter("remarks");
			String university_id = session.getAttribute("university_id").toString();
//			String form_a_id = request.getParameter("form_a_id");
			String current_month_year = request.getParameter("Calendar");

			if (academic_file_upload=="") {
				academic_file_upload = hid_upload_file;
			}
			if (permission_from_central_gov=="") {
				permission_from_central_gov = hid_upload_file2;
			}
			int id = td.getId() > 0 ? td.getId() : 0;
			
			try {
				Long c = (Long) sessionHQL.createQuery(
						
						"select count(id) from  DG_REC_UG_FORM_A where name_of_applicant_university=:name_of_applicant_university and "
						+ "name_ug_course=:name_ug_course and abbre_undergraduate_course=:abbre_undergraduate_course and "
						+ "institute_name=:institute_name and academic_year_applied_for=:academic_year_applied_for and "
						+ "academic_file_upload=:academic_file_upload and "
						+ "permission_from_central_gov=:permission_from_central_gov and admission_intake=:admission_intake and "
						+ "num_of_student_admitted=:num_of_student_admitted and postal_address=:postal_address and email=:email and "
						+ "website=:website and remarks=:remarks and  id !=:id")
						
						.setParameter("name_of_applicant_university", td.getName_of_applicant_university())
						.setParameter("name_ug_course", td.getName_ug_course())
						.setParameter("abbre_undergraduate_course", td.getAbbre_undergraduate_course())
						.setParameter("institute_name", td.getInstitute_name())
						.setParameter("academic_year_applied_for", td.getAcademic_year_applied_for())
						.setParameter("academic_file_upload", td.getAcademic_file_upload())
						.setParameter("permission_from_central_gov", td.getPermission_from_central_gov())
						.setParameter("admission_intake", td.getAdmission_intake())
						.setParameter("num_of_student_admitted", td.getNum_of_student_admitted())
						.setParameter("postal_address", td.getPostal_address())
						.setParameter("email", td.getEmail())
						.setParameter("website",td.getWebsite())
						.setParameter("remarks", td.getRemarks())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					
					td.setName_of_applicant_university(name_of_applicant_university);
					td.setName_ug_course(name_ug_course);
					td.setAbbre_undergraduate_course(abbre_undergraduate_course);
					td.setInstitute_name(institute_name);
					td.setAcademic_year_applied_for(academic_year_applied_for);
					td.setAcademic_file_upload(academic_file_upload);
					td.setPermission_from_central_gov(permission_from_central_gov);
					td.setAdmission_intake(Integer.parseInt(admission_intake));
					td.setNum_of_student_admitted(Integer.parseInt(num_of_student_admitted));
					td.setPostal_address(postal_address);
					td.setEmail(email);
					td.setWebsite(website);
					td.setRemarks(remarks);
					td.setUser_id(Integer.parseInt(userId));
					td.setCreated_date(date);
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setUser_id(Integer.parseInt(userId));
					td.setUniversity_id(Integer.parseInt(university_id));
					td.setCurrent_month_year(current_mm_yy);
					
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
					ra.addAttribute("msg", "Couldn t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:Degree_rec_Url");
		}

//====================================file uplode=================================================//

public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile(id);
		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {
		}
		return fname;

	}
//	 //==========================================SAVE Form A Add more============================================
//    
//	 @RequestMapping(value = "form_a_action_Add", method = RequestMethod.POST)
//
//		public @ResponseBody Map<String, String> form_a_action_Add(ModelMap Mmap, HttpSession session,
//							HttpServletRequest request) throws ParseException 
//	 	{
//			Map<String, String> data = new HashMap<>();
//		    if(request.getHeader("Referer") == null ) 
//		    { 
//			 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
//			 return data;
//		    }
//
//			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//			Session sessionHQL = this.sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			Date date = new Date();
//			
//			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
//		    String current_mm_yy = month+"-"+year;
//		        
//			String username = session.getAttribute("username").toString();
//			String userId = session.getAttribute("userId").toString();
//			
//			String institute_id=request.getParameter("institute_id");
//			String academic_year_applied_for =request.getParameter("academic_year_applied_for");
//			String academic_file_upload = request.getParameter("filePath");
//			String permission_from_central_gov = request.getParameter("filePath");
//			String admission_intake = request.getParameter("admission_intake");
//			String num_of_student_admitted = request.getParameter("num_of_student_admitted");
////			String country = request.getParameter("country");
////			String state_id = request.getParameter("state_id");
////			String district_id= request.getParameter("district_id");
////			String city = request.getParameter("city");
//			String postal_address = request.getParameter("postal_address");
//			String email = request.getParameter("email");
//			String website = request.getParameter("website");
//			String remarks = request.getParameter("remarks");
//			String university_id = session.getAttribute("university_id").toString();
//			String sib_ch_id = request.getParameter("formID");
//			String current_month_year = request.getParameter("Calendar");
//			
//			try {
//
//				if (Integer.parseInt(sib_ch_id) == 0) {
//					
//					DG_REC_UG_FORM_A fam_sib = new DG_REC_UG_FORM_A();
//					
//					fam_sib.setInstitute_id(institute_id);
//					fam_sib.setAcademic_year_applied_for(academic_year_applied_for);
//					fam_sib.setAcademic_file_upload(academic_file_upload);
//					fam_sib.setPermission_from_central_gov(permission_from_central_gov);
//					fam_sib.setAdmission_intake(Integer.parseInt(admission_intake));
//					fam_sib.setNum_of_student_admitted(Integer.parseInt(num_of_student_admitted));
////					fam_sib.setCountry(country);
////					fam_sib.setState_id(Integer.parseInt(state_id));
////					fam_sib.setDistrict_id(Integer.parseInt(district_id));
////					fam_sib.setCity(city);
//					fam_sib.setPostal_address(postal_address);
//					fam_sib.setEmail(email);
//					fam_sib.setWebsite(website);
//					fam_sib.setRemarks(remarks);
//					fam_sib.setCreated_by(username);
//					fam_sib.setCreated_date(date);
//					fam_sib.setUser_id(Integer.parseInt(userId));
//					fam_sib.setUniversity_id(Integer.parseInt(university_id));
//					fam_sib.setCurrent_month_year(current_mm_yy);
//				
//					int id = (int) sessionHQL.save(fam_sib);
//					data.put("sib_ch_id", String.valueOf(id));
//					data.put("saved","Data Saved as Draft Successfully.") ;
//
//				} 
//				tx.commit();
//
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//				try {
//
//					tx.rollback();
//
//					data.put("error", "Data Not Updated");
//
//				} catch (RuntimeException rbe) {
//
//					data.put("error", "Data Not Updated");
//
//				}
//
//			} finally {
//
//				if (sessionHQL != null) {
//
//					sessionHQL.close();
//				}
//			}
//			return data;
//		}	
//		   @RequestMapping(value = "/count_stu_admitted", method = RequestMethod.POST)
//		   	public @ResponseBody long count_stu_admitted(String institute_id) {
//		   	return UDdao.get_count_student(Integer.parseInt(institute_id));
//	 	}
//----------------------------------------------------------DELETE--ADDMORE------------------------
		   
		   @RequestMapping(value = "form_a_delete_action", method = RequestMethod.POST)
	public @ResponseBody String form_a_delete_action(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

				String msg = "";
	
				Session sessionHQL = this.sessionFactory.openSession();
	
				Transaction tx = sessionHQL.beginTransaction();
	
				int id = Integer.parseInt(request.getParameter("sib_ch_id"));
				try {
	
					String hqlUpdate = "delete from DG_REC_UG_FORM_A where id=:id";
	
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
	
					tx.commit();
	
					sessionHQL.close();
	
					if (app > 0) {
	
						msg = "1";
	
					} else {
	
						msg = "0";
	
					}
	
				} catch (Exception e) {
	
				}
	
				return msg;

	}


	//==========================================SAVE Form E========================================== 
	 
		 @RequestMapping(value = "form_e_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form_e_action(ModelMap Mmap, HttpSession session,HttpServletRequest request,DG_REC_EXAMINERS_APPOINTED ea)
					throws ParseException {

				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ) 
	   { 
	        data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
	        return data;
	   }
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String roleid1 = session.getAttribute("roleid").toString();
				
				String name_of_examiners = request.getParameter("name_of_examiners");
				String subject_examiners = request.getParameter("subject_examiners");
				String year_examiners = request.getParameter("year_examiners");	
				String qualification_examiners =request.getParameter("qualification_examiners");
				String teaching_experience = request.getParameter("teaching_experience");
				int teacher_code = Integer.parseInt(request.getParameter("teacher_code"));
				int reg_number = Integer.parseInt(request.getParameter("reg_number"));
				String d_university_appointment = request.getParameter("d_university_appointment");
				String userId = request.getParameter("userId");
				String form_e_id = request.getParameter("form_e_id");
				String university_id = session.getAttribute("university_id").toString();
				
				 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			      String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
			        
			      String current_mm_yy = month+"-"+year;
				
				 
				  Date d_university = null;
				
				  if (!d_university_appointment.trim().equals("") && !d_university_appointment.equals("DD/MM/YYYY")) {
					d_university = format.parse(d_university_appointment);
				  }
				
			//	try {

					if (Integer.parseInt(form_e_id) == 0) {

						DG_REC_EXAMINERS_APPOINTED FORM_E = new DG_REC_EXAMINERS_APPOINTED();

						FORM_E.setName_of_examiners(name_of_examiners);
						FORM_E.setSubject_examiners(subject_examiners);
						FORM_E.setYear_examiners(year_examiners);
						FORM_E.setQualification_examiners(qualification_examiners);
						FORM_E.setTeaching_experience(teaching_experience);
						FORM_E.setTeacher_code(teacher_code);
						FORM_E.setReg_number(reg_number);
						FORM_E.setD_university_appointment(d_university);
						FORM_E.setCreated_by(username);
						FORM_E.setCreated_date(date);
						FORM_E.setUser_id(Integer.parseInt(userId));
						FORM_E.setUniversity_id(Integer.parseInt(university_id));
						FORM_E.setCurrent_month_year(current_mm_yy);
						int id = (int) sessionHQL.save(FORM_E);

						data.put("form_e_id", String.valueOf(id));

						data.put("saved","Data Saved Successfully.") ;
						
					} else {

						String hql = "update DG_REC_EXAMINERS_APPOINTED set modified_by=:modified_by ,modified_date=:modified_date ,name_of_examiners=:name_of_examiners, "
								+ "subject_examiners=:subject_examiners,year_examiners=:year_examiners,qualification_examiners=:qualification_examiners,"
								+ "teaching_experience=:teaching_experience ,teacher_code=:teacher_code,"
								+ "reg_number=:reg_number,d_university_appointment=:d_university_appointment,status=:status"
								+ " where  id=:id";

						Query query = sessionHQL.createQuery(hql)
								
								.setTimestamp("modified_date", date)
						        .setString("modified_by",username)
								.setString("name_of_examiners", name_of_examiners)
								.setString("subject_examiners",subject_examiners)
								.setString("year_examiners", year_examiners)
								.setString("qualification_examiners",qualification_examiners)
								.setString("teaching_experience", teaching_experience)
								.setInteger("teacher_code", teacher_code)
								.setInteger("reg_number", reg_number)
								.setTimestamp("d_university_appointment",d_university)
							//	.setInteger("inst_status",0)
								.setInteger("id",Integer.parseInt(form_e_id));
								

						int update = query.executeUpdate() > 0 ? 1 : 0;

						if (update == 1)
							data.put("updated", "Data Updated Successfully");
						else
							data.put("error", "Data Not Updated");

					}
					tx.commit();

//				} catch (RuntimeException e) {
//
//					try {
//
//						tx.rollback();
//
//						data.put("error", "Data Not Updated");
//
//					} catch (RuntimeException rbe) {
//
//						data.put("error", "Data Not Updated");
//
//					}
//
//				} finally {
//
//					if (sessionHQL != null) {
//
//						sessionHQL.close();
//
//					}
//
//				}
				return data;
				
		 }
		//==========================================PDF=========================================== 	

		 public String dg(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
		 		throws IOException {
		 	String extension = ""; // add line
		 	String fname = ""; // add line

		 	request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		 	MultipartFile file = mul.getFile(id);
		 	
		 	if (!file.getOriginalFilename().isEmpty()) {

		 		byte[] bytes = file.getBytes();
		 		String mnhFilePath = session.getAttribute(id).toString();

		 		File dir = new File(mnhFilePath);
		 		if (!dir.exists())
		 			dir.mkdirs();
		 		String filename = file.getOriginalFilename();
		 		
		 		int j = filename.lastIndexOf('.');
		 		if (j >= 0) {
		 			extension = filename.substring(j + 1);
		 		}
		 		java.util.Date date1 = new java.util.Date();
		 		fname = dir.getAbsolutePath()
		 				+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
		 						.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
		 				+ "." + extension;

		 		File serverFile = new File(fname);
		 		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		 		stream.write(bytes);
		 		stream.close();

		 	} else {
		 	}
		 	return fname;
		}
			
//==========================================OPEN PAGE INSTITUTE DETAILS UNIVERSITY APPROVED REJECT========================================== //
						
		 @RequestMapping(value = "/Institute_app_rej_Url", method = RequestMethod.GET)
			public ModelAndView Institute_app_rej_Url(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Institute_app_rej_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String roleid = session.getAttribute("roleid").toString();
				Mmap.put("msg", msg);
				LocalDate date_without_time = LocalDate.now();
				Mmap.put("date_without_time", date_without_time);
				Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
				Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
				Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
				Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
				Mmap.put("getMedStateName",common.getMedStateName(sessionFactory));
				Mmap.put("getMedDistrictName",common.getMedDistrictName(sessionFactory));
				
				return new ModelAndView("Ins_app_rej_Tiles");
			}
		//-------------------------------------------View table Form B Admitted Student-------------------------------------------

		 @PostMapping("/getFilter_Admitted_list")	
		public @ResponseBody List<Map<String, Object>> getFilter_Admitted_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name,
				String date_of_admission, String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,
				String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return UDdao.getFilter_Admitted_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status, 
		    		 student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
		     
	    }
		 @PostMapping("/getFilter_Admitted_ListCount")	
		 public @ResponseBody long getFilter_Admitted_ListCount(HttpSession session ,String Search,String id ,
				 String institute_id,String status,String student_name,
					String date_of_admission, String neet_rank,String rank,String marks,String all_india,String state,
					String management_quota,String admission_authority,String court_order,
					String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) throws ParseException {
			 
				 String university_id = session.getAttribute("university_id").toString();
			return UDdao.getFilter_Admitted_ListCount(Search,university_id,institute_id,status, student_name,date_of_admission,neet_rank,rank,marks,
					all_india,state,management_quota,admission_authority,
					court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
			
	    }  
		//-------------------------------------------View table Form C Hospital Student-------------------------------------------

		 @PostMapping("/getFilter_Hospital_list")	
		public @ResponseBody List<Map<String, Object>> getFilter_Hospital_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String id,String institute_id,String status,String name_homoeopathic_medical_clg,
				String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
				String designation,String qualification,String fulltime_parttime,String remarks_form_c) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return UDdao.getFilter_Hospital_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
		     
	    }
		 @PostMapping("/getFilter_HospitalAttached_ListCount")	
		 public @ResponseBody long getFilter_HospitalAttached_ListCount(HttpSession session ,String Search,String id ,
				 String institute_id,String status,String name_homoeopathic_medical_clg,
					String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
					String designation,String qualification,String fulltime_parttime,String remarks_form_c) throws ParseException {
			 
				 String university_id = session.getAttribute("university_id").toString();
			return UDdao.getFilter_HospitalAttached_ListCount(Search,university_id,institute_id,status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
					mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
			
	    }  
	//-------------------------------------------View table Form D Migrated Student-------------------------------------------

	 @PostMapping("/getFilter_Migrated")	
	
	public @ResponseBody List<Map<String, Object>> getFilter_Migrated(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String id,String institute_id,String institute_status,String name_of_inst,
			String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,
			String remarks_form_d) throws ParseException {
		 
		 String university_id = session.getAttribute("university_id").toString();
	     return UDdao.getFilter_Migrated_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_status,institute_id,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);

    }
	 @PostMapping("/getFilter_Migrated_ListCount")	
	 public @ResponseBody long getFilter_Migrated_ListCount(HttpSession session ,String Search,String id,String institute_id,String institute_status,String name_of_inst,
				String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,
				String remarks_form_d) throws ParseException {
			 
		String university_id = session.getAttribute("university_id").toString();
		return UDdao.getFilter_Migrated_ListCount(Search,university_id,institute_status,institute_id,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
				university_enrollment_number, remarks_form_d);
    } 
	 
	//-------------------------------------------View table Form D Migrated From Student-------------------------------------------

		 @PostMapping("/getFilter_Migrated_From")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Migrated_From_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String id,String institute_id,String institute_status, String name_of_institution,
				String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return UDdao.getFilter_Migrated_From_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_status,institute_id,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
						university_enroll_num, remarks_migrated);

	    }
		 @PostMapping("/getFilter_Migrated_From_ListCount")	
		 public @ResponseBody long getFilter_Migrated_From_ListCount(HttpSession session ,String Search,String id,String institute_id,String institute_status, String name_of_institution,
					String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated) throws ParseException {
				 
			String university_id = session.getAttribute("university_id").toString();
			return UDdao.getFilter_Migrated_From_ListCount(Search,university_id,institute_status,institute_id,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);
	    } 
	
	//-------------------------------------------View table Form F Intern Student-------------------------------------------

		 @PostMapping("/getFilter_Intern")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Intern_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return UDdao.getFilter_Intern_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_status,institute_id,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
						year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);

	    }
		 @PostMapping("/getFilter_Intern_ListCount")	
		 public @ResponseBody long getFilter_Intern_ListCount(HttpSession session ,String Search,String id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
					String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
					String remark_form_f) throws ParseException {
				 
				// String userId = session.getAttribute("userId").toString();
				 String university_id = session.getAttribute("university_id").toString();
				 //String user_id=(userId);
		     	 
			return UDdao.getFilter_Intern_ListCount(Search,university_id,institute_status,institute_id,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
	    }  
		 /////////////////// Reject ///////////////
				
		 @RequestMapping(value = "reject_action", method = RequestMethod.POST)
			public @ResponseBody String reject_action(ModelMap Mmap, String id,String reject_remarks,HttpSession session, HttpServletRequest request)
					throws ParseException {
							
			 	String msg = "";
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				String username = session.getAttribute("username").toString();
				Date date = new Date();
				String userId = request.getParameter("user_id");
				System.out.println("---user_id------"+userId);
		     	String university_id = session.getAttribute("university_id").toString();
					        
							try {
								
								String hqlUpdate = "update DG_REC_ADMITTED_STUDENT set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
								int app = sessionHQL.createQuery(hqlUpdate)
										.setInteger("university_approved_status",-1)
										.setInteger("inst_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
								
								Long c6 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY where tbl_id=:id")
		                                 .setParameter("id",Integer.parseInt(id)).uniqueResult();
		                          
		                          if(c6 == 0) {
		                        	  DG_REC_CLARIFICATION_DATA_HISTORY trx = new DG_REC_CLARIFICATION_DATA_HISTORY();					
											trx.setTbl_id(Integer.parseInt(id));
											trx.setClarification_remarks(reject_remarks);
											trx.setUniversity_id(Integer.parseInt(university_id));
											trx.setDate_of_current(date);
											trx.setCreated_date(date);
											trx.setCreated_by(username);
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
		                          }
										
								String hqlUpdate1 = "update DG_REC_HOSPITAL_ATTACHED set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
								System.out.println("----hqlUpdate1-"+hqlUpdate1);
								int app1 = sessionHQL.createQuery(hqlUpdate1)
										.setInteger("university_approved_status",-1)
										.setInteger("inst_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
								
								Long c6a = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY where tbl_id=:id")
		                                 .setParameter("id",Integer.parseInt(id)).uniqueResult();
		                          
		                          if(c6a == 0) {
		                        	  DG_REC_CLARIFICATION_DATA_HISTORY trx = new DG_REC_CLARIFICATION_DATA_HISTORY();					
											trx.setTbl_id(Integer.parseInt(id));
											trx.setClarification_remarks(reject_remarks);
											trx.setUniversity_id(Integer.parseInt(university_id));
											trx.setDate_of_current(date);
											trx.setCreated_date(date);
											trx.setCreated_by(username);
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
		                          }
		                          
								String hqlUpdate2 = "update DG_REC_MIGRATED_STUDENT_SUB_CHILD set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
								System.out.println("----hqlUpdate2-"+hqlUpdate2);
								int app2 = sessionHQL.createQuery(hqlUpdate2)
										.setInteger("university_approved_status",-1)
										.setInteger("inst_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
								
								Long c6b = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY where tbl_id=:id")
		                                 .setParameter("id",Integer.parseInt(id)).uniqueResult();
		                          
		                          if(c6b == 0) {
		                        	  DG_REC_CLARIFICATION_DATA_HISTORY trx = new DG_REC_CLARIFICATION_DATA_HISTORY();					
											trx.setTbl_id(Integer.parseInt(id));
											trx.setClarification_remarks(reject_remarks);
											trx.setUniversity_id(Integer.parseInt(university_id));
											trx.setDate_of_current(date);
											trx.setCreated_date(date);
											trx.setCreated_by(username);
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
		                          }
		                          
								String hqlUpdate3 = "update DG_REC_MIGRATED_STUDENT_FROM set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
								System.out.println("----hqlUpdate3-"+hqlUpdate3);
								int app3 = sessionHQL.createQuery(hqlUpdate3)
										.setInteger("university_approved_status",-1)
										.setInteger("inst_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
								
								Long c6c = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY where tbl_id=:id")
		                                 .setParameter("id",Integer.parseInt(id)).uniqueResult();
		                          
		                          if(c6c == 0) {
		                        	  DG_REC_CLARIFICATION_DATA_HISTORY trx = new DG_REC_CLARIFICATION_DATA_HISTORY();					
											trx.setTbl_id(Integer.parseInt(id));
											trx.setClarification_remarks(reject_remarks);
											trx.setUniversity_id(Integer.parseInt(university_id));
											trx.setDate_of_current(date);
											trx.setCreated_date(date);
											trx.setCreated_by(username);
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
		                          }
		                          
								String hqlUpdate4 = "update DG_REC_INTERN_STUDENT_COURSE set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
								System.out.println("----hqlUpdate4-"+hqlUpdate4);
								int app4 = sessionHQL.createQuery(hqlUpdate4)
										.setInteger("university_approved_status",-1)
										.setInteger("inst_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();

								Long c6d = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY where tbl_id=:id")
		                                 .setParameter("id",Integer.parseInt(id)).uniqueResult();
		                          
		                          if(c6d == 0) {
		                        	  DG_REC_CLARIFICATION_DATA_HISTORY trx = new DG_REC_CLARIFICATION_DATA_HISTORY();					
											trx.setTbl_id(Integer.parseInt(id));
											trx.setClarification_remarks(reject_remarks);
											trx.setUniversity_id(Integer.parseInt(university_id));
											trx.setDate_of_current(date);
											trx.setCreated_date(date);
											trx.setCreated_by(username);
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
		                          }
								tx.commit();
								sessionHQL.close();
								System.err.println("uni---APPROVED-APPS-"+app+"----------uni---APPROVED-APPS-"+app1);

								if ((app > 0) || (app1 > 0) || (app2 > 0) || (app3 > 0) || (app4 > 0)) {
									msg = "Rejected Successfully";
								} else {
									msg = "Rejection Unsuccessfull";
								}
								
								sessionHQL.close();
							  } catch (Exception e) {
								  e.printStackTrace();
							}
							return msg;
						}	
/*------------------------------------------------STUDENT DETAILS--------------------------------------------------------*/
				
//==========================================OPEN PAGE STUDENT DETAILS========================================== 
				
				@RequestMapping(value = "/Student_Details_UG", method = RequestMethod.GET)
				public ModelAndView Student_Details_UG(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
					//SECURITY ABHISHEK
					if(request.getHeader("Referer") == null ) { 
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Student_Details_UG", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						String userid = session.getAttribute("userId_for_jnlp").toString();
					String roleid = session.getAttribute("roleid").toString();
					String userId = session.getAttribute("userId").toString();
					System.err.println("UserId---"+userId);
					Mmap.put("msg", msg);
					LocalDate date_without_time = LocalDate.now();
					Mmap.put("date_without_time", date_without_time);
					Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
					Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
					Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
//					Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
					Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
					
					 Mmap.put("getper_dtl", UDdao.getpersonal_details(Integer.parseInt(userid)));
					 
					return new ModelAndView("Student_detail_Tiles");
				}
				
				
//==========================================SAVE Form B Admitted Student==================================== 	
				
				@PostMapping(value = "/form_b_action")
		         
		         public @ResponseBody Map<String, String> form_b_action(@Validated  DG_REC_ADMITTED_STUDENT td, BindingResult re,
		                         HttpServletRequest request, ModelMap model, HttpSession session, Principal college,MultipartHttpServletRequest mul,
		                         RedirectAttributes ra,@RequestParam(value = "upload_excel", required = false) MultipartFile upload) throws ParseException,
		                         IOException {
		                 Map<String, String> data = new HashMap<>();
		             if(request.getHeader("Referer") == null ) 
		             { 
		                  session.invalidate();
		                  data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
		                  return data;
		             }  
		             
		               // DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		                  DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				 		  Session sessionHQL = this.sessionFactory.openSession();
				 		  Transaction tx = sessionHQL.beginTransaction();
				 		  String date_of_admission = request.getParameter("date_of_admission");
			 		      String date_of_enroll_university = request.getParameter("date_of_enroll_university");
				          String date_of_intern_compl = request.getParameter("date_of_intern_compl");
				 	      Date dt_adm = null;
						  Date dt_university = null;
						  Date dt_completion = null;
						  System.err.println("---dt_adm---"+dt_adm);
						  
						  if (!date_of_admission.trim().equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
							  dt_adm = format.parse(date_of_admission);
						  }
						  if (!date_of_enroll_university.trim().equals("") && !date_of_enroll_university.equals("DD/MM/YYYY")) {
							   dt_university = format.parse(date_of_enroll_university);
						  }
						  if (!date_of_intern_compl.trim().equals("") && !date_of_intern_compl.equals("DD/MM/YYYY")) {
							   dt_completion = format.parse(date_of_intern_compl);
						  }
		             
						try {
							
				 			 String h_id= request.getParameter("h_id");
				 			 System.err.println("H-ID---"+h_id);
					         Date date = new Date();
					         String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
					         String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
					         String current_mm_yy = month+"-"+year;
				 			 String userId = session.getAttribute("userId").toString();
					     	 String university_id = session.getAttribute("university_id").toString();
			               

					 			if(h_id.equals("a1")) {
					 				System.err.println("---fff-upload "+upload);
									if (upload.isEmpty()) {
										ra.addAttribute("msg", "Please Upload File.");
									}
								
									File file = new File(expcon.fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
									
									String extention="";
									int z = upload.getOriginalFilename().lastIndexOf('.');
									if (z >= 0) {
										extention = upload.getOriginalFilename().substring(z + 1);
									}
									
									if(!extention.equals("xls")) {
										ra.addAttribute("msg", "Please Select Excel File");
									}
									
									FileInputStream fis = new FileInputStream(file);
									HSSFWorkbook wb = new HSSFWorkbook(fis);
									HSSFSheet sheet = wb.getSheetAt(0);
									Row row_head = sheet.getRow(0);
//						if (!row_head.getCell(0).getStringCellValue().equals("Name Of Student")) {
//							ra.addAttribute("msg", "Please Enter Correct File Header for Name");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
//						if (!row_head.getCell(1).getStringCellValue().equals("Mobile_No")) {
//							ra.addAttribute("msg", "Please Enter Correct File Header for Mobile No");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
//						if (!row_head.getCell(2).getStringCellValue().equals("Email")) {
//							ra.addAttribute("msg", "Please Enter Correct File Header for Email");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
//						if (!row_head.getCell(3).getStringCellValue().equals("Passing_Year")) {
//							ra.addAttribute("msg", "Please Enter Correct File Header for Passing Year");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
//						if (!row_head.getCell(4).getStringCellValue().equals("Batch")) {
//							ra.addAttribute("msg", "Please Enter Correct File Header for Batch");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
//						if (sheet.getLastRowNum() == 0) {
//							ra.addAttribute("msg", "Please Enter Data in Atleast One Row");
//							//return new ModelAndView("redirect:clg_Add_Alumni_Url");
//						}
						
						for (int i = 1; i <= sheet.getLastRowNum(); i++) {
							
							Row row = sheet.getRow(i);
							if (row.getCell(0) == null) {
								break;
							}
							
							for (int i1 = 0; i1 <= 10; i1++) {
								
							String varforval = "";
								if(i1==0) {
									varforval = "Name Of Student";
								}
								if(i1==1) {
									varforval = "Date of Admission";
								}
								if(i1==2) {
									varforval = "Rank";
								}
								if(i1==3) {
									varforval = "Marks";
								}
								if(i1==4) {
									varforval = "All India(Yes/No)";
								}
								if(i1==5) {
									varforval = "State(Yes/No)";
								}
								if(i1==6) {
									varforval = "Admission Authority(Yes/No)";
								}
								if(i1==7) {
									varforval = "Court Order & Others";
								}
//								if(i1==8) {
//									varforval = "Upload File Court Order";
//								}
								if(i1==8) {
									varforval = "Date of Enrollment In University";
								}
								if(i1==9) {
									varforval = "University Enrollment Number";
								}
								if(i1==10) {
									varforval = "Date Of Internship Completion";
								}
								if(i1==11) {
									varforval = "Remarks";
								}
								
								Cell cell = row.getCell(i1);
								if(cell == null) {
									ra.addAttribute("msg", "Please Enter "+varforval+" in row "+i);
								}
								
								String value = "";
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									value = cell.getStringCellValue();
									break;
								case Cell.CELL_TYPE_NUMERIC:
									if (HSSFDateUtil.isCellDateFormatted(cell)) {
										value = String.valueOf(cell.getDateCellValue());
									} 
									else {
										value = String.valueOf((long) cell.getNumericCellValue());
									}
									//value=String.valueOf(cell.get());
									break;
								case Cell.CELL_TYPE_BOOLEAN:
									value = String.valueOf(cell.getBooleanCellValue());
									break;
								default:
								}
								
								if (row_head.getCell(i1).getStringCellValue().equals("Name Of Student")) {
									System.err.println("Name Of Student---"+value);
									td.setStudent_name(value);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Date of Admission")) {
									td.setDate_of_admission(dt_adm);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Rank")) {
									td.setRank(Integer.parseInt(value));
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Marks")) {
									td.setMarks(Integer.parseInt(value));
								}
								if (row_head.getCell(i1).getStringCellValue().equals("All India(Yes/No)")) {
									td.setAll_india(value);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("State(Yes/No)")) {
									td.setState(value);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Admission Authority(Yes/No)")) {
									td.setAdmission_authority(value);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Court Order & Others")) {
									td.setCourt_order(value);
								}
//								if (row_head.getCell(i1).getStringCellValue().equals("Upload File Court Order")) {
//									td.setCourt_order_file(value);
//								}
								if (row_head.getCell(i1).getStringCellValue().equals("Date Of Enrollment University")) {
									td.setDate_of_enroll_university(dt_university);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("University Enrollment Number")) {
									td.setUni_enroll_number(value);
								}
								if (row_head.getCell(i1).getStringCellValue().equals("Remarks")) {
									td.setRemarks_form_b(value);
								}
								td.setCurrent_month_year(current_mm_yy);
							}
							int idm = (int) sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							
							if(idm > 0) {
								data.put("saved","Data Saved as Draft Successfully.") ;
							}
							tx.commit();
						}
					
		 			}
		 			
		 			if(h_id.equals("a2")) {
			            

						 String username = session.getAttribute("username").toString();
						 String roleid1 = session.getAttribute("roleid").toString();
		 				 String student_name = request.getParameter("student_name");
		 				 String neet_rank = request.getParameter("neet_rank");
		 				 System.out.println("--neet_rank-"+neet_rank);
		 				 String rank = request.getParameter("rank");
		 				 System.out.println("--rank---"+rank);
				         int marks = Integer.parseInt(request.getParameter("marks"));
				         String all_india = request.getParameter("all_india");   
				         String state = request.getParameter("state");
				         String management_quota = request.getParameter("management_quota");
				         
				         String admission_authority =request.getParameter("admission_authority");
				         String court_order = request.getParameter("court_order");
				         String uni_enroll_number=request.getParameter("uni_enroll_number");
				         String remarks_form_b = request.getParameter("remarks_form_b");
				         String form_b_id = request.getParameter("formID");
				        
				         String current_month_year = request.getParameter("Calendar");
				         
				         String court_order_file = doc(request, mul, session, "filePath");
				         	System.out.println("--document_upload-"+court_order_file);

						if (Integer.parseInt(form_b_id) == 0) {
							
							DG_REC_ADMITTED_STUDENT form_b = new DG_REC_ADMITTED_STUDENT();

							form_b.setStudent_name(student_name);
							form_b.setDate_of_admission(dt_adm);
							form_b.setNeet_rank(neet_rank);
							 if (request.getParameter("neet_rank") != null) {
		          					if (request.getParameter("neet_rank").equalsIgnoreCase("Yes")) {
		          						System.out.println("----neet_rank----"+neet_rank);
		          						form_b.setRank(Integer.parseInt(rank));
		          						System.out.println("----rank----"+rank);
		          					}
		          				}
							form_b.setMarks(marks);
							form_b.setAll_india(all_india);
							form_b.setState(state);
							form_b.setManagement_quota(management_quota);
							form_b.setAdmission_authority(admission_authority);
							form_b.setCourt_order(court_order);
							form_b.setCourt_order_file(court_order_file);
							form_b.setDate_of_enroll_university(dt_university);
							form_b.setUni_enroll_number(uni_enroll_number);
							form_b.setDate_of_intern_compl(dt_completion);
							form_b.setRemarks_form_b(remarks_form_b);
							form_b.setCreated_by(username);
							form_b.setCreated_date(date);
							form_b.setCurrent_month_year(current_mm_yy);
							form_b.setUniversity_id(Integer.parseInt(university_id));
							form_b.setUser_id(Integer.parseInt(userId));
							form_b.setInst_status(1);
							
		 					int id = (int) sessionHQL.save(form_b);
							data.put("form_b_id", String.valueOf(id));

							data.put("saved","Data Saved as Draft Successfully.") ;

						} 
						tx.commit();
		 			}
			} catch (RuntimeException e) {
				
				e.printStackTrace();

				try {

					tx.rollback();

					data.put("error", "Data Not Updated");

				} catch (RuntimeException rbe) {

					data.put("error", "Data Not Updated");
				}

			} finally {

				if (sessionHQL != null) {

					sessionHQL.close();
				}
			}
			return data;
		}				
				
				public String doc(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
					
					System.err.println("id----"+id);
				
				String extension=""; //add line
				String fname = ""; //add line
				
				request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
				
				MultipartFile file = mul.getFile(id);
				if (!file.getOriginalFilename().isEmpty()) {
					
					byte[] bytes = file.getBytes();
					String  mnhFilePath = session.getAttribute(id).toString();
					
			        File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();
							
					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j+1);
					}
					java.util.Date date1= new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
					
					File serverFile = new File(fname);	               
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();

				}else {

					
				}
				return fname;
				
				}
				
				
				//==========================================SAVE Form C========================================== 	

//				 @RequestMapping(value = "form_c_action", method = RequestMethod.POST)
//
//						public @ResponseBody Map<String, String> form_c_action(ModelMap Mmap, HttpSession session,
//								HttpServletRequest request) throws ParseException {
//
//						System.out.println("---copy_of_mou--"+request.getParameter("filePath"));
//					 
//						Map<String, String> data = new HashMap<>();
//						if(request.getHeader("Referer") == null ) 
//				        { 
//				             data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
//				             return data;
//				        }
//						
//						DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
//						Date date = new Date();
//						
//						String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//					    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
//					    String current_mm_yy = month+"-"+year;
//						String username = session.getAttribute("username").toString();
//						
//						String name_homoeopathic_medical_clg = request.getParameter("name_homoeopathic_medical_clg");
//						String attached_homoeopath_hospital = request.getParameter("attached_homoeopath_hospital");
//						String super_speciality_hospital = request.getParameter("super_speciality_hospital");	
//						String mou_date = request.getParameter("mou_date");
//						String copy_of_mou1 = request.getParameter("filePath");
//						String name_of_hospital_staff =request.getParameter("name_of_hospital_staff");
//						String designation = request.getParameter("designation");
//						String qualification = request.getParameter("qualification");
//						String fulltime_parttime = request.getParameter("fulltime_parttime");
//						System.out.println("-----------fulltime_parttime------"+fulltime_parttime);
//						String remarks_form_c = request.getParameter("remarks_form_c");
//					
//						String userId = request.getParameter("userId");
//						String form_c_id = request.getParameter("formID");
//						String university_id = session.getAttribute("university_id").toString();
//						String current_month_year = request.getParameter("Calendar");
//
//						  Date m_dt = null;
//						 
//						  if (!mou_date.trim().equals("") && !mou_date.equals("DD/MM/YYYY")) {
//							m_dt = format.parse(mou_date);
//						  }
//
//						try {
//							
//							if (Integer.parseInt(form_c_id) == 0) {
//
//								DG_REC_HOSPITAL_ATTACHED td = new DG_REC_HOSPITAL_ATTACHED();
//
//								td.setName_homoeopathic_medical_clg(name_homoeopathic_medical_clg);
//								td.setAttached_homoeopath_hospital(attached_homoeopath_hospital);
//								td.setSuper_speciality_hospital(super_speciality_hospital);
//								td.setMou_date(m_dt);
//								td.setCopy_of_mou(copy_of_mou1);
//								td.setName_of_hospital_staff(name_of_hospital_staff);
//								td.setDesignation(designation);
//								td.setQualification(qualification);
//								td.setFulltime_parttime(fulltime_parttime);
//								td.setRemarks_form_c(remarks_form_c);
//								td.setCreated_by(username);
//								td.setCreated_date(date);
//								td.setUniversity_id(Integer.parseInt(university_id));
//								td.setUser_id(Integer.parseInt(userId));
//								td.setCurrent_month_year(current_mm_yy);
//								
//								int id = (int) sessionHQL.save(td);
//								data.put("form_c_id", String.valueOf(id));
//								data.put("saved","Data Saved as Draft Successfully.") ;
//			               	   
//							}
//							tx.commit();
//
//						} catch (RuntimeException e) {
//
//							try {
//
//								tx.rollback();
//
//								data.put("error", "Data Not Updated");
//
//							} catch (RuntimeException rbe) {
//
//								data.put("error", "Data Not Updated");
//
//							}
//
//						} finally {
//
//							if (sessionHQL != null) {
//
//								sessionHQL.close();
//
//							}
//
//						}
//						return data;
//					}    
     
        //==========================================SAVE Form D Migrated Student To Other College============================================
          
		 @RequestMapping(value = "form_d_action_Add", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form_d_action_Add(ModelMap Mmap, HttpSession session,HttpServletRequest request,DG_REC_MIGRATED_STUDENT_SUB_CHILD mc) 
					throws ParseException {

				Map<String, String> data = new HashMap<>();
			    if(request.getHeader("Referer") == null ) 
			    { 
				 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
				 return data;
			}
			    
			  

				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				
				 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			     String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
			         
			     String current_mm_yy = month+"-"+year;
			        
				String username = session.getAttribute("username").toString();
				String userId = session.getAttribute("userId").toString();
				String name_of_inst=request.getParameter("name_of_inst");
				String student_name_to_migrated =request.getParameter("student_name_to_migrated");
				String migrated_dt_to1 = request.getParameter("migrated_dt_to1");
				String professional_year_migrated = request.getParameter("professional_year_migrated");
				String university_enrollment_number = request.getParameter("university_enrollment_number");
				String remarks_form_d = request.getParameter("remarks_form_d");
				String university_id = session.getAttribute("university_id").toString();
				String sib_ch_id = request.getParameter("sib_ch_id");
				String current_month_year = request.getParameter("Calendar");

				Date to_dt = null;
				
				if (!migrated_dt_to1.trim().equals("") && !migrated_dt_to1.equals("DD/MM/YYYY")) {
					to_dt = format.parse(migrated_dt_to1);
				}
				
				
				try {

					if (Integer.parseInt(sib_ch_id) == 0) {
						
						DG_REC_MIGRATED_STUDENT_SUB_CHILD fam_sib = new DG_REC_MIGRATED_STUDENT_SUB_CHILD();
						
						fam_sib.setName_of_inst(name_of_inst);
						fam_sib.setStudent_name_to_migrated(student_name_to_migrated);
						fam_sib.setMigrated_dt_to(to_dt);
						fam_sib.setProfessional_year_migrated(professional_year_migrated);
						fam_sib.setUniversity_enrollment_number(university_enrollment_number);
						fam_sib.setRemarks_form_d(remarks_form_d);
						fam_sib.setCreated_by(username);
						fam_sib.setCreated_date(date);
						fam_sib.setUser_id(Integer.parseInt(userId));
						fam_sib.setUniversity_id(Integer.parseInt(university_id));
						fam_sib.setCurrent_month_year(current_mm_yy);
						fam_sib.setInst_status(1);
						
						int id = (int) sessionHQL.save(fam_sib);
						data.put("sibId", String.valueOf(id));
						data.put("saved","Data Saved as Draft Successfully.") ;

					} 
					tx.commit();

				} catch (RuntimeException e) {
					e.printStackTrace();
					try {

						tx.rollback();

						data.put("error", "Data Not Updated");

					} catch (RuntimeException rbe) {

						data.put("error", "Data Not Updated");

					}

				} finally {

					if (sessionHQL != null) {

						sessionHQL.close();
					}
				}
				return data;
			}	
//----------------------------------------------------------DELETE--ADDMORE------------------------
			
@RequestMapping(value = "form_d_Add_delete_action", method = RequestMethod.POST)
		public @ResponseBody String form_d_Add_delete_action(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

					String msg = "";
		
					Session sessionHQL = this.sessionFactory.openSession();
		
					Transaction tx = sessionHQL.beginTransaction();
		
					int id = Integer.parseInt(request.getParameter("sib_ch_id"));
					try {
		
						String hqlUpdate = "delete from DG_REC_MIGRATED_STUDENT_SUB_CHILD where id=:id";
		
						int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
		
						tx.commit();
		
						sessionHQL.close();
		
						if (app > 0) {
		
							msg = "1";
		
						} else {
		
							msg = "0";
		
						}
		
					} catch (Exception e) {
		
					}
		
					return msg;

		}

//==========================================SAVE Form D Migrated Student From Other College============================================

@RequestMapping(value = "form_d_action_from", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> form_d_action_from(ModelMap Mmap, HttpSession session,HttpServletRequest request,DG_REC_MIGRATED_STUDENT_FROM ma) 
			throws ParseException {

		Map<String, String> data = new HashMap<>();
	    if(request.getHeader("Referer") == null ) 
	    { 
		 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
		 return data;
	}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
	         
	    String current_mm_yy = month+"-"+year;
	    
		String username = session.getAttribute("username").toString();
		String userId = session.getAttribute("userId").toString();
		String name_of_institution=request.getParameter("name_of_institution");
		String name_of_students_migrated =request.getParameter("name_of_students_migrated");
		String dt_of_migration1 = request.getParameter("dt_of_migration1");
		String professional_year = request.getParameter("professional_year");
		String university_enroll_num = request.getParameter("university_enroll_num");
		String remarks_migrated = request.getParameter("remarks_migrated");
		String university_id = session.getAttribute("university_id").toString();
		String sib_xy_id = request.getParameter("sib_xy_id");
		String current_month_year = request.getParameter("Calendar");

		Date to_dt_migra = null;
		
		if (!dt_of_migration1.trim().equals("") && !dt_of_migration1.equals("DD/MM/YYYY")) {
			to_dt_migra = format.parse(dt_of_migration1);
		}
		
		try {

			if (Integer.parseInt(sib_xy_id) == 0) {
				DG_REC_MIGRATED_STUDENT_FROM fb_sib = new DG_REC_MIGRATED_STUDENT_FROM();
				
				fb_sib.setName_of_institution(name_of_institution);
				fb_sib.setName_of_students_migrated(name_of_students_migrated);
				fb_sib.setDt_of_migration(to_dt_migra);
				fb_sib.setProfessional_year(professional_year);
				fb_sib.setUniversity_enroll_num(university_enroll_num);
				fb_sib.setRemarks_migrated(remarks_migrated);
				fb_sib.setCreated_by(username);
				fb_sib.setCreated_date(date);
				fb_sib.setUser_id(Integer.parseInt(userId));
				fb_sib.setUniversity_id(Integer.parseInt(university_id));
				fb_sib.setCurrent_month_year(current_mm_yy);
				fb_sib.setInst_status(1);
				int id = (int) sessionHQL.save(fb_sib);
				data.put("xyId", String.valueOf(id));
				data.put("saved","Data Saved Sucessfully.") ;

			} 
			tx.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();
			}
		}
		return data;
	}	
//-----------------------------------------------------DELETE--ADDMORE-------------------------------------------------------

@RequestMapping(value = "form_d_Add_delete_action_from", method = RequestMethod.POST)

public @ResponseBody String form_d_Add_delete_action_from(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

			String msg = "";

			Session sessionHQL = this.sessionFactory.openSession();

			Transaction tx = sessionHQL.beginTransaction();

			int id = Integer.parseInt(request.getParameter("sib_xy_id"));
			try {

				String hqlUpdate = "delete from DG_REC_MIGRATED_STUDENT_FROM where id=:id";

				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

				tx.commit();

				sessionHQL.close();

				if (app > 0) {

					msg = "1";

				} else {

					msg = "0";

				}

			} catch (Exception e) {

			}

			return msg;

}
			
//========================================================SAVE Form F Intern Student=============================================== 	

		 @RequestMapping(value = "form_f_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form_f_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)
					throws ParseException {
System.out.println("----action---");
				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ) 
	            { 
	                 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
	                 return data;
	            }
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				
				 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			     String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
			         
			     String current_mm_yy = month+"-"+year;
			     
				String username = session.getAttribute("username").toString();
				String roleid1 = session.getAttribute("roleid").toString();
				String name_of_students = request.getParameter("name_of_students");
				String year_of_admission = request.getParameter("year_of_admission");
				String date_of_result_final_year = request.getParameter("date_of_result_final_year");	
				int provisional_reg_no = Integer.parseInt(request.getParameter("provisional_reg_no"));
				String year_of_provisional_reg = request.getParameter("year_of_provisional_reg");
				String date_of_starting_internship = request.getParameter("date_of_starting_internship");
				String date_of_completion_internship = request.getParameter("date_of_completion_internship");
				String remark_form_f = request.getParameter("remark_form_f");
				String userId = session.getAttribute("userId").toString();
				String university_id = session.getAttribute("university_id").toString();
				String form_f_id = request.getParameter("form_f_id");
				String current_month_year = request.getParameter("Calendar");
				
				  Date f_dt = null;
				  Date dt_internship = null;
				  Date dt_completion_internship = null;
				  
				if (!date_of_result_final_year.trim().equals("") && !date_of_result_final_year.equals("DD/MM/YYYY")) {
						f_dt = format.parse(date_of_result_final_year);
				  }
				if (!date_of_starting_internship.trim().equals("") && !date_of_starting_internship.equals("DD/MM/YYYY")) {
					dt_internship = format.parse(date_of_starting_internship);
				}
				if (!date_of_completion_internship.trim().equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
					dt_completion_internship = format.parse(date_of_completion_internship);
				}
				
			try {

					if (Integer.parseInt(form_f_id) == 0) {

						DG_REC_INTERN_STUDENT_COURSE td = new DG_REC_INTERN_STUDENT_COURSE();

						td.setName_of_students(name_of_students);
						td.setYear_of_admission(year_of_admission);
						td.setDate_of_result_final_year(f_dt);
						td.setProvisional_reg_no(provisional_reg_no);
						td.setYear_of_provisional_reg(year_of_provisional_reg);
						td.setDate_of_starting_internship(dt_internship);
						td.setDate_of_completion_internship(dt_completion_internship);
						td.setRemark_form_f(remark_form_f);
						td.setUniversity_id(Integer.parseInt(university_id));
						td.setCreated_by(username);
						td.setCreated_date(date);
						td.setCurrent_month_year(current_mm_yy);
						td.setUser_id(Integer.parseInt(userId));
						td.setInst_status(1);
						int id = (int) sessionHQL.save(td);

						data.put("form_f_id", String.valueOf(id));
						data.put("saved","Data Saved Sucessfully.") ;
						 
					} 
					
					tx.commit();
				} catch (RuntimeException e) {

					try {
						tx.rollback();	
						data.put("error", "Data Not Updated");

					} catch (RuntimeException rbe) {

						data.put("error", "Data Not Updated");

					}

				} finally {

					if (sessionHQL != null) {

						sessionHQL.close();
					}
				}
				return data;
			}	

		 /////////// Declaration Save ///////////
		 @PostMapping(value = "/form_enclosure_action")
			public ModelAndView form_enclosure_action(@Validated @ModelAttribute("UG_enclosureCMD") DG_REC_ENCLOSURE td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
				
					System.out.println("----in dec-formmmm-");
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					@SuppressWarnings("unused")
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Date date = new Date();
					
					String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
				    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
				    String current_mm_yy = month+"-"+year;
				        
					String username = session.getAttribute("username").toString();
					System.out.println("--------- username-----------"+username);
					String userId = session.getAttribute("userId").toString();

					String upload_signature = gd(request, mul, session, "upload_signature");
					String hid_upload_file = request.getParameter("hid_upload_file");
					String declaration_date = request.getParameter("declaration_date");
					String current_month_year = request.getParameter("Calendar");
					String university_id = session.getAttribute("university_id").toString();
					String user_id = session.getAttribute("userId").toString();
					 Date d_decl = null;
					  
					  if (!declaration_date.trim().equals("") && !declaration_date.equals("DD/MM/YYYY")) {
						  d_decl = format.parse(declaration_date);
					  }
					  if (upload_signature=="") {
						  upload_signature = hid_upload_file;
						}
					int id = td.getId() > 0 ? td.getId() : 0;
					
					try {
						Long c = (Long) sessionHQL.createQuery(
								
								"select count(id) from  DG_REC_ENCLOSURE where upload_signature=:upload_signature and "
								+ "declaration_date=:declaration_date and  id !=:id")
								
								.setParameter("upload_signature", td.getUpload_signature())
								.setParameter("declaration_date",td.getDeclaration_date())
								.setParameter("id", id).uniqueResult();
						
						if (id == 0) {
							td.setUpload_signature(upload_signature);
							td.setDeclaration_date(d_decl);
							td.setUser_id(Integer.parseInt(userId));
							td.setCreated_date(date);
							td.setCreated_by(username);
							td.setCreated_date(date);
							td.setUser_id(Integer.parseInt(userId));
							td.setUniversity_id(Integer.parseInt(university_id));
							td.setCurrent_month_year(current_mm_yy);
							td.setInst_status(1);
							if (c == 0) {
								sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();

								ra.addAttribute("msg", "Data Submitted Successfully.");
							} else {
								ra.addAttribute("msg", "Data already Exist.");
							}
						} 
						tx.commit();
					} catch (RuntimeException e) {
						try {
							ra.addAttribute("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ra.addAttribute("msg", "Couldn t roll back transaction " + rbe);
						}
						throw e;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
					return new ModelAndView("redirect:Student_Details_UG");
				}
		 
		 
		 @RequestMapping(value = "/DocumentImagePath", method = RequestMethod.GET)
			public void DocumentImagePath(@ModelAttribute("i_id") String id,@ModelAttribute("myimg") String myImg, ModelMap model,
					HttpServletRequest request, HttpServletResponse response) throws IOException {
				
				final int BUFFER_SIZE = 4096;

				String i_id = id;
				String filePath = SDdao.getImagePath(i_id,myImg);
				model.put("filePath", filePath);
				ServletContext context = request.getSession().getServletContext();

				try {

					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//						request.getRealPath("/") + "/srv/Document/No_Image.jpg";

						File downloadFile = new File(fullPath);

						FileInputStream inputStream = new FileInputStream(downloadFile);

						String mimeType = context.getMimeType(fullPath);

						if (mimeType == null) {

							mimeType = "application/octet-stream";

						}

						response.setContentType(mimeType);

						response.setContentLength((int) downloadFile.length());

						OutputStream outStream = response.getOutputStream();

						byte[] buffer = new byte[BUFFER_SIZE];

						int bytesRead = -1;

						while ((bytesRead = inputStream.read(buffer)) != -1) {

							outStream.write(buffer, 0, bytesRead);

						}

						inputStream.close();

						outStream.close();

					} else {

						String fullPath = filePath;

						File downloadFile = new File(fullPath);

						FileInputStream inputStream = new FileInputStream(downloadFile);

						String mimeType = context.getMimeType(fullPath);

						if (mimeType == null) {

							mimeType = "application/octet-stream";

						}

						response.setContentType(mimeType);

						response.setContentLength((int) downloadFile.length());

						OutputStream outStream = response.getOutputStream();

						byte[] buffer = new byte[BUFFER_SIZE];

						int bytesRead = -1;

						while ((bytesRead = inputStream.read(buffer)) != -1) {

							outStream.write(buffer, 0, bytesRead);

						}

						inputStream.close();

						outStream.close();

					}

				} catch (Exception ex) {

					System.out.println(ex);
					
				//	admin//js//img//No_Image.jpg
					
					
					
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//							request.getRealPath("/") + "/srv/Document/No_Image.jpg";
					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = context.getMimeType(fullPath);

					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}

					response.setContentType(mimeType);

					response.setContentLength((int) downloadFile.length());

					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {

						outStream.write(buffer, 0, bytesRead);

					}

					inputStream.close();

					outStream.close();
				}
			}
			//end
		//--------------------------------------------Submit for approval Rejected Data---University------------------------------------
			
			@RequestMapping(value = "/Submit_Approval_Reject_Data_ug", method = RequestMethod.POST)
			public @ResponseBody String Submit_Approval_Reject_Data_ug(ModelMap Mmap, HttpSession session, HttpServletRequest request)
					throws ParseException {
							
							
							String msg = "";
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
					        
							String userId = session.getAttribute("userId").toString();
							
							
							try {

								String hqlUpdate = "update from DG_REC_UG_FORM_A set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
								int app = sessionHQL.createQuery(hqlUpdate)
										.setInteger("university_approved_status",1)
										.setInteger("council_approved_status",0)
										.setInteger("user_id", Integer.parseInt(userId))
									    .executeUpdate();
								
								if ((app > 0)) {
									msg = "Data Submitted Successfully";
									tx.commit();
								} else {
									msg = "Something Went Wrong !!!";
								}
								sessionHQL.close();
							  } catch (Exception e) {
								  e.printStackTrace();
							}
							return msg;
						}	
		//--------------------------------------------Submit for approval Rejected Data---University------------------------------------
			
			@RequestMapping(value = "/Submit_Approval_Reject_Data_examiners", method = RequestMethod.POST)
			public @ResponseBody String Submit_Approval_Reject_Data_examiners(ModelMap Mmap, HttpSession session, HttpServletRequest request)
					throws ParseException {
							
							
							String msg = "";
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
					        
							String userId = session.getAttribute("userId").toString();
							
							
							try {
								
								String hqlUpdate1 = "update from DG_REC_EXAMINERS_APPOINTED set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
								int app1 = sessionHQL.createQuery(hqlUpdate1)
										.setInteger("university_approved_status",1)
										.setInteger("council_approved_status",0)
										.setInteger("user_id", Integer.parseInt(userId))
									    .executeUpdate();
								
								if ((app1 > 0)) {
									msg = "Data Submitted Successfully";
									tx.commit();
								} else {
									msg = "Something Went Wrong !!!";
								}
								sessionHQL.close();
							  } catch (Exception e) {
								  e.printStackTrace();
							}
							return msg;
						}	
}