package com.AyushEdu.Models.Part_One.Masters;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clg_reg_hosp_infra_dept_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class CLG_REG_HOSP_INFRA_DEPT_MSTR {
	
	private int id;
	private String hospital_department_name;
	private Integer status;
	private Integer hos_department_id;
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


	public String getHospital_department_name() {
		return hospital_department_name;
	}


	public void setHospital_department_name(String hospital_department_name) {
		this.hospital_department_name = hospital_department_name;
	}


	public Integer getHos_department_id() {
		return hos_department_id;
	}


	public void setHos_department_id(Integer hos_department_id) {
		this.hos_department_id = hos_department_id;
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


	public void setId(int id) {
		this.id = id;
	}
	
}
