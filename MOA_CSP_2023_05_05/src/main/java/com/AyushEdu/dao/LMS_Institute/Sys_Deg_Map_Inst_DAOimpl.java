package com.AyushEdu.dao.LMS_Institute;

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

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYS_DEG_MAP_INST;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;



@Repository
public class Sys_Deg_Map_Inst_DAOimpl implements Sys_Deg_Map_Inst_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
		
	@Override
	public List<Map<String, Object>> DataTablesystem_degree_mapping_instDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String institute_id, String degree_id,
			String status) {
		
		System.out.println("----------Inst-----03------"+institute_id);
		String SearchValue = GenerateQueryWhereClause_SQL(Search,institute_id,degree_id,status);
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
			if (Search.equals("") && degree_id.equals("0")  && status == "1") {
			q="select distinct ROW_NUMBER() OVER(order by sys.id ASC) as ser,sys.id as sys_id,sdm.id as id, sdm.degree_id, drg.degree_name,sys.system_name, sdm.status from edu_lms_system_degree_map_inst sdm\n"
					+ "inner join edu_lms_system_mstr sys on sys.id= sdm.system_id::int\n"
					+ "inner join edu_lms_degree_mstr drg on drg.id= sdm.degree_id::int\n"
					+ "where sdm.status='1' and sdm.institute_id= ? "+ SearchValue +" order by ser " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			}else {
				q="select distinct ROW_NUMBER() OVER(order by sys.id ASC) as ser,sys.id as sys_id,sdm.id as id, sdm.degree_id, drg.degree_name,sys.system_name, sdm.status from edu_lms_system_degree_map_inst sdm\n"
						+ "inner join edu_lms_system_mstr sys on sys.id= sdm.system_id::int\n"
						+ "inner join edu_lms_degree_mstr drg on drg.id= sdm.degree_id::int\n"
						+ "where sdm.status='" + status + "' and sdm.institute_id= ? "+ SearchValue +" order by ser " + orderType
							+ " limit " + pageL + " OFFSET " + startPage;
				
			}

			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,institute_id,degree_id,status);
			System.err.println("-------qry111 "+stmt);
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("-------qry111 "+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int countFunction=1;
			int countFunctionDelete=1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				
				String f = "";
				String action = "";
				String f1 = "";

				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("degree_id") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADD_Degree' value='ADD'  title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>"
						+"<input type='hidden' id='Id_degree"+countFunction+"' value="+rs.getString("id")+">"
						+"<input type='hidden' id='degreeName"+countFunction+"' value="+rs.getString("degree_id")+">"
						+"<input type='hidden' id='status"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm Delete_degree' value='Delete' title='Delete Data' >"
						+ "<i class='lni lni-trash-can'>"
						+"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a> </li>";
				

				ul += f ;
				ul+="</ul>";
				countFunction+=1;
				countFunctionDelete+=1;
				action = ul;
				
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
	public long DataTablesystem_degree_mapping_instDataTotalCount(String Search, String institute_id, String degree_id,
			String status) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,institute_id,degree_id,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		
		System.err.println("------insti "+institute_id);
		try {
			conn = dataSource.getConnection();
			
				q="select count(*) from (select distinct sys.id as sys_id,sdm.id as id, sdm.degree_id,drg.degree_name,sys.system_name,sdm.status \n"
						+ "	from edu_lms_system_degree_map_inst sdm\n"
						+ "	inner join edu_lms_degree_mstr drg on drg.id = sdm.degree_id::integer\n"
						+ "	inner join edu_lms_system_mstr sys on sys.id = sdm.system_id::integer\n"
						+ "	where sdm.status='1' and sdm.institute_id=? "+SearchValue+ ")a";
				
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id));
			stmt = setQueryWhereClause_SQL(stmt,Search,institute_id,degree_id,status);
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("-------qry "+stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search,String institute_id,String degree_id,String status) {
		String SearchValue = "";
		
		if (Search !=null && Search != "") { // for Input Filter
			SearchValue += "and ( upper(drg.degree_name) like ?  )";
			System.err.println("globalllll search" + SearchValue);
		}

		/// advance search
//		if (!system_id.trim().equals("0")) {
//			SearchValue += " and sdm.system_id = ? ";
//			System.err.println("parameter search" + SearchValue);
//		}
		

//		if (!degree_id.trim().equals("0")) {
//			SearchValue += " and sdm.degree_id = ? ";
//			System.err.println("parameter search" + SearchValue);
//		}
		
		if (!degree_id.equals("0") && degree_id != null) {
			System.err.println("-----++----degree_id"+degree_id);
			SearchValue += " and sdm.degree_id = ? ";
		}
		
		if (status != null) {
		SearchValue += " and sdm.status like ? ";
	    }
		
			
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String institute_id,String degree_id, String status) {
		int flag = 0;
		try {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(institute_id));
			if (Search !=null && Search != "") {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			
			if (!degree_id.equals("0") && degree_id != null) {

				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			
			if (status != null ) {
				flag += 1;
				stmt.setString(flag, status);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public EDU_LMS_SYS_DEG_MAP_INST get_system_degree_mapping_instByid(int id) {
		Session session = sessionFactory.openSession();
		
		 Transaction tx = session.beginTransaction();
		 Query q1 = session.createQuery("from EDU_LMS_SYS_DEG_MAP_INST where id=:system_id order by id desc");
			q1.setParameter("system_id", id);
			@SuppressWarnings("unchecked")
			List<EDU_LMS_SYS_DEG_MAP_INST> list = (List<EDU_LMS_SYS_DEG_MAP_INST>) q1.list();
		 EDU_LMS_SYS_DEG_MAP_INST updateid = (EDU_LMS_SYS_DEG_MAP_INST) session.get(EDU_LMS_SYS_DEG_MAP_INST.class, list.get(0).getId());
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
//	public EDU_LMS_SYS_DEG_MAP_INST get_system_degree_mapping_instByid(int id) {
//		Session session = sessionFactory.openSession();
//		 Transaction tx = session.beginTransaction();
//		 EDU_LMS_SYS_DEG_MAP_INST updateid = (EDU_LMS_SYS_DEG_MAP_INST) session.get(EDU_LMS_SYS_DEG_MAP_INST.class, id);
//         session.getTransaction().commit();
//         session.close();                
//        return updateid;
//  }
	
	public List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegrebysysidlistdata(String selval) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			
		//	Query q1 = sessionHQL.createQuery("from EDU_LMS_SYS_DEG_MAP_MASTER  where status='1' and system_name=:system_name");
			
			Query q1 = sessionHQL.createQuery("select dm.id, dm.degree_name   from EDU_LMS_SYS_DEG_MAP_MASTER sdm,EDU_LMS_DEGREE_MASTER dm where cast(dm.id as int)  = sdm.degree_name and sdm.status='1' and sdm.system_name=:system_name");
			q1.setParameter("system_name", Integer.parseInt(selval));
			@SuppressWarnings("unchecked")
			List<EDU_LMS_SYS_DEG_MAP_MASTER> list = (List<EDU_LMS_SYS_DEG_MAP_MASTER>) q1.list();
			tx.commit();
			return list;	
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@Override
	public ArrayList<ArrayList<String>> Getsytemid_fetch(String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select s.id,s.system_name from edu_lms_institute_reg ir\n"
						+ "inner join logininformation l on l.institute_id=ir.id\n"
						+ "inner join edu_lms_system_mstr s on s.id = ir.system_id\n"
						+ "where l.userid=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(userid));
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("system_name")); //0
				alist.add(rs.getString("id")); //1
				list.add(alist);
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
}
