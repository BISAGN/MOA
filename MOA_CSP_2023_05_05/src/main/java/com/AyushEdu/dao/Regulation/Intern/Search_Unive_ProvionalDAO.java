package com.AyushEdu.dao.Regulation.Intern;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 
public interface Search_Unive_ProvionalDAO {

	//institute
	List<Map<String, Object>> DataTablegetFilter_Edu_Reg_provisional_data(int startPage, int pageLength, String Search, String orderColunm, String orderType, String first_name, String status,String gender,
			String registration_state ,String dob,String date_of_reg ,String institute_name);

	long DataTablegetTotalEdu_provisional_dataCount(String Search,  String first_name, String status , String gender , String registration_state, String dob,
			String date_of_reg ,String institute_name);

//	public String approve_INSregData(String a,String status,String b) ;
//	
//	public String reject_INSregData(String a,String status,String reject_remarks);
//
//	public ArrayList<ArrayList<String>> get_Parctname_by_InstiReject_idata(String id);
//	public ArrayList<ArrayList<String>> get_medicalcildID(String id) ;
    

	public ArrayList<ArrayList<String>> medicalDataPreviewUniProvisional(int data  ,int d);
	
}
