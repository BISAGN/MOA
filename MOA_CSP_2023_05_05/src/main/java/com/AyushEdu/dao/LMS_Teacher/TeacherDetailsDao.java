package com.AyushEdu.dao.LMS_Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TeacherDetailsDao {
	
	public ArrayList<ArrayList<String>> getTypelistFromDocDataList(String doc_name);
	public ArrayList<ArrayList<String>> getinstitutelistbyStudent(int institute_id) ;
	public List<Map<String, Object>> DataTableTeacher_dtlDataList(int startPage, int pageLength, String Search,String orderColunm, String orderType, 
			String ayush_id, String teacher_code,String first_name, String gender, String date_of_birth,String per_state,String per_district,String per_village,
			String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,String yr_of_exp,String institute,String university,String status);
	
	public long DataTableTeacher_dtlDataTotalCount(String Search,String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,
			String per_state,String per_district,String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,
			String role,String userid,String yr_of_exp,String institute,String university,String status);	
	
	public ArrayList<ArrayList<String>> getchild_idByp_id(int id);
	public ArrayList<ArrayList<String>> getchild_idByp_idDegree(int id);
	
	public ArrayList<ArrayList<String>> getaddmoredata1(int updateid);
	public ArrayList<ArrayList<String>> getaddmoredata2(int updateid);
	public ArrayList<ArrayList<String>> getaddmoredata3(int updateid);////////Riddhi_18_7_22
	
	public ArrayList<ArrayList<String>> View_getaddmoredata2(int updateid);
	
	public String getFilePathQueryForUpload_file(String id);
	public String getFilePathQueryForDoc(String id);
	
	public List<Map<String,Object>> getAllPersdetails(int userid);
	
	public List<Map<String,Object>> getTeacherchild(int userid);
	
	public List<Map<String,Object>> getdegreeandsupportchild(int userid);
	
	public ArrayList<ArrayList<String>> getfacultyList(int fID);
	public ArrayList<ArrayList<String>> getpreviousexperiancelist(int pID);
	public ArrayList<ArrayList<String>> getdatalist(int fID);  //teacherdetails-pf------tushar_11_07_22
	
	public ArrayList<ArrayList<String>> getmedicalpdf(int pId);
	
	public ArrayList<ArrayList<String>> getothermedicalpdf(int pId);
	
	//----rahul_11_07_22
	public ArrayList<ArrayList<String>> getUniversitylist();
	
	public ArrayList<ArrayList<String>> getinstitutelist(String userid);
	
	public ArrayList<ArrayList<String>> getUniversity_id(String userid,String rolename);
	
	public ArrayList<ArrayList<String>> getPrincipal_id(String userid,String rolename);
	
	public ArrayList<ArrayList<String>> getqualificationchildAttchment(String userid); /////////Rahul_19_7_22
	
	public ArrayList<ArrayList<String>> getSubFromCourse(String course);
	
	public ArrayList<ArrayList<String>> getaddmoredata4(int updateid);
	
	public ArrayList<ArrayList<String>> getotherqualisubchild(int main_id);

	public ArrayList<ArrayList<String>> getotherquali_chlist(int updateid);
	public ArrayList<ArrayList<String>> getcoursebytypeOfDegreeList(String typeofdegree);
	public ArrayList<ArrayList<String>> getPopup_Datalistquali(String userid,String parent_id);
	
	public String getFilePathQueryForDocAttSub(String id);
	
	public ArrayList<ArrayList<String>> getuniversityname(int id) ;
	public ArrayList<ArrayList<String>> getaddmoredata5(int updateid) ;
	public ArrayList<ArrayList<String>> getaddmoredataforview1(int updateid);
	
	public ArrayList<ArrayList<String>> getPopup_Datalistother(String userid,String parent_id);
	
	public String getFilePathQueryForDocAttSubother(String id) ;
	
	public List<Map<String, Object>> DataTableTeacher_dtl_princi_DataList(int startPage, int pageLength, String Search,String orderColunm, String orderType, 
			String ayush_id, String teacher_code,String first_name, String gender, String date_of_birth,String per_state,String per_district,String per_village,
			String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,String yr_of_exp,String institute,String university,String status,String inst_id);
	
	public long DataTableTeacher_dtl_princi_DataTotalCount(String Search,String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,
			String per_state,String per_district,String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,
			String role,String userid,String yr_of_exp,String institute,String university,String status,String inst_id);	
	
	
	public ArrayList<ArrayList<String>> getlogininformation(String role,int userid);
	
	public ArrayList<ArrayList<String>> getregistrationViewdata(int mainid);
	
	public ArrayList<ArrayList<String>> getacademicexp(int userid) ;
	
	public ArrayList<ArrayList<String>> getacademicexpView(int userid);
	
	public ArrayList<ArrayList<String>> getacademicexpViewforPDF(int userid); 
	
}
