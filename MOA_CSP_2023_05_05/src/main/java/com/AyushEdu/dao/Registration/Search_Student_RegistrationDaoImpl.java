package com.AyushEdu.dao.Registration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Search_Student_RegistrationDaoImpl implements Search_Student_RegistrationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	

	@Override
	public ArrayList<ArrayList<String>> getUniversitylist() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q=" select userid,login_name from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
					+ "	inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("userid"));// 0
				list.add(rs.getString("login_name"));// 1
				alist.add(list);
			}
			rs.close();
			stmt.close();
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
		return alist;
	}

	@Override
	public ArrayList<ArrayList<String>> getinstitutelist(String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			q="select DISTINCT ir.id,ir.institute_name\n"
					+ "from logininformation l \n"
					+ "inner join edu_lms_institute_reg ir on ir.id = l.institute_id and status= '1' and app_status= '1'\n"
					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
					+ "inner join roleinformation ro on ro.role_id=ul.role_id 	and role='Institute_NCH' ";
			
//			q="select userid,login_name from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
//					+ "	inner join roleinformation ro on ro.role_id=ul.role_id \n"
//					+ "	and role='Institute_NCH' and university_id=(select university_id from logininformation where userid=? )";

		    	stmt = conn.prepareStatement(q);
				//stmt.setInt(1, Integer.parseInt(userid));
				
				System.err.println("----getinstitutelist-------------------"+stmt);
		    	ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("institute_name").replaceAll("\\n", ""));// 1
				alist.add(list);
			}
			rs.close();
			stmt.close();
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
		return alist;
	}
	
	@Override
	public List<Map<String, Object>> DataTableSearch_Stu_RegDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, university_id, institute_id,name,ayush_id,gender,date_of_birth);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			q=" select pd.id, upper(sd.ayush_id)as ayush_id,ir.institute_name,um.university_name,pd.pers_name,pd.pers_surname,pd.pers_father_name, pd.pers_mother_name,g.gender_name,\n"
					+ "TO_CHAR(pd.pers_date_of_birth , 'DD-MON-YYYY') as pers_date_of_birth,cm1.category,\n"
					+ "r1.religion,ms.marital_status,c1.name as nationality ,s.state_name,d.district_name,\n"
					+ "pd.neet_rank,pd.neet_marks,pd.neet_percentile,sd.university_enrollment_no from edu_reg_gradu_personal_dtls pd\n"
					+ "inner join edu_lms_state_mstr s on s.state_id= pd.state_id::int\n"
					+ "inner join edu_lms_country_mstr c1 on c1.id = pd.pers_nationality\n"
					+ "inner join edu_lms_district_mstr d on d.district_id= pd.district_id::int\n"
					+ "inner join edu_lms_gender_mstr g on g.id= pd.pers_gender::int\n"
					+ "inner join logininformation l on l.userid= pd.p_id\n"
					+ "inner join edu_lms_nch_student_details sd on sd.email= l.email_id \n"
					+ "inner join tb_lms_category_mstr cm1 on cm1.id = pd.pers_category::int  \n"
					+ "inner join edu_lms_religion_mstr r1 on r1.id = pd.pers_religion::int  \n"
					+ "inner join edu_lms_marital_status_mstr ms on ms.id = pd.pers_marital_status::int  \n"
					+ "inner join edu_lms_institute_reg ir on ir.id=  sd.institude_userid\n"
					+ "inner join edu_lms_university_mstr um on um.id = ir.university_id\n where pd.id!=0 and  pd.status = 1 and verified_status = 1"
					+ SearchValue + " ORDER BY pd.id "
					+ orderType + " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL1(stmt, Search,university_id, institute_id,name,ayush_id,gender,date_of_birth);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countview=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String vd = "";
				String action = "";
				
						vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm VIEWdetails' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				
				countview += 1;
				
				action = vd;
				
				columns.put("action", action);
				
				list.add(columns);
			}
			rs.close();
			stmt.close();
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
		return list;
	}
	
	@Override
	public long DataTableSearch_Stu_RegDataTotalCount(String Search, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, university_id, institute_id,name,ayush_id,gender,date_of_birth);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			q = "select count(*) from ( select pd.id, upper(sd.ayush_id) as ayush_id,ir.institute_name,um.university_name,pd.pers_name,pd.pers_surname,pd.pers_father_name, pd.pers_mother_name,g.gender_name,\n"
					+ "TO_CHAR(pd.pers_date_of_birth , 'DD-MON-YYYY') as pers_date_of_birth,cm1.category,\n"
					+ "r1.religion,ms.marital_status,c1.name as nationality ,s.state_name,d.district_name,\n"
					+ "pd.neet_rank,pd.neet_marks,pd.neet_percentile,sd.university_enrollment_no from edu_reg_gradu_personal_dtls pd\n"
					+ "inner join edu_lms_state_mstr s on s.state_id= pd.state_id::int\n"
					+ "inner join edu_lms_country_mstr c1 on c1.id = pd.pers_nationality\n"
					+ "inner join edu_lms_district_mstr d on d.district_id= pd.district_id::int\n"
					+ "inner join edu_lms_gender_mstr g on g.id= pd.pers_gender::int\n"
					+ "inner join logininformation l on l.userid= pd.p_id\n"
					+ "inner join edu_lms_nch_student_details sd on sd.email= l.email_id \n"
					+ "inner join tb_lms_category_mstr cm1 on cm1.id = pd.pers_category::int  \n"
					+ "inner join edu_lms_religion_mstr r1 on r1.id = pd.pers_religion::int  \n"
					+ "inner join edu_lms_marital_status_mstr ms on ms.id = pd.pers_marital_status::int  \n"
					+ "inner join edu_lms_institute_reg ir on ir.id=  sd.institude_userid\n"
					+ "inner join edu_lms_university_mstr um on um.id = ir.university_id where pd.id!=0 and  pd.status = 1 and verified_status = 1" +SearchValue+ ")a where id!=0 " ;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, Search, university_id, institute_id,name,ayush_id,gender,date_of_birth);
			System.err.println("---------g.gender_name-----------"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			stmt.close();
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
		return (long) total;
	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL1(String Search, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (sd.ayush_id like ?  or upper(pd.pers_name) like ? or upper(pd.pers_surname) like ?"
					+ "or upper(pd.pers_father_name) like ? or upper(pd.pers_mother_name) like ?"
					+ "or upper(g.gender_name) like ? or  upper (to_char(pers_date_of_birth,'dd-mon-yyyy') )like ?"
					+ "or upper(cm1.category) like ? or upper(r1.religion::text) like ?"
					+ "or upper(ms.marital_status::text) like ? or  upper(c1.name::text) like ? "
					+ "or upper(s.state_name) like ? or upper(d.district_name) like ? "
					+ "or pd.neet_rank::text like ? or pd.neet_marks::text like ? or pd.neet_percentile like ? or upper(um.university_name) like ? or upper(ir.institute_name) like ?)";
			System.err.println("globalllll search" + SearchValue);
		}
		/// advance search
		if (!university_id.trim().equals("0")) {
			SearchValue += " and sd.university_userid = ? ";
		}
		if (!institute_id.trim().equals("0") ) {
			SearchValue += " and sd.institude_userid = ? ";
		}
		if (!name.trim().equals("")) {
			SearchValue += " and upper(pd.pers_name) like ? ";
		}
		if (!ayush_id.trim().equals("")) {
			SearchValue += " and upper(sd.ayush_id) like ? ";
		}
		if (!gender.trim().equals("0") && gender != null ) {
			SearchValue += " and pd.pers_gender = ? ";
		}
		if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")){
			SearchValue += " where to_char(pd.pers_date_of_birth,'DD/MM/YYYY')= ?";
		}	
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if (!university_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(university_id));
			}
			if (!institute_id.equals("0") ) {
				flag += 1;
				
				
				
				System.err.println("institute_id------->           "+institute_id);
				
				
				
				stmt.setInt(flag, Integer.parseInt(institute_id) );
			}
			if (!name.equals("") && name != null) {
				flag += 1;
				stmt.setString(flag, "%" + name.toUpperCase() + "%");
			}
			if (!ayush_id.equals("") && ayush_id != null) {
				flag += 1;
				stmt.setString(flag, "%" + ayush_id.toUpperCase() + "%");
			}
			if (!gender.equals("0") && gender != null) {
				flag += 1;
				stmt.setString(flag,  gender);
			}
			if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_birth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
