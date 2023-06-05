package com.AyushEdu.dao.Degree_recognition_Quali_Grant;

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

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;

@Repository
public class University_In_IndiaDAOImpl implements University_In_IndiaDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	@Override
	public long DataTableUniversityDataTotalCount(String Search, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name, String validity_period, String digital_code) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="  select count(*) from dg_rec_university_in_india";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
			System.err.println("q_______" +q);
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
	public String GenerateQueryWhereClause_SQL(String Search, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name, String validity_period, String digital_code) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += "and (upper(c.name) Like ? or upper(uni.university_name) Like ? or upper(r.institute_name) Like ? or upper(abbreviation)  Like ? \n"
					    + "or upper(hm.qualification) Like ? or TO_CHAR(validity_period , 'dd/MON/yyyy') like ? or digital_code::character varying Like ?) ";
		}
		
//		if (country_name != null && !country_name.equals("0")) {
//			SearchValue += "and u.country_name = ? ";
//		}
		if (university_name != null && !university_name.equals("0")) {
			SearchValue += "and u.university_name = ? ";
		}
		if (college_name != null && !college_name.equals("0")) {
			SearchValue += "and u.college_name = ? ";
		}
		if (abbreviation != null && !abbreviation.equals("")) {
			SearchValue += "and upper(abbreviation) Like ? ";
		}
		if (medical_course_name != null && !medical_course_name.equals("0")) {
			SearchValue += "and u.medical_course_name = ? ";
		}
		
		if( validity_period != null && !validity_period.equals("") && !validity_period.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(validity_period , 'dd/mm/yyyy') = ? ";
	     }
		if (digital_code != null && !digital_code.equals("")) {
			SearchValue += "and digital_code = ? ";
		}
		
	
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name, String validity_period, String digital_code) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				
			
				
			}
//			if (country_name != null && !country_name.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag,Integer.parseInt(country_name));
//			}
			if (university_name != null && !university_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(university_name));
			}
			if (college_name != null && !college_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(college_name));
			}
			if (abbreviation != null && !abbreviation.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+abbreviation.toUpperCase()+"%");
			}
			if (medical_course_name != null && !medical_course_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(medical_course_name));
			}
			if( validity_period != null && !validity_period.equals("") && !validity_period.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, validity_period );
			}
			if (digital_code != null && !digital_code.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(digital_code));
			}
			
//			if (!digital_code.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag,Integer.parseInt(digital_code));
//			}
			

		} catch (Exception e) {
		}

		return stmt;
	}
	
	@Override
	public String updateuniversity(DG_REC_UNIVERSITY_IN_INDIA obj) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update DG_REC_UNIVERSITY_IN_INDIA set country_name=:country_name, university_name=:university_name,college_name=:college_name,"
					+ "abbreviation=:abbreviation,medical_course_name=:medical_course_name,validity_period=:validity_period,"
					+ "digital_code=:digital_code,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql)
					.setParameter("country_name", obj.getCountry_name())
					.setParameter("university_name", obj.getUniversity_name())
					.setParameter("college_name", obj.getCollege_name())
					.setParameter("abbreviation", obj.getAbbreviation())
					.setParameter("medical_course_name", obj.getMedical_course_name())
					.setParameter("validity_period", obj.getValidity_period())
					.setParameter("digital_code", obj.getDigital_code())
					.setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";		
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
	
	public List<Map<String,Object>> DataTableUniversityDataList(int startPage, int pageLength, String Search, String orderColunm, String orderType, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name , String validity_period, String digital_code) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
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
			
			if(Search.equals("") && country_name == null && university_name == null && college_name == null && abbreviation == null && medical_course_name == null && validity_period == null && digital_code == null ) {
				q = "select ROW_NUMBER() OVER(order by u.id ASC) as sr_no,u.university_name as uni_id, u.id,u.country_name, uni.university_name, r.institute_name, u.abbreviation,concat(hm.code,'-',hm.qualification) as qualification,\n"
						+ "TO_CHAR(u.validity_period , 'dd-MON-YYYY') as validity_period,TO_CHAR(u.validity_period , 'DD/MM/YYYY') as vp, u.digital_code,u.country_name,u.college_name,u.medical_course_name\n"
						+ "from dg_rec_university_in_india u\n"
						+ "inner join edu_lms_university_mstr uni on uni.id = u.university_name\n"
						+ "inner join edu_lms_institute_reg  r on r.id = u.college_name \n"
						+ "inner join dg_rec_coding_courses_homoeopathy_mstr hm on hm.id = u.medical_course_name\n"
						+ "where u.id != 0\n"
						+ SearchValue + " ORDER BY u.id "+ orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			    q = "select ROW_NUMBER() OVER(order by u.id ASC) as sr_no,u.university_name as uni_id, u.id,u.country_name, uni.university_name, r.institute_name, u.abbreviation,concat(hm.code,'-',hm.qualification) as qualification,\n"
			    		+ "TO_CHAR(u.validity_period , 'dd-MON-YYYY') as validity_period, TO_CHAR(u.validity_period , 'DD/MM/YYYY') as vp,u.digital_code,u.country_name,u.college_name,u.medical_course_name\n"
			    		+ "from dg_rec_university_in_india u\n"
			    		+ "inner join edu_lms_university_mstr uni on uni.id = u.university_name\n"
			    		+ "inner join edu_lms_institute_reg  r on r.id = u.college_name \n"
			    		+ "inner join dg_rec_coding_courses_homoeopathy_mstr hm on hm.id = u.medical_course_name\n"
			    		+ "where u.id != 0 \n"
			    		+ SearchValue + " ORDER BY u.id " + orderType + " limit "
					    + pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
			System.err.println("STMT------acd---------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm editOnclick' value='ADD' title='EditData' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='conID"+countFunction+"' value='"+rs.getString("country_name")+"'>"
								+"<input type='hidden' id='uniID"+countFunction+"' value='"+rs.getString("uni_id")+"'>"
								+"<input type='hidden' id='clgID"+countFunction+"' value='"+rs.getString("college_name")+"'>"
								+"<input type='hidden' id='abbID"+countFunction+"' value='"+rs.getString("abbreviation")+"'>"
								+"<input type='hidden' id='quaID"+countFunction+"' value='"+rs.getString("medical_course_name")+"'>"
								+"<input type='hidden' id='vpID"+countFunction+"' value='"+rs.getString("vp")+"'>"
								+"<input type='hidden' id='dcId"+countFunction+"' value='"+rs.getString("digital_code")+"'></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='DeleteData' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
	
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
	
	
	
	
//	public ArrayList<ArrayList<String>> getCollegeListdao(String university_id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//
//			q = "SELECT ir.code FROM edu_lms_institute_reg ir \n"
//					+ "	inner join edu_lms_university_mstr um on um.id = ir.university_id  where ir.university_id= ? \n";
//					
//			stmt = conn.prepareStatement(q);
//			stmt.setInt(1, Integer.parseInt(university_id)); 
//			System.err.println("TT=====" + stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//
//				list.add(rs.getString("code"));// 0
//				
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {System.err.println("e");
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}

	
	public ArrayList<ArrayList<String>> getAbbreviationdao(String institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select college_abbr FROM edu_lms_institute_reg \n"
					+ "where id = ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id)); 
			System.err.println("TT==TT===" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

			
				list.add(rs.getString("college_abbr"));// 0
				

				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {System.err.println("e");
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
