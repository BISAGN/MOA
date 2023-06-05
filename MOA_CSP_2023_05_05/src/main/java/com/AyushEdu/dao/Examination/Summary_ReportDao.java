package com.AyushEdu.dao.Examination;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Summary_ReportDao {
	public List<Map<String, Object>> get_Summary_report(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role,String userid) throws ParseException;

	public long getTotal_Summary_report_dataCount(String search, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role) throws ParseException;
}
