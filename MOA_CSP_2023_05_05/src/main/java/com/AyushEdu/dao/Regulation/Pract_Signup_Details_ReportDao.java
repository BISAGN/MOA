package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Pract_Signup_Details_ReportDao {
	
//	public List<Map<String, Object>> DataTablePract_Signup_Details_ReportDataList(int startPage, int pageLength,
//			String Search, String orderColunm, String orderType, String name,String dob, String aadhar_card ,String email,
//			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
//			 ,String state_reg_num,String status)  ;
//	
//	public long DataTablePract_Signup_Details_ReportTotalCount(String Search, String orderColunm,
//			String orderType, String name,String dob, String aadhar_card ,String email,
//			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
//			 ,String state_reg_num,String status);
	
	public List<Map<String, Object>> DataTablePract_Signup_Details_ReportDataList(int startPage, int pageLength,
			String orderColunm, String orderType,String Search, String name,String dob, String aadhar_card ,String email,
			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String status)  ;
	
	public long DataTablePract_Signup_Details_ReportTotalCount(String Search, String orderColunm,
			String orderType, String name,String dob, String aadhar_card ,String email,
			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String status);

	public String Approve_Pract_SignUp_ReportData(String a,String username,String userId,int id); 
	
	public String Reject_Pract_SignUp_ReportData(String a,String username,String userId,int id);
	
	public ArrayList<ArrayList<String>> getPract_Signup_Details_Report_Excel(String Search,String name,String dob, String aadhar_card ,String email,
			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String institute_status);
//	--18-04-2023
	public ArrayList<ArrayList<String>> get_Parctname_reports_by_Reject_idata(String id);
	public String Approve_Pract_SignUp_ReportData33(String a,String status,String username,String reject_remarks);


}
