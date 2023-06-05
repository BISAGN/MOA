package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;



public interface Sys_Deg_Map_Mstr_DAO {

	public List<Map<String, Object>> DataTablesystem_degree_mappingDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name, String degree_name, String status);

	public long DataTablesystem_degree_mappingDataTotalCount(String search, String system_name, String degree_name,
			String status);

	
	public EDU_LMS_SYS_DEG_MAP_MASTER get_system_degree_mappingByid(int id); 
}
