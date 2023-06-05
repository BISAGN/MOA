package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_PAPER_LAYOUT;

public interface Paper_Layout_Dao {

	public EDU_CC_TB_PAPER_LAYOUT getPaperLayoutByid(int id);

	public String updatePaperLayout(EDU_CC_TB_PAPER_LAYOUT td);

	public List<Map<String, Object>> DataTablePaperLayoutDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String paper_id, String time, String instructions, String question_type_id,
			String num_questions, String marks_questions, String status, String role);

	public long DataTablePaperLayoutDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id, String time, String instructions,
			String question_type_id, String num_questions, String marks_questions, String status, String role);

}
