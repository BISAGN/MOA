package com.AyushEdu.Models.LMS_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_statelogo_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_STATE_LOGO_MSTR {
	
	private int id;
	private int state_id;
	private String state_logo;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String status;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_logo() {
		return state_logo;
	}
	public void setState_logo(String state_logo) {
		this.state_logo = state_logo;
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
