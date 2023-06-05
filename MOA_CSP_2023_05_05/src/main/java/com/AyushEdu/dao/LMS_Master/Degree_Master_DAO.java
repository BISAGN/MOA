package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;



public interface Degree_Master_DAO {

	public List<Map<String, Object>> DataTableDegree_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String type_of_degree, String degree_name, String semester,String degree_code, String status);
	

	public long DataTableDegree_masterDataTotalCount(String Search,String type_of_degree,String degree_name,String semester,String degree_code, String status);
	
	public EDU_LMS_DEGREE_MASTER get_degreeByid(int id);
	
}
