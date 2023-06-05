package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_ORGANIZATION_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface organization_MasterDao {
	public EDU_LMS_ORGANIZATION_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTableorganization_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String organization, String status);

	public long DataTableorganization_MasterDataTotalCount(String Search, String organization, String status);
}
