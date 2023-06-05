package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Course_Content_Dao {
	
	public List<Map<String,Object>> Course_nameDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name, String module_name,String level_of_module,String type_of_content,String system_name, String degree,String role);
	
	public long DataTotalCount(String Search, String course_name,String module_name ,String level_of_module,String type_of_content,String system_name,String degree, String role);

	public String getFilePathQueryForDocFile(String id,String fildname);
	public ArrayList<ArrayList<String>> getlevelofmodule(String id);

	
}
