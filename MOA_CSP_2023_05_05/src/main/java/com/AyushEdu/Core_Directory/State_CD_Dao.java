package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface State_CD_Dao {
	
	
	public void Insert_State_Mstr_Data(int id);
	public Boolean  Update_State_Mstr_Data(int state_id, String state_name,int country_id,String state_abbr, String status
			, String modify_by, Date modify_date);
	public Boolean  Delete_State_Mstr_Data(int id);
	
	
}
