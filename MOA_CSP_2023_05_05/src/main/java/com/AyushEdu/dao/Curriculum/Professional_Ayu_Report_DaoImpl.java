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
public class Professional_Ayu_Report_DaoImpl implements Professional_Ayu_Report_Dao {
	
	@Autowired
	private DataSource dataSource;

	//fetch data for pdf table 0==========================================
	  @Override
		public List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataList_pdf(String course_id) {
	
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				q="select DISTINCT sm.id,sm.system_name,dm.degree_name,prm.professional,cm.course_name,cm.course_code,\n"
						+ "toh.type_of_hours,tot.type_of_teaching,pm.paper,tc.no_of_hours,sepc.practical_marks,ec.theory_comp_marks\n"
						+ "from edu_cc_summary_parent p\n"
						+ "inner join edu_cc_summary_teach_hrs_child tc on tc.p_id=p.id\n"
						+ "inner join edu_cc_summary_examination_child ec on ec.p_id=p.id\n"
						+ "inner join edu_cc_tb_type_of_hours_mstr toh on toh.id=tc.hours_type\n"
						+ "inner join edu_cc_tb_type_of_teaching_mstr tot on tot.id=tc.lecture_type\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tc.paper\n"
						+ "inner join edu_lms_system_mstr sm on  sm.id=p.system_id\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr prm on prm.id=p.professional_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id::int\n"
						+ "--inner join edu_cc_summary_examination_child sec on sec.p_id=p.id\n"
						+ "inner join edu_cc_summary_exam_pract_comp_child sepc on sepc.p_id=p.id\n"
						+ "where p.course_id = ? and tc.status=0";
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("qqqqqqqqqqqqqqqqqqpppppppppppppppppp"+stmt);
				System.err.println("\n *****-----1-----***** \n"+stmt + "\n *****-----1-----***** \n");
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
	  
	  //table 0(2)===================================
	  
	  @Override
		public List<Map<String, Object>> examination_list(String course_id) {
//			System.err.println("dao____________course_id"+course_id);
	
		  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				q="\n"
						+ "select distinct pm.paper,sec.theory_comp_marks,sepcc.practical_marks,sepcc.viva_marks,sepcc.ia_marks,sepcc.elective_marks\n"
						+ "from edu_cc_summary_parent sp \n"
						+ "inner join edu_cc_summary_examination_child sec on sec.p_id=sp.id\n"
						+ "inner join edu_cc_summary_exam_pract_comp_child sepcc on sepcc.p_id=sp.id\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=sec.exam_paper\n"
						+ "where sp.course_id= ?";
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----2-----***** \n"+stmt + "\n *****-----2-----***** \n");
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
	  
	  // end of table 0(2)==========================
	  
	//end of fetch data for pdf table 0==========================================
	  
	  //random table=============================================
	  
	  public ArrayList<ArrayList<String>> non_lec_activities(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				sql = "select c.practical,c.activity_practical_description,t.term from edu_cc_tb_list_of_practical_parent p\n"
						+ "inner join edu_cc_tb_list_of_practical_child c on c.p_id = p.id\n"
						+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id where p.course_id=?";
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----3-----***** \n"+stmt + "\n *****-----3-----***** \n");
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("practical"));// 0
					list.add(rs.getString("activity_practical_description"));// 1
					list.add(rs.getString("term"));// 2
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
	  
	  //end of random table================================
		
	//Table 1- Course learning outcomes and matched PO========================================
	  
		public ArrayList<ArrayList<String>> table1_co_po_link(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				sql = "select com.co_code,com.course_outcome as co,string_agg(pom.code,',') as po\n"
						+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent p\n"
						+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child c on c.p_id=p.id\n"
						+ "inner join edu_cc_tb_add_course_outcome_mstr com on com.id=p.course_outcome_id\n"
						+ "inner join edu_cc_tb_program_outcome_mstr pom on pom.id=c.programoutcome_id\n"
						+ "where p.course_id=? and p.status=0 \n"
						+ "group by 1,2,p.id order by p.id ";
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----4-----***** \n"+stmt + "\n *****-----4-----***** \n");
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("co_code"));// 0
					list.add(rs.getString("co"));// 1
					list.add(rs.getString("po"));// 2
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
		
		//end of Table 1- Course learning outcomes and matched PO========================================
		
		//Table 2: Contents of Course AyUG-RS=======================================
	  
//	  public ArrayList<ArrayList<String>> table2_Content_Course_AyUGRS(String course_id) {
//			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//			Connection conn = null;
//			try {
//				conn = dataSource.getConnection();
//				String sql = "";
//				PreparedStatement stmt = null;
//				sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,tom.topic,tm.term,mm.marks,lecture_hours as lh,non_lecture_hours as nlh,\n"
//						+ "string_agg(sut.sub_topic,'/') as sub_topic\n"
//						+ "from edu_cc_tb_list_of_topics_parent p\n"
//						+ "inner join edu_cc_tb_list_of_topics_child c on c.p_id=p.id\n"
//						+ "inner join edu_lms_term_mstr tm on tm.id=c.term_id\n"
//						+ "inner join edu_cc_tb_topics_mstr tom on tom.id=c.topic_id\n"
//						+ "inner join edu_cc_tb_define_topic_wise_marks_child mmc on mmc.topic_id=c.topic_id\n"
//						+ "inner join edu_cc_tb_define_topic_wise_marks_parent mm on mm.id=mmc.p_id\n"
//						+ "inner join edu_cc_tb_sub_topics_mstr sut on sut.topic_id=c.topic_id\n"
//						+ "where p.status=0 and p.course_id= ?\n"
//						+ "group by p.id,2,3,4,5,6";
//				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("stmt----table2_Course_AyUGRS-------" + stmt);
//				ResultSet rs = stmt.executeQuery();
//				int i = 0;
//				while (rs.next()) {
//					i++;
//					ArrayList<String> list = new ArrayList<String>();
//					list.add(rs.getString("sr_no"));//0
//					list.add(rs.getString("topic"));// 1
//					list.add(rs.getString("sub_topic"));// 2
//					list.add(rs.getString("term"));// 3
//					list.add(rs.getString("marks"));// 4
//					list.add(rs.getString("lh"));// 5
//					list.add(rs.getString("nlh"));// 6
//					alist.add(list);
//					System.err.println("table2_Course_AyUGRS list========"+list);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//					}
//				}
//			}
//			return alist;
//		}
		
		
		
		
		
		public ArrayList<ArrayList<String>> t2Content_Course_AyUGRS_list(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
//			String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
			try {
				System.err.println("----getView_T2_data----"+course_id);
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select ROW_NUMBER() OVER(order by c.id ) as sr_no,tom.topic,tm.term,mm.marks,lecture_hours as lh,non_lecture_hours as nlh,\n"
						+ "string_agg(sut.sub_topic,'/') as sub_topic,pm.paper\n"
						+ "from edu_cc_tb_list_of_topics_parent p\n"
						+ "inner join edu_cc_tb_list_of_topics_child c on c.p_id=p.id\n"
						+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=c.term_id\n"
						+ "inner join edu_cc_tb_topics_mstr tom on tom.id=c.topic_id\n"
						+ "left join edu_cc_tb_define_topic_wise_marks_child mmc on mmc.topic_id=c.topic_id\n"
						+ "left join edu_cc_tb_define_topic_wise_marks_parent mm on mm.id=mmc.p_id\n"
						+ "inner join edu_cc_tb_sub_topics_mstr sut on sut.topic_id=c.topic_id\n"
						+"inner join edu_cc_tb_paper_mstr pm ON pm.id = p.paper_id \n"
						+ "where p.status=0 and p.course_id= ? \n"
						+ "group by p.id,2,3,4,5,6,8,c.id\n"
						+ "order by c.id ";
				
//				SELECT topic,string_agg(sub_topic,'/') as st from edu_cc_tb_sub_topics_mstr  stm
//				inner join edu_cc_tb_topics_mstr tm on tm.id = stm.topic_id
//				group by 1
//				where stm.status=0 and tm.course_id=50

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----5-----***** \n"+stmt );
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("sr_no"));//0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("sub_topic"));// 2
					list.add(rs.getString("term"));// 3
					list.add(rs.getString("marks"));// 4
					list.add(rs.getString("lh"));// 5
					list.add(rs.getString("nlh"));// 6
					list.add(rs.getString("paper"));// 7
					

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
		
 
	//end of Table 2: Contents of Course AyUG-RS=======================================
	  
	  	//Table 3: Learning objectives (Theory) of Course AyUG-RS============================
		
		public ArrayList<ArrayList<String>> table3_Learning_Objectives_Course_AyUGRS(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				
				sql = "select co.co_code,tm.topic,c.b3_learning_obj as b3,c3.domain, d3.scope,e3.rep_und,f3.method,g3.assessment_method,h3.assessment_type,\n"
						+ "i3.term,case when c.j3_integration is null then '' else c.j3_integration end as j3,pm.paper\n"
						+ "from edu_cc_tb_t3_learning_object_parent p\n"
						+ "inner join edu_cc_tb_t3_learning_object_child c on c.p_id=p.id\n"
						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=p.topic_id\n"
						+ "inner join edu_cc_tb_c3_domain_mstr c3 on c3.id=c.c3_domain_sub\n"
						+ "inner join edu_cc_tb_d3_scope_understanding_mstr d3 on d3.id=c.d3_desirable_know\n"
						+ "inner join edu_cc_tb_e3_representation_understanding_mstr e3 on e3.id=c.e3_level_show_know\n"
						+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr f3 on f3.id=c.f3_t_l_method\n"
						+ "inner join edu_cc_tb_g3_assessment_method_mstr g3 on g3.id=c.g3_assessment\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr h3 on h3.id=c.h3_formative_summative\n"
						+ "inner join edu_cc_tb_i3_term_mstr i3 on i3.id=c.i3_term\n"
						+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=c.a3_couse_outcome\n"
						+ "inner join edu_cc_tb_list_of_topics_child ltc on ltc.topic_id=tm.id\n"
						+ "inner join edu_cc_tb_list_of_topics_parent ltp on ltp.id=ltc.p_id\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=ltp.paper_id \n"
						+ "where p.course_id=? and p.status=0  order by paper,topic,co_code  \n";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----6-----***** \n"+stmt + "\n *****-----6-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("paper"));//0
					list.add(rs.getString("topic"));//1
					list.add(rs.getString("co_code"));//2
					list.add(rs.getString("b3"));//3
					list.add(rs.getString("domain"));//4
					list.add(rs.getString("scope"));//5
					list.add(rs.getString("rep_und"));//6
					list.add(rs.getString("method"));//7
					list.add(rs.getString("assessment_method"));//8
					list.add(rs.getString("assessment_type"));//9
					list.add(rs.getString("term"));//10
					list.add(rs.getString("j3"));//11
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
		
		//end of Table 3: Learning objectives (Theory) of Course AyUG-RS===============================
		
		//Practical- Number of Papers and Marks Distribution============================
		  
		  
		  public ArrayList<ArrayList<String>> TableList_of_practicalDataTotalCount(String course_id) {
				ArrayList<ArrayList<String>> plist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;

//					sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, p.practical,t.term\n"
//							+ "from edu_cc_tb_list_of_practical_child p \n"
//							+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
//							+ "where p.course_id=?";
					
					
					sql="select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,c.practical,t.term,c.activity_practical_description \n"
							+ "from edu_cc_tb_list_of_practical_parent p\n"
							+ "inner join edu_cc_tb_list_of_practical_child c on c.p_id = p.id\n"
							+ "left join edu_cc_tb_i3_term_mstr t on t.id = c.term_id\n"
							+ " where p.course_id=? and c.status=0 ";
					
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----7-----***** \n"+stmt + "\n *****-----7-----***** \n");
					ResultSet rs = stmt.executeQuery();

					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("sr_no"));// 0
						list.add(rs.getString("practical"));// 1
						list.add(rs.getString("term"));// 2
						list.add(rs.getString("activity_practical_description"));// 1
						plist.add(list);
//						System.err.println("plist--------"+plist);
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
				return plist;
			}
		
		
		//Table 4: Learning objectives (Practical) of AyUG- RS===========================
		
		  public ArrayList<ArrayList<String>> table4_Learning_Objectives_Practical_of_AyUGRS(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
//				String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;

					sql = "select co.co_code,tm.activity_practical_description as practical,c.b3_learning_obj as b3,c3.domain, d3.scope,e3.rep_und,\n"
							+ "f3.method,g3.assessment_method,h3.assessment_type,i3.term,case when c.j3_integration is null then '' else c.j3_integration end as j3\n"
							+ "from edu_cc_tb_t4_learning_object_parent p\n"
							+ "inner join edu_cc_tb_t4_learning_object_child c on c.p_id=p.id\n"
							+ "inner join edu_cc_tb_list_of_practical_child tm on tm.id=p.practical_id\n"
							+ "inner join edu_cc_tb_c3_domain_mstr c3 on c3.id=c.c3_domain_sub\n"
							+ "inner join edu_cc_tb_d3_scope_understanding_mstr d3 on d3.id=c.d3_desirable_know\n"
							+ "inner join edu_cc_tb_e3_representation_understanding_mstr e3 on e3.id=c.e3_level_show_know\n"
							+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr f3 on f3.id=c.f3_t_l_method\n"
							+ "inner join edu_cc_tb_g3_assessment_method_mstr g3 on g3.id=c.g3_assessment\n"
							+ "inner join edu_cc_tb_h3_assessment_type_mstr h3 on h3.id=c.h3_formative_summative\n"
							+ "inner join edu_cc_tb_i3_term_mstr i3 on i3.id=c.i3_term\n"
							+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=c.a3_couse_outcome\n"
							+ "where p.course_id=? and p.status=0";
//							+ "group by 1,2,p.id order by p.id ";

					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----8-----***** \n"+stmt + "\n *****-----8-----***** \n");
					ResultSet rs = stmt.executeQuery();

					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();

//						list.add(rs.getString("paper"));//0
						list.add(rs.getString("practical"));//0
						list.add(rs.getString("co_code"));//1
						list.add(rs.getString("b3"));//2
						list.add(rs.getString("domain"));//3
						list.add(rs.getString("scope"));//4
						list.add(rs.getString("rep_und"));//5
						list.add(rs.getString("method"));//6
						list.add(rs.getString("assessment_method"));//7
						list.add(rs.getString("assessment_type"));//8
						list.add(rs.getString("term"));//9
						list.add(rs.getString("j3"));//10

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
		
		//end of Table 4: Learning objectives (Practical) of AyUG- RS===========================
	  
	//Table 5: Non-Lecture Activities Course AyUG-RS===============================
	  
	  public ArrayList<ArrayList<String>> table5_Non_Lecture_Activities_Course_AyUGRS(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
//			String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				
				sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,non_lecture_tl_method,no_of_activities\n"
						+ "from edu_cc_tb_add_non_lecture_activities_parent  p\n"
						+ "inner join edu_cc_tb_add_non_lecture_activities_child c on c.p_id=p.id\n"
						+ "where p.course_id=? and c.status=0 ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****----9-----***** \n"+stmt + "\n *****-----9----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					
					i++;
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("non_lecture_tl_method"));// 1
					list.add(rs.getString("no_of_activities"));// 2
//					list.add(rs.getString("po"));// 2

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
	  
	  public ArrayList<ArrayList<String>> practhours(String course_id) {
			ArrayList<ArrayList<String>> T5list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

						sql = "select no_of_hours \n"
						+ "from edu_cc_summary_teach_hrs_child c\n"
						+ "inner join edu_cc_summary_parent p on p.id=c.P_id\n"
						+ "where p.course_id=? and c.hours_type=3 and lecture_type=5";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----10-----***** \n"+stmt + "\n *****-----10-----***** \n");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("no_of_hours"));// 0
					T5list.add(list);
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
			return T5list;
		}
	  
	//end of Table 5: Non-Lecture Activities Course AyUG-RS===============================
	  
	  
	//6 A - Number of Papers and Marks Distribution============================
		
	  public ArrayList<ArrayList<String>> Table6A_NumberofPapersDataTotalCount(String course_id) {
			ArrayList<ArrayList<String>> nplist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
//			String formcode = sessionUserId.getAttribute("roleFormationNo").toString();
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, cm.course_code,\n"
						+ "(select count(ec.exam_paper) \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as papers,\n"
						+ "(select sum(ec.theory_comp_marks) \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as theory,\n"
						+ "(select epc.practical_marks \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as practical,\n"
						+ "(select epc.viva_marks \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as viva,\n"
						+ "(select epc.elective_marks \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as elective,\n"
						+ "(select epc.ia_marks \n"
						+ "	  from edu_cc_summary_parent p\n"
						+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as ia\n"
						+ "from edu_cc_summary_parent p \n"
						+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id\n"
						+ "where p.course_id=?";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				stmt.setInt(1, Integer.parseInt(course_id));
				stmt.setInt(2, Integer.parseInt(course_id));
				stmt.setInt(3, Integer.parseInt(course_id));
				stmt.setInt(4, Integer.parseInt(course_id));
				stmt.setInt(5, Integer.parseInt(course_id));
				stmt.setInt(6, Integer.parseInt(course_id));
				stmt.setInt(7, Integer.parseInt(course_id));
				System.err.println("\n *****-----11-----***** \n"+stmt + "\n *****-----11-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("course_code"));// 1
					list.add(rs.getString("papers"));// 2
					list.add(rs.getString("theory"));// 3
					list.add(rs.getString("practical"));// 4
					list.add(rs.getString("viva"));// 5
					list.add(rs.getString("elective"));// 6
					list.add(rs.getString("ia"));// 7

					nplist.add(list);
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
			return nplist;
		}

		
		//end of 6 A - Number of Papers and Marks Distribution============================


	// 6 D - Evaluation Methods for Periodical Assessment===============================
	  
		  public ArrayList<ArrayList<String>> Table6D_Evaluation_Methods_For_Periodical_Assessment(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;

					
					sql = "select ROW_NUMBER() OVER(order by e.id DESC) as sr_no,em.evaluation_method_pa\n"
							+ "from edu_cc_tb_evaluation_method_pa e\n"
							+ "inner join edu_cc_tb_evaluation_method_pa_mstr em on  em.id = e.evaluation_method_pa_id\n"
							+ "where e.status='1'and e.course_id=?";

					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----12-----***** \n"+stmt + "\n *****-----12-----***** \n");
					ResultSet rs = stmt.executeQuery();

					int i = 0;
					while (rs.next()) {
						
						i++;
						ArrayList<String> list = new ArrayList<String>();
						
						list.add(rs.getString("sr_no"));// 0
						list.add(rs.getString("evaluation_method_pa"));// 1

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
		  
		//end of 6 D - Evaluation Methods for Periodical Assessment===============================
		  
		//Table 6E Paper Layout=======================================
		  
			public ArrayList<ArrayList<String>> table_6E_paper_layout(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;
					sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\n"
							+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\n"
							+ "from edu_cc_tb_paper_layout pl\n"
							+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
							+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
							+ "where pl.status='1' and pl.course_id=? and pm.paper='PAPER I'\n";
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----13-----***** \n"+stmt + "\n *****-----13-----***** \n");
					ResultSet rs = stmt.executeQuery();
					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("sr_no"));// 0
						list.add(rs.getString("question_type"));// 1
						list.add(rs.getString("num_questions"));// 2
						list.add(rs.getString("marks_questions"));// 3
						list.add(rs.getString("total"));// 4
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
			

			public ArrayList<ArrayList<String>> table_6E_paper_layout2(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;
					sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\n"
							+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\n"
							+ "from edu_cc_tb_paper_layout pl\n"
							+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
							+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
							+ "where pl.status='1' and pl.course_id=? and pm.paper='PAPER II'\n";
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----14-----***** \n"+stmt + "\n *****-----14-----***** \n");
					ResultSet rs = stmt.executeQuery();
					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("sr_no"));// 0
						list.add(rs.getString("question_type"));// 1
						list.add(rs.getString("num_questions"));// 2
						list.add(rs.getString("marks_questions"));// 3
						list.add(rs.getString("total"));// 4
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
			//end of Table 6E Paper Layout=======================================
			
			 //shivali 6F_I 
			  
			  
			  public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_DAO(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;

						
						sql="select distinct ROW_NUMBER() OVER(order by dte.id asc) as sr_no, tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\n"
								+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\n"
								+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\n"
								+ "inner join edu_cc_tb_list_of_topics_child tc on tc.topic_id=dte.topic_id\n"
								+ "inner join edu_cc_tb_list_of_topics_parent tp on tp.id=tc.p_id\n"
								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\n"
								+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\n"
								+ "where tm.course_id= ?  and pm.paper='PAPER I' and dte.status=0 group by topic,mcq,saq,laq,term,marks,dte.id";

						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						stmt.setInt(1, Integer.parseInt(course_id));
						System.err.println("\n *****-----15-----***** \n"+stmt + "\n *****-----15-----***** \n");
						ResultSet rs = stmt.executeQuery();

						int i = 0;
						while (rs.next()) {
							
							i++;
							ArrayList<String> list = new ArrayList<String>();
							
							list.add(rs.getString("sr_no"));// 0
							list.add(rs.getString("topic"));// 1
							list.add(rs.getString("term"));// 2
							list.add(rs.getString("marks"));// 3
							list.add(rs.getString("mcq"));// 4
							list.add(rs.getString("saq"));// 5
							list.add(rs.getString("laq"));// 6

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
			  
			  
			
			  
			  
			  public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_DAO(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;

						
						sql="select distinct ROW_NUMBER() OVER(order by dte.id asc) as sr_no,tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\n"
								+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\n"
								+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\n"
								+ "inner join edu_cc_tb_list_of_topics_child tc on tc.topic_id=dte.topic_id\n"
								+ "inner join edu_cc_tb_list_of_topics_parent tp on tp.id=tc.p_id\n"
								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\n"
								+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\n"
								+ "where tm.course_id= ?  and pm.paper='PAPER II' and dte.status=0 and tp.status=0 group by topic,mcq,saq,laq,term,marks,dte.id";

						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						stmt.setInt(1, Integer.parseInt(course_id));
						System.err.println("\n *****-----16-----***** \n"+stmt + "\n *****-----16-----***** \n");
						ResultSet rs = stmt.executeQuery();

						int i = 0;
						while (rs.next()) {
							
							i++;
							ArrayList<String> list = new ArrayList<String>();
							
							list.add(rs.getString("sr_no"));// 0
							list.add(rs.getString("topic"));// 1
							list.add(rs.getString("term"));// 2
							list.add(rs.getString("marks"));// 3
							list.add(rs.getString("mcq"));// 4
							list.add(rs.getString("saq"));// 5
							list.add(rs.getString("laq"));// 6

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
			  
			//end of shivali 6F_I 
		  
		//6 G Question paper Blue print for AyU-RS====================================
			
			
			
		//end of 6 G Question paper Blue print for AyU-RS================================
		  
		//Table 6 H1 Distribution of Practical Exam=======================================
		  
			public ArrayList<ArrayList<String>> table_6H_I_Distribution_of_Practical_Exam(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;
					sql = "select ROW_NUMBER() OVER(order by id DESC) as SN, head , mark from edu_cc_tb_6h_distrubution_practical_exam \n "
							+"              where course_id=? and status='1' \n";
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----17-----***** \n"+stmt + "\n *****-----17-----***** \n");
					ResultSet rs = stmt.executeQuery();
					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("SN"));// 0
						list.add(rs.getString("head"));// 1
						list.add(rs.getString("mark"));// 2
						alist.add(list);
//						System.err.println("Table6F_IIDistribution_of_Theory_Exam_List list==========="+list);
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
			
			//end of Table 6 H1 Distribution of Practical Exam==========================
		
		// Reference and Resourses ============================
			public ArrayList<ArrayList<String>> tablereference_resourses(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;
					sql = "select id,ROW_NUMBER() OVER(order by id asc) as sr_no, resource from edu_cc_link_reference_resourses_mstr r where  r.status=1 and course_id=?";
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.setInt(1, Integer.parseInt(course_id));
					System.err.println("\n *****-----18-----***** \n"+stmt + "\n *****-----18-----***** \n");
					ResultSet rs = stmt.executeQuery();
					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("resource"));// 0
						list.add(rs.getString("sr_no"));// 1
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
			//End Reference and Resourses============================
		  
			
//			 public ArrayList<ArrayList<String>> getCoursedata(String course_id) {
//					ArrayList<ArrayList<String>> plist = new ArrayList<ArrayList<String>>();
//					Connection conn = null;
//					try {
//						conn = dataSource.getConnection();
//						String sql = "";
//						PreparedStatement stmt = null;
//
////						sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, p.practical,t.term\n"
////								+ "from edu_cc_tb_list_of_practical_child p \n"
////								+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
////								+ "where p.course_id=?";
//						
//						
//						sql="select \n"
//								+ "case when data.a::int > 0 then \n"
//								+ "case when data.b::int > 0 then \n"
//								+ "case when data.c::int > 0 then \n"
//								+ "case when data.d::int > 0 then \n"
//								+ "case when data.e::int > 0 then \n"
//								+ "case when data.f::int > 0 then \n"
//								+ "case when data.g::int > 0 then \n"
//								+ "case when data.h::int > 0 then \n"
//								+ "case when data.i::int > 0 then \n"
//								+ "case when data.j::int > 0 then \n"
//								+ "1\n"
//								+ "else 0 end else 0 end else 0 end else 0 end\n"
//								+ "else 0 end else 0 end else 0 end else 0 end else 0 end else 0 end as result\n"
//								+ "\n"
//								+ "from (\n"
//								+ "select count(aa)::int as a,count(bb)::int as b,count(cc)::int as c ,count(dd)::int as d,count(ee)::int as e,count(ff)::int as f,count(gg)::int as g , count(hh)::int as h , count(ii)::int as i, count(jj)::int as j , count(kk)::int as k , count(ll)::int as l , count(mm)::int as m, count(nn)::int as n ,count(oo)::int as o, count(pp)::int as p,count(qq)::int as q from (select count(a) from (select sm.id,sm.system_name,dm.degree_name,prm.professional,cm.course_name,cm.course_code,\n"
//								+ "toh.type_of_hours,tot.type_of_teaching,pm.paper,tc.no_of_hours,sepc.practical_marks,sec.theory_comp_marks\n"
//								+ "from edu_cc_summary_parent p\n"
//								+ "inner join edu_cc_summary_teach_hrs_child tc on tc.p_id=p.id\n"
//								+ "inner join edu_cc_summary_examination_child ec on ec.p_id=p.id\n"
//								+ "inner join edu_cc_tb_type_of_hours_mstr toh on toh.id=tc.hours_type\n"
//								+ "inner join edu_cc_tb_type_of_teaching_mstr tot on tot.id=tc.lecture_type\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tc.paper\n"
//								+ "inner join edu_lms_system_mstr sm on  sm.id=p.system_id\n"
//								+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree_id\n"
//								+ "inner join edu_cc_tb_professional_mstr prm on prm.id=p.professional_id\n"
//								+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id::int\n"
//								+ "inner join edu_cc_summary_examination_child sec on sec.p_id=p.id\n"
//								+ "inner join edu_cc_summary_exam_pract_comp_child sepc on sepc.p_id=p.id\n"
//								+ "where p.course_id = ? and tc.status=0 \n"
//								+ ") a) aa,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(b) from (select distinct pm.paper,sec.theory_comp_marks,sepcc.practical_marks,sepcc.viva_marks,sepcc.ia_marks,sepcc.elective_marks\n"
//								+ "from edu_cc_summary_parent sp \n"
//								+ "inner join edu_cc_summary_examination_child sec on sec.p_id=sp.id\n"
//								+ "inner join edu_cc_summary_exam_pract_comp_child sepcc on sepcc.p_id=sp.id\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=sec.exam_paper\n"
//								+ "where sp.course_id= ?) b) bb\n"
//								+ ",\n"
//								+ "(select count(c) from (select c.practical,c.activity_practical_description,t.term from edu_cc_tb_list_of_practical_parent p\n"
//								+ "inner join edu_cc_tb_list_of_practical_child c on c.p_id = p.id\n"
//								+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id where p.course_id=?) c) cc,\n"
//								+ "\n"
//								+ "(select count(d) from (select com.co_code,com.course_outcome as co,string_agg(pom.code,',') as po\n"
//								+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent p\n"
//								+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child c on c.p_id=p.id\n"
//								+ "inner join edu_cc_tb_add_course_outcome_mstr com on com.id=p.course_outcome_id\n"
//								+ "inner join edu_cc_tb_program_outcome_mstr pom on pom.id=c.programoutcome_id\n"
//								+ "where p.course_id=? and p.status=0 \n"
//								+ "group by 1,2,p.id order by p.id ) d) dd,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(e) from (select ROW_NUMBER() OVER(order by c.id ) as sr_no,tom.topic,tm.term,mm.marks,lecture_hours as lh,non_lecture_hours as nlh,\n"
//								+ "string_agg(sut.sub_topic,'/') as sub_topic,pm.paper\n"
//								+ "from edu_cc_tb_list_of_topics_parent p\n"
//								+ "inner join edu_cc_tb_list_of_topics_child c on c.p_id=p.id\n"
//								+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=c.term_id\n"
//								+ "inner join edu_cc_tb_topics_mstr tom on tom.id=c.topic_id\n"
//								+ "inner join edu_cc_tb_define_topic_wise_marks_child mmc on mmc.topic_id=c.topic_id\n"
//								+ "inner join edu_cc_tb_define_topic_wise_marks_parent mm on mm.id=mmc.p_id\n"
//								+ "inner join edu_cc_tb_sub_topics_mstr sut on sut.topic_id=c.topic_id\n"
//								+ "inner join edu_cc_tb_paper_mstr pm ON pm.id = p.paper_id \n"
//								+ "where p.status=0 and p.course_id= ? \n"
//								+ "group by p.id,2,3,4,5,6,8,c.id\n"
//								+ "order by c.id ) e ) ee,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(f) from(select co.co_code,tm.topic,c.b3_learning_obj as b3,c3.domain, d3.scope,e3.rep_und,f3.method,g3.assessment_method,h3.assessment_type,\n"
//								+ "i3.term,c.j3_integration as j3,pm.paper\n"
//								+ "from edu_cc_tb_t3_learning_object_parent p\n"
//								+ "inner join edu_cc_tb_t3_learning_object_child c on c.p_id=p.id\n"
//								+ "inner join edu_cc_tb_topics_mstr tm on tm.id=p.topic_id\n"
//								+ "inner join edu_cc_tb_c3_domain_mstr c3 on c3.id=c.c3_domain_sub\n"
//								+ "inner join edu_cc_tb_d3_scope_understanding_mstr d3 on d3.id=c.d3_desirable_know\n"
//								+ "inner join edu_cc_tb_e3_representation_understanding_mstr e3 on e3.id=c.e3_level_show_know\n"
//								+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr f3 on f3.id=c.f3_t_l_method\n"
//								+ "inner join edu_cc_tb_g3_assessment_method_mstr g3 on g3.id=c.g3_assessment\n"
//								+ "inner join edu_cc_tb_h3_assessment_type_mstr h3 on h3.id=c.h3_formative_summative\n"
//								+ "inner join edu_cc_tb_i3_term_mstr i3 on i3.id=c.i3_term\n"
//								+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=c.a3_couse_outcome\n"
//								+ "inner join edu_cc_tb_list_of_topics_child ltc on ltc.topic_id=tm.id\n"
//								+ "inner join edu_cc_tb_list_of_topics_parent ltp on ltp.id=ltc.p_id\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=ltp.paper_id \n"
//								+ "where p.course_id=? and p.status=0  order by paper,topic,co_code   ) f) ff,\n"
//								+ "\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(g) from (select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,c.activity_practical_description,t.term \n"
//								+ "from edu_cc_tb_list_of_practical_parent p\n"
//								+ "inner join edu_cc_tb_list_of_practical_child c on c.p_id = p.id\n"
//								+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id where p.course_id=?) g) gg,\n"
//								+ "\n"
//								+ "(select count(h) from (select co.co_code,tm.activity_practical_description as practical,c.b3_learning_obj as b3,c3.domain, d3.scope,e3.rep_und,\n"
//								+ "f3.method,g3.assessment_method,h3.assessment_type,i3.term,c.j3_integration as j3\n"
//								+ "from edu_cc_tb_t4_learning_object_parent p\n"
//								+ "inner join edu_cc_tb_t4_learning_object_child c on c.p_id=p.id\n"
//								+ "inner join edu_cc_tb_list_of_practical_child tm on tm.id=p.practical_id\n"
//								+ "inner join edu_cc_tb_c3_domain_mstr c3 on c3.id=c.d3_desirable_know\n"
//								+ "inner join edu_cc_tb_d3_scope_understanding_mstr d3 on d3.id=c.c3_domain_sub\n"
//								+ "inner join edu_cc_tb_e3_representation_understanding_mstr e3 on e3.id=c.e3_level_show_know\n"
//								+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr f3 on f3.id=c.f3_t_l_method\n"
//								+ "inner join edu_cc_tb_g3_assessment_method_mstr g3 on g3.id=c.g3_assessment\n"
//								+ "inner join edu_cc_tb_h3_assessment_type_mstr h3 on h3.id=c.h3_formative_summative\n"
//								+ "inner join edu_cc_tb_i3_term_mstr i3 on i3.id=c.i3_term\n"
//								+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=c.a3_couse_outcome\n"
//								+ "where p.course_id=? and p.status=0) h) hh,\n"
//								+ "\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(i) from (select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,non_lecture_tl_method,no_of_activities\n"
//								+ "from edu_cc_tb_add_non_lecture_activities_parent  p\n"
//								+ "inner join edu_cc_tb_add_non_lecture_activities_child c on c.p_id=p.id\n"
//								+ "where p.course_id=?) i ) ii,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(j) from (select no_of_hours \n"
//								+ "from edu_cc_summary_teach_hrs_child c\n"
//								+ "inner join edu_cc_summary_parent p on p.id=c.P_id\n"
//								+ "where p.course_id=? and c.hours_type=3 and lecture_type=5) j) jj,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(k) from (select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, cm.course_code,\n"
//								+ "(select count(ec.exam_paper) \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as papers,\n"
//								+ "(select sum(ec.theory_comp_marks) \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as theory,\n"
//								+ "(select epc.practical_marks \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as practical,\n"
//								+ "(select epc.viva_marks \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as viva,\n"
//								+ "(select epc.elective_marks \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as elective,\n"
//								+ "(select epc.ia_marks \n"
//								+ "	  from edu_cc_summary_parent p\n"
//								+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as ia\n"
//								+ "from edu_cc_summary_parent p \n"
//								+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id\n"
//								+ "where p.course_id=?\n"
//								+ ") k) kk,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(l) from (select ROW_NUMBER() OVER(order by e.id DESC) as sr_no,em.evaluation_method_pa\n"
//								+ "from edu_cc_tb_evaluation_method_pa e\n"
//								+ "inner join edu_cc_tb_evaluation_method_pa_mstr em on  em.id = e.evaluation_method_pa_id\n"
//								+ "where e.status='1'and e.course_id= ? ) l) ll,\n"
//								+ "\n"
//								+ "(select count(m) from (select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\n"
//								+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\n"
//								+ "from edu_cc_tb_paper_layout pl\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
//								+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
//								+ "where pl.status='1' and pl.course_id=? and pm.paper='PAPER I') m) mm,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(n) from ( select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\n"
//								+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\n"
//								+ "from edu_cc_tb_paper_layout pl\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
//								+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
//								+ "where pl.status='1' and pl.course_id=? and pm.paper='PAPER II') n ) nn,\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(o) from (select distinct ROW_NUMBER() OVER(order by dte.id asc) as sr_no, tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\n"
//								+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\n"
//								+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\n"
//								+ "inner join edu_cc_tb_list_of_topics_child tc on tc.topic_id=dte.topic_id\n"
//								+ "inner join edu_cc_tb_list_of_topics_parent tp on tp.id=tc.p_id\n"
//								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\n"
//								+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\n"
//								+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\n"
//								+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\n"
//								+ "where tm.course_id= ?  and pm.paper='PAPER I' and dte.status=0 group by topic,mcq,saq,laq,term,marks,dte.id) o) oo,\n"
//								+ "\n"
//								+ "\n"
//								+ "\n"
//								+ "(select count(p) from (select ROW_NUMBER() OVER(order by id DESC) as SN, head , mark from edu_cc_tb_6h_distrubution_practical_exam \n"
//								+ "where course_id=? and status='1' ) p) pp,\n"
//								+ "\n"
//								+ "(select count(q) from (select id,ROW_NUMBER() OVER(order by id asc) as sr_no, resource from edu_cc_link_reference_resourses_mstr where course_id=?\n"
//								+ ") q ) qq\n"
//								+ ") data";
//						
//						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//						for(int j = 1 ;j <= 23; j++) {
//						stmt.setInt(j, Integer.parseInt(course_id));
//						}
//						System.err.println("ssssss---------------Final-------------"+stmt);
//						ResultSet rs = stmt.executeQuery();
//
//						int i = 0;
//						while (rs.next()) {
//							i++;
//							ArrayList<String> list = new ArrayList<String>();
//							list.add(rs.getString("result"));// 0
////							list.add(rs.getString("activity_practical_description"));// 1
////							list.add(rs.getString("term"));// 2
//							plist.add(list);
////							System.err.println("plist--------"+plist);
//						}
//						rs.close();
//						stmt.close();
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					} finally {
//						if (conn != null) {
//							try {
//								conn.close();
//							} catch (SQLException e) {
//							}
//						}
//					}
//					return plist;
//				}
			
			 @Override
				public ArrayList<ArrayList<String>> GetSystemdegreeid_fetch(String userid) {
					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					
					String q = "";
				
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
						String qry = "";
				
							q = "select a.system,a.degree,a.semester  from (select ayush_id,aadhar_card,system,degree,semester,email \n"
									+ "from edu_lms_student_details\n"
									+ "UNION ALL\n"
									+ "select ayush_id,aadhar_card,system,degree,semester,email from edu_lms_nch_student_details) a \n"
									+ "inner join edu_lms_system_mstr s on s.id = a.system\n"
									+ "inner join logininformation l on l.email_id = a.email\n"
									+ "inner join edu_lms_degree_mstr m on m.id = a.degree\n"
									+ "where userid = ?";
						
							stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(userid));
							System.err.println("------Getdegreeid_fetch----"+stmt);
							ResultSet rs = stmt.executeQuery();      
							while (rs.next()) {				
							  
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("system")); //0
							alist.add(rs.getString("degree")); //1
							
							alist.add(rs.getString("semester")); //2
							
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
				public ArrayList<ArrayList<String>> GetSystem_fetch(String userid) {
					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					
					String q = "";
				
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
						String qry = "";
				
							q = "select s.id from edu_lms_institute_reg i \n"
									+ "inner join  edu_lms_system_mstr s on s.id = i.system_id\n"
									+ "inner join logininformation l on l.institute_id = i.id "
									+ "where l.userid = ?";
						
							stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(userid));
							System.err.println("------Getdegreeid_fetch----"+stmt);
							ResultSet rs = stmt.executeQuery();      
							while (rs.next()) {				
							  
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id")); //0
							
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
