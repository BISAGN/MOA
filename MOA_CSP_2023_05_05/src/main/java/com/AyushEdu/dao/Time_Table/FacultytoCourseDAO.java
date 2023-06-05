package com.AyushEdu.dao.Time_Table;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Time_Table.EDU_TT_FACULTY_TO_COURSE_MASTER;

public interface FacultytoCourseDAO {
	 
	public EDU_TT_FACULTY_TO_COURSE_MASTER getFacultytoCourseByid(BigInteger id);

	public List<Map<String, Object>> DataTableFacultytoCourseDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String degree, String professional, String course, String faculty, int institute_id);

	public long DataTableFacultytoCourseDataTotalCount(String search, String degree, String professional, String course, String faculty, int institute_id);
	
	//For TT
		public ArrayList<ArrayList<String>> getFacultyData(String userId);

	

		public ArrayList<ArrayList<String>> getCourseDetailsDao(String degree_id);
	
		
		public ArrayList<ArrayList<String>> getFacultyDetailsDao(String course_id);

}
