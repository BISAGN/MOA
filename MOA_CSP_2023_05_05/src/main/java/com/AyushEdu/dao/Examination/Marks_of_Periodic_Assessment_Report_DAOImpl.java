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
public class Marks_of_Periodic_Assessment_Report_DAOImpl implements Marks_of_Periodic_Assessment_Report_DAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> getFilter_Marks_perodic_reports_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String degree_id, String professional_id, String term_id,
			String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id, String role,
			String institute_uid) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, degree_id, professional_id, term_id, exam_serial,
				exam_type_id, mon_year, institute_uid,course_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {
			System.err.println("----degree_id---" + degree_id);
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {

				q = "  select p.id,d.degree_name,p.degree_id,i.term,e.exam_serial,et.exam_type,p.institute_id,ir.institute_name,p.mon_year,c.course_name,pc.marks,s.name,p.degree_id from edu_exam_tb_add_marks_of_pa_parent p\n"
						+ "inner join edu_exam_tb_add_marks_of_pa_child pc on pc.p_id=p.id\n"
						+ "inner join edu_lms_degree_mstr d on d.id= p.degree_id\n"
						+ "--inner join edu_cc_tb_professional_mstr pm on p.id = p.professional_id\n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= p.term_id\n"
						+ "inner join edu_exam_tb_exam_serial_mstr e on e.id = p.exam_serial\n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id= p.exam_type_id\n"
						+ "inner join edu_lms_institute_reg ir on ir.id= p.institute_id\n"
						+ "inner join edu_lms_course_mstr c on c.id = p.course_id\n"
						+ "inner join edu_lms_student_details s on s.id = pc.student_name_id \n"
						+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid where p.id is not null  \n";
//					+SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10   order by id " + orderType
//		            + " limit " + pageL + " OFFSET " + startPage;

				if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
					q = q + " and s.institude_userid=?  " + SearchValue
							+ " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id " + orderType + " limit " + pageL
							+ " OFFSET " + startPage;
				}
				if (role.toLowerCase().contains("university") && institute_id.equals("0")) {
					q = q + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id " + orderType + " limit "
							+ pageL + " OFFSET " + startPage;
				}
				if (role.toLowerCase().contains("institute")) {
					q = q + " and s.institude_userid=?" + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id "
							+ orderType + " limit " + pageL + " OFFSET " + startPage;
				}

			}

			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {

				q = "  select p.id,d.degree_name,p.degree_id,i.term,e.exam_serial,et.exam_type,ir.institute_name,p.institute_id,p.mon_year,c.course_name,pc.marks,s.name from edu_exam_tb_add_marks_of_pa_parent p\n"
						+ "inner join edu_exam_tb_add_marks_of_pa_child pc on pc.p_id=p.id\n"
						+ "inner join edu_lms_degree_mstr d on d.id= p.degree_id\n"
						+ "--inner join edu_cc_tb_professional_mstr pm on p.id = p.professional_id\n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= p.term_id\n"
						+ "inner join edu_exam_tb_exam_serial_mstr e on e.id = p.exam_serial\n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id= p.exam_type_id\n"
						+ "inner join edu_lms_institute_reg ir on ir.id= p.institute_id\n"
						+ "inner join edu_lms_course_mstr c on c.id = p.course_id\n"
						+ "inner join edu_lms_nch_student_details s on s.id = pc.student_name_id \n"
						+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid where p.id is not null \n";
//						+SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10   order by id " + orderType
//			            + " limit " + pageL + " OFFSET " + startPage;

				if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
					q = q + " and  s.institude_userid=?  " + SearchValue
							+ " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id " + orderType + " limit " + pageL
							+ " OFFSET " + startPage;
				}
				if (role.toLowerCase().contains("university") && institute_id.equals("0")) {
					q = q + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id " + orderType + " limit "
							+ pageL + " OFFSET " + startPage;
				}
				if (role.toLowerCase().contains("institute")) {
					q = q + " and s.institude_userid=?" + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10,11,12   order by id "
							+ orderType + " limit " + pageL + " OFFSET " + startPage;
				}

			}

			PreparedStatement stmt = conn.prepareStatement(q);
			if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
				stmt.setInt(1, Integer.parseInt(institute_id));
			}
			if (role.toLowerCase().contains("institute")) {
				stmt.setInt(1, Integer.parseInt(institute_uid));
			}
			stmt = setQueryWhereClause_SQL(stmt, Search, degree_id, professional_id, term_id, exam_serial, exam_type_id,
					mon_year, institute_id,course_id);
			System.err.println("----check the statment---www----" + stmt);
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
	public long DataTableEdu_Marks_perodic_reports_Count(String Search, String degree_id, String professional_id,
			String term_id, String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id, String role,
			String institute_id1) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, degree_id, professional_id, term_id, exam_serial,
				exam_type_id, mon_year, institute_id,course_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			String iq = " s.institude_userid=? ";

			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {

				q = "select count(*) from (select p.id,d.degree_name,i.term,e.exam_serial,et.exam_type,ir.institute_name,p.mon_year,c.course_name,pc.marks,s.name from edu_exam_tb_add_marks_of_pa_parent p \n"
						+ "inner join edu_exam_tb_add_marks_of_pa_child pc on pc.p_id=p.id \n"
						+ "inner join edu_lms_degree_mstr d on d.id= p.degree_id \n"
						+ "--inner join edu_cc_tb_professional_mstr pm on p.id = p.professional_id \n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= p.term_id \n"
						+ "inner join edu_exam_tb_exam_serial_mstr e on e.id = p.exam_serial \n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id= p.exam_type_id \n"
						+ "inner join edu_lms_institute_reg ir on ir.id= p.institute_id \n"
						+ "inner join edu_lms_course_mstr c on c.id = p.course_id \n"
						+ "inner join edu_lms_student_details s on s.id = pc.student_name_id \n"
						+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid where  p.id != 0 ";

				if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
					q = q + " and s.institude_userid=?  " + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}
				if (role.toLowerCase().contains("university") && institute_id.equals("0")) {
					q = q + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}
				if (role.toLowerCase().contains("institute")) {
					q = q + " and s.institude_userid=?" + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}

			}

			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {

				q = "select count(*) from (select p.id,d.degree_name,i.term,e.exam_serial,et.exam_type,ir.institute_name,p.mon_year,c.course_name,pc.marks,s.name from edu_exam_tb_add_marks_of_pa_parent p \n"
						+ "inner join edu_exam_tb_add_marks_of_pa_child pc on pc.p_id=p.id \n"
						+ "inner join edu_lms_degree_mstr d on d.id= p.degree_id \n"
						+ "--inner join edu_cc_tb_professional_mstr pm on p.id = p.professional_id \n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= p.term_id \n"
						+ "inner join edu_exam_tb_exam_serial_mstr e on e.id = p.exam_serial \n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id= p.exam_type_id \n"
						+ "inner join edu_lms_institute_reg ir on ir.id= p.institute_id \n"
						+ "inner join edu_lms_course_mstr c on c.id = p.course_id \n"
						+ "inner join edu_lms_nch_student_details s on s.id = pc.student_name_id \n"
						+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid where   ";

				if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
					q = q + " and s.institude_userid=?  " + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}
				if (role.toLowerCase().contains("university") && institute_id.equals("0")) {
					q = q + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}
				if (role.toLowerCase().contains("institute")) {
					q = q + " and s.institude_userid=?" + SearchValue + " group by 1,2,3,4,5,6,7,8,9,10  ) a ";
				}

			}

			PreparedStatement stmt = conn.prepareStatement(q);
			if (role.toLowerCase().contains("university") && !institute_id.equals("0")) {
				stmt.setInt(1, Integer.parseInt(institute_id));
			}
			if (role.toLowerCase().contains("institute")) {
				stmt.setInt(1, Integer.parseInt(institute_id1));
			}
			stmt = setQueryWhereClause_SQL(stmt, Search, degree_id, professional_id, term_id, exam_serial, exam_type_id,
					mon_year, institute_id,course_id);
			System.err.println("------------count--------check the statment-------" + stmt);

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
	public String GenerateQueryWhereClause_SQL(String Search, String degree_id, String professional_id, String term_id,
			String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id) throws ParseException {
		String SearchValue = "";
		
		if (Search != null && !Search.equals("")) {
			SearchValue += " and (upper(d.degree_name) like ?" + " or upper(i.term::text) like ? "
					+ " or upper(e.exam_serial::text) like ? " + " or upper(et.exam_type) like ?  "
					+ " or upper(ir.institute_name) like ?  " + " or upper(p.mon_year) like ?  "
					+ " or upper(c.course_name) like ?  " + " or upper(pc.marks::text) like ?  "
					+ " or upper(p.mon_year) like ?  " + " or upper(s.name) like ?  " + ")";
		}
		System.err.println("------------degree_id2-------" + degree_id);
		if (!degree_id.equals("0") && degree_id != null) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and p.course_id = ? ";
		}
		if (!term_id.equals("0")) {
			SearchValue += " and i.id =? ";
		}
		
		if (!exam_serial.equals("0")) {
			SearchValue += " and e.id =? ";
		}
		if (!exam_type_id.equals("0")) {
			SearchValue += " and et.id =? ";
		}
		if (!institute_id.equals("0")) {
			SearchValue += " and p.institute_id =? ";
		}
		if (mon_year != null && !mon_year.equals("") && !mon_year.equals("DD/MM/YYYY")) {
			SearchValue += " and  p.mon_year = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String degree_id,
			String professional_id, String term_id, String exam_serial, String exam_type_id, String mon_year,
			String institute_id,String course_id) {
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!degree_id.equals("0") && degree_id != null ) {
				System.err.println("degree_id=========--------------"+degree_id);
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_id));
			}
			if (!term_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(term_id));
			}
			
			if (!exam_serial.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(exam_serial));
			}
			if (!exam_type_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(exam_type_id));
			}
			if (!institute_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_id));
			}
			if (mon_year != null && !mon_year.equals("") && !mon_year.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, mon_year);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@Override
	public ArrayList<ArrayList<String>> getDegreeFromInstituteExam(String institute_id, String userId, String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM")) {
				qry = "where lo.userid=?";
				q = "select distinct dm.id,dm.degree_name from edu_lms_degree_mstr dm \n"
						+ " inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
						+ " inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;
			}
			if (role.trim().equals("Institute_NCH")) {
				qry = "where dmi.system_id=45";
				q = "select d.id,d.degree_name from edu_lms_system_degree_map_mstr dm\n"
						+ " inner join edu_lms_degree_mstr d on d.id = dm.degree_name\n" + " where system_name=45";
			}
			if (role.trim().equals("University_NCISM")) {
				q = "select id,degree_name from edu_lms_degree_mstr where status='1'";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM")) {
				stmt.setObject(1, Integer.parseInt(userId));
			}
			System.err.println("-------getDegreeListFromInstitute--------" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("degree_name"));// 1

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
