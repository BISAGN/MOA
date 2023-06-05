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
@Table(name="hd_inq_link_role_mstr", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HD_INQ_LINK_ROLE_MSTR {
	
	private int id;
	private int role;
	private int user_id1;
	private String inq_no;
	private String created_by;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	private String modified_by;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_dt;
	private int status;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getInq_no() {
		return inq_no;
	}
	public void setInq_no(String inq_no) {
		this.inq_no = inq_no;
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

	public int getUser_id1() {
		return user_id1;
	}
	public void setUser_id1(int user_id1) {
		this.user_id1 = user_id1;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
