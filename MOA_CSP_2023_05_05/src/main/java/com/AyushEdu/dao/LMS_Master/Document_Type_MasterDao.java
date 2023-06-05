package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_DOCUMENT_TYPE_MSTR;

public interface Document_Type_MasterDao {
	public EDU_LMS_DOCUMENT_TYPE_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTableDocument_Type_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Document_Type, String status);

	public long DataTableDocument_Type_MasterDataTotalCount(String Search, String Document_Type, String status);
}
