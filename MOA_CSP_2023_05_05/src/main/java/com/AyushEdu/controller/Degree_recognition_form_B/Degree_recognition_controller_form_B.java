package com.AyushEdu.controller.Degree_recognition_form_B;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_ASSIGNMENT_PG_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_ENCLOSURE_UNIVERSITY_PG;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINERS_MD_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINER_LIST_PG_COURSE_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_LECTURE_PG_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_FORM_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PRESENTATION_SEMINAR_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_STUDENTS_ADMITTED_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_TEACHING_STAFF_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_WORK_DONE_PG_STUDENT_B;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_recognition_controller_form_B {

	private static final Session HibernateUtil = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	ValidationController validation;
	@Autowired
	Gender_DAO sdao;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	private Commondao cd;
	@Autowired
	Form_b_pg_Dao PDdao;
	
	// ==========================================OPEN PAGE DEGREE  RECOGNITION==========================================

	@RequestMapping(value = "/Degree_rec_Form_B_Url", method = RequestMethod.GET)
	public ModelAndView Degree_rec_Form_B_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Degree_rec_Form_B_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String roleid = session.getAttribute("roleid").toString();

		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		Mmap.put("date_without_time", date_without_time);
		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getMedStateName",common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName",common.getMedDistrictName(sessionFactory));
		return new ModelAndView("PG_Courses_form_B_Tiles");
	}

	// ======================================== SAVE FORM A  ======================================//

	@PostMapping(value = "/Degree_rec_action_B")
	
	public @ResponseBody Map<String, String> Degree_rec_action_B(ModelMap Mmap, HttpSession session,HttpServletRequest request) 
		throws ParseException, IOException {

		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
        { 
             data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
             return data;
        }

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date date = new Date();

		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
	        
	    String current_mm_yy = month+"-"+year;

	    String username = session.getAttribute("username").toString();
		String name_of_applicant_university = request.getParameter("name_of_applicant_university");
		String postgraduate_course = request.getParameter("postgraduate_course");
		String abbre_postgraduate_course = request.getParameter("abbre_postgraduate_course");
		String academic_session = request.getParameter("academic_session");
		String name_of_college = request.getParameter("name_of_college");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String district = request.getParameter("district");
		String city = request.getParameter("city");
		String postal_address = request.getParameter("postal_address");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String academic_year_applied_for = request.getParameter("academic_year_applied_for");	
		int num_of_student_admitted = Integer.parseInt(request.getParameter("num_of_student_admitted"));
		String permission_from_central_gov = request.getParameter("permission_from_central_gov");
		int admission_intake = Integer.parseInt(request.getParameter("admission_intake"));
		String remarks = request.getParameter("remarks");
		String university_id = session.getAttribute("university_id").toString();
		String form_a_id = request.getParameter("form_a_id");
		String userId = session.getAttribute("userId").toString();
		String current_month_year = request.getParameter("Calendar");

		try {
			if (Integer.parseInt(form_a_id) == 0) {

				DG_REC_PG_FORM_B td = new DG_REC_PG_FORM_B();
				td.setName_of_applicant_university(name_of_applicant_university);
				td.setPostgraduate_course(postgraduate_course);
				td.setAbbre_postgraduate_course(abbre_postgraduate_course);
				td.setAcademic_session(academic_session);
				td.setName_of_college(name_of_college);
				td.setCountry(country);
				td.setState(state);
				td.setDistrict(district);
				td.setCity(city);
				td.setPostal_address(postal_address);
				td.setEmail(email);
				td.setWebsite(website);
				td.setAcademic_year_applied_for(academic_year_applied_for);
				td.setPermission_from_central_gov(permission_from_central_gov);
				td.setAdmission_intake(admission_intake);
				td.setNum_of_student_admitted(num_of_student_admitted);
				td.setRemarks(remarks);
				td.setUniversity_id(Integer.parseInt(university_id));
				td.setCurrent_month_year(current_mm_yy);
				td.setCreated_by(username);
				td.setCreated_date(date);
				td.setUser_id(Integer.parseInt(userId));
				
				int id = (int) sessionHQL.save(td);

				data.put("form_a_id", String.valueOf(id));

				data.put("saved","Data Saved as Draft Successfully.") ;
					 
			}
		tx.commit();

	} catch (RuntimeException e) {

		try {

			tx.rollback();

			data.put("error", "Data Not Save Successfully");

		} catch (RuntimeException rbe) {

			data.put("error", "Data Not Save Successfully");

		}

	} finally {

		if (sessionHQL != null) {

			sessionHQL.close();

		}

	}
	return data;
					
	}
	// ======================================== SAVE FORM C  ======================================//

		@PostMapping(value = "/form_c_pg")
		
	public @ResponseBody Map<String, String> form_c_pg(ModelMap Mmap, HttpSession session,HttpServletRequest request) 
			throws ParseException, IOException {

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
		    String name_of_college_pg = request.getParameter("name_of_college_pg");
			String name_of_teaching_staff = request.getParameter("name_of_teaching_staff");
			String phone = request.getParameter("phone");
			String email_id = request.getParameter("email_id");
			String designation = request.getParameter("designation");
			String department = request.getParameter("department");
			String registration_no = request.getParameter("registration_no");
			String date_of_reg = request.getParameter("date_of_reg");
			String date_of_birth = request.getParameter("date_of_birth");
			String qualification_awarding_authority = request.getParameter("qualification_awarding_authority");
			String year_of_award = request.getParameter("year_of_award");
			String date_of_appointment = request.getParameter("date_of_appointment");
			String fulltime_parttime = request.getParameter("fulltime_parttime");	
			String post_teaching = request.getParameter("post_teaching");
			String exp_from = request.getParameter("exp_from");
			String exp_to = request.getParameter("exp_to");
			String total_teaching_exp_in_year = request.getParameter("total_teaching_exp_in_year");
			String university_id = session.getAttribute("university_id").toString();
			String form_c_id = request.getParameter("form_c_id");
			String userId = session.getAttribute("userId").toString();
			String current_month_year = request.getParameter("Calendar");

			 Date dt_reg = null;
		     Date dt_brt = null;
		     Date dt_app = null;
		     Date dt_expfrom = null;
		     Date dt_expto = null;
		     
		     if (!date_of_reg.trim().equals("") && !date_of_reg.equals("DD/MM/YYYY")) {
		    	 dt_reg = format.parse(date_of_reg);
			  }
		     if (!date_of_birth.trim().equals("") && !date_of_birth.equals("DD/MM/YYYY")) {
		    	 dt_brt = format.parse(date_of_birth);
			  }
		     if (!date_of_appointment.trim().equals("") && !date_of_appointment.equals("DD/MM/YYYY")) {
		    	 dt_app = format.parse(date_of_appointment);
			  }
		     if (!exp_from.trim().equals("") && !exp_from.equals("DD/MM/YYYY")) {
		    	 dt_expfrom = format.parse(exp_from);
			  }
		     if (!exp_to.trim().equals("") && !exp_to.equals("DD/MM/YYYY")) {
		    	 dt_expto = format.parse(exp_to);
			  }
		     
			try {
				if (Integer.parseInt(form_c_id) == 0) {

					DG_REC_TEACHING_STAFF_B ts = new DG_REC_TEACHING_STAFF_B();
					
					ts.setName_of_college_pg(name_of_college_pg);
					ts.setName_of_teaching_staff(name_of_teaching_staff);
					ts.setPhone(Integer.parseInt(phone));
					ts.setEmail_id(email_id);
					ts.setDesignation(designation);
					ts.setDepartment(department);
					ts.setRegistration_no(Integer.parseInt(registration_no));
					ts.setDate_of_reg(dt_reg);
					ts.setDate_of_birth(dt_brt);
					ts.setQualification_awarding_authority(qualification_awarding_authority);
					ts.setYear_of_award(year_of_award);
					ts.setDate_of_appointment(dt_app);
					ts.setFulltime_parttime(fulltime_parttime);
					ts.setPost_teaching(post_teaching);
					ts.setExp_from(dt_expfrom);
					ts.setExp_to(dt_expto);
					ts.setTotal_teaching_exp_in_year(total_teaching_exp_in_year);
					ts.setUniversity_id(Integer.parseInt(university_id));
					ts.setCurrent_month_year(current_mm_yy);
					ts.setCreated_by(username);
					ts.setCreated_date(date);
					ts.setUser_id(Integer.parseInt(userId));
					
					int id = (int) sessionHQL.save(ts);

					data.put("form_c_id", String.valueOf(id));

					data.put("saved","Data Saved as Draft Successfully.") ;
						 
				}
			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Saved Successfully");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Saved Successfully");

			}

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}
		return data;
						
		}
	

	//////////////////////////////////////////// Form F ////////////////////////////////

		@PostMapping(value = "/form_f_pg")
		
		public @ResponseBody Map<String, String> form_f_pg(ModelMap Mmap, HttpSession session,HttpServletRequest request) 
				throws ParseException, IOException {

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
			    String subject = request.getParameter("subject");
				String name_of_examiners = request.getParameter("name_of_examiners");
				String date_of_examination = request.getParameter("date_of_examination");
				String university_id = session.getAttribute("university_id").toString();
				String form_f_id = request.getParameter("form_f_id");
				String userId = session.getAttribute("userId").toString();
				String current_month_year = request.getParameter("Calendar");

				 Date dt_exam = null;
			     
			     if (!date_of_examination.trim().equals("") && !date_of_examination.equals("DD/MM/YYYY")) {
			    	 dt_exam = format.parse(date_of_examination);
				  }
			     
			     
				try {
					if (Integer.parseInt(form_f_id) == 0) {

						DG_REC_EXAMINER_LIST_PG_COURSE_B te = new DG_REC_EXAMINER_LIST_PG_COURSE_B();
						
						te.setSubject(subject);
						te.setName_of_examiners(name_of_examiners);
						te.setDate_of_examination(dt_exam);
						te.setUniversity_id(Integer.parseInt(university_id));
						te.setCurrent_month_year(current_mm_yy);
						te.setCreated_by(username);
						te.setCreated_date(date);
						te.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(te);

						data.put("form_f_id", String.valueOf(id));

						data.put("saved","Data Saved as Draft Successfully.") ;
							 
					}
				tx.commit();

			} catch (RuntimeException e) {

				try {

					tx.rollback();

					data.put("error", "Data Not Saved Successfully");

				} catch (RuntimeException rbe) {

					data.put("error", "Data Not Saved Successfully");

				}

			} finally {

				if (sessionHQL != null) {

					sessionHQL.close();

				}

			}
			return data;
							
			}
		

	/*------------------------------------------------STUDENT DETAILS--------------------------------------------------------by Parth*/

	// ==========================================OPEN PAGE STUDENT DETAILS==========================================

	@RequestMapping(value = "/Student_Details_PG", method = RequestMethod.GET)
	public ModelAndView Student_Details_PG(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Student_Details_PG", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		String roleid = session.getAttribute("roleid").toString();
		String userId = session.getAttribute("userId").toString();
		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		Mmap.put("date_without_time", date_without_time);
		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		return new ModelAndView("Student_detail_PG_Tiles");
	}
	
	@RequestMapping(value = "/Stu_PG__Exp_Excel", method = RequestMethod.POST)
	public ModelAndView Stu_PG__Exp_Excel(HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra,
			String typeReport1) {
		

		List<String> TH = new ArrayList<String>();
		TH.add("Name Of Student");
		TH.add("Date of Admission");
		TH.add("Date of Registration");
		TH.add("Course Name");
		TH.add("Rank");
		TH.add("Marks");
		TH.add("All India(Yes/No)");
		TH.add("State(Yes/No)");
		TH.add("Admission Authority(Yes/No)");
		TH.add("Court Order & Others");
		TH.add("Qualification Name");
		TH.add("Year Of Award ");
		TH.add("Date of Completion Of Internship");
		TH.add("Date of Registration With State Council Of Homoeopathy");
		TH.add("Date of Completion Of MD Part-1 Examination/Scheduled Examination");
		TH.add("Date of Completion Of MD Part-2 Examination/Scheduled Examination");
		TH.add("Date of Declaration Of MD(Hom.)Part-2 Result");
		TH.add("Remarks");
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Exp_Excel_Stu_PG_Report("L", TH, Heading, username), "userList", TH);
	}

	// ==========================================SAVE STUDENT DATA====INSTITUTE======================================
	
