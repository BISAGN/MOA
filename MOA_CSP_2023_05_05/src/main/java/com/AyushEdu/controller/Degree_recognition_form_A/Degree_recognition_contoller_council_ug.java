package com.AyushEdu.controller.Degree_recognition_form_A;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Admitted_Students_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Examiners_Appointed_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Institute_Wise_Regarding_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Intern_Student_Report_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Migrated_From_Other_College_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Migrated_To_Other_College_Report;
import com.AyushEdu.controller.Degree_recognition_PDF.Download_PDF_Undergraduate_Course_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Degree_reco_council_Dao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Degree_recognition_contoller_council_ug {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Degree_reco_council_Dao drdao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	//==========================================OPEN PAGE DEGREE RECOGNITION========================================== 
	
		@RequestMapping(value = "/Degree_reco_council", method = RequestMethod.GET)
		public ModelAndView Degree_reco_council(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//			SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String roleid = session.getAttribute("roleid").toString();
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
			return new ModelAndView("dr_council_Tiles");
		}
		
		@RequestMapping(value = "/getInstituteUrl", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
			System.err.println("selval------>>>"+selval);

			List<EDU_LMS_INSTITUTE_REGISTRATION> list = drdao.getuniversitylistUrl(selval);

			return list;
		}
		
		//-------------------------------------------View table UG Form A-------------------------------------------

		 @PostMapping("/getFilter_UG_a")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_alists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
				String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_alist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status, name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
		    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
		     
	    }
		 @PostMapping("/getFilter_UG_aListCount")	
		 public @ResponseBody long getFilter_UG_aListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
					String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) throws ParseException {
		
			 String university_id = session.getAttribute("university_id").toString();	 
		     return drdao.getFilter_UG_aListCount(Search,university_id,uni_id,institute_id,institute_status, name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
		    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
	    } 
		//-------------------------------------------View table UG Form B-------------------------------------------

		 @PostMapping("/getFilter_UG_b")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_blists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,
				String student_name,String date_of_admission, String neet_rank,String rank,String marks,String all_india,
				String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
				String date_of_intern_compl,String remarks_form_b) throws ParseException {
			 
			 
			 String university_id = session.getAttribute("university_id").toString();	
		     return drdao.getFilter_UG_blist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,
		    		 institute_status, student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);

	    }
		 @PostMapping("/getFilter_UG_bListCount")	
		 public @ResponseBody long getFilter_UG_bListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,
				 String institute_status,String student_name,String date_of_admission,String neet_rank, String rank,String marks,String all_india,
					String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
					String date_of_intern_compl,String remarks_form_b) throws ParseException {
			 
			 
		String university_id = session.getAttribute("university_id").toString();		 
		return drdao.getFilter_UG_bListCount(Search,university_id,uni_id,institute_id,institute_status,student_name,date_of_admission,neet_rank,rank,marks,
				all_india,state,management_quota,admission_authority,
				court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
	    } 
		//-------------------------------------------View table UG Form C-------------------------------------------

		 @PostMapping("/getFilter_UG_c")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_clists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
				String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
				String remarks_form_c) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_clist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);

	    }
		 @PostMapping("/getFilter_UG_cListCount")	
		 public @ResponseBody long getFilter_UG_cListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
					String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
					String remarks_form_c) throws ParseException {
		
			 String university_id = session.getAttribute("university_id").toString();
		return drdao.getFilter_UG_cListCount(Search,university_id,uni_id,institute_id,institute_status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
				mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
	    } 
		//-------------------------------------------View table UG Form D To-------------------------------------------

		 @PostMapping("/getFilter_UG_dto")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_dtolists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
				String university_enrollment_number,String remarks_form_d) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_dtolist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
						university_enrollment_number, remarks_form_d);

	    }
		 @PostMapping("/getFilter_UG_dtoListCount")	
		 public @ResponseBody long getFilter_UG_dtoListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
					String university_enrollment_number,String remarks_form_d) throws ParseException {
				 
			 String university_id = session.getAttribute("university_id").toString();
		return drdao.getFilter_UG_dtoListCount(Search,university_id,uni_id,institute_id,institute_status,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
				university_enrollment_number, remarks_form_d);
	    } 
		//-------------------------------------------View table UG Form D From-------------------------------------------

		 @PostMapping("/getFilter_UG_dfrom")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_dfromlists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
				String university_enroll_num,String remarks_migrated) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_dfromlist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year, university_enroll_num, remarks_migrated);

	    }
		 @PostMapping("/getFilter_UG_dfromListCount")	
		 public @ResponseBody long getFilter_UG_dfromListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
					String university_enroll_num,String remarks_migrated) throws ParseException {
				 
			 String university_id = session.getAttribute("university_id").toString();
		return drdao.getFilter_UG_dfromListCount(Search,university_id,uni_id,institute_id,institute_status,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year, university_enroll_num, remarks_migrated);
	    }
		//-------------------------------------------View table UG E-------------------------------------------

		 @PostMapping("/getFilter_UG_e")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_elists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
				String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_elist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
						teaching_experience,teacher_code,reg_number,d_university_appointment);

	    }
		 @PostMapping("/getFilter_UG_eListCount")	
		 public @ResponseBody long getFilter_UG_eListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
					String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		return drdao.getFilter_UG_eListCount(Search,university_id,uni_id,institute_id,institute_status,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
				teaching_experience,teacher_code,reg_number,d_university_appointment);
	    } 
		//-------------------------------------------View table UG F-------------------------------------------

		 @PostMapping("/getFilter_UG_f")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_UG_flists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f) throws ParseException {
			
			 String university_id = session.getAttribute("university_id").toString();
		     return drdao.getFilter_UG_flist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
						year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);

	    }
		 @PostMapping("/getFilter_UG_fListCount")	
		 public @ResponseBody long getFilter_UG_fListCount(HttpSession session ,String Search,String id,String uni_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
					String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
					String remark_form_f) throws ParseException {
		
			 String university_id = session.getAttribute("university_id").toString();
		return drdao.getFilter_UG_fListCount(Search,university_id,uni_id,institute_id,institute_status,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
				year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
	    } 
		//--------------------------------------------Submit for approval-----institute------------------------------------
			
			@RequestMapping(value = "/Final_Submit_Council", method = RequestMethod.POST)
			public @ResponseBody String Final_Submit_Council(ModelMap Mmap, HttpSession session, HttpServletRequest request,
					@RequestParam(value = "acoun_hid", required = false) int acoun_hid,
					@RequestParam(value = "bcoun_hid", required = false) int bcoun_hid,
					@RequestParam(value = "dtocoun_hid", required = false) int dtocoun_hid,
					@RequestParam(value = "dfromcoun_hid", required = false) int dfromcoun_hid,
//					@RequestParam(value = "ecoun_hid", required = false) int ecoun_hid,
					@RequestParam(value = "fcoun_hid", required = false) int fcoun_hid) throws ParseException {
							
				String msg = "";

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
			
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int mnt = c.get(Calendar.MONTH);
				
				String cmoyr = (mnt+1)+"-"+year;
							
							try {
								
								if(acoun_hid > 0) 
								{
									String hqlUpdate = "update from DG_REC_UG_FORM_A set council_approved_status=:council_approved_status where "
														+ "current_month_year=:current_month_year";
									int app = sessionHQL.createQuery(hqlUpdate)
											.setInteger("council_approved_status",1)
											.setString("current_month_year",cmoyr)
										    .executeUpdate();
							
									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
														+ "commi_status=:commi_status where month_year=:month_year";
							
									int app1 = sessionHQL.createQuery(hqlUpdate1)
											.setInteger("inst_status", 1)
											.setInteger("uni_status", 1)
											.setInteger("commi_status", 1)
											.setString("month_year",cmoyr)
											.executeUpdate();
								}
								if(bcoun_hid > 0) 
								{
									String hqlUpdate = "update from DG_REC_ADMITTED_STUDENT set council_approved_status=:council_approved_status where "
														+ "current_month_year=:current_month_year";
								
									int app = sessionHQL.createQuery(hqlUpdate)
											.setInteger("council_approved_status", 1)
											.setString("current_month_year",cmoyr)
											.executeUpdate();
							
									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
														+ "commi_status=:commi_status where month_year=:month_year";
				
									int app1 = sessionHQL.createQuery(hqlUpdate1)
												.setInteger("inst_status", 1)
												.setInteger("uni_status", 1)
												.setInteger("commi_status", 1)
												.setString("month_year",cmoyr)
												.executeUpdate();
								}
//								if(ccoun_hid > 0) 
//								{
//									String hqlUpdate = "update from DG_REC_HOSPITAL_ATTACHED set council_approved_status=:council_approved_status where "
//														+ "current_month_year=:current_month_year";
//									
//									int app = sessionHQL.createQuery(hqlUpdate)
//											.setInteger("council_approved_status", 1)
//											.setString("current_month_year",cmoyr)
//											.executeUpdate();
//							
//									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
//														+ "commi_status=:commi_status where month_year=:month_year";
//				
//									int app1 = sessionHQL.createQuery(hqlUpdate1)
//											.setInteger("inst_status", 1)
//											.setInteger("uni_status", 1)
//											.setInteger("commi_status", 1)
//											.setString("month_year",cmoyr)
//											.executeUpdate();
//								}
								if(dtocoun_hid > 0) 
								{
									
									String hqlUpdate = "update from DG_REC_MIGRATED_STUDENT_SUB_CHILD set council_approved_status=:council_approved_status where "
														+ "current_month_year=:current_month_year";
									int app = sessionHQL.createQuery(hqlUpdate)
											.setInteger("council_approved_status",1)
											.setString("current_month_year",cmoyr)
										    .executeUpdate();
						
									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
														+ "commi_status=:commi_status where month_year=:month_year";
				
									int app1 = sessionHQL.createQuery(hqlUpdate1)
											.setInteger("inst_status", 1)
											.setInteger("uni_status", 1)
											.setInteger("commi_status", 1)
											.setString("month_year",cmoyr)
											.executeUpdate();
								}
								if(dfromcoun_hid > 0) 
								{
							
									String hqlUpdate = "update from DG_REC_MIGRATED_STUDENT_FROM set council_approved_status=:council_approved_status where "
											+ "current_month_year=:current_month_year";
									
									int app = sessionHQL.createQuery(hqlUpdate)
											.setInteger("council_approved_status",1)
											.setString("current_month_year",cmoyr)
										    .executeUpdate();
						
									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
														+ "commi_status=:commi_status where month_year=:month_year";
				
									int app1 = sessionHQL.createQuery(hqlUpdate1)
											.setInteger("inst_status", 1)
											.setInteger("uni_status", 1)
											.setInteger("commi_status", 1)
											.setString("month_year",cmoyr)
											.executeUpdate();
								}
//								if(ecoun_hid > 0) 
//								{
//							
//									String hqlUpdate = "update from DG_REC_EXAMINERS_APPOINTED set council_approved_status=:council_approved_status where "
//											+ "current_month_year=:current_month_year";
//									
//									int app = sessionHQL.createQuery(hqlUpdate)
//											.setInteger("council_approved_status",1)
//											.setString("current_month_year",cmoyr)
//										    .executeUpdate();
//									
//									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
//														+ "commi_status=:commi_status where month_year=:month_year";
//				
//									int app1 = sessionHQL.createQuery(hqlUpdate1)
//											.setInteger("inst_status", 1)
//											.setInteger("uni_status", 1)
//											.setInteger("commi_status", 1)
//											.setString("month_year",cmoyr)
//											.executeUpdate();
//								}
								if(fcoun_hid > 0) 
								{
									
									String hqlUpdate = "update from DG_REC_INTERN_STUDENT_COURSE set council_approved_status=:council_approved_status where "
											+ "current_month_year=:current_month_year";
									
									int app = sessionHQL.createQuery(hqlUpdate)
											.setInteger("council_approved_status",1)
											.setString("current_month_year",cmoyr)
										    .executeUpdate();
									
									String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
														+ "commi_status=:commi_status where month_year=:month_year";
				
									int app1 = sessionHQL.createQuery(hqlUpdate1)
												.setInteger("inst_status", 1)
												.setInteger("uni_status", 1)
												.setInteger("commi_status", 1)
												.setString("month_year",cmoyr)
												.executeUpdate();
								}
						
							
				if ((acoun_hid > 0) || (bcoun_hid > 0) ||  (dtocoun_hid > 0) || (dfromcoun_hid > 0) || (fcoun_hid > 0)) 
				{
					msg = "Data Approved Successfully";
					tx.commit();
				} 
				else 
				{
					msg = "Something Went Wrong !!!";
				}
				sessionHQL.close();
			}
				catch (Exception e)
							{
								  e.printStackTrace();
							}
							return msg;
			}		
			/////////////////// Reject Council ///////////////
			
		 @RequestMapping(value = "reject_action_council", method = RequestMethod.POST)
			public @ResponseBody String reject_action_council(ModelMap Mmap, String id,String reject_remarks,String actind,
					HttpSession session, HttpServletRequest request)
					throws ParseException {
							
			 	String msg = "";
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
					        
							try {
								
									String hqlUpdate6 = "update DG_REC_UG_FORM_A set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app6 = sessionHQL.createQuery(hqlUpdate6)
										.setInteger("university_approved_status",-1)
										.setInteger("council_approved_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
									
									String hqlUpdate7 = "update DG_REC_EXAMINERS_APPOINTED set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app7 = sessionHQL.createQuery(hqlUpdate7)
										.setInteger("university_approved_status",-1)
										.setInteger("council_approved_status",-1)
										.setString("reject_remarks",reject_remarks)
										.setInteger("id", Integer.parseInt(id))
									    .executeUpdate();
									
									
									String hqlUpdate1 = "update DG_REC_ADMITTED_STUDENT set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app1 = sessionHQL.createQuery(hqlUpdate1)
											.setInteger("inst_status",-1)
											.setInteger("university_approved_status",-1)
											.setInteger("council_approved_status",-1)
											.setString("reject_remarks",reject_remarks)
											.setInteger("id", Integer.parseInt(id))
										    .executeUpdate();
									
									String hqlUpdate2 = "update DG_REC_HOSPITAL_ATTACHED set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app2 = sessionHQL.createQuery(hqlUpdate2)
											.setInteger("inst_status",-1)
											.setInteger("council_approved_status",-1)
											.setString("reject_remarks",reject_remarks)
											.setInteger("id", Integer.parseInt(id))
										    .executeUpdate();
									
									String hqlUpdate3 = "update DG_REC_MIGRATED_STUDENT_SUB_CHILD set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app3 = sessionHQL.createQuery(hqlUpdate3)
											.setInteger("inst_status",-1)
											.setInteger("council_approved_status",-1)
											.setString("reject_remarks",reject_remarks)
											.setInteger("id", Integer.parseInt(id))
										    .executeUpdate();
									
									String hqlUpdate4 = "update DG_REC_MIGRATED_STUDENT_FROM set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app4 = sessionHQL.createQuery(hqlUpdate4)
											.setInteger("inst_status",-1)
											.setInteger("council_approved_status",-1)
											.setString("reject_remarks",reject_remarks)
											.setInteger("id", Integer.parseInt(id))
										    .executeUpdate();
									
									String hqlUpdate5 = "update DG_REC_INTERN_STUDENT_COURSE set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
									int app5 = sessionHQL.createQuery(hqlUpdate5)
											.setInteger("inst_status",-1)
											.setInteger("council_approved_status",-1)
											.setString("reject_remarks",reject_remarks)
											.setInteger("id", Integer.parseInt(id))
										    .executeUpdate();

								
								tx.commit();
								sessionHQL.close();
							

								if ( (app6 > 0) || (app7 > 0) || (app1 > 0) || (app2 > 0) || (app3 > 0) || (app4 > 0) || (app5 > 0)) {
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
 
			
			////////////////////////////// Council View Data //////////////////////
			
			@PostMapping(value="/getviewdatauga")
			@ResponseBody public List<Map<String, Object>> getviewdatauga(String id) {
				return drdao.getviewdataugaid(id);
			}
			
			@PostMapping(value="/getviewdataugb")
			@ResponseBody public List<Map<String, Object>> getviewdataugb(String id) {
				return drdao.getviewdataugbid(id);
			}
			
			@PostMapping(value="/getviewdataugc")
			@ResponseBody public List<Map<String, Object>> getviewdataugc(String id) {
				return drdao.getviewdataugcid(id);
			}
			
			@PostMapping(value="/getviewdataugd")
			@ResponseBody public List<Map<String, Object>> getviewdataugd(String id) {
				return drdao.getviewdataugdid(id);
			}
			
			@PostMapping(value="/getviewdatauge")
			@ResponseBody public List<Map<String, Object>> getviewdatauge(String id) {
				return drdao.getviewdataugeid(id);
			}
			
			@PostMapping(value="/getviewdataugf")
			@ResponseBody public List<Map<String, Object>> getviewdataugf(String id) {
				return drdao.getviewdataugfid(id);
			}
			
			@PostMapping(value="/getviewdataugg")
			@ResponseBody public List<Map<String, Object>> getviewdataugg(String id) {
				return drdao.getviewdatauggid(id);
			}
			
			////////////////////View PDF ////////////////////
	
			
			//DOWNLOAD MAIN PDF
			@RequestMapping(value = "/Undergraduate_Course_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Undergraduate_Course_Report_Url_pdf(@RequestParam(value = "course_id1", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugaidPDF(id);
						
						System.out.println("list  " + list);
				
						List<String> TH = new ArrayList<String>();
			

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Undergraduate_Course_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
			
			@RequestMapping(value = "/Admitted_Students_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Admitted_Students_Report_Url_pdf(@RequestParam(value = "add_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugbidPDF(id);
						
						System.out.println(id+"___id____list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Admitted_Students_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
			
			@RequestMapping(value = "/Institute_Wise_Regarding_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Institute_Wise_Regarding_Report_Url_pdf(@RequestParam(value = "inst_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugcidPDF(id);
						
						System.out.println(id+"___id__aa__list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Institute_Wise_Regarding_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
		
			@RequestMapping(value = "/Migrated_To_Other_College_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Migrated_To_Other_College_Report_Url_pdf(@RequestParam(value = "migrat_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugdtoidPDF(id);
						
						System.out.println(id+"___id__ppp__list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Migrated_To_Other_College_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
			
			@RequestMapping(value = "/Migrated_From_Other_College_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Migrated_From_Other_College_Report_Url_pdf(@RequestParam(value = "migrat_from_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugdfromidPDF(id);
						
						System.out.println(id+"___id__pdddpp__list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Migrated_From_Other_College_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
			
			@RequestMapping(value = "/Examiners_Appointed_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Examiners_Appointed_Report_Url_pdf(@RequestParam(value = "exam_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataugeidPDF(id);
						
						System.out.println(id+"___id__aaa__list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Examiners_Appointed_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}
			
			@RequestMapping(value = "/Intern_Student_Report_Url_pdf", method = RequestMethod.POST)
			public ModelAndView Intern_Student_Report_Url_pdf(@RequestParam(value = "intern_stud", required = false)String id,
					Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = Session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String userid = Session.getAttribute("userId_for_jnlp").toString();
	 
				ArrayList<ArrayList<String>> list = drdao.getviewdataufeidPDF(id);
						
						System.out.println(id+"___id__aabbbba__list" + list);
						
						List<String> TH = new ArrayList<String>();

						String Heading = "\nIn Inspection";
						String username = Session.getAttribute("username").toString();
						return new ModelAndView(new Download_PDF_Intern_Student_Report_Report("P", TH, Heading, username,list),
								"userList", list);
					
		}	
				
				

			@RequestMapping(value = "admin/track_UG_status", method = RequestMethod.GET)
            public ModelAndView track_UG_status(ModelMap Mmap, HttpSession session,
                            @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,ModelMap model) {
//				SECURITY ABHISHEK
//				if(request.getHeader("Referer") == null ) { 
//					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Degree_reco_council", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
//                    
                    String userid = session.getAttribute("userId_for_jnlp").toString();
                    String role = session.getAttribute("role").toString();
//		            Mmap.put("get_p_id", PTdao.getper_reg_trans_details(Integer.parseInt(userid)));
                    return new ModelAndView("UG_trackstatus_Tiles");
}

		@PostMapping("/getFilter_All_data")
		public @ResponseBody List<Map<String, Object>> getFilter_All_data(HttpSession session,int startPage, int pageLength, String Search,
		String orderColunm, String orderType,String institute_status) {//String uni_status,String commi_status,String choose_role
			
		String userId = session.getAttribute("userId").toString();
		
		System.out.println("__inst_status____" + institute_status);
//		System.out.println("__uni_status____" + uni_status);
//		System.out.println("___commi_status___" + commi_status);
		
		return drdao.DataTable_All_DataList(startPage, pageLength, Search, orderColunm, orderType,userId,institute_status);
		}
		
		@PostMapping("/getTotal_All_dataCount")
		public @ResponseBody long getTotal_Student_dataCount(HttpSession session, String Search,String institute_status) {
		String userId = session.getAttribute("userId").toString();
		String role = session.getAttribute("role").toString();
		return drdao.DataTable_Student_DataTotalCount(Search,userId,institute_status);
		}
		
		@RequestMapping(value = "/get_track_status", method = RequestMethod.POST)
		public @ResponseBody  ArrayList<ArrayList<String>> get_track_status(HttpSession session,String institute_status,String choose_role) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		return drdao.data_get_track_status(userId,institute_status,choose_role);
		}

}

