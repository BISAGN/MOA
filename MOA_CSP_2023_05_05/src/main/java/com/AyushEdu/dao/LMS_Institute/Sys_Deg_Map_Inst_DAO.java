package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYS_DEG_MAP_INST;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;



public interface Sys_Deg_Map_Inst_DAO {

	public	List<Map<String, Object>> DataTablesystem_degree_mapping_instDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String institute_id, String degree_id, String status);

	public long DataTablesystem_degree_mapping_instDataTotalCount(String search,String institute_id,String degree_id,
			String status);

	public EDU_LMS_SYS_DEG_MAP_INST get_system_degree_mapping_instByid(int parseInt);

	public List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegrebysysidlistdata(String selval);
	
	public ArrayList<ArrayList<String>> Getsytemid_fetch(String userid);
	
	

}
