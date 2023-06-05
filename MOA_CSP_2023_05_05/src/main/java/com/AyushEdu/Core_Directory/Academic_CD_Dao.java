package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Academic_CD_Dao {
	public void Insert_Academic_Mstr_Data(int id);
	public Boolean  Update_Academic_Mstr_Data(int id, String academic_details_name, String status, String modified_by,
			Date modified_dt);
	public Boolean  Delete_Academic_Mstr_Data(int id);
}
