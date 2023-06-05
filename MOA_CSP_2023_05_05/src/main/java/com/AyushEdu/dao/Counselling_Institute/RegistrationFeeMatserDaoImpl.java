package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Counselling_Institute.CoRegistrationfeestructure;

@Service
@Repository
public class RegistrationFeeMatserDaoImpl implements RegistrationFeeMatserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<CoRegistrationfeestructure> getRegistrationFeesData(String year, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRegistrationfeestructure> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRegistrationfeestructure where year = :year and createBy = :createby and status = '1' ");

			q1.setParameter("createby", userid);
			q1.setParameter("year", year);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRegistrationfeestructure>) q1.list();

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
	public void SaveRegistrationFeesData(CoRegistrationfeestructure coRegistrationfeestructure, String actiontpe) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			if (actiontpe.equalsIgnoreCase("add")) {
				sessionHQL.save(coRegistrationfeestructure);
			} else {
				sessionHQL.update(coRegistrationfeestructure);
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
	public List CheckRegistrationDetailsExist(int userid, String year, int catid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoRegistrationfeestructure where  status = '1'  and year = :year and createBy = :createby and catId.id =: catId");
			q1.setParameter("year", year);
			q1.setParameter("createby", userid);
			q1.setParameter("catId", catid);
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
	public List<CoRegistrationfeestructure> LoadRegistrationFeeData(String yearval, int userid, String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRegistrationfeestructure> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";
			String SearchValue = "";
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(catId.category) like :category" + " OR upper(cast(fees as text)) like :fees"
						+ " OR upper(cast(securityMoney as text)) like :securityMoney" + ")";
//						

			}
			String orderColunm = jsonObject.get("orderColunm").toString();

			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "catId.category";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "fees";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "securityMoney";
			}

			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
			Query q1 = sessionHQL.createQuery(
					"From CoRegistrationfeestructure where year = :year and createBy = :createby and status = '1' "
							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

//			Query q1 = sessionHQL.createQuery(
//					"select new com.AyushEdu.seat_allocation_entity.CoStudentenrollment(commtype,neetrollnumber,applicationnumber,neetmarks,neetrank,tenthpercentage,twelvthpercentage,year,firstname,middlename,lastname,dob,mobilenumber,emaildi,aadhaarnumber,seid) From CoStudentenrollment where year = :year and createby = :createby and status = '1' "
//							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));
			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();

				q1.setParameter("category", '%' + search + '%');
				q1.setParameter("fees", '%' + search + '%');
				q1.setParameter("securityMoney", '%' + search + '%');

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
	public List<CoRegistrationfeestructure> LoadRegistrationFeeDataCount(String yearval, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRegistrationfeestructure> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRegistrationfeestructure where year = :year and createBy = :createby and status = '1'");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRegistrationfeestructure>) q1.list();

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
	public boolean CheckPaymentForCategory(int catid, String yearval, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRegistrationfeestructure> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		boolean result = false;
		try {

//			Query q1 = sessionHQL.createQuery(
//					"From CoRegistrationfeestructure where year = :year and createBy = :createby and status = '1' and catId.id = :catid order by rfid desc");

			Query q1 = sessionHQL.createQuery(
					"From CoRegistrationfeestructure where year = :year  and status = '1' and catId.id = :catid order by rfid desc");

//			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			q1.setParameter("catid", catid);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRegistrationfeestructure>) q1.list();

			tx.commit();
			CoRegistrationfeestructure coRegistrationfeestructure = list.get(0);

			double fees = coRegistrationfeestructure.getFees();
			double secfees = coRegistrationfeestructure.getSecurityMoney();

			if (fees + secfees > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return result;
	}

}
