package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.TB_EXPERIENCE_DETAILS;




public interface TotalExperienceDao {
	
	public ArrayList<ArrayList<String>> DataTableExperienceDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid);
	
	public long DataTableExperienceDataTotalCount(String Search, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid);
	
	public String getFilePathQuery(int id);
	public String updateSubCategory(TB_EXPERIENCE_DETAILS obj);
	
	///////////////for date
	public ArrayList<ArrayList<String>> DataFor_DateDataList(String exp_emp_fromdate,String exp_emp_todate,int userid);

}
