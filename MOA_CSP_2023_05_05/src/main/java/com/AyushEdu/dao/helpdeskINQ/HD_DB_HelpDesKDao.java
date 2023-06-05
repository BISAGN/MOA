package com.AyushEdu.dao.helpdeskINQ;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface HD_DB_HelpDesKDao {


	public List<Map<String,Object>> getFilterdb_helpdesklist(int startPage, int pageLength, String Search,String des,
			String orderColunm, String orderType, String inq_no, String inq_cat,String  per_state,String module,String sub_module,String screen_name,String status, HttpSession session);
	
	public long getTotaldb_helpdeskCount(String search, String inq_no, String inq_cat,String per_state, String des,String module,String sub_module,String screen_name,String status, HttpSession sessionUserId);
	
}
