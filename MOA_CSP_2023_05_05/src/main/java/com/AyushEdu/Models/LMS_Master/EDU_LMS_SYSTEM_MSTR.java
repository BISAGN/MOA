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
@Table(name = "edu_lms_system_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_SYSTEM_MSTR {
	
	private int id;
	private String system_name;
	private String status;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private String created_role;
	private String system_abbr;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
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
	public String getCreated_role() {
		return created_role;
	}
	public void setCreated_role(String created_role) {
		this.created_role = created_role;
	}
	public String getSystem_abbr() {
		return system_abbr;
	}
	public void setSystem_abbr(String system_abbr) {
		this.system_abbr = system_abbr;
	}
	

}
