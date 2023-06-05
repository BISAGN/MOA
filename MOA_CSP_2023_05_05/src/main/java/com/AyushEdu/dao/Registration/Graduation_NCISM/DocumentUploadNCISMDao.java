package com.AyushEdu.dao.Registration.Graduation_NCISM;

import java.util.ArrayList;

public interface DocumentUploadNCISMDao {

	public ArrayList<ArrayList<String>> DataTableDocNcism_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, Integer doc_name,int userid);
	
	public long DataTableDocNcism_DataTotalCount(String Search, Integer doc_name,int userid);
	public String getFilePathNcism_Query(int id);
	public String getSignatureImagePath_Ncisam(String p_id);
	public String getPhotographImagePath_Ncisam(String p_id);
	public ArrayList<ArrayList<String>> get_uploded_imgthumb_Ncism_data(String p_id);
	
}
