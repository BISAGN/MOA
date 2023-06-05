package com.AyushEdu.dao.Curriculum_Mstr;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_HOURS_MSTR;

@Repository
public class CC_Type_Of_Hours_Mstr_DaoImpl implements CC_Type_Of_Hours_Mstr_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableTypeOfHoursDataTotalCount(String Search, String type_of_hours, String status) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_hours);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(Search.equals("") && type_of_hours.equals("0") && status=="1") {
			q="select count(*) from (select id,type_of_hours,status  from edu_cc_tb_type_of_hours_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}else {
			q="select count(*) from (select id,type_of_hours,status  from edu_cc_tb_type_of_hours_mstr where id!=0 and status='"+ status +"' \n"
						+ SearchValue + ") ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_hours);

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
	public String GenerateQueryWhereClause_SQL(String Search, String type_of_hours) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(type_of_hours) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( type_of_hours != null && !type_of_hours.equals("")) {
			SearchValue += " and upper(type_of_hours) like ? ";
		
	     }
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String type_of_hours) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (type_of_hours != null && !type_of_hours.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+type_of_hours.toUpperCase()+"%");
			}
		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableTypeOfHoursDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_hours, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_hours);
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

			if(Search.equals("") && type_of_hours.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by type_of_hours ) as ser, id,type_of_hours,status from edu_cc_tb_type_of_hours_mstr where status='1'"  + SearchValue +  " ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by type_of_hours ) as ser,id,type_of_hours,status from edu_cc_tb_type_of_hours_mstr where status='"+ status +"'"  + SearchValue +  " ORDER BY "+orderColunm+" " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_hours);
			System.err.println("stmt"+stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDType_Of_Hours' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("type_of_hours")+"'>"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";
				
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
	
	public String updateType_Of_Hours(CC_TB_TYPE_OF_HOURS_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_TYPE_OF_HOURS_MSTR set type_of_hours=:type_of_hours,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("type_of_hours", obj.getType_of_hours()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
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
}