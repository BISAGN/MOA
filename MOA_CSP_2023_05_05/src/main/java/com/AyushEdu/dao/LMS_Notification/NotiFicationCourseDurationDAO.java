package com.AyushEdu.dao.LMS_Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface NotiFicationCourseDurationDAO {
	
	public ArrayList<ArrayList<String>> GetDays(String system,String course);
	
	
	public List<Map<String,Object>> getFilterNoticourse_data(int startPage,int pageLength,String Search,String orderColunm,String orderType, 
			String course,String system,String degree,String ayushid,String name);
	
	public long DataTotalCountNotiCourseData(String Search,String course,String system,String degree,String ayushid,String name);

}
