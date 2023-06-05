package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface District_CD_DAO {
	
public void Insert_District_Mstr_Data(int district_id);
	
	public Boolean  Update_District_Mstr_Data(int country_id,int state_id,String status,String district_name,String modify_by,Date modify_date,int id);
	
	public Boolean  Delete_District_Mstr_Data(int district_id);


}
