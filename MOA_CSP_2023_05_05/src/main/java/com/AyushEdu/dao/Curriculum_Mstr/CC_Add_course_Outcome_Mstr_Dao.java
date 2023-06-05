package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;

public interface CC_Add_course_Outcome_Mstr_Dao {

	public List<Map<String, Object>> DataTableCourse_OutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_id, String course_outcome,String co_code, String status,String system_id ,String degree_id,
			String professional_id,String role);

//	public long DataTableCourse_OutcomeDataTotalCount(String Search,String course_id,String co_code, String status);

	public String updateCourse_Outcome(CC_TB_ADD_COURSE_OUTCOME_MSTR td);

	long DataTableCourse_OutcomeDataTotalCount(String Search, String course_id, String course_outcome, String co_code,
			String status,String system_id ,String degree_id,
			String professional_id,String role);

}
