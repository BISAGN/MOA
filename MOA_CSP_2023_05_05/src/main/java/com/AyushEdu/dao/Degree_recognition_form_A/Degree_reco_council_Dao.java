package com.AyushEdu.dao.Degree_recognition_form_A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;

public interface Degree_reco_council_Dao {


	public List<Map<String, Object>> getviewdataugaid(String id);

	public List<Map<String, Object>> getviewdataugbid(String id);

	public List<Map<String, Object>> getviewdataugcid(String id);

	public List<Map<String, Object>> getviewdataugdid(String id);

	public List<Map<String, Object>> getviewdataugeid(String id);

	public List<Map<String, Object>> getviewdataugfid(String id);

	public List<Map<String, Object>> getviewdatauggid(String id);
	
	public List<EDU_LMS_INSTITUTE_REGISTRATION> getuniversitylistUrl(Integer selval);
	
	public List<Map<String, Object>> getFilter_UG_alist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks);
	
	public long getFilter_UG_aListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks);

	public List<Map<String, Object>> getFilter_UG_blist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name,String date_of_admission, String neet_rank,String rank,String marks,String all_india,
			String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
			String date_of_intern_compl,String remarks_form_b);

	public long getFilter_UG_bListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String student_name,String date_of_admission, String neet_rank,String rank,String marks,String all_india,
			String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
			String date_of_intern_compl,String remarks_form_b);

	public List<Map<String, Object>> getFilter_UG_clist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
			String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
			String remarks_form_c);

	public long getFilter_UG_cListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
			String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
			String remarks_form_c);

	public List<Map<String, Object>> getFilter_UG_dtolist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
			String university_enrollment_number,String remarks_form_d);

	public long getFilter_UG_dtoListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
			String university_enrollment_number,String remarks_form_d);

	public List<Map<String, Object>> getFilter_UG_elist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
			String teaching_experience,String teacher_code, String reg_number,String d_university_appointment);

	public long getFilter_UG_eListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
			String teaching_experience,String teacher_code, String reg_number,String d_university_appointment);

	public List<Map<String, Object>> getFilter_UG_flist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);

	public long getFilter_UG_fListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);

	public List<Map<String, Object>> getFilter_UG_dfromlist(int startPage, int pageLength,String Search, String orderColunm,
			String orderType, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_students_migrated, String name_of_institution, String professional_year,
			String remarks_migrated, String university_enroll_num, String dt_of_migration);

	public long getFilter_UG_dfromListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_students_migrated, String name_of_institution, String professional_year,
			String remarks_migrated, String university_enroll_num, String dt_of_migration);
	
	public ArrayList<ArrayList<String>> getviewdataugaidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataugbidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataugcidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataugdtoidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataugdfromidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataugeidPDF(String id);

	public ArrayList<ArrayList<String>> getviewdataufeidPDF(String id);
	
	//-------------------------start track status

	public List<Map<String, Object>> DataTable_All_DataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String userId, String institute_status);//String uni_status,String commi_status,
	

	public long DataTable_Student_DataTotalCount(String search, String userId, String institute_status);

	public ArrayList<ArrayList<String>> data_get_track_status(String userId, String institute_status,
			String choose_role);
	
//-------------------------end track status

}
