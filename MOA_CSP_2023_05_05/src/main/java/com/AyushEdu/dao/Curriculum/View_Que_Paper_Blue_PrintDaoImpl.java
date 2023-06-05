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
public class View_Que_Paper_Blue_PrintDaoImpl implements View_Que_Paper_Blue_PrintDao {
	
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> getView_QuePaperBluePrint_viewdata(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q="select p.id,q.question_type,p.num_questions,p.marks_questions,p.instructions,pm.paper \n"
					+" from edu_cc_tb_paper_layout p \n"
					+" inner join edu_cc_tb_question_type_mstr q on q.id = p.question_type_id \n"
					+" inner  join edu_cc_tb_paper_mstr pm on pm.id=p.paper_id \n"
					+" where p.course_id=? and p.status=1 order by paper";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("STMT--VQB----"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));//0
				list.add(rs.getString("question_type"));//1 
				list.add(rs.getString("num_questions"));//2
				list.add(rs.getString("marks_questions"));//3
				list.add(rs.getString("instructions"));//4
				list.add(rs.getString("paper"));//5
				alist.add(list);
//				System.err.println("list------------"+list);
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
	
	
	public ArrayList<ArrayList<String>> getpaperformatdata(String course_id,String d3_desirable_know,String qt,String noofpaper) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qs ="";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(qt.equals("mcq")) {
				qs+=" and sf.mcq = 1 ";
			}
			if(qt.equals("saq")) {
				qs+=" and sf.saq = 1 ";
			}
			if(qt.equals("laq")) {
				qs+=" and sf.laq = 1 ";
			}
			
			String d3a[] = d3_desirable_know.split(",");
			String d3s = "";
			for(int d=0;d<d3a.length;d++) {
				if(d == 0) {
					d3s += " and (ttc.d3_desirable_know = "+d3a[d]+" ";
				}else {
					d3s += " or ttc.d3_desirable_know = "+d3a[d]+" ";
				}
			}
			d3s += " ) ";
			
			q="select tm.topic,pm.paper\n"
					+ "from edu_cc_tb_sixf_distribution_of_theory_exam sf \n"
					+ "inner join edu_cc_tb_t3_learning_object_parent ttp on ttp.topic_id=sf.topic_id\n"
					+ "inner join edu_cc_tb_t3_learning_object_child ttc on ttc.p_id=ttp.id\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=sf.topic_id\n"
					+ "inner join edu_cc_tb_list_of_topics_child loc on loc.topic_id=sf.topic_id\n"
					+ "inner join edu_cc_tb_list_of_topics_parent lop on lop.id=loc.p_id\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id= lop.paper_id\n"
					+ "where ttp.course_id=? and lop.status=0 "+d3s+" "+qs+" group by 1,2\n";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
//			stmt.setInt(2, Integer.parseInt(d3_desirable_know));
			System.err.println("stmt------------"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("topic"));//0
				list.add(rs.getString("paper"));//1 
			
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
	public ArrayList<ArrayList<String>> getpaperformat_NCH_data(String course_id,String mk_dk,String qt,String noofpaper) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qs ="";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(qt.equals("mcq")) {
				qs+=" and sf.mcq = 1 ";
			}
			if(qt.equals("saq")) {
				qs+=" and sf.saq = 1 ";
			}
			if(qt.equals("laq")) {
				qs+=" and sf.laq = 1 ";
			}
			
			String d3a[] = mk_dk.split(",");
			String d3s = "";
			for(int d=0;d<d3a.length;d++) {
				if(d == 0) {
					d3s += " and (ttc.mk_dk = "+d3a[d]+" ";
				}else {
					d3s += " or ttc.mk_dk = "+d3a[d]+" ";
				}
			}
			d3s += " ) ";
			
			q="select tm.topic,pm.paper\n"
					+ "from edu_cc_tb_sixf_distribution_of_theory_exam sf \n"
					+ "inner join edu_cc_tb_nch_theory_learning_object_parent ttp on ttp.topic_id=sf.topic_id\n"
					+ "inner join edu_cc_tb_nch_theory_learning_object_child ttc on ttc.p_id=ttp.id\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=sf.topic_id\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_parent lop on lop.id=sf.topic_id\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id= lop.paper_id\n"
					+ "where ttp.course_id=? "+d3s+" "+qs+" group by 1,2\n";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("stmt----getpaperformat_NCH_data--------"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("topic"));//0
				list.add(rs.getString("paper"));//1 
			
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
