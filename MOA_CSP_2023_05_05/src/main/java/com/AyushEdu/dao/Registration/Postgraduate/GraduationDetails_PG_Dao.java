package com.AyushEdu.dao.Registration.Postgraduate;

import java.util.ArrayList;

public interface GraduationDetails_PG_Dao {

	ArrayList<ArrayList<String>> getaddmoredata1(String p_id);
	ArrayList<ArrayList<String>> getaddmoredata2(String p_id);
	

	ArrayList<ArrayList<String>> DataTableGraduation_PGDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name_of_exam, String month_year, String university,
			String no_of_attempts);

	long DataTableGraduation_PGDataTotalCount(String search, String name_of_exam, String month_year, String university,
			String no_of_attempts);

}
