package com.AyushEdu.dao.Examination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import com.AyushEdu.Core_Directory.Exam_Serial_Master_CD_DAO;
import com.AyushEdu.Models.Examination.EXAM_TB_EXAM_SERIAL_MSTR;

@Repository
public class Exam_Serial_Mstr_DaoImpl implements Exam_Serial_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	Exam_Serial_Master_CD_DAO ES_dirdao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableExamSerialTotalCount(String Search, String exam_serial,String status) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, exam_serial);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			if(Search.equals("") && exam_serial.equals("0") && status=="1") {
			q="select count(*) from (select id,exam_serial,status  from edu_exam_tb_exam_serial_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select id,exam_serial,status  from edu_exam_tb_exam_serial_mstr where id!=0 and status='"+ status +"'  \n"
						+ SearchValue + ") ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, exam_serial);

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
	public String GenerateQueryWhereClause_SQL(String Search, String exam_serial) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  cast(exam_serial as character varying) like ?)";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if (!exam_serial.equals("")) {
			SearchValue += " and exam_serial =? ";
		}
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String exam_serial) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, Search+"%");
			
				
			}
			
			if (!exam_serial.equals("") && exam_serial != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(exam_serial));
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableExamSerialist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String exam_serial, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, exam_serial);
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

			if(Search.equals("") && exam_serial.equals("0") && status=="1") {
				q = "select id,exam_serial,status from edu_exam_tb_exam_serial_mstr where status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,exam_serial,status from edu_exam_tb_exam_serial_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, exam_serial);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDExam_Serial' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdPAP"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='approfPAP"+countFunction+"' value="+rs.getString("exam_serial")+">"
								+"<input type='hidden' id='apstatusPAP"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
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
	
	public EXAM_TB_EXAM_SERIAL_MSTR getExamSerialByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EXAM_TB_EXAM_SERIAL_MSTR updateid = (EXAM_TB_EXAM_SERIAL_MSTR) session.get(EXAM_TB_EXAM_SERIAL_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateExamSerial(EXAM_TB_EXAM_SERIAL_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EXAM_TB_EXAM_SERIAL_MSTR set exam_serial=:exam_serial,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("exam_serial", obj.getExam_serial()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
//			ES_dirdao.Update_Exam_Serial_Data(id,Integer.parseInt(obj.getExam_serial()),Integer.parseInt(status),username,new Date()); 
			ES_dirdao.Update_Exam_Serial_Data(obj.getId(),obj.getExam_serial(),obj.getStatus() ,obj.getModified_by(), obj.getModified_date());
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