package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Hospital_Opd_Ipd_Report_DAO {

	
	public List<Map<String, Object>> View_OPD_Patients(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_OPD_Patientssum(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_IPD_Patients(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_IPD_Patientssum(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Bed_Days_Occupied(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Bed_Days_Occupiedsum(int id, int institute_id, String role, String userid);
	public List<Map<String, Object>> View_Beds_Existed(int id, int institute_id, String role, String userid);

	///doc view
	public String getFilePath_DynemicQueryForCommonDocPartOne(String id, String val, String fildname);
}
