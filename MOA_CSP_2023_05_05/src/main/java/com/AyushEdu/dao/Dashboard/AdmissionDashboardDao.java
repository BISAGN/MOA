package com.AyushEdu.dao.Dashboard;

import java.util.List;
import java.util.Map;

public interface AdmissionDashboardDao {
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState(String temp) ;
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState2(String role,String dq,String temp,String p_id,String data,String type) ;
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState3(String role,String obj,String p_id) ;
	public List<Map<String, Object>> getAdmissionSeatDetails(String obj,String role,String p_id);
	public List<Map<String, Object>>  AdmissionDashboardTableList(String role,String dq,String data,String type,String p_id);
	public List<Map<String, Object>> callDefaultSelectDashboard(String obj,String role,String user_id,String roleStaff_lvl);
	public List<Map<String, Object>> getDashboardProfileByrole(String userid,String role);
	public String getNonSelectedList(String role,String userid);
	//Riddhi
	public List<Map<String, Object>> getAdmissionSeatDetails_Faculty(String role);
}
