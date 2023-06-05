package com.AyushEdu.dao.Counselling_Institute;

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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Counselling_Institute.CoCommissiontype;
import com.AyushEdu.Models.Counselling_Institute.CoRoundgeneration;

@Service
@Repository
public class RoundGenerationDaoImpl implements RoundGenerationDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void SaveRoundGenerationData(CoRoundgeneration coRoundgeneration, String actiontype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			if (actiontype.equalsIgnoreCase("add")) {
				sessionHQL.save(coRoundgeneration);
			} else {
				sessionHQL.update(coRoundgeneration);
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
	public List<CoRoundgeneration> LoadRoundGenerationData(String username, String data, String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";

			String SearchValue = "";
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(cast(commtype as text))  like :commtype"

						+ " OR upper(cast(startdate as text))  like :startdate"
						+ " OR upper(cast(enddate as text)) like :enddate"
						+ " OR upper(cast(meritdate as text)) like :meritdate" + ")";
			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "commtype";
			}

			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "startdate";
			}
			if (orderColunm.equalsIgnoreCase("5")) {
				orderColunm = "enddate";
			}

			if (orderColunm.equalsIgnoreCase("6")) {
				orderColunm = "meritdate";
			}

			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
			Query q1 = sessionHQL
					.createQuery("From CoRoundgeneration where  createby = :createby and status = '1' and year=:year "
							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));
			q1.setParameter("createby", Integer.parseInt(username));
			q1.setParameter("year", yearval);

			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();

				q1.setParameter("commtype", '%' + search + '%');
				q1.setParameter("startdate", '%' + search + '%');
				q1.setParameter("enddate", '%' + search + '%');
				q1.setParameter("meritdate", '%' + search + '%');

			}

			System.out.println("Q1" + q1.getQueryString().toString());
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
	public String GetCommissionnameFromId(int commtype) {
		// TODO Auto-generated method stub

		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoCommissiontype co = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		String comname = "";
		try {

			Query q1 = sessionHQL.createQuery("From CoCommissiontype where  comid=:comid and status='1'");

			q1.setParameter("comid", commtype);

			System.out.println("Q1" + q1.getQueryString());
			co = (CoCommissiontype) q1.uniqueResult();
			if (co != null) {
				comname = co.getComname();
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return comname;
	}

	@Override
	public List<CoRoundgeneration> LoadRoundGenerationDataForCount(String username, String data, String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year = :year and createby = :createby and status = '1' ");

			q1.setParameter("createby", Integer.parseInt(username));
			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public List<CoRoundgeneration> getRoundGenerationDataFromCompType(String yearval, int userid, int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year = :year and createby = :createby and status = '1' and commtype=:commtype order by rid");

//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year = :year and  status = '1'");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			q1.setParameter("commtype", commtype);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public List<CoRoundgeneration> getRoundGenerationDataFromCompTypeResult(String yearval, int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year = :year and  status = '1' and commtype=:commtype order by rid");

//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year = :year and  status = '1'");

			q1.setParameter("year", yearval);
			q1.setParameter("commtype", commtype);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public List<CoRoundgeneration> getRoundGenerationData(String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year = :year and status = '1'  order by rid");
			
//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year = :year and status = '1' and rolename = 'Commission' order by rid");

			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public List CheckRoundForCommissionExist(int comtype, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL
					.createQuery("From CoRoundgeneration where year = :year  and status = '1' and commtype=:commtype");

			q1.setParameter("year", year);
			q1.setParameter("commtype", comtype);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public int getCountForCommission(int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		try {

			Query q1 = sessionHQL.createQuery(
					"select DISTINCT (commtype) From CoRoundgeneration where year = :year  and status = '1' and createby=:createby");

			q1.setParameter("year", year);
			q1.setParameter("createby", userid);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();
			if (!list.isEmpty()) {
				count = list.size();
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public int getCommissionType(int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		try {

			Query q1 = sessionHQL.createQuery(
					"select DISTINCT (commtype) From CoRoundgeneration where year = :year  and status = '1' and createby=:createby");
			q1.setMaxResults(1);
			q1.setParameter("year", year);
			q1.setParameter("createby", userid);

			System.out.println("Q1" + q1.getQueryString());
			count = (int) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public String getCommissionList(int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		String html = "";
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoCommissiontype where comid in (select DISTINCT (commtype) From CoRoundgeneration where year = :year  and status = '1' and createby=:createby) and status='1'");

			q1.setParameter("year", year);
			q1.setParameter("createby", userid);

			System.out.println("Q1" + q1.getQueryString());
			List<CoCommissiontype> commissiontypes = q1.list();
			html = "<div class=\"col-12 col-sm-6 col-md-6 col-lg-4\">\n"
					+ "									<div class=\"select-style-1\">\n"
					+ "										<label>Commission Type<span class=\"mandatory\">*</span></label>\n"
					+ "										<div class=\"select-position\">\n"
					+ "											<select class=\"form-select\" id=\"commtype\" name=\"commtype\" onchange=CallLoadButton()\n"
					+ "												tabindex=\"11\">\n"
					+ "											<option value=\"-1\">Please Select Commission Type</option>	\n";

			for (CoCommissiontype coCommissiontype : commissiontypes) {
				html += "<option value='" + coCommissiontype.getComid() + "'>" + coCommissiontype.getComname()
						+ "</option>";
			}
			html += "</select>\n" + "										</div>\n"
					+ "									</div>\n"
					+ "									<!-- end select -->\n" + "								</div>";

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return html;
	}

	@Override
	public CoRoundgeneration GetlastDateAfterAllRound(String year, int createby, int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoRoundgeneration coRoundgeneration = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		try {

//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year=:year and status='1' and createby = :createby and commtype = :commtype order by rid desc");
			
			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year=:year and status='1' and  commtype = :commtype order by rid desc");
			q1.setMaxResults(1);
			q1.setParameter("year", year);
			//q1.setParameter("createby", createby);
			q1.setParameter("commtype", commtype);

			System.out.println("Q1" + q1.getQueryString());
			coRoundgeneration = (CoRoundgeneration) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coRoundgeneration;
	}

	@Override
	public Date GetLastDateOFCommission(String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoRoundgeneration coRoundgeneration = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		Date d = null;
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year=:year and status='1' and rolename = 'Commission'  order by rid desc");
			q1.setMaxResults(1);
			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			coRoundgeneration = (CoRoundgeneration) q1.uniqueResult();
			if (coRoundgeneration != null) {
				d = coRoundgeneration.getResultenddate();
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return d;
	}

	@Override
	public List<CoRoundgeneration> getRoundGenerationDataFromCompTypeChoiceFilling(String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year = :year and createby = :createby and status = '1' and commtype=:commtype");

			Query q1 = sessionHQL.createQuery("From CoRoundgeneration where year = :year and  status = '1'");

//			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
//			q1.setParameter("commtype", commtype);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
	public List getRoundGenerationDataForSendListToInstitute(String yearval, int userid, int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createSQLQuery(
					"WITH cte AS (\n"
					+ "    SELECT \n"
					+ "        resultenddate, \n"
					+ "        startdate as overs\n"
					+ "    FROM co_roundgeneration\n"
					+ "    GROUP BY commtype,resultenddate,startdate,rid\n"
					+ "    ORDER BY rid\n"
					+ ") \n"
					+ "SELECT\n"
					+ "    resultenddate, \n"
					+ "    startdate,\n"
					+ "    LEAD(startdate, 1) OVER (\n"
					+ "        ORDER BY rid\n"
					+ "    ) year_average\n"
					+ "FROM\n"
					+ "    co_roundgeneration where commtype=:commtype and year=:year and createby=:createby  and status='1'");

//			Query q1 = sessionHQL.createQuery(
//					"From CoRoundgeneration where year = :year and  status = '1'");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			q1.setParameter("commtype", commtype);

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
	public List getRoundGenerationDataForSendListToCounselling(String yearval,int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createSQLQuery(
					"WITH cte AS (\n"
					+ "    SELECT \n"
					+ "        resultenddate, \n"
					+ "        startdate as overs\n"
					+ "    FROM co_roundgeneration\n"
					+ "    GROUP BY commtype,resultenddate,startdate,rid\n"
					+ "    ORDER BY rid\n"
					+ ") \n"
					+ "SELECT\n"
					+ "    resultenddate, \n"
					+ "    startdate,\n"
					+ "    LEAD(startdate, 1) OVER (\n"
					+ "        ORDER BY rid\n"
					+ "    ) year_average\n"
					+ "FROM\n"
					+ "    co_roundgeneration where  year=:year  and status='1' and commtype =:commtype");


			q1.setParameter("year", yearval);
			q1.setParameter("commtype", commtype);

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
	public CoRoundgeneration GetlastDateAfterAllRoundForCommission(String year, int commtype) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoRoundgeneration coRoundgeneration = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		int count = 0;
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoRoundgeneration where year=:year and status='1' and commtype = :commtype order by rid desc");
			q1.setMaxResults(1);
			q1.setParameter("year", year);
			q1.setParameter("commtype", commtype);

			System.out.println("Q1" + q1.getQueryString());
			coRoundgeneration = (CoRoundgeneration) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coRoundgeneration;
	}

	@Override
	public List<CoRoundgeneration> LoadRoundGenerationDataFORPDF(String username, String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			

			
			Query q1 = sessionHQL
					.createQuery("From CoRoundgeneration where  createby = :createby and status = '1' and year=:year order by rid"
							);

			
			q1.setParameter("createby", Integer.parseInt(username));
			q1.setParameter("year", yearval);

			

			System.out.println("Q1" + q1.getQueryString().toString());
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
	public List<CoRoundgeneration>  CheckRegistrationAllow(String datefrm,String yearval) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
	List<CoRoundgeneration> coRoundgeneration = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			

			
			Query q1 = sessionHQL
					.createQuery("From CoRoundgeneration where  regstartdate <= '"+datefrm+"' and regenddate >= '"+datefrm+"' and status = '1' and year= '"+yearval+"' order by rid");
			System.out.println("Q1" + q1.getQueryString().toString());
			coRoundgeneration =q1.list();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coRoundgeneration;
	}
	
	@Override
	public List CheckRoundForCommissionExistRound(int comtype, String year,int round) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoRoundgeneration> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL
					.createQuery("From CoRoundgeneration where year = :year  and status = '1' and commtype=:commtype and round=:round");

			q1.setParameter("year", year);
			q1.setParameter("commtype", comtype);
			q1.setParameter("round", round);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoRoundgeneration>) q1.list();

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
