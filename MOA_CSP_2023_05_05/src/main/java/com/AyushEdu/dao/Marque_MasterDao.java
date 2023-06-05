package com.AyushEdu.dao;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.TB_MAERQUE;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Marque_MasterDao {
	public TB_MAERQUE getsystemByid(int id);

	


	public List<Map<String, Object>> DataTableMarque_MasterDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String Marque, String from_date, String to_date, String ayu_port,
			String nch_port, String ncism_port, String status);



	public long DataTableMarque_MasterDataTotalCount(String search, String Marque, String from_date, String to_date,
			String ayu_port, String nch_port, String ncism_port, String status);
}
