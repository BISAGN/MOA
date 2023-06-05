package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Course_Master_CD_DAO {
	
	public void Insert_Course_Mstr_Data(int id);
	
	public Boolean  Update_Course_Mstr_Data(int id,String course_name, String status,String modified_by, Date modified_date, String course_code, int type_of_content_id);
	
	public Boolean  Delete_Courser_Mstr_Data(int id);
}
