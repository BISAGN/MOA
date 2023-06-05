package com.AyushEdu.dao.LMS_Course_Publisher;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_PUBLISHER_MSTR;

public interface Course_Publisher_Staff_DAO {
	public List<Map<String, Object>> DataTablePubli_Staff_Act_InactDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name,String status);

	public long DataTablePubli_Staff_Act_InactDataTotalCount(String search, String name,String status);
	
//	public EDU_LMS_COURSE_PUBLISHER_MSTR getCourse_PublisherByid(int id);

}
