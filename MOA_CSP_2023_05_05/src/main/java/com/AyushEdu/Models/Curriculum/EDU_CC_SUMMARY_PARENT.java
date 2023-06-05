package com.AyushEdu.Models.Curriculum;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "edu_cc_summary_parent", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_CC_SUMMARY_PARENT {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private Integer system_id; 
	private Integer degree_id; 
	private Integer professional_id; 
	private Integer course_id; 
	private Integer status; 
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
	public Integer getSystem_id() {
		return system_id;
	}
	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}
	public Integer getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(Integer degree_id) {
		this.degree_id = degree_id;
	}
	public Integer getProfessional_id() {
		return professional_id;
	}
	public void setProfessional_id(Integer professional_id) {
		this.professional_id = professional_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
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
	
}
