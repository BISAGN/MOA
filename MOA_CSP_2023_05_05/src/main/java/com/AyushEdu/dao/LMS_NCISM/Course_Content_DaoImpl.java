package com.AyushEdu.dao.LMS_NCISM;

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
	public class Course_Content_DaoImpl implements  Course_Content_Dao{	
	

		@Autowired
		private DataSource dataSource;	
		
		
@Override
	public List<Map<String,Object>> Course_nameDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name, String module_name,String level_of_module,String type_of_content,String system_name, String degree,String role) {
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name , module_name ,level_of_module,type_of_content, system_name,degree);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			
//			System.err.println("app_status"+app_status);

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

//			q="SELECT  el.id, toc.type_of_content,sm.system_name, ec.course_name,em.module_name,  lm.level_name as level_of_course, lm1.level_name as level_of_module, el.app_status,\n"
//					+ "el.upload_ppt,el.other_note,el.upload_file,el.ref_video\n"
//					+ "FROM public.edu_lms_course_content el\n"
//					+ "inner join edu_lms_ele_course_mstr ec on ec.id = el.course_name::int and ec.status='1'\n"
//					+ "INNER join edu_lms_module_mstr em on em.id = el.module_name::int \n"
//					+ "INNER join edu_lms_type_of_content_mstr toc on toc.id = el.type_of_content::int \n"
//					+ "INNER join edu_lms_level_mstr lm on lm.id = el.level_of_course::int \n"
//					+ "INNER join edu_lms_level_mstr lm1 on lm1.id = el.level_of_module::int\n"
//					+ "INNER join edu_lms_system_mstr sm on sm.id = el.system_name::int\n"
//					+ "where el.app_status ="+"'"+ app_status +"'"+""+  SearchValue+ "\n"
//					+ " order by lm1.level_name  " + orderType 
//					+ " limit " + pageL + " OFFSET " + startPage;
			
			q=" SELECT ROW_NUMBER() OVER(order by el.id ASC) as sr_no, el.id, toc.type_of_content,sm.system_name, cm.course_name,em.module_name,lm1.level_name as level_of_module, el.app_status,\n"
					+ "ccc.upload_ppt,ccc.other_note,ccc.upload_file,ccc.ref_video,d.degree_name,ccc.id as cid\n"
					+ "FROM public.edu_lms_course_content el\n"
					+ "INNER join edu_lms_course_content_child ccc on ccc.p_id = el.id::int \n"
					+ "INNER join edu_lms_degree_mstr d on d.id = el.degree_name \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=el.course_name::int \n"
					+ "INNER join edu_lms_module_mstr em on em.id = ccc.module \n"
					+ "INNER join edu_lms_type_of_content_mstr toc on toc.id = el.type_of_content::int \n"
					+ "INNER join edu_lms_level_mstr lm1 on lm1.id = ccc.level_of_module::int\n"
					+ "INNER join edu_lms_system_mstr sm on sm.id = el.system_name::int\n"
					+ "inner join roleinformation r on r.staff_lvl=sm.created_role \n"
					+ "where el.app_status ="+"'"+ 1 +"' and r.role=? "+SearchValue+ "\n"
					+ " order by  el.id " + orderType 
					+ " limit " + pageL + " OFFSET " + startPage;

			
			


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, course_name , module_name  ,level_of_module,type_of_content, system_name,degree);
			
			
			System.err.println("stmt-- ------>  "+stmt);
			ResultSet rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction = 1;
			int countFunction1 = 1;
			int countFunction2 = 1;
			int countFunction3 = 1;
			int countFunction4 = 1;
			int countFunction5 = 1;
			int countFunction6 = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String action = "";
				String action1 = "";
				String action2 = "";
				String	content="";
				
				String f1 = "";
				
				String f2 = "";
				String f3 = "";
				String f4 = "";
				String f5 = "";
				String f6 = "";
				
				
                String video = "";
				
				String ul1="";
				ul1+="<ul class='buttons-group mainbtn action daobtn'>";
				
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm Delete'  title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='delete_id" + countFunction
						+ "' value=" + rs.getInt("id") + "></i></a></li>";
				ul1+= f1; 
				ul1+="</ul>";
				
//				String ADD2 = "onclick=\" if (confirm('Are You Sure You Want to Approve Detail ?') ){Accepturl('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f2 = "<li><a class='main-btn success-btn btn-hover btn-sm'><i class='lni lni-checkmark'  " + ADD2 + " title='Approve Data'></i></a></li>";
//				f2 = "<li><a class='main-btn success-btn btn-hover btn-sm ApproveID'  title='Approve Data'>"
//						+ "<i class='lni lni-checkmark'>" + "<input type='hidden' id='approve_id" + countFunction1
//						+ "' value=" + rs.getString("id") + "></i></a></li>";
				
