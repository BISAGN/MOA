package com.AyushEdu.dao.Time_Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TIME_TABLE_DAO {

	ArrayList<ArrayList<String>> getweeklytimetable(String sdate, String degree, String professional,int institute_id);
	
	ArrayList<ArrayList<String>> getWeeklyTimetable(String sdate, String professional,int institute_id);

	ArrayList<ArrayList<String>> getcoursedetailsDao(String degree_id);

	ArrayList<ArrayList<String>> getfacultydetailsDao(String course_id, int institute_id);

	ArrayList<ArrayList<String>> getlayouttimetableDao(String degree, String professional, int institute_id);

	ArrayList<ArrayList<String>> getcoursehours(String degree, String professional);
	
	ArrayList<ArrayList<String>> getDailyTimetable(String ldate, String degree, String professional, int institute_id);
	
	ArrayList<ArrayList<String>> getDailyttlayoutDao(String degree, String professional,String day, int institute_id);
	
	ArrayList<ArrayList<String>> getLecturedataDao(String lec_id);
	public List<Map<String,Object>> getTimetableList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, int institute_id);

	public long getTimetableListCount(String Search, int institute_id);
	
	ArrayList<ArrayList<String>> getweeklyExamList(String sdate, String professional,int institute_id);
	
//	ArrayList<ArrayList<String>> getweeklyAcademicList(String sdate, String professional,int institute_id);
	
	ArrayList<ArrayList<String>> getweeklyEventList(String event_date, int institute_id);
	
	//-----------------------------------Hiral--------------------------------------------------//
	
	//-----------------------------------Validate Time For Layout----------------------------//
	
	public long ValidateTimeForLayout(String day, String degree, String professional,
			String start_time, String end_time, int institute_id,String id);
	
	//-----------------------------------Validate Time For Extra-class----------------------------//
	
	public long ValidateTimeForExtraClass(String ldt, String degree, String professional,
			String start_time, String end_time, int institute_id);
	
	ArrayList<ArrayList<String>> getweeklyTransitionalList(String sdate, String professional,int institute_id);

//	ArrayList<ArrayList<String>> getDelLecdataDao(String lec_id);
	
	
	
}
