package com.AyushEdu.dao.Degree_recognition_Quali_Grant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_OUT_INDIA;

public interface University_Out_IndiaDAO {
	

	public long DataTable_Out_UniversityDataTotalCount(String search, String country_name, String university_name,
			String college_name, String abbreviation, String medical_course_name, String validity_period,
			String digital_code);
	public List<Map<String, Object>> DataTable_Out_UniversityDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String country_name, String university_name, String college_name,
			String abbreviation, String medical_course_name, String validity_period, String digital_code);

	public String updateoutuniversity(DG_REC_UNIVERSITY_OUT_INDIA td);

	ArrayList<ArrayList<String>> getAbbreviationoutdao(String institute_id);

	
}
