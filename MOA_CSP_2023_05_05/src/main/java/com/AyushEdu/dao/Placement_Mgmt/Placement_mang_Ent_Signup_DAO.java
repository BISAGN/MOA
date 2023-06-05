package com.AyushEdu.dao.Placement_Mgmt;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public interface Placement_mang_Ent_Signup_DAO {
	
	List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic,String userid) throws ParseException;

	long DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise(String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic,String userid) throws ParseException;
	
	String getImagePath(String id);
	
	String getImagePath3(String id);
	
	public String approve_StudentPracData(String a,String username,String userId);
	//end
	public String reject_StudentPracData(String a,String username,String userId_reject);
	///HOD/////
	public String approve_EnterprisePracData(String a,String username,String userId);
	public String reject_EnterprisePracData(String a,String username,String userId_reject);
	List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise_FOR_HOD(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status) throws ParseException;

	long DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise_ForHOD(String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status) throws ParseException;
//	public String getMaxAID();
	
	List<Map<String, Object>> GetIntrested_Students_Data(String id) throws ParseException;
	
	public String ApproveIntrested_Students_Data(String Id);
	
	List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise_fromId(String id) throws ParseException;
	
}
