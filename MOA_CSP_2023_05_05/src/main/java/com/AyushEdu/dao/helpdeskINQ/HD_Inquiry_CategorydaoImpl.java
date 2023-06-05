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

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.helpdeskINQ.HD_INQUIRY_CATEGORY_MSTR;
import com.AyushEdu.Models.helpdeskINQ.HD_INQ_LINK_ROLE_MSTR;

@Repository
public class HD_Inquiry_CategorydaoImpl implements HD_Inquiry_CategoryDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableInq_CatDataTotalCount(String search, String inq_cat,String type) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(search, inq_cat, type);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q = "select count(*) from (select id,inq_cat, CASE  WHEN type=0 THEN 'External' when type=1 THEN 'Internal' END as type,status  from hd_inq_cat_mstr where id!=0 \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, inq_cat, type);
			
			System.err.println("------stmt----------" +stmt);

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
	public String GenerateQueryWhereClause_SQL(String search, String inq_cat,String type) {
		String SearchValue = "";
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(inq_cat) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( inq_cat != null && !inq_cat.equals("")) {
			SearchValue += " and upper(inq_cat) like ? ";
		
	     }
		
		if( type != null && !type.equals("")) {
			SearchValue += " and type = ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String inq_cat,String type) {
		int flag = 0;
		try {
			
			if (search!=null &&  !search.equals("")) {				
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");

			}
			
			if (inq_cat != null && !inq_cat.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+inq_cat.toUpperCase()+"%");
			}
			
			if (type != null && !type.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(type));
			}
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableInq_CatDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String inq_cat,String type) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, inq_cat, type);
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

//			if(search.equals("") && inq_cat.equals("0") && type.equals("")) {
				q = "select id,inq_cat, CASE  WHEN type=0 THEN 'External' when type=1 THEN 'Internal' END as type,status  from hd_inq_cat_mstr where id!=0" + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
