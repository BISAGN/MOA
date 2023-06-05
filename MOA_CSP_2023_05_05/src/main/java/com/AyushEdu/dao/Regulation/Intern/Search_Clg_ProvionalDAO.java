package com.AyushEdu.dao.Regulation.Intern;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 
public interface Search_Clg_ProvionalDAO {

	//institute
	List<Map<String, Object>> DataTablegetFilter_Edu_clg_provisional_data(int startPage, int pageLength, String Search, String orderColunm, String orderType, String first_name, String status,String gender,
			String registration_state ,String dob,String date_of_reg ,String institute_name);

	long DataTablegetTotalclg_provisional_dataCount(String Search,  String first_name, String status , String gender , String registration_state, String dob,
			String date_of_reg ,String institute_name);
 
	
	
	
	public ArrayList<ArrayList<String>> medicalDataPreviewClgProvisional(int data  ,int d) ;
	

	public String Clg_Approve_DegreeData(String a ,String status ,String u_id,int u_id_n  ,String cp) ;
	

	public String Clg_Reject_DegreeData(String a,String status,String reject_remarks);
	
	
	 
}
