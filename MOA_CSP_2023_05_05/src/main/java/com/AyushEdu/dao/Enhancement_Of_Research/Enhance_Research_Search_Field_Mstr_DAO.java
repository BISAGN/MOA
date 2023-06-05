package com.AyushEdu.dao.Enhancement_Of_Research;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR;


public interface Enhance_Research_Search_Field_Mstr_DAO {
		public List<Map<String,Object>> Enhance_Research_Search_Field_Mstr_DataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String search_field_name, String status);
		
		public long DataTotalEnhance_Research_Search_Field_MstrCount(String search, String search_field_name, String status);
		
		public TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR getEnhance_Research_Search_Field_MstrByid(int id);

}
