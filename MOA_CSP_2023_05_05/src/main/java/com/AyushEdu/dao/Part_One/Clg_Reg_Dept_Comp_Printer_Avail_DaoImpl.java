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

public class Clg_Reg_Dept_Comp_Printer_Avail_DaoImpl implements Clg_Reg_Dept_Comp_Printer_Avail_Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	//GET department names 
	public ArrayList<ArrayList<String>> getAllDepartment_name() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department from clg_reg_clg_dept_availabilty_mstr where status = 1 order by id\n";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Department========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("department"));// 1

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
	
	//fetch the data of computer and printer
	
	public List<Map<String,Object>> getdeptcomputerdetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="select id,department,computer,printer from clg_reg_clg_dept_comp_printer_avail where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
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
	
	
	
	//GET ALL OTHER DEPARTMENT LIST--2 
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ug() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where ug_status = '1' order by id\n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Department=====ug===="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("department_name"));// 1

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
			System.err.println("stmt=====Department===pg======"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("department_name"));// 1

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
	
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ap() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where ap_status = '3' order by id\n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Department========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("department_name"));// 1

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
	
	
	//fetch the data of ug details 
	
		public List<Map<String,Object>> getugdetails(int institute_id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		    Connection conn = null;
		    try{          
		    	conn = dataSource.getConnection();

		    	String sq1="select id,department, computer_printer_avail, dept_library_books, teacher_training_material, museum_charts, museum_models, museum_specimens, practical, tutorial, seminar, theory\n"
		    			+ "from clg_reg_clg_dept_ug \n"
		    			+ "where institute_id=?";
		    	
		        PreparedStatement stmt = conn.prepareStatement(sq1);
		        stmt.setInt(1, institute_id);
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
		
		//fetch the data of Academic Performance Details=====
		
			public List<Map<String,Object>> getapdetails(int institute_id) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			    Connection conn = null;
			    try{          
			    	conn = dataSource.getConnection();

			    	String sq1="select id,department, advance_teaching_plan,teaching_diary,journal_practical from clg_reg_clg_dept_academic_performance\n"
			    			+ "where institute_id=?";
			    	
			        PreparedStatement stmt = conn.prepareStatement(sq1);
			        stmt.setInt(1, institute_id);
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
	
	
	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select cc.id,cc.institute_id \n"
					+ "from logininformation li\n"
					+ "inner join clg_reg_clg_dept_ug cc on cc.institute_id = li.institute_id\n"
					+ "where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====Parent- ID========"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("institute_id"));// 1

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
	//GET hospital department names 
			public ArrayList<ArrayList<String>> getHosptal_opd_ipd_listDepartment() {

				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				try {
					
					conn = dataSource.getConnection();

					q =  "select id,department_name from clg_reg_hos_dept_mstr where status = 1 order by id\n";
					

					PreparedStatement stmt = conn.prepareStatement(q);
					System.err.println("stmt=====Department========="+stmt);
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("department_name"));// 1

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
			
			
			
			
			//GET hospital department table opd
					public String getHosptal_opd_ipd_TableDepartment() {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select id,department_name from clg_reg_hos_dept_mstr where status = 1 order by id\n";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							while (rs.next()) {
								
								System.err.println("IN LOOP---"+StaticValue);
								
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								
								
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
								"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janopd"+rs.getString("id")+"' id='gm_janopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control jan grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febopd"+rs.getString("id")+"' id='gm_febopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control feb grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchopd"+rs.getString("id")+"' id='gm_marchopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control mar grand' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilopd"+rs.getString("id")+"' id='gm_aprilopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control appr grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_mayopd"+rs.getString("id")+"' id='gm_mayopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control may grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_juneopd"+rs.getString("id")+"' id='gm_juneopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control jun grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julyopd"+rs.getString("id")+"' id='gm_julyopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control jul grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augopd"+rs.getString("id")+"' id='gm_augopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control aug grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septopd"+rs.getString("id")+"' id='gm_septopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control sep grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							"	<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octopd"+rs.getString("id")+"' id='gm_octopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control oct grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novopd"+rs.getString("id")+"' id='gm_novopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control nov grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decopd"+rs.getString("id")+"' id='gm_decopd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control dec grand' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_hidden"+rs.getString("id")+"' id='gm_hidden"+rs.getString("id")+"' value='0'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
//							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_janopd_mon' id='gm_janopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_febopd_mon' id='gm_febopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_marchopd_mon' id='gm_marchopd_mon' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='gm_aprilopd_mon' id='gm_aprilopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='gm_mayopd_mon' id='gm_mayopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_juneopd_mon' id='gm_juneopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_julyopd_mon' id='gm_julyopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_augopd_mon' id='gm_augopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_septopd_mon' id='gm_septopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='gm_octopd_mon' id='gm_octopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_novopd_mon' id='gm_novopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_decopd_mon' id='gm_decopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
							
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
						return table;
					}
					//GET hospital department table opd Fetch
					public String getHosptal_opd_ipd_TableDepartmentFetch(String institute_id) {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select dm.id as dmid,a.id,a.sp_id,a.p_id,a.dep_id,a.jan,a.feb,a.mar,a.appr,a.may,a.jun,a.july,a.aug,a.sep,a.oct,a.nov,a.dec,dm.department_name,a.total_wardsopd,a.institute_id as inst_id from  clg_reg_hos_opd_ipd a\n"
									+ "inner join clg_reg_hos_dept_mstr dm on dm.id=a.dep_id where a.institute_id=? \n";
							
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(institute_id));
							System.err.println("stmt=====Department====fetch====="+stmt);
							ResultSet rs = stmt.executeQuery();
							String jan="";
							String feb="";
							String mar="";
							String appr="";
							String may="";
							String jun="";
							String july="";
							String aug="";
							String sep="";
							String oct="";
							String nov="";
							String dec="";
							
							int StaticValue=0;
							while (rs.next()) {
								
								System.err.println("IN LOOP---"+StaticValue);
								
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								
								jan = rs.getString("jan");
								feb = rs.getString("feb");
								mar = rs.getString("mar");
								appr = rs.getString("appr");
								may = rs.getString("may");
								jun = rs.getString("jun");
								july = rs.getString("july");
								aug = rs.getString("aug");
								sep = rs.getString("sep");
								oct = rs.getString("oct");
								nov = rs.getString("nov");
								dec = rs.getString("dec");
								
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
								"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janopd"+rs.getString("dmid")+"' id='gm_janopd"+rs.getString("dmid")+"' value='"+jan+"' class='form-control jan grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febopd"+rs.getString("dmid")+"' id='gm_febopd"+rs.getString("dmid")+"' value='"+feb+"' class='form-control feb grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchopd"+rs.getString("dmid")+"' id='gm_marchopd"+rs.getString("dmid")+"' value='"+mar+"' class='form-control mar grand' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilopd"+rs.getString("dmid")+"' id='gm_aprilopd"+rs.getString("dmid")+"' value='"+appr+"' class='form-control appr grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_mayopd"+rs.getString("dmid")+"' id='gm_mayopd"+rs.getString("dmid")+"' value='"+may+"' class='form-control may grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_juneopd"+rs.getString("dmid")+"' id='gm_juneopd"+rs.getString("dmid")+"' value='"+jun+"' class='form-control jun grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julyopd"+rs.getString("dmid")+"' id='gm_julyopd"+rs.getString("dmid")+"' value='"+july+"' class='form-control jul grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augopd"+rs.getString("dmid")+"' id='gm_augopd"+rs.getString("dmid")+"' value='"+aug+"' class='form-control aug grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septopd"+rs.getString("dmid")+"' id='gm_septopd"+rs.getString("dmid")+"' value='"+sep+"' class='form-control sep grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							    "<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octopd"+rs.getString("dmid")+"' id='gm_octopd"+rs.getString("dmid")+"' value='"+oct+"' class='form-control oct grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novopd"+rs.getString("dmid")+"' id='gm_novopd"+rs.getString("dmid")+"' value='"+nov+"' class='form-control nov grand' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decopd"+rs.getString("dmid")+"' id='gm_decopd"+rs.getString("dmid")+"' value='"+dec+"' class='form-control dec grand' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_hidden"+rs.getString("dmid")+"' id='gm_hidden"+rs.getString("dmid")+"' value='"+rs.getString("id")+"'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							if(StaticValue > 0) {
							
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_janopd_mon' id='gm_janopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_febopd_mon' id='gm_febopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_marchopd_mon' id='gm_marchopd_mon' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='gm_aprilopd_mon' id='gm_aprilopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='gm_mayopd_mon' id='gm_mayopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_juneopd_mon' id='gm_juneopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_julyopd_mon' id='gm_julyopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_augopd_mon' id='gm_augopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_septopd_mon' id='gm_septopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='gm_octopd_mon' id='gm_octopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_novopd_mon' id='gm_novopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='gm_decopd_mon' id='gm_decopd_mon' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
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
						return table;
					}
					
					
					//GET hospital department table ipd
					public String getHospital_ipd_TableDepartment() {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select id,department_name from clg_reg_hos_dept_mstr where status = 1 order by id\n";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
									"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janipd"+rs.getString("id")+"' id='gm_janipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdjan' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febipd"+rs.getString("id")+"' id='gm_febipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdfeb' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchipd"+rs.getString("id")+"' id='gm_marchipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdmarch' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilipd"+rs.getString("id")+"' id='gm_aprilipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdappr' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_mayipd"+rs.getString("id")+"' id='gm_mayipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdmay' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_juneipd"+rs.getString("id")+"' id='gm_juneipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdjun' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julyipd"+rs.getString("id")+"' id='gm_julyipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdjuly' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augipd"+rs.getString("id")+"' id='gm_augipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdAug' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septipd"+rs.getString("id")+"' id='gm_septipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdsep' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							"	<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octipd"+rs.getString("id")+"' id='gm_octipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdoct' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novipd"+rs.getString("id")+"' id='gm_novipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipdnov' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decipd"+rs.getString("id")+"' id='gm_decipd"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control ipd_Grand ipddec' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_ipd_hidden"+rs.getString("id")+"' id='gm_ipd_hidden"+rs.getString("id")+"' value='0'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
//							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_janipd' id='monthtotal_janipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_febipd' id='monthtotal_febipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_marchipd' id='monthtotal_marchipd' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_aprilipd' id='monthtotal_aprilipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_mayipd' id='monthtotal_mayipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_juneipd' id='monthtotal_juneipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_julyipd' id='monthtotal_julyipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_augipd' id='monthtotal_augipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_septipd' id='monthtotal_septipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='monthtotal_octipd' id='monthtotal_octipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_novipd' id='monthtotal_novipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_decipd' id='monthtotal_decipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
							
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
						return table;
					}
					
					//GET hospital department table ipd Fetch
					public String getHospital_ipd_patientTableDepart_Fetch(String institute_id) {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select dm.id as dmid,a.id,a.sp_id,a.p_id,a.dep_id,a.jan,a.feb,a.mar,a.appr,a.may,a.jun,a.july,a.aug,a.sep,a.oct,a.nov,a.dec,dm.department_name from  clg_reg_hos_ipd_patients_tbl a\n"
									+ "inner join clg_reg_hos_dept_mstr dm on dm.id=a.dep_id where a.institute_id=? \n";
							
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(institute_id));
							
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							String jan="";
							String feb="";
							String mar="";
							String appr="";
							String may="";
							String jun="";
							String july="";
							String aug="";
							String sep="";
							String oct="";
							String nov="";
							String dec="";
							
							int StaticValue=0;
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								jan = rs.getString("jan");
								feb = rs.getString("feb");
								mar = rs.getString("mar");
								appr = rs.getString("appr");
								may = rs.getString("may");
								jun = rs.getString("jun");
								july = rs.getString("july");
								aug = rs.getString("aug");
								sep = rs.getString("sep");
								oct = rs.getString("oct");
								nov = rs.getString("nov");
								dec = rs.getString("dec");
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
									"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janipd"+rs.getString("dmid")+"' id='gm_janipd"+rs.getString("dmid")+"' value='"+jan+"' class='form-control ipd_Grand ipdjan' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febipd"+rs.getString("dmid")+"' id='gm_febipd"+rs.getString("dmid")+"' value='"+feb+"' class='form-control ipd_Grand ipdfeb' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchipd"+rs.getString("dmid")+"' id='gm_marchipd"+rs.getString("dmid")+"' value='"+mar+"' class='form-control ipd_Grand ipdmarch' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilipd"+rs.getString("dmid")+"' id='gm_aprilipd"+rs.getString("dmid")+"' value='"+appr+"' class='form-control ipd_Grand ipdappr' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_mayipd"+rs.getString("dmid")+"' id='gm_mayipd"+rs.getString("dmid")+"' value='"+may+"' class='form-control ipd_Grand ipdmay' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_juneipd"+rs.getString("dmid")+"' id='gm_juneipd"+rs.getString("dmid")+"' value='"+jun+"' class='form-control ipd_Grand ipdjun' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julyipd"+rs.getString("dmid")+"' id='gm_julyipd"+rs.getString("dmid")+"' value='"+july+"' class='form-control ipd_Grand ipdjuly' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augipd"+rs.getString("dmid")+"' id='gm_augipd"+rs.getString("dmid")+"' value='"+aug+"' class='form-control ipd_Grand ipdAug' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septipd"+rs.getString("dmid")+"' id='gm_septipd"+rs.getString("dmid")+"' value='"+sep+"' class='form-control ipd_Grand ipdsep' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							"	<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octipd"+rs.getString("dmid")+"' id='gm_octipd"+rs.getString("dmid")+"' value='"+oct+"' class='form-control ipd_Grand ipdoct' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novipd"+rs.getString("dmid")+"' id='gm_novipd"+rs.getString("dmid")+"' value='"+nov+"' class='form-control ipd_Grand ipdnov' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decipd"+rs.getString("dmid")+"' id='gm_decipd"+rs.getString("dmid")+"' value='"+dec+"' class='form-control ipd_Grand ipddec' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_ipd_hidden"+rs.getString("dmid")+"' id='gm_ipd_hidden"+rs.getString("dmid")+"' value='"+rs.getString("id")+"'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							
							if(StaticValue > 0) {
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_janipd' id='monthtotal_janipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_febipd' id='monthtotal_febipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_marchipd' id='monthtotal_marchipd' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_aprilipd' id='monthtotal_aprilipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_mayipd' id='monthtotal_mayipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_juneipd' id='monthtotal_juneipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_julyipd' id='monthtotal_julyipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_augipd' id='monthtotal_augipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_septipd' id='monthtotal_septipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='monthtotal_octipd' id='monthtotal_octipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_novipd' id='monthtotal_novipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_decipd' id='monthtotal_decipd' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
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
						return table;
					}	
					
					
					
					//GET hospital department table Bed Days Occupied
					public String getHospital_Bed_Days_Occupied_TableDepart() {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select id,department_name from clg_reg_hos_dept_mstr where status = 1 order by id\n";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
									"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janbdo"+rs.getString("id")+"' id='gm_janbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdojan' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febbdo"+rs.getString("id")+"' id='gm_febbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdofeb' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchbdo"+rs.getString("id")+"' id='gm_marchbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdomarch' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilbdo"+rs.getString("id")+"' id='gm_aprilbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdoappr' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_maybdo"+rs.getString("id")+"' id='gm_maybdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdomay' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_junebdo"+rs.getString("id")+"' id='gm_junebdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdojun' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julybdo"+rs.getString("id")+"' id='gm_julybdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdojuly' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augbdo"+rs.getString("id")+"' id='gm_augbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdoAug' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septbdo"+rs.getString("id")+"' id='gm_septbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdosep' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							"	<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octbdo"+rs.getString("id")+"' id='gm_octbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdooct' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novbdo"+rs.getString("id")+"' id='gm_novbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdonov' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decbdo"+rs.getString("id")+"' id='gm_decbdo"+rs.getString("id")+"' value='"+StaticValue+"' class='form-control bdo_Grand bdodec' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_bdo_hidden"+rs.getString("id")+"' id='gm_bdo_hidden"+rs.getString("id")+"' value='0'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
//							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_janbdo' id='monthtotal_janbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_febbdo' id='monthtotal_febbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_marchbdo' id='monthtotal_marchbdo' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_aprilbdo' id='monthtotal_aprilbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_maybdo' id='monthtotal_maybdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_junebdo' id='monthtotal_junebdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_julybdo' id='monthtotal_julybdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_augbdo' id='monthtotal_augbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_septbdo' id='monthtotal_septbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='monthtotal_octbdo' id='monthtotal_octbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_novbdo' id='monthtotal_novbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_decbdo' id='monthtotal_decbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
							
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
						return table;
					}
					
					//GET hospital department table Bed Days Occupied Fetch
					public String getHospital_Bed_Days_Occupied_TableDepartFetch(String institute_id) {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select dm.id as dmid,a.id,a.sp_id,a.p_id,a.dep_id,a.jan,a.feb,a.mar,a.appr,a.may,a.jun,a.july,a.aug,a.sep,a.oct,a.nov,a.dec,dm.department_name from  clg_reg_hos_bed_days_occupied_tbl a\n"
									+ "inner join clg_reg_hos_dept_mstr dm on dm.id=a.dep_id where a.institute_id=?";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(institute_id));
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							String jan="";
							String feb="";
							String mar="";
							String appr="";
							String may="";
							String jun="";
							String july="";
							String aug="";
							String sep="";
							String oct="";
							String nov="";
							String dec="";
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								jan = rs.getString("jan");
								feb = rs.getString("feb");
								mar = rs.getString("mar");
								appr = rs.getString("appr");
								may = rs.getString("may");
								jun = rs.getString("jun");
								july = rs.getString("july");
								aug = rs.getString("aug");
								sep = rs.getString("sep");
								oct = rs.getString("oct");
								nov = rs.getString("nov");
								dec = rs.getString("dec");
								
								table+="<tr>"+
								"<td class='sr-no'>"+
									"<p></p>"+
								"</td>"+
								"<td>"+
									"<p>"+rs.getString("department_name")+"</p>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_janbdo"+rs.getString("dmid")+"' id='gm_janbdo"+rs.getString("dmid")+"' value='"+jan+"' class='form-control bdo_Grand bdojan' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_febbdo"+rs.getString("dmid")+"' id='gm_febbdo"+rs.getString("dmid")+"' value='"+feb+"' class='form-control bdo_Grand bdofeb' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_marchbdo"+rs.getString("dmid")+"' id='gm_marchbdo"+rs.getString("dmid")+"' value='"+mar+"' class='form-control bdo_Grand bdomarch' placeholder='Total Number'>"+
								"	</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_aprilbdo"+rs.getString("dmid")+"' id='gm_aprilbdo"+rs.getString("dmid")+"' value='"+appr+"' class='form-control bdo_Grand bdoappr' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
								"	<div class='input-style-1'>"+
										"<input type='text' name='gm_maybdo"+rs.getString("dmid")+"' id='gm_maybdo"+rs.getString("dmid")+"' value='"+may+"' class='form-control bdo_Grand bdomay' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_junebdo"+rs.getString("dmid")+"' id='gm_junebdo"+rs.getString("dmid")+"' value='"+jun+"' class='form-control bdo_Grand bdojun' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_julybdo"+rs.getString("dmid")+"' id='gm_julybdo"+rs.getString("dmid")+"' value='"+july+"' class='form-control bdo_Grand bdojuly' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_augbdo"+rs.getString("dmid")+"' id='gm_augbdo"+rs.getString("dmid")+"' value='"+aug+"' class='form-control bdo_Grand bdoAug' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_septbdo"+rs.getString("dmid")+"' id='gm_septbdo"+rs.getString("dmid")+"' value='"+sep+"' class='form-control bdo_Grand bdosep' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
							"	<td>"+
									"<div class='input-style-1'>"+
									"	<input type='text' name='gm_octbdo"+rs.getString("dmid")+"' id='gm_octbdo"+rs.getString("dmid")+"' value='"+oct+"' class='form-control bdo_Grand bdooct' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_novbdo"+rs.getString("dmid")+"' id='gm_novbdo"+rs.getString("dmid")+"' value='"+nov+"' class='form-control bdo_Grand bdonov' placeholder='Total Number'>"+
									"</div>"+

								"</td>"+
								"<td>"+
									"<div class='input-style-1'>"+
										"<input type='text' name='gm_decbdo"+rs.getString("dmid")+"' id='gm_decbdo"+rs.getString("dmid")+"' value='"+dec+"' class='form-control bdo_Grand bdodec' placeholder='Total Number'>"
												+"<input type='hidden' name='gm_bdo_hidden"+rs.getString("dmid")+"' id='gm_bdo_hidden"+rs.getString("dmid")+"' value='"+rs.getString("id")+"'; class='form-control' placeholder='Total Number'>" +
									"</div>"+

								"</td>"+
							"</tr>";
							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
							if(StaticValue > 0) {
							table+="<tr>"+
									"<td colspan='2'>"+
										"<p>Month total</p>"+
									"</td>"+
									
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_janbdo' id='monthtotal_janbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_febbdo' id='monthtotal_febbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_marchbdo' id='monthtotal_marchbdo' class='form-control' placeholder='Total Number'>"+
									"	</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_aprilbdo' id='monthtotal_aprilbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
									"	<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_maybdo' id='monthtotal_maybdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_junebdo' id='monthtotal_junebdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_julybdo' id='monthtotal_julybdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_augbdo' id='monthtotal_augbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_septbdo' id='monthtotal_septbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"	<td>"+
										"<div class='input-style-1'>"+
										"	<input type='text' name='monthtotal_octbdo' id='monthtotal_octbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_novbdo' id='monthtotal_novbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' name='monthtotal_decbdo' id='monthtotal_decbdo' class='form-control' placeholder='Total Number'>"+
										"</div>"+

									"</td>"+
								"</tr>";
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
						return table;
					}
					
					
					//GET hospital department table Bed Days Occupied
					public String getHospital_Bed_ExistedDepart() {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select id,department_name from clg_reg_hos_dept_mstr where status = 1 order by id\n";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								
								table+="<tr>"+
										"<td class='sr-no'>"+
										"<p></p>"+
									"</td>"+
									"<td>"+
									  "<p>"+rs.getString("department_name")+"</p>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+StaticValue+"' name='gm_existbed"+rs.getString("id")+"' id='gm_existbed"+rs.getString("id")+"' class='form-control' placeholder='Total UG Beds'>"+
										"</div>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+StaticValue+"' name='gm_addionalbed"+rs.getString("id")+"' id='gm_addionalbed"+rs.getString("id")+"' class='form-control' placeholder='Total PG Beds'>"+
										"</div>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+StaticValue+"' name='gm_totalbed"+rs.getString("id")+"' id='gm_totalbed"+rs.getString("id")+"' class='form-control' placeholder='Total Available Beds'>"+
											"<input type='hidden' name='gm_bo_hidden"+rs.getString("id")+"' id='gm_bo_hidden"+rs.getString("id")+"' value='0'; class='form-control' placeholder='Total Number'>" +
										"</div>"+
									"</td>"+
								"</tr>";
//							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
//							table+="<tr>"+
//									"<td colspan='2'>"+
//										"<p>Month total</p>"+
//									"</td>"+
//									
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_janbdo' id='monthtotal_janbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_febbdo' id='monthtotal_febbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_marchbdo' id='monthtotal_marchbdo' class='form-control' placeholder='Total Number'>"+
//									"	</div>"+
//
//									"</td>"+
//									"<td>"+
//									"	<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_aprilbdo' id='monthtotal_aprilbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//									"	<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_maybdo' id='monthtotal_maybdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_junebdo' id='monthtotal_junebdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_julybdo' id='monthtotal_julybdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_augbdo' id='monthtotal_augbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_septbdo' id='monthtotal_septbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//								    "<td>"+
//										"<div class='input-style-1'>"+
//										"	<input type='text' name='monthtotal_octbdo' id='monthtotal_octbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_novbdo' id='monthtotal_novbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_decbdo' id='monthtotal_decbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//								"</tr>";
							
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
						return table;
					}		
				
					
					//GET hospital department table Bed Days Occupied Fetch
					public String getHospital_Bed_ExistedDepartFetch(String institute_id) {
						String table="";
						Connection conn = null;
						String q = "";
						try {
							
							conn = dataSource.getConnection();

							q =  "select dm.id as dmid,a.id,a.sp_id,a.p_id,a.dep_id,a.existbed,a.addionalbed,a.totalbed,\n"
									+ "dm.department_name from  clg_reg_hos_bed_existed_tbl a\n"
									+ "inner join clg_reg_hos_dept_mstr dm on dm.id=a.dep_id where a.institute_id=?\n";
							

							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(institute_id));
							System.err.println("stmt=====Department========="+stmt);
							ResultSet rs = stmt.executeQuery();
							int StaticValue=0;
							String existbed="";
							String addionalbed="";
							String totalbed="";
							while (rs.next()) {
//								ArrayList<String> alist = new ArrayList<String>();
//								alist.add(rs.getString("id"));// 0
//								alist.add(rs.getString("department_name"));// 1
	//
//								list.add(alist);
								existbed = rs.getString("existbed");
								addionalbed = rs.getString("addionalbed");
								totalbed = rs.getString("totalbed");
								table+="<tr>"+
										"<td class='sr-no'>"+
										"<p></p>"+
									"</td>"+
									"<td>"+
									  "<p>"+rs.getString("department_name")+"</p>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+existbed+"' name='gm_existbed"+rs.getString("dmid")+"' id='gm_existbed"+rs.getString("dmid")+"' class='form-control' placeholder='Total UG Beds'>"+
										"</div>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+addionalbed+"' name='gm_addionalbed"+rs.getString("dmid")+"' id='gm_addionalbed"+rs.getString("dmid")+"' class='form-control' placeholder='Total PG Beds'>"+
										"</div>"+
									"</td>"+
									"<td>"+
										"<div class='input-style-1'>"+
											"<input type='text' value='"+totalbed+"' name='gm_totalbed"+rs.getString("dmid")+"' id='gm_totalbed"+rs.getString("dmid")+"' class='form-control' placeholder='Total Available Beds'>"+
											"<input type='hidden' name='gm_bo_hidden"+rs.getString("dmid")+"' id='gm_bo_hidden"+rs.getString("dmid")+"' value='"+rs.getString("id")+"'; class='form-control' placeholder='Total Number'>" +
										"</div>"+
									"</td>"+
								"</tr>";
							StaticValue++;
//								StaticValue=StaticValue+StaticValue;
								
							}
//							table+="<tr>"+
//									"<td colspan='2'>"+
//										"<p>Month total</p>"+
//									"</td>"+
//									
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_janbdo' id='monthtotal_janbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_febbdo' id='monthtotal_febbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_marchbdo' id='monthtotal_marchbdo' class='form-control' placeholder='Total Number'>"+
//									"	</div>"+
//
//									"</td>"+
//									"<td>"+
//									"	<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_aprilbdo' id='monthtotal_aprilbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//									"	<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_maybdo' id='monthtotal_maybdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_junebdo' id='monthtotal_junebdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_julybdo' id='monthtotal_julybdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_augbdo' id='monthtotal_augbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_septbdo' id='monthtotal_septbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//								    "<td>"+
//										"<div class='input-style-1'>"+
//										"	<input type='text' name='monthtotal_octbdo' id='monthtotal_octbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_novbdo' id='monthtotal_novbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//									"<td>"+
//										"<div class='input-style-1'>"+
//											"<input type='text' name='monthtotal_decbdo' id='monthtotal_decbdo' class='form-control' placeholder='Total Number'>"+
//										"</div>"+
//
//									"</td>"+
//								"</tr>";
							
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
						return table;
					}

					//getHosptal_opd_ipd_UploadDocumentsFetch
					@Override
					public ArrayList<ArrayList<String>> getHosptal_opd_ipd_UploadDocumentsFetch(String institute_id) {

						ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						try {
							conn = dataSource.getConnection();

							q="select id,add_diet_ecg_doc,xray_usg_opdipd,register_opdipd,medi_stock_opdipd,last_inveopdipd\n"
									+ "from clg_reg_hos_opd_ipd_upload_documents where institute_id=?";

							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(institute_id));
							ResultSet rs = stmt.executeQuery();
							System.err.println("Documents Details================"+stmt);
							while (rs.next()) {
								ArrayList<String> alist = new ArrayList<String>();
								alist.add(rs.getString("id"));// 0
								alist.add(rs.getString("add_diet_ecg_doc"));// 1
								alist.add(rs.getString("xray_usg_opdipd"));// 2
								alist.add(rs.getString("register_opdipd"));// 3
								alist.add(rs.getString("medi_stock_opdipd"));// 4
								alist.add(rs.getString("last_inveopdipd"));// 5
								
								
						
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
