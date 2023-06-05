package com.AyushEdu.dao.Alumni;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_POST;

@Repository
public class Alumni_Ventures_DAOimpl implements Alumni_Ventures_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public void SaveAlumVenturesData(EDU_ALUM_ALUMNI_POST alum_ven, String actiontpe) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			if (actiontpe.equalsIgnoreCase("add")) {

				sessionHQL.save(alum_ven);
			} else {
				sessionHQL.update(alum_ven);
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
	public List<EDU_ALUM_ALUMNI_POST> Loadalum_ventureData(int userid, String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EDU_ALUM_ALUMNI_POST> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();

		try {

			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";

			String SearchValue = "";

			String feed_cat = jsonObject.get("feed_cat").toString();
			String title = jsonObject.get("title").toString();
//			String post_date =jsonObject.get("post_date").toString();
			String batch = jsonObject.get("batch").toString();

			if (!feed_cat.equals("0")) {
				SearchValue += " and feedCatId.id = : feed_cat ";
			}
			if (!title.equals("")) {
				SearchValue += " and upper(title) like : title ";
			}

//			if (!post_date.equals("") && !post_date.equals("DD/MM/YYYY")) {
//				SearchValue +=  " and TO_CHAR(postDate,'DD/MM/YYYY') like : post_date ";
//			}

			if (!batch.equals("")) {
				SearchValue += " and upper(batch) like : batch ";
			}

			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue += " and ( ";
				SearchValue += " upper(feedCatId.feed_category) like : feed_category" + " OR upper(title) like : title"
						+ " OR  upper(TO_CHAR(postDate,'DD-MM-YYYY')) like :postdate " + " OR upper(batch) like :batch"
						+ ")";

			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "title";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "postdate";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "batch";
			}

			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();
			String startPage = jsonObject.get("startPage").toString();

			System.out.println("pageLength" + pageLength);
			Query q1 = sessionHQL.createQuery("From EDU_ALUM_ALUMNI_POST where status='1' and createdBy=:createdBy "
					+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));
			q1.setParameter("createdBy", userid);

			if (!feed_cat.equals("0")) {

				q1.setParameter("feed_cat", Integer.parseInt(feed_cat));
			}

			if (!title.equals("")) {
				q1.setParameter("title", '%' + title.toUpperCase() + '%');
			}

//			if (!post_date.equals("") && !post_date.equals("DD/MM/YYYY")) {
//				q1.setParameter("post_date", post_date);
//			}

			if (!batch.equals("")) {
				q1.setParameter("batch", batch);
			}

			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();
				q1.setParameter("feed_category", '%' + search + '%');
				q1.setParameter("title", '%' + search + '%');
				q1.setParameter("postdate", '%' + search + '%');
				q1.setParameter("batch", '%' + search + '%');

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
	public List<EDU_ALUM_ALUMNI_POST> LoadalumVenturesDataForCount(int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EDU_ALUM_ALUMNI_POST> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			System.err.println("--------" +userid);
			Query q1 = sessionHQL
					.createQuery("From EDU_ALUM_ALUMNI_POST where status='1' and created_by=:createby order by id");

			q1.setParameter("createby", userid);

			System.out.println("Q1" + q1.getQueryString());
			System.err.println("------" +list);
			list = (List<EDU_ALUM_ALUMNI_POST>) q1.list();
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
	public EDU_ALUM_ALUMNI_POST GetAlumVenturesDataByID(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_ALUM_ALUMNI_POST alum_venture = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery("From EDU_ALUM_ALUMNI_POST where id =:id  and status='1'");
			q1.setParameter("id", id);

			alum_venture = (EDU_ALUM_ALUMNI_POST) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return alum_venture;
	}
	
	@Override
	public List CheckTitleNameExist(String title) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<EDU_ALUM_ALUMNI_POST> list = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From EDU_ALUM_ALUMNI_POST where status = '1' and title=:title ");
			q1.setParameter("title", title);
			
			list = (List<EDU_ALUM_ALUMNI_POST>) q1.list();

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
