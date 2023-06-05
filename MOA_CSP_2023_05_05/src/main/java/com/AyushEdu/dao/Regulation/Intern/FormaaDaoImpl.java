package com.AyushEdu.dao.Regulation.Intern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;

@Repository
public class FormaaDaoImpl implements FormaaDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}




	public List<Map<String, Object>> DataTableFormaaDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String reg_state, String to_state,String  address ,String from_date,String to_date) {

		String SearchValue = GenerateQueryWhereCandiList(Search,   reg_state,  to_state,  address , from_date, to_date);
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

		 
//				q = "select ROW_NUMBER() OVER(order by reg_state ASC) as ser,id,reg_state,to_state ,from_date,to_date,address,status  from reg_nch_formaa where status= 0 " + SearchValue
//						+ " ORDER BY reg_state " + orderType + " limit " + pageL + " OFFSET " + startPage;
//		 
				q = " select ROW_NUMBER() OVER(order by a.reg_state ASC) as ser,a.id,s.state_name as reg_state,ss.state_name as to_state,  \r\n"
						+ "  to_char(a.from_date , 'DD/MM/YYYY') as from_date ,\r\n"
						+ "    to_char(a.to_date , 'DD/MM/YYYY') as to_date ,\r\n"
						+ " a.address,a.status  ,p.user_id ,p.valid_up_to , st_temp_no \r\n"
						+ "from reg_nch_formaa  a\r\n"
						+ "inner join   edu_lms_state_mstr s on s.state_id = a.reg_state::integer\r\n"
						+ "inner join   edu_lms_state_mstr ss on ss.state_id = a.to_state::integer \r\n"
						+ "inner join   reg_nch_form_a_p p on p.user_id = a.user_id\r\n"
						+ " where a.id != 0     " + SearchValue 
						+ " ORDER BY a.reg_state " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
				
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search, reg_state,  to_state,  address , from_date, to_date);

			ResultSet rs = stmt.executeQuery();
System.err.println("stmt===form aa=="+stmt);
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

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') )"
						+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDCountry' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='CounId" + countFunction
						+ "' value=" + rs.getInt("id") + "></i></a> </li>";

				String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete  ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteCoun' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='DCounId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction++;
				countFunctionDelete += 1;

				action = ul;
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

	public long DataTableFormaaDataTotalCount(String Search, HttpSession session,  String reg_state, String to_state,String  address ,String from_date,String to_date) {
		String role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, reg_state,  to_state,  address , from_date, to_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

// 		q = "select count(*) from (select id,name,status from reg_nch_formaa where status= '1'  "
// 					+ SearchValue + ") ab  ";

 		q=" select count(*)  "
 				+ "from ( select ROW_NUMBER() OVER(order by a.reg_state ASC) as ser,a.id,s.state_name as reg_state,ss.state_name as to_state,  \r\n"
				+ "to_char(a.from_date , 'DD/MM/YYYY') as from_date ,\r\n"
				+ "to_char(a.to_date , 'DD/MM/YYYY') as to_date ,\r\n"
				+ "a.address,a.status  ,p.user_id ,p.valid_up_to , st_temp_no \r\n"
				+ "from reg_nch_formaa  a\r\n"
				+ "inner join   edu_lms_state_mstr s on s.state_id = a.reg_state::integer\r\n"
				+ "inner join   edu_lms_state_mstr ss on ss.state_id = a.to_state::integer \r\n"
				+ "inner join   reg_nch_form_a_p p on p.user_id = a.user_id\r\n"
				+ "where a.id != 0    "
				+ "      "+SearchValue+") a  ";
			 
			
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, reg_state,  to_state,  address , from_date, to_date);
			ResultSet rs = stmt.executeQuery();

			System.err.println("count === "+stmt);
			
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
					e.printStackTrace();
				}
			}
		}
		return (long) total;

	}
	public String GenerateQueryWhereCandiList(String Search, String reg_state,  String to_state, String address , String from_date, String to_date) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(address) like ? )";

		}
		if (reg_state != null  && !reg_state.equals("")) {
			SearchValue += " and a.reg_state = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String reg_state, String to_state, String address ,String from_date,String to_date) {

		int flag = 0;
		try {

			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (reg_state != null && !reg_state.equals("0")  ) {
				flag += 1;
//				stmt.setString(flag, "%" + country.toUpperCase() + "%");
				stmt.setString(flag, reg_state);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}


}
