package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface State_wise_Institute_Registration_Report_DAO {

	public List<Map<String, Object>> DataTable_state_wise_institute_regDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String institute_id, String system_id,
			String course_id, int co, int st);

	public long DataTable_state_wise_institute_regDataTotalCount(String Search, String institute_id, String system_id,
			String course_id, int co, int st);

	public ArrayList<ArrayList<String>> getSystem_Institute_course_list(int id, String course_name, String institute_id,
			int co, int st);

}
