package com.AyushEdu.dao.LMS_Institute;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;

@Repository
public class Institute_Registration_DaoImpl implements Institute_Registration_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableinstitute_regDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_name, String country_id, String state_id,
			String district_id, String app_status,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_name, country_id, state_id, district_id,
				app_status);
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
//
//			q = " select ir.id, ir.institute_name,c.name,ir.country_id, s.state_name, d.district_name,ir.code,ir.address,ir.app_status,ir.institute_email  From edu_lms_institute_reg ir\n"
//					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
//					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
//					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer) where ir.id!=0 "
//					+ SearchValue + " order by id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			
			if(role.toUpperCase().contains("NCISM")) {
			q = " select ROW_NUMBER() OVER(order by ir.id ) as ser,ir.id, ir.institute_name,c.name,ir.country_id, s.state_name, d.district_name,ir.code,ir.address,ir.app_status,ir.institute_email  From edu_lms_institute_reg ir\n"
					+"inner join edu_lms_system_mstr sm on sm.id=ir.system_id\n"
					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer) where ir.id!=0  and sm.commission_id=1"
					+ SearchValue + " order by ir.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}else {
				q = " select ROW_NUMBER() OVER(order by ir.id ) as ser,ir.id, ir.institute_name,c.name,ir.country_id, s.state_name, d.district_name,ir.code,ir.address,ir.app_status,ir.institute_email  From edu_lms_institute_reg ir\n"
						+"inner join edu_lms_system_mstr sm on sm.id=ir.system_id\n"
						+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
						+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
						+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer) where ir.id!=0  and sm.commission_id=2"
						+ SearchValue + " order by ir.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, institute_name, country_id, state_id, district_id, app_status);

			ResultSet rs = stmt.executeQuery();
			
			System.err.println("-----stmt institute- - "+stmt);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunctionappr=1;
			int countFunctionReject=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String action1 = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				
//				String ul="";
//				ul+="<ul class='buttons-group mainbtn action daobtn'>";
//				
//				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDTeaching_learning_method' value='ADD' title='Edit Data' >" 
//								+"<i class='lni lni-pencil-alt'>"
//								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
//								+"<input type='hidden' id='approfAGE"+countFunction+"' value="+rs.getString("teaching_learning_method")+">"
//								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
//				
//				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
//						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
//
//	
//				ul+=f + " " + f1 ;
//				ul+="</ul>";
				
				
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

//				String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
//						+ rs.getString("id") + "','" + rs.getString("institute_email") + "') }else{ return false;}\"";
				f2 = "<li><a class='main-btn success-btn btn-hover btn-sm approve_inst' value='APPROVE' title='Approve Data'><i class='lni lni-checkmark'>"
						+"<input type='hidden' id='appID"+countFunctionappr+"' value="+rs.getString("id")+">"
						+"<input type='hidden' id='apIdAGE"+countFunctionappr+"' value="+rs.getString("institute_email")+">"
								+"<input type='hidden' id='apIdCODE"+countFunctionappr+"' value="+rs.getString("code")+">"
						+ "</i></a></li>";

