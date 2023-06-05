package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;

public interface Quota_Dao {
	public List<Map<String, Object>> DataTableQuotaDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String quota, String status);

	public long DataTableQuotaDataTotalCount(String search, String quota);
	
	public EDU_LMS_QUOTA_MSTR getQuotaByid(int id);

	public String updateQuota (EDU_LMS_QUOTA_MSTR td);


}
