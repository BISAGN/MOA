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
public class Clg_Reg_Hospital_Infrastructure_Report_DAOImpl implements Clg_Reg_Hospital_Infrastructure_Report_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	
	@Override
	public List<Map<String, Object>> View_Infast_rede(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_infra where institute_id= ?";
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
	
	@Override
	public List<Map<String, Object>> View_Hospital_Administrator(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_administration where institute_id= ?";
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
	
	
	
	public List<Map<String, Object>> View_Hospital_OPD(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_opd where institute_id= ?";
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
	
	@Override
	public List<Map<String, Object>> View_Hospital_IPD(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select a.*,b.id as amb_id,b.*,c.id as doc_id,c.* from clg_reg_hosp_ipd a\n"
					+ "left join clg_reg_other_infra_details b on a.institute_id=b.institute_id \n"
					+ "left join clg_reg_hosp_other_ipd_detail c on c.institute_id=b.institute_id where a.institute_id=? ";
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
	
	public List<Map<String, Object>> View_Hospital_OT(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_ot where institute_id= ?";
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
	
	@Override
	public List<Map<String, Object>> View_Hospital_Reha_unit(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_rehabilation_unit where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_CL(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_clinical_laboratory where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Radiology_Sonography(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_radiology_sonography where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Hosp_Stores(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_stores where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Kitchen_Canteen(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_kitchen_canteen where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Infrastructure_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_hosp_other_infrastructure_details where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Infra_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_infra_details where institute_id= ?";
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
