package com.AyushEdu.dao.Registration.Postgraduate;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_PRE_EDUCATION_DETAILS;




public interface EducationDetails_PG_Dao {
	
	public long DataTableEducation_PGDataTotalCount(String Search, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);

	public ArrayList<ArrayList<String>> DataTableEducation_PGDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);
	
	public String getFilePathQuery(int id);
	public String update_PGSubCategory(EDU_PG_PRE_EDUCATION_DETAILS obj);

	public ArrayList<ArrayList<String>> geteditEducation_PG_data(String id);

}
