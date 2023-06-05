package com.AyushEdu.Models.Masters;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mmarital_status", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_MSTR_MMARITAL_STATUS {

	private int id;
	@NotBlank(message = "Please Enter Marital Code")
	@Size(min = 0, max = 1, message = "Length of enter value must be less than or equal to 1")
	private String marital_cd;
	@Size(min = 0, max = 10, message = "Length of enter value must be less than or equal to 10")
	@NotBlank(message = "Please Enter Marital Name")
	private String marital_desc;
	@NotBlank(message = "Please Enter Marital Description")
	private String status;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	/*
	 * private String entry_by;
	 * 
	 * @DateTimeFormat(pattern = "yyyy-MM-dd") private Date entry_dt;
	 */
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_dt;
	private String del_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date del_dt;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarital_cd() {
		return marital_cd;
	}
	public void setMarital_cd(String marital_cd) {
		this.marital_cd = marital_cd;
	}
	public String getMarital_desc() {
		return marital_desc;
	}
	public void setMarital_desc(String marital_desc) {
		this.marital_desc = marital_desc;
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
	public String getDel_by() {
		return del_by;
	}
	public void setDel_by(String del_by) {
		this.del_by = del_by;
	}
	public Date getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(Date del_dt) {
		this.del_dt = del_dt;
	}

	
}
