package com.AyushEdu.dao.helpdeskINQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;

@Repository
public class HD_Inquiry_ReportDaoImpl implements HD_Inquiry_ReportDao {

	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Map<String, Object>> getFilterInq_Report_datalist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String inq_no, String inq_cat, String per_state, String des, String status, HttpSession session) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,  inq_no, inq_cat, per_state,  des, status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String assRole = "";
		String assRolewhr = "";
		try {

			String rolename = session.getAttribute("rolename").toString();
			String userId = session.getAttribute("userId").toString();
			
			if (rolename.equals("NCISM_ADMIN")) {
				assRolewhr = "  and a.system_id in (44,46,47,48) ";
			}
			else if (rolename.equals("NCH_ADMIN")) {
				assRolewhr = " and a.system_id in (45) ";
			}
			else {
				assRole = " inner join hd_inq_link_role_mstr lm on lm.inq_no = a.inq_no \n";
				assRolewhr = " and lm.user_id1 in ("+userId+") ";
			}
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			q = "select ROW_NUMBER() OVER(order by a.id ASC) as ser,c.state_name,b.inq_cat, a.inq_no,a.description,a.email,a.id, CASE  WHEN a.status=1 THEN 'Under Process' when a.status=2 THEN 'Close' Else '--' END as status  from hd_inq_helpdesk_p a \n"
					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
					+ assRole
					+ "inner join edu_lms_state_mstr c on c.state_id=a.state where a.id!=0 "+assRolewhr
					+ SearchValue + " ORDER BY a.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;


			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, Search,  inq_no, inq_cat, per_state, des, status);
//			stmt.setInt(1,Integer.parseInt(userid));
	System.err.println("stmt========jj=============="+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

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
				f = "<li><a data-bs-toggle=\"modal\"\n"
						+ "	data-bs-target=\"#Inq_Case_model\" class='main-btn active-btn btn-hover btn-sm ADDInquiryFrom' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" 
						+"<input type='hidden' id='hid_inq_rep"+countFunction+"' value="+rs.getString("id")+">"
						+"</i></a> </li>";
				
				if (rolename.equals("NCISM_ADMIN") || rolename.equals("NCH_ADMIN")) {
					
					String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Asign Detail ?') ){redirectData('"+ rs.getString("id") + "') }else{ return false;}\"";
					 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm redirectOnclick' +ADD1+ value='ADD' title='Redirect Data' >"+ //id='id_add_attHospital1'
							 "<input type='hidden' id='hid_asg_rep"+countFunctionDelete+"' value="+rs.getString("inq_no")+"><i class='lni lni-save'></i></a> </li>";
	
				}
				 
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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


	public long getTotalInq_Report_dataCount1(String Search, String inq_no, String inq_cat, String per_state, String des,String status, HttpSession sessionUserId) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, inq_no, inq_cat, per_state,  des, status);
		int total = 0;
		String q = null;
		String assRole = "";
		String assRolewhr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String rolename = sessionUserId.getAttribute("rolename").toString();
			String userId = sessionUserId.getAttribute("userId").toString();
			
			if (rolename.equals("NCISM_ADMIN")) {
				assRolewhr = "  and a.system_id in (44,46,47,48) ";
			}
			else if (rolename.equals("NCH_ADMIN")) {
				assRolewhr = " and a.system_id in (45) ";
			}
			else {
				assRole = " inner join hd_inq_link_role_mstr lm on lm.inq_no = a.inq_no \n";
				assRolewhr = " and lm.user_id1 in ("+userId+") ";
			}

			q = "select count(*) from (select ROW_NUMBER() OVER(order by a.id ASC) as ser,c.state_name,b.inq_cat, a.inq_no,a.description,a.email,a.id, CASE  WHEN a.status=1 THEN 'Under Process' when a.status=2 THEN 'Close' Else '--' END as status  from hd_inq_helpdesk_p a \n"
					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
					+ assRole
					+ "inner join edu_lms_state_mstr c on c.state_id=a.state where a.id!=0 "+assRolewhr
					+ SearchValue + ")a";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, Search,  inq_no, inq_cat, per_state,  des, status);
			System.err.println("stmt=========dsd============="+stmt);
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

//	private String GenerateQueryWhereClause_SQL(String search, String inq_no, String per_state, String des) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String inq_no, String inq_cat, String per_state, String des, String status ) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter 
			SearchValue += " and ( upper(c.state_name) like ? or upper(b.inq_cat) like ? or upper(a.inq_no) like ? or upper(a.description) like ? or upper(a.email) like ? ) ";
		}
		
		
		
		if (!per_state.equals("0")) {
			SearchValue += " and a.state = ? ";
		}
		
		if (!inq_cat.equals("0")) {
			SearchValue += " and a.inq_cat = ? ";

		}
		
		if (!inq_no.trim().equals("")) {
			SearchValue += " and upper(a.inq_no) like ? ";
		}
		
		if (!des.equals("")) {
			SearchValue += " and upper(a.description) like ? ";

		}
//		if (!status.equals("0")) {
//			SearchValue += " and a.status = ? ";
//
//		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String inq_no, String inq_cat, String per_state, String des, String status ) {

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
//				flag += 1;
//				stmt.setString(flag, "%" + Search + "%");
			}

			if (!inq_no.equals("") && inq_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + inq_no.toUpperCase() + "%");
			}
			if (!inq_cat.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(inq_cat));
			}
			if (!per_state.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(per_state));
			}
			if (!des.equals("") && des != null) {
				flag += 1;
				stmt.setString(flag, "%" + des.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}


//	@Override
//	public ArrayList<ArrayList<String>> getInq_RepDetailsDao(String hid, String note, int status) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			
//			q="SELECT li.userid,li.login_name FROM edu_tt_faculty_course_map cmap\n"
//					+ "INNER join logininformation li on cmap.faculty = li.userid\n"
//					+ "where cmap.course = ? and cmap.institute_id = ?\r\n"
//					+ "";
//			
//			stmt = conn.prepareStatement(q);
//			stmt.setInt(1,Integer.parseInt(hid));
//			stmt.setString(2,note);
//			stmt.setInt(3,status);
//			System.err.println("TT====="+stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				
//				list.add(rs.getString("userid"));// 0
//				list.add(rs.getString("login_name"));//1
//				
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}
	
	public String getFilePathhd_Query(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select attachment from hd_inq_helpdesk_p where id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("attachment");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
	
	public List<Map<String,Object>> getInq_Reportchildeditstatus(String p_id,  String note, String status) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();\
		List<Map<String, Object>> alist = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select a.note,b.status from hd_inq_helpdesk_child a\n"
					+ "inner join hd_inq_helpdesk_p b on b.id=a.p_id where p_id=? ";
			
			stmt = conn.prepareStatement(q);
				
			stmt.setInt(1, Integer.parseInt(p_id));
//			stmt.setInt(1, Integer.parseInt(status));
			System.err.println("TfThjghhgjh====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
//				ArrayList<String> list = new ArrayList<String>();
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				
//				list.add(rs.getString("note"));// 0
//				list.add(rs.getString("status"));//1				
//				list.add(rs.getString("course"));//2
//				list.add(rs.getString("faculty"));//3
//				list.add(rs.getString("classroom"));//4 
//				list.add(rs.getString("lec_type"));//5
				columns.put("note", rs.getString("note"));
				columns.put("status", rs.getString("status"));
				alist.add(columns);
			
				
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
