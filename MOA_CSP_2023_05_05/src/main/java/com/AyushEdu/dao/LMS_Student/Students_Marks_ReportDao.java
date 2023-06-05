package com.AyushEdu.dao.LMS_Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Students_Marks_ReportDao {

	public List<Map<String, Object>> DataTableSearch_Stu_MarksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String courses, String set,String userid);
	
	public long DataTableSearch_Stu_MarksDataTotalCount(String Search,String courses, String set,String userid);
	
	
	public ArrayList<ArrayList<String>> getStu_Marks_Replist(String userid);
	public ArrayList<ArrayList<String>> getStu_Marks_Reportlist(String userid,String course_id);
//	 change on 23/11/2022
	public ArrayList<ArrayList<String>> getPopup_Datalist(String userid,String course_id,String set_id,String degree_id);
	
}
