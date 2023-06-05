package com.AyushEdu.dao.LMS_Practitioner;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Add_Lecture_DAOImpl implements Add_Lecture_DAO {
	
	@Autowired
	private DataSource dataSource;	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
@Override
public List<Map<String,Object>> ADDLecDataList(int startPage,int pageLength,String Search,String orderColunm,String orderType, 
		String system,String degree,String from_date,String to_date) {
	
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search, system , degree ,from_date,  to_date);
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


		q = "select l.id,s.system_name as system,d.degree_name as degree,to_char(from_date,'DD-MON-YYYY') as from_date,to_char(to_date,'DD-MON-YYYY') as to_date,lec_video,lec_pdf \n"
				+ "from tb_add_lectures l \n"
				+ "inner join edu_lms_system_mstr s on s.id = l.system \n"
				+ "inner join edu_lms_degree_mstr d on d.id = l.degree where l.id!= 0 "+ SearchValue + "  ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, system , degree ,from_date,  to_date);
		
//		System.err.println("STMT---"+stmt);
		
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
			
			
			String action = "";
			String action1 = "";
//			String action2 = "";
			String f1 = "";
			
			String f2 = "";
			String f3 = "";
			String video = "";
			

			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('" + rs.getInt("id") +"') }else{ return false;}\"";
			f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
			
			String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f2 = "<i class='fa fa-check '  " + ADD2 + " title='Approve Data'></i>";
			
			String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f3 = "<i class='fa fa-close '  " + ADD3 + " title='Reject Data'></i>";
		
			
			video = "<a class='fa fa-play-circle-o' onclick='return videotopicCall("+rs.getString("id")+");' style='color: #04474a; font-size: 100%;'></a>";
			action = f1 + " " + f2 + " " + " " + f3 + " " + video;
			
			action1 = f1 + " " + video ;
//			action2 = video;
//			columns.put("action", action);
			columns.put("action", action1);
			
			
			
//			if(role.equals("NCISM")) {
//				//action = f1+""+f2+""+f3+""+video;
////				String app_status1=rs.getString("app_status");
//				
//				
//				if(app_status.equals("0")) {
//
//					columns.put("action", action);
//					}
//				
//				if(app_status.equals("1")) {
//
//					columns.put("action", action1);
//					
//					}
//				if(app_status.equals("2")) {
//
//					columns.put("action", action2);
//					
//					}
//			}else {
//				
//				columns.put("action", action2);
//			}
			
			
//			columns.put("action", action);
//			
//			columns.put("video", video);
			
			
			
		
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
public String GenerateQueryWhereClause_SQL( String Search,String system, String degree,String from_date,String to_date ) {

String SearchValue = "";

		if (!system.trim().equals("0")) {
			SearchValue += " and l.system = ? ";
		}
		if (!degree.trim().equals("0")) {
			SearchValue += " and l.degree  = ? ";
		}
		if (!from_date.trim().equals("") && !from_date.trim().equals("DD/MM/YYYY")) {
			SearchValue += " and l.from_date = ? ";
		}
		if (!to_date.trim().equals("") && !to_date.trim().equals("DD/MM/YYYY")) {
			SearchValue += " and l.to_date  = ? ";
		}
		
		return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system, String degree, String from_date,String to_date){
int flag = 0;
try {

	if (!system.trim().equals("0")) {
		flag += 1;
		stmt.setInt(flag,Integer.parseInt(system) );
	}
	if (!degree.trim().equals("0")) {
		flag += 1;
		stmt.setInt(flag,Integer.parseInt(degree));
	}
	if (!from_date.trim().equals("") && !from_date.trim().equals("DD/MM/YYYY")) {
		flag += 1;
		stmt.setInt(flag,Integer.parseInt(from_date) );
	}
	if (!to_date.trim().equals("") && !to_date.trim().equals("DD/MM/YYYY")) {
		flag += 1;
		stmt.setInt(flag,Integer.parseInt(to_date));
	}


} catch (Exception e) {
	e.printStackTrace();
}
return stmt;
}

