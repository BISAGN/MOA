package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR;




public interface Add_Non_Lecture_Activities_Mstr_DAO {
	public List<Map<String, Object>> DataTableTeaching_learning_methodDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String teaching_learning_method, String status);

	public long DataTableTeaching_learning_methodDataTotalCount(String search, String teaching_learning_method,String status);
	
	public EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR getTeaching_learning_methodByid(int id);


	public String updateTeaching_learning_method(EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR td);

}
