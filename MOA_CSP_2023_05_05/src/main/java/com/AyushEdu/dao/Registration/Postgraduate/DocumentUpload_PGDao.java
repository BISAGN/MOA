package com.AyushEdu.dao.Registration.Postgraduate;

import java.util.ArrayList;

public interface DocumentUpload_PGDao {

	public ArrayList<ArrayList<String>> DataTableDocData_PGList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, Integer doc_name,int userid);
	
	public long DataTableDocData_PGTotalCount(String Search, Integer doc_name,int userid);
	public String getFilePathQuery_PG(int id);
	public String getSignatureImagePath_PG(String p_id);
	public String getPhotographImagePath_PG(String p_id);

	public ArrayList<ArrayList<String>> get_uploded_imgthumb_data_PG(String p_id);
	
}
