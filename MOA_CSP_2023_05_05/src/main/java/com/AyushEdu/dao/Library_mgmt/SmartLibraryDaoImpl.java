package com.AyushEdu.dao.Library_mgmt;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SmartLibraryDaoImpl implements SmartLibraryDao{
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<Map<String, Object>> getBorrowList(String status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
			if(status != null && !status.equals("") && !status.equals("0")) {
			
			q=" select m.member_name,b.book_name,to_char(s.date_of_booked,'DD/MON/YYYY') as date_of_booked,to_char(s.date_of_return,'DD/MON/YYYY') as date_of_return from tb_smart_book_system s \n"
					+ "inner join tb_member_dtl m on m.id = s.member_id\n"
					+ "inner join tb_book_dtl b on b.id = s.book_id";	
			}else {
				q=" select m.member_name,b.book_name,to_char(s.date_of_booked,'DD/MON/YYYY') as date_of_booked,to_char(s.date_of_return,'DD/MON/YYYY') as date_of_return from tb_smart_book_system s \n"
						+ "inner join tb_member_dtl m on m.id = s.member_id\n"
						+ "inner join tb_book_dtl b on b.id = s.book_id where s.status = 0";	
			}
			
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
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

	@Override
	public List<Map<String, Object>> DataTableall_booksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered, int book_dept) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, book_number,book_name,book_author,
				book_price,total_book_qty,date_of_entered,book_dept);
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

//			if(Search.equals("") ) {
//				q = "select id,book_number,book_name, book_author,book_price,total_book_qty,TO_CHAR(date_of_entered , 'dd/MON/yyyy') as date_of_entered from tb_book_dtl "
//						+  SearchValue + " ORDER BY id " + orderType + " limit "
//						+ pageL + " OFFSET " + startPage;
//			
//			q="select t.id,book_number,book_name, book_author,book_price,total_book_qty,TO_CHAR(date_of_entered , 'dd/MON/yyyy') as date_of_entered , ed.system_name as book_dept from tb_book_dtl t\r\n"
//					+ "inner join edu_lms_system_mstr ed on ed.id = t.book_dept"+ SearchValue +" order by id " + orderType
//					+ " limit " + pageL + " OFFSET " + startPage;
			
			q="select t.id,t.book_number,t.book_name, t.book_author,t.book_price,t.total_book_qty,TO_CHAR(t.date_of_entered , 'dd/MON/yyyy') as date_of_entered , ed.system_name as book_dept from tb_book_dtl t\n"
					+"inner join edu_lms_system_mstr ed on ed.id = t.book_dept where t.id != 0 "+ SearchValue +" order by t.id " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;
			
			

			System.err.println("///////////////////"+q);
//			}else {
//			q = "select id,book_number,book_name, book_author,book_price,total_book_qty,TO_CHAR(date_of_entered , 'dd/MON/yyyy') as date_of_entered from tb_book_dtl "
//					+ SearchValue + " ORDER BY id " + orderType + " limit "
//					+ pageL + " OFFSET " + startPage;
//			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, book_number,book_name,book_author,
					book_price,total_book_qty,date_of_entered,book_dept);
			System.err.println("---------DataTableTopicMstrDataList--------------------"+stmt);
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
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDBooks' value='ADD' title='Edit Data' >"
						+ "<input type='hidden' id='apIdAGE" + countFunction + "' value=" + rs.getString("id")
						+ "><input type='hidden' id='apbook" + countFunction + "' value=" + rs.getString("book_number")
						+ "><input type='hidden' id='apbook_n" + countFunction + "' value=" + rs.getString("book_name")
						+ "><input type='hidden' id='apbook_a" + countFunction + "' value=" + rs.getString("book_author")
						+ "><input type='hidden' id='apbook_p" + countFunction + "' value=" + rs.getString("book_price")
						+ "><input type='hidden' id='apbook_q" + countFunction + "' value=" + rs.getString("total_book_qty")
						+ "><input type='hidden' id='apbook_date" + countFunction + "' value=" + rs.getString("book_dept")
						+ "><i class='lni lni-pencil-alt'></i></a> </li>";
				
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
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
	@Override
	public long DataTableall_booksDataTotalCount(String Search,String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered,int book_dept) {
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search,book_number,book_name,book_author,
				book_price,total_book_qty,date_of_entered,book_dept);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			
			q = "select count(*) from (select t.id,t.book_number,t.book_name, t.book_author,t.book_price,t.total_book_qty,TO_CHAR(t.date_of_entered , 'dd/MON/yyyy') as date_of_entered , ed.system_name as book_dept from tb_book_dtl t\n"
					+ "inner join edu_lms_system_mstr ed on ed.id = t.book_dept where t.id!=0 "
					+ SearchValue+ " ) as a ";
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, book_number,book_name,book_author,
					book_price,total_book_qty,date_of_entered,book_dept);
			System.err.println("---------DataTableall_booksDataTotalCount-------123-------------"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered,int book_dept) {
		String SearchValue = "";
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and (  upper(t.book_number) like ? ) OR (  upper(t.book_name) like ? ) OR (  upper(t.book_author) like ? ) OR"
					+ " t.book_price::character varying like ? OR  t.total_book_qty::character varying like ?  OR (  upper(TO_CHAR(t.date_of_entered,'DD/MM/YYYY')) like ? ) OR"
					+ " (  upper(ed.system_name) like ? ) ";

		}
		
