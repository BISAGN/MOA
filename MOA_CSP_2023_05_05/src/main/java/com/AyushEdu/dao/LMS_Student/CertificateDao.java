package com.AyushEdu.dao.LMS_Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CertificateDao {
	
	
	public ArrayList<ArrayList<String>> getpercentage(int userid);
	public ArrayList<ArrayList<String>> getcredit(int userid, int course_id);
//	public ArrayList<ArrayList<String>> getuserList(int userid, int module_id,String exam_name );

}
