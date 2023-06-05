package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Document_Attachments_CD_DAO {
public void Insert_Document_Attachments_Data(int id);
	
//	public Boolean  Update_Document_Attachments_Data(int id,String doc_name, int status,String modified_by, Date modified_date,int screen_id,int screen_module_id,int screen_submodule_id);
	
	public Boolean  Delete_Document_Attachments_Data(int id);

	public Boolean Update_Document_Attachments_Data(int id, int screen_module_id, int screen_id, String doc_name,
			int status, String modified_by, Date modified_date);


}
