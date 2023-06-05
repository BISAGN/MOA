package com.AyushEdu.dao.helpdeskINQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Common_Controller.CommonController;

@Repository
public class HD_DB_HelpDesKDaoImpl implements HD_DB_HelpDesKDao {


	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Map<String, Object>> getFilterdb_helpdesklist(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String inq_no, String inq_cat, String per_state, String des,String module,String sub_module,String screen_name, String status, HttpSession session) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,    inq_no,  inq_cat,  per_state,  des, module, sub_module, screen_name,  status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String assRole = "";
		String assRolewhr = "";
		try {

			String rolename = session.getAttribute("rolename").toString();
			String userId = session.getAttribute("userId").toString();
			
//			if (rolename.equals("NCISM_ADMIN")) {
//				assRolewhr = "  and a.system_id in (44,46,47,48) ";
//			}
//			else if (rolename.equals("NCH_ADMIN")) {
//				assRolewhr = " and a.system_id in (45) ";
//			}
//			else {
//				assRole = " inner join hd_inq_link_role_mstr lm on lm.inq_no = a.inq_no \n";
//				assRolewhr = " and lm.user_id1 in ("+userId+") ";
//			}
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			q = "select ROW_NUMBER() OVER(order by a.id ASC) as ser,c.state_name,b.inq_cat, a.inq_no,a.description,a.email,a.id,d.module_name as module,e.submodule_name as sub_module,f.screen_name, CASE  WHEN a.status=1 THEN 'Under Process' when a.status=2 THEN 'Close' Else '--' END as status  from hd_inq_in_helpdesk_p a \n"
					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
					+ "left join edu_lms_state_mstr c on c.state_id=a.state \n"
					+ "inner join tb_ldap_module_master d on d.id=a.module\n"
					+ "inner join tb_ldap_submodule_master e on e.id=a.sub_module\n"
					+ "inner join tb_ldap_screen_master f on f.id=a.screen_name where a.id!=0 "+assRolewhr
					+ SearchValue + " ORDER BY a.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt, Search,inq_no,inq_cat,per_state,des,module,sub_module,screen_name,status);
		
            ResultSet rs = stmt.executeQuery();
            
			
	
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
	
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
	
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";	
				f = "<li><a data-bs-toggle=\"modal\"\n"
						+ "	data-bs-target=\"#Inq_Case_model\" class='main-btn active-btn btn-hover btn-sm ADDInquiryFrom' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" 
						+"<input type='hidden' id='hid_inq_rep"+countFunction+"' value="+rs.getString("id")+">"
						+"</i></a> </li>";
				
				if (rolename.equals("NCISM_ADMIN") || rolename.equals("NCH_ADMIN")) {
					
					String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Asign Detail ?') ){redirectData('"+ rs.getString("id") + "') }else{ return false;}\"";
					 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm redirectOnclick' +ADD1+ value='ADD' title='Redirect Data' >"+ //id='id_add_attHospital1'
							 "<input type='hidden' id='hid_asg_rep"+countFunctionDelete+"' value="+rs.getString("inq_no")+"><i class='lni lni-save'></i></a> </li>";
	
				}
				 
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


	public long getTotaldb_helpdeskCount(String Search,  String inq_no, String inq_cat, String per_state, String des,String module,String sub_module,String screen_name, String status,HttpSession sessionUserId) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,   inq_no,  inq_cat,  per_state,  des, module, sub_module, screen_name,  status);
		int total = 0;
		String q = null;
		String assRole = "";
		String assRolewhr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String rolename = sessionUserId.getAttribute("rolename").toString();
			String userId = sessionUserId.getAttribute("userId").toString();
			
//			if (rolename.equals("NCISM_ADMIN")) {
//				assRolewhr = "  and a.system_id in (44,46,47,48) ";
//			}
//			else if (rolename.equals("NCH_ADMIN")) {
//				assRolewhr = " and a.system_id in (45) ";
//			}
//			else {
//				assRole = " inner join hd_inq_link_role_mstr lm on lm.inq_no = a.inq_no \n";
//				assRolewhr = " and lm.user_id1 in ("+userId+") ";
//			}

			q = "select count(*) from (select ROW_NUMBER() OVER(order by a.id ASC) as ser,c.state_name,b.inq_cat, a.inq_no,a.description,a.email,a.id,d.module_name as module,e.submodule_name as sub_module,f.screen_name, CASE  WHEN a.status=1 THEN 'Under Process' when a.status=2 THEN 'Close' Else '--' END as status  from hd_inq_in_helpdesk_p a \n"
					+ "inner join hd_inq_cat_mstr b on b.id=a.inq_cat\n"
					+ "left join edu_lms_state_mstr c on c.state_id=a.state \n"
					+ "inner join tb_ldap_module_master d on d.id=a.module\n"
					+ "inner join tb_ldap_submodule_master e on e.id=a.sub_module\n"
					+ "inner join tb_ldap_screen_master f on f.id=a.screen_name where a.id!=0 "+assRolewhr
					+ SearchValue + ")a";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, Search,   inq_no,  inq_cat,  per_state,  des, module, sub_module, screen_name,  status);
			System.err.println("stmt=========dsdrertre============="+stmt);
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

//	private String GenerateQueryWhereClause_SQL(String search, String inq_no, String per_state, String des) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String inq_no, String inq_cat, String per_state, String des,String module,String sub_module,String screen_name, String status) {

		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter 
			SearchValue += " and (c.state_name like ? or upper(b.inq_cat) like ? or upper(a.inq_no) like  ? "
					+ "or upper(a.description) like ? or upper(a.email) like ? or upper(d.module_name) like ? "
					+ "or upper(e.submodule_name) like ? or upper(f.screen_name) like ? ) ";
		}

//		if (!inq_no.equals("") && inq_no != null) {
//			SearchValue += " and upper(a.inq_no) like ? ";
//		}
//
//		if (!inq_cat.equals("0")) {
//			SearchValue += " and a.inq_cat = ? ";
//
//		}
//		
//		if (!per_state.equals("0")) {
//			SearchValue += " and a.state = ? ";
//
//		}
//		if (!des.equals("") && des != null) {
//			SearchValue += " and upper(a.description) like ? ";
//
//		}
//		if (!module.equals("0")) {
//			SearchValue += " a.module = ? ";
//
//		}
//		if (!sub_module.equals("0")) {
//			SearchValue += " a.sub_module = ? ";
//
//		}
//		if (!screen_name.equals("0")) {
//			SearchValue += " a.screen_name = ? ";
//
//		}
//		if (!status.equals("0")) {
//			SearchValue += " and a.status = ? ";
//
//		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String inq_no, String inq_cat, String per_state, String des,String module,String sub_module,String screen_name, String status ) {

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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				/*
				 * flag += 1; stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				 */
			}

//			if (!inq_no.equals("") && inq_no != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + inq_no.toUpperCase() + "%");
//			}
//			if (!inq_cat.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(inq_cat));
//			}
//			if (!per_state.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(per_state));
//			}
//			if (!des.equals("") && des != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + des.toUpperCase() + "%");
//		}
//			if (!module.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(module));
//			}
//			if (!sub_module.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(sub_module));
//			}
//			if (!screen_name.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(screen_name));
//			}
//			if (!status.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(status));
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	
	
	
}
