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
public class Clg_Reg_College_Infrastructure_Report_DaoImpl implements Clg_Reg_College_Infrastructure_Report_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	//GET INSTITUTE_ID
	public ArrayList<ArrayList<String>> getInstitute_idReport(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select institute_id from logininformation where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====INSTITUTE ID========"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("institute_id"));// 0

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


	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_idReport(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select cc.id,cc.institute_id \n"
					+ "from logininformation li\n"
					+ "inner join clg_reg_infra_college_council cc on cc.institute_id = li.institute_id\n"
					+ "where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====P ID========"+stmt);
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


	//GET QULIFICATION DETAILS FOR PDF
	public ArrayList<ArrayList<String>> getAllDepartment_nameReport() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where status = 1 order by id\n";

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


	//GET DOCUMENT ATTACHMENT DETAILS
	public ArrayList<ArrayList<String>> getCollege_Council_DetailsReport(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,council_check,council_document,college_website,\n"
					+ "TO_CHAR(website_update_date,'DD/MM/YYYY') as website_update_date,cctv_status,login_url,\n"
					+ "username,password,biometric_status,college_working_hours,institute_id\n"
					+ "from clg_reg_infra_college_council where institute_id = ?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Documents Details================"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("council_check"));// 1
				alist.add(rs.getString("council_document"));// 2
				alist.add(rs.getString("college_website"));// 3
				alist.add(rs.getString("website_update_date"));// 4
				alist.add(rs.getString("cctv_status"));// 5
				alist.add(rs.getString("login_url"));// 6
				alist.add(rs.getString("username"));// 7
				alist.add(rs.getString("password"));// 8
				alist.add(rs.getString("biometric_status"));// 9
				alist.add(rs.getString("college_working_hours"));// 10
				alist.add(rs.getString("institute_id"));// 11
				
		
				list.add(alist);
			}
			System.err.println("DOCUMENT LIST============="+list);
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
	public List<Map<String, Object>> DataTableSearch_College_InfrastructureDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String council_check, String website_update_date,
			String username, String biometric_status, String cctv_status, String role, String userid,
			String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, council_check, website_update_date, username,
				biometric_status, cctv_status, role, userid, institute_id);
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

			q = "select distinct p.*,TO_CHAR(p.website_update_date , 'DD-MM-YYYY') as website_update_date\n"
					+ "from clg_reg_infra_college_council p\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, council_check, website_update_date, username, biometric_status,
					cctv_status, role, userid, institute_id);

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
								+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"><input type='hidden' id='instituteId"+countFunction+"' value="+rs.getString("institute_id")+"></i></a> </li>";
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

	public long DataTableSearch_College_InfrastructureDataTotalCount(String Search, String council_check,
			String website_update_date, String username, String biometric_status, String cctv_status, String role,
			String userid, String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, council_check, website_update_date, username,
				biometric_status, cctv_status, role, userid, institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select p.*,TO_CHAR(p.website_update_date , 'DD-MM-YYYY') as website_update_date1\n"
					+ "from clg_reg_infra_college_council p\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 and p.institute_id=" + institute_id
					+ " and l.userid= " + userid + "  " + SearchValue + ")ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, council_check, website_update_date, username, biometric_status,
					cctv_status, role, userid, institute_id);
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
	public String GenerateQueryWhereClause_SQL(String Search, String council_check, String website_update_date,
			String username, String biometric_status, String cctv_status, String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( TO_CHAR(p.website_update_date , 'DD-MM-YYYY') like ? "
//					+ "or cast(p.cctv_status as varchar) like ?"
					+ " or upper(p.username) like ?"
//					+ "or cast(p.biometric_status as varchar) like ?"
					+ ") ";
		}

		/// advance search

		
		if (council_check != null  && council_check != ""  &&  !council_check.trim().equals("") && !council_check.trim().equals("undefined")) {
			SearchValue += " and p.council_check = ? "; // 1
		}
		if (!website_update_date.equals("") && website_update_date != "" && !website_update_date.equals("DD/MM/YYYY")){
			SearchValue += " and to_char(p.website_update_date,'DD/MM/YYYY')=?";
		}
		if (!username.trim().equals("")) {
			SearchValue += " and p.username like ? "; // 4
		}
		if (biometric_status != null  && biometric_status != ""  &&  !biometric_status.trim().equals("") && !biometric_status.trim().equals("undefined")) {
			SearchValue += " and p.biometric_status = ? "; // 5
		}
		if (cctv_status != null  && cctv_status != ""  &&  !cctv_status.trim().equals("") && !cctv_status.trim().equals("undefined")) {
			SearchValue += " and (p.cctv_status) = ? "; // 3
		}

	//if (!yr_of_exp.trim().equals("")) {
//		SearchValue += " and (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) = ? ";
	//
	//}
	//if(!institute.equals("0")) {
//		SearchValue += " and lo.institute_id = ? ";
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String council_check,
			String website_update_date, String username, String biometric_status, String cctv_status, String role,
			String userid, String institute_id) {

		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
			}

			
			System.out.println("council_check------>    "+council_check);
			
			if (council_check != null  && council_check != ""  &&  !council_check.trim().equals("") && !council_check.trim().equals("undefined")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(council_check)); // 1
			}
			if (!website_update_date.equals("") && website_update_date != "" && !website_update_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, website_update_date); //2
			}
			if (!username.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + username + "%" ); // 3
			}
			if (biometric_status != null  && biometric_status != ""  &&  !biometric_status.trim().equals("") && !biometric_status.trim().equals("undefined")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(biometric_status)); // 4
			}
			if (cctv_status != null  && cctv_status != ""  &&  !cctv_status.trim().equals("") && !cctv_status.trim().equals("undefined")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(cctv_status)); // 5
			}


