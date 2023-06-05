package com.AyushEdu.dao.Degree_recognition_form_A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_EXAMINERS_APPOINTED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;

public interface Form_a_ug_Dao {

	//List<Map<String, Integer>> approveDetailOfStudentByid(String userId);

	//String approve_StudentData(String string, String username, String userId);

	public List<Map<String,Object>> getFilter_UG_list(int startPage, int pageLength,String Search,
			String orderColunm, String orderType,String university_id, String university_approved_status,
			String council_approved_status,String university_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks) ;
	
	public long getFilter_UGListCount(String search, int university_id,int user_id,String university_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks);
	
	public List<Map<String,Object>> getFilter_Admitted_list(int startPage, int pageLength,String Search, 
			String orderColunm, String orderType,String university_id,String institute_id,String status,String student_name,
			String date_of_admission, String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,
			String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) ;
	
	public long getFilter_Admitted_ListCount(String search, String university_id,String institute_id,String status,String student_name,
			String date_of_admission,  String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,
			String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b); //,int institute_status,int institute_id
	
	public List<Map<String, Object>> getFilter_Hospital_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String institute_id, String status,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c);
	
	public long getFilter_HospitalAttached_ListCount(String search, String university_id, String institute_id,
			String status,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c);

	public List<Map<String, Object>> getFilter_Migrated_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id,String institute_status,String institute_id,String name_of_inst,
			String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,
			String remarks_form_d);
	
	public long getFilter_Migrated_ListCount(String search, String university_id,String institute_status,String institute_id,String name_of_inst,
			String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,
			String remarks_form_d);
	
	public List<Map<String, Object>> getFilter_Migrated_From_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id,String institute_id,String institute_status, String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated);

	public long getFilter_Migrated_From_ListCount(String search, String university_id,String institute_id,String institute_status, String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated);

	public List<Map<String, Object>> getFilter_Examiners_appointed_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String university_approved_status,
			String council_approved_status,String university_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
			String teaching_experience,String teacher_code, String reg_number,String d_university_appointment);

	public long getFilter_Examiners_appointedListCount(String search, int university_id,int user_id,String university_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
			String teaching_experience,String teacher_code, String reg_number,String d_university_appointment);
	
	public List<Map<String, Object>> getFilter_Intern_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);

	public long getFilter_Intern_ListCount(String search, String university_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);
	 
	public DG_REC_UG_FORM_A getugByid(int id);

	public DG_REC_EXAMINERS_APPOINTED getugexamByid(int id);
	
	public List<Map<String, Object>> getInstituteListbyUserID();
	public ArrayList<ArrayList<String>> getinstitutelist(String userid);

	public ArrayList<ArrayList<String>> get_inst_uni_data(String university_id);


	public ArrayList<ArrayList<String>> getpersonal_details(int userid);
	public ArrayList<ArrayList<String>> getUni_detail(int userid);

	public long get_count_student(int institute_id);

	public List<EDU_LMS_INSTITUTE_REGISTRATION> getuniversitylistUrl(Integer selval);

//	public ArrayList<ArrayList<String>> getdataofcertiforberism(String id);

	public String getFilePathQueryForDocAttFormA(String upload_docattsub);
	public String getFilePathQueryForDocAttFormB(String upload_docattsub);

	//public String reject(String a, String inst_status, String username);
	public ArrayList<ArrayList<String>> getDegreeListFromInstitute(String institute_id,String userId,String role);

}
