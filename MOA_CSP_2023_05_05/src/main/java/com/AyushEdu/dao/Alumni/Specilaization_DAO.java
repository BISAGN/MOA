package com.AyushEdu.dao.Alumni;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Alumni.EDU_ALUM_SPECIALIZATION_MSTR;



public interface Specilaization_DAO {

	public EDU_ALUM_SPECIALIZATION_MSTR getSpecializationByid(int id);
	public long DataTableSpecilaizationDataTotalCount(String search, String degree, String specialization_name);

	public List<Map<String, Object>> DataTableSpecilaizationDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String degree, String specialization_name);

}
