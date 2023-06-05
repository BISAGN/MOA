package com.AyushEdu.dao.LMS_Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;


public interface Document_Attachments_Mstr_DAO {
	

	public List<Map<String, Object>> DataTableDocumentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String screen_module_id,String screen_submodule_id, String screen_id, String doc_name, String status);

	public long DataTableDocumentDataTotalCount(String search, String screen_module_id,String screen_submodule_id, String screen_id, String doc_name,String status);
	
	public EDU_DOC_ATTACHMENTS_MSTR getDocumentByid(int id);

	public String updateDocument(EDU_DOC_ATTACHMENTS_MSTR td);
	public ArrayList<ArrayList<String>> getsubmodule_name_FromScreen_Module(String screen_module_id);
	public ArrayList<ArrayList<String>> getScreen_NameFromScreen_SubModule(String screen_submodule_id);

	

//	public ArrayList<ArrayList<String>> getScreen_SubModuleFromScreen_Module(String screen_module_id);

}
