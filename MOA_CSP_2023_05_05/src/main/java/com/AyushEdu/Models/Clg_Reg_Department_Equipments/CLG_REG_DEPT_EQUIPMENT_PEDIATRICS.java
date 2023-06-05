package com.AyushEdu.Models.Clg_Reg_Department_Equipments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_dept_equipment_pediatrics", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_DEPT_EQUIPMENT_PEDIATRICS {
	
	private int id;
	private int p_id;
	private String total_charts;
	private String total_models;
	private String num_depart_lib;
	private String other_items;
	private int inst_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getTotal_charts() {
		return total_charts;
	}
	public void setTotal_charts(String total_charts) {
		this.total_charts = total_charts;
	}
	public String getTotal_models() {
		return total_models;
	}
	public void setTotal_models(String total_models) {
		this.total_models = total_models;
	}
	public String getNum_depart_lib() {
		return num_depart_lib;
	}
	public void setNum_depart_lib(String num_depart_lib) {
		this.num_depart_lib = num_depart_lib;
	}
	public String getOther_items() {
		return other_items;
	}
	public void setOther_items(String other_items) {
		this.other_items = other_items;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
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
	
}
