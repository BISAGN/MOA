package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Registration.TB_PRE_EDUCATION_DETAILS;




@Service
@Repository
public class EducationDetailsDaoImpl implements EducationDetailsDao {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<ArrayList<String>> DataTableEducationDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic, Integer passing_year, String institute_name,
			 Integer obtain_marks,Integer total_marks, String grade, String doc_path,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, academic, passing_year, institute_name, obtain_marks,total_marks,
				 grade, doc_path,userid);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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

			q="select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no, \n"
					+" ed.id, am.academic, ed.passing_year,ed.institute_name,ed.obtain_marks,ed.total_marks,edm.division_name as grade,ed.doc_path, ed.board_or_university,ed.school_or_collage,ed.subject,am.id as academic_id from edu_reg_gradu_pre_edu_dtls_tbl ed\n"
					+ "inner join edu_reg_gradu_personal_dtls tpd on tpd.id = ed.p_id\n"
					+ "inner join edu_academic_mstr am on am.id::text  = ed.academic\n"
//					+ "left join t_domain_value m on m.codevalue=cast(ed.academic as text) and m.domainid='ACADEMIC'\n"
					+"inner join logininformation mp2 on mp2.userid = tpd.p_id\n"
					+"inner join edu_division_mstr edm on edm.id::text = ed.grade\n"
					+ "where ed.id!=0  " + SearchValue + "  ORDER BY ed.id  "+ orderType +"  limit "+ pageL +"  OFFSET "+ startPage;
			
			
//			q = "select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no, ed.id,m.label as academic,ed.passing_year,ed.institute_name,ed.obtain_marks,ed.total_marks,ed.grade,ed.doc_path from edu_reg_gradu_pre_edu_dtls_tbl ed \n"
//					+ "inner join tb_registration_dtl mp on mp.recr_email =  ed.created_by \n"
//					+ "inner join logininformation mp2 on mp2.username =  mp.recr_email \n"
//					+ "left join t_domain_value m on m.codevalue=cast(ed.academic as text) and m.domainid='ACADEMIC'\n"
//					+ "where ed.id!=0 " + SearchValue + " ORDER BY ed.id " + orderType + " limit " + pageL + " OFFSET "
//					+ startPage; // "+orderColunm +"

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, academic, passing_year, institute_name, obtain_marks, total_marks,
					 grade, doc_path,userid);

			System.err.println("stmt---- rs ----->   "+stmt);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;
			
			
			int countDownload=1;
			int countFunctionAdd=1;
	    	  int countFunctionDelete=1;
			
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
				
				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Education Details ?') )"   
						+ "{editData('"+ rs.getInt("id") + "','"+rs.getString("academic_id")+"','"+rs.getInt("passing_year")+"','" +rs.getString("board_or_university")+ "',"
								+ "'"+rs.getInt("obtain_marks")+"','"+rs.getString("school_or_collage")+"','"+rs.getString("grade")+"','"+rs.getString("subject")+"','"+rs.getString("doc_path")+"')}else{ return false;}\"";
				
				f =  "<li><a class='main-btn active-btn btn-hover btn-sm addAPP' value='ADD' title='Edit Data' ><i class='lni lni-pencil-alt'>"
						+ "<input type='hidden' id='upDOWN"+countFunctionAdd+"' value="+rs.getString("id")+"></i></a></li>";
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Education Details?') )"
						+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
				
				f2 ="<li><a class='main-btn danger-btn btn-hover btn-sm removeAPP' value='Delete'  title='Delete Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-trash-can'>"
								+"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"> </i></a> </li> ";
				
				 ul+=f + " " + f2 ;
				 ul+="</ul>";
				 
				 action = ul;
				
				 countFunctionAdd+=1;
				 countFunctionDelete+=1;
				 
				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Education Details ?') )"
						+ "{getDownloadPdfEdu('"+ rs.getInt("id") + "')}else{ return false;}\"";
				
				f1 = "<ul class='buttons-group mainbtn action'>" + "<li><a class='main-btn info-btn btn-hover btn-sm eddownload' value='download'  title='Downlaod' ><i class='lni lni-download'>"
						+ "<input type='hidden' id='dlID"+countDownload+"' value="+rs.getInt("id")+"></i></a></li></ul>";
				
				 countDownload+=1;
				
				list.add(rs.getString("sr_no"));//0
				list.add(rs.getString("academic"));//1
				list.add(rs.getString("passing_year"));//2
				list.add(rs.getString("institute_name"));//3
				list.add(rs.getString("obtain_marks"));//4
				list.add(rs.getString("total_marks"));//5
				list.add(rs.getString("grade"));//6
				list.add(rs.getString("board_or_university"));//7
				list.add(rs.getString("school_or_collage"));//8
				list.add(rs.getString("subject"));//9
				
				//list.add(rs.getString("doc_path"));//7
				list.add(f1);
				list.add(action);
				list.add(rs.getString("id"));//9
				list.add(rs.getString("doc_path"));
