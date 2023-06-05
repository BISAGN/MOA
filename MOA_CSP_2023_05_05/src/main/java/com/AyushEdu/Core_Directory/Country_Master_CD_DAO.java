package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Country_Master_CD_DAO {
	
public void Insert_Country_Mstr_Data(int id);
	
	public Boolean  Update_Country_Mstr_Data(int id,String name,String modify_by, Date modify_date, String status);
	
	public Boolean  Delete_Country_Mstr_Data(int id);

}
