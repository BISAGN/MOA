package com.AyushEdu.dao.Regulation.Intern;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Search_FormAADAO {
	
	public List<Map<String, Object>> DataTableSeacrh_formaaDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status,String gender,
			String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,
			String place_of_working ,String dob,String date_of_reg,String institute_name,String type_status);
	
	public long DataTableSeacrh_formaaDataTotalCount(String Search, String nrh_en_no,String first_name,String institute_status,String gender ,
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status);
	
	  
	public String approve_formaaData(String a,String upto,String status,String username,String state_reg_no,String per_state ,String from_date , String to_date ,String pid)throws ParseException ;
 
	public String reject_formaaData(String a,String status,String username,String reject_remarks ,String pid);

 	public String getMaxAID();
 
	
}
