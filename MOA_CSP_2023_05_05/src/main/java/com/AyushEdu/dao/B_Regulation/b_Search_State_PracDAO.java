package com.AyushEdu.dao.B_Regulation;

import java.util.List;
import java.util.Map;

public interface b_Search_State_PracDAO {

	List<Map<String, Object>> DataTable_b_Seacrh_State_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status, String gender, String reg_no,
			String registration_state, String per_state, String per_district, String type_of_degree, String degree_name,
			String place_of_working, String registration_for_type, String dob, String date_of_reg);

	long DataTable_b_Seacrh_State_PracDataTotalCount(String Search, String nrh_en_no, String first_name,
			String institute_status, String gender, String reg_no, String registration_state, String per_state,
			String per_district, String type_of_degree, String degree_name, String place_of_working,
			String registration_for_type, String dob, String date_of_reg);

	String approve_b_StatePracData(String string, String status, String username);

	String reject_b_StatePracData(String string, String status, String username);

}
