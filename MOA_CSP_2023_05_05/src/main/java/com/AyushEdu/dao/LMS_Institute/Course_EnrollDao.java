package com.AyushEdu.dao.LMS_Institute;
import java.util.ArrayList;

import org.hibernate.SessionFactory;

public interface Course_EnrollDao {
	
	public ArrayList<ArrayList<String>> GetCourse_Set(String system_id,String term_id,String degree_id, String userid);
	
	public ArrayList<ArrayList<String>> GetModule_fetch(String course_id);
	
	public ArrayList<ArrayList<String>> GetSummary(String course_id);
	
	public ArrayList<ArrayList<String>> GetCourse_Description(String userid,String system_id,String degree_id,String p_id);
	
	public ArrayList<ArrayList<String>> GetLearn_Count(String system_id);
	
	public String getTopicVideoPath(String id);
	
	public ArrayList<ArrayList<String>> GetCourse_Exit(String system_id,String degree_id,String userid);
	
//	public ArrayList<ArrayList<String>> Getjoindate_fetch(String course_id,String userid);
	
//	public ArrayList<ArrayList<String>> GetSetModule_Fetch(String course_id,String userid); 
	
	public ArrayList<ArrayList<String>> GetlevelofCoursese(String course_id);
	
	public String getCoursedemoVideoPath(int id);
	//shivali
	public ArrayList<ArrayList<String>> getIfExitCourseWiseSet(String userId,String set_id);


}
