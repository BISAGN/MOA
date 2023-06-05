package com.AyushEdu.dao.LMS_Attendance;

import java.util.ArrayList;

public interface Exp_Excel_Student_Attendance_Dao {

//	public ArrayList<ArrayList<String>> Exp_Excel_Student_attendance(String institute_id);

	ArrayList<ArrayList<String>> Exp_Excel_Student_attendance(String institute_id, String userid,String role,String role_id,String professional_id);
	
	public ArrayList<ArrayList<String>>systemofstud_excel(String userid);

	ArrayList<ArrayList<String>> getCourseidByCoursename(String course_name);



}
