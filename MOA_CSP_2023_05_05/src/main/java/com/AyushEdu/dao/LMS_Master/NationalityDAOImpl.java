package com.AyushEdu.dao.LMS_Master;

import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;

@Service
@Repository
public class NationalityDAOImpl implements NationalityDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;

	public recr_nationality_mst getNationalityByid(int id) {
		recr_nationality_mst updateid = null;
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from recr_nationality_mst where nationality_id=:id");
			q.setParameter("id", id);
			updateid = (recr_nationality_mst) q.list().get(0);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {

		}
		return updateid;
	}

	public String UpdateNationalityValue(recr_nationality_mst mscountryValues, String username) {
		int rowCount = 0;
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update recr_nationality_mst  set nationality=:nationality,nationality_updatedby=:nationality_updatedby,nationality_updateddate='"
					+ new Date() + "'  where nationality_id=:nationality_id";
			Query query = session.createQuery(hql)
					.setString("nationality", mscountryValues.getNationality().toUpperCase())
					.setString("nationality_updatedby", username)
					.setInteger("nationality_id", mscountryValues.getNationality_id());
			rowCount = query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {

		}
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated Not Successfully";
		}
	}

	public String deleteNationalityUrlAdd(int appid, String username) {
		int rowCount = 0;
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update recr_nationality_mst set status= :status ,del_id =:del_id,del_dt ='" + new Date()
					+ "' where nationality_id = :nationality_id";
			Query q = session.createQuery(hql).setString("status", "I").setString("del_id", username)
					.setInteger("nationality_id", appid);
			rowCount = q.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {

		}
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted Not Successfully";
		}
	}

}
