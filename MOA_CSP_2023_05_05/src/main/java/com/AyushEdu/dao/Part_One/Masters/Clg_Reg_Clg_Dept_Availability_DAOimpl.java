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
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_CLG_DEPT_AVAILABILITY_MSTR;


@Repository
public class Clg_Reg_Clg_Dept_Availability_DAOimpl implements Clg_Reg_Clg_Dept_Availability_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableClgDataTotalCount(String Search, String department) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,department,status from clg_reg_clg_dept_availabilty_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, department);
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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String department) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(department) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( department != null && !department.equals("")) {
			SearchValue += " and upper(department) like ? ";
		
	     }

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String department) {
		
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (department != null && !department.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+department.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableClgDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String department, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department);
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

			if(Search.equals("") && department.equals("0") && status=="1") {
				q = "select id,department,status from clg_reg_clg_dept_availabilty_mstr where status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,department,status from clg_reg_clg_dept_availabilty_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, department);
			System.out.print(stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDClgDeptAvailability' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apId"+countFunction+"' value="+rs.getString("id")+">"
								
								+"<input type='hidden' id='apDepartment"+countFunction+"' value='"+rs.getString("department")+"'>"

								+"<input type='hidden' id='apStatus"+countFunction+"' value="+rs.getString("status")+" ></i></a> </li>";
				
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

	public CLG_REG_CLG_DEPT_AVAILABILITY_MSTR getClgDeptAvailByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CLG_REG_CLG_DEPT_AVAILABILITY_MSTR updateid = (CLG_REG_CLG_DEPT_AVAILABILITY_MSTR) session.get(CLG_REG_CLG_DEPT_AVAILABILITY_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
	
	public String updateClgDept(CLG_REG_CLG_DEPT_AVAILABILITY_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CLG_REG_CLG_DEPT_AVAILABILITY_MSTR set department=:department,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("department", obj.getDepartment()).setParameter("status", (obj. getStatus()))
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
