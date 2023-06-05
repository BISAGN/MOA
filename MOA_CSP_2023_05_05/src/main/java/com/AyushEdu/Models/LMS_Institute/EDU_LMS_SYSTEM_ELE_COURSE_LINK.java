package com.AyushEdu.Models.LMS_Institute;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_system_ele_course_link", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_SYSTEM_ELE_COURSE_LINK {
	
	private int id;
	private int system_id;
	private int elec_course_id;
	private int  degree_id;
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
	public void setId(int id) {
		this.id = id;
	}

	public int getSystem_id() {
		return system_id;
	}
	public void setSystem_id(int system_id) {
		this.system_id = system_id;
	}
	public int getElec_course_id() {
		return elec_course_id;
	}
	public void setElec_course_id(int elec_course_id) {
		this.elec_course_id = elec_course_id;
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
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	
}
