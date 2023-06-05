package com.AyushEdu.dao.Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;

public interface Search_PracDtlDAO {
	
	public List<Map<String, Object>> DataTableSeacrh_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status,String gender 
		 ,String dob ,String userId,String state_id,String university_id,String role);
	
	public long DataTableSeacrh_PracDataTotalCount(String Search,  String first_name, String status , String gender ,String dob ,String userId,String state_id,String university_id,String role);
	
//	public ArrayList<ArrayList<String>> getpercentage1(int userid, int exam_name);
//	public ArrayList<ArrayList<String>> getcredit1(int userid, int course_id);
	
	public ArrayList<ArrayList<String>> getdataofcerti(String id);

	public REG_NCH_FORM_A_P getViewByid(int id);

	String getImagePath7(String id);
	
	public ArrayList<ArrayList<String>> getdataofview(String id);
//	public ArrayList<ArrayList<String>> getviewinformation(int id);
	//public String approve_StatePracData(String a,String status,String username);

	public ArrayList<String> data_Search_Status(String userId);

	public ArrayList<ArrayList<String>> getdataofviewdegree(String id);

}
