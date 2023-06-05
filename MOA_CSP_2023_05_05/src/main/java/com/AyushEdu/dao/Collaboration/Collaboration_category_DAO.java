
package com.AyushEdu.dao.Collaboration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Collaboration.TB_COL_ORG_CATEGORY;


//import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;

public interface Collaboration_category_DAO {

	public TB_COL_ORG_CATEGORY getCollaborationcategorytByid(int id);

	public String updateCollaborationcategory(TB_COL_ORG_CATEGORY td);


	public List<Map<String, Object>> DataTableCollaborationcategoryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String collab_cate, String status );

	public long DataTableCollaborationcategoryDataTotalCount1(String Search, String collab_cate, String status);

	public ArrayList<ArrayList<String>> getCollaboration_category_Report_Excel(String collab_cate, String role1,
			String string);
	


}
