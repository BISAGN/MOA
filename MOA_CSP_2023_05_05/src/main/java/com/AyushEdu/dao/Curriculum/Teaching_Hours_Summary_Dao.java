package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Teaching_Hours_Summary_Dao {
	
	public List<Map<String, Object>> DataTableTopicDataList(String course_id);

	public ArrayList<ArrayList<String>> getfetchsaveddata_list(String system_id, String degree_id,
			String professional_id, String course_id);

	public ArrayList<ArrayList<String>> getfetchteach_hrssaveddata_list(String p_id);

	public ArrayList<ArrayList<String>> getfetchfetchexam_sumsaveddata_list(String p_id);
	
	public ArrayList<ArrayList<String>> getViewTHSummary(String course_id);

}
