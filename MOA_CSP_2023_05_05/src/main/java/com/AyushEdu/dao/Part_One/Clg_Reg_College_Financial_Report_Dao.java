package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_College_Financial_Report_Dao {

	public List<Map<String, Object>> DataTableSearch_College_FinancialDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String fix_deposite, String current_acct, String saving_acct,
			String project_cost, String income_statement, String role, String userid, String institute_id);

	public long DataTableSearch_College_FinancialDataTotalCount(String search, String fix_deposite, String current_acct,
			String saving_acct, String project_cost, String income_statement, String role, String userid,
			String institute_id);

	public List<Map<String, Object>> getAllfinancialdetailsReport(int id, int institute_id,String role);

	public List<Map<String, Object>> getAllbankdetailsReport(int id, int institute_id,String role);

	public List<Map<String, Object>> getAllExpensesReport(int id, int institute_id,String role);

	public List<Map<String, Object>> getLastYearPurchaseExp(int id, int inst_id, String role);

	public List<Map<String, Object>> getLastYearExpenditureExp(int id, int inst_id, String role);

}