//				list.add(f2);
				i++;
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
	public long DataTableEducationDataTotalCount(String Search, String academic, Integer passing_year,
			String institute_name, Integer obtain_marks, Integer total_marks, String grade, String doc_path,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, academic, passing_year, institute_name, obtain_marks, total_marks,
				 grade, doc_path,userid);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by ed.id) as sr_no,\n"
				+" ed.id, am.academic, ed.passing_year,ed.institute_name,ed.obtain_marks,ed.total_marks,edm.division_name as grade,ed.doc_path, ed.board_or_university,ed.school_or_collage,ed.subject,am.id as academic_id from edu_reg_gradu_pre_edu_dtls_tbl ed\n"
				+ "inner join edu_reg_gradu_personal_dtls tpd on tpd.id = ed.p_id\n"
				+ "inner join edu_academic_mstr am on am.id::text  = ed.academic\n"
//				+ "left join t_domain_value m on m.codevalue=cast(ed.academic as text) and m.domainid='ACADEMIC'\n"
				+"inner join logininformation mp2 on mp2.userid = tpd.p_id\n"
				+"inner join edu_division_mstr edm on edm.id::text = ed.grade\n"
				+ "where ed.id!=0"
					+ SearchValue + ") ab  ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt, Search, academic, passing_year, institute_name, obtain_marks, total_marks,
					 grade, doc_path,userid);
			
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

	public String GenerateQueryWhereClause_SQL(String Search, String academic, Integer passing_year, String institute_name,
			 Integer obtain_marks, Integer total_marks, String grade, String doc_path,int userid) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and ( upper(ed.board_or_university) like ? or upper(ed.subject) like ? or upper(ed.school_or_collage) like ? \n"
					+ "or upper(am.academic::text) like ? or ed.passing_year::text like ?  or ed.obtain_marks::text like ? \n"
					+ "  or upper(ed.grade) like ? or upper(ed.doc_path) like ?) ";
		}

		if (userid != 0) {
			SearchValue += " and mp2.userid = ? ";
		}
		
//		if (passing_year != 0) {
//			SearchValue += " and ed.passing_year like ? ";
//		}
//		if (!institute_name.equals("") && institute_name != null) {
//			SearchValue += " and upper(ed.institute_name)like ? ";
//		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String academic,
			Integer passing_year, String institute_name, Integer obtain_marks, Integer total_marks, String grade, String doc_path,int userid) {
		
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
			}
			
