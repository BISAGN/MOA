package com.AyushEdu.dao.LMS_Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class TeacherReportDaoImpl implements TeacherReportDao {
	
	
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<Map<String, Object>> DataTableTeacher_DataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, ayush_id, teacher_code, name, university_id,institute_id,ug_pg,subject,gender,date_of_birth,
				experience,state,district,village,othquali,role,userid);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q = "";
		String q1 = "";
		try {
//	System.out.println("university "+university);
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			
		
			
			q="SELECT distinct sp.id,sp.ayush_id,oqc.name_of_exam_degree,string_agg(sm.subject_name::character varying,':') as subject,string_agg(dm.type_of_degree::character varying,':')as degree_name,sp.teacher_code,um.university_name,ir.institute_name,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
					+ "				TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,principal_status FROM tb_nch_add_teacher_details sp\n"
					+ "				inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
					+ "				inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
					+ "				inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
					+ "				inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
					+ "				inner join logininformation lo on lo.userid=sp.user_id\n"
					+ "				inner join edu_lms_faculty_nch sd on sd.email= lo.email_id \n"
					+ "				inner join edu_lms_institute_reg ir on ir.id=sd.institute_id \n"
					+ "				inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
					+ "				inner join tb_nch_teacher_quali_child qc on qc.p_id=sp.id\n"
					+ "				inner join  edu_faculty_subject_mstr sm on sm.id=qc.subject\n"
					+"				inner join edu_lms_type_of_degree_mstr dm on dm.id=qc.type_of_degree::int"
					+"				left join tb_nch_teacher_other_quali_child oqc on oqc.p_id=oqc.id\n"	
					+ "				where sp.id !=0 and principal_status= 1 "+SearchValue+" group by 1,2,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 order by id "+ orderType +"  limit "+pageL+" OFFSET "+startPage;
			
			
			
		
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, ayush_id, teacher_code, name, university_id,institute_id,ug_pg,subject,gender,date_of_birth,
					experience,state,district,village,othquali,role,userid);
			
			System.err.println("-------------meera---->>>>"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			int j = startPage;
			int countFunction=1;
			
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
				String f2 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm' value='ADD' "+ADD+" title='Edit Data' >"+ //id='id_add_attHospital1'
//								"<i class='lni lni-pencil-alt'></i></a> </li> ";
//				
	//
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm' value='ADD' "+ADD1+" title='Delete Data' >"+ //id='id_add_attHospital1'
//								"<i class='lni lni-trash-can'></i></a> </li> ";
			
				
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-eye'></i></a> </li>"
								+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				ul+=f + " " + f1 + " " + " " + f2;
				 ul+="</ul>";
				

				 countFunction+=1;
				action = ul;
				columns.put("action", action);
				
//				alist.add(rs.getString("id")); //0
				
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
	public long DataTableTeacher_DataTotalCount(String Search, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, ayush_id, teacher_code, name, university_id,institute_id,ug_pg,subject,gender,date_of_birth,
				experience,state,district,village,othquali,role,userid);
		int total = 0;
		//System.out.println("institute "+institute);
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			
			
			q="select count(*) from (SELECT distinct sp.id,sp.ayush_id,oqc.name_of_exam_degree,string_agg(sm.subject_name::character varying,':') as subject,string_agg(dm.type_of_degree::character varying,':')as degree_name,sp.teacher_code,um.university_name,ir.institute_name,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
					+ "				TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,principal_status FROM tb_nch_add_teacher_details sp\n"
					+ "				inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
					+ "				inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
					+ "				inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
					+ "				inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
					+ "				inner join logininformation lo on lo.userid=sp.user_id\n"
					+ "				inner join edu_lms_faculty_nch sd on sd.email= lo.email_id \n"
					+ "				inner join edu_lms_institute_reg ir on ir.id=sd.institute_id \n"
					+ "				inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
					+ "				inner join tb_nch_teacher_quali_child qc on qc.p_id=sp.id\n"
					+ "				inner join  edu_faculty_subject_mstr sm on sm.id=qc.subject\n"
					+"				left join tb_nch_teacher_other_quali_child oqc on oqc.p_id=oqc.id\n"	
					+ "				inner join edu_lms_type_of_degree_mstr dm on dm.id=qc.type_of_degree::int\n"
					+ "				where sp.id !=0 and principal_status= 1  "+SearchValue+" group by 1,2,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 order by id)ab";
			
				
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt,Search, ayush_id, teacher_code, name, university_id,institute_id,ug_pg,subject,gender,date_of_birth,
					experience,state,district,village,othquali,role,userid);

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
	public String GenerateQueryWhereClause_SQL(String Search,  String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid) {
	
	String SearchValue = "";
	
//	
	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and (  sp.ayush_id like ? or sp.teacher_code like ? or upper(sp.first_name) like ?  or upper(um.university_name) like ?  or upper(ir.institute_name) like ? or upper(dm.type_of_degree) like ? or upper(sm.subject_name) like ? "
				+ "or upper(g.gender_name) like ? or to_char(sp.date_of_birth,'DD-MM-YYYY') like ? or upper(s.state_name::text) like ? or upper(d.district_name::text) like ? or upper(sp.per_village) like ? or upper(oqc.name_of_exam_degree) like ? )";

//		or (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) like ?";	
	}
//
//	/// advance search
//	
//	if(role.equals("University_NCH")) {
//		//SearchValue=" and university_userid = ?";
//	}else if(role.equals("Principal_NCH")) {
//		//SearchValue=" and principal_userid = ?";
//	}
//	else if(role.equals("Faculty_NCH")) {
//		//SearchValue=" and user_id = ?";
//	}
	if (!ayush_id.trim().equals("")) {
		SearchValue += " and upper(sp.ayush_id) like ? ";
	}
	if (!teacher_code.trim().equals("")) {
		SearchValue += " and upper(sp.teacher_code) like ? ";
	}
	if (!name.trim().equals("")) {
		SearchValue += " and upper(sp.first_name) like ? ";
	}
	if(!institute_id.equals("0")) {
		SearchValue += " and sd.institute_id = ? ";
	}
	if(!university_id.equals("0")) {
		SearchValue += " and ir.university_id = ? ";
	}
	if(!ug_pg.equals("0")) {
		SearchValue += " and qc.type_of_degree::int = ? ";
	}
	if(!subject.equals("0")) {
		SearchValue += " and qc.subject = ? ";
	}
	
	if (!gender.trim().equals("0")) {
		SearchValue += " and upper(sp.gender) like ? ";
	}
	if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")){
		SearchValue += " and to_char(sp.date_of_birth,'DD/MM/YYYY')=?";
	}
	if (!experience.trim().equals("")) {
		SearchValue += " and yr_of_exp::text like ? ";
	}
	if (!state.trim().equals("0")) {
		SearchValue += " and upper(s.state_id::text) like ? ";
	}
	if (!district.trim().equals("0")) {
		SearchValue += " and upper(d.district_id::text) like ? ";
	}
	if (!village.trim().equals("")) {
		SearchValue += " and upper(sp.per_village) like ? ";

	}
	if (!othquali.trim().equals("")) {
		SearchValue += " and upper(oqc.name_of_exam_degree) like ? ";

	}
	
