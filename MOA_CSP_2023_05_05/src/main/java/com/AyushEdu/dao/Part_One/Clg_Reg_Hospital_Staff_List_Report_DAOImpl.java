package com.AyushEdu.dao.Part_One;

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
public class Clg_Reg_Hospital_Staff_List_Report_DAOImpl implements Clg_Reg_Hospital_Staff_List_Report_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableSearch_Hospital_Staff_ListDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String name, String post, String department, String role, String userid,
			String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, post, department,
				 role, userid, institute_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String q1 = "";
		try {
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			q = "select distinct p.* from clg_reg_hospital_medical_staff_details p\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, name, post, department, role, userid, institute_id);

			System.err.println("stmt===============list" + stmt);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int j = startPage;
			int countFunction = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String action = "";
				String f2 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-eye'></i></a> </li>"
								+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				ul+= f2;
				 ul+="</ul>";
				 countFunction+=1;
				action = ul;
				columns.put("action", action);
//			alist.add(rs.getString("id")); //0

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

	public long DataTableSearch_Hospital_Staff_ListDataTotalCount(String Search, String name, String post, String department, String role,
			String userid, String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, post, department, role, userid, institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct p.* from clg_reg_hospital_medical_staff_details p\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 " + SearchValue + ")ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, name, post, department, role, userid, institute_id);
			System.err.println("stmt===============count" + stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String name, String post, String department, String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and  upper(p.name) like ? or  cast(p.post as varchar) like ? or cast(p.department as varchar) like ? "
					+ ") ";
		}

		/// advance search

		if (!name.trim().equals("")) {
			SearchValue += " and p.name like ? "; // 1
		}
		if (post != null  && post != ""  &&  !post.trim().equals("0") && !post.trim().equals("undefined")) {
			SearchValue += " and p.post = ? "; // 2
		}
		if (department != null  && department != ""  &&  !department.trim().equals("0") && !department.trim().equals("undefined")) {
			SearchValue += " and p.department = ? "; // 3
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name, String post, String department, String role,
			String userid, String institute_id) {

		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
			}
			
			if (!name.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + name + "%" ); // 1
			}
			if (post != null  && post != ""  &&  !post.trim().equals("0") && !post.trim().equals("undefined") && post != "0") {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(post)); // 2
			}
			if (department != null  && department != ""  &&  !department.trim().equals("0") && !department.trim().equals("undefined")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(department)); // 3
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	
	@Override
	public List<Map<String, Object>> View_Hospital_Medical_Staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select ms.*,pm.post from clg_reg_hospital_medical_staff_details ms\n"
					+ "inner join hosp_post_mstr pm on pm.id=ms.post::int\n"
					+ " where ms.institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, id);
	        }
	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, institute_id);
//			stmt.setString(2, userid);
	        }
			System.err.println("stmttttt" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> View_Hospital_paramedical_staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select msd.*,pm.post from clg_reg_hospital_paramedical_staff_details msd\n "
					+ "inner join hosp_post_mstr pm on pm.id=msd.post::int\n"
					+ "where msd.institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, id);
	        }
	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, institute_id);
//			stmt.setString(2, userid);
	        }
			System.err.println("stmttttt" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	public List<Map<String, Object>> View_Hospital_auxillary_medical_staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select asd.*,pm.post from clg_reg_hospital_auxillary_medical_staff_details asd "
					+ "inner join hosp_post_mstr pm on pm.id=asd.post::int\n"
					+ "where asd.institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, id);
	        }
	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, institute_id);
//			stmt.setString(2, userid);
	        }
			System.err.println("stmttttt" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	
	

}
