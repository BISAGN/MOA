package com.AyushEdu.dao.Regulation.Intern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface internSearch_State_PracDAO {
	
	public List<Map<String, Object>> DataTableSeacrh_State_internDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status,String gender,
			String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,
			String place_of_working ,String dob,String date_of_reg,String institute_name,String type_status);
	
	public long DataTableSeacrh_State_internDataTotalCount(String Search, String nrh_en_no,String first_name,String institute_status,String gender ,
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status);
	
	  
	public String approve_StateinternData(String a,String status,String username,String state_reg_no,String per_state   );
	//end
	public String reject_StateinternData(String a,String status,String username,String reject_remarks);

	public String getMaxAID();

//	public String reject_name_StatePracData(String string, String first_name, String username);

	public ArrayList<ArrayList<String>> get_internname_by_Reject_idata(String id);

	//ArrayList<ArrayList<String>> getstatusfrommedDegree(String regulation_p_id);


	
}
