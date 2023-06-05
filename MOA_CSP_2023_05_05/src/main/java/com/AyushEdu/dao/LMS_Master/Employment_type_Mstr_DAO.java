package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.TB_NCH_EMPLOYEMENT_TYPE_MSTR;

public interface Employment_type_Mstr_DAO {

	public List<Map<String, Object>> DataTableEmploymentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String empl_type, String status);

	public long DataTableEmploymentDataTotalCount(String search, String empl_type);
	
	public TB_NCH_EMPLOYEMENT_TYPE_MSTR getEmploymentByid(int id);

	public String updateEmployment(TB_NCH_EMPLOYEMENT_TYPE_MSTR td);





}

