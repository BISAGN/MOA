package com.AyushEdu.Models.helpdeskINQ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="hd_inq_cat_mstr", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HD_INQUIRY_CATEGORY_MSTR {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false) 
	private int id;
	private String inq_cat;
	private int type;
	private int status;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_dt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInq_cat() {
		return inq_cat;
	}
	public void setInq_cat(String inq_cat) {
		this.inq_cat = inq_cat;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
	
	
}
