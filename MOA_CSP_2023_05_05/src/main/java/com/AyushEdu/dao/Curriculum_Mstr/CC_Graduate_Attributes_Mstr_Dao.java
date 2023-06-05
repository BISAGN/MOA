package com.AyushEdu.dao.Curriculum_Mstr;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GRADUATE_ATTRIBUTES_MSTR;

public interface CC_Graduate_Attributes_Mstr_Dao {

	public List<Map<String, Object>> DataTableGraduate_AttributesDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String code, String graduate_attributes, String status);
	public long DataTableGraduate_AttributesDataTotalCount(String Search,String code, String graduate_attributes, String status);
	public String updateGraduate_Attributes(CC_TB_GRADUATE_ATTRIBUTES_MSTR td);

}