//	if (!state_reg_no.trim().equals("")) {
//		SearchValue += " and upper(sp.state_reg_no) like ? ";
//	}
//	if (!state_board_name.trim().equals("")) {
//		SearchValue += " and upper(sp.state_board_name) like ? ";
//	}
//	if (!date_of_reg.equals("") && date_of_reg != "" && !date_of_reg.equals("DD/MM/YYYY")){
//		SearchValue += " and to_char(sp.date_of_reg,'DD/MM/YYYY')=?";
//	}
//	if (!central_reg_no.trim().equals("")) {
//		SearchValue += " and upper(sp.central_reg_no) like ? ";
//	}

//	
//	
//	if (!yr_of_exp.trim().equals("")) {
//		SearchValue += " and (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) = ? ";
//
//	}




	return SearchValue;
}
	

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String ayush_id, String teacher_code,
		String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid) {
	
	int flag = 0;
	try {
//		
//		
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
			
//			flag += 1;
//			stmt.setString(flag, "%" + Search.toUpperCase() + "%");

		}
//		
//		if(role.equals("University_NCH")) {
//			//flag += 1;
//			//stmt.setInt(flag,Integer.parseInt(userid));
//		}else if(role.equals("Principal_NCH")) {
//			//flag += 1;
//			//stmt.setInt(flag, Integer.parseInt(userid));
//		}
//		else if(role.equals("Faculty_NCH")) {
//			//flag += 1;
//			//stmt.setInt(flag, Integer.parseInt(userid));
//		}
//		
		if (!ayush_id.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + ayush_id.toUpperCase() + "%");
		}
		if (!teacher_code.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + teacher_code.toUpperCase() + "%");
		}
		if (!name.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + name.toUpperCase() + "%");
		}
		if(!university_id.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( university_id));

		}
		if(!institute_id.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( institute_id));

		}
		if(!ug_pg.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( ug_pg));

		}
		if(!subject.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( subject));

		}
		if (!gender.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + gender.toUpperCase() + "%");
		}
		if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")) {
			flag += 1;
			stmt.setString(flag, date_of_birth);
		}
		if (!experience.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + experience + "%");
		}
		if (!state.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + state.toUpperCase() + "%");
		}
		if (!district.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + district.toUpperCase() + "%");
		}
		if (!village.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + village.toUpperCase() + "%");
		}
		if (!othquali.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + othquali.toUpperCase() + "%");
		}
