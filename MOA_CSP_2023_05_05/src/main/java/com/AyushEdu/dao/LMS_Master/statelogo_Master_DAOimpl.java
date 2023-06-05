package com.AyushEdu.dao.LMS_Master;

import java.awt.Image;
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
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_STATE_LOGO_MSTR;

@Repository
public class statelogo_Master_DAOimpl implements statelogo_Master_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public long DataTablestatelogoDataTotalCount(String search, String state_id, String state_logo_path, String status) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, state_id, state_logo_path,status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select id, state_name,e.state_id,state_logo, e.status  from edu_lms_statelogo_mstr e\n"
					+ "inner join edu_lms_state_mstr s on s.state_id =e.state_id \n"
					+ " where id!=0 and e.status='1' \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, state_id, state_logo_path,status);

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
	

	private String GenerateQueryWhereClause_SQL(String search, String state_id, String state_logo_path,String status) {
		String SearchValue = "";
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and upper(s.state_name) like ? ";
			System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
	
	
	   if(!SearchValue.contains("and") && state_id != null && !state_id.equals("0")){
			SearchValue += " and e.state_id = ? ";
			System.err.println("parameter search"+SearchValue);	
	    }

	   
		return SearchValue;
	}
	private PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String state_id, String state_logo_path,String status) {
		int flag = 0;
		try {
			
			
			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				
			}

			if (state_id != null && !state_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag ,Integer.parseInt(state_id));
			}
			
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	@Override
	public List<Map<String, Object>> DataTablestatelogoDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state_id, String state_logo_path,String status) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, state_id, state_logo_path,status);
		Image image = new ImageIcon(this.getClass().getResource(state_logo_path)).getImage();
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

			if(status=="1") {
				q = "select e.id, s.state_name,e.state_id,state_logo, e.status  from edu_lms_statelogo_mstr e\n"
						+ "inner join edu_lms_state_mstr s on s.state_id =e.state_id \n"
						+ " where id!=0 and e.status='1' "  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select e.id, s.state_name,e.state_id,state_logo, e.status  from edu_lms_statelogo_mstr e\n"
					+ "inner join edu_lms_state_mstr s on s.state_id =e.state_id \n"
					+ " where id!=0 and e.status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
//			System.err.println("globalllll search"+SearchValue);
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, state_id, state_logo_path,status);
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
				
				columns.put("img",
						"<img class='d-block img5050 imageZomm' alt='No Image' "
								+"src='View_StateLogo?i_id=" + rs.getString("id") + "' onclick='imageView("
								+ rs.getString("id") + ");' />");
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDstatelogo' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("state_id")+"'>"
								+"<input type='hidden' id='approfAGE1"+countFunction+"' value='"+rs.getString("state_logo")+"'>"
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

	@Override
	public EDU_LMS_STATE_LOGO_MSTR getClassroomByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_STATE_LOGO_MSTR updateid = (EDU_LMS_STATE_LOGO_MSTR) session.get(EDU_LMS_STATE_LOGO_MSTR.class, id);
        session.getTransaction().commit();
        session.close();                
       return updateid;
	}

	@Override
	public String updateStudentLogo(EDU_LMS_STATE_LOGO_MSTR rmd) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_LMS_STATE_LOGO_MSTR set state_id=:state_id,state_logo=:state_logo,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("state_id", rmd.getState_id()).setParameter("state_logo", rmd.getState_logo()).setParameter("status", (rmd. getStatus()))
					.setParameter("modified_by", rmd.getModified_by()).setParameter("modified_date", rmd.getModified_date())
					.setParameter("id", rmd.getId());
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
	

	@Override
	public String getStatelogoImagePath(int id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select state_logo from edu_lms_statelogo_mstr where id=? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("state_logo");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

}
