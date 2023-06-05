package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface CC_Nch_Topic_Learning_Outcome_Dao {
	
	public List<Map<String, Object>> DataTableLearning_OutcomeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id, String topic_id, String status,String role);

	public long DataTableLearning_OutcomeTotalCount(String search, String system_id, String degree_id,String professional_id,String course_id, String topic_id,String status,String role);
	
	public ArrayList<ArrayList<String>> GetTopic_Learning_Outcome_ParentData(int id);
	
	public List<ArrayList<String>> getTopic_Learning_Outcome_Child_By_id(int id) ;
	
	public ArrayList<ArrayList<String>> getPopup_Topic_Learning_OutcomeDatalist1(String hid);
}
