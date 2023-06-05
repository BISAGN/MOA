package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_NCH_PracDAO {
	
	public List<Map<String, Object>> DataTableSeacrh_NCH_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status,String gender ,
			String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,
			String place_of_working  ,String dob,String date_of_reg,String institute_name,String type_status);
	
	public long DataTableSeacrh_NCH_PracDataTotalCount(String Search,  String first_name, String status , String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,
			  String dob,String date_of_reg,String institute_name,String type_status);

	//public String approve_NCHPracData(String a,String status,String username);
	//public String approve_NCHregData(String a,String status,String username,String nrh_en_no) throws ParseException;
	public String approve_NCHregData(String a,String upto,String status,String username,String state_reg_no,String per_state,String nrh_en_no) throws ParseException;
	 public String getMaxAID();
	 public String reject_NCHPracData(String a,String status,String reject_remarks);
	 
	 
	// public String EncInsertdataHistory(int updateid);
	 
 long NationalDataTotalCount(int id);

 public ArrayList<ArrayList<String>> get_Parctname_by_NCHReject_idata(String id);
}
