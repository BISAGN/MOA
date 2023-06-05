package com.AyushEdu.dao.Degree_recognition_NCISM;

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

import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_DEGREE_RECOGNITION_LIST_DRL;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA;
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;


@Repository
public class Degree_recognition_Within_indiaDaoImpl implements Degree_recognition_Within_inidaDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	public List<Map<String, Object>> getFilter_withinIndiaForm_A_UG_list(int startPage, int pageLength,String Search, String orderColunm,
			String orderType,int user_id) {//,int university_id
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

					conn = dataSource.getConnection();
		
					 q="select a.id,a.user_id,a.university_name,a.contact_name,a.email,a.medical_stream,a.contact_mobile "
					 		+ "from dg_rec_within_india_ug_ncism  a where  a.user_id=? "; //where a.id=?
		
					 
					PreparedStatement stmt = conn.prepareStatement(q);
					//stmt.setInt(1, university_id);
					stmt.setInt(1, user_id);
					System.err.println("stmt--withinIndiaForm---"+stmt);
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
					
					f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
							+ "<i class='lni lni-eye'>"
							+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
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

//---------------- Admitted List UG Count -----------------//
@Override
public long getFilter_withinIndiaForm_A_UGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_within_india_ug_ncism  a where a.user_id=? ";//a.university_id=?
		
		PreparedStatement stmt = conn.prepareStatement(q);
		 //stmt.setInt(1, university_id);
		 stmt.setInt(1, user_id);
		    System.err.println("stmt-count---"+stmt);
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
public List<Map<String, Object>> getviewdatatoByid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_within_india_ug_ncism where id=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("--stmt---"+stmt);

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


public List<Map<String, Object>> getFilter_withinIndiaForm_B_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
				 q="select a.id,a.user_id,a.aff_university_name,a.cont_name,a.reg_email,a.medical_system,a.cont_mobile_no "
				 		+ "from dg_rec_within_india_pg_ncism  a where  a.user_id=? "; //where a.id=?
	
				 
				PreparedStatement stmt = conn.prepareStatement(q);
				//stmt.setInt(1, university_id);
				stmt.setInt(1, user_id);
				System.err.println("stmt--withinIndiaForm---"+stmt);
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
				
				f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
						+ "<i class='lni lni-eye'>"
						+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
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

//---------------- Admitted List PG Count -----------------//
@Override
public long getFilter_withinIndiaForm_B_PGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_within_india_pg_ncism  a where a.user_id=? ";//a.university_id=?
		
		PreparedStatement stmt = conn.prepareStatement(q);
		 //stmt.setInt(1, university_id);
		 stmt.setInt(1, user_id);
		    System.err.println("stmt-count---"+stmt);
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
public List<Map<String, Object>> getviewdatatoBypgid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_within_india_pg_ncism where id=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("--stmt---"+stmt);

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

public List<Map<String, Object>> getFilter_withinIndiaForm_C_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
				 q="select a.id,a.user_id,a.pg_sub,a.nom_of_degree,a.abbr_of_degree "
				 		+ "from dg_rec_medical_qual_pg_35b_courses  a where  a.user_id=? "; //where a.id=?
	
				 
				PreparedStatement stmt = conn.prepareStatement(q);
				//stmt.setInt(1, university_id);
				stmt.setInt(1, user_id);
				System.err.println("stmt--withinIndiaForm---"+stmt);
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
				
				f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
						+ "<i class='lni lni-eye'>"
						+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
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

//---------------- Admitted List PG Count -----------------//
@Override
public long getFilter_withinIndiaForm_C_PGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_medical_qual_pg_35b_courses  a where a.user_id=? ";//a.university_id=?
		
		PreparedStatement stmt = conn.prepareStatement(q);
		 //stmt.setInt(1, university_id);
		 stmt.setInt(1, user_id);
		    System.err.println("stmt-count---"+stmt);
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
public List<Map<String, Object>> getviewdatatoBypgmedicalid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_medical_qual_pg_35b_courses where id=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("--stmt---"+stmt);

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

public List<Map<String, Object>> getFilter_withinIndiaForm_D_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
				 q="select a.id,a.user_id,a.pg_dip_subject,a.nomenclature_dg_dip,a.abbreviation_dg_dip "
				 		+ "from dg_rec_medical_qua_pg_diploma_35b_courses  a where  a.user_id=? "; //where a.id=?
	
				 
				PreparedStatement stmt = conn.prepareStatement(q);
				//stmt.setInt(1, university_id);
				stmt.setInt(1, user_id);
				System.err.println("stmt--withinIndiaForm---"+stmt);
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
				
				f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDMigratedTo' data-bs-toggle='modal' data-bs-target='#studentmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
						+ "<i class='lni lni-eye'>"
						+"<input type='hidden' id='apIdMig"+countFunction+"' value="+rs.getString("id")+">"
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

//---------------- Admitted List PG Count -----------------//
@Override
public long getFilter_withinIndiaForm_D_PGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_medical_qua_pg_diploma_35b_courses  a where a.user_id=? ";//a.university_id=?
		
		PreparedStatement stmt = conn.prepareStatement(q);
		 //stmt.setInt(1, university_id);
		 stmt.setInt(1, user_id);
		    System.err.println("stmt-count---"+stmt);
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
public List<Map<String, Object>> getviewdatatoBypgdiplomaid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_medical_qua_pg_diploma_35b_courses where id=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("--stmt---"+stmt);

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

//public ArrayList<ArrayList<String>> getWithinindiaug(int userid) {
//	 
//	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//    Connection conn = null;
//    try{          
//    	conn = dataSource.getConnection();
//        String sq1="select * ,to_char(commencement_dt,'dd/MM/yyyy') as commencement_dt from dg_rec_within_india_ug_ncism where institute_status=0 and  user_id = ?";                         
//        
//        PreparedStatement stmt = conn.prepareStatement(sq1);
//        stmt.setInt(1, userid);
//       
//        ResultSet rs = stmt.executeQuery();  
//        
//        
//        String str1="";
//        
//        while(rs.next()){
//        	
//        	ArrayList<String> list = new ArrayList<String>();
//        	
//			list.add(rs.getString("university_name"));// 0
//			list.add(rs.getString("university_address"));// 1
//			list.add(rs.getString("email"));// 2
//			list.add(rs.getString("contact_name"));//3
//			list.add(rs.getString("contact_designation"));// 4
//			list.add(rs.getString("contact_email"));//5
//			list.add(rs.getString("contact_mobile"));//6
//			list.add(rs.getString("medical_stream"));//7
//			list.add(rs.getString("institute_name"));//8
//			list.add(rs.getString("nomenclature_degree"));//9
//			list.add(rs.getString("abbreviation_degree"));//10
//			list.add(rs.getString("first_batch_admission"));//11
//			list.add(rs.getString("awarded_degree"));//12
//			list.add(rs.getString("final_year_examination"));//13
//			list.add(rs.getString("completion_of_internship"));//14
//			list.add(rs.getString("provisional_certificate"));//15
//			list.add(rs.getString("examinaton_year"));//16
//			list.add(rs.getString("commencement_dt"));//17
//			list.add(rs.getString("expected_month_year"));//18
//			list.add(rs.getString("continued_degree"));//19
//			list.add(rs.getString("last_batch_year"));//20
//			
//			
//			alist.add(list);     
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//   }catch(SQLException e){
//	   e.printStackTrace();
//   }       
//    
//   return alist;
//}
//
//
//public ArrayList<ArrayList<String>> getWithinindiapg(int userid) {
//	 
//	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//    Connection conn = null;
//    try{          
//    	conn = dataSource.getConnection();
//        String sq1="select * from dg_rec_within_india_pg_ncism where institute_status=0 and  user_id = ?";                         
//        
//        PreparedStatement stmt = conn.prepareStatement(sq1);
//        stmt.setInt(1, userid);
//       
//        ResultSet rs = stmt.executeQuery();  
//        
//        
//        String str1="";
//        
//        while(rs.next()){
//        	
//        	ArrayList<String> list = new ArrayList<String>();
//        	
//			list.add(rs.getString("aff_university_name"));// 0
//			list.add(rs.getString("aff_university_address"));// 1
//			list.add(rs.getString("reg_email"));// 2
//			list.add(rs.getString("cont_name"));//3
//			list.add(rs.getString("cont_designation"));// 4
//			list.add(rs.getString("cont_mobile_no"));//5
//			list.add(rs.getString("cont_email_id"));//6
//			list.add(rs.getString("medical_system"));//7
//			list.add(rs.getString("insti_name"));//8
//			
//			
//			alist.add(list);     
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//   }catch(SQLException e){
//	   e.printStackTrace();
//   }       
//    
//      return alist;
//   }
//
//public ArrayList<ArrayList<String>> getWithinindiampg(int userid) {
//	 
//	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//    Connection conn = null;
//    try{          
//    	conn = dataSource.getConnection();
//        String sq1="select * from dg_rec_medical_qual_pg_35b_courses where institute_status=0 and  user_id = ?";                         
//        
//        PreparedStatement stmt = conn.prepareStatement(sq1);
//        stmt.setInt(1, userid);
//       
//        ResultSet rs = stmt.executeQuery();  
//        
//        
//        String str1="";
//        
//        while(rs.next()){
//        	
//        	ArrayList<String> list = new ArrayList<String>();
//        	
//			list.add(rs.getString("pg_sub"));// 0
//			list.add(rs.getString("nom_of_degree"));// 1
//			list.add(rs.getString("abbr_of_degree"));// 2
//			list.add(rs.getString("year_of_addmission"));//3
//			list.add(rs.getString("month_of_passing"));// 4
//			list.add(rs.getString("issue_of_provisional"));//5
//			list.add(rs.getString("date_of_latter"));//6
//			list.add(rs.getString("ref_of_latter"));//7
//			
//			
//			alist.add(list);     
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//   }catch(SQLException e){
//	   e.printStackTrace();
//   }       
//    
//      return alist;
//   }
//
//public ArrayList<ArrayList<String>> getWithinindiampgd(int userid) {
//	 
//	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//    Connection conn = null;
//    try{          
//    	conn = dataSource.getConnection();
//        String sq1="select * from dg_rec_medical_qua_pg_diploma_35b_courses where institute_status=0 and  user_id = ?";                         
//        
//        PreparedStatement stmt = conn.prepareStatement(sq1);
//        stmt.setInt(1, userid);
//       
//        ResultSet rs = stmt.executeQuery();  
//        
//        
//        String str1="";
//        
//        while(rs.next()){
//        	
//        	ArrayList<String> list = new ArrayList<String>();
//        	
//			list.add(rs.getString("pg_dip_subject"));// 0
//			list.add(rs.getString("nomenclature_dg_dip"));// 1
//			list.add(rs.getString("abbreviation_dg_dip"));// 2
//			list.add(rs.getString("year_admi_first_batch_dip"));//3
//			list.add(rs.getString("passing_year_of_finalyear_dip"));// 4
//			list.add(rs.getString("year_provisional_certi_dip"));//5
//			list.add(rs.getString("permission_lattter_dt"));//6
//			list.add(rs.getString("ref_permission_latter"));//7
//			
//			
//			alist.add(list);     
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//   }catch(SQLException e){
//	   e.printStackTrace();
//   }       
//    
//      return alist;
//   }

}

