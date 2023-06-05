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
public class Clg_Reg_Department_Equipment_Report_DaoImpl implements Clg_Reg_Department_Equipment_Report_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<Map<String,Object>> getObstricView(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dep_equip_obstetric_and_gynacology ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getAnatomyView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select * from clg_reg_dep_equip_anatomy ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	

	
	@Override
	public List<Map<String,Object>> getAllinfo_anatomy_childView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equip_anatomy_child ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getcommunity_medicineView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_community_medi ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getcommunity_medicine_childView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_community_medi_child ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getForensic_Medicine_ToxicologyEquipView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_fm_equipment_details ic\n"
	    			+ "inner join clg_reg_dept_equipment_item_mstr em on em.id=ic.item_id\n"
	    			+ "where ic.inst_id=? and em.fm_equip_id=1 and em.status=1 ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getForensic_Medicine_ToxicologyActView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_fm_acts_legislation_regulations ic where inst_id=? ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getHomeo_PharmView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_homeopathic_pharmacy_parent ic\n"
	    			+ "inner join clg_reg_dept_equipment_item_mstr em on em.id=ic.item_id\n"
	    			+ "where ic.inst_id=? and em.homeo_pharmarcy_id=1 and em.status=1  ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getHomeo_Pharm_ChildView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_homeopathic_pharmacy_child ic\n"
	    			+ "inner join clg_reg_dept_equipment_practice_medicine pm on pm.inst_id=ic.inst_id\n"
	    			+ "where ic.inst_id=? ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getEquip_organon_medicineView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_organon_medicine ic where ic.inst_id=? ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getpathology_microbiologyView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_pathology_microbiology ic\n"
	    			+ "inner join clg_reg_dept_equipment_item_mstr em on em.id=ic.item_id\n"
	    			+ "where ic.inst_id=? and em.patho_micro_id=1 and em.status=1 ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getPhysiology_BiochemistryView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_pathology_biochemeistry ic\n"
	    			+ "inner join clg_reg_dept_equipment_item_mstr em on em.id=ic.item_id\n"
	    			+ "where ic.inst_id=? and em.patho_bio_id=1 and em.status=1  ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getBiochemistryView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_biochemeistry ic\n"
	    			+ "inner join clg_reg_dept_equipment_item_mstr em on em.id=ic.item_id\n"
	    			+ "where ic.inst_id=? and em.biochem_id=1 and em.status=1  ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getPractice_MedView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_practice_medicine ic"
//	    			+ "inner join clg_reg_dept_equipment_repertory er on er.inst_id=ic.inst_id"
	    			+ " where ic.inst_id=?";
	    	
	    	PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT- err----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getRepertoryView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_repertory ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getSurgeryView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_surgery ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getHMMView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_homeo_materia_medica ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getPsychView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_psychiatry ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	@Override
	public List<Map<String,Object>> getPediatricView(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_pediatrics ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	
	
	

//@Override
//public String getFilePath_DynemicQueryForDoc_part_one(String id, String val, String fildname) {
//	
//	
//	System.err.println("id   "+ id + "  val "+val +" fildname "+ fildname);
//	
//	String whr = "";
//	String q1="";
//	Connection conn = null;
//	String fildname1="";
//
//	if(val.equals("1")) {
//		q1="clg_reg_dep_equip_obstetric_and_gynacology";
//	}
//	
//	if (fildname.equals("1")) {
//		fildname1 = "copy_of_alchoho";
//	}
//	else if (fildname.equals("2")) {
//		fildname1 = "photographs_of_cadavers";
//	}
//	else if (fildname.equals("3")) {
//		fildname1 = "upload_purchase_bill";
//	}
//	
//	
//	try {
//		conn = dataSource.getConnection();
//		PreparedStatement stmt = null;
//		String query = null;
//		query = "select $fildname from "+q1+" where id=?";
//		query = query.replace("$fildname", fildname1);
//		
//		stmt = conn.prepareStatement(query);
//		stmt.setInt(1, Integer.parseInt(id));
//		
//		System.out.println("stmt=========>      "+stmt);
//		ResultSet rs = stmt.executeQuery();
//
//		while (rs.next()) {
//			whr = rs.getString(fildname1);
//		}
//		rs.close();
//		stmt.close();
//		conn.close();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	
//	System.err.println("whr-------->      "+whr);
//	
//	return whr;
//	
//}
}