@Override
public long DataTotalCount(String Search,String system,String degree,String from_date,String to_date) {
String SearchValue = GenerateQueryWhereClause_SQL(Search, system,degree,from_date, to_date);
int total = 0;
String q = null;
Connection conn = null;
try {
	conn = dataSource.getConnection();
	
	 		
	 		q="select count(*) from ( select l.id,s.system_name,d.degree_name,from_date,to_date,lec_video,lec_pdf \n"
	 				+ "from tb_add_lectures l \n"
	 				+ "inner join edu_lms_system_mstr s on s.id = l.system \n"
	 				+ "inner join edu_lms_degree_mstr d on d.id = l.degree"
	 			
				+ SearchValue + ") ab  ";
	
	PreparedStatement stmt = conn.prepareStatement(q);
	
	stmt = setQueryWhereClause_SQL(stmt,Search, system,degree,from_date, to_date);
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

public String getLecVideoPath(int id) {
	Session sessionHQL = sessionFactory.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	try {
		Query q1 = sessionHQL
				.createQuery("SELECT lec_video FROM TB_ADD_LECTURES where id=:id");
		q1.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		String path=list.get(0);
		tx.commit();
		return path;
	} catch (RuntimeException e) {
		return null;
	} finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}
}

@Override
public List<Map<String,Object>> ViewLecDataList(int startPage,int pageLength,String Search,String orderColunm,String orderType) {
	
	
	String SearchValue = GenerateQueryWhereClause_SQL_VIEWLec(Search);
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


		q = "select l.id,s.system_name as system,d.degree_name as degree,to_char(from_date,'DD-MON-YYYY') as from_date,to_char(to_date,'DD-MON-YYYY') as to_date,lec_video,lec_pdf \n"
				+ "from tb_add_lectures l \n"
				+ "inner join edu_lms_system_mstr s on s.id = l.system \n"
				+ "inner join edu_lms_degree_mstr d on d.id = l.degree where l.id!= 0 "+ SearchValue + "  ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_VIEWLec(stmt, Search);
		
//		System.err.println("STMT---viewLec---"+stmt);
		
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
			
			String action1 = "";
			String video = "";
			
			video = "<a class='fa fa-play-circle-o' onclick='return videotopicCall("+rs.getString("id")+");' style='color: #04474a; font-size: 100%;'></a>";
			
			action1 =  video ;
			columns.put("action", action1);
		
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
public String GenerateQueryWhereClause_SQL_VIEWLec( String Search) {

		String SearchValue = "";

		if (!Search.trim().equals("")) {
			SearchValue += " and upper(s.system_name) like ? or upper(d.degree_name) like ? or upper(to_char(from_date,'DD-MON-YYYY')) like ? or upper(to_char(to_date,'DD-MON-YYYY')) like ?";
		}
		
		return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_VIEWLec(PreparedStatement stmt, String Search){
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
	}


} catch (Exception e) {
	e.printStackTrace();
}
return stmt;
}

@Override
public long DataTotalCountView(String Search) {
String SearchValue = GenerateQueryWhereClause_SQL_VIEWLec(Search);
int total = 0;
String q = null;
Connection conn = null;
try {
	conn = dataSource.getConnection();
	
	 		
	 		q="select count(*) from ( select l.id,s.system_name,d.degree_name,from_date,to_date,lec_video,lec_pdf \n"
	 				+ "from tb_add_lectures l \n"
	 				+ "inner join edu_lms_system_mstr s on s.id = l.system \n"
	 				+ "inner join edu_lms_degree_mstr d on d.id = l.degree"
	 			
				+ SearchValue + ") ab  ";
	
	PreparedStatement stmt = conn.prepareStatement(q);
	
	stmt = setQueryWhereClause_SQL_VIEWLec(stmt,Search);
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
