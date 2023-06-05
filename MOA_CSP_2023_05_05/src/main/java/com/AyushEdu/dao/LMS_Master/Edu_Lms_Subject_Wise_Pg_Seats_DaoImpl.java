package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_WISE_PG_SEATS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;

@Repository
public class Edu_Lms_Subject_Wise_Pg_Seats_DaoImpl implements Edu_Lms_Subject_Wise_Pg_Seats_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTablesubjectdegreepglinkDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_id,String degree, String pg_subject,
			String seat, String status, HttpSession session) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_id,degree, pg_subject, seat,status);

		String sd = "";

//		if (role.contains("NCISM")) {
//			sd = " and p.system_id != 45 ";
//		}
//		if (role.contains("NCH")) {
//			sd = " and p.system_id = 45 ";
//		}

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
			
			q = "  select a.id,a.institute_id,b.institute_name,a.degree,d.degree_name,a.pg_subject,c.course_name,a.seat \n"
					+ " from edu_lms_subjects_wise_pg_seats a\n"
					+ " inner join edu_lms_institute_reg b on b.id=a.institute_id\n"
					+ " inner join edu_lms_degree_mstr d on d.id = a.degree\n"
					+ " inner join edu_lms_course_mstr c on c.id = a.pg_subject\n"
					+ " where a.status=1"
					+ SearchValue + "ORDER BY institute_id  " + orderType + " limit " + pageL + " OFFSET "
					+ startPage;
		
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt----main---------------------------->  " + stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id,degree, pg_subject, seat,status);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction = 1;
			int countFunctionDelete = 1;
			int countview = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
//				 <i class='lni lni-trash-can'></i></a> </li>";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value='" + rs.getString("id") + "'>" + "<input type='hidden' id='hinstituteid" + countFunction
						+ "' value='" + rs.getString("institute_id") + "'>" + "<input type='hidden' id='hdegree"
						+ countFunction + "' value='" + rs.getString("degree") + "'>"
						+ "<input type='hidden' id='hpdsubject" + countFunction + "' value='"
						+ rs.getString("pg_subject") + "'>" + "<input type='hidden' id='hseat" + countFunction
						+ "' value='" + rs.getString("seat") + "'>" + "</i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='deleteID" + countFunctionDelete
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='deleteID"
						+ countFunctionDelete + "' value=" + rs.getString("id") + "></i></a> </li>";
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("system_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("degree_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("professional_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("course_id")+">";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
				columns.put("action", action);

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
	public long DataTablesubjectdegreepglinkDataListTotalCount(String Search, String institute_id,String degree,
			String pg_subject, String seat, String status, HttpSession session) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_id,degree, pg_subject, seat,status);
		String sd = "";
//
//		if (role.contains("NCISM")) {
//			sd = " and p.system_id != 45 ";
//		}
//		if (role.contains("NCH")) {
//			sd = " and p.system_id = 45 ";
//		}

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			// Query for count page in data-table....by ruler
		
			q = "select count(*) from ( select a.id,a.institute_id,b.institute_name,a.degree,d.degree_name,a.pg_subject,c.course_name,a.seat \n"
					+ " from edu_lms_subjects_wise_pg_seats a\n"
					+ " inner join edu_lms_institute_reg b on b.id=a.institute_id\n"
					+ " inner join edu_lms_degree_mstr d on d.id = a.degree\n"
					+ " inner join edu_lms_course_mstr c on c.id = a.pg_subject\n"
					+ " where a.status=1\n"
					+ SearchValue + ") ab  ";
		

//			q="select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.status\n"
//					+ "from edu_cc_link_system_degree_professional_course p\n"
//					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
//					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
//					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
//					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
//					+ "where p.status=1 "
//					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id,degree, pg_subject, seat,status);
//			System.err.println("STMT--------count-----------"+stmt);
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

	public String GenerateQueryWhereClause_SQL(String Search, String institute_id, String degree, String pg_subject,
			String seat, String status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter  a.id,a.institute_id,b.institute_name,a.degree,d.degree_name,a.pg_subject,c.course_name,a.seat
			SearchValue += " and (  upper(b.institute_name) like ? or upper(d.degree_name) like ? "
					+ " or upper(c.course_name) like ? or cast(a.seat as character varying) like ? ) ";
		}
		/// advance search
		if (!institute_id.equals("0") && institute_id != null) {
			SearchValue += " and a.institute_id =? ";
		}
		if (!degree.equals("0") && degree != null) {
			SearchValue += " and a.degree =? ";
		}
		if (!pg_subject.equals("0") && pg_subject != null) {
			SearchValue += " and a.pg_subject =? ";
		}
		if (!seat.trim().equals("")) {
			SearchValue += " and a.seat =? ";
		}
		

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String institute_id,String degree, String pg_subject,
			String seat, String status) {
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
				stmt.setString(flag, Search+"%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if (!institute_id.equals("0") && institute_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_id));
			}
			if (!degree.equals("0") && degree != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree));
			}
			if (!pg_subject.equals("0") && pg_subject != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(pg_subject));
			}
			if (!seat.equals("") && seat != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(seat));
			}
