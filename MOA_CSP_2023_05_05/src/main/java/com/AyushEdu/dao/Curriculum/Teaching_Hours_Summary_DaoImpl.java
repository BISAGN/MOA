package com.AyushEdu.dao.Curriculum;

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
public class Teaching_Hours_Summary_DaoImpl implements Teaching_Hours_Summary_Dao {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableTopicDataList(String course_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q=" select p.id,sm.system_name,dm.degree_name,prm.professional,cm.course_name,cm.course_code,\n"
					+ "toh.type_of_hours,tot.type_of_teaching,pm.paper,tc.no_of_hours\n"
					+ "from edu_cc_summary_parent p\n"
					+ "inner join edu_cc_summary_teach_hrs_child tc on tc.p_id=p.id\n"
					+ "inner join edu_cc_summary_examination_child ec on ec.p_id=p.id\n"
					+ "inner join edu_cc_tb_type_of_hours_mstr toh on toh.id=tc.hours_type\n"
					+ "inner join edu_cc_tb_type_of_teaching_mstr tot on tot.id=tc.lecture_type\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tc.paper\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id=p.system_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr prm on prm.id=p.professional_id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=p.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "where p.course_id = ?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("stmt----THS---------------------------->  "+stmt);
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
	public ArrayList<ArrayList<String>> getfetchsaveddata_list(String system_id, String degree_id,
			String professional_id, String course_id) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select id from edu_cc_summary_parent WHERE status=0 and  system_id = ? and degree_id = ? and professional_id = ? and course_id =?";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(system_id));
			stmt.setInt(2, Integer.parseInt(degree_id));
			stmt.setInt(3, Integer.parseInt(professional_id));
			stmt.setInt(4, Integer.parseInt(course_id));
			
			System.err.println("SUMMARY PARENT====="+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
//				list.add(rs.getString("term"));//1
//				list.add(rs.getString("lecture_hours"));//2
//				list.add(rs.getString("non_lecture_hours"));//3
				
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
	public ArrayList<ArrayList<String>> getfetchteach_hrssaveddata_list(String p_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select id,hours_type,lecture_type,paper,no_of_hours from edu_cc_summary_teach_hrs_child WHERE p_id = ?";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(p_id));
			
			System.err.println("SUMMARY THC====="+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("hours_type"));//1
				list.add(rs.getString("lecture_type"));//2
				list.add(rs.getString("paper"));//3
				list.add(rs.getString("no_of_hours"));//4
				
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
	public ArrayList<ArrayList<String>> getfetchfetchexam_sumsaveddata_list(String p_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select xy.id as cid,x.id,x.exam_paper,x.theory_comp_marks,xy.pract_cb,xy.practical_marks,xy.viva_cb,xy.viva_marks,xy.ele_cb,"
					+ " xy.elective_marks,xy.ia_cb,xy.ia_marks,xy.pa_cb,xy.pa_marks\n"
					+ " from edu_cc_summary_examination_child x\n"
					+ " inner join edu_cc_summary_exam_pract_comp_child xy on xy.p_id=x.p_id where x.p_id = ?";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(p_id));
			
			System.err.println("SUMMARY EX 1====="+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("exam_paper"));//1
				list.add(rs.getString("theory_comp_marks"));//2
				list.add(rs.getString("pract_cb"));//3
				list.add(rs.getString("practical_marks"));//4
				list.add(rs.getString("viva_cb"));//5
				list.add(rs.getString("viva_marks"));//6
				list.add(rs.getString("ele_cb"));//7
				list.add(rs.getString("elective_marks"));//8
				list.add(rs.getString("ia_cb"));//9
				list.add(rs.getString("ia_marks"));//10
				list.add(rs.getString("cid"));//11
				list.add(rs.getString("pa_cb"));//12
				list.add(rs.getString("pa_marks"));//13
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
	public ArrayList<ArrayList<String>> getViewTHSummary(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select p.id,sm.system_name,dm.degree_name,prm.professional,cm.course_name,cm.course_code,\n"
					+ "toh.type_of_hours,tot.type_of_teaching,pm.paper,tc.no_of_hours\n"
					+ "from edu_cc_summary_parent p\n"
					+ "inner join edu_cc_summary_teach_hrs_child tc on tc.p_id=p.id\n"
					+ "inner join edu_cc_summary_examination_child ec on ec.p_id=p.id\n"
					+ "inner join edu_cc_tb_type_of_hours_mstr toh on toh.id=tc.hours_type\n"
					+ "inner join edu_cc_tb_type_of_teaching_mstr tot on tot.id=tc.lecture_type\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tc.paper\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id=p.system_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr prm on prm.id=p.professional_id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=p.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "where p.course_id = ? \n"
					+ "group by 1,2,3,4,5,6,7,8,9,10";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(course_id));
			
//			System.err.println("getViewSummarySTMT====="+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("type_of_hours"));//0
				list.add(rs.getString("type_of_teaching"));//1
				list.add(rs.getString("paper"));//2
				list.add(rs.getString("no_of_hours"));//3
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
	

}
