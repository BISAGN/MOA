package com.AyushEdu.dao.LMS_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;


public interface CountryDao {

//	public ArrayList<ArrayList<String>> search_Country_name(String name,String status);
	
	 public TB_COUNTRY getCountryByid(int id);
	 
	 public ArrayList<ArrayList<String>> search_Country_report();
	 
	 public List<Map<String, Object>> DataTableCountryDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String country,String status) ;
	 
	 public long DataTableCountryDataTotalCount(String Search, HttpSession session,String country);
	 
	 
	 
	 
}
