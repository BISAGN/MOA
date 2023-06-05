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
@Table(name="clg_reg_dept_equip_anatomy_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_DEPT_EQUIP_ANATOMY_CHILD {
	
	private int id;
	private int p_id;
	
	private String total_add_item;
	private String total_cadavers_avai;
	private int anatomy_act;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	
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
	public String getTotal_add_item() {
		return total_add_item;
	}
	public void setTotal_add_item(String total_add_item) {
		this.total_add_item = total_add_item;
	}
	public String getTotal_cadavers_avai() {
		return total_cadavers_avai;
	}
	public void setTotal_cadavers_avai(String total_cadavers_avai) {
		this.total_cadavers_avai = total_cadavers_avai;
	}
	public int getAnatomy_act() {
		return anatomy_act;
	}
	public void setAnatomy_act(int anatomy_act) {
		this.anatomy_act = anatomy_act;
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
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	
	

	
	
	
	
	
	

}
