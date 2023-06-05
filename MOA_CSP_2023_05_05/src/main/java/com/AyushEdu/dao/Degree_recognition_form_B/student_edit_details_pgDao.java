package com.AyushEdu.dao.Degree_recognition_form_B;

import java.util.List;
import java.util.Map;

public interface student_edit_details_pgDao {
	
	// Admitted Student //
	public List<Map<String,Object>> getFilter_Admitted_Student_pg_list(int startPage, int pageLength,String Search, 
			String orderColunm, String orderType,int university_id,int user_id,String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks) ;
	
	public long getFilter_Admitted_StudentList_pg_Count(String search, int user_id,int university_id,String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks);

	public Object getadmittedPGByid(int id);
	
	// Examiners Student //

	public List<Map<String, Object>> getFilter_Examiners_Student_pg_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id, int user_id,String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);

	public long getFilter_Examiners_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks);
	
	public Object getmigratedPGByid(int id);
	
	// Dissertation Student //

	public List<Map<String, Object>> getFilter_Dissertation_Student_pg_List(int startPage, int pageLength,
			String search, String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);

	public long getFilter_Dissertation_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name_pg,String from_date,
			String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
			String publication,String mention_article);

	public List<Map<String, Object>> getdissertationdatafromByid(String id);
	
	// Lecture Student //

	public List<Map<String, Object>> getFilter_Lecture_Student_pg_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);

	public long getFilter_Lecture_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name,String student_class,
			String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks);

	public List<Map<String, Object>> getlacturetakenpgtoByid(String id);

	// Assignment Student //

	public List<Map<String, Object>> getFilter_assignment_pg_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	public long getFilter_assignment_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name_formc,
			String list_of_assignment_formc, String reject_remarks);

	public List<Map<String, Object>> getassignmentpgtoByid(String id);

	// Presentation Student //
	
	public List<Map<String, Object>> getFilter_presentation_pg_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id, int user_id, String institute_status,String student_name_presen,
			String list_of_presentations,String title_of_seminar_attended, String reject_remarks);

	public long getFilter_presentation_pgListCount(String search, int user_id, int university_id,
			String institute_status,String student_name_presen,
			String list_of_presentations,String title_of_seminar_attended, String reject_remarks);

	public List<Map<String, Object>> getpresentationpgtoByid(String id);
	
	
	

}
