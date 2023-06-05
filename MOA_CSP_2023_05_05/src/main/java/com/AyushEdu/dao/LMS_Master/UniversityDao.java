package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;

public interface UniversityDao {
	
	public List<Map<String, Object>> DataTableUniversDataList(int startPage, int pageLength,String Search, String orderColunm, String orderType, 
			String university_name,String university_code, String country_id,String state_id,String district_id,String city_name, String status,  String university_type, String organization_id, String system_id);
	public long DataTableUniversDataTotalCount(String Search, String university_name,String university_code,String country_id,String state_id,
			String district_id,String city_name, String status,  String university_type, String organization_id, String system_id);
	
	public EDU_LMS_UNIVERSITY_MSTR getUniversByid(int id);

}
