package com.AyushEdu.dao.Counselling_Institute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype;
import com.AyushEdu.Models.Counselling_Institute.CoInstituteotherdetail;
import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDegreeCateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;

@Service
@Repository
public class InstituteMasterDaoImpl implements InstituteMasterDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<EduLmsCountryMstr> GetCountry() {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsCountryMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsCountryMstr where status = '1' order by name");

			list = (List<EduLmsCountryMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsStateMstr> getStateData(int countryid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsStateMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsStateMstr where status = '1' and countryId.id=:countryId order by stateName");
			q1.setParameter("countryId", countryid);
			list = (List<EduLmsStateMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsDistrictMstr> getDistrictData(int countryid, int stateid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsDistrictMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsDistrictMstr where status = '1' and countryId.id=:countryId and stateId.stateId = :state_id order by districtName");
			q1.setParameter("countryId", countryid);
			q1.setParameter("state_id", stateid);
			list = (List<EduLmsDistrictMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsSystemMstr> GetSystemType() {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsSystemMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsSystemMstr where status = '1' order by system_name");

			list = (List<EduLmsSystemMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsUniversityMstr> GetUniversity() {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsUniversityMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsUniversityMstr where status = '1' order by university_name");

			list = (List<EduLmsUniversityMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List CheckInstituteCodeExist(String inscode, int systemtypeint, int universityint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where status = '1' and code=:code  and systemId.id=:systemId and universityId.id=:universityId");
			q1.setParameter("systemId", systemtypeint);
			q1.setParameter("universityId", universityint);
			q1.setParameter("code", inscode);
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List CheckInstiuteNameExist(String insname, int systemtypeint, int universityint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where status = '1' and instituteName=:instituteName  and systemId.id=:systemId and universityId.id=:universityId group by instituteName");
			q1.setParameter("systemId", systemtypeint);
			q1.setParameter("universityId", universityint);
			q1.setParameter("instituteName", insname);
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public EduLmsInstituteReg GetInstituteDataByID(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		String comtype = "";
		
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsInstituteReg where id = :id  and app_status = '1'");
			q1.setParameter("id", id);

			eduLmsInstituteReg = (EduLmsInstituteReg) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return eduLmsInstituteReg;
	}

	@Override
	public EduLmsInstituteReg GetInstituteDataByIDForActivateORDeactivate(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsInstituteReg where id = :id order by id desc");
			q1.setParameter("id", id);
			q1.setMaxResults(1);
			eduLmsInstituteReg = (EduLmsInstituteReg) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return eduLmsInstituteReg;
	}

	@Override
	public void SaveInstituteData(EduLmsInstituteReg eduLmsInstituteReg, String actiontype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			if (actiontype.equalsIgnoreCase("add")) {
				sessionHQL.save(eduLmsInstituteReg);
			} else {
				sessionHQL.update(eduLmsInstituteReg);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

	}

	@Override
	public String GETCollgegeUniqueIDwithIncrement(int systemtypeint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		String collegeuniqueid = "";
		try {

			String q = "select distinct sm.system_abbr||lpad(case  when max(SubString(college_unique_id,4,4)) is null\n"
					+ "or max(SubString(college_unique_id,4,4))=''\n"
					+ "then '1' else max(SubString(college_unique_id,4,4)) :::: int+1 end :::: text,4,'0') \n"
					+ "as serial_col from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=ir.system_id where system_id=" + systemtypeint
					+ " group by sm.system_abbr ";
			Query q1 = sessionHQL.createSQLQuery(q);
			// q1.setParameter("system_id", systemtypeint);
			System.out.println("wqd" + q1.getQueryString());
			collegeuniqueid = (String) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return collegeuniqueid;
	}

	@Override
	public String GetSystemABR(int systemtypeint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		String collegeuniqueid = "";
		try {

			Query q1 = sessionHQL.createQuery("select systemAbbr From EduLmsSystemMstr where id = :id");
			q1.setParameter("id", systemtypeint);

			collegeuniqueid = (String) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return collegeuniqueid;
	}

	@Override
	public List<EduLmsInstituteReg> LoadInstituteData(String username, String data, String rolename,int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";

			String SearchValue = "";
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(systemId.systemName) like : systemName"
						+ " OR  upper(universityId.universityName) like :universityName " + " OR upper(code) like :code"
						+ " OR upper(instituteName) like :instituteName" + " OR upper(collegeAbbr) like :collegeAbbr"
						+ " OR upper(collegeUniqueId) like :collegeUniqueId"
						+ " OR upper(instituteMobNo) like :instituteMobNo"
						+ " OR upper(instituteEmail) like :instituteEmail"
						+ " OR upper(countryId.name) like :countryname" + " OR upper(stateId.stateName) like :stateName"
						+ " OR upper(districtId.districtName) like :districtName" + " OR upper(address) like :address"

						+ ")";

			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "systemId.systemName";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "universityId.universityName";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "code";
			}
			if (orderColunm.equalsIgnoreCase("5")) {
				orderColunm = "instituteName";
			}

			if (orderColunm.equalsIgnoreCase("7")) {
				orderColunm = "collegeAbbr";
			}
			if (orderColunm.equalsIgnoreCase("8")) {
				orderColunm = "collegeUniqueId";
			}
			if (orderColunm.equalsIgnoreCase("9")) {
				orderColunm = "instituteMobNo";
			}
			if (orderColunm.equalsIgnoreCase("10")) {
				orderColunm = "instituteEmail";
			}
			if (orderColunm.equalsIgnoreCase("11")) {
				orderColunm = "countryId.name";
			}
			if (orderColunm.equalsIgnoreCase("12")) {
				orderColunm = "stateId.stateName";
			}
			if (orderColunm.equalsIgnoreCase("13")) {
				orderColunm = "districtId.districtName";
			}
			if (orderColunm.equalsIgnoreCase("14")) {
				orderColunm = "address";
			}
			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
//			Query q1 = sessionHQL.createQuery("From EduLmsInstituteReg where  createdBy = :createby and status = '1' "
			Query q1 = null;
			 q1 = sessionHQL.createQuery("From UserLogin where userId = :userId  and enabled = 1");
			q1.setParameter("userId", userid);

			UserLogin userlogin = (UserLogin) q1.uniqueResult();
				q1 = sessionHQL.createQuery("From EduLmsInstituteReg where  id = :createby and app_status = '1' "
						+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);
				q1.setParameter("createby", userlogin.getInstitute_id());
			
			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));

			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();

				q1.setParameter("systemName", '%' + search + '%');
				q1.setParameter("universityName", '%' + search + '%');
				q1.setParameter("code", '%' + search + '%');
				q1.setParameter("instituteName", '%' + search + '%');
				q1.setParameter("collegeAbbr", '%' + search + '%');
				q1.setParameter("collegeUniqueId", '%' + search + '%');
				q1.setParameter("instituteMobNo", '%' + search + '%');
//				
				q1.setParameter("instituteEmail", '%' + search + '%');
				q1.setParameter("countryname", '%' + search + '%');
				q1.setParameter("stateName", '%' + search + '%');
				q1.setParameter("districtName", '%' + search + '%');
				q1.setParameter("address", '%' + search + '%');

			}

			System.out.println("Q1" + q1.getQueryString());
			list = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsInstituteReg> LoadInstituteDataForCount(String username, String rolename,int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			Query q1 = null;
//			if (rolename.equalsIgnoreCase("Administrator_Councelling_NCISM")) {
//				q1 = sessionHQL.createQuery("From EduLmsInstituteReg where    status = '1' order by instituteName");
//
//			} else {
			System.err.println("------username "+username);
			System.err.println("------userid "+userid);
		
			 q1 = sessionHQL.createQuery("From UserLogin where userId = :userId  and enabled = 1");
			q1.setParameter("userId", userid);
				
				UserLogin userlogin = (UserLogin) q1.uniqueResult();
				q1 = sessionHQL.createQuery("From EduLmsInstituteReg where  id = :createby and status = '1' ");
				q1.setParameter("createby", userlogin.getInstitute_id());
			//}
			System.out.println("Q1" + q1.getQueryString());
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsInstituteReg> LoadInstituteDataForState(int stateid) {
		// TODO Auto-generated method stub\
		System.out.println("stateid" + stateid);
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where  stateId.stateId = :stateId and status = '1'  order by instituteName");

			q1.setParameter("stateId", stateid);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;

	}

	@Override
	public List<EduLmsUniversityMstr> getUniversityList() {
		// TODO Auto-generated method stub

		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsUniversityMstr> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();

		try {

			Query q1 = sessionHQL
					.createQuery("select distinct id, universityName from EduLmsUniversityMstr order by id ");

			System.out.println("Q1" + q1.getQueryString());
			list = (List<EduLmsUniversityMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List CheckInstiuteMobileExist(String instituteMobNo, int systemtypeint, int universityint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where status = '1' and instituteMobNo=:instituteMobNo  and systemId.id=:systemId and universityId.id=:universityId");
			q1.setParameter("systemId", systemtypeint);
			q1.setParameter("universityId", universityint);
			q1.setParameter("instituteMobNo", instituteMobNo);
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List CheckInstiuteEmailExist(String instituteEmail, int systemtypeint, int universityint) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where status = '1' and instituteEmail=:instituteEmail  and systemId.id=:systemId and universityId.id=:universityId");
			q1.setParameter("systemId", systemtypeint);
			q1.setParameter("universityId", universityint);
			q1.setParameter("instituteEmail", instituteEmail);
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public void SaveInstituteSignUpData(EduLmsInstituteReg eduLmsInstituteReg, String actiontype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			if (actiontype.equalsIgnoreCase("add")) {

				Query q1 = sessionHQL.createSQLQuery(
						"select role_id From roleinformation where  lower(role)='institute_councelling'");
				int roleid = (int) q1.uniqueResult();
				// list = (List<TbLmsCategoryMstr>) q1.list();

				UserLogin userLogin = new UserLogin();
				userLogin.setUserName(eduLmsInstituteReg.getInstituteMobNo());
				// String pasword = generateCommonLangPassword();
				String pasword = "123Bisag#";
				System.out.println("pasword" + pasword);
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(pasword);
				userLogin.setPassword(encodedPassword);
				userLogin.setEnabled(1);
				userLogin.setAccountNonExpired(0);
				userLogin.setAccountNonLocked(1);
				userLogin.setCredentialsNonExpired(1);
				userLogin.setLogin_name(eduLmsInstituteReg.getInstituteMobNo());
				userLogin.setCreated_by(eduLmsInstituteReg.getInstituteMobNo() + "");
				userLogin.setCreated_on(new Date());

				sessionHQL.save(userLogin);

				UserRole userRole = new UserRole();
				userRole.setUserId(userLogin.getUserId());

				userRole.setRoleId(roleid);
				sessionHQL.save(userRole);

				eduLmsInstituteReg.setCreatedBy(userLogin.getUserName());
				sessionHQL.save(eduLmsInstituteReg);
			} else {
				sessionHQL.update(eduLmsInstituteReg);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

	}

	@Override
	public EduLmsInstituteReg getInstitituteDataFromUserName(String username) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EduLmsInstituteReg where status = '1' and createdBy =:createdBy order by id desc");
			q1.setParameter("createdBy", username);
			q1.setMaxResults(1);
			eduLmsInstituteReg = (EduLmsInstituteReg) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return eduLmsInstituteReg;
	}

	@Override
	public EduLmsInstituteReg GetInstituteDataByUsername(String username) {
		// TODO Auto-generated method stub
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsInstituteReg eduLmsInstituteReg = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From UserLogin where userId = :userId  and enabled = 1");
			q1.setParameter("userId", Integer.parseInt(username));

			UserLogin userlogin = (UserLogin) q1.uniqueResult();
			
			int insid = userlogin.getInstitute_id();
			System.err.println("\n\n Institute ID-------------------------"+insid);
			 q1 = sessionHQL.createQuery("From EduLmsInstituteReg where id = :id  and app_status = '1'");
			q1.setParameter("id", insid);

			eduLmsInstituteReg = (EduLmsInstituteReg) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return eduLmsInstituteReg;
	}

	@Override
	public List<CoFeescategorytype> GetFees() {
		// TODO Auto-generated method stub
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoFeescategorytype> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From CoFeescategorytype where status = '1' order by ftid");

			list = (List<CoFeescategorytype>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public void saveInstituteOtherDetail(CoInstituteotherdetail coInstituteotherdetails) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			sessionHQL.save(coInstituteotherdetails);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@Override
	public void DeleteInstituteOtherDetail(CoInstituteotherdetail coInstituteotherdetails) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			sessionHQL.update(coInstituteotherdetails);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@Override
	public List<CoInstituteotherdetail> GetInstituteOTHERDataByID(int insid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoInstituteotherdetail> coInstituteotherdetails = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoInstituteotherdetail where inid.id = :inid  and inid.status = '1' and status = '1'  order by catid");
			q1.setParameter("inid", insid);

			coInstituteotherdetails = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coInstituteotherdetails;
	}

	@Override
	public boolean CheckInstituteOtherDetailAlreadyExist(int insid, int feesid, int categoryid, String fees_sub_cat) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoInstituteotherdetail> list = null;
		boolean result = true;
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoInstituteotherdetail where inid.id = :inid and inid.status = '1' and status = '1' and feesid.ftid = :feesid and catid.id = :catid "
					+ "and fees_sub_cat=:fees_sub_cat");
			q1.setParameter("inid", insid);
			q1.setParameter("feesid", feesid);
			q1.setParameter("catid", categoryid);
			q1.setParameter("fees_sub_cat", fees_sub_cat);

			list = (List<CoInstituteotherdetail>) q1.list();
			if (list != null) {
				if (list.size() > 0) {
					result = false;
				}
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return result;
	}

	@Override
	public List<EduLmsInstituteReg> LoadInstituteDataGeneral(String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";

			String SearchValue = "";
			int systemtype = 0;
			String insid = "";
			List<Integer> insidaar = new ArrayList<Integer>();
			if (jsonObject.get("insid") != null && jsonObject.get("insid") != "") {
				insid = jsonObject.get("insid").toString();
				String insidarr[] = insid.split(",");
				for (int i = 0; i < insidarr.length; i++) {
					insidaar.add(Integer.parseInt(insidarr[i]));
				}

			}
			if (jsonObject.get("systemtype") != null && jsonObject.get("systemtype") != "") {

				if (!jsonObject.get("systemtype").toString().equalsIgnoreCase("All")) {
					systemtype = Integer.parseInt(jsonObject.get("systemtype").toString());
				}

			}
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(systemId.systemName) like : systemName"
						+ " OR  upper(universityId.universityName) like :universityName " + " OR upper(code) like :code"
						+ " OR upper(instituteName) like :instituteName" + " OR upper(collegeAbbr) like :collegeAbbr"
						+ " OR upper(collegeUniqueId) like :collegeUniqueId"
						+ " OR upper(instituteMobNo) like :instituteMobNo"
						+ " OR upper(instituteEmail) like :instituteEmail"
						+ " OR upper(countryId.name) like :countryname" + " OR upper(stateId.stateName) like :stateName"
						+ " OR upper(districtId.districtName) like :districtName" + " OR upper(address) like :address"

						+ ")";
				if (insid != "") {

					SearchValue += " and id in (:insid)";

				}

			} else {
				if (systemtype != 0) {

					SearchValue += " and systemId.id = :systemId";

				}
				if (insid != "") {
					SearchValue += " and id in (:insid)";

				}

			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "systemId.systemName";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "universityId.universityName";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "code";
			}
			if (orderColunm.equalsIgnoreCase("5")) {
				orderColunm = "instituteName";
			}

			if (orderColunm.equalsIgnoreCase("7")) {
				orderColunm = "collegeAbbr";
			}
			if (orderColunm.equalsIgnoreCase("8")) {
				orderColunm = "collegeUniqueId";
			}
			if (orderColunm.equalsIgnoreCase("9")) {
				orderColunm = "instituteMobNo";
			}
			if (orderColunm.equalsIgnoreCase("10")) {
				orderColunm = "instituteEmail";
			}
			if (orderColunm.equalsIgnoreCase("11")) {
				orderColunm = "countryId.name";
			}
			if (orderColunm.equalsIgnoreCase("12")) {
				orderColunm = "stateId.stateName";
			}
			if (orderColunm.equalsIgnoreCase("13")) {
				orderColunm = "districtId.districtName";
			}
			if (orderColunm.equalsIgnoreCase("14")) {
				orderColunm = "address";
			}
			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
//			Query q1 = sessionHQL.createQuery("From EduLmsInstituteReg where  createdBy = :createby and status = '1' "
			Query q1 = null;
			if (insid != "") {
				q1 = sessionHQL.createQuery("From EduLmsInstituteReg where status = '1' " + SearchValue + " ORDER BY "
						+ orderColunm + " " + orderType);
			} else {
				q1 = sessionHQL.createQuery(
						"From EduLmsInstituteReg where status = '1' and id in (select distinct(insid.id) From CoSeatallocationmatrix where  status = '1' and insid.status = '1') "
								+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);
			}
			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));

			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();

				q1.setParameter("systemName", '%' + search + '%');
				q1.setParameter("universityName", '%' + search + '%');
				q1.setParameter("code", '%' + search + '%');
				q1.setParameter("instituteName", '%' + search + '%');
				q1.setParameter("collegeAbbr", '%' + search + '%');
				q1.setParameter("collegeUniqueId", '%' + search + '%');
				q1.setParameter("instituteMobNo", '%' + search + '%');
//				
				q1.setParameter("instituteEmail", '%' + search + '%');
				q1.setParameter("countryname", '%' + search + '%');
				q1.setParameter("stateName", '%' + search + '%');
				q1.setParameter("districtName", '%' + search + '%');
				q1.setParameter("address", '%' + search + '%');
				if (insid != "") {

					q1.setParameter("insid", insidaar);
				}
			} else {
				if (systemtype != 0) {

					q1.setParameter("systemId", systemtype);
				}
				if (insid != "") {
					q1.setParameter("insid", insidaar);
				}
			}

			System.out.println("Q1" + q1.getQueryString());
			list = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<EduLmsInstituteReg> LoadInstituteDataForCountGeneral(String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			Query q1 = null;
			q1 = sessionHQL.createQuery("From EduLmsInstituteReg where status = '1' order by instituteName");

			System.out.println("Q1" + q1.getQueryString());
			list = (List<EduLmsInstituteReg>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public int GetTotalSeatsOFInstitute(Integer id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EduLmsInstituteReg> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int totalseats = 0;
		try {
			CoInstituteotherdetail coInstituteotherdetail  = null;
			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"From CoInstituteotherdetail where    status = '1' and inid.id =:inid order by infid desc");
			q1.setParameter("inid", id);
			System.out.println("Q1" + q1.getQueryString());
			q1.setMaxResults(1);
			coInstituteotherdetail = (CoInstituteotherdetail)q1.uniqueResult();
			
			if(coInstituteotherdetail != null) {
				totalseats = coInstituteotherdetail.getTotalseat();
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return totalseats;
	}

	@Override
	public EduLmsDegreeCateMstr GetIdDegreeType(String string) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EduLmsDegreeCateMstr eduLmsDegreeCateMstr = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsDegreeCateMstr where degreeCate = :degreeCate  and status = '1'");
			q1.setParameter("degreeCate", string);

			eduLmsDegreeCateMstr = (EduLmsDegreeCateMstr) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return eduLmsDegreeCateMstr;
	}

	
	@Override
	public List<TbLmsCategoryMstr> GetCategoryType() {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<TbLmsCategoryMstr> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From TbLmsCategoryMstr where status = '1'");

			list = (List<TbLmsCategoryMstr>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}
	
	
}
