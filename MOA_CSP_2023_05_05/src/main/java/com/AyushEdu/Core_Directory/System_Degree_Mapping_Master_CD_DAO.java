package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface System_Degree_Mapping_Master_CD_DAO {
	
public void Insert_System_Degree_Mapping_Mstr_Data(int id);
	
	public Boolean  Update_System_Degree_Mapping_Mstr_Data(int id,int degree_name, int system_name,String status,String modified_by, Date modified_date);
	
	public Boolean  Delete_System_Degree_Mapping_Mstr_Data(int id);

}
