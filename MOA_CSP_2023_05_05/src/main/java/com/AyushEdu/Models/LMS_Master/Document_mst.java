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
@Table(name = "recr_document_mst", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class Document_mst {
	
	private int id;
	private String doc_name;
	private String doc_type;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
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
	

}
