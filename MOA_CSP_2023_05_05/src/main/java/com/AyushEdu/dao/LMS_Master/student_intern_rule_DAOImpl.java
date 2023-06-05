package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.DriverManager;
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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_STUDENT_INTERN_RULES;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
@Repository
public class student_intern_rule_DAOImpl implements studentintern_ruleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public long DataTablesystemDataTotalCountRule(String Search, String role_name,String year,String month) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,role_name, year, month);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q = "select x.id,x.role_name,x.year,x.month,x.status from edu_lms_student_intern_rules_mstr  where id!=0 and status='1' \n"
//					+ SearchValue + ") ab  ";
			
			q="select count(*) from (select id,role_name,year,month,status from edu_lms_student_intern_rules_mstr  where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,role_name, year, month);
System.err.println("for count"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String role_name,String year,String month) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and  upper(role_name) like ? or  year::character varying  like ? or month::character varying like ?";
			System.err.println("SearchValue===="+SearchValue);
		}

		if (role_name != null && !role_name.equals("")) {
			SearchValue += " and upper(role_name) like ? ";

		}
		if (year != null && !year.equals("")) {
			SearchValue += " and year::character varying like ? ";

		}
		if (month != null && !month.equals("")) {
			SearchValue += " and month::character varying like ? ";

		}
	 
		System.err.println("SearchValue====="+SearchValue);
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String role_name,String year,String month) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}

			if (role_name != null && !role_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + role_name.toUpperCase() + "%");
			}
			if (year != null && !year.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + year + "%");
			}
			if (month != null && !month.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + month + "%");
			}
 

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTablesystemDataListRule(int startPage, int pageLength,
			String orderColunm, String orderType,String Search,String role_name, String year,String month,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, role_name, year, month);
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

			if (Search.equals("") && role_name.equals("0") && status == "1") {
				q = "\n"
						+ "select x.id,x.role_name,x.year,x.month,x.status from edu_lms_student_intern_rules_mstr x  where id!=0 and status='1' " + SearchValue
						+ " ORDER BY role_name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select x.id,x.role_name,x.year,x.month,x.status from edu_lms_student_intern_rules_mstr x  where id!=0 and status='" + status + "'"
						+ SearchValue + " ORDER BY role_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, role_name, year, month);
			System.err.println("urmikkkkkkk"+stmt);
			ResultSet rs = stmt.executeQuery();
         
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
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
	
	public Boolean  Delete_SI_RULE_Mstr_Data(int id){
	      Connection c = null;
	      ArrayList<String> list = new ArrayList<String>();
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
//	         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp')");  
//	         ResultSet rs=stmt.executeQuery();
//	 	   	 
//	         System.out.println("Opened database successfully");
//	         
//	         while(rs.next()) {
//	        	 list.add(rs.getString("dblink_connect"));
//	        	 ///System.out.println("list----------"+list);
//	        	 
//	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_student_intern_rules_mstr m where state_id="+id+" ");  
	         /// ResultSet rs1 =stmt.executeQuery();
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"serving records inserted" +pstmt);  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	 
	        // System.exit(0);
	         return false;
	      }
	      System.out.println("Operation Done Successfully");
	      return true;
	   }
	@Override
	public EDU_LMS_STUDENT_INTERN_RULES getsystemByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_STUDENT_INTERN_RULES updateid = (EDU_LMS_STUDENT_INTERN_RULES) session.get(EDU_LMS_STUDENT_INTERN_RULES.class, id);
       session.getTransaction().commit();
       session.close();                
      return updateid;
	}

//	public EDU_LMS_SYSTEM_MSTR getsystemByid(int id) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		EDU_LMS_SYSTEM_MSTR updateid = (EDU_LMS_SYSTEM_MSTR) session.get(EDU_LMS_SYSTEM_MSTR.class, id);
//		session.getTransaction().commit();
//		session.close();
//		return updateid;
//	}
}
