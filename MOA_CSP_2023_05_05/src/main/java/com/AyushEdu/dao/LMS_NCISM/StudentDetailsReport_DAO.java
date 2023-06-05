package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public interface StudentDetailsReport_DAO {

	ArrayList<ArrayList<String>> DataTablestudentdetailsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
			String pg_degree,String pg_subject,String pg_intake);

	
	long DataTablestudentdetailsTotalCount(String search, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
			String pg_degree,String pg_subject,String pg_intake);
	

List<ArrayList<String>> getstudentdetails_Report_Excel(String search, String userid,
		String institute_id, String institute_code, String state_id,
		String name,String last_name,String mother_name, String father_name,
		String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
		String neet_percentile, String neet_marks, 
		String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
		String pg_degree,String pg_subject,String pg_intake);
	
}
