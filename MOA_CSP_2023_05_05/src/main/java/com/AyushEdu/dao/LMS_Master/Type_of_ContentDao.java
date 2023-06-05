package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_CONTENT_MSTR;

public interface Type_of_ContentDao {
	
	public List<Map<String, Object>> DataTabletype_of_contentDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_content, String status);

	public long DataTabletype_of_contentDataTotalCount(String Search,String type_of_content, String status);
	
	public EDU_LMS_TYPE_OF_CONTENT_MSTR gettype_of_contentByid(int id);

}
