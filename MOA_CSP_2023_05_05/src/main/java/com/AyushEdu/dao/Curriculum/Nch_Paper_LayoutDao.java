package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_PAPER_LAYOUT;

public interface Nch_Paper_LayoutDao {

	public List<Map<String, Object>> DataTableNCHPaperLayoutDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String paper_id,  String instructions, String question_type_id,
			String num_questions, String marks_questions, String status, String role);

	public long DataTableNCHPaperLayoutDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id,  String instructions,
			String question_type_id, String num_questions, String marks_questions, String status, String role);
	
	
	public EDU_CC_TB_PAPER_LAYOUT getNCHPaperLayoutByid(int id);

	public String updateNCHPaperLayout(EDU_CC_TB_PAPER_LAYOUT td);
}
