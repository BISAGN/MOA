package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;

public interface Gender_DAO {
	public List<Map<String, Object>> DataTableGenderDataList1(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String gender_name,String status);

	public long DataTableGenderDataTotalCount1(String search, String gender_name);
	public EDU_LMS_GENDER_MSTR getGenderByid(int id);

}
