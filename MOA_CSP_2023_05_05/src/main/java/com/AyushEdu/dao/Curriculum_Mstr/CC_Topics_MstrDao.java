package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TOPICS_MSTR;

public interface CC_Topics_MstrDao {
	
	public List<Map<String, Object>> DataTableTopicMstrDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String course_id, String topic, String status,String system_id ,String degree_id,
			String professional_id,String role);

	public long DataTableTopicMstrDataTotalCount(String Search,String course_id, String topic,String status,String system_id ,String degree_id,
			String professional_id,String role);

	public String updatetopic(CC_TB_TOPICS_MSTR td);
	
}
