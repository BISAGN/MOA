package com.AyushEdu.dao.TT_Lecture;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;

public interface Instructional_Method_MSTRDAO {

	public long DataTable_Instrucational_Method_DataTotalCount(String search, String instructional_method_name);

	public List<Map<String, Object>> DataTable_Instrucational_Method_DataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String instructional_method_name);

	public EDU_LEC_INSTRUCTION_METHOD_MSTR getinstructionalByid(int parseInt);

	

}
