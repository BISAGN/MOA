package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface System_Master_CD_DAO {

	public void Insert_System_Mstr_Data(int id);
	public Boolean  Update_System_Mstr_Data(int id, String system_name,String system_abbr, String status
			, String modified_by, Date modified_dt);
	public Boolean  Delete_System_Mstr_Data(int id);
	
}
