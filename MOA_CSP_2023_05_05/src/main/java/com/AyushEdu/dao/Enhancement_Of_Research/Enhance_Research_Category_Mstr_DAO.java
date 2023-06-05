package com.AyushEdu.dao.Enhancement_Of_Research;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_CATEGORY_MSTR;

public interface Enhance_Research_Category_Mstr_DAO {
	public List<Map<String,Object>> Enhance_Research_Category_Mstr_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String category_name, String status);
	
	public long DataTotalEnhance_Research_Category_MstrCount(String search, String category_name, String status);
	
	public TB_ENHANCE_RESEARCH_CATEGORY_MSTR getEnhance_Research_Category_MstrByid(int id);
}
