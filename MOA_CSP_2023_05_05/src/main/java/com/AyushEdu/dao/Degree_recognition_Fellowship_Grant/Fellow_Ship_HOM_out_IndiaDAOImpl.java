package com.AyushEdu.dao.Degree_recognition_Fellowship_Grant;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_OUT_INDIA;

@Repository
public class Fellow_Ship_HOM_out_IndiaDAOImpl implements Fellow_ship_HOM_out_IndiaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


		public long DataTableFellow_ship_hom_out_india_Details_DataTotalCount(String Search, String country_id, String university_name, String college_id, String abbreviation, String medical_course_name, String validity_period, String digital_code) {

			
			String SearchValue = GenerateQueryWhereClause_SQL(Search, country_id, university_name, college_id, abbreviation, medical_course_name, validity_period, digital_code);
			
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q="  select count(*) from dg_rec_fellow_ship_hom_out_india";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, country_id, university_name, college_id, abbreviation, medical_course_name, validity_period, digital_code);

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
		public String GenerateQueryWhereClause_SQL(String Search, String country_id, String university_name, String college_id, String abbreviation, String medical_course_name, String validity_period, String digital_code) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += "and (upper(c.name) Like ? or upper(uni.university_name) Like ? or upper(r.institute_name) Like ? or upper(abbreviation)  Like ? \n"
						    + "or upper(hm.qualification) Like ? or TO_CHAR(validity_period , 'dd/MON/yyyy') like ? or digital_code::character varying Like ?) ";
			}
			
//			if (country_id != null && !country_id.equals("0")) {
//				SearchValue += "and u.country_id = ? ";
//			}
			if (university_name != null && !university_name.equals("0")) {
				SearchValue += "and u.university_name = ? ";
			}
			if (college_id != null && !college_id.equals("0")) {
				SearchValue += "and u.college_id = ? ";
			}
			if (abbreviation != null && !abbreviation.equals("")) {
				SearchValue += "and upper(abbreviation) Like ? ";
			}
			if (medical_course_name != null && !medical_course_name.equals("0")) {
				SearchValue += "and u.qualification = ? ";
			}
			
			if( validity_period != null && !validity_period.equals("") && !validity_period.equals("DD/MM/YYYY")) {
				SearchValue +=  "and TO_CHAR(validity_period , 'dd/mm/yyyy') = ? ";
		     }
			if (digital_code != null && !digital_code.equals("")) {
				SearchValue += "and digital_code = ? ";
			}
			
		
			return SearchValue;
		}
		

		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String country_id, String university_name, String college_id, String abbreviation, String medical_course_name, String validity_period, String digital_code) {
			int flag = 0;
			try {
				if (Search!=null &&  !Search.equals("")) {
					
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
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
					
				
					
				}
//				if (country_id != null && !country_id.equals("0")) {
//					flag += 1;
//					stmt.setInt(flag,Integer.parseInt(country_id));
//				}
				if (university_name != null && !university_name.equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(university_name));
				}
				if (college_id != null && !college_id.equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(college_id));
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
				
//				if (!digital_code.equals("0")) {
//					flag += 1;
//					stmt.setInt(flag,Integer.parseInt(digital_code));
//				}
				

			} catch (Exception e) {
			}

			return stmt;
		}
		

		public List<Map<String, Object>> DataTableFellow_ship_hom_out_india_Details_DataList(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String country_id, String university_name, String college_id,
				 String abbreviation, String medical_course_name,String validity_period, String digital_code) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search, country_id, university_name, college_id, 
					abbreviation, medical_course_name,validity_period, digital_code);
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



