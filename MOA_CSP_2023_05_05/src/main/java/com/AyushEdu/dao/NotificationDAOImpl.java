package com.AyushEdu.dao;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class NotificationDAOImpl implements NotificationDAO{
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public List<Map<String, Object>> notiboxlist(String ticket, String ticket_status, String from_date, String to_date,
			String help_topic, String userId, String role, String module, String label1, String screen_url) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			// Getting Data Of New ticket generated List from HelpDesk

			// qry += " and cast(a.userid as character varying) = ? ";
//					qry = "and ?=any(string_to_array(e.to_name_sent,',')::text[]) and (?!=any(string_to_array(e.to_name_receive,',')::text[]) or e.to_name_receive is null) ";

//				q = "select 	e.id,e.masage,e.from_name_send from tb_notification_emb e\r\n" + 
//						"where e.masage !='null'  "+ qry ;

//				q = "select e.id,e.message,e.from_name_send,url_id,url_value,cast (AGE(current_timestamp,e.created_date::timestamp) as character varying ) age from tb_notification e\r\n" + 
//						"where e.message !='null'  "+ qry +"  order by id desc ";
//			q = "select e.id,e.message,e.from_name_send,url_id,url_value,cast (AGE(current_timestamp,e.created_date) as character varying ) age from tb_notification e\n"
//					+ "where ? not in (select unnest(string_to_array(to_name_receive, ',')) \n"
//					+ "	 from tb_notification where e.id=id ) and \n"
//					+ " ? in (select unnest(string_to_array(to_name_sent, ',')) \n"
//					+ "	 from tb_notification where e.id=id )";
q="  select n.*,login_name from (select e.id,e.message,e.from_name_send,url_id,url_value,cast (AGE(current_timestamp,e.created_date) as character varying ) age from tb_notification e\n"
		+ "where ? not in (select unnest(string_to_array(to_name_receive, ',')) \n"
		+ "	 from tb_notification where e.id=id ) and \n"
		+ " ? in (select unnest(string_to_array(to_name_sent, ',')) \n"
		+ "	 from tb_notification where e.id=id )) n inner join logininformation l on l.userid::text=n.from_name_send\n"
		+ "";

//				
//				q="select count(n.id) from tb_notification n\n"
//						+ "where '158' not in (select unnest(string_to_array(to_name_recieve, ',')) \n"
//						+ "		 from tb_notification where n.id=id ) and \n"
//						+ "		 '-1' in (select unnest(string_to_array(to_name_send, ',')) \n"
//						+ "		 from tb_notification where n.id=id )";
//				
			//
			int j = 1;
			stmt = conn.prepareStatement(q);

			stmt.setString(1, userId);
			stmt.setString(2, userId);
			
//					j += 1;
//					stmt.setString(j, role);

		System.out.println("notification=======  "+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				// Edit Button
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') )" + "{Search_manage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				// String updateButton = "<i class='action_icons action_update' " + Update + "
				// title='Edit Data'></i>";

				String f = "";
				columns.put("action", f);

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
	
//	public List<Map<String, Object>> notiboxlist(String ticket, String ticket_status, String from_date,
//			String to_date, String help_topic, String userId, String role, String module, String label1,String screen_url) {
//		
//			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//			Connection conn = null;
//			String q = "";
//			String qry = "";
//			// System.out.println("roleid----" +role);
//			try {
//				conn = dataSource.getConnection();
//				PreparedStatement stmt = null;
//				 //Getting Data Of New ticket generated List from HelpDesk 
//				
//					//qry += " and cast(a.userid as character varying) = ? ";
//					qry = "and ?=any(string_to_array(e.to_name_sent,',')::text[]) and (?!=any(string_to_array(e.to_name_receive,',')::text[]) or e.to_name_receive is null) ";
//					
////				q = "select 	e.id,e.masage,e.from_name_send from tb_notification_emb e\r\n" + 
////						"where e.masage !='null'  "+ qry ;
//				
//				q = "select e.id,e.message,e.from_name_send,url_id,url_value,cast (AGE(current_timestamp,e.created_date::timestamp) as character varying ) age from tb_notification e\r\n" + 
//						"where e.message !='null'  "+ qry +"  order by id desc ";
//				
//				//
//				int j = 1;
//				stmt = conn.prepareStatement(q);			 
//               
//				
//					stmt.setString(j, role);
//					j += 1;
//					stmt.setString(j, role);
//					
//				
//				ResultSet rs = stmt.executeQuery();
//				ResultSetMetaData metaData = rs.getMetaData();
//				
//				int columnCount = metaData.getColumnCount();
//				while (rs.next()) {
//					Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
//					for (int i = 1; i <= columnCount; i++) {
//						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//					} 
//					 //Edit Button  
//					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') )"
//							+ "{Search_manage(" + rs.getObject(1) + ")}else{ return false;}\"";
//					//String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
//					 
//					String f = "";
//					//f += updateButton;
//					columns.put("action", f);
//					 
//					list.add(columns);
//				}
//				 
//				 
//				rs.close();
//				
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//					}
//				}
//			}
//			return list;
//		}
//	
	
	public List<Map<String, Object>> notiseeall_list(String ticket, String ticket_status, String from_date,
			String to_date, String help_topic, String userId, String role, String module, String label1,String screen_url) {
		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
//			 System.out.println("roleid----" +role);
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				 //Getting Data Of New ticket generated List from HelpDesk 
				
					//qry += " and cast(a.userid as character varying) = ? ";
					qry = "and ?=any(string_to_array(e.from_name_send,',')::text[]) and (?!=any(string_to_array(e.to_name_receive,',')::text[]) or e.to_name_receive is null) ";
					
//				q = "select 	e.id,e.masage,e.from_name_send from tb_notification_emb e\r\n" + 
//						"where e.masage !='null'  "+ qry ;
				
				q = "select e.id,e.message,e.from_name_send,url_id,url_value,cast (AGE(current_timestamp,e.created_date::timestamp) as character varying ) age from tb_notification e\r\n" + 
						"where e.message !='null'  "+ qry +"   ";
				
				//
				int j = 1;
				stmt = conn.prepareStatement(q);			 
               
				
					stmt.setString(j, role);
					j += 1;
					stmt.setString(j, role);
					
				
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					} 
					 //Edit Button  
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') )"
							+ "{Search_manage(" + rs.getObject(1) + ")}else{ return false;}\"";
					//String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					 
					String f = "";
					//f += updateButton;
					columns.put("action", f);
					 
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
	public String getRoleUserId(String roleid) {
		String list="";
		String s="";
		String s1="";
		System.out.println("roleid "+roleid);
		if(!roleid.equals("")) {
			s1+="and ro.role_id in (";
					
					for(int i=0;i<roleid.split(",").length;i++) {
						if(i==0) {
							s1+="'"+roleid.split(",")[i]+"'";
						}else {
							s1+=",'"+roleid.split(",")[i]+"'";
						}
					}
					s1+=")";
					
		}
		System.out.println("s1  "+s1);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select string_agg(userid::text,',') as userid from logininformation lo\n"
					+ "inner join userroleinformation ro on lo.userid=ro.user_id\n"
					+ "inner join roleinformation r on r.role_id=ro.role_id $s";
			sql=sql.replace("$s", s1);
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			System.out.println("stmt   "+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
					list=rs.getString("userid");
			
			}
			System.out.println("list   "+list);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
