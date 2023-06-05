package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_PUBLISHER_MSTR;

public interface Course_Publisher_Mstr_DAO {
	
	public List<Map<String, Object>> DataTablecourse_publisherDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name,String status);

	public long DataTablecourse_publisherDataTotalCount(String search, String name,String status);
	
	public EDU_LMS_COURSE_PUBLISHER_MSTR getCourse_PublisherByid(int id);

}
