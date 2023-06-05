package com.AyushEdu.dao.Regulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Regulation.REG_NCH_REGISTRATION_A;

@Repository
public class Search_Pra_RegistrationDAOImpl implements Search_Pra_RegistrationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	CommonController common;
	

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTablePra_Registration_masterDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String institute_state, String status) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_state, status);
		// Image image = new
		// ImageIcon(this.getClass().getResource(photo_path)).getImage();
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

//			q = " select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
//					+ "						from reg_nch_registration_a  pr\n"
//					+ "						inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
//					+ "						inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = pr.institute_state\n"
//					+ "						\n" + "						where pr.id!= 0  " + SearchValue
//					+ " order by id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			
			
//			------------------------24/06/22 Urmik changes 
			
			q="select distinct pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
					+ "						from reg_nch_registration_a  pr\n"
					+ "						inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
					+ "						inner join edu_lms_state_mstr ss on ss.state_id   = pr.institute_state"+ SearchValue
					+ " order by id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			
			
			
			
			
			

//			if (Search.equals("")  && status =="1") {
//				q=" select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
//						+ "from reg_nch_registration_a  pr\n"
//						+ "inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
//						+ "inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = pr.institute_state\n"
//						
//						+ "where pr.id!= 0 and status = 1 "+ SearchValue +" order by id " + orderType
//							+ " limit " + pageL + " OFFSET " + startPage;	
//				}
//			if (Search.equals("")  && status =="2") {
//				q=" select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
//						+ "from reg_nch_registration_a  pr\n"
//						+ "inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
//						+ "inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = pr.institute_state\n"
//						
//						+ "where pr.id!= 0 and status = 2 "+ SearchValue +" order by id " + orderType
//							+ " limit " + pageL + " OFFSET " + startPage;	
//			}

//			q=" select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
//					+ "from reg_nch_registration_a  pr\n"
//					+ "inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
//					+ "inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = pr.institute_state\n"
//					
//					+ "where pr.id!= 0 "+ SearchValue +" order by id " + orderType
//						+ " limit " + pageL + " OFFSET " + startPage;	

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, institute_state, status);
			System.out.println("stmt-----Urmikkkkkkkkkkkkk-----" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction = "";

				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)// 13
						+ "' name='cbox' onclick='checkCKBT();singleappcount();'/>";
//				onchange='checkbox_count(this," + rs.getObject(1) + ");'

				String CheckboxId = "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)// 14
						+ "' value='" + rs.getObject(1) + "'   />";
				chekboxaction += Checkbox;
//		     System.err.println("chekboxaction----------"+chekboxaction);

				action = f + " " + f1;

				columns.put("chekboxaction", chekboxaction);
				// columns.put("action", action);

//				list.add(chekboxaction);
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
	public long DataTablePra_Registration_masterDataTotalCount(String Search, String institute_state, String status
			) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, institute_state, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q = "select count(*)  from (select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
//					+ "						from reg_nch_registration_a  pr\n"
//					+ "						inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
//					+ "						inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = pr.institute_state\n"
//					+ "						where pr.id!= 0 " + SearchValue + " ) a ";
			
			
			
			
			
//			24/06/22 Urmik Changes 
			
			
			q="	select count(*)  from (select pr.id,pr.aayushid,pr.name,pr.email_id, ir.institute_name ,ss.state_name as institute_state,pr.status\n"
					+ "						from reg_nch_registration_a  pr\n"
					+ "						inner join edu_lms_institute_reg ir on cast (ir.id as character varying)  = pr.institute_name\n"
					+ "						inner join edu_lms_state_mstr ss on  (ss.state_id )  = pr.institute_state"+ SearchValue + " ) a ";
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_state, status);
			System.out.println("-------------stmt count" + stmt);
			ResultSet rs = stmt.executeQuery();

			// System.err.println("stmt-----hhuuuuhhh-----"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String institute_state, String status) throws ParseException {
		String SearchValue = "";

		System.out.println("status dao" + status);

		/// advance search
//System.err.println("---------insti "+institute_state);
//System.err.println("---------status "+status);

		if (!institute_state.equals("0")) {
			SearchValue += " and pr.institute_state = ? ";
		}

		if (status.equals("0")) {
			SearchValue += " and pr.status = ? ";

		}
		if (status.equals("1")) {
			SearchValue += " and pr.status = ? ";

		}
		if (status.equals("2")) {
			SearchValue += " and pr.status = ? ";

		}

//		if (!from_date.equals("") && !to_date.equals("")) {
//			//SearchValue += " and pr.institute_state = ? ";
//			SearchValue += " and pr.created_date between ? and ? ";
//			 
//			}

//		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//		Date from_date2 = null;
//		Date to_date2 = null;
//		if (!from_date.equals("") && !to_date.equals("")) {
//
//			String from_date_i = from_date.substring(8, 10) + "-" + from_date.substring(5, 7) + "-"
//					+ from_date.substring(0, 4);
//			String to_date_i = to_date.substring(8, 10) + "-" + to_date.substring(5, 7) + "-" + to_date.substring(0, 4);
//			from_date2 = format.parse(from_date);
//			to_date2 = format.parse(to_date);
//			SearchValue += " and pr.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
//
//		}

//		Date from_date = null;
//		if (!request.getParameter("from_date").equals("")) {
//			String l[] = request.getParameter("from_date").split("/");
//			String from_date_i = l[2]+"-"+l[1]+"-"+l[0];
//			from_date = format.parse(from_date_i);
//		} else {
//			from_date = null;
//		}
//		Date to_date = null;
//		if (!request.getParameter("to_date").equals("")) {
//			String l[] = request.getParameter("to_date").split("/");
//			String to_date_i = l[2]+"-"+l[1]+"-"+l[0];
//			to_date = format.parse(to_date_i);
//		} else {
//			to_date = null;
//		}
//		

		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "  (lower(ss.state_name) like ? or lower(pr.aayushid) like ? or lower(pr.name) like ? or lower(pr.email_id) like ? or lower(ir.institute_name) like ? ) ";
		}
		
