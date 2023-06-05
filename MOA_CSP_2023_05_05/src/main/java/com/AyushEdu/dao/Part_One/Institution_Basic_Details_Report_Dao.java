package com.AyushEdu.dao.Part_One;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Institution_Basic_Details_Report_Dao {

	public List<Map<String, Object>> DataTableSearch_Basic_InfoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String inst_code, String inst_state, String inst_city, String inst_pincode,
			String inst_mo_no, String inst_email, String institution_type, String managing_body,
			String management_contact, String name_of_society, String mng_state, String mng_city, String mng_mo_no,
			String mng_email, String s_registration_no, String university_affiliated, String role, String userid,
			String institute_id,String status);
	
	public long DataTableSearch_Basic_InfoDataTotalCount(String Search, String inst_code, String inst_state, String inst_city,
			String inst_pincode, String inst_mo_no, String inst_email, String institution_type, String managing_body,
			String management_contact, String name_of_society, String mng_state, String mng_city, String mng_mo_no,
			String mng_email, String s_registration_no, String university_affiliated, String role, String userid,
			String institute_id,String status);

	public List<Map<String, Object>> getAllinfo_connectivityReport(int id, int inst_id,String role);

	public List<Map<String, Object>> getAllinfo_police_stReport(int id, int inst_id,String role);

	public List<Map<String, Object>> getAllinfo_inst_dtlReport(int id, int inst_id, String role);

	public List<Map<String, Object>> getInstnameReport(int id, int inst_id,String role);

	public List<Map<String, Object>> getAllinfo_undertaling_repoReport(int inst_id);

	public List<Map<String, Object>> getInstDetailReport(int id, int inst_id, String role, String userid);

	public List<Map<String,Object>> getPidfromInstidReport(int id, int inst_id,String role,String userid,String table_name,String fieldname);

	public List<Map<String, Object>> getAllinfo_intake_capacity_Report(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_quali_instReport(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_UG_intake_capacity_Report(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_PG_intake_capacity_Report(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_dtl_landReport(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllPersdetailsReport(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_intake_capacity_for_PG_Report(int id, int inst_id, String role);
	
	public List<Map<String, Object>> getInstitute_id_from_main_id(int id);

	public List<Map<String, Object>> getAllinfo_intake_cap_childView(int id, int inst_id, String role);

	public List<Map<String, Object>> getStu_Details_Basic_info_Upload_Doc_View(int id, int inst_id, String role);

}
