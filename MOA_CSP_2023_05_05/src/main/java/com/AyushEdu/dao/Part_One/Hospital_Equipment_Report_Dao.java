package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Hospital_Equipment_Report_Dao {
	
	public List<Map<String, Object>> DataTableSearch_Hospital_Equipment_ReportDataList( int startPage,
			int pageLength, String Search, String orderColunm, String orderType,String articles,String instId,String role,String userid,String institute_id);

	public long DataTableSearch_Hospital_Equipment_ReportDataTotalCount(String search,String articles,String instId,String role,String userid,String institute_id);

	public List<Map<String, Object>> View_Hospital_Details(int id, int institute_id, String role, String userid);
	
	public List<Map<String, Object>> getHosp_Equipment_Document_Details_List(String institute_id);


}
