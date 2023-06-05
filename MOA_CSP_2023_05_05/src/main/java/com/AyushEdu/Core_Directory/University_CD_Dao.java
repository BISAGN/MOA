package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface University_CD_Dao {

	public void Insert_University_Mstr_Data(int id);
	public Boolean  Update_University_Mstr_Data(int id, String university_name, String university_code, String address, int country_id,int state_id,int district_id,String city_name,int university_type,int organization_id,int system_id
			,String status, String modified_by, Date modified_date);
	public Boolean  Delete_University_Mstr_Data(int id);
}
