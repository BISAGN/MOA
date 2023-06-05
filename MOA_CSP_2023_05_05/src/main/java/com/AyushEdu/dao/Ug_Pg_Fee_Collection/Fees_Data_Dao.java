package com.AyushEdu.dao.Ug_Pg_Fee_Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public interface Fees_Data_Dao {

	public ArrayList<ArrayList<String>> getSystemListFromInstituteExam(String institute_id1, String userId,
			String role); // TODO Auto-generated method stub
	
	public List<Map<String, Object>> DataTable_CMEAttend_DataList(String userId,String system_id,String degree_name,String prof_id,String inst_id,String role);

	public List<Map<String, Object>> DataTable_CMEAttend_DataList_count(String userId, String system_id, String degree_name,
			String prof_id, String inst_id, String role);

	
}
