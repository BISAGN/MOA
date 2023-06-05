package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Type_Of_Lecture_Master_CD_DAO {
	
public void Insert_Type_Of_Lecture_Master_Data(int id);
	
	public Boolean  Update_Type_Of_Lecture_Master_Data(int id, String type_of_content, String status,
			String modified_by, Date modified_date);
	
	public Boolean  Delete_Type_Of_Lecture_Master_Data(int id);

}
