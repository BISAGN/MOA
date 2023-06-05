package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_College_Infrastructure_DAO {
	
	public ArrayList<ArrayList<String>> getInstitute_id(String user_id);
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id);
	public ArrayList<ArrayList<String>> getAllDepartment_name();
	public ArrayList<ArrayList<String>> getCollege_Council_Details(int institute_id);
	List<Map<String, Object>> getClg_central_lib_info(int institute_id);
	List<Map<String, Object>> getDepart_dtl(int institute_id);
	public String getAllDepartment();
	public List<Map<String,Object>> GetProgress_of_Institution_Details(String institute_id);
	public List<Map<String,Object>> getAdd_Info_Details(String institute_id);
	public List<Map<String,Object>> getHerbal_Garden_Details(String institute_id);
	public List<Map<String,Object>> getCentral_Library_Details(String institute_id);
	public List<Map<String,Object>> getConstructed_Area_Details(String institute_id);
	public List<Map<String,Object>> getMessDetails(String institute_id);
	public List<Map<String,Object>> getHostelDetails(String institute_id);

}
