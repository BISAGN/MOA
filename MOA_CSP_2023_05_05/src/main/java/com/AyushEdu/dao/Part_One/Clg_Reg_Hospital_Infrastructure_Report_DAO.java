package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Hospital_Infrastructure_Report_DAO {

	
	public List<Map<String, Object>> View_Infast_rede(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Administrator(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_OPD(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_IPD(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_OT(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Reha_unit(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_CL(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Radiology_Sonography(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Kitchen_Canteen(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Hosp_Stores(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Infrastructure_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Infra_Details(int id, int institute_id, String role, String userid);

}
