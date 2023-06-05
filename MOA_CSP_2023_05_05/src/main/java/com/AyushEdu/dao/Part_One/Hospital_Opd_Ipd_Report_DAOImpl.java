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
public class Hospital_Opd_Ipd_Report_DAOImpl implements Hospital_Opd_Ipd_Report_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	
	@Override
	public List<Map<String, Object>> View_OPD_Patients(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select hd.department_name, rho.* from clg_reg_hos_opd_ipd rho\n"
					+ "inner join clg_reg_hos_dept_mstr hd on hd.id= rho.dep_id\n"
					+ "where rho.institute_id= ?";
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
	public List<Map<String, Object>> View_OPD_Patientssum(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select sum(jan) as jan,sum(feb) as feb,sum(mar) as mar,sum(appr) as appr,sum(may) as may,sum(jun) as jun,\n"
					+ "sum(july) as july,sum(aug) as aug,sum(sep) as sep,sum(oct) as oct,sum(nov) as nov,sum(dec) as dec,\n"
					+ "GREATEST(0, sum(jan+feb+mar+appr+may+jun+july+aug+sep+oct+nov+dec)) as grandtotal,\n"
					+ "ROUND(GREATEST(0, sum(jan+feb+mar+appr+may+jun+july+aug+sep+oct+nov+dec)/300),2) as opd_patients_total  \n"
					+ "from clg_reg_hos_opd_ipd where institute_id= ?";
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
	
	
	
	public List<Map<String, Object>> View_IPD_Patients(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select hd.department_name, rho.* from clg_reg_hos_ipd_patients_tbl rho\n"
					+ "inner join clg_reg_hos_dept_mstr hd on hd.id= rho.dep_id\n"
					+ "where rho.institute_id= ?";
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
	public List<Map<String, Object>> View_IPD_Patientssum(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select sum(jan) as jan,sum(feb) as feb,sum(mar) as mar,sum(appr) as appr,sum(may) as may,sum(jun) as jun,\n"
					+ "sum(july) as july,sum(aug) as aug,sum(sep) as sep,sum(oct) as oct,sum(nov) as nov,sum(dec) as dec,\n"
					+ "GREATEST(0, sum(jan+feb+mar+appr+may+jun+july+aug+sep+oct+nov+dec)) as grandtotal, \n"
					+ "ROUND(GREATEST(0, sum(jan+feb+mar+appr+may+jun+july+aug+sep+oct+nov+dec)/300),2) as ipd_patients_total  \n"
					+ "from clg_reg_hos_ipd_patients_tbl where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Bed_Days_Occupied(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select hd.department_name, rho.* from clg_reg_hos_bed_days_occupied_tbl rho\n"
					+ "inner join clg_reg_hos_dept_mstr hd on hd.id= rho.dep_id\n"
					+ "where rho.institute_id= ?";
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
	public List<Map<String, Object>> View_Bed_Days_Occupiedsum(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select sum(jan) as jan,sum(feb) as feb,sum(mar) as mar,sum(appr) as appr,sum(may) as may,sum(jun) as jun,\n"
					+ "sum(july) as july,sum(aug) as aug,sum(sep) as sep,sum(oct) as oct,sum(nov) as nov,sum(dec) as dec,\n"
					+ "GREATEST(0, sum(jan+feb+mar+appr+may+jun+july+aug+sep+oct+nov+dec)) as grandtotal \n"
					+ "from clg_reg_hos_bed_days_occupied_tbl where institute_id= ?";
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
	
	public List<Map<String, Object>> View_Beds_Existed(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select hd.department_name, rho.* from clg_reg_hos_bed_existed_tbl rho\n"
					+ "inner join clg_reg_hos_dept_mstr hd on hd.id= rho.dep_id\n"
					+ "where rho.institute_id= ?";
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
	public String getFilePath_DynemicQueryForCommonDocPartOne(String id, String val, String fildname) {
		
		
		System.err.println("id   "+ id + "  val "+val +" fildname "+ fildname);
		
		String whr = "";
		String q1=val;
		Connection conn = null;
		String fildname1=fildname;
		
		//hospital_ipd_opd
//		if(val.equals("1")) {
//			q1="clg_reg_hos_opd_ipd_upload_documents";
//		}
		
//		//hospital_Staff Details
//		else if(val.equals("2")){
//			q1="hosp_staff_details_upload_document";
//		}
		
		//declaration
//		else if(val.equals("3")) {
//			q1="clg_reg_college_declaration";
//		}
		
		//other Hospital Details
//		 if(val.equals("4")) {
//			q1="clg_reg_other_hos_dtl_functionality";
//		}
		
		//Hospital Equipment
//		else if(val.equals("5")) {
//			q1="hosp_equipments_document";
//		}
		
		//Department Equipment
//		else if(val.equals("6")) {
//			q1="clg_reg_dep_equip_obstetric_and_gynacology";
//		}
		//clg infrastructure
//		else if(val.equals("7")){
//			q1="clg_reg_infra_college_council";
//		}
//		else if(val.equals("8")){
//			q1="clg_reg_infra_collge_council_camera";
//		}
//		else if(val.equals("9")){
//			q1="clg_reg_infra_herbal_garden";
//		}
//		else if(val.equals("10")){
//			q1="clg_reg_infra_additional_information";
//		}

		
		
		//hospital_ipd_opd
//		if (fildname.equals("1")) {
//			fildname1 = "support_doc";
//		}
//		else if (fildname.equals("2")) {
//			fildname1 = "xray_usg_opdipd";
//		}
//		else if (fildname.equals("3")) {
//			fildname1 = "register_opdipd";
//		}
//		else if (fildname.equals("4")) {
//			fildname1 = "medi_stock_opdipd";
//		}
//		else if (fildname.equals("5")) {
//			fildname1 = "last_inveopdipd";
//			
//		}
		//hospital_Staff Details
//		else if (fildname.equals("6")) {
//			fildname1 = "attend_hospitalstaff";
//		}
//		else if (fildname.equals("7")) {
//			fildname1 = "acquittancestaff";
//		}
//		else if (fildname.equals("8")) {
//			fildname1 = "doctor_rosters";
//		}
//		else if (fildname.equals("9")) {
//			fildname1 = "nurse_rosters";
//		}
		//declaration
//		else if (fildname.equals("10")) {
//			fildname1 = "notarizedundertaking";
//		}
//		else if (fildname.equals("11")) {
//			fildname1 = "notarizedaffidavit";
//		}
		
		//other Hospital Details
//		 if (fildname.equals("12")) {
//			fildname1 = "doc_of_multispecialty_hospital";
//		}
//		else if (fildname.equals("13")) {
//			fildname1 = "ipd_diet_register_doc";
//		}
		
		
		//Hospital Equipment
//		else if (fildname.equals("14")) {
//			fildname1 = "sother_equip";
//		}
//		else if (fildname.equals("15")) {
//			fildname1 = "patho_bio_equip";
//		}
		
		//Department Equipment

//		if (fildname.equals("16")) {
//			fildname1 = "copy_of_alchoho";
//		}
//		else if (fildname.equals("17")) {
//			fildname1 = "photographs_of_cadavers";
//		}
//		else if (fildname.equals("18")) {
//			fildname1 = "upload_purchase_bill";
//		}
		
		///clg Infrastructure
//		else if (fildname.equals("19")) {
//			fildname1 = "council_document";
//		}
//		else if (fildname.equals("20")) {
//			fildname1 = "plant_species_list";
//		}
//		else if (fildname.equals("21")) {
//			fildname1 = "herbal_garden_list";
//		}
//		else if (fildname.equals("22")) {
//			fildname1 = "compliance_report_doc";
//		}
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select $fildname from $table where id=?";
			query = query.replace("$fildname", fildname1);
			query = query.replace("$table", q1);
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			
			System.out.println("stmt=========>      "+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString(fildname1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.err.println("whr-------->      "+whr);
		
		return whr;
		
	}
	
	

}
