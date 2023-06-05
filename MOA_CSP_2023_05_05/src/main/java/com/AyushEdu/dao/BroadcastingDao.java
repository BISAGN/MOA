package com.AyushEdu.dao;

import java.util.List;
import java.util.Map;

public interface BroadcastingDao {

	public List<Map<String, Object>> DataTableBroadcastingDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String MSG, String receiver) ;

	public long DataTableBroadcastingDataTotalCount(String search, String MSG, String receiver);



}
