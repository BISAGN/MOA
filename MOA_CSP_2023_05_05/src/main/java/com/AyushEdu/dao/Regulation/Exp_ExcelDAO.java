package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Exp_ExcelDAO {

	List<Map<String, Object>> DataTableEdu_Exp_excel(int startPage, int pageLength, String search, String orderColunm,
			String orderType,String role_type,String upload_date ) throws ParseException;

	long DataTableEdu_Exp_Excel_Count(String search,String role_type,String upload_date ) throws ParseException;

}
