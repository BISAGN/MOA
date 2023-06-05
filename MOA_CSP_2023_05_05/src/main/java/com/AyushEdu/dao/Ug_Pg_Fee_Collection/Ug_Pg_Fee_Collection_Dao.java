package com.AyushEdu.dao.Ug_Pg_Fee_Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Ug_Pg_Fee_Collection_Dao {
	
	public List<Map<String, Object>> StudentDataAuto(String autoString,String role,String degree_cat,String instid);
	
	public List<Map<String, Object>> Getfeesdetails(String studentid,String role,String inst_id);
	
	public List<Map<String, Object>> GetRoleInfoFromStudentId(String Instrole,String studentid);

	public ArrayList<ArrayList<String>> degreefrom_fromybyinstlist_ctrl(String type_of_degree,String inst_id,String role);
	
	public List<Map<String, Object>> getStuTypeofDegProf(String role,String user_id);
	
	public String getSupplyData(String stu_id);
	
	public String isFessPaid(String stu_id,String prof) ;
	
	public String InstNoOfPart(String inst_id);
	
	public String CheckFessdataAvlbl(String stu_id,String prof,String degree);

	public ArrayList<ArrayList<String>> getVerifyStatus(String stu_id, String role);

	public ArrayList<ArrayList<String>> Getlastnoofpart(String studentid, String role);

	
	//public ArrayList<ArrayList<String>> payfees_part(String id);
	
}
