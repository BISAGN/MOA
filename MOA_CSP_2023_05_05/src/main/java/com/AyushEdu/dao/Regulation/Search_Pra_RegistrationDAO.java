package com.AyushEdu.dao.Regulation;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Search_Pra_RegistrationDAO {

	List<Map<String, Object>> DataTablePra_Registration_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_state, String status) throws ParseException;

	long DataTablePra_Registration_masterDataTotalCount(String Search, String institute_state, String status) throws ParseException;

	String approve_reject_reg(String a,String status,HttpSession session) throws ParseException;
}
