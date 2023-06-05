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
public class sixA_paper_mark_DaoImpl  implements sixA_paper_mark_Dao{

	@Autowired
	private DataSource dataSource;
	
	
	public ArrayList<ArrayList<String>> get6AViewdatabyCourse(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q="select cm.course_code,\n"
					+ "(select count(ec.exam_paper) \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as papers,\n"
					+ "(select sum(ec.theory_comp_marks) \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as theory,\n"
					+ "(select epc.practical_marks \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as practical,\n"
					+ "(select epc.viva_marks \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as viva,\n"
					+ "(select epc.elective_marks \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as elective,\n"
					+ "(select epc.ia_marks \n"
					+ "	  from edu_cc_summary_parent p\n"
					+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as ia\n"
					+ "from edu_cc_summary_parent p \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id\n"
					+ "where p.course_id=?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			stmt.setInt(2, Integer.parseInt(course_id));
			stmt.setInt(3, Integer.parseInt(course_id));
			stmt.setInt(4, Integer.parseInt(course_id));
			stmt.setInt(5, Integer.parseInt(course_id));
			stmt.setInt(6, Integer.parseInt(course_id));
			stmt.setInt(7, Integer.parseInt(course_id));
			System.err.println("Child Table Data==========="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("course_code"));//0
				list.add(rs.getString("papers"));//1 
				list.add(rs.getString("theory"));//2
				list.add(rs.getString("practical"));//3
				list.add(rs.getString("viva"));//4
				list.add(rs.getString("elective"));//5
				list.add(rs.getString("ia"));//6
				
				alist.add(list);
				System.err.println("list------------"+list);
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
