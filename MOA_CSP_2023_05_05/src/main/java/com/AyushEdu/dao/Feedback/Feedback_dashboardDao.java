package com.AyushEdu.dao.Feedback;

import java.util.List;
import java.util.Map;

public interface Feedback_dashboardDao {
	
	public List<Map<String, Object>> FeedbackDashboardchartDataListByInst(int inst_id) ;
	public List<Map<String, Object>> FeedbackDashboardchartDataListByUni(int Uni_id,String inst_id) ;
	public List<Map<String, Object>> FeedbackDashboardchartDataListBySystem(String sys_id) ;
	public List<Map<String, Object>> FeedbackDashboardchartDataListByInstSubCat(int inst_id) ;
	public List<Map<String, Object>> FeedbackDashboardchartDataListByUniSubCat(int Uni_id,String inst_id) ;
	public List<Map<String, Object>> FeedbackDashboardchartDataListBySystemSubCat(String sys_id) ;
	public String getStudentDetailsByUserId(String user_id);
	public void genrateScheduleRandomStudentsForFeedBack();
}
