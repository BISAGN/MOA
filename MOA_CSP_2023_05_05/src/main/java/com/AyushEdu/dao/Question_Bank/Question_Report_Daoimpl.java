package com.AyushEdu.dao.Question_Bank;
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
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_PARENT;
import com.AyushEdu.dao.Question_Bank.Question_Report_Dao;

@Service
@Repository
public class Question_Report_Daoimpl implements Question_Report_Dao {
	
		@Autowired
		private SessionFactory sessionFactory;
		@Autowired
		private DataSource dataSource;


		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}
	@Override
	public List<Map<String, Object>> DataTableQuestion_ReportDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String course_id, String set_id, String module_id) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, orderType,course_id,set_id,module_id);
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
			
			if(search.equals("") && course_id != null && course_id.equals("0") ) {
				q = " select p.id,question,correct_ans,String_agg(Distinct c.answer,',') as answer,level_id,marks,time,exam_name from edu_lms_question_band_parent p\r\n"
						+ "inner join edu_lms_question_band_child c ON c.p_id = p.id\r\n"
						+ ""
						+ ""
						+ "where p.id != 0 " + SearchValue 
						+ "group by p.id,p.question,c.correct_ans\r\n"+" ORDER BY course_id " + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = " select p.id,question,correct_ans,String_agg(Distinct c.answer,',') as answer,level_id,marks,time,exam_name from edu_lms_question_band_parent p\r\n"
					+ "inner join edu_lms_question_band_child c ON c.p_id = p.id\r\n"
					
					+ ""
					+ "where p.id != 0 "+ SearchValue  
					+ "group by p.id,p.question,c.correct_ans\r\n"
					+ "ORDER BY course_id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, orderType,course_id,set_id,module_id);
			
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
			
//				String f = "";
//				String action = "";
//				String f1 = "";
//				
//				
//				String ul="";
//				ul+="<ul class='buttons-group mainbtn action daobtn'>";
//				
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){ApproveData('"+ rs.getString("id") +"') }else{ return false;}\"";
//				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDStudent_Room_Request' value='ADD' title='Approve Data' >"+ //id='id_add_attHospital1'
//								"<i class='lni lni-checkmark'>"
//								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
//				
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){RejectData('"+ rs.getString("id") + "') }else{ return false;}\"";
//				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
//						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-close'></i></i></a> </li>";
//
//				
//				ul+=f + " " + f1 ;
//				ul+="</ul>";
//				
//				action = ul;
//				countFunction+=1;
//				countFunctionDelete+=1;
//				
//				
//				if(status != null && status.equals("0"))
//				columns.put("action", action);
//				else
//				columns.put("action", "");


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

	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String orderType, String course_id, String set_id, String module_id) {
		int flag = 0;
		try {
			
				System.err.println("cid-"+course_id+"\n sid-"+set_id+"\n mid-"+module_id);
				
				if (search != null && !search.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag,"%"+search.toUpperCase()+"%");

				}
				
				if (search.equals("") && course_id != null && !course_id.equals("0")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(course_id));
				}
			
				if (search.equals("") && set_id != null && !set_id.equals("0")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(set_id));
				}
				
				if (search.equals("") && module_id != null && !module_id.equals("0")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(module_id));
				}

		} catch (Exception e) {
		}

		return stmt;
	}
	private String GenerateQueryWhereClause_SQL(String search, String orderType, String course_id, String set_id, String module_id) {
		String SearchValue = "";
		if (search!=null && !search.equals("")) { // for Input Filter
			
			
			
			SearchValue += " and upper(question) = ? or upper(answer) like ? or upper(correct_ans) like ? or level_id::character varying like ? or upper(marks) like ?  or upper(time) like ? or upper(exam_name) like ?   ";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
	
		if(search.equals("") && course_id != null && !course_id.equals("0")){
			  
			SearchValue += "  and course_id = ? \r\n";
			  System.err.println("parameter search"+SearchValue); }
		
		
		if(search.equals("") && set_id != null && !set_id.equals("0")){
			  
			SearchValue += "  and set_id = ? \r\n";
			  System.err.println("parameter search"+SearchValue); }
		
		if(search.equals("") && module_id != null && !module_id.equals("0")){
			  
			SearchValue += "  and module_id = ? \r\n";
			  System.err.println("parameter search"+SearchValue); }
		/*
		 * if(!SearchValue.contains("and") && hostel_id != null &&
		 * !hostel_id.equals("0")){
		 * 
		 * SearchValue += " and hostel_id like  ";
		 * System.err.println("parameter search"+SearchValue); }
		 */

		return SearchValue;
	}
	@Override
	public long DataTableQuestion_ReportDataTotalCount(String search, String orderType, String course_id, String set_id, String module_id) {
String SearchValue = GenerateQueryWhereClause_SQL(search, orderType,course_id,set_id,module_id);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			
		
			
			q="select count(*) from ( \r\n"
					+ "select p.id,question,correct_ans,String_agg(Distinct c.answer,',') as Answer,level_id,marks,time,exam_name from edu_lms_question_band_parent p\r\n"
					+ "inner join edu_lms_question_band_child c ON c.p_id = p.id\r\n"
					+ "\r\n"
					+ "where p.id != 0 "+ SearchValue +" group by p.id,p.question,c.correct_ans) ab  ";

			
			System.err.println("q----------" + q);
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt,search,course_id,set_id,module_id, orderType);

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
	@Override
	public EDU_LMS_QUESTION_BAND_PARENT getsystemByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_QUESTION_BAND_PARENT updateid = (EDU_LMS_QUESTION_BAND_PARENT) session.get(EDU_LMS_QUESTION_BAND_PARENT.class, id);
     session.getTransaction().commit();
     session.close();                
    return updateid;
	}

}
