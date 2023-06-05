package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Link_Graduate_Attribute_And_System_Dao {
	
	
	public List<Map<String, Object>> DataTableGraduatelinksystemDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name,String degree_name,String graduate_attribute, String status,String role);

	public long DataTableGraduatelinksystemTotalCount(String search, String system_name,String degree_name,String graduate_attribute,String status,String role);
	
	

}
