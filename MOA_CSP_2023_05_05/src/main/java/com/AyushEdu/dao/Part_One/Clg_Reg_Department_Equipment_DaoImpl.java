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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public class Clg_Reg_Department_Equipment_DaoImpl implements Clg_Reg_Department_Equipment_Dao {
	
	
	@Autowired
	private DataSource dataSource;
	
	
	
	
	//GET PID FROM USERID
		public ArrayList<ArrayList<String>> getpid_from_userid_anatomy(String userid) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

				
				q="select id,inst_id from clg_reg_dep_equip_obstetric_and_gynacology where userid=?";
			

				PreparedStatement stmt = conn.prepareStatement(q);
				 stmt.setInt(1, Integer.parseInt(userid));
				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("inst_id"));// 1

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<Map<String,Object>> getAllinfo_obstetric_gynacology(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    //	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
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
	public List<Map<String,Object>> getAllinfo_anatomy_child(int inst_id) {
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
	
	
	
	//GET COURSE FOR UG
		public ArrayList<ArrayList<String>> getAllitem_for_anatomy() {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

			//	q =  "select id,department_name from clg_reg_department_mstr where status = 1 order by id\n";
				
				q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=1 and status=1 \n";

				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("stmt=====Department========="+stmt);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("item_name"));// 1

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
	
	
	
	
	public String getAllItemforAnatomy() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=1 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Anatomy========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				TEMP+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_ana_"+rs.getString("id")+"'"+
							"name='item_name_ana_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_ana_"+rs.getString("id")+"' name='hid_item_ana_"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_ana"+rs.getString("id")+"' name='item_id_ana"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_ana_"+rs.getString("id")+"'"+
							"name='available_num_ana_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		
		
		return TEMP;
	}
	
	//GET All Data For Community Medicine
	
			public ArrayList<ArrayList<String>> getAllCommunityMedicine_id() {

				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				try {
					
					conn = dataSource.getConnection();

				
					
					q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=2 and status=1 \n";
					

					PreparedStatement stmt = conn.prepareStatement(q);
					System.err.println("stmt=====Department========="+stmt);
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("item_name"));// 1

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
	
	
	public String getAllInfoCommunityMedicine() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=2 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Anatomy========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				TEMP+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_cm_"+rs.getString("id")+"'"+
							"name='item_name_cm_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_cm_"+rs.getString("id")+"' name='hid_item_cm_"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_cm"+rs.getString("id")+"' name='item_id_cm"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_cm_"+rs.getString("id")+"'"+
							"name='available_num_cm_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		
		
		return TEMP;
	}
	
	public List<Map<String,Object>> getAllComm(int inst_id) {
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
	
	public String getForensicEquipmentDetails() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String forensic="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=3 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Forensic_Equip_Details========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				forensic+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_fm_eq"+rs.getString("id")+"'"+
							"name='item_name_fm_eq"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_fm_eq"+rs.getString("id")+"' name='hid_item_fm_eq"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_fm_eq"+rs.getString("id")+"' name='item_id_fm_eq"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_quant"+rs.getString("id")+"'"+
							"name='available_num_quant"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		
		
		return forensic;
	}
	
	
	public ArrayList<ArrayList<String>> getAllForensic_Equip_Details() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=3 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Forensic_Eq========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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

	
	public String getForensicActs() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String forensic="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=4 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Forensic_Acts========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				forensic+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='act_item_name"+rs.getString("id")+"'"+
							"name='act_item_name"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_fm_act"+rs.getString("id")+"' name='hid_item_fm_act"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_fm_act"+rs.getString("id")+"' name='item_id_fm_act"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='act_available_num_quant"+rs.getString("id")+"'"+
							"name='act_available_num_quant"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		return forensic;
	}
	
	public ArrayList<ArrayList<String>> getAllForensic_Acts() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=4 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Forensic_Act========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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
	
	
	public String getHomeophathic_Pharmacy() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String pharmacy="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=5 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Homeophathic_Pharmacy========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				pharmacy+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_ph"+rs.getString("id")+"'"+
							"name='item_name_ph"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_ph"+rs.getString("id")+"' name='hid_item_ph"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_ph"+rs.getString("id")+"' name='item_id_ph"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_quant_ph"+rs.getString("id")+"'"+
							"name='available_num_quant_ph"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		return pharmacy;
	}
	
	public List<Map<String,Object>> getHomeo_Pharmacy_child(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_homeopathic_pharmacy_child ic where inst_id=?";
	    	
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
	
	
	
	public ArrayList<ArrayList<String>> getHomeophatic_Pharmacy_id() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		//	q =  "select id,department_name from clg_reg_department_mstr where status = 1 order by id\n";
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=5 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Homeophatic_Pharmacy========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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
	
	
	public String getPatho_Micro() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String patho="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=6 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Patho_Micro========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				patho+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_pm"+rs.getString("id")+"'"+
							"name='item_name_pm"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_pm"+rs.getString("id")+"' name='hid_item_pm"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_pm"+rs.getString("id")+"' name='item_id_pm"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_quant_pm"+rs.getString("id")+"'"+
							"name='available_num_quant_pm"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		return patho;
	}
	
	public ArrayList<ArrayList<String>> getPatho_microbioDetails() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=6 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Patho_microbioDetails========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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
	
	public String getPatho_Bio() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String patho="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=7 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Patho_Bio========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				patho+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_pb"+rs.getString("id")+"'"+
							"name='item_name_pb"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_pb"+rs.getString("id")+"' name='hid_item_pb"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_pb"+rs.getString("id")+"' name='item_id_pb"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_quant_pb"+rs.getString("id")+"'"+
							"name='available_num_quant_pb"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		return patho;
	}
	
	public ArrayList<ArrayList<String>> getPatho_biochemDetails() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=7 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Patho_biochemDetails========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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
	
	public String get_Bio() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String bio="";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=8 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Item Patho_Bio========="+stmt);
			ResultSet rs = stmt.executeQuery();
					
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				bio+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='item_name_b"+rs.getString("id")+"'"+
							"name='item_name_b"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("item_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_item_b"+rs.getString("id")+"' name='hid_item_b"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='item_id_b"+rs.getString("id")+"' name='item_id_b"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='available_num_quant_b"+rs.getString("id")+"'"+
							"name='available_num_quant_b"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Available(Number/Quantity)'>"+
					"</div>"+
				"</td>"+
			"</tr>";

			}
			
			System.err.println("list--PG->  "+list);
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
		
		return bio;
	}
	
	public ArrayList<ArrayList<String>> get_biochemDetails() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,item_name from clg_reg_dept_equipment_item_mstr where dept_equip_item_id=8 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====biochemDetails========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("item_name"));// 1

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
	
	public List<Map<String,Object>> getPractice_Med(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select * from clg_reg_dept_equipment_practice_medicine ic where inst_id=?";
	    	
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
	

	public List<Map<String,Object>> getRepertory(int inst_id) {
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
	
	public List<Map<String,Object>> getSurgery(int inst_id) {
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
	
	public List<Map<String,Object>> getHMM(int inst_id) {
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
	
	public List<Map<String,Object>> getPsych(int inst_id) {
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
	
	public List<Map<String,Object>> getPediatric(int inst_id) {
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
	
	public List<Map<String,Object>> getOrganon(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	
	    	String sq2="select id,founder_philoso_name,photo_path,to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth ,to_char(date_of_death,'DD/MM/YYYY') as date_of_death,available_num_quant from clg_reg_dept_equipment_organon_medicine ic where inst_id=?";
	    	
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
	
	public ArrayList<ArrayList<String>> getOrganon_id(int inst_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		
			
			q="select id,founder_philoso_name,photo_path,to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth ,date_of_death,available_num_quant "
					+ "from clg_reg_dept_equipment_organon_medicine where inst_id=?  \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Organon========="+stmt);
			stmt.setInt(1,inst_id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("founder_philoso_name"));// 1
				alist.add(rs.getString("photo_path"));// 2
				alist.add(rs.getString("date_of_birth"));// 3
				alist.add(rs.getString("date_of_death"));// 4
				alist.add(rs.getString("available_num_quant"));// 5

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

}
