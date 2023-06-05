package com.AyushEdu.dao.Ug_Pg_Fee_Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Ug_Pg_Fee_Payment_Report_Dao {


	public List<Map<String, Object>> DataTableSearch_fee_paymentDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name, String status1, String type_of_degree, String degree_name, String term_id,String inst_id, String role);

	public long DataTableSearch_fee_paymentDataTotalCount(String Search, String name, String status1, String type_of_degree, String degree_name, String term_id,String inst_id, String role);
	
//	public List<ArrayList<String>> getug_pg_fee_payment_Report_Excel(String type_of_degree, String degree_name,
//			String term_id, String studentId_hid, String role);

	List<ArrayList<String>> getug_pg_fee_payment_Report_Excel(String Search,
			String name, String status1, String type_of_degree, String degree_name, String term_id, String inst_id,
			String role);
}
