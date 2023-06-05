package com.AyushEdu.dao.helpdeskINQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface HD_DB_HelpDesK_ReportDao {

	public List<Map<String,Object>> getFilterDB_Helpdesk_report_datalist(int startPage, int pageLength, String Search,String des,
			String orderColunm, String orderType, String inq_no, String inq_cat,String  per_state,String module,String sub_module,String screen_name,String status, HttpSession session);
	
	public long getTotalHelpdesk_report_dataCount1(String search, String inq_no, String inq_cat,String per_state, String des,String module,String sub_module,String screen_name,String status, HttpSession sessionUserId);
	
	public List<Map<String,Object>> getInq_HD_HelpDesk_Reportchildeditstatus( String p_id, String note, String status);
	
public ArrayList<ArrayList<String>> getsubmodule(String module);
	public ArrayList<ArrayList<String>> getscreenname( String module,String sub_module);
}
