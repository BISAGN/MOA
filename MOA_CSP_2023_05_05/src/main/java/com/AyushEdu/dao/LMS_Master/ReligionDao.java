package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.TB_LMS_RELIGION_MSTR;

public interface ReligionDao {
	
	public List<Map<String, Object>> DataTableReligionDataList1(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String religion,String status);

	public long DataTableReligionDataTotalCount1(String search, String religion);
	
	public TB_LMS_RELIGION_MSTR getReligionByid(int id);

}
