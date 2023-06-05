package com.AyushEdu.dao.Contact_details;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Contact_Dao {

	public List<Map<String, Object>> DataTableSearch_contactusList(int startPage, int pageLength, String Search,String orderColunm,
			String orderType,  String name, String email, String subject, String message,String date);

	public long DataTableSearch_contactus_count(String Search, String name, String email, String subject, String message,String date);
	
	
		

}
