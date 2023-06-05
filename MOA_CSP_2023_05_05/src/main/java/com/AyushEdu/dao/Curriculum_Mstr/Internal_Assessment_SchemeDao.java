package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_INTERNAL_ASSESSMENT_SCHEME_MSTR;

public interface Internal_Assessment_SchemeDao {
	
	public List<Map<String, Object>> DataTableinternal_assessment_schemeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String scheme, String status);
	public long DataTableinternal_assessment_schemeDataTotalCount(String Search,String scheme);
	public String updateinternal_assessment_schememstr(CC_TB_INTERNAL_ASSESSMENT_SCHEME_MSTR obj);

}
