package com.AyushEdu.dao.LMS_Attendance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Student_Attendance_ReportDao {
	
	public ArrayList<ArrayList<String>>student_attendance_report(String month,String year);
	public ArrayList<ArrayList<String>>atdnsreport(String print,String month_year);
	public List<Map<String, Object>> getCourselistofStudent(String system,String degree,String professional);
	public List<Map<String, Object>> getStudentSysDegProf(String userid,String role);
//	ArrayList<ArrayList<String>> getPopup_ChildDatalist(String userid, String course_name,String role,String role_id);
	public List<Map<String, Object>> getStudcountPA(String crsid , String userid1, String role,String attendance);
	public ArrayList<ArrayList<String>> getPopup_ChildDatalist(String userid, String course_name, String role, String role_id,
			String month, String year, String instid) throws ParseException;

}
