package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_DIVISION_MSTR;

public interface Division_DAO {
	
	public EDU_DIVISION_MSTR getdivisionByid(int id);

	public List<Map<String, Object>> DataTableDivisionDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String division_name, String status);

	public long DataTableDivisionDataTotalCount(String search, String division_name);

}
