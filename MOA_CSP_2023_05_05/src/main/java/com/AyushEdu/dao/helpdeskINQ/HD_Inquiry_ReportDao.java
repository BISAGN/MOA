package com.AyushEdu.dao.helpdeskINQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface HD_Inquiry_ReportDao {

	public List<Map<String,Object>> getFilterInq_Report_datalist(int startPage, int pageLength, String Search,String des,
			String orderColunm, String orderType, String inq_no, String inq_cat,String  per_state,String status, HttpSession session);
	
	public long getTotalInq_Report_dataCount1(String search, String inq_no, String inq_cat,String per_state, String des,String status, HttpSession sessionUserId);
	
	public String getFilePathhd_Query(int id);
	public List<Map<String,Object>> getInq_Reportchildeditstatus( String p_id, String note, String status);
	
//	public ArrayList<ArrayList<String>> getInq_RepDetailsDao(String hid, String note, int status);
	
}
