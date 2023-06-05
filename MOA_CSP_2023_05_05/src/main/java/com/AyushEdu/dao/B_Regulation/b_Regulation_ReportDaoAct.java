package com.AyushEdu.dao.B_Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface b_Regulation_ReportDaoAct {

	public List<Map<String, Object>> DataTable_b_Edu_Reg_Report_masterDataListAct(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String pre_state,
			String from_date, String to_date, String status) throws ParseException;

	public long DataTable_b_Edu_Reg_Report_masterDataTotalCountAct(String Search, String nrh_en_no, String first_name,
			String pre_state, String from_date, String to_date, String status) throws ParseException;

	String getImagePath1Act(String id);

	String getFilePathQueryForDocFileAct(String id, String fildname);

	ArrayList<ArrayList<String>> DataTable_b_Edu_Reg_Report_masterDataList_pdfAct();

	public String b_active_inact_user(String a, String status);

}
