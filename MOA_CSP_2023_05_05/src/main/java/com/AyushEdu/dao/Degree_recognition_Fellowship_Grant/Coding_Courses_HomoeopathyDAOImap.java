package com.AyushEdu.dao.Degree_recognition_Fellowship_Grant;

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

import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR;




@Repository
public class Coding_Courses_HomoeopathyDAOImap implements Coding_Courses_HomoeopathyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	
	public long DataTableCoding_Courses_Hom_Details_DataTotalCount(String Search, String sr_no, String qualification, String code) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, sr_no, qualification, code);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,sr_no, qualification, code  from dg_rec_coding_courses_homoeopathy_mstr where id!=0 \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, sr_no, qualification, code);

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
	public String GenerateQueryWhereClause_SQL(String Search, String sr_no, String qualification, String code) {
		String SearchValue = "";
		
		
	
		
		if (qualification != null && !qualification.equals("")) {
			SearchValue += " and upper(qualification) like ? ";

		}
		if (code != null && !code.equals("")) {
			SearchValue += " and upper(code) like ? ";

		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String sr_no, String qualification, String code) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}


			if (Search.equals("") &&  qualification != null && !qualification.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + qualification.toString() + "%");
			}

			if (Search.equals("") &&  code != null && !code.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + code.toString() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableCoding_Courses_Hom_Details_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String sr_no, String qualification, String code) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, sr_no, qualification, code);
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

			if (Search.equals("") && sr_no.equals("0") && qualification.equals("0") && code.equals("0")) {
				q = "select ROW_NUMBER() OVER(order by id ASC) as sr_no ,id,qualification,code from dg_rec_coding_courses_homoeopathy_mstr " + SearchValue
						+ " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by id ASC) as sr_no ,id,qualification,code from dg_rec_coding_courses_homoeopathy_mstr " + SearchValue
						+ " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, sr_no, qualification, code);
			
			ResultSet rs = stmt.executeQuery();
         System.err.println("-----eeee-----"+stmt);
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

	public DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR getcode_course_homByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR updateid = (DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR) session.get(DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}








}
