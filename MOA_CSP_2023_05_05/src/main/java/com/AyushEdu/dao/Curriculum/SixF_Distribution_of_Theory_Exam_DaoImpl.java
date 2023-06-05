package com.AyushEdu.dao.Curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SixF_Distribution_of_Theory_Exam_DaoImpl implements SixF_Distribution_of_Theory_Exam_Dao {

	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> getSixFViewdatabyCourse(String course_id, String role) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (role.contains("NCISM")) {
			q = "select distinct pm.paper,ltp.id,t.topic,tm.term,twp.marks,ltc.topic_id from edu_cc_tb_list_of_topics_parent ltp \n"
					+ "inner join edu_cc_tb_list_of_topics_child ltc on ltc.p_id = ltp.id\n"
					+ "INNER join edu_cc_tb_define_topic_wise_marks_child twc on twc.topic_id = ltc.topic_id \n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent twp on twp.id = twc.p_id\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = ltc.topic_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id = ltc.term_id \n"
					+ "inner join edu_cc_tb_paper_mstr pm ON pm.id = ltp.paper_id\n"
					+ "where ltp.course_id = ?  and ltp.status=0 and t.status=1 and ltc.status=0";
			}
			if (role.contains("NCH")) {
				
			q=	"select distinct pm.paper,ltp.id,t.topic,tm.term,twp.marks,ltp.topic_id from edu_cc_tb_nch_list_of_topics_parent ltp \n"
					+ "inner join edu_cc_tb_nch_list_of_topics_child ltc on ltc.p_id = ltp.id\n"
					+ "INNER join edu_cc_tb_define_topic_wise_marks_child twc on twc.topic_id = ltp.topic_id \n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent twp on twp.id = twc.p_id\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = ltp.topic_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id = ltc.term_id \n"
					+ "inner join edu_cc_tb_paper_mstr pm ON pm.id = ltp.paper_id\n"
					+ "where ltp.course_id = ?  and ltp.status=0 and t.status=1 and ltc.status=0";
			}
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("6F QUERY===========" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("id"));// 0
				list.add(rs.getString("topic"));// 1
				list.add(rs.getString("term"));// 2
				list.add(rs.getString("marks"));// 3
				list.add(rs.getString("paper"));// 4
				list.add(rs.getString("topic_id"));// 5

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