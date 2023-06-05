package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;

@Repository

public class University_Enrollment_DaoImpl implements  University_Enrollment_Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

@Override
public ArrayList<ArrayList<String>> getUniversityEnrollmentDetails(int userid) {
	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
        String sq1="select * from edu_university_enrollment_mstr where userid = ?";                         
        
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, userid);
        ResultSet rs = stmt.executeQuery(); 
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("university_enrollment_formate"));// 0
			alist.add(list);     
        }
        rs.close();
        stmt.close();
        conn.close();
   }catch(SQLException e){
	   e.printStackTrace();
   }       
    
   return alist;
}

@Override
public List<Map<String, Object>> getAdmissionFeepaidstudentListForunienrollReport(String academic_year,
		String system_id, String degree_id, String institute_id, String university_id,String Staff_lvl) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String qry = "";
	String q = "";

	try {
		System.out.println("academic_year ::: " + academic_year);
		System.out.println("degree_id ::: " + degree_id);
		System.out.println("system_id ::: " + system_id);
		System.out.println("institute_id ::: " + institute_id);
		System.out.println("university_id ::: " + university_id);

		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		if (academic_year != "" && academic_year != null && academic_year != "null" && !academic_year.equals("")) {
			qry += " and upper(academic_year) like  ? ";
		}

		if (system_id.trim() != "" && system_id.trim() != null && system_id.trim() != "null" && !system_id.trim().equals("")) {
			qry +=  "and cast(sd.system as character varying) = ? " ;
		}

		if (degree_id.trim() != ""  && degree_id.trim() != null && degree_id.trim() != "null" && !degree_id.trim().equals("")) {
			qry += "   and cast(sd.degree as character varying) = ? ";
		}

		if (institute_id.trim() != "" && institute_id.trim() != null && institute_id.trim() != "null" && !institute_id.trim().equals("")) {
			qry += "and cast(sd.institude_userid as character varying) = ? ";
		}
		
		if (university_id != "" && university_id != null && university_id != "null"
				&& !university_id.equals("")) {
			qry += " and cast(sd.university_userid as character varying) = ?";
		}
		
		if (Staff_lvl.toUpperCase().equals("NCH")) {

			if (qry == "") {

				q = " select \n"
						+ "  INITCAP((pd.pers_name ||' '||  pd.pers_surname)) as student_name,TO_CHAR(pd.pers_date_of_birth , 'DD-MM-YYYY') as pers_date_of_birth,pd.pers_email,pd.pers_aadhar_no,\n"
						+ "	 sd.id as sd_id,sd.ayush_id,sd.university_enrollment_no,dm.degree_name,ir.institute_name\n"
						+ "from edu_reg_gradu_personal_dtls pd\n"
						+ "inner join edu_lms_nch_student_details sd on sd.email= pd.pers_email\n"
						+ "inner join edu_lms_institute_reg ir on ir.id= sd.institude_userid\n"
						+ "inner join edu_lms_university_mstr um on um.id = ir.university_id \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id = sd.degree \n"
						+ "where pd.id!=0 and  pd.status = 1 and verified_status = 1  ";
				
			} else {
				
				q = " select \n"
						+ "  INITCAP((pd.pers_name ||' '||  pd.pers_surname)) as student_name,TO_CHAR(pd.pers_date_of_birth , 'DD-MM-YYYY') as pers_date_of_birth,pd.pers_email,pd.pers_aadhar_no,\n"
						+ "	 sd.id as sd_id,sd.ayush_id,sd.university_enrollment_no,dm.degree_name,ir.institute_name\n"
						+ "from edu_reg_gradu_personal_dtls pd\n"
						+ "inner join edu_lms_nch_student_details sd on sd.email= pd.pers_email\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=  sd.institude_userid\n"
						+ "inner join edu_lms_university_mstr um on um.id = ir.university_id \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id = sd.degree \n"
						+ "where pd.id!=0 and  pd.status = 1 and verified_status = 1 " + qry;
			}
		} else if (Staff_lvl.toUpperCase().equals("NCISM")) {

			if (qry == "") {

				q = " select \n"
						+ "  INITCAP((pd.pers_name ||' '||  pd.pers_surname)) as student_name,TO_CHAR(pd.pers_date_of_birth , 'DD-MM-YYYY') as pers_date_of_birth,pd.pers_email,pd.pers_aadhar_no,\n"
						+ "	 sd.id as sd_id,sd.ayush_id,sd.university_enrollment_no,dm.degree_name,ir.institute_name\n"
						+ "from edu_ncism_reg_gradu_personal_dtls pd\n"
						+ "inner join edu_lms_student_details sd on sd.email= pd.pers_email\n"
						+ "inner join edu_lms_institute_reg ir on ir.id= sd.institude_userid\n"
						+ "inner join edu_lms_university_mstr um on um.id = ir.university_id \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id = sd.degree \n"
						+ "where pd.id!=0 and  pd.status = 1 and verified_status = 1  ";
				
			} else {
				
				q = " select \n"
						+ "  INITCAP((pd.pers_name ||' '||  pd.pers_surname)) as student_name,TO_CHAR(pd.pers_date_of_birth , 'DD-MM-YYYY') as pers_date_of_birth,pd.pers_email,pd.pers_aadhar_no,\n"
						+ "	 sd.id as sd_id,sd.ayush_id,sd.university_enrollment_no,dm.degree_name,ir.institute_name\n"
						+ "from edu_ncism_reg_gradu_personal_dtls pd\n"
						+ "inner join edu_lms_student_details sd on sd.email= pd.pers_email\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=  sd.institude_userid\n"
						+ "inner join edu_lms_university_mstr um on um.id = ir.university_id \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id = sd.degree \n"
						+ "where pd.id!=0 and  pd.status = 1 and verified_status = 1 " + qry;
			}
		}
		
		
		

		stmt = conn.prepareStatement(q);

		int j = 1;

		if (academic_year != "" && academic_year != null && academic_year != "null" && !academic_year.equals("")) {
			stmt.setInt(j, Integer.parseInt(academic_year));
			j += 1;
		}

		if (system_id.trim() != "" && system_id.trim() != null && system_id.trim() != "null" && !system_id.trim().equals("")) {
			stmt.setString(j,  system_id.toUpperCase() );
			j += 1;
		}
		
		if (degree_id.trim() != ""  && degree_id.trim() != null && degree_id.trim() != "null" && !degree_id.trim().equals("")) {
			stmt.setString(j,  degree_id.toUpperCase());
			j += 1;
		}
		if (institute_id.trim() != "" && institute_id.trim() != null && institute_id.trim() != "null" && !institute_id.trim().equals("")) {
			stmt.setString(j,  institute_id.toUpperCase() );
			j += 1;
		}
		
		if (university_id.trim() != "" && university_id.trim() != null && university_id.trim() != "null" && !university_id.trim().equals("")) {
			stmt.setString(j, university_id.toUpperCase());
			j += 1;
		}
		
		
		
		System.err.println("stmt------------>   "+stmt);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();
		int countFunctionview = 1;
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			String f1 = "";
			String ul = "";
			String action = "";

			ul += "<ul class='buttons-group mainbtn action daobtn'>";
			f1 = "<li><a class='main-btn dark-btn btn-hover btn-sm veiwOnclick' value='VIEW' title='View Data' >"
					+ "<input type='hidden' id='viewID" + countFunctionview + "' value=" + rs.getObject(3)
					+ "><i class='lni lni-eye'></i></a> </li>";
			ul += f1;
			ul += "</ul>";
			
			String val="";
			String enroll="";
			System.out.println("rs.getObject(5)--------->   "+rs.getObject(7));
			
		 if (rs.getObject(7) != null  && rs.getObject(7) !="null" &&  !rs.getObject(7).equals("")) {
			 
			 val = (String) rs.getObject(7);
			 enroll  =	"  <input class='form-control-plaintext'  id='university_enrollment" + rs.getObject(5) + "'  name='university_enrollment"+ rs.getObject(5) + "' value='"+val+"' readonly='readonly' >";
		}else {
			 enroll =	"<input  id='university_enrollment" + rs.getObject(5) + "'    name='university_enrollment"+ rs.getObject(5) + "' value=''>";	
		}	
			action = ul;
			countFunctionview += 1;

			columns.put("enroll", enroll);

			list.add(columns);
			
		}
		rs.close();
		stmt.close();
		conn.close();

	} catch (SQLException e) {
		// throw new RuntimeException(e);
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	System.err.println("list---------->   " + list);
	return list;
}
		public String getMaxUniEnrollmentID(String userid,String staff_lvl) {
		
			Connection conn = null;
			String reg_no = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query =""; 
				
				if (staff_lvl.toUpperCase().equals("NCH")) {
					query =	"select to_char(CURRENT_TIMESTAMP,'yyyy')||'/'||upper(university_code)||'/'||lpad((select case when (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_nch_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%')='' or (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_nch_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%') is null  then '0' else (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_nch_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%') end::int+1)::text, 5, '0')as max from logininformation l\n"
							+ "inner join edu_lms_university_mstr um on um.id = l.university_id and userid = ? limit 1";
				}else if (staff_lvl.toUpperCase().equals("NCISM")) {
					query =	"select to_char(CURRENT_TIMESTAMP,'yyyy')||'/'||upper(university_code)||'/'||lpad((select case when (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%')='' or (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%') is null  then '0' else (select max(split_part(university_enrollment_no,'/', 3))\n"
							+ "from edu_lms_student_details where upper(university_enrollment_no) like '%'|| upper(university_code) ||'%') end::int+1)::text, 5, '0')as max from logininformation l\n"
							+ "inner join edu_lms_university_mstr um on um.id = l.university_id and userid = ? limit 1";
				}
				
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(userid));
				
				ResultSet rs = stmt.executeQuery();
		
				while (rs.next()) {
					reg_no = rs.getString("max");
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reg_no;
		}

		@Override
		public String getUpdateuniversityenrolmentno(String sd_id, String maxUEID, String staff_lvl) {
			
			Connection conn = null;
			String msg = "";
			try {
				System.err.println("String sd_id->  "+sd_id+"   String maxUEID  "+ maxUEID  +"   stafflevel  "+ staff_lvl);
				
				Session sessionHQL = this.sessionFactory.openSession();
				conn = dataSource.getConnection();
				Transaction tx = sessionHQL.beginTransaction();
				
				if (staff_lvl.toUpperCase().equals("NCH")) {
					
					EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
					
					System.err.println("sd.getUniversity_enrollment_no()--->     "+sd.getUniversity_enrollment_no());
					
					if (sd.getUniversity_enrollment_no() == "" || sd.getUniversity_enrollment_no() == "null"  ||  sd.getUniversity_enrollment_no() == null) {
						sd.setUniversity_enrollment_no(maxUEID);
					}
					
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
					
				}
				else if (staff_lvl.toUpperCase().equals("NCISM")) {
					
					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
					
					if (sd2.getUniversity_enrollment_no() == "" || sd2.getUniversity_enrollment_no() == "null"  ||  sd2.getUniversity_enrollment_no() == null) {
						sd2.setUniversity_enrollment_no(maxUEID);
					}
					
					sessionHQL.update(sd2);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
				}

				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return msg;
		}

		
		public String getUpdateuniversityenrolmentnoasaayushid(String sd_idayu, String staff_lvl) {

			Connection conn = null;
			String msg = "";
			try {
				
				System.err.println("String sd_id->  "+sd_idayu+"    stafflevel  "+ staff_lvl);
				
				Session sessionHQL = this.sessionFactory.openSession();
				conn = dataSource.getConnection();
				Transaction tx = sessionHQL.beginTransaction();
				
				if (staff_lvl.toUpperCase().equals("NCH")) {
					
					EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_idayu));
					
					System.err.println("sd.getUniversity_enrollment_no()--->     "+sd.getUniversity_enrollment_no());
					
					if (sd.getUniversity_enrollment_no() == "" || sd.getUniversity_enrollment_no() == "null"  ||  sd.getUniversity_enrollment_no() == null) {
						sd.setUniversity_enrollment_no(sd.getAyush_id());
					}
					
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
					
				}
				else if (staff_lvl.toUpperCase().equals("NCISM")) {
					
					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_idayu));
					
					if (sd2.getUniversity_enrollment_no() == "" || sd2.getUniversity_enrollment_no() == "null"  ||  sd2.getUniversity_enrollment_no() == null) {
						sd2.setUniversity_enrollment_no(sd2.getAyush_id());
					}
					
					sessionHQL.update(sd2);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
				}

				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return msg;
		}

		@Override
		public String getUpdateuniversityenrolmentnomanualid(String sd_idman, String university_enrollment,
				String staff_lvl) {
			
			Connection conn = null;
			String msg = "";
			try {
				
				System.err.println("String sd_idman->  "+sd_idman+"  uni  "+university_enrollment+"   stafflevel  "+ staff_lvl);
				
				Session sessionHQL = this.sessionFactory.openSession();
				conn = dataSource.getConnection();
				Transaction tx = sessionHQL.beginTransaction();
				
				if (staff_lvl.toUpperCase().equals("NCH")) {
					EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_idman));
					
					System.err.println("sd.getUniversity_enrollment_no()--->     "+sd.getUniversity_enrollment_no());
					
//					if (sd.getUniversity_enrollment_no() == "" || sd.getUniversity_enrollment_no() == "null"  ||  sd.getUniversity_enrollment_no() == null) {
						sd.setUniversity_enrollment_no(university_enrollment);
//					}
					
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
					
				}
				else if (staff_lvl.toUpperCase().equals("NCISM")) {
					
					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_idman));
					
//					if (sd2.getUniversity_enrollment_no() == "" || sd2.getUniversity_enrollment_no() == "null"  ||  sd2.getUniversity_enrollment_no() == null) {
						sd2.setUniversity_enrollment_no(university_enrollment);
//					}
					
					sessionHQL.update(sd2);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();

//					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL
//							.get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(sd_id));
//
//					String status = sd2.getUniversity_enrollment_no();
//
//					System.err.println("status---" + status);
//
//					if (status != "") {
						msg = "University Enrollment No Saved Successfully";
//						
//					} else {
//						msg = "Admission Not Verify";
//					}
				}

				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return msg;
			
		}

	
}
