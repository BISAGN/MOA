package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Staff_Info_Report_Dao {

	public List<Map<String, Object>> DataTableSearch_College_Staff_InfoDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String ug_pg_status, String department_id, String role, String userid,
			String institute_id,String ug_status,String pg_status);

	public long DataTableSearch_College_Staff_InfoDataTotalCount(String search, String ug_pg_status,
			String department_id, String role, String userid, String institute_id,String ug_status,String pg_status);
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ug();
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_pg();
	
	
	public List<Map<String, Object>> View_Teaching_Staff(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Teaching_Staff_Pg(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Non_Teaching_Staff_Info(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Last_Acadmic_Year_Staff(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Staff_Salary_info(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Clg_Techer_Prom(int id, int institute_id, String role, String userid);

	public List<Map<String, Object>> View_Clg_Staff_Document(int id, int institute_id, String role, String userid);

}
