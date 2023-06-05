package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Marital_Status_CD_DAO {
	
public void Insert_Marital_Status_Data(int id);
	
	public Boolean  Update_Marital_Status_Data(int id,String marital_status, String status ,String modified_by, Date modified_date);
	
	public Boolean  Delete_Marital_Status_Data(int id);

}
