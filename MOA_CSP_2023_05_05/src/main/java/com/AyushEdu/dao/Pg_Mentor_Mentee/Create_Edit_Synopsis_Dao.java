package com.AyushEdu.dao.Pg_Mentor_Mentee;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Pg_Mentor_Mentee.PG_CREATE_EDIT_SYNOPSIS;

public interface Create_Edit_Synopsis_Dao {
	
	public String updateSynopsis(PG_CREATE_EDIT_SYNOPSIS obj);
	
	public long DataTableSynopsisDataTotalCount(String Search, String title,String userid);
	
	public List<Map<String,Object>> DataTableSynopsisDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String title ,String userid);

}
