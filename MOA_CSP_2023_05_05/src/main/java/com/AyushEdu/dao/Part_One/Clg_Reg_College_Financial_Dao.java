package com.AyushEdu.dao.Part_One;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public interface Clg_Reg_College_Financial_Dao {

	public List<Map<String,Object>> getAllfinancialdetails(int institute_id);
	public List<Map<String,Object>> getAllbankdetails(int institute_id);
	public List<Map<String,Object>> getAllExpenses(int institute_id);
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id);
	public ArrayList<ArrayList<String>> getInstitute_id(String userid);
	public  List<Map<String,Object>> getAll_purchase_Expenses(int institute_id);
	public List<Map<String,Object>> getAll_misc_expen_Expenses(int institute_id);

}
