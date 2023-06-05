package com.AyushEdu.dao.Enhancement_Of_Research;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;
@Repository
public class Advance_Search_DAOImpl implements Advance_Search_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> Advance_Enhance_Research_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, medicine_system, category,
	              institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);
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

			q = "select ROW_NUMBER() OVER(order by sd.id ASC) as ser,sd.id,sm.system_name,sd.medicine_system,sd.category,sd.search_field,sd.title,sd.hyperlink,sd.desc_content, \n"
					+ "sd.status,rcm.category_name,sd.author_name,sd.journal_name,sd.abstract_content,sd.institute_name,rsm.institute_name as name \n"
					+ "from tb_enhance_research_advance_search_details sd\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=sd.medicine_system \n"
					+ "inner join tb_enhance_research_category_mstr rcm on rcm.id=sd.category \n"
					+ "inner join edu_lms_institute_reg rsm on rsm.id=sd.institute_name \n"
					+ "where sd.status=1 \n"
					+ SearchValue + " ORDER BY sd.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, medicine_system, category,
		              institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt======================"+stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
	
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
	
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm Edit_Advance_Reasearch' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EDIT_ADVANCE_Id" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteAdvanceSearch_Reasearch' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DELETE_AD_SEARCH" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	
				ul += f + " " + f1;
				ul += "</ul>";
	
				countFunction += 1;
				countFunctionDelete += 1;
	
				action = ul;
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

	@Override
	public long DataTotalAdvance_Enhance_ResearchCount(String search, String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, medicine_system, category,
	              institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select sd.id,sm.system_name,sd.medicine_system,sd.category,sd.search_field,sd.title,sd.hyperlink,sd.desc_content, \n"
					+ "sd.status,rcm.category_name,sd.author_name,sd.journal_name,sd.abstract_content,rsm.institute_name as name \n"
					+ "from tb_enhance_research_advance_search_details sd\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=sd.medicine_system \n"
					+ "inner join tb_enhance_research_category_mstr rcm on rcm.id=sd.category \n"
					+ "inner join edu_lms_institute_reg rsm on rsm.id=sd.institute_name \n"
					+ "where sd.status=1 \n "
					+ SearchValue + ")a";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, medicine_system, category,
		              institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt========count=============="+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(rcm.category_name) like ?  or upper(rsm.institute_name) like ? \n"
					+ "        or upper(sd.title) like ?  or upper(sd.author_name) like ? or upper(sd.journal_name) like ? \n "
					+ "        or upper(sd.hyperlink) like ? or upper(sd.abstract_content) like ? or upper(sd.desc_content) like ?) ";
		}
		if (!medicine_system.equals("0")) {
			SearchValue += " and sd.medicine_system = ? ";

		}
		if (!category.equals("0")) {
			SearchValue += " and sd.category = ? ";

		}
		if (!institute_name.equals("0")) {
			SearchValue += " and sd.institute_name = ? ";

		}
//		if (!search_field.equals("0")) {
//			SearchValue += " and sd.search_field = ? ";
//
//		}
	
		if (!author_name.trim().equals("")) {
			SearchValue += " and upper(sd.author_name) like ? ";
		}
		if (!journal_name.trim().equals("")) {
			SearchValue += " and upper(sd.journal_name) like ? ";
		}
		if (!title.trim().equals("")) {
			SearchValue += " and upper(sd.title) like ? ";
		}
		if (!hyperlink.trim().equals("")) {
			SearchValue += " and upper(sd.hyperlink) like ? ";
		}
		if (!abstract_content.trim().equals("")) {
			SearchValue += " and upper(sd.abstract_content) like ? ";
		}
		if (!desc_content.trim().equals("")) {
			SearchValue += " and upper(desc_content) like ? ";
		}
		if (!status.equals("0")) {
			SearchValue += " and sd.status = ? ";

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String medicine_system, String category,
            String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {

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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!medicine_system.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(medicine_system));
			}
			if (!category.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(category));
			}
			if (!institute_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_name));
			}
