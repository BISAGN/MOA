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
public class View_SixF_Distribution_theoryDaoImpl implements View_SixF_Distribution_theoryDao{
	
	@Autowired
	private DataSource dataSource;
	
	
	public ArrayList<ArrayList<String>> getSixFViewdata(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select distinct  pm.paper,p.id,t.topic,tm.term,d.marks,c.topic_id,sf.mcq,sf.saq,sf.laq\n"
					+ "from edu_cc_tb_list_of_topics_parent p\n"
					+ "inner join edu_cc_tb_list_of_topics_child c on c.p_id = p.id\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent d on d.id=p.id\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = c.topic_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id = c.term_id \n"
					+ "inner join edu_cc_tb_paper_mstr pm ON pm.id = p.paper_id\n"
					+ "inner join edu_cc_tb_sixf_distribution_of_theory_exam sf on sf.topic_id=c.topic_id\n"
					+ "where p.course_id = ? order by paper";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("============6F View query==========="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));//0
				list.add(rs.getString("topic"));//1 
				list.add(rs.getString("term"));//2
				list.add(rs.getString("marks"));//3
				list.add(rs.getString("paper"));//4
				list.add(rs.getString("topic_id"));//5
				list.add(rs.getString("mcq"));//6
				list.add(rs.getString("saq"));//7
				list.add(rs.getString("laq"));//8
				
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
