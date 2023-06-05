package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_STUDENT_INTERN_RULES;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;

public interface studentintern_ruleDAO {

	List<Map<String, Object>> DataTablesystemDataListRule(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String role_name,String year,String month, String status);

	long DataTablesystemDataTotalCountRule(String search, String role_name,String year,String month);
	
	public Boolean  Delete_SI_RULE_Mstr_Data(int id);

	EDU_LMS_STUDENT_INTERN_RULES getsystemByid(int id);


	
//	public EDU_LMS_SYSTEM_MSTR getsystemByid(int id);

//	public List<Map<String, Object>> DataTablesystemDataListRule(int startPage, int pageLength, String search,
//			String orderColunm, String orderType,String role_name,String status);

	 

	 
}
