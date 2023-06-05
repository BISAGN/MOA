package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Department_Equipment_Dao {
	
	
	public ArrayList<ArrayList<String>> getpid_from_userid_anatomy(String userid);
	
	public List<Map<String,Object>> getAllinfo_anatomy_child(int inst_id);
	
	public List<Map<String,Object>> getAllinfo_obstetric_gynacology(int inst_id);
	
	public String getAllItemforAnatomy();
	
	public ArrayList<ArrayList<String>> getAllitem_for_anatomy();
	
	public String getAllInfoCommunityMedicine();
	public ArrayList<ArrayList<String>> getAllCommunityMedicine_id();

	public String getForensicEquipmentDetails();
	public ArrayList<ArrayList<String>> getAllForensic_Equip_Details();
	
	public String getForensicActs();
	public ArrayList<ArrayList<String>> getAllForensic_Acts();

	public String getHomeophathic_Pharmacy();
	public ArrayList<ArrayList<String>> getHomeophatic_Pharmacy_id();
	public List<Map<String,Object>> getHomeo_Pharmacy_child(int inst_id);

	public String getPatho_Micro();
	public ArrayList<ArrayList<String>> getPatho_microbioDetails();

	public String getPatho_Bio();
	public ArrayList<ArrayList<String>> getPatho_biochemDetails();

	public String get_Bio();
	public ArrayList<ArrayList<String>> get_biochemDetails();
	
	public List<Map<String,Object>> getPractice_Med(int inst_id) ;

	public  List<Map<String,Object>> getRepertory(int inst_id);

	public  List<Map<String,Object>> getSurgery(int inst_id);
	
	public  List<Map<String,Object>> getHMM(int inst_id);
	
	public  List<Map<String,Object>> getPsych(int inst_id);
	
	public  List<Map<String,Object>> getPediatric(int inst_id);

	public List<Map<String,Object>> getOrganon(int inst_id);

	public List<Map<String,Object>> getAllComm(int inst_id);

	public ArrayList<ArrayList<String>> getOrganon_id(int parseInt);

}
