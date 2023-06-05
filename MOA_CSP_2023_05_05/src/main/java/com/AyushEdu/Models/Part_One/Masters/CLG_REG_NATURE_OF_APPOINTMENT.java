package com.AyushEdu.Models.Part_One.Masters;


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clg_reg_nature_of_appoinment_mstr", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class CLG_REG_NATURE_OF_APPOINTMENT {

    private int id;
    private String nature_of_appoinment;
    private Integer status;
    private String created_by;
    private Date created_date;
    private String modified_by;
    private Date modified_date;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public Date getCreated_date() {
        return created_date;
    }
    public void setCreated_date(Date created_dt) {
        this.created_date = created_dt;
    }
    public Date getModified_date() {
        return modified_date;
    }
    public void setModified_date(Date modified_dt) {
        this.modified_date = modified_dt;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getModified_by() {
        return modified_by;
    }


    public String getNature_of_appoinment() {
        return nature_of_appoinment;
    }

    public void setNature_of_appoinment(String nature_of_appoinment) {
        this.nature_of_appoinment = nature_of_appoinment;
    }

    public String getCreated_by() {
        return created_by;
    }
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }


}
