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
public class Summary_ReportDaoImpl implements Summary_ReportDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> get_Summary_report(int startPage, int pageLength, String search,
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
				sd = " and ss.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and ss.system_id = 45 ";
			}
					q = "select distinct ss.id, d.degree_name,s.name,(select count(*) from edu_exam_tb_supplementary_student where student_id = s.id and subject::int=c.id ) as count,\n"
							+ "c.course_name,to_char(date_of_exam,'DD-MON-YYYY') as date_of_exam\n"
							+ "from edu_exam_tb_supplementary_student ss\n"
							+ "inner join edu_lms_student_details s on s.id=ss.student_id\n"
							+ "inner join edu_lms_course_mstr c on c.id=ss.subject::int\n"
							+ "inner join edu_lms_degree_mstr d on d.id=ss.degree_id" + sd 
							+ SearchValue + " ORDER BY ss.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

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
	public long getTotal_Summary_report_dataCount(String Search, String state_id, String university_id,
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
				sd = " and ss.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and ss.system_id = 45 ";
			}
			
			
//			if (role.trim().contains("NCISM")) {

			q = "select count(*) \n"
					+ " from (select distinct ss.id, d.degree_name,s.name,(select count(*) from edu_exam_tb_supplementary_student where student_id = s.id and subject::int=c.id ) as count,\n"
					+ "c.course_name,to_char(date_of_exam,'DD-MON-YYYY') as date_of_exam\n"
					+ "from edu_exam_tb_supplementary_student ss\n"
					+ "inner join edu_lms_student_details s on s.id=ss.student_id\n"
					+ "inner join edu_lms_course_mstr c on c.id=ss.subject::int\n"
					+ "inner join edu_lms_degree_mstr d on d.id=ss.degree_id " +sd
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

//		if (Search != null && !Search.equals("")) {
//			SearchValue += " and (upper(stm.state_name) like ? or upper(um.university_name) like ?  "
//					+ " or upper(ir.institute_name) like ?  or upper(dm.degree_name) like ? )";
//		}
//
//		if (!state_id.equals("0")) {
//			SearchValue += " and ir.state_id = ? ";
//		}
//		if (!university_id.equals("0")) {
//			SearchValue += " and ir.university_id = ? ";
//		}
//		if (!institute_id.equals("0")) {
//			SearchValue += " and ir.id =? ";
//		}
//		if (!degree_id.equals("0")) {
//			SearchValue += " and dm.id =? ";
//		}
//		if (!professional_id.equals("0")) {
//			SearchValue += " and sd.semester as term =? ";
//		}
//		if (date_of_exam != null && !date_of_exam.equals("") && !date_of_exam.equals("DD/MM/YYYY")) {
//			SearchValue += "and a.date_of_exam = ? ";
//		}

		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String state_id, String university_id,
			String institute_id, String degree_id, String professional_id) {
		int flag = 0;
		try {

//			if (Search != null && !Search.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//			}
//
//			if (!state_id.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(state_id));
//			}
//			if (!university_id.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(university_id));
//			}
//			if (!institute_id.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(institute_id));
//			}
//			if (!degree_id.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(degree_id));
//			}
//			if (!professional_id.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(professional_id));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
}
