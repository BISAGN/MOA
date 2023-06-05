package com.AyushEdu.dao.LMS_Attendance;

import java.util.ArrayList;

public interface Staff_Attendance_ReportDao {
	
	public ArrayList<ArrayList<String>>staff_attendance_report1(String month,String year,String name,String teach_code,String role, String role_id,String userid,String instid);
//	public ArrayList<ArrayList<String>>staff_attendance_report(String month,String year);

	

}
