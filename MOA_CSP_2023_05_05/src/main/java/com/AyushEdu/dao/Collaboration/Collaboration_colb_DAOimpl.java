package com.AyushEdu.dao.Collaboration;

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

import com.AyushEdu.Models.Collaboration.TB_COL_ORG_COLB;
//import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;
//import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

@Repository
public class Collaboration_colb_DAOimpl implements Collaboration_colb_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public long DataTableCollaborationcolbDataTotalCount1(String Search, String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, collaborationtype, collaborationsector,collaborationcategory,
				collaborationtitle,collaborationdescription,from_date,to_date);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
//			q="select count(*) from (select id,collab_cate,status from tb_col_org_colb where status='"+ status +"'\n"
//					+ SearchValue + ") ab";
			
			q="select count(*) from (select coc.id,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,\n"
					+ "collaborationdescription,to_char(from_date,'dd/mm/yyyy') as from_date ,to_char(to_date,'dd/mm/yyyy') as to_date ,cot.collaboration_type ,cos.sector_type ,cob.collab_cate \n"
					+ "from TB_COL_ORG_COLB coc\n"
					+ "INNER join tb_col_org_type cot on cot.id::text = coc.collaborationtype\n"
					+ "INNER join tb_col_org_sector cos on cos.id::text = coc.collaborationsector\n"
					+ "INNER join tb_col_org_category cob on cob.id::text = coc.collaborationcategory\n"
					+ " WHERE\n"
					+ " coc.id is not null\n"
					+ SearchValue + ") ab";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date);

