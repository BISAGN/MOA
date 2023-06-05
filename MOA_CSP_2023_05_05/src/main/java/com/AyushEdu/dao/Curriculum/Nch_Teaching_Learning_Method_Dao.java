package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Nch_Teaching_Learning_Method_Dao {
	
	public List<Map<String, Object>> DataTableTeaching_Learning_MethodDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id,String role);

	public long DataTableTeaching_Learning_MethodDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id,String role);

	public ArrayList<ArrayList<String>> getPopup_TeachingChildDatalist(String course_id);

	public ArrayList<ArrayList<String>> GetTeaching_Learning_Method_ParentData(int id);

	public List<ArrayList<String>> getTeaching_Learning_Method_Child_By_id(int id);
	
	public ArrayList<ArrayList<String>> getPopup_TeachingChildDatalist1(String hid);
	

}
