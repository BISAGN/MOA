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
public class Set_Student_Fees_Amount_DaoImpl implements Set_Student_Fees_Amount_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTable_CMEAttend_DataList(String userId, String degree_name, String prof_id,
			String inst_id, String role) {
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
						+ "sd.system,sd.degree,sd.semester,sd.institude_id,rpd.pers_category,ir.no_of_part,sum(lsd.feesvalue) as feesvalue\n"
						+ "from edu_lms_student_details sd\n"
						+ "inner join edu_ncism_reg_gradu_personal_dtls rpd on rpd.pers_email=sd.email \n"
						+ "inner join tb_lms_category_mstr lm on lm.id=rpd.pers_category::int\n"
						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=rpd.pers_category::int\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry + " and sd.verified_status=0 "
						+ " group by 1,2,3,4,5,6,7,8,9,10,11,12 ";
			}
			if (role.contains("NCH")) {
				q = "select sd.id,sd.name,sd.ayush_id,TO_CHAR(sd.dob,'DD-MON-YYYY') as dob,sd.mobile_no,lm.category,\n"
						+ "sd.system,sd.degree,sd.semester,sd.institude_id,rpd.pers_category,ir.no_of_part,sum(lsd.feesvalue) as feesvalue\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join edu_reg_gradu_personal_dtls rpd on rpd.pers_email=sd.email \n"
						+ "inner join tb_lms_category_mstr lm on lm.id=rpd.pers_category::int\n"
						+ "inner join co_instituteotherdetail lsd on lsd.inid=sd.institude_userid and lsd.catid=rpd.pers_category::int\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=sd.institude_userid\n" + "where " + qry + " and sd.verified_status=0 "
						+ " group by 1,2,3,4,5,6,7,8,9,10,11,12 ";
			}

			stmt = conn.prepareStatement(q);

			int j = 0;

//			System.err.println("degree_name=============" + degree_name);

			if (!inst_id.equals("")) {
				j = j + 1;
				stmt.setInt(j, Integer.parseInt(inst_id));
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
			
			int countFunction = 1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				long isc = checkisdataSaved(rs.getString("id"),degree_name,prof_id);
				columns.put("isc", isc);
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm updateonlick' id='viewId"+countFunction+"' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='viewhidId"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='chkES"+countFunction+"' value='1'>"
								+"</i></a> </li>";
				
				 f1 ="<li><a class='main-btn dark-btn-outline btn-hover btn-sm saveonclick' id='saveId"+countFunction+"' value='ADD' title='Save Data' >" 
						 		+"<input type='hidden' id='savehidId"+countFunction+"' value='"+rs.getString("id")+"'>"
						 		+"<input type='hidden' id='chkES"+countFunction+"' value='2'>"
						 		+ "<i class='lni lni-book'></i></a></li>";

//				 <a class="main-btn dark-btn-outline btn-hover btn-sm btnback" title="Book"><i class="lni lni-book"></i></a>

				if(isc > 0) {
					 ul+= f ;
				}else {
					ul+= f1 ;
				}
				
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
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

	public List<Map<String, Object>> getPopup_FeesChildDatalist(String studentid, String role, String inst_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();

			q = "select c.id,c.part_ser,c.amount \n" + "from edu_lms_set_stu_fees_parent p\n"
					+ "inner join edu_lms_set_stu_fees_child c on c.p_id=p.id\n" + "where p.stu_id=? and c.status=0 order by c.part_ser asc";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setInt(1, Integer.parseInt(studentid));
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-=-=-=-=-=-="+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			int countupdt = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String ud = "";
//				String sv = "";
				
//				ud = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm UPDTdetails' id='updtId" + countupdt + "' name='updtId" + countupdt + "' value='ADD' title='Update Data' >\n"
//						+ "		<i class='lni lni-book'> <input type='hidden' id='updthidId" + countupdt + "' value='"
//						+ rs.getInt("id") + "'></i></a> </li></ul>";
				
				ud = "<ul class='buttons-group mainbtn action daobtn'>"
					+"<li><span class='status-btn active-btn' id='updtId"+ countupdt +"'>Update</span>"
					+"<input type='hidden' id='updthidId"+countupdt+"' value='"+rs.getInt("id")+"'></li></ul>";
				
//				sv = "<ul class='buttons-group mainbtn action daobtn'>"
//						+"<li><span class='status-btn active-btn' id='updtId"+ countupdt +"'>Save</span>"
//						+"<input type='hidden' id='updthidId" + countupdt + "' value='"+rs.getInt("id")+"'></li></ul>";

				countupdt += 1;

				columns.put("ud", ud);

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
	
	
	public long checkisdataSaved(String stu_id,String degree,String proff) {
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q=" select count(*) from edu_lms_set_stu_fees_parent where stu_id=? and degree=? and prof_id=? ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(stu_id));
			stmt.setInt(2,Integer.parseInt(degree));
			stmt.setInt(3,Integer.parseInt(proff));
			
			System.err.println("\n\n checkisdataSaved--->>>"+stmt);

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
