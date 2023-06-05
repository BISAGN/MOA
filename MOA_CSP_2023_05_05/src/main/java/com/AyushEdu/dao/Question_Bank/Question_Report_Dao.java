package com.AyushEdu.dao.Question_Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_PARENT;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_CHILD;

public interface Question_Report_Dao {


	
	public EDU_LMS_QUESTION_BAND_PARENT getsystemByid(int id);

	List<Map<String, Object>> DataTableQuestion_ReportDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String course_id, String set_id, String module_id);



	long DataTableQuestion_ReportDataTotalCount(String search, String orderType, String course_id, String set_id,
			String module_id);

	

}
