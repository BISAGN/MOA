package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;

public interface Registration_Type_DAO {


	public List<Map<String, Object>> DataTableRegistrationDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String reg_type, String status);

	public long DataTableRegistrationDataTotalCount1(String search, String reg_type);
	
	public TB_NCH_REGISTRATION_TYPE_MSTR getProfessionalByid(int id);

	public String updateProfessional(TB_NCH_REGISTRATION_TYPE_MSTR td);

}

