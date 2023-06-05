package com.AyushEdu.Models.Part_One.Masters;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clg_reg_last_year_teacher_dtls_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR {
	private int id;
	private String teacher_dtls;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private Integer status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacher_dtls() {
		return teacher_dtls;
	}
	public void setTeacher_dtls(String teacher_dtls) {
		this.teacher_dtls = teacher_dtls;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}	
	
}
