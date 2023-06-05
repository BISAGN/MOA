package com.AyushEdu.Core_Directory;
import java.util.Date;

public interface Course_Publisher_Act_Inact_CD_DAO {
	
public void Insert_Course_Publisher_Act_Inact_Data(int id);
	
	public Boolean  Update_Course_Publisher_Act_Inact_Data(int id,String name, String status,String modified_by, Date modified_date);
	
	public Boolean  Delete_Course_Publisher_Act_Inact_Data(int id);
}