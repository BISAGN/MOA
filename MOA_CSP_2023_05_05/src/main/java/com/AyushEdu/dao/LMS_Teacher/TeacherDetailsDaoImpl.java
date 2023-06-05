package com.AyushEdu.dao.LMS_Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;

@Service
@Repository
public class TeacherDetailsDaoImpl implements TeacherDetailsDao{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public ArrayList<ArrayList<String>> getTypelistFromDocDataList(String doc_name) {
		
		System.out.println("doc_name-----25/08------"+doc_name);

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		String whr = "";
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q = "select d.id,d.doc_type from recr_document_mst d where d.id = ?" ; 
					
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(doc_name));
		//	stmt.setString(2, doc_type);
			
		//	System.err.println("stmt-----lajjooooo-------" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			 while (rs.next()) {
				 
		    	  ArrayList<String> list = new ArrayList<String>();
				
		    	  list.add(rs.getString("id"));//0
		    	  list.add(rs.getString("doc_type"));//1
					
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
	//	System.err.println("list------" + alist);
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getSubFromCourse(String course) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		String whr = "";
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			q = "select id,subject_name from edu_faculty_subject_mstr where fac_course_id = ? and status='1' " ; 
					
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course));
		//	stmt.setString(2, doc_type);
			
			System.err.println("stmt-----HAC-------" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			 while (rs.next()) {
				 
		    	  ArrayList<String> list = new ArrayList<String>();
				
		    	  list.add(rs.getString("id"));//0
		    	  list.add(rs.getString("subject_name"));//1
					
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
	//	System.err.println("list------" + alist);
		return alist;
	}
	

public ArrayList<ArrayList<String>> getinstitutelistbyStudent(int institute_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();
				
				q="select user_id from edu_lms_student_details sd\n"
						+ "inner join logininformation l on sd.name=l.username\n"
						+ "inner join userroleinformation ur on ur.user_id=l.userid\n"
						+ "inner join roleinformation ro on ro.role_id=ur.role_id and role='STUDENT'\n"
						+ "where l.institute_id=?";
				
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1, institute_id);

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();

					alist.add(rs.getString("user_id"));// 0
					list.add(alist);
				}
				
				rs.close();
				stmt.close();
				conn.close();
			}
			catch (SQLException e) {
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
public List<Map<String, Object>> DataTableTeacher_dtlDataList(int startPage, int pageLength, String Search,String orderColunm, String orderType, 
		String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,String per_state,String per_district,String per_village,
		String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,String yr_of_exp,String institute,String university,String status) {
	
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search, ayush_id, teacher_code, first_name, gender,date_of_birth,
			per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp,institute,university,status);
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	
	Connection conn = null;
	String q = "";
	String q1 = "";
	try {
System.out.println("university "+university);
		conn = dataSource.getConnection();
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}
		
//		q="SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
//				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp FROM tb_nch_add_teacher_details sp\n"
//				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
//				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
//				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
//				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
//				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
//				+ "	where sp.id !=0 and userid >1011  "+q1+ ""+ SearchValue +" order by id " + orderType
//				+ 	 " limit " + pageL + " OFFSET " + startPage;
		
		
		q="SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp FROM tb_nch_add_teacher_details sp\n"
				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
				+ "	where sp.id !=0 and sp.user_id="+userid+"  "+q1+ ""+ SearchValue +" order by id " + orderType
				+ 	 " limit " + pageL + " OFFSET " + startPage;
		
		
		
		
//		q="SELECT sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
//				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no FROM tb_nch_add_teacher_details sp\n"
//				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
//				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
//				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) where sp.id !=0"+q1+ ""+ SearchValue +" order by id " + orderType
//				+ 	 " limit " + pageL + " OFFSET " + startPage;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		
		stmt = setQueryWhereClause_SQL(stmt, Search, ayush_id, teacher_code, first_name, gender, date_of_birth,
				per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp, institute, university,status);
		
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
//			String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm' value='ADD' "+ADD+" title='Edit Data' >"+ //id='id_add_attHospital1'
//							"<i class='lni lni-pencil-alt'></i></a> </li> ";
//			
//
//			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f1 ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm' value='ADD' "+ADD1+" title='Delete Data' >"+ //id='id_add_attHospital1'
//							"<i class='lni lni-trash-can'></i></a> </li> ";
		
			
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
			
//			alist.add(rs.getString("id")); //0
			
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

public long DataTableTeacher_dtlDataTotalCount(String Search,String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,
		String per_state,String per_district,String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,
			String yr_of_exp,String institute,String university,String status) {
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search,ayush_id,teacher_code,first_name, gender,date_of_birth,
			per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid, yr_of_exp,institute,university,status);
	int total = 0;
	System.out.println("institute "+institute);
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		
//		q="select count(*) from (SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
//				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp FROM tb_nch_add_teacher_details sp\n"
//				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
//				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
//				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
//				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
//				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
//				+ "	where sp.id !=0 and userid >1011  "+ SearchValue +")ab";
		
		
		q="select count(*) from (SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,sp.user_id FROM tb_nch_add_teacher_details sp\n"
				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
				+ "	where sp.id !=0 and sp.user_id= "+userid+"  "+ SearchValue +")ab";
		
			
		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, ayush_id, teacher_code, first_name, gender,date_of_birth,
				per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp, institute, university,status);

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
	public String GenerateQueryWhereClause_SQL(String Search,String ayush_id, String teacher_code,String first_name, String gender, String date_of_birth,
			String per_state,String per_district, String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,
				String yr_of_exp,String institute,String university,String status) {
	
	String SearchValue = "";
	
	
	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and (  sp.ayush_id like ? or sp.teacher_code like ? or upper(sp.first_name) like ? or upper(gender_name) like ?"
				+ "or upper(s.state_name::text) like ? or upper(d.district_name::text) like ? or upper(sp.state_reg_no) like ? or upper(sp.state_board_name) like ?)"
				+ "or upper(sp.central_reg_no) like ? or upper(sp.per_village) like ? or to_char(sp.date_of_birth,'DD-MM-YYYY') like ? "
				+ "or to_char(sp.date_of_reg , 'DD-MM-YYYY') like ? ";
//		or (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) like ?";	
	}

	/// advance search
	
	if(role.equals("University_NCH")) {
		//SearchValue=" and university_userid = ?";
	}else if(role.equals("Principal_NCH")) {
		//SearchValue=" and principal_userid = ?";
	}
	else if(role.equals("Faculty_NCH")) {
		//SearchValue=" and user_id = ?";
	}
	if (!ayush_id.trim().equals("")) {
		SearchValue += " and upper(sp.ayush_id) like ? ";
	}
	if (!teacher_code.trim().equals("")) {
		SearchValue += " and upper(sp.teacher_code) like ? ";
	}
	if (!first_name.trim().equals("")) {
		SearchValue += " and upper(sp.first_name) like ? ";
	}
	if (!gender.trim().equals("0")) {
		SearchValue += " and upper(sp.gender) like ? ";
	}
	if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")){
		SearchValue += " and to_char(sp.date_of_birth,'DD/MM/YYYY')=?";
	}
	if (!per_state.trim().equals("0")) {
		SearchValue += " and upper(s.state_id::text) like ? ";
	}
	if (!per_district.trim().equals("0")) {
		SearchValue += " and upper(d.district_id::text) like ? ";
	}
	if (!state_reg_no.trim().equals("")) {
		SearchValue += " and upper(sp.state_reg_no) like ? ";
	}
	if (!state_board_name.trim().equals("")) {
		SearchValue += " and upper(sp.state_board_name) like ? ";
	}
	if (!date_of_reg.equals("") && date_of_reg != "" && !date_of_reg.equals("DD/MM/YYYY")){
		SearchValue += " and to_char(sp.date_of_reg,'DD/MM/YYYY')=?";
	}
	if (!central_reg_no.trim().equals("")) {
		SearchValue += " and upper(sp.central_reg_no) like ? ";
	}
	if (!per_village.trim().equals("")) {
		SearchValue += " and upper(sp.per_village) like ? ";

	}
	
	
	if (!yr_of_exp.trim().equals("")) {
		SearchValue += " and (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) = ? ";

	}
	if(!institute.equals("0")) {
		SearchValue += " and lo.institute_id = ? ";
		}
	if(!university.equals("0")) {
		SearchValue += " and lo.university_id = ? ";
	}



	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String ayush_id, String teacher_code,String first_name, String gender, 
		String date_of_birth,String per_state,String per_district,String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,
		String role,String userid,String yr_of_exp,String institute,String university,String status) {
	
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
		
		if(role.equals("University_NCH")) {
			//flag += 1;
			//stmt.setInt(flag,Integer.parseInt(userid));
		}else if(role.equals("Principal_NCH")) {
			//flag += 1;
			//stmt.setInt(flag, Integer.parseInt(userid));
		}
		else if(role.equals("Faculty_NCH")) {
			//flag += 1;
			//stmt.setInt(flag, Integer.parseInt(userid));
		}
		
		if (!ayush_id.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + ayush_id.toUpperCase() + "%");
		}
		if (!teacher_code.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + teacher_code.toUpperCase() + "%");
		}
		if (!first_name.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
		}
		if (!gender.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + gender.toUpperCase() + "%");
		}
		if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")) {
			flag += 1;
			stmt.setString(flag, date_of_birth);
		}
		if (!per_state.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + per_state.toUpperCase() + "%");
		}
		if (!per_district.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, "%" + per_district.toUpperCase() + "%");
		}
		if (!state_reg_no.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + state_reg_no.toUpperCase() + "%");
		}
		if (!state_board_name.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + state_board_name.toUpperCase() + "%");
		}
		if (!date_of_reg.equals("") && date_of_reg != "" && !date_of_reg.equals("DD/MM/YYYY")) {
			flag += 1;
			stmt.setString(flag, date_of_reg);
		}
		if (!central_reg_no.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + central_reg_no.toUpperCase() + "%");
		}
		if (!per_village.trim().equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + per_village.toUpperCase() + "%");
		}
		
		if (!yr_of_exp.trim().equals("")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(yr_of_exp));
		}
		if(!institute.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( institute));

			}
		if(!university.equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt( university));

		}
