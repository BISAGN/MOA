package com.AyushEdu.dao.Enhancement_Of_Research;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;

public interface Advance_Search_DAO {
	
	public List<Map<String,Object>> Advance_Enhance_Research_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status);
	//String search_field,  String search_field,
	
	public long DataTotalAdvance_Enhance_ResearchCount(String search, String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status);
	
	public TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS getAdvance_SearchByid(int id);

	public ArrayList<ArrayList<String>> getFilterAdvance_Enhance_Research_dataSearch(HttpSession session,String category,String institute_name,String medicine_system);
	
	public ArrayList<ArrayList<String>> getFilterAdvance_Enhance_Research_GlobalSearch(String searchstring);
	public ArrayList<ArrayList<String>> getInstituteBy_systemList(String medicine_system);

}
