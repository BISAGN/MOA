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

import com.AyushEdu.Models.Time_Table.EDU_TT_EVENT_MSTR;


@Repository
public class Event_MSTR_DAOImpl implements Event_MSTR_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableEventDataTotalCount(String Search, String event_name, String event_date, int holiday, int institute_id ) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, event_name, event_date, holiday, institute_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by event_name ASC) as sr_no,id,event_name,TO_CHAR(event_date , 'dd/MON/yyyy') as event_date,holiday from edu_tt_event_mstr where id!=0 and institute_id = ? \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, event_name, event_date, holiday, institute_id);
			System.err.println("q_______" +q);
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
	public String GenerateQueryWhereClause_SQL(String Search, String event_name, String event_date, int holiday, int institute_id) {
		String SearchValue = "";
		
		
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(event_name) like ? or TO_CHAR(event_date ,'dd/MON/yyyy') like ? )";

		}
		
		if (event_name != null && !event_name.equals("")) {
			SearchValue += " and upper(event_name) like ? ";

		}
		if (event_date != null && !event_date.equals("") && !event_date.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(event_date , 'dd/mm/yyyy') = ? ";
	     }
		
		if (holiday != 0) {
			
			SearchValue += " and holiday = ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String event_name, String event_date,
			int holiday, int institute_id) {
		int flag = 0;
		try {
			flag += 1;
			stmt.setInt(flag,institute_id);
			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
			}

			if (event_name != null && !event_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + event_name.toUpperCase() + "%");
			}
			if (event_date != null && !event_date.equals("") && !event_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, event_date );
			}
			if (holiday != 0) {
				flag += 1;
				stmt.setInt(flag,holiday);
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableEventDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String event_name,String event_date, int holiday, int institute_id ) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, event_name, event_date, holiday, institute_id);
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

			if (Search.equals("") && event_name == null && event_date == null && holiday == 0) {
				q = "select ROW_NUMBER() OVER(order by event_name ASC) as sr_no,id,event_name,TO_CHAR(event_date , 'dd/MON/yyyy') as event_date,holiday from edu_tt_event_mstr where id!=0 and institute_id = ?" + SearchValue
						+ " ORDER BY event_name,event_date,holiday " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by event_name ASC) as sr_no,id,event_name,TO_CHAR(event_date , 'dd/MON/yyyy') as event_date,holiday from edu_tt_event_mstr where id!=0 and institute_id = ?" + SearchValue 
						+ " ORDER BY event_name,event_date,holiday " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, event_name, event_date, holiday, institute_id);
			System.err.println("STMT------acd---------"+stmt);
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

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDEvent' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
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

	public EDU_TT_EVENT_MSTR geteventByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_TT_EVENT_MSTR updateid = (EDU_TT_EVENT_MSTR) session.get(EDU_TT_EVENT_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	

	
}
