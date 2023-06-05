package com.AyushEdu.dao.Examination;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Report_Dao {
	
	public List<Map<String, Object>> get_report(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role,String userid) throws ParseException;

	public long getTotal_report_dataCount(String search, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role) throws ParseException;
	
	public ArrayList<ArrayList<String>> getStudent_Report_Excel(String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role, String search)
			throws ParseException;
	
	public ArrayList<ArrayList<String>> getuniversity_list(String state_id,String role);
}
