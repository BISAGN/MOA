package com.AyushEdu.dao.Alumni;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_EVENT;
import com.AyushEdu.Models.LMS_Master.TB_NCH_CERTIFICATE_TYPE_MSTR;


@Repository
public class Alumni_CREATE_EVENT_DAOimpl implements Alumni_CREATE_EVENT_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableEventDataTotalCount1(String Search, String title, String description, String upload_img,String venue, String date_time, String batch,String userid) 
	{

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, title,description,upload_img,venue,date_time,batch,userid);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select id,title,description,upload_img,venue,date_time,batch  from edu_alum_alumni_event where id!=0  \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,title,description,upload_img,venue,date_time,batch,userid);
//			System.err.println("stmt============"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String title,String description,String upload_img,String venue,String date_time,String batch,String userid) 
	
	{
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(title) like ? or upper(description) like ? or upper(upload_img) like ? or upper(venue) like ? or upper(date_time) like ? or upper(batch) like ? )";
			//System.err.println("global search"+SearchValue);
		}
		
		///advance search
		if( title != null && !title.equals("")) {
			SearchValue += " and upper(title) like ? ";
	
	     }
		if( userid != null && !userid.equals("")) {
			SearchValue += " and created_by = ? ";
	
	     }
		if( description != null && !description.equals("")) {
			SearchValue += " and upper(description) like ? ";
	
	     }
		if( upload_img != null && !upload_img.equals("")) {
			SearchValue += " and upper(upload_img) like ? ";
	
	     }
		if( venue != null && !venue.equals("")) {
			SearchValue += " and upper(venue) like ? ";
	
	     }
		if( date_time != null && !date_time.equals("")) {
			SearchValue += " and upper(date_time) like ? ";
	
	     }
		if( batch != null && !batch.equals("")) {
			SearchValue += " and upper(batch) like ? ";
	
	     }

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String title,String description,String upload_img,String venue,String date_time,String batch,String userid) {
		int flag = 0;
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
				
			}
			if( userid != null && !userid.equals("")) {
				flag += 1;
				stmt.setString(flag,userid);
		
		     }
			if (title != null && !title.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+title.toUpperCase()+"%");
			}
			
			if (description != null && !description.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+description.toUpperCase()+"%");
			}
			if (upload_img != null && !upload_img.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+upload_img.toUpperCase()+"%");
			}
			if (venue != null && !venue.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+venue.toUpperCase()+"%");
			}
			if (date_time != null && !date_time.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+date_time.toUpperCase()+"%");
			}
			if (batch != null && !batch.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+batch.toUpperCase()+"%");
			}
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String, Object>> DataTableEventDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String title, String description, String upload_img, String venue,
			String date_time, String batch,String userid)
	
	{
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, title,description,upload_img,venue,date_time,batch,userid);
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

			if(Search.equals("") && title.equals("")) {
				q = "select id,title,description,venue,date_time,batch,upload_img from edu_alum_alumni_event where id IS NOT NULL "  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,title,description,venue,date_time,batch,upload_img from edu_alum_alumni_event where id IS NOT NULL "  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, title,description,upload_img,venue,date_time,batch,userid);
			System.err.println("stmt--->"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDtitle' value='ADD' title='Edit Data' >" 
				
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='aptitleAGE"+countFunction+"' value='"+rs.getString("title")+"'>"
								+"<input type='hidden' id='apdesAGE"+countFunction+"' value='"+rs.getString("description")+"'>"
								+"<input type='hidden' id='apimgAGE"+countFunction+"' value='"+rs.getString("upload_img")+"'>"
								+"<input type='hidden' id='apvenueAGE"+countFunction+"' value='"+rs.getString("venue")+"'>"
								+"<input type='hidden' id='apdtAGE"+countFunction+"' value='"+rs.getString("date_time")+"'>"
								+"<input type='hidden' id='apbetchAGE"+countFunction+"' value='"+rs.getString("batch")+"'></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value='"+rs.getString("id")+"'><i class='lni lni-trash-can'></i></a> </li>";

	
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
	
	public EDU_ALUM_ALUMNI_EVENT getEventByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_ALUM_ALUMNI_EVENT updateid = (EDU_ALUM_ALUMNI_EVENT) session.get(EDU_ALUM_ALUMNI_EVENT.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateEvent(EDU_ALUM_ALUMNI_EVENT obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_ALUM_ALUMNI_EVENT set title=:title, description=:description, upload_img=:upload_img, \n"
					+ " venue=:venue, date_time=:date_time, batch=:batch,modified_by=:modified_by,modified_date=:modified_date \n"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("title", obj.getTitle()) 
					.setParameter("description", obj.getDescription()) 
					.setParameter("upload_img", obj.getUpload_img()) 
					.setParameter("venue", obj.getVenue()) 
					.setParameter("date_time", obj.getDate_time())
					.setParameter("batch", (obj. getBatch()))
					.setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getFeedsData() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select e.id,l.login_name,to_char(post_date,'dd-Mon-yyyy') as post_date,e.description,e.title,ca.id as ppid\n"
					+ "from edu_alum_alumni_post e\n"
					+ "inner join logininformation l on l.userid=e.created_by::int\n"
					+ "inner join edu_alum_register_alumni_clg ca on ca.user_id=l.userid ";
			
			stmt = conn.prepareStatement(q);
			System.err.println("GET-FEEDS==="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("login_name"));// 1
				list.add(rs.getString("post_date"));// 2
				list.add(rs.getString("description"));// 3
				list.add(rs.getString("title"));// 4
				list.add(rs.getString("ppid"));// 5
				
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
	
//	upcomingevent
	
	@Override
	public List<Map<String, Object>> Getupcomingeventsdata(){
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

				q = "select id, title, description, \n"
						+ "to_char(to_date(substring(date_time,0,11),'yyyy-mm-dd'),'MON') as mon ,\n"
						+ "to_char(to_date(substring(date_time,0,11),'yyyy-mm-dd'),'DD') as day \n"
						+ "from edu_alum_alumni_event where id IS NOT NULL";
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt--->           "+stmt);
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
	public List<Map<String, Object>> Getbatchmatealumnidata(){
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

				q = "select id, title, description, \n"
						+ "to_char(to_date(substring(date_time,0,11),'yyyy-mm-dd'),'MON') as mon ,\n"
						+ "to_char(to_date(substring(date_time,0,11),'yyyy-mm-dd'),'DD') as day \n"
						+ "from edu_alum_alumni_event where id IS NOT NULL";
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt--->           "+stmt);
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
	
//EVENTDIV

@Override
public List<Map<String, Object>> Getevent_divdata(String event_id){
	
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {

		conn = dataSource.getConnection();

			q = "select id,upload_img,title,l.login_name,description,date_time,venue,batch,\n"
					+ "to_char(to_date(substring(date_time,0,11),'yyyy-mm-dd'),'MON DD , YYYY') as day,substring(date_time,12,15) as time\n"
					+ "from edu_alum_alumni_event e\n"
					+ "inner join logininformation l on l.userid=e.created_by::int\n"
					+ "where e.id=? ";


		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(event_id));
		System.err.println("stmt---Getevent_divdata--->"+stmt);
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
public String getEventImg(String id) {
	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		query = "select upload_img from edu_alum_alumni_event where id=? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		System.err.println("stmt---" + stmt);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			whr = rs.getString("upload_img");// 0
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
}

@Override
public String getFeedsImg(String id) {
	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		query = "select upload_img from edu_alum_alumni_post where id=? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		System.err.println("stmt---" + stmt);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			whr = rs.getString("upload_img");// 0
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
}

@Override
public String getAlumniPP(String id) {
	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		query = "select alum_photo from edu_alum_register_alumni_clg where id=? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		System.err.println("stmt---getAlumniPP---" + stmt);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			whr = rs.getString("alum_photo");// 0
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
}



//Alumnibatch

	@Override
	public List<Map<String, Object>> GetAlumnibatchdata(String userid){
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
	
			conn = dataSource.getConnection();
	
				q = "select e.id,e.alum_name,e.alum_batch \n"
						+ "from edu_alum_register_alumni_clg e\n"
						+ "where  e.alum_batch=(select alum_batch from edu_alum_register_alumni_clg where user_id=?) and e.user_id != ?";
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(userid));
			stmt.setInt(2,Integer.parseInt(userid));
			System.err.println("stmt--->           "+stmt);
			
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
	
	// array list Count  method
	public ArrayList<ArrayList<String>> getTotaleventinterestedCount(String event_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "SELECT count(*) FROM edu_alum_alumni_particapate where interested = 1 and event_id = ? ";


			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(event_id));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("count")); // 0
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


	public ArrayList<ArrayList<String>> getTotaleventparticipateCount(String event_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "SELECT count(*) FROM edu_alum_alumni_particapate where participate = 1 and event_id = ?";


			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(event_id));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("count")); // 0
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
	
	public ArrayList<ArrayList<String>> DataTableSearchEventDataList(int startPage, int pageLength, String Search,
			 String orderColunm, String orderType, String event_id, String name, String interested, String participating, String event_date) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}
		String SearchValue = GenerateQueryWhereCandiList(Search,  event_id,  name,  interested,  participating,  event_date);
																									
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "select distinct ROW_NUMBER() OVER(order by lo.login_name ASC) as ser, lo.login_name,ae.title, ap.interested, ap.participate,ap.event_id,\n"
					+ "TO_CHAR(ae.date_time::date,'DD-MM-YYYY') as date_time  \n"
					+ "from edu_alum_alumni_particapate ap\n"
					+ "inner join logininformation lo on lo.userid=ap.user_id\n"
					+ "inner join edu_alum_alumni_event ae on ae.id=ap.event_id  where ap.id!=0 \n"
				+ SearchValue + " ORDER BY lo.login_name " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,  event_id,  name,  interested,  participating, event_date);
			System.err.println("ddddddddddddstmet="+stmt);
			ResultSet rs = stmt.executeQuery();
			

			int i = 1;
			int countFunction = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

//				String action = "";
//				String f2 = "";
				alist.add(rs.getString("ser"));// 0
				alist.add(rs.getString("title"));// 1
				alist.add(rs.getString("login_name"));// 2
				alist.add(rs.getString("interested"));// 3
				alist.add(rs.getString("participate"));// 4
				alist.add(rs.getString("date_time"));// 5

//				String Download = "onclick=\" if (confirm('Are You Sure You Want to Download ?') ){download_file("
//						+ rs.getInt("id") + ")}else{ return false;}\"";
//				f2 = "<ul class='buttons-group'><li><a class='main-btn info-btn btn-hover btn-sm downloadOnclick'  title='Download'><i class='lni lni-download' >"
//						+ "<input type='hidden' id='downloadid" + countFunction + "' value=" + rs.getString("id")
//						+ "> </i></a></li></ul>";
//
////				action = f2;

//				alist.add(rs.getString("start_date"));// 0
//				alist.add(rs.getString("end_date"));// 1
//				alist.add(rs.getString("username"));// 2
//				alist.add(rs.getString("coursename"));// 3
//				alist.add(action);// 4
//				alist.add(rs.getString("id"));// 5
//				alist.add(rs.getString("sr_no"));// 6

				i++;
				countFunction += 1;
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

	public long DataTableSearchEventDataTotalCount1(String Search, String event_id, String name, String interested, String participating, String event_date) {
		String SearchValue = GenerateQueryWhereCandiList(Search,  event_id,  name,  interested,  participating,  event_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by lo.login_name ASC) as ser, lo.login_name,ae.title, ap.interested, ap.participate,ap.event_id,\n"
					+ "TO_CHAR(ae.date_time::date,'DD-MM-YYYY') as date_time  \n"
					+ "from edu_alum_alumni_particapate ap\n"
					+ "inner join logininformation lo on lo.userid=ap.user_id\n"
					+ "inner join edu_alum_alumni_event ae on ae.id=ap.event_id  where ap.id!=0  \n"
					+ SearchValue + ")ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search,  event_id,  name,  interested,  participating,  event_date);

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
					e.printStackTrace();
				}
			}
		}
		return (long) total;
	}
	@SuppressWarnings("null")
	public String GenerateQueryWhereCandiList(String Search, String event_id, String name, String interested, String participating, String event_date) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { 
			SearchValue += " and ( upper(lo.login_name) like ? or upper(ae.title) like ? or cast(ap.interested as text) like ? or cast(ap.participate as text) like ? or  TO_CHAR(ae.date_time::date,'DD-MM-YYYY') like ? ) ";
		}

		/// advance search 
		if (!event_id.equals("0") && event_id != null) {
			SearchValue += " and ap.event_id = ? ";
		}
		
		if (!name.equals("") && name != null) {
			SearchValue += " and upper(lo.login_name) like ? ";

		}
		if( interested != null && !interested.equals("")) {
			SearchValue += " and cast (ap.interested as text) like ? ";
		
	     }
		if( participating != null && !participating.equals("")) {
			SearchValue += " and cast (ap.participate as text) like ? ";
		
	     }
		if (!event_date.equals("DD/MM/YYYY") && event_date != null && !event_date.equals("")) {
			SearchValue += "  and TO_CHAR(ae.date_time::date,'DD/MM/YYYY')  like ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search,String event_id, String name, String interested, String participating, String event_date) {

		int flag = 0;
		try {
			if (Search != "" && Search != null) {
				
				
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
			


		
			if (!event_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(event_id));
			}

			if (name != null && !name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+name.toUpperCase() + "%");
			}
			if (interested != null && !interested.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+ Integer.parseInt(interested) + "%");
			}
			if (participating != null && !participating.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+ Integer.parseInt(participating) + "%");
			}
			if (!event_date.equals("DD/MM/YYYY") && event_date != null && !event_date.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + event_date.toUpperCase() + "%");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

}


