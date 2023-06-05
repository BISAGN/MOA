package com.AyushEdu.dao.Question_Bank;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Upload_Question_DAO {
	
	public List<Map<String, Object>> DataTableQuestion_BankDataList(HttpSession sessionUserId,int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id,String role);
	

	public long DataTableQuestion_BankDataTotalCount(String Search,String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id,String role);

}