//			if (!seat.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + seat.toUpperCase() + "%");
//			}
			
		
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	public EDU_LMS_SUBJECT_WISE_PG_SEATS getsubjectwisepgseats(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_SUBJECT_WISE_PG_SEATS updateid = (EDU_LMS_SUBJECT_WISE_PG_SEATS) session
				.get(EDU_LMS_SUBJECT_WISE_PG_SEATS.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	public String updateSubjectWisepgSeats(EDU_LMS_SUBJECT_WISE_PG_SEATS td) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EDU_LMS_SUBJECT_WISE_PG_SEATS set institute_id=:institute_id,pg_subject=:pg_subject,seat=:seat,degree=:degree,status=:status,modify_by=:modify_by,modify_date=:modify_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("institute_id", td.getInstitute_id())
					.setParameter("pg_subject", td.getPg_subject())
					.setParameter("seat", td.getSeat()).setParameter("degree", td.getDegree())
					.setParameter("status", (td.getStatus())).setParameter("modify_by", td.getModify_by())
					.setParameter("modify_date", td.getModify_date()).setParameter("id", td.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		} finally {
			session1.close();
		}
		return msg;
	}
	
	
	@Override
	public List<Map<String, Object>> PGDegreeofInst(String inst_id) {


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select distinct c.id,c.degree_name\n"
					+ "from edu_cc_link_system_degree_professional_course a\n"
					+ "inner join edu_lms_system_degree_map_inst b on b.system_id=a.system_id\n"
					+ "inner join edu_lms_degree_mstr c on c.id=b.degree_id\n"
					+ "where b.institute_id = ? and b.status='1' and a.status=1 and c.type_of_degree=16";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(inst_id));
			
			System.err.println("\n\nstmt----PGSubjectofInst---------------------------->  " + stmt+"\n\n");

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
	public List<Map<String, Object>> PGSubjectsofDegree(String degree) {


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select d.id,d.course_name\n"
					+ "from edu_cc_link_system_degree_professional_course a\n"
					+ "inner join edu_lms_course_mstr d on d.id=a.course_id\n"
					+ "where a.degree_id = ? and a.status=1";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(degree));
			
			System.err.println("\n\nstmt----PGSubjectofInst---------------------------->  " + stmt+"\n\n");

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
	public List<Map<String, Object>> PGSubjectsofInst(String degree,String inst_id) {


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select b.id,b.course_name\n"
					+ "from edu_lms_subjects_wise_pg_seats a\n"
					+ "inner join edu_lms_course_mstr b on b.id=a.pg_subject\n"
					+ "where institute_id = ? and degree = ? and a.status=1 and b.status='1'";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(inst_id));
			stmt.setInt(2,Integer.parseInt(degree));
			
			System.err.println("\n\nstmt----PGSubjectofInst---------------------------->  " + stmt+"\n\n");

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
	
	

}
