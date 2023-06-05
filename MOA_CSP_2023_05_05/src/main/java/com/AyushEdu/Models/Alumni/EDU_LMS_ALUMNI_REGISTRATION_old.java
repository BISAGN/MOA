package com.AyushEdu.Models.Alumni;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_alumni_registration", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_ALUMNI_REGISTRATION_old {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private String ayush_id;
	private String passing_year;
	private String alu_name;
	private String batch_id;
	private String alu_mobile_no;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAyush_id() {
		return ayush_id;
	}
	public void setAyush_id(String ayush_id) {
		this.ayush_id = ayush_id;
	}
	public String getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(String passing_year) {
		this.passing_year = passing_year;
	}
	public String getAlu_name() {
		return alu_name;
	}
	public void setAlu_name(String alu_name) {
		this.alu_name = alu_name;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getAlu_mobile_no() {
		return alu_mobile_no;
	}
	public void setAlu_mobile_no(String alu_mobile_no) {
		this.alu_mobile_no = alu_mobile_no;
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
