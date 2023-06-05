package com.AyushEdu.dao.LMS_Teacher;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TeacherReportDao {
	
	
	public List<Map<String, Object>> DataTableTeacher_DataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid);
	
	public long DataTableTeacher_DataTotalCount( String Search, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid);

	
	public ArrayList<ArrayList<String>> getTeacher_Report_Excel( String ayush_id,String teacher_code,String faculty_name,String university_id, String institute_id,String ug_pg,String subject,String gender,String date_of_birth,String experience,String state_name,String district,String village,String othquali,String role,String userid)
			throws ParseException;
	
	
	
	public ArrayList<ArrayList<String>> getStatelistlogin(String state_id);
	
	public ArrayList<ArrayList<String>> getfromdatelogoninfo(int userid);
	
	
}