//		if (!yr_of_exp.trim().equals("")) {
//			flag += 1;
//			stmt.setInt(flag, Integer.parseInt(yr_of_exp));
//		}
//		if(!institute.equals("0")) {
//			flag += 1;
//			stmt.setInt(flag,Integer.parseInt( institute));
	//
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public List<Map<String,Object>> getClg_central_lib_infoReport(int id, int institute_id,String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    String sq2 = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
//	    	if(role.equals("NCH")) {
		    	sq2="select ccl.*,p.*,chg.*,cai.*,chd.*,cpi.*,cca.*,md.*"
		    			+ " from clg_reg_infra_central_library ccl\n"
		    			+ "left join clg_reg_infra_college_council p on ccl.p_id=p.id\n"
		    			+ "left join clg_reg_infra_herbal_garden chg on chg.p_id=p.id\n"
		    			+ "left join clg_reg_infra_additional_information cai on cai.p_id=p.id\n"
		    			+ "left join clg_reg_infra_hostel_details chd on chd.p_id=p.id\n"
		    			+ "left join clg_reg_infra_progress_of_institution cpi on cpi.p_id=p.id\n"
		    			+ "left join clg_reg_infra_constructed_area cca on cca.p_id=p.id\n"
		    			+"left join clg_reg_infra_mess_details md on md.p_id=p.id\n"
		    			+ " where p.institute_id=? ";
//	    	}
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2="select ccl.*,p.*,chg.*,cai.*,chd.*,cpi.*,cca.*,md.*"
//		    			+ " from clg_reg_infra_central_library ccl\n"
//		    			+ "left join clg_reg_infra_college_council p on ccl.p_id=p.id\n"
//		    			+ "left join clg_reg_infra_herbal_garden chg on chg.p_id=p.id\n"
//		    			+ "left join clg_reg_infra_additional_information cai on cai.p_id=p.id\n"
//		    			+ "left join clg_reg_infra_hostel_details chd on chd.p_id=p.id\n"
//		    			+ "left join clg_reg_infra_progress_of_institution cpi on cpi.p_id=p.id\n"
//		    			+ "left join clg_reg_infra_constructed_area cca on cca.p_id=p.id\n"
//		    			+"left join clg_reg_infra_mess_details md on md.p_id=p.id\n"
//		    			+ " where p.institute_id=? ";
//	    	}
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        if(role.equals("NCH")) {
	        	stmt.setInt(1, institute_id);
	        }
	        if(role.equals("Institute_NCH")) {
	        	stmt.setInt(1, institute_id);
	        }
	        
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT--getClg_central_lib_infoReport---"+stmt);
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
	public List<Map<String,Object>> getDepart_dtlReport(int id, int institute_id,String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    String sq2 = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
//	    	if(role.equals("NCH")) {
		    	sq2="select ccad.id,ccad.department_id,ccad.department_name,ccad.area_of_department,cca.id,cca.total_area_of_college,cca.total_lecture_hall,\n"
		    			+ "(select sum(CAST(ccad.area_of_department AS numeric))  from clg_reg_infra_constructed_area_dept ccad where ccad.p_id=? ) as total_area_of_department\n"
		    			+ "from clg_reg_infra_constructed_area_dept ccad\n"
		    			+ "left join clg_reg_infra_college_council p on p.id=ccad.p_id\n"
		    			+ "left join clg_reg_infra_constructed_area cca on cca.p_id=p.id\n"
		    			+ " where p.institute_id=? group by ccad.id,cca.id";
//	    	}
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2="select ccad.id,ccad.department_id,ccad.department_name,ccad.area_of_department,cca.id,cca.total_area_of_college,cca.total_lecture_hall,\n"
//		    			+ "(select sum(CAST(ccad.area_of_department AS numeric))  from clg_reg_infra_constructed_area_dept ccad where ccad.p_id=? ) as total_area_of_department\n"
//		    			+ "from clg_reg_infra_constructed_area_dept ccad\n"
//		    			+ "left join clg_reg_infra_college_council p on p.id=ccad.p_id\n"
//		    			+ "left join clg_reg_infra_constructed_area cca on cca.p_id=p.id\n"
//		    			+ " where p.institute_id=? group by ccad.id,cca.id";
//	    	}
	    	
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        if(role.equals("NCH")) {
	        	stmt.setInt(1, id);
	        	stmt.setInt(2, institute_id);
	        }
	        if(role.equals("Institute_NCH")) {
	        	stmt.setInt(1, id);
	        	stmt.setInt(2, institute_id);
	        }
	        
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-----"+stmt);
	        ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			float jj = 0;
			
			while (rs.next()) {
				
				
				jj += rs.getFloat("total_area_of_department");
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				columns.put("total_area_of_department", jj);
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
	public List<Map<String,Object>> getClg_central_lib_childinfoReport(int id, int institute_id,String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    String sq2 = null;
	    try{          
	    	conn = dataSource.getConnection();
		    	sq2="select p.*,c.* from clg_reg_infra_central_library_child c\n"
		    			+ "inner join clg_reg_infra_college_council p on c.p_id=p.id\n"
		    			+ "where p.institute_id=? ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        	stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---getClg_central_lib_childinfoReport--"+stmt);
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
	
	//GET VIEW ID
		@Override
		public List<Map<String,Object>> getView_idFrom_Institute_id(int institute_id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{          
			conn = dataSource.getConnection();
			
			String sq2="select * from clg_reg_remarks where inst_id = ?";
			
		PreparedStatement stmt = conn.prepareStatement(sq2);
		stmt.setInt(1, institute_id);
		System.err.println("STMT COLLEGE REGULATION--------------------"+sq2);
		ResultSet rs = stmt.executeQuery();  
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
		public List<Map<String,Object>> getClg_camera_locationinfoReport(int id, int institute_id,String role) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		    Connection conn = null;
		    String sq2 = null;
		    try{          
		    	conn = dataSource.getConnection();
			    	sq2="select * from clg_reg_infra_collge_council_camera \n"
			    			+ "where institute_id=? ";
		    	
		        PreparedStatement stmt = conn.prepareStatement(sq2);
		        	stmt.setInt(1, institute_id);
		        ResultSet rs = stmt.executeQuery();  
		        System.err.println("STMT---getClg_camera_locationinfoReport--"+stmt);
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
