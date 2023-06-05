package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE;

public interface CC_System_Professional_Degree_Course_Dao {

	public EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE getsysdegprocourselinkdata(int id);

	public List<Map<String, Object>> DataTablesysDegProCourselinkDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id,String type_of_degree, String degree_id, String professional_id,
			String course_id, String status, String role);

	public long DataTablesysDegProCourselinkDataListTotalCount(String Search, String system_id,String type_of_degree, String degree_id,
			String professional_id, String course_id, String status, String role);

	public String updateSystem_Professional_Degree_Course(EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE td);
}
