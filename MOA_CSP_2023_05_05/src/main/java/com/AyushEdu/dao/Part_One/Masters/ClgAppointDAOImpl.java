package com.AyushEdu.dao.Part_One.Masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_NATURE_OF_APPOINTMENT;
@Repository
public class ClgAppointDAOImpl implements ClgAppointDAO{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("null")
    public String GenerateQueryWhereClause_SQL(String Search, String nature_of_appoinment) {
        String SearchValue = "";
        if (Search != null && !Search.equals("")) { // for Input Filter
            SearchValue += " and (  upper(nature_of_appoinment) like ? )";

        }

        if (nature_of_appoinment != null && !nature_of_appoinment.equals("")) {
            SearchValue += " and upper(nature_of_appoinment) like ? ";

        }
        return SearchValue;
    }

    public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String nature_of_appoinment) {
        int flag = 0;
        try {
            if (Search != null && !Search.equals("")) {
                flag += 1;
                stmt.setString(flag, "%" + Search.toUpperCase() + "%");
            }
            if (nature_of_appoinment != null && !nature_of_appoinment.equals("")) {
                flag += 1;
                stmt.setString(flag, "%" + nature_of_appoinment.toUpperCase() + "%");
            }

        } catch (Exception e) {
        }
        return stmt;
    }


    @Override
    public CLG_REG_NATURE_OF_APPOINTMENT getNatureAppointmentByid(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        CLG_REG_NATURE_OF_APPOINTMENT updateid = (CLG_REG_NATURE_OF_APPOINTMENT) session.get(CLG_REG_NATURE_OF_APPOINTMENT.class, id);
        session.getTransaction().commit();
        session.close();
        return updateid;
    }

    @Override
    public List<Map<String, Object>> DataTableClgAppointDataList(int startPage, int pageLength, String search, String orderColunm, String orderType, String clg_appoint_name, String status) {
        String SearchValue = GenerateQueryWhereClause_SQL(search, clg_appoint_name);
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

            if (search.equals("") && clg_appoint_name.equals("0") && status == "1") {
                q = "select ROW_NUMBER() OVER(order by nature_of_appoinment ASC) as sr_no,id,nature_of_appoinment,status from clg_reg_nature_of_appoinment_mstr where status='1'" + SearchValue
                        + " ORDER BY nature_of_appoinment " + orderType + " limit " + pageL + " OFFSET " + startPage;
            } else {
                q = "select ROW_NUMBER() OVER(order by nature_of_appoinment ASC) as sr_no,id,nature_of_appoinment,status from clg_reg_nature_of_appoinment_mstr where status='" + status + "'"
                        + SearchValue + " ORDER BY nature_of_appoinment " + orderType + " limit " + pageL + " OFFSET "
                        + startPage;
            }

            PreparedStatement stmt = conn.prepareStatement(q);
            stmt = setQueryWhereClause_SQL(stmt, search, clg_appoint_name);
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

                String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
                        + rs.getString("id") + "') }else{ return false;}\"";
                f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDClgAppoint' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
                        "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
                        + "' value=" + rs.getString("id") + ">"
                        +"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("nature_of_appoinment")+"'>"

                        +"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+" ></i></a> </li>";

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
    public String updateNatureOfAppointment(CLG_REG_NATURE_OF_APPOINTMENT td) {
        Session session1 = this.sessionFactory.openSession();
        Transaction tx = session1.beginTransaction();

        String msg = "";
        try{
            String hql = "update CLG_REG_NATURE_OF_APPOINTMENT set nature_of_appoinment=:nature_of_appoinment,status=:status,modified_by=:modified_by,modified_date=:modified_date"
                    + " where id=:id";

            Query query = session1.createQuery(hql).
                    setParameter("nature_of_appoinment", td.getNature_of_appoinment())
                    .setParameter("status", (td. getStatus()))
                    .setParameter("modified_by", td.getModified_by()).
                    setParameter("modified_date", td.getModified_date())
                    .setParameter("id", td.getId());

//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
            msg = query.executeUpdate() > 0 ? "1" : "0";
            tx.commit();
        }
        catch (Exception e) {
            msg = "Data Not Updated";
            tx.rollback();
        }
        finally {
            session1.close();
        }
        return msg;
    }

    @Override
    public long DataTableClgAppointDataTotalCount(String search, String clg_appoint_name) {
        String SearchValue =GenerateQueryWhereClause_SQL(search, clg_appoint_name);

        int total = 0;
        String q = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            //Query for count page in data-table....by ruler
            //q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;


            q="select count(*) from (select id,nature_of_appoinment,status  from clg_reg_nature_of_appoinment_mstr where id!=0 and status='1' \n"
                    + SearchValue + ") ab  ";


            PreparedStatement stmt = conn.prepareStatement(q);

            stmt = setQueryWhereClause_SQL(stmt, search, clg_appoint_name);

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
        return (long) total;
    }
}
