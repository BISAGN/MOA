package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR;

public interface Formative_And_Summative_Mstr_Dao {
	public List<Map<String, Object>> DataTableFormative_And_SummativeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String formative, String status,String role);

	public long DataTableFormative_And_SummativeDataTotalCount(String Search,String system_id, String formative, String status,String role);

	public String updateFormative_And_Summative(CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR td);

}
