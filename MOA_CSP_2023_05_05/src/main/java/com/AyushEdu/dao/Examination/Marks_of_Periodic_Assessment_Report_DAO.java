package com.AyushEdu.dao.Examination;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Marks_of_Periodic_Assessment_Report_DAO {

	public List<Map<String, Object>> getFilter_Marks_perodic_reports_data(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String degree_id, String professional_id, String term_id,
			String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id, String role,
			String institute_id1) throws ParseException;

	public long DataTableEdu_Marks_perodic_reports_Count(String search, String degree_id, String professional_id,
			String term_id, String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id, String role,
			String institute_id1) throws ParseException;

	public ArrayList<ArrayList<String>> getDegreeFromInstituteExam(String institute_id, String userId, String role);
}
