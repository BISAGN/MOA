package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface University_Institute_Stud_ReportDAO {
	
	public ArrayList<ArrayList<String>> getInstituteListFromUniversity(String userId);
	public ArrayList<ArrayList<String>> getDegreeListFromInstitute(String institute_id,String userId,String role);
	public ArrayList<ArrayList<String>> getCoursesListFromDegree(String degree_id,String userId);
	public ArrayList<ArrayList<String>> getModuleListFromCourses(String course_id,String userId);
//	public ArrayList<ArrayList<String>> getDegreeListFromModule(String module_id,String userId);


	public List<Map<String, Object>> DataTableUniversityInstituteStudReportDetailsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String institute_id,String degree_id, String course_id, String module_id,String role);

	public long DataTableUniversityInstituteStudReportDetailsDataTotalCount(String search,String institute_id,String degree_id, String course_id, String module_id,String role);
//	change on 23/11/2022
	public ArrayList<ArrayList<String>> getPopup_Data(String userId,String degree_id,String term_id);
}
