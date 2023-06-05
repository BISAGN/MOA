package com.AyushEdu.dao.Degree_recognition_NCISM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_DEGREE_RECOGNITION_LIST_DRL;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA;
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;

public interface Degree_recognition_Within_inidaDao {

	public List<Map<String, Object>> getFilter_withinIndiaForm_A_UG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_withinIndiaForm_A_UGListCount(String search, int user_id);//int university_id,

	//public ArrayList<ArrayList<String>> getPopup_Data(String hid);

	public List<Map<String, Object>> getviewdatatoByid(String id);
	
//	public ArrayList<ArrayList<String>> getWithinindiaug(int userid);
//	
//	public ArrayList<ArrayList<String>> getWithinindiapg(int userid);
//	
//	public ArrayList<ArrayList<String>> getWithinindiampg(int userid);
//	
//	public ArrayList<ArrayList<String>> getWithinindiampgd(int userid);
	
	public List<Map<String, Object>> getFilter_withinIndiaForm_B_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,
	
	public long getFilter_withinIndiaForm_B_PGListCount(String search, int user_id);//int university_id,
	
	public List<Map<String, Object>> getviewdatatoBypgid(String id);
	
	public List<Map<String, Object>> getFilter_withinIndiaForm_C_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,
	
	public long getFilter_withinIndiaForm_C_PGListCount(String search, int user_id);//int university_id,
	
	public List<Map<String, Object>> getviewdatatoBypgmedicalid(String id);
	
	public List<Map<String, Object>> getFilter_withinIndiaForm_D_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,
	
	public long getFilter_withinIndiaForm_D_PGListCount(String search, int user_id);//int university_id,
	
	public List<Map<String, Object>> getviewdatatoBypgdiplomaid(String id);
}
