package com.AyushEdu.dao.TT_Lecture;

import java.util.List;
import java.util.Map;

public interface Institute_Lecture_DAO {

	List<Map<String, Object>> DataTableLecturerInsNchDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String faculty, String professional, String course_name, String topic_id,
			String learning_outcome, String lecture_hours, String non_lecture_hours, String instructional_method,
			String academic_year, String fdate, String time, String time_rem, String activity_description,
			String resources, String assessment_method, String userId, int institute_id);

	long DataTableLecturerInsNchDataTotalCount(String Search, String orderColunm, String orderType,String faculty, String professional,
			String course_name, String topic_id, String learning_outcome, String lecture_hours,
			String non_lecture_hours, String instructional_method, String academic_year, String fdate, String time,
			String time_rem, String activity_description, String resources, String assessment_method, String userId, int institute_id);

	List<Map<String, Object>> DataTableLecturerInsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String faculty, String professional, String course_name,
			String topic, String learning_objective, String lecture_hours, String non_lecture_hours,
			String instructional_method, String academic_year, String fdate, String time, String time_rem,
			String activity_description, String resources, String assessment_method, String userId, int institute_id);

	long DataTableLecturerInsDataTotalCount(String search, String orderColunm, String orderType, String faculty,
			String professional, String course_name, String topic, String learning_objective, String lecture_hours,
			String non_lecture_hours, String instructional_method, String academic_year, String fdate, String time,
			String time_rem, String activity_description, String resources, String assessment_method, String userId,
			int institute_id);

}
