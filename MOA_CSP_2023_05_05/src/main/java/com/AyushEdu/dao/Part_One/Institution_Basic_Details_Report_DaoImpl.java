package com.AyushEdu.dao.Part_One;

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
import org.springframework.stereotype.Service;

@Service
@Repository
public class Institution_Basic_Details_Report_DaoImpl implements Institution_Basic_Details_Report_Dao {
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> getAllPersdetailsReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq1 = "";
		try {
			conn = dataSource.getConnection();
//			if (role.equals("NCH")) {
//			sq1 = "select bd.id as mainid,TO_CHAR(bd.dop_state_govn , 'DD/MM/YYYY') as date_of_permission,"
//					+ "TO_CHAR(bd.dop_central_govn , 'DD/MM/YYYY') as date_of_central,TO_CHAR(bd.doa_university , 'DD/MM/YYYY') as date_of_first_affilia,"
//					+ "TO_CHAR(bd.doc_affilation_university , 'DD/MM/YYYY') as date_of_consent_affilia,"
//					+ "TO_CHAR(bd.doc_last_aff_university , 'DD/MM/YYYY') as doc_last_aff_universityd,bd.*,sm.state_name\n"
//					+ " from clg_reg_inst_info_institution_basic_details bd \n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=bd.state_id \n"
//					+ "where bd.id=? ";
//			}
//			if (role.equals("Institute_NCH")) {
				sq1 = "select bd.id as mainid,TO_CHAR(bd.dop_state_govn , 'DD/MM/YYYY') as date_of_permission,"
						+ "TO_CHAR(bd.dop_central_govn , 'DD/MM/YYYY') as date_of_central,TO_CHAR(bd.doa_university , 'DD/MM/YYYY') as date_of_first_affilia,"
						+ "TO_CHAR(bd.doc_affilation_university , 'DD/MM/YYYY') as date_of_consent_affilia,"
						+ "TO_CHAR(bd.doc_last_aff_university , 'DD/MM/YYYY') as doc_last_aff_universityd,bd.*,sm.state_name,dm.district_name\n"
						+ " from clg_reg_inst_info_institution_basic_details bd \n"
						+ "left join edu_lms_state_mstr sm on sm.state_id=bd.state_id \n"
						+ "left join edu_lms_district_mstr dm on dm.district_id=bd.district_inst_add or dm.district_id=bd.mngt_district\n"
						+ "where inst_id=? ";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq1);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
//			stmt.setInt(1, inst_id);
			System.err.println("STMT--getAllPersdetailsReport---" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> DataTableSearch_Basic_InfoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String inst_code, String inst_state, String inst_city, String inst_pincode,
			String inst_mo_no, String inst_email, String institution_type, String managing_body,
			String management_contact, String name_of_society, String mng_state, String mng_city, String mng_mo_no,
			String mng_email, String s_registration_no, String university_affiliated, String role, String userid,
			String institute_id,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, inst_code, inst_state, inst_city, inst_pincode, inst_mo_no,
				inst_email, institution_type, managing_body, management_contact, name_of_society, mng_state, mng_city,
				mng_mo_no, mng_email, s_registration_no, university_affiliated, role, userid, institute_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String q1 = "";
		try {
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			
//			q="select sm.state_name,p.city,p.pincode,p.mobile_no,p.email_id,p.institute_type,p.name_of_managing_body,p.name_of_management_contact,\n"
//					+ "p.mngt_city,p.mngt_mobile_no,p.mngt_email_id,p.name_of_society,p.society_reg_no,p.name_of_uni_affilate\n"
//					+ "from clg_reg_inst_info_institution_basic_details p\n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
//					+ "inner join logininformation lo on lo.userid=p.userid\n"
//					+ "where p.id!=0 and p.inst_id="+institute_id+" and p.userid="+userid+"  "+ SearchValue +" order by id " + orderType
//					+ 	 " limit " + pageL + " OFFSET " + startPage;

			if (role.equals("NCH")) {

				q = "select p.*,sm.state_name,ir.code\n" + "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0  and p.status=? " + SearchValue
						+ " order by p.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			}

			if (role.equals("Institute_NCH")) {

				q = "select p.*,sm.state_name,ir.code\n" + "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0 and p.inst_id="
						+ institute_id + " and p.userid=" + userid + "  " + SearchValue + " order by p.id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			if (role.equals("NCH")) {
			stmt.setInt(1, Integer.parseInt(status));
			}
			stmt = setQueryWhereClause_SQL(stmt, Search, inst_code, inst_state, inst_city, inst_pincode, inst_mo_no, inst_email,
					institution_type, managing_body, management_contact, name_of_society, mng_state, mng_city,
					mng_mo_no, mng_email, s_registration_no, university_affiliated, role, userid, institute_id);


			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int j = startPage;
			int countFunction = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "<li><a class='main-btn dark-btn btn-hover btn-sm EnableData' value='ENABLE'  title='Emable To Edit Data' >" + // id='id_add_attHospital1'
				"<i class='lni lni-eye'></i></a> </li>" + "<input type='hidden' id='EnableId" + countFunction
				+ "' value=" + rs.getString("id") + "></i></a> </li>";
				String action = "";
				String f1 ="<li><a class='main-btn success-btn btn-hover btn-sm ApproveData' value='APPROVE'  title='Approve Data' >" + // id='id_add_attHospital1'
				"<i class='lni lni-checkmark'></i></a> </li>" + "<input type='hidden' id='ApproveId" + countFunction
				+ "' value=" + rs.getString("id") + "></i></a> </li>";
				
				String f2 = "";
				String f3 = "<li><a class='main-btn danger-btn btn-hover btn-sm RejectData' value='REJECT'  title='Reject Data' >" + // id='id_add_attHospital1'
				"<i class='lni lni-close'></i></a> </li>" + "<input type='hidden' id='RejectId" + countFunction
				+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f2 = "<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-eye'></i></a> </li>" + "<input type='hidden' id='viewId" + countFunction
						+ "' value=" + rs.getString("id") + "><input type='hidden' id='instituteId"+countFunction+"' value="+rs.getString("inst_id")+"></i></a> </li>";
				ul +=  f1 + " " + " " + f2+" "+f3;
				ul += "</ul>";
				countFunction += 1;
				action = ul;
				columns.put("action", action);

//				alist.add(rs.getString("id")); //0

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

	public long DataTableSearch_Basic_InfoDataTotalCount(String Search, String inst_code, String inst_state, String inst_city,
			String inst_pincode, String inst_mo_no, String inst_email, String institution_type, String managing_body,
			String management_contact, String name_of_society, String mng_state, String mng_city, String mng_mo_no,
			String mng_email, String s_registration_no, String university_affiliated, String role, String userid,
			String institute_id,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, inst_code, inst_state, inst_city, inst_pincode, inst_mo_no,
				inst_email, institution_type, managing_body, management_contact, name_of_society, mng_state, mng_city,
				mng_mo_no, mng_email, s_registration_no, university_affiliated, role, userid, institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q="select count(*) from (select sm.state_name,p.city,p.pincode,p.mobile_no,p.email_id,p.institute_type,p.name_of_managing_body,p.name_of_management_contact,\n"
//					+ "p.mngt_city,p.mngt_mobile_no,p.mngt_email_id,p.name_of_society,p.society_reg_no,p.name_of_uni_affilate\n"
//					+ "from clg_reg_inst_info_institution_basic_details p\n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
//					+ "inner join logininformation lo on lo.userid=p.userid\n"
//					+ "where p.id!=0 and p.inst_id="+institute_id+" and p.userid= "+userid+"  "+ SearchValue +")ab";
			if (role.equals("NCH")) {

				q = "select count(*) from (select p.*,sm.state_name,ir.code\n"
						+ "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0  and p.status=? " + SearchValue
						+ ")ab";
			}
			if (role.equals("Institute_NCH")) {
				q = "select count(*) from (select p.*,sm.state_name,ir.code\n"
						+ "from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
						+ "inner join logininformation lo on lo.userid=p.userid\n" + "where p.id!=0 and p.inst_id="
						+ institute_id + " and p.userid= " + userid + "  " + SearchValue + ")ab";
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			if (role.equals("NCH")) {
				stmt.setInt(1, Integer.parseInt(status));
				}
			stmt = setQueryWhereClause_SQL(stmt, Search, inst_code, inst_state, inst_city, inst_pincode, inst_mo_no, inst_email,
					institution_type, managing_body, management_contact, name_of_society, mng_state, mng_city,
					mng_mo_no, mng_email, s_registration_no, university_affiliated, role, userid, institute_id);
			System.err.println("stmt===============count" + stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String inst_code, String inst_state, String inst_city, String inst_pincode,
			String inst_mo_no, String inst_email, String institution_type, String managing_body,
			String management_contact, String name_of_society, String mng_state, String mng_city, String mng_mo_no,
			String mng_email, String s_registration_no, String university_affiliated, String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  ir.code like ? or sm.state_name like ? or upper(p.city) like ? or cast(p.pincode as varchar) like ? or cast(p.mobile_no as varchar) like ?"
					+ "or p.email_id like ? or cast(p.institute_type as varchar) like ? or upper(p.name_of_managing_body) like ? or upper(p.name_of_management_contact) like ?"
					+ "or upper(p.name_of_society) like ? or cast(p.mngt_mobile_no as varchar) like ?  or p.mngt_email_id like ? "
					+ "or upper(p.society_reg_no) like ? or upper(p.name_of_uni_affilate) like ?) "
//					+ "or to_char(sp.date_of_reg , 'DD-MM-YYYY') like ? "
					+ "";
//			or (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) like ?";	
		}

		/// advance search

		if (!inst_code.trim().equals("")) {
			SearchValue += " and upper(ir.code) like ? "; // 2
		}
		if (!inst_state.trim().equals("0")) {
			SearchValue += " and upper(sm.state_id::text) like ? "; // 1
		}
		if (!inst_city.trim().equals("")) {
			SearchValue += " and upper(p.city) like ? "; // 2
		}
		if (!inst_pincode.trim().equals("")) {
			SearchValue += " and (p.pincode) = ? "; // 3
		}
		if (!inst_mo_no.trim().equals("")) {
			SearchValue += " and cast(p.mobile_no as varchar) like ? "; // 4
		}
		if (!inst_email.trim().equals("")) {
			SearchValue += " and p.email_id like ? "; // 5
		}
		if (!institution_type.trim().equals("0")) {
			SearchValue += " and p.institute_type = ? "; // 6
		}
		if (!managing_body.trim().equals("")) {
			SearchValue += " and upper(p.name_of_managing_body) like ? "; // 7
		}
		if (!management_contact.trim().equals("")) {
			SearchValue += " and upper(p.name_of_management_contact) like ? "; // 8
		}
		if (!name_of_society.trim().equals("")) {
			SearchValue += " and upper(p.name_of_society) like ? "; // 9
		}
//		if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")){
//			SearchValue += " and to_char(sp.date_of_birth,'DD/MM/YYYY')=?";
//		}
		if (!mng_state.trim().equals("0")) {
			SearchValue += " and upper(sm.state_id::text) like ? "; // 10
		}
		if (!mng_city.trim().equals("")) {
			SearchValue += " and upper(p.mngt_city) like ? "; // 11
		}
		if (!mng_mo_no.trim().equals("")) {
			SearchValue += " and cast(p.mngt_mobile_no as varchar) like ? "; // 12
		}
		if (!mng_email.trim().equals("")) {
			SearchValue += " and upper(p.mngt_email_id) like ? "; // 13
		}
		if (!s_registration_no.trim().equals("")) {
			SearchValue += " and upper(p.society_reg_no) like ? "; // 14
		}
		if (!university_affiliated.trim().equals("")) {
			SearchValue += " and upper(p.name_of_uni_affilate) like ? "; // 15
		}

//		if (!yr_of_exp.trim().equals("")) {
//			SearchValue += " and (round(abs(mp.from_date :: date - mp.to_date :: date)/365.25,1)::int) = ? ";
//
//		}
//		if(!institute.equals("0")) {
//			SearchValue += " and lo.institute_id = ? ";
//			}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String inst_code, String inst_state,
			String inst_city, String inst_pincode, String inst_mo_no, String inst_email, String institution_type,
			String managing_body, String management_contact, String name_of_society, String mng_state, String mng_city,
			String mng_mo_no, String mng_email, String s_registration_no, String university_affiliated, String role,
			String userid, String institute_id) {

		int flag = 1;
		try {

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 0
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 6
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 7
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 8
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 9
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 10
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 11
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 12
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 13
			}

			if (!inst_code.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + inst_code.toUpperCase() + "%"); // 2
			}
			if (!inst_state.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, "%" + inst_state.toUpperCase() + "%"); // 1
			}
			if (!inst_city.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + inst_city.toUpperCase() + "%"); // 2
			}
			if (!inst_pincode.trim().equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(inst_pincode)); // 3
			}
			if (!inst_mo_no.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, (inst_mo_no)); // 4
			}
			if (!inst_email.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + inst_email + "%"); // 5
			}
