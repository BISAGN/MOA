package com.AyushEdu.dao.Examination;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Examination.EDU_TB_NO_OF_ATTEMPT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

@Repository
public class No_Of_Attempt_Mstr_Daoimpl implements No_Of_Attempt_Mstr_Dao{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public EDU_TB_NO_OF_ATTEMPT_MSTR getsystemByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_TB_NO_OF_ATTEMPT_MSTR updateid = (EDU_TB_NO_OF_ATTEMPT_MSTR) session.get(EDU_TB_NO_OF_ATTEMPT_MSTR.class, id);
       session.getTransaction().commit();
       session.close();                
      return updateid;
	}
	
	@Override
	public EDU_LMS_SYSTEM_MSTR getsystemByid1(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_SYSTEM_MSTR updateid = (EDU_LMS_SYSTEM_MSTR) session.get(EDU_LMS_SYSTEM_MSTR.class, id);
      session.getTransaction().commit();
      session.close();                
     return updateid;
	}

	@Override
	public EDU_LMS_DEGREE_MASTER getsystemByid2(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LMS_DEGREE_MASTER updateid = (EDU_LMS_DEGREE_MASTER) session.get(EDU_LMS_DEGREE_MASTER.class, id);
      session.getTransaction().commit();
      session.close();                
     return updateid;
	}

	@Override
	public CC_TB_PROFESSIONAL_MSTR getsystemByid3(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 CC_TB_PROFESSIONAL_MSTR updateid = (CC_TB_PROFESSIONAL_MSTR) session.get(CC_TB_PROFESSIONAL_MSTR.class, id);
      session.getTransaction().commit();
      session.close();                
     return updateid;
	}

	
	@Override
	public List<Map<String, Object>> DataTablesysDegProCourselinkDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String no_of_attempt, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, system_id, degree_id, professional_id, no_of_attempt,
				status);

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
			
			if(search.equals("")  && status == "1") {
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,p.system_id,\n"
						+ "p.degree_id,p.professional_id,p.no_of_attempt,p.status\n"
						+ "from edu_exam_tb_no_of_attempt_mstr p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "where p.status=1 " + SearchValue + " ORDER BY id " + " limit "
						+ pageL + " OFFSET " + startPage;
			}
			else {
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,p.system_id,\n"
						+ "p.degree_id,p.professional_id,p.no_of_attempt,p.status\n"
						+ "from edu_exam_tb_no_of_attempt_mstr p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "where p.status= " +status+ SearchValue + " ORDER BY id " + " limit "
						+ pageL + " OFFSET " + startPage;	
			}

			
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, system_id, degree_id, professional_id, no_of_attempt, status);
			
			System.err.println("------------------"+stmt);
			
			
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
				
				//end
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDNo_Of_Attempt__Master' value='ADD' title='Edit Data'>"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data'>"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

				
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
		System.out.println("list-------"+list);
		return list;
		
	}

	@Override
	public long DataTablesysDegProCourselinkDataListTotalCount(String search, String system_id, String degree_id,
			String professional_id, String no_of_attempt, String status) {
		

		String SearchValue = GenerateQueryWhereClause_SQL(search, system_id, degree_id, professional_id, no_of_attempt,
				status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
//			Query for count page in data-table....by ruler
//			q = "select count(*) \n" + " from designation where id!=0 and status='1' " + SearchValue;
			if(search.equals("")  && status == "1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,p.system_id,\n"
					+ "p.degree_id,p.professional_id,p.no_of_attempt,p.status\n"
					+ "from edu_exam_tb_no_of_attempt_mstr p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "where p.status=1 \n"
					+ SearchValue + ") ab  ";
			}
			else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,p.system_id,\n"
						+ "p.degree_id,p.professional_id,p.no_of_attempt,p.status\n"
						+ "from edu_exam_tb_no_of_attempt_mstr p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "where p.status="+status+"\n"
						+ SearchValue + ") ab  ";
			}
			System.err.println("q----------" + q);
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, system_id, degree_id, professional_id, no_of_attempt, status);

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

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String no_of_attempt, String status) {
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
				
			}

			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!no_of_attempt.equals("") && no_of_attempt != null) {
				flag += 1;
				stmt.setString(flag, "%" + no_of_attempt.toUpperCase() + "%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}

	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String no_of_attempt, String status)  {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += "  and (  upper(s.system_name) like ? or upper(d.degree_name) like ? \n"
					+ " or upper(pm.professional) like ?  or p.no_of_attempt::character varying like ?) ";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
	
	
		if (!system_id.equals("0") && system_id != null) {
			SearchValue += " and p.system_id =? ";
		}
		if (!degree_id.equals("0") && degree_id != null) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!professional_id.equals("0") && professional_id != null) {
			SearchValue += " and p.professional_id =? ";
		}
		if (!no_of_attempt.equals("") && no_of_attempt != null) {
			SearchValue += " and p.no_of_attempt::character varying  like ? ";
		}


	   
		return SearchValue;
	}


}
