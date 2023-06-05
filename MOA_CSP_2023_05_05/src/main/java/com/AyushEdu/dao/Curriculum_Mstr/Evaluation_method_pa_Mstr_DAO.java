package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_EVALUATION_METHOD_PA_MSTR;

public interface Evaluation_method_pa_Mstr_DAO {
	public List<Map<String, Object>> DataTableEvaluation_method_paDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String system_id, String evaluation_method_pa, String status,String role);

	public long DataTableEvaluation_method_paDataTotalCount(String search,String system_id, String evaluation_method_pa,String status,String role);
	public EDU_CC_TB_EVALUATION_METHOD_PA_MSTR getEvaluation_method_paByid(int id);
	public String updateEvaluation_method_pa(EDU_CC_TB_EVALUATION_METHOD_PA_MSTR td);
}
