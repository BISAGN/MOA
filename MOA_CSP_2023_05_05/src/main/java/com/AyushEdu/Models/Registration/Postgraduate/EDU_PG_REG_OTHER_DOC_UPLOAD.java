package com.AyushEdu.Models.Registration.Postgraduate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="edu_pg_reg_other_doc_upload", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_PG_REG_OTHER_DOC_UPLOAD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String doc_name;
	private String upload_document;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int p_id;
	
	
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
	public String getUpload_document() {
		return upload_document;
	}
	public void setUpload_document(String upload_document) {
		this.upload_document = upload_document;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
}
