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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class View_Course_Content_DaoImpl implements View_Course_Content_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Map<String, Object>> DataTableviewcourse_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name,String module_name,String type_content,String level_of_module,String userid,HttpSession session) {
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name,module_name,type_content,level_of_module,userid);
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
//change09
//			q="select DISTINCT m.sequence,cm2.course_name,lom.level_name as level_of_module,tc.type_of_content,mm.module_name,m.module_id,m.cc_id, \n"
//					+" m.course_id,lom.sequence_name as lm , cm2.id as elecourseid\n"
//					+ "from edu_lms_student_course_enroll_sequence m\n"
//					+ "inner join edu_lms_ele_course_mstr cm on cm.id=m.course_id\n"
//					+ "inner join edu_lms_level_mstr lom on lom.id=m.level_of_module::int \n"
//					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
//					+ "inner join edu_lms_course_content cc on cc.course_name::int=cm2.id\n"
//					+ "inner join edu_lms_type_of_content_mstr tc on tc.id=cc.type_of_content ::int\n"
//					+ "inner join edu_lms_module_mstr mm on mm.id=m.module_id \n"
//					+ "inner join edu_lms_system_ele_course_sets_link_child pre on (pre.user_id=m.user_id::int and pre.course_id=m.course_id::int) \n"
//					+ "where pre.status='0' "+SearchValue+" group by 1,2,3,4,5,6,7,m.id,m.course_id,lm ,cm2.id  ORDER BY mm.module_name,lom.level_name";
			
			q="select DISTINCT m.sequence,cm2.course_name,lom.level_name as level_of_module,tc.type_of_content,mm.module_name,m.module_id,m.cc_id, \n"
					+" m.course_id,lom.sequence_name as lm , cm2.id as elecourseid"
					+ "---,ccc.upload_file,ccc.other_note,ccc.upload_ppt\n"
					+ "from edu_lms_student_course_enroll_sequence m\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=m.course_id\n"
					+ "inner join edu_lms_level_mstr lom on lom.id=m.level_of_module::int \n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
					+ "inner join edu_lms_course_content cc on cc.course_name::int=cm2.id\n"
					+ "inner join edu_lms_type_of_content_mstr tc on tc.id=cc.type_of_content ::int\n"
					+ "inner join edu_lms_module_mstr mm on mm.id=m.module_id \n"
					+ "inner join edu_lms_system_ele_course_sets_link_child pre on (pre.user_id=m.user_id::int and pre.course_id=m.course_id::int) \n"
					+"---inner join edu_lms_course_content_child ccc on (ccc.p_id=m.cc_id::int and ccc.module=mm.id::int) \n"
					+ "where  pre.status='0' "+SearchValue+" group by 1,2,3,4,5,6,7,m.id,m.course_id,lm ,cm2.id "
					+ "---,ccc.upload_file,ccc.other_note,ccc.upload_ppt \n"
					+ "  ORDER BY module_id,sequence,elecourseid";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,course_name,module_name,type_content,level_of_module,userid);
			
			System.out.println("stmt==========23/11===================>"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunction1=1;
			int countFunction2=1;
			int countFunction3=1;
			while (rs.next()) {
				
				ArrayList<String> alist = new ArrayList<String>();
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				
				String f = "";
				String action = "";
				String action1 = "";
				String action2 = "";
				String action3 = "";
//				String action4 = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";
				String f5 = "";
				String f6 = "";
//				String f7 = "";
				String video = "";
				String file = "";
				String note = "";
				String ppt = "";

//				onclick='return videotopicCall("+rs.getString("cc_id")+","+rs.getString("course_id")+","+rs.getString("sequence")+","+rs.getString("module_id")+","+rs.getString("lm")+");'

				
				video = "<a class='main-btn active-btn-outline btn-hover btn-sm topiccal' title='View Video' >"
						+ "<i class='bi bi-play-circle'>"
						+ "<input type='hidden' id='topicvid"+countFunction+"' value="+rs.getString("cc_id")+">"
						+ "<input type='hidden' id='topicvid_c"+countFunction+"' value="+rs.getString("course_id")+">"
						+ "<input type='hidden' id='topicvid_s"+countFunction+"' value="+rs.getString("sequence")+">"
						+ "<input type='hidden' id='topicvid_m"+countFunction+"' value="+rs.getString("module_id")+">"
						+ "<input type='hidden' id='topicvid_lm"+countFunction+"' value="+rs.getString("lm")+">"
						+ "</i></a>";
				

				f4 = "<a class='main-btn success-btn-outline btn-hover btn-sm filedown' title='Pdf Download'>"
								+ "<i class='lni lni-download'>"
								+ "<input type='hidden' id='file"+countFunction1+"' value="+rs.getInt("cc_id")+">"
								+ "<input type='hidden' id='file_c"+countFunction1+"' value='upload_file'></i></a>";

				
				//f5 = "<i  " + Downloadnote + " title='Download Note' ></i>";
				f5 = "<a class='main-btn dark-btn-outline btn-hover btn-sm othernote1' title='Download Notes'><i class='lni lni-book'>"
								+ "<input type='hidden' id='notedown"+countFunction2+"' value="+rs.getInt("cc_id")+">"
								+ "<input type='hidden' id='notedown_c"+countFunction2+"' value='other_note'></i></a>";

//				String Downloadppt = "<a href=\"#\" class='btn-main fa fa-file-text-o'  onclick=\"  if (confirm('Are You Sure You Want to Download PPT ?') ){downloadnote_file("
//						+ rs.getInt("cc_id") + ", 'upload_ppt' )}else{ return false;}\"";
				
				//f6 = "<i  " + Downloadppt + " title='Download PPT' ></i>";
				f6 = "<a class='main-btn secondary-btn-outline btn-hover btn-sm downppt' title='Download PPT'><i class='lni lni-gallery'>"
								+ "<input type='hidden' id='ppt1"+countFunction3+"' value="+rs.getInt("cc_id")+">"
								+ "<input type='hidden' id='ppt_c"+countFunction3+"' value='upload_ppt'>"
								+ "</i></a>";
				
//				f7 = "<a class='main-btn secondary-btn-outline btn-hover btn-sm' onclick=\"  if (confirm('Are You Sure You Want to Download PPT ?') ){downloadnote_file("
//						+ rs.getInt("cc_id") + ", 'upload_ppt' )}else{ return false;}\");'><i class='lni lni-gallery'></i></a>";

				
			
				action =video ;
				action1 =  f4;
				action2 =  f5;
				action3 =  f6;
				
				alist.add(rs.getString("cc_id")); //0
				alist.add(rs.getString("course_name"));//1
				alist.add(rs.getString("Module_name"));//2
				alist.add(rs.getString("type_of_content"));//3
//				alist.add(rs.getString("Level_Of_Course"));//4
				alist.add(rs.getString("Level_Of_Module"));//5
				alist.add(rs.getString("course_id"));//6
				alist.add(rs.getString("module_id"));//7
//				alist.add(rs.getString("credit"));//8
				alist.add(rs.getString("elecourseid"));//9
//				alist.add(rs.getString("upload_file"));//10
//				alist.add(rs.getString("other_note"));//11
//				alist.add(rs.getString("upload_ppt"));//12
				
				
				countFunction+=1;
				countFunction1+=1;
				countFunction2+=1;
				countFunction3+=1;
				
				columns.put("action", action);
				columns.put("action1", action1);
				columns.put("action2", action2);
				columns.put("action3", action3);
				
				
				
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
	
	public long DataTableviewcourse_DataTotalCount(String Search, String course_name,String module_name,String type_content,String level_of_module,String userid) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,course_name,module_name,type_content,level_of_module,userid);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q="select count(*) from (select  cm.course_name,lom.level_name as level_of_module,tc.type_of_content, \n"
					+ "mm.module_name,m.cc_id,m.course_id,m.level_of_module as lm \n"
					+ "from edu_lms_student_course_enroll_sequence m\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=m.course_id\n"
					+ "inner join edu_lms_level_mstr lom on lom.id=m.level_of_module::int \n"
					+ "inner join edu_lms_course_content cc on cc.course_name::int=m.course_id\n"
					+ "inner join edu_lms_type_of_content_mstr tc on tc.id=cc.type_of_content ::int\n"
					+ "inner join edu_lms_module_mstr mm on mm.id=m.module_id \n"
					+ "-- inner join edu_lms_course_content_child ccc on ccc.id=ccc.level_of_module\n"
					+ "inner join edu_lms_system_ele_course_sets_link_parent pre on (pre.user_id=m.user_id::int and pre.ele_course_id=m.course_id::int)\n"
					+ "where cc.app_status='1' "+SearchValue+" group by 1,2,3,4,5,6,m.id,m.course_id   )a  ";
				
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,course_name,module_name,type_content,level_of_module,userid);
			
			System.out.println("count================>"+stmt);

			
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
	public String GenerateQueryWhereClause_SQL(String Search, String course_name,String module_name,String type_content,String level_of_module,String userid) {
	
		
		String SearchValue = "";
		
		/// advance search
		if (!userid.trim().equals("")) {
			SearchValue += " and m.user_id::text = ? ";
		}
		if (!type_content.trim().equals("0")) {
			SearchValue += " and cc.type_of_content  = ? ";
		}
		if (!course_name.trim().equals("0")) {
			SearchValue += " and cm.id = ? ";
		}
		if (!module_name.trim().equals("0")) {
			SearchValue += " and m.module_id  = ? ";
		}
		if (!level_of_module.trim().equals("0")) {
			SearchValue += " and m.Level_Of_Module  = ? ";
		}
//		if (Search != null && Search!="") { // for Input Filter
//			SearchValue += " and   ( cm.course_name) like ? or (mm.Module_name) like ?"
//					+ "or (tc.type_of_content) like ? or (lom.level_name) like ?  ";
//		}

//		if (app_status.equals("0")) {
//			SearchValue += " and ir.app_status :: int = ? ";
//		}
//		if(app_status.equals("1")) {
//			SearchValue += " and ir.app_status :: int = ? ";
//		}
//		if(app_status.equals("2")) {
//			SearchValue += " and ir.app_status :: int = ? ";
//		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String course_name,String module_name,String type_content,String level_of_module ,String userid) {
		
		int flag = 0;
		try {
			if (!userid.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,userid);
			}
			if (!type_content.equals("0") && type_content != null) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(type_content));
			}
			if (!course_name.equals("0") && course_name != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name));
			}
			if (!module_name.equals("0") && module_name != null) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(module_name));
			}
			if (!level_of_module.equals("0") && level_of_module != null) {
				flag += 1;
				stmt.setString(flag, level_of_module);
			}
