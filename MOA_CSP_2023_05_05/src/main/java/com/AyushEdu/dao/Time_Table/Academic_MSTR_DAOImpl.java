package com.AyushEdu.dao.Time_Table;

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

import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS_MSTR;

@Repository
public class Academic_MSTR_DAOImpl implements Academic_MSTR_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	public long DataTableAcademic_Details_DataTotalCount(String Search, String academic_details_name, String refer_code) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, academic_details_name, refer_code);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by academic_details_name ASC) as sr_no,id,academic_details_name,refer_code from edu_tt_academic_details_mstr \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, academic_details_name, refer_code);

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
	public String GenerateQueryWhereClause_SQL(String Search, String academic_details_name, String refer_code) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " where (upper(academic_details_name) like ?) or (upper(refer_code) like ?)";

		}

		
		if (Search.equals("")  && academic_details_name != null && !academic_details_name.equals("")) {
			if(!SearchValue.contains("where")) {
			SearchValue += " where (upper(academic_details_name) like ? )";
			}
			else {
				SearchValue += " and (upper(academic_details_name) like ? )";

			}
		}
		
		

		if (Search.equals("") && refer_code != null && !refer_code.equals("")) {
			if(!SearchValue.contains("where")) {
			SearchValue += " where refer_code like ? ";
			}else{
				SearchValue +=  " and refer_code like ? ";

			}
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String academic_details_name,
			String refer_code) {
		int flag = 0;
		try {
//			flag += 1;
//			stmt.setInt(flag,institute_id);
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (Search.equals("") && academic_details_name != null && !academic_details_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + academic_details_name.toUpperCase() + "%");
			}

			if (Search.equals("") &&  refer_code != null && !refer_code.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + refer_code.toString() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableAcademic_Details_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic_details_name, String refer_code) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, academic_details_name, refer_code);
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

			if (Search.equals("") && academic_details_name.equals("0") && refer_code.equals("0")) {
				q = "select ROW_NUMBER() OVER(order by academic_details_name ASC) as sr_no,id,academic_details_name,refer_code from edu_tt_academic_details_mstr  " 
						+ SearchValue + " ORDER BY " +orderColunm+ " " + orderType + " limit " + pageL + " OFFSET " 
						+ startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by academic_details_name ASC) as sr_no,id,academic_details_name,refer_code from edu_tt_academic_details_mstr  " 
						+ SearchValue + " ORDER BY " +orderColunm+ " " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, academic_details_name, refer_code);
			
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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
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

	public EDU_TT_ACADEMIC_DETAILS_MSTR getacademicByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_TT_ACADEMIC_DETAILS_MSTR updateid = (EDU_TT_ACADEMIC_DETAILS_MSTR) session.get(EDU_TT_ACADEMIC_DETAILS_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
}
