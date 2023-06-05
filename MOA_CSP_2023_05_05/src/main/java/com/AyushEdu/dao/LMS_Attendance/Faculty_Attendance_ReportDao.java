package com.AyushEdu.dao.LMS_Attendance;

import java.util.ArrayList;

public interface Faculty_Attendance_ReportDao {

	public ArrayList<ArrayList<String>> faculty_attendance_report(String month, String year, String teach_code_or_ayush_id,String role,String instid);

}
