package com.AyushEdu.dao.Curriculum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface List_of_Practical_Dao {
	
	public List<Map<String, Object>> DataTablePracticalDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String role);
	public long DataTablePracticalDataTotalCount(String Search, String system_id, String degree_id,String professional_id,String course_id,String role);
	public ArrayList<ArrayList<String>> getPopup_Practical_ChildDatalist(String hid);
	public ArrayList<ArrayList<String>> GetPractical_Parent_Data(int id);
	public ArrayList<ArrayList<String>> getPractical_Child_By_id(int id);
}
