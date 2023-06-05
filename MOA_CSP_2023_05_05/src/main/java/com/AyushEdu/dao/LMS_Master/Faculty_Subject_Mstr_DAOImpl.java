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

import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

@Repository
public class Faculty_Subject_Mstr_DAOImpl implements Faculty_Subject_Mstr_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableSubjectDataTotalCount(String Search, String fac_course_id, String subject_name) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,fac_course_id, subject_name);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select fsubm.id,fcm.course_name ,fsubm.fac_course_id,fsubm.subject_name, fsubm.status from edu_faculty_subject_mstr fsubm \n"
											+ "inner join  edu_faculty_course_mstr fcm on fcm.id = fsubm.fac_course_id\n"
											+ "where fsubm.id!=0 and fsubm.status='1'\n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,fac_course_id, subject_name);
System.err.println("stmt---count   "+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String fac_course_id, String subject_name) {
		String SearchValue = "";
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(subject_name) like ? or  upper(course_name) like ?)";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( subject_name != null && !subject_name.equals("")) {
			SearchValue += " and upper(subject_name) like ? ";
		
	     }
		
		if (!fac_course_id.equals("0")) {
			SearchValue += " and fac_course_id = ? ";
		}
		


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String fac_course_id, String subject_name) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
			}
			
			if (subject_name != null && !subject_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+subject_name.toUpperCase()+"%");
			}
			
			if (!fac_course_id.equals("0") && fac_course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(fac_course_id));
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableSubjectDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String fac_course_id, String subject_name , String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,fac_course_id, subject_name);
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

			if(Search.equals("") && subject_name.equals("0") && status=="1") {
				q = "select fsubm.id,fcm.course_name ,fsubm.fac_course_id,fsubm.subject_name, fsubm.status from edu_faculty_subject_mstr fsubm \n"
						+ "inner join  edu_faculty_course_mstr fcm on fcm.id = fsubm.fac_course_id\n"
						+ "where fsubm.status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select fsubm.id,fcm.course_name , fsubm.fac_course_id,fsubm.subject_name,fsubm.status from edu_faculty_subject_mstr fsubm \n"
					+ "inner join  edu_faculty_course_mstr fcm on fcm.id = fsubm.fac_course_id\n"
					+ "where fsubm.status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,fac_course_id, subject_name);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDSubject' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='apcouAGE"+countFunction+"' value="+rs.getString("fac_course_id")+">"
								+"<input type='hidden' id='apsubAGE"+countFunction+"' value="+rs.getString("subject_name")+">"
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
	
	public EDU_FACULTY_SUBJECT_MSTR getSubjectByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_FACULTY_SUBJECT_MSTR updateid = (EDU_FACULTY_SUBJECT_MSTR) session.get(EDU_FACULTY_SUBJECT_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateFacultySubject(EDU_FACULTY_SUBJECT_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_FACULTY_SUBJECT_MSTR set fac_course_id=:fac_course_id,subject_name=:subject_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("fac_course_id", obj.getFac_course_id()).setParameter("subject_name", obj.getSubject_name()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
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








}