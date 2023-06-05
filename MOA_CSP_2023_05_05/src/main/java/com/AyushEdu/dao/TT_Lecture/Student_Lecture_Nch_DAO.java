package com.AyushEdu.dao.TT_Lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Student_Lecture_Nch_DAO {

	Object getNchFacultyData(String userid);

	Object TotalAttendedNchLecture(String userId);

	Object TotalNotAttendedNchLecture(String userId);

	Object TotalNchLecture(String professional, int institute_id);

	List<Map<String, Object>> DataTableNchStudentLecturerDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String professional, String start_time, String end_time,
			String course_name, String faculty, String topic_id, String learning_outcome,
			String instructional_method, String sdate, String userId);

	long DataTableNchStudentLecturerDataTotalCount(String search, String orderColunm, String orderType,
			String professional, String start_time, String end_time, String course_name, String faculty, String topic_id,
			String learning_outcome, String instructional_method, String sdate, String userId);

	ArrayList<ArrayList<String>> getNchfacultydetailsDao1(String course_id);

	ArrayList<ArrayList<String>> getNchCourse1(String professional);

	ArrayList<ArrayList<String>> getNchTopic_name1(int course_id, String userid);

	ArrayList<ArrayList<String>> NchLectureDataByDate(int institute_id, String ldate, int parseInt);

	ArrayList<ArrayList<String>> NchLectureDataByDateforEdit(int institute_id, int student_id, String sdate, int parseInt);


}
