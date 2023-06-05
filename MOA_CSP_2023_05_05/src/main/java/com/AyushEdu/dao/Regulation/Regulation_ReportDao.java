package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Regulation_ReportDao {

	
	List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String status,String per_state,String from_date,String to_date ,String gender ,
			String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException;

	long DataTableEdu_Reg_Report_masterDataTotalCount(String Search, String nrh_en_no, String first_name, String status,String per_state,String from_date,String to_date ,
			String gender ,String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException;

	String getImagePath1(String id);
	
	public String getFilePathQueryForDocFile(String id,String fildname);

	
	
	//start new pdf
//	04-03-2023
	public ArrayList<ArrayList<String>> DataTableEdu_Reg_Report_masterDataList_pdf(String Search,String nrh_en_no, String first_name, 
			String status,String per_state,String from_date,String to_date  ,String gender ,
			String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException;
	//end
	 
	//start
//	public String getFilePathQueryForDocFile(String id,String fildname);
	//end

	
	public ArrayList<ArrayList<String>>  getRegulation_Report_Excel( String Search,String nrh_en_no, String first_name, 
			String status,String per_state,String from_date,String to_date  ,String gender ,
			String state_reg_no ,String dob ,String institute_name ,String type_status)throws ParseException;
}
