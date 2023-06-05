package com.AyushEdu.dao.Part_One.Masters;

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
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR;


@Service

public class Last_Year_Teacher_Dtls_DAOImpl implements Last_Year_Teacher_Dtls_DAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	

	public long DataTablelast_year_teacher_dtlsTotalCount(String Search, String teacher_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, teacher_name);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,teacher_dtls,status from clg_reg_last_year_teacher_dtls_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, teacher_name);

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
	private String GenerateQueryWhereClause_SQL(String search, String teacher_name) {
		String SearchValue = "";
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(teacher_dtls) like ?)";

		}

		if (teacher_name != null && !teacher_name.equals("")) {
			SearchValue += " and upper(teacher_dtls) like ? ";

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String teacher_name) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (teacher_name != null && !teacher_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + teacher_name.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	
	public List<Map<String, Object>> DataTablelast_year_teacher_dtlsList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String teacher_name, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, teacher_name);
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

			if (Search.equals("") && teacher_name.equals("0") && status == "1") {
				q = "select ROW_NUMBER() OVER(order by teacher_dtls ASC) as sr_no,id,teacher_dtls,status from clg_reg_last_year_teacher_dtls_mstr where status='1'" + SearchValue
						+ " ORDER BY teacher_dtls " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by teacher_dtls ASC) as sr_no,id,teacher_dtls,status from clg_reg_last_year_teacher_dtls_mstr where status='" + status + "'"
						+ SearchValue + " ORDER BY teacher_dtls " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, teacher_name);
			
			//System.err.println("stmt-------->    "+stmt);
			
			ResultSet rs = stmt.executeQuery();
         
			
			
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDteacher' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								
								+"<input type='hidden' id='apteacher_nameAGE"+countFunction+"' value='"+rs.getString("teacher_dtls")+"'>"

								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+" ></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
				ul+=f + " " + f1 ;
				ul+="</ul>";

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


	public CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR getlast_year_teacher_dtlsByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR updateid = (CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR) session.get(CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
	
	public String updatelast_year_teacher_dtls(CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR set teacher_dtls=:teacher_dtls,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("teacher_dtls", obj.getTeacher_dtls()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
//			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
	
	
}
