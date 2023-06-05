package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface University_Type_CD_Dao {
	
	
	public void Insert_Uni_Type__Mstr_Data(int id);
	public Boolean  Update_Uni_Typet_Mstr_Data(int id ,String university_type
			,int status, String modified_by, Date modified_date);
	public Boolean  Delete_Uni_Type_Mstr_Data(int id);
	
	
}
