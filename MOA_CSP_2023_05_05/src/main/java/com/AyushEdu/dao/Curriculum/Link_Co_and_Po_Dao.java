package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Link_Co_and_Po_Dao {

	public String updatega_po(String po, String id, String co, String degree_id, String system_id);

	public long DataTableCo_Po_DataTotalCount(String Search, String system_id, String degree_id, String professional_id,
			String course_id, String course_outcome_id, String programoutcome_name, String role);

	public List<Map<String, Object>> DataTableCo_PoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String course_outcome_id, String programoutcome_name, String role);

}
