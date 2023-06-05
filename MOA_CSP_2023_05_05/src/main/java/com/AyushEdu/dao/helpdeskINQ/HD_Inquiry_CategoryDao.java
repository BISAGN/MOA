package com.AyushEdu.dao.helpdeskINQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.helpdeskINQ.HD_INQUIRY_CATEGORY_MSTR;
import com.AyushEdu.Models.helpdeskINQ.HD_INQ_LINK_ROLE_MSTR;


public interface HD_Inquiry_CategoryDao {

	public List<Map<String, Object>> DataTableInq_CatDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String inq_cat,String type);

	public long DataTableInq_CatDataTotalCount(String search, String inq_cat,String type);

	public HD_INQUIRY_CATEGORY_MSTR getInq_CatByid(int parseInt);

	public String updateInq_Cat(HD_INQUIRY_CATEGORY_MSTR td);
	
	
	
	///////////link and report
	public ArrayList<ArrayList<String>> getuser(String roleId);	
	
	public List<Map<String,Object>> getFilterInq_Cat_datalist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String inq_no, String role, String user,String status);
	
	public long getTotalInq_Cat_dataCount1(String search, String inq_no, String role, String user,String status);
	public HD_INQ_LINK_ROLE_MSTR getInquiry_Link_RoleByid(int id);

	
//	public List<Map<String,Object>> getFilterInq_Report_datalist(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType, String inq_no, String  per_state, String des);
//	
//	public long getTotalInq_Report_dataCount1(String search, String inq_no, String per_state, String des);
	
}
