package com.AyushEdu.dao.Question_Bank;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Paper_GenerationDao {

	public List<Map<String, Object>> DataTablePaper_GenDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,HttpSession session, String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks,String role);
	
	public long DataTablePaper_GenDataTotalCount(String Search,String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks,String role);
	
}
