package com.AyushEdu.dao.Alumni;

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

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;


@Repository

public class Feed_Category_DaoImpl implements Feed_Category_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}



@Override
public List<Map<String, Object>> DataTableFeedCategoryDataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, String feed_category, String status) {
 
	String SearchValue = GenerateQueryWhereClause_SQL(Search, feed_category);
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

		if (Search.equals("") && feed_category.equals("0") && status == "1") {

			q = "select ROW_NUMBER() OVER(order by feed_category ASC) as ser,id, feed_category,status from edu_alum_alumni_feed_category_mstr\n" + " where status= '1' \n"
					+ " ORDER BY feed_category " + orderType + " limit " + pageL + " OFFSET " + startPage;
		} else {
			q = "select ROW_NUMBER() OVER(order by feed_category ASC) as ser,id, feed_category,status from edu_alum_alumni_feed_category_mstr\n" + " where status= '" + status
					+ "'" + SearchValue + " ORDER BY feed_category " + orderType + " limit " + pageL + " OFFSET "
					+ startPage;
		}

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, feed_category);

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
			
			
			String ul="";
			ul+="<ul class='buttons-group mainbtn action daobtn'>";
			
			 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDFC' value='ADD' title='Edit Data' >" 
							+"<i class='lni lni-pencil-alt'>"
							+"<input type='hidden' id='FCId"+countFunction+"' value="+rs.getString("id")+">"
							+"<input type='hidden' id='FCmst"+countFunction+"' value="+rs.getString("feed_category")+">"
							+"<input type='hidden' id='FCStatus"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
			
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
public long DataTableFeedCategoryDataTotalCount(String Search, String feed_category) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, feed_category);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		q = "select count(*) from (select id, feed_category,status from edu_alum_alumni_feed_category_mstr\n"
				+ " where status='1' " + SearchValue + ")a where id!=0 ";

		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, feed_category);

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
public String GenerateQueryWhereClause_SQL(String Search, String feed_category) {

	String SearchValue = "";
	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and (upper(feed_category) like ?)";
		System.err.println("globalllll search" + SearchValue);

	}

	if (!feed_category.trim().equals("")) {
		SearchValue += " and feed_category like ? ";
		System.err.println("parameter search" + SearchValue);

	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String feed_category) {
	int flag = 0;
	try {

		if (Search != null && !Search.equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
		}

		if (feed_category != null && !feed_category.equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + feed_category.toUpperCase() + "%");
		}

	} catch (Exception e) {
	}

	return stmt;

}

public String updatecategory(EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR obj){
	Session session1 = this.sessionFactory.openSession();
	Transaction tx = session1.beginTransaction();
	
	 String msg = "";
	try{
		String hql = "update EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR set feed_category=:feed_category,status=:status,modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id";

		Query query = session1.createQuery(hql).setParameter("feed_category", obj.getFeed_category()).setParameter("status", (obj. getStatus()))
				.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
				.setParameter("id", obj.getId());
		
//		msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
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

public EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR getfeedcategoryByid(int id) {
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR updateid = (EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR) session
			.get(EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR.class, id);
	session.getTransaction().commit();
	session.close();
	return updateid;
}



//@Override
//public long DataTableFeedCategoryDataTotalCount(String Search, String feed_category) {
//	// TODO Auto-generated method stub
//	return 0;
//}

}

