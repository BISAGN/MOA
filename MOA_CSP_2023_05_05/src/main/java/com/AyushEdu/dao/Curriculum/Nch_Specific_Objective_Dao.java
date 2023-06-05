package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.CC_TB_NCH_SPECIFIC_OBJECTIVE;

public interface Nch_Specific_Objective_Dao {
	
	public List<Map<String, Object>> DataTableNch_Specific_ObjectiveDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String specific_objective, String status,String role);

	public long DataTableNch_Specific_ObjectiveDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String specific_objective, String status,String role);

	public String updateNch_Specific_Objective(CC_TB_NCH_SPECIFIC_OBJECTIVE td);

}
