package com.AyushEdu.dao.Time_Table;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS;
@Repository
public class AcademicDAOImp implements AcademicDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableacademicDataTotalCount(String Search, String professional, String academic_details, 
			String term, String exam_type, String exam_serial, String starting_date, String ending_date, int institute_id) {		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, professional, academic_details, 
				term, exam_type, exam_serial, starting_date, ending_date);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			q= "select count(*) from (select s.id,m.academic_details_name,TO_CHAR(s.starting_date , 'dd/MON/yyyy') as starting_date,\r\n"
					+ "TO_CHAR(s.ending_date , 'dd/MON/YYYY') as ending_date, p.professional,tm.term,et.exam_type,sm.exam_serial \r\n"
					+ "from edu_tt_academic_details s\r\n"
					+ "inner join edu_cc_tb_professional_mstr p on p.id= s.professional\r\n"
					+ "left join edu_cc_tb_i3_term_mstr tm on tm.id=s.term\r\n"
					+ "left join edu_cc_tb_exam_type_mstr et on et.id=s.exam_type\r\n"
					+ "left join edu_exam_tb_exam_serial_mstr sm on sm.id=s.exam_serial\r\n"
					+ "inner join edu_tt_academic_details_mstr m on m.refer_code= s.academic_details \r\n"
					+ "where s.institute_id = ? \n"
					+ SearchValue +") ab ";
			
