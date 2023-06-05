package com.AyushEdu.dao.B_Regulation;

import java.util.List;
import java.util.Map;

public interface b_Search_NCH_PracDAO {

	public List<Map<String, Object>> DataTable_b_Seacrh_NCH_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status, String gender, String reg_no,
			String registration_state, String per_state, String per_district, String type_of_degree, String degree_name,
			String place_of_working, String dob, String date_of_reg);

	public long DataTable_b_Seacrh_NCH_PracDataTotalCount(String Search, String first_name, String institute_status,
			String gender, String reg_no, String registration_state, String per_state, String per_district,
			String type_of_degree, String degree_name, String place_of_working, String dob, String date_of_reg);
	
	public String approve_b_NCHregData(String a,String status,String username,String nrh_en_no);
	 public String getMaxAID();
	 public String reject_b_NCHPracData(String a,String status);

}