//				String ADD3 = "onclick=\" if (confirm('Are You Sure You Want to Reject Detail ?') ){Rejecturl('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f3 = "<li><a class='main-btn danger-btn btn-hover btn-sm'><i class='lni lni-close'  " + ADD3 + " title='Reject Data'></i></a></li>";
//				
//				f3 = "<li><a class='main-btn danger-btn btn-hover btn-sm RejectId'  title='Reject Data'>"
//						+ "<i class='lni lni-close'>" + "<input type='hidden' id='reject_id" + countFunction2
//						+ "' value=" + rs.getString("id") + "></i></a></li>";
//				
				String ul2="";
				ul2+="<ul class='buttons-group mainbtn action daobtn'>";
				
//				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download PDF?') ){downloadnote_file("
//						+ rs.getInt("cid") + ",'upload_file')}else{ return false;}\"";
//				
//				f4 = "<li><a class='main-btn secondary-btn  btn-sm btn-hover btn-iconic-icon Download'  title='Download PDF'>"
//						+ "<i class='bi bi-file-pdf m-0'>" + "<input type='hidden' id='download_id" + countFunction3
//						+ "' value=" + rs.getInt("cid") + "></i></a></li>";
				
				if(!rs.getString("upload_file").trim().isEmpty()) {
//				f4 = "<li><a class='main-btn secondary-btn  btn-sm btn-hover btn-iconic-icon'><i class='bi bi-file-pdf m-0' " + Download + " title='Download PDF' ></i></a></li>";
					f4 = "<li><a class='main-btn info-btn btn-hover btn-sm btndownload Download'  title='Download PDF'>"
							+ "<i class='lni lni-download'>" + "<input type='hidden' id='download_id" + countFunction3
							+ "' value=" + rs.getString("cid") +"></i></a></li>";
				}
				if(!rs.getString("other_note").trim().isEmpty()) {
				f5 = "<li><a class='main-btn deactive-btn btn-hover btn-sm Download_Other_NoteID'  title='Download Note'>"
						+ "<i class='bi bi-file-earmark-text m-0'>" + "<input type='hidden' id='download_id1" + countFunction4
						+ "' value=" + rs.getString("cid") +"></i></a></li>";
				
				}
//				String Downloadppt = "onclick=\"  if (confirm('Are You Sure You Want to Download PPT ?') ){downloadnote_file("
//						+ rs.getInt("cid") + ", 'upload_ppt' )}else{ return false;}\"";
				if(!rs.getString("upload_ppt").trim().isEmpty()) {
					f6 = "<li><a class='main-btn light-btn btn-hover btn-sm Download_PPT_Id'  title='Download PPT'>"
							+ "<i class='bi bi-file-earmark-ppt-fill m-0'>" + "<input type='hidden' id='download_ppt_id" + countFunction5
							+ "' value=" + rs.getString("cid") +"></i></a></li>";
				}


				if(rs.getString("ref_video") != null) {
					
					video = "<li><a class='main-btn active-btn btn-hover btn-sm btnvideo  Download_Vedio'  title='View Vedio'>"
							+ "<i class='bi bi-play-circle'>" + "<input type='hidden' id='download_Vedio" + countFunction6
							+ "' value=" + rs.getString("cid") +"></i></a></li>";	
//				video = "<li><a class='main-btn active-btn btn-hover' onclick='return videotopicCall("+rs.getString("cid")+");'><i class='bi bi-play-circle'></i></a></li>";
				}
				
				
				
				ul2+= f4 + " " + f5 + " " + f6 + " " + video ; 
				ul2+="</ul>";
				
				action1 = ul1 ;
				countFunction += 1;
				countFunction1 += 1;
				countFunction2 += 1;
				countFunction3 += 1;
				countFunction4 += 1;
				countFunction5 += 1;
				 countFunction6 += 1;
				content =ul2 ;
					columns.put("content", content);
					
					
					columns.put("action", action1);
				
				
			
				list.add(columns);
//				}
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

