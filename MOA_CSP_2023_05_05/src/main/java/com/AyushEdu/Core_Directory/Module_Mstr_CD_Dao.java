package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Module_Mstr_CD_Dao {

	public void Insert_Module_Mstr_Data(int id);
	public Boolean  Update_Module_Mstr_Data(int id, String module_name, int system_id, int degree_id, int course_id, String status
			, String modified_by, Date modified_date);
	public Boolean  Delete_Module_Mstr_Data(int id);
	
}
