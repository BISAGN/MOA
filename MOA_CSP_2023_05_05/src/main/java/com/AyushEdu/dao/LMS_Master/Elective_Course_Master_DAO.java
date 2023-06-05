package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;

public interface Elective_Course_Master_DAO {

	

	public List<Map<String, Object>> DataTableEle_Course_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name,String upload_img, String status,String degree_id,String semester_id,String demo_video);
	

	public long DataTableEle_Course_masterDataTotalCount(String Search, String course_name,String upload_img, String status,String degree_id,String semester_id,String demo_video);
    

     
	//shivali
		public EDU_LMS_ELECTIVE_COURSE_MASTER getEle_CourseByid(int id);

//start image fetch
		String getImagePath(String id);
//end
		
		public String getdemoVideoPath(int id);

}
