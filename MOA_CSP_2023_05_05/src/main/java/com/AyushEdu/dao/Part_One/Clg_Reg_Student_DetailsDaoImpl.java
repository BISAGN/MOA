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
public class Clg_Reg_Student_DetailsDaoImpl implements Clg_Reg_Student_DetailsDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableSearch_Student_DetailsDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck, String role, String userid,
			String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, last_student, year, internsopd, internsipd, 
				prescribe, seminar, house_job, no_house_job, migrationcheck, 
				 role, userid,institute_id);
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

			q = "select p.last_student,p.year,sdc.internsopd,sdc.internsipd,sdc.prescribe,sdc.seminar,"
					+ "sdc.house_job,sdc.no_house_job,sdc.migrationcheck\n"
					+ "from clg_reg_student_dtl_admitted_student p\n"
					+ "inner join clg_reg_student_dtl_upload_doc sdc on sdc.institute_id=p.institute_id\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 and p.institute_id=" + institute_id
					+ " and l.userid=" + userid + "  " + SearchValue + " order by p.id " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, last_student, year, internsopd, internsipd, 
					prescribe, seminar, house_job, no_house_job, migrationcheck, 
					 role, userid,institute_id);

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

//			String f = "";
//			String action = "";
//			String f1 = "";
//			String f2 = "";
//			
//			String ul="";
//			ul+="<ul class='buttons-group mainbtn action daobtn'>";
//			String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
//					+ rs.getString("id") + "')}else{ return false;}\"";
//			f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
//							"<i class='lni lni-eye'></i></a> </li>"
//							+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
//			ul+=f + " " + f1 + " " + " " + f2;
//			 ul+="</ul>";
//			 countFunction+=1;
//			action = ul;
//			columns.put("action", action);

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

	public long DataTableSearch_Student_DetailsDataTotalCount(String Search, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck, String role, String userid,
			String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, last_student, year, internsopd, internsipd, 
				prescribe, seminar, house_job, no_house_job, migrationcheck, 
				 role, userid,institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select p.last_student,p.year,sdc.internsopd,sdc.internsipd,sdc.prescribe,sdc.seminar,"
					+ "sdc.house_job,sdc.no_house_job,sdc.migrationcheck\n"
					+ "from clg_reg_student_dtl_admitted_student p\n"
					+ "inner join clg_reg_student_dtl_upload_doc sdc on sdc.institute_id=p.institute_id\n"
					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
					+ "where p.id!=0 and p.institute_id=" + institute_id
					+ " and l.userid= " + userid + "  " + SearchValue + ")ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, last_student, year, internsopd, internsipd, 
					prescribe, seminar, house_job, no_house_job, migrationcheck, 
					 role, userid,institute_id);
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
	public String GenerateQueryWhereClause_SQL(String Search, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck, String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(p.last_student) like ? or cast(p.year as varchar) like ? or cast(sdc.internsopd as varchar) like ? "
					+ "or cast(sdc.internsipd as varchar) like ? or upper(sdc.prescribe) like ? or upper(sdc.seminar) like ? or upper(sdc.house_job) like ?"
					+ " or cast(sdc.no_house_job as varchar) like ?"
					+ ") ";
		}

		/// advance search

		if (!last_student.trim().equals("")) {
			SearchValue += " and upper(p.last_student) like ? "; // 1
		}
		if (!year.trim().equals("0")) {
			SearchValue += " and p.year = ? "; // 2
		}
		if (!internsopd.trim().equals("")) {
			SearchValue += " and sdc.internsopd = ? "; // 3
		}
		if (!internsipd.trim().equals("")) {
			SearchValue += " and (sdc.internsipd) = ? "; // 4
		}
		if (!prescribe.trim().equals("")) {
			SearchValue += " and upper(sdc.prescribe) = ? "; // 5
		}
		if (!seminar.trim().equals("")) {
			SearchValue += " and upper(sdc.seminar) like ? "; // 6
		}
		if (!house_job.trim().equals("")) {
			SearchValue += " and upper(sdc.house_job) like ? "; // 7
		}
		if (!no_house_job.trim().equals("")) {
			SearchValue += " and (sdc.no_house_job) = ? "; // 7
		}
		if (migrationcheck != null  && migrationcheck != ""  &&  !migrationcheck.trim().equals("") && !migrationcheck.trim().equals("undefined")) {
			SearchValue += " and (sdc.migrationcheck) = ? "; // 8
		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String last_student, String year,String internsopd,String internsipd, 
			String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck, String role, String userid,
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 6
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 7
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 8
			}

			if (!last_student.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + last_student.toUpperCase() + "%" ); // 1
			}
			if (!year.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(year)); // 2
			}
			if (!internsopd.trim().equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(internsopd)); // 3
			}
			if (!internsipd.trim().equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(internsipd)); // 4
			}
			if (!prescribe.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + prescribe.toUpperCase() + "%" ); // 5
			}
			if (!seminar.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + seminar.toUpperCase() + "%" ); // 6
			}
			if (!house_job.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,"%" + house_job.toUpperCase() + "%" ); // 7
			}
			if (!no_house_job.trim().equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(no_house_job)); // 8
			}
			if (migrationcheck != null  && migrationcheck != ""  &&  !migrationcheck.trim().equals("") && !migrationcheck.trim().equals("undefined")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(migrationcheck)); // 9
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	
	//GET ADMITTED STUDENT DETAILS
	public ArrayList<ArrayList<String>> getAdmitted_Student_Details(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,institute_id,year,current_year,govt_quota_ug,mang_quota_ug,govt_quota_pg,mang_quota_pg,\n"
					+ "court_order,last_student,TO_CHAR(last_stu_add_date,'DD/MM/YYYY') as last_stu_add_date,final_status\n"
					+ "from clg_reg_student_dtl_admitted_student where institute_id = ? order by id";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Admitted Student Details================"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("institute_id"));// 1
				alist.add(rs.getString("year"));// 2
				alist.add(rs.getString("current_year"));// 3
				alist.add(rs.getString("govt_quota_ug"));// 4
				alist.add(rs.getString("mang_quota_ug"));// 5
				alist.add(rs.getString("govt_quota_pg"));// 6
				alist.add(rs.getString("mang_quota_pg"));// 7
				alist.add(rs.getString("court_order"));// 8
				alist.add(rs.getString("last_student"));// 9
				alist.add(rs.getString("last_stu_add_date"));// 10
				alist.add(rs.getString("final_status"));// 11
				
		
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
