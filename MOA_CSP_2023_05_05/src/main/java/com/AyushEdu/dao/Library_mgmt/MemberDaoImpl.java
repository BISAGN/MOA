package com.AyushEdu.dao.Library_mgmt;

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

import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_DTL;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public TB_MEMBER_DTL getsystemByid1(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_MEMBER_DTL updateid = (TB_MEMBER_DTL) session.get(TB_MEMBER_DTL.class, id);
        session.getTransaction().commit();
        session.close();                
       return updateid;
	}

	////////////////////////AUTOCOMPLETE MEMBER DETAILS/////////////////////////////////
	
public List<Map<String, Object>> MemberDataAuto(String autoString) {
		
		System.err.println("**********************************");
		System.err.println(autoString);
		System.err.println("**********************************");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
	
		try {

			conn = dataSource.getConnection();
			
			q = "select distinct id,ayush_id from( select id,ayush_id from edu_lms_student_details\r\n"
					+ "UNION ALL\r\n"
					+ "select id,ayush_id from edu_lms_nch_student_details) a  where upper(ayush_id) like ? \r\n"
					+ "order by ayush_id asc ";
	
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setString(1,"%"+autoString.toUpperCase()+"%");
			
			System.err.println("stmt---MEMBER AUTO--->"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
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
	
	
	public List<Map<String, Object>> DataTableTB_MEMBER_DTLDataList(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, String member_name,String member_joined_date)  {
		

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			String pageL="";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			q="select ROW_NUMBER() OVER(order by t.id ) as ser,t.id,t.member_name,TO_CHAR(member_joined_date,'DD/MM/YYYY') as member_joined_date,t.member_phone_number,  \r\n"
					+ "s.system_name as member_dept\r\n"
					+ "from tb_member_dtl t\r\n"
					+ "inner join edu_lms_system_mstr s on s.id = t.member_dept where t.id != 0\r\n"
					+ SearchValue + " ORDER BY t.id" + " " + orderType + " limit "
				    + pageL + " OFFSET " + startPage;
			


			 
			System.err.println("=================List query======================================="+q);   
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			System.err.println("=================stmt==============="+stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

					
				String f = "";
				String action = "";
				String f1 = "";
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";


				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDMember_Pojo' value='ADD' title='Edit Data' >"
						+ "<input type='hidden' id='apIdAGE" + countFunction + "' value=" + rs.getString("id")
						+ "><i class='lni lni-pencil-alt'></i></a> </li>";
				
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
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
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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
	
	
	public long DataTableTB_MEMBER_DTLTotalCount(String Search, String member_name, String member_joined_date, 
			String member_dept, String member_phone_number ) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();
			
//			Query for count page in data-table....by ruler
//			q = "select count(*) \n" + " from designation where id!=0 and status='1' " + SearchValue;
//			
			
//			  q="select count(* ) \r\n"
//			  		+ "from tb_member_dtl t\r\n"
//			  		+ " " +SearchValue;
			q= "\r\n"
					+ "select count(*) from (select t.id,t.member_name,TO_CHAR(member_joined_date,'DD/MM/YYYY') as member_joined_date,t.member_phone_number,  \r\n"
					+ "s.system_name as member_dept\r\n"
					+ "from tb_member_dtl t\r\n"
					+ "inner join edu_lms_system_mstr s on s.id = t.member_dept where t.id != 0 \r\n"
					+ SearchValue+ " ) as ab ";
			
			System.err.println("count query----------" + q);
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search);

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





	private String GenerateQueryWhereClause_SQL(String search) {
		
		String SearchValue ="";
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += "and Upper(member_name)::character varying like ? OR TO_CHAR(member_joined_date,'DD/MM/YYYY') like ? OR upper(s.system_name) like ? OR member_phone_number like ?";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search

		return SearchValue;
	}
	

	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search)  {
		int flag = 0;
		try {
			
			
			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag,"%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag,"%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag,"%"+search.toUpperCase()+"%");
//				flag += 1;
//				stmt.setString(flag,"%"+search.toUpperCase()+"%");
			}
			
//			if (member_name != null && !member_name.equals("")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(member_name));
//			}
//			
//		
//			if (member_joined_date != null && !member_joined_date.equals("")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(member_joined_date));
//			}
	
		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public List<Map<String, Object>> getMemberDetails(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q="select TO_CHAR(member_joined_date,'DD/MM/YYYY') as member_joined_date_new,* from tb_member_dtl where id = ?";
					
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

			
					list.add(columns);
					
			}		
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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
	
	
	
public List<Map<String, Object>> MemberdetailsbyayushidDetailsMethod(String ayush_id) {
		
	
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		

		
	
		try {

			conn = dataSource.getConnection();
			
			q = "select distinct * from( \r\n"
					+ "select s.id,name,r.state_id,r.district_id,mobile_no,r.system_id,EXTRACT(YEAR FROM (AGE(current_date,dob))) as age from edu_lms_student_details s\r\n"
					+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid\r\n"
					+ "UNION ALL\r\n"
					+ "select k.id,name,m.state_id,m.district_id,mobile_no,m.system_id,EXTRACT(YEAR FROM (AGE(current_date,dob))) as age from edu_lms_nch_student_details k\r\n"
					+ "inner join edu_lms_institute_reg m on m.id = k.institude_userid\r\n"
					+ ") a\r\n"
					+ "where id = ?";
	
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(ayush_id));
			
			System.err.println("stmt---MEMBER AUTO--->"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
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
public List<Map<String, Object>> getAyushIdFromUserId(int user_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
//		q="select ayush_id from (select ayush_id,aadhar_card from edu_lms_student_details\r\n"
//				+ "UNION ALL\r\n"
//				+ "select ayush_id,aadhar_card from edu_lms_nch_student_details) a \r\n"
//				+ "inner join logininformation l on l.aadhar_no = a.aadhar_card\r\n"
//				+ "where userid = ?\r\n"
//				+ "group by 1";
		
		//tushar---08feb23
		
		q="select ayush_id from (select ayush_id,email from edu_lms_student_details\r\n"
				+ "UNION ALL\r\n"
				+ "select ayush_id,email from edu_lms_nch_student_details) a \r\n"
				+ "inner join logininformation l on l.email_id = a.email\r\n"
				+ "where userid = ?\r\n"
				+ "group by 1";
				
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, user_id);
		
		System.err.println("stmt--->08feb23_---->"+stmt);
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

				list.add(columns);
				
		}		
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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


}