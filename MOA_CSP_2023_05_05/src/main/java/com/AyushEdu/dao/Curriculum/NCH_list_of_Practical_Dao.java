package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface NCH_list_of_Practical_Dao {
	public List<Map<String, Object>> DataTableNCH_PracticalDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id,String practical_id, String role);

	public long DataTableNCH_PracticalDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id,String practical_id, String role);

	public ArrayList<ArrayList<String>> getPopupNch_Practical_ChildDatalist(String hid);

	public ArrayList<ArrayList<String>> GetPractical_Parent_Data(int id);

	public ArrayList<ArrayList<String>> getPractical_Child_By_id(int id);

	public List<ArrayList<String>> TableNCH_List_of_practicalDataTotalCount(String course_id);

	public List<ArrayList<String>> Nch_practhours(String course_id);
}
