package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Degree_Category_MasterDao {
	public EDU_LMS_DEGREE_CATE_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTableDegree_Category_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Degree_Category, String status);

	public long DataTableDegree_Category_MasterDataTotalCount(String Search, String Degree_Category, String status);
}
