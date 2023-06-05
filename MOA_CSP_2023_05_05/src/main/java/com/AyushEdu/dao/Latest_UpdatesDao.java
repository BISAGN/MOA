package com.AyushEdu.dao;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.TB_LATESTUPDATES;

public interface Latest_UpdatesDao {

	public List<Map<String, Object>> DataTableLatest_UpdatesDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String updates, String from_date,String url, String to_date, String ayu_port,
			String nch_port, String ncism_port, String status) ;

	public long DataTableLatest_UpdatesDataTotalCount(String search, String updates, String from_date, String to_date,String url,
			String ayu_port, String nch_port, String ncism_port, String status);

	public TB_LATESTUPDATES getsystemByid(int id);
	

}
