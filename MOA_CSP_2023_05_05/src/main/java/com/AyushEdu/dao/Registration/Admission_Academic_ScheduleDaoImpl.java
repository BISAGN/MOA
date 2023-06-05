package com.AyushEdu.dao.Registration;

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

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;
import com.AyushEdu.Models.Registration.EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR;

@Repository
public class Admission_Academic_ScheduleDaoImpl implements Admission_Academic_ScheduleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;



	
	 public ArrayList<ArrayList<String>> getSystemID(String role) {
		 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			
	        Connection conn = null;
	        try{          
	        	
	        	conn = dataSource.getConnection();
	        	
	        	String sq1="select id,system_name from edu_lms_system_mstr where created_role=?";
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setString(1, role);
	            ResultSet rs = stmt.executeQuery();  
	            
	            System.err.println("stmt---"+stmt);
	            
	            String str1="";
	            while(rs.next()){
	            	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("system_name"));// 1
					alist.add(list);                           	  
	            }
	            
	            rs.close();
	            stmt.close();
	            conn.close();
	            
	       }catch(SQLException e){
	    	   e.printStackTrace();
	       }        
	       return alist;
		}
	
	 
	 
	 @Override
	 public List<Map<String,Object>> DataTableacademicscheduleDataList(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,  String academic_year, String system_name,String start_date,String end_date, String status,String userid) {

			String SearchValue = GenerateQueryWhereClause_SQL(Search, academic_year, system_name,start_date,end_date, status, userid);
	 
	 
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
			
			q = "\n"
					+ "select ROW_NUMBER() OVER(order by d.id) as sr_no, d.id,TO_CHAR(  TO_DATE(  d.academic_year,'YYYY-MM' ), 'MON-YYYY') as academic_year,"
					+ "s.system_name,   TO_CHAR(d.start_date, 'DD-MON-YYYY') as start_date, TO_CHAR(d.end_date, 'DD-MON-YYYY') as end_date ,s.status, d.academic_year as academic_yearformat, TO_CHAR(d.start_date, 'DD/MM/YYYY') as start_dateformat, TO_CHAR(d.end_date, 'DD/MM/YYYY') as end_dateformat,\n"
					+ "s.id as sysid \n "
					+ "from  edu_reg_admission_academic_schedule_mstr d\n"
					+ "inner join edu_lms_system_mstr s on s.id = d.system_id::int  where d.id!=0 AND d.status = '1' " + SearchValue + " ORDER BY d.id " + orderType + " limit " + pageL + " OFFSET "
					+ startPage; // "+orderColunm +"

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,academic_year,system_name, start_date,end_date, status, userid);
			System.err.println("\n\n stmt----"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
			
				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDAcademicdate1' value='ADD' title='Edit Data' >" 
							+"<i class='lni lni-pencil-alt'>"
							+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
							+"<input type='hidden' id='apacaAGE"+countFunction+"' value='"+rs.getString("academic_yearformat")+"'>"
							+"<input type='hidden' id='apsnAGE"+countFunction+"' value='"+rs.getString("sysid")+"'>"
							+"<input type='hidden' id='apsdAGE"+countFunction+"' value='"+rs.getString("start_dateformat")+"'>"
							+"<input type='hidden' id='apedAGE"+countFunction+"' value='"+rs.getString("end_dateformat")+"'>"
							+"<input type='hidden' id='apstatusAGE"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";

				
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+"<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f+ " " + f1;
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
	 
	 
	 public String GenerateQueryWhereClause_SQL(String Search, String academic_year,String system_name,String start_date,String end_date,String status,String userid) {
		 
			String SearchValue = "";
			if (Search != null && !Search.equals("")) { // for Input Filter
				SearchValue += "and (  upper(TO_CHAR(TO_DATE(d.academic_year,'MON-YYYY'), 'MM-YYYY')) like ? "
						+ "  or upper(s.system_name) like ?)"
						+ "  or  upper( TO_CHAR(d.start_date, 'DD-MM-YYYY'))  like ?"
						+  "  or  upper(TO_CHAR(d.end_date, 'DD-MM-YYYY'))  like ?"
						;
			}
			
			if (userid != null && !userid.equals("")) {
				SearchValue += "and d.created_by = ? ";
			}

			if (academic_year != null && !academic_year.equals("")) {
				SearchValue += "and d.academic_year = ? ";
			}
			
			if (!system_name.equals("0")) {
				SearchValue += "and s.id = ? ";
			}
			
			
			///advance search
		
			
			if( start_date != null && !start_date.equals("") && !start_date.equals("DD/MM/YYYY")) {
				SearchValue +=  "and TO_CHAR(start_date , 'dd/mm/yyyy') = ? ";
		     }
			
			if( end_date != null && !end_date.equals("") && !end_date.equals("DD/MM/YYYY")) {
				SearchValue += "and TO_CHAR(end_date , 'dd/mm/yyyy') = ? ";
		     }
		
			return SearchValue;
		}

	 public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String academic_year,String system_name,String start_date,String end_date,String status,String userid) {
			
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
				
				if (userid != null && !userid.equals("")) {
					flag += 1;
					stmt.setString(flag,userid);
				}
				
				if (academic_year != null && !academic_year.equals("")) {
					flag += 1;
					stmt.setString(flag,academic_year);
				}
				
				if (!system_name.equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(system_name));
				}
				
				if( start_date != null && !start_date.equals("") && !start_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, start_date );
				}
				if( end_date != null && !end_date.equals("") && !end_date.equals("DD/MM/YYYY")){
					flag += 1;
					stmt.setString(flag, end_date );
				}
			

			} catch (Exception e) {
			}

			return stmt;
		}
	 
	 		@Override
			public long DataTableacademicscheduleDataTotalCount(String Search, String academic_year,String system_name,String start_date,String end_date,String status,String userid)
			{

				String SearchValue = GenerateQueryWhereClause_SQL(Search,academic_year,system_name,start_date,end_date, status, userid);
				int total = 0;
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					q = "select count(*) from\n" 
					+ "(select ROW_NUMBER() OVER(order by d.id) as sr_no, d.id,TO_CHAR(  TO_DATE(  d.academic_year,'YYYY-MM' ), 'MON-YYYY') as academic_year,s.system_name,   TO_CHAR(d.start_date, 'DD/MM/YYYY') as start_date, TO_CHAR(d.end_date, 'DD/MM/YYYY') as end_date ,s.status, d.academic_year as academic_yearformat, TO_CHAR(d.start_date, 'DD/MM/YYYY') as start_dateformat, TO_CHAR(d.end_date, 'DD/MM/YYYY') as end_dateformat,\n"
					+ "s.id as sysid \n"
					+ "from  edu_reg_admission_academic_schedule_mstr d\n"
					+ "inner join edu_lms_system_mstr s on s.id = d.system_id::int  where d.id!=0  AND d.status = '1'"
							+ SearchValue+")ab";
					PreparedStatement stmt = conn.prepareStatement(q);

					stmt = setQueryWhereClause_SQL(stmt, Search,academic_year,system_name,start_date,end_date,status,userid);

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

			@Override
			public String updateAcademic_year(EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR td) {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				 String msg = "";
				try{
					String hql = "update EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR set academic_year=:academic_year,start_date=:start_date,end_date=:end_date,status=:status,modified_by=:modified_by,modified_dt=:modified_dt where id=:id";		
					
					@SuppressWarnings("rawtypes")
					Query query = sessionHQL.createQuery(hql)
							.setParameter("academic_year",td.getAcademic_year())
							.setParameter("start_date",td.getStart_date())
							.setParameter("end_date",td.getEnd_date())
							.setParameter("status",td.getStatus())
							.setParameter("modified_by",td.getModified_by())
							.setParameter("modified_dt",td.getModified_dt())
							.setParameter("id", td.getId());
					
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

			
	}

	
	


