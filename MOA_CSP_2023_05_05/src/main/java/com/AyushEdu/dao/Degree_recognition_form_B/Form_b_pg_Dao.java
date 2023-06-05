package com.AyushEdu.dao.Degree_recognition_form_B;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINER_LIST_PG_COURSE_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_FORM_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_TEACHING_STAFF_B;

public interface Form_b_pg_Dao {

	public List<Map<String, Object>> getInstituteListbyUserID();
	
	public List<Map<String,Object>> getFilter_PG_list(int startPage, int pageLength,String Search, 
			String orderColunm, String orderType,String university_id, String university_approved_status, 
			String council_approved_status,String university_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks) ;
	
	public long getFilter_PGListCount(String search, String university_id,String university_approved_status, 
				String council_approved_status,String university_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
				String country,String state,String district,String city,String postal_address,String email,String website,
				String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
				String remarks,String reject_remarks);

	public List<Map<String, Object>> getFilter_Teaching_staff_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String university_approved_status,
			String council_approved_status,String university_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
			String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
			String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks);

	public long getFilter_Teaching_staffListCount(String search, String university_id,String university_approved_status, 
			String council_approved_status,String university_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
			String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
			String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks);

	public List<Map<String, Object>> getFilter_Examiners_pg_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String university_approved_status,
			String council_approved_status,String university_status,String subject,String name_of_examiners,
			String date_of_examination,String reject_remarks);

	public long getFilter_Examiners_pgListCount(String search, String university_id, String university_approved_status,
			String council_approved_status,String university_status,String subject,String name_of_examiners,
			String date_of_examination,String reject_remarks);

	public DG_REC_PG_FORM_B getpgByid(int id);
	
	public DG_REC_TEACHING_STAFF_B getpgteachingByid(int id);

	public DG_REC_EXAMINER_LIST_PG_COURSE_B getpgexaminersByid(int id);

	
	/// Admitted Student Institute ///
	public List<Map<String, Object>> getFilter_studentAdmitted_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks);

	public long getFilter_Admitted_pg_ListCount(String search, String university_id, String institute_id, String status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks);

	/// Examiners Student Institute ///
	
	public List<Map<String, Object>> getFilter_examiners_md_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);

	public long getFilter_examiners_md_ListCount(String search, String university_id, String institute_id,
			String status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);

	/// Dissertation Student Institute ///
	
	public List<Map<String, Object>> getFilter_disseration_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);

	public long getFilter_disseration_ListCount(String search, String university_id, String institute_id,
			String status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);

	/// Lecture  Student Institute ///
	
	public List<Map<String, Object>> getFilter_lecture_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);

	public long getFilter_lectuture_ListCount(String search, String university_id, String institute_id, String status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);

	/// Assignment  Student Institute ///
	
	public List<Map<String, Object>> getFilter_assignment_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	public long getFilter_assignment_ListCount(String search, String university_id, String institute_id, String status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	/// Presentation  Student Institute ///
	public List<Map<String, Object>> getFilter_presentation_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String student_name_presen,
			String list_of_presentations,String title_of_seminar_attended, String reject_remarks);

	public long getFilter_presentation_ListCount(String search, String university_id, String institute_id,
			String status,String student_name_presen,String list_of_presentations,String title_of_seminar_attended, String reject_remarks);
	
}
