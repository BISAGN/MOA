package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;

public interface Course_Duration_EnrollDao {

public ArrayList<ArrayList<String>> GetCourse_Set(String course_id );
	
	public ArrayList<ArrayList<String>> GetModule_fetch(String set ,String course_id);
	
	public ArrayList<ArrayList<String>> GetSummary(String course_id);
	
	public ArrayList<ArrayList<String>> GetCourse_Description(String course_id );
	
	public ArrayList<ArrayList<String>> GetCourse_Title(String course_id );
	
	public ArrayList<ArrayList<String>> GetLearn_Count(String course_id );
	
	public String getTopicVideoPath1(String id);
	
//	public ArrayList<ArrayList<String>> GetCredit_Point(String course_id);
	
	public ArrayList<ArrayList<String>> GetSetModule_Fetch(String course_id,String userid); 
	
	public ArrayList<ArrayList<String>> GetlevelofCoursese(String course_id);

}
