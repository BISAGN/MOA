package com.AyushEdu.dao.LMS_Master;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;

public interface Type_of_Degree_MstrDao {

	public List<Map<String, Object>> DataTabletype_of_degreeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_degree, String status);
	
	public long DataTabletype_of_degreeDataTotalCount(String Search,String type_of_degree, String status);
	
	public EDU_LMS_TYPE_OF_DEGREE_MASTER getType_of_DegreeByid(int id);
	public ArrayList<ArrayList<String>> getDataListofdegSysToD(String system);
	public ArrayList<ArrayList<String>> getMarqueData(String system);
}
