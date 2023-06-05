package com.AyushEdu.dao.Degree_recognition_mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_QUALIFICTATION_CODE_mstr;

public interface Degree_reco_qualification_code_mstr_Dao {

	List<Map<String, Object>> DataTableQualificationDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String qualification_name, String codes, String status);

	long DataTableQualificationDataTotalCount(String search, String qualification_name, String codes);


}
