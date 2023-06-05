package com.AyushEdu.dao.Curriculum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface List_of_Topic_Dao {

	public List<Map<String, Object>> DataTableTopicDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String paper_id,String role);
	public long DataTableTopicDataTotalCount(String Search, String system_id, String degree_id,String professional_id,String course_id,String paper_id,String role);
	public ArrayList<ArrayList<String>> GetTopic_Parent_Data(int id);
	public ArrayList<ArrayList<String>> getTopic_Child_By_id(int id);
	public ArrayList<ArrayList<String>> getPopup_ChildDatalist(String id);
}