//		@RequestMapping(value = "/Student_Details_PG_Action", method = RequestMethod.POST)
//		public @ResponseBody Map<String, String> Student_Details_PG_Action(@Validated @ModelAttribute("PG_Form_student_CMD")DG_REC_STUDENTS_ADMITTED_B am,
//				ModelMap Mmap, HttpSession session,
//				HttpServletRequest request,  Principal principal, RedirectAttributes ra,
//				@RequestParam(value = "upload_excel", required = false) MultipartFile upload)
//				throws ParseException, IOException {
//			System.out.println("----in pg---action");
//			Map<String, String> data = new HashMap<>();
//							if(request.getHeader("Referer") == null ) 
//					        { 
//					             data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
//					             return data;
//					        }
//							    DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//						 		Session sessionHQL = this.sessionFactory.openSession();
//						 		Transaction tx = sessionHQL.beginTransaction();
//			
//			try {
//								String h_id = request.getParameter("h_id");
//								 Date date = new Date();
//						         
//						         String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//						         String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
//						         
//						         String current_mm_yy = month+"-"+year;
//						         
//								String userId = session.getAttribute("userId").toString();
//						     	String university_id = session.getAttribute("university_id").toString();
//
//								
//			if (h_id.equals("a1")) {
//				DG_REC_STUDENTS_ADMITTED_B td1 = new DG_REC_STUDENTS_ADMITTED_B();
//
//				System.err.println("----upload " + upload);
//				if (upload.isEmpty()) {
//					data.put("error", "Please Upload File.");
//				}
//				File file = new File(expcon.fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
//				String extention = "";
//				int z = upload.getOriginalFilename().lastIndexOf('.');
//				if (z >= 0) {
//					extention = upload.getOriginalFilename().substring(z + 1);
//				}
//				if (!extention.equals("xls")) {
//					data.put("error", "Please Select Excel File");
//				}
//				FileInputStream fis = new FileInputStream(file);
//				HSSFWorkbook wb = new HSSFWorkbook(fis);
//				HSSFSheet sheet = wb.getSheetAt(0);
//				Row row_head = sheet.getRow(0);
//
////				if (!row_head.getCell(0).getStringCellValue().equals("Name Of Student")) {
////					data.put("error", "Please Select Excel File");
////					ra.addAttribute("msg", "Please Enter Correct File Header for Name");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(1).getStringCellValue().equals("Date Of Admission")) {
////					data.put("error", "Please Select Excel File");
////					ra.addAttribute("msg", "Please Enter Correct File Header for Date Of Admission in the College");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(2).getStringCellValue().equals("Date of Registration")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Date of Registration");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(3).getStringCellValue().equals("Course Name")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Course Name");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(4).getStringCellValue().equals("Rank")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Rank");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(5).getStringCellValue().equals("Marks")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Marks");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(6).getStringCellValue().equals("All India(Yes/No)")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for All India");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(7).getStringCellValue().equals("State(Yes/No)")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for State");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(8).getStringCellValue().equals("Admission Authority(Yes/No)")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Admission Authority");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(9).getStringCellValue().equals("Court Order & Others")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Court Order & Others");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(10).getStringCellValue().equals("Qualification Name")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Qualification Name");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(11).getStringCellValue().equals("Year Of Award")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Year Of Award");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(12).getStringCellValue().equals("Date of Completion Of Internship")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Date of Completion Of Internship");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(13).getStringCellValue()
////						.equals("Date of Registration With State Council Of Homoeopathy")) {
////					ra.addAttribute("msg",
////							"Please Enter Correct File Header for Date of Registration With State Council Of Homoeopathy");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(14).getStringCellValue()
////						.equals("Date of Completion Of MD Part-1 Examination/Scheduled Examination")) {
////					ra.addAttribute("msg",
////							"Please Enter Correct File Header for Date of Completion Of MD Part-1 Examination/Scheduled Examination");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(15).getStringCellValue()
////						.equals("Date of Completion Of MD Part-2 Examination/Scheduled Examination")) {
////					ra.addAttribute("msg",
////							"Please Enter Correct File Header for Date of Completion Of MD Part-2 Examination/Scheduled Examination");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(16).getStringCellValue().equals("Date of Declaration Of MD(Hom.)Part-2 Result")) {
////					ra.addAttribute("msg",
////							"Please Enter Correct File Header for Date of Date of Declaration Of MD(Hom.)Part-2 Result");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (!row_head.getCell(17).getStringCellValue().equals("Remarks")) {
////					ra.addAttribute("msg", "Please Enter Correct File Header for Remarks");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
////				if (sheet.getLastRowNum() == 0) {
////					ra.addAttribute("msg", "Please Enter Data in Atleast One Row");
////					// return new ModelAndView("redirect:clg_Add_Alumni_Url");
////				}
//				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//					Row row = sheet.getRow(i);
//					if (row.getCell(0) == null) {
//						break;
//					}
//					for (int i1 = 0; i1 <= 17; i1++) {
//
//						String varforval = "";
//						if (i1 == 0) {
//							varforval = "Name Of Student";
//						}
//						if (i1 == 1) {
//							varforval = "Date of Admission";
//						}
//						if (i1 == 2) {
//							varforval = "Date of Registration";
//						}
//						if (i1 == 3) {
//							varforval = "Course Name";
//						}
//						if (i1 == 4) {
//							varforval = "Rank";
//						}
//						if (i1 == 5) {
//							varforval = "Marks";
//						}
//						if (i1 == 6) {
//							varforval = "All India(Yes/No)";
//						}
//						if (i1 == 7) {
//							varforval = "State(Yes/No)";
//						}
//						if (i1 == 8) {
//							varforval = "Admission Authority(Yes/No)";
//						}
//						if (i1 == 9) {
//							varforval = "Court Order & Others";
//						}
//						if (i1 == 10) {
//							varforval = "Qualification Name";
//						}
//						if (i1 == 11) {
//							varforval = "Year Of Award";
//						}
//						if (i1 == 12) {
//							varforval = "Date of Completion Of Internship";
//						}
//						if (i1 == 13) {
//							varforval = "Date of Registration With State Council Of Homoeopathy";
//						}
//						if (i1 == 14) {
//							varforval = "Date of Completion Of MD Part-1 Examination/Scheduled Examination";
//						}
//						if (i1 == 15) {
//							varforval = "Date of Completion Of MD Part-2 Examination/Scheduled Examination";
//						}
//						if (i1 == 16) {
//							varforval = "Date of Declaration Of MD(Hom.)Part-2 Result";
//						}
//						if (i1 == 17) {
//							varforval = "Remarks";
//						}
//						Cell cell = row.getCell(i1);
//						if (cell == null) {
//							data.put("error", "Please Enter " + varforval + " in row " + i);
//						}
//						String value = "";
//						switch (cell.getCellType()) {
//						case Cell.CELL_TYPE_STRING:
//							value = cell.getStringCellValue();
//							break;
//						case Cell.CELL_TYPE_NUMERIC:
//							if (HSSFDateUtil.isCellDateFormatted(cell)) {
//								value = String.valueOf(cell.getDateCellValue());
//							} else {
//								value = String.valueOf((long) cell.getNumericCellValue());
//							}
//							// value=String.valueOf(cell.get());
//							break;
//						case Cell.CELL_TYPE_BOOLEAN:
//							value = String.valueOf(cell.getBooleanCellValue());
//							break;
//						default:
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Name Of Student")) {
//							System.err.println("Name Of Student---" + value);
//							td1.setStudent_name(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Date of Admission")) {
//							//Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_admission);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_admission(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Date of Registration")) {
//						//	Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_registration);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_registration(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Course Name")) {
//							td1.setCourse_name(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Rank")) {
//							td1.setRank(Integer.parseInt(value));
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Marks")) {
//							td1.setMarks(Integer.parseInt(value));
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("All India(Yes/No)")) {
//							td1.setAll_india(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("State(Yes/No)")) {
//							td1.setState(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Admission Authority(Yes/No)")) {
//							td1.setAdmission_authority(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Court Order & Others")) {
//							td1.setCourt_order(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Qualification Name")) {
//							td1.setQualification_name(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Year Of Award")) {
//							td1.setYear_of_award_admission(value);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Date of Internship Completion")) {
//							//Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_completion_internship);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_completion_internship(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue()
//								.equals("Date of Registration With State Council Of Homoeopathy")) {
//							//Date date = (Date) formatter.parse(value);
//							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_registration_state);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_registration_state(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue()
//								.equals("Date of Completion Of MD Part-1 Examination/Scheduled Examination")) {
//							//Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_completion_md_part1);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_completion_md_part1(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue()
//								.equals("Date of Completion Of MD Part-2 Examination/Scheduled Examination")) {
//							//Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_completion_md_part2);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							td1.setDate_of_completion_md_part2(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue()
//								.equals("Date of Declaration Of MD(Hom.)Part-2 Result")) {
//							//Date date = (Date) formatter.parse(value);
////							Calendar cal = Calendar.getInstance();
////							cal.setTime(date_of_declaration_of_md);
////							String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
////									+ cal.get(Calendar.YEAR);
//							System.err.println("date_of_declaration_of_md============"+date);
//							td1.setDate_of_declaration_of_md(date);
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("Remarks")) {
//							td1.setRemarks(value);
//						}
////								if (!userid.equals("")) {
////				                	
////									skl.setInstitute_id(ea.getInstitute_id());
////									p.setInstitute_id(Integer.parseInt(institute));
////									
////									String state = String.valueOf(ea.getState_id());
////									p.setState_id(Integer.parseInt(state));
////								}
//					}
//					int idm = (int) sessionHQL.save(td1);
//					sessionHQL.flush();
//					sessionHQL.clear();
//
//					if (idm > 0) {
//						data.put("saved", "Data Saved as Draft Successfully.");
//					}
//				}
//			}
//			
//				if (h_id.equals("a2")) {
//					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//
//					 String username = session.getAttribute("username").toString();
//					 String roleid1 = session.getAttribute("roleid").toString();
//					String student_name = request.getParameter("student_name");
//					String date_of_admission = request.getParameter("date_of_admission");
//					String date_of_registration = request.getParameter("date_of_registration");
//					String course_name = request.getParameter("course_name");
//					String rank = request.getParameter("rank");
//					String marks = request.getParameter("marks");
//					String all_india = request.getParameter("all_india");
//					String state = request.getParameter("state");
//					String admission_authority = request.getParameter("admission_authority");
//					String court_order = request.getParameter("court_order");
//					String qualification_name = request.getParameter("qualification_name");
//					String year_of_award_admission = request.getParameter("year_of_award_admission");
//					String date_of_completion_internship =request.getParameter("date_of_completion_internship");
//					String date_of_registration_state = request.getParameter("date_of_registration_state");
//					String date_of_completion_md_part1 = request.getParameter("date_of_completion_md_part1");
//					String date_of_completion_md_part2 = request.getParameter("date_of_completion_md_part2");
//					String date_of_declaration_of_md = request.getParameter("date_of_declaration_of_md");
//					String remarks = request.getParameter("remarks");
//			        String form_pg_id = request.getParameter("form_pg_id");
//			        String current_month_year = request.getParameter("Calendar");
//					
//					Date date_admission = null;
//					Date date_registration = null;
//					Date date_completion_internship = null;
//					Date date_registration_state = null;
//					Date date_completion_md_part1 = null;
//					Date date_completion_md_part2 = null;
//					Date date_declaration_of_md = null;
//					
//					
//					if (!date_of_admission.trim().equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
//						date_admission = format.parse(date_of_admission);
//					}
//					if (!date_of_registration.trim().equals("") && !date_of_registration.equals("DD/MM/YYYY")) {
//						date_registration = format.parse(date_of_registration);
//					}
//					if (!date_of_completion_internship.trim().equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
//						date_completion_internship = format.parse(date_of_completion_internship);
//					}
//					if (!date_of_registration_state.trim().equals("") && !date_of_registration_state.equals("DD/MM/YYYY")) {
//						date_registration_state = format.parse(date_of_registration_state);
//					}
//					if (!date_of_completion_md_part1.trim().equals("") && !date_of_completion_md_part1.equals("DD/MM/YYYY")) {
//						date_completion_md_part1 = format.parse(date_of_completion_md_part1);
//					}
//					if (!date_of_completion_md_part2.trim().equals("") && !date_of_completion_md_part2.equals("DD/MM/YYYY")) {
//						date_completion_md_part2 = format.parse(date_of_completion_md_part2);
//					}
//					if (!date_of_declaration_of_md.trim().equals("") && !date_of_declaration_of_md.equals("DD/MM/YYYY")) {
//						date_declaration_of_md = format.parse(date_of_declaration_of_md);
//					}
//					
//					
//					
//					if (Integer.parseInt(form_pg_id) == 0) {
//						DG_REC_STUDENTS_ADMITTED_B td = new DG_REC_STUDENTS_ADMITTED_B();
//
//						td.setUser_id(Integer.parseInt(userId));
//						td.setStudent_name(student_name);
//						td.setDate_of_admission(date_admission);
//						td.setDate_of_registration(date_registration);
//						td.setCourse_name(course_name);
//						td.setRank(Integer.parseInt(rank));
//						td.setMarks(Integer.parseInt(marks));
//						td.setAll_india(all_india);
//						td.setState(state);
//						td.setAdmission_authority(admission_authority);
//						td.setCourt_order(court_order);
//						td.setQualification_name(qualification_name);
//						td.setYear_of_award_admission(year_of_award_admission);
//						td.setDate_of_registration_state(date_registration_state);
//						td.setDate_of_completion_md_part1(date_completion_md_part1);
//						td.setDate_of_completion_md_part2(date_completion_md_part2);
//						td.setDate_of_declaration_of_md(date_declaration_of_md);
//						td.setDate_of_completion_internship(date_completion_internship);
//						td.setRemarks(remarks);
//						td.setCreated_by(username);
//						td.setCreated_date(date);
//						td.setCurrent_month_year(current_mm_yy);
//						td.setUniversity_id(Integer.parseInt(university_id));
//						td.setUser_id(Integer.parseInt(userId));
//						td.setInst_status(0);
//
//						int id = (int) sessionHQL.save(td);
//						data.put("form_pg_id", String.valueOf(id));
//						data.put("saved", "Data Saved as Draft Successfully.");
//					} 
//				}
//				tx.commit();
//
//			} catch (RuntimeException e) {
//				
//				e.printStackTrace();
//
//				try {
//					tx.rollback();
//					data.put("error", "Data Not Updated");
//				} catch (RuntimeException rbe) {
//					data.put("error", "Data Not Updated");
//				}
//
//			} finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//				}
//			}
//			return data;
//			
//		}
	@PostMapping(value = "/Student_Details_PG_Action")
	public ModelAndView Student_Details_PG_Action(@Validated @ModelAttribute("PG_Form_student_CMD") DG_REC_STUDENTS_ADMITTED_B td, 
			BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
		
			System.out.println("----in action--formmmm-");
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		        
			String username = session.getAttribute("username").toString();
			String userId = session.getAttribute("userId").toString();
			String student_name = request.getParameter("student_name");
			System.out.println("--------- student_name-----------"+student_name);
			String date_of_admission = request.getParameter("date_of_admission");
			String date_of_registration = request.getParameter("date_of_registration");
			String course_name=request.getParameter("course_name");
			String neet_rank=request.getParameter("neet_rank");
			String rank =request.getParameter("rank");
			String marks = request.getParameter("marks");
			String all_india = request.getParameter("all_india");
			String state = request.getParameter("state");
			String management_quota = request.getParameter("management_quota");
			String admission_authority = request.getParameter("admission_authority");
			String court_order = request.getParameter("court_order");
			String court_order_file_upload_pg = gd(request, mul, session, "court_order_file_upload_pg");
			System.out.println("---court_order_file_upload_pg---"+court_order_file_upload_pg);
			String hid_upload_file = request.getParameter("hid_upload_file");
			System.out.println("---hid_upload_file--"+hid_upload_file);
			String qualification_name = request.getParameter("qualification_name");
			String year_of_award_admission = request.getParameter("year_of_award_admission");
			String date_of_completion_internship = request.getParameter("date_of_completion_internship");
			String date_of_registration_state = request.getParameter("date_of_registration_state");
			String date_of_completion_md_part1 = request.getParameter("date_of_completion_md_part1");
			String date_of_completion_md_part2 = request.getParameter("date_of_completion_md_part2");
			String date_of_declaration_of_md = request.getParameter("date_of_declaration_of_md");
			String remarks = request.getParameter("remarks");
			String university_id = session.getAttribute("university_id").toString();
			String current_month_year = request.getParameter("Calendar");

			 
			  Date d_admission = null;
			  Date d_reg = null;
			  Date d_st = null;
			  Date d_md1 = null;
			  Date d_md2 = null;
			  Date d_md = null;
			  Date d_intern = null;
			  
			  if (!date_of_admission.trim().equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
				  d_admission = format.parse(date_of_admission);
			  }
			  if (!date_of_registration.trim().equals("") && !date_of_registration.equals("DD/MM/YYYY")) {
				  d_reg = format.parse(date_of_registration);
			  }
			  if (!date_of_registration_state.trim().equals("") && !date_of_registration_state.equals("DD/MM/YYYY")) {
				  d_st = format.parse(date_of_registration_state);
			  }
			  if (!date_of_completion_md_part1.trim().equals("") && !date_of_completion_md_part1.equals("DD/MM/YYYY")) {
				  d_md1 = format.parse(date_of_completion_md_part1);
			  }
			  if (!date_of_completion_md_part2.trim().equals("") && !date_of_completion_md_part2.equals("DD/MM/YYYY")) {
				  d_md2 = format.parse(date_of_completion_md_part2);
			  }
			  if (!date_of_declaration_of_md.trim().equals("") && !date_of_declaration_of_md.equals("DD/MM/YYYY")) {
				  d_md = format.parse(date_of_declaration_of_md);
			  }
			  if (!date_of_completion_internship.trim().equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
				  d_intern = format.parse(date_of_completion_internship);
			  }

				if (court_order_file_upload_pg=="") {
					court_order_file_upload_pg = hid_upload_file;
				}
			  int id = td.getId() > 0 ? td.getId() : 0;
			
			try {
				Long c = (Long) sessionHQL.createQuery(
						
						
						"select count(id) from  DG_REC_STUDENTS_ADMITTED_B where student_name=:student_name and "
								+ "date_of_admission=:date_of_admission and "
								+ "date_of_registration=:date_of_registration and "
								+ "course_name=:course_name and "
								+ "neet_rank=:neet_rank and "
								+ "rank=:rank and "
								+ "marks=:marks and "
								+ "all_india=:all_india and "
								+ "state=:state and "
								+ "management_quota=:management_quota and "
								+ "admission_authority=:admission_authority and "
								+ "court_order=:court_order and "
								+ "court_order_file_upload_pg=:court_order_file_upload_pg and "
								+ "qualification_name=:qualification_name and "
								+ "year_of_award_admission=:year_of_award_admission and "
								+ "date_of_completion_internship=:date_of_completion_internship and "
								+ "date_of_registration_state=:date_of_registration_state and "
								+ "date_of_completion_md_part1=:date_of_completion_md_part1 and "
								+ "date_of_completion_md_part2=:date_of_completion_md_part2 and "
								+ "date_of_declaration_of_md=:date_of_declaration_of_md and "
								+ "remarks=:remarks and  id !=:id")
			
						

						.setParameter("student_name", td.getStudent_name())
						.setParameter("date_of_admission", td.getDate_of_admission())
						.setParameter("date_of_registration", td.getDate_of_registration())
						.setParameter("course_name", td.getCourse_name())
						.setParameter("neet_rank",td.getNeet_rank())
						.setParameter("rank", td.getRank())
						.setParameter("marks", td.getMarks())
						.setParameter("all_india", td.getAll_india())
						.setParameter("state", td.getState())
						.setParameter("management_quota", td.getManagement_quota())
						.setParameter("admission_authority", td.getAdmission_authority())
						.setParameter("court_order", td.getCourt_order())
						.setParameter("court_order_file_upload_pg",td.getCourt_order_file_upload_pg())
						.setParameter("qualification_name", td.getQualification_name())
						.setParameter("year_of_award_admission",td.getYear_of_award_admission())
						.setParameter("date_of_completion_internship",td.getDate_of_completion_internship())
						.setParameter("date_of_registration_state",td.getDate_of_registration_state())
						.setParameter("date_of_completion_md_part1",td.getDate_of_completion_md_part1())
						.setParameter("date_of_completion_md_part2",td.getDate_of_completion_md_part2())
						.setParameter("date_of_declaration_of_md",td.getDate_of_declaration_of_md())
						.setParameter("remarks", td.getRemarks())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					
					td.setStudent_name(student_name);
					td.setDate_of_admission(d_admission);
					td.setDate_of_registration(d_reg);
					td.setCourse_name(course_name);
					td.setNeet_rank(neet_rank);
					td.setRank(Integer.parseInt(rank));
					td.setMarks(Integer.parseInt(marks));
					td.setAll_india(all_india);
					td.setState(state);
					td.setManagement_quota(management_quota);
					td.setAdmission_authority(admission_authority);
					td.setCourt_order(court_order);
					td.setCourt_order_file_upload_pg(court_order_file_upload_pg);
					td.setQualification_name(qualification_name);
					td.setYear_of_award_admission(year_of_award_admission);
					td.setDate_of_registration_state(d_st);			
					td.setDate_of_completion_md_part1(d_md1);
					td.setDate_of_completion_md_part2(d_md2);
					td.setDate_of_declaration_of_md(d_md);
					td.setDate_of_completion_internship(d_intern);
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
			return new ModelAndView("redirect:Student_Details_PG");
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
//			====================================Student Details PG(2) Save==================================
			
				@RequestMapping(value = "/form_b_action_pg", method = RequestMethod.POST)
					public @ResponseBody Map<String, String> form_b_action_pg(@Validated @ModelAttribute("PG_FormCMD") DG_REC_EXAMINERS_MD_B tad, 
							BindingResult result,
							HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
							@RequestParam(value = "msg", required = false) String msg,
						RedirectAttributes ra,MultipartHttpServletRequest mul) 
							throws ParseException {

					Map<String, String> data = new HashMap<>();
				    if(request.getHeader("Referer") == null ) 
				    { 
					 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
					 return data;
				}
				    
//				    @PostMapping(value = "/form_b_action")
//					public ModelAndView form_b_action(@Validated @ModelAttribute("PG_FormCMD") DG_REC_UG_FORM_A td, BindingResult result,
//							HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//							@RequestParam(value = "msg", required = false) String msg,
//							RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
						
//							System.out.println("----in action--formmmm-");
//							
//							@SuppressWarnings("unused")

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			
			 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		     String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		         
		     String current_mm_yy = month+"-"+year;
		            
		     		String username = session.getAttribute("username").toString();
				    String userId = session.getAttribute("userId").toString();
					String name_of_homoeopathic_medical_college = request.getParameter("name_of_homoeopathic_medical_college");
					String name_of_guide = request.getParameter("name_of_guide");
					String name_of_student_for_guide = request.getParameter("name_of_student_for_guide");
					String topic_of_dissertation = request.getParameter("topic_of_dissertation");
					String whether_guide_participated_in_examination = request.getParameter("whether_guide_participated_in_examination");
					String date_of_submission = request.getParameter("date_of_submission");
					String date_of_acceptance = request.getParameter("date_of_acceptance");
					String whether_student_published_article = request.getParameter("whether_student_published_article");
					String mention_details = request.getParameter("mention_details");
					String article_title = request.getParameter("article_title");
					String month_year_exam = request.getParameter("month_year_exam");
					String form_pg_id2 = request.getParameter("form_pg_id2");
					String university_id = session.getAttribute("university_id").toString();
					String current_month_year = request.getParameter("Calendar");

					Date date_submission = null;
					Date date_acceptance = null;
					

					if (!date_of_submission.trim().equals("") && !date_of_submission.equals("DD/MM/YYYY")) {
						date_submission = format.parse(date_of_submission);
					}
					if (!date_of_acceptance.trim().equals("") && !date_of_acceptance.equals("DD/MM/YYYY")) {
						date_acceptance = format.parse(date_of_acceptance);
					}
					
				try {
					if (Integer.parseInt(form_pg_id2) == 0) {
						DG_REC_EXAMINERS_MD_B td = new DG_REC_EXAMINERS_MD_B();

						td.setUser_id(Integer.parseInt(userId));
						td.setName_of_homoeopathic_medical_college(name_of_homoeopathic_medical_college);
						td.setName_of_guide(name_of_guide);
						td.setName_of_student_for_guide(name_of_student_for_guide);
						td.setTopic_of_dissertation(topic_of_dissertation);
						td.setWhether_guide_participated_in_examination(whether_guide_participated_in_examination);
						td.setDate_of_submission(date_submission);
						td.setDate_of_acceptance(date_acceptance);
						td.setWhether_student_published_article(whether_student_published_article);
						td.setMention_details(mention_details);
						td.setUniversity_id(Integer.parseInt(university_id));
						td.setCurrent_month_year(current_mm_yy);

						 if (request.getParameter("mention_details") != null) {
	          					if (request.getParameter("mention_details").equalsIgnoreCase("Yes")) {
	          					      td.setArticle_title(article_title);
	          					      td.setMonth_year_exam(month_year_exam);
	          					}
	          				}
						td.setCreated_date(date);
						td.setCreated_by(username);
						int id = (int) sessionHQL.save(td);
						
						data.put("form_pg_id2", String.valueOf(id));
						data.put("saved", "Data Saved as Draft Successfully.");
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
			
//			====================================End of Student Details PG(2) Save==================================
				
//				====================================Student Details PG(3)(A) Save==================================
				

				@RequestMapping(value = "Disseratation_Topic_Action", method = RequestMethod.POST)

				public @ResponseBody Map<String, String> Disseratation_Topic_Action(ModelMap Mmap, HttpSession session,
						HttpServletRequest request,DG_REC_WORK_DONE_PG_STUDENT_B mc) 
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
	
		try {
					String username = session.getAttribute("username").toString();
					String userId = session.getAttribute("userId").toString();
					String student_name_pg=request.getParameter("student_name_pg");
					String from_date =request.getParameter("from_date");
					String to_date =request.getParameter("to_date");
					String name_of_topic =request.getParameter("name_of_topic");
					String conclusion_obtain =request.getParameter("conclusion_obtain");
					String date_of_submission_pg =request.getParameter("date_of_submission_pg");
					String publication =request.getParameter("publication");
					String mention_article =request.getParameter("mention_article");
					String forma_id = request.getParameter("forma_id");
					String university_id = session.getAttribute("university_id").toString();
					String current_month_year = request.getParameter("Calendar");
			
			Date from_date1 = null;
			Date to_date1 = null;
			Date date_of_submission_pg1 = null;

			if (!from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
				from_date1 = format.parse(from_date);
			}
			if (!to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY")) {
				to_date1 = format.parse(to_date);
			}
			
			if (!date_of_submission_pg.trim().equals("") && !date_of_submission_pg.equals("DD/MM/YYYY")) {
				date_of_submission_pg1 = format.parse(date_of_submission_pg);
			}
			
					if (Integer.parseInt(forma_id) == 0) {
			
						DG_REC_WORK_DONE_PG_STUDENT_B fam_sib = new DG_REC_WORK_DONE_PG_STUDENT_B();
			
							fam_sib.setStudent_name_pg(student_name_pg);
							fam_sib.setFrom_date(from_date1);
							fam_sib.setTo_date(to_date1);
							fam_sib.setName_of_topic(name_of_topic);
							fam_sib.setConclusion_obtain(conclusion_obtain);
							fam_sib.setDate_of_submission_pg(date_of_submission_pg1);
							fam_sib.setPublication(publication);
							fam_sib.setMention_article(mention_article);
							
							fam_sib.setUniversity_id(Integer.parseInt(university_id));
							fam_sib.setCurrent_month_year(current_mm_yy);
							fam_sib.setCreated_by(username);
							fam_sib.setCreated_date(date);
							fam_sib.setUser_id(Integer.parseInt(userId));
			
			
			
							int id = (int) sessionHQL.save(fam_sib);
							data.put("sibId", String.valueOf(id));
							data.put("saved","Data Saved as Draft Successfully.") ;
		} 

		else {
			
				String hql = "update DG_REC_WORK_DONE_PG_STUDENT_B set"
					+ " modified_by=:modified_by ,"
					+ "modified_date=:modified_date , "
					+ "student_name_pg=:student_name_pg,"
					+ "from_date=:from_date,"
					+ "to_date =:to_date,"
					+ "name_of_topic =:name_of_topic,"
					+ "conclusion_obtain =:conclusion_obtain,"
					+ "date_of_submission_pg =:date_of_submission_pg,"
					+ "publication =:publication,"
					+ "mention_article =:mention_article"
					+ " where  id=:id";

				Query query = sessionHQL.createQuery(hql)
					
					.setString("student_name_pg", student_name_pg)
					.setTimestamp("from_date",from_date1)
					.setTimestamp("to_date",to_date1)
					.setString("name_of_topic",name_of_topic)
					.setString("conclusion_obtain",conclusion_obtain)
					.setTimestamp("date_of_submission_pg",date_of_submission_pg1)
					.setString("publication",publication)
					.setString("mention_article",mention_article)
			        .setString("modified_by",username)
			        .setTimestamp("modified_date", date)
			        .setInteger("id",Integer.parseInt(forma_id));
				
				
			int update = query.executeUpdate() > 0 ? 1 : 0;
			if (update == 1)
				data.put("updated", "Data Updated Successfully");
			else
				data.put("error", "Data Not Updated");

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
		//-------------------------------------------------Disseratation_Topic-(A)DELETE--ADDMORE------------------------

				@RequestMapping(value = "Disseratation_Topic_Delete", method = RequestMethod.POST)
				public @ResponseBody String Disseratation_Topic_Delete(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

						String msg = "";

						Session sessionHQL = this.sessionFactory.openSession();

						Transaction tx = sessionHQL.beginTransaction();

						int id = Integer.parseInt(request.getParameter("forma_id"));

						try {

								String hqlUpdate = "delete from DG_REC_WORK_DONE_PG_STUDENT_B where id=:id";

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
				//---------------------------------------------END----Disseratation_Topic-(A)------------------------

				
//				====================================End of Student Details PG(3)(A) Save==================================
					
				
//					====================================Student Details PG(3)(B) Save==================================
					
					 @RequestMapping(value = "form_3b_action_Add", method = RequestMethod.POST)

						public @ResponseBody Map<String, String> form_3b_action_Add(ModelMap Mmap, HttpSession session,HttpServletRequest request,
								DG_REC_LECTURE_PG_STUDENT_B mc) 
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
							String student_name_lec=request.getParameter("student_name_lec");
							String student_class_lec =request.getParameter("student_class_lec");
							String lecture_date = request.getParameter("lecture_date");
							String lecture_day = request.getParameter("lecture_day");
							String lecture_time = request.getParameter("lecture_time");
							String topic = request.getParameter("topic");
							String sib_ch_idb = request.getParameter("sib_ch_idb");
							String university_id = session.getAttribute("university_id").toString();
							String current_month_year = request.getParameter("Calendar");

							
							Date to_dt = null;
							
							if (!lecture_date.trim().equals("") && !lecture_date.equals("DD/MM/YYYY")) {
								to_dt = format.parse(lecture_date);
							}
							
							try {

								if (Integer.parseInt(sib_ch_idb) == 0) {
									
									DG_REC_LECTURE_PG_STUDENT_B fam_sib = new DG_REC_LECTURE_PG_STUDENT_B();
									
									fam_sib.setStudent_name_lec(student_name_lec);
									fam_sib.setStudent_class_lec(student_class_lec);
									fam_sib.setLecture_date(to_dt);
									fam_sib.setLecture_day(lecture_day);
									fam_sib.setLecture_time(lecture_time);
									fam_sib.setTopic(topic);
									fam_sib.setUniversity_id(Integer.parseInt(university_id));
									fam_sib.setCurrent_month_year(current_mm_yy);
									fam_sib.setCreated_by(username);
									fam_sib.setCreated_date(date);
									fam_sib.setUser_id(Integer.parseInt(userId));
									
									
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
					//-------------------------------------------------Disseratation_Topic-(A)DELETE--ADDMORE------------------------

						@RequestMapping(value = "lecture_pg_student_action", method = RequestMethod.POST)
						public @ResponseBody String lecture_pg_student_action(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

								String msg = "";

								Session sessionHQL = this.sessionFactory.openSession();

								Transaction tx = sessionHQL.beginTransaction();

								int id = Integer.parseInt(request.getParameter("sib_ch_idb"));

								try {

										String hqlUpdate = "delete from DG_REC_LECTURE_PG_STUDENT_B where id=:id";

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
						
//					====================================End of Student Details PG(3)(B) Save==================================
					 
					//----------------------------------------------------------DELETE--ADDMORE Form 3 B Lecture Taken ------------------------//
						
					 @RequestMapping(value = "form_3b_Add_delete_action", method = RequestMethod.POST)
					 		public @ResponseBody String form_3b_Add_delete_action(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

					 					String msg = "";
					 		
					 					Session sessionHQL = this.sessionFactory.openSession();
					 		
					 					Transaction tx = sessionHQL.beginTransaction();
					 		
					 					int id = Integer.parseInt(request.getParameter("sib_ch_idb"));
					 					try {
					 		
					 						String hqlUpdate = "delete from DG_REC_LECTURE_PG_STUDENT_B where id=:id";
					 		
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
					 
			
	//==========================================SAVE Assignment Details PG(3)(c) Save=============================================
	  
	 @RequestMapping(value = "form_c_action_Add", method = RequestMethod.POST)

		public @ResponseBody Map<String, String> form_c_action_Add(ModelMap Mmap, HttpSession session,HttpServletRequest request,DG_REC_ASSIGNMENT_PG_STUDENT_B mc) 
				throws ParseException {

			Map<String, String> data = new HashMap<>();
		    if(request.getHeader("Referer") == null ) 
		    { 
			 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
			 return data;
		    }
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			
			 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		     String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		         
		     String current_mm_yy = month+"-"+year;   
			String username = session.getAttribute("username").toString();
			String userId = session.getAttribute("userId").toString();
			String student_name_formc=request.getParameter("student_name_formc");
			String list_of_assignment_formc =request.getParameter("list_of_assignment_formc");
			String sib_ch_idc = request.getParameter("sib_ch_idc");
			String university_id = session.getAttribute("university_id").toString();
			String current_month_year = request.getParameter("Calendar");

			try {

				if (Integer.parseInt(sib_ch_idc) == 0) {
					
					DG_REC_ASSIGNMENT_PG_STUDENT_B fam_sib = new DG_REC_ASSIGNMENT_PG_STUDENT_B();
					
					fam_sib.setStudent_name_formc(student_name_formc);
					fam_sib.setList_of_assignment_formc(list_of_assignment_formc);
					fam_sib.setUniversity_id(Integer.parseInt(university_id));
					fam_sib.setCurrent_month_year(current_mm_yy);
					fam_sib.setCreated_by(username);
					fam_sib.setCreated_date(date);
					fam_sib.setUser_id(Integer.parseInt(userId));
					
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
		
	@RequestMapping(value = "form_c_Add_delete_action", method = RequestMethod.POST)
	public @ResponseBody String form_c_Add_delete_action(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

				String msg = "";

				Session sessionHQL = this.sessionFactory.openSession();

				Transaction tx = sessionHQL.beginTransaction();

				int id = Integer.parseInt(request.getParameter("sib_ch_idc"));
				try {

					String hqlUpdate = "delete from DG_REC_ASSIGNMENT_PG_STUDENT_B where id=:id";

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

	//==========================================SAVE Student Details PG(3)(D) Save=============================================

			@RequestMapping(value = "form_d_action_Add1", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form_d_action_Add1(ModelMap Mmap, HttpSession session,HttpServletRequest request,DG_REC_PRESENTATION_SEMINAR_STUDENT_B mc) 
					throws ParseException {

				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ) 
				{ 
					data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
					return data;
			    }	

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				
				 String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			     String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
			         
			     String current_mm_yy = month+"-"+year;

				String username = session.getAttribute("username").toString();
				String userId = session.getAttribute("userId").toString();
				String student_name_presen=request.getParameter("student_name_presen");
				String list_of_presentations =request.getParameter("list_of_presentations");
				String title_of_seminar_attended =request.getParameter("title_of_seminar_attended");
				String formd_id = request.getParameter("formd_id");
				String university_id = session.getAttribute("university_id").toString();
				String current_month_year = request.getParameter("Calendar");

				try {

						if (Integer.parseInt(formd_id) == 0) {
					    DG_REC_PRESENTATION_SEMINAR_STUDENT_B fc = new DG_REC_PRESENTATION_SEMINAR_STUDENT_B();
		
					    fc.setStudent_name_presen(student_name_presen);
					    fc.setList_of_presentations(list_of_presentations);
					    fc.setTitle_of_seminar_attended(title_of_seminar_attended);
					    fc.setUniversity_id(Integer.parseInt(university_id));
					    fc.setCurrent_month_year(current_mm_yy);
					    fc.setCreated_by(username);
					    fc.setCreated_date(date);
					    fc.setUser_id(Integer.parseInt(userId));
		
						int id = (int) sessionHQL.save(fc);
						data.put("sibId", String.valueOf(id));
						data.put("saved","Data Saved as Draft Successfully.") ;
	                   } 
						tx.commit();
					} catch (RuntimeException e) {
						e.printStackTrace();
						try {
								tx.rollback();
								data.put("error", "Data Not Updated");
							} 
						catch (RuntimeException rbe)
						    {
								data.put("error", "Data Not Updated");
							 }
						} 
				finally 
				{
					if (sessionHQL != null) 
					{
						sessionHQL.close();
					}
					}
				return data;
	}	
	//----------------------------------------------------------DELETE--ADDMORE------------------------

			@RequestMapping(value = "form_d_Add_delete_action1", method = RequestMethod.POST)
			public @ResponseBody String form_d_Add_delete_action1(ModelMap Mmap, HttpSession session,HttpServletRequest request) throws ParseException {

					String msg = "";

					Session sessionHQL = this.sessionFactory.openSession();

					Transaction tx = sessionHQL.beginTransaction();

					int id = Integer.parseInt(request.getParameter("formd_id"));
					try {

							String hqlUpdate = "delete from DG_REC_PRESENTATION_SEMINAR_STUDENT_B where id=:id";

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
			// ======================================== SAVE ENCLOSURE FORM  ======================================//
			
			 @PostMapping(value = "/enclosure_u_PG_Action")
				public ModelAndView enclosure_u_PG_Action(@Validated @ModelAttribute("en_form_cmd") DG_REC_ENCLOSURE_UNIVERSITY_PG td, 
						BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
				 
			
				 System.out.println("----in action");
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					Date date = new Date();

					  String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
				      String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
				        
				      String current_mm_yy = month+"-"+year;
				      
				    String username = session.getAttribute("username").toString();
				    System.out.println("---username-"+username);
				    String upload_file = gd(request, mul, session, "upload_file");
				    System.out.println("---upload_file-"+upload_file);
					String hid_upload_file = request.getParameter("hid_upload_file");
					String en_date = request.getParameter("en_date");				
					String userId = session.getAttribute("userId").toString();
					String university_id = session.getAttribute("university_id").toString();
					String int_status = request.getParameter("int_status");
					String university_approved_status = request.getParameter("university_approved_status");
					String council_approved_status = request.getParameter("council_approved_status");
					String current_month_year = request.getParameter("current_month_year");
					

					 Date en_date1 = null;

				     
				     if (!en_date.trim().equals("") && !en_date.equals("DD/MM/YYYY")) {
				    	 en_date1 = format.parse(en_date);
					  }
				     
				     if (upload_file=="") {
				    	 upload_file = hid_upload_file;
						}
				     
				     int id = td.getId() > 0 ? td.getId() : 0;
						
						try {
							Long c = (Long) sessionHQL.createQuery(
									
									"select count(id) from  DG_REC_ENCLOSURE_UNIVERSITY_PG where"
									+ " upload_file=:upload_file and "
									+ "en_date=:en_date and "
									+ "council_approved_status=:council_approved_status and "
									+ "id !=:id")
									
									.setParameter("upload_file", td.getUpload_file())
									.setParameter("en_date", td.getEn_date())
									.setParameter("council_approved_status", td.getCouncil_approved_status())
									.setParameter("id", id).uniqueResult();
							System.err.println(upload_file+"------upload_file");
							if (id == 0) {
								
								td.setUpload_file(upload_file);
								td.setEn_date(en_date1);
								td.setUser_id(Integer.parseInt(userId));
								td.setUniversity_id(Integer.parseInt(university_id));
								td.setUniversity_approved_status(1);
								td.setCouncil_approved_status(0);
								td.setCreated_by(username);
								td.setCreated_date(date);
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
						return new ModelAndView("redirect:Degree_rec_Form_B_Url");
					}
			 
			 
				public String gd1(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
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
}
