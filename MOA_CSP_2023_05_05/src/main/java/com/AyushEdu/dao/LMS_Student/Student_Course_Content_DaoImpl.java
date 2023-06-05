package com.AyushEdu.dao.LMS_Student;

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
public class Student_Course_Content_DaoImpl implements Student_Course_Content_Dao {
	
	
	@Autowired
	private DataSource dataSource;
	
	
	
	@Override
public List<Map<String,Object>> Student_Course_nameDataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, String course_name, String module_name,String ref_video ,String upload_file, String app_status,String role) {
	
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name , module_name ,ref_video,upload_file, app_status);
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


		q = "SELECT  el.id,cm2.course_name,em.module_name,el.ref_video, el.app_status\n"
				+ "FROM public.edu_lms_student_course_content el\n"
				+ "inner join edu_lms_ele_course_mstr ec on ec.course_name = el.course_name::int and ec.status='1'\n"
				+ "inner join edu_lms_course_mstr cm2 on cm2.id=ec.course_name::int \n"
				+ "INNER join edu_lms_module_mstr em on em.id = el.module_name::int where el.id!= 0 "+ SearchValue + "  ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, course_name , module_name ,ref_video, upload_file,  app_status);
		
		
		System.out.println("stmt----------------------->"+stmt);
		ResultSet rs = stmt.executeQuery();
		

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		int j = startPage;
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("ser", ++j);
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			
			String action = "";
			String action1 = "";
			String action2 = "";
			String f1 = "";
			
			String f2 = "";
			String f3 = "";
			String f4 = "";
			
			String video = "";
			

			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('" + rs.getInt("id") +"') }else{ return false;}\"";
			f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
			
			String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f2 = "<i class='fa fa-check '  " + ADD2 + " title='Approve Data'></i>";
			
			String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			f3 = "<i class='fa fa-close '  " + ADD3 + " title='Reject Data'></i>";
			
			String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download ?') ){download_file("
					+ rs.getInt("id") + ")}else{ return false;}\"";
			f4 = "<i class='fa fa-download' " + Download + " title='Download' ></i>";
		
			
			video = "<a class='fa fa-play-circle-o' onclick='return videotopicCall("+rs.getString("id")+");' style='color: #04474a; font-size: 100%;'></a>";
			action = f1 + " " + f2 + " " + " " + f3 + " " + f4 + " " + video;
			
			action1 = f1 + " " + f4 + " " + video ;
			action2 = video;
//			columns.put("action", action);
//			columns.put("action", action1);
			
			
			
			if(role.equals("ADMIN")) {
				//action = f1+""+f2+""+f3+""+video;
//				String app_status1=rs.getString("app_status");
				
				
				if(app_status.equals("0")) {

					columns.put("action", action);
					}
				
				if(app_status.equals("1")) {

					columns.put("action", action1);
					
					}
				if(app_status.equals("2")) {

					columns.put("action", action2);
					
					}
			}else {
				
				columns.put("action", action2);
			}
			
			
//			columns.put("action", action);
//			
//			columns.put("video", video);
			
			
			
		
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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL( String Search,String course_name, String module_name,String ref_video,String upload_file,String app_status ) {
		

		String SearchValue = "";
		
		

				if (!course_name.trim().equals("0")) {
					SearchValue += " and el.course_name = ?::text ";

				}
				if (!module_name.trim().equals("0")) {
					SearchValue += " and el.module_name  = ?::text ";

				}
				
		if (app_status.equals("0")) {
			SearchValue += " and el.app_status :: int = ?::int ";
		}
		if(app_status.equals("1")) {
			SearchValue += " and el.app_status :: int = ?::int ";
		}
		if(app_status.equals("2")) {
			SearchValue += " and el.app_status :: int = ?::int ";
		}
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(cm2.course_name) like ? or upper(em.module_name) like ? )";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course_name, String module_name, String ref_video,String upload_file
			,String app_status){
		int flag = 0;
		int flag_i=1;
		try {
			
			if (!course_name.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name) );
			}
			if (!module_name.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(module_name));
			}


			if (app_status.equals("0")) {
				flag += 1;
				stmt.setInt(flag,0);
			}
			 if(app_status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			 if(app_status.equals("2")) {
				flag += 1;
				stmt.setInt(flag,2);
			}
			 
				if (Search != null && !Search.equals("")) {

					flag_i += 1;
					stmt.setString(flag_i, "%" + Search.toUpperCase() + "%");
					flag_i += 1;
					stmt.setString(flag_i, "%" + Search.toUpperCase() + "%");
				
				}
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	@Override
	public long DataTotalCount(String search, String course_name,String module_name,String ref_video,String upload_file, String app_status,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, course_name,module_name,ref_video,upload_file, app_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			 		
			 		q="select count(*) from ( SELECT cm2.course_name,em.module_name,el.ref_video, el.app_status FROM public.edu_lms_student_course_content el "
			 				+ "inner join edu_lms_ele_course_mstr ec on ec.course_name = el.course_name::int and ec.status='1'"
			 				+ "  inner join edu_lms_course_mstr cm2 on cm2.id=ec.course_name::int \n"
			 				+ " INNER join edu_lms_module_mstr em on em.id = el.module_name::int"
			 			
						+ SearchValue + ") ab  ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt,search, course_name,module_name,ref_video,upload_file, app_status);
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
	public String getFilePathQueryForDocFile(String id) {
	System.err.println("id---->>>"+id);
		String whr = "";
		String q1 = "";
		Connection conn = null;
		String fildname1 = "";
//		if(val.equals("1")) {
//			q1="persdetails";
//		}
//		else if(val.equals("2")){
//			q1="edudetails";
//		}
	//	
//		if (fildname.equals("resumedoc")) {
//			fildname1 = "resumedoc";
//		}
//		else if (fildname.equals("identitydoc")) {
//			fildname1 = "identitydoc";
//		}
//		else if (fildname.equals("3")) {
//			fildname1 = "identitydoc";
//		}


		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select upload_file from edu_lms_student_course_content where id=?";
			// query = query.replace("$fildname", fildname);

			stmt = conn.prepareStatement(query);
			System.err.println();
			stmt.setInt(1,Integer.parseInt(id));

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
	
	

}
