package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Masters.TB_CASTE_CATEGORY_MASTER;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Cast_Category_MasterDao {
	public TB_CASTE_CATEGORY_MASTER getsystemByid(int id);

	public List<Map<String, Object>> DataTableCast_Category_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Cast_Category, String status);

	public long DataTableCast_Category_MasterDataTotalCount(String Search, String Cast_Category, String status);
}
