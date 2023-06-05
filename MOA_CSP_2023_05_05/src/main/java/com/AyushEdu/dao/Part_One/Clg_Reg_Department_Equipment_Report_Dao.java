package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Department_Equipment_Report_Dao {

	public List<Map<String, Object>> getAnatomyView(int id, int inst_id, String role);

	public List<Map<String, Object>> getAllinfo_anatomy_childView(int id, int inst_id, String role);

	public List<Map<String, Object>> getcommunity_medicineView(int id, int inst_id, String role);

	public List<Map<String, Object>> getForensic_Medicine_ToxicologyEquipView(int id, int inst_id, String role);

	public List<Map<String, Object>> getForensic_Medicine_ToxicologyActView(int id, int inst_id, String role);

	public List<Map<String, Object>> getHomeo_PharmView(int id, int inst_id, String role);

	public List<Map<String, Object>> getHomeo_Pharm_ChildView(int id, int inst_id, String role);

	public List<Map<String, Object>> getEquip_organon_medicineView(int id, int inst_id, String role);

	public List<Map<String, Object>> getpathology_microbiologyView(int id, int inst_id, String role);

	public List<Map<String, Object>> getPhysiology_BiochemistryView(int id, int inst_id, String role);

	public List<Map<String, Object>> getBiochemistryView(int id, int inst_id, String role);

	public List<Map<String, Object>> getPractice_MedView(int id, int inst_id, String role);

	public List<Map<String, Object>> getRepertoryView(int id, int inst_id, String role);

	public List<Map<String, Object>> getSurgeryView(int id, int inst_id, String role);

	public List<Map<String, Object>> getHMMView(int id, int inst_id, String role);

	public List<Map<String, Object>> getPsychView(int id, int inst_id, String role);

	public List<Map<String, Object>> getPediatricView(int id, int inst_id, String role);

	public List<Map<String, Object>> getcommunity_medicine_childView(int id, int inst_id, String role);

	public List<Map<String, Object>> getObstricView(int inst_id);
	
//	public String getFilePath_DynemicQueryForDoc_part_one(String id, String val, String fildname);

}