//				String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
				f3 = "<li><a class='main-btn danger-btn btn-hover btn-sm reject_inst' value='REJECT' title='Reject Data'><i class='lni lni-close'>"
						+"<input type='hidden' id='rejID"+countFunctionReject+"' value="+rs.getString("id")+">"
						+ "</i></a></li>";

				ul += f2 + " " + f3;
				ul += "</ul>";

				action = ul;
				countFunctionappr+=1;
				countFunctionReject+=1;
				
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("institute_name"));// 1
				alist.add(rs.getString("country_id"));// 2
				alist.add(rs.getString("state_name"));// 3
				alist.add(rs.getString("district_name"));// 4
				alist.add(rs.getString("code"));// 5
				alist.add(rs.getString("address"));// 6

				String app_status1 = rs.getString("app_status");

				if (app_status1.equals("0")) {

					columns.put("action", action);
				}

				if (app_status1.equals("1")) {
					columns.put("action", action1);
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
	public long DataTableinstitute_regDataTotalCount(String Search, String institute_name, String country_id,
			String state_id, String district_id, String app_status,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_name, country_id, state_id, district_id,
				app_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(role.toUpperCase().contains("NCISM")) {
			q = "select count(*) \n"
					+ " from (select ROW_NUMBER() OVER(order by ir.id ) as ser,ir.id, ir.institute_name,c.name,ir.country_id, \n"
					+ "s.state_name, d.district_name,ir.code,ir.address,ir.app_status,ir.institute_email  From edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=ir.system_id\n"
					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer) where ir.id!=0  and ir.app_status :: int = 0 and sm.commission_id=1  "
					+ SearchValue + " ) a";
			}else {
				q = "select count(*) \n"
						+ " from (select ROW_NUMBER() OVER(order by ir.id ) as ser,ir.id, ir.institute_name,c.name,ir.country_id, \n"
						+ "s.state_name, d.district_name,ir.code,ir.address,ir.app_status,ir.institute_email  From edu_lms_institute_reg ir\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=ir.system_id\n"
						+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
						+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
						+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer) where ir.id!=0  and ir.app_status :: int = 0 and sm.commission_id=2  "
						+ SearchValue + " ) a";
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, institute_name, country_id, state_id, district_id, app_status);

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
	public String GenerateQueryWhereClause_SQL(String Search, String institute_name, String country_id, String state_id,
			String district_id, String app_status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(ir.institute_name) like ? or upper(c.name) like ?"
					+ "or upper(s.state_name) like ? or upper(d.district_name) like ?"
					+ "or upper(ir.code) like ? or upper(ir.address) like ?)";
		}

		if (!institute_name.trim().equals("")) {
			SearchValue += " and upper(ir.institute_name) like ? ";
		}
		if (!country_id.trim().equals("0")) {
			SearchValue += " and ir.country_id :: int = ? ";
		}
		if (!state_id.trim().equals("0")) {
			SearchValue += " and ir.state_id :: int = ? ";
		}
		if (!district_id.trim().equals("0")) {
			SearchValue += " and ir.district_id :: int = ? ";
		}

		if (app_status.equals("0")) {
			SearchValue += " and ir.app_status :: int = ? ";
		}
		if (app_status.equals("1")) {
			SearchValue += " and ir.app_status :: int = ? ";
		}
		if (app_status.equals("2")) {
			SearchValue += " and ir.app_status :: int = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String institute_name,
			String country_id, String state_id, String district_id, String app_status) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!institute_name.equals("") && institute_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + institute_name.toUpperCase() + "%");
			}
			if (!country_id.equals("0") && country_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(country_id));
			}
			if (!state_id.equals("0") && state_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(state_id));
			}
			if (!district_id.equals("0") && district_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(district_id));
			}

			if (app_status.equals("0")) {
				flag += 1;
				stmt.setInt(flag, 0);
			}
			if (app_status.equals("1")) {
				flag += 1;
				stmt.setInt(flag, 1);
			}
			if (app_status.equals("2")) {
				flag += 1;
				stmt.setInt(flag, 2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@SuppressWarnings("deprecation")
	public @ResponseBody List<TB_STATE> getcountrylistUrl(Integer selval) {

		System.err.println("selval" + selval);

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL
					.createQuery("from TB_STATE where status='1' and country_id=:country_id order by state_name");
			q1.setInteger("country_id", selval);
			@SuppressWarnings("unchecked")
			List<TB_STATE> list = (List<TB_STATE>) q1.list();
			tx.commit();

			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getStatelistUrl(Integer selval) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"from EDU_LMS_DISTRICT_MSTR where status='1' and state_id=:state_id order by district_name");
			q1.setInteger("state_id", selval);
			@SuppressWarnings("unchecked")
			List<EDU_LMS_DISTRICT_MSTR> list = (List<EDU_LMS_DISTRICT_MSTR>) q1.list();
			tx.commit();

			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversitylistUrl(Integer selval) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
				
			Query q1 = sessionHQL.createQuery(
					"from EDU_LMS_UNIVERSITY_MSTR where status='1' and system_id=:system_id order by id");
			q1.setInteger("system_id", selval);
			@SuppressWarnings("unchecked")
			List<EDU_LMS_UNIVERSITY_MSTR> list = (List<EDU_LMS_UNIVERSITY_MSTR>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	public ArrayList<ArrayList<String>> getCountrylistbyUniversity(int selval) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select cm.id,cm.name from edu_lms_country_mstr cm \n"
					+ "inner join edu_lms_university_mstr um on cm.id=um.country_id \n"
					+ "where um.id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, selval);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("name"));// 1

				list.add(alist);
				System.err.println("---list data "+list);
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
		return list;
	}
	

	public ArrayList<ArrayList<String>> getinstitute_system(int id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select id,college_unique_id,state_id,institute_name,university_id,institute_email,institute_mob_no  from edu_lms_institute_reg where id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("college_unique_id"));// 1
				alist.add(rs.getString("state_id"));// 2
				alist.add(rs.getString("institute_name"));// 3
				alist.add(rs.getString("university_id"));// 4
				alist.add(rs.getString("institute_email"));// 5
				alist.add(rs.getString("institute_mob_no"));// 6

				list.add(alist);
				System.err.println("---list data "+list);
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
		return list;
	}

	public ArrayList<ArrayList<String>> getSerialCollegeNumber(String system_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct sm.system_abbr||lpad(case  when max(SubString(college_unique_id,4,4)) is null\n"
					+ "or max(SubString(college_unique_id,4,4))=''\n"
					+ "then '1' else max(SubString(college_unique_id,4,4))::int+1 end::text,4,'0') \n"
					+ "as serial_col from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=ir.system_id where system_id=? group by sm.system_abbr  ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("serial_col"));// 0

				list.add(alist);
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
		return list;
	}

	public ArrayList<ArrayList<String>> getSyatemAbbr(String system_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct sm.system_abbr from edu_lms_system_mstr sm where sm.id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("system_abbr"));// 0

				list.add(alist);
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
		return list;
	}

	
	@Override
	public List<Map<String, Object>> getInstitueDetailsforEmail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q = " select id as institute_id,institute_name, institute_email as email_id, system_id as system_id from edu_lms_institute_reg order by system_id \n"
					+ "";

			PreparedStatement stmt = conn.prepareStatement(q);


			ResultSet rs = stmt.executeQuery();
			
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int countFunctionappr=1;
			int countFunctionReject=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
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
	public List<Map<String, Object>> getStudentDetailsforEmail() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q = "select name,email,system as system_id,institute_name ,institude_userid as inid from (select id,name,email,system,institude_userid from edu_lms_student_details where verified_status = -1\n"
					+ "Union all\n"
					+ "select id,name,email,system,institude_userid from edu_lms_nch_student_details where verified_status = -1) d\n"
					+ "inner join edu_lms_institute_reg i on i.id = d.institude_userid";

			PreparedStatement stmt = conn.prepareStatement(q);


			ResultSet rs = stmt.executeQuery();
			
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int countFunctionappr=1;
			int countFunctionReject=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
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
	public List<Map<String, Object>> GetAyushCollegeDataDetails() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

			q = "SELECT ir.address as address,institute_name as collegename,\n"
					+ "institute_mob_no as contactno,dm.district_name as district,\n"
					+ "institute_email as email,s.state_name as state,ir.status as status,\n"
					+ "sm.system_name as stream,\n"
					+ "ir.created_date as datetime,total_sanctioned_seat as ugstrength,\n"
					+ "ir.created_by as record_inserted_by,ir.created_date as record_inserted_on,\n"
					+ "code as unique_val,um.university_name as universityaffiliatedto,n.district_code,n.state_code\n"
					+ "FROM edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id = ir.system_id\n"
					+ "inner join edu_lms_district_mstr dm on dm.district_id = ir.district_id\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = ir.state_id\n"
					+ "inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
					+ "inner join nayan3 n on  upper(REPLACE(n.distrit,' ',''))=upper( REPLACE(dm.district_name,' ',''))\n"
					+ "--and upper(REPLACE(n.state,' ',''))=upper( REPLACE(s.state_name,' ',''))\n"
					+ "order by stream,collegename";

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
	
}
