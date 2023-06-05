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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LINK_SYS_DEG_PROF_TERM;
import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
@Repository
public class Link_Sys_Deg_Prof_Term_DAOImpl implements Link_Sys_Deg_Prof_Term_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	

	public List<Map<String, Object>> DataTablelink_sys_deg_prof_termDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id,String degree_id,String prof,String term, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id,degree_id,prof,term,status);
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

				q = "select ROW_NUMBER() OVER(order by sdpt.id ASC) as sr_no,sdpt.id,sm.system_name,dm.degree_name,cc_pm.professional,i3_tm.term, \n"
						+"sdpt.system,sdpt.degree,sdpt.prof,sdpt.term as term_id \n"
						+"from edu_link_sys_deg_prof_term sdpt\n" 
						+"inner join edu_lms_system_mstr sm on sm.id=sdpt.system \n"  
						+"inner join edu_lms_degree_mstr dm on dm.id=sdpt.degree \n"  
						+"inner join edu_cc_tb_professional_mstr cc_pm on cc_pm.id=sdpt.prof \n"  
						+"inner join edu_cc_tb_i3_term_mstr i3_tm on i3_tm.id=sdpt.term where sdpt.status='1'" 
						+ SearchValue + " ORDER BY sdpt.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id,degree_id,prof,term,status);
			
			System.err.println("\n\nstmt========================="+stmt+"\n\n");
			
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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" 
						+ "<input type='hidden' id='editId" + countFunction + "' value='" + rs.getString("id") + "'>"
						+ "<input type='hidden' id='sysId" + countFunction + "' value='" + rs.getString("system") + "'>"
						+ "<input type='hidden' id='degId" + countFunction + "' value='" + rs.getString("degree") + "'>"
						+ "<input type='hidden' id='profId" + countFunction + "' value='" + rs.getString("prof") + "'>"
						+ "<input type='hidden' id='termId" + countFunction + "' value='" + rs.getString("term_id") + "'>"
						+"</i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+"<input type='hidden' id='deleteID" + countFunctionDelete + "' value='" + rs.getString("id") +"'>"
						+"<i class='lni lni-trash-can'></i></a></li>";

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
	public long DataTablelink_sys_deg_prof_termDataTotalCount(String Search, String system_id,String degree_id,String prof,String term, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id,degree_id,prof,term,status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select sdpt.id,sm.system_name,dm.degree_name,cc_pm.professional,i3_tm.term from edu_link_sys_deg_prof_term sdpt\n" + 
					"inner join edu_lms_system_mstr sm on sm.id=sdpt.system\n" + 
					"inner join edu_lms_degree_mstr dm on dm.id=sdpt.degree\n" + 
					"inner join edu_cc_tb_professional_mstr cc_pm on cc_pm.id=sdpt.prof\n" + 
					"inner join edu_cc_tb_i3_term_mstr i3_tm on i3_tm.id=sdpt.term where sdpt.id!=0 and sdpt.status='1' \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);
System.err.println("stmt========================="+stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id,degree_id,prof,term,status);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id,String degree_id,String prof,String term, String status) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(dm.degree_name) like ? or upper(cc_pm.professional) like ? or upper(i3_tm.term) like ?)";

		}
		if (!system_id.equals("0")) {
			SearchValue += " and sdpt.system = ? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and sdpt.degree = ? ";
		}
		if (!prof.equals("0")) {
			SearchValue += " and sdpt.prof = ? ";
		}
		if (!term.equals("0")) {
			SearchValue += " and sdpt.term = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,String degree_id,String prof,String term, String status) {
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
			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			if (!prof.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(prof));
			}
			if (!term.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(term));
			}
			

		} catch (Exception e) {
		}

		return stmt;
	}
	
	@Override
	public String updateSysDegProfTerm(EDU_LINK_SYS_DEG_PROF_TERM obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_LINK_SYS_DEG_PROF_TERM set system=:system,degree=:degree,prof=:prof,term=:term,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("system", obj.getSystem()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("degree", obj.getDegree()).setParameter("prof", obj.getProf())
					.setParameter("term", obj.getTerm())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
//			msg = query.executeUpdate() > 0 ? "1" : "0";
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


}
