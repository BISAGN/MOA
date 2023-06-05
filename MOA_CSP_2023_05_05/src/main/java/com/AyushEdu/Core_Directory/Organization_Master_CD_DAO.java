package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Organization_Master_CD_DAO {
	
public void Insert_Organization_Master_Data(int id);
	
	public Boolean  Update_Organization_Master_Data(int id,String organization,String modified_by, Date modified_date,String status);
	
	public Boolean  Delete_Organization_Master_Data(int id);


}
