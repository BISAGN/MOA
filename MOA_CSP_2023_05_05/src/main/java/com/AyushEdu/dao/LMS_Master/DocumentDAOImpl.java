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

import com.AyushEdu.Models.LMS_Master.Document_mst;

@Service
@Repository
public class DocumentDAOImpl implements DocumentDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<ArrayList<String>> search_Documentmaster(String doc1) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select id,doc_name,doc_type from recr_document_mst";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("doc_name"));
				list.add(rs.getString("doc_type"));

				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Document ?') ){editData("
						+ rs.getInt("id") + ",'" + rs.getString("doc_name") + "','" + rs.getString("doc_type").trim()
						+ "')}else{ return false;}\"";
				f = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Document  ?') ){deleteData("
						+ rs.getInt("id") + ")}else{ return false;}\"";
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

	public String updatedoc(Document_mst obj) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update Document_mst set doc_name=:doc_name,doc_type=:doc_type ,modified_dt=:modified_dt,modified_by=:modified_by where id=:id";

			Query query = sessionHQL.createQuery(hql).setInteger("id", obj.getId())
					.setString("doc_name", obj.getDoc_name()).setString("doc_type", obj.getDoc_type())
					.setDate("modified_dt", new Date()).setString("modified_by", obj.getModified_by());

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

}
