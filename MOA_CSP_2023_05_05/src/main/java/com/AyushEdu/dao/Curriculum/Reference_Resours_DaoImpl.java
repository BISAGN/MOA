package com.AyushEdu.dao.Curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum.EDU_CC_LINK_REFERENCE_RESOURCES_MSTR;

@Repository
public class Reference_Resours_DaoImpl implements Reference_Resours_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> ReferenceResoursesDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String status,String resource,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id,professional_id,course_id,status,resource);
		
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
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
			if(Search.equals("") && system_id.equals("0") && status=="1") {
			q=" select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.resource,p.status\n"
					+ "from edu_cc_link_reference_resourses_mstr p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "where p.status=1 "+ sd + SearchValue +"ORDER BY "+orderColunm+" " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;
			}else {
				q=" select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.resource,p.status\n"
						+ "from edu_cc_link_reference_resourses_mstr p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
						+ "where p.status='"+ status +"'" + sd + SearchValue +"ORDER BY "+orderColunm+" " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt----main---------------------------->  "+stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id,status,resource);
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction=1;
			int countFunctionDelete=1;
			int countview=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}


				String f = "";
				String action = "";
				String f1 = "";
//				 <i class='lni lni-trash-can'></i></a> </li>";

				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
 								+"<input type='hidden' id='hidsystem"+countFunction+"' value='"+rs.getString("system_id")+"'>"
 								+"<input type='hidden' id='hidprofessional"+countFunction+"' value='"+rs.getString("degree_id")+"'>"
 								+"<input type='hidden' id='hprodegree"+countFunction+"' value='"+rs.getString("professional_id")+"'>"
 								+"<input type='hidden' id='hcourse"+countFunction+"' value='"+rs.getString("course_id")+"'>"
 								+"<input type='hidden' id='dpresource"+countFunction+"' value='"+rs.getString("resource")+"'></i></a> </li>"
 								+"<input type='hidden' id='hstatus"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";

				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
					 "<i class='lni lni-trash-can'>" 
						 + "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+">"
						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a> </li>";
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("system_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("degree_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("professional_id")+">"
//						 		+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("course_id")+">";

	
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
	public long DataTableReferenceResourcesTotalCount(String Search, String system_id, String degree_id,String professional_id,String course_id,String status,String resource,String role) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, degree_id,professional_id,course_id,status,resource);
		
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			
			if(Search.equals("") && system_id.equals("0") && status=="1") {
			q="select count(*) from (select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.resource,p.status \n"
					+ "	from edu_cc_link_reference_resourses_mstr p \n"
					+ "	inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "	inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "	inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "	inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "	where p.status=1 "
					+ sd + SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.resource,p.status \n"
						+ "	from edu_cc_link_reference_resourses_mstr p \n"
						+ "	inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "	inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "	inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "	inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
						+ "	where p.status='"+ status +"' "
						+ sd + SearchValue + ") ab  ";
			}
			
//			q="select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,p.system_id,p.degree_id,p.professional_id,p.course_id,p.status\n"
//					+ "from edu_cc_link_system_degree_professional_course p\n"
//					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
//					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
//					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
//					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
//					+ "where p.status=1 "
//					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt =setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id,status,resource);
//			System.err.println("STMT--------count-----------"+stmt);
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
	
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String status,String resource) {
		
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ " or upper(pm.professional) like ?  or upper(cc.course_name) like ? or upper(p.resource) like ?) ";
		}
		/// advance search
		if (!system_id.equals("0") && system_id != null ) {
			SearchValue += " and p.system_id =? ";
		}
		if (!degree_id.equals("0") && degree_id != null ) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!professional_id.equals("0") && professional_id != null ) {
			SearchValue += " and p.professional_id =? ";
		}
		if (!course_id.equals("0") && course_id != null) {
			SearchValue += " and p.course_id =? ";
		}
		if( resource != null && !resource.equals("")) {
			SearchValue += " and upper(resource) like ? ";
		
	     }
//		if (!resource.equals(" ") && resource != null) {
//			SearchValue += " and upper(p.resource) like ? ";
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id, 
			String degree_id,String professional_id,String course_id,String status,String resource) {
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
			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_id));
			}
			if (resource != null && !resource.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+resource.toUpperCase()+"%");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	
	public EDU_CC_LINK_REFERENCE_RESOURCES_MSTR getreferenceresoursekdata(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_CC_LINK_REFERENCE_RESOURCES_MSTR updateid = (EDU_CC_LINK_REFERENCE_RESOURCES_MSTR) session.get(EDU_CC_LINK_REFERENCE_RESOURCES_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	
	
	public String updateReferenceResouces(EDU_CC_LINK_REFERENCE_RESOURCES_MSTR td){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_CC_LINK_REFERENCE_RESOURCES_MSTR set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,status=:status,resource=:resource,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", td.getSystem_id()).setParameter("professional_id", td.getProfessional_id()).setParameter("degree_id", td.getDegree_id()).setParameter("course_id", td.getCourse_id()).setParameter("resource", td.getResource()).setParameter("status", (td. getStatus()))
					.setParameter("modified_by", td.getModified_by()).setParameter("modified_date", td.getModified_date())
					.setParameter("id", td.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
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
