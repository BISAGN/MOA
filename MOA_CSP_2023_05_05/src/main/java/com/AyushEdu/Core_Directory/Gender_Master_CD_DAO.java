package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Gender_Master_CD_DAO {
public void Insert_Gender_Master_Data(int id);
	
	public Boolean  Update_Gender_Master_Data(int id,String gender_name, String status, Date modified_date ,String modified_by);
	
	public Boolean  Delete_Gender_Master_Data(int id);
}