//			if (!search_field.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(search_field));
//			}
			if (!author_name.equals("") && author_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + author_name.toUpperCase() + "%");
			}
			if (!journal_name.equals("") && journal_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + journal_name.toUpperCase() + "%");
			}
			if (!title.equals("") && title != null) {
				flag += 1;
				stmt.setString(flag, "%" + title.toUpperCase() + "%");
			}
			if (!hyperlink.equals("") && hyperlink != null) {
				flag += 1;
				stmt.setString(flag, "%" + hyperlink.toUpperCase() + "%");
			}
			
			if (!abstract_content.equals("") && abstract_content != null) {
				flag += 1;
				stmt.setString(flag, "%" + abstract_content.toUpperCase() + "%");
			}
			if (!desc_content.equals("") && desc_content != null) {
				flag += 1;
				stmt.setString(flag, "%" + desc_content.toUpperCase() + "%");
			}
			if (!status.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	
	public TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS getAdvance_SearchByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS updateid = (TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS) session.get(TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
	
	public ArrayList<ArrayList<String>> getFilterAdvance_Enhance_Research_dataSearch(HttpSession session,String category,String institute_name,
			String medicine_system) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String SearchValue = GenerateQueryWhereClause_SQL_New(session,category,institute_name, medicine_system);
		String q = "";
		System.err.println("institute_name====================="+institute_name);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select asd.id,asd.title,asd.desc_content,asd.hyperlink,asd.category,asd.search_field,asd.institute_name,ir.institute_name as inst_name,\n"
					+ "asd.author_name,asd.journal_name,asd.abstract_content,asd.medicine_system\n"
					+ "from tb_enhance_research_advance_search_details asd \n"
					+ "inner join edu_lms_institute_reg ir on ir.id=asd.institute_name\n"
					+ "where asd.id!=0 "+SearchValue+" order by id";

			stmt = conn.prepareStatement(q);
//			stmt.setInt(1, Integer.parseInt(system_id));
//			stmt.setInt(2, Integer.parseInt(degree_id));
//			stmt.setInt(3, Integer.parseInt(p_id));
			
			System.out.println("stmtSearchAdvanced=========="+stmt );
			
			stmt = setQueryWhereClause_SQL_New(stmt,session, category, institute_name, medicine_system);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("title")); // 1
				alist.add(rs.getString("desc_content")); // 2
				alist.add(rs.getString("hyperlink")); // 3
				alist.add(rs.getString("inst_name")); // 4
				alist.add(rs.getString("author_name")); // 5
				alist.add(rs.getString("journal_name")); // 6
				alist.add(rs.getString("abstract_content")); // 7
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

@SuppressWarnings("null")
public String GenerateQueryWhereClause_SQL_New(HttpSession session,String category,String institute_name, String medicine_system) {

	String SearchValue = "";
//	if (Search != null && !Search.equals("")) { // for Input Filter
//		SearchValue += " and ( upper(sm.system_name) like ? or upper(rcm.category_name) like ?  or upper(rsm.institute_name) like ? \n"
//				+ "        or upper(sd.title) like ?  or upper(sd.author_name) like ? or upper(sd.journal_name) like ? \n "
//				+ "        or upper(sd.hyperlink) like ? or upper(sd.abstract_content) like ? or upper(sd.desc_content) like ?) ";
//	}
/// advance search

	if (!category.trim().equals("0")) {
		SearchValue += " and asd.category =? ";
	}
//	if (!search_field.trim().equals("0")) {
//		SearchValue += " and asd.search_field =? ";
//	}
	if (!institute_name.trim().equals("0")) {
		SearchValue += " and asd.institute_name = ? ";
	}
	if (!medicine_system.trim().equals("0")) {
		SearchValue += " and asd.medicine_system = ? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_New(PreparedStatement stmt, HttpSession session,String category,String institute_name, String medicine_system) {
	int flag = 0;
	try {

		if (!category.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(category));
		}
//		if (!search_field.trim().equals("0")) {
//			flag += 1;
//			stmt.setInt(flag, Integer.parseInt(search_field));
//		}
		if (!institute_name.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(institute_name));
		}
		if (!medicine_system.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(medicine_system));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	return stmt;
}

@Override
public ArrayList<ArrayList<String>> getFilterAdvance_Enhance_Research_GlobalSearch(String searchstring) {
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

//		q = "select asd.id,asd.title,asd.desc_content,asd.hyperlink,asd.category,asd.search_field,asd.institute_name,ir.institute_name as inst_name \n"
//				+ "from tb_enhance_research_advance_search_details asd \n"
//				+ "inner join edu_lms_institute_reg ir on ir.id=asd.institute_name \n"
//				+ "where lower(title) like ? or lower(desc_content) like ? order by id";
		
		q = "select asd.id,asd.title,asd.desc_content,asd.hyperlink,asd.category,asd.search_field,asd.institute_name,ir.institute_name as inst_name,\n"
				+ "asd.author_name,asd.journal_name,asd.abstract_content\n"
				+ "from tb_enhance_research_advance_search_details asd \n"
				+ "inner join edu_lms_institute_reg ir on ir.id=asd.institute_name \n"
				+ "where lower(asd.title) like ? or lower(asd.desc_content) like ?  or lower(asd.abstract_content) like ?  or lower(asd.author_name) like ?  order by id";

		stmt = conn.prepareStatement(q);
		stmt.setString(1,"%"+searchstring+"%");
		stmt.setString(2,"%"+searchstring+"%");
		stmt.setString(3,"%"+searchstring+"%");
		stmt.setString(4,"%"+searchstring+"%");
		
		System.out.println("stmtSearchGLOBAL=========="+stmt );
		
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id")); // 0
			alist.add(rs.getString("title")); // 1
			alist.add(rs.getString("desc_content")); // 2
			alist.add(rs.getString("hyperlink")); // 3
			alist.add(rs.getString("inst_name")); // 4
			alist.add(rs.getString("author_name")); // 5
			alist.add(rs.getString("journal_name")); // 6
			alist.add(rs.getString("abstract_content")); // 7
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



@Override
public ArrayList<ArrayList<String>> getInstituteBy_systemList(String medicine_system) {
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select id,institute_name from edu_lms_institute_reg where status='1' and app_status='1' and system_id=? \n";
				

		stmt = conn.prepareStatement(q);
		stmt.setInt(1,Integer.parseInt(medicine_system));
//		stmt.setString(2,"%"+searchstring+"%");
		
		System.out.println("instituteList=========="+stmt );
		
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id")); // 0
			alist.add(rs.getString("institute_name")); // 1
//			alist.add(rs.getString("desc_content")); // 2
//			alist.add(rs.getString("hyperlink")); // 3
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
