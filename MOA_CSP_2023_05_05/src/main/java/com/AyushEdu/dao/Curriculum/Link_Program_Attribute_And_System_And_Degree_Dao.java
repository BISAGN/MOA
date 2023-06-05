package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

public interface Link_Program_Attribute_And_System_And_Degree_Dao {
	
	
	public List<Map<String, Object>> DataTableProgramlinksystemDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name,String degree_name,String program_outcome, String status,String role);

	public long DataTableProgramlinksystemTotalCount(String search, String system_name,String degree_name,String program_outcome,
			String status,String role);
	
	
	

}