//		if (!state_reg_no.trim().equals("")) {
//			flag += 1;
//			stmt.setString(flag, "%" + state_reg_no.toUpperCase() + "%");
//		}
//		if (!state_board_name.trim().equals("")) {
//			flag += 1;
//			stmt.setString(flag, "%" + state_board_name.toUpperCase() + "%");
//		}
//		if (!date_of_reg.equals("") && date_of_reg != "" && !date_of_reg.equals("DD/MM/YYYY")) {
//			flag += 1;
//			stmt.setString(flag, date_of_reg);
//		}
//		if (!central_reg_no.trim().equals("")) {
//			flag += 1;
//			stmt.setString(flag, "%" + central_reg_no.toUpperCase() + "%");
//		}

//		
//		if (!yr_of_exp.trim().equals("")) {
//			flag += 1;
//			stmt.setInt(flag, Integer.parseInt(yr_of_exp));
//		}
//		if(!institute.equals("0")) {
//			flag += 1;
//			stmt.setInt(flag,Integer.parseInt( institute));
//
//			}
		

	} catch (Exception e) {
		e.printStackTrace();
	}

	return stmt;
}





public ArrayList<ArrayList<String>> getTeacher_Report_Excel( String ayush_id,String teacher_code,String faculty_name,String university_id, String institute_id,String ug_pg,String subject,String gender,String date_of_birth,String experience,String state_name,String district,String village,String othquali,String role,String userid)
		throws ParseException {
	
//	university_id, institute_id,
//	name, ayush_id, gender, date_of_birth,role1
//	

//	System.err.println("institute_id ------>     "+ university_id+"   "+ institute_id +"   "+ name+"   "+
//			 ayush_id +"   "+gender +"   "+ date_of_birth);
	
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	String SearchValue = GenerateQueryWhereClause_SQL("", ayush_id,teacher_code,faculty_name,university_id, institute_id, ug_pg,subject,gender,date_of_birth,experience,state_name,district,village,othquali,role,userid);
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();

		String qry = "";

				
		
		q="SELECT distinct sp.id,sp.ayush_id,oqc.name_of_exam_degree,string_agg(sm.subject_name::character varying,':') as subject,string_agg(dm.type_of_degree::character varying,':')as degree_name,sp.teacher_code,um.university_name,ir.institute_name,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
				+ "				TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,principal_status FROM tb_nch_add_teacher_details sp\n"
				+ "				inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
				+ "				inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
				+ "				inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
				+ "				inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
				+ "				inner join logininformation lo on lo.userid=sp.user_id\n"
				+ "				inner join edu_lms_faculty_nch sd on sd.email= lo.email_id \n"
				+ "				inner join edu_lms_institute_reg ir on ir.id=sd.institute_id \n"
				+ "				inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
				+ "				inner join tb_nch_teacher_quali_child qc on qc.p_id=sp.id\n"
				+ "				inner join  edu_faculty_subject_mstr sm on sm.id=qc.subject\n"
				+"				inner join edu_lms_type_of_degree_mstr dm on dm.id=qc.type_of_degree::int"
				+"             left join tb_nch_teacher_other_quali_child oqc on oqc.p_id=oqc.id"
				+ "				where sp.id !=0 and principal_status= 1 "+SearchValue+" group by 1,2,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 order by id ";
		
		
		
		
		
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,"",ayush_id,teacher_code,faculty_name,university_id, institute_id, ug_pg,subject,gender,date_of_birth,experience,state_name,district,village,othquali,role,userid);
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("-----------17 jan----------"+stmt);
			
			int i = 1;
			while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(String.valueOf(i));// 0
			alist.add(rs.getString("ayush_id"));// 1
			alist.add(rs.getString("teacher_code"));// 2
			alist.add(rs.getString("first_name"));// 3
			alist.add(rs.getString("university_name"));// 4
			alist.add(rs.getString("institute_name"));// 5
			alist.add(rs.getString("degree_name"));// 6
			alist.add(rs.getString("subject"));// 7
			alist.add(rs.getString("gender"));// 8
			alist.add(rs.getString("date_of_birth"));// 9
			alist.add(rs.getString("yr_of_exp"));// 10
			alist.add(rs.getString("state"));// 11
			alist.add(rs.getString("district"));// 12
			alist.add(rs.getString("per_village"));// 13
			alist.add(rs.getString("name_of_exam_degree"));// 14
			
			
			
			list.add(alist);
			i++;
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
				public ArrayList<ArrayList<String>> getStatelistlogin(String state_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
//						q="select DISTINCT um.id,university_name from edu_lms_university_mstr um\n"
//								+ "inner join edu_lms_institute_reg ir on ir.university_id =um.id and um.status='1'\n"
//								+ "where ir.id =?";
						
						q="select DISTINCT state_id,state_name from edu_lms_state_mstr where state_id=? ";
						
						stmt = conn.prepareStatement(q);
						stmt.setInt(1, Integer.parseInt(state_id) );
						
						ResultSet rs = stmt.executeQuery();
						
						System.err.println("-----------18 jan----------"+stmt);
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("state_id"));// 0
							list.add(rs.getString("state_name"));// 1
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
				public ArrayList<ArrayList<String>> getfromdatelogoninfo(int userid) {
					
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
				

						
//						q="select  to_char(l.created_on,'DD/MM/YYYY') as upload_date  from logininformation l\n"
//								+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
//								+ "inner join roleinformation r on r.role_id=ro.role_id and role='Faculty_NCH' where l.userid=?";
						
						q="select p.id as mainid,to_char(fn.created_date,'DD/MM/YYYY')as upload_date,* from tb_nch_add_teacher_details p\n"
								+ "	inner join edu_lms_faculty_nch fn on fn.aadhar_card=p.aadhar_no where user_id=?";
				
						stmt = conn.prepareStatement(q);
								//stmt.setString(1, (role));
								stmt.setInt(1, userid);
								
								System.err.println("stm-------login------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("upload_date"));// 0
							list.add(rs.getString("mainid"));// 1
							
							alist.add(list);
							System.err.println("list--upload_date 20/01-"+alist);
				 
						}
				
						rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						// throw new RuntimeException(e);
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
