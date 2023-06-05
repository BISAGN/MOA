package com.AyushEdu.dao.Alumni;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;




public interface Knowledge_Repo_Category_Mstr_DAO {
	


	public List<Map<String, Object>> DataTableRepoCategoryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String category_repo, String status);

	public long DataTableRepoCategoryDataTotalCount(String Search, String category_repo, String status);
	
	public TB_KNOWLEDGE_REPO_CATEGORY_MSTR getRepoCategoryByid(int id);

	public String updateRepoCategory(TB_KNOWLEDGE_REPO_CATEGORY_MSTR td);




}
