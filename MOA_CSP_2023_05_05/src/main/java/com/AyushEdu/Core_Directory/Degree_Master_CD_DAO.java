package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Degree_Master_CD_DAO {

	public void Insert_Degree_Mstr_Data(int id);
	public Boolean  Update_Degree_Mstr_Data(int id, String type_of_degree, String degree_name, String semester, String degree_code, String status
			, String modified_by, Date modified_date);
	public Boolean  Delete_Degree_Mstr_Data(int id);
	
}
