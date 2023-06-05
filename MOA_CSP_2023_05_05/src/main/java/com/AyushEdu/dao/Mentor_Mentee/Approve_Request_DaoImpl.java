package com.AyushEdu.dao.Mentor_Mentee;

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
public class Approve_Request_DaoImpl implements Approve_Request_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	

	@Override
	public List<Map<String, Object>> DataTableApprove_Request(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String facUserId) {
		
		
			String SearchValue = GenerateQueryWhereClause_SQL(Search,facUserId);
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
				
				q = " select m.id,l2.login_name as student\n"
						+ "from edu_men_mentor_mentee_request m\n"
						+ "inner join logininformation l on l.userid=m.faculty_user_id \n"
						+ "inner join logininformation l2 on l2.userid=m.student_user_id\n"
						+ "where  m.status=0\n"
						+ "" + SearchValue  + " ORDER BY m.student_user_id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
		
		
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt, Search,facUserId);
				
				System.err.println("-------aaaaa-----------"+stmt);
				
				ResultSet rs = stmt.executeQuery();
				
				
				ResultSetMetaData metaData = rs.getMetaData();
				
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				
				int countFunction=1;
				int countFunctionDelete=1;
				
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					columns.put("ser", ++j);
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
				
					String f = "";
					String action = "";
					String f1 = "";
					
					
					String ul="";
					ul+="<ul class='buttons-group mainbtn action daobtn'>";
					
					String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){ApproveData('"+ rs.getString("id") +"') }else{ return false;}\"";
					 f ="<li><a class='main-btn success-btn btn-hover btn-sm ADDApprove_Request' value='ADD' title='Approve Data' >"+ //id='id_add_attHospital1'
									"<i class='lni lni-checkmark'>"
									+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
					
					String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){RejectData('"+ rs.getString("id") + "') }else{ return false;}\"";
					 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Reject Data' >"+ //id='id_add_attHospital1'
							 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-close'></i></a> </li>";

					
					ul+=f + " " + f1 ;
					ul+="</ul>";
					
					action = ul;
					countFunction+=1;
					countFunctionDelete+=1;
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
	public long DataTableApprove_Request_count(String Search,String facUserId) {
		
	String SearchValue = GenerateQueryWhereClause_SQL(Search,facUserId);
			
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				
				
				q="select count(*) from  ( select m.id,l2.login_name as student\n"
						+ "from edu_men_mentor_mentee_request m\n"
						+ " inner join logininformation l2 on l2.userid=m.student_user_id\n"
						+ "where  m.status=0"+ SearchValue + ")a";

				
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt, Search,facUserId);
				System.err.println("q----------" +stmt);

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


	private String GenerateQueryWhereClause_SQL(String search,String facUserId) {
		
        String SearchValue = "";
        if (search!=null && !search.equals("")) { // for Input Filter
            SearchValue += " and upper(l2.login_name) like ?    ";
        }
        if (facUserId!=null && !facUserId.equals("")) { // for Input Filter
            SearchValue += " and faculty_user_id  = ?   ";
        }
        return SearchValue;
	}
	
	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search,String facUserId) {
			int flag = 0;
			try {
				if (search != null && !search.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
				}
				if (facUserId != null && !facUserId.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(facUserId));
				}
			} catch (Exception e) {
			}
			return stmt;
		}
 
	
		


@Override
public List<Map<String, Object>> DataTableMentee_Request(int startPage, int pageLength, String Search,
		String orderColunm, String orderType,String facUserId2,String role_id) {
	
	
		String SearchValue = GenerateQueryWhereClause_SQL1(Search,facUserId2);
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
			
 
			
			q="select l.login_name as students, d.student_user_id,\n"
					+ "(select count(id) from edu_mentor_mentee_communication m where m.faculty_user_id= "+facUserId2+" and status=0 and to_msg = "+role_id+") as msg_count,ur.role_id\n"
					+ "from edu_mentor_mentee_communication d\n"
					+ "inner join logininformation l on l.userid= d.student_user_id\n"
					+ "inner join userroleinformation ur on ur.user_id=l.userid where id !=0 \n"
					+  SearchValue  + " group by 1,2,4 limit "
					+ pageL + " OFFSET " + startPage;
	
		 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL1(stmt, Search,facUserId2);
			
			System.err.println("-mentee 1111111111111111 MSGS---"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){ApproveData('"+ rs.getString("id") +"') }else{ return false;}\"";
//				 f ="<li><a class='main-btn success-btn btn-hover btn-sm ADDApprove_Request' value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
//								"<i class='lni lni-checkmark'>"
//								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
//				
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){RejectData('"+ rs.getString("id") + "') }else{ return false;}\"";
//				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
//						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-close'></i></a> </li>";

				
				 String cnt = GetMsgCount( rs.getString("student_user_id"), facUserId2, role_id); 
				 f ="<li><a class='main-btn active-btn btn-hover btn-iconic-icon AskQuery' data-bs-toggle='modal' data-bs-target='#askquerymodal'>"
							+""
//							+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("msg_count")+">"
							+ "<input type='hidden' id='apSUIdAGE1"+countFunction+"' value="+rs.getString("student_user_id")+">"
							+ "<input type='hidden' id='apROIdAGE"+countFunction+"' value="+rs.getString("role_id")+">"
							+ ""+cnt+"</a></li>";
				
				 
				 ul+=f + " " + f1 ;
					ul+="</ul>";
					
					action = ul;
					countFunction+=1;
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