//			}else {
//			q = "select id,inq_cat,type,status from hd_inq_cat_mstr where status='1'" + SearchValue + " ORDER BY id " + orderType + " limit "
//					+ pageL + " OFFSET " + startPage;
//			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, inq_cat, type);
			System.err.println("stmt--->"+stmt);
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
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDinq_cat' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("inq_cat")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("type")+"'>"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
	
	
	public HD_INQUIRY_CATEGORY_MSTR getInq_CatByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 HD_INQUIRY_CATEGORY_MSTR updateid = (HD_INQUIRY_CATEGORY_MSTR) session.get(HD_INQUIRY_CATEGORY_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
	}

	public String updateInq_Cat(HD_INQUIRY_CATEGORY_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update HD_INQUIRY_CATEGORY_MSTR set inq_cat=:inq_cat,type =:type,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("inq_cat", obj.getInq_cat())
					.setParameter("type", obj.getType())
					.setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_dt", obj.getModified_dt())
					.setParameter("id", obj.getId());
			 
			 System.err.println("------query------" +query);
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
	
	
	
	//////////link and report
	
	public ArrayList<ArrayList<String>> getuser(String roleId) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select a.userid,a.username  from logininformation a\n"
					+ "inner join userroleinformation b on b.user_id=a.userid\n"
					+ "where b.role_id=?  ";
			
			stmt = conn.prepareStatement(q);
				
			stmt.setInt(1, Integer.parseInt(roleId));
			System.err.println("TfT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("userid"));// 0
				list.add(rs.getString("username"));//1				
//				list.add(rs.getString("course"));//2
//				list.add(rs.getString("faculty"));//3
//				list.add(rs.getString("classroom"));//4
//				list.add(rs.getString("lec_type"));//5
				
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
	
	
	////////////////////////////////////////////////////////////link and report//////////////////////////////////////////////////
	
	@Override
	public List<Map<String, Object>> getFilterInq_Cat_datalist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String inq_no, String role, String user,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,  inq_no,  role,  user, status);
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
			if(status.equals("1"))
			{
			q = " select ROW_NUMBER() OVER(order by a.id ASC) as ser,a.id,a.inq_no,b.role,c.login_name,c.username,a.user_id1 from hd_inq_link_role_mstr a\n"
					+ "inner join roleinformation b on b.role_id=a.role\n"
					+ "inner join logininformation c on c.userid=a.user_id1 where a.status=1 "
					+ SearchValue + " ORDER BY a.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;
			}
			if(status.equals("2"))
			{
			q = " select ROW_NUMBER() OVER(order by a.id ASC) as ser,a.id,a.inq_no,b.role,c.login_name,c.username,a.user_id1 from hd_inq_link_role_mstr a\n"
					+ "inner join roleinformation b on b.role_id=a.role\n"
					+ "inner join logininformation c on c.userid=a.user_id1 where a.status=2 "
					+ SearchValue + " ORDER BY a.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,  inq_no,  role,  user, status);
	System.err.println("stmt======================"+stmt);
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
	if(status.equals("1"))
	{
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm Edit_Inquirylink' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EDIT_INQUIRYLINK" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteInquirylink' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DELETE_INQUIRYLINK" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	}
	
	if(status.equals("2"))
	{
		String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
				+ rs.getString("id") + "') }else{ return false;}\"";
		f = "<li><a class='main-btn active-btn btn-hover btn-sm Edit_Inquirylink' title='Edit Data'>"
				+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EDIT_INQUIRYLINK" + countFunction
				+ "' value=" + rs.getString("id") + "></i></a> </li>";
	}
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
	public long getTotalInq_Cat_dataCount1(String Search, String inq_no, String role, String user,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,   inq_no,  role,  user, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			if(status.equals("1"))
			{
			q = "select count(*) from (select a.id,a.inq_no,b.role,c.login_name,c.username,a.user_id1,a.role from hd_inq_link_role_mstr a\n"
					+ "inner join roleinformation b on b.role_id=a.role\n"
					+ "inner join logininformation c on c.userid=a.user_id1 where a.status=1 "
					+ SearchValue + ")a";
			}
			if(status.equals("2"))
			{
			q = "select count(*) from (select a.id,a.inq_no,b.role,c.login_name,c.username,a.user_id1,a.role from hd_inq_link_role_mstr a\n"
					+ "inner join roleinformation b on b.role_id=a.role\n"
					+ "inner join logininformation c on c.userid=a.user_id1 where a.status=2 "
					+ SearchValue + ")a";   
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,  inq_no,  role,  user, status);
			System.err.println("stmt======================"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,  String inq_no, String role, String user,String status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(a.inq_no) like ? or upper(b.role) like ? or upper(c.login_name) like ? or upper(c.username) like ?) ";
		}

		if (!inq_no.trim().equals("")) {
			SearchValue += " and upper(a.inq_no) like ? ";
		}
		
		if (!role.equals("0")) {
			SearchValue += " and a.role = ? ";

		}
		if (!user.equals("0")) {
			SearchValue += " and a.user_id1 = ? ";

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search, String inq_no, String role, String user,String status) {

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
			}

			if (!inq_no.equals("") && inq_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + inq_no.toUpperCase() + "%");
			}
			if (!role.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(role));
			}
			if (!user.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(user));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public HD_INQ_LINK_ROLE_MSTR getInquiry_Link_RoleByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		HD_INQ_LINK_ROLE_MSTR updateid = (HD_INQ_LINK_ROLE_MSTR) session.get(HD_INQ_LINK_ROLE_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	

	
//	@Override
//	public List<Map<String, Object>> getFilterInq_Report_datalist(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType,  String inq_no, String per_state, String des) {
//		String SearchValue = GenerateQueryWhereClause_SQL(Search,  inq_no,  per_state,  des, null);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//		Connection conn = null;
//		String q = "";
//		try {
//
//			conn = dataSource.getConnection();
//			String pageL = "";
//			if (pageLength == -1) {
//				pageL = "ALL";
//			} else {
//				pageL = String.valueOf(pageLength);
//			}
//
//			q = " select ROW_NUMBER() OVER(order by a.id ASC) as ser,c.state_name,b.inq_cat, a.inq_no,a.description,a.email from hd_inq_helpdesk_p a \n"
//					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
//					+ "inner join edu_lms_state_mstr c on c.state_id=a.state where id!=0 "
//					+ SearchValue + " ORDER BY a.id "  + orderType + " limit " + pageL + " OFFSET "
//					+ startPage;
//
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt = setQueryWhereClause_SQL1(stmt, Search,  inq_no,  per_state,  des);
//	System.err.println("stmt========jj=============="+stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			int j = startPage;
//
//			int countFunction = 1;
//			int countFunctionDelete = 1;
//
//			while (rs.next()) {
//
//				ArrayList<String> alist = new ArrayList<String>();
//
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//
////				String f = "";
////				String action = "";
////				String f1 = "";
////	
////				String ul = "";
////				ul += "<ul class='buttons-group mainbtn action daobtn'>";
////	
////				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
////						+ rs.getString("id") + "') }else{ return false;}\"";
////				f = "<li><a class='main-btn active-btn btn-hover btn-sm Edit_Inquirylink' title='Edit Data'>"
////						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EDIT_INQUIRYLINK" + countFunction
////						+ "' value=" + rs.getString("id") + "></i></a> </li>";
////	
////				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
////						+ rs.getString("id") + "') }else{ return false;}\"";
////				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteInquirylink' title='Delete Data'>"
////						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DELETE_INQUIRYLINK" + countFunctionDelete
////						+ "' value=" + rs.getString("id") + "></i></a> </li>";
////	
////				ul += f + " " + f1;
////				ul += "</ul>";
////	
////				countFunction += 1;
////				countFunctionDelete += 1;
////	
////				action = ul;
////				columns.put("action", action);
////				list.add(columns);
//
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
//		return list;
//	}
//
//	@Override
//	public long getTotalInq_Report_dataCount1(String Search, String inq_no, String per_state, String des) {
//		String SearchValue = GenerateQueryWhereClause_SQL(Search, inq_no,  per_state,  des, null);
//		int total = 0;
//		String q = null;
//		Connection conn = null;
//		try {
//			conn = dataSource.getConnection();
//
//			q = "select count(*) from (select c.state_name,b.inq_cat, a.inq_no,a.description,a.email from hd_inq_helpdesk_p a \n"
//					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
//					+ "inner join edu_lms_state_mstr c on c.state_id=a.state where id!=0 "
//					+ SearchValue + ")a";
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt = setQueryWhereClause_SQL1(stmt, Search,  inq_no,  per_state,  des);
//			System.err.println("stmt=========dsd============="+stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				total = rs.getInt(1);
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
//		return (long) total;
//	}
//
//	@SuppressWarnings("null")
//	public String WhereClause_SQL1(String Search,  String inq_no, String per_state, String des) {
//
//		String SearchValue = "";
//		if (Search != null && !Search.equals("")) { // for Input Filter
//			SearchValue += " and (upper(a.inq_no) like ? or upper(c.state_name) like ? or upper(b.inq_cat) like ? or upper(a.description) like ? or upper(a.email) like ?) ";
//		}
//
//		if (!inq_no.trim().equals("")) {
//			SearchValue += " and upper(a.inq_no) like ? ";
//		}
//		
//		if (!per_state.equals("0")) {
//			SearchValue += " and a.per_state = ? ";
//
//		}
//		if (!des.equals("")) {
//			SearchValue += " and a.description like ? ";
//
//		}
//		return SearchValue;
//	}

//	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String inq_no, String per_state, String des) {
//
//		int flag = 0;
//		try {
//			if (Search != null && !Search.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//			}
//
//			if (!inq_no.equals("") && inq_no != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + inq_no.toUpperCase() + "%");
//			}
//			if (!per_state.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(per_state));
//			}
//			if (!des.equals("") && inq_no != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + des.toUpperCase() + "%");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return stmt;
//	}

	


}
