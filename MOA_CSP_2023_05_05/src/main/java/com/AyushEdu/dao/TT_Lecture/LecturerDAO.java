package com.AyushEdu.dao.TT_Lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_PLAN_TEMP;

public interface LecturerDAO {
	
	public EDU_LEC_PLAN_TEMP getlecturerByid(int id);

	public long DataTableLecturerDataTotalCount(String Search, String orderColunm, String orderType, String professional, String course_name,String topic,
			String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, String academic_year, String fdate,
			 String time, String time_rem, String activity_description, String resources, String assessment_method,String userId);

	public List<Map<String, Object>> DataTableLecturerDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional, String course_name,String topic,
			String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, String academic_year, String fdate,
			 String time, String time_rem, String activity_description, String resources, String assessment_method, String userId);
	
	public ArrayList<ArrayList<String>> getLecturerDao(String learning_objective);
	public ArrayList<ArrayList<String>> gettime_duration(String fdate,String userid);
	public ArrayList<ArrayList<String>> getlearningobj(String courseid);

	public ArrayList<ArrayList<String>> getTopic_name(String course_id,String userid);

	public ArrayList<ArrayList<String>> getLearning_Objective(String topic_id, String userid);

	public EDU_LEC_PLAN_TEMP getlectureByid(int id);

	public ArrayList<ArrayList<String>> getPrev_Lec_HRDao(HttpSession session,String professional, String course_name, String topic,
			String learning_objective);

	public ArrayList<ArrayList<String>> getCourse(String professional_id, String system_id, String userid);

	public ArrayList<ArrayList<String>> getFaculty(String institute_id);

}

