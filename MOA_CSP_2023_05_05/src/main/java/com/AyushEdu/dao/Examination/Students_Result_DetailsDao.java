package com.AyushEdu.dao.Examination;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Students_Result_DetailsDao {

	public List<ArrayList<String>> getstu_res_declare_data(String system_id,String degree_id,String professional_id,String institute_id,String role,String uni_id);
	
	public List<ArrayList<String>>currentTerm(int stuId,String role);
	
	public ArrayList<ArrayList<String>> getuserid_listby_university(int userid);
}
