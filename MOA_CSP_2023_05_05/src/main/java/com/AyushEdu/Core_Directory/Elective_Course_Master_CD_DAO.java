package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Elective_Course_Master_CD_DAO {

	public void Insert_Ele_Course_Mstr_Data(int id);
	public Boolean  Update_Ele_Course_Mstr_Data(int id,String  course_name,String course_description,String upload_img,String degree_id,String semester_id,String demo_video, String status
				, String modified_by, Date modified_date);
	public Boolean  Delete_Ele_Coursee_Mstr_Data(int id);
}
