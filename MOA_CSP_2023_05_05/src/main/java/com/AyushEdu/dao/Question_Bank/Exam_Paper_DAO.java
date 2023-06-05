package com.AyushEdu.dao.Question_Bank;

import java.util.ArrayList;

public interface Exam_Paper_DAO {
	
	public ArrayList<ArrayList<String>> getquestion(String course_id,String module_id);
	public ArrayList<ArrayList<String>> getattemptedQuizdata(int userid,String course_id);
	public ArrayList<ArrayList<String>> getcorrectanscheck(int quiz_id);
	public ArrayList<ArrayList<String>> getcoursename();
	public ArrayList<ArrayList<String>> getselectedcoursename(int courseid);
	public ArrayList<ArrayList<String>> getselectedsetname(int setid);
	public ArrayList<ArrayList<String>> getSelectedCourseSetbyStudent(String userid) ;
	public ArrayList<ArrayList<String>> getInstLogo(int userid);
	public ArrayList<ArrayList<String>> getModulelistFromcourse(String course_id,String user_id);
//	public ArrayList<ArrayList<String>> getExamlistFromModule(String set_id, String course_id, String module_id) ;
	public ArrayList<ArrayList<String>> getStudentCourseListData(String userid);
//	public ArrayList<ArrayList<String>> getsetlistFromCourse(String ele_course_id,String user_id );
	
	public ArrayList<ArrayList<String>> coursenamelist(String userid,String module_id);
	public ArrayList<String> method2(String userid, String course_id, String sequence);
	public ArrayList<String> getaayush_idby_uid(Integer userid);

	
	public ArrayList<ArrayList<String>> papersolution(String course_id,String module_id,String userid);
	public ArrayList<ArrayList<String>> getquestion();
	public ArrayList<ArrayList<String>> coursenamelistofResult(String userid) ;
	
}
