package com.AyushEdu.Models.LMS_Master;

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
@Table(name = "recr_nationality_mst", uniqueConstraints = {
@UniqueConstraint(columnNames = "nationality_id"),})
public class recr_nationality_mst {
	
	 private int nationality_id;
		
	 @NotBlank(message="Please Enter Nationality")
	 @Size(min=0,max=15,message="Length of enter value must be less than or equal to 15")
	 private String nationality;

	 private String nationality_createdby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date nationality_createddate;
	 
	 private String nationality_updatedby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date nationality_updateddate;
	 
	 private String del_id;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date del_dt;
	 private String status;
	 
	 
	@Id
 	@GeneratedValue(strategy = IDENTITY)
 	@Column(name = "nationality_id", unique = true, nullable = false) 
	public int getNationality_id() {
		return nationality_id;
	}
	public void setNationality_id(int nationality_id) {
		this.nationality_id = nationality_id;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality.toUpperCase();
	}
	public String getNationality_createdby() {
		return nationality_createdby;
	}
	public void setNationality_createdby(String nationality_createdby) {
		this.nationality_createdby = nationality_createdby;
	}
	public Date getNationality_createddate() {
		return nationality_createddate;
	}
	public void setNationality_createddate(Date nationality_createddate) {
		this.nationality_createddate = nationality_createddate;
	}
	public String getNationality_updatedby() {
		return nationality_updatedby;
	}
	public void setNationality_updatedby(String nationality_updatedby) {
		this.nationality_updatedby = nationality_updatedby;
	}
	public Date getNationality_updateddate() {
		return nationality_updateddate;
	}
	public void setNationality_updateddate(Date nationality_updateddate) {
		this.nationality_updateddate = nationality_updateddate;
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
}
