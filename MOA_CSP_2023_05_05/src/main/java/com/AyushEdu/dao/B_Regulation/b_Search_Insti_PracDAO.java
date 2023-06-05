package com.AyushEdu.dao.B_Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface b_Search_Insti_PracDAO {

	List<Map<String, Object>> DataTableEdu_b_Reg_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status, String gender, String reg_no,
			String registration_state, String dob, String date_of_reg);

	long DataTableEdu_b_Reg_masterDataTotalCount(String Search, String first_name, String institute_status,
			String gender, String reg_no, String registration_state, String dob, String date_of_reg);

	String approve_INS_b_regData(String a, String status);

	String reject_INS_b_regData(String a, String status);
	
	public ArrayList<ArrayList<String>> DataTableEdu_b_Reg_masterDataList();

}