//			if (!date_of_birth.equals("") && date_of_birth != "" && !date_of_birth.equals("DD/MM/YYYY")) {
//				flag += 1;
//				stmt.setString(flag, date_of_birth);
//			}
			if (!institution_type.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institution_type)); // 6
			}
			if (!managing_body.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + managing_body.toUpperCase() + "%"); // 7
			}
			if (!management_contact.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + management_contact.toUpperCase() + "%"); // 8
			}
			if (!name_of_society.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + name_of_society.toUpperCase() + "%"); // 9
			}
			if (!mng_state.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, "%" + mng_state.toUpperCase() + "%"); // 10
			}
			if (!mng_city.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + mng_city.toUpperCase() + "%"); // 11
			}
			if (!mng_mo_no.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, (mng_mo_no)); // 4
			}
			if (!mng_email.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + mng_email.toUpperCase() + "%"); // 12
			}
			if (!s_registration_no.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + s_registration_no.toUpperCase() + "%"); // 13
			}
			if (!university_affiliated.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + university_affiliated.toUpperCase() + "%"); // 14
			}

//			if (!yr_of_exp.trim().equals("")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(yr_of_exp));
//			}
//			if(!institute.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag,Integer.parseInt( institute));
//
//				}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public List<Map<String, Object>> getAllinfo_connectivityReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

