package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Nch_T4_Learning_Object_Dao {

	public List<Map<String, Object>> DataTableNchT4SearchLearningObjectDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String practical_id,String role);

	public long DataTableNchT4SearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id,String role);

	public ArrayList<ArrayList<String>> getPopup_NchChild_LearningDatalist(String course_id);

	public ArrayList<ArrayList<String>> getPop_Up_view_child_data(String hid);
	
	public ArrayList<ArrayList<String>> GetNchLearning_Parent_Data(int id);

	public List<ArrayList<String>> getNchLearning_Child_By_id(int id);
	
	
	
	
}
