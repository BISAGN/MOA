package com.AyushEdu.dao.Degree_recognition_form_B;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;

public interface Degree_reco_council_pg_Dao {

	public List<Map<String, Object>> getFilter_PG_alist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks);

	public long getFilter_PG_aListCount(String search, String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks);

	public List<Map<String, Object>> getFilter_PG_blist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks);

	public long getFilter_PG_bListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks);
	
	public List<Map<String, Object>> getFilter_PG_clist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
			String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
			String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks);

	public long getFilter_PG_cListCount(String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
			String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
			String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks);

	public List<Map<String, Object>> getFilter_PG_dlist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);

	public long getFilter_PG_dListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);
	
	public List<Map<String, Object>> getFilter_PG_ealists(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);

	public long getFilter_PG_eaListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);
	
	public List<Map<String, Object>> getFilter_PG_eblists(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);

	public long getFilter_PG_ebListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);
	
	public List<Map<String, Object>> getFilter_PG_eclists(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	public long getFilter_PG_ecListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	public List<Map<String, Object>> getFilter_PG_edlists(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name_presen,
			String list_of_presentations,String title_of_seminar_attended, String reject_remarks);

	public long getFilter_PG_edListCount(String search, String orderColunm, String orderType, String university_id,
			String uni_id, String institute_id, String institute_status,String student_name_presen,
			String list_of_presentations,String title_of_seminar_attended, String reject_remarks);
	
	public List<Map<String, Object>> getFilter_PG_flist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String subject,String name_of_examiners,
			String date_of_examination,String reject_remarks);

	public long getFilter_PG_fListCount(String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String subject,String name_of_examiners,
			String date_of_examination,String reject_remarks);

	
	public List<Map<String, Object>> getviewdatapgaid(String id);

	public List<Map<String, Object>> getviewdatapgbid(String id);

	public List<Map<String, Object>> getviewdatapgcid(String id);
	
	public List<Map<String, Object>> getviewdatapgdid(String id);
	
	public List<Map<String, Object>> getviewdatapgeid(String id);

	public List<Map<String, Object>> getviewdatapgeaid(String id);

	public List<Map<String, Object>> getviewdatapgebid(String id);

	public List<Map<String, Object>> getviewdatapgecid(String id);

	public List<Map<String, Object>> getviewdatapgedid(String id);

	public List<EDU_LMS_INSTITUTE_REGISTRATION> getuniversitylistUrl(Integer selval);

	

	///////////// PDF /////////////////////

	public ArrayList<ArrayList<String>> getviewdatapgaidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdatapgbidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdatapgcidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdatapgdidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdatapgeaidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdatapgebidPDF(String id);
	
	public ArrayList<ArrayList<String>> getviewdatapgecidPDF(String id);
	
	public ArrayList<ArrayList<String>> getviewdatapgedidPDF(String id);
	
	public ArrayList<ArrayList<String>> getviewdatapgfidPDF(String id);
	
	
	

	

	

	

	

}
