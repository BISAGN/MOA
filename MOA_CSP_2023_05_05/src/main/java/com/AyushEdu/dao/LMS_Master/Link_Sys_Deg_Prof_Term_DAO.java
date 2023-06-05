package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LINK_SYS_DEG_PROF_TERM;

public interface Link_Sys_Deg_Prof_Term_DAO {
	
	public List<Map<String, Object>> DataTablelink_sys_deg_prof_termDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String system_id,String degree_id,String prof,String term, String status);

	public long DataTablelink_sys_deg_prof_termDataTotalCount(String search,String system_id,String degree_id,String prof,String term, String status);
	
	public String updateSysDegProfTerm(EDU_LINK_SYS_DEG_PROF_TERM obj);

}
