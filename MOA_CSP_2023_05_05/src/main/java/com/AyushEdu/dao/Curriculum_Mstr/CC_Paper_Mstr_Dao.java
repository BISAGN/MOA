package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PAPER_MASTER;



public interface CC_Paper_Mstr_Dao {

	public List<Map<String, Object>> DataTablePaperDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String paper, String status);

	public long DataTablePaperDataTotalCount(String search, String paper, String status);
	
	public CC_TB_PAPER_MASTER getPaperByid(int id);

	public String updatePaper(CC_TB_PAPER_MASTER td);



}
