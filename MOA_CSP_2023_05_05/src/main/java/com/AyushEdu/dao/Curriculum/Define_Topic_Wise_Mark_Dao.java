package com.AyushEdu.dao.Curriculum;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Define_Topic_Wise_Mark_Dao {
	
	
	public List<Map<String, Object>> DataTabledefine_topic_wise_marksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name,String role);

	public long DataTabledefine_topic_wise_marksDataTotalCount(String Search,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name,String role);
	
	public String updateTopicwiseMarksChild(String id,String topic_id);

}
