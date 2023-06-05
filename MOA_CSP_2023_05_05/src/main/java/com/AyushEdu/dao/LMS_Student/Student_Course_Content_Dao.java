package com.AyushEdu.dao.LMS_Student;

import java.util.List;
import java.util.Map;

public interface Student_Course_Content_Dao {
	
	
	public List<Map<String,Object>> Student_Course_nameDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name, String module_name,String ref_video,String upload_file, String app_status,String role);
	public String GenerateQueryWhereClause_SQL(String Search, String course_name, String module_name,String ref_video, String upload_file, String app_status);
	
	public long DataTotalCount(String search, String course_name,String module_name,String ref_video, String upload_file, String app_status,String role);

	public String getFilePathQueryForDocFile(String id);

}
