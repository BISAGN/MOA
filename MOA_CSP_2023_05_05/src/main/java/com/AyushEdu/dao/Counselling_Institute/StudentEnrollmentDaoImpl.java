package com.AyushEdu.dao.Counselling_Institute;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
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
import com.AyushEdu.Models.Counselling_Institute.CoCommissiontype;
import com.AyushEdu.Models.Counselling_Institute.CoStudentenrollment;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;

@Service
@Repository
public class StudentEnrollmentDaoImpl implements StudentEnrollmentDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<CoCommissiontype> GetCommissionType() {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoCommissiontype> list = null;
		try {
			Query q1 = sessionHQL.createQuery("From CoCommissiontype where status = '1'");

			list = (List<CoCommissiontype>) q1.list();

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

	@Override
	public void SaveStudentEnrommentData(CoStudentenrollment coStudentenrollment, String actiontpe) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// List<TbLmsCategoryMstr> list = null;
		try {
			if (actiontpe.equalsIgnoreCase("add")) {
				if (coStudentenrollment.getPaymentstatus().equalsIgnoreCase("1")) {
					Query q1 = sessionHQL
							.createSQLQuery("select role_id From roleinformation where  lower(role)='candidate'");
					int roleid = (int) q1.uniqueResult();
					// list = (List<TbLmsCategoryMstr>) q1.list();

					UserLogin userLogin = new UserLogin();
					userLogin.setUserName(coStudentenrollment.getAadhaarnumber());
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
					userLogin.setLogin_name(coStudentenrollment.getAadhaarnumber());
					userLogin.setCreated_by(coStudentenrollment.getCreateby() + "");
					userLogin.setCreated_on(new Date());

					sessionHQL.save(userLogin);

					UserRole userRole = new UserRole();
					userRole.setUserId(userLogin.getUserId());

					userRole.setRoleId(roleid);
					sessionHQL.save(userRole);

					coStudentenrollment.setUserid(userLogin);
				}
				// coStudentenrollment.setCreateby(userLogin.getUserId());
				sessionHQL.save(coStudentenrollment);
			} else {

				if (coStudentenrollment.getUserid() == null) {
					Query q1 = sessionHQL
							.createSQLQuery("select role_id From roleinformation where  lower(role)='candidate'");
					int roleid = (int) q1.uniqueResult();
					// list = (List<TbLmsCategoryMstr>) q1.list();

					UserLogin userLogin = new UserLogin();
					userLogin.setUserName(coStudentenrollment.getAadhaarnumber());
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
					userLogin.setLogin_name(coStudentenrollment.getAadhaarnumber());
					userLogin.setCreated_by(coStudentenrollment.getCreateby() + "");
					userLogin.setCreated_on(new Date());

					sessionHQL.save(userLogin);

					UserRole userRole = new UserRole();
					userRole.setUserId(userLogin.getUserId());

					userRole.setRoleId(roleid);
					sessionHQL.save(userRole);

					coStudentenrollment.setUserid(userLogin);
				}
				sessionHQL.update(coStudentenrollment);
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

	public static String generateCommonLangPassword() {
		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(1, 35, 38, false, false);
		String totalChars = "9";
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String password = pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		System.out.println("password" + password);

//	    $2a$10$0tuU4MT6jtxqtQh5pW.h6Obe48.XWJGLmG4hymaVQRsHHUhv/legW
		return password;
	}

	@Override
	public List<CoStudentenrollment> LoadStudentEnrolledData(String yearval, int createby, String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoStudentenrollment> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";

			String SearchValue = "";
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(neetrollnumber) like : neetrollnumber"
						+ " OR  upper(applicationnumber) like :applicationnumber "
						+ " OR upper(cast(neetmarks as text))  like :neetmarks"
						+ " OR upper(cast(neetrank as text)) like :neetrank"
						+ " OR upper(cast(tenthpercentage as text)) like :tenthpercentage"
						+ " OR upper(cast(twelvthpercentage as text)) like :twelvthpercentage"
//						+ " OR upper(year) like :yearval"
						+ " OR upper(firstname) like :firstname" + " OR upper(middlename) like :middlename"
						+ " OR upper(lastname) like :lastname" + " OR upper(dob) like :dob"
						+ " OR upper(mobilenumber) like :mobilenumber" + " OR upper(emaildi) like :emaildi"
						+ " OR upper(aadhaarnumber) like :aadhaarnumber" + " "
//								+ "OR cast(commtype as text) like :commtype"
						+ " OR catid.category like :category"

						+ ")";

			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "neetrollnumber";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "applicationnumber";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "neetmarks";
			}
			if (orderColunm.equalsIgnoreCase("5")) {
				orderColunm = "neetrank";
			}
			if (orderColunm.equalsIgnoreCase("6")) {
				orderColunm = "twelvthpercentage";
			}
			if (orderColunm.equalsIgnoreCase("7")) {
				orderColunm = "tenthpercentage";
			}
			if (orderColunm.equalsIgnoreCase("8")) {
				orderColunm = "year";
			}
			if (orderColunm.equalsIgnoreCase("9")) {
				orderColunm = "firstname";
			}
			if (orderColunm.equalsIgnoreCase("10")) {
				orderColunm = "dob";
			}
			if (orderColunm.equalsIgnoreCase("11")) {
				orderColunm = "mobilenumber";
			}
			if (orderColunm.equalsIgnoreCase("12")) {
				orderColunm = "emaildi";
			}
			if (orderColunm.equalsIgnoreCase("13")) {
				orderColunm = "catid.category";
			}
			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where year = :year and createby = :createby and status = '1' "
							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

//			Query q1 = sessionHQL.createQuery(
//					"select new com.AyushEdu.seat_allocation_entity.CoStudentenrollment(commtype,neetrollnumber,applicationnumber,neetmarks,neetrank,tenthpercentage,twelvthpercentage,year,firstname,middlename,lastname,dob,mobilenumber,emaildi,aadhaarnumber,seid) From CoStudentenrollment where year = :year and createby = :createby and status = '1' "
//							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));
			q1.setParameter("createby", createby);
			q1.setParameter("year", yearval);
			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();
				System.out.println("Safgvxbfdbnrstgn-->" + search);
				q1.setParameter("neetrollnumber", '%' + search + '%');
				q1.setParameter("applicationnumber", '%' + search + '%');
				q1.setParameter("neetmarks", '%' + search + '%');
				q1.setParameter("neetrank", '%' + search + '%');
				q1.setParameter("tenthpercentage", '%' + search + '%');
				q1.setParameter("twelvthpercentage", '%' + search + '%');
//				q1.setParameter("yearval", '%' + search + '%');
				q1.setParameter("firstname", '%' + search + '%');
				q1.setParameter("middlename", '%' + search + '%');
				q1.setParameter("lastname", '%' + search + '%');
				q1.setParameter("dob", '%' + search + '%');
				q1.setParameter("mobilenumber", '%' + search + '%');
				q1.setParameter("emaildi", '%' + search + '%');
				q1.setParameter("aadhaarnumber", '%' + search + '%');
				// q1.setParameter("commtype", '%' + search + '%');
				q1.setParameter("category", '%' + search + '%');

			}
			// q1.setResultTransformer(Transformers.aliasToBean(StudentEnrollment.class));
			System.out.println("Q1" + q1.getQueryString());
			list = q1.list();
			// List<StudentEnrollment> list1 = q1.list();

//			for (CoStudentenrollment coStudentenrollment : list) {
//				System.out.println(coStudentenrollment.getAadhaarnumber());
//			}

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
	public String GetCommissionTypeFromID(Integer commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From CoCommissiontype where status = '1' and comid = :comid");
			q1.setParameter("comid", commtype);

			CoCommissiontype coCommissiontype = (CoCommissiontype) q1.uniqueResult();
			comtype = coCommissiontype.getComname();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return comtype;
	}

	@Override
	public String getStateName(Integer commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From EduLmsStateMstr where status = '1' and state_id = :state_id");
			q1.setParameter("state_id", commtype);

			EduLmsStateMstr coCommissiontype = (EduLmsStateMstr) q1.uniqueResult();
			comtype = coCommissiontype.getStateName();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return comtype;
	}

	@Override
	public CoStudentenrollment GetStudentEnrolledDataByID(long seid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoStudentenrollment coStudentenrollment = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From CoStudentenrollment where seid = :seid  and status = '1'");
			q1.setParameter("seid", seid);

			coStudentenrollment = (CoStudentenrollment) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coStudentenrollment;

	}

	@Override
	public List CheckNEETRollNumberexist(String lowerCase) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where lower(neetrollnumber) = :neetrollnumber  and status = '1' and paymentstatus = 'Y'");
			q1.setParameter("neetrollnumber", lowerCase);

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
	public List CheckApplciationNumberexist(String lowerCase) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where lower(applicationnumber) = :applicationnumber  and status = '1'  and paymentstatus = 'Y'");
			q1.setParameter("applicationnumber", lowerCase);

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
	public List<CoStudentenrollment> LoadStudentEnrolledDataForCount(String yearval, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoStudentenrollment> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where year = :year and createby = :createby and status = '1' ");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoStudentenrollment>) q1.list();

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
	public String GetCategoryFromSEID(Long seid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoStudentenrollment> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		CoStudentenrollment coStudentenrollment = null;
		try {

			Query q1 = sessionHQL.createQuery("From CoStudentenrollment where seid = :seid");

			q1.setParameter("seid", seid);
			coStudentenrollment = (CoStudentenrollment) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coStudentenrollment.getCatid().getCategory();
	}

	@Override
	public CoStudentenrollment GetStudentEnrolledDataByLoginId(int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoStudentenrollment coStudentenrollment = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL
					.createQuery("From CoStudentenrollment where userid.userId = :userid  and status = '1'");
			q1.setParameter("userid", userid);

			coStudentenrollment = (CoStudentenrollment) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coStudentenrollment;

	}

	@Override
	public List<CoStudentenrollment> GetStudentDataAccordingToNEETRank(String meritround, String year, int userid,
			String rolename) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoStudentenrollment> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			Query q1 = null;
			
		
			if (rolename.equalsIgnoreCase("State Council")) {
				
				Query q2 =sessionHQL.createQuery("select distinct(seid.seid) from CoStudentchoicefilling where commtype = 1 and seid.roundstatus not  like '%Assigned%'");
				List listd = q2.list();
				if(listd != null) {
					if(!listd.isEmpty()) {
						if(listd.size()>0) {
							q1 = sessionHQL.createQuery(
									"From CoStudentenrollment where year = :year and stateid.stateId = :stateid and round=:round and status = :status and seid   in (select distinct(seid.seid) from CoStudentchoicefilling where commtype = 1 and seid.roundstatus not  like '%Assigned%') order by neetrank asc");	
						}else {
							q1 = sessionHQL.createQuery(
									"From CoStudentenrollment where year = :year and stateid.stateId = :stateid and round=:round and status = :status  order by neetrank asc");	
						}
					}else {
						q1 = sessionHQL.createQuery(
								"From CoStudentenrollment where year = :year and stateid.stateId = :stateid and round=:round and status = :status  order by neetrank asc");		
					}
				}else {
					q1 = sessionHQL.createQuery(
							"From CoStudentenrollment where year = :year and stateid.stateId = :stateid and round=:round and status = :status  order by neetrank asc");	
				}
				
//				q1 = sessionHQL.createQuery(
//						"From CoStudentenrollment where year = :year and stateid.stateId = :stateid and round=:round and status = :status and seid   in (select distinct(seid.seid) from CoStudentchoicefilling where commtype = 1 and seid.roundstatus not  like '%Assigned%') order by neetrank asc");
				q1.setParameter("stateid", userid);
			} else {
				q1 = sessionHQL.createQuery(
						"From CoStudentenrollment where year = :year and createby = :createby and round=:round and status = :status order by neetrank asc");
				q1.setParameter("createby", userid);
			}

			q1.setParameter("round", Integer.parseInt(meritround));
			q1.setParameter("year", year);
			q1.setParameter("status", "1");

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoStudentenrollment>) q1.list();

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
	public int getCategoryIDFromCategoryName(String category) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TbLmsCategoryMstr tbLmsCategoryMstr = null;
		int catid = 0;
		try {
			Query q1 = sessionHQL
					.createQuery("From TbLmsCategoryMstr where status = '1' and category = :category order by id desc");
			q1.setMaxResults(1);
			q1.setParameter("category", category);
			tbLmsCategoryMstr = (TbLmsCategoryMstr) q1.uniqueResult();
			if (tbLmsCategoryMstr != null) {
				catid = tbLmsCategoryMstr.getId();
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return catid;
	}

	@Override
	public List CheckAadaarNumberexist(String aadhaarnumber) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where aadhaarnumber = :aadhaarnumber  and status = '1'  and paymentstatus = 'Y'");
			q1.setParameter("aadhaarnumber", aadhaarnumber);

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
	public boolean ValdiateNEEtDetails(String aadhaarnumber, String applicationnumber, String neetrollnumber,
			String neetmarks, String neetrank, String year,String dob,int category) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		boolean checkres = false;
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentneetinfo where aadhaarnumber = :aadhaarnumber and year = :year  and status = '1' and neetrollnumber =:neetrollnumber and applicationnumber = :applicationnumber and neetmarks = :neetmarks and neetrank = :neetrank and dob =:dob and categoryid.id= :categoryid");
			q1.setParameter("aadhaarnumber", aadhaarnumber);
			q1.setParameter("neetrollnumber", neetrollnumber);
			q1.setParameter("applicationnumber", applicationnumber);
			q1.setParameter("neetmarks", Integer.parseInt(neetmarks));
			q1.setParameter("neetrank", Integer.parseInt(neetrank));
			q1.setParameter("year", year);
			q1.setParameter("dob",dob);
			q1.setParameter("categoryid",category);
			
			list = q1.list();
			System.out.println(list.size() + "werg");
			tx.commit();
			if (list.size() > 0) {
				checkres = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return checkres;
	}

	@Override
	public boolean CheckUplaodedInformation(String aadhaatnumber, String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		boolean checkres = false;
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentneetinfo where aadhaarnumber = :aadhaarnumber and year = :year  and status = '1' ");
			q1.setParameter("aadhaarnumber", aadhaatnumber);

			q1.setParameter("year", yearval);
			list = q1.list();

			tx.commit();
			if (list != null || !list.isEmpty()) {
				checkres = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return checkres;
	}

	@Override
	public List CheckNEETRollNumberexistForCouncilORState(String lowerCase, int createby) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where lower(neetrollnumber) = :neetrollnumber  and status = '1' and paymentstatus = 'Y' and createby =:createby");
			q1.setParameter("neetrollnumber", lowerCase);
			q1.setParameter("createby", createby);

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
	public List CheckApplciationNumberexistForCouncilORState(String lowerCase, int createby) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where lower(applicationnumber) = :applicationnumber  and status = '1'  and paymentstatus = 'Y'  and createby =:createby");
			q1.setParameter("applicationnumber", lowerCase);
			q1.setParameter("createby", createby);
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
	public List CheckAadaarNumberexistForCouncilORState(String aadhaarnumber, int createby) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where aadhaarnumber = :aadhaarnumber  and status = '1'  and paymentstatus = 'Y'  and createby =:createby");
			q1.setParameter("aadhaarnumber", aadhaarnumber);
			q1.setParameter("createby", createby);
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
	public CoStudentenrollment CheckDetailsExist(String applicationnumber, String aadhaatnumber, String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		CoStudentenrollment coStudentenrollment = null;
		;

		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentenrollment where aadhaarnumber = :aadhaarnumber  and applicationnumber = :applicationnumber and status = '1'  and year=:year order by seid desc");
			q1.setParameter("aadhaarnumber", aadhaatnumber);
			q1.setParameter("applicationnumber", applicationnumber);
			q1.setParameter("year", yearval);
			list = q1.list();
			if (list != null && !list.isEmpty()) {
				coStudentenrollment = (CoStudentenrollment) list.get(0);
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coStudentenrollment;
	}
}
