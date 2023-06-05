package com.AyushEdu.dao.Dashboard;

import java.util.List;
import java.util.Map;

public interface e_fromAdmissionDashboardDao {
	public List<Map<String, Object>> e_formAdmissionDashboardchartDataListByState(String temp) ;
	public List<Map<String, Object>> e_formAdmissionDashboardchartDataListByState2(String role,String dq,String temp,String p_id,String data,String type) ;
	public List<Map<String, Object>> e_formAdmissionDashboardchartDataListByState3(String role,String obj,String p_id) ;
	public List<Map<String, Object>> e_formgetAdmissionSeatDetails(String obj,String role,String p_id);
	public List<Map<String, Object>>  e_formAdmissionDashboardTableList(String role,String dq,String data,String type,String p_id);
	public List<Map<String, Object>> e_formcallDefaultSelectDashboard(String obj,String role,String user_id,String roleStaff_lvl);
	public List<Map<String, Object>> e_formgetDashboardProfileByrole(String userid,String role);
	public String getNonSelectedList(String role,String userid);
	public List<Map<String, Object>> getAllAutoList(String data,String dq);

}
