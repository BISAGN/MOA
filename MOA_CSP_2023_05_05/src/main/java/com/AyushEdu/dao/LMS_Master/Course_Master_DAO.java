package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;


public interface Course_Master_DAO {
	
	
	public List<Map<String, Object>> DataTable_Course_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_name, String type_of_content_id, String status, String course_code);
	

	public long DataTable_Course_masterDataTotalCount(String Search,String course_name, String type_of_content_id, String status, String course_code);
    

     
	
		public EDU_LMS_COURSE_MASTER get_CourseByid(int id);

}
