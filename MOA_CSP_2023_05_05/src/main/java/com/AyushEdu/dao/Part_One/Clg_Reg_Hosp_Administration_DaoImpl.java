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

public class Clg_Reg_Hosp_Administration_DaoImpl implements Clg_Reg_Hosp_Administration_Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	public String getAllDepartmentforHA(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
//			 stmt.setInt(1, institute_id);
			
			System.err.println("stmt=====Subject========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_ha_"+rs.getString("id")+"'"+
									"name='course_name_ha_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_ha_"+rs.getString("id")+"' name='hid_course_ha_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_ha"+rs.getString("id")+"' name='course_id_ha"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_ha_"+rs.getString("id")+"'"+
									"name='intake_cap_course_ha_"+rs.getString("id")+"'"+
									"class='form-control grand' autocomplete='off' value='0'"+
									"placeholder='available area'>"+
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
		
		
		return TEMP;
	}
	
	
	public ArrayList<ArrayList<String>> getHospital_department_administrative_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 1";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt====Hospital Administarative List========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public ArrayList<ArrayList<String>> getHospital_department_opd_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,opd_department_name from clg_reg_hosp_opd where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 2 ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt====Hospital OPD List========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public String getAllDepartmentforOPD() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,opd_department_name from clg_reg_hosp_opd where  status=1 \n";
			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 2";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Subject of opd========"+stmt);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_opd_"+rs.getString("id")+"'"+
									"name='course_name_opd_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_opd_"+rs.getString("id")+"' name='hid_course_opd_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_opd"+rs.getString("id")+"' name='course_id_opd"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_opd_"+rs.getString("id")+"'"+
									"name='intake_cap_course_opd_"+rs.getString("id")+"'"+
									"class='form-control grandopd' autocomplete='off' value='0'"+
									"placeholder='Available area'>"+
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
		
		return TEMP;
	}
	
	public ArrayList<ArrayList<String>> getHospital_department_ipd_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();
			
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 3";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt====Hospital IPD List========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	public String getAllDepartmentforIPD() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,ipd_department_name from clg_reg_hosp_ipd where  status=1 ";
			
			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 3";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Subject of Ipd========"+stmt);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_ipd_"+rs.getString("id")+"'"+
									"name='course_name_ipd_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_ipd_"+rs.getString("id")+"' name='hid_course_ipd_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_ipd"+rs.getString("id")+"' name='course_id_ipd"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_ipd_"+rs.getString("id")+"'"+
									"name='intake_cap_course_ipd_"+rs.getString("id")+"'"+
									"class='form-control grandipd' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		
		return TEMP;
	}
	
	
	
	public String getAllDepartmentforOT() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,ot_department_name from clg_reg_hosp_ot where  status=1 ";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 4";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Subject of ot========"+stmt);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_ot_"+rs.getString("id")+"'"+
									"name='course_name_ot_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_ot_"+rs.getString("id")+"' name='hid_course_ot_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_ot"+rs.getString("id")+"' name='course_id_ot"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_ot_"+rs.getString("id")+"'"+
									"name='intake_cap_course_ot_"+rs.getString("id")+"'"+
									"class='form-control grandot' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	
	public ArrayList<ArrayList<String>> getHospital_department_ot_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,ot_department_name from clg_reg_hosp_ot where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 4";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt====Hospital OPD List========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public ArrayList<ArrayList<String>> getHospital_department_ru_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,department_name from clg_reg_hosp_rehabilation_unit where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 5";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt====Hospital rehabilation_unit========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	//0410

	public String getAllDepartmentforRU() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,department_name from clg_reg_hosp_rehabilation_unit where  status=1 ";
			q= "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 5";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_ru_"+rs.getString("id")+"'"+
									"name='course_name_ru_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_ru_"+rs.getString("id")+"' name='hid_course_ru_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_ru"+rs.getString("id")+"' name='course_id_ru"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_ru_"+rs.getString("id")+"'"+
									"name='intake_cap_course_ru_"+rs.getString("id")+"'"+
									"class='form-control grandru' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	
	
	
	
	public ArrayList<ArrayList<String>> getHospital_department_cl_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,department_name from clg_reg_hosp_clinical_laboratory where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 6";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public String getAllDepartmentforCL() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,department_name from clg_reg_hosp_clinical_laboratory where  status=1 ";
			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 6";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_cl_"+rs.getString("id")+"'"+
									"name='course_name_cl_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_cl_"+rs.getString("id")+"' name='hid_course_cl_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_cl"+rs.getString("id")+"' name='course_id_cl"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_cl_"+rs.getString("id")+"'"+
									"name='intake_cap_course_cl_"+rs.getString("id")+"'"+
									"class='form-control grandcl' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	
	
	public ArrayList<ArrayList<String>> getHospital_department_rs_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,department_name from clg_reg_hosp_radiology_sonography where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 7";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public String getAllDepartmentforRS() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,department_name from clg_reg_hosp_radiology_sonography where  status=1 ";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 7";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_rs_"+rs.getString("id")+"'"+
									"name='course_name_rs_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_rs_"+rs.getString("id")+"' name='hid_course_rs_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_rs"+rs.getString("id")+"' name='course_id_rs"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_rs_"+rs.getString("id")+"'"+
									"name='intake_cap_course_rs_"+rs.getString("id")+"'"+
									"class='form-control grandrs' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	
	
	public ArrayList<ArrayList<String>> getHospital_department_hk_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,kitchen_department_name from clg_reg_hosp_kitchen_canteen where status = 1 order by id";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 8";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public String getAllDepartmentforHK() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,kitchen_department_name from clg_reg_hosp_kitchen_canteen where  status=1 ";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 8";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_hk_"+rs.getString("id")+"'"+
									"name='course_name_hk_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_hk_"+rs.getString("id")+"' name='hid_course_hk_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_hk"+rs.getString("id")+"' name='course_id_hk"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_hk_"+rs.getString("id")+"'"+
									"name='intake_cap_course_hk_"+rs.getString("id")+"'"+
									"class='form-control grandhk' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	
	
	
	public ArrayList<ArrayList<String>> getHospital_department_hs_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

