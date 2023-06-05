package com.AyushEdu.dao.LMS_Notification;

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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class NotificationCourseDurationDAOImpl implements NotiFicationCourseDurationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> GetDays(String system,String course) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
				q = "select DATE_PART('day',age(end_date,current_date)) as diff \n"
						+ "from edu_lms_system_course_duration\n"
						+ "where system_id=? and course_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,system);
				stmt.setString(2,course);
				
//			    System.err.println("---stmt--->"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("diff")); //0
				list.add(alist);
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
	public List<Map<String,Object>> getFilterNoticourse_data(int startPage,int pageLength,String Search,String orderColunm,String orderType, 
			String course,String system,String degree,String ayushid,String name) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,course,system,degree,ayushid,name);
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


			q = "SELECT p.id,l.login_name,sd.ayush_id,sm.system_name,cm.course_name,dm.degree_name,p.user_id\n"
					+ "FROM edu_lms_system_ele_course_sets_link_parent p\n"
					+ "inner join logininformation l on l.userid=p.user_id::int\n"
					+ "inner join edu_lms_student_details sd on sd.name=l.login_name\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=p.system_id::int\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name=p.ele_course_id::int\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
					+ "left join edu_lms_degree_mstr dm on dm.id=sd.degree::int\n"
					+ "where p.status='1' "+ SearchValue + "  ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,course,system,degree,ayushid,name);
			
			System.err.println("STMT---HARSH--->"+stmt);
			
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
				
				String send = "";
				String cb = "";
				
				send = "<button type='button' class='btn-print' onclick='SendNoti("+rs.getString("user_id")+");'><i class='fa fa-send'></i></button>";
				
				cb = "<input type='checkbox' id='cb1' name='cb"+rs.getString("id")+"' value='' onclick='cbSendNoti("+rs.getString("user_id")+");'>";
				
				columns.put("cb",cb);
				columns.put("sendBtn",send);
			
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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL( String Search,String course,String system, String degree,String ayushid,String name ) {

	String SearchValue = "";
	
			if (!Search.trim().equals("")) {
				SearchValue += " and ( upper(l.login_name) like ? or upper(sd.ayush_id) like ? or upper(sm.system_name) "
						+ "like ? or upper(cm.course_name) like ? or upper(dm.degree_name) like ? ) ";
			}

			if (!system.trim().equals("0")) {
				SearchValue += " and p.system_id = ? ";
			}
			if (!degree.trim().equals("0")) {
				SearchValue += " and sd.degree  = ? ";
			}
			if (!course.trim().equals("0")) {
				SearchValue += " and p.ele_course_id  = ? ";
			}
			if (!ayushid.trim().equals("")) {
				SearchValue += " and upper(sd.ayush_id) like ? ";
			}
			if (!name.trim().equals("")) {
				SearchValue += " and upper(l.login_name) like ? ";
			}
			
			return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String course,String system,String degree,String ayushid,String name){
	int flag = 0;
	try {
		
		if (!Search.trim().equals("")) {
			flag += 1;
			stmt.setString(flag,"%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag,"%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag,"%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag,"%"+Search.toUpperCase()+"%");
			flag += 1;
			stmt.setString(flag,"%"+Search.toUpperCase()+"%");
		}

		if (!system.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag,system);
		}
		if (!degree.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag,degree);
		}
		if (!course.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag,course);
		}
		if (!ayushid.trim().equals("")) {
			flag += 1;
			stmt.setString(flag,"%"+ayushid.toUpperCase()+"%");
		}
		if (!name.trim().equals("")) {
			flag += 1;
			stmt.setString(flag,"%"+name.toUpperCase()+"%");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
	}
	
	@Override
	public long DataTotalCountNotiCourseData(String Search,String course,String system,String degree,String ayushid,String name) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search,course,system,degree,ayushid,name);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		
		 		
		 		q="select count(*) from ( SELECT p.id,l.login_name,sd.ayush_id,sm.system_name,cm.course_name,dm.degree_name\n"
		 				+ "FROM edu_lms_system_ele_course_sets_link_parent p\n"
		 				+ "inner join logininformation l on l.userid=p.user_id::int\n"
		 				+ "inner join edu_lms_student_details sd on sd.name=l.login_name\n"
		 				+ "inner join edu_lms_system_mstr sm on sm.id=p.system_id::int\n"
		 				+ "inner join edu_lms_ele_course_mstr cm on cm.course_name=p.ele_course_id::int\n"
		 				+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
		 				+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree::int\n"
		 				+ "where p.status='1' "
		 			
					+ SearchValue + ") ab  ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		
		stmt = setQueryWhereClause_SQL(stmt,Search,course,system,degree,ayushid,name);
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

}
