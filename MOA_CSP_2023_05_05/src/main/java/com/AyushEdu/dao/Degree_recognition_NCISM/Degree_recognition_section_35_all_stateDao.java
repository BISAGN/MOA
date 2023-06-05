package com.AyushEdu.dao.Degree_recognition_NCISM;

import java.util.List;
import java.util.Map;

public interface Degree_recognition_section_35_all_stateDao {
	
	public List<Map<String, Object>> getFilter_section_35_all_state_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_section_35_all_state_Count(String search, int user_id);//int university_id,

	public List<Map<String, Object>> getviewdatatoBysectionallstate35id(String id);

}