//		if (Search!=null && !Search.equals("")) { // for Input Filter
//			SearchValue += "select * from tb_book_dtl where ( upper ( book_number) like ? or upper(book_name) like ? "
//					+ " or upper(book_author) like ? or cast(book_price as character varying) like ? "
//					+ " or  cast(total_book_qty as character varying) like ? or  upper(TO_CHAR(date_of_entered,'DD/MON/YYYY')) like ? or book_dept like ? )";
//		}
//		
		
		
//		///advance search
		
		if( book_number != null && !book_number.equals("")) {
			SearchValue += " and upper(t.book_number) like ? ";
	     }
		if( book_name != null && !book_name.equals("")) {
			SearchValue += " and upper(t.book_name) like ? ";
	     }
		if( book_author != null && !book_author.equals("")) {
			SearchValue += " and upper(t.book_author) like ? ";
	     }
		if (!book_price.equals("")) {
			SearchValue += " and book_price =? ";
		}
		if (!total_book_qty.equals("")) {
			SearchValue += " and total_book_qty =? ";
		}
		
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String book_number,String book_name, String book_author,
			String book_price,String total_book_qty,String date_of_entered, int book_dept) {
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
				stmt.setString(flag,  "%" +Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag,  "%" +Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase()+ "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase()+ "%");
			}
			if (book_number != null && !book_number.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+book_number.toUpperCase()+"%");
			}
			if (book_name != null && !book_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+book_name.toUpperCase()+"%");
			}
			if (book_author != null && !book_author.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+book_author.toUpperCase()+"%");
			}
			if (!book_price.equals("") && book_price != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(book_price));
			}
			if (!total_book_qty.equals("") && total_book_qty != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(total_book_qty));
			}
			
		} catch (Exception e) {
		}
		return stmt;
	}
	

	
//	@Override
//	public String updatetopic(tb_book_dtl obj) {
//		// TODO Auto-generated method stub
//		Session session1 = this.sessionFactory.openSession();
//		Transaction tx = session1.beginTransaction();
//		
//		 String msg = "";
//		try{
//			String hql = "update tb_book_dtl set course_id=:course_id, topic=:topic,status=:status,modified_by=:modified_by,modified_date=:modified_date"
//					+ " where id=:id";
//
//			Query query = session1.createQuery(hql).setParameter("course_id", obj.getCourse_id())
//					.setParameter("topic", obj.getTopic()).setParameter("status", (obj. getStatus()))
//					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
//					.setParameter("id", obj.getId());
//			
//			msg = query.executeUpdate() > 0 ? "1" : "0";
//			tx.commit();
//		}
//		catch (Exception e) {
//			msg = "Data Not Updated";
//			tx.rollback();
//		}
//		finally {
//			session1.close();
//		}
//		return msg;
//	}
}