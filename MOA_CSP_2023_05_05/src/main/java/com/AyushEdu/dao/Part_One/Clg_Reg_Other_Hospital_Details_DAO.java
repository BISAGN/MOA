package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Other_Hospital_Details_DAO {
	
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id);
	public List<Map<String,Object>> GetMaintance_Record_Details(String institute_id);
	public List<Map<String,Object>> GetFunctionality_Details(String institute_id);
	public List<Map<String,Object>> getLabour_Room_Details(String institute_id);
	public List<Map<String,Object>> getOperation_Theatre_Details(String institute_id);
	public List<Map<String,Object>> getInvestigation_Details(String institute_id);
	public List<Map<String,Object>> getClinical_Laboratory_Details(String institute_id);

}
