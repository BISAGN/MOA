package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR;

public interface CC_D3_Scope_Of_Understanding_Mstr_Dao {

	public List<Map<String, Object>> DataTableScopeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String scope, String status,String system_id ,String role);

	public long DataTableScopeDataTotalCount(String search, String scope,String status, String ssytem_id, String role);
	
	public CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR getScopeByid(int id);

	public String updateScope(CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR td);



}
