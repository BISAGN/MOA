package com.AyushEdu.dao.Placement_Mgmt;

import java.util.List;
import java.util.Map;

public interface Search_placement_reg_Dao {

	public List<Map<String, Object>> DataTableSearch_placement_reg(int startPage, int pageLength,
			String Search,String orderColunm,String orderType);
	
	public long DataTableSearch_placement_reg_count(String search);
	
	String getImagePath(String id);


}
