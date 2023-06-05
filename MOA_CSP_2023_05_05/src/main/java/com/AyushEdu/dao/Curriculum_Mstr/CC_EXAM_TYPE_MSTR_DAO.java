package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;

public interface CC_EXAM_TYPE_MSTR_DAO {
	
	public List<Map<String, Object>> DataTableExamtypeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String exam_type, String status);

	public long DataTableExamtypeDataTotalCount1(String search, String exam_type,String status);
	
	public CC_TB_EXAM_TYPE_MSTR getExamtypeByid(int id);

	public String updateExamtype(CC_TB_EXAM_TYPE_MSTR td);

}
