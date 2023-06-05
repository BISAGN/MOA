package com.AyushEdu.dao.Examination;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Student_Result_Declaration_Report_Dao {

	public List<Map<String, Object>> getFilter_Student_result_report(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String degree_id, String professional_id, String date_of_exam,
			String institute_id, String result_status, String role,String institute_id1) throws ParseException;

	public long getTotal_Student_result_report_dataCount(String search, String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, String role,String institute_id1) throws ParseException;

	public ArrayList<ArrayList<String>> getDegreeLFromInstituteExam(String institute_id, String userId, String role);

	public ArrayList<ArrayList<String>> getStudent_Result_Report_Excel(String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, String role, String search, String institute_uid)
			throws ParseException;

}
