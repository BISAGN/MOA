package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Institution_Basic_Details_Dao {
	
	
	public List<Map<String,Object>> getAllPersdetails(int inst_id);


	
	public List<Map<String,Object>> getAllinfo_connectivity(int inst_id);
	
	public List<Map<String,Object>> getAllinfo_police_st(int inst_id);
	public List<Map<String,Object>> getAllinfo_inst_dtl(int inst_id);
	public List<Map<String,Object>> getAllinfo_dtl_land(int inst_id);
	
	public List<Map<String,Object>> getInstname(int inst_id); 
	
	public List<Map<String,Object>> getAllinfo_undertaling_repo(int inst_id);
	
	public List<Map<String,Object>> getAllinfo_quali_inst(int inst_id);
	
	public String getAllSubjectforPG();
	
	public String getAllSubjectforUG(int inst_id);
	
	public ArrayList<ArrayList<String>> getAllCourse_UG(int inst_id);
	
	public ArrayList<ArrayList<String>> getAllCourse_PG();
	
	public ArrayList<ArrayList<String>> getpid_from_userid(String userid);
	
	public List<Map<String,Object>> getAllinfo_intake_cap_child(int inst_id);
	
	public ArrayList<ArrayList<String>> getinfofromteacher_code(String teacher_code);
	
	public ArrayList<ArrayList<String>> getdate(int inst_id); 
	
	public ArrayList<ArrayList<String>> getinstName_Code(int inst_id) ;
	
	public List<Map<String,Object>> getIntake_Cap(String institute_id);
	
	public List<Map<String,Object>> getIntake_Cap_pg(String institute_id);


}
