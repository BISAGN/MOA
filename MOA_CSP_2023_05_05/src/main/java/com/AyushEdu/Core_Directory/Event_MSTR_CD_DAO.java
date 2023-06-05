package com.AyushEdu.Core_Directory;
import java.util.Date;

public interface Event_MSTR_CD_DAO {
	public void Insert_event_Mstr_Data(int id);
	public Boolean  Update_event_Mstr_Data(int id, String event_name,  Date event_date,int holiday, String modified_by, Date modified_date);
	public Boolean  Delete_event_Mstr_Data(int id);
}
