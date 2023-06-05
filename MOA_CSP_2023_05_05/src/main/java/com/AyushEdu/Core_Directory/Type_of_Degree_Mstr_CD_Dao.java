package com.AyushEdu.Core_Directory;

import java.util.Date;
public interface Type_of_Degree_Mstr_CD_Dao {
	public void Insert_Type_Of_Degree_Mstr_Data(int id);
	public Boolean  Update_Type_Of_Degree_Mstr_Data(int id, String type_of_degree
			,int status, String modified_by, Date modified_date);
	public Boolean  Delete_Type_Of_Degree_Mstr_Data(int id);
}
