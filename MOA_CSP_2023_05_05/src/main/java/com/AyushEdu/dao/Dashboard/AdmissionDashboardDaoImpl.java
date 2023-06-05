package com.AyushEdu.dao.Dashboard;

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
public class AdmissionDashboardDaoImpl implements AdmissionDashboardDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState(String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";
		String dq = "";
		try {

			String[] name = type.split(",");

			if (name != null && name[0].trim().toUpperCase().equals("S")) {
				dq = "where d.state_id = ?";
			} else if (name != null && name[0].trim().toUpperCase().equals("U")) {

				dq = "where d.university_userid = ?";

			} else if (name != null && name[0].trim().toUpperCase().equals("I")) {

				dq = "where d.inid = ?";

			}

			conn = dataSource.getConnection();

			q = "select string_agg(totalseat,',') as totalseat,d.inid,d.state_id,d.college_abbr from (select concat('T','-',SUM(total_seats)) as totalseat,inid,state_id,college_abbr from(select SUM(totalseat) as total_seats,inid,i.state_id,i.college_abbr from co_instituteotherdetail n \n"
					+ "inner join edu_lms_institute_reg i on i.id = n.inid\n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where date_part('year', createdate) = date_part('year', current_date) and n.status='1' and n.status='1' \n"
					+ "group by 2,3,4) a group by 2,3,4\n" + "UNION ALL\n"
					+ "select concat('AV','-',SUM(available)),inid,state_id,college_abbr from(select sum(b.available_seats)-b.alloted_seat as available,inid,state_id,college_abbr from(select SUM(totalseat) as available_seats,inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = n.inid),i.college_abbr from co_instituteotherdetail n \n"
					+ "inner join edu_lms_institute_reg i on i.id = n.inid   \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where date_part('year', createdate) = date_part('year', current_date) and n.status='1' \n"
					+ "group by 2,3,4,5) b\n" + "group by b.alloted_seat,2,3,4) c group by 2,3,4\n" + "UNION ALL\n"
					+ "select concat('AL','-',SUM(alloted)),inid,state_id,college_abbr from (select b.alloted_seat as alloted,inid,state_id,college_abbr from(select SUM(totalseat) as available_seats,inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = n.inid),i.college_abbr from co_instituteotherdetail n \n"
					+ "inner join edu_lms_institute_reg i on i.id = n.inid and dashboard_status=1 \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where date_part('year', createdate) = date_part('year', current_date) and n.status='1' \n"
					+ "group by 2,3,4,5) b\n" + "group by b.alloted_seat,2,3,4) c group by 2,3,4) d " + dq
					+ " group by 2,3,4";

			PreparedStatement stmt = conn.prepareStatement(q);

			if (name != null && name[0].trim().toUpperCase().equals("S")) {
				stmt.setInt(1, Integer.parseInt(name[1]));

			} else if (name != null && name[0].trim().toUpperCase().equals("U")) {
				stmt.setInt(1, Integer.parseInt(name[1]));

			} else if (name != null && name[0].trim().toUpperCase().equals("I")) {
				stmt.setInt(1, Integer.parseInt(name[1]));

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("stmt---hhg----" + q);

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = ==oiuj===" + stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
				columns.put("x_axis", rs.getString("college_abbr"));

				for (int i = 0; i < rs.getString("totalseat").split(",").length; i++) {
					System.err.println(
							"================" + rs.getString("totalseat").split(",")[i].toString().split("-")[0]);

					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("T")) {
						columns.put("total", Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
					}
					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("AL")) {
						columns.put("allocated",
								Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
					}
					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("AV")) {
						columns.put("available",
								Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
					}
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
		System.out.println("list-------" + list);
		return list;
	}

	@Override
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState2(String role, String dq2, String type,String p_id,String data,String type2) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String query = "";
		String temp = "[";
		String dq = "";
		try {
			String[] name = null;
			if (!type.equals("")) {
				name = type.split(",");

				if (name != null && name[0].trim().toUpperCase().equals("S")) {
					dq = "and i.state_id = ?";
				} else if (name != null && name[0].trim().toUpperCase().equals("U")) {

					dq = "and i.university_id = ?";

				} else if (name != null && name[0].trim().toUpperCase().equals("I")) {

					dq = "and inid = ?";

				}

			}
			
			if (type2.equals("S")) {
				query = " where i.state_id = ? ";
			} else if (type2.equals("U")) {
				query = " where i.university_id = ? ";
			} else if (type2.equals("I")) {
				query = " where i.id = ? ";
			}

			conn = dataSource.getConnection();
//			dq2 = dq2.replaceAll("system_id", "a.system_id");
			dq2 = dq2.replaceAll("and", "where");
			p_id = p_id.replaceAll("where", "and");
			
			q = " select SUM(totalseat) as count ,s.system_name,system_id from (select total_seats as totalseat,inid,state_id,institute_name,university_id,university_name,system_id from\n"
					+ "(select sum(total_sanctioned_seat) as total_seats,i.id as inid ,i.state_id,u.university_name,i.institute_name,u.id as university_id,i.system_id from \n"
					+ "edu_lms_institute_reg i \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id "+query+p_id+" \n" + 
					"group by 2,3,4,5,6,7) a group by 1,2,3,4,5,6,7) e \n" + 
					"inner join edu_lms_system_mstr s on s.id = e.system_id "+dq2+" group by 2,3";

			PreparedStatement stmt = conn.prepareStatement(q);
			if (!type.equals("")) {

				if (name != null && name[0].trim().toUpperCase().equals("S")) {
					stmt.setInt(1, Integer.parseInt(name[1]));

				} else if (name != null && name[0].trim().toUpperCase().equals("U")) {
					stmt.setInt(1, Integer.parseInt(name[1]));

				} else if (name != null && name[0].trim().toUpperCase().equals("I")) {
					stmt.setInt(1, Integer.parseInt(name[1]));

				}
			}
			
			if (type2.equals("S")) {
				stmt.setInt(1, Integer.parseInt(data));

			} else if (type2.equals("U")) {
				stmt.setInt(1, Integer.parseInt(data));

			} else if (type2.equals("I")) {
				stmt.setInt(1, Integer.parseInt(data));

			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("stmt2-------" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = HH=====" + stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
				columns.put("degree_name", rs.getString("system_name"));
				columns.put("count", Integer.parseInt(rs.getString("count")));

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
		System.out.println("list-------" + list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAdmissionSeatDetails(String obj,String role,String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			String db = "where ";

			if (obj != null && !obj.equals("") && !obj.equals("0")) {
				for (int i = 0; i < obj.split(",").length; i++) {
					if (i == 0)
						db = db + " system_id = ?";
					else
						db = db + " or system_id = ?";

				}
			} else {
				db = "";
			}

			conn = dataSource.getConnection();

			if(id == null && id.equals("")) {
				q = "select q_type,sum(totalseat) as totalseat from (select 'total' as q_type,sum(total_seats) as totalseat,system_id  from (select sum(total_sanctioned_seat) as total_seats,i.system_id,i.id as inid,i.state_id,u.university_name,u.id as university_id \n"
						+ "from  edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id 	\n"
						+ "group by 2,3,4,5) p group by 3\n"
						+ "UNION ALL\n"
						+ "select 'available' as q_type,sum(available),system_id from(select SUM(totalseat)-b.alloted_seat as available,system_id from(select SUM(totalseat) as totalseat,inid,state_id,university_name,alloted_seat,system_id from (select total_sanctioned_seat as totalseat,i.id as inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and  m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id,u.id as university_id from \n"
						+ "	 edu_lms_institute_reg i    \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' \n"
						+ "group by 1,2,3,4,5,6) b group by 2,3,4,5,6) b\n"
						+ "group by b.alloted_seat,2) c\n"
						+ "group by 3\n"
						+ "UNION ALL\n"
						+ "select 'alloted' as q_type,sum(alloted),system_id from (select sum(b.alloted_seat) as alloted,system_id  from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id,u.id as university_id from \n"
						+ "   edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.dashboard_status=1  and i.status='1' \n"
						+ "group by 2,3,4,5,6) b\n"
						+ "group by b.alloted_seat,2) c\n"
						+ "group by 3\n"
						+ ") z "+db+" group by 1\n"
						+ "";
			
			}else {
				q = "select q_type,sum(totalseat) as totalseat from (select 'total' as q_type,sum(total_seats) as totalseat,system_id  from (select sum(total_sanctioned_seat) as total_seats,i.system_id,i.id as inid,i.state_id,u.university_name,u.id as university_id \n"
						+ "from  edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id  "+id+"	\n"
						+ "group by 2,3,4,5,6) p group by 3\n"
						+ "UNION ALL\n"
						+ "select 'available' as q_type,sum(available),system_id from(select SUM(totalseat)-b.alloted_seat as available,system_id from(select SUM(totalseat) as totalseat,inid,state_id,university_name,alloted_seat,system_id from (select total_sanctioned_seat as totalseat,i.id as inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and  m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id,u.id as university_id from \n"
						+ "	 edu_lms_institute_reg i    \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+id.replaceAll("where", "and")+" \n"
						+ "group by 1,2,3,4,5,6,7) b group by 2,3,4,5,6) b\n"
						+ "group by b.alloted_seat,2) c\n"
						+ "group by 3\n"
						+ "UNION ALL\n"
						+ "select 'alloted' as q_type,sum(alloted),system_id from (select sum(b.alloted_seat) as alloted,system_id  from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id,u.id as university_id from \n"
						+ "   edu_lms_institute_reg i  \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.dashboard_status=1   and i.status='1'  "+id.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4,5,6,7) b\n"
						+ "group by b.alloted_seat,2) c\n"
						+ "group by 3\n"
						+ " ) z "+db+" group by 1\n"
						+ "";
			}
			System.err.println("====================LMS_-----------------"+role);
			if (!role.contains("NCISM")) {
				q = q.replaceAll("edu_lms_student_details", "edu_lms_nch_student_details");
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			if (obj != null && !obj.equals("") && !obj.equals("0")) {
				for (int i = 0; i < obj.split(",").length; i++) {
					stmt.setInt((i + 1), Integer.parseInt(obj.split(",")[i]));
				}
			}
			System.err.println("qry =A =====" + stmt);
			if (!role.contains("NCISM")) {
				q = q.replaceAll("edu_lms_student_details", "edu_lms_nch_student_details");
			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
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

	@Override
	public List<Map<String, Object>> callDefaultSelectDashboard(String dq,String role,String user_id,String roleStaff_lvl) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

//			dq = dq.replaceAll("system_id", "n.system_id");
			
			
if(role.equals("NCISM") || role.equals("NCH") || role.equals("AYU_BOARD") || role.equals("UNI_BOARD")  || role.equals("HOM_BOARD")  || role.equals("SID_BOARD") || role.equals("SWR_BOARD")) {
				
	q = "select MAX(total),MAX(available),MAX(alloted),state_id from (select concat('T','-',SUM(total_seats)) as totalseat,inid,state_id,SUM(total_seats) as total,0  as available,0 as alloted from(select SUM(total_sanctioned_seat) as total_seats,i.id as inid,i.state_id  \n"
			+ "from edu_lms_institute_reg i  \n"
			+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
			+ "where i.status='1' " + dq.replaceAll("where", "and") + " \n"
			+ "group by 2,3) a group by 2,3\n" + "UNION ALL\n"
			+ "select concat('AV','-',SUM(available)),inid,state_id,0 as total,SUM(available) as available,0  as alloted from(select sum(b.available_seats)-b.alloted_seat as available,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
			+ "   (select count(m.id) as alloted_seat \n"
			+ "	from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) \n"
			+ "	and m.institude_userid = i.id) \n"
			+ "from edu_lms_institute_reg i  \n"
			+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
			+ "where  i.status='1' " + dq.replaceAll("where", "and") + " \n"
			+ "group by 2,3,4) b\n" + "group by b.alloted_seat,2,3) c group by 2,3\n" + "UNION ALL\n"
			+ "select concat('AL','-',SUM(alloted)),inid,state_id,0 as total,0  as available,SUM(alloted) as alloted from (select b.alloted_seat as alloted,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
			+ " (select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = i.id) \n"
			+ " from edu_lms_institute_reg i   \n"
			+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
			+ "where  dashboard_status=1   and i.status='1' " + dq.replaceAll("where", "and") + " \n"
			+ "group by 2,3,4) b\n"
			+ "group by b.alloted_seat,2,3) c group by 2,3) d where total > 0 OR available > 0 OR alloted > 0 group by 4 limit 1";
			
			}
			
			
			
			else if(role.equals("State_Council_NCISM") || role.equals("State_Council_NCH")) {
				q = "select MAX(total),MAX(available),MAX(alloted),d.state_id from (select concat('T','-',SUM(total_seats)) as totalseat,inid,state_id,SUM(total_seats) as total,0  as available,0 as alloted from(select SUM(total_sanctioned_seat) as total_seats,i.id as inid,i.state_id \n"
						+ "from edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3) a group by 2,3\n"
						+ "UNION ALL\n"
						+ "select concat('AV','-',SUM(available)),inid,state_id,0 as total,SUM(available) as available,0  as alloted from(select sum(b.available_seats)-b.alloted_seat as available,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
						+ "   (select count(m.id) as alloted_seat \n"
						+ "	from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) \n"
						+ "	and m.institude_userid = i.id) \n"
						+ "from edu_lms_institute_reg i    \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4) b\n"
						+ "group by b.alloted_seat,2,3) c group by 2,3\n"
						+ "UNION ALL\n"
						+ "select concat('AL','-',SUM(alloted)),inid,state_id,0 as total,0  as available,SUM(alloted) as alloted from (select b.alloted_seat as alloted,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
						+ " (select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = i.id) \n"
						+ " from edu_lms_institute_reg i   \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where  dashboard_status=1   and i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4) b\n"
						+ "group by b.alloted_seat,2,3) c group by 2,3) d \n"
						+ "inner join logininformation l on l.state_id = d.state_id\n"
						+ "where (total > 0 OR available > 0 OR alloted > 0) and l.userid = ? group by 4 limit 1";
			}
			else if(role.equals("University_NCISM") || role.equals("University_NCH")) {

				q = "select MAX(total),MAX(available),MAX(alloted),d.state_id from (select concat('T','-',SUM(total_seats)) as totalseat,inid,state_id,SUM(total_seats) as total,0  as available,0 as alloted,university_id from(select SUM(total_sanctioned_seat) as total_seats,i.id as inid,i.state_id, i.university_id \n"
						+ "from edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4) a group by 2,3,7\n"
						+ "UNION ALL\n"
						+ "select concat('AV','-',SUM(available)),inid,state_id,0 as total,SUM(available) as available,0  as alloted,university_id from(select sum(b.available_seats)-b.alloted_seat as available,inid,state_id,university_id  \n"
						+ "from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id , i.university_id,\n"
						+ "   (select count(m.id) as alloted_seat \n"
						+ "	from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) \n"
						+ "	and m.institude_userid = i.id) \n"
						+ "from edu_lms_institute_reg i    \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4,5) b\n"
						+ "group by b.alloted_seat,2,3,4) c group by 2,3,7\n"
						+ "UNION ALL\n"
						+ "select concat('AL','-',SUM(alloted)),inid,state_id,0 as total,0  as available,SUM(alloted) as alloted ,university_id \n"
						+ "from (select b.alloted_seat as alloted,inid,state_id,university_id \n"
						+ "from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id , i.university_id,\n"
						+ " (select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = i.id) \n"
						+ " from edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where dashboard_status=1   and i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4,5) b\n"
						+ "group by b.alloted_seat,2,3,4) c group by 2,3,4,7) d \n"
						+ "inner join logininformation l on l.university_id = d.university_id \n"
						+ "where (total > 0 OR available > 0 OR alloted > 0) and l.userid = ? group by 4 limit 1";
			}
			else if(role.equals("Institute_NCISM") || role.equals("Institute_NCH")){

				q = "select MAX(total),MAX(available),MAX(alloted),d.state_id from (select concat('T','-',SUM(total_seats)) as totalseat,inid,state_id,SUM(total_seats) as total,0  as available,0 as alloted from(select SUM(total_sanctioned_seat) as total_seats,i.id as inid,i.state_id \n"
						+ "from edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3) a group by 2,3\n"
						+ "UNION ALL\n"
						+ "select concat('AV','-',SUM(available)),inid,state_id,0 as total,SUM(available) as available,0  as alloted from(select sum(b.available_seats)-b.alloted_seat as available,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
						+ "   (select count(m.id) as alloted_seat \n"
						+ "	from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) \n"
						+ "	and m.institude_userid = i.id) \n"
						+ "from edu_lms_institute_reg i  \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4) b\n"
						+ "group by b.alloted_seat,2,3) c group by 2,3\n"
						+ "UNION ALL\n"
						+ "select concat('AL','-',SUM(alloted)),inid,state_id,0 as total,0  as available,SUM(alloted) as alloted from (select b.alloted_seat as alloted,inid,state_id from(select SUM(total_sanctioned_seat) as available_seats,i.id as inid,i.state_id,\n"
						+ " (select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.admission_date) = date_part('year', current_date) and m.institude_userid = i.id) \n"
						+ " from edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where dashboard_status=1   and i.status='1' "+dq.replaceAll("where", "and")+" \n"
						+ "group by 2,3,4) b\n"
						+ "group by b.alloted_seat,2,3) c group by 2,3) d \n"
						+ "inner join logininformation l on l.institute_id = d.inid\n"
						+ "where (total > 0 OR available > 0 OR alloted > 0) and l.userid = ? group by 4 limit 1";
			}
			
			

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("====================LMS_-----------------"+role);
			
			if(role.equals("State_Council_NCISM") || role.equals("State_Council_NCH")) {
				stmt.setInt(1, Integer.parseInt(user_id));
			}
			else if(role.equals("University_NCISM") || role.equals("University_NCH")) {
				stmt.setInt(1, Integer.parseInt(user_id));	
			}
			else if(role.equals("Institute_NCISM") || role.equals("Institute_NCH")){
				stmt.setInt(1, Integer.parseInt(user_id));
				
			}
			
			
			if (!roleStaff_lvl.contains("NCISM")) {
				q = q.replaceAll("edu_lms_student_details", "edu_lms_nch_student_details");
			}
			
			System.err.println("qry =GG =====" + stmt);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
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
//karan
	@Override
	public List<Map<String, Object>> AdmissionDashboardchartDataListByState3(String role, String obj,String p_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			String db = "where system_id in (";

			if (obj != null && !obj.equals("") && !obj.equals("0")) {
				for (int i = 0; i < obj.split(",").length; i++) {
					if (i == 0)
						db = db + ""+Integer.parseInt(obj.split(",")[i]);
					else
						db = db + ","+Integer.parseInt(obj.split(",")[i]);

                    
				}
				db+=" ) ";
			} else {
				db = "";
			}
			conn = dataSource.getConnection();
			if(p_id == null && p_id.equals("")) {

			q = "select totalseat,state_id from (select concat('T','-',SUM(total_seats)) as totalseat,state_id  from (select sum(total_sanctioned_seat) as total_seats,i.system_id,i.id as inid,i.state_id,u.university_name from \n"
					+ "								 edu_lms_institute_reg i \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id	 \n"
					+ "group by 2,3,4,5) p group by 2\n"
					+ "UNION ALL\n"
					+ "select concat('AV','-',SUM(available)),state_id from(select SUM(totalseat)-b.alloted_seat as available,system_id,state_id from(select SUM(totalseat) as totalseat,inid,state_id,university_name,alloted_seat,system_id from (select total_sanctioned_seat as totalseat,i.id inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id from \n"
					+ "	  edu_lms_institute_reg i  \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where i.status='1' \n"
					+ "group by 1,2,3,4,5,6) b group by 2,3,4,5,6) b \n"
					+ "group by b.alloted_seat,2,3) c\n"
					+ "group by 2\n"
					+ "UNION ALL\n"
					+ "select concat('AL','-',SUM(alloted)),state_id from (select sum(b.alloted_seat) as alloted,system_id,state_id  from(select SUM(totalseat) as available_seats,inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = n.inid  and m.verified_status='1'),u.university_name,n.system_id from co_instituteotherdetail n \n"
					+ "inner join edu_lms_institute_reg i on i.id = n.inid and dashboard_status=1 \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where date_part('year', createdate) = date_part('year', current_date) and n.status='1' \n"
					+ "group by 2,3,4,5,6) b\n"
					+ " "+db+" group by b.alloted_seat,2,3) c\n"
					+ "group by 2\n"
					+ " ) z  group by 1,2\n"
					+ "";
			}else {
				q = "select totalseat,state_id from (select concat('T','-',SUM(total_seats)) as totalseat,state_id  from (select sum(total_sanctioned_seat) as total_seats,i.system_id,i.id as inid,i.state_id,u.university_name from \n"
						+ "								 edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id	"+p_id+" "+db.replaceAll("where", "and").replaceAll("system_id","i.system_id")+"\n"
						+ "group by 2,3,4,5) p group by 2\n"
						+ "UNION ALL\n"
						+ "select concat('AV','-',SUM(available)),state_id from(select SUM(totalseat)-b.alloted_seat as available,system_id,state_id from(select SUM(totalseat) as totalseat,inid,state_id,university_name,alloted_seat,system_id from (select total_sanctioned_seat as totalseat,i.id inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id from \n"
						+ "	  edu_lms_institute_reg i  \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.status='1' "+p_id.replaceAll("where", "and")+" "+db.replaceAll("where", "and").replaceAll("system_id","i.system_id")+" \n"
						+ "group by 1,2,3,4,5,6) b group by 2,3,4,5,6) b \n"
						+ "group by b.alloted_seat,2,3) c\n"
						+ "group by 2\n"
						+ "UNION ALL\n"
						+ "select concat('AL','-',SUM(alloted)),state_id from (select sum(b.alloted_seat) as alloted,system_id,state_id  from(select SUM(total_sanctioned_seat) as available_seats,i.id inid,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id from \n"
						+ "	 edu_lms_institute_reg i \n"
						+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
						+ "where i.dashboard_status=1  and i.status='1' "+p_id.replaceAll("where", "and")+" "+db.replaceAll("where", "and").replaceAll("system_id","i.system_id")+"\n"
						+ "group by 2,3,4,5,6) b\n"
						+ " "+db+" group by b.alloted_seat,2,3) c\n"
						+ "group by 2\n"
						+ " ) z  group by 1,2\n";
			}
			

			System.err.println("====================statewise-----------------"+role+q);
			if (!role.contains("NCISM")) {
				q = q.replaceAll("edu_lms_student_details", "edu_lms_nch_student_details");
			}
			PreparedStatement stmt = conn.prepareStatement(q);

//			if (obj != null && !obj.equals("")) {
//				for (int i = 0; i < obj.split(",").length; i++) {
//					stmt.setInt((i + 1), Integer.parseInt(obj.split(",")[i]));
//				}
//			}
			System.out.println("ALL STATE WISE DATA" + stmt);
			System.err.println("qry =33333 =====" + stmt);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("q_type", rs.getString("totalseat"));
				columns.put("state_id", rs.getString("state_id"));

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
	public List<Map<String, Object>> AdmissionDashboardTableList(String role, String dq, String data, String type,String p_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String query = "";
		String queryGroup = "";
		try {
			
			
			// KAAM CHALU
			System.err.println("pp======================" + type);
			dq = dq.replaceAll("system_id", "i.system_id");

			if (type.equals("S")) {
				query = "where i.state_id = ?";
				queryGroup =",university_name,institute_name";
			} else if (type.equals("U")) {
				query = "where i.university_id = ?";
				queryGroup =",university_name,institute_name";
			} else if (type.equals("I")) {
				query = "where i.id = ?";
				queryGroup =",university_name,institute_name";
			}
			
			
			
			q = "select string_agg(totalseat,',') as totalseat,system_id,university_name,institute_name from ( \n"
					+ "select concat('T','-',SUM(total_seats)) as totalseat,sys_id as system_id,u_name as university_name,institute_name  \n"
					+ "from (select sum(total_sanctioned_seat) as total_seats,i.system_id as sys_id,i.id as inid,i.state_id,institute_name,university_id,u.university_name as u_name\n"
					+ "from   edu_lms_institute_reg i\n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id "+query+p_id.replaceAll("where", "and")+" "+dq.replaceAll("where", "and")+"\n"
					+ "group by 2,3,4,5,6,7) p group by 2,3,4\n"
					+ "UNION ALL\n"
					+ "select concat('AV','-',SUM(available)),system_id,u_name as university_name,institute_name   from(select SUM(totalseat)-b.alloted_seat as available,sys_id as system_id,b.inst_id as inid,state_id,institute_name,university_id,b.u_name\n"
					+ "from(select SUM(totalseat) as totalseat,b.inst_id as inst_id,state_id,university_name as u_name,alloted_seat,b.sys_id,institute_name,university_id from (select total_sanctioned_seat as totalseat,i.id as inst_id,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),i.system_id,u.university_name,i.system_id as sys_id,i.id as inid,institute_name,university_id,u.university_name as u_name from \n"
					+ "	   edu_lms_institute_reg i  \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where i.status='1' "+query.replaceAll("where", "and")+p_id.replaceAll("where", " and")+" "+dq.replaceAll("where", "and")+"\n"
					+ "group by 1,2,3,4,5,6,7,8,9,10) b group by 2,3,4,5,6,7,8) b \n"
					+ "group by b.alloted_seat,2,3,4,5,6,7) c\n"
					+ "group by 2,3,4\n"
					+ "UNION ALL\n"
					+ "select concat('AL','-',SUM(alloted)),system_id  ,university_name,institute_name  \n"
					+ "from (select sum(b.alloted_seat) as alloted,system_id,b.inst_id as inid,state_id,institute_name,university_id,b.u_name as university_name\n"
					+ "from(select SUM(total_sanctioned_seat) as available_seats,i.id as  inst_id,i.state_id,(select count(m.id) as alloted_seat from edu_lms_student_details m where date_part('year', m.created_date) = date_part('year', current_date) and m.institude_userid = i.id  and m.verified_status='1'),u.university_name,i.system_id,i.id as inid,institute_name,university_id,u.university_name as u_name from \n"
					+ "	 edu_lms_institute_reg i  \n"
					+ "inner join edu_lms_university_mstr u on u.id = i.university_id\n"
					+ "where i.dashboard_status=1  and i.status='1' "+query.replaceAll("where", "and")+p_id.replaceAll("where", " and")+dq.replaceAll("where", "and")+"\n"
					+ "group by 2,3,4,5,6,7,8,9) b\n"
					+ "   group by b.alloted_seat,2,3,4,5,6,7) c\n"
					+ "group by 2,3,4\n"
					+ " ) fnl \n"
					+ "group by system_id,university_name,institute_name"; 
			
			
			conn = dataSource.getConnection();

			if (!role.equals("NCISM")) {
				q = q.replaceAll("edu_lms_student_details", "edu_lms_nch_student_details");
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			if (type.equals("S")) {
				stmt.setInt(1, Integer.parseInt(data));
				stmt.setInt(2, Integer.parseInt(data));

				stmt.setInt(3, Integer.parseInt(data));

			} else if (type.equals("U")) {
				stmt.setInt(1, Integer.parseInt(data));
				stmt.setInt(2, Integer.parseInt(data));

				stmt.setInt(3, Integer.parseInt(data));

			} else if (type.equals("I")) {
				stmt.setInt(1, Integer.parseInt(data));
				stmt.setInt(2, Integer.parseInt(data));

				stmt.setInt(3, Integer.parseInt(data));

			}
			System.err.println("pp==========yuyuyhtgyh============" + stmt);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 0; i < rs.getString("totalseat").split(",").length; i++) {
					columns.put("ser", j);

					System.err.println(
							"================" + rs.getString("totalseat").split(",")[i].toString().split("-")[0]);

					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("T")) {
						columns.put("total", Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
					}
					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("AL")) {
						columns.put("allocated",
								Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
					}
					if (rs.getString("totalseat").split(",")[i].toString().split("-")[0].equals("AV")) {
						if(!rs.getString("totalseat").equals(""))
						{
							System.out.println("TOTAL SEATS===="+rs.getString("totalseat"));
							columns.put("available",Integer.parseInt(rs.getString("totalseat").split(",")[i].split("-")[1]));
						}
						
					}
				}

				if (type.equals("S")) {
//					columns.put("name", rs.getString("university_name"));
//					columns.put("name", rs.getString("university_name"));
					columns.put("name", rs.getString("institute_name"));
				} else if (type.equals("U")) {
					columns.put("name", rs.getString("institute_name"));
				} else if (type.equals("I")) {
					columns.put("name", rs.getString("institute_name"));
				}

				list.add(columns);
				j++;

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
	public List<Map<String, Object>> getDashboardProfileByrole(String userid,String role) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			if(role.equals("NCISM") || role.equals("NCH") || role.equals("AYU_BOARD") || role.equals("UNI_BOARD")  || role.equals("HOM_BOARD")  || role.equals("SID_BOARD") || role.equals("SWR_BOARD")) {
				
				q = "select concat('S-0',',','U-0',',','I-0') as type,'0' as pre_data ,'0' as x_id,'0' userid from logininformation where userid = ?\n"
						+ "";
			
			}
			
			
			
			else if(role.equals("State_Council_NCISM") || role.equals("State_Council_NCH")) {
				q = "select concat('S-',x_id,',','U-0',',','I-0') as type,* \n"
						+ " from (select 'S'as pre_data,e.state_id as x_id,l.userid from logininformation l\n"
						+ "inner join edu_lms_state_mstr e on e.state_id = l.state_id \n"
						+ "inner join userroleinformation u on u.user_id = l.userid\n"
						+ "inner join roleinformation r on r.role_id = u.role_id\n"
						+ "where UPPER(r.role) like '%STATE_COUNCIL_NCISM%' or UPPER(r.role) like '%STATE_COUNCIL_NCH%') d where userid = ? ";
			}
			else if(role.equals("University_NCISM") || role.equals("University_NCH")) {

				q = "select concat('S-',state_id,',','U-',x_id,',','I-0') as type,* \n"
						+ " from (select 'U' as pre_data,e.id,l.userid,e.state_id,e.id as x_id from logininformation l\n"
						+ "inner join edu_lms_university_mstr e on e.id = l.university_id \n"
						+ "inner join userroleinformation u on u.user_id = l.userid\n"
						+ "inner join roleinformation r on r.role_id = u.role_id\n"
						+ "where UPPER(r.role) like '%UNIVERSITY_NCISM%' or UPPER(r.role) like '%UNIVERSITY_NCH%' ) d where userid = ?";
			}
			else if(role.equals("Institute_NCISM") || role.equals("Institute_NCH")){

				q = "select concat('S-',state_id,',','U-',university_id,',','I-',x_id) as type,* \n"
						+ " from (select 'I'as pre_data,e.state_id,l.userid,e.university_id,e.id as x_id from logininformation l\n"
						+ "inner join edu_lms_institute_reg e on e.id = l.institute_id  \n"
						+ "inner join userroleinformation u on u.user_id = l.userid\n"
						+ "inner join roleinformation r on r.role_id = u.role_id\n"
						+ "where UPPER(r.role) like '%INSTITUTE_NCISM%' or UPPER(r.role) like '%INSTITUTE_NCH%' ) d where userid = ? ";
			}

//			q = "select case when pre_data = 'I' or pre_data = 'U' or pre_data = 'S' then concat((select concat('S-',state_id::character varying) from logininformation b where b.userid = d.userid),',',(select concat('U-',university_id::character varying) from logininformation b where b.userid = d.userid),',',(select concat('I-',institute_id::character varying) from logininformation b where b.userid = d.userid)) else '0' end as type,* \n"
//					+ "\n" + "\n"
//					+ "from (select pre_data,state_id as id,userid from (select 'S'as pre_data,e.state_id,l.userid from logininformation l\n"
//					+ "inner join edu_lms_state_mstr e on e.state_id = l.state_id \n"
//					+ "inner join userroleinformation u on u.user_id = l.userid\n"
//					+ "inner join roleinformation r on r.role_id = u.role_id\n"
//					+ "where UPPER(r.role) like '%STATE COUNCIL%'\n" + "UNION ALL\n"
//					+ "select 'U' as pre_data,e.id,l.userid from logininformation l\n"
//					+ "inner join edu_lms_university_mstr e on e.id = l.university_id \n"
//					+ "inner join userroleinformation u on u.user_id = l.userid\n"
//					+ "inner join roleinformation r on r.role_id = u.role_id\n"
//					+ "where UPPER(r.role) like '%UNIVERSITY_NCISM%' or UPPER(r.role) like '%UNIVERSITY_NCH%'\n"
//					+ "UNION ALL\n" + "select 'I'as pre_data,e.state_id,l.userid from logininformation l\n"
//					+ "inner join edu_lms_institute_reg e on e.id = l.institute_id \n"
//					+ "inner join userroleinformation u on u.user_id = l.userid\n"
//					+ "inner join roleinformation r on r.role_id = u.role_id\n"
//					+ "where UPPER(r.role) like '%INSTITUTE_NCISM%' or UPPER(r.role) like '%INSTITUTE_NCH%') a  where userid = ? ) d";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("qry =22 =====" + stmt);

			stmt.setInt(1, Integer.parseInt(userid));

			System.out.println("stmt-------" + stmt);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry =22 =====" + stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				if (rs.getString("type").split(",")[0].split("-")[0].equals("S") && rs.getString("pre_data").equals("S")) {
					columns.put("pre_data", "S");
					columns.put("type_s", rs.getString("type").split(",")[0].split("-")[1] );

				} if (rs.getString("type").split(",")[1].split("-")[0].equals("U") && rs.getString("pre_data").equals("U")) {
					columns.put("pre_data", "U");
					columns.put("type_u", rs.getString("type").split(",")[1].split("-")[1]);
					columns.put("pre_data2", "S");
					columns.put("type_us", rs.getString("type").split(",")[0].split("-")[1] );

				}
				
				
				if (rs.getString("type").split(",")[2].split("-")[0].equals("I") && rs.getString("pre_data").equals("I")) {
					columns.put("pre_data", "I");
					columns.put("type_i", rs.getString("type").split(",")[2].split("-")[1]);
					columns.put("pre_data2", "S");
					columns.put("type_is", rs.getString("type").split(",")[0].split("-")[1] );
					columns.put("pre_data3", "U");
					columns.put("type_iu", rs.getString("type").split(",")[1].split("-")[1]);
				}
				
				if(role.equals("NCISM") || role.equals("NCH") || role.equals("AYU_BOARD") || role.equals("UNI_BOARD")  || role.equals("HOM_BOARD")  || role.equals("SID_BOARD") || role.equals("SWR_BOARD")) {

				columns.put("pre_data", "0");
				columns.put("pre_data2", "0");
				}
				columns.put("id", rs.getString("x_id"));
//				columns.put("type", rs.getString("type"));
//				columns.put("pre_data", rs.getString("pre_data"));
				
				if(columns.get("pre_data") == null || columns.get("pre_data").equals("")) {
					columns.put("pre_data", "0");

				}
				if(columns.get("pre_data2") == null || columns.get("pre_data2").equals("")) {
					columns.put("pre_data2", "0");

				}
				
				list.add(columns);

			}
			System.err.println("-----------list--------"+list);

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
	public String getNonSelectedList(String role,String userid) {
		Connection conn = null;
		String q = "";
		String query = "";
		String data ="";
		String dynamic="";

		try {
			
			
			q = "select institute_id,university_id,state_id,system_id from logininformation  l \n"
					+ "inner join userroleinformation u on u.user_id = l.userid\n"
					+ "inner join roleinformation r on r.role_id = u.role_id where userid = ?"; 
			
			
			conn = dataSource.getConnection();

			
			if(role.equals("Institute_NCISM") || role.equals("Institute_NCH")) {
				dynamic += "where i.id = ";
			}
			else if (role.equals("University_NCISM") || role.equals("University_NCH")) {
				dynamic += "where university_id = ";
			}
			else if (role.equals("State_Council_NCISM") || role.equals("State_Council_NCH")) {
				dynamic += "where i.state_id = ";

			}
			else if (role.equals("AYU_BOARD") || role.equals("UNI_BOARD")  || role.equals("HOM_BOARD")  || role.equals("SID_BOARD") || role.equals("SWR_BOARD")) {
				dynamic += "where i.system_id = ";

			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1,Integer.parseInt(userid));

			System.err.println("=========HJ======"+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				if(role.equals("Institute_NCISM") || role.equals("Institute_NCH")) {
					data += rs.getString("institute_id");
				}
				else if (role.equals("University_NCISM") || role.equals("University_NCH")) {
					data += rs.getString("university_id");
				}
				else if (role.equals("State_Council_NCISM") || role.equals("State_Council_NCH")) {
					data += rs.getString("state_id");

				}
				else if (role.equals("AYU_BOARD") || role.equals("UNI_BOARD")  || role.equals("HOM_BOARD")  || role.equals("SID_BOARD") || role.equals("SWR_BOARD")) {
					data += rs.getString("system_id");

				}
			}
			dynamic += data;
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
		return dynamic;
	}
	
	//Riddhi
	@Override
	public List<Map<String, Object>> getAdmissionSeatDetails_Faculty(String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

				q = "select * from (select count(*) as no_clg from edu_lms_institute_reg  where  status='1' and system_id=45\n"
						+ ") a,(select count(*) as fac_reg from edu_lms_faculty_nch  where  status='1' and role='Faculty_NCH'\n"
						+ ") b,(select count(*) as final_submitted from tb_nch_add_teacher_details td \n"
						+ "	 inner join edu_lms_institute_reg ir on ir.id=td.institute_id where  td.status='1' and ir.system_id=45\n"
						+ ") c,(select count(*) as tech_code from tb_nch_add_teacher_details td2\n"
						+ "	 inner join edu_lms_institute_reg ir2 on ir2.id=td2.institute_id where td2.principal_status='1' and ir2.system_id=45\n"
						+ ")d ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);

			System.err.println("FACULTY DASHBOARD STMT---->"+stmt);
			
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
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

}
