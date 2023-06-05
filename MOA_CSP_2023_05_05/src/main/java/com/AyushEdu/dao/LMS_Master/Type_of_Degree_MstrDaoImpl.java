package com.AyushEdu.dao.LMS_Master;

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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;

@Repository
public class Type_of_Degree_MstrDaoImpl implements Type_of_Degree_MstrDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTabletype_of_degreeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_degree, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_degree);
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

			if (Search.equals("") && type_of_degree.equals("") && status == "1") {

				q = "select ROW_NUMBER() OVER(order by type_of_degree ASC) as ser,id, type_of_degree,status from edu_lms_type_of_degree_mstr\n" + " where status= '1' \n"
						+ " ORDER BY type_of_degree " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by type_of_degree ASC) as ser,id, type_of_degree,status from edu_lms_type_of_degree_mstr\n" + " where status= '" + status
						+ "'" + SearchValue + " ORDER BY type_of_degree " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_degree);

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
						+ rs.getString("id") + "','" + rs.getString("type_of_degree") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDTyDeg' title='Edit Data'></i>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='TyDegId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='TyDeg" + countFunction
						+ "' value=" + rs.getString("type_of_degree") + ">" + "<input type='hidden' id='TyDegStatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteTyDeg' title='Delete Data'></i>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DETyDegId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
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

	@Override
	public long DataTabletype_of_degreeDataTotalCount(String Search, String type_of_degree, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_degree);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id, type_of_degree,status from edu_lms_type_of_degree_mstr\n"
					+ " where status='1' " + SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_degree);

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
	public String GenerateQueryWhereClause_SQL(String Search, String type_of_degree) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(type_of_degree) like ?)";
			System.err.println("globalllll search" + SearchValue);

		}

		if (!type_of_degree.trim().equals("")) {
			SearchValue += " and type_of_degree like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String type_of_degree) {

		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!type_of_degree.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + type_of_degree + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@Override
	public EDU_LMS_TYPE_OF_DEGREE_MASTER getType_of_DegreeByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_TYPE_OF_DEGREE_MASTER updateid = (EDU_LMS_TYPE_OF_DEGREE_MASTER) session
				.get(EDU_LMS_TYPE_OF_DEGREE_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getDataListofdegSysToD(String system) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			
			String qs = "";
			
			if(system.equals("NCH")) {
				qs = " and sm.system_name = 'HOMOEOPATHY' ";
			}
			if(system.equals("NCISM")) {
				qs = " and sm.system_name != 'HOMOEOPATHY' ";
			}
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select sm.system_name,tdm.type_of_degree,dm.degree_name \n"
					+ "from edu_lms_degree_mstr dm\n"
					+ "inner join edu_lms_system_degree_map_mstr sd on sd.degree_name = dm.id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id = dm.type_of_degree\n"
					+ "inner join edu_lms_system_mstr sm on sm.id = sd.system_name\n"
					+ "where dm.status::int=1 and sd.status::int=1 "+ qs + " order by system_name " ;
			
			stmt = conn.prepareStatement(q);
			System.err.println("getDataListofdegSysToD==="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("system_name"));// 0
				list.add(rs.getString("type_of_degree"));//1
				list.add(rs.getString("degree_name"));//2
				
				alist.add(list);
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
		return alist;
	}
	@Override
	public ArrayList<ArrayList<String>> getMarqueData(String system) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		 
		try {
			
			
			
			
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="\n"
					+ "select * from (select unnest(string_to_array(marque_for, ',')) as portal,\n"
					+ "			   case when highlight=1 then '<span class=\"latest-update\">Latest Update</span><a href=\"#\">'||msg||'</a>'\n"
					+ "else '<a href=\"#\">'||msg||'</a>' end as msg  from tb_marque where status=1 and current_timestamp between from_date and to_date) m\n"
					+ "where portal=?" ;
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, system);
			System.out.println("stmt "+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				
				list.add(rs.getString("msg"));//1
				
				
				alist.add(list);
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
		return alist;
	}

}
