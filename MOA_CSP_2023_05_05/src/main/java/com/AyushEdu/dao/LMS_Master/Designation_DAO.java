package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;

public interface Designation_DAO {
	public List<Map<String, Object>> DataTableDesignationDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String designation, String status);

	public long DataTableDesignationDataTotalCount(String search, String designation);
	
	public TB_NCH_DESIGNATION_MSTR getDesignationByid(int id);

	public String updateDesignation(TB_NCH_DESIGNATION_MSTR td);

}
