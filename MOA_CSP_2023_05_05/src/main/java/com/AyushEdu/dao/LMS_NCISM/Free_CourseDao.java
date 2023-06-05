package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;



public interface Free_CourseDao {

	
	public ArrayList<ArrayList<String>> DataTablefc_urlDataList(int startPage, int pageLength, String search,String coursename,
			String orderColunm, String orderType, String url, String description, HttpSession sessionUserId,String role);

	public long DataTablefc_urlDataTotalCount(String search, HttpSession sessionUserId, String url,String coursename,
			String description);
	 
	public EDU_LMS_FREE_COURSE getolinecourseByid(int id);
	 
	public String getFilePathQueryForDocFile(int id);
	
	public ArrayList<ArrayList<String>> DataTablefc_urlDataList2(String coursename, String url, String description,String role);
	 
}
