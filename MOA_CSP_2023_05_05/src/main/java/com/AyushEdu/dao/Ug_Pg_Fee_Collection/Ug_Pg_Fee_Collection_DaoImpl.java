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

import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Ug_Pg_Fee_Collection_DaoImpl implements Ug_Pg_Fee_Collection_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Map<String, Object>> StudentDataAuto(String autoString,String role,String degree_cat,String instid) {
		
		System.err.println("**********************************");
		System.err.println(autoString+"\n"+role+"\n"+degree_cat);
		System.err.println("**********************************");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		String tb_name = "";
		String student_role = "";
		String student_role2 = "";
		String rq = "";
		
		if(role.contains("NCISM")) {
			tb_name = "edu_lms_student_details";
			if(degree_cat.equals("Degree/Under Graduate")) {
				student_role = "ADM_UG_Student_NCISM";
				student_role2 = "Student_NCISM";
				rq = " and (role = ? or role = ?) ";
			}
			if(degree_cat.equals("Post Graduate Degree")) {
				student_role = "Student_PG_NCISM";
				rq = " and role = ? ";
			}
		}
		if(role.contains("NCH")) {
			tb_name = "edu_lms_nch_student_details";
			if(degree_cat.equals("Degree/Under Graduate")) {
				student_role = "ADM_UG_Student_NCH";
				student_role2 = "Student_NCH";
				rq = " and (role = ? or role = ?) ";
			}
			if(degree_cat.equals("Post Graduate Degree")) {
				student_role = "Student_PG_NCH";
				rq = " and role = ? ";
			}
		}

		
		try {

			conn = dataSource.getConnection();
			if(role.contains("NCISM")) {
				q = "select sd.id,sd.name \n"
						+ "from edu_lms_student_details sd\n"
						+ "inner join logininformation li on li.email_id=sd.email\n"
						+ "inner join userroleinformation um on um.user_id=li.userid\n"
						+ "inner join roleinformation ri ON ri.role_id = um.role_id\n"
						+ "where --sd.verified_status=0 and \n lower(sd.name) like ? and sd.institude_userid = ?  "+rq;
			}
			if(role.contains("NCH")) {
				q = "select sd.id,sd.name \n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join logininformation li on li.email_id=sd.email\n"
						+ "inner join userroleinformation um on um.user_id=li.userid\n"
						+ "inner join roleinformation ri ON ri.role_id = um.role_id\n"
						+ "where --sd.verified_status=0 and \n lower(sd.name) like ? and sd.institude_userid = ?  "+rq;
			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setString(1,"%"+autoString.toLowerCase()+"%");
			stmt.setInt(2,Integer.parseInt(instid));
			if(degree_cat.equals("Degree/Under Graduate")) {
				stmt.setString(3,student_role);
				stmt.setString(4,student_role2);
			}
			if(degree_cat.equals("Post Graduate Degree")) {
				stmt.setString(3,student_role);
			}
			
			System.err.println("stmt---STUDENT AUTO--->"+stmt);
			
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
	
	
public List<Map<String, Object>> getStuTypeofDegProf(String role,String user_id) {
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			
			if(role.contains("NCISM")) {
			q = "select s.verified_status,s.degree,s.semester,s.name,s.id,i.no_of_part,s.fee_paid_status \n"
					+ "from edu_lms_student_details s \n"
					+ "inner join logininformation l on l.email_id=s.email \n"
					+ "inner join edu_lms_institute_reg i on i.id=s.institude_userid \n"
					+ "where l.userid=? ";
			}
			
			if(role.contains("NCH")) {
				q = "select s.verified_status,s.degree,s.semester,s.name,s.id,i.no_of_part,s.fee_paid_status  \n"
					+ " from edu_lms_nch_student_details s\n"
					+ "	inner join logininformation l on l.email_id=s.email\n"
					+ "inner join edu_lms_institute_reg i on i.id=s.institude_userid \n"
					+ "	where l.userid=?";
			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(user_id));
			
			System.err.println("stmt---getStuTypeofDegProf--->"+stmt);
			
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
	
public List<Map<String, Object>> Getfeesdetails(String studentid,String role,String inst_id) {
		
//		System.err.println("**********************************");
//		System.err.println(autoString+"\n"+role+"\n"+degree_cat);
//		System.err.println("**********************************");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
//		String tb_name1 = "";
//		String tb_name2 = "";
//		
//		if(role.contains("NCISM")) {
//			tb_name1 = "edu_lms_student_details";
//			tb_name2 = "edu_ncism_reg_gradu_personal_dtls";
//		}
//		if(role.contains("NCH")) {
//			tb_name1 = "edu_lms_nch_student_details";
//			tb_name2 = "edu_reg_gradu_personal_dtls";
//		}	
		
		try {

			conn = dataSource.getConnection();
			
			q = "select c.id,c.part_ser,c.amount \n"
					+ "from edu_lms_set_stu_fees_parent p\n"
					+ "inner join edu_lms_set_stu_fees_child c on c.p_id=p.id\n"
					+ "where p.stu_id=? and c.status=0 order by part_ser ASC";
			
//			q= "select sm.feestype,lsd.feesvalue\n"
//					+ "from  co_instituteotherdetail lsd \n"
//					+ "inner join co_feescategorytype sm on sm.ftid=lsd.feesid\n"
//					+ "inner join edu_lms_degree_cate_mstr dm on dm.id=lsd.degreetype\n"
//					+ "where lsd.status = '1' and lsd.inid=?  and sm.ftid in (2,3,4)";
			
//			if(role.contains("NCISM")) {
//				q = "select sm.feestype,lsd.feesvalue,sd.id,sd.verified_status,sd.degree,sd.semester,sd.name,ir.no_of_part,fc.amount,fp.stu_id\n"
//						+ "from edu_lms_student_details sd\n"
//						+ "inner join logininformation li on li.aadhar_no=sd.aadhar_card\n"
//						+ "inner join edu_lms_institute_reg ir on ir.id=li.institute_id\n"
//						+ "inner join edu_lms_set_stu_fees_parent fp on fp.stu_id=sd.id\n"
//						+ "inner join edu_lms_set_stu_fees_child fc on fc.p_id=fp.id \n"
//						+ "inner join edu_ncism_reg_gradu_personal_dtls pd on pd.p_id=li.userid\n"
//						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=pd.pers_category::int\n"
//						+ "inner join co_feescategorytype sm on sm.ftid=lsd.feesid\n"
//						+ "inner join edu_lms_degree_cate_mstr dm on dm.id=lsd.degreetype\n"
//						+ "where lsd.status = '1' and lsd.inid=?  and sm.ftid != 3 and sd.id= ?  ";
//			}
			
//			if(role.contains("NCH")) {
//				q = "select sm.feestype,lsd.feesvalue\n"
//						+ "from edu_lms_nch_student_details sd\n"
//						+ "inner join logininformation li on li.aadhar_no=sd.aadhar_card\n"
//						+ "inner join edu_reg_gradu_personal_dtls pd on pd.p_id=li.userid\n"
//						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=pd.pers_category::int\n"
//						+ "inner join co_feescategorytype sm on sm.ftid=lsd.feesid\n"
//						+ "inner join edu_lms_degree_cate_mstr dm on dm.id=lsd.degreetype\n"
//						+ "where lsd.status = '1' and lsd.inid=?  and sm.ftid != 3 and sd.id= ?  ";
				
//				q="select sm.feestype,lsd.feesvalue,sd.id,sd.verified_status,sd.degree,sd.semester,sd.name,ir.no_of_part,fc.amount,fp.stu_id\n"
//						+ "from edu_lms_nch_student_details sd\n"
//						+ "inner join logininformation li on li.aadhar_no=sd.aadhar_card\n"
//						+ "inner join edu_lms_institute_reg ir on ir.id=li.institute_id\n"
//						+ "inner join edu_lms_set_stu_fees_parent fp on fp.stu_id=sd.id\n"
//						+ "inner join edu_lms_set_stu_fees_child fc on fc.p_id=fp.id \n"
//						+ "inner join edu_ncism_reg_gradu_personal_dtls pd on pd.p_id=li.userid\n"
//						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=pd.pers_category::int\n"
//						+ "inner join co_feescategorytype sm on sm.ftid=lsd.feesid\n"
//						+ "inner join edu_lms_degree_cate_mstr dm on dm.id=lsd.degreetype\n"
//						+ "where lsd.status = '1' and lsd.inid=?  and sm.ftid != 3 and sd.id= ?  ";
//			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			
//			stmt.setInt(1,Integer.parseInt(inst_id));
			stmt.setInt(1,Integer.parseInt(studentid));
			
			System.err.println("stmt---GET FEES DATA--->"+stmt);
			
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

@Override
public List<Map<String, Object>> GetRoleInfoFromStudentId(String Instrole,String studentid) {
	
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	
	String tb_name = "";
	
	if(Instrole.contains("NCISM")) {
		tb_name = "edu_lms_student_details";
	}
	if(Instrole.contains("NCH")) {
		tb_name = "edu_lms_nch_student_details";
	}
	
	try {

		conn = dataSource.getConnection();
		
		q = "select l.userid,ri.role_id,ri.role\n"
				+ "from "+tb_name+" sd\n"
				+ "inner join logininformation l on l.email_id=sd.email \n"
				+ "inner join userroleinformation ur on ur.user_id=l.userid\n"
				+ "inner join roleinformation ri on ri.role_id=ur.role_id\n"
				+ "where sd.id=?";

		PreparedStatement stmt = conn.prepareStatement(q);
		
		stmt.setInt(1,Integer.parseInt(studentid));
		
		System.err.println("stmt---GetRoleInfoFromStudentId--->"+stmt);
		
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


// degreefrom_fromybyinstlist_ctrl

@Override
public ArrayList<ArrayList<String>> degreefrom_fromybyinstlist_ctrl(String type_of_degree,String inst_id,String role) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		if(role.contains("NCISM")) {
			q="select distinct dm.id,dm.degree_name \n"
					+ "from edu_lms_degree_mstr dm \n"
					+ "inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
					+ "where dm.status='1' and system_id=(select system_id from edu_lms_institute_reg where id=? ) and type_of_degree = ?";
		}
		if(role.contains("NCH")) {
//			q="select distinct dm.id,dm.degree_name \n"
//					+ "from edu_lms_degree_mstr dm \n"
//					+ "inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
//					+ "where dm.status='1' and system_id=45 and type_of_degree = ?";
			
			q = "select d.id,d.degree_name from edu_lms_system_degree_map_mstr dm\n"
					+ " inner join edu_lms_degree_mstr d on d.id = dm.degree_name\n"
					+ " where system_name=45";
		}

		stmt = conn.prepareStatement(q);
		if(role.contains("NCISM")){
			stmt.setInt(1, Integer.parseInt(inst_id));
			stmt.setInt(2, Integer.parseInt(type_of_degree));
		}
//		if(role.contains("NCH")){
//			stmt.setInt(1, Integer.parseInt(type_of_degree) );
//		}
		
		System.err.println("degreefrom_fromybyinstlist_ctrl---"+stmt);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("degree_name"));// 1
			alist.add(list);
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
	return alist;
}

@Override
public String getSupplyData(String stu_id) {
	Connection conn = null;
	String q = "";
	
	String count = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		q="select count(*) from edu_exam_tb_supplementary_student where student_id=? and status=0";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(stu_id));
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			count = rs.getString("count");
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
	return count;
}

@Override
public String isFessPaid(String stu_id,String prof) {
	Connection conn = null;
	String q = "";
	
	String count = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		q="select count(*) from edu_lms_fees_collect where student_name=? and term_id=?";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(stu_id));
		stmt.setInt(2, Integer.parseInt(prof));
		
		ResultSet rs = stmt.executeQuery();
		
		System.err.println("STMT-FEES-PAID---"+stmt);

		while (rs.next()) {
			count = rs.getString("count");
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
	return count;
}

@Override
public String CheckFessdataAvlbl(String stu_id,String prof,String degree) {
	Connection conn = null;
	String q = "";
	
	String count = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		q="select count(*)\n"
				+ "from edu_lms_set_stu_fees_parent p\n"
				+ "inner join edu_lms_set_stu_fees_child c on c.p_id=p.id\n"
				+ "where p.stu_id=? and c.status in (0,1) and p.degree=? and p.prof_id=?";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(stu_id));
		stmt.setInt(2, Integer.parseInt(degree));
		stmt.setInt(3, Integer.parseInt(prof));
		
		ResultSet rs = stmt.executeQuery();
		
		System.err.println("\n \n STMT-CheckFessdataAvlbl---"+stmt);

		while (rs.next()) {
			count = rs.getString("count");
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
	return count;
}

@Override
public String InstNoOfPart(String inst_id) {
	Connection conn = null;
	String q = "";
	
	String count = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		q="select no_of_part from edu_lms_institute_reg where id=? ";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(inst_id));
		
		ResultSet rs = stmt.executeQuery();
		
		System.err.println("STMT-InstNoOfPart---"+stmt);

		while (rs.next()) {
			count = rs.getString("no_of_part");
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
	return count;
}



@Override
public ArrayList<ArrayList<String>> getVerifyStatus(String stu_id, String role) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		String tb_name = "";
		
		if(role.contains("NCISM")) {
			tb_name = "edu_lms_student_details";
		}
		if(role.contains("NCH")) {
			tb_name = "edu_lms_nch_student_details";
		}
	
		q="select id,verified_status from "+tb_name+" where id=?";


		stmt = conn.prepareStatement(q);
		
			stmt.setInt(1, Integer.parseInt(stu_id));
		
		
//		
		
		System.err.println("verified_status---"+stmt);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("verified_status"));// 1
			alist.add(list);
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
	return alist;
}

public  ArrayList<ArrayList<String>> Getlastnoofpart(String studentid, String role) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {

		conn = dataSource.getConnection();
		
		String tb_name = "";
		
		if(role.contains("NCISM")) {
			tb_name = "edu_lms_student_details";
		}
		if(role.contains("NCH")) {
			tb_name = "edu_lms_nch_student_details";
		}
		
		q = "\n"
				+ "select c.id,max(part_ser) as count,ir.no_of_part from edu_lms_set_stu_fees_child c\n"
				+ "inner join edu_lms_set_stu_fees_parent p on c.p_id=p.id\n"
				+ "inner join "+tb_name+" sd on sd.id=p.stu_id\n"
				+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n"
				+ "where p.stu_id=? and c.status=0 group by c.id,ir.no_of_part";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(studentid));
		
		System.err.println("stmt---Getlastnoofpart--->"+stmt);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("count"));// 1
			list.add(rs.getString("no_of_part"));// 1
			alist.add(list);
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
	return alist;
}


/*--Fess Part And Full -- 
//payfees_part


@Override
public ArrayList<ArrayList<String>> payfees_part(String id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
	
		q="select count(*) from edu_lms_institute_reg where no_of_part=?";

		stmt = conn.prepareStatement(q);
		
			//stmt.setInt(1, Integer.parseInt(inst_id));
		
		
//		
		
		System.err.println("payfees_part---"+stmt);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("payment"));// 1
			alist.add(list);
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
	return alist;
}

*/

}

