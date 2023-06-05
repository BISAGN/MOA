package com.AyushEdu.dao.Curriculum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public interface Nch_Practical_Learning_OutcomeDao {
//	public ArrayList<ArrayList<String>> getPopup_TeachingChildDatalist(String course_id);

//	public ArrayList<ArrayList<String>> GetTeaching_Learning_Method_ParentData(int id);

//	public List<ArrayList<String>> getTeaching_Learning_Method_Child_By_id(int id);
	

	


//	public ArrayList<ArrayList<String>> getPopupPract_learniOutcome_list(String hid);

	public ArrayList<ArrayList<String>> GetPractical_Learning_Outcome_ParentData(int id);

	public List<ArrayList<String>> getPractical_Learning_Outcome_Child_By_id(int id);

	public ArrayList<ArrayList<String>> getPopup_Practical_Learning_OutcomeDatalist1(String hid);

	public List<Map<String, Object>> DataTablePract_learniOutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String practical_id, String status, String role);

	public long DataTablePract_learniOutcomeTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id, String status, String role);
	

}
