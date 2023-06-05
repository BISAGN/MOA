package com.AyushEdu.dao.Curriculum;
import java.util.List;
import java.util.Map;

public interface NCH_Define_Topic_Wise_Mark_Dao {

	public List<Map<String, Object>> DataTableNCH_define_topic_wise_marksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name,String role);

	public long DataTableNCHdefine_topic_wise_marksDataTotalCount(String Search,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name,String role);
	
	public String updateTopicwiseMarksChild(String id,String topic_id);

	
}
