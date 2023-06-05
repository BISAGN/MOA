package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_NCH_PRACTICAL_MSTR;

public interface NCH_Practical_Dao {
	
	public List<Map<String, Object>> DataTablePractMstrDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_id, String practical, String status,String system_id ,String degree_id,
			String professional_id,String role);

	public long DataTablePractMstrDataTotalCount(String Search,String course_id, String practical,String status,String system_id ,String degree_id,
			String professional_id,String role);

	public String updatepract(EDU_CC_TB_NCH_PRACTICAL_MSTR td);
	

}
