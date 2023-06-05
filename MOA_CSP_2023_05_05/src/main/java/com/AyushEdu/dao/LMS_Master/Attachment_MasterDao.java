package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Attachment_MasterDao {
	public EDU_PG_DOC_UPLOAD_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTableAttachment_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String gracing_prd_mon, String status);

	public long DataTableAttachment_MasterDataTotalCount(String Search, String gracing_prd_mon, String status);
}
