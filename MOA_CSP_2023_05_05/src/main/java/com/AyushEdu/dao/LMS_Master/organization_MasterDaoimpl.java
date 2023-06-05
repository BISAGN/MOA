package com.AyushEdu.dao.LMS_Master;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_ORGANIZATION_MSTR;



@Repository
public class organization_MasterDaoimpl implements organization_MasterDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public EDU_LMS_ORGANIZATION_MSTR getsystemByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_ORGANIZATION_MSTR updateid = (EDU_LMS_ORGANIZATION_MSTR) session.get(EDU_LMS_ORGANIZATION_MSTR.class, id);
        session.getTransaction().commit();
        session.close();                
       return updateid;
	}

	@Override
	public List<Map<String, Object>> DataTableorganization_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String organization,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, organization, status);
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
			
			if(Search.equals("") && organization != null && organization.equals("0") && status == "1"  ) {
				q = "select id,organization from EDU_LMS_ORGANIZATION_MSTR where status='1'" + SearchValue + " ORDER BY id " + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,organization from EDU_LMS_ORGANIZATION_MSTR " + " where status= '" + status + "'" + SearchValue  + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, organization,status);
			
			System.err.println("------------------"+stmt);
			
			
			ResultSet rs = stmt.executeQuery();
			
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				//end
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDorganization_Master' value='ADD' title='Edit Data'>"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				String ADD1 = "onclick=\" if () ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data'>"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

				
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
		System.out.println("list-------"+list);
		return list;
	}

	@Override
	public long DataTableorganization_MasterDataTotalCount(String Search, String organization,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,organization, status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
//			Query for count page in data-table....by ruler
//			q = "select count(*) \n" + " from designation where id!=0 and status='1' " + SearchValue;
			if(status == "1" ) {
			q="select count(*) from (select id,organization from EDU_LMS_ORGANIZATION_MSTR where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}
			else {
				q="select count(*) from (select id,organization from EDU_LMS_ORGANIZATION_MSTR where id!=0 and status= '" + status + "'" 
						+ SearchValue + ") ab  ";	
			}
 
			
			System.err.println("q----------" + q);
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, organization,status);

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

	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String organization, String status) {
		int flag = 0;
		try {
			
			
			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				
			}

			if (organization != null && !organization.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + organization.toUpperCase() + "%");
			}
			
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}

	private String GenerateQueryWhereClause_SQL(String search, String organization,String status) {
		String SearchValue = "";
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(organization) like ? )  ";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
	
	
	   if(!SearchValue.contains("and") && organization != null && !organization.equals("")){
		   
			SearchValue += " and organization like ? ";
			System.err.println("parameter search"+SearchValue);	
	    }


		return SearchValue;
	}
}
