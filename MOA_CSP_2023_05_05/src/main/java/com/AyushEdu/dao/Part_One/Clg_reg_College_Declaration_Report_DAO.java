package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_reg_College_Declaration_Report_DAO {

	public List<Map<String, Object>> GetPrinacipal_Name(int id, int inst_id, String role);
	
	public List<Map<String, Object>> getDeclaration_UploadDocumentsFetch(String institute_id);

}
