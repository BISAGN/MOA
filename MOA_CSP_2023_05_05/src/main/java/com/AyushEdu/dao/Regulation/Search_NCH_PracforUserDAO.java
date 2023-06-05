package com.AyushEdu.dao.Regulation;

import java.util.List;
import java.util.Map;


public interface Search_NCH_PracforUserDAO {

	
	
	public List<Map<String, Object>> DataTableSeacrh_NCH_PracforUserDataList(int startPage, int pageLength,	String Search, 
			String orderColunm, String orderType, String nrh_en_no,String first_name ,String per_state, String registration_state);
	
	public long DataTableSeacrh_NCH_PracforUserDataTotalCount(String Search, String nrh_en_no,
			String first_name ,String per_state, String registration_state);
}
