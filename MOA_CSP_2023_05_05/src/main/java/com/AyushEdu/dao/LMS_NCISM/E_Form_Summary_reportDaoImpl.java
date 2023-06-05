package com.AyushEdu.dao.LMS_NCISM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class E_Form_Summary_reportDaoImpl implements E_Form_Summary_reportDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public ArrayList<ArrayList<String>> getEformSummaryReport(String institute1, String category1, String authority1,
			String quota1,String role,String roleStaff_lvl) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			System.out.println("category1 "+category1);
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String in = "";
			String stu = "";
			String fildname="";
			if (!institute1.equals("0")) {
				in += " and state_id= ?";
			}
			if (!category1.equals("0")) {
				stu+=" and category= ?";
			}
			if (!authority1.equals("0")) {
				stu+=" and counc_auth=? ";
			}
			if (!quota1.equals("0")) {
				stu+=" and quota=? ";
			}
			if(role.equals("AYU_BOARD")) {
				in += " and system_id= 44";
			}
			if(role.equals("UNI_BOARD")) {
				in += " and system_id= 48";
			}
			if(role.equals("SID_BOARD")) {
				in += " and system_id= 46";
			}
			if(role.equals("SWR_BOARD")) {
				in += " and system_id= 47";
			}
			if(role.equals("HOM_BOARD")) {
				in += " and system_id= 45";
			}
			if(roleStaff_lvl.equals("NCH")) {
				fildname="edu_lms_nch_student_details";
			}else {
				fildname="edu_lms_student_details";
			}
 
			q = "select system_name,m.* from (select system_id,COALESCE(count(distinct inst.id),0) as college_total,COALESCE(sum(total_sanctioned_seat),0) as totalseat,\n"
					+ "COALESCE(sum(st.allot),0) as allot,\n"
					+ "COALESCE(sum(total_sanctioned_seat),0)-COALESCE(sum(st.allot),0) as vacant from edu_lms_institute_reg inst  \n"
					+ "\n"
					+ " left join (select institude_userid,count(id) as allot from $fildname where id!=0 "+stu+" group by institude_userid) st on inst.id=st.institude_userid \n"
					+ " where id!=0 "+in+" group by system_id) m inner join edu_lms_system_mstr sm on sm.id=m.system_id and created_role=? ";
			q = q.replace("$fildname", fildname);
			stmt = conn.prepareStatement(q);
			int count=1;
			
			if (!category1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(category1));
				count++;
			}
			if (!authority1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(authority1));
				count++;
			}
			if (!quota1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(quota1));
				count++;
			}
			if (!institute1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(institute1));
				count++;
			}
			stmt.setString(count,roleStaff_lvl);
			
			System.out.println("\n\nstmt summary "+stmt+"\n\n");
			ResultSet rs = stmt.executeQuery();
			int serno = 1;
			int college=0;
			int totalseat=0;
			int allot=0;
			int vacant=0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(serno));// 0
				list.add(rs.getString("system_name"));// 0
				list.add(rs.getString("college_total"));// 1
				college+=Integer.parseInt(rs.getString("college_total"));
				list.add(rs.getString("totalseat"));// 2
				totalseat+=Integer.parseInt(rs.getString("totalseat"));
				list.add(rs.getString("allot"));// 3
				allot+=Integer.parseInt(rs.getString("allot"));
				list.add(rs.getString("vacant"));// 4
				vacant+=Integer.parseInt(rs.getString("vacant"));
				alist.add(list);
				serno++;
			}
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.valueOf(serno));// 0
			list.add("Total");// 0
			list.add(college+"");// 1
			list.add(totalseat+"");// 2
			list.add(allot+"");// 3
			list.add(vacant+"");// 4
			alist.add(list);
			
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
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getEformSummaryVacantReport(String institute1, String category1, String authority1,
			String quota1,String role,String roleStaff_lvl) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			System.out.println("category1 "+category1);
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String in = "";
			String stu = "";
			String fildname="";
			if (!institute1.equals("0")) {
				in += " and state_id= ?";
			}
			if (!category1.equals("0")) {
				stu+=" and category= ?";
			}
			if (!authority1.equals("0")) {
				stu+=" and counc_auth=? ";
			}
			if (!quota1.equals("0")) {
				stu+=" and quota=? ";
			}
			if(role.equals("AYU_BOARD")) {
				in += " and system_id= 44";
			}
			if(role.equals("UNI_BOARD")) {
				in += " and system_id= 48";
			}
			if(role.equals("SID_BOARD")) {
				in += " and system_id= 46";
			}
			if(role.equals("SWR_BOARD")) {
				in += " and system_id= 47";
			}
			if(role.equals("HOM_BOARD")) {
				in += " and system_id= 45";
			}
			if(roleStaff_lvl.equals("NCH")) {
				fildname="edu_lms_nch_student_details";
			}else {
				fildname="edu_lms_student_details";
			}
			q = "  select system_name,count(inst.id) as total_college,count(inst.id) filter(where total_sanctioned_seat=allot) as fully,\n"
					+ " count(inst.id) filter(where 0=allot or allot is null) as vaccant0,\n"
					+ " count(inst.id) filter(where total_sanctioned_seat!=allot)as partialVaccant\n"
					+ " from (select system_id ,id,total_sanctioned_seat from edu_lms_institute_reg  where id!=0 "+in+"  group by id) inst\n"
					+ "  left join \n"
					+ "  (select system,institude_userid,count(id) as allot from $fildname \n"
					+ "where id!=0 "+stu+"  group by system,institude_userid) st on st.institude_userid=inst.id\n"
					+ " inner join edu_lms_system_mstr sm on sm.id=inst.system_id and created_role=?\n"
					+ "group by system_name\n"
					+ "";
			q = q.replace("$fildname", fildname);
			stmt = conn.prepareStatement(q);
			int count=1;
			if (!institute1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(institute1));
				count++;
			}
			if (!category1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(category1));
				count++;
			}
			if (!authority1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(authority1));
				count++;
			}
			if (!quota1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(quota1));
				count++;
			}
			stmt.setString(count,roleStaff_lvl);
			System.out.println("stmt summary "+stmt);
			ResultSet rs = stmt.executeQuery();
			int serno = 1;
			int college=0;
			int totalseat=0;
			int allot=0;
			int vacant=0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(serno));// 0
				list.add(rs.getString("system_name"));// 0
				list.add(rs.getString("total_college"));// 1
				college+=Integer.parseInt(rs.getString("total_college"));
				list.add(rs.getString("fully"));// 2
				totalseat+=Integer.parseInt(rs.getString("fully"));
				list.add(rs.getString("vaccant0"));// 3
				allot+=Integer.parseInt(rs.getString("vaccant0"));
				list.add(rs.getString("partialVaccant"));// 4
				vacant+=Integer.parseInt(rs.getString("partialVaccant"));
				alist.add(list);
				serno++;
			}
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.valueOf(serno));// 0
			list.add("Total");// 0
			list.add(college+"");// 1
			list.add(totalseat+"");// 2
			list.add(allot+"");// 3
			list.add(vacant+"");// 4
			alist.add(list);
			
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
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getEformSummaryReportPG(String institute1, String category1, String authority1,
			String quota1,String role,String roleStaff_lvl,String degree1,String subject1,String intake1) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			System.out.println("quota1 "+quota1);
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String in = "";
			String stu = "";
			String fildname="";
			
			
			if (!institute1.equals("0")) {
				in += " and state_id= ?";
			}
			if(role.equals("AYU_BOARD")) {
				in += " and system_id= 44";
			}
			if(role.equals("UNI_BOARD")) {
				in += " and system_id= 48";
			}
			if(role.equals("SID_BOARD")) {
				in += " and system_id= 46";
			}
			if(role.equals("SWR_BOARD")) {
				in += " and system_id= 47";
			}
			if(role.equals("HOM_BOARD")) {
				in += " and system_id= 45";
			}
			if (!category1.equals("0")) {
				stu+=" and category= ?";
			}
			if (!authority1.equals("0")) {
				stu+=" and counc_auth=? ";
			}
			if (!quota1.equals("0")) {
				stu+=" and quota=? ";
			}
			if(roleStaff_lvl.equals("NCH")) {
				fildname="edu_lms_nch_student_details";
			}else {
				fildname="edu_lms_student_details";
			}
 
			q = "select system_name,COALESCE(count(it.id),0) AS college_total,COALESCE(sum(total_seat),0)as totalseat,COALESCE(sum(allot),0) as allot,\n"
					+ " COALESCE(sum(total_seat),0)-COALESCE(sum(allot),0) as vacant \n"
					+ " from edu_lms_institute_reg it inner join \n"
					+ " (select institute_id,sum(seat) as total_seat from edu_lms_subjects_wise_pg_seats group by institute_id ) s\n"
					+ " on it.id=s.institute_id  \n"
					+ ""+in+""
					+ " left join (select institude_userid,count(st.id) as allot from $fildname st \n"
					+ "		   inner join  edu_lms_degree_mstr dt on dt.id=st.degree and type_of_degree=16 where st.id!=0 "+stu+"    \n"
					+ "		   group by institude_userid) st on st.institude_userid=it.id\n"
					+ "		   inner join edu_lms_system_mstr sm on sm.id=system_id and created_role=? \n"
					+ "		    \n"
					+ ""
					+ "		   group by system_name";
			
			q = q.replace("$fildname", fildname);
			stmt = conn.prepareStatement(q);
			int count=1;
			if (!institute1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(institute1));
				count++;
			}
			
			if (!category1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(category1));
				count++;
			}
			if (!authority1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(authority1));
				count++;
			}
			if (!quota1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(quota1));
				count++;
			}
			stmt.setString(count,roleStaff_lvl);
			count++;
			
			
			System.out.println("\n\nstmt summary "+stmt+"\n\n");
			ResultSet rs = stmt.executeQuery();
			int serno = 1;
			int college=0;
			int totalseat=0;
			int allot=0;
			int vacant=0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(serno));// 0
				list.add(rs.getString("system_name"));// 0
				list.add(rs.getString("college_total"));// 1
				college+=Integer.parseInt(rs.getString("college_total"));
				list.add(rs.getString("totalseat"));// 2
				totalseat+=Integer.parseInt(rs.getString("totalseat"));
				list.add(rs.getString("allot"));// 3
				allot+=Integer.parseInt(rs.getString("allot"));
				list.add(rs.getString("vacant"));// 4
				vacant+=Integer.parseInt(rs.getString("vacant"));
				alist.add(list);
				serno++;
			}
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.valueOf(serno));// 0
			list.add("Total");// 0
			list.add(college+"");// 1
			list.add(totalseat+"");// 2
			list.add(allot+"");// 3
			list.add(vacant+"");// 4
			alist.add(list);
			
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
		return alist;
	}
	
	
	@Override
	public ArrayList<ArrayList<String>> getEformSummaryVacantReportPG(String institute1, String category1, String authority1,
			String quota1,String role,String roleStaff_lvl,String degree1,String subject1,String intake1) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			System.out.println("category1 "+category1);
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String in = "";
			String stu = "";
			String fildname="";
			if (!institute1.equals("0")) {
				in += " and state_id= ?";
			}
			if (!category1.equals("0")) {
				stu+=" and category= ?";
			}
			if (!authority1.equals("0")) {
				stu+=" and counc_auth=? ";
			}
			if (!quota1.equals("0")) {
				stu+=" and quota=? ";
			}
			if(role.equals("AYU_BOARD")) {
				in += " and system_id= 44";
			}
			if(role.equals("UNI_BOARD")) {
				in += " and system_id= 48";
			}
			if(role.equals("SID_BOARD")) {
				in += " and system_id= 46";
			}
			if(role.equals("SWR_BOARD")) {
				in += " and system_id= 47";
			}
			if(role.equals("HOM_BOARD")) {
				in += " and system_id= 45";
			}
			if(roleStaff_lvl.equals("NCH")) {
				fildname="edu_lms_nch_student_details";
			}else {
				fildname="edu_lms_student_details";
			}
			q = "select system_name,count(inst.institute_id) as total_college,\n"
					+ "count(inst.institute_id) filter(where total_sanctioned_seat=allot) as fully,\n"
					+ "count(inst.institute_id) filter(where 0=allot or allot is null) as vaccant0,\n"
					+ "count(inst.institute_id) filter(where total_sanctioned_seat!=allot)as partialVaccant\n"
					+ "from (select ir.system_id,ps.institute_id,count(ps.seat) as total_sanctioned_seat\n"
					+ "from edu_lms_subjects_wise_pg_seats ps\n"
					+ "inner join edu_lms_institute_reg ir on ir.id=ps.institute_id  "+in+"\n"
					+ "group by 1,2) inst\n"
					+ "left join \n"
					+ "  (select system,institude_userid,count(sd.id) as allot from $fildname sd\n"
					+ "   inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ "where sd.id!=0  and dm.type_of_degree=16 "+stu+" group by system,institude_userid) st on st.institude_userid=inst.institute_id\n"
					+ " inner join edu_lms_system_mstr sm on sm.id=inst.system_id and created_role=?\n"
					+ "group by system_name";
			q = q.replace("$fildname", fildname);
			stmt = conn.prepareStatement(q);
			int count=1;
			if (!institute1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(institute1));
				count++;
			}
			if (!category1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(category1));
				count++;
			}
			if (!authority1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(authority1));
				count++;
			}
			if (!quota1.equals("0")) {
				stmt.setInt(count,Integer.parseInt(quota1));
				count++;
			}
			stmt.setString(count,roleStaff_lvl);
			System.out.println("stmt summary PG Vacant-"+stmt);
			ResultSet rs = stmt.executeQuery();
			int serno = 1;
			int college=0;
			int totalseat=0;
			int allot=0;
			int vacant=0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(serno));// 0
				list.add(rs.getString("system_name"));// 0
				list.add(rs.getString("total_college"));// 1
				college+=Integer.parseInt(rs.getString("total_college"));
				list.add(rs.getString("fully"));// 2
				totalseat+=Integer.parseInt(rs.getString("fully"));
				list.add(rs.getString("vaccant0"));// 3
				allot+=Integer.parseInt(rs.getString("vaccant0"));
				list.add(rs.getString("partialVaccant"));// 4
				vacant+=Integer.parseInt(rs.getString("partialVaccant"));
				alist.add(list);
				serno++;
			}
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.valueOf(serno));// 0
			list.add("Total");// 0
			list.add(college+"");// 1
			list.add(totalseat+"");// 2
			list.add(allot+"");// 3
			list.add(vacant+"");// 4
			alist.add(list);
			
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
		return alist;
	}
	
}
