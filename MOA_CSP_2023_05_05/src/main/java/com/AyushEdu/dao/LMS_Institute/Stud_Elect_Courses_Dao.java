package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

public interface Stud_Elect_Courses_Dao {
	///course duration list
	public ArrayList<ArrayList<String>> getCourse_Duration(SessionFactory sessionFactory, String userId,String system_id);
	public ArrayList<ArrayList<String>> getCourse_Start_Date(SessionFactory sessionFactory,String system_id);
	public ArrayList<ArrayList<String>> getcoursenameList_new(SessionFactory sessionFactory,String system_id);
	
	///shivali
	public String getCourse_wise_images_fetch_list(String id,String system_id);
	public ArrayList<ArrayList<String>> getCourse_Description_fetch_list(String userId,String course_category,String course_duration,String course_start_date,String system_id1,String institute_id);
	
//	public String OnGoing_Enroll_Closed_Path_fetch_list(String id,String system_id);
//	public ArrayList<ArrayList<String>> getCourse_Description_fetch_OnGoing_Enroll_Closed_list(String course_category1,String course_duration1,String course_start_date1,String system_id,String userId);

//	public String Already_Applied_list_Path_fetch_list(String id,String userId, String system_id);
//	public ArrayList<ArrayList<String>> getCourse_Description_fetch_Already_Applied(String userId,String course_category2,String course_duration2,String course_start_date2,String system_id2);
	
	public ArrayList<ArrayList<String>> getsystem_list(String userId);
	public ArrayList<ArrayList<String>> getdegree_list(String userId);
	public ArrayList<ArrayList<String>> getterm_list(String userId);
	
	///Courses
	public String getTopicChoose_Ele_Course_Stu(String id);
	public ArrayList<ArrayList<String>> getCourse_Description_fetch_new(String userId,String course_category,String course_duration,String course_start_date,String system_id1,String degree_id1,String term_id1);

	//MyCourses
	public String Already_Applied_Path_fetch_list_My_Courses(String id,String userId, String system_id);
	public ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses_List(String userId,String course_category2,String course_duration2,String course_start_date2,String system_id2,String degree_id2,String term_id2);
	
	//switch duration Exit Courses
	public ArrayList<ArrayList<String>> getExitCourse_Switch_Duration(String course_category);
	public ArrayList<ArrayList<String>> getExitCourse_count(String userId, String set_id);
	

}
