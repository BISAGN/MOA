package com.AyushEdu.Models.Policy_Model_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_ply_mst_subpolicycategory", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_SUBPOLICYCATEGORY {
	
	
	private int id;
	private String subcategory;
	private String status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private String policycategory;
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
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
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
	public String getPolicycategory() {
		return policycategory;
	}
	public void setPolicycategory(String policycategory) {
		this.policycategory = policycategory;
	}
	

}