//			if (role.equals("NCH")) {
//				sq2 = "select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select ic.id as info_conn_mainid,ic.*,sm.state_name,dm.district_name from clg_reg_inst_info_information_of_connectivity ic\n"
						+ "left join edu_lms_state_mstr sm on ic.other_state=sm.state_id\n"
						+ "left join edu_lms_district_mstr dm on ic.other_district=dm.district_id\n"
						+ " where inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT--getAllinfo_connectivityReport---" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllinfo_police_stReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//			if (role.equals("NCH")) {
//				sq2 = "select ps.*,sm.state_name from   clg_reg_inst_info_police_station_details ps\n"
//						+ "inner join edu_lms_state_mstr sm on sm.state_id=ps.state\n"
//						+ " where p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select ps.*,sm.state_name,dm.district_name from   clg_reg_inst_info_police_station_details ps\n "
						+ "left join edu_lms_state_mstr sm on sm.state_id=ps.state\n"
						+ "left join edu_lms_district_mstr dm on ps.district_id=dm.district_id\n"
						+ "where ps.inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			
			System.err.println("STMT--getAllinfo_police_stReport---" + stmt);

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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllinfo_inst_dtlReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//			if (role.equals("NCH")) {
//			 sq2 = "select TO_CHAR(bd.dob , 'DD/MM/YYYY') as date_of_birth,TO_CHAR(bd.date_of_join_princi , 'DD/MM/YYYY') as date_of_join,bd.*,sm.state_name "
//					+ "from   clg_reg_inst_info_head_of_institution_details bd "
//					+ "inner join edu_lms_state_mstr sm on bd.state_reg=sm.state_id "
//					+ " where bd.p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select TO_CHAR(bd.dob , 'DD/MM/YYYY') as date_of_birth,TO_CHAR(bd.date_of_join_princi , 'DD/MM/YYYY') as date_of_join,bd.*,sm.state_name "
						+ "from   clg_reg_inst_info_head_of_institution_details bd "
						+ "inner join edu_lms_state_mstr sm on bd.state_reg=sm.state_id "
						+ " where bd.inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
