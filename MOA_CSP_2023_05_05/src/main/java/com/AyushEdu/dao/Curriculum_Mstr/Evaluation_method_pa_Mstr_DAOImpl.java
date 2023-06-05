package com.AyushEdu.dao.Curriculum_Mstr;
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

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_EVALUATION_METHOD_PA_MSTR;

@Repository
public class Evaluation_method_pa_Mstr_DAOImpl implements Evaluation_method_pa_Mstr_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


	public long DataTableEvaluation_method_paDataTotalCount(String Search,String system_id ,String evaluation_method_pa,String status,String role) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, evaluation_method_pa);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and e.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and e.system_id = 45 ";
			}
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			if(Search.equals("") && evaluation_method_pa.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by evaluation_method_pa ) as ser,e.id,e.evaluation_method_pa,e.status,e.system_id,s.system_name\n"
					+ "from edu_cc_tb_evaluation_method_pa_mstr e\n"
					+ "inner join edu_lms_system_mstr s on s.id= e.system_id\n"
					+ " where e.status='1'  \n"+ sd
					+ SearchValue + ") ab  ";
			}else {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by evaluation_method_pa ) as ser,e.id,e.evaluation_method_pa,e.status,e.system_id,s.system_name\n"
					+ "from edu_cc_tb_evaluation_method_pa_mstr e\n"
					+ "inner join edu_lms_system_mstr s on s.id= e.system_id\n"
					+ " where e.status='"+ status +"' \n"+ sd
					+ SearchValue + ") ab  ";
			}
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id, evaluation_method_pa);

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
	public String GenerateQueryWhereClause_SQL(String Search,String system_id, String evaluation_method_pa) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(e.evaluation_method_pa) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and e.system_id  = ? ";
		}
		if( evaluation_method_pa != null && !evaluation_method_pa.equals("")) {
			SearchValue += " and upper(e.evaluation_method_pa) like ? ";
		
	     }

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id, String evaluation_method_pa) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			if( system_id != null && !system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (evaluation_method_pa != null && !evaluation_method_pa.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+evaluation_method_pa.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableEvaluation_method_paDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String evaluation_method_pa, String status,String role ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, evaluation_method_pa);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			  String sd = "";
				
				if(role.contains("NCISM")) {
					sd = " and e.system_id != 45 ";
				}
				if(role.contains("NCH")) {
					sd = " and e.system_id = 45 ";
				}
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && evaluation_method_pa.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by evaluation_method_pa ) as ser,e.id,e.evaluation_method_pa,e.status,e.system_id,s.system_name\n"
						+ "from edu_cc_tb_evaluation_method_pa_mstr e\n"
						+ "inner join edu_lms_system_mstr s on s.id= e.system_id\n"
						+ " where e.status='1' "+ sd   + SearchValue + "ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by evaluation_method_pa ) as ser,e.id,e.evaluation_method_pa,e.status,e.system_id,s.system_name\n"
					+ "from edu_cc_tb_evaluation_method_pa_mstr e\n"
					+ "inner join edu_lms_system_mstr s on s.id= e.system_id where e.status='"+ status +"'" + sd + SearchValue + "ORDER BY "+orderColunm+" " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id,evaluation_method_pa);
//			System.err.println("stmt--->"+stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDEvaluation_method_pa' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfsys"+countFunction+"' value='"+rs.getString("system_id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("evaluation_method_pa")+"'>"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";
				
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
	
	public EDU_CC_TB_EVALUATION_METHOD_PA_MSTR getEvaluation_method_paByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_CC_TB_EVALUATION_METHOD_PA_MSTR updateid = (EDU_CC_TB_EVALUATION_METHOD_PA_MSTR) session.get(EDU_CC_TB_EVALUATION_METHOD_PA_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateEvaluation_method_pa(EDU_CC_TB_EVALUATION_METHOD_PA_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_CC_TB_EVALUATION_METHOD_PA_MSTR set system_id=:system_id, evaluation_method_pa=:evaluation_method_pa,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					 .setParameter("evaluation_method_pa", obj.getEvaluation_method_pa()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
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