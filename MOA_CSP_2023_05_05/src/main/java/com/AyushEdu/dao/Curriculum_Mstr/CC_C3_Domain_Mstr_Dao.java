package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_C3_DOMAIN_MSTR;

public interface CC_C3_Domain_Mstr_Dao {

	public List<Map<String, Object>> DataTableC3_DomainDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String domain, String status, String system_id,String role);

	public long DataTableC3_DomainDataTotalCount(String search, String domain,String status, String system_id,String role);

	public String updateC3_Domain(CC_TB_C3_DOMAIN_MSTR td);
}
