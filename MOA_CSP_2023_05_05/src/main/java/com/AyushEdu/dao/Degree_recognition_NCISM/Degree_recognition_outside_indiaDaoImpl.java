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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Degree_recognition_outside_indiaDaoImpl implements Degree_recognition_outside_indiaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Map<String, Object>> getFilter_outsideIndiaForm_A_UG_list(int startPage, int pageLength,String Search, String orderColunm,
			String orderType,int user_id) {//,int university_id
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

					conn = dataSource.getConnection();
		
					 q="select a.id,a.user_id,a.uni_name,a.reg_email_id,a.con_per_name,a.con_per_mob_no,a.madical_stream "
					 		+ "from dg_rec_outside_india_ug_ncism  a where  a.user_id=? "; //where a.id=?
		
					 
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
public long getFilter_outsideIndiaForm_A_UGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_outside_india_ug_ncism  a where a.user_id=? ";//a.university_id=?
		
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
public List<Map<String, Object>> getviewdatatoByoutsideindiaugid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_outside_india_ug_ncism where id=? ";
		
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

public List<Map<String, Object>> getFilter_outsideIndiaForm_B_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
				 q="select a.id,a.user_id,a.aff_uni_name,a.registered_email_id,a.con_person_name,a.con_person_mob_no,a.medical_stream "
				 		+ "from dg_rec_outside_india_pg_ncism  a where  a.user_id=? "; //where a.id=?
	
				 
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
public long getFilter_outsideIndiaForm_B_PGListCount(String search, int user_id) {//int university_id,

System.err.println("---getFilter_Admitted_StudentListCount-");
int total = 0;
Connection conn = null;
String q = "";
try {
	conn = dataSource.getConnection();			
	
	q="select count(*) from dg_rec_outside_india_pg_ncism  a where a.user_id=? ";//a.university_id=?
	
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
public List<Map<String, Object>> getviewdatatoByoutsideindiapgid(String id) {
List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
Connection conn = null;
String q = "";
System.err.println("--C-in daoimpl-dddd---");
try {
	
	conn = dataSource.getConnection();
	
//	q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
			
	q="select * from dg_rec_outside_india_pg_ncism where id=? ";
	
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

public List<Map<String, Object>> getFilter_outsideIndiaForm_C_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
//				 q="select a.id,a.user_id,a.pg_subject,a.nom_degree,a.abb_degree "
//				 		+ "from dg_rec_medical_qual_pg_36b_courses  a where  a.user_id=? "; //where a.id=?
				 
				 q="select a.id,a.user_id,a.pg_subject,a.nom_degree,a.abb_degree "
					 		+ "from dg_rec_medical_qual_pg_36b_courses  a where  a.user_id=? "; //where a.id=?
	
				 
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
public long getFilter_outsideIndiaForm_C_PGListCount(String search, int user_id) {//int university_id,

System.err.println("---getFilter_Admitted_StudentListCount-");
int total = 0;
Connection conn = null;
String q = "";
try {
	conn = dataSource.getConnection();			
	
	q="select count(*) from dg_rec_medical_qual_pg_36b_courses  a where a.user_id=? ";//a.university_id=?
	
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
public List<Map<String, Object>> getviewdatatoByoutsideindiapgmedicalid(String id) {
List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
Connection conn = null;
String q = "";
System.err.println("--C-in daoimpl-dddd---");
try {
	
	conn = dataSource.getConnection();
	
//	q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
			
	q="select * from dg_rec_medical_qual_pg_36b_courses where id=? ";
	
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

public List<Map<String, Object>> getFilter_outsideIndiaForm_D_PG_list(int startPage, int pageLength,String Search, String orderColunm,
		String orderType,int user_id) {//,int university_id
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

				conn = dataSource.getConnection();
	
				 q="select a.id,a.user_id,a.pg_dip_subject,a.nomenclature_dg_dip,a.abbreviation_dg_dip "
				 		+ "from dg_rec_medical_qua_pg_diploma_36b_courses  a where  a.user_id=? "; //where a.id=?
	
				 
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
public long getFilter_outsideIndiaForm_D_PGListCount(String search, int user_id) {//int university_id,
	
	System.err.println("---getFilter_Admitted_StudentListCount-");
	int total = 0;
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();			
		
		q="select count(*) from dg_rec_medical_qua_pg_diploma_36b_courses  a where a.user_id=? ";//a.university_id=?
		
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
public List<Map<String, Object>> getviewdatatoByoutsideindiapgdiplomaid(String id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	System.err.println("--C-in daoimpl-dddd---");
	try {
		
		conn = dataSource.getConnection();
		
//		q="select *,to_char(migrated_dt_to,'dd/MM/yyyy') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
				
		q="select * from dg_rec_medical_qua_pg_diploma_36b_courses where id=? ";
		
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

}
