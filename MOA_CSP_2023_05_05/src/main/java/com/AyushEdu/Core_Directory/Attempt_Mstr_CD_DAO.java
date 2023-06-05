package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Attempt_Mstr_CD_DAO {
public void Insert_Attempt_Master_Data(int id);
	
	public Boolean  Update_Attempt_Master_Data(int id,String attempt, int status,String modified_by, Date modified_date);
	
	public Boolean  Delete_Attempt_Master_Data(int id);
}