//			q =  "select id,store_department_name from clg_reg_hosp_stores where status = 1 order by id";
			
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 9";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	
	public String getAllDepartmentforHS() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,store_department_name from clg_reg_hosp_stores where  status=1 ";
			q = "select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 9";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_hs_"+rs.getString("id")+"'"+
									"name='course_name_hs_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_hs_"+rs.getString("id")+"' name='hid_course_hs_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_hs"+rs.getString("id")+"' name='course_id_hs"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_hs_"+rs.getString("id")+"'"+
									"name='intake_cap_course_hs_"+rs.getString("id")+"'"+
									"class='form-control grandhs  grandhss' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
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
		return TEMP;
	}
	

	
	public ArrayList<ArrayList<String>> getHospital_department_oi_list() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			
			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_department_id = 10";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("hospital_department_name"));// 1

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
	
	
	public String getAllDepartmentforOI() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

//			q="select id,hospital_department_name from clg_reg_hosp_infra_dept_mstr where hos_other_infr_id = 1";
			
			q="select  dm.id,dm.hospital_department_name,lm.licence_name from clg_reg_hosp_infra_dept_mstr dm\n"
					+ "inner join clg_reg_aerb_licence_mstr lm on lm.department_id=dm.id\n"
					+ "where dm.hos_department_id = 10";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
		
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
			TEMP+="<tr>"+
						"<td class='sr-no'><p></p></td>"+
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='course_name_oi_"+rs.getString("id")+"'"+
									"name='course_name_oi_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off'"+
									"value='"+rs.getString("hospital_department_name")+"' readonly>"+
							"</div>"+
							
							"<input type='hidden' id='hid_course_oi_"+rs.getString("id")+"' name='hid_course_oi_"+rs.getString("id")+"' value='0'>"+
							"<input type='hidden' id='course_id_oi"+rs.getString("id")+"' name='course_id_oi"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
							
						"</td>"+
						
					"<td>" + "<div class='input-style-1'>" + "<input type='text' id='course_name_licence_"
					+ rs.getString("id") + "'" + "name='course_name_licence_" + rs.getString("id") + "'"
					+ "class='form-control' autocomplete='off'" + "value='" + rs.getString("licence_name") 
					+ "' readonly>" + "</div>" +

					"<input type='hidden' id='hid_course_oi_" + rs.getString("id") + "' name='hid_course_oi_"
					+ rs.getString("id") + "' value='0'>" + "<input type='hidden' id='course_id_oi" + rs.getString("id")
					+ "' name='course_id_oi" + rs.getString("id") + "' value='" + rs.getString("id") + "'>" +

					"</td>" +
						
						"<td>"+
							"<div class='input-style-1'>"+
								"<input type='text' id='intake_cap_course_oi_"+rs.getString("id")+"'"+
									"name='intake_cap_course_oi_"+rs.getString("id")+"'"+
									"class='form-control' autocomplete='off' value='0'"+
									"placeholder='Available Area'>"+
									
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
		return TEMP;
	}
	
	
	public List<Map<String,Object>> getAllOtherdetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="select id,ambulance_serv,sitting_arrangment,central_research_lab,casualty_dept,ambulance_document from clg_reg_other_infra_details where institute_id = ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-15----"+stmt);
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
	
	public List<Map<String,Object>> getAllOtherdetailsipd(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="\n"
	    			+ "select id,ipd_casulty_service,treatment_outcome_ipd,ipd_casulty_document,treatment_outcome_ipd_document from clg_reg_hosp_other_ipd_detail where institute_id = ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-15----"+stmt);
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
	
	public List<Map<String,Object>> getAllStatuatorydata(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="select id,statutory_requirements_for_hospital from clg_reg_hosp_infra \n"
	    			+ "where institute_id = ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT----17----"+stmt);
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
	
}
