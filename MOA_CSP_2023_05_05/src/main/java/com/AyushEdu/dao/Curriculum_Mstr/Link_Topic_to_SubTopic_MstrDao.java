package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR;

public interface Link_Topic_to_SubTopic_MstrDao {
	
	public List<Map<String, Object>> DataTableTopic_SubTopicMStrDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_id,String topic_id, String subtopic_id, String status,String system_id ,String degree_id,
			String professional_id,String role);

	public long DataTableTopic_SubTopicMStrDataTotalCount(String Search,String course_id,String topic_id, String subtopic_id,String status,String system_id ,String degree_id,
			String professional_id,String role);
	
	public String  updateTopic_Subtopic(String st,String id,String topic_id);

}
