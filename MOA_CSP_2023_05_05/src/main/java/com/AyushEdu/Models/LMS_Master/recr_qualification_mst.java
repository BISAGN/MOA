package com.AyushEdu.Models.LMS_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "recr_qualification_mst", uniqueConstraints = { @UniqueConstraint(columnNames = "qualification_id"), })
public class recr_qualification_mst {
	
	 private int qualification_id;
	
	 //private String qualifiation_details;	 
	 private String qualification_createdby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date qualification_createddate;
	 
	 private String qualification_updatedby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date qualification_updateddate;
	 
	 private String del_id;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date del_dt;
	 private String status;
	  private String qualification;
	 
	
	 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "qualification_id", unique = true, nullable = false) 
	public int getQualification_id() {
		return qualification_id;
	}
	public void setQualification_id(int qualification_id) {
		this.qualification_id = qualification_id;
	}
	

	public String getQualification_createdby() {
		return qualification_createdby;
	}
	public void setQualification_createdby(String qualification_createdby) {
		this.qualification_createdby = qualification_createdby;
	}
	public Date getQualification_createddate() {
		return qualification_createddate;
	}
	public void setQualification_createddate(Date qualification_createddate) {
		this.qualification_createddate = qualification_createddate;
	}
	public String getQualification_updatedby() {
		return qualification_updatedby;
	}
	public void setQualification_updatedby(String qualification_updatedby) {
		this.qualification_updatedby = qualification_updatedby;
	}
	public Date getQualification_updateddate() {
		return qualification_updateddate;
	}
	public void setQualification_updateddate(Date qualification_updateddate) {
		this.qualification_updateddate = qualification_updateddate;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public Date getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(Date del_dt) {
		this.del_dt = del_dt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
	


}
