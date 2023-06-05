package com.AyushEdu.Models.Hosp_Staff_Detail;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="hosp_staff_details_upload_document", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HOSP_STAFF_DETAIL_UP_DOCUMENT {
	
	private int id;
	private int institute_id;
	private String attend_hospitalstaff;
	private String acquittancestaff;
	private String doctor_rosters;
	private String nurse_rosters;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttend_hospitalstaff() {
		return attend_hospitalstaff;
	}
	public void setAttend_hospitalstaff(String attend_hospitalstaff) {
		this.attend_hospitalstaff = attend_hospitalstaff;
	}
	public String getAcquittancestaff() {
		return acquittancestaff;
	}
	public void setAcquittancestaff(String acquittancestaff) {
		this.acquittancestaff = acquittancestaff;
	}
	public String getDoctor_rosters() {
		return doctor_rosters;
	}
	public void setDoctor_rosters(String doctor_rosters) {
		this.doctor_rosters = doctor_rosters;
	}
	public String getNurse_rosters() {
		return nurse_rosters;
	}
	public void setNurse_rosters(String nurse_rosters) {
		this.nurse_rosters = nurse_rosters;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
		
	
}
