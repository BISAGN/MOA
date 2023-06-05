package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;

public interface Student_Course_Dao {
	
	
	public List<Map<String, Object>> DataTablestudent_regDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String username, String system_name,String setname,String course_name,String app_status);

	public long DataTablestudent_regDataTotalCount(String Search,String username, String system_name,String setname,String course_name,String app_status);
	
	 public ArrayList<ArrayList<String>> studentSelectElective(String user_id,String system,String Course,HttpSession session);
	 
	 public String getMaxAID();

}
