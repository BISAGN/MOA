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
public class Clg_Reg_Dept_Comp_Printer_Avail_Report_DaoImpl implements Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	//GET department names 
	public ArrayList<ArrayList<String>> getAllDepartment_nameReport() {

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
	
	public List<Map<String,Object>> getdeptcomputerdetailsReport(int institute_id) {
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
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ugReport() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where ug_status = '1' order by id\n";

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
	
	//31
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_pgReport() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id,department_name from clg_reg_department_mstr where pg_status = '2' order by id\n";

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
	
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_apReport() {

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
	
		public List<Map<String,Object>> getugdetailsReport(int institute_id) {
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
		
			public List<Map<String,Object>> getapdetailsReport(int institute_id) {
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
	public ArrayList<ArrayList<String>> getp_idfromuser_idReport(String user_id) {

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
	
	//=======search==
	
	@Override
	public List<Map<String, Object>> DataTableSearch_College_DepartmentDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String department, String computer, String printer, String role, String userid,
			String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department, computer, printer, role, userid,
				institute_id);
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

			if (role.equals("NCH")) {
				q = "select distinct p.id,p.department,p.computer,p.printer from clg_reg_clg_dept_comp_printer_avail p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0 "
						+ SearchValue + " order by id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}
			if (role.equals("Institute_NCH")) {
				q = "select distinct p.id,p.department,p.computer,p.printer from clg_reg_clg_dept_comp_printer_avail p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0 and p.institute_id=" + institute_id
						+ " and l.userid=" + userid + "  " + SearchValue + " order by id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, department, computer, printer, role, userid,
					institute_id);

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
			
			String ul="";
			ul+="<ul class='buttons-group mainbtn action daobtn'>";
			String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
					+ rs.getString("id") + "')}else{ return false;}\"";
			f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
							"<i class='lni lni-eye'></i></a> </li>"
							+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
			ul+=f + " " + f1 + " " + " " + f2;
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

	public long DataTableSearch_College_DepartmentDataTotalCount(String Search, String department, String computer, String printer, String role,
			String userid, String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department, computer, printer, role, userid,
				institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			if (role.equals("NCH")) {
				q = "select count(*) from (select distinct p.id,p.department,p.computer,p.printer from clg_reg_clg_dept_comp_printer_avail p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0  " + SearchValue + ")ab";
			}
			if (role.equals("Institute_NCH")) {
				q = "select count(*) from (select distinct p.id,p.department,p.computer,p.printer from clg_reg_clg_dept_comp_printer_avail p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0 and p.institute_id=" + institute_id
						+ " and l.userid= " + userid + "  " + SearchValue + ")ab";
			}
			

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, department, computer, printer, role, userid,
					institute_id);
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
	public String GenerateQueryWhereClause_SQL(String Search, String department, String computer, String printer, String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (p.department like ? "
					+ ") ";
		}

		/// advance search

		if (!department.trim().equals("")) {
			SearchValue += " and upper(p.department) like ? "; // 1
		}
		if (!computer.trim().equals("0")) {
			SearchValue += " and p.computer like ? ";   //2
		}
		if (!printer.trim().equals("0")) {
			SearchValue += " and p.printer like ? "; // 3
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String department, String computer, String printer, String role, String userid,
			String institute_id) {

		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
			}
			if (!department.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + department.toUpperCase() + "%"); // 1
			}
			if (!computer.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, computer);  //6
			}
			if (!printer.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag,printer); // 3
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	@Override
	public List<Map<String, Object>> getComp_Printer_Avail_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_clg_dept_comp_printer_avail ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getComp_Printer_Avail_View--" + stmt);
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
	public List<Map<String, Object>> getUG_Depart_From_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_clg_dept_ug ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
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
	public List<Map<String, Object>> getPG_Depart_From_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_clg_dept_pg ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
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
	public List<Map<String, Object>> getDepart_tours_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_clg_dept_tours ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
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
	public List<Map<String, Object>> getDepart_academic_performance_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_clg_dept_academic_performance ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
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
	public String getFilePath_DynemicQueryForDoc_clg_dept_view(String id, String val, String fildname) {
		
		
		System.err.println("id   "+ id + "  val "+val +" fildname "+ fildname);
		
		String whr = "";
		String q1="";
		Connection conn = null;
		String fildname1="";

		if(val.equals("1")) {
			q1="clg_reg_clg_dept_tours";
		}
//		else if(val.equals("2")) {
//			q1="clg_reg_clg_dept_tours";
//		}
//		else if(val.equals("2")){
//			q1="clg_reg_hos_opd_ipd_upload_documents";
//		}
//		else if(val.equals("3")){
//			q1="edu_reg_gradu_pre_edu_dtls_tbl";
//		}
//		else if(val.equals("4")){
//			q1="edu_reg_gradu_other_doc_upload";
//		}
//		else if(val.equals("5")){
//			q1="edu_reg_gradu_document_upload";
//		}
//		else if(val.equals("6")){
//			q1="edu_ncism_reg_gradu_document_upload";
//		}
//		else if(val.equals("7")){
//			q1="edu_lms_student_details";
//		}else if(val.equals("9")){
//			q1="tb_nch_teacher_quali_attach_subchild";
//		}

		
		if (fildname.equals("1")) {
			fildname1 = "edu_tours_pg";
		}
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
//		}
		
//		else if (fildname.equals("6")) {
//			fildname1 = "edu_tours_pg";
//		}
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select $fildname from "+q1+" where id=?";
			query = query.replace("$fildname", fildname1);
			
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
