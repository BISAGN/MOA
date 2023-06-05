package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

public interface DocumentUploadDao {

	public ArrayList<ArrayList<String>> DataTableDocDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, Integer doc_name,int userid);
	
	public long DataTableDocDataTotalCount(String Search, Integer doc_name,int userid);
	public String getFilePathQuery(int id);
	public String getSignatureImagePath(String p_id);
	public String getPhotographImagePath(String p_id);

	public ArrayList<ArrayList<String>> get_uploded_imgthumb_data(String p_id);
	
}
