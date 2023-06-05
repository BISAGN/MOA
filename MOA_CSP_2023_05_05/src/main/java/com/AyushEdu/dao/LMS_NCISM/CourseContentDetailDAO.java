package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;


public interface CourseContentDetailDAO {

	public TB_COURSE_CONTENT_MSTR getCourseContentDetailByid(int id);

	public List<Map<String, Object>> DataTableCourseContentDetailDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name,String degree,String course_name,String setname, String module_name,
			String start_date, String end_date, String type_of_content,String role);

	public long DataTableCourseContentDetailDataTotalCount(String search, String system_name,String degree,String course_name,String setname, String module_name,
			String start_date, String end_date, String type_of_content,String role);
	 
public ArrayList<ArrayList<String>> getsetlistByCourse(String id);

//public ArrayList<ArrayList<String>> getModuleListBySet(String id);

public ArrayList<ArrayList<String>> getModulelistFromset(String id,String id1);

public ArrayList<ArrayList<String>> getDegreelistFromsystem(String id);

public ArrayList<ArrayList<String>> getSetlistFromDegree(String id,String id1);

public ArrayList<ArrayList<String>> getCourselistFromSet(String id,String id1,String id2);

public ArrayList<ArrayList<String>> getCourselistFromSet(String id);
}
