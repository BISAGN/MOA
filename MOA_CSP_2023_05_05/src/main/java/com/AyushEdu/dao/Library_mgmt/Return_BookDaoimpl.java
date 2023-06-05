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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_BOOK_REQ;

@Repository
public class Return_BookDaoimpl implements Return_BookDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public TB_MEMBER_BOOK_REQ getsystemByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_MEMBER_BOOK_REQ updateid = (TB_MEMBER_BOOK_REQ) session.get(TB_MEMBER_BOOK_REQ.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	@Override
	public List<Map<String, Object>> DataTableReturn_BookDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String member_id, String book_select, String book_charges,
			String book_req_date, String return_status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, member_id, book_select, book_charges, book_req_date,
				return_status);
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

			q = " select n.p_id as id,n.member_name_p as member_name,n.member_p_id,TO_CHAR(n.book_req_date,'DD/MON/YYYY') as book_req_date,e.system_name,STRING_AGG( book_name, ',' ) as book_name\r\n"
					+ "from (select r.id as p_id,r.member_id as member_p_id,unnest(string_to_array(book_select, ',')) as bid,m.member_name as member_name_p,return_status,member_dept,book_req_date from tb_member_book_req r\r\n"
					+ "inner join tb_member_dtl m on upper(m.member_id) like concat('%',upper(r.member_id::character varying),'%') \r\n"
					+ " )n \r\n"
					+ "inner join tb_book_dtl b on b.id::text=n.bid::text\r\n"
					+ "inner join edu_lms_system_mstr e on e.id = n.member_dept where e.status = '1' and n.return_status = 1  " + SearchValue + " \r\n" + "group by 1,2,3,4,5"
					+ "ORDER BY n.p_id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			
			

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, member_id, book_select, book_charges, book_req_date,
					return_status);

			System.err.println("-------aaaaa-----------" + stmt);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				// end

				String f = "";
				String action = "";
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn deactive-btn btn-hover btn-sm returnbook' value='ADD' title='update Data' > Return"
						+ "<input type='hidden' id='returnID" + countFunction + "' value=" + rs.getString("id")
						+ "></a> </li>";

				ul += f;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
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
		System.out.println("list-------" + list);
		return list;
	}

	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String member_id,
			String book_select, String book_charges, String book_req_date, String return_status) {
		int flag = 0;
		try {

			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				/*
				 * stmt.setString(flag,"%"+search.toUpperCase()+"%"); flag += 1;
				 * stmt.setString(flag,"%"+search.toUpperCase()+"%"); flag += 1;
				 * stmt.setString(flag,"%"+search.toUpperCase()+"%");
				 */

			}

			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
			}

			if (member_id != null && !member_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + member_id + "%");
			}
			/*
			 * if (registration_no != null && !registration_no.equals("")) { flag += 1;
			 * stmt.setString(flag, "%" + hostel_id + "%"); } if (room_no != null &&
			 * !room_no.equals("")) { flag += 1; stmt.setString(flag, "%" + hostel_id +
			 * "%"); }
			 */

			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;

	}

	private String GenerateQueryWhereClause_SQL(String search, String member_id, String book_select,
			String book_charges, String book_req_date, String return_status) {
		String SearchValue = "";
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue += " and Upper(n.member_name_p::character varying) like ? OR Upper(b.book_name)::character varying like ? OR "
					+ " b.book_price::character varying like ? OR TO_CHAR(n.book_req_date,'DD/MM/YYYY') like ? ";

			// System.err.println("globalllll search"+SearchValue);
		}

		/// advance search

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
	public long DataTableReturn_BookDataTotalCount(String Search, String member_id, String book_select,
			String book_charges, String book_req_date, String return_status) {
		System.err.println("===================" + Search);
		String SearchValue = GenerateQueryWhereClause_SQL(Search, member_id, book_select, book_charges, book_req_date,
				return_status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q=" (select n.p_id as id,n.member_pid,TO_CHAR(n.book_req_date,'DD/MON/YYYY') as book_req_date,e.system_name,STRING_AGG( book_name, ',' ) as book_name \r\n"
//					+"from (select *,r.id as p_id,r.member_id as member_pid,unnest(string_to_array(book_select, ',')) as bid from tb_member_book_req r \r\n"
//					+"inner join tb_member_dtl m on m.member_id = r.member_id::character varying  )n \r\n"
//					+"inner join tb_book_dtl b on b.id::text=n.bid::text \r\n"
//					+"inner join edu_lms_system_mstr e on e.id = n.member_dept \r\n"
//					+"where e.status = '1' and n.return_status = 1 \r\n"
//					+"group by 1,2,3,4" + SearchValue + ")";

			q = "select count(*) from (select n.p_id as id,n.member_name_p as member_name,n.member_p_id,TO_CHAR(n.book_req_date,'DD/MON/YYYY') as book_req_date,e.system_name,STRING_AGG( book_name, ',' ) as book_name\r\n"
					+ "from (select r.id as p_id,r.member_id as member_p_id,unnest(string_to_array(book_select, ',')) as bid,m.member_name as member_name_p,return_status,member_dept,book_req_date from tb_member_book_req r\r\n"
					+ "inner join tb_member_dtl m on upper(m.member_id) like concat('%',upper(r.member_id::character varying),'%') \r\n"
					+ " )n \r\n"
					+ "inner join tb_book_dtl b on b.id::text=n.bid::text\r\n"
					+ "inner join edu_lms_system_mstr e on e.id = n.member_dept where e.status = '1' and n.return_status = 1 " + SearchValue + "  \r\n"
					+ "group by 1,2,3,4,5ORDER BY n.p_id ) a ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, member_id, book_select, book_charges, book_req_date,
					return_status);
			System.err.println("q----------" + stmt);

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

}
