package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Religion_Master_CD_DAO {
	
public void Insert_Religion_Master_Data(int id);
	
	public Boolean  Update_Religion_Master_Data(int id,String religion, String status,String modified_by, Date modified_date);
	
	public Boolean  Delete_Religion_Master_Data(int id);

}
