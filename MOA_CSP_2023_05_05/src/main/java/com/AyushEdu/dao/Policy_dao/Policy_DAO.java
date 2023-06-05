package com.AyushEdu.dao.Policy_dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;

public interface Policy_DAO {


public ArrayList<ArrayList<String>> DataTablePolicyDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String policy,String initial_date,String policy_number,String globSearch,String status,String policy_type,HttpSession session);
	public long DataTablePolicyDataTotalCount(String Search,String policy,String initial_date,String policy_number,String status,String policy_type,String globSearch,HttpSession session);
	public ArrayList<ArrayList<String>> getpolicyinformation(int id);
	public String getFilePathQueryForDoc(String id);
	
	public String Policy(TB_POLICY_INITIAL_DRAFT obj);
	public ArrayList<ArrayList<String>> getpolicydocumentandversion(int id);
	public String getFilePathQuery_policy(int id);
	public Integer getchild_id_by_p_id(int id);
	public List<TB_SUBPOLICYCATEGORY> getPolicylistUrl(String u_id);
	public ArrayList<ArrayList<String>> getPolicyremarkList(int id);
	public Integer getpolicy_id_by_policy_unique_id(String puid);
}
