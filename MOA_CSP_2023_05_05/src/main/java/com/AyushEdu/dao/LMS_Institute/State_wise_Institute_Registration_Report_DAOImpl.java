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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class State_wise_Institute_Registration_Report_DAOImpl implements State_wise_Institute_Registration_Report_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTable_state_wise_institute_regDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String institute_id, String system_id,
			String course_id,int co,int st) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_id, system_id, course_id,co,st);
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
			
			q = " select distinct ir.id,sm.system_name,ir.institute_name,STRING_AGG(cm.course_name, ', ') as course_name,isl.institute_id as institute_id\n"
					+ "from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_institute_link isl on isl.institute_id::integer=ir.id\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=isl.system_id::integer\n"
					+ "inner join edu_lms_system_ele_course_link csl on csl.system_id = isl.system_id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name=csl.elec_course_id::integer\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "where ir.state_id::integer=? \n"
					+ "group by  ir.id,ir.institute_name,sm.system_name,ir.institute_name,isl.institute_id" + SearchValue + " order by ir.id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;

//			q = " select distinct ir.id,sm.system_name, ir.institute_name,\n" + "cm.course_name\n"
//					+ "from edu_lms_institute_reg ir\n"
//					+ "inner join edu_lms_institute_link isl on isl.institute_id::integer=ir.id\n"
//					+ "inner join edu_lms_system_mstr sm on sm.id=isl.system_id::integer\n"
//					+ "inner join edu_lms_system_ele_course_link csl on csl.system_id = isl.system_id\n"
//					+ "inner join edu_lms_ele_course_mstr cm on cm.id=csl.elec_course_id::integer\n"
//					+ "where ir.state_id::integer=? " + SearchValue + " order by ir.id " + orderType + " limit "
//					+ pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,st);
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id, system_id, course_id,co,st);
			//System.err.println("querry=====list==========" + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String popup = "";
				String action = "";
//				String f1 = "";
//				String f2 = "";
//				String f3 = "";

//				String ADD = "onclick=\" getSystem_Institute_course('"+ rs.getString("id")+"','" + rs.getString("institute_id") + "','"+rs.getString("course_name")+"' )\"";
//				popup = "<i class='fa fa-info-circle ' data-toggle='modal' " + ADD + " data-target='#System_Institute_Course_Modal' title='View Data'></i>";
				
				String ADD = "onclick=\" Pop_Up_History('"+ rs.getString("id")+"','" + rs.getString("institute_id") + "','"+rs.getString("course_name")+"' )\"";
				popup = "<i class='fa fa-info-circle '  " + ADD + " title='View Data'></i>";


//
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
//
//				String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f2 = "<i class='fa fa-check '  " + ADD2 + " title='Approve Data'></i>";
//				
//				String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f3 = "<i class='fa fa-close '  " + ADD3 + " title='Reject Data'></i>";

				action = popup ;
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
	public long DataTable_state_wise_institute_regDataTotalCount(String Search, String institute_id, String system_id,
			String course_id,int co,int st) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_id, system_id, course_id,co, st);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n" + " from ( select distinct ir.id,sm.system_name,ir.institute_name,STRING_AGG(cm.course_name, ', ') as course_name,isl.institute_id as institute_id\n"
					+ "					from edu_lms_institute_reg ir\n"
					+ "					inner join edu_lms_institute_link isl on isl.institute_id::integer=ir.id\n"
					+ "					inner join edu_lms_system_mstr sm on sm.id=isl.system_id::integer\n"
					+ "					inner join edu_lms_system_ele_course_link csl on csl.system_id = isl.system_id\n"
					+ "					inner join edu_lms_ele_course_mstr ecm on ecm.course_name=csl.elec_course_id::integer\n"
					+ "					inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "					where ir.state_id::integer=? \n"
					+ "					group by  ir.id,ir.institute_name,sm.system_name,ir.institute_name,isl.institute_id ) a " + SearchValue;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,st);
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id, system_id, course_id,co,st);
		

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
	public String GenerateQueryWhereClause_SQL(String Search, String institute_id, String system_id, String course_id,int co,int st) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(ir.institute_name) like ?"
					+ "or upper(cm.course_name) like ? )";
		}

		/// advance search
		
		if (!system_id.trim().equals("0")) {
			SearchValue += " and isl.system_id like ? ";

		}
		if (!institute_id.trim().equals("0")) {
			SearchValue += " and isl.institute_id like ? ";

		}
		if (!course_id.trim().equals("0")) {
			SearchValue += " and csl.elec_course_id like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String institute_id,
			String system_id, String course_id,int co,int st) {
		// System.err.println("policy cat ---3-"+policycategory);
		int flag = 1;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setString(flag, "%" + system_id + "%");
			}
			if (!institute_id.equals("0") && institute_id != null) {
				flag += 1;
				stmt.setString(flag, "%" + institute_id + "%");
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setString(flag, "%" + course_id + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	//shivali
		public ArrayList<ArrayList<String>> getSystem_Institute_course_list(int id, String course_name,String institute_id,int co,int st) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		
//				 q = "select distinct eclp.ele_course_id,ecm.course_name,count(li.login_name) as total_stud_enroll from edu_lms_system_ele_course_sets_link_parent eclp\n"
//				 		+ "inner join logininformation li on li.userid = eclp.user_id::integer\n"
//				 		+ "inner join edu_lms_ele_course_mstr ecm on ecm.id =eclp.ele_course_id::integer where eclp.ele_course_id =? group by 1,2";
				
				
//				q="select distinct eclp.ele_course_id,ecm.course_name,count(li.login_name) as total_stud_enroll \n"
//						+ "from edu_lms_system_ele_course_sets_link_parent eclp\n"
//						+ "inner join logininformation li on li.userid = eclp.user_id::integer\n"
//						+ "inner join edu_lms_ele_course_mstr ecm on ecm.id =eclp.ele_course_id::integer\n"
//						+ "left join edu_lms_institute_link isl on isl.institute_id=eclp.system_id\n"
//						+ "left join \n"
//						+ "(select cm.course_name,isl.id\n"
//						+ "from edu_lms_institute_reg ir\n"
//						+ "inner join edu_lms_institute_link isl on isl.institute_id::integer=ir.id\n"
//						+ "inner join edu_lms_system_mstr sm on sm.id=isl.system_id::integer\n"
//						+ "inner join edu_lms_system_ele_course_link csl on csl.system_id = isl.system_id\n"
//						+ "inner join edu_lms_ele_course_mstr cm on cm.id=csl.elec_course_id::integer\n"
//						+ "where ir.state_id::integer = ? and isl.institute_id =?\n"
//						+ ")n on n.id=isl.id\n"
//						+ "group by 1,2\n";
				
				q="select distinct cm.course_name,count(li.login_name) as total_stud_enroll  from edu_lms_institute_link eil \n"
						+ "inner join edu_lms_system_ele_course_sets_link_parent  lp on lp.system_id=eil.system_id \n"
						+ "inner join logininformation li on li.userid = lp.user_id::integer \n"
						+ "inner join edu_lms_system_ele_course_link scl on scl.system_id=eil.system_id \n"
						+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name =scl.elec_course_id::integer \n"
						+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
						+ "where eil.institute_id=? \n"
						+ "group by 1";
					
				    stmt = conn.prepareStatement(q);
//					stmt.setInt(1, st);
					stmt.setString(1, institute_id);
					ResultSet rs = stmt.executeQuery();  
				    System.err.println("-stmt---SHIVALI--"+stmt);

					while (rs.next()) {				
					  
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("course_name")); //0
					alist.add(rs.getString("total_stud_enroll"));//1
				
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
