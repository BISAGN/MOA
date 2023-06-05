package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Link_System_Elective_CourseDAO {
	public ArrayList<ArrayList<String>> GetSystemCourse_Degree(String system_id,String degree_id);
	public ArrayList<ArrayList<String>> getDegreeFromCourse_Checked(String degree_id,String system_id);
	
	

	public ArrayList<ArrayList<String>> DataTableLink_System_Elective_CourseDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,String system_id,String degree_id);

	public long DataTableLink_System_Elective_CourseDataTotalCount(String search, HttpSession sessionUserId,String system_id,String degree_id);


	

}