//			if (!academic.equals("0") && academic != null) {
//				System.err.println("flag----b-----"+flag);
//				flag += 1;
//				stmt.setString(flag, "%" + academic.toUpperCase() + "%");
//			}
//			if (passing_year != 0) {
//				System.err.println("flag---c------"+flag);
//				flag += 1;
//				stmt.setInt(flag, passing_year);
//			}
//			if (!institute_name.equals("") && institute_name != null) {
//				System.err.println("flag-----d----"+flag);
//				flag += 1;
//				stmt.setString(flag, "%" + institute_name.toUpperCase() + "%");
//			}
//			if (obtain_marks != 0 && obtain_marks != null) {
//				System.err.println("flag-----e----"+flag);
//				flag += 1;
//				stmt.setInt(flag,  obtain_marks);
//			}
//			if (total_marks != 0 && total_marks != null) {
//				System.err.println("flag---f------"+flag);
//				flag += 1;
//				stmt.setInt(flag, total_marks);
//			}
//			if (!grade.equals("") && grade != null) {
//				System.err.println("flag---g------"+flag);
//				flag += 1;
//				stmt.setString(flag, "%" + grade.toUpperCase() + "%");
//			}
//			if (!doc_path.equals("") && doc_path != null) {
//				System.err.println("flag-----h----"+flag);
//				flag += 1;
//				stmt.setString(flag, "%" + doc_path.toUpperCase() + "%");
//			}
			
			if (userid != 0) {
				flag += 1;
				stmt.setInt(flag, userid);
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public String getFilePathQuery(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select doc_path from edu_reg_gradu_pre_edu_dtls_tbl where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("doc_path");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
	
	public String updateSubCategory(TB_PRE_EDUCATION_DETAILS obj){
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update TB_PRE_EDUCATION_DETAILS set academic=:academic,passing_year=:passing_year,board_or_university=:board_or_university,school_or_collage=:school_or_collage,subject=:subject,obtain_marks=:obtain_marks,"
					+ "grade=:grade,doc_path=:doc_path,modified_by=:modified_by,modified_date=:modified_date where id=:id";		
			
			@SuppressWarnings("rawtypes")
			Query query = sessionHQL.createQuery(hql)
					.setParameter("academic",obj.getAcademic())
					.setParameter("passing_year",obj.getPassing_year())
					.setParameter("board_or_university",obj.getBoard_or_university())
					.setParameter("school_or_collage",obj.getSchool_or_collage())
					.setParameter("subject",obj.getSubject())
					//.setParameter("institute_name",obj.getInstitute_name())
					.setParameter("obtain_marks",obj.getObtain_marks())
					//.setParameter("total_marks",obj.getTotal_marks())
					.setParameter("grade",obj.getGrade())
					.setParameter("doc_path",obj.getDoc_path())
					.setParameter("modified_by",obj.getModified_by())
					.setParameter("modified_date",obj.getModified_date())
					.setParameter("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	}

	@Override
	public ArrayList<ArrayList<String>> geteditEducation_data(String id) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	       Connection conn = null;
	       try{          
	       	conn = dataSource.getConnection();
	           String sq1=" select * from edu_reg_gradu_pre_edu_dtls_tbl WHERE id = ?";                         
	           
	           PreparedStatement stmt = conn.prepareStatement(sq1);
	           stmt.setInt(1, Integer.parseInt(id));
	          
	           ResultSet rs = stmt.executeQuery();  
	           
	           String str1="";
	           while(rs.next()){
	           	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("academic"));// 0
					list.add(rs.getString("board_or_university"));// 0
					list.add(rs.getString("school_or_collage"));// 0
					list.add(rs.getString("subject"));// 0
					list.add(rs.getString("passing_year"));// 0
					list.add(rs.getString("obtain_marks"));// 0
					list.add(rs.getString("grade"));// 0
					list.add(rs.getString("doc_path"));// 0
					alist.add(list);                           	  
	           }
	           rs.close();
	           stmt.close();
	           conn.close();
	      }catch(SQLException e){
	   	   e.printStackTrace();
	      }        
	      return alist;
	}

	public ArrayList<ArrayList<String>> Getedu_chekyear_nch_data(String p_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	       Connection conn = null;
	       try{          
	       	conn = dataSource.getConnection();
	           String sq1=" select academic,passing_year from edu_reg_gradu_pre_edu_dtls_tbl WHERE p_id =?";                         
	           
	           PreparedStatement stmt = conn.prepareStatement(sq1);
	           stmt.setInt(1, Integer.parseInt(p_id));
	          
	           ResultSet rs = stmt.executeQuery();  
	           String str1="";
	           while(rs.next()){
	           	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("academic"));// 0
					list.add(rs.getString("passing_year"));// 1
					alist.add(list);                           	  
	           }
	           rs.close();
	           stmt.close();
	           conn.close();
	      }catch(SQLException e){
	   	   e.printStackTrace();
	      }        
	       System.err.println("tkkk -------->  "+alist);
	       
	      return alist;
	}

}	


