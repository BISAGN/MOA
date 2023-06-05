package com.AyushEdu.dao.Alumni;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Alumni_Exp_ExcelDAO {

	List<Map<String, Object>> DataTableEdu_Alumni_Exp_excel(int startPage, int pageLength, String search,
			String orderColunm, String orderType) throws ParseException;;

	long DataTableEdu_Alumni_Exp_Excel_Count(String search) throws ParseException;
	
	public List<Map<String, Object>> DataTableAlumniApprove_Request(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String InstId,String status,String name);
	
	public long DataTableAlumniApprove_Request_Count(String Search,String InstId,String status,String name);

}
