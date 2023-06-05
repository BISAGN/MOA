package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Fees_Category_MasterDao {
	public CoFeescategorytype getsystemByid(int id);

	public List<Map<String, Object>> DataTableFees_Category_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Fees_Category, String status);

	public long DataTableFees_Category_MasterDataTotalCount(String Search, String Fees_Category, String status);
}
