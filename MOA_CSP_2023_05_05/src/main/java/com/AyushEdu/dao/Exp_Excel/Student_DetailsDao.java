package com.AyushEdu.dao.Exp_Excel;

import java.util.ArrayList;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;


public interface Student_DetailsDao {
	
	public ArrayList<ArrayList<String>> DataTableStudentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree);
	
	public long DataTableStudentDataTotalCount(String search, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree);
	
}