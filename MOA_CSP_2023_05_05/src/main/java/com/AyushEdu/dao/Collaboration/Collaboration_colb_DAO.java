
package com.AyushEdu.dao.Collaboration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Collaboration.TB_COL_ORG_COLB;


//import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;

public interface Collaboration_colb_DAO {

	public TB_COL_ORG_COLB getCollaborationcolbtByid(int id);

	public String updateCollaborationcolb(TB_COL_ORG_COLB td);


	public List<Map<String, Object>> DataTableCollaborationcolbDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date );

	public long DataTableCollaborationcolbDataTotalCount1(String Search, String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date);
	

	 public ArrayList<ArrayList<String>> DataOfCollab();
}
