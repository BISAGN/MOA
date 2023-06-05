package com.AyushEdu.dao.LMS_Master;


import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;




public interface Set_MasterDAO {
	
	

	public TB_SET_MASTER getSetmasterByid(int id);

	public List<Map<String, Object>> DataTablesetmasterDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String setname,String status, String term_id,String prof_name);

	public long DataTablesetmasterDataTotalCount(String search, String setname, String term_id,String prof_name );


 	public TB_SET_MASTER getsetmasterByid(int id);
	
}
