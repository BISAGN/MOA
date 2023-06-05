package com.AyushEdu.dao.LMS_Master;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Link_Coures_setDao {
	
	public ArrayList<ArrayList<String>> DataTable_DataList(int startPage, int pageLength, String search,String system_id, String degree_id,
			String orderColunm, String orderType, HttpSession sessionUserId);

	public long DataTable_DataTotalCount(String search, HttpSession sessionUserId,String system_id, String degree_id);

}
