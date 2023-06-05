package com.AyushEdu.dao.Policy_dao_Master;


import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Policy_Model_Master.TB_POLICYCATEGORY_MASTER;



public interface PolicyCategoryDAO {
	
	

	public TB_POLICYCATEGORY_MASTER getPolicycategoryByid(int id);

	public List<Map<String, Object>> DataTablepolicycategoryDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String policycategory,String status);

	public long DataTablepolicycategoryDataTotalCount(String search, String policycategory);


	public TB_POLICYCATEGORY_MASTER getcategorypolicyByid(int id);
	
}
