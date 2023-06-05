package com.AyushEdu.dao.AyushId_Directory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.AyushId_Directory.AYUSH_ROLE_INFORMATION;

@Service
public class Ayush_Role_InformationDAOImpl implements Ayush_Role_InformationDAO {
	
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;		
	
	@Override
	public long DataTableAyushRoleInformationDataTotalCount(String Search, Integer roleid,String role,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, roleid,role,status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();
			
			q= "select count(*) from (select a.id,r.role_id,r.role ,a.status from ayush_roleinformation a join\n"
					+ "roleinformation r on a.roleid = r.role_id"+ SearchValue +") ab ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, role,roleid,status);
			
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
	
	
	public List<Map<String, Object>> DataTableAyushRoleInformationDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, Integer roleid, String status,String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, roleid, role,status);
		//System.out.print(SearchValue);
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
			
			if(Search.equals("") && roleid.equals("0")) {				
				q= "select a.id,r.role_id,r.role ,a.status from ayush_roleinformation a join\n"
						+ "roleinformation r on a.roleid = r.role_id"+ SearchValue + " ORDER BY role " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
				q= "select a.id,r.role_id,r.role ,a.status from ayush_roleinformation a join\n"
					+ "roleinformation r on a.roleid = r.role_id"+ SearchValue + " ORDER BY role " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,role,roleid,status);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDAyushRoleInformation' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apId"+countFunction+"' value="+rs.getString("id")+">"
								
								+"<input type='hidden' id='aproleid"+countFunction+"' value='"+rs.getString("role_id")+"'>"

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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, Integer roleid,String role,String status) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(r.role) like ? or upper(a.status) like ? )";
		}
		
		///advance search
		if( roleid != null && roleid != 0) {
			SearchValue += " and roleid = ? ";
	     }
		
		if( status != null && status != "") {
			SearchValue += " and upper(status) = ? ";
	    }

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String role,Integer roleid,String status) {
		
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			
			if (roleid != null && roleid != 0) {
				flag += 1;
				stmt.setInt(flag, roleid);
			}			
			
			if (status != null && !status.equals("")) {
				flag += 1;
				stmt.setString(flag,status);
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public String updateAyushRoleInformation(AYUSH_ROLE_INFORMATION obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update AYUSH_ROLE_INFORMATION set roleid=:roleid,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("roleid", obj.getRoleid()).setParameter("status", (obj. getStatus()))
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
