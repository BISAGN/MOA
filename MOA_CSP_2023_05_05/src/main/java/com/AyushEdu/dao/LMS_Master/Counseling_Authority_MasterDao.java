package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Counseling_Authority_MasterDao {
	public EDU_LMS_COUNSELING_AUTHORITY_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTablecounseling_authority_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String counseling_authority, String status);

	public long DataTablecounseling_authority_MasterDataTotalCount(String Search, String counseling_authority, String status);
}
