package com.AyushEdu.dao.LMS_Teacher;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class faculty_transfer_intake_DaoImpl implements Faculty_transfer_intake_Dao {
	
	
	

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<Map<String, Object>> DataTableFaculty_transfer_DataList(int startPage,
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
					+ "				where sp.id !=0 and principal_status= 1  "+SearchValue+" group by 1,2,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 order by id "+ orderType +"  limit "+pageL+" OFFSET "+startPage;
			
			
			
		
			
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
				
				
				String chekboxaction = "";

				String st = rs.getString(1);

				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)// 13
						+ "' name='cbox'  /> <input type='hidden' id='nrCHid" + rs.getObject(1) + "' value='" + st
						+ "'> " + "<input type='hidden' id='notif" + rs.getObject(1) + "' value='" + rs.getObject(5)
						+ "'>" + "<input type='hidden' id='inst_id" + rs.getObject(1) + "' value='" + rs.getObject(8)
						+ "'>";

				String CheckboxId = "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)// 14
						+ "' value='" + rs.getObject(1) + "'   />";

				chekboxaction += Checkbox;
				// end
				columns.put("chekboxaction", chekboxaction);
				
				
				

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";

			
				
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
	public long DataTableFaculty_transfer_DataTotalCount(String Search, String ayush_id, String teacher_code,
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
					+ "				where sp.id !=0 and principal_status= 1  "+SearchValue+" group by 1,2,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)ab";
			
				
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
		SearchValue += " and sd.institute_id != ? ";
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

		

	} catch (Exception e) {
		e.printStackTrace();
	}

	return stmt;
}



				@Override
				public ArrayList<ArrayList<String>> getuseridfrommainid(int mainid) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;

	
						q="select user_id from tb_nch_add_teacher_details  where id=?";
						
						stmt = conn.prepareStatement(q);
						stmt.setInt(1, (mainid) );
						
						ResultSet rs = stmt.executeQuery();
						
						System.err.println("-----------18 jan----------"+stmt);
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("user_id"));// 0
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


					
				//	AUTOCOMPLETE FIRST NAME NAME
				public List<Map<String, Object>> TeachercodeAuto(String autoString,String role) {
					
					System.err.println("**********************************");
					System.err.println(autoString+"\n"+role+"\n");
					System.err.println("**********************************");
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					

					
					try {

						conn = dataSource.getConnection();
						
//						q = "select id,first_name \n"
//								+ "from ncism_pract_pro_reg_personal_dtls\n"
//								+ "where lower(first_name)  like ? ";
						
						q="select id,teacher_code from tb_nch_add_teacher_details where lower(teacher_code)  like ? ";

						PreparedStatement stmt = conn.prepareStatement(q);
						
						stmt.setString(1,"%"+autoString.toLowerCase()+"%");
						
						System.err.println("stmt---Teacher Code AUTO--->"+stmt);
						
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
				public ArrayList<ArrayList<String>> getuseridforupdateinst(int mainid) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;

	

						
						q="	select p.user_id from tb_nch_add_teacher_details p\n"
								+ "	inner join edu_lms_faculty_nch fn on fn.aadhar_card=p.aadhar_no\n"
								+ "	inner join logininformation l on l.aadhar_no = p.aadhar_no where p.id=?";
						
						stmt = conn.prepareStatement(q);
						stmt.setInt(1, (mainid) );
						
						ResultSet rs = stmt.executeQuery();
						
						System.err.println("-----------18 jan----------"+stmt);
				
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("user_id"));// 0
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









}
