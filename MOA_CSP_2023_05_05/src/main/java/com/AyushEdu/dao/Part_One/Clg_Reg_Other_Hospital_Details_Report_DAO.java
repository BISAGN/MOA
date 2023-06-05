package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Other_Hospital_Details_Report_DAO {

	
	public List<Map<String, Object>> View_Other_hosp_maintenance_Staff_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_labour_room_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_investigation_Details(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_clinical_laboratory(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_dtl_functionality(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Hospital_Operation_theatre_staff_Details(int id, int institute_id, String role, String userid);

}
