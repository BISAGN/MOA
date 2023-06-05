package com.AyushEdu.dao.Registration.Postgraduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;
import com.AyushEdu.dao.AyushId_Directory.AyushId_DirectoryDAO;

@Repository
public class Declaration_PG_Daoimpl implements Declaration_PG_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	AyushId_DirectoryDAO add;
	
	@Override
	public EDU_PG_REG_PERSONAL_DETAILS getStudDetView_PG_Byid(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		EDU_PG_REG_PERSONAL_DETAILS viewid = (EDU_PG_REG_PERSONAL_DETAILS) sessionHQL.get(EDU_PG_REG_PERSONAL_DETAILS.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return viewid;
	}
	
	public ArrayList<ArrayList<String>> getGrdu_pg_Detail_Data(String p_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			
			q="SELECT   distinct ROW_NUMBER() OVER(order by en.id) as sr_no, \n"
					+ "name_of_the_exam as name_of_exam, to_char(TO_DATE(month_year,'YYYY-MM'),'MONTH - YYYY') as month_year, \n"
					+ "case when rgd.university='-1'  then rgd.universityother  else u.university_name end,\n"
					+ "no_of_attempts FROM edu_pg_reg_gradu_dtls rgd\n"
					+ "inner join edu_pg_gradu_examname_mstr en on en.id::text = rgd.name_of_exam\n"
					+ "left join edu_lms_university_mstr u on u.id::text= rgd.university\n"
					+ "WHERE p_id = ?  ORDER BY 1 asc";
			
//			q="SELECT   distinct ROW_NUMBER() OVER(order by name_of_exam) as sr_no, name_of_exam, to_char(TO_DATE(month_year,'YYYY-MM'),'MONTH - YYYY') as month_year,university,no_of_attempts FROM edu_pg_reg_gradu_dtls WHERE p_id = ?  ORDER BY name_of_exam asc";
			
			
//			q="SELECT   distinct ROW_NUMBER() OVER(order by en.id) as sr_no, \n"
//					+ "name_of_the_exam as name_of_exam, to_char(TO_DATE(month_year,'YYYY-MM'),'MONTH - YYYY') as month_year,\n"
//					+ "university,no_of_attempts,universityother FROM edu_pg_reg_gradu_dtls rgd\n"
//					+ "inner join edu_pg_gradu_examname_mstr en on en.id::text = rgd.name_of_exam\n"
//					+ "WHERE p_id = ?  ORDER BY 1 asc";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("sr_no"));//0
				alist.add(rs.getString("name_of_exam"));//1
				alist.add(rs.getString("month_year"));//2
				alist.add(rs.getString("university_name"));//3
				alist.add(rs.getString("no_of_attempts"));//4
//				alist.add(rs.getString("universityother"));//5
//				alist.add(rs.getString("obtain_marks"));//6
//				alist.add(rs.getString("grade"));//7
			
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

	@Override
	public String genrate_PG_Ayush_id_on_Submit(String userid,HttpSession session) {
		Connection conn = null;
		String msg = "";
		String ayushid="";
		String idddd="";
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			
			conn = dataSource.getConnection();
			String staff_lvl = session.getAttribute("roleStaff_lvl").toString();

	        String sq1="";
	        		
	        if (staff_lvl.toUpperCase().equals("NCISM")) {
	        sq1="select sd.id from edu_lms_student_details sd \n"
	        		+ "inner join  edu_pg_reg_personal_details pd on pd.email = sd.email\n"
	        		+ "WHERE pd.p_id = ?";        
	        }
	        if (staff_lvl.toUpperCase().equals("NCH")) {
	        	 sq1="select sd.id from edu_lms_nch_student_details sd \n"
	 	        		+ "inner join  edu_pg_reg_personal_details pd on pd.email = sd.email\n"
	 	        		+ "WHERE pd.p_id = ?";        
	        }
	        
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(userid));
	        
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        while(rs.next()){
					idddd = rs.getString("id");
	        }
	        rs.close();
	        stmt.close();
			
	        ayushid =  add.getAyushID(userid, session);
	        if (staff_lvl.toUpperCase().equals("NCISM")) {
	        	EDU_LMS_STUDENT_DETAILS sdayu = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class,Integer.parseInt(idddd));
	        	
	        	if (sdayu.getAyush_id() == null   || sdayu.getAyush_id().equals("") ) {
					sdayu.setAyush_id(ayushid);
					sessionHQL.update(sdayu);
					sessionHQL.flush();
					sessionHQL.clear();
				} 
	        }
	        if (staff_lvl.toUpperCase().equals("NCH")) {
	        	EDU_LMS_NCH_STUDENT_DETAILS sdhom = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(idddd));
	        	
	        	if (sdhom.getAyush_id() == null  || sdhom.getAyush_id().equals("")) {
	        		sdhom.setAyush_id(ayushid);
	        		sessionHQL.update(sdhom);
	        		sessionHQL.flush();
					sessionHQL.clear();
				} 
	        }
	        
	        msg="DONE";
	        tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			msg="NOT-DONE";
			tx.rollback();
		}
		sessionHQL.close();
		return msg;
	}
}
