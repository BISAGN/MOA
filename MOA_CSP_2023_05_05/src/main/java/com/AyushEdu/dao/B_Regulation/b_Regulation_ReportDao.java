package com.AyushEdu.dao.B_Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface b_Regulation_ReportDao {

	public List<Map<String, Object>> DataTable_b_Edu_Reg_Report_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String status, String per_state,
			String from_date, String to_date) throws ParseException;

	String getImagePath1(String id);

	String getFilePathQueryForDocFile(String id, String fildname);

	public ArrayList<ArrayList<String>> DataTable_b_Edu_Reg_Report_masterDataList_pdf();

	public long DataTable_b_Edu_Reg_Report_masterDataTotalCount(String Search, String nrh_en_no, String first_name,
			String status, String per_state, String from_date, String to_date) throws ParseException;

}
