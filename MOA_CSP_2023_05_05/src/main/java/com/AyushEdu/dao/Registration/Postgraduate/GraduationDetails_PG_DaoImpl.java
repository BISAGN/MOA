package com.AyushEdu.dao.Registration.Postgraduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class GraduationDetails_PG_DaoImpl implements GraduationDetails_PG_Dao {
	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	
	
	public ArrayList<ArrayList<String>> getaddmoredata1(String p_id) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

//			q = "select mp.id,mp.institute_name,mp.depart_name,mp.employment_type,mp.honorarium,mp.desig,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,"
//					+ "round(abs(from_date :: date - to_date :: date)/365.25,1) as yr_of_exp,\n"
//					+ "mp.upload_file from tb_lms_teacher_exp_child mp where mp.p_id = ? order by mp.id";
			
			q = "SELECT id, name_of_exam,month_year,university,no_of_attempts,universityother  FROM public.edu_pg_reg_gradu_dtls WHERE p_id = ?  ORDER BY name_of_exam asc" ;
			
			stmt = conn.prepareStatement(q);
					stmt.setInt(1, Integer.parseInt(p_id));
					System.err.println("stm-------mmmmmmmmmmmm------"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name_of_exam"));// 1
				list.add(rs.getString("month_year"));// 2
				list.add(rs.getString("university"));// 3
				list.add(rs.getString("no_of_attempts"));// 4
				list.add(rs.getString("universityother"));// 5
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
	public ArrayList<ArrayList<String>> DataTableGraduation_PGDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name_of_exam, String month_year, String university,
			String no_of_attempts) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name_of_exam, month_year, university, no_of_attempts);
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
			
//			SELECT id, name_of_exam,month_year,university,no_of_attempts FROM public.edu_pg_reg_gradu_dtls ORDER by name_of_exam

			q="select distinct ROW_NUMBER() OVER(order by id) as sr_no, \n"
					+" id, name_of_exam, to_char(TO_DATE(month_year,'YYYY-MM'),'MONTH - YYYY') as month_year,\n"
					+ " university,no_of_attempts from edu_pg_reg_gradu_dtls \n"
					+ "where id!=0  " + SearchValue + "  ORDER BY id  "+ orderType +"  limit "+ pageL +"  OFFSET "+ startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name_of_exam, month_year, university, no_of_attempts);

			System.err.println("stmt---- rs ----->   "+stmt);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;
			
			
//			int countDownload=1;
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

//				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Education Details ?') )"   
//						+ "{editData('"+ rs.getInt("id") + "','"+rs.getString("name_of_exam_id")+"','"+rs.getInt("passing_year")+"','" +rs.getString("board_or_university")+ "',"
//								+ "'"+rs.getInt("percentage_of_marks")+"','"+rs.getString("school_or_collage")+"','"+rs.getString("division")+"','"+rs.getString("subject")+"','"+rs.getString("doc_path")+"')}else{ return false;}\"";
				
//				f =  "<li><a class='main-btn active-btn btn-hover btn-sm addAPP' value='ADD' title='Edit Data' ><i class='lni lni-pencil-alt'>"
//						+ "<input type='hidden' id='upDOWN"+countFunctionAdd+"' value="+rs.getString("id")+"></i></a></li>";
				
//				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Education Details?') )"
//						+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
				
				f2 ="<li><a class='main-btn danger-btn btn-hover btn-sm removeAPP' value='Delete'  title='Delete Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-trash-can'>"
								+"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"> </i></a> </li> ";
				
				 ul+=f + " " + f2 ;
				 ul+="</ul>";
				 
				 action = ul;
				
				 countFunctionAdd+=1;
				 countFunctionDelete+=1;
				 
//				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Education Details ?') )"
//						+ "{getDownloadPdfEdu('"+ rs.getInt("id") + "')}else{ return false;}\"";
//				
//				f1 = "<ul class='buttons-group mainbtn action'>" + "<li><a class='main-btn info-btn btn-hover btn-sm eddownload' value='download'  title='Downlaod' ><i class='lni lni-download'>"
//						+ "<input type='hidden' id='dlID"+countDownload+"' value="+rs.getInt("id")+"></i></a></li></ul>";
				
