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
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_HOS_DEPT_MSTR;

@Service
@Repository
public class Hospital_Dept_DAOimpl implements Hospital_Dept_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableHospitalDepartmentDataTotalCount(String Search, String department_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department_name);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,department_name,status from clg_reg_hos_dept_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, department_name);
			
			System.out.println(stmt);

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
	public String GenerateQueryWhereClause_SQL(String Search, String department_name) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(department_name) like ?)";

		}

		if (department_name != null && !department_name.equals("")) {
			SearchValue += " and upper(department_name) like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String department_name) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (department_name != null && !department_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + department_name.toUpperCase() + "%");
			}
		} catch (Exception e) {
	}

		return stmt;
	}
	@Override
	public List<Map<String, Object>> DataTableHospitalDepartmentDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String department_name, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, department_name);
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
			

			if (Search.equals("") && department_name != null && status == "1") {
				q = "select ROW_NUMBER() OVER(order by department_name ASC) as sr_no,id,department_name,status from clg_reg_hos_dept_mstr where status='1'" + SearchValue
						+ " ORDER BY deparment_name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by department_name ASC) as sr_no,id,department_name,status from clg_reg_hos_dept_mstr where status='" + status + "'"
						+ SearchValue + " ORDER BY department_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, department_name);
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
				
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDepartmentName' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								
								+"<input type='hidden' id='apdepartmentnameAGE"+countFunction+"' value='"+rs.getString("department_name")+"'>"

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

@Override
public String updateHospitalDepartment(CLG_REG_HOS_DEPT_MSTR td) {
	Session session1 = this.sessionFactory.openSession();
	Transaction tx = session1.beginTransaction();
	//System.out.print(td);
	
	 String msg = "";
	try{
		String hql = "update CLG_REG_HOS_DEPT_MSTR set department_name=:department_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id";

		 Query query = session1.createQuery(hql)
				.setParameter("department_name", td.getDepartment_name())
				.setParameter("status", td. getStatus())
				.setParameter("modified_by", td.getModified_by())
				.setParameter("modified_date", td.getModified_date())
				.setParameter("id", td.getId());
		 
		 System.out.print(query);
		
		msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
		//msg = query.executeUpdate() > 0 ? "1" : "0";
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


