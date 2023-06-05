package com.AyushEdu.dao.Part_One.Masters;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClgDeptEquipmentItemDaoimpl implements ClgDeptEquipmentItemDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Map<String, Object>> DataTableDeptEquipmentItemDataList(int startPage, int pageLength, String search, String orderColunm, String orderType, String item_name, String status, Integer dept_equip_item_id) {
        String SearchValue = GenerateQueryWhereClause_SQL(search, item_name, dept_equip_item_id);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        String q = "";
        try {

            conn = dataSource.getConnection();
            String pageL = "";
            if (pageLength == -1) {
                pageL = "ALL";
            } else {
                pageL = String.valueOf(pageLength);
            }

            if (search.equals("") && item_name.equals("0") && status == "1") {
                q = "select  id,status ,item_name,dept_equip_item_id from clg_reg_dept_equipment_item_mstr where status='1'" + SearchValue
                        + " ORDER BY item_name " + orderType + " limit " + pageL + " OFFSET " + startPage;
            } else {
                q = "select  id,status,item_name,dept_equip_item_id from clg_reg_dept_equipment_item_mstr where status='" + status + "'"
                        + SearchValue + " ORDER BY item_name " + orderType + " limit " + pageL + " OFFSET "
                        + startPage;
            }


            PreparedStatement stmt = conn.prepareStatement(q);
            stmt = setQueryWhereClause_SQL(stmt, search, item_name, dept_equip_item_id);
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            int j = startPage;

            int countFunction = 1;
            int countFunctionDelete = 1;

            while (rs.next()) {
                Map<String, Object> columns = new LinkedHashMap<String, Object>();
                columns.put("ser", ++j);
                for (int i = 1; i <= columnCount; i++) {
                    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
                }

                String f = "";
                String action = "";
                String f1 = "";

                String ul = "";
                ul += "<ul class='buttons-group mainbtn action daobtn'>";

               
                f = "<li><a class='main-btn active-btn btn-hover btn-sm EditEquipmentItem' value='ADD' title='Edit Data' >" 
                
                		+ "<i class='lni lni-pencil-alt'>" 
                
                		+ "<input type='hidden' id='apId" + countFunction + "' value=" + rs.getString("id") + ">"
                        
                        + "<input type='hidden' id='apequipid" + countFunction + "' value='" + rs.getString("item_name") + "'>"
                        
						+ "<input type='hidden' id='apdeptequipid" + countFunction + "' value='" + rs.getString("dept_equip_item_id") + "'>"

                        + "<input type='hidden' id='apStatus" + countFunction + "' value=" + rs.getString("status") + " ></i></a> </li>";
                
                String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
                        + rs.getString("id") + "') }else{ return false;}\"";
                f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
                        + // id='id_add_attHospital1'
                        "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
                        + "><i class='lni lni-trash-can'></i></a> </li>";

                ul += f + " " + f1;
                ul += "</ul>";

                action = ul;
                countFunction += 1;
                countFunctionDelete += 1;
                columns.put("action", action);

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

    @Override
    public String updateDeptEquipmentItem(CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER td) {
        Session session1 = this.sessionFactory.openSession();
        Transaction tx = session1.beginTransaction();

        String msg = "";
        try {
            String hql = "update CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER set item_name=:item_name,dept_equip_item_id=:dept_equip_item_id,status=:status,modified_by=:modified_by,modified_date=:modified_date"
                    + " where id=:id ";

            Query query = session1.createQuery(hql).
                    setParameter("item_name", td.getItem_name())
                    .setParameter("dept_equip_item_id", td.getDept_equip_item_id())
                    .setParameter("status", td.getStatus())
                    .setParameter("modified_by", td.getModified_by()).
                    setParameter("modified_date", td.getModified_date())
                    .setParameter("id", td.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
//            msg = query.executeUpdate() > 0 ? "1" : "0";
            tx.commit();
        } catch (Exception e) {
            msg = "Data Not Updated";
            tx.rollback();
        } finally {
            session1.close();
        }
        return msg;
    }

    @Override
    public long DataTableDeptEquipmentItemCount(String search, String item_name, Integer dept_equip_item_id) {
        String SearchValue = GenerateQueryWhereClause_SQL(search, item_name, dept_equip_item_id);

        int total = 0;
        String q = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            //Query for count page in data-table....by ruler
            //q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;


            q = "select count(*) from (select  id,item_name,dept_equip_item_id from clg_reg_dept_equipment_item_mstr where status='1' \n"
                    + SearchValue + " ) ab  ";


            PreparedStatement stmt = conn.prepareStatement(q);

            stmt = setQueryWhereClause_SQL(stmt, search, item_name, dept_equip_item_id);

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
        return total;
    }

    public String GenerateQueryWhereClause_SQL(String Search, String item_name, Integer dept_equip_item_id) {
        String SearchValue = "";
        if (Search != null && !Search.equals("")) { // for Input Filter
            SearchValue += " and (  upper(item_name) like ? or dept_equip_item_id = ?::Integer ) ";

        }

        if (item_name != null && !item_name.equals("")) {
            SearchValue += " and upper(item_name) like ? ";

        }

        if (dept_equip_item_id != null && dept_equip_item_id != 0) {
            SearchValue += " and dept_equip_item_id = ?:: Integer";

        }


        return SearchValue;
    }

    public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String item_name, Integer dept_equip_item_id) {
        int flag = 0;
        try {
            if (Search != null && !Search.equals("")) {
                flag += 1;
                stmt.setString(flag, "%" + Search.toUpperCase() + "%");
            }
            if (item_name != null && !item_name.equals("")) {
                flag += 1;
                stmt.setString(flag, "%" + item_name.toUpperCase() + "%");
            }
            if (dept_equip_item_id != null && !dept_equip_item_id.equals("")) {
                flag += 1;
                stmt.setString(flag, dept_equip_item_id.toString());
            }

        } catch (Exception e) {
        }

        return stmt;
    }


}
