package com.AyushEdu.dao.Exp_Excel;

import java.util.ArrayList;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_FACULTY_NCH;

public interface Faculty_DetailsDao {
	
	
	
	public long DataTableFacultyDataTotalCount(String Search, String userid,String dob,String aadhar_card,String email,
			String mobile_no, String role_type,String upload_date,String role) ;

	public ArrayList<ArrayList<String>> DataTableFacultyDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String userid, String dob, String aadhar_card,
			 String email,String mobile_no, String role_type,String upload_date,String role);
	
	public EDU_LMS_FACULTY_NCH getFacultyDataByid(int id);
	
	

}
