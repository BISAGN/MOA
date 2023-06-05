package com.AyushEdu.dao.LMS_Credit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CreditDao {
	
	public ArrayList<ArrayList<String>> GetTotalDuration(String course );
	
	public ArrayList<ArrayList<String>> GetSystemCourse_fetch(String system_id1,String course_id1);
	
	public List<Map<String, Object>> DataTableCreditDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course, String max_duration,String no_of_days,String point);
	
	public long DataTableCreditDataTotalCount(String Search,String course, String max_duration,String no_of_days,String point);

}
