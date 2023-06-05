package com.AyushEdu.dao.LMS_Master;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.TB_STATE;



public interface StateDao {

	 
	 
	 public TB_STATE getstateByid(int id);
		public List<Map<String, Object>> State_nameDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String state_name,String status,String country_id,String state_abbr);
		public long DataTotalCount(String search, String state_name,String country_id,String state_abbr);
		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String state_name,String country_id,String state_abbr);
	
}
