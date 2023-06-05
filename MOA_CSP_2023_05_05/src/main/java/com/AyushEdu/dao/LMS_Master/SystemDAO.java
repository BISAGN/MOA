package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;



public interface SystemDAO {
	
	public EDU_LMS_SYSTEM_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTablesystemDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name,String system_abbr,String status);

	public long DataTablesystemDataTotalCount(String search, String system_name,String system_abbr);
	
}
