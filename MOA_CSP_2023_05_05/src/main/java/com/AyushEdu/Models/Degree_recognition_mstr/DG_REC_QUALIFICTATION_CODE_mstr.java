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
@Table(name = "dg_rec_qualification_code_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class DG_REC_QUALIFICTATION_CODE_mstr {

	private int id;
	private String qualification_name;
	private String codes;
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
	public String getQualification_name() {
		return qualification_name;
	}
	public void setQualification_name(String qualification_name) {
		this.qualification_name = qualification_name;
	}
	
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
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
