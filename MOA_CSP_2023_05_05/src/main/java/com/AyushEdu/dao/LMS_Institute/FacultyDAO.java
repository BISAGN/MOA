package com.AyushEdu.dao.LMS_Institute;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_FACULTY_MSTR;

public interface FacultyDAO {

	public List<Map<String, Object>> search_Faculty_name(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system,  String course , String faculty,String ayush_id , String status);
	public long DataTablefacultyDataTotalCount(HttpSession sessionUserId, String Search, String system, String course,
			String faculty, String ayush_id);
	public EDU_LMS_FACULTY_MSTR getFacultyByid(int id);
}
