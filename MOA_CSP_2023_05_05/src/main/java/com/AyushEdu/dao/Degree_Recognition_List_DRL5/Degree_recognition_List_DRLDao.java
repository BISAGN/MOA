package com.AyushEdu.dao.Degree_Recognition_List_DRL5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_DEGREE_RECOGNITION_LIST_DRL;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA;
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;

public interface Degree_recognition_List_DRLDao {
	
	
	ArrayList<ArrayList<String>> getAbbreviationdaome(String institute_id);
	
	List<Map<String, Object>> DataTableDegreeRecognitionListDataLista(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String applicant_name,String country_name, String university_name, String college_name,
			String abbreviation, String medical_course_name, String validity_period, String digital_code);

	
	long DataTableDegreeRecognitionListDataTotalCounta(String search, String applicant_name,String country_name, String university_name,
			String college_name, String abbreviation, String medical_course_name, String validity_period,
			String digital_code);
	
	public String updatedegreerecognitionlistA(DG_REC_DEGREE_RECOGNITION_LIST_DRL td);

	//////////// FORM B ///////////////
	
	List<Map<String, Object>> DataTableRecMedQuaInIndiaDataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType,String university_name,
			String college_id, String abbreviation,String medical_course_name,  String remarks);

	long DataTableRecMedQuaInIndiaDataTotalCount(String search, String university_name, String college_id,String abbreviation,
			String medical_course_name, String remarks);

	String updaterecmedquainindia(DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA obj);

	//////////// FORM C ///////////////

	List<Map<String, Object>> DataTableRecMedQuaOutIndiaDataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType,String university_name,
			String college_id, String abbreviation,String medical_course_name,  String remarks);

	long DataTableRecMedQuaOutIndiaDataTotalCount(String search, String university_name, String college_id,String abbreviation,
			String medical_course_name, String remarks);

	String updaterecmedquaoutindia(DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA obj);
}
