package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Set_Master_CD_DAO {

public void Insert_Set_Master_Data(int id);
	
	public Boolean  Update_Set_Master_Data(int id,String setname, String status,String modify_by, Date modify_date,String prof_name);
	public Boolean  Delete_Set_Master_Data(int id);
}
