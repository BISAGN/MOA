package com.AyushEdu.dao.LMS_Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Exp_Excel_Attendance_DaoImpl implements Exp_Excel_Attendance_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<ArrayList<String>>Exp_Excel_attendance_report(String role,String institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			int lastDay =0;
			if(role.contains("NCH")) {
				 q="select l.login_name,atd.ayush_id,atd.teacher_code from logininformation l\n"
					 		+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
					 		+ "inner join roleinformation r on r.role_id=ro.role_id and role= ? and institute_id=? and enabled=1"
					 		+ "inner join tb_nch_add_teacher_details atd on atd.user_id=l.userid";
			} 
			if(role.contains("NCISM")) {
			
			 q="select l.login_name,fn.teacher_code,fn.ayush_id_ncism from logininformation l\n"
			 		+ "			 inner join userroleinformation ro on ro.user_id=l.userid\n"
			 		+ "			 inner join roleinformation r on r.role_id=ro.role_id and role= ? and institute_id=? and enabled=1\n"
			 		+ "			 inner join edu_lms_faculty_nch fn on fn.email=l.email_id";
			}

			 role="Faculty_"+role; 
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1, role);
				stmt.setInt(2, Integer.parseInt(institute_id));
				System.err.println("quey print------>"+stmt);
				ResultSet rs = stmt.executeQuery();
			int x=1;
			while (rs.next()) {
				int p_count=0;
				ArrayList<String> list = new ArrayList<String>();
				System.err.println("DAOLIST"+list);
				list.add(rs.getString("login_name"));
				if(role.contains("NCH")) {
				list.add(rs.getString("ayush_id"));
				}else if(role.contains("NCISM")) {
//					list.add(""+x);
					list.add(rs.getString("ayush_id_ncism"));
				}
				list.add(rs.getString("teacher_code"));
				for(int i=1; i<=lastDay; i++) {
//					if(i==1) {
//						int s1= 0;int s2= 0;
//					}
					if (rs.getString("_"+i).equals("A")) {
						list.add("A");
						
					//	s+i++;
						
//						i++;
					}else if (rs.getString("_"+i).equals("P")){
						list.add("P");
						p_count++;
					}
				}
				list.add(p_count+"");
				alist.add(list);
//				x++;
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
//		System.err.println("Alist--"+alist);
		return alist;
	}
}
