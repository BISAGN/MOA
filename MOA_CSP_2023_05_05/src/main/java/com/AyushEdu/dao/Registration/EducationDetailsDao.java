package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.TB_PRE_EDUCATION_DETAILS;




public interface EducationDetailsDao {
	
	public long DataTableEducationDataTotalCount(String Search, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);

	public ArrayList<ArrayList<String>> DataTableEducationDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);
	
	public String getFilePathQuery(int id);
	public String updateSubCategory(TB_PRE_EDUCATION_DETAILS obj);

	public ArrayList<ArrayList<String>> geteditEducation_data(String id);

	public ArrayList<ArrayList<String>> Getedu_chekyear_nch_data(String p_id);

}
