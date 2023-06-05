package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Classroom_CD_DAO {
	public void Insert_classroom_Mstr_Data(int id);
	public Boolean  Update_classroom_Mstr_Data(int id, String classroom, int institute_id,int status, String modified_by,
			Date modified_date);
	public Boolean  Delete_classroom_Mstr_Data(int id);
}
