package com.AyushEdu.dao.Examination;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Examination.EXAM_RESULT_STATUS;

public interface student_result_Dao {

	public List<Map<String, Object>> DataTableStudentresultList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String result_status, String status);

	public long DataTableStudentresultTotalCount(String search, String result_status,String status);

	public EXAM_RESULT_STATUS getResult_statustByid(int id);

	public String update_result_status(EXAM_RESULT_STATUS td);

}
