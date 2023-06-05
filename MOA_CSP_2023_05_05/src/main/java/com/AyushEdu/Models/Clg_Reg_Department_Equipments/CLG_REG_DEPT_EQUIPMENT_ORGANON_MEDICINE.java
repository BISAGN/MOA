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
@Table(name="clg_reg_dept_equipment_organon_medicine", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE {
	
	private int id;
	private int p_id;
	private String founder_philoso_name;
	private String photo_path;
	private Date date_of_birth;
	private Date date_of_death;
	private String available_num_quant;
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
	public String getFounder_philoso_name() {
		return founder_philoso_name;
	}
	public void setFounder_philoso_name(String founder_philoso_name) {
		this.founder_philoso_name = founder_philoso_name;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_death() {
		return date_of_death;
	}
	public void setDate_of_death(Date date_of_death) {
		this.date_of_death = date_of_death;
	}
	public String getAvailable_num_quant() {
		return available_num_quant;
	}
	public void setAvailable_num_quant(String available_num_quant) {
		this.available_num_quant = available_num_quant;
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