//				 countDownload+=1;
				
				list.add(rs.getString("sr_no"));//0
				list.add(rs.getString("name_of_exam"));//1
				list.add(rs.getString("month_year"));//2
				list.add(rs.getString("university"));//3
				list.add(rs.getString("no_of_attempts"));//4
				
//				list.add(f1);
				list.add(action);//5
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
	public long DataTableGraduation_PGDataTotalCount(String Search, String name_of_exam, String month_year,
			String university, String no_of_attempts) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name_of_exam, month_year, university, no_of_attempts);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by id) as sr_no,\n"
				+" id, name_of_exam,month_year,university,no_of_attempts from edu_pg_reg_gradu_dtls\n"
//				+ "inner join edu_pg_reg_personal_details tpd on tpd.id = ed.p_id\n"
//				+ "left join t_domain_value m on m.codevalue=cast(ed.name_of_exam as text) and m.domainid='ACADEMIC'\n"
//				+"inner join logininformation mp2 on mp2.userid = tpd.p_id\n"
				+ "where id!=0"
					+ SearchValue + ") ab";
			
			PreparedStatement stmt = conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt, Search, name_of_exam, month_year, university, no_of_attempts);
			
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
	
	public String GenerateQueryWhereClause_SQL(String Search, String name_of_exam, String month_year,
			String university, String no_of_attempts) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and ( upper(name_of_exam) like ? or upper(university) like ? or upper(no_of_attempts) like ? \n"
					+ "or upper(to_char(TO_DATE(month_year,'YYYY-MM'),'MONTH - YYYY')) like ? ) ";
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name_of_exam, String month_year,
			String university, String no_of_attempts) {
		
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
				
			}

		} catch (Exception e) {
		}

		return stmt;
	}





	@Override
	public ArrayList<ArrayList<String>> getaddmoredata2(String p_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

//			q = "select mp.id,mp.institute_name,mp.depart_name,mp.employment_type,mp.honorarium,mp.desig,TO_CHAR(mp.from_date, 'DD/MM/YYYY') as from_date,TO_CHAR(mp.to_date, 'DD/MM/YYYY') as to_date,"
//					+ "round(abs(from_date :: date - to_date :: date)/365.25,1) as yr_of_exp,\n"
//					+ "mp.upload_file from tb_lms_teacher_exp_child mp where mp.p_id = ? order by mp.id";
			
			q = "SELECT  id,subject, semwise_no_of_atmpts, maxmark_y1_,obtainmark_y1_,maxmark_y2_,obtainmark_y2_,maxmark_y3_,obtainmark_y3_,maxmark_y4_,obtainmark_y4_,percentage_of_marks,remarks FROM public.edu_pg_reg_gradu_semwise_dtls\n"
					+ "WHERE p_id = ? ORDER BY id asc";
			
			stmt = conn.prepareStatement(q);
					stmt.setInt(1, Integer.parseInt(p_id));
					System.err.println("stm-------mmmmmmmmmmmm------"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("subject"));// 1
				list.add(rs.getString("semwise_no_of_atmpts"));// 2
				list.add(rs.getString("maxmark_y1_"));// 3
				list.add(rs.getString("obtainmark_y1_"));// 4
				list.add(rs.getString("maxmark_y2_"));// 5
				list.add(rs.getString("obtainmark_y2_"));// 6
				list.add(rs.getString("maxmark_y3_"));// 7
				list.add(rs.getString("obtainmark_y3_"));// 8
				list.add(rs.getString("maxmark_y4_"));// 9
				list.add(rs.getString("obtainmark_y4_"));// 10
				list.add(rs.getString("percentage_of_marks"));// 11
				list.add(rs.getString("remarks"));// 12
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
	
	
	
	
}