public List<Map<String, Object>> getSearch_Stu_RegDataforPopup(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			
			q="select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no, \n"
					+ "ed.id, am.academic,ed.board_or_university, ed.passing_year,\n"
					+ "ed.doc_path from edu_reg_gradu_pre_edu_dtls_tbl ed\n"
					+ "inner join edu_reg_gradu_personal_dtls tpd on tpd.id = ed.p_id\n"
					+ "inner join edu_academic_mstr am on am.id::text  = ed.academic\n"
					+ "where ed.id!=0   and tpd.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(id));
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt----Riddhi PopUp--MAIN---"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("file_id",id);
				list.add(columns);
			}
			rs.close();
			stmt.close();
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
		return list;
	}

public String getFilePathQuery_popup1(int id) {

	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		query = "select ed.doc_path from edu_reg_gradu_pre_edu_dtls_tbl ed\n"
				+ "inner join edu_reg_gradu_personal_dtls tpd on tpd.id = ed.p_id \n"
				+ "where tpd.id = ?";

		stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			whr = rs.getString("doc_path");
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
 }

@Override
public ArrayList<ArrayList<String>> get_inst_by_uni_nch_data(String university_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{
    	conn = dataSource.getConnection();
    	
    	String sq1="select ir.id,ir.institute_name from edu_lms_institute_reg ir  WHERE ir.university_id =  ?\n";
        
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, Integer.parseInt(university_id));
        
      
        ResultSet rs = stmt.executeQuery();  
        
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("institute_name"));// 0
				alist.add(list);                           	  
        }
        rs.close();
        stmt.close();
        conn.close();
    }catch(SQLException e){
	   e.printStackTrace();
   }        
    
   System.err.println("alist----->    "+alist);  
    
   return alist;
}

