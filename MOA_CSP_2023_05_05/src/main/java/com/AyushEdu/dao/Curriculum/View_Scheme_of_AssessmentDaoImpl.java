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
public class View_Scheme_of_AssessmentDaoImpl implements View_Scheme_of_AssessmentDao {
	
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse(String course_id,String term) {
//		System.err.println("-------NCH--------"+role);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q="select p.id,pm.professional,t.term,p.no_of_exam,e.exam_type  from edu_cc_tb_link_exam_and_course p\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
					+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id where p.course_id=? and t.term=? order by id asc";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			stmt.setString(2, term);
			System.err.println("Child Table Data==========="+stmt);
			ResultSet rs = stmt.executeQuery();
			int ser = 1;
			String term1 = "";
			String term2 = "";
			String term3 = "";
			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				
				if(ser == 1) {
					term1 += rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
				}
				if(ser == 2) {
					term1 += " + " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
				}
//				if(ser == 3) {
//					System.err.println("riiiiiiiiiiiii");
////					if(role.contains("NCH")) {
////						System.err.println("hiiiiiiiiii");
////					term1 += " & " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
////					System.err.println("riiiiiiiiiiiii"+term1);
////					}
//				}
				
				list.add(term1);
				ser++;
			}
			alist.add(list);
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