//			System.err.println("stmt----ch-->  "+stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search,String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date ) {
		String SearchValue = "";
	
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(collaborationtype) like ? or upper(collaborationtitle) like ? or upper(collaborationdescription) like ? \n"
							+" or to_char(from_date,'dd-MON-yyyy') like ? or to_char(to_date,'dd-MON-yyyy') like ? or upper(cot.collaboration_type) like ? \n"
					+" or upper(cos.sector_type) like ? or upper(cob.collab_cate) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
	
		if( !collaborationtype.equals("0")) {
			SearchValue += " and upper(collaborationtype) like ? ";
		
	     }
         
		if( !collaborationsector.equals("0")) {
			SearchValue += " and upper(collaborationsector) like ? ";
		
	     }
		if( !collaborationcategory.equals("0")) {
			SearchValue += " and upper(collaborationcategory) like ? ";
		
	     }
		if( collaborationtitle != null && !collaborationtitle.equals("")) {
			SearchValue += " and upper(collaborationtitle) like ? ";
		
	     }
		if( collaborationdescription != null && !collaborationdescription.equals("")) {
			SearchValue += " and upper(collaborationdescription) like ? ";
		
	     }
		if( from_date != null && !from_date.equals("")) {
			SearchValue += " and to_char(from_date,'dd/mm/yyyy') like ? ";
		
	     }
		if( to_date != null && !to_date.equals("")) {
			SearchValue += " and to_char(to_date,'dd/mm/yyyy') like ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }
          
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date) {
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			
			if ( !collaborationtype.equals("0")) {
				flag += 1;
				stmt.setString(flag, "%"+collaborationtype.toUpperCase() + "%");
			}
			
			if (!collaborationsector.equals("0")) {
				flag += 1;
				stmt.setString(flag, "%"+collaborationsector.toUpperCase() + "%");
			}
			
			if ( !collaborationcategory.equals("0")) {
				flag += 1;
				stmt.setString(flag, "%"+collaborationcategory.toUpperCase() + "%");
			}
			
			if (collaborationtitle != null && !collaborationtitle.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+collaborationtitle.toUpperCase() + "%");
			}
			
			if (collaborationdescription != null && !collaborationdescription.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+collaborationdescription.toUpperCase() + "%");
			}
			
			if (from_date != null && !from_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
			}
			 
			if (to_date != null && !to_date.equals("")) {
				flag += 1;
				stmt.setString(flag,to_date);
			}
		
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String, Object>> DataTableCollaborationcolbDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String collaborationtype, String collaborationsector,  
			String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date);
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

			if(Search.equals("") && collaborationtype.equals("0") && collaborationsector.equals("0") && collaborationcategory.equals("0") && collaborationtitle.equals("0") && collaborationdescription.equals("0") && from_date.equals("0") && to_date.equals("0") ) {
				q = "select coc.id,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,\n"
						+ "collaborationdescription,to_char(from_date,'dd-MON-yyyy') as from_date ,to_char(to_date,'dd-MON-yyyy') as to_date , \n"
						+"cot.collaboration_type ,cos.sector_type ,cob.collab_cate ,to_char(from_date,'dd/mm/yyyy') as from_date_formated,to_char(to_date,'dd/mm/yyyy') as to_date_formated\n"
						+ "from TB_COL_ORG_COLB coc\n"
						+ "INNER join tb_col_org_type cot on cot.id::text = coc.collaborationtype\n"
						+ "INNER join tb_col_org_sector cos on cos.id::text = coc.collaborationsector\n"
						+ "INNER join tb_col_org_category cob on cob.id::text = coc.collaborationcategory\n"
						+ "WHERE\n"
						+ " coc.id is not null" + SearchValue + " ORDER BY coc.id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select coc.id,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,\n"
					+ "collaborationdescription,to_char(from_date,'dd-MON-yyyy') as from_date ,to_char(to_date,'dd-MON-yyyy') as to_date , \n "
					+"cot.collaboration_type ,cos.sector_type ,cob.collab_cate,to_char(from_date,'dd/mm/yyyy') as from_date_formated,to_char(to_date,'dd/mm/yyyy') as to_date_formated \n"
					+ "from TB_COL_ORG_COLB coc\n"
					+ "INNER join tb_col_org_type cot on cot.id::text = coc.collaborationtype\n"
					+ "INNER join tb_col_org_sector cos on cos.id::text = coc.collaborationsector\n"
					+ "INNER join tb_col_org_category cob on cob.id::text = coc.collaborationcategory\n"
					+ "WHERE \n"
					+ " coc.id is not null "+ SearchValue + " ORDER BY coc.id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDCollaboratoncolb' value='ADD' title='editData' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apidID"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='aptypeTYPE"+countFunction+"' value="+rs.getString("collaborationtype")+">"
								+"<input type='hidden' id='apsectSECT"+countFunction+"' value="+rs.getString("collaborationsector")+"></i></a> </li>"
								+"<input type='hidden' id='apcateCATE"+countFunction+"' value="+rs.getString("collaborationcategory")+"></i></a> </li>"
								+"<input type='hidden' id='aptitlTITL"+countFunction+"' value="+rs.getString("collaborationtitle")+"></i></a> </li>"
								+"<input type='hidden' id='apdescDESC"+countFunction+"' value="+rs.getString("collaborationdescription")+"></i></a> </li>"
								+"<input type='hidden' id='apfrodFROD"+countFunction+"' value="+rs.getString("from_date_formated")+"></i></a> </li>"
				                +"<input type='hidden' id='aptodTOD"+countFunction+"' value="+rs.getString("to_date_formated")+"></i></a> </li>";
																
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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

	public TB_COL_ORG_COLB getCollaborationcolbtByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_COL_ORG_COLB updateid = (TB_COL_ORG_COLB) session.get(TB_COL_ORG_COLB.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateCollaborationcolb(TB_COL_ORG_COLB obj)
	{
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			
			System.err.println(obj.getFrom_date()+"---"+obj.getTo_date());
			
			String hql = "update TB_COL_ORG_COLB set collaborationtype=:collaborationtype,collaborationsector=:collaborationsector, collaborationcategory=:collaborationcategory, collaborationtitle=:collaborationtitle, collaborationdescription=:collaborationdescription, from_date=:from_date, to_date=:to_date,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("collaborationtype", obj.getCollaborationtype()).setParameter("collaborationsector", obj.getCollaborationsector())
					.setParameter("collaborationcategory", obj.getCollaborationcategory()).setParameter("collaborationtitle", obj.getCollaborationtitle())
					.setParameter("collaborationdescription", obj.getCollaborationdescription()).setParameter("from_date", obj.getFrom_date())
					.setParameter("to_date", obj.getTo_date())
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
//			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	

	}
	
	 public ArrayList<ArrayList<String>> DataOfCollab() {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				sql = "select coc.id,cot.collaboration_type,collaborationtitle,cos.sector_type,cob.collab_cate,\n"
						+ "to_char(from_date,'DD-MM-YYYY') as from_date,to_char(to_date,'DD-MM-YYYY') as to_date,collaborationdescription\n"
						+ "from TB_COL_ORG_COLB coc\n"
						+ "INNER join tb_col_org_type cot on cot.id::int = coc.collaborationtype::int\n"
						+ "INNER join tb_col_org_sector cos on cos.id::int = coc.collaborationsector::int\n"
						+ "INNER join tb_col_org_category cob on cob.id::int = coc.collaborationcategory::int\n";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("stmt----collab data------" + stmt);
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("collaboration_type"));// 1
					list.add(rs.getString("collaborationtitle"));// 2
					list.add(rs.getString("sector_type"));// 3
					list.add(rs.getString("collab_cate"));// 4
					list.add(rs.getString("from_date"));// 5
					list.add(rs.getString("to_date"));// 6
					list.add(rs.getString("collaborationdescription"));// 7
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
