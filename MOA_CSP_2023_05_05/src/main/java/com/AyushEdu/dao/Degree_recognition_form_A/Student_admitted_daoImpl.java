package com.AyushEdu.dao.Degree_recognition_form_A;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Degree_recognition_form_A.*;


@Repository
public class Student_admitted_daoImpl implements Student_admitted_dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	//---------------------- Admitted ---------------------//
	
	public List<Map<String, Object>> getFilter_Admitted_Student_list(int startPage, int pageLength,String Search, String orderColunm,
			String orderType,int university_id,int user_id,String institute_status,String student_name,String date_of_admission, String rank,String marks,String all_india,
			String state,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
			String date_of_intern_compl,String remarks_form_b) {
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search, student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
			court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

					conn = dataSource.getConnection();
		
					 q="select a.id,a.user_id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission,"
					 		+ "a.court_order,a.court_order_file,\r\n"
							 + "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl,"
							 + "to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b,a.reject_remarks \r\n"
							 + "from dg_rec_admitted_student_child  a where a.university_id=? and a.user_id=? and a.inst_status=? " + SearchValue + " ORDER BY a.id "+ orderType + " limit "
								+ pageLength + " OFFSET " + startPage; //where and inst_status=0 and university_approved_status=0 and council_approved_status=0 
			
					 PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
								court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
						stmt.setInt(1, university_id);
						stmt.setInt(2, user_id);
						stmt.setInt(3, Integer.parseInt(institute_status));
						 System.err.println("INST_EDIT_1----"+stmt);
						ResultSet rs = stmt.executeQuery();
					
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;
						
						int countFunction = 1;
						int countFunction2 =1;
						while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
						columns.put("ser", ++j);
						for (int i = 1; i <= columnCount; i++) {
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						}
						String f = "";
						String action = "";
						String f1 = "";
						String vmp1 ="";
						String ul="";
						ul+="<ul class='buttons-group mainbtn action daobtn'>";
						
						if(institute_status=="-1" || institute_status=="0") {
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDStudent' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
					   }
						else {
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDStudent' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
						}
						String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
								+ rs.getString("id") + "') }else{ return false;}\"";
						vmp1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download File' >\n"
								+ "		<i class='lni lni-download pdfdown'>"
								+ "<input type='hidden' id='DCounpdf"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
						ul+=f + " " + f1 ;
						ul+="</ul>";
						
						action = ul;
						countFunction+=1;
						countFunction2+=1;

						columns.put("action", action);
						columns.put("vmp1",vmp1);
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
	
	//---------------- Admitted List Count -----------------//
		@Override
		public long getFilter_Admitted_StudentListCount(String search, int university_id,int user_id,String institute_status,String student_name,
				String date_of_admission, String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) {
			
			String SearchValue = GenerateQueryWhereClause_SQL(search, student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
					court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
			
			
//			System.err.println("---getFilter_Admitted_StudentListCount-");
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.id,a.user_id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission,a.rank,a.marks,a.all_india,a.state,a.admission_authority,a.court_order,a.court_order_file,\r\n"
						+ "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl ,to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b,a.reject_remarks \r\n"
						+ "from dg_rec_admitted_student_child  a where a.university_id=? and a.user_id=? and inst_status=? ) a  ";//and inst_status=0 and university_approved_status=0 and council_approved_status=0
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,search,student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
				 stmt.setInt(1, university_id);
				 stmt.setInt(2, user_id);
				 stmt.setInt(3, Integer.parseInt(institute_status));
				    System.err.println("INST_EDIT_Count_1----"+stmt);
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
		public String GenerateQueryWhereClause_SQL(String Search,String student_name,String date_of_admission, String rank,String marks,String all_india,
				String state,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
				String date_of_intern_compl,String remarks_form_b) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(student_name) Like ? or TO_CHAR(date_of_admission , 'DD-MON-YYYY') like ? "
						+ " or rank::character varying like ? or marks::character varying like ? or upper(all_india) Like ? "
						+ " or upper(state) Like ? or upper(admission_authority) Like ? or upper(court_order) Like ? "
						+ " or TO_CHAR(date_of_enroll_university , 'DD-MON-YYYY') like ? "
						+ "or uni_enroll_number::character varying like ? or TO_CHAR(date_of_intern_compl , 'DD-MON-YYYY') like ?"
						+ " or upper(remarks_form_b) Like ?) ";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
				String rank,String marks,String all_india,String state,String admission_authority,String court_order,String date_of_enroll_university,
				String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) {
			int flag = 3;
			try {
				if (Search!=null &&  !Search.equals("")) {
					
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
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
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
					
					
					
				}
				if (student_name != null && !student_name.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
				}
				if( date_of_admission != null && !date_of_admission.equals("") && !date_of_admission.equals("DD-MON-YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_admission );
				}
				if (rank != null && !rank.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(rank));
				}
				if (marks != null && !marks.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(marks));
				}
				if (all_india != null && !all_india.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+all_india.toUpperCase()+"%");
				}
				if (state != null && !state.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+state.toUpperCase()+"%");
				}
				if (admission_authority != null && !admission_authority.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+admission_authority.toUpperCase()+"%");
				}
				if (court_order != null && !court_order.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+court_order.toUpperCase()+"%");
				}
				if( date_of_enroll_university != null && !date_of_enroll_university.equals("") && !date_of_enroll_university.equals("DD-MON-YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_enroll_university );
				}
				if (uni_enroll_number != null && !uni_enroll_number.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(uni_enroll_number));
				}
				if( date_of_intern_compl != null && !date_of_intern_compl.equals("") && !date_of_intern_compl.equals("DD-MON-YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_intern_compl );
				}
				if (remarks_form_b != null && !remarks_form_b.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+remarks_form_b.toUpperCase()+"%");
				}

			} catch (Exception e) {
			}

			return stmt;
		}
	//---------------------- UG Form C ---------------------//
	
	public List<Map<String, Object>> getFilter_Hospital_Attached_list(int startPage, int pageLength,String Search, String orderColunm,
			String orderType,int university_id,int user_id,String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
			String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
			String remarks_form_c) {
		
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
				mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
		
				List<Map<String, Object>> alist = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q = "";
				try {

						conn = dataSource.getConnection();
			
						 q=" select CASE when inst_status=-1 then a.reject_remarks end , a.id,a.name_homoeopathic_medical_clg,"
						 		+ "a.attached_homoeopath_hospital,a.super_speciality_hospital, to_char(a.mou_date,'DD-MON-YYYY') as mou_date,"
						 		+ "a.copy_of_mou,a.name_of_hospital_staff,a.designation, \r\n"
								 +"a.qualification,a.fulltime_parttime,a.remarks_form_c,a.reject_remarks \r\n"
								 +"from dg_rec_hospital_attached_child a \n"
								 +"where a.university_id=? and a.user_id=? and inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage;// inst_status=0 and university_approved_status=0 and council_approved_status=0 
						 
						 PreparedStatement stmt = conn.prepareStatement(q);
						 stmt = setQueryWhereClause_SQL1(stmt,Search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
									mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
							stmt.setInt(1, university_id);
							stmt.setInt(2, user_id);
							stmt.setInt(3, Integer.parseInt(institute_status));
						 System.err.println("INST_EDIT_hos----"+stmt);
						 
						ResultSet rs = stmt.executeQuery();
					
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;
						
						int countFunction = 1;
				
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
						
						if(institute_status=="-1" || institute_status=="0") {
							System.out.println("----hospital---if dao---impl--");
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDHospital' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='hospiIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
					   }
						else {
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDHospital' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='hospiIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
						}
						
						ul+=f + " " + f1 ;
						ul+="</ul>";
						
						action = ul;
						countFunction+=1;
						columns.put("action", action);

						alist.add(columns);
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
	
	//---------------- Hospital List Count -----------------//

	@Override
	public long getFilter_Hospital_AttachedListCount(String search, int university_id,int user_id,String institute_status,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c) {
		
		String SearchValue = GenerateQueryWhereClause_SQL1(search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
				mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			

		q="select count(*) from (select a.id,a.user_id,a.name_homoeopathic_medical_clg,a.attached_homoeopath_hospital,"
				+ "a.super_speciality_hospital,to_char(a.mou_date,'DD-MON-YYYY') as mou_date,\r\n"
		        + "a.copy_of_mou,a.name_of_hospital_staff,a.designation,a.qualification,a.fulltime_parttime,"
		        + "a.remarks_form_c,a.reject_remarks \r\n"
	            + "from dg_rec_hospital_attached_child  a where a.university_id=? and a.user_id=? and "
	            + " inst_status=?) a  ";//university_approved_status=0 and council_approved_status=0 
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL1(stmt,search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
				mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
		 stmt.setInt(1, university_id);
		 stmt.setInt(2, user_id);
		 stmt.setInt(3, Integer.parseInt(institute_status));
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
	public DG_REC_HOSPITAL_ATTACHED getughospByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_HOSPITAL_ATTACHED hospiId = (DG_REC_HOSPITAL_ATTACHED) session
				.get(DG_REC_HOSPITAL_ATTACHED.class, id);
		session.getTransaction().commit();
		session.close();
		return hospiId;
	}
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL1(String Search,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
			String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
			String remarks_form_c) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_homoeopathic_medical_clg) Like ? or upper(attached_homoeopath_hospital) Like ? "
					+ " or upper(super_speciality_hospital) Like ? or TO_CHAR(mou_date , 'DD-MON-YYYY') like ? "
					+ " or upper(copy_of_mou) Like ? or upper(name_of_hospital_staff) Like ? "
					+ "or designation::character varying like ? or upper(qualification) Like ? "
					+ " or upper(fulltime_parttime) Like ? or upper(remarks_form_c) Like ?) ";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search,String name_homoeopathic_medical_clg,
			String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
			String designation,String qualification,String fulltime_parttime,String remarks_form_c) {
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
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
			}
			
			
			if (name_homoeopathic_medical_clg != null && !name_homoeopathic_medical_clg.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_homoeopathic_medical_clg.toUpperCase()+"%");
			}
			if (attached_homoeopath_hospital != null && !attached_homoeopath_hospital.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+attached_homoeopath_hospital.toUpperCase()+"%");
			}
			if (super_speciality_hospital != null && !super_speciality_hospital.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+super_speciality_hospital.toUpperCase()+"%");
			}
			if( mou_date != null && !mou_date.equals("") && !mou_date.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, mou_date );
			}
			if (copy_of_mou != null && !copy_of_mou.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+copy_of_mou.toUpperCase()+"%");
			}
			if (name_of_hospital_staff != null && !name_of_hospital_staff.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_hospital_staff.toUpperCase()+"%");
			}
			if (designation != null && !designation.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(designation));
			}
			if (qualification != null && !qualification.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+qualification.toUpperCase()+"%");
			}
			if (fulltime_parttime != null && !fulltime_parttime.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+fulltime_parttime.toUpperCase()+"%");
			}
			if (remarks_form_c != null && !remarks_form_c.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks_form_c.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	//----------------Migrated Student To --------------------//
	
	@Override
	public List<Map<String, Object>> getFilter_Migrated_Student_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_inst,String student_name_to_migrated,
			String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d) {
		
		String SearchValue = GenerateQueryWhereClause_SQL2(search, name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
				university_enrollment_number, remarks_form_d);
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
//		System.err.println("----in daoimpl-dddd---");
		try {
			
			conn = dataSource.getConnection();
			
			q=" select a.user_id,a.id,ir.institute_name,a.name_of_inst,a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to,a.professional_year_migrated,\r\n"
					+ "a.university_enrollment_number,a.remarks_form_d,a.reject_remarks \r\n"
					+ "from dg_rec_migrated_student_sub_child a \r\n"
					+" inner join edu_lms_institute_reg ir on ir.institute_name=a.name_of_inst \r\n"
					+ "where a.university_id=? and a.user_id=? and inst_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
					+ pageLength + " OFFSET " + startPage;//and inst_status=0 and university_approved_status=0 and council_approved_status=0
			
					
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);
			stmt.setInt(1, university_id);
			stmt.setInt(2, user_id);
			stmt.setInt(3, Integer.parseInt(institute_status));
			    System.err.println("Inst_Edit_2---"+stmt);
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				int countFunction = 1;

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
					
					
					
					if(institute_status=="-1" || institute_status=="0") {
						System.out.println("----mig to---if dao---impl--");
						f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>";
				   }
					else {
						f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>";
					}
					
					ul+=f + " " + f1 ;
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

		//-------------------- Migrated Student To List Count ------------------------------//
	@Override
	public long getFilter_Migrated_StudentListCount(String search, int university_id,int user_id,String institute_status,String name_of_inst,
			String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d) {
		
		String SearchValue = GenerateQueryWhereClause_SQL2(search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
				university_enrollment_number, remarks_form_d);
		
//		  System.err.println("stmt-migrated_student-cccount----");
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select a.user_id,a.id,ir.institute_name,a.name_of_inst,a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to,a.professional_year_migrated,\r\n"
					+ "a.university_enrollment_number,a.remarks_form_d,a.reject_remarks \r\n"
					+ "from dg_rec_migrated_student_sub_child a \r\n"
					+ " inner join edu_lms_institute_reg ir on ir.institute_name=a.name_of_inst \r\n"
					+ "where a.university_id=? and a.user_id=? and inst_status=? ) a";//inst_status=0 and university_approved_status=0 and council_approved_status=0
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			    System.err.println("Inst_Edit_2_Count-----"+stmt);
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
	public String GenerateQueryWhereClause_SQL2(String Search,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
			String university_enrollment_number,String remarks_form_d) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_inst) Like ? or upper(student_name_to_migrated) Like ? or "
					+ "TO_CHAR(migrated_dt_to , 'dd/MON/yyyy') like ? or upper(professional_year_migrated)  Like ? "
					+ "or university_enrollment_number::character varying like ? or upper(remarks_form_d) Like ? )";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search, String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
			String university_enrollment_number,String remarks_form_d) {
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if (name_of_inst != null && !name_of_inst.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_inst.toUpperCase()+"%");
			}
			if (student_name_to_migrated != null && !student_name_to_migrated.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+student_name_to_migrated.toUpperCase()+"%");
			}
			if( migrated_dt_to != null && !migrated_dt_to.equals("") && !migrated_dt_to.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, migrated_dt_to );
			}
			if (professional_year_migrated != null && !professional_year_migrated.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+professional_year_migrated.toUpperCase()+"%");
			}
			if (university_enrollment_number != null && !university_enrollment_number.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(university_enrollment_number));
			}
			if (remarks_form_d != null && !remarks_form_d.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks_form_d.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	//----------------Migrated Student From --------------------//
	
	@Override
	public List<Map<String, Object>> getFilter_Migrated_Student_From_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated) {
		
		String SearchValue = GenerateQueryWhereClause_SQL3(search, name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
				university_enroll_num, remarks_migrated);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
//		System.err.println("----in daoimpl-dddd---");
		try {
			
			conn = dataSource.getConnection();
			
			q=" select a.user_id,a.id,ir.institute_name,a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration,a.professional_year,\r\n"
					+ " a.university_enroll_num,a.remarks_migrated,a.reject_remarks \r\n"
					+ "from dg_rec_migrated_student_from_child a \r\n"
					+" inner join edu_lms_institute_reg ir on ir.institute_name=a.name_of_institution \r\n"//on ir.id=a.name_of_institution::int
					+ " where a.university_id=? and a.user_id=? and inst_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
							+ pageLength + " OFFSET " + startPage;
					// and inst_status=0 and university_approved_status=0 and council_approved_status=0
		
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL3(stmt,search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);
			stmt.setInt(1, university_id);
			stmt.setInt(2, user_id);
			stmt.setInt(3, Integer.parseInt(institute_status));
		    System.err.println("INST_EDIT_3---"+stmt);
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				int countFunction = 1;

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

					
						
						if(institute_status=="-1" || institute_status=="0") {
							System.out.println("----mig from---if dao---impl--");
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDMigratedFrom' data-bs-toggle='modal' data-bs-target='#studentmodal' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdMigfrom"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
					   }
						else {
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDMigratedFrom' data-bs-toggle='modal' data-bs-target='#studentmodal' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdMigfrom"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
						}
						
						ul+=f + " " + f1 ;
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

			//-------------------- Migrated Student From List Count ------------------------------//
	@Override
	public long getFilter_Migrated_Student_FromListCount(String search, int university_id,int user_id,String institute_status, String name_of_institution,
			String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated) {
		
		String SearchValue = GenerateQueryWhereClause_SQL3(search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
				university_enroll_num, remarks_migrated);
		
//		  System.err.println("stmt-migrated_student-cccount----");
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select a.user_id,a.id,ir.institute_name,a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration,a.professional_year,\r\n"
					+ " a.university_enroll_num,a.remarks_migrated,a.reject_remarks \r\n"
					+ "from dg_rec_migrated_student_from_child a \r\n"
					+ " inner join edu_lms_institute_reg ir on ir.institute_name=a.name_of_institution \r\n" //on ir.id=a.name_of_institution::int
					+ " where a.university_id=? and a.user_id=? and inst_status=? ) a";
			

			//and inst_status=0 and university_approved_status=0 and council_approved_status=0
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL3(stmt,search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			 
			    System.err.println("Inst_Edit_3_Count----"+stmt);
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
	public String GenerateQueryWhereClause_SQL3(String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
			 String university_enroll_num,String remarks_migrated ) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_institution) Like ? or upper(name_of_students_migrated) Like ? or "
					+ "TO_CHAR(dt_of_migration , 'dd/MON/yyyy') like ? or upper(professional_year)  Like ? "
					+ "or university_enroll_num::character varying like ? or upper(remarks_migrated) Like ? )";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
			 String university_enroll_num,String remarks_migrated) {
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if (name_of_students_migrated != null && !name_of_students_migrated.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_students_migrated.toUpperCase()+"%");
			}
			if (name_of_institution != null && !name_of_institution.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_institution.toUpperCase()+"%");
			}
			if( dt_of_migration != null && !dt_of_migration.equals("") && !dt_of_migration.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, dt_of_migration );
			}
			if (professional_year != null && !professional_year.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+professional_year.toUpperCase()+"%");
			}
			if (university_enroll_num != null && !university_enroll_num.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(university_enroll_num));
			}
			if (remarks_migrated != null && !remarks_migrated.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks_migrated.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}		
	//------------------------- Intern Student ------------------------------//
	@Override
	public List<Map<String, Object>> getFilter_Intern_Student_list(int startPage, int pageLength, String search,
			String orderColunm, String orderType, int university_id,int user_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f) {
		
		String SearchValue = GenerateQueryWhereClause_SQL4(search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
				year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
//		System.err.println("---in daoimpl-dddd---");
		try {
			
			conn = dataSource.getConnection();
			
			q="select a.user_id,a.id,a.name_of_students,a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year ,\r\n"
					+ "a.provisional_reg_no,a.year_of_provisional_reg,to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship ,\r\n"
					+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,\r\n"
					+ "a.remark_form_f,a.reject_remarks \r\n"
					+ "from dg_rec_intern_student_course_child  a where a.university_id=? and a.user_id=? and inst_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
					+ pageLength + " OFFSET " + startPage;
					//+ "inst_status=0 and university_approved_status=0 and council_approved_status=0";
					
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
			stmt.setInt(1, university_id);
			stmt.setInt(2, user_id);
			stmt.setInt(3, Integer.parseInt(institute_status));
			System.err.println("Inst_edit_4-----"+stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				int countFunction = 1;

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
					
					
					
					if(institute_status=="-1" || institute_status=="0") {
						System.out.println("----intern--if dao---impl--");
						f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDIntern' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='insIdAGE"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>";
				   }
					else {
						f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDIntern' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='insIdAGE"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>";
					}
					ul+=f + " " + f1 ;
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

	//-----------------------Intern Student List Count ------------------------//
	@Override
	public long getFilter_Intern_StudentListCount(String search, int university_id,int user_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f) {
		
		String SearchValue = GenerateQueryWhereClause_SQL4(search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
				year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select a.user_id,a.id,a.name_of_students,a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year ,\r\n"
					+ "a.provisional_reg_no,a.year_of_provisional_reg,to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship ,\r\n"
					+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,\r\n"
					+ "a.remark_form_f,a.reject_remarks \r\n"
					+ "from dg_rec_intern_student_course_child  a where a.university_id=? and a.user_id=? and inst_status=? ) a";
			// and inst_status=0 and university_approved_status=0 and council_approved_status=0
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
//			    System.err.println("Inst_edit_4_Count-----"+stmt);
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
	public String GenerateQueryWhereClause_SQL4(String Search, String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f) {
		
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_students) Like ? or upper(year_of_admission) Like ? "
					+ "or TO_CHAR(date_of_result_final_year , 'DD-MON-YYYY') like ? or provisional_reg_no::character varying like ? "
					+ "or upper(year_of_provisional_reg) Like ? or TO_CHAR(date_of_starting_internship , 'DD-MON-YYYY') like ? "
					+ "or TO_CHAR(date_of_completion_internship , 'DD-MON-YYYY') like ? or upper(remark_form_f) Like ? )";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search,String name_of_students,String year_of_admission, String date_of_result_final_year,
			String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
			String remark_form_f) {
		
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				
			}
			if (name_of_students != null && !name_of_students.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_students.toUpperCase()+"%");
			}
			if (year_of_admission != null && !year_of_admission.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+year_of_admission.toUpperCase()+"%");
			}
			if( date_of_result_final_year != null && !date_of_result_final_year.equals("") && !date_of_result_final_year.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_result_final_year );
			}
			if (provisional_reg_no != null && !provisional_reg_no.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(provisional_reg_no));
			}
			if (year_of_provisional_reg != null && !year_of_provisional_reg.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+year_of_provisional_reg.toUpperCase()+"%");
			}
			if( date_of_starting_internship != null && !date_of_starting_internship.equals("") && !date_of_starting_internship.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_starting_internship );
			}
			if( date_of_completion_internship != null && !date_of_completion_internship.equals("") && !date_of_completion_internship.equals("DD-MON-YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_completion_internship );
			}
			if (remark_form_f != null && !remark_form_f.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remark_form_f.toUpperCase()+"%");
			}
			

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public DG_REC_ADMITTED_STUDENT getadmittedByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_ADMITTED_STUDENT eid = (DG_REC_ADMITTED_STUDENT) session
				.get(DG_REC_ADMITTED_STUDENT.class, id);
		session.getTransaction().commit();
		session.close();
		return eid;
	}
	
	public DG_REC_INTERN_STUDENT_COURSE getinternByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_INTERN_STUDENT_COURSE isid = (DG_REC_INTERN_STUDENT_COURSE) session
				.get(DG_REC_INTERN_STUDENT_COURSE.class, id);
		session.getTransaction().commit();
		session.close();
		return isid;
	}
	
	@Override
	public List<Map<String, Object>> getmigratedtoByid(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
//		System.err.println("--C-in daoimpl-dddd---");
		try {
			
			conn = dataSource.getConnection();
			
			q="select *,to_char(migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
					
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(id));
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
	public List<Map<String, Object>> getmigratedfromByid(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
//		System.err.println("--C-in daoimpl-dddd---");
		try {
			
			conn = dataSource.getConnection();
			
			q="select *,to_char(dt_of_migration,'DD-MON-YYYY') as dt_of_migration2 from dg_rec_migrated_student_from_child where id=? ";
					
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				System.err.println("eee---------"+stmt);
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

	//GET UNIVERSITY USER ID
		public ArrayList<ArrayList<String>> getUni_user_id(String uni_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

				q =  "select l.userid \n"
						+ "from logininformation l \n"
						+ "inner join userroleinformation ur on ur.user_id=l.userid \n"
						+ "inner join roleinformation r on r.role_id=ur.role_id \n"
						+ "where lower(r.role) like '%university%' and l.university_id=? ";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(uni_id));
				System.err.println("stmt============user_id============="+stmt);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("userid"));// 0

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

		public String getImagePath(String id,String myImg) {
			String whr = "";
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = null;
				
				query = "select upload_signature"
						+ " from dg_rec_ug_declaration where id=? ";
				
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (myImg.equals("upload_signature")) {
						whr = rs.getString("upload_signature");
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return whr;
		}
		
		//GET FILE PATH FOR DOWNLOAD DOCUMENT PDF
		public String getFilePathQueryForDocAttFormA(String id){

			String whr = "";
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = null;
//						query = "select upload_file from tb_nch_teacher_exp_child \n"
//								+ " where id = ? order by id";
				query="  select court_order_file from dg_rec_admitted_student_child\n"
						+ "where id=?";
				
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(id));
//				System.err.println("PROVISIONAL FORM A VIEW PAGE PDF------------------>>>>>>>>>>>"+stmt);
				ResultSet rs = stmt.executeQuery();
				
//				System.err.println("PROVISIONAL FORM A VIEW PAGE PDF------------------>>>>>>>>>>>"+stmt);
				
				while (rs.next()) {
					whr = rs.getString("court_order_file");
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return whr;
		}
		
}
	 

