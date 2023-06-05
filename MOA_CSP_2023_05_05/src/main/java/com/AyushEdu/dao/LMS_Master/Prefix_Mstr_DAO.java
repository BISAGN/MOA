package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;


import com.AyushEdu.Models.Teacher_Master.TB_NCH_PREFIX_MSTR;

public interface Prefix_Mstr_DAO {
	public List<Map<String, Object>> DataTablePrefixDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String prefix, String status);

	public long DataTablePrefixDataTotalCount(String search, String prefix);
	
	public TB_NCH_PREFIX_MSTR getPrefixByid(int id);

	public String updatePrefix(TB_NCH_PREFIX_MSTR td);

}
