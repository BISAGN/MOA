package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Link_ga_po_dao {

	
	public ArrayList<ArrayList<String>> getGAbyDegree(String degree_id,String user_id);
	
	public ArrayList<ArrayList<String>> getPObyDegree(String degree_id,String user_id);
	
	public List<Map<String, Object>> DataTableGa_PoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id,String degree_id,String graduateattribute_id,String programoutcome_id, String role,String status);
	
	public long DataTableGa_Po_DataTotalCount(String Search,String system_id,String degree_id, String graduateattribute_id,
			String programoutcome_id,String status,String role);
	
	
	public String  updatega_po(String po,String id,String ga,String degree_id);
	
	
	
}
