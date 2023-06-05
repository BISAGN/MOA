package com.AyushEdu.dao.Regulation.Intern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;


public interface FormaaDao {

 	 
	 

	public List<Map<String, Object>> DataTableFormaaDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String reg_state, String to_state,String  address ,String from_date,String to_date) ;
 
	public long DataTableFormaaDataTotalCount(String Search, HttpSession session,  String reg_state, String to_state,String  address ,String from_date,String to_date) ;






}
