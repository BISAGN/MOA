package com.AyushEdu.dao.Registration.Postgraduate;


import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;


public interface Pg_Gradu_ExamName_Dao {
	
	public EDU_PG_GRADU_EXAMNAME getExamtypetByid(int id);

	public String updateExamtype(EDU_PG_GRADU_EXAMNAME td);

	public List<Map<String, Object>> DataTableExamtypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name_of_the_exam, String system_id, String degree_id,
			String status);

	public long DataTableExamtypeDataTotalCount1(String Search, String name_of_the_exam, String system_id,
			String degree_id, String status);

}
