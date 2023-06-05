package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.TB_LMS_MARITAL_STATUS_MSTR;

public interface Marital_StatusDao {

	public List<Map<String, Object>> DataTablemarital_statusDataList1(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String marital_status,String status);

	public long DataTablemarital_statusDataTotalCount1(String search, String marital_status);
	
	public TB_LMS_MARITAL_STATUS_MSTR getmarital_statusByid(int id);
	
}
