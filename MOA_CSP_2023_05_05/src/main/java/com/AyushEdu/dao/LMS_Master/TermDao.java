package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_TERM_MASTER;

public interface TermDao {
	
	public EDU_LMS_TERM_MASTER gettermByid(int id);
	
	public List<Map<String, Object>> DataTabletermDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String term, String prof_name,String status);
	
	public long DataTabletermDataTotalCount(String search, String term, String prof_name);

}
