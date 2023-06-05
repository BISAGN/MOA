package com.AyushEdu.dao.Alumni;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

public interface Feed_Category_Dao {
	
	public EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR getfeedcategoryByid(int id);
	
	public List<Map<String, Object>> DataTableFeedCategoryDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String feed_category,String status);
	
	
	public long DataTableFeedCategoryDataTotalCount(String search, String feed_category);
	
	public String updatecategory(EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR td);
}
