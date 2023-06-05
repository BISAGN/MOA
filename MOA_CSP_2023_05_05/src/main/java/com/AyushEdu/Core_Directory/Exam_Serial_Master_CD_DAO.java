package com.AyushEdu.Core_Directory;
import java.util.Date;
public interface Exam_Serial_Master_CD_DAO {
	
public void Insert_Exam_Serial_Data(int id);
	
	public Boolean  Update_Exam_Serial_Data(int id,int exam_serial, int status,String modified_by, Date modified_date);
	
	public Boolean  Delete_Exam_Serial_Data(int id);

}
