package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Regulation_ReportDaoAct {

	
	List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataListAct(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String pre_state,String from_date,String to_date, String status,
			String institute_name) throws ParseException;


	long DataTableEdu_Reg_Report_masterDataTotalCountAct(String Search, String nrh_en_no, String first_name,String pre_state,String from_date,String to_date, 
			String status,String institute_name) throws ParseException;

	String getImagePath1Act(String id);
	
	public String getFilePathQueryForDocFileAct(String id,String fildname);

	
	
	//start new pdf
	public ArrayList<ArrayList<String>> DataTableEdu_Reg_Report_masterDataList_pdfAct();
	//end
	public String active_inact_user(String a,String status,String sus_upto,String sus_reason) throws ParseException;
	//start
//	public String getFilePathQueryForDocFile(String id,String fildname);
	//end
	
	public String suspend_NCHPracData(String a,String sus_upto,String status,String username);




}