//		if (!name.trim().equals("")) {
//			SearchValue += " and upper(name) like ? ";
//			System.err.println("parameter search" + SearchValue);
//
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String institute_state,
			String status) {
		int flag = 0;
		try {
//			if (Search!=null  &&  !Search.equals("") ) {
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//			}
//			
//			if (!institute_state.equals("0") && institute_state != "0") {
//				flag += 1;
//				stmt.setString(flag, institute_state);
//			}

			if (!institute_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, institute_state);
			}

			if (status.equals("0")) {
				flag += 1;
				stmt.setInt(flag, 0);
			}
			if (status.equals("1")) {
				flag += 1;
				stmt.setInt(flag, 1);
			}
			if (status.equals("2")) {
				flag += 1;
				stmt.setInt(flag, 2);
			}
			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",
			// Locale.ENGLISH);

			// from_date1 = formatter.parse(from_date);
//			if (!from_date2.equals("") && !to_date2.equals("")) {
//				flag += 1;
//				stmt.setString(flag, from_date2);
//				flag += 1;
//				stmt.setString(flag, to_date2);
//			}

			if (Search != null && !Search.equals("")) {
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

//			if (!name.equals("") && name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + name.toUpperCase() + "%");
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	public String approve_reject_reg(String a, String status,HttpSession session) throws ParseException {
		System.err.println("============" + a);
		String[] id_list = a.split(":");
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		System.err.println("check the id_list---- "+id_list.toString());
		
		Connection conn = null;
		Integer out = 0;
		String q = "";

		try {
			
			System.err.println("enterrrrrrrr or not in approve_reject");
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			 

			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				// Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 REG_NCH_REGISTRATION_A assetupd = (REG_NCH_REGISTRATION_A) sessionHQL .get(REG_NCH_REGISTRATION_A.class, id);
				
				if (assetupd.getAayushid() == null || assetupd.getAayushid().equals("")) {
					String maxAID = getMaxAID();
					System.err.println("maxAID "+maxAID);
					int newn=0;
					if(maxAID!=null) {
						 newn = Integer.parseInt(maxAID);
						 System.err.println("newn "+newn);
					}
					newn++;
				
					String abc = String.format("%6s", newn).replace(' ', '0');
					abc = "AU"+abc;
					System.err.println("abc "+abc);
					assetupd.setAayushid(abc);
					assetupd.setStatus(1);
					
				}else {
					assetupd.setStatus(1);
				}
 
				sessionHQL.update(assetupd);
//				out = stmt.executeUpdate();
				UserLogin p=new UserLogin();
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode("Bisag@123");
				p.setPassword(hashedPassword);
				p.setUserName(assetupd.getEmail_id());
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
				p.setAc_dc_date(modifydate);
//				p.setMobile_no(mobile_no);
				p.setEmail_id(assetupd.getEmail_id());
				p.setInstitute_id(Integer.parseInt( common.getInstIdfromUserid(sessionFactory,userid ).get(0)));
//				sp.setState_id(Integer.parseInt(common.getInstitudeState(sessionFactory, common.getInstIdfromUserid(sessionFactory,userid ).get(13))));
				
				//p.setArmy_no(army_no);
				p.setCreated_on(new Date());
				p.setCreated_by(assetupd.getEmail_id());
				UserRole role_tbl = new UserRole();
				//sessionHQL.beginTransaction();

				int did = (Integer) sessionHQL.save(p);
				assetupd.setUser_id(did);
				role_tbl.setRoleId(13);
				role_tbl.setUserId(did);
				sessionHQL.save(role_tbl);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				
				
				
				//if (status.equals("1")) {
//				 SendRegMail(assetupd.getEmail_id(),"Bisag@123",assetupd.getEmail_id());
				 
//				 SendRegMail(assetupd.getState_id());
				//}

			}
			
			conn.close();
				return "Email has been sent Successfully.";


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

		if (status.equals("1")) {
		
			return "Email has been sent Successfully.";
		} else if (status.equals("5")) {
			return "Email has not been sent Successfully.";
		} else {
			return "Invalid";
		}
		
		
		

	}
	
	
	///generate ayushid/////
	public String getMaxAID() {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			query = "select max(id) from reg_nch_registration_a where aayushid != '' and aayushid is not null";
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("max");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reg_no;
	}
	
	// FOR EMAIL
//	public void SendRegMail(String email, String pass, String user) throws ParseException {
//			System.err.println("email--"+email);
////		String code = randomString(5);
//		MailHTML html = new MailHTML();
//
//		try {
//System.err.println("enter or nottttt sendregmail");
//			MimeMessage mimeMessage = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				String text = "Congratulations Your Registration is successful for the Practitioner Regulation.Now,You can Login with your credentials : "
//						+ "username - "+user
//						+ "<br>password - "+pass;
//				String note = "";
////				String uname ="xyz";
////				String pass ="xyz";
//				html.setHTML(text, note);
//				String htmlMsg = html.getHTML();
//				helper.setText(htmlMsg, true);
//				helper.setTo(email);
//				helper.setSubject("Practitioner Registration is Successfull");
//				/* helper.setFrom("abc@gmail.com"); */
//				mailSender.send(mimeMessage);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//		}
//	}

}
