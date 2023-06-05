package com.AyushEdu.dao.Curriculum_Pdf;

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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;

@Repository

public class T_1_PDF_DAOimpl implements T_1_PDF_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
//	public ArrayList<ArrayList<String>> DataTableExamtypeDataTotalCount1(String course_id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
////		String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
//		try {
//			conn = dataSource.getConnection();
//			String sql = "";
//			PreparedStatement stmt = null;
//
//			sql = "select com.co_code,com.course_outcome as co,string_agg(pom.code,',') as po\n"
//					+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent p\n"
//					+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child c on c.p_id=p.id\n"
//					+ "inner join edu_cc_tb_add_course_outcome_mstr com on com.id=p.course_outcome_id\n"
//					+ "inner join edu_cc_tb_program_outcome_mstr pom on pom.id=c.programoutcome_id\n"
//					+ "where p.course_id=? and p.status=1 \n"
//					+ "group by 1,2,p.id order by p.id ";
//
//			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("stmt-----------" + stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			int i = 0;
//			while (rs.next()) {
//				i++;
//				ArrayList<String> list = new ArrayList<String>();
//
//				list.add(rs.getString("co_code"));// 0
//				list.add(rs.getString("co"));// 1
//				list.add(rs.getString("po"));// 2
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}
	public ArrayList<ArrayList<String>> non_lec_activities(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
//		String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select c.practical,c.activity_practical_description,t.term from edu_cc_tb_list_of_practical_parent p\n"
					+ "inner join edu_cc_tb_list_of_practical_child c on c.p_id = p.id\n"
					+ "inner join edu_lms_term_mstr t on t.id = c.term_id where p.course_id=?";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("stmt-----------" + stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("practical"));// 0
				list.add(rs.getString("activity_practical_description"));// 1
				list.add(rs.getString("term"));// 2

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
	
	public ArrayList<ArrayList<String>> table1_co_po_link(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
//		String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select com.co_code,com.course_outcome as co,string_agg(pom.code,',') as po\n"
					+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent p\n"
					+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child c on c.p_id=p.id\n"
					+ "inner join edu_cc_tb_add_course_outcome_mstr com on com.id=p.course_outcome_id\n"
					+ "inner join edu_cc_tb_program_outcome_mstr pom on pom.id=c.programoutcome_id\n"
					+ "where p.course_id=? and p.status=0 \n"
					+ "group by 1,2,p.id order by p.id ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("stmt-----------" + stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("co_code"));// 0
				list.add(rs.getString("co"));// 1
				list.add(rs.getString("po"));// 2

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
