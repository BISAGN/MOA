package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROGRAM_OUTCOME_MSTR;

public interface CC_Program_Outcome_Mstr_Dao {

	public List<Map<String, Object>> DataTableProgram_OutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id,String code, String program_outcome, String status,String degree_id,
			String professional_id, String course_id,String role);
	public long DataTableProgram_OutcomeDataTotalCount(String Search,String system_id,String code, String program_outcome,String status,String degree_id,
			String professional_id, String course_id,String role);
	public String updateProgram_Outcome(CC_TB_PROGRAM_OUTCOME_MSTR td);
}