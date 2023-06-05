package com.AyushEdu.Models.Degree_recognition_mstr;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_college_code_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class DG_REC_COLLEGE_CODE_MSTR {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
private int id;
	
	private String name_of_college;
	private String state;
	private String college_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_college() {
		return name_of_college;
	}
	public void setName_of_college(String name_of_college) {
		this.name_of_college = name_of_college;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCollege_id() {
		return college_id;
	}
	public void setCollege_id(String college_id) {
		this.college_id = college_id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}




}
