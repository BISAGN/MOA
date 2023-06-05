package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Hospital_Staff_List_Report_DAO {

	public List<Map<String, Object>> DataTableSearch_Hospital_Staff_ListDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name, String post, String department, String role,
			String userid, String institute_id);

	public long DataTableSearch_Hospital_Staff_ListDataTotalCount(String search, String name, String post, String department,
			String role, String userid, String institute_id);
	
	public List<Map<String, Object>> View_Hospital_Medical_Staff_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_paramedical_staff_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_auxillary_medical_staff_Details(int id, int institute_id, String role, String userid);

}
