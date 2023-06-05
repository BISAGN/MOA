package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR;

public interface CC_F3_Teaching_Learning_Method_Mstr_Dao {

	public List<Map<String, Object>> DataTableF3_Teaching_Learning_MethodDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,String system_id, String method, String status,String role);

	public long DataTableF3_Teaching_Learning_MethodDataTotalCount(String search,String system_id, String method, String status,String role);

	public String updateF3_Teaching_Learning_Method(CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR td);

}