//			if (Search != null && Search!="") {
//
//				flag += 1;
//				stmt.setString(flag, "%" + Search + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search + "%");
//			}
		} catch (Exception e) {
			e.printStackTrace();
	  }
		return stmt;
	}
	
	public ArrayList<ArrayList<String>> getdataForSeqVal(String course_id,String lm,String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select status \n"
						+ "from edu_lms_student_course_enroll_sequence \n"
						+ "where course_id=?  and level_of_module=? and user_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				stmt.setString(2,lm);
				stmt.setInt(3,Integer.parseInt(userid));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("status")); //0
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
				}
			}
		}
		return list;
	}
	
	public ArrayList<ArrayList<String>> getcourselistFromtypeofcontent(String type_content,String user_id ) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
//			q="select DISTINCT ces.course_id,lcm.course_name from  edu_lms_course_content cc\n"
//					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::int = cc.course_name\n"
//					+ "inner join edu_lms_student_course_enroll_sequence ces on ces.course_id=cm.id\n"
//					+ "inner join edu_lms_course_mstr lcm on lcm.id=cm.course_name::int\n"
//					+ "WHERE cc.type_of_content =? and cc.app_status ='1' ";
			
			q="select DISTINCT ces.course_id,lcm.course_name from  edu_lms_course_content cc\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::int = cc.course_name\n"
					+ "inner join edu_lms_student_course_enroll_sequence ces on ces.course_id=cm.id\n"
					+ "inner join edu_lms_course_mstr lcm on lcm.id=cm.course_name::int\n"
					+ "inner join edu_lms_system_ele_course_sets_link_child slc on slc.course_id=cm.id\n"
					+ "WHERE cc.type_of_content = ? and slc.user_id= ? and cc.app_status ='1' ";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			
			stmt.setInt(1,Integer.parseInt(type_content));
			stmt.setInt(2,Integer.parseInt(user_id));
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("course_id"));// 0
				alist.add(rs.getString("course_name"));// 1
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
//	shivali 20/08
	public ArrayList<ArrayList<String>> getlevel_of_modulebyModule(String module_name,String user_id ) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//			q="\n"
