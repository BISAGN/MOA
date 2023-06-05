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
public class Clg_Reg_Staff_Info_Report_DaoImpl implements Clg_Reg_Staff_Info_Report_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableSearch_College_Staff_InfoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String ug_pg_status, String department_id, String role, String userid,
			String institute_id,String ug_status,String pg_status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, ug_pg_status, department_id,
				 role, userid,institute_id,ug_status,pg_status);
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

//			q="select sm.state_name,p.city,p.pincode,p.mobile_no,p.email_id,p.institute_type,p.name_of_managing_body,p.name_of_management_contact,\n"
//					+ "p.mngt_city,p.mngt_mobile_no,p.mngt_email_id,p.name_of_society,p.society_reg_no,p.name_of_uni_affilate\n"
//					+ "from clg_reg_inst_info_institution_basic_details p\n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
//					+ "inner join logininformation lo on lo.userid=p.userid\n"
//					+ "where p.id!=0 and p.inst_id="+institute_id+" and p.userid="+userid+"  "+ SearchValue +" order by id " + orderType
//					+ 	 " limit " + pageL + " OFFSET " + startPage;

			if (role.equals("NCH")) {

				q = "select p.*,sm.state_name\n" + "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0  " + SearchValue
						+ " order by p.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			}

			if (role.equals("Institute_NCH")) {

				q = "select p.*,sm.state_name\n" + "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0 and p.inst_id="
						+ institute_id + " and p.userid=" + userid + "  " + SearchValue + " order by p.id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, ug_pg_status, department_id,
					 role, userid,institute_id,ug_status,pg_status);

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

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f2 = "<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-eye'></i></a> </li>" + "<input type='hidden' id='viewId" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
				ul += f + " " + f1 + " " + " " + f2;
				ul += "</ul>";
				countFunction += 1;
				action = ul;
				columns.put("action", action);

//				alist.add(rs.getString("id")); //0

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

	public long DataTableSearch_College_Staff_InfoDataTotalCount(String Search, String ug_pg_status, String department_id, String role, String userid,
			String institute_id,String ug_status,String pg_status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, ug_pg_status, department_id,
				 role, userid,institute_id,ug_status,pg_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q="select count(*) from (select sm.state_name,p.city,p.pincode,p.mobile_no,p.email_id,p.institute_type,p.name_of_managing_body,p.name_of_management_contact,\n"
//					+ "p.mngt_city,p.mngt_mobile_no,p.mngt_email_id,p.name_of_society,p.society_reg_no,p.name_of_uni_affilate\n"
//					+ "from clg_reg_inst_info_institution_basic_details p\n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
//					+ "inner join logininformation lo on lo.userid=p.userid\n"
//					+ "where p.id!=0 and p.inst_id="+institute_id+" and p.userid= "+userid+"  "+ SearchValue +")ab";
			if (role.equals("NCH")) {

				q = "select count(*) from (select p.*,sm.state_name\n"
						+ "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0  " + SearchValue
						+ ")ab";
			}
			if (role.equals("Institute_NCH")) {
				q = "select count(*) from (select p.*,sm.state_name\n"
						+ "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0 and p.inst_id="
						+ institute_id + " and p.userid= " + userid + "  " + SearchValue + ")ab";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, ug_pg_status, department_id,
					 role, userid,institute_id,ug_status,pg_status);
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
	public String GenerateQueryWhereClause_SQL(String Search, String ug_pg_status, String department_id, String role, String userid,
			String institute_id,String ug_status,String pg_status) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  cd.department_name like ? ) "
					+ "";
		}
		/// advance search

//		if (!ug_status.trim().equals("0")) {
//			SearchValue += " and upper(p.ug_pg_status::text) like ? "; // 1
//		}
		if (!ug_status.trim().equals("0")) {
			SearchValue += " and upper(dm.ug_status::text) like ? "; // 1
		}
		if (!pg_status.trim().equals("0")) {
			SearchValue += " and upper(dm.pg_status::text) like ? "; // 1
		}
		if (!department_id.trim().equals("")) {
			SearchValue += " and (p.department_id) = ? "; // 2
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String ug_pg_status, String department_id, String role, String userid,
			String institute_id,String ug_status,String pg_status) {

		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
			}

			if (!ug_status.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(ug_status)); // 1
			}
			if (!pg_status.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(pg_status)); // 1
			}
			if (!department_id.trim().equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(department_id)); // 2
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	//GET ALL OTHER DEPARTMENT LIST--2 
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ug() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where ug_status = '1' order by id\n";

			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0

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
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_pg() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where pg_status = '2' order by id\n";

			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0

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
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	//----------------------------------------VIEW------------------------------------------------------------------------//
	
	@Override
	public List<Map<String, Object>> View_Teaching_Staff(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_teaching_staff_ug where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
	
	@Override
	public List<Map<String, Object>> View_Teaching_Staff_Pg(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_teaching_staff_pg where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
	
	
	@Override
	public List<Map<String, Object>> View_Non_Teaching_Staff_Info(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_non_teaching_staff_info where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
	
	@Override
	public List<Map<String, Object>> View_Last_Acadmic_Year_Staff(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_last_academic_year_staff_details where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
	
	@Override
	public List<Map<String, Object>> View_Staff_Salary_info(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();
			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_staff_salary_information where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
	        }
	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, institute_id);
//			stmt.setString(2, userid);
	        }
			System.err.println("stmttttt" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT----View_Staff_Salary_info-" + stmt);
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
	
	@Override
	public List<Map<String, Object>> View_Clg_Techer_Prom(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_college_teacher_promotion where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
	
	@Override
	public List<Map<String, Object>> View_Clg_Staff_Document(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_staff_upload_document_info where institute_id= ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
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
