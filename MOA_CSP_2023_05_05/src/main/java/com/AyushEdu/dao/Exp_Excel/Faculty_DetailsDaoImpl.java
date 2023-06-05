package com.AyushEdu.dao.Exp_Excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_FACULTY_NCH;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;

@Repository
public class Faculty_DetailsDaoImpl implements Faculty_DetailsDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public ArrayList<ArrayList<String>> DataTableFacultyDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String userid, String dob, String aadhar_card, String email,
			String mobile_no, String role_type, String upload_date, String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, userid, dob, aadhar_card, email, mobile_no, role_type,
				upload_date);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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

			q = "select distinct ROW_NUMBER() OVER(order by l.userid) as sr_no, (l.userid) as id, (l.login_name) as name,l.aadhar_no,l.email_id,l.mobile_no,to_char(l.created_on,'DD/MM/YYYY') as upload_date,fn.id as facid from logininformation l\n"
					+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
					+ "inner join roleinformation r on r.role_id=ro.role_id and role=? and l.institute_id=(select institute_id from logininformation where userid="
					+ userid + ") inner join edu_lms_faculty_nch fn on fn.aadhar_card=l.aadhar_no  where aadhar_no  is not null and fn.status='1' " + SearchValue + "  ORDER BY id  " + orderType
					+ "  limit " + pageL + "  OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			System.err.println("stmt----14/03/22----->   " + stmt);
			
			if (role.equals("Institute_NCISM") || role.equals("Principal_NCISM")) {
				stmt.setString(1, "Faculty_NCISM");
			} else if (role.equals("Institute_NCH") || role.equals("Principal_NCH")) {
				stmt.setString(1, "Faculty_NCH");
			}
			stmt = setQueryWhereClause_SQL(stmt, Search, userid, dob, aadhar_card, email, mobile_no, role_type,
					upload_date);

			System.err.println("stmt---- rs ----->   " + stmt);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;

			int countDownload = 1;
			int countFunctionAdd = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				f1 ="<li><a class='main-btn active-btn btn-hover btn-sm btnedit editFaculty' value='ADD'  title='Edit Data' >"+
						"<i class='lni lni-pencil-alt'></i></a></li>"
						+ "<input type='hidden' id='editFacId"+countFunctionDelete+"' value="+rs.getString("facid")+"></i></a> </li>";
				
				f2 ="<li><a class='main-btn danger-btn btn-hover btn-sm btnremove deleteFaculty' value='ADD'  title='Delete Data' >"+ //id='id_add_attHospital1'
						"<i class='lni lni-trash-can'></i></a> </li>"
						+ "<input type='hidden' id='deleteFacId"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a> </li>";
				
				ul+=f1;
//				+ f2
				 ul+="</ul>";
				

				 countFunctionDelete+=1;
					action = ul;
					//columns.put("action", action);
				

				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name"));// 1
				list.add(rs.getString("aadhar_no"));// 2
				list.add(rs.getString("email_id"));// 3
				list.add(rs.getString("mobile_no"));// 4
				list.add(rs.getString("upload_date"));// 5
				list.add(rs.getString("sr_no"));// 6

				list.add(f1);// 7
				list.add(action);// 8

//				list.add(f2);
				i++;
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
	public long DataTableFacultyDataTotalCount(String Search, String userid, String dob, String aadhar_card,
			String email, String mobile_no, String role_type, String upload_date,String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, userid, dob, aadhar_card, email, mobile_no, role_type,
				upload_date);

		System.err.println("SearchValue========>    " + SearchValue);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by l.userid) as sr_no, (l.userid) as id, (l.login_name) as name,l.aadhar_no,l.email_id,l.mobile_no,to_char(l.created_on,'DD/MM/YYYY') as upload_date from logininformation l\n"
					+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
					+ "inner join roleinformation r on r.role_id=ro.role_id and role=? and l.institute_id=(select institute_id from logininformation where userid="
					+ userid + ") inner join edu_lms_faculty_nch fn on fn.aadhar_card=l.aadhar_no where aadhar_no  is not null and fn.status='1' "+SearchValue+") ab ";


			System.err.println("////////////Count Query///////////////" + q);
			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1, Integer.parseInt(role_type));
			if (role.equals("Institute_NCISM") || role.equals("Principal_NCISM")) {
				stmt.setString(1, "Faculty_NCISM");
			} else if (role.equals("Institute_NCH") || role.equals("Principal_NCH")) {
				stmt.setString(1, "Faculty_NCH");
			}
			stmt = setQueryWhereClause_SQL(stmt, Search, userid, dob, aadhar_card, email, mobile_no, role_type,
					upload_date);

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

	public String GenerateQueryWhereClause_SQL(String Search, String name, String dob, String aadhar_card, String email,
			String mobile_no, String role_type, String upload_date) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter

			SearchValue += "and ( upper(login_name) like ?  or upper(aadhar_no) like ? \n"
					+ "or upper(email_id) like ? or upper(mobile_no) like ?  \n"
					+ "   or upper(r.role) like ? or to_char(l.created_on,'DD/MM/YYYY') like ?) ";
		}

//	if (role_type != 0) {
//			SearchValue += " and name = ? ";
//		}
//		
//		if (passing_year != 0) {
//			SearchValue += " and ed.passing_year like ? ";
//		}

		if (role_type != null && !role_type.equals("0")) {
			SearchValue += " and r.role_id = ? ";
		}

		if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
			SearchValue += "and TO_CHAR(l.created_on , 'dd/mm/yyyy') = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name, String dob,
			String aadhar_card, String email, String mobile_no, String role_type, String upload_date) {

		int flag = 1;
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

			}

			if (role_type != null && !role_type.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(role_type));
			}
			if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, upload_date);
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	@Override
	public EDU_LMS_FACULTY_NCH getFacultyDataByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_FACULTY_NCH updateid = (EDU_LMS_FACULTY_NCH) session.get(EDU_LMS_FACULTY_NCH.class, id);
       session.getTransaction().commit();
       session.close();                
      return updateid;
	}

}
