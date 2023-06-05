package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface NCH_List_Of_TopicsDAO {

	public List<Map<String, Object>> DataTableNCHTopicDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String paper_id, String topic_id, String role);

	public long DataTableNCHTopicDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id, String topic_id, String role);

	public ArrayList<ArrayList<String>> GetTopic_Parent_Data1(int id);

	public ArrayList<ArrayList<String>> getTopic_Child_By_id2(int id);

	public ArrayList<ArrayList<String>> getNCHPopup_ChildDatalist(String id);

	public List<ArrayList<String>> Nch_View_list(String course_id);

}
