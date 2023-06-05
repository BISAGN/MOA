package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Nch_Search_Theory_learning_obj_Dao {
	

	public List<Map<String, Object>> DataTableNCHSearchLearningObjectDataList(int startPage, int pageLength,
			String search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id,String learn_outme_id,String role);

	public long DataTableNCHSearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id,String learn_outme_id, String role);

	public ArrayList<ArrayList<String>> getPopup_LearningDatalist(String id);

	public ArrayList<ArrayList<String>> GetLearning_Parent_Data(int id);

	public List<ArrayList<String>> getLearning_Child_By_id(int id);
	
	public List<ArrayList<String>> NCH_theoryLearning_Objectives(String course_id);

}
