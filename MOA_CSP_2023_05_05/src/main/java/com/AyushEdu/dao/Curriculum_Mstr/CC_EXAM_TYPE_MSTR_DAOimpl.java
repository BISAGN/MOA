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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;

@Repository

public class CC_EXAM_TYPE_MSTR_DAOimpl implements CC_EXAM_TYPE_MSTR_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public long DataTableExamtypeDataTotalCount1(String Search, String exam_type,String status) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, exam_type);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(Search.equals("") && exam_type.equals("0") && status=="1") {
			q="select count(*) from (select id,exam_type,status  from edu_cc_tb_exam_type_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}else {
			q="select count(*) from (select id,exam_type,status  from edu_cc_tb_exam_type_mstr where id!=0 and status='"+ status +"'  \n"
						+ SearchValue + ") ab  ";
			}
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, exam_type);

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
	public String GenerateQueryWhereClause_SQL(String Search, String exam_type) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(exam_type) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( exam_type != null && !exam_type.equals("")) {
			SearchValue += " and upper(exam_type) like ? ";
		
	     }
	
	   if(!SearchValue.contains("and") && !exam_type.equals("")){
			SearchValue += " and upper(exam_type) like ? ";
			System.err.println("parameter search"+SearchValue);
	    }

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String exam_type) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if (exam_type != null && !exam_type.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+exam_type.toUpperCase()+"%");
			}
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	public List<Map<String,Object>> DataTableExamtypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String exam_type, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, exam_type);
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

			if(Search.equals("") && exam_type.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by exam_type ) as ser, id,exam_type,status from edu_cc_tb_exam_type_mstr where status='1'"  + SearchValue +  "ORDER BY "+orderColunm+" "  + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by exam_type ) as ser, id,exam_type,status from edu_cc_tb_exam_type_mstr where status='"+ status +"'"  + SearchValue +  "ORDER BY "+orderColunm+" "  + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, exam_type);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDexam_type' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("exam_type")+"'>"
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
	public CC_TB_EXAM_TYPE_MSTR getExamtypeByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 CC_TB_EXAM_TYPE_MSTR updateid = (CC_TB_EXAM_TYPE_MSTR) session.get(CC_TB_EXAM_TYPE_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }

	@Override
	public String updateExamtype(CC_TB_EXAM_TYPE_MSTR td) {
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_EXAM_TYPE_MSTR set exam_type=:exam_type,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("exam_type", td.getExam_type()).setParameter("status", (td. getStatus()))
					.setParameter("modified_by", td.getModified_by()).setParameter("modified_date", td.getModified_date())
					.setParameter("id", td.getId());
			
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



