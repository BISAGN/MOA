package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface T3_Search_Learning_Object_Dao {

	public List<Map<String, Object>> DataTableT3SearchLearningObjectDataList(int startPage, int pageLength,
			String search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id,String role);

	public long DataTableT3SearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id,String role);

	public ArrayList<ArrayList<String>> getPopup_Child_LearningDatalist(String id);

	public ArrayList<ArrayList<String>> GetLearning_Parent_Data(int id);

	public List<ArrayList<String>> getLearning_Child_By_id(int id);

}
