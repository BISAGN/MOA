package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.LMS_Master.recr_qualification_mst;

@Service
@Repository
public class QualificationDAOimpl implements QualificationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> getReportQualification() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select * from  recr_qualification_mst where status='A' order by qualification_id desc";

			stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("qualification"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This  ?') ){editData("
						+ rs.getInt("qualification_id") + ",'" + rs.getString("qualification")
						+ "')}else{ return false;}\"";
				f = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This   ?') ){deleteData("
						+ rs.getInt("qualification_id") + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				list.add(f);
				list.add(f1);
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

	public String updateQualification(recr_qualification_mst obj) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
			String hql = "update recr_qualification_mst set  " + "qualification=:qualification,"
					+ "qualification_updatedby=:qualification_updatedby,"
					+ "qualification_updateddate=:qualification_updateddate "
					+ "where qualification_id=:qualification_id";
			Query query = sessionHQL.createQuery(hql)

					.setString("qualification", obj.getQualification()).setDate("qualification_updateddate", new Date())
					.setString("qualification_updatedby", obj.getQualification_updatedby())
					.setInteger("qualification_id", obj.getQualification_id());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {

			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}

	public String deleteQualificationUrlAdd(int appid, String username) {
		int rowCount = 0;
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update recr_qualification_mst set status= :status ,del_id =:del_id,del_dt ='" + new Date()
					+ "' where qualification_id = :qualification_id";
			Query q = session.createQuery(hql).setString("status", "I").setString("del_id", username)
					.setInteger("qualification_id", appid);
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
