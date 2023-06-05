package com.AyushEdu.dao.Policy_dao;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Track_Policy_StatusDAO {
	public ArrayList<ArrayList<String>> DataTableTrackPolicyStatusDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String policy_number,String category, String sub_category,String policy_type,String globSearch,HttpSession session);
	public long DataTableTrackPolicyStatusTotalCount(String Search, String policy_number,String category, String sub_category,String policy_type,String globSearch,HttpSession session);
	public String getFilePathQuery_Track_policy(int id);
	public ArrayList<ArrayList<String>> GetCategoryData();
	public ArrayList<ArrayList<String>> GetSubCategoryData(int policy_category);
	public ArrayList<String> data_policy_number_Search_Status(String p_no);

}
