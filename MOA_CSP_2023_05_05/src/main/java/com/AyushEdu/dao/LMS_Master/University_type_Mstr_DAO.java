package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_TYPE_MSTR;


public interface University_type_Mstr_DAO {
	public List<Map<String, Object>> DataTableUniversity_typeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_type, String status);

	public long DataTableUniversity_typeDataTotalCount(String search, String university_type);
	
	public EDU_LMS_UNIVERSITY_TYPE_MSTR getUniversity_typeByid(int id);

	public String updateUniversity_type(EDU_LMS_UNIVERSITY_TYPE_MSTR td);

}
