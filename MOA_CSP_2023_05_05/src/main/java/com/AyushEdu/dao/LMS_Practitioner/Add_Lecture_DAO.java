package com.AyushEdu.dao.LMS_Practitioner;

import java.util.List;
import java.util.Map;

public interface Add_Lecture_DAO {

	public List<Map<String,Object>> ADDLecDataList(int startPage,int pageLength,String Search,String orderColunm,String orderType, 
			String system,String degree,String from_date,String to_date);
	
	public long DataTotalCount(String Search,String system,String degree,String from_date,String to_date);
	
	public String getLecVideoPath(int id);
	
	public List<Map<String,Object>> ViewLecDataList(int startPage,int pageLength,String Search,String orderColunm,String orderType);
	
	public long DataTotalCountView(String Search);
	
	
	
}