//					+ "select qp.set_id,sm.setname  FROM edu_lms_system_ele_course_sets_link_parent qp\n"
//					+ "INNER JOIN edu_lms_set_mstr sm  ON sm.id=qp.set_id::INT \n"
//					+ "WHERE qp.ele_course_id=? and qp.user_id=? \n"
//					+ "";
//			
//			q="select distinct ce.course_id,cm.course_name from edu_lms_student_course_enroll_sequence ce\n"
//					+ "inner join edu_lms_ele_course_mstr cm on cm.id=ce.course_id\n"
//					+ "where user_id=? ";
			
//			q="select DISTINCT cc.course_name as course_id,lcm.course_name from  edu_lms_course_content cc\n"
//					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::int = cc.course_name\n"
//					+ "inner join edu_lms_course_mstr lcm on lcm.id=cm.course_name::int\n"
//					+ "WHERE cc.type_of_content =? and cc.app_status ='1'";
			
			q="select DISTINCT lm.id,sces.level_of_module,lm.level_name from edu_lms_student_course_enroll_sequence sces\n"
					+ "inner join edu_lms_level_mstr lm on sces.level_of_module::int=lm.id\n"
					+ "where sces.module_id=? order by level_name ";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			
			stmt.setInt(1,Integer.parseInt(module_name));
			System.err.println("stmt----------level---q-----"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("level_of_module"));// 0
				alist.add(rs.getString("level_name"));// 1
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
	
  public ArrayList<ArrayList<String>> getmodulelistFromtcourse(String course_name,String user_id ) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q="select DISTINCT cc.module_id,cm.module_name from edu_lms_student_course_enroll_sequence cc\n"
					+ "inner join edu_lms_module_mstr cm on cm.id=cc.module_id\n"
					+ "where cc.course_id=? order by module_name ";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			
			stmt.setInt(1,Integer.parseInt(course_name));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("module_id"));// 0
				alist.add(rs.getString("module_name"));// 1
			
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

