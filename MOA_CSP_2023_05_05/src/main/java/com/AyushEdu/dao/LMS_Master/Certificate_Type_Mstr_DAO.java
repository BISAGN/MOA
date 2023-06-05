
package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_NCH_CERTIFICATE_TYPE_MSTR;

public interface Certificate_Type_Mstr_DAO {


	public TB_NCH_CERTIFICATE_TYPE_MSTR getProfessionalByid(int id);

	public String updateCertificate(TB_NCH_CERTIFICATE_TYPE_MSTR td);

	public List<Map<String, Object>> DataTableCertificateDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String certi_type, String status);

	public long DataTableCertificateDataTotalCount1(String search, String certi_type);

}


