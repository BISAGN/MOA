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

import com.AyushEdu.Core_Directory.Document_Attachments_CD_DAO;
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

@Repository
public class Document_Attachments_Mstr_DAOImpl implements Document_Attachments_Mstr_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	 @Autowired
	 Document_Attachments_CD_DAO Doc_dirdao;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableDocumentDataTotalCount(String Search, String screen_module_id,String screen_submodule_id, String screen_id, String doc_name,String status) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,screen_module_id,screen_submodule_id,screen_id,doc_name,status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select fsubm.id,mm.module_name,submodule_name,fsubm.screen_submodule_id,fsubm.screen_module_id,fcm.screen_name,fsubm.screen_id,fsubm.doc_name,fsubm.status from edu_doc_attachments_mstr fsubm\n"
					+ "inner join tb_ldap_screen_master fcm on fcm.id = fsubm.screen_id\n"
					+ "inner join tb_ldap_submodule_master sbm on sbm.id=fsubm.screen_submodule_id\n"
					+ "inner join tb_ldap_module_master mm on mm.id = fsubm.screen_module_id\n"
					+ "where fsubm.status='"+ status +"' \n"
						+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,screen_module_id,screen_submodule_id,screen_id,doc_name);

			System.err.println("stmt"+stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String screen_module_id,String screen_submodule_id,String screen_id, String doc_name,String status) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(doc_name) like ? or  upper(module_name) like ? or upper(submodule_name) like ? or  upper(screen_name) like ?)";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( doc_name != null && !doc_name.equals("")) {
			SearchValue += " and upper(doc_name) like ? ";
		
	     }
		
		if (!screen_module_id.equals("0")) {
			SearchValue += " and fsubm.screen_module_id = ? ";
		}
		if (!screen_submodule_id.equals("0")) {
			SearchValue += " and fsubm.screen_submodule_id = ? ";
		}
		
		if (!screen_id.equals("0")) {
			SearchValue += " and fsubm.screen_id = ? ";
		}
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String screen_module_id,String screen_submodule_id, String screen_id, String doc_name) {
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
			}
			
			if (doc_name != null && !doc_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+doc_name.toUpperCase()+"%");
			}
			
			if (!screen_module_id.equals("0") && screen_module_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(screen_module_id));
			}
			if (!screen_submodule_id.equals("0") && screen_submodule_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(screen_submodule_id));
			}
			if (!screen_id.equals("0") && screen_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(screen_id));
			}
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableDocumentDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String screen_module_id,String screen_submodule_id, String screen_id, String doc_name, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, screen_module_id,screen_submodule_id, screen_id, doc_name,status);
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

			if(Search.equals("") && doc_name.equals("0") && status=="1") {
				q = "select fsubm.id,mm.module_name,submodule_name,fsubm.screen_submodule_id,fsubm.screen_module_id,fcm.screen_name,fsubm.screen_id,fsubm.doc_name,fsubm.status from edu_doc_attachments_mstr fsubm\n"
						+ "inner join tb_ldap_screen_master fcm on fcm.id = fsubm.screen_id\n"
						+ "inner join tb_ldap_submodule_master sbm on sbm.id=fsubm.screen_submodule_id\n"
						+ "inner join tb_ldap_module_master mm on mm.id = fsubm.screen_module_id\n"
						+ "where fsubm.status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select fsubm.id,mm.module_name,submodule_name,fsubm.screen_submodule_id,fsubm.screen_module_id,fcm.screen_name,fsubm.screen_id,fsubm.doc_name,fsubm.status from edu_doc_attachments_mstr fsubm\n"
					+ "inner join tb_ldap_screen_master fcm on fcm.id = fsubm.screen_id\n"
					+ "inner join tb_ldap_submodule_master sbm on sbm.id=fsubm.screen_submodule_id\n"
					+ "inner join tb_ldap_module_master mm on mm.id = fsubm.screen_module_id\n"
					
					+ "where fsubm.status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, screen_module_id,screen_submodule_id,screen_id,doc_name);
//			System.err.println("stmt--->"+stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDocument' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='apsmoAGE"+countFunction+"' value="+rs.getString("screen_module_id")+">"
								+"<input type='hidden' id='apsmosAGE"+countFunction+"' value="+rs.getString("screen_submodule_id")+">"
								+"<input type='hidden' id='apsnaAGE"+countFunction+"' value="+rs.getString("screen_id")+">"
								+"<input type='hidden' id='apdocAGE"+countFunction+"' value="+rs.getString("doc_name")+">"
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
	
	public EDU_DOC_ATTACHMENTS_MSTR getDocumentByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_DOC_ATTACHMENTS_MSTR updateid = (EDU_DOC_ATTACHMENTS_MSTR) session.get(EDU_DOC_ATTACHMENTS_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateDocument(EDU_DOC_ATTACHMENTS_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_DOC_ATTACHMENTS_MSTR set screen_module_id=:screen_module_id,screen_id=:screen_id,doc_name=:doc_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("screen_module_id", obj.getScreen_module_id()).setParameter("screen_id", obj.getScreen_id()).setParameter("doc_name", obj.getDoc_name()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
			Doc_dirdao.Update_Document_Attachments_Data(obj.getId(),obj.getScreen_module_id(), obj.getScreen_id(), obj.getDoc_name(),obj. getStatus(),obj.getModified_by(), obj.getModified_date());
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
	public ArrayList<ArrayList<String>> getsubmodule_name_FromScreen_Module(String screen_module_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,submodule_name from tb_ldap_submodule_master where module_id=? \n";
			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, Integer.parseInt(screen_module_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("submodule_name"));// 1

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
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	
	
	public ArrayList<ArrayList<String>> getScreen_NameFromScreen_SubModule(String screen_submodule_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,screen_name from tb_ldap_screen_master where screen_submodule_id=? \n";
			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, Integer.parseInt(screen_submodule_id));
			 System.err.println("============================screen_submodule_id====================="+screen_submodule_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("screen_name"));// 0

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
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	
	
/*	public ArrayList<ArrayList<String>> getScreen_SubModuleFromScreen_Module(String screen_module_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select sub_m.submodule_name\n"
					+ "from tb_ldap_screen_master sm\n"
					+ "inner join tb_ldap_module_master mm on mm.id=sm.screen_module_id\n"
					+ "inner join tb_ldap_submodule_master sub_m on sub_m.id=sm.screen_submodule_id\n"
					+ "where sm.screen_module_id=?\n";
			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, Integer.parseInt(screen_module_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
//				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("screen_name"));// 0

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
					e.printStackTrace();
				}
			}
		}
		return list;
	}

*/

}