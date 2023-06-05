package com.AyushEdu.dao.LMS_NCISM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface CourseDuration_Dao {
	
	///course duration list
		public ArrayList<ArrayList<String>> getCourse_Duration(SessionFactory sessionFactory, String userId);
		public ArrayList<ArrayList<String>> getCourse_Start_Date(SessionFactory sessionFactory);
		public ArrayList<ArrayList<String>> getcoursenameList_new(SessionFactory sessionFactory);
		public ArrayList<ArrayList<String>> getsystem_list(String userId);

		
		/////////Riddhi
		
		public ArrayList<ArrayList<String>> getCourse_End_Date(SessionFactory sessionFactory);
		public ArrayList<ArrayList<String>> getCourses_System_degree_term_Fetch(String system_id,String degree_id,String term_id);

		//Courses
//		public String getTopicChoose_Ele_Course_Stu_Search(String id);
//		public ArrayList<ArrayList<String>> getCourse_Description_fetch_new_Search(String course_category,String course_duration,String course_start_date);

		//MyCourses
		public String Already_Applied_Path_fetch_list_My_Courses_Search(String id);
		public ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses_List_Search(String course_duration2,String course_start_date2,String system_id2,String degree_id2,String term_id2,String role);

		
		
}
