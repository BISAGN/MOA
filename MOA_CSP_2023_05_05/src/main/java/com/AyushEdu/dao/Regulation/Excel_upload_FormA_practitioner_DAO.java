package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

public interface Excel_upload_FormA_practitioner_DAO {
	List<Map<String, Object>> DataTableEdu_Exp_excel_forma_practitioner(int startPage, int pageLength, String search, String orderColunm,
			String orderType,String upload_date ) throws ParseException;

	long DataTableEdu_Exp_excel_forma_practitioner_Count(String search,String upload_date ) throws ParseException;

}
