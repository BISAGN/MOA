package com.AyushEdu.dao.Degree_recognition_mstr;

import java.util.List;
import java.util.Map;

public interface Degree_reco_university_code_mstr_Dao {

	List<Map<String, Object>> DataTableuniversity_codeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state, String name_of_university, String university_id, String status);

	long DataTableuniversity_codeDataTotalCount(String search, String state, String name_of_university,
			String university_id, String status);
	
}