//			stmt.setInt(1, inst_id);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT----getAllinfo_inst_dtlReport-" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllinfo_dtl_landReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

//			if (role.equals("NCH")) {
//			 sq2 = "select ic.id as info_conn_mainid,* from clg_reg_inst_info_details_of_land ic where ic.p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select ic.id as info_conn_mainid,* from clg_reg_inst_info_details_of_land ic where inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
//			stmt.setInt(1, inst_id);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}

			// String sq2="select TO_CHAR(bd.dob , 'DD/MM/YYYY') as
			// date_of_birth,TO_CHAR(bd.date_of_join_princi , 'DD/MM/YYYY') as
			// date_of_join,* from clg_reg_inst_info_head_of_institution_details bd where
			// inst_id=?";

			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getAllinfo_dtl_landReport--" + stmt);
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
		}
		return list;

	}

	@Override
	public List<Map<String, Object>> getInstnameReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//			if (role.equals("NCH")) {
//				sq2 = "select * from clg_reg_inst_info_institution_basic_details p\n"
//						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id  where p.id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select * from clg_reg_inst_info_institution_basic_details p\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=p.inst_id  where p.inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllinfo_undertaling_repoReport(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select ic.id as info_conn_mainid,* from clg_reg_inst_info_undertaking_reports ic where p_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;

	}

	@Override
	public List<Map<String, Object>> getAllinfo_quali_instReport(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 ="";
		try {
			conn = dataSource.getConnection();
			
			
//			if (role.equals("NCH")) {
//				sq2 = "select ic.*,tdm.type_of_degree from clg_reg_inst_info_head_of_institution_details_child ic "
//						+ "inner join edu_lms_type_of_degree_mstr tdm on ic.quali_type=tdm.id "
//						+ "where ic.p_id=?";
//			}
//			
//			if (role.equals("Institute_NCH")) {
				sq2 = "select ic.*,tdm.type_of_degree from clg_reg_inst_info_head_of_institution_details_child ic "
						+ "inner join edu_lms_type_of_degree_mstr tdm on ic.quali_type=tdm.id "
						+ "where ic.inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getInstDetailReport(int id, int inst_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("Institute_NCH")) {

			sq2 = "select p.*,sm.state_name\n" + "from clg_reg_inst_info_institution_basic_details p\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id=p.state_id\n"
					+ "inner join logininformation lo on lo.userid=p.userid\n"
					+ "where p.id!=0 and p.inst_id=? and p.userid=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

//	        if(role.equals("NCH")) {
//	        	stmt.setInt(1, id);
//	        }
//	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, inst_id);
			stmt.setString(2, userid);
//	        }
			System.err.println("stmttttt" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getPidfromInstidReport(int id, int inst_id, String role, String userid,
			String table_name,String fieldname) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		String s = "";
		try {
			conn = dataSource.getConnection();

			if (role.equals("Institute_NCH")) {
				s = " where $fieldname=? ";
				s = s.replace("$fieldname", fieldname);
			}
//			if (role.equals("NCH")) {
//				s = " where $fieldname=? ";
//				s = s.replace("$fieldname", fieldname);
//			}
			sq2 = "select id from $table " + s;
			sq2 = sq2.replace("$table", table_name);
			PreparedStatement stmt = conn.prepareStatement(sq2);

			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
//			if (role.equals("NCH")) {
//				stmt.setInt(1, id);
//			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getPidfromInstidReport--" + stmt);
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
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getAllinfo_intake_capacity_Report(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();
			
			
//			if (role.equals("NCH")) {
//				sq2 = "select * from clg_reg_inst_info_intake_capacity ic where p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select TO_CHAR(ic.last_stu_add_date , 'DD/MM/YYYY') as last_stu_add_datel,ic.* from clg_reg_inst_info_intake_capacity ic where inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getAllinfo_intake_capacity_Report--" + stmt);
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
		}
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> getAllinfo_intake_capacity_for_PG_Report(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

//			if (role.equals("NCH")) {
//			 sq2 = "select * from clg_reg_inst_info_intake_capacity_pg ic where p_id=?";
//			}
//			if (role.equals("Institute_NCH")) {
				sq2 = "select TO_CHAR(ic.date_stu_admitted , 'DD/MM/YYYY') as date_stu_admittedl,ic.* from clg_reg_inst_info_intake_capacity_pg ic where inst_id=?";
//			}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT--getAllinfo_intake_capacity_for_PG_Report---" + stmt);
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
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getAllinfo_UG_intake_capacity_Report(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();
			
//			if (role.equals("NCH")) {
//				sq2 = "select * from clg_reg_inst_info_course_intake_capacity_ug ic where p_id=?";
//				}
//				if (role.equals("Institute_NCH")) {
					sq2 = "select * from clg_reg_inst_info_course_intake_capacity_ug ic where inst_id=?";
//				}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getAllinfo_PG_intake_capacity_Report(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

//			String sq2 = "select * from clg_reg_inst_info_course_intake_capacity_pg ic where inst_id=?";
//
//			PreparedStatement stmt = conn.prepareStatement(sq2);
//			stmt.setInt(1, inst_id);
			
//			if (role.equals("NCH")) {
//				sq2 = "select * from clg_reg_inst_info_course_intake_capacity_pg ic where p_id=?";
//				}
//				if (role.equals("Institute_NCH")) {
					sq2 = "select * from clg_reg_inst_info_course_intake_capacity_pg ic where inst_id=?";
//				}

			PreparedStatement stmt = conn.prepareStatement(sq2);
			if (role.equals("NCH")) {
				stmt.setInt(1, inst_id);
			}
			if (role.equals("Institute_NCH")) {
				stmt.setInt(1, inst_id);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getAllinfo_PG_intake_capacity_Report--" + stmt);
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
		}
		return list;
	}
	
	//GET INSTITUTE ID FROM MAIN ID
	@Override
	public List<Map<String, Object>> getInstitute_id_from_main_id(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

//			String sq2 = "select * from clg_reg_inst_info_course_intake_capacity_pg ic where inst_id=?";
//
//			PreparedStatement stmt = conn.prepareStatement(sq2);
//			stmt.setInt(1, inst_id);
			
				sq2 = "select * from clg_reg_inst_info_institution_basic_details where id = ?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
				stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> getAllinfo_intake_cap_childView(int id, int inst_id, String role){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    String sq2="";
	    try{          
	    	conn = dataSource.getConnection();
	    	
//	    	if (role.equals("NCH")) {
//	    		sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_course_intake_capacity_child ic where p_id=?";
//	    	}
//	    	if (role.equals("Institute_NCH")) {
	    		sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_course_intake_capacity_child ic where inst_id=?";
//	    	}
	    	
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        if (role.equals("NCH")) {
	        	stmt.setInt(1, inst_id);
	        }
	        if (role.equals("Institute_NCH")) {
	        	stmt.setInt(1, inst_id);
	        }
	        
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---getAllinfo_intake_cap_childView--"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
			
	}
	
	@Override
	public List<Map<String, Object>> getStu_Details_Basic_info_Upload_Doc_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from clg_reg_student_dtl_upload_doc ic where institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			 if (role.equals("NCH")) {
		        	stmt.setInt(1, inst_id);
		        }
		        if (role.equals("Institute_NCH")) {
		        	stmt.setInt(1, inst_id);
		        }
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---getStu_Details_Basic_info_Upload_Doc_View--" + stmt);
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
		}
		return list;
	}

}
