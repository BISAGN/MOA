package com.AyushEdu.dao.TT_Lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface Student_Lecture_DAO {

	public ArrayList<ArrayList<String>> getCourse1(String professional);

	public ArrayList<ArrayList<String>> getTopic_name1(int course_id, String userid);
	
	public ArrayList<ArrayList<String>> getSubTopic_name1(int topic_id, String userid);

	public ArrayList<ArrayList<String>> getLearning_Objective1(String topic_id, String userid);

	public ArrayList<ArrayList<String>> getFacultyData(String userId);

	public ArrayList<ArrayList<String>> getfacultydetailsDao1(String course_id);
	
	public ArrayList<ArrayList<String>> LectureDataByDate(int institute_id,String ldate,int professional);

	public ArrayList<ArrayList<String>> LectureDataByDateforEdit(int institute_id,int student_id,String sdate,int professional);
	
	public List<Map<String, Object>> DataTableStudentLecturerDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional,String start_time,String end_time, String course_name,
			String faculty, String topic,String sub_topic,String learning_objective, String instructional_method, 
			String sdate, String userId);

	public long DataTableStudentLecturerDataTotalCount(String Search, String orderColunm, String orderType,
			String professional,String start_time,String end_time,String course_name, String faculty, String topic, 
			String sub_topic,String learning_objective,String instructional_method, String sdate, String userId);
	
	public String TotalAttendedLecture(String student_id);
	
	public String TotalNotAttendedLecture(String student_id);
	
	public String TotalLecture(String professional,int institute_id);
}
