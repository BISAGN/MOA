package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

public interface CC_Professional_Mstr_Dao {

	public List<Map<String, Object>> DataTableProfessionalDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String professional, String status);
	public long DataTableProfessionalDataTotalCount(String search, String professional,String status);
	public CC_TB_PROFESSIONAL_MSTR getProfessionalByid(int id);
	public String updateProfessional(CC_TB_PROFESSIONAL_MSTR td);
}
