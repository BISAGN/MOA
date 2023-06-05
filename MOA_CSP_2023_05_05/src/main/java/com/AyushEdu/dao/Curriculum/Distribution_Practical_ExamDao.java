package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;

public interface Distribution_Practical_ExamDao {

	public List<Map<String, Object>> DataTableDistribution_practical_examDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status, String role);

	public long DataTableDistribution_practical_examDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status, String role);

//	public EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM getDistribution_practical_examByid(int id);

	public ArrayList<ArrayList<String>> getdegreelistbysystem(String system_id);

	public String updateDistribution_practical_exam(EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM obj);

}
