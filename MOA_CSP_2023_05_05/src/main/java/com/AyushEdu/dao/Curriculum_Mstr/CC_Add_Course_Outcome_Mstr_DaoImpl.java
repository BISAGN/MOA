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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;

@Repository
public class CC_Add_Course_Outcome_Mstr_DaoImpl implements CC_Add_course_Outcome_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public List<Map<String, Object>> DataTableCourse_OutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_id,String course_outcome,String co_code, String status,String system_id ,String degree_id,
			String professional_id,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_id,course_outcome,co_code,system_id,degree_id, professional_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			 String sd = "";
				
				if(role.contains("NCISM")) {
					sd = " and aco.system_id != 45 ";
				}
				if(role.contains("NCH")) {
					sd = " and aco.system_id = 45 ";
				}
			
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && course_outcome.equals("0") &&  course_id.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by aco.course_outcome ) as ser,aco.id,aco.course_id,"
						+ "aco.course_outcome,cm.course_name,aco.co_code,aco.status,aco.system_id,s.system_name,d.degree_name,pm.professional,aco.degree_id,aco.professional_id \n"
						+ "from edu_cc_tb_add_course_outcome_mstr aco\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=aco.course_id \n"
						+ "inner join edu_lms_system_mstr s on s.id= aco.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = aco.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = aco.professional_id\n"
						+ "where aco.status='1' \n" + sd
					    + SearchValue + " ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			    q = "select distinct ROW_NUMBER() OVER(order by aco.course_outcome ) as ser,aco.id,aco.course_id,aco.course_outcome,cm.course_name,aco.co_code,"
			    		+ "aco.status,aco.system_id,s.system_name,d.degree_name,pm.professional,aco.degree_id,aco.professional_id \n"
					+ "from edu_cc_tb_add_course_outcome_mstr aco\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=aco.course_id \n"
					+ "inner join edu_lms_system_mstr s on s.id= aco.system_id \n" 
					+ "inner join edu_lms_degree_mstr d on d.id = aco.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = aco.professional_id\n"
					+ "where aco.status='"+ status +"'" + sd + SearchValue +" ORDER BY "+orderColunm+" " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, course_id,course_outcome,co_code,system_id,degree_id, professional_id);
			System.err.println("----------oycom----------"+stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDCourse_Outcome' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdCO"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approsAGE"+countFunction+"' value='"+rs.getString("system_id")+"' >"
								+"<input type='hidden' id='hprodegree"+countFunction+"' value='"+rs.getString("degree_id")+"' >"
								+"<input type='hidden' id='hidprofessional"+countFunction+"' value='"+rs.getString("professional_id")+"' >"
								+"<input type='hidden' id='apcoofCO"+countFunction+"' value='"+rs.getString("course_id")+"'>"
								+"<input type='hidden' id='approfCO"+countFunction+"' value='"+rs.getString("course_outcome")+"'>"
								+"<input type='hidden' id='apcodeCO"+countFunction+"' value='"+rs.getString("co_code")+"'>"
								+"<input type='hidden' id='apstatusCO"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";
				
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
	public long DataTableCourse_OutcomeDataTotalCount(String Search,String course_id, String course_outcome,String co_code, String status,String system_id ,String degree_id,
			String professional_id,String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,course_id, course_outcome,co_code,system_id,degree_id, professional_id);
		
		int total = 0;
		String q = null;
		
		String sd = "";
		if(role.contains("NCISM")) {
			sd = " and aco.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and aco.system_id = 45 ";
		}
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(Search.equals("") && course_outcome.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by aco.course_outcome ) as ser,aco.id,aco.course_id,aco.course_outcome,"
					+ "cm.course_name,aco.co_code,aco.status,aco.system_id,s.system_name,d.degree_name,pm.professional,aco.degree_id,aco.professional_id \n"
					+ "from edu_cc_tb_add_course_outcome_mstr aco\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=aco.course_id \n"
					+ "inner join edu_lms_system_mstr s on s.id= aco.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = aco.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = aco.professional_id\n"
					+ "where aco.status='1' \n" + sd
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by aco.course_outcome ) as ser,aco.id,aco.course_id,"
						+ "aco.course_outcome,cm.course_name,aco.co_code,aco.status,aco.system_id,s.system_name,d.degree_name,pm.professional,aco.degree_id,aco.professional_id \n"
						+ "from edu_cc_tb_add_course_outcome_mstr aco\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=aco.course_id \n"
						+ "inner join edu_lms_system_mstr s on s.id= aco.system_id \n"
						+ "inner join edu_lms_degree_mstr d on d.id = aco.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = aco.professional_id\n"
						+ "where aco.status='"+ status +"' \n" + sd
						+ SearchValue + ") ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, course_id,course_outcome,co_code,system_id,degree_id, professional_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String course_id,String course_outcome,String co_code,String system_id,String degree_id,
			String professional_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(cm.course_name) like ? or upper(aco.course_outcome) like ? or"
					+ " upper(aco.co_code) like ? or upper(s.system_name) like ? or upper(d.degree_name) like ? or upper(pm.professional) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search  or cast(aco.course_outcome as text) like ?
		if (!course_id.trim().equals("0"))  {
			SearchValue += " and aco.course_id = ? ";
	     }
		if( course_outcome != null && !course_outcome.equals("")) {
			SearchValue += " and upper(aco.course_outcome) like ? ";
	     }
		if( co_code != null && !co_code.equals("")) {
			SearchValue += " and upper(aco.co_code) like ? ";
	     }
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and (aco.system_id ) = ? ";
		
	     }
		if( degree_id != null && !degree_id.equals("0")) {
			SearchValue += " and (aco.degree_id) = ? ";
		
	     }
		if( professional_id != null && !professional_id.equals("0")) {
			SearchValue += " and (aco.professional_id) = ? ";
		
	     }
		//if (course_outcome != null && !course_outcome.equals("")) {
			//SearchValue += " and cast (aco.course_outcome as text) like ? ";
		//}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course_id,String course_outcome,String co_code,String system_id,String degree_id,
			String professional_id) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
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
			
			if (course_outcome != null && !course_outcome.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+course_outcome.toUpperCase()+"%");
			}
			if (! course_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(course_id));
			}
			if (co_code != null && !co_code.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+co_code.toUpperCase()+"%");
			}
			if (system_id != null && !system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (degree_id != null && !degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			if (professional_id != null && !professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional_id));
			}
		} catch (Exception e) {
		}
		return stmt;
	}

	@Override
	public String updateCourse_Outcome(CC_TB_ADD_COURSE_OUTCOME_MSTR obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_ADD_COURSE_OUTCOME_MSTR set system_id=:system_id,degree_id=:degree_id ,professional_id=:professional_id,course_id=:course_id, course_outcome=:course_outcome,co_code=:co_code,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", (obj. getSystem_id()))
					.setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", obj.getProfessional_id())
					.setParameter("course_id", obj.getCourse_id())
					.setParameter("course_outcome", obj.getCourse_outcome())
					.setParameter("co_code", obj.getCo_code())
					.setParameter("status", (obj. getStatus()))
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
