package com.AyushEdu.dao.Registration.Graduation_NCISM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.dao.AyushId_Directory.AyushId_DirectoryDAO;


@Repository
public class Std_Pers_Details_NCISMDaoimpl implements Std_Pers_Details_NCISMDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	AyushId_Directory_Controller directory;
	
	@Autowired
	AyushId_DirectoryDAO addao;
	
	@Override
	public EDU_NCISM_REG_GRADU_PERSONAL_DTLS getStudDetView_NcismByid(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		EDU_NCISM_REG_GRADU_PERSONAL_DTLS viewid = (EDU_NCISM_REG_GRADU_PERSONAL_DTLS) sessionHQL.get(EDU_NCISM_REG_GRADU_PERSONAL_DTLS.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return viewid;
	}
	
	public ArrayList<ArrayList<String>> getEdu_Detail_Ncism_data(String p_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			
			q="select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no, \n"
					+" ed.id, am.academic, ed.passing_year,ed.institute_name,ed.obtain_marks,ed.total_marks,edm.division_name as grade,ed.doc_path, ed.board_or_university,ed.school_or_collage,ed.subject,ed.id as docid from edu_ncism_reg_gradu_pre_edu_dtls_tbl ed\n"
					+ "inner join edu_ncism_reg_gradu_personal_dtls tpd on tpd.id = ed.p_id\n"
					+"inner join edu_academic_mstr am on am.id::text  = ed.academic\n"
					+"inner join logininformation mp2 on mp2.userid = tpd.p_id\n"
					+"inner join edu_division_mstr edm on edm.id::text = ed.grade\n"
					+ "where ed.p_id= ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			ResultSet rs = stmt.executeQuery();

			int countFunction=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("sr_no"));//0
				alist.add(rs.getString("academic"));//1
				alist.add(rs.getString("board_or_university"));//2
				alist.add(rs.getString("school_or_collage"));//3
				alist.add(rs.getString("subject"));//4
				alist.add(rs.getString("passing_year"));//5
				alist.add(rs.getString("obtain_marks"));//6
				alist.add(rs.getString("grade"));//7
				
				String vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm view_degree'  value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'>"
							+ "<input type='hidden' id='docid"+countFunction+"' value="+rs.getString("docid")+">"
							+ "</i></a> </li></ul>";
				
				alist.add(vd);//8
				alist.add(rs.getString("id"));//9
				list.add(alist);
				
				countFunction+=1;
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

	public ArrayList<ArrayList<String>> get_ayush_idbypid_Ncism_data(String pid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	        String sq1=" select \n"
	        		+ "upper(sd.ayush_id) as ayush_id,um.university_name,ir.institute_name,sm.system_name,dm.degree_name,sd.university_enrollment_no\n"
	        		+ "from edu_lms_student_details sd\n"
	        		+ "inner join logininformation l on sd.email= l.email_id\n"
	        		+" inner join edu_ncism_reg_gradu_personal_dtls pd on pd.p_id= l.userid\n"
	        		+ "inner join edu_lms_university_mstr um on um.id= l.university_id\n"
	        		+ "inner join edu_lms_institute_reg ir on ir.id= l.institute_id \n"
	        		+ "inner join edu_lms_system_mstr sm on sm.id= sd.system\n"
	        		+ "inner join edu_lms_degree_mstr dm on dm.id= sd.degree\n"
	        		+ "WHERE pd.id= ?";                         
	        
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(pid));
	       
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        while(rs.next()){
	        	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ayush_id"));// 0
					list.add(rs.getString("university_name"));// 1
					list.add(rs.getString("institute_name"));// 2
					list.add(rs.getString("system_name"));// 3
					list.add(rs.getString("degree_name"));// 4
					
					if ((rs.getString("university_enrollment_no") == null)){
						list.add("-");
					}else {
						list.add(rs.getString("university_enrollment_no"));// 5
								}
					
//					list.add(rs.getString("university_enrollment_no"));// 5
					
					alist.add(list);                           	  
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }       
	    
	    System.err.println("alist-------->   "+alist);
	   return alist;
	}

	@Override
	public ArrayList<ArrayList<String>> getuploaded_doc_Ncism_data(String p_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			
			q="select distinct ROW_NUMBER() OVER(order by d.id) as sr_no, d.id,dam.doc_name,d.id as docid\n"
					+ "	from edu_ncism_reg_gradu_other_doc_upload d \n"
					+ "inner join edu_doc_attachments_mstr dam on  dam.id =  d.doc_name\n"
					+ "where\n"
					+ "d.id!=0  and d.p_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			ResultSet rs = stmt.executeQuery();

			int countFunction=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("sr_no"));//0
				alist.add(rs.getString("doc_name"));//1
				alist.add(rs.getString("docid"));//2
				
				
				
				String vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm view_uploaddoc'  value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'>"
							+ "<input type='hidden' id='uploddocid"+countFunction+"' value="+rs.getString("docid")+">"
							+ "</i></a> </li></ul>";
				
				alist.add(vd);//3
				
				list.add(alist);
				
				countFunction+=1;
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
	
	
	
	public String update_verify_at_final_submit_ncism(String p_id,String system_id,HttpSession session) {
		Connection conn = null;
		int out = 0;
		String q="";
		String msg = "";
		
	String idddd = "";
	String enddate = "";
		
		
	 //end
	 	try {
			Session sessionHQL = this.sessionFactory.openSession();
			
			conn = dataSource.getConnection();
	    	
	        String sq1=" select sd.id,pd.p_id as userid,pd.pers_aadhar_no from edu_lms_student_details sd \n"
	        		+ "inner join  edu_ncism_reg_gradu_personal_dtls pd on pd.pers_email = sd.email\n"
	        		+ "WHERE pd.id = ?";                         
	        
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(p_id));
	        
	        System.err.println("stmt------>   "+stmt);
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        ArrayList<String> list = new ArrayList<String>();
	        
	        while(rs.next()){
	        	
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("userid"));// 1
					list.add(rs.getString("pers_aadhar_no"));// 2
					
					idddd=	rs.getString("id");
	        }
	        rs.close();
	        stmt.close();
	        
	        String sq2=" SELECT TO_CHAR(end_date , 'YYYYMMDD') as end_date  FROM edu_reg_admission_academic_schedule_mstr\n"
	        		+ "WHERE TO_CHAR(end_date, 'YYYY') = date_part('year', now())::text and status='1'  and system_id=?\n"
	        		+ "ORDER BY id ASC";                    
	        
	        PreparedStatement stmt2 = conn.prepareStatement(sq2);
	        stmt2.setString(1, system_id);
	        
	        System.err.println("stmt2------>   "+stmt2);
	        
	        ResultSet rs2 = stmt2.executeQuery();  
	        
	        String str2="";
	        while(rs2.next()){
	        	ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs2.getString("end_date"));// 0
					enddate = rs2.getString("end_date");
	        }
	        rs2.close();
	        stmt2.close();
	        
	       String today = new SimpleDateFormat("YYYYMMdd").format(new Date());
	       
	       System.err.println(" enddate ----------->      "+enddate  +"  today ---------------->   "+today);
	       String maxAID = addao.getAyushID(list.get(1),session);
	       
					int id = Integer.parseInt(idddd);
					
					
					System.err.println("id -------->  "+id   +" idddd------->   "+idddd );
					
					
					Transaction  tx= sessionHQL.beginTransaction();
					EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, id);
					
					if (enddate.equals("") || enddate == "") {
						sd.setLate_admission_status(0);
					} else if (Integer.parseInt(enddate) < Integer.parseInt(today)) {
						sd.setLate_admission_status(1);
					} else {
						sd.setLate_admission_status(0);
					}
					
					if (sd.getAyush_id() == null || sd.getAyush_id().equals("null") || sd.getAyush_id().equals("")) {
						sd.setAyush_id(maxAID);
						Boolean Directory = directory.SaveAyushId_Directory(maxAID, String.valueOf(list.get(1)), list.get(2), "46", session);
					}
					sd.setVerified_status(-1);
						sessionHQL.update(sd);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						msg="Student -1";
						out = 1;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			msg="Student 0";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return msg;
		}

	public ArrayList<ArrayList<String>> getuploaded_Court_Order_NCISM_data(String p_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			
			q="select doc.id,doc.court_order,p_id,remarks from edu_ncism_reg_gradu_document_upload doc  where doc.id!=0  and court_order is not null and doc.p_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			ResultSet rs = stmt.executeQuery();

			int countFunction=1;
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));//0
				alist.add(rs.getString("court_order"));//1
				alist.add(rs.getString("p_id"));//2
				
				String vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-iconic-icon view_uploadDraftdoc'  value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'>"
							+ "<input type='hidden' id='draftdocid"+countFunction+"' value="+rs.getString("id")+">"
							+ "</i>View</a> </li></ul>";
				
				alist.add(vd);//3
				alist.add(rs.getString("remarks"));//4
				
				list.add(alist);
				countFunction+=1;
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
	
}
