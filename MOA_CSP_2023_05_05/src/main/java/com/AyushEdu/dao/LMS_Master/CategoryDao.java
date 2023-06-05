package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;

public interface CategoryDao {

	public List<Map<String, Object>> DataTableCategoryDataList1(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String category,String status);

	public long DataTableCategoryDataTotalCount1(String search, String category);
	
	public EDU_LMS_CATEGORY_MSTR getCategoryByid(int id);
}
