package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Link_System_Elective_Course_Master_CD_DAO {
	
public void Insert_link_system_elective_course_Mstr_Data(int id);
	
	public Boolean  Update_link_system_elective_course_Mstr_Data(int id,int system_id, int elec_course_id,String modified_by, Date modified_date, int degree_id);
	
	public Boolean  Delete_link_system_elective_course_Mstr_Data(int id);

}
