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
@Table(name = "recr_category_mst", uniqueConstraints = {
@UniqueConstraint(columnNames = "category_id"),})
public class recr_category_mst {
	
	private int category_id;
	
	 @NotBlank(message="Please Enter Category")
	 @Size(min=0,max=255,message="Length of enter value must be less than or equal to 255")
	 private String category;

	 private String category_createdby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date category_createddate;
	 
	 private String category_updatedby;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date category_updateddate;
	 
	 private String del_id;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date del_dt;
	 private String status;
	 
	@Id
 	@GeneratedValue(strategy = IDENTITY)
 	@Column(name = "category_id", unique = true, nullable = false) 
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category.toUpperCase();
	}

	public String getCategory_createdby() {
		return category_createdby;
	}

	public void setCategory_createdby(String category_createdby) {
		this.category_createdby = category_createdby;
	}

	public Date getCategory_createddate() {
		return category_createddate;
	}

	public void setCategory_createddate(Date category_createddate) {
		this.category_createddate = category_createddate;
	}

	public String getCategory_updatedby() {
		return category_updatedby;
	}

	public void setCategory_updatedby(String category_updatedby) {
		this.category_updatedby = category_updatedby;
	}

	public Date getCategory_updateddate() {
		return category_updateddate;
	}

	public void setCategory_updateddate(Date category_updateddate) {
		this.category_updateddate = category_updateddate;
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
