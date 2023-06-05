package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Attachment_Master_CD_Dao {
	public void Insert_Attachment_Mstr_Data(int id);
	public Boolean  Update_Attachment_Mstr_Data(int id,String doc_name,
			int status, String modified_by, Date modified_date);
	public Boolean  Delete_Attachment_Mstr_Data(int id);
}
