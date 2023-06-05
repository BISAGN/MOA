package com.AyushEdu.dao.Examination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Add_Marks_of_PA_DaoImpl implements Add_Marks_of_PA_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> getStudent_Name(String degree_id,String institute_id, String role,  String professional_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			
		if (role.trim().equals("Institute_NCISM") || role.trim().contains("Faculty_NCISM")) {
			
			q="select distinct ROW_NUMBER() OVER(order by s.id ) as ser,s.id,s.name from edu_lms_student_details s\n"
					+ "inner join edu_lms_degree_mstr d on d.id = s.degree where s.degree=? and s.institude_userid=? and s.semester=? and s.verified_status=1  ";
		}
		if (role.trim().equals("Institute_NCH") || role.trim().contains("Faculty_NCH")) {
			q="select distinct ROW_NUMBER() OVER(order by s.id ) as ser,s.id,s.name from edu_lms_nch_student_details s\n"
					+ "inner join edu_lms_degree_mstr d on d.id = s.degree where s.degree=? and s.institude_userid=? and s.semester=? and s.verified_status=1";
		}
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(degree_id));
			stmt.setInt(2, Integer.parseInt(institute_id));
			if(professional_id.equals("15")){
				professional_id = "1";
			}
			if(professional_id.equals ("16")){
				professional_id = "2";
			}
			if(professional_id.equals ("17")){
				professional_id = "3";
			}
			stmt.setString(3, professional_id);
			System.err.println("-------getStudent_Name--------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("name"));// 0
				alist.add(rs.getString("id"));// 1
				alist.add(rs.getString("ser"));// 2
				list.add(alist);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
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
	public ArrayList<ArrayList<String>> getDegreeListFromInstituteExam(String institute_id, String userId, String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM") || role.trim().contains("Faculty_NCISM")) {
				qry = "where lo.userid=?";
				q = "select distinct dm.id,dm.degree_name from edu_lms_degree_mstr dm \n"
						+ " inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
						+ " inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;
			}
			if (role.trim().equals("Institute_NCH") || role.trim().contains("Faculty_NCH")) {
				qry = "where dmi.system_id=45";
				q = "select d.id,d.degree_name from edu_lms_system_degree_map_mstr dm\n"
						+ " inner join edu_lms_degree_mstr d on d.id = dm.degree_name\n"
						+ " where system_name=45";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM") || role.trim().contains("Faculty_NCISM")) {
				stmt.setObject(1, Integer.parseInt(userId));
			}
			System.err.println("-------getDegreeListFromInstitute--------"+stmt);
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
	public ArrayList<ArrayList<String>> getIA_Marks(String degree_id,String professional_id,String course_id,String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			
		if (role.trim().equals("Institute_NCISM") || role.trim().contains("Faculty_NCISM")) {
			
//			q="select distinct cm.id,cm.course_name ,ex.ia_marks\n"
//					+ "from edu_cc_link_system_degree_professional_course m\n"
//					+ "inner join edu_lms_course_mstr cm on cm.id=m.course_id\n"
//					+ "left join edu_cc_summary_parent p on p.course_id= m.course_id\n"
//					+ "left join edu_cc_summary_exam_pract_comp_child ex on ex.p_id=p.id  \n"
//					+ "where m.degree_id= ? and m.professional_id= ? --and p.course_id=? \nand m.status=1 ";
			
			
			q=" select distinct cm.id,concat(cm.course_name,'-',cm.course_code) as course_name ,ex.ia_marks\n"
					+ "from edu_cc_link_system_degree_professional_course m\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=m.course_id\n"
					+ "inner join edu_cc_summary_parent p on p.course_id= m.course_id\n"
					+ "inner join edu_cc_summary_exam_pract_comp_child ex on ex.p_id=p.id  \n"
					+ "where m.degree_id= ? and m.professional_id= ? --and p.course_id=? \n"
					+ " and m.status=1";
		}
		if (role.trim().equals("Institute_NCH") || role.trim().contains("Faculty_NCH")) {
			q="select distinct cm.id,concat(cm.course_name,'(',cm.course_code,')') as course_name ,ex.ia_marks\n"
					+ "from edu_cc_link_system_degree_professional_course m\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=m.course_id\n"
					+ "inner join edu_cc_summary_parent p on p.course_id= m.course_id\n"
					+ "inner join edu_cc_summary_exam_pract_comp_child ex on ex.p_id=p.id  \n"
					+ "where m.degree_id= ? and m.professional_id= ? --and p.course_id=? \n and m.status=1 ";
		}
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(degree_id));
			stmt.setInt(2, Integer.parseInt(professional_id));
//			stmt.setInt(3, Integer.parseInt(course_id));
			System.err.println("-------getIA_Marks--------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 1
				alist.add(rs.getString("ia_marks"));// 2
				list.add(alist);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
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