//		if(!status.equals("0")) {
//			flag += 1;
//			stmt.setInt(flag,Integer.parseInt( status));
//
//		}

	} catch (Exception e) {
		e.printStackTrace();
	}

	return stmt;
}

//////////////////edit child

		@Override
		public ArrayList<ArrayList<String>> getchild_idByp_id(int id) {
		
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = null;
				query = "select id from tb_nch_teacher_exp_child where p_id = ?";
		
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
		
				while (rs.next()) {
					list.add(rs.getString("id"));
					 alist.add(list);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return alist;
		}

@Override
public ArrayList<ArrayList<String>> getaddmoredata1(int updateid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

//		q = "select mp.id,mp.institute_name,mp.depart_name,mp.employment_type,mp.honorarium,mp.desig,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,"
//				+ "age(to_date, from_date) as yr_of_exp,\n"
//				+ "mp.upload_file from tb_nch_teacher_exp_child mp where mp.p_id = ? order by mp.id";
		
//		q="select mp.id,mp.institute_name,dm.department,tm.empl_type,mp.honorarium,dms.designation,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,age(to_date, from_date) as yr_of_exp,\n"
//				+ "mp.upload_file from tb_nch_teacher_exp_child mp\n"
//				+ "inner join tb_nch_department_mstr dm on dm.id=mp.depart_name::int\n"
//				+ "inner join tb_nch_employement_type_mstr tm on tm.id=mp.employment_type::int\n"
//				+ "inner join tb_nch_designation_mstr dms on dms.id=mp.desig::int\n"
//				+ "where mp.p_id = ? order by mp.id";
		
		q="select mp.id,mp.institute_name,mp.depart_name,mp.employment_type,mp.honorarium,mp.desig,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,age(to_date, from_date) as yr_of_exp,\n"
				+ "mp.upload_file from tb_nch_teacher_exp_child mp\n"
				+ "inner join tb_nch_department_mstr dm on dm.id=mp.depart_name::int\n"
				+ "inner join tb_nch_employement_type_mstr tm on tm.id=mp.employment_type::int\n"
				+ "inner join tb_nch_designation_mstr dms on dms.id=mp.desig::int\n"
				+ "where mp.p_id = ? order by mp.id";
		
		
		
//		q = "select mp.id,mp.institute_name,mp.depart_name,mp.desig,mp.from_date,mp.to_date,mp.upload_file from tb_nch_teacher_exp_child mp\n"
//				+ " where mp.p_id = ? order by mp.id";

		stmt = conn.prepareStatement(q);
				stmt.setInt(1, updateid);
				System.err.println("stm-------mmmmmmmmmmmm------"+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("institute_name"));// 0
			list.add(rs.getString("depart_name"));// 1
			list.add(rs.getString("desig"));// 2
			list.add(rs.getString("from_date"));// 3
			list.add(rs.getString("to_date"));// 4
		//	list.add(rs.getString("to_date").substring(0,10));
			list.add(rs.getString("upload_file"));// 5
			list.add(rs.getString("id"));// 6
			
			list.add(rs.getString("yr_of_exp"));// 7
			list.add(rs.getString("employment_type"));// 8
			list.add(rs.getString("honorarium"));// 9

//					
//             String honorarium=rs.getString("honorarium");//9
//					
//					if(honorarium.equals("1"))
//					{
//						list.add("Yes");// 13
//					}
//					if(honorarium.equals("2"))
//					{
//						list.add("No");// 13
//					}
		
			alist.add(list);

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
	
	System.err.println("alist---------->>>>      "+alist);
	
	
	return alist;
}




@Override
public ArrayList<ArrayList<String>> getaddmoredataforview1(int updateid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		
		q="select mp.id,mp.institute_name,dm.department,tm.empl_type,mp.honorarium,dms.designation,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,age(to_date, from_date) as yr_of_exp,\n"
				+ "mp.upload_file from tb_nch_teacher_exp_child mp\n"
				+ "inner join tb_nch_department_mstr dm on dm.id=mp.depart_name::int\n"
				+ "inner join tb_nch_employement_type_mstr tm on tm.id=mp.employment_type::int\n"
				+ "inner join tb_nch_designation_mstr dms on dms.id=mp.desig::int\n"
				+ "where mp.p_id = ? order by mp.id";
		


		stmt = conn.prepareStatement(q);
				stmt.setInt(1, updateid);
				System.err.println("stm-------mmmmmmmmmmmm------"+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("institute_name"));// 0
			list.add(rs.getString("department"));// 1
			list.add(rs.getString("designation"));// 2
			list.add(rs.getString("from_date"));// 3
			list.add(rs.getString("to_date"));// 4
		//	list.add(rs.getString("to_date").substring(0,10));
			list.add(rs.getString("upload_file"));// 5
			list.add(rs.getString("id"));// 6
			
			list.add(rs.getString("yr_of_exp"));// 7
			list.add(rs.getString("empl_type"));// 8
			//list.add(rs.getString("honorarium"));// 9

				
             String honorarium=rs.getString("honorarium");//9
					
					if(honorarium.equals("1"))
					{
						list.add("Yes");// 13
					}
					if(honorarium.equals("2"))
					{
						list.add("No");// 13
					}
		
			alist.add(list);

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
	
	System.err.println("alist---------->>>>      "+alist);
	
	
	return alist;
}



@Override
public ArrayList<ArrayList<String>> getaddmoredata2(int updateid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select ds.id,ds.doc_name,ds.doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
				+ " where ds.p_id = ? order by ds.id";

		stmt = conn.prepareStatement(q);
				stmt.setInt(1, updateid);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("doc_name"));// 0
			list.add(rs.getString("doc_id"));// 1
			list.add(rs.getString("upload_doc"));// 2
			list.add(rs.getString("id"));// 3
		
			alist.add(list);
// System.err.println("list--meerangi-"+alist);
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

/////////////////////Riddhi_18_7_22
@Override
public ArrayList<ArrayList<String>> getaddmoredata3(int updateid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;


		
		q = "select tq.id,tq.p_id,tq.otheruniversity,tq.type_of_degree,tq.course,tq.subject,tq.name_of_institute,tq.affiliated_university,tq.month_and_year,dm.type_of_degree as tod,\n"
				+ "dm1.course_name,s.subject_name\n"
				+ "from tb_nch_teacher_quali_child tq\n"
				+" inner join edu_lms_type_of_degree_mstr dm on dm.id = tq.type_of_degree::int\n"
				+ "inner join edu_faculty_course_mstr dm1 on dm1.id = tq.course::int\n"
				+ "left join edu_faculty_subject_mstr s on s.id = tq.subject\n"
				+ "where tq.p_id = ? order by tq.id";
		
//		q="select tq.id,tq.otheruniversity,tq.type_of_degree,tq.course,tq.subject,ir.institute_name,um.university_name,tq.month_and_year,dm.type_of_degree as tod,\n"
//				+ "dm1.course_name,s.subject_name\n"
//				+ "from tb_nch_teacher_quali_child tq\n"
//				+ " inner join edu_lms_type_of_degree_mstr dm on dm.id = tq.type_of_degree::int\n"
//				+ "inner join edu_faculty_course_mstr dm1 on dm1.id = tq.course::int\n"
//				+ "inner join edu_faculty_subject_mstr s on s.id = tq.subject\n"
//				+ "inner join edu_lms_university_mstr um on um.id=tq.affiliated_university::int\n"
//				+ "inner join edu_lms_institute_reg ir on ir.id=tq.name_of_institute::int\n"
//				+ "where tq.p_id = ? order by tq.id";
		
		
		stmt = conn.prepareStatement(q);
				stmt.setInt(1, updateid);
				System.err.println("stm-------getaddmoredata3------"+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("type_of_degree"));// 1
			list.add(rs.getString("course"));// 2
			list.add(rs.getString("subject"));// 3
			list.add(rs.getString("name_of_institute"));// 4
			list.add(rs.getString("affiliated_university"));// 5
			list.add(rs.getString("month_and_year"));// 6
			list.add(rs.getString("tod"));// 7
			list.add(rs.getString("course_name"));// 8
			list.add(rs.getString("subject_name"));// 9
		list.add(rs.getString("otheruniversity"));// 10
		list.add(rs.getString("p_id"));// 11
			
		
			alist.add(list);

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



@Override
public ArrayList<ArrayList<String>> getaddmoredata5(int updateid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;


		
//		q = "select tq.id,tq.otheruniversity,tq.type_of_degree,tq.course,tq.subject,tq.name_of_institute,tq.affiliated_university,tq.month_and_year,dm.type_of_degree as tod,\n"
//				+ "dm1.course_name,s.subject_name\n"
//				+ "from tb_nch_teacher_quali_child tq\n"
//				+" inner join edu_lms_type_of_degree_mstr dm on dm.id = tq.type_of_degree::int\n"
//				+ "inner join edu_faculty_course_mstr dm1 on dm1.id = tq.course::int\n"
//				+ "inner join edu_faculty_subject_mstr s on s.id = tq.subject\n"
//				+ "where tq.p_id = ? order by tq.id";
		
		q="select tq.id,tq.p_id,tq.otheruniversity,tq.type_of_degree,tq.course,tq.subject,ir.institute_name,\n"
				+ "case when tq.affiliated_university::text !='OTHER' then um.university_name else tq.affiliated_university::text end as university_name ,tq.month_and_year,dm.type_of_degree as tod,\n"
				+ "dm1.course_name,s.subject_name\n"
				+ "from tb_nch_teacher_quali_child tq\n"
				+ " inner join edu_lms_type_of_degree_mstr dm on dm.id = tq.type_of_degree::int\n"
				+ "inner join edu_faculty_course_mstr dm1 on dm1.id = tq.course::int\n"
				+ "inner join edu_faculty_subject_mstr s on s.id = tq.subject\n"
				+ "left join edu_lms_university_mstr um on um.id::text=tq.affiliated_university::text\n"
				+ "inner join edu_lms_institute_reg ir on ir.id=tq.name_of_institute::int\n"
				+ "where tq.p_id = ? order by tq.id";
		
		
		stmt = conn.prepareStatement(q);
				stmt.setInt(1, updateid);
				System.err.println("stm-------getaddmoredata3----------"+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("type_of_degree"));// 1
			list.add(rs.getString("course"));// 2
			list.add(rs.getString("subject"));// 3
			list.add(rs.getString("institute_name"));// 4
			list.add(rs.getString("university_name"));// 5
			list.add(rs.getString("month_and_year"));// 6
			list.add(rs.getString("tod"));// 7
			list.add(rs.getString("course_name"));// 8
			list.add(rs.getString("subject_name"));// 9
		list.add(rs.getString("otheruniversity"));// 10
		list.add(rs.getString("p_id"));// 11
			
		
			alist.add(list);

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


			
			@Override
			public ArrayList<ArrayList<String>> getchild_idByp_idDegree(int id) {
			
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				ArrayList<String> list = new ArrayList<String>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String query = null;
					query = "select id from tb_nch_degree_and_support_doc_child where p_id = ?";
			
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
			
					while (rs.next()) {
						list.add(rs.getString("id"));
						 alist.add(list);
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return alist;
			}

			////////////////////view
			
			@Override
			public ArrayList<ArrayList<String>> View_getaddmoredata2(int updateid) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
			
					q = "select ds.id,d.doc_name,d.doc_type as doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
							+ "inner join recr_document_mst d on d.id = cast(ds.doc_name as integer)\n"
							+ "where ds.p_id = ? order by ds.id";
					
			//		q = "select ds.id,ds.doc_name,ds.doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
			//				+ " where ds.p_id = ? order by ds.id";
			
					stmt = conn.prepareStatement(q);
							stmt.setInt(1, updateid);
			
					ResultSet rs = stmt.executeQuery();
			
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("doc_name"));// 0
						list.add(rs.getString("doc_id"));// 1
						list.add(rs.getString("upload_doc"));// 2
						list.add(rs.getString("id"));// 3
					
						alist.add(list);
			// System.err.println("list--meerangi-"+alist);
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

			public String getFilePathQueryForUpload_file(String id){
			
				String whr = "";
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String query = null;
					query = "select upload_file from tb_nch_teacher_exp_child \n"
							+ " where id = ? order by id";
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						whr = rs.getString("upload_file");
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return whr;
			}

			public String getFilePathQueryForDoc(String id){
			
				String whr = "";
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String query = null;
					query = " select upload_doc from tb_nch_degree_and_support_doc_child where id = ? order by id";
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						whr = rs.getString("upload_doc");
					}
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return whr;
			}

			public List<Map<String,Object>> getAllPersdetails(int userid) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			    Connection conn = null;
			    try{          
			    	conn = dataSource.getConnection();
			//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
			//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
			//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
			    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
			    			+ "where user_id = ?";
			        PreparedStatement stmt = conn.prepareStatement(sq1);
			        stmt.setInt(1, userid);
			        ResultSet rs = stmt.executeQuery();  
			        System.err.println("STMT-----"+stmt);
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
			   }catch(SQLException e){
				   e.printStackTrace();
			   }        
			   return list;
			}

				public List<Map<String,Object>> getTeacherchild(int userid) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				    Connection conn = null;
				    try{          
				    	conn = dataSource.getConnection();
				    	
				    	
				    	String sq1="select id from tb_nch_teacher_exp_child ec where p_id=? ";
				    		
				        PreparedStatement stmt = conn.prepareStatement(sq1);
				        stmt.setInt(1, userid);
				        ResultSet rs = stmt.executeQuery();  
				        System.err.println("STMT-----"+stmt);
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
				   }catch(SQLException e){
					   e.printStackTrace();
				   }        
				   return list;
				}
					
					public List<Map<String,Object>> getdegreeandsupportchild(int userid) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					    Connection conn = null;
					    try{          
					    	conn = dataSource.getConnection();
					    	
					    	String sq1="select id from tb_nch_degree_and_support_doc_child ec where p_id=? ";
					    	
					        PreparedStatement stmt = conn.prepareStatement(sq1);
					        stmt.setInt(1, userid);
					        ResultSet rs = stmt.executeQuery();  
					        System.err.println("STMT-----"+stmt);
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
					   }catch(SQLException e){
						   e.printStackTrace();
					   }        
					   return list;
					}

							/////////////////////////tushar_11_07_22//teacher deatils-pdf-->>>
					public ArrayList<ArrayList<String>> getfacultyList(int fId) {
							
								ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
								Connection conn = null;
								String q = "";
								String qry = " where f.id=?";
							
								try {
									conn = dataSource.getConnection();
									PreparedStatement stmt = null;
							
									// System.out.println("userId"+user_id);
							//		q="select * from tb_nch_add_teacher_details";
									q="SELECT sm.state_name as per_state, dm.district_name as per_district,sm.state_name as present_state, dm.district_name as present_district,lm.gender_name as gender,\n"
											+ "* FROM public.tb_nch_add_teacher_details td\n"
											+ "inner join edu_lms_state_mstr sm on sm.state_id=td.per_state\n"
											+ "inner join edu_lms_district_mstr dm on dm.district_id=td.per_district\n"
											+ "inner join edu_lms_gender_mstr lm on lm.id::text=td.gender\n"
											+ "where td.user_id=? ";
									
									stmt = conn.prepareStatement(q);
									stmt.setInt(1, fId);
							
									ResultSet rs = stmt.executeQuery();
									System.out.println("-----------main---->>> "+stmt);
									while (rs.next()) {
							
							//			System.out.println("xcvbhs");
							
										ArrayList<String> list = new ArrayList<String>();
							
										list.add(rs.getString("first_name"));//0
										list.add(rs.getString("middle_name"));//1
										list.add(rs.getString("last_name"));//2
										list.add(rs.getString("gender"));//3
										list.add(rs.getString("date_of_birth"));//4
										list.add(rs.getString("father_name"));//5
										list.add(rs.getString("mother_name"));//6	
										list.add(rs.getString("spouse_name"));//7
										list.add(rs.getString("mobile_no"));//8
										list.add(rs.getString("email"));//9
										list.add(rs.getString("aadhar_no"));//10
										list.add(rs.getString("pan_no"));//11
							//			list.add(rs.getString("academic_quali"));//12
										//String academic_quali= rs.getString("academic_quali");
							//			if(academic_quali.equals("1"))
							//			{
							//				list.add("Degree");// 2
							//			}
							//			if(academic_quali.equals("2"))
							//			{
							//				list.add("MD");// 2
							//			}
							//			if(academic_quali.equals("3"))
							//			{
							//				list.add("PhD");// 2
							//			}
										
										list.add(rs.getString("present_add_line1"));//13
										list.add(rs.getString("present_add_line2"));//14
										list.add(rs.getString("present_state"));//15
										list.add(rs.getString("present_district"));//16
										list.add(rs.getString("present_village"));//17
										list.add(rs.getString("present_pincode"));//18
										list.add(rs.getString("present_phn_no"));//19
										
										list.add(rs.getString("per_add_line1"));//20
										list.add(rs.getString("per_add_line2"));//21
										list.add(rs.getString("per_state"));//22
										list.add(rs.getString("per_district"));//23
										list.add(rs.getString("per_village"));//24
										list.add(rs.getString("per_pincode"));//25
										list.add(rs.getString("per_phn_no"));//26
										
										list.add(rs.getString("state_reg_no"));//27
										list.add(rs.getString("state_board_name"));//28
										list.add(rs.getString("date_of_reg"));//29
										list.add(rs.getString("central_reg_no"));//30
										list.add(rs.getString("adjunct_registration_no"));//31
										list.add(rs.getString("adjunct_state_no"));//31
										list.add(rs.getString("state_validity_upto"));//32
										
										list.add(rs.getString("direct_reg_no"));//33
										list.add(rs.getString("direct_date_of_reg"));//34
										list.add(rs.getString("name_of_department"));//35
										list.add(rs.getString("direct_validity_upto"));//36
										
										//adjunct_registration_no
										
										alist.add(list);
							
							//			System.out.println("hi   " + alist.get(0).toString());
							
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
								
	

				public ArrayList<ArrayList<String>> getpreviousexperiancelist(int pId) {
						
						ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						String qry = " where p.id=?";
						
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
						
						//	q="select * from tb_nch_teacher_exp_child";
							
						//	q="select tc.institute_name,tc.depart_name,tc.desig,TO_CHAR(tc.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(tc.to_date, 'DD/MM/YYYY') as to_date,tc.honorarium,tc.employment_type\n"
						//			+ "from tb_nch_add_teacher_details p\n"
						//			+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=p.id\n"
						//			+ "where p.user_id=?";
							
							q="select tc.institute_name,dm.department,tc.desig,TO_CHAR(tc.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(tc.to_date, 'DD/MM/YYYY') as to_date,tc.honorarium,tc.employment_type\n"
									+ "			from tb_nch_add_teacher_details p\n"
									+ "			inner join tb_nch_teacher_exp_child tc on tc.p_id=p.id\n"
									+ "            inner join tb_nch_department_mstr dm on dm.id=tc.depart_name::int\n"
									+ "			where p.user_id=?";
							
							stmt = conn.prepareStatement(q);
							stmt.setInt(1, pId);
							ResultSet rs = stmt.executeQuery();
							
							System.out.println("child1-------->> "+stmt);
							while (rs.next()) {
						
								ArrayList<String> list = new ArrayList<String>();
						
								list.add(rs.getString("institute_name"));//1
								list.add(rs.getString("department"));//2
						
						
						String designation=rs.getString("desig");//3
						
								if(designation.equals("1"))
								{
									list.add("Demonstrator");
								}
								if(designation.equals("2"))
								{
									list.add("Tutor");
								}
								if(designation.equals("3"))
								{
									list.add("Reader");
								}
								if(designation.equals("4"))
								{
									list.add("Lecturer");
								}
								if(designation.equals("5"))
								{
									list.add("Assistant Professor");
								}
								if(designation.equals("6"))
								{
									list.add("Associate Professor");
								}
								if(designation.equals("7"))
								{
									list.add("Associate Professor");
								}
								//list.add(rs.getString("desig"));
								list.add(rs.getString("from_date"));//4
								list.add(rs.getString("to_date"));//5
								
								String honorarium=rs.getString("honorarium");//6
								if(honorarium.equals("1"))
								{
									list.add("Yes");// 3
								}
								if(honorarium.equals("2"))
								{
									list.add("No");// 3
								}
								String employment_type=rs.getString("employment_type");//7
								if(employment_type.equals("1"))
								{
									list.add("Full Time");// 3
								}
								if(employment_type.equals("2"))
								{
									list.add("Part Time");// 3
								}
								if(employment_type.equals("3"))
								{
									list.add("Guest Faculty");// 3
								}
						
								alist1.add(list);
						
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
						return alist1;
				}


					
					public ArrayList<ArrayList<String>> getdatalist(int pId) {
					
						ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						String qry = " where p.id=?";
					
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
					
							q="select dm.doc_name,dm.doc_type from tb_nch_add_teacher_details p\n"
									+ "inner join tb_nch_degree_and_support_doc_child dc on dc.p_id=p.id\n"
									+ "inner join recr_document_mst dm on dm.id::text=dc.doc_name\n"
									+ "where p.user_id=?";
							
					//		q="select dm.doc_name,dm.doc_type from tb_nch_degree_and_support_doc_child dc\n"
					//				+ "inner join recr_document_mst dm on dm.id::text=dc.doc_name";
							
							stmt = conn.prepareStatement(q);
							stmt.setInt(1, pId);
							
							ResultSet rs = stmt.executeQuery();
							
							System.err.println("child2-------->> "+stmt);
							
							while (rs.next()) {
					
					
								ArrayList<String> list = new ArrayList<String>();
					
								list.add(rs.getString("doc_name"));//1
								list.add(rs.getString("doc_type"));//2
					//			list.add(rs.getString("upload_doc"));//3
					//			list.add(rs.getString("from_date"));//4
								
					
					
								alist1.add(list);
					
					//			System.out.println("hi   " + alist.get(0).toString());
					
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
						return alist1;
					}


					public ArrayList<ArrayList<String>> getmedicalpdf(int pId) {
					
						ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						String qry = " where p.id=?";
					
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
					
					
							
					
							
							q="select dm.type_of_degree,cm.course_name,sm.subject_name,\n"
									+ "						case when dc.affiliated_university='OTHER'  then dc.otheruniversity  else um.university_name  end,\n"
									+ "						ir.institute_name,dc.month_and_year from tb_nch_add_teacher_details p\n"
									+ "						inner join tb_nch_teacher_quali_child dc on dc.p_id=p.id\n"
									+ "						inner join edu_lms_type_of_degree_mstr dm on dm.id::int=dc.type_of_degree::int\n"
									+ "						inner join edu_faculty_course_mstr cm on cm.id=dc.course::int\n"
									+ "						inner join edu_faculty_subject_mstr sm on sm.id=dc.subject::int\n"
									+ "						inner join edu_lms_institute_reg ir on ir.id=dc.name_of_institute::int\n"
									+ "						left join edu_lms_university_mstr um on um.id::text=dc.affiliated_university\n"
									+ "						where p.user_id=?";
							
					//		q="select dm.doc_name,dm.doc_type from tb_nch_degree_and_support_doc_child dc\n"
					//				+ "inner join recr_document_mst dm on dm.id::text=dc.doc_name";
							
							stmt = conn.prepareStatement(q);
							stmt.setInt(1, pId);
							
							System.err.println("getmedicalpdf-------->> "+stmt);
							
							ResultSet rs = stmt.executeQuery();
							
							System.err.println("getmedicalpdf-------->> "+stmt);
							
							while (rs.next()) {
					
					
								ArrayList<String> list = new ArrayList<String>();
					
								list.add(rs.getString("type_of_degree"));//0
								list.add(rs.getString("course_name"));//1
								list.add(rs.getString("subject_name"));//2
								list.add(rs.getString("institute_name"));//3
								list.add(rs.getString("university_name"));//4
								list.add(rs.getString("month_and_year"));//5
								//list.add(rs.getString("affiliated_university"));//6
								
					
					
								alist1.add(list);
					
					//			System.out.println("hi   " + alist.get(0).toString());
					
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
						return alist1;
					}


					public ArrayList<ArrayList<String>> getothermedicalpdf(int pId) {
					
						ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						String qry = " where p.id=?";
					
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
					
							
							q=" select oqc.name_of_exam_degree,oqc.subject,oqc.name_of_uni_inst,oqc.name_of_aff_uni,oqc.month_and_year from tb_nch_add_teacher_details p\n"
									+ "                 inner join tb_nch_teacher_other_quali_child oqc on oqc.p_id=p.id\n"
									+ "                 where p.user_id=? ";
							
					
							
							stmt = conn.prepareStatement(q);
							stmt.setInt(1, pId);
							
							ResultSet rs = stmt.executeQuery();
							
							System.err.println("getothermedicalpdf-------->> "+stmt);
							
							while (rs.next()) {
					
					
								ArrayList<String> list = new ArrayList<String>();
					
								list.add(rs.getString("name_of_exam_degree"));//1
								list.add(rs.getString("subject"));//2
								list.add(rs.getString("name_of_uni_inst"));//3
								list.add(rs.getString("name_of_aff_uni"));//4
								list.add(rs.getString("month_and_year"));//3
								
								
					
					
								alist1.add(list);
					
					//			System.out.println("hi   " + alist.get(0).toString());
					
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
						return alist1;
					}



			//////////	//----rahul_11_07_22
			
			@Override
			public ArrayList<ArrayList<String>> getUniversitylist() {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
			
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
			
					q = " select university_id,login_name from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
							+ "	inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";
			
					stmt = conn.prepareStatement(q);
			//		stmt.setInt(1, userid);
			
			//System.out.println("------getUniversitylist-------stmt----"+stmt);
			//System.err.println("------getUniversitylist-------stmt----"+stmt);
			
					ResultSet rs = stmt.executeQuery();
			
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("university_id"));// 0
						list.add(rs.getString("login_name"));// 1
			//list.add(rs.getString("upload_doc"));// 2
			//list.add(rs.getString("id"));// 3
			
						alist.add(list);
			//System.err.println("list--meerangi-"+alist);
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

					@Override
					public ArrayList<ArrayList<String>> getinstitutelist(String userid) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
					
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
					
					//q=" select userid,login_name from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
					//+ "	inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";
					
							q = "select institute_id,login_name from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
									+ "	inner join roleinformation ro on ro.role_id=ul.role_id \n"
									+ "	and role='Institute_NCH' and university_id=(select university_id from logininformation where userid=? )";
					
							stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(userid));
					
							System.out.println("------getinstitutelist-------stmt----" + stmt);
							System.err.println("------getinstitutelist-------stmt----" + stmt);
					
							ResultSet rs = stmt.executeQuery();
					
							while (rs.next()) {
								ArrayList<String> list = new ArrayList<String>();
								list.add(rs.getString("institute_id"));// 0
								list.add(rs.getString("login_name"));// 1
					//list.add(rs.getString("upload_doc"));// 2
					//list.add(rs.getString("id"));// 3
					
								alist.add(list);
					//System.err.println("list--meerangi-"+alist);
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



				@Override
				public ArrayList<ArrayList<String>> getUniversity_id(String userid,String rolename) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						
						
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
				
						q="select userid from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
								+ "inner join roleinformation ro on ro.role_id=ul.role_id \n"
								+ "and role=? and university_id=(select university_id from logininformation where userid=?)";
				
						stmt = conn.prepareStatement(q);
						System.err.println("stmt------------>   "+stmt);
							stmt.setString(1, rolename);
							stmt.setInt(2, Integer.parseInt(userid));
							
							System.out.println("stmt---getUniversity_id-------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
						
						
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("userid"));// 0
				
						
							alist.add(list);
				// System.err.println("list--meerangi-"+alist);
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


				@Override
				public ArrayList<ArrayList<String>> getPrincipal_id(String userid,String rolename) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
				
				
						
						q="select userid from logininformation l inner join userroleinformation ul on ul.user_id=l.userid\n"
								+ "inner join roleinformation ro on ro.role_id=ul.role_id \n"
								+ "and role=? and institute_id=(select institute_id from logininformation where userid=?)";
						
						
				//		q = "select ds.id,ds.doc_name,ds.doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
				//				+ " where ds.p_id = ? order by ds.id";
				
						stmt = conn.prepareStatement(q);
						stmt.setString(1, rolename);
						stmt.setInt(2, Integer.parseInt(userid));
				
						System.out.println("stmt---getPrincipal_id-------"+stmt);
						System.err.println("stmt-----getPrincipal_id------->   "+stmt);
						
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("userid"));// 0
				
						
							alist.add(list);
				// System.err.println("list--meerangi-"+alist);
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

				//////////////////////Rahul_19_7_22
				
				@Override
				public ArrayList<ArrayList<String>> getqualificationchildAttchment(String userid) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
						
						q="select distinct  qas.id, qas.parent_id, qas.name_of_attachment, qas.attachment,qas.certificate_type from tb_nch_teacher_quali_child qc\n"
								+ "inner join tb_nch_add_teacher_details td on td.id=qc.p_id\n"
								+ "inner join tb_nch_teacher_quali_attach_subchild qas on qas.parent_id= qc.id\n"
								+ "where user_id=? ";
						
						stmt = conn.prepareStatement(q);
								stmt.setInt(1, Integer.parseInt(userid));
								System.err.println("stm-------OTHQUALISUBCHILD------"+stmt);
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							list.add(String.valueOf(rs.getInt("parent_id")));// 1
							list.add(rs.getString("name_of_attachment"));// 2
							list.add(rs.getString("attachment"));// 3
							list.add(rs.getString("certificate_type"));// 3
							
							alist.add(list);
				
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

				@Override
				public ArrayList<ArrayList<String>> getaddmoredata4(int updateid) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
						q = "select * from tb_nch_teacher_other_quali_child toq\n"
								+ "where toq.p_id = ? order by toq.id";
						
						stmt = conn.prepareStatement(q);
								stmt.setInt(1, updateid);
								System.err.println("stm-------OTHQUALICHILD------"+stmt);
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							list.add(rs.getString("p_id"));// 1
							list.add(rs.getString("name_of_exam_degree"));// 2
							list.add(rs.getString("name_of_uni_inst"));// 3
							list.add(rs.getString("name_of_aff_uni"));// 4
							list.add(rs.getString("month_and_year"));// 5
							list.add(rs.getString("subject"));// 6
						
							alist.add(list);
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

					@Override
					public ArrayList<ArrayList<String>> getotherqualisubchild(int main_id) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
							
							
							q="select * from tb_nch_teacher_other_quali_sub_child toqsc\n"
									+ "inner join tb_nch_teacher_other_quali_child toq on toq.id=toqsc.parent_id\n"
									+ "where toq.p_id = ?";
							
							stmt = conn.prepareStatement(q);
									stmt.setInt(1,main_id);
									System.err.println("stm-------OTHQUALISUBCHILD------"+stmt);
							ResultSet rs = stmt.executeQuery();
					
							while (rs.next()) {
								ArrayList<String> list = new ArrayList<String>();
								list.add(rs.getString("id"));// 0
								list.add(String.valueOf(rs.getInt("parent_id")));// 1
								list.add(rs.getString("name_of_attachment"));// 2
								list.add(rs.getString("attachment"));// 3
								
								alist.add(list);
					
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

				@Override
				public ArrayList<ArrayList<String>> getotherquali_chlist(int updateid) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
				
						q = " select id, name_of_exam_degree,subject,name_of_uni_inst,name_of_aff_uni,month_and_year \n"
								+ "from tb_nch_teacher_other_quali_child\n"
								+ "where p_id = ? order by id";
						
				//		q = "select ds.id,ds.doc_name,ds.doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
				//				+ " where ds.p_id = ? order by ds.id";
				
						stmt = conn.prepareStatement(q);
								stmt.setInt(1, updateid);
				
								System.err.println("------15-----"+stmt);
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							list.add(rs.getString("name_of_exam_degree"));// 1
							list.add(rs.getString("subject"));// 2
							list.add(rs.getString("name_of_uni_inst"));// 3
							list.add(rs.getString("name_of_aff_uni"));// 4
							list.add(rs.getString("month_and_year"));// 5
						
							alist.add(list);
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

					@Override
					public ArrayList<ArrayList<String>> getcoursebytypeOfDegreeList(String typeofdegree) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						
						
						System.err.println("typeofdegree"+typeofdegree);
						
						try {
							conn = dataSource.getConnection();
							PreparedStatement stmt = null;
							
							q="SELECT id,course_name FROM edu_faculty_course_mstr WHERE type_degree_id = ? and status='1' \n";
							
							stmt = conn.prepareStatement(q);
									stmt.setInt(1,Integer.parseInt(typeofdegree));
									System.err.println("stm-------OTHQUALISUBCHILD------"+stmt);
							ResultSet rs = stmt.executeQuery();
					
							while (rs.next()) {
								ArrayList<String> list = new ArrayList<String>();
								list.add(rs.getString("id"));// 0
								list.add(rs.getString("course_name"));// 1
								
								alist.add(list);
					
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
					

			@Override
			public ArrayList<ArrayList<String>> getPopup_Datalistquali(String userid,String parent_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					
			
					
			//		q="select qac.id,qac.parent_id,qac.name_of_attachment,qac.attachment,qac.certificate_type from tb_nch_teacher_quali_attach_subchild qac\n"
			//				+ "inner join tb_nch_teacher_quali_child qc on qc.id=qac.parent_id\n"
			//				+ "where qc.id=?";
					
					q="select qac.id,qac.parent_id,am.doc_name,qac.attachment,qac.certificate_type from tb_nch_teacher_quali_attach_subchild qac\n"
							+ "inner join tb_nch_teacher_quali_child qc on qc.id=qac.parent_id\n"
							+ "inner join edu_doc_attachments_mstr am on am.id=qac.name_of_attachment::int\n"
							+ "where qc.id=?";
					
					
					stmt = conn.prepareStatement(q);
					stmt.setInt(1, Integer.parseInt(parent_id));
					
					
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						
						
						list.add(rs.getString("id"));// 0
						list.add(rs.getString("parent_id"));//1
						list.add(rs.getString("doc_name"));//2
						list.add(rs.getString("attachment"));//3
						//list.add(rs.getString("certificate_type"));//4
						String certificate_type=rs.getString("certificate_type");//4
						if(certificate_type.equals("0"))
						{
							list.add("-");// 3
						}
						if(certificate_type.equals("1"))
						{
							list.add("Provisional Degree Certificate");// 3
						}
						if(certificate_type.equals("2"))
						{
							list.add("Final Degree certificate");// 3
						}
						
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



							public String getFilePathQueryForDocAttSub(String id){
							
										String whr = "";
										Connection conn = null;
										try {
											conn = dataSource.getConnection();
											PreparedStatement stmt = null;
											String query = null;
										//	query = "select upload_file from tb_nch_teacher_exp_child \n"
										//			+ " where id = ? order by id";
											query="  select qas.attachment from tb_nch_teacher_quali_attach_subchild qas\n"
													+ "                 inner join tb_nch_teacher_quali_child qc on qc.id=qas.parent_id\n"
													+ "                 where qas.id=?";
											
											stmt = conn.prepareStatement(query);
											stmt.setInt(1, Integer.parseInt(id));
											ResultSet rs = stmt.executeQuery();
											
											while (rs.next()) {
												whr = rs.getString("attachment");
											}
											rs.close();
											stmt.close();
											conn.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										return whr;
							}


						@Override
						public ArrayList<ArrayList<String>> getuniversityname(int id) {
									ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
									Connection conn = null;
									String q = "";
									
									
									//System.err.println("typeofdegree"+typeofdegree);
									
									try {
										conn = dataSource.getConnection();
										PreparedStatement stmt = null;
										
										//q="SELECT id,course_name FROM edu_faculty_course_mstr WHERE type_degree_id = ? \n";
										
										q="select um.id,um.university_name from logininformation l\n"
												+ "inner join edu_lms_university_mstr um on um.id=l.university_id\n"
												+ "where userid=?";
										
										stmt = conn.prepareStatement(q);
												stmt.setInt(1,(id));
												//System.err.println("stm-------OTHQUALISUBCHILD------"+stmt);
										ResultSet rs = stmt.executeQuery();
								
										while (rs.next()) {
											ArrayList<String> list = new ArrayList<String>();
											list.add(rs.getString("id"));// 0
											list.add(rs.getString("university_name"));// 0
											
											
											alist.add(list);
								
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




						@Override
						public ArrayList<ArrayList<String>> getPopup_Datalistother(String userid,String parent_id) {
							
							
									ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
									Connection conn = null;
									String q = "";
									
									try {
									conn = dataSource.getConnection();
									PreparedStatement stmt = null;
									
									
									
									
									q="select otc.id,otc.parent_id,am.doc_name,otc.attachment from tb_nch_teacher_other_quali_sub_child otc\n"
											+ "				inner join tb_nch_teacher_other_quali_child oc on oc.id=otc.parent_id\n"
											+ "				inner join edu_doc_attachments_mstr am on am.id=otc.name_of_attachment::int\n"
											+ "				where oc.id=?";
									//
									
									
									
									
									stmt = conn.prepareStatement(q);
									stmt.setInt(1, Integer.parseInt(parent_id));
									
									System.err.println("------2209dao----"+stmt);
									
									ResultSet rs = stmt.executeQuery();
									while (rs.next()) {
										ArrayList<String> list = new ArrayList<String>();
										
										
										list.add(rs.getString("id"));// 0
										list.add(rs.getString("parent_id"));//1
										list.add(rs.getString("doc_name"));//2
										list.add(rs.getString("attachment"));//3
									
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


						public String getFilePathQueryForDocAttSubother(String id) {
						
									String whr = "";
									Connection conn = null;
									try {
									conn = dataSource.getConnection();
									PreparedStatement stmt = null;
									String query = null;
									
									
									
									query="select qas.attachment from tb_nch_teacher_other_quali_sub_child qas\n"
									+ "	 inner join tb_nch_teacher_other_quali_child qc on qc.id=qas.parent_id\n"
									+ "	  where qas.id=?";
									
									
									stmt = conn.prepareStatement(query);
									stmt.setInt(1, Integer.parseInt(id));
									ResultSet rs = stmt.executeQuery();
									
									while (rs.next()) {
									whr = rs.getString("attachment");
									}
									rs.close();
									stmt.close();
									conn.close();
									} catch (SQLException e) {
									e.printStackTrace();
									}
									return whr;
						}




			@Override
			public List<Map<String, Object>> DataTableTeacher_dtl_princi_DataList(int startPage, int pageLength, String Search,String orderColunm, String orderType, 
					String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,String per_state,String per_district,String per_village,
					String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,String yr_of_exp,String institute,String university,String status,String inst_id) {
				
				
				String SearchValue = GenerateQueryWhereClause_SQL(Search, ayush_id, teacher_code, first_name, gender,date_of_birth,
						per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp,institute,university,status);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				System.err.println("----------19 jan institute"+inst_id);
				
				Connection conn = null;
				String q = "";
				String q1 = "";
				try {
			System.out.println("university "+university);
					conn = dataSource.getConnection();
					String pageL = "";
					if (pageLength == -1) {
						pageL = "ALL";
					} else {
						pageL = String.valueOf(pageLength);
					}
		
		q="SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,principal_status,sp.status FROM tb_nch_add_teacher_details sp\n"
				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
				+ "	where sp.id !=0 and userid >1011 and principal_status= "+status+" and lo.institute_id="+inst_id+" "+q1+ ""+ SearchValue +" order by id " + orderType
				+ 	 " limit " + pageL + " OFFSET " + startPage;
		

		
		PreparedStatement stmt = conn.prepareStatement(q);
//		
//			stmt.setInt(1,Integer.parseInt(status));

		
		stmt = setQueryWhereClause_SQL(stmt, Search, ayush_id, teacher_code, first_name, gender, date_of_birth,
				per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp, institute, university,status);
		
		System.err.println("-------------princi---->>>>"+stmt);
		
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
			String f3 = "";
			
			String ul="";
			
			ul+="<ul class='buttons-group mainbtn action daobtn'>";
			String ADD = "onclick=\" if (confirm('Are You Sure You Want to Approve ?') ){approveData('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f ="<li><a class='main-btn success-btn btn-hover btn-sm btnapprove approveData' value='ADD'  title='Approve Data' >"+ //id='id_add_attHospital1'
							"<i class='lni lni-checkmark'></i></a> </li> "
								+ "<input type='hidden' id='approveId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
//
			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Enable To Edit ?') ){rejectData('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f1 ="<li><a class='main-btn active-btn btn-hover btn-sm btnedit rejectData' value='ADD'  title='Enable To Edit' >"+ //id='id_add_attHospital1'
							"<i class='lni lni-pencil-alt'></i></a> </li> "
							+ "<input type='hidden' id='RejectId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
			
			String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
					+ rs.getString("id") + "')}else{ return false;}\"";
			f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm btnview viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
							"<i class='lni lni-eye'></i></a> </li>"
							+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
			
			
			String Edit = "onclick=\"  if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
					+ rs.getString("id") + "')}else{ return false;}\"";
			f3 ="<li><a class='main-btn active-btn btn-hover btn-sm btnedit EditData' value='ADD'  title='Edit Data' >"+ //id='id_add_attHospital1'
							"<i class='lni lni-pencil-alt'></i></a> </li>"
							+ "<input type='hidden' id='editId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
			
			
			
			
			
			if(rs.getString("principal_status").equals("0") && rs.getString("status").equals("3")) {
				ul+= f2;
			}
//			else if(rs.getString("principal_status").equals("0") && rs.getString("status").equals("3")) {
//				ul+= f2;
//			}
			else if(rs.getString("principal_status").equals("0")) {
				ul+=f + " " + f1 + " " + " " + f2;
			}else if(rs.getString("principal_status").equals("1")) {
				ul+= f2+" "+f3;
			}else {
				ul+= f2;
			}
			 ul+="</ul>";
			

			countFunction+=1;
			action = ul;
			columns.put("action", action);
			
//			alist.add(rs.getString("id")); //0
			
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

		public long DataTableTeacher_dtl_princi_DataTotalCount(String Search,String ayush_id, String teacher_code, String first_name, String gender, String date_of_birth,
				String per_state,String per_district,String per_village,String state_reg_no, String state_board_name,String date_of_reg,String central_reg_no,String role,String userid,
					String yr_of_exp,String institute,String university,String status,String inst_id) {
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search,ayush_id,teacher_code,first_name, gender,date_of_birth,
					per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid, yr_of_exp,institute,university,status);
			int total = 0;
			System.out.println("institute "+institute);
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
		
		q="select count(*) from (SELECT distinct username,sp.id,sp.ayush_id,sp.teacher_code,sp.first_name,g.gender_name as gender,TO_CHAR(sp.date_of_birth , 'DD-MM-YYYY') as date_of_birth,s.state_name as state,d.district_name as district,sp.per_village,sp.state_board_name,sp.state_reg_no,\n"
				+ "TO_CHAR(sp.date_of_reg , 'DD-MM-YYYY') as date_of_reg,sp.central_reg_no,yr_of_exp,principal_status,sp.status FROM tb_nch_add_teacher_details sp\n"
				+ "	inner join edu_lms_state_mstr s on s.state_id = cast(sp.per_state as integer)\n"
				+ "	inner join edu_lms_district_mstr d on d.district_id = cast(sp.per_district as integer)\n"
				+ "	inner join edu_lms_gender_mstr g on g.id = cast(sp.gender as integer) \n"
				+ "	inner join (select p_id,sum(round(abs(from_date :: date - to_date :: date)/365.25,1)) as yr_of_exp from tb_nch_teacher_exp_child group by p_id) mp on mp.p_id = sp.id \n"
				+ "	inner join logininformation lo on lo.userid=sp.user_id\n"
				+ "	where sp.id !=0 and userid >1011 and principal_status= "+status+" and lo.institute_id="+inst_id+"    "+ SearchValue +")ab";
		
			
		PreparedStatement stmt = conn.prepareStatement(q);
	//	stmt.setInt(1,Integer.parseInt(status));
		stmt = setQueryWhereClause_SQL(stmt, Search, ayush_id, teacher_code, first_name, gender,date_of_birth,
				per_state,per_district,per_village,state_reg_no,state_board_name,date_of_reg,central_reg_no,role,userid,yr_of_exp, institute, university,status);
		
		System.err.println("-------------rahul---->>>>"+stmt);

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


				
				@Override
				public ArrayList<ArrayList<String>> getlogininformation(String role,int userid) {
					
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
				
				//		q = "select ds.id,ds.doc_name,ds.doc_id,ds.upload_doc from tb_nch_degree_and_support_doc_child ds\n"
				//				+ " where ds.p_id = ? order by ds.id";
						
						
						q="select l.aadhar_no,l.mobile_no,l.email_id,l.login_name from logininformation l\n"
								+ "inner join userroleinformation ro on ro.user_id=l.userid\n"
								+ "inner join roleinformation r on r.role_id=ro.role_id and role=?\n"
								+ "where aadhar_no  is not null and userid=? ";
				
						stmt = conn.prepareStatement(q);
								stmt.setString(1, (role));
								stmt.setInt(2, userid);
								
								System.err.println("stm-------login------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("aadhar_no"));// 0
							list.add(rs.getString("mobile_no"));// 1
							list.add(rs.getString("email_id"));// 2
							list.add(rs.getString("login_name"));// 3
						
							alist.add(list);
				 System.err.println("list--login 17/11-"+alist);
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



				@Override
				public ArrayList<ArrayList<String>> getregistrationViewdata(int mainid) {
							ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
							Connection conn = null;
							String q = "";
							
							try {
								conn = dataSource.getConnection();
								PreparedStatement stmt = null;
						
							
								q="select distinct s.state_name from tb_nch_add_teacher_details p\n"
										+ "inner join edu_lms_state_mstr s on s.state_id::text=p.state_board_name where p.id=? \n" ;
										//+ "where user_id=?";
						
								stmt = conn.prepareStatement(q);
								
										stmt.setInt(1, mainid);
										
										System.err.println("stm-------regisview------"+stmt);
						
								ResultSet rs = stmt.executeQuery();
						
								while (rs.next()) {
									ArrayList<String> list = new ArrayList<String>();
									list.add(rs.getString("state_name"));// 0
						
								
									alist.add(list);
						 System.err.println("list--login 17/11-"+alist);
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


				
				
				@Override
				public ArrayList<ArrayList<String>> getacademicexp(int userid) {
					
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;

						
//						q="select id,type_of_experience,othertype_of_exp from tb_nch_teacher_academic_experience_child\n"
//								+ "where p_id=?";
						
						q="select id,type_of_experience,othertype_of_exp,organization_name,to_char(from_date_pro,'DD/MM/YYYY')as from_date,to_char(to_date_pro,'DD/MM/YYYY')as to_date,designation_name,age(to_date_pro, from_date_pro) as yr_of_exp from tb_nch_teacher_academic_experience_child\n"
								+ "where p_id=?";
				
						stmt = conn.prepareStatement(q);
							
								stmt.setInt(1, userid);
								
								System.err.println("stm-------login------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							list.add(rs.getString("type_of_experience"));// 1
							list.add(rs.getString("othertype_of_exp"));// 2
							list.add(rs.getString("organization_name"));//3
							list.add(rs.getString("from_date"));//4
							list.add(rs.getString("to_date"));//5
							list.add(rs.getString("designation_name"));//6
							list.add(rs.getString("yr_of_exp"));//7
		
							alist.add(list);
				 System.err.println("list--login 17/11 aaaaa-"+alist);
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
				
				
				
				@Override
				public ArrayList<ArrayList<String>> getacademicexpView(int userid) {
					
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;

						
						q="select id,type_of_experience,othertype_of_exp,organization_name,to_char(from_date_pro,'DD/MM/YYYY')as from_date,to_char(to_date_pro,'DD/MM/YYYY')as to_date,designation_name,age(to_date_pro, from_date_pro) as yr_of_exp from tb_nch_teacher_academic_experience_child\n"
								+ "where p_id=?";
				
						stmt = conn.prepareStatement(q);
							
								stmt.setInt(1, userid);
								
								System.err.println("stm-------login------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							
							//list.add(rs.getString("type_of_experience"));// 1
							String certificate_type=rs.getString("type_of_experience");//1
							if(certificate_type.equals("1"))
							{
								list.add("Private Practice");// 3
							}
							if(certificate_type.equals("2"))
							{
								list.add("Research");
							}
							if(certificate_type.equals("3"))
							{
								list.add("Government");
							}
							if(certificate_type.equals("-1"))
							{
								list.add(rs.getString("othertype_of_exp"));
							}
	
							
							list.add(rs.getString("othertype_of_exp"));// 2
							list.add(rs.getString("organization_name"));//3
							list.add(rs.getString("from_date"));//4
							list.add(rs.getString("to_date"));//5
							list.add(rs.getString("designation_name"));//6
							list.add(rs.getString("yr_of_exp"));//7
							
		
							alist.add(list);
				 System.err.println("list--login 17/11- bbbb"+alist);
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
				
				
				
				
				@Override
				public ArrayList<ArrayList<String>> getacademicexpViewforPDF(int userid) {
					
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;

						
						q="select id,type_of_experience,othertype_of_exp,organization_name,to_char(from_date_pro,'DD/MM/YYYY')as from_date,to_char(to_date_pro,'DD/MM/YYYY')as to_date,designation_name,age(to_date_pro, from_date_pro) as yr_of_exp from tb_nch_teacher_academic_experience_child\n"
								+ "where p_id=?";
				
						stmt = conn.prepareStatement(q);
							
								stmt.setInt(1, userid);
								
								System.err.println("stm-------login------"+stmt);
				
						ResultSet rs = stmt.executeQuery();
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("organization_name"));// 0
							list.add(rs.getString("from_date"));//1
							list.add(rs.getString("to_date"));//2
							list.add(rs.getString("yr_of_exp"));//3
							list.add(rs.getString("designation_name"));//4
							
							String certificate_type=rs.getString("type_of_experience");//5
							if(certificate_type.equals("1"))
							{
								list.add("Private Practice");
							}
							if(certificate_type.equals("2"))
							{
								list.add("Research");
							}
							if(certificate_type.equals("3"))
							{
								list.add("Government");
							}
							if(certificate_type.equals("-1"))
							{
								list.add(rs.getString("othertype_of_exp"));
							}
	

							alist.add(list);
				
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
