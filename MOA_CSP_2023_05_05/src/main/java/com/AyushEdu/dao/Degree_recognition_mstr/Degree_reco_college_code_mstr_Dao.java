package com.AyushEdu.dao.Degree_recognition_mstr;

import java.util.List;
import java.util.Map;

public interface Degree_reco_college_code_mstr_Dao {

	List<Map<String, Object>> DataTablecCollegeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name_of_college, String state, String college_id,
			String status);

	long DataTableCollegeDataTotalCount(String search, String name_of_college, String state, String college_id);


}