package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_G3_ASSESSMENT_METHOD_MSTR;

public interface CC_G3_Assessment_Method_Mstr_Dao {

	public long DataTableG3_Assessment_MethodDataTotalCount(String Search,String system_id, String assessment_method, String status,String role);

	public List<Map<String, Object>> DataTableG3_Assessment_MethodDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String assessment_method, String status,String role);

	String updateG3_Assessment_Method(CC_TB_G3_ASSESSMENT_METHOD_MSTR td);

}
