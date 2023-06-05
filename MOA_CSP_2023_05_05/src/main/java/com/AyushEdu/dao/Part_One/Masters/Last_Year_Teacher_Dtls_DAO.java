package com.AyushEdu.dao.Part_One.Masters;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR;


public interface Last_Year_Teacher_Dtls_DAO {
	public CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR getlast_year_teacher_dtlsByid(int id);

	public List<Map<String, Object>> DataTablelast_year_teacher_dtlsList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String teacher_name,String status);

	public long DataTablelast_year_teacher_dtlsTotalCount(String search, String teacher_name);
	
	public String updatelast_year_teacher_dtls(CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR td);
}