public String GetMsgCount(String StuId,String facId,String role_id) {
	
		
		String count = "";
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			 
			q="select count(id) from edu_mentor_mentee_communication m where m.faculty_user_id= ? and status=0 and to_msg = ? and student_user_id = ?";

		 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(facId));
			stmt.setInt(2, Integer.parseInt(role_id));
			stmt.setInt(3, Integer.parseInt(StuId));
			
			System.err.println("COUNT-=-=-=-=-=-=-=-=-=-=-=-=-=" +stmt);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getString(1);
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
		return count;
	}



@Override
public long DataTableMentee_Request_count(String Search,String facUserId,String role_id) {
	
String SearchValue = GenerateQueryWhereClause_SQL(Search,facUserId);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			 
//			q="select count(*) from (select l.login_name as students, d.student_user_id,\n"
//					+ "(select count(id) from edu_mentor_mentee_communication m where m.faculty_user_id="+facUserId+" and status=0) as msg_count\n"
//					+ "from edu_mentor_mentee_communication d\n"
//					+ "inner join logininformation l on l.userid= d.student_user_id\n"
//					+ " "+ SearchValue + ")a";
			
//			q="select count(student_user_id) from edu_men_mentor_mentee_request where faculty_user_id="+facUserId+" and status='1'";

			
			q="select count(*) from (select l.login_name as students, d.student_user_id,\n"
				+ "	(select count(id) from edu_mentor_mentee_communication m where m.faculty_user_id= "+facUserId+" and status=0 and to_msg = "+role_id+") as msg_count,ur.role_id \n"
				+ "	from edu_mentor_mentee_communication d \n"
				+ "	inner join logininformation l on l.userid= d.student_user_id \n"
				+ "	inner join userroleinformation ur on ur.user_id=l.userid where id !=0 \n"
				+ SearchValue +" group by 1,2,4) a";
		 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,facUserId);
			System.err.println("q----urmikkk-- counttt----" +stmt);

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

private String GenerateQueryWhereClause_SQL1(String search,String facUserId2) {
	
    String SearchValue = "";
    if (search!=null && !search.equals("")) { // for Input Filter
        SearchValue += " and upper(l.login_name) like ?    ";
    }
    if (facUserId2!=null && !facUserId2.equals("")) { // for Input Filter
        SearchValue += " and d.faculty_user_id  = ?    ";
    }
     
    return SearchValue;
}

private PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String search,String facUserId2) {
		int flag = 0;
		try {
			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+search.toUpperCase()+"%");
			}
			if (facUserId2 != null && !facUserId2.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(facUserId2));
			}
		} catch (Exception e) {
		}
		return stmt;
	}


 


@Override
public List<Map<String, Object>> getMesgs(String facUserId,String student_userid) {
	
	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q="select id,to_char(created_date,'dd Mon,YYYY HH:MI') as timing,message,file,r1.role as from,r2.role as to \n"
					+" from edu_mentor_mentee_communication c\n"
					+" inner join roleinformation r1 on r1.role_id=c.from_msg \n"
					+" inner join roleinformation r2 on r2.role_id=c.to_msg \n"
					+" where student_user_id=? and faculty_user_id = ? and status in (0,1) order by id";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(student_userid));
			stmt.setInt(2, Integer.parseInt(facUserId));
			System.err.println("---APP MSGS------"+stmt);
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
public ArrayList<ArrayList<String>>getStudentlist(String role,String institute_id,String prof,String degree) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = "";
	try {

		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		if(role.contains("NCH")) {
			 q="select l.userid,sd.name\n"
			 		+ "from edu_lms_nch_student_details sd \n"
			 		+ "inner join logininformation l on sd.aadhar_card=l.aadhar_no\n"
			 		+ "inner join userroleinformation ur on ur.user_id=l.userid \n"
			 		+ "where sd.institude_userid=? and sd.verified_status=1 and sd.semester=? and sd.degree=? and ur.role_id in (25,26)";
		} 
		if(role.contains("NCISM")) {
		 q="select l.userid,sd.name\n"
		 		+ "from edu_lms_student_details sd \n"
		 		+ "inner join logininformation l on sd.aadhar_card=l.aadhar_no\n"
		 		+ "inner join userroleinformation ur on ur.user_id=l.userid \n"
		 		+ "where sd.institude_userid=? and sd.verified_status=1 and sd.semester=? and sd.degree=? and ur.role_id in (25,26)";
		}

		
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id));
			stmt.setString(2, prof);
			stmt.setInt(3, Integer.parseInt(degree));
			System.err.println("getMentees---"+stmt);
			ResultSet rs = stmt.executeQuery();
			
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("userid"));//0
			list.add(rs.getString("name"));//1
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
public ArrayList<ArrayList<String>>getMentorlist(String role,String institute_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = "";
	try {

		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		if(role.contains("NCH")) {
			 q="select upper(l.login_name) as login_name,l.userid \n"
			 		+ "from logininformation l\n"
			 		+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
			 		+ "inner join roleinformation r on r.role_id=ro.role_id and role= 'Faculty_NCH' and institute_id=? and enabled=1\n"
			 		+ "inner join tb_nch_add_teacher_details atd on atd.user_id=l.userid";
		} 
		if(role.contains("NCISM")) {
		 q="select upper(l.login_name) as login_name,l.userid \n"
		 		+ "from logininformation l\n"
		 		+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
		 		+ "inner join roleinformation r on r.role_id=ro.role_id and role= 'Faculty_NCISM' and institute_id=? and enabled=1\n"
		 		+ "inner join edu_lms_faculty_nch atd on atd.userid::int=l.userid";
		}

		
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id));
			System.err.println("getMentors---"+stmt);
			ResultSet rs = stmt.executeQuery();
			
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("userid"));//0
			list.add(rs.getString("login_name"));//1
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



}


	


