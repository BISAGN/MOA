package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Add_Non_Lecture_Activities_Dao {

	public List<Map<String, Object>> DataTableAdd_Non_Lecture_ActivitiesDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id,String role);

	public long DataTableAdd_Non_Lecture_ActivitiesDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id,String role);

	public ArrayList<ArrayList<String>> getPopup_ChildDatalist(String hid);

	public ArrayList<ArrayList<String>> GetAdd_Non_Lecture_Activities_Parent_Data(int id);

	public List<ArrayList<String>> getAdd_Non_Lecture_Activities_Child_By_id(int id);

}
