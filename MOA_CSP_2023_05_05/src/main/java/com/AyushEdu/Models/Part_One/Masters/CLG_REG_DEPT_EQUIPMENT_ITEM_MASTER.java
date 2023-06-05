package com.AyushEdu.Models.Part_One.Masters;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clg_reg_dept_equipment_item_mstr", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER {

    private int id;
    private String item_name;
    private Integer status;
    private String created_by;
    private Date created_date;
    private String modified_by;
    private Date modified_date;
    private Integer dept_equip_item_id;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public Integer getDept_equip_item_id() {
        return dept_equip_item_id;
    }

    public void setDept_equip_item_id(Integer dept_equip_item_id) {
        this.dept_equip_item_id = dept_equip_item_id;
    }
}
