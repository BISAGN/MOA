package com.AyushEdu.dao.Library_mgmt;

import java.util.List;
import java.util.Map;

public interface SmartLibraryDao {
	public List<Map<String, Object>> getBorrowList(String status);
	
	public List<Map<String, Object>> DataTableall_booksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered, int book_dept);

	public long DataTableall_booksDataTotalCount(String Search,String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered,int book_dept);
}