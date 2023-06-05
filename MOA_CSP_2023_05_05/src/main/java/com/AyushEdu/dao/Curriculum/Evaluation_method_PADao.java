package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_EVALUATION_METHOD_PA;

public interface Evaluation_method_PADao {

	public List<Map<String, Object>> DataTableEvalu_Method_PADataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String evaluation_method_pa_id, String status, String role);

	public long DataTableEvalu_Method_PADataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String evaluation_method_pa_id,String status, String role);

	public String updateEvalu_Method_PA(EDU_CC_TB_EVALUATION_METHOD_PA obj);
}
