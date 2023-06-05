package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_H3_ASSESSMENT_TYPE_MSTR;

public interface CC_H3_Assessment_Type_Mstr_Dao {

	public List<Map<String, Object>> DataTableH3_Assessment_TypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String assessment_type, String status,String role);

	public long DataTableH3_Assessment_TypeDataTotalCount(String Search,String system_id, String assessment_type, String status,String role);

	public String updateH3_Assessment_Type(CC_TB_H3_ASSESSMENT_TYPE_MSTR td);

}