public String GenerateQueryWhereClause_SQL( String Search,String course_name, String module_name,String level_of_module,String type_of_content,String system_name,String degree ) {
	
	System.err.println("Search-----GenerateQueryWhereClause_SQL----------->    "+Search);
	
	String SearchValue = "";
	
			

			if (!course_name.trim().equals("0")) {
				SearchValue += " and el.course_name = ? ";
			}
//			if (!module_name.equals("0")) {
//				SearchValue += " and el.module_name  = ? ";
//			}
//			if (!level_of_course.trim().equals("0")) {
//				SearchValue += " and el.level_of_course = ? ";
//			}
			
			if (!type_of_content.trim().equals("0")) {
				SearchValue += " and el.type_of_content  = ? ";
			}
			
			if (!system_name.trim().equals("0")) {
				SearchValue += " and el.system_name = ? ";
			}
			if (!degree.trim().equals("0")) {
				SearchValue += " and el.degree_name  = ? ";
			}
			if (!Search.equals("")) {
				Search = Search.toLowerCase();

				SearchValue += " and ";
				SearchValue += "  (lower( toc.type_of_content) like ? or lower(cm.course_name) like ? or lower(em.module_name) like ? "
						+ "\n or lower( lm1.level_name) like ? or  lower( sm.system_name) like ? or  lower( d.degree_name) like ?)"	;
				
				
			}
			
	
	return SearchValue;
}
	
public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course_name, String module_name, String level_of_module
		,String type_of_content,String system_name,String degree){
	
	
	System.err.println("Search-----setQueryWhereClause_SQL-------"+Search);
	int flag = 1;
	try {

		if (!course_name.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(course_name ));
		}
//		if (!module_name.equals("0")) {
//			flag += 1;
//			stmt.setString(flag,module_name);
//		}
//		if (!level_of_course.trim().equals("0")) {
//			flag += 1;
//			stmt.setString(flag,level_of_course);
//		}
		
		if (!type_of_content.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(type_of_content));
		}
		
		if (!system_name.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(system_name));
		}
		if (!degree.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(degree));
		}
		if (!Search.equals("")) {
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toLowerCase() + "%");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
}

@Override
public long DataTotalCount(String Search, String course_name,String module_name,String level_of_module, String type_of_content,String system_name,String degree, String role) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name,module_name,level_of_module,type_of_content, system_name,degree);
	
	System.err.println("Search ----total---->  "+Search);
	
	
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		
		
		q="select count(*) from ( SELECT  el.id, toc.type_of_content,sm.system_name, cm.course_name,em.module_name,   lm1.level_name as level_of_module, el.app_status,\n"
				+ "ccc.upload_ppt,ccc.other_note,ccc.upload_file,ccc.ref_video,d.degree_name\n"
				+ "FROM public.edu_lms_course_content el\n"
				+ "INNER join edu_lms_course_content_child ccc on ccc.p_id = el.id::int \n"
				+ "INNER join edu_lms_degree_mstr d on d.id = el.degree_name \n"
				+ "inner join edu_lms_course_mstr cm on cm.id=el.course_name::int \n"
				+ "INNER join edu_lms_module_mstr em on em.id = ccc.module \n"
				+ "INNER join edu_lms_type_of_content_mstr toc on toc.id = el.type_of_content::int \n"
				+ "INNER join edu_lms_level_mstr lm1 on lm1.id = ccc.level_of_module::int\n"
				+ "INNER join edu_lms_system_mstr sm on sm.id = el.system_name::int\n"
				+ "inner join roleinformation r on r.staff_lvl=sm.created_role \n"
				+ "where el.app_status ="+"'"+ 1 +"' and r.role=? "+SearchValue+ ") ab";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setString(1, role);
		stmt = setQueryWhereClause_SQL(stmt,Search, course_name,module_name,level_of_module,type_of_content, system_name,degree);
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
public String getFilePathQueryForDocFile(String id, String fildname) {
	
	
	System.err.println("fildname................."+fildname);
	
	String whr = "";
	String q1 = "";
	Connection conn = null;
	String fildname1 = "";
//	if(val.equals("1")) {
//		q1="persdetails";
//	}
//	else if(val.equals("2")){
//		q1="edudetails";
//	}

	if (fildname.equals("upload_file")) {
		fildname1 = "resumedoc";
	} else if (fildname.equals("other_note")) {
		fildname1 = "identitydoc";
	} else if (fildname.equals("upload_ppt")) {
		fildname1 = "identitydoc";
	}

	try {

		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
//		query = "select $fildname from edu_lms_course_content where id=?";
		query="select $fildname from edu_lms_course_content_child where id=?";
		query = query.replace("$fildname", fildname);

		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			whr = rs.getString(fildname);
		}
		rs.close();
		stmt.close();
		conn.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;

}

public ArrayList<ArrayList<String>> getlevelofmodule(String id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		
	q="select cc.level_of_module,lm.level_name from edu_lms_course_content cc\n"
			+ "inner join edu_lms_level_mstr lm on lm.id=cc.level_of_module::int\n"
			+ "where cc.id=?";
		
		
		PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("level_of_module"));// 0
		
			list.add(alist);
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
