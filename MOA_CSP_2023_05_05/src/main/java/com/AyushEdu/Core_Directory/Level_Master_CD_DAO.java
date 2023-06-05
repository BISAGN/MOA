package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Level_Master_CD_DAO {
	
public void Insert_Level_Master_Data(int id);
	
	public Boolean  Update_Level_Master_Data(int id,String level_name, String status,String modified_by, Date modified_date, String sequence_name);
	
	public Boolean  Delete_Level_Master_Data(int id);

}
