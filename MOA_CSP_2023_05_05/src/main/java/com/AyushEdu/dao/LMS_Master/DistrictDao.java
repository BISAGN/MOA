package com.AyushEdu.dao.LMS_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;





public interface DistrictDao {

	public List<Map<String, Object>> search_District_name(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String country,  String state ,  String district , String status);
	 public EDU_LMS_DISTRICT_MSTR getDistrictByid(int id) ;
	// public ArrayList<ArrayList<String>> search_DistrictReport(); 
	// public long DataTablesystemDataTotalCount(String Search, String country, String state, String district);
	 public long DataTabledistrictDataTotalCount(HttpSession sessionUserId, String Search, String country,  String state ,  String district);

	 public ArrayList<ArrayList<String>> getCountry_List(String country_id);
}
