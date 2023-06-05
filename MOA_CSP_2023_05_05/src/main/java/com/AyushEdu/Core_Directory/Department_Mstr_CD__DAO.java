package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Department_Mstr_CD__DAO {
	public void Insert_Department_Mstr_Data(int id);
	public Boolean  Update_Department_Mstr_Data(int id, String Department
			,int status, String modified_by, Date modified_date);
	public Boolean  Delete_Department_Mstr_Data(int id);
	
}
