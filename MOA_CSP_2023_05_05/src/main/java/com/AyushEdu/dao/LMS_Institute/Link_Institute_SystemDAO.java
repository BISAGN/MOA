package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Link_Institute_SystemDAO {
	
	public ArrayList<ArrayList<String>> DataTableLink_Institute_SystemDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,String institute_id);

	public long DataTableLink_Institute_SystemDataTotalCount(String search, HttpSession sessionUserId,String institute_id);


}
