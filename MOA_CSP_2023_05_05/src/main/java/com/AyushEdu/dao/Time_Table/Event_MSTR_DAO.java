package com.AyushEdu.dao.Time_Table;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Time_Table.EDU_TT_EVENT_MSTR;

public interface Event_MSTR_DAO {
	
	public EDU_TT_EVENT_MSTR geteventByid(int id);

	
	public List<Map<String, Object>> DataTableEventDataList(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String event_name, String event_date, int holiday, int institute_id);
	
	public long DataTableEventDataTotalCount(String Search, String event_name, String event_date, int holiday, int institute_id);


}

