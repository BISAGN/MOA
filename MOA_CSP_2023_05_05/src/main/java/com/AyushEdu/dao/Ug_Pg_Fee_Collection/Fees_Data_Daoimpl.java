package com.AyushEdu.dao.Ug_Pg_Fee_Collection;

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
public class Fees_Data_Daoimpl implements Fees_Data_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public ArrayList<ArrayList<String>> getSystemListFromInstituteExam(String institute_id1, String userId,
			String role) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM")) {
				qry = "where lo.userid=?";
				q = "select distinct sm.id,sm.system_name from edu_lms_system_mstr sm \n"
						+ "inner join edu_lms_system_degree_map_inst dmi on dmi.system_id=sm.id\n"
						+ "inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;
			}
			if (role.trim().equals("Institute_NCH") || role.trim().contains("Faculty_NCH")) {
				qry = "where lo.userid=?";
				q = "select distinct sm.id,sm.system_name from edu_lms_system_mstr sm \n"
						+ "inner join edu_lms_system_degree_map_inst dmi on dmi.system_id=sm.id\n"
						+ "inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM")) {
				stmt.setObject(1, Integer.parseInt(userId));
			}
			if (role.trim().equals("Institute_NCH")) {
				stmt.setObject(1, Integer.parseInt(userId));
			}
			System.err.println("-------getSystemListFromInstituteExam--------" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("system_name"));// 1

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

	@Override
	public List<Map<String, Object>> DataTable_CMEAttend_DataList(String userId, String system_id, String degree_name,
			String prof_id, String inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!inst_id.equals("")) {
				qry += " sd.institude_userid=? ";
			}
			if (!system_id.equals("0")) {
				qry += " and sd.system=? ";
			}

			if (!degree_name.equals("0")) {
				qry += " and sd.degree = ? ";
			}

			if (!prof_id.equals("0")) {
				qry += " and cast(sd.semester as integer) = ? ";
			}
//				q = "select DISTINCT sd.id,sd.name,sd.ayush_id,TO_CHAR(dob,'DD-MON-YYYY') as dob,sd.mobile_no,lm.category,rpd.pers_category,rpd.id,lsd.feesvalue,\n"
//						+ "lsd.catid ,dm.degree_name,sd.degree,sd.system,sd.semester,ir.no_of_part \n"
//						+ "from edu_lms_student_details sd \n"
//						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid \n"
//						+ "inner join edu_lms_degree_mstr dm on sd.degree=dm.id \n"
//						+ "inner join logininformation l on l.aadhar_no=sd.aadhar_card\n"
//						+ "inner join edu_ncism_reg_gradu_personal_dtls rpd on rpd.pers_aadhar_no=sd.aadhar_card\n"
//						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid\n"
//						+ "inner join co_feescategorytype sm on sm.ftid=lsd.feesid\n"
//						+ "inner join tb_lms_category_mstr lm on lm.id=rpd.pers_category::int\n"
//						+ "where sd.id != 0 "+qry+" limit 10 OFFSET 0";

			if (role.contains("NCISM")) {

				q = "select sd.id,sd.name,sd.ayush_id,TO_CHAR(sd.dob,'DD-MON-YYYY') as dob,sd.mobile_no,lm.category,\n"
						+ "sd.system,rpd.pers_category,ir.no_of_part,sum(lsd.feesvalue) as feesvalue\n"
						+ "from edu_lms_student_details sd\n"
						+ "inner join edu_ncism_reg_gradu_personal_dtls rpd on rpd.pers_email=sd.email\n"
						+ "inner join tb_lms_category_mstr lm on lm.id=rpd.pers_category::int\n"
						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=rpd.pers_category::int\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry
						+ " and sd.verified_status=0 " + " group by 1,2,3,4,5,6,7,8,9 ";
			}
			if (role.contains("NCH")) {
				q = "select sd.id,sd.name,sd.ayush_id,TO_CHAR(sd.dob,'DD-MON-YYYY') as dob,sd.mobile_no,lm.category,\n"
						+ "sd.system,rpd.pers_category,ir.no_of_part,sum(lsd.feesvalue) as feesvalue\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join edu_reg_gradu_personal_dtls rpd on rpd.pers_email=sd.email\n"
						+ "inner join tb_lms_category_mstr lm on lm.id=rpd.pers_category::int\n"
						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=rpd.pers_category::int\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry
						+ " and sd.verified_status=0 " + " group by 1,2,3,4,5,6,7,8,9 ";
			}

			stmt = conn.prepareStatement(q);

			int j = 0;

			if (!inst_id.equals("")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(inst_id));
			}
			if (!system_id.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(system_id));
			}
			if (!degree_name.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(degree_name));
			}
			if (!prof_id.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(prof_id));
			}
			System.err.println("\n\n*****stmt====wwwww===========" + stmt + "\n \n");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			int countFunction = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

//				long isc = checkisdataSaved(rs.getString("id"),system_id,degree_name,prof_id);
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
	public List<Map<String, Object>> DataTable_CMEAttend_DataList_count(String userId, String system_id,
			String degree_name, String prof_id, String inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!inst_id.equals("")) {
				qry += " sd.institude_userid=? ";
			}
			if (!system_id.equals("0")) {
				qry += " and sd.system=? ";
			}
			if (!degree_name.equals("0")) {
				qry += " and sd.degree = ? ";
			}
			if (!prof_id.equals("0")) {
				qry += " and cast(sd.semester as integer) = ? ";
			}
			if (role.contains("NCISM")) {
				q = "select sd.id,fd.paid_fees\n" + "from edu_lms_student_details sd\n"
						+ "inner join edu_institute_fees_data fd on fd.stud_id=sd.id\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry
						+ " and sd.verified_status=1 " + " group by 1,2 ";
			}
			if (role.contains("NCH")) {
				q = "select sd.id,fd.paid_fees\n" + "from edu_lms_nch_student_details sd\n"
						+ "inner join edu_institute_fees_data fd on fd.stud_id=sd.id\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry
						+ " and sd.verified_status=1 " + " group by 1,2 ";
			}
			stmt = conn.prepareStatement(q);
			int j = 0;

			if (!inst_id.equals("")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(inst_id));
			}
			if (!system_id.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(system_id));
			}
			if (!degree_name.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(degree_name));
			}
			if (!prof_id.equals("0")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(prof_id));
			}
			System.err.println("\n\n*****stmt===============" + stmt + "\n \n");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
//				long isc = checkisdataSaved(rs.getString("id"),system_id,degree_name,prof_id);
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

	public long checkisdataSaved(String stu_id, String system, String degree, String proff) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = " select count(*) from edu_lms_set_stu_fees_parent where stu_id=? and system=? and degree=? and prof_id=? ";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(stu_id));
			stmt.setInt(2, Integer.parseInt(system));
			stmt.setInt(3, Integer.parseInt(degree));
			stmt.setInt(4, Integer.parseInt(proff));
			System.err.println("\n\n checkisdataSaved--->>>" + stmt);

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
}