//HET CHANGES
	public ArrayList<ArrayList<String>> getdataForSeqVal2(String course_id,String lm,String userid,String module_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select se.status,time \n"
						+ "from edu_lms_student_course_enroll_sequence  se\n"
						+ "inner join edu_lms_ele_course_mstr m on m.id=se.course_id\n"
						+ "where m.id=?  and sequence=? and user_id=? and module_id = ?\n"
						+ "";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				stmt.setInt(2,Integer.parseInt(lm));
				stmt.setInt(3,Integer.parseInt(userid));
				stmt.setInt(4,Integer.parseInt(module_id));

				ResultSet rs = stmt.executeQuery();    
				System.err.println("STMT----NOW---"+stmt);
				while (rs.next()) {				
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("status")); //0
				alist.add(rs.getString("time")); //1
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
				}
			}
		}
		return list;
	}
	
	
	public ArrayList<ArrayList<String>> GetExamPaper(String courseid,String module,String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";

			q="select count(*) from edu_lms_exam_paper where course_id =? and module_id =? and user_id =? ";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(courseid));
				stmt.setInt(2,Integer.parseInt(module));
				stmt.setInt(3,Integer.parseInt(userid));
			   
				ResultSet rs = stmt.executeQuery();      
				 System.err.println("-stmt---------GetExamPaper-----"+stmt);
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("count")); //0
			
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
				}
			}
		}
		return list;
	}
	
	public ArrayList<ArrayList<String>> getcourselistForViewCourse(String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";

			q="select  ecm.course_name as course_id, cm.course_name from edu_lms_system_ele_course_sets_link_child evcc\n"
					+ "Inner join edu_lms_ele_course_mstr ecm ON ecm.id = evcc.course_id\n"
					+ "Inner join edu_lms_course_mstr cm ON ecm.course_name::int = cm.id\n"
					+ "where user_id =? ";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(userid));
			   
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_id")); //0
				alist.add(rs.getString("course_name")); //1
			
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
				}
			}
		}
		return list;
	}

}
