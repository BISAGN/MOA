package com.AyushEdu.dao.Degree_recognition_Quali_Grant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;

public interface University_In_IndiaDAO {

//	ArrayList<ArrayList<String>> getCollegeListdao(String university_id);
	ArrayList<ArrayList<String>> getAbbreviationdao(String institute_id);

	public long DataTableUniversityDataTotalCount(String search, String country_name, String university_name,
			String college_name, String abbreviation, String medical_course_name, String validity_period,
			String digital_code);

	public List<Map<String, Object>> DataTableUniversityDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String country_name, String university_name, String college_name,
			String abbreviation, String medical_course_name, String validity_period, String digital_code);

	public String updateuniversity(DG_REC_UNIVERSITY_IN_INDIA td);

	

}
