package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Student_DetailsDao {

	public List<Map<String, Object>> DataTableSearch_Student_DetailsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck, String role, String userid,
			String institute_id);

	public long DataTableSearch_Student_DetailsDataTotalCount(String search, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck,
			String role, String userid, String institute_id);
	
	public ArrayList<ArrayList<String>> getAdmitted_Student_Details(int institute_id);

}
