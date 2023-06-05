package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_I3_TERM_MSTR;

public interface CC_I3_Term_Mstr_Dao {

	public List<Map<String, Object>> DataTableI3_TermDataList(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, String term, String status);

	public long DataTableI3_TermDataTotalCount(String Search, String term, String status);

	String updateI3_Term(CC_TB_I3_TERM_MSTR td);

}