//			q="select count(*) from (select s.id,m.academic_details_name,TO_CHAR(s.starting_date , 'dd/MON/yyyy') as starting_date,\r\n"
//					+ "TO_CHAR(s.ending_date , 'dd/MON/YYYY') as ending_date, p.professional,tm.term,et.exam_type,sm.exam_serial \r\n"
//					+ "from edu_tt_academic_details s\r\n"
//					+ "inner join edu_cc_tb_professional_mstr p on p.id= s.professional\r\n"
//					+ "left join edu_cc_tb_i3_term_mstr tm on tm.id=s.term\r\n"
//					+ "left join edu_cc_tb_exam_type_mstr et on et.id=s.exam_type\r\n"
//					+ "left join edu_exam_tb_exam_serial_mstr sm on sm.id=s.exam_serial\r\n"
//					+ "inner join edu_tt_academic_details_mstr m on m.refer_code= s.academic_details \r\n"
//					+ "where s.institute_id = ? \n"
//					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, professional, academic_details, term, exam_type, exam_serial, starting_date, ending_date, institute_id);
			
			System.err.println("DataTableacademicDataTotalCount--->" +q);
			
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
	
	
	public String GenerateQueryWhereClause_SQL(String Search, String professional, String academic_details, String term, String exam_type, String exam_serial, String starting_date, String ending_date) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += "and ( cast(p.professional as text) like ? or upper(academic_details_name) like ? "
					+ "or cast(tm.term as text) like ? or cast(et.exam_type as text) Like ?"
					+ " or cast(sm.exam_serial as text) like ? or TO_CHAR(s.starting_date , 'dd/MON/yyyy') like ? "
					+ "or TO_CHAR(s.ending_date , 'dd/MON/yyyy') like ?)";
		}
		
		if (professional != null && !professional.equals("0")) {
			SearchValue += "and s.professional = ? ";
		}
		
		if (academic_details != null && !academic_details.equals("0")) {
			SearchValue += "and s.academic_details = ? ";
		}
		
		if (term != null && !term.equals("0")) {
			SearchValue += "and s.term = ? ";
		}
		
		if (exam_type != null && !exam_type.equals("0")) {
			SearchValue += "and s.exam_type = ? ";
		}
		
		if (exam_serial != null && !exam_serial.equals("0")) {
			SearchValue += "and s.exam_serial = ? ";
		}
		
		///advance search
		if( starting_date != null && !starting_date.equals("") && !starting_date.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(starting_date , 'dd/mm/yyyy') = ? ";
	     }
		
		if( ending_date != null && !ending_date.equals("") && !ending_date.equals("DD/MM/YYYY")) {
			SearchValue += "and TO_CHAR(ending_date , 'dd/mm/yyyy') = ? ";
	     }
	
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String professional, 
			String academic_details, String term, String exam_type, String exam_serial, String starting_date, 
			String ending_date, int institute_id) {
		int flag = 0;
		try {
			flag += 1;
			stmt.setInt(flag, institute_id);
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
			
				
			}
			if (professional != null && !professional.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional));
			}
			
			if (academic_details != null && !academic_details.equals("0")) {
				flag += 1;
				stmt.setString(flag, academic_details);
			}
			
			if (term != null && !term.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(term));
			}
			
			if (exam_type != null && !exam_type.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(exam_type));
			}
			
			if (exam_serial != null && !exam_serial.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(exam_serial));
			}
			
			if( starting_date != null && !starting_date.equals("") && !starting_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, starting_date );
			}
			if( ending_date != null && !ending_date.equals("") && !ending_date.equals("DD/MM/YYYY")){
				flag += 1;
				stmt.setString(flag, ending_date );
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	
	public List<Map<String,Object>> DataTableacademicDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional, String academic_details , String term, String exam_type, String exam_serial, String starting_date, String ending_date, int institute_id) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, professional, academic_details, term, exam_type, 
				exam_serial, starting_date, ending_date);
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

			if(Search.equals("") && professional == null && academic_details == null && term == null && exam_type == null && exam_serial == null&& starting_date == null && ending_date == null) {
				q = "select s.id,m.academic_details_name,TO_CHAR(s.starting_date , 'dd/MON/yyyy') as starting_date,\r\n"
						+ "TO_CHAR(s.ending_date , 'dd/MON/YYYY') as ending_date, p.professional,tm.term,et.exam_type,sm.exam_serial \r\n"
						+ "from edu_tt_academic_details s\r\n"
						+ "inner join edu_cc_tb_professional_mstr p on p.id= s.professional\r\n"
						+ "left join edu_cc_tb_i3_term_mstr tm on tm.id=s.term\r\n"
						+ "left join edu_cc_tb_exam_type_mstr et on et.id=s.exam_type\r\n"
						+ "left join edu_exam_tb_exam_serial_mstr sm on sm.id=s.exam_serial\r\n"
						+ "inner join edu_tt_academic_details_mstr m on m.refer_code= s.academic_details \r\n"
//						+ "where s.institute_id = ?  "  + SearchValue + " ORDER BY academic_details,starting_date,ending_date " +orderColunm+ " " + orderType + " limit "
						+ "where s.institute_id = ?  "  + SearchValue + " ORDER BY  " +orderColunm+ " " + orderType + " limit "

						+ pageL + " OFFSET " + startPage;
			}else {
			    q = "select s.id,m.academic_details_name,TO_CHAR(s.starting_date , 'dd/MON/yyyy') as starting_date,\r\n"
			    		+ "TO_CHAR(s.ending_date , 'dd/MON/YYYY') as ending_date, p.professional,tm.term,et.exam_type,sm.exam_serial \r\n"
			    		+ "from edu_tt_academic_details s\r\n"
			    		+ "inner join edu_cc_tb_professional_mstr p on p.id= s.professional\r\n"
			    		+ "left join edu_cc_tb_i3_term_mstr tm on tm.id=s.term\r\n"
			    		+ "left join edu_cc_tb_exam_type_mstr et on et.id=s.exam_type\r\n"
			    		+ "left join edu_exam_tb_exam_serial_mstr sm on sm.id=s.exam_serial\r\n"
			    		+ "inner join edu_tt_academic_details_mstr m on m.refer_code= s.academic_details \r\n"
//			    		+ "where s.institute_id = ?  "  + SearchValue + " ORDER BY academic_details,starting_date, ending_date " + orderType + " limit "
			    		+ "where s.institute_id = ?  "  + SearchValue + " ORDER BY  " +orderColunm+ " " + orderType + " limit "
					    + pageL + " OFFSET " + startPage;
			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, professional, academic_details, term, exam_type, exam_serial, starting_date, ending_date, institute_id);
			
			System.err.println("DataTableacademicDataList--->"+stmt);
			
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
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
	
	public EDU_TT_ACADEMIC_DETAILS getacademicByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_TT_ACADEMIC_DETAILS updateid = (EDU_TT_ACADEMIC_DETAILS) session.get(EDU_TT_ACADEMIC_DETAILS.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	
	
	public List<Map<String, Object>> GetTermFromNoofpart(String role, String inst_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();

			q = "select id,no_of_part from edu_lms_institute_reg where id=?";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setInt(1, Integer.parseInt(inst_id));
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-=-=-=-=-=-="+stmt);
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
	
