package com.AyushEdu.dao.LMS_Institute;

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

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_CLASSROOM_MSTR;

@Repository
public class Classroom_DAOImpl1 implements ClassroomDAO1 {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableclassroomDataTotalCount(String Search, String classroom_name, String block_name, String strength) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, classroom_name, block_name, strength);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			q = "select count(*) \n" + " from edu_lms_classroom_mstr where status = '1' and id!=0 " + SearchValue;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, classroom_name, block_name, strength);

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
	public String GenerateQueryWhereClause_SQL(String Search, String classroom_name, String block_name, String strength) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(classroom_name) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(block_name) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(strength) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if(SearchValue.contains("and") && !classroom_name.equals("")) {
			SearchValue += " and upper(classroom_name) like ? ";
		
	     }
	
	   if(!SearchValue.contains("and") && !classroom_name.equals("")){
			SearchValue += " and upper(classroom_name) like ? ";
			System.err.println("parameter search"+SearchValue);
		
	
	    }
	   if(SearchValue.contains("and") && !block_name.equals("")) {
			SearchValue += " and upper(block_name) like ? ";
		
	     }
	
//	   if(!SearchValue.contains("and") && !block_name.equals("")){
//			SearchValue += " and upper(block_name) like ? ";
//			System.err.println("parameter search"+SearchValue);
//		
//	
//	    }
	   
	   if(SearchValue.contains("and") && !strength.equals("")) {
			SearchValue += " and upper(strength) like ? ";
		
	     }
	
//	   if(!SearchValue.contains("and") && !strength.equals("")){
//			SearchValue += " and upper(strength) like ? ";
//			System.err.println("parameter search"+SearchValue);
//		
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String classroom_name, String block_name, String strength) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			
			if (!classroom_name.equals("") && classroom_name != null) {
				flag += 1;
				stmt.setString(flag,"%"+classroom_name.toUpperCase()+"%");
			}
			if (!block_name.equals("") && block_name != null) {
				flag += 1;
				stmt.setString(flag,"%"+block_name.toUpperCase()+"%");
			}
			if (!strength.equals("") && strength != null) {
				flag += 1;
				stmt.setString(flag,"%"+strength.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableclassroomDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String classroom_name, String block_name, String strength, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, classroom_name, block_name, strength);
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

//			if(Search.equals("") && classroom_name.equals("0") && block_name.equals("0") && strength.equals("0") && status=="1") {
//				q = "select id,cls\n"
//						+ "	public List<Map<String, Object>> DataTableclassroomDataList(classroom_name,block_name,strength,status from edu_lms_classroom_mstr where status= '1'  "  + " ORDER BY classroom_name " + orderType + " limit "
//						+ pageL + " OFFSET " + startPage;
//			}else {
//			q = "select id,classroom_name,block_name,strength,status from edu_lms_classroom_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY classroom_name " + orderType + " limit "
//					+ pageL + " OFFSET " + startPage;
//			}
			
			if (Search.equals("") && classroom_name.equals("") && status =="1") {

				q=" select id,classroom_name,block_name,strength,status from edu_lms_classroom_mstr where status='1' "+ SearchValue +" order by classroom_name " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
				} else {
					q=" select id,classroom_name,block_name,strength,status from edu_lms_classroom_mstr where status='"+status+"' "+ SearchValue +" order by classroom_name " + orderType
							+ " limit " + pageL + " OFFSET " + startPage;
				}
			
//			q=" select id,classroom_name,block_name,strength,status from edu_lms_classroom_mstr where status='1' "+ SearchValue +" order by classroom_name " + orderType
//					+ " limit " + pageL + " OFFSET " + startPage;	
//	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, classroom_name, block_name, strength);
			ResultSet rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
				String f = "";
				String action = "";
				String f1 = "";

				
			
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o'  " + ADD + " title='Edit Data'></i>";
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f+" "+f1;
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
	
	public EDU_LMS_CLASSROOM_MSTR getclassroomByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_CLASSROOM_MSTR updateid = (EDU_LMS_CLASSROOM_MSTR) session.get(EDU_LMS_CLASSROOM_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
}
