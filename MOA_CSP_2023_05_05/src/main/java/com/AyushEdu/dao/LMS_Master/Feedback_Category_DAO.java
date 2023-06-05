package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_SUBCATEGORY_MSTR;

public interface Feedback_Category_DAO {
	public List<Map<String, Object>> DataTableCategoryDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String category, String status);

	public long DataTableCategoryDataTotalCount(String search, String category,String status);
	
	public TB_FEEDBACK_CATEGORY_MSTR getCategoryByid(int id);

	public String updateCategory(TB_FEEDBACK_CATEGORY_MSTR td);
	public String updateSubCategory(TB_FEEDBACK_SUBCATEGORY_MSTR td);
	
	
	public List<Map<String, Object>> DataTableSubCategoryDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String category,String subcategory,String feedback_detail, String status);

	public long DataTableSubCategoryDataTotalCount(String search, String category,String subcategory,String feedback_detail,String status);

}
