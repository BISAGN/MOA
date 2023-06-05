package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.TB_COLLAGE_MSTR;




public interface CollageMstrDao {
	
	public ArrayList<ArrayList<String>> DataTablecollageDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String doc_name,String upload_document,String status) ;
	
	
	public long DataTablecollageDataTotalCount(String Search, String collage_name,String collage_code,String status);
	
	public String updateCollagedata(TB_COLLAGE_MSTR obj);
	
}