@Override
public ArrayList<ArrayList<String>> getStudent_Registration_Report_Excel( String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth)
		throws ParseException {
	
//	university_id, institute_id,
//	name, ayush_id, gender, date_of_birth,role1
//	

	System.err.println("institute_id ------>     "+ university_id+"   "+ institute_id +"   "+ name+"   "+
			 ayush_id +"   "+gender +"   "+ date_of_birth);
	
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	String SearchValue = GenerateQueryWhereClause_SQL1("", university_id, institute_id, name,
			 ayush_id,gender,date_of_birth);
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();

		String qry = "";

		q=" select pd.id, upper(sd.ayush_id)as ayush_id,ir.institute_name,um.university_name,pd.pers_name,pd.pers_surname,pd.pers_father_name, pd.pers_mother_name,g.gender_name,\n"
				+ "TO_CHAR(pd.pers_date_of_birth , 'DD-MM-YYYY') as pers_date_of_birth,cm1.category,\n"
				+ "r1.religion,ms.marital_status,c1.name as nationality ,s.state_name,d.district_name,\n"
				+ "pd.neet_rank,pd.neet_marks,pd.neet_percentile,sd.university_enrollment_no from edu_reg_gradu_personal_dtls pd\n"
				+ "inner join edu_lms_state_mstr s on s.state_id= pd.state_id::int\n"
				+ "inner join edu_lms_country_mstr c1 on c1.id = pd.pers_nationality\n"
				+ "inner join edu_lms_district_mstr d on d.district_id= pd.district_id::int\n"
				+ "inner join edu_lms_gender_mstr g on g.id= pd.pers_gender::int\n"
				+ "inner join logininformation l on l.userid= pd.p_id\n"
				+ "inner join edu_lms_nch_student_details sd on sd.email= l.email_id \n"
				+ "inner join tb_lms_category_mstr cm1 on cm1.id = pd.pers_category::int  \n"
				+ "inner join edu_lms_religion_mstr r1 on r1.id = pd.pers_religion::int  \n"
				+ "inner join edu_lms_marital_status_mstr ms on ms.id = pd.pers_marital_status::int  \n"
				+ "inner join edu_lms_institute_reg ir on ir.id= sd.institude_userid\n"
				+ "inner join edu_lms_university_mstr um on um.id = ir.university_id\n where pd.id!=0 and  pd.status = 1 and verified_status = 1"
				+ SearchValue + " ORDER BY pd.id ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,"",university_id, institute_id,name,ayush_id,gender,date_of_birth);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(String.valueOf(i));// 0
			alist.add(rs.getString("ayush_id"));// 1
			alist.add(rs.getString("institute_name"));// 2
			alist.add(rs.getString("university_name"));// 3
			alist.add(rs.getString("pers_name"));// 4
			alist.add(rs.getString("pers_surname"));// 5
			alist.add(rs.getString("pers_father_name"));// 6
			alist.add(rs.getString("pers_mother_name"));// 7
			alist.add(rs.getString("gender_name"));// 8
			alist.add(rs.getString("pers_date_of_birth"));// 9
			alist.add(rs.getString("category"));// 10
			alist.add(rs.getString("religion"));// 11
			alist.add(rs.getString("marital_status"));// 12
			alist.add(rs.getString("nationality"));// 13
			alist.add(rs.getString("state_name"));// 14
			alist.add(rs.getString("district_name"));// 15
			alist.add(rs.getString("neet_rank"));// 16
			alist.add(rs.getString("neet_marks"));// 17
			alist.add(rs.getString("neet_percentile"));// 18
			alist.add(rs.getString("university_enrollment_no"));// 19
			list.add(alist);
			i++;
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return list;
}
}


