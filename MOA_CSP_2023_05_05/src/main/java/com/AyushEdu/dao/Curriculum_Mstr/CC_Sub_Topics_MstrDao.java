package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;

public interface CC_Sub_Topics_MstrDao {

	public List<Map<String, Object>> DataTableSub_TopicMstrDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_id,String topic_id, String sub_topic, String status,String system_id,String degree_id,String professional_id,String role);

	public long DataTableSub_TopicMstrDataTotalCount(String Search,String course_id,String topic_id, String sub_topic,String status,String system_id ,String degree_id,String professional_id ,String role);

	public String updateSub_topic(CC_TB_SUB_TOPICS_MSTR td);
}
