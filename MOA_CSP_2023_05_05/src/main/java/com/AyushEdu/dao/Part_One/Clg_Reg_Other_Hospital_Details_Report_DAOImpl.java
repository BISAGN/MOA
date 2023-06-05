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
public class Clg_Reg_Other_Hospital_Details_Report_DAOImpl implements Clg_Reg_Other_Hospital_Details_Report_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	

	@Override
	public List<Map<String, Object>> View_Other_hosp_maintenance_Staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_maintenance_records ms where institute_id= ?";
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
	
	
	
	public List<Map<String, Object>> View_Hospital_labour_room_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_labour_room msd where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_Operation_theatre_staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_operation_theatre asd where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Hospital_investigation_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_investigation roi where institute_id= ?";
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
	
	
	public List<Map<String, Object>> View_Hospital_clinical_laboratory(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_clinical_laboratory cl where institute_id= ?";
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
	
	
	public List<Map<String, Object>> View_Hospital_dtl_functionality(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select * from clg_reg_other_hos_dtl_functionality hdf where institute_id= ?";
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
