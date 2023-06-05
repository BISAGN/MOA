package com.AyushEdu.dao.Library_mgmt;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_BOOK_REQ;

public interface Return_BookDao {
	public TB_MEMBER_BOOK_REQ getsystemByid(int id);
	
	public List<Map<String,Object>> DataTableReturn_BookDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String member_id , String book_select, String book_charges,String book_req_date, String return_status) ;

	public long DataTableReturn_BookDataTotalCount(String Search, String member_id, String book_select,String book_charges, String book_req_date,String return_status);
}
