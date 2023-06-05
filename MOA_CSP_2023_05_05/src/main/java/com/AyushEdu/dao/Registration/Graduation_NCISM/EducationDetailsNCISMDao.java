package com.AyushEdu.dao.Registration.Graduation_NCISM;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL;


public interface EducationDetailsNCISMDao {
	
	public long DataTableEducationNcism_DataTotalCount(String Search, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);

	public ArrayList<ArrayList<String>> DataTableEducationNcism_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic,Integer passing_year,String institute_name,Integer total_marks,
			Integer obtain_marks,String grade,String doc_path,int userid);
	
	public String getFilePathQueryNcism_(int id);
	public String updateNcism_SubCategory(EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL obj);

	public ArrayList<ArrayList<String>> geteditEducation_Ncism_data(String id);
	
	public ArrayList<ArrayList<String>> Getedu_chekyear_ncism_data(String p_id);

}
