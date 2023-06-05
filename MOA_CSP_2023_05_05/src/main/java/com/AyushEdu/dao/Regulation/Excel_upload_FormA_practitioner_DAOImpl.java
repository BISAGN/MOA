package com.AyushEdu.dao.Regulation;

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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class Excel_upload_FormA_practitioner_DAOImpl implements Excel_upload_FormA_practitioner_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableEdu_Exp_excel_forma_practitioner(int startPage, int pageLength, String Search, String orderColunm,
			String orderType,String upload_date ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, upload_date);
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
			
//			q="  select e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date, \n"
//					+ "e.enrollment_no ,e.gender,ss.system_name as system,sg.degree_name as degree,to_char(e.created_date,'DD/MM/YYYY') as upload_date from reg_nch_details_a e \n"
//					+ " inner join edu_lms_system_mstr ss on (ss.id)  = e.system\n"
//					+ "  inner join edu_lms_degree_mstr sg on (sg.id)  = e.degree\n"
//					+ " where e.id!= 0  \n"
//					 +SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10,11,12  order by id " + orderType
//		                + " limit " + pageL + " OFFSET " + startPage;
			
			q=" select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name, s.state_name,e.pre_pincode, \n"
					+ "dd.district_name as permentdistrict,ss.state_name as permentstatename,e.per_pincode,\n"
					+ "e.email_id,\n"
					+ "to_char(e.dob , 'DD/MM/YYYY'), n.nationality,\n"
					+ "e.ayush_id,e.abha_no,e.state_reg_no,to_char(e.created_date , 'DD/MM/YYYY'),ss.state_name as reg_state,\n"
					+ "e.father_name, e.status,e.mo_no,\n"
					+ "  m.degree_name,sm.type_of_degree,c.month_and_year_of_degree,c.name_of_institute,sks.university_name as uni\n"
					+ "  \n"
					+ "from reg_nch_form_a_p  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
					+ "inner join edu_lms_gender_mstr ee on ee.id=e.gender\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "--inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
					+ "inner join edu_lms_state_mstr ss on    ss.state_id    = e.per_state\n"
					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
					+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
					+"inner join edu_lms_type_of_degree_mstr sm on sm.id=c.type_of_degree\n"
					+"inner join edu_lms_university_mstr sks on sks.id=c.name_of_institute::int\n"
					+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id where e.id!= 0"
					 +SearchValue+ "group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28  order by id " + orderType
	                + " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,upload_date);
			System.err.println("check the statment"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction2=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String vm1 ="";
//				if (rs.getString("role_id").equals("25")) {
//					vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//							+ "		<i class='lni lni-eye popdegreep'>"
//							+ "<input type='hidden' id='InstiaccessId"+countFunction2+"' value="+rs.getString("userid")+"></i></a> </li></ul>";
//					countFunction2+=1;
//					
//				}
				
				columns.put("vm1",vm1);	
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
	public long DataTableEdu_Exp_excel_forma_practitioner_Count(String Search,String upload_date ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,upload_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
//			q="select count(*)  from (select "
//					+"	e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date,to_char(e.created_date,'DD/MM/YYYY') as upload_date, \n"
//					+ " e.system,e.degree,e.enrollment_no,e.gender from reg_nch_details_a e \n"
//						+ " "+SearchValue+" group by 1,2,3,4,5,6,7,8,9,10,11,12 ) a ";
		
					q="select count(*)  from ( select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name, s.state_name,e.pre_pincode, \n"
							+ "dd.district_name as permentdistrict,ss.state_name as permentstatename,e.per_pincode,\n"
							+ "e.email_id,\n"
							+ "to_char(e.dob , 'DD/MM/YYYY'), n.nationality,\n"
							+ "e.ayush_id,e.abha_no,e.state_reg_no,to_char(e.created_date , 'DD/MM/YYYY'),ss.state_name as reg_state,\n"
							+ "e.father_name, e.status,e.mo_no,\n"
							+ "  m.degree_name,sm.type_of_degree,c.month_and_year_of_degree,c.name_of_institute,sks.university_name as ui\n"
							+ "  \n"
							+ "from reg_nch_form_a_p  e\n"
							+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
							+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
							+ "inner join edu_lms_gender_mstr ee on ee.id=e.gender\n"
							+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
							+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
							+ "--inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
							+ "inner join edu_lms_state_mstr ss on    ss.state_id    = e.per_state\n"
							+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
							+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
							+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
							+"inner join edu_lms_type_of_degree_mstr sm on sm.id=c.type_of_degree\n"
							+"inner join edu_lms_university_mstr sks on sks.id=c.name_of_institute::int\n"
							+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id"
							+    " where e.id!=0"+SearchValue+") a"; 
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,upload_date );
			
			
			System.err.println("check the statment"+stmt);

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
	public String GenerateQueryWhereClause_SQL(String Search,String upload_date ) throws ParseException {
		String SearchValue = "";
 
		if (Search!=null && !Search.equals("")) { 
			System.err.println("enter or not");
//			SearchValue += " and (upper(e.name) like ?"
//					+ " or upper(e.dob) like ?  "
//					+ " or upper(e.aadhar_card) like ? "
//					+ " or e.email like ?  "
//					+ " or upper(e.mobile_no) like ?  "
//					+ " or upper(e.admission_date) like ?  "
//					+ " or upper(e.enrollment_no) like ?  "
//					+ " or upper(e.gender) like ?  "
////					+ " or upper(e.admission_date) like ?  "
//					+ ")";
		}
		

		 

		if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(e.created_date , 'dd/mm/yyyy') = ? ";
	     }
				
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String upload_date ) {
		int flag = 0;
		try {
 
			if (Search!=null &&  !Search.equals("")) {
				System.err.println("INSIDE=-====");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
		 
			if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, upload_date );
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

}

