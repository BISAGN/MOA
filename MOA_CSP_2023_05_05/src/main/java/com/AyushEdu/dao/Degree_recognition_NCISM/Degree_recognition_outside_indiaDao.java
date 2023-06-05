package com.AyushEdu.dao.Degree_recognition_NCISM;

import java.util.List;
import java.util.Map;

public interface Degree_recognition_outside_indiaDao {
	
	public List<Map<String, Object>> getFilter_outsideIndiaForm_A_UG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_outsideIndiaForm_A_UGListCount(String search, int user_id);//int university_id,

	public List<Map<String, Object>> getviewdatatoByoutsideindiaugid(String id);
	
	public List<Map<String, Object>> getFilter_outsideIndiaForm_B_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_outsideIndiaForm_B_PGListCount(String search, int user_id);//int university_id,

	public List<Map<String, Object>> getviewdatatoByoutsideindiapgid(String id);
	
	public List<Map<String, Object>> getFilter_outsideIndiaForm_C_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_outsideIndiaForm_C_PGListCount(String search, int user_id);//int university_id,

	public List<Map<String, Object>> getviewdatatoByoutsideindiapgmedicalid(String id);
	
	public List<Map<String, Object>> getFilter_outsideIndiaForm_D_PG_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  int user_id);//int university_id,

	public long getFilter_outsideIndiaForm_D_PGListCount(String search, int user_id);//int university_id,

	public List<Map<String, Object>> getviewdatatoByoutsideindiapgdiplomaid(String id);

}
