package com.AyushEdu.Models.Institute_NCH;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_nch_institute_reg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_NCH_INSTITUTE_REGISTRATION {

	private int id;
	private String institute_name;
	private String institute_email;
	private String institute_mob_no;
	private int country_id;
	private int state_id;
	private int district_id;
	private String code;
	private String address;
	private String status;
	private String app_status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String upload_image;
	private int university_id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public String getInstitute_email() {
		return institute_email;
	}
	public void setInstitute_email(String institute_email) {
		this.institute_email = institute_email;
	}
	public String getInstitute_mob_no() {
		return institute_mob_no;
	}
	public void setInstitute_mob_no(String institute_mob_no) {
		this.institute_mob_no = institute_mob_no;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApp_status() {
		return app_status;
	}
	public void setApp_status(String app_status) {
		this.app_status = app_status;
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
	public String getUpload_image() {
		return upload_image;
	}
	public void setUpload_image(String upload_image) {
		this.upload_image = upload_image;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	
	

}
