package com.AyushEdu.dao.AyushId_Directory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AyushId_DirectoryDAOImpl implements AyushId_DirectoryDAO {
	
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String getAyushRoleStatus(String roleid) {
		Connection conn = null;
		String q="";
		String qry="";
		String status="";
		   try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				 q = "select ari.status from roleinformation ri\n"
				 		+ "inner join ayush_roleinformation ari  on ari.roleid = ri.role_id\n"
				 		+ "where ri.role_id=?";
				
				   stmt=conn.prepareStatement(q);
			       stmt.setString(1, roleid);
									
			      ResultSet rs = stmt.executeQuery();      
			      while (rs.next()) {
//		 	       Map<String, Object> columns = new LinkedHashMap<String, Object>();
//		 	            for (int i = 1; i <= columnCount; i++) {
//		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//		 	            }
			    	  status= rs.getString("status");//0
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
			return status;		
	}
	
	
////////////////////////////////Ayush Id Generate
	
	public String getAyushID(String userid,HttpSession session) {

		Connection conn = null;
		String reg_no = "";
		String query = "";
		String staff_lvl = session.getAttribute("roleStaff_lvl").toString();
		
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if (staff_lvl.toUpperCase().equals("NCH")) {
				 query = "select to_char(CURRENT_TIMESTAMP,'yy')||'HOM'||ir.college_unique_id||sm.state_abbr|| lpad((select case when (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| ('HOM'||ir.college_unique_id) ||'%'  and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy'))='' or (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| ('HOM'||ir.college_unique_id) ||'%'  and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy')) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| ('HOM'||ir.college_unique_id) ||'%'  and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy')) end::int+1)::text, 5, '0')as ayushid  from logininformation li\n"
				 		+ "inner join edu_lms_institute_reg ir on ir.id=li.institute_id and userid = ?\n"
				 		+ "inner join edu_lms_state_mstr sm on sm.state_id = li.state_id";
						}
			
			if (staff_lvl.toUpperCase().equals("NCISM")) {
				 query = "select to_char(CURRENT_TIMESTAMP,'yy')||ir.college_unique_id||sm.state_abbr|| lpad((select case when (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| (ir.college_unique_id) ||'%' and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy'))='' or (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| (ir.college_unique_id) ||'%' and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy')) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
				 		+ "from ayush_id_directory_parent where upper(ayush_id) like '%'|| (ir.college_unique_id) ||'%' and Substring(ayush_id,1,2)=to_char(CURRENT_TIMESTAMP,'yy')) end::int+1)::text, 5, '0')as ayushid  from logininformation li\n"
				 		+ "inner join edu_lms_institute_reg ir on ir.id=li.institute_id and userid = ?\n"
				 		+ "inner join edu_lms_state_mstr sm on sm.state_id = li.state_id ";	
						}
			
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(userid));
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("ayushid");
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reg_no;
	}


	//Fetch Ayush ID Directory Data
	@Override
	public List<Map<String, Object>> DataTableAyushIdDirectoryList(int startPage, int pageLength, String search, String orderColunm, String orderType, String aadharNo, String ayushId, String userId, String roleId
				,String user_name , String login_name , String email_id  ) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, aadharNo , ayushId , userId , roleId,  user_name , login_name , email_id);
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


				q = "select li.username , li.login_name,li.email_id ,aip.ayush_id,aip.aadhaar_no,aip.user_id,ri.role  from ayush_id_directory_parent aip" +
						" inner join ayush_id_entity_directory_child aic on aip.id = aic.p_id"
						+ " inner join roleinformation ri on aic.role_id = ri.role_id "
						+ "inner join logininformation li on  aip.user_id = li.userid"
						+ SearchValue
						+ " ORDER BY ayush_id " + orderType + " limit " + pageL + " OFFSET " + startPage;




			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search,  aadharNo , ayushId , userId , roleId ,  user_name , login_name , email_id);

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

				countFunction += 1;
				countFunctionDelete += 1;

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

		System.out.println(list.toString());
		return list;
	}

	@Override
	public long DataTableAyushIdDirectoryTotalCount(String search, String aadharNo, String ayushId, String userId, String roleId, String user_name , String login_name , String email_id  ) {
		String SearchValue =GenerateQueryWhereClause_SQL(search, aadharNo , ayushId , userId , roleId ,  user_name , login_name , email_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q="select count(*) from (select li.username,li.login_name,li.email_id,aip.ayush_id,aip.aadhaar_no,aip.user_id,ri.role from ayush_id_directory_parent aip" +
					" inner join ayush_id_entity_directory_child aic on aip.id = aic.p_id  "
					+"inner join roleinformation ri on aic.role_id = ri.role_id  "
					+"inner join logininformation li on  aip.user_id = li.userid"+ SearchValue + ") ab  ";




			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, aadharNo , ayushId , userId , roleId ,  user_name , login_name , email_id);


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

	public String GenerateQueryWhereClause_SQL(String Search, String aadhar_no , String ayush_id , String user_id , String role_id ,String user_name , String login_name , String email_id ) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for
			if(Search.matches("^[a-zA-Z]*$")){
				SearchValue += " and (  upper(aadhaar_no) like ? or upper(ayush_id) like ? or upper(ri.role) like  ? or upper(li.username) like  ? or upper(li.login_name) like  ? or upper(li.email_id) like  ? ) ";
			}else{
				SearchValue += " and (  upper(aadhaar_no) like ? or upper(ayush_id) like ? or upper(ri.role) like  ? or upper(li.username) like  ? or upper(li.login_name) like  ? or upper(li.email_id) like  ?  or CAST(user_id as varchar(20)) like ? ) ";
			}


		}

		if (aadhar_no != null && !aadhar_no.equals("")) {
			SearchValue += " and upper(aadhaar_no) like ? ";

		}

		if (ayush_id != null && !ayush_id.equals("")) {
			SearchValue += " and upper(ayush_id) like ? ";

		}

		if (user_id != null && !user_id.equals("")) {
			SearchValue += " and user_id = ?::bigint ";

		}

		if (role_id != null && !role_id.equals("0")) {
			SearchValue += " and ri.role_id = ?::integer ";

		}

		if (user_name != null && !user_name.equals("")) {
			SearchValue += " and upper(li.username) like ? ";

		}

		if (login_name != null && !login_name.equals("")) {
			SearchValue += " and upper(li.login_name) like ? ";

		}

		if (email_id != null && !email_id.equals("")) {
			SearchValue += " and upper(li.email_id) like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String aadhar_no , String ayush_id , String user_id , String role_id ,String user_name , String login_name , String email_id ) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase().trim() + "%");
				if(!Search.matches("^[a-zA-Z]*$")){
					flag += 1;
					stmt.setString(flag ,"%" + Search.toUpperCase().trim() + "%");
				}

			}
			if (aadhar_no != null && !aadhar_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + aadhar_no.toUpperCase().trim() + "%");
			}
			if (ayush_id != null && !ayush_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + ayush_id.toUpperCase().trim() + "%");
			}
			if (user_id != null && !user_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_id.trim() );
			}
			if (role_id != null && !role_id.equals("0")) {
				flag += 1;
				stmt.setString(flag,role_id.trim() );
			}
			if (user_name != null && !user_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + user_name.toUpperCase().trim() + "%");
			}
			if (login_name != null && !login_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + login_name.toUpperCase().trim() + "%");
			}
			if (email_id != null && !email_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + email_id.toUpperCase().trim() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}


}
