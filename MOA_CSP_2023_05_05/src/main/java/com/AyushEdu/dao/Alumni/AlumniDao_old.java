package com.AyushEdu.dao.Alumni;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;







public interface AlumniDao_old {

	
	public ArrayList<ArrayList<String>> DataTableAlu_urlDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String url, String description, HttpSession sessionUserId);

	public long DataTableAlu_urlDataTotalCount(String search, HttpSession sessionUserId, String url,
			String description);
	 
	 
	 
	 
}
