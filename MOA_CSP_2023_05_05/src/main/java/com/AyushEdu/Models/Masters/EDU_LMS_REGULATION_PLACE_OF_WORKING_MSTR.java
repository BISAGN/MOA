package com.AyushEdu.Models.Masters;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_regulation_place_of_working_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR {
	
	private int id;
	private String place_of_working_practitioner;
	private String status;
	private Date created_date ;
	private String created_by;
	private Date modified_date ;
	private String modified_by;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace_of_working_practitioner() {
		return place_of_working_practitioner;
	}
	public void setPlace_of_working_practitioner(String place_of_working_practitioner) {
		this.place_of_working_practitioner = place_of_working_practitioner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
	
}
