package com.AyushEdu.Models.Clg_Reg_hospital_Infra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_hosp_ot", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_HOSP_OT {

	private int id;
	private String ot_department_name;
	private int available_area;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	private int department_id;
	private int status;
	private int total_avail_area;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOt_department_name() {
		return ot_department_name;
	}
	public void setOt_department_name(String ot_department_name) {
		this.ot_department_name = ot_department_name;
	}
	public int getAvailable_area() {
		return available_area;
	}
	public void setAvailable_area(int available_area) {
		this.available_area = available_area;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotal_avail_area() {
		return total_avail_area;
	}
	public void setTotal_avail_area(int total_avail_area) {
		this.total_avail_area = total_avail_area;
	}
	
	
	
	
}
