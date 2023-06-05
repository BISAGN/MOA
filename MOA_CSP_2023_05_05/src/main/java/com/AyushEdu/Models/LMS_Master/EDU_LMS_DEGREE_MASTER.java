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
@Table(name = "edu_lms_degree_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_DEGREE_MASTER {
	
	
	private int id;
	private String degree_name;
	private String semester;
	private String degree_code;
	private String status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int type_of_degree;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getDegree_code() {
		return degree_code;
	}
	public void setDegree_code(String degree_code) {
		this.degree_code = degree_code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public int getType_of_degree() {
		return type_of_degree;
	}
	public void setType_of_degree(int type_of_degree) {
		this.type_of_degree = type_of_degree;
	}
	
	

}
