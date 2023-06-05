package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Registration.TB_EXPERIENCE_DETAILS;


@Service
@Repository
public class TotalExperienceDaoImpl implements TotalExperienceDao{
	
	@Autowired
	private DataSource dataSource;

	CommonController comMstr = new CommonController();
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<ArrayList<String>> DataTableExperienceDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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
			
			q = "select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no, ed.id,ed.exp_emp_name,TO_CHAR(exp_emp_fromdate , 'DD-MON-YYYY') as exp_emp_fromdate,\n"
					+ "					TO_CHAR(exp_emp_todate , 'DD-MON-YYYY') as exp_emp_todate,\n"
					+ "					ed.exp_emp_document,ed.exp_total_year,ed.exp_total_month,ed.exp_total_day,\n"
					+ "					TO_CHAR(exp_emp_fromdate , 'DD/MM/YYYY') as fmd1,TO_CHAR(exp_emp_todate , 'DD/MM/YYYY') as td1 from tb_total_experience ed\n"
					+ "					inner join tb_registration_dtl mp on mp.recr_email =  ed.created_by \n"
					+ "					inner join logininformation mp2 on mp2.username =  mp.recr_email \n"
					+ "					where ed.id!=0 " + SearchValue + " ORDER BY ed.id " + orderType + " limit " + pageL + " OFFSET "
					+ startPage; // "+orderColunm +"

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);
System.err.println("stmt------------" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;
			
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Category ?') )"
						+ "{editData('"+ rs.getInt("id") + "','"+rs.getString("exp_emp_name")+"','" +rs.getString("fmd1")+ "',"
						+ "'"+rs.getString("td1")+"','"+rs.getInt("exp_total_year")+"',"
						+ "'"+rs.getInt("exp_total_month")+"','"+rs.getInt("exp_total_day")+"','"+rs.getString("exp_emp_document")+"')}else{ return false;}\"";
				f = "<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
				
				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Category ?') )"
						+ "{getDownloadPdfExperience('"+ rs.getInt("id") + "')}else{ return false;}\"";
				f1 = "<i class='fa fa-download' "+Download+" title='Downlaod'></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Data?') )"
						+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				
				action = f + f2;
				
				list.add(rs.getString("sr_no"));
				list.add(rs.getString("exp_emp_name"));//1
				list.add(rs.getString("exp_emp_fromdate"));//2
				list.add(rs.getString("exp_emp_todate"));//3
				list.add(rs.getString("exp_total_year"));//4
				list.add(rs.getString("exp_total_month"));//5
				list.add(rs.getString("exp_total_day"));//6
//				list.add(rs.getString("exp_emp_document"));//7
				
				list.add(f1);
				list.add(action);
				i++;
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
	public long DataTableExperienceDataTotalCount(String Search, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q = "select count(*) from (SELECT ROW_NUMBER() OVER(order by ed.id) as sr_no, ed.id,ed.exp_emp_name,TO_CHAR(exp_emp_fromdate , 'DD-MON-YYYY') as exp_emp_fromdate,\n"
					+ "					TO_CHAR(exp_emp_todate , 'DD-MON-YYYY') as exp_emp_todate,\n"
					+ "					ed.exp_emp_document,ed.exp_total_year,ed.exp_total_month,ed.exp_total_day,\n"
					+ "					TO_CHAR(exp_emp_fromdate , 'DD/MM/YYYY') as fmd1,TO_CHAR(exp_emp_todate , 'DD/MM/YYYY') as td1 from tb_total_experience ed\n"
					+ "					inner join tb_registration_dtl mp on mp.recr_email =  ed.created_by \n"
					+ "					inner join logininformation mp2 on mp2.username =  mp.recr_email \n"
					+ "					where ed.id!=0  "
					+ SearchValue + ") ab  ";
	//		q = "select count(*) \n" + "from tb_total_experience ed where id!=0 " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);
			
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
	
	public String GenerateQueryWhereClause_SQL(String Search, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and (  upper(ed.exp_emp_name) like ? or ed.exp_emp_fromdate::text like ? or ed.exp_emp_fromdate::text like ?"
					+ "  or upper(ed.exp_emp_document) like ?)";
		}

		if (userid != 0) {
			SearchValue += " and mp2.userid = ? ";
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,int userid) {
		
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
//			if (!exp_emp_name.equals("") && exp_emp_name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + exp_emp_name.toUpperCase() + "%");
//			}
//			if (!institute_name.equals("") && institute_name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + institute_name.toUpperCase() + "%");
//			}
//			if (!exp_emp_document.equals("") && exp_emp_document != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + exp_emp_document.toUpperCase() + "%");
//			}
			
			if (userid != 0) {
				flag += 1;
				stmt.setInt(flag, userid);
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public String getFilePathQuery(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select exp_emp_document from tb_total_experience where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("exp_emp_document");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
	
	public String updateSubCategory(TB_EXPERIENCE_DETAILS obj){
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		 String msg = "";
		 
		try{
			String hql = "update TB_EXPERIENCE_DETAILS set exp_emp_name=:exp_emp_name,exp_emp_fromdate=:exp_emp_fromdate,exp_emp_todate=:exp_emp_todate,"
					+ "exp_emp_document=:exp_emp_document,modified_by=:modified_by,modified_date=:modified_date where id=:id";		
			
			@SuppressWarnings("rawtypes")
			Query query = sessionHQL.createQuery(hql)
					.setParameter("exp_emp_name",obj.getExp_emp_name())
					.setParameter("exp_emp_fromdate",obj.getExp_emp_fromdate())
				//	.setParameter("exp_emp_fromdate", comMstr.convertStringToDate(exp_emp_fromdate))
					.setParameter("exp_emp_todate",obj.getExp_emp_todate())
				//	.setParameter("exp_emp_todate", comMstr.convertStringToDate(exp_emp_todate))
					.setParameter("exp_emp_document",obj.getExp_emp_document())
					.setParameter("modified_by",obj.getModified_by())
					.setParameter("modified_date",obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	}
	
////////////////////////////////for date	
	@Override
	public ArrayList<ArrayList<String>> DataFor_DateDataList(String exp_emp_fromdate,String exp_emp_todate,int userid) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q = " SELECT count(id)\n"
					+ "   FROM tb_total_experience ed \n"
					+ "    inner join tb_registration_dtl mp on mp.recr_email =  ed.created_by \n"
					+ "	inner join logininformation mp2 on mp2.username =  mp.recr_email \n"
					+ "  WHERE (? ::DATE, ? ::DATE)   OVERLAPS (exp_emp_fromdate, exp_emp_todate) and ed.id!=0  and mp2.userid = ? " ;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, exp_emp_fromdate);
			stmt.setString(2, exp_emp_todate);
			stmt.setInt(3, userid);

			ResultSet rs = stmt.executeQuery();
		System.err.println("STMT-------------------"+stmt);
			 while (rs.next()) {
				 
		    	 ArrayList<String> list = new ArrayList<String>();
				
		    	list.add(rs.getString("count"));//0
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
