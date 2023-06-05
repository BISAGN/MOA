package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;



public interface Student_FreeCourseRepot_DAO {

	ArrayList<ArrayList<String>> getFilterstu_dtl_data(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String coursename, String start_date, String end_date,String username,  HttpSession sessionUserId);

	public long getTotalstu_dtl_dataCount(String search, String start_date, String end_date, String username,
			String coursename,HttpSession sessionUserId);
	
	public EDU_LMS_FREE_COURSE getolinecourseByid(int id);
	
	public String getFilePathQueryForDocFile(int id);

}
