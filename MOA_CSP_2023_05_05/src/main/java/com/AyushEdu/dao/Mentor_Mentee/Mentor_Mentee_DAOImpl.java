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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Mentor_Mentee.EDU_Mentor_Mentee_communication;
import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_STUDENT_TO_ENTERPRISE;
@Repository
public class Mentor_Mentee_DAOImpl implements Mentor_Mentee_DAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
@Override
public List<Map<String, Object>> getSystemofStudent(String userid,String role) {
	
		String tb_name = "";
		if(role.contains("NCISM")) {
			tb_name = " edu_lms_student_details ";
		}
		if(role.contains("NCH")) {
			tb_name = "edu_lms_nch_student_details";
		}
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select system from "+tb_name+" sd \n"
				+"inner join logininformation l on l.email_id=sd.email \n"
				+"where l.userid = ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(userid));

			ResultSet rs = stmt.executeQuery();
				
			System.err.println("getSystemofStudent---STMT----------"+stmt);
			
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

	
	public List<Map<String, Object>> getSearchMentorDetails(String a,String system) {
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
//			q="select * from logininformation as li inner join edu_lms_state_mstr sm on sm.state_id = li.state_id inner join edu_lms_institute_reg ir on ir.id = li.institute_id where TRIM(upper(li.login_name)) like ? OR TRIM(upper(sm.state_name)) like ? OR TRIM(upper(ir.institute_name)) like ?";
			
//			q = "select li.login_name,li.state_id,sm.state_name,li.institute_id,ir.institute_name,li.userid \n"
//					+ "from logininformation li \n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id = li.state_id \n"
//					+ "inner join edu_lms_institute_reg ir on ir.id = li.institute_id \n"
//					+ "inner join userroleinformation uri on uri.user_id = li.userid \n"
//					+ "where ( role_id = ? or role_id = ? ) \n --and ir.system_id = ?  \n"
//					+" and (upper(li.login_name) like ? or upper(sm.state_name) like ? or upper(ir.institute_name) like ? )";
			
			q = "select li.login_name,li.state_id,sm.state_name,li.institute_id,ir.institute_name,li.userid \n"
					+ "from logininformation li \n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id = li.state_id \n"
					+ "inner join edu_lms_institute_reg ir on ir.id = li.institute_id \n"
					+ "inner join userroleinformation uri on uri.user_id = li.userid \n"
					+ "where ( role_id = ? or role_id = ? ) \n and ir.system_id = ?  \n"
					+" and (upper(li.login_name) like ? or upper(sm.state_name) like ? or upper(ir.institute_name) like ? )";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, 51);
			stmt.setInt(2, 52);
			stmt.setInt(3, Integer.parseInt(system));
			stmt.setString(4, "%"+a.toUpperCase()+"%");
			stmt.setString(5, "%"+a.toUpperCase()+"%");
			stmt.setString(6, "%"+a.toUpperCase()+"%");
			
//			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1, 51);
//			stmt.setInt(2, 52);
////			stmt.setInt(3, Integer.parseInt(system));
//			stmt.setString(3, "%"+a.toUpperCase()+"%");
//			stmt.setString(4, "%"+a.toUpperCase()+"%");
//			stmt.setString(5, "%"+a.toUpperCase()+"%");

			ResultSet rs = stmt.executeQuery();
				
			System.err.println("STMT----------"+stmt);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			
			 int countFunction=1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				 
				 f ="<li><a href='#0' class='main-btn secondary-btn-outline rounded-full btn-hover ADDpaper_layout'>Send Request</a>" 
						 +"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getInt("userid")+"></i></a></li>";
				 
				ul+=f;
				ul+="</ul>";
				
				action = ul;
				
				
				columns.put("action", action);
			
					list.add(columns);
					countFunction+=1;	
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
	
	@Override
	public List<Map<String, Object>> DataTableMentorDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String login_name, String stu_user_id,String to_msg_uid) {
		
			String SearchValue = GenerateQueryWhereClause_SQL(Search,login_name,stu_user_id);
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
				
//				q = " select m.id,l.login_name as faculty_name,m.faculty_user_id \n"
//						+ "from edu_men_mentor_mentee_request m\n"
//						+ "inner join logininformation l on l.userid=m.faculty_user_id \n"
//						+ "inner join logininformation l2 on l2.userid=m.student_user_id\n"
//						+ "where  m.status=1  \n"
//						+  SearchValue  + " ORDER BY student_user_id " + orderType + " limit "
//						+ pageL + " OFFSET " + startPage;
		
				q="select l.login_name as faculty_name,m.faculty_user_id,m.student_user_id,\n"
						+ "(select count(id) from edu_mentor_mentee_communication m where m.student_user_id="+stu_user_id+" and status=0 and to_msg = "+to_msg_uid+") as msg_count,ur.role_id\n"
						+ "from edu_men_mentor_mentee_request m\n"
						+ "inner join logininformation l on l.userid=m.faculty_user_id \n"
						+ "inner join logininformation l2 on l2.userid=m.student_user_id\n"
						+ "inner join userroleinformation ur on ur.user_id=l.userid \n"
						+ "where  m.status=1\n"+  SearchValue  + " group by 1,2,3,5 ORDER BY student_user_id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
		
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt, Search,login_name,stu_user_id);
				
				System.err.println("-------aaaaa--HARSH---------"+stmt);
				
				ResultSet rs = stmt.executeQuery();
				
				
				ResultSetMetaData metaData = rs.getMetaData();
				
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				
				int countFunction=1;
				
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					columns.put("ser", ++j);
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
				
					String f = "";
					String action = "";
					String f1 = "";
					
					 String cnt = GetMsgCount( rs.getString("student_user_id"), rs.getString("faculty_user_id"), to_msg_uid); 
					
					String ul="";
					ul+="<ul class='buttons-group mainbtn action daobtn'>";
					
					if(Integer.parseInt(cnt) > 0) {
						
						
						f = "<li><a class='main-btn active-btn btn-hover btn-iconic-icon Msss' data-bs-toggle='modal' data-bs-target='#askquerymodal'>"
								+ "<input type='hidden' id='apROIdAGE"+countFunction+"' value="+rs.getString("role_id")+">"
								+ "<input type='hidden' id='apSUIdAGE2"+countFunction+"' value="+rs.getString("student_user_id")+">"
								+ cnt+"</a></li>";
						
						 f1 ="<li class='d-none'><a class='main-btn active-btn btn-hover btn-iconic-icon AskQuery' data-bs-toggle='modal' data-bs-target='#askquerymodal'>"
									+"<i class='lni lni-question-circle'>"
//									+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
									+ "<input type='hidden' id='apFUIdAGE"+countFunction+"' value="+rs.getString("faculty_user_id")+">"
									+ "<input type='hidden' id='apROIdAGE"+countFunction+"' value="+rs.getString("role_id")+">"
									+ "</i>Ask Query</a></li>";
						
					}
					if(Integer.parseInt(cnt) == 0) {
						
						
						f = "<li class='d-none'><a class='main-btn active-btn btn-hover btn-iconic-icon Msss' data-bs-toggle='modal' data-bs-target='#askquerymodal'>"
								+ "<input type='hidden' id='apROIdAGE"+countFunction+"' value="+rs.getString("role_id")+">"
								+ "<input type='hidden' id='apSUIdAGE2"+countFunction+"' value="+rs.getString("student_user_id")+">"
								+ cnt+"</a></li>";
						
						 f1 ="<li ><a class='main-btn active-btn btn-hover btn-iconic-icon AskQuery' data-bs-toggle='modal' data-bs-target='#askquerymodal'>"
									+"<i class='lni lni-question-circle'>"
//									+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
									+ "<input type='hidden' id='apFUIdAGE"+countFunction+"' value="+rs.getString("faculty_user_id")+">"
									+ "<input type='hidden' id='apROIdAGE"+countFunction+"' value="+rs.getString("role_id")+">"
									+ "</i>Ask Query</a></li>";
						
					}
					
					 
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
	public long DataTableMentorDataTotalCount(String Search,String login_name, String faculty_user_id,String stu_user_id) {
		
	String SearchValue = GenerateQueryWhereClause_SQL(Search,login_name,stu_user_id);
			
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				
				
				q="select count(*) from  ( select m.id,l.login_name as faculty_name\n"
						+ "from edu_men_mentor_mentee_request m\n"
						+ "inner join logininformation l on l.userid=m.faculty_user_id \n"
						+ "inner join logininformation l2 on l2.userid=m.student_user_id\n"
						+ "where  m.status=1 "+ SearchValue + ")a";

				
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt, Search,login_name,stu_user_id);
				System.err.println("COUNT--------q----------" +stmt);

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


	private String GenerateQueryWhereClause_SQL(String search,String login_name,String stu_user_id) {
		
        String SearchValue = "";
        if (search!=null && !search.equals("")) { // for Input Filter
            SearchValue += " and upper(l.login_name) like ?    ";
        }
        if (login_name!=null && !login_name.equals("")) { // for Input Filter
            SearchValue += " and l2.login_name  = ?    ";
        }
        if (stu_user_id!=null && !stu_user_id.equals("")) { // for Input Filter
            SearchValue += " and m.student_user_id  = ?    ";
        }
        return SearchValue;
	}
	
	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search,String login_name,String faculty_user_id) {
			int flag = 0;
			try {
				if (search != null && !search.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
				}
				if (login_name != null && !login_name.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+login_name.toUpperCase()+"%");
				}
				if (faculty_user_id != null && !faculty_user_id.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(faculty_user_id));
				}
			} catch (Exception e) {
			}
			return stmt;
		}
	
	@Override
	public List<Map<String, Object>> getMesgsformenties(String facUserId,String student_userid,String role_id) {
		
		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				
				q="select id,to_char(created_date,'dd Mon,YYYY HH:MI') as timing,message,file,r1.role as from,r2.role as to \n"
					+" from edu_mentor_mentee_communication c \n"
					+" inner join roleinformation r1 on r1.role_id=c.from_msg \n"
					+" inner join roleinformation r2 on r2.role_id=c.to_msg \n"		
					+" where student_user_id=? and faculty_user_id = ? and status in (0,1) order by id \n --and to_msg = ?";
		
		
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt.setInt(1, Integer.parseInt(student_userid));
				stmt.setInt(2, Integer.parseInt(facUserId));
//				stmt.setInt(3,Integer.parseInt(role_id));
				
				System.err.println("----MentorMSGS-----"+stmt);
				
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
	public String getFilePathQuery(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select file from edu_mentor_mentee_communication where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("file");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	
}



	



