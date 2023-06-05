package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.CC_TB_LINK_EXAM_AND_COURSE;

public interface Link_Exam_and_CourseDao {

	public List<Map<String, Object>> DataTableLink_Exam_and_CourseDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String term_id, String exam_type_id, String no_of_exam, String status, String role);

	public long DataTableLink_Exam_and_CourseDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String term_id, String exam_type_id, String no_of_exam,String status,
			String role);

	public String updateLink_Exam_and_Course(CC_TB_LINK_EXAM_AND_COURSE obj);
}
