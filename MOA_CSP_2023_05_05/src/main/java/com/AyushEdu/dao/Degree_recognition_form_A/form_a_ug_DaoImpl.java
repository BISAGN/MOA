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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_EXAMINERS_APPOINTED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;

@Repository
public class form_a_ug_DaoImpl implements Form_a_ug_Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	
					//---------------------- UG Form A ---------------------//
	
	public List<Map<String, Object>> getFilter_UG_list(int startPage, int pageLength,String Search, String orderColunm, 
			String orderType,String university_id,String university_approved_status,String council_approved_status,String university_status,
			String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks) {
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search,  name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
			institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
			admission_intake,num_of_student_admitted,postal_address,email,website,remarks);
	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			
			try {
					conn = dataSource.getConnection();
		
//					 q="select a.id,ir.institute_name,a.institute_id,a.academic_year_applied_for,a.admission_intake,a.num_of_student_admitted,"
//						+ "a.country,s.state_name,a.state_id,dm.district_name,a.district_id,\r\n"
//						+"a.city,a.postal_address,a.email,a.website,a.permission_from_central_gov,a.remarks,a.reject_remarks \r\n"
//						+"from dg_rec_ug_form_a_parent a \n"
//						+" inner join edu_lms_institute_reg ir on ir.institute_name=a.institute_id \r\n"
//						+ "inner join edu_lms_state_mstr s on s.state_id  = cast(a.state_id as integer)\n"
//						+ "inner join edu_lms_district_mstr dm on dm.district_id = cast(a.district_id as integer)\n"
//						+"where a.university_id=? and university_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
//						+ pageLength + " OFFSET " + startPage;
					 
					 
//					 q="select a.id,ir.institute_name,a.institute_id,dg.name_of_applicant_university,dg.name_ug_course,dg.abbre_undergraduate_course,dg.academic_file_upload,"
//					 			+"a.academic_year_applied_for,a.admission_intake,a.num_of_student_admitted,"
//								+"a.remarks,a.permission_from_central_gov,a.reject_remarks \r\n"
//								+"from dg_rec_ug_form_a_parent a \n"
//								+"inner join edu_lms_institute_reg ir on ir.institute_name=a.institute_id \r\n"
//								+"inner join dg_rec_ug dg on dg.user_id=a.user_id  \r\n"
//								+"where a.university_id=? and a.university_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
//								+ pageLength + " OFFSET " + startPage;
					 
//					q="select a.id,ir.institute_name,a.institute_id,dg.name_of_applicant_university,dg.name_ug_course,dg.abbre_undergraduate_course,"
//				 			+"a.academic_year_applied_for,a.admission_intake,a.num_of_student_admitted,"
//							+"a.remarks,a.permission_from_central_gov,a.academic_file_upload,a.reject_remarks \r\n"
//							+"from dg_rec_ug_form_a_parent a \n"
//							+"inner join edu_lms_institute_reg ir on ir.institute_name=a.institute_id \r\n"
//							+"inner join dg_rec_ug dg on dg.user_id=a.user_id  \r\n"
//							+"where a.university_id=? and a.university_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
//							+ pageLength + " OFFSET " + startPage;
					
					q="select a.id,ir.institute_name,a.name_of_applicant_university,a.name_ug_course,\r\n"
							+ "a.abbre_undergraduate_course,a.academic_year_applied_for,a.academic_file_upload,\r\n"
							+ "a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.postal_address,a.email,a.website,a.remarks,a.reject_remarks \r\n"
							+ "from dg_rec_ug_form_a_parent a \r\n"
							+ "inner join edu_lms_institute_reg ir on cast(ir.id as character varying) =a.institute_name \r\n"
							+ "where a.university_id=? and a.university_approved_status=?  ORDER BY a.id asc limit 10 OFFSET 0 ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt,Search,name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
							institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
							admission_intake,num_of_student_admitted,postal_address,email,website,remarks);
					stmt.setInt(1, Integer.parseInt(university_id));
					stmt.setInt(2, Integer.parseInt(university_status));
					  System.err.println("stmt form a university---"+stmt);
					ResultSet rs = stmt.executeQuery();
						
						  

							ResultSetMetaData metaData = rs.getMetaData();
							int columnCount = metaData.getColumnCount();
							int j = startPage;
							
							int countFunction = 1;
							int countFunction2 =1;
							int countFunction3 =1;
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
							String vmp2 ="";
							String ul="";
							ul+="<ul class='buttons-group mainbtn action daobtn'>";
							
							
							
							if(university_status=="-1" || university_status=="0") {
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDUG' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='ugIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							}
							else
							{
								f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDUG' value='ADD' title='Edit Data' >" 
										+"<i class='lni lni-pencil-alt'>"
										+"<input type='hidden' id='ugIdAGE"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
							}
							
							String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
									+ rs.getString("id") + "') }else{ return false;}\"";
							vmp1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download File' >\n"
									+ "		<i class='lni lni-download pdfdown'>"
									+ "<input type='hidden' id='DCounpdf"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
							
							String download2 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
									+ rs.getString("id") + "') }else{ return false;}\"";
							vmp2 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download File' >\n"
									+ "		<i class='lni lni-download pdfdownpermission'>"
									+ "<input type='hidden' id='DPermissionpdf"+countFunction3+"' value="+rs.getString("id")+"></i></a> </li></ul>";
							
							
							ul+=f + " " + f1 ;
							ul+="</ul>";
							
							action = ul;
							countFunction+=1;
							countFunction2+=1;
							columns.put("action", action);
							columns.put("vmp1",vmp1);
							columns.put("vmp2",vmp2);
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
	public ArrayList<ArrayList<String>> getDegreeListFromInstitute(String institute_id, String userId, String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM")) {
				qry = "where lo.userid=?";
			} else {
				qry = "where lo.institute_id=?";
			}

			q = "select distinct dm.id,dm.degree_name from edu_lms_degree_mstr dm \n"
					+ " inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
					+ " inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;

			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM")) {
				stmt.setObject(1, Integer.parseInt(userId));
			} else {
				stmt.setObject(1, Integer.parseInt(institute_id));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("degree_name"));// 1

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

					////////////////////////////// Form A UG ListCount ///////////////////////////
		
	@Override
	public long getFilter_UGListCount(String search, int university_id,int user_id,String university_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
				institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
				admission_intake,num_of_student_admitted,postal_address,email,website,remarks);
		
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
	q="select count(*) from (select a.id,ir.institute_name,a.name_of_applicant_university,a.name_ug_course,a.abbre_undergraduate_course,\r\n"
			+ "a.academic_year_applied_for,a.academic_file_upload,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.postal_address,\r\n"
			+ "a.email,a.website,a.remarks,a.reject_remarks \r\n"
			+ "from dg_rec_ug_form_a_parent a "
			+ "inner join edu_lms_institute_reg ir on cast(ir.id as character varying)=a.institute_name \r\n"
	        + "where a.university_id=? and a.user_id=? and a.university_approved_status=? ) a  ";
	 
	PreparedStatement stmt = conn.prepareStatement(q);
	stmt = setQueryWhereClause_SQL(stmt,search,name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
			institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
			admission_intake,num_of_student_admitted,postal_address,email,website,remarks);
	 stmt.setInt(1, university_id);
	 stmt.setInt(2, user_id);
	 stmt.setInt(3, Integer.parseInt(university_status));

			    System.err.println("stmt-foem a list---"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_applicant_university) Like ? "
					+ " or upper(name_ug_course) Like or upper(abbre_undergraduate_course) Like ? or upper(institute_name) Like or upper(academic_year_applied_for) Like ? "
					+ "or upper(academic_file_upload) Like ? or upper(permission_from_central_gov) Like ? or upper(admission_intake) Like ? "
					+ " or upper(num_of_student_admitted) Like ? or upper(postal_address) Like ? or upper(email) Like ? or upper(website) Like ?"
					+ " or upper(remarks) Like ? )";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String institute_name,String academic_year_applied_for, String academic_file_upload,
			String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
			String remarks) {
		int flag = 2;
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
				
			}
			
			if (name_of_applicant_university != null && !name_of_applicant_university.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_applicant_university.toUpperCase()+"%");
			}
			if (name_ug_course != null && !name_ug_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_ug_course.toUpperCase()+"%");
			}
			if (abbre_undergraduate_course != null && !abbre_undergraduate_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+abbre_undergraduate_course.toUpperCase()+"%");
			}
			if (institute_name != null && !institute_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+institute_name.toUpperCase()+"%");
			}
			if (academic_year_applied_for != null && !academic_year_applied_for.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+academic_year_applied_for.toUpperCase()+"%");
			}
			if (permission_from_central_gov != null && !permission_from_central_gov.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+permission_from_central_gov.toUpperCase()+"%");
			}
			if (admission_intake != null && !admission_intake.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+admission_intake.toUpperCase()+"%");
			}
			if (num_of_student_admitted != null && !num_of_student_admitted.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+num_of_student_admitted.toUpperCase()+"%");
			}
			if (remarks != null && !remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
		public DG_REC_UG_FORM_A getugByid(int id) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DG_REC_UG_FORM_A eid = (DG_REC_UG_FORM_A) session
					.get(DG_REC_UG_FORM_A.class, id);
			session.getTransaction().commit();
			session.close();
			return eid;
		}
	//----------------------	getInstituteListbyUserID--------------------------
		
		public List<Map<String, Object>> getInstituteListbyUserID() {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
				
							 q= " select userid,institute_name from logininformation lo inner join userroleinformation ur on lo.userid=ur.user_id\r\n"
							 		+ "inner join roleinformation ro on ro.role_id=ur.role_id and role='Institute_NCH' \r\n"
							 		+ "inner join edu_lms_institute_reg ins on lo.institute_id=ins.id ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							
							ResultSet rs = stmt.executeQuery();
						System.out.println("---stmt---"+stmt);
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
		//----------------------end	getInstituteListbyUserID--------------------------		
		
		@Override
		public ArrayList<ArrayList<String>> getinstitutelist(String userid) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		
				q="select DISTINCT ir.id,ir.institute_name\n"
						+ "from logininformation l \n"
						+ "inner join edu_lms_institute_reg ir on ir.id = l.institute_id and status= '1' and app_status= '1'\n"
						+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
						+ "inner join roleinformation ro on ro.role_id=ul.role_id 	and role='Institute_NCH' ";
	
			    	stmt = conn.prepareStatement(q);
					//stmt.setInt(1, Integer.parseInt(userid));
					
					System.err.println("----getinstitutelist-------------------"+stmt);
			    	ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("institute_name"));// 1
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
		public ArrayList<ArrayList<String>> get_inst_uni_data(String university_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		    Connection conn = null;
		    try{
		    	conn = dataSource.getConnection();
		        String sq1="select ir.id,ir.institute_name\n"
		        		+ "					from logininformation l \n"
		        		+ "					inner join edu_lms_institute_reg ir on ir.id = l.institute_id\n"
		        		+ "					inner join userroleinformation ul on ul.user_id=l.userid\n"
		        		+ "					inner join roleinformation ro on ro.role_id=ul.role_id 	and role='Institute_NCH' \n"
		        		+ "                    WHERE l.university_id = ?";                         
		        
		        PreparedStatement stmt = conn.prepareStatement(sq1);
		        stmt.setInt(1, Integer.parseInt(university_id));
		        
		        System.err.println("----getinstitutelist-ddddd------------------"+stmt);
		        ResultSet rs = stmt.executeQuery();  
		        
		        String str1="";
		        while(rs.next()){
		        	ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("id"));// 0
						list.add(rs.getString("institute_name"));// 0
						alist.add(list);                           	  
		        }
		        rs.close();
		        stmt.close();
		        conn.close();
		    }catch(SQLException e){
			   e.printStackTrace();
		   }        
		    
		   System.err.println("alist----->    "+alist);  
		    
		   return alist;
		}
		
		//---------------------- Admitted ---------------------//

		public List<Map<String, Object>> getFilter_Admitted_list(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String university_id,String institute_id,String status,String student_name,
				String date_of_admission,String neet_rank, String rank,String marks,String all_india,
				String management_quota,String state,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
				String date_of_intern_compl,String remarks_form_b) {
			
			String SearchValue = GenerateQueryWhereClause_SQL2(Search, student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
					court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
				
							 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission, \r\n"
							 		+ "a.neet_rank,a.rank,a.marks,a.all_india,a.state,a.management_quota,a.admission_authority,a.court_order,\r\n"
							 		+ "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl , \r\n"
							 		+ "to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b \r\n"
							 		+ "from dg_rec_admitted_student_child a \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
							 		+ "where a.university_id=? and a.university_approved_status=?  and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage;//and a.inst_status=? and a.council_approved_status=?
							
//							System.out.println("q---"+q);
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL2(stmt,Search,student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
									court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
							stmt.setInt(1, Integer.parseInt(university_id));
							stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
							
							System.err.println("UNI_APP_1----"+stmt);
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
							System.err.println("status--ad----"+status);
							
							if(status=="-1" || status=="0") {
							f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationAdmittedData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
							//data-bs-toggle='modal' data-bs-target='#myModal'
											"<i class='lni lni-close'></i></a> </li> "
											+ "<input type='hidden' id='RejectAdmittedId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
							}
							else
							{
								f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationAdmittedData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
										//data-bs-toggle='modal' data-bs-target='#myModal'
														"<i class='lni lni-close'></i></a> </li> "
														+ "<input type='hidden' id='RejectAdmittedId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
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
		
//									//---------------- Admitted List Count -----------------//
			
		@Override
		public long getFilter_Admitted_ListCount(String search, String university_id,String institute_id,String status,String student_name,
				String date_of_admission, String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,
				String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b){
			
			String SearchValue = GenerateQueryWhereClause_SQL2(search, student_name,date_of_admission,neet_rank,rank,marks,all_india,state,
					management_quota,admission_authority,
					court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
			
//			,int institute_status,int institute_id
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission, \r\n"
						+ "a.neet_rank,a.rank,a.marks,a.all_india,a.state,a.management_quota,a.admission_authority,a.court_order,\r\n"
						+ "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl , \r\n"
						+ "to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b \r\n"
						+ "from dg_rec_admitted_student_child a \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.university_approved_status=?  and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
						+ ") a";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL2(stmt,search,student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2, Integer.parseInt(status));
				 stmt.setInt(3, Integer.parseInt(institute_id));
				 System.err.println("UNI_APP_1_Count----"+stmt);
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
		public String GenerateQueryWhereClause_SQL2(String Search,String student_name,String date_of_admission,String neet_rank, String rank,String marks,String all_india,
				String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
				String date_of_intern_compl,String remarks_form_b) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(student_name) Like ? or TO_CHAR(date_of_admission , 'DD-MON-YYYY') like ? "
						+ " or neet_rank::character varying like ? or rank::character varying like ? or marks::character varying like ?"
						+ " or upper(all_india) Like ? "
						+ " or upper(state) Like ? or upper(management_quota) Like ? or upper(admission_authority) Like ? or upper(court_order) Like ? "
						+ " or TO_CHAR(date_of_enroll_university , 'DD-MON-YYYY') like ? "
						+ "or uni_enroll_number::character varying like ? or TO_CHAR(date_of_intern_compl , 'DD-MON-YYYY') like ?"
						+ " or upper(remarks_form_b) Like ?) ";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
				String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,
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
			//---------------------- Hospital ---------------------//
			
		public List<Map<String, Object>> getFilter_Hospital_list(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String university_id,String institute_id,String status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
				String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
				String remarks_form_c) {
			
			String SearchValue = GenerateQueryWhereClause_SQL3(Search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
					mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);

					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
				
		 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_homoeopathic_medical_clg,a.attached_homoeopath_hospital, \r\n"
			 + "a.super_speciality_hospital,to_char(a.mou_date,'DD-MON-YYYY') as mou_date,\r\n"
			 + "a.copy_of_mou,a.name_of_hospital_staff,a.designation,a.qualification, \r\n"
			 + "a.fulltime_parttime,a.remarks_form_c \r\n"
		     + "from dg_rec_hospital_attached_child a \r\n"
		     + "inner join logininformation li on li.userid=a.user_id\r\n"
		     + "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
				+ pageLength + " OFFSET " + startPage;//and a.inst_status=? and a.council_approved_status=?
							
							System.out.println("Hospital---"+q);
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL3(stmt,Search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
									mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
							stmt.setInt(1, Integer.parseInt(university_id));
							stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
							
							System.err.println("Hospital--"+stmt);
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
								
								f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationHospitalData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
										//data-bs-toggle='modal' data-bs-target='#myModal'
														"<i class='lni lni-close'></i></a> </li> "
														+ "<input type='hidden' id='RejectHospitalId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
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
				//---------------- Hospital List Count -----------------//
			
		@Override
		public long getFilter_HospitalAttached_ListCount(String search, String university_id,String institute_id,String status,String name_homoeopathic_medical_clg,
				String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
				String designation,String qualification,String fulltime_parttime,String remarks_form_c){
			
			String SearchValue = GenerateQueryWhereClause_SQL3(search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
					mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
		
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_homoeopathic_medical_clg,a.attached_homoeopath_hospital, \r\n"
						+ "a.super_speciality_hospital,to_char(a.mou_date,'DD-MON-YYYY') as mou_date,\r\n"
						+ "a.copy_of_mou,a.name_of_hospital_staff,a.designation,a.qualification, \r\n"
						+ "a.fulltime_parttime,a.remarks_form_c \r\n"
						+ "from dg_rec_hospital_attached_child a \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.university_approved_status=?  and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
						+ ") a";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL3(stmt,search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2, Integer.parseInt(status));
				 stmt.setInt(3, Integer.parseInt(institute_id));
				 System.err.println("UNI_APP_HospiCount----"+stmt);
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
		public String GenerateQueryWhereClause_SQL3(String Search,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
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
		
		public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search,String name_homoeopathic_medical_clg,
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
			//---------------------- Migrated ---------------------//
			
		
		public List<Map<String, Object>> getFilter_Migrated_list(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String university_id,String institute_status,String institute_id,String name_of_inst,String student_name_to_migrated,
				String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d) {
			
			String SearchValue = GenerateQueryWhereClause_SQL4(Search, name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
				
							 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_inst, \r\n"
									 + "a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to,\r\n"
									 + "a.professional_year_migrated,a.university_enrollment_number,a.remarks_form_d \r\n"
								     + "from dg_rec_migrated_student_sub_child a \r\n"
								     + "inner join logininformation li on li.userid=a.user_id \r\n"
								     + "where a.university_id=? and a.university_approved_status=? and a.user_id=? " + SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; //and a.inst_status=? and a.council_approved_status=?
							 
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL4(stmt,Search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
									university_enrollment_number, remarks_form_d);
							stmt.setInt(1, Integer.parseInt(university_id));
							stmt.setInt(2,  Integer.parseInt(institute_status));
							stmt.setInt(3,  Integer.parseInt(institute_id));
								
//								System.err.println("-----inst_status---daoimpl--"+institute_status);
//								System.err.println("-----institute_id---daoimpl--"+institute_id);
								
								System.err.println("Migrated-2--universit--"+stmt);
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
								
								
								f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationMigratedData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
								//data-bs-toggle='modal' data-bs-target='#myModal'
												"<i class='lni lni-close'></i></a> </li> "
												+ "<input type='hidden' id='RejectMigratedId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
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
			
			//---------------- Migrated List Count -----------------//
				
		@Override
		public long getFilter_Migrated_ListCount(String search, String university_id,String institute_status,String institute_id,String name_of_inst,
				String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,
				String remarks_form_d){
			
			String SearchValue = GenerateQueryWhereClause_SQL4(search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_inst, \r\n"
						+ "a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to, \r\n"
						+ "a.professional_year_migrated,a.university_enrollment_number,a.remarks_form_d \r\n"
						+ "from dg_rec_migrated_student_sub_child a \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.university_approved_status=?  and a.user_id=?" //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
						+ ") a";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL4(stmt,search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
						university_enrollment_number, remarks_form_d);
				stmt.setInt(1, Integer.parseInt(university_id));
				stmt.setInt(2,  Integer.parseInt(institute_status));
				stmt.setInt(3,  Integer.parseInt(institute_id));
						    ResultSet rs = stmt.executeQuery();
						    System.err.println("UNI_APP-2_COUNT----"+stmt);
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
		public String GenerateQueryWhereClause_SQL4(String Search,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
				String university_enrollment_number,String remarks_form_d) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(name_of_inst) Like ? or upper(student_name_to_migrated) Like ? or "
						+ "TO_CHAR(migrated_dt_to , 'dd/MON/yyyy') like ? or upper(professional_year_migrated)  Like ? "
						+ "or university_enrollment_number::character varying like ? or upper(remarks_form_d) Like ? )";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search, String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
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
//---------------------- Migrated From---------------------//
			
		public List<Map<String, Object>> getFilter_Migrated_From_list(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String university_id,String institute_id,String institute_status,String name_of_institution,
				String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated) {
			
			String SearchValue = GenerateQueryWhereClause_SQL5(Search, name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
						
							 q ="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_institution, \r\n"
							 		+ "a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration,\r\n"
							 		+ "a.professional_year,a.university_enroll_num,a.remarks_migrated \r\n"
							 		+ "from dg_rec_migrated_student_from_child a \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id \r\n"
							 		+ "where a.university_id=? and a.user_id=? and a.university_approved_status=?  "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage; //and a.inst_status=? and a.council_approved_status=?
							 
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL5(stmt,Search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
									university_enroll_num, remarks_migrated);
							stmt.setInt(1, Integer.parseInt(university_id));
							stmt.setInt(2,  Integer.parseInt(institute_status));
							stmt.setInt(3,  Integer.parseInt(institute_id));
								
								System.err.println("UNI_APP_3----"+stmt);
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
								
								
								f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationMigfromData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
								//data-bs-toggle='modal' data-bs-target='#myModal'
												"<i class='lni lni-close'></i></a> </li> "
												+ "<input type='hidden' id='RejectMigfromId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
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
			
			//---------------- Migrated From List Count -----------------//
		@Override
		public long getFilter_Migrated_From_ListCount(String search, String university_id,String institute_id,String institute_status, String name_of_institution,
				String name_of_students_migrated, String dt_of_migration,String professional_year,String university_enroll_num,String remarks_migrated){
			
			String SearchValue = GenerateQueryWhereClause_SQL5(search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_institution, \r\n"
						+ "a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration, \r\n"
						+ "a.professional_year,a.university_enroll_num,a.remarks_migrated \r\n"
						+ "from dg_rec_migrated_student_from_child a \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.user_id=? and a.university_approved_status=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
						+ ") a";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL5(stmt,search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
						university_enroll_num, remarks_migrated);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2,  Integer.parseInt(institute_status));
				 stmt.setInt(3,  Integer.parseInt(institute_id));
							
						 System.err.println("UNI_APP_3_Count----"+stmt);
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
		public String GenerateQueryWhereClause_SQL5(String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
				 String university_enroll_num,String remarks_migrated ) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(name_of_institution) Like ? or upper(name_of_students_migrated) Like ? or "
						+ "TO_CHAR(dt_of_migration , 'dd/MON/yyyy') like ? or upper(professional_year)  Like ? "
						+ "or university_enroll_num::character varying like ? or upper(remarks_migrated) Like ? )";
			}
			
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
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

//---------------------- Intern ---------------------//
			
		public List<Map<String, Object>> getFilter_Intern_list(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String university_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f) {
			
			String SearchValue = GenerateQueryWhereClause_SQL6(Search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

							conn = dataSource.getConnection();
				
							 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_students, \r\n"
							 		+ "a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year, \r\n"
							 		+ "a.provisional_reg_no,a.year_of_provisional_reg, \r\n"
							 		+ "to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship, \r\n"
							 		+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,a.remark_form_f \r\n"
							 		+ "from dg_rec_intern_student_course_child a \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								     + "where a.university_id=? and a.user_id=?  and a.university_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;//and a.inst_status=? and a.council_approved_status=?
							 
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL6(stmt,Search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
									year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
							stmt.setInt(1, Integer.parseInt(university_id));
							 stmt.setInt(2,  Integer.parseInt(institute_status));
							 stmt.setInt(3,  Integer.parseInt(institute_id));
								
								System.err.println("-----inst_status---daoimpl--xx--"+institute_status);
								   System.err.println("-----institute_id---daoimpl--xx--"+institute_id);
								   
								 System.err.println("UNI_APP_4----"+stmt);
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
								
								
								f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationInternData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
								//data-bs-toggle='modal' data-bs-target='#myModal'
												"<i class='lni lni-close'></i></a> </li> "
												+ "<input type='hidden' id='RejectInternId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
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
			
			//---------------- Intern List Count -----------------//
		@Override
		public long getFilter_Intern_ListCount(String search, String university_id,String institute_id,String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f){
			
			String SearchValue = GenerateQueryWhereClause_SQL6(search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_students, \r\n"
						+ "a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year, \r\n"
						+ "a.provisional_reg_no,a.year_of_provisional_reg, \r\n"
						+ "to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship, \r\n"
						+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,a.remark_form_f \r\n"
						+ "from dg_rec_intern_student_course_child a \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.user_id=?  and a.university_approved_status=?  " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
						+ ") a";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL6(stmt,search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
						year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2,  Integer.parseInt(institute_status));
				 stmt.setInt(3,  Integer.parseInt(institute_id));
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
		public String GenerateQueryWhereClause_SQL6(String Search, String name_of_students,String year_of_admission, String date_of_result_final_year,
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
		
		public PreparedStatement setQueryWhereClause_SQL6(PreparedStatement stmt, String Search,String name_of_students,String year_of_admission, String date_of_result_final_year,
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

//---------------------- UG Form E ---------------------//
				
				public List<Map<String, Object>> getFilter_Examiners_appointed_list(int startPage, int pageLength,String Search, String orderColunm,
							String orderType,String university_id,String university_approved_status,String council_approved_status,String university_status,
							String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
							String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) {
					
					String SearchValue = GenerateQueryWhereClause_SQL1(Search, name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
							teaching_experience,teacher_code,reg_number,d_university_appointment);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.id,a.name_of_examiners,a.subject_examiners,a.year_examiners,a.qualification_examiners,a.teaching_experience,a.teacher_code,a.reg_number, \r\n"
											 +"to_char(a.d_university_appointment,'DD-MON-YYYY') as d_university_appointment,a.reject_remarks \r\n" 
											 +"from dg_rec_examiners_appointed_child a \n"
											 +"where a.university_id=? and university_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
												+ pageLength + " OFFSET " + startPage;
						
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL1(stmt,Search,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
											teaching_experience,teacher_code,reg_number,d_university_appointment);
									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(university_status));
									
									 System.err.println("stmt-UG----"+stmt);
									 
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
									
									f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDExaminers' value='ADD' title='Edit Data' >" 
											+"<i class='lni lni-pencil-alt'>"
											+"<input type='hidden' id='examIdAGE"+countFunction+"' value="+rs.getString("id")+">"
										    +"</i></a></li>";
									
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
				
				//---------------- Examiners From List Count -----------------//

				@Override
				public long getFilter_Examiners_appointedListCount(String search, int university_id,int user_id,String university_status,
						String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
						String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) {
					
					String SearchValue = GenerateQueryWhereClause_SQL1(search, name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
							teaching_experience,teacher_code,reg_number,d_university_appointment);

					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.id,a.user_id,a.name_of_examiners,a.subject_examiners,"
						        + "a.qualification_examiners,a.teaching_experience,a.teacher_code,a.reg_number,a.reject_remarks,"
						        + "a.d_university_appointment \r\n"
					            + "from dg_rec_examiners_appointed_child  a where a.university_id=? and a.user_id=? and university_approved_status=? ) a  ";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL1(stmt,search,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
								teaching_experience,teacher_code,reg_number,d_university_appointment);
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(university_status));

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
				public String GenerateQueryWhereClause_SQL1(String Search, String name_of_examiners,String subject_examiners, String year_examiners,
						String qualification_examiners,String teaching_experience,String teacher_code, String reg_number,String d_university_appointment ) {
					
					String SearchValue="" ;
					
					if (Search!=null && !Search.equals("")) { // for Input Filter
						SearchValue += " and (upper(name_of_examiners) Like ? or upper(subject_examiners) Like ? "
								+ "or upper(year_examiners) Like ? or upper(qualification_examiners)  Like ? "
								+ "or upper(teaching_experience) Like ? or teacher_code::character varying like ? "
								+ "or reg_number::character varying like ? or TO_CHAR(d_university_appointment , 'DD-MON-YYYY') like ?) ";
					}
					
					return SearchValue;
				}
				
				public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search, String name_of_examiners,String subject_examiners,
						String year_examiners,String qualification_examiners,String teaching_experience,String teacher_code, String reg_number,
						String d_university_appointment) {
					
					int flag = 2;
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
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search + "%");
							
						}
						if (name_of_examiners != null && !name_of_examiners.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+name_of_examiners.toUpperCase()+"%");
						}
						if (subject_examiners != null && !subject_examiners.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+subject_examiners.toUpperCase()+"%");
						}
						if (year_examiners != null && !year_examiners.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+year_examiners.toUpperCase()+"%");
						}
						if (qualification_examiners != null && !qualification_examiners.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+qualification_examiners.toUpperCase()+"%");
						}
						if (teaching_experience != null && !teaching_experience.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+teaching_experience.toUpperCase()+"%");
						}
						if (teacher_code != null && !teacher_code.equals("")) {
							flag += 1;
							stmt.setInt(flag,Integer.parseInt(teacher_code));
						}
						if (reg_number != null && !reg_number.equals("")) {
							flag += 1;
							stmt.setInt(flag,Integer.parseInt(reg_number));
						}
						if( d_university_appointment != null && !d_university_appointment.equals("") && !d_university_appointment.equals("DD-MON-YYYY")) {
							flag += 1;
							stmt.setString(flag, d_university_appointment );
						}

					} catch (Exception e) {
					}

					return stmt;
				}
				public DG_REC_EXAMINERS_APPOINTED getugexamByid(int id) {
					Session session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					DG_REC_EXAMINERS_APPOINTED examIdAGE = (DG_REC_EXAMINERS_APPOINTED) session
							.get(DG_REC_EXAMINERS_APPOINTED.class, id);
					session.getTransaction().commit();
					session.close();
					return examIdAGE;
				}

				
				//GET ALL FORM DETAILS
				public ArrayList<ArrayList<String>> getpersonal_details(int userid) {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select li.login_name as name_homoeopathic_medical_clg from dg_rec_hospital_attached_child a\r\n"
								+ "inner join logininformation li on li.userid=a.user_id \r\n"
								+ "where a.user_id=?";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, userid);
						System.err.println("stmt================="+stmt);
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("name_homoeopathic_medical_clg"));// 0
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
				
				
				public ArrayList<ArrayList<String>> getUni_detail(int userid) {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select li.login_name as name_of_applicant_university from dg_rec_ug a\r\n"
								+ "inner join logininformation li on li.userid=a.user_id \r\n"
								+ "where a.user_id=?";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, userid);
						System.err.println("stmt================="+stmt);
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("name_of_applicant_university"));// 0
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
				
				
				@Override
				public long get_count_student(int institute_id){
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
				
						q="select count(*) from (select * from dg_rec_admitted_student_child where user_id = ?) as a";
						
//						 q= " select userid,institute_name from logininformation lo inner join userroleinformation ur on lo.userid=ur.user_id\r\n"
//							 		+ "inner join roleinformation ro on ro.role_id=ur.role_id and role='Institute_NCH' \r\n"
//							 		+ "inner join edu_lms_institute_reg ins on lo.institute_id=ins.id ";
//						 
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, institute_id);
						 System.err.println("---count----"+stmt);
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
				@SuppressWarnings("deprecation")
				public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getuniversitylistUrl(Integer selval) {

					System.err.println("selval------>>>>" + selval);

					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					try {

						Query q1 = sessionHQL
								.createQuery("from UserLogin lo,UserRole ur, Role ro,EDU_LMS_INSTITUTE_REGISTRATION ins where "
										+ "lo.userId = ur.userId and "
										+ "ro.roleId=ur.roleId and role='Institute_NCH' "
										+ "and lo.institute_id=ins.id and ins.university_id=:university_id");
						
						q1.setInteger("university_id", selval);
						@SuppressWarnings("unchecked")
						List<EDU_LMS_INSTITUTE_REGISTRATION> list = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q1.list();
						tx.commit();
						System.err.println(q1+"----list------>>>"+list);
						return list;
						
					} catch (RuntimeException e) {
						e.printStackTrace();
						return null;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
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
				query="  select academic_file_upload from dg_rec_ug_form_a_parent\n"
						+ "where id=?";
				
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(id));
				System.err.println("PROVISIONAL FORM A VIEW PAGE PDF------------------>>>>>>>>>>>"+stmt);
				ResultSet rs = stmt.executeQuery();
				
				System.err.println("PROVISIONAL FORM A VIEW PAGE PDF------------------>>>>>>>>>>>"+stmt);
				
				while (rs.next()) {
					whr = rs.getString("academic_file_upload");
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
				public String getFilePathQueryForDocAttFormB(String id){

					String whr = "";
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						String query = null;
//								query = "select upload_file from tb_nch_teacher_exp_child \n"
//										+ " where id = ? order by id";
						query="  select permission_from_central_gov from dg_rec_ug_form_a_parent\n"
								+ "where id=?";
						
						stmt = conn.prepareStatement(query);
						stmt.setInt(1, Integer.parseInt(id));
						System.err.println("PROVISIONAL FORM A VIEW PAGE PDF------------------>>>>>>>>>>>"+stmt);
						ResultSet rs = stmt.executeQuery();
						
						
						while (rs.next()) {
							whr = rs.getString("permission_from_central_gov");
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
	 

