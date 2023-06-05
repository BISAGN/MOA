package com.AyushEdu.dao.TT_Lecture;

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

import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;


@Repository
public class Instructional_Method_MSTR_DAOImpl implements Instructional_Method_MSTRDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	public long DataTable_Instrucational_Method_DataTotalCount(String Search, String instructional_method_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, instructional_method_name);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,instructional_method_name from edu_lec_instruction_method_mstr where id!=0 \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, instructional_method_name);
			System.err.println("+++++++++"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String instructional_method_name) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " where (upper(instructional_method_name) like ? )";

		}

		
		if (Search.equals("")  && instructional_method_name != null && !instructional_method_name.equals("")) {
			if(!SearchValue.contains("where")) {
			SearchValue += " where (upper(instructional_method_name) like ? )";
			}
			else {
				SearchValue += " and (upper(instructional_method_name) like ? )";

			}
		}
		
		

//		if (Search.equals("") && refer_code != null && !refer_code.equals("")) {
//			if(!SearchValue.contains("where")) {
//			SearchValue += " where refer_code like ? ";
//			}else{
//				SearchValue +=  " and refer_code like ? ";
//
//			}
//		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String instructional_method_name) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (Search.equals("") && instructional_method_name != null && !instructional_method_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + instructional_method_name.toUpperCase() + "%");
			}

//			if (Search.equals("") &&  refer_code != null && !refer_code.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + refer_code.toString() + "%");
//			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTable_Instrucational_Method_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String instructional_method_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, instructional_method_name);
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

			if (Search.equals("") && instructional_method_name.equals("0")) {
				q = "select ROW_NUMBER() OVER(order by instructional_method_name ASC) as sr_no,id,instructional_method_name from edu_lec_instruction_method_mstr " + SearchValue
						+ " ORDER BY instructional_method_name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by instructional_method_name ASC) as sr_no,id,instructional_method_name from edu_lec_instruction_method_mstr " 
						+ SearchValue + " ORDER BY instructional_method_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, instructional_method_name);
			
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

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' "+ADD+" value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' \r\n"
						+ "				f = \"<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' "+ADD1+"  value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

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

	public EDU_LEC_INSTRUCTION_METHOD_MSTR getinstructionalByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LEC_INSTRUCTION_METHOD_MSTR updateid = (EDU_LEC_INSTRUCTION_METHOD_MSTR) session.get(EDU_LEC_INSTRUCTION_METHOD_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
}


