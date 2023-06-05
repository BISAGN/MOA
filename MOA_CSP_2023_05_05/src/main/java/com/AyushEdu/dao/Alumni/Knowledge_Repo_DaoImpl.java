package com.AyushEdu.dao.Alumni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class Knowledge_Repo_DaoImpl implements Knowledge_Repo_DAO{
	@Autowired
	private DataSource dataSource;

	
	public List<Map<String, Object>> getSearchDetails(String a) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q="select * from tb_knowledge_repo as r inner join tb_knowledge_repo_category_mstr m on m.id = r.category_id where TRIM(upper(m.category_repo)) like ? OR TRIM(upper(title)) like ? OR TRIM(upper(upload_doc)) like ? OR TRIM(upper(description)) like ?";
					
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, "%"+a.toUpperCase()+"%");
			stmt.setString(2, "%"+a.toUpperCase()+"%");
			stmt.setString(3, "%"+a.toUpperCase()+"%");
			stmt.setString(4, "%"+a.toUpperCase()+"%");
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
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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


}
