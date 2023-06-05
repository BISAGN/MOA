package com.AyushEdu.Core_Directory;

import java.util.Date;
public interface  Subject_CD_DAO {
	public void Insert_subject_Mstr_Data(int id);
	public Boolean  Update_subject_Mstr_Data(int id, int system_id,int course_id,String subject_name,String status, String modified_by,
			Date modified_date);
	public Boolean  Delete_subject_Mstr_Data(int id);
}
