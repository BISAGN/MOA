package com.AyushEdu.dao.Examination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Report_DaoImpl implements Report_Dao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> get_report(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role,String userid ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(search, state_id,
				university_id, institute_id, degree_id, professional_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			
			String sd = "";
			if(role.contains("NCISM")) {
				sd = " and ir.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and ir.system_id = 45 ";
			}
					q = "select distinct ir.id,stm.state_name,um.university_name,ir.institute_name,dm.degree_name,\n"
							+ "(select count(*) from history_edu_exam_tb_pass_student where university_id=ir.university_id and institute_id=ir.id ) as count,\n"
							+ "(select count(*) from history_edu_exam_tb_detain_student where university_id=ir.university_id and institute_id=ir.id) as count1,\n"
							+ "(select count(*) from history_edu_exam_tb_supplementary_student where university_id=ir.university_id and institute_id=ir.id ) as count2\n"
							+ ",ir.state_id,\n"
							+ "ir.university_id,dm.id,sd.semester as term from edu_lms_institute_reg ir\n"
							+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
							+ "inner join edu_lms_university_mstr um on um.id=ir.university_id\n"
							+ "inner join edu_lms_system_degree_map_inst sdi on sdi.institute_id=ir.id\n"
							+ "inner join edu_lms_degree_mstr dm on dm.id=sdi.degree_id\n"
							+ "inner join edu_lms_student_details sd on sd.institude_userid=ir.id\n"
							+ "inner join edu_lms_term_mstr tm on tm.id=sd.semester::int" + sd 
							+ SearchValue + " ORDER BY ir.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, state_id,
					university_id, institute_id, degree_id, professional_id);
			System.err.println("-------check the statment------30----" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			while (rs.next()) {

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
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
	
	@Override
	public long getTotal_report_dataCount(String Search, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, state_id,
				university_id, institute_id, degree_id, professional_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();


			String sd = "";
			if(role.contains("NCISM")) {
				sd = " and ir.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and ir.system_id = 45 ";
			}
			
			
//			if (role.trim().contains("NCISM")) {

			q = "select count(*) \n"
					+ " from (select distinct ir.id,stm.state_name,um.university_name,ir.institute_name,dm.degree_name,\n"
					+ "(select count(*) from history_edu_exam_tb_pass_student where university_id=ir.university_id and institute_id=ir.id ) as count,\n"
					+ "(select count(*) from history_edu_exam_tb_detain_student where university_id=ir.university_id and institute_id=ir.id) as count1,\n"
					+ "(select count(*) from history_edu_exam_tb_supplementary_student where university_id=ir.university_id and institute_id=ir.id ) as count2\n"
					+ ",ir.state_id,\n"
					+ "ir.university_id,dm.id,sd.semester as term from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
					+ "inner join edu_lms_university_mstr um on um.id=ir.university_id\n"
					+ "inner join edu_lms_system_degree_map_inst sdi on sdi.institute_id=ir.id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sdi.degree_id\n"
					+ "inner join edu_lms_student_details sd on sd.institude_userid=ir.id\n"
					+ "inner join edu_lms_term_mstr tm on tm.id=sd.semester::int " +sd
					+ SearchValue + " ) a ";


//			}

			PreparedStatement stmt = conn.prepareStatement(q);
//			if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
//				stmt.setInt(1, Integer.parseInt(institute_id));
//			}
//			if (role.toLowerCase().contains("institute")) {
//				stmt.setInt(1, Integer.parseInt(institute_id1));
//			}
			stmt = setQueryWhereClause_SQL(stmt, Search, state_id,
					university_id, institute_id, degree_id, professional_id);

			ResultSet rs = stmt.executeQuery();
			System.err.println("------------count--------check the statment-------" + stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id) throws ParseException {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) {
			SearchValue += " and (upper(stm.state_name) like ? or upper(um.university_name) like ?  "
					+ " or upper(ir.institute_name) like ?  or upper(dm.degree_name) like ? )";
		}

		if (!state_id.equals("0")) {
			SearchValue += " and ir.state_id = ? ";
		}
		if (!university_id.equals("0")) {
			SearchValue += " and ir.university_id = ? ";
		}
		if (!institute_id.equals("0")) {
			SearchValue += " and ir.id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and dm.id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and sd.semester as term =? ";
		}
//		if (date_of_exam != null && !date_of_exam.equals("") && !date_of_exam.equals("DD/MM/YYYY")) {
//			SearchValue += "and a.date_of_exam = ? ";
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String state_id, String university_id,
			String institute_id, String degree_id, String professional_id) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!state_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(state_id));
			}
			if (!university_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(university_id));
			}
			if (!institute_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_id));
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getStudent_Report_Excel(String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, String role, String search)
			throws ParseException {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL(search, state_id, university_id,
				institute_id, degree_id, professional_id);
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			String sd = "";
			if(role.contains("NCISM")) {
				sd = " and ir.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and ir.system_id = 45 ";
			}

			String qry = "";
			if (role.trim().contains("NCH") || role.trim().contains("NCISM")) {
				
				q = "select distinct ir.id,stm.state_name,um.university_name,ir.institute_name,dm.degree_name,\n"
						+ "(select count(*) from history_edu_exam_tb_pass_student where university_id=ir.university_id and institute_id=ir.id ) as count,\n"
						+ "(select count(*) from history_edu_exam_tb_detain_student where university_id=ir.university_id and institute_id=ir.id) as count1,\n"
						+ "(select count(*) from history_edu_exam_tb_supplementary_student where university_id=ir.university_id and institute_id=ir.id ) as count2\n"
						+ ",ir.state_id,\n"
						+ "ir.university_id,dm.id,sd.semester as term from edu_lms_institute_reg ir\n"
						+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
						+ "inner join edu_lms_university_mstr um on um.id=ir.university_id\n"
						+ "inner join edu_lms_system_degree_map_inst sdi on sdi.institute_id=ir.id\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sdi.degree_id\n"
						+ "inner join edu_lms_student_details sd on sd.institude_userid=ir.id\n"
						+ "inner join edu_lms_term_mstr tm on tm.id=sd.semester::int  " +sd
						+ SearchValue + " ORDER BY ir.id ";
			} else {
				
		
			if (role.trim().contains("University")) {
				
			q = "select distinct ir.id,ir.institute_name,dm.degree_name,\n"
					+ "(select count(*) from history_edu_exam_tb_pass_student where university_id=ir.university_id and institute_id=ir.id ) as count,\n"
					+ "(select count(*) from history_edu_exam_tb_detain_student where university_id=ir.university_id and institute_id=ir.id) as count1,\n"
					+ "(select count(*) from history_edu_exam_tb_supplementary_student where university_id=ir.university_id and institute_id=ir.id ) as count2\n"
					+ ",ir.state_id,\n"
					+ "ir.university_id,dm.id,sd.semester as term from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
					+ "inner join edu_lms_university_mstr um on um.id=ir.university_id\n"
					+ "inner join edu_lms_system_degree_map_inst sdi on sdi.institute_id=ir.id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sdi.degree_id\n"
					+ "inner join edu_lms_student_details sd on sd.institude_userid=ir.id\n"
					+ "inner join edu_lms_term_mstr tm on tm.id=sd.semester::int"
					+ SearchValue + " ORDER BY ir.id ";
		
				
			}
		}
	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, state_id, university_id,
					institute_id, degree_id, professional_id);
			ResultSet rs = stmt.executeQuery();
			System.out.println("\n \n -------getStudent_Result_Report_Excel----" + stmt);
			int i = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(String.valueOf(i));// 0
				alist.add(rs.getString("state_name"));// 1
				alist.add(rs.getString("university_name"));// 2
				alist.add(rs.getString("institute_name"));// 3
				alist.add(rs.getString("degree_name"));// 4
				alist.add(rs.getString("count"));// 5
				alist.add(rs.getString("count1"));// 6
				alist.add(rs.getString("count2"));// 7
				list.add(alist);
				i++;
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
	
	public ArrayList<ArrayList<String>> getuniversity_list(String state_id,String role) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		String sd = "";
		if(role.contains("NCISM")) {
			sd = " and u.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and u.system_id = 45 ";
		}

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct u.id,u.university_name from logininformation l\n"
					+ "inner join edu_lms_university_mstr u on u.id=l.university_id where u.state_id = ? " + sd ;

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(state_id));
			System.err.println("6F QUERY===========" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("id"));// 0
				list.add(rs.getString("university_name"));// 1
				alist.add(list);
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
		return alist;
	}
}
