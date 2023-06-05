package com.AyushEdu.dao.Registration.Postgraduate;


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

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;

@Repository
public class Pg_Gradu_ExamName_DAOimpl implements Pg_Gradu_ExamName_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public long DataTableExamtypeDataTotalCount1(String Search, String name_of_the_exam,String system_id, String degree_id,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name_of_the_exam, system_id, degree_id, status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select e.id,e.name_of_the_exam ,e.system_id,e.degree_id,s.system_name,d.degree_name from edu_pg_gradu_examname_mstr e\n"
					+ "inner join edu_lms_system_mstr s on s.id = e.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = e.degree_id\n"
					+ "WHERE e.id is not null and e.status='"+ status +"'\n"
					+ SearchValue + ") ab";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,name_of_the_exam,system_id,degree_id);
			System.err.println("stmt"+stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String name_of_the_exam,String system_id, String degree_id,String status) {
		String SearchValue = "";
	
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_the_exam) like ? or upper(s.system_name) like ? or upper(d.degree_name) like ? )";
			//System.err.println("globalllll search"+SearchValue);  
		}
		
		///advance search
	
		if( name_of_the_exam != null && !name_of_the_exam.equals("")) {
			SearchValue += " and upper(name_of_the_exam) like ? ";
		
	     }
		//dropdown search
		if( !system_id.equals("0")) {
			SearchValue += " and upper(system_id) like ? ";
		
	     }
         
		if( !degree_id.equals("0")) {
			SearchValue += " and upper(degree_id) like ? ";
		
	     }
	
        
	/*   if(!SearchValue.contains("and") && !professional.equals("")){
			SearchValue += " and upper(professional) like ? ";
			System.err.println("parameter search"+SearchValue);
} */
          
      
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name_of_the_exam,String system_id, String degree_id) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
			}
			
			
			if (name_of_the_exam != null && !name_of_the_exam.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+name_of_the_exam.toUpperCase() + "%");
			}
			
			if (system_id != null && !system_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+system_id.toUpperCase() + "%");
			}
			
			if (degree_id != null && !degree_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+degree_id.toUpperCase() + "%");
			}
			
			
			
			 // if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			  //Integer.parseInt(status)); }
			 

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String, Object>> DataTableExamtypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name_of_the_exam,String system_id, String degree_id,String status) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,name_of_the_exam, system_id, degree_id, status);
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

			if(Search.equals("") && name_of_the_exam.equals("0") && system_id.equals("0") && degree_id.equals("0") && status=="1") {
				q = "select e.id,e.name_of_the_exam ,e.system_id,e.degree_id,s.system_name,d.degree_name,e.status from edu_pg_gradu_examname_mstr e\n"
						+ "inner join edu_lms_system_mstr s on s.id = e.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = e.degree_id\n"
						+ "WHERE e.id is not null and e.status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit \n"
						+ pageL + " OFFSET " + startPage;
			}else {
				
				
				
				
			q = "select e.id,e.name_of_the_exam ,e.system_id,e.degree_id,s.system_name,d.degree_name,e.status from edu_pg_gradu_examname_mstr e\n"
					+ "inner join edu_lms_system_mstr s on s.id = e.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = e.degree_id\n"
					+ "WHERE e.id is not null and e.status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,name_of_the_exam,system_id,degree_id);
			System.err.println("stmt--->"+stmt);
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
				
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDExamname' value='ADD' title='editData' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdExam"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='apnameExam"+countFunction+"' value="+rs.getString("name_of_the_exam")+">"
								+"<input type='hidden' id='apsystemExam"+countFunction+"' value="+rs.getString("system_id")+">"
								+"<input type='hidden' id='apdegreeExam"+countFunction+"' value="+rs.getString("degree_id")+">"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
		return list;
		
		
	}
	
	public EDU_PG_GRADU_EXAMNAME getExamtypetByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_PG_GRADU_EXAMNAME updateid = (EDU_PG_GRADU_EXAMNAME) session.get(EDU_PG_GRADU_EXAMNAME.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateExamtype(EDU_PG_GRADU_EXAMNAME obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_PG_GRADU_EXAMNAME set name_of_the_exam=:name_of_the_exam,system_id=:system_id, degree_id=:degree_id, status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("name_of_the_exam", obj.getName_of_the_exam()) .setParameter("system_id", obj.getSystem_id()) .setParameter("degree_id", obj.getDegree_id()) .setParameter("status", obj.getStatus())  
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
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
