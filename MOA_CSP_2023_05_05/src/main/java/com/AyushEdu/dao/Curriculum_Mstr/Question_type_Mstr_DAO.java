package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;


public interface Question_type_Mstr_DAO {
	
	public EDU_CC_TB_QUESTION_TYPE_MSTR getQuestionByid(int parseInt);
	
	public List<Map<String, Object>> DataTableQuestionDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String question_type, String status);

	public long DataTableQuestionDataTotalCount(String search, String question_type,String status);

	public String updatequestion_type(EDU_CC_TB_QUESTION_TYPE_MSTR td);
	
}
