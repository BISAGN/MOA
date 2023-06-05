
package com.AyushEdu.dao.Collaboration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Collaboration.TB_COL_ORG_TYPE;


//import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;

public interface Collaboration_type_DAO {

	public TB_COL_ORG_TYPE getCollaborationtypetByid(int id);

	public String updateCollaborationtype(TB_COL_ORG_TYPE td);


	public List<Map<String, Object>> DataTableCollaborationtypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String collaboration_type, String status );

	public long DataTableCollaborationtypeDataTotalCount1(String Search, String collaboration_type, String status);

	public List<ArrayList<String>> getCollaboration_type_Report_Excel(String collaboration_type, String role,
			String string);
	


}
