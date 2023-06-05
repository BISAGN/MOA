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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;



@Repository
public class Student_Course_DaoImpl implements Student_Course_Dao {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTablestudent_regDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String username, String system_name, String setname,
			String course_name,String app_status) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, username,system_name,setname,course_name,app_status);
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
			
			q="SELECT sp.id,lm.login_name,scm.system_name,cm.course_name,lsm.setname,sp.status FROM public.edu_lms_system_ele_course_sets_link_parent sp\n"
					+ "inner join edu_lms_system_ele_course_sets_link_child sch on sch.p_id= sp.id::text\n"
					+ "inner join edu_lms_system_mstr scm on scm.id=sp.system_id::int\n"
					+ "inner join logininformation lm on lm.userid=sp.user_id::int\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name=sp.ele_course_id::int\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=sch.set_id::int where sp.id !=0 "+ SearchValue +" order by id " + orderType
					+ 	 " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, username,system_name,setname,course_name,app_status);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			int j = startPage;
			while (rs.next()) {
				
				ArrayList<String> alist = new ArrayList<String>();
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String action1 = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";


				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "','" + rs.getString("id") + "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f2 = "<i class='fa fa-check '  " + ADD2 + " title='Approve Data'></i>";
				
				String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f3 = "<i class='fa fa-close '  " + ADD3 + " title='Reject Data'></i>";
				
			
				action = f + " " + f1 + " " + " " + f2 + " " + f3;
				action1 = f + " " + f1 ;
				
				
				//columns.put("action", action1);
				
//				
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("login_name"));//1
				alist.add(rs.getString("system_name"));//2
				alist.add(rs.getString("setname"));//3
				alist.add(rs.getString("course_name"));//4
				
				
				
				
				String app_status1=rs.getString("status");
				
				if(app_status1.equals("0")) {

					columns.put("action", action);
					}
				
				if(app_status1.equals("1")) {

					columns.put("action", action1);
					
					}
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

	
	public long DataTablestudent_regDataTotalCount(String Search, String username, String system_name, String setname,
			String course_name,String app_status) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,username,system_name,setname,course_name,app_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			
			q="select count(*) from (SELECT sp.id,lm.login_name,scm.system_name,cm.course_name,lsm.setname,sp.status FROM public.edu_lms_system_ele_course_sets_link_parent sp 	"
					+ "inner join edu_lms_system_ele_course_sets_link_child sch on sch.p_id= sp.id::text"
					+ " inner join edu_lms_system_mstr scm on scm.id=sp.system_id::int "
					+ "inner join logininformation lm on lm.userid=sp.user_id::int "
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name=sp.ele_course_id::int	"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=sch.set_id::int where sp.id !=0 "+ SearchValue +")ab";
			
				
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,username,system_name,setname,course_name,app_status);

			
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
	public String GenerateQueryWhereClause_SQL(String Search, String username, String system_name, String setname,String course_name,String status) {

		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(lm.login_name) like ? or upper(scm.system_name) like ?"
					+ "or upper(cm.course_name) like ? or upper(lsm.setname) like ?)";
					
		}

		/// advance search

		if (!username.trim().equals("")) {
			SearchValue += " and upper(lm.login_name) like ? ";

		}
		if (!system_name.trim().equals("0")) {
			SearchValue += " and sp.system_id :: int = ? ";

		}
		if (!setname.trim().equals("0")) {
			SearchValue += " and sch.set_id :: int  = ? ";

		}
		if (!course_name.trim().equals("0")) {
			SearchValue += " and sp.ele_course_id :: int = ? ";
			
		}
		
		
		if (status.equals("0")) {
			SearchValue += " and sp.status :: int = ? ";
		}
		if(status.equals("1")) {
			SearchValue += " and sp.status :: int = ? ";
		}
		if(status.equals("2")) {
			SearchValue += " and sp.status :: int = ? ";
		}

		return SearchValue;
	}
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String username, String system_name, String setname,String course_name,String status) {
		
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

			if (!username.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + username.toUpperCase() + "%");
			}
			if (!system_name.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_name));
				
			}
			if (!setname.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(setname));
				
			}
			if (!course_name.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name));
			}
			
			if (status.equals("0")) {
				flag += 1;
				stmt.setInt(flag,0);
			}
			 if(status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			 if(status.equals("2")) {
				flag += 1;
				stmt.setInt(flag,2);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}


	 @Override
	 public ArrayList<ArrayList<String>> studentSelectElective(String user_id,String system,String Course,HttpSession session) {
			
			String  role = session.getAttribute("role").toString();
			String ls="";

			
			String pageL = "";
			System.out.println("Course "+Course);
			if(!Course.equals("")) {
				ls+="and (";
				for(int i=0;i<Course.split(",").length;i++) {
					if(i==Course.split(",").length-1) {
						ls+="p.ele_course_id=? ";
					}else {
						ls+=" p.ele_course_id=? or ";
					}
				}
				ls+=")";
			}
			

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSource.getConnection();

				q = "select p.ele_course_id ||','|| set_id as set from edu_lms_system_ele_course_sets_link_child c\n"
						+ "inner join edu_lms_system_ele_course_sets_link_parent p on c.p_id=p.id::text where c.user_id=? and c.system_id=? "+ls;


				PreparedStatement stmt = conn.prepareStatement(q);
				int j=1;
				stmt.setString(j, user_id);
				j++;
				stmt.setString(j, system);
				j++;
				System.out.println("stmt2 "+stmt);
				if(!Course.equals("")) {
					
					for(int i=0;i<Course.split(",").length;i++) {
						stmt.setString(j, Course.split(",")[i]);
						j++;
					}
					
				}
				
				System.out.println("stmt "+stmt);
				ResultSet rs = stmt.executeQuery();
				

				int i = 1;
				while (rs.next()) {
					// alist....arrange icon column wise......by ruler
					ArrayList<String> alist = new ArrayList<String>();


					
					
					alist.add(rs.getString("set")); //0
					
					
					
					

					
					
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
	
	 public String getMaxAID() {

			Connection conn = null;
			String reg_no = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = "";
				query = "select max(substring(ayush_id,3,10))::int from edu_lms_student_details";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					reg_no = rs.getString("max");

				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		System.err.println("MAXXAID----------"+reg_no);
			return reg_no;
		}
	
	
	
	
	
	
	
	

}
