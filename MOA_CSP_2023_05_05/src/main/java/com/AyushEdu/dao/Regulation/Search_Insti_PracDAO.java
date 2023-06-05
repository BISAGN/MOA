package com.AyushEdu.dao.Regulation;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

 
public interface Search_Insti_PracDAO {

	//institute
	List<Map<String, Object>> DataTableEdu_Reg_masterDataList(int startPage, int pageLength, String Search, String orderColunm, String orderType, String first_name, String status,String gender,
			String registration_state ,String dob,String date_of_reg ,String institute_name,HttpSession session);

	long DataTableEdu_Reg_masterDataTotalCount(String Search,  String first_name, String status , String gender , String registration_state, String dob,
			String date_of_reg ,String institute_name);

	public String approve_INSregData(String a,String status,String b) ;
	
	public String reject_INSregData(String a,String status,String reject_remarks);

	public ArrayList<ArrayList<String>> get_Parctname_by_InstiReject_idata(String id);
//	public ArrayList<ArrayList<String>> get_medicalcildID(String id) ;
    
	
	
}