if(Search.equals("") && country_id == null && university_name == null && college_id == null && abbreviation == null && medical_course_name == null && validity_period == null && digital_code == null ) {
				
				q = "select ROW_NUMBER() OVER(order by u.id ASC) as sr_no,u.university_name as uni_id, u.id,u.country_id, uni.university_name, r.institute_name, u.abbreviation,concat(hm.code,'-',hm.qualification) as qualification,\n"
						+ "TO_CHAR(u.validity_period , 'dd-MON-YYYY') as validity_period,TO_CHAR(u.validity_period , 'DD/MM/YYYY') as vp, u.digital_code,u.country_name,u.college_id,u.medical_course_name\n"
						+ "from dg_rec_fellow_ship_hom_out_india u\n"
						+ "inner join edu_lms_university_mstr uni on uni.id = u.university_name\n"
						+ "inner join edu_lms_institute_reg  r on r.id = u.college_id \n"
						+ "inner join dg_rec_coding_courses_homoeopathy_mstr hm on hm.id = u.medical_course_name\n"
						+ "where u.id != 0\n"
						+ SearchValue + " ORDER BY u.id "+ orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			    q = "select ROW_NUMBER() OVER(order by u.id ASC) as sr_no,u.university_name as uni_id, u.id,u.country_id, uni.university_name, r.institute_name, u.abbreviation,concat(hm.code,'-',hm.qualification) as qualification,\n"
			    		+ "TO_CHAR(u.validity_period , 'dd-MON-YYYY') as validity_period, TO_CHAR(u.validity_period , 'DD/MM/YYYY') as vp,u.digital_code,u.country_id,u.college_id,u.medical_course_name\n"
			    		+ "from dg_rec_fellow_ship_hom_out_india u\n"
			    		+ "inner join edu_lms_university_mstr uni on uni.id = u.university_name\n"
			    		+ "inner join edu_lms_institute_reg  r on r.id = u.college_id \n"
			    		+ "inner join dg_rec_coding_courses_homoeopathy_mstr hm on hm.id = u.medical_course_name\n"
			    		+ "where u.id != 0 \n"
			    		+ SearchValue + " ORDER BY u.id " + orderType + " limit "
					    + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, country_id, university_name, college_id, abbreviation, medical_course_name, validity_period, digital_code);

			System.err.println("-----eeee-----" + stmt);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

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
							+"<input type='hidden' id='conID"+countFunction+"' value='"+rs.getString("country_id")+"'>"
							+"<input type='hidden' id='uniID"+countFunction+"' value='"+rs.getString("uni_id")+"'>"
							+"<input type='hidden' id='clgID"+countFunction+"' value='"+rs.getString("college_id")+"'>"
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

	public DG_REC_FELLOW_SHIP_HOM_OUT_INDIA getfellow_ship_hom_out_indiaByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_FELLOW_SHIP_HOM_OUT_INDIA updateid = (DG_REC_FELLOW_SHIP_HOM_OUT_INDIA) session.get(DG_REC_FELLOW_SHIP_HOM_OUT_INDIA.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	@Override
	public String updateFellow_ship_hom_out_india(DG_REC_FELLOW_SHIP_HOM_OUT_INDIA obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update DG_REC_FELLOW_SHIP_HOM_OUT_INDIA set country_id=:country_id,university_name=:university_name,"
					+ "college_id=:college_id,abbreviation=:abbreviation,medical_course_name=:medical_course_name,"
					+ "	validity_period=:validity_period,digital_code=:digital_code,modified_by=:modified_by,modified_date=:modified_date"
					+ "	where id=:id";

			Query query = session1.createQuery(hql)
			.setParameter("country_id", obj.getCountry_id())
			.setParameter("university_name", obj.getUniversity_name())
			.setParameter("college_id", (obj.getCollege_id()))
			.setParameter("abbreviation", (obj.getAbbreviation()))
			.setParameter("medical_course_name", (obj.getMedical_course_name()))
			.setParameter("validity_period", (obj.getValidity_period()))
			.setParameter("digital_code", (obj.getDigital_code()))
			.setParameter("modified_by", obj.getModified_by())
			.setParameter("modified_date", obj.getModified_date())
			.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
}
