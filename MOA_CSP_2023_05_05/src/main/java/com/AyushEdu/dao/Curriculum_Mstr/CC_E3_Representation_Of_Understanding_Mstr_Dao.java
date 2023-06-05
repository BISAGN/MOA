package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR;

public interface CC_E3_Representation_Of_Understanding_Mstr_Dao {

	public List<Map<String, Object>> DataTableRepUndDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String rep_und, String status,String system_id ,String role);

	public long DataTableRepUndDataTotalCount(String search, String rep_und, String status,String system_id ,String role);
	
	public CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR getRepUndByid(int id);

	public String updateRepUnd(CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR td);

	
}
