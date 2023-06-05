package com.AyushEdu.dao.Policy_dao_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;


public interface SubcategoryDAO {
	
	public TB_SUBPOLICYCATEGORY getSubcategoryByid(int id);

	public List<Map<String, Object>> DataTablesubcategoryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String subcategory, String status,String policycategory);
	

	public long DataTablesubcategoryDataTotalCount(String Search, String subcategory, String status,String policycategory);


	public TB_SUBPOLICYCATEGORY getsubpolicyByid(int id);

	ArrayList<ArrayList<String>> getpolicycategory(int id);
	

}
