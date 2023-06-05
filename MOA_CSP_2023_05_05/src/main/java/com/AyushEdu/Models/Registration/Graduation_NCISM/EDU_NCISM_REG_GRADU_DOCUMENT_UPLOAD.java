package com.AyushEdu.Models.Registration.Graduation_NCISM;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="edu_ncism_reg_gradu_document_upload", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD {
	
	private int id;
	private String photograph;
	private String signature;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int reg_id;
	private int p_id;
	private String court_order;
	private String remarks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPhotograph() {
		return photograph;
	}
	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getReg_id() {
		return reg_id;
	}
	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getCourt_order() {
		return court_order;
	}
	public void setCourt_order(String court_order) {
		this.court_order = court_order;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
