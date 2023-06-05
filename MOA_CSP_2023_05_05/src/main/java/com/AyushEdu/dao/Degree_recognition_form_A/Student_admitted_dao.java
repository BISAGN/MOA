package com.AyushEdu.dao.Degree_recognition_form_A;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_INTERN_STUDENT_COURSE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_SUB_CHILD;

public interface Student_admitted_dao {
	
	public List<Map<String,Object>> getFilter_Admitted_Student_list(int startPage, int pageLength,String Search, 
			String orderColunm, String orderType,int university_id,int user_id,String institute_status,String student_name,
			String date_of_admission, String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) ;
	
	public long getFilter_Admitted_StudentListCount(String search, int university_id,int user_id,String institute_status,String student_name,
			String date_of_admission, String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b);
	
	public List<Map<String, Object>> getFilter_Hospital_Attached_list(int startPage, int pageLength,String Search, 
			String orderColunm, String orderType,int university_id,int user_id,String institute_status,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c);

	public long getFilter_Hospital_AttachedListCount(String search, int university_id,int user_id,String institute_status,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c);
	
	public List<Map<String, Object>> getFilter_Migrated_Student_list(int startPage, int pageLength, String search,
            String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_inst,String student_name_to_migrated,
			String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d);
	
	public long getFilter_Migrated_StudentListCount(String search, int university_id,int user_id,String institute_status,String name_of_inst,String student_name_to_migrated,
			String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d);
	
	public List<Map<String, Object>> getFilter_Migrated_Student_From_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated);
	
	public long getFilter_Migrated_Student_FromListCount(String search, int university_id,int user_id,String institute_status,String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated);

    public List<Map<String, Object>> getFilter_Intern_Student_list(int startPage, int pageLength, String search,
            String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);
    
	public long getFilter_Intern_StudentListCount(String search, int university_id,int user_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f);
	
	public DG_REC_ADMITTED_STUDENT getadmittedByid(int id);

	public DG_REC_HOSPITAL_ATTACHED getughospByid(int id);
	
	public List<Map<String, Object>> getmigratedtoByid(String id);

	public List<Map<String, Object>> getmigratedfromByid(String id);

	public DG_REC_INTERN_STUDENT_COURSE getinternByid(int id);

	public ArrayList<ArrayList<String>> getUni_user_id(String uni_id);

	public String getImagePath(String i_id, String myImg);

	public String getFilePathQueryForDocAttFormA(String upload_docformA);

	
}
