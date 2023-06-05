
package com.AyushEdu.dao.Collaboration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Collaboration.TB_COL_ORG_SECTOR;

//import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;

public interface Collaboration_sector_DAO {

	public TB_COL_ORG_SECTOR getCollaborationsectortByid(int id);

	public String updateCollaborationsector(TB_COL_ORG_SECTOR td);


	public List<Map<String, Object>> DataTableCollaborationsectorDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String collaboration_type, String status );

	public long DataTableCollaborationsectorDataTotalCount1(String Search, String collaboration_type, String status);

	public List<ArrayList<String>> getCollaboration_sector_Report_Excel(String sector_type, String role, String string);
	


}
