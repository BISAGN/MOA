package com.AyushEdu.Models.LMS_Institute;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_institute_reg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_INSTITUTE_REGISTRATION {

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
	private String college_unique_id;
	private String college_abbr;
	private int system_id;
	private int no_of_part;
	private int total_sanctioned_seat;
	private int dashboard_status;
	
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
	public String getCollege_unique_id() {
		return college_unique_id;
	}
	public void setCollege_unique_id(String college_unique_id) {
		this.college_unique_id = college_unique_id;
	}
	public String getCollege_abbr() {
		return college_abbr;
	}
	public void setCollege_abbr(String college_abbr) {
		this.college_abbr = college_abbr;
	}
	public int getSystem_id() {
		return system_id;
	}
	public void setSystem_id(int system_id) {
		this.system_id = system_id;
	}
	public int getNo_of_part() {
		return no_of_part;
	}
	public void setNo_of_part(int no_of_part) {
		this.no_of_part = no_of_part;
	}
	public int getTotal_sanctioned_seat() {
		return total_sanctioned_seat;
	}
	public void setTotal_sanctioned_seat(int total_sanctioned_seat) {
		this.total_sanctioned_seat = total_sanctioned_seat;
	}
	public int getDashboard_status() {
		return dashboard_status;
	}
	public void setDashboard_status(int dashboard_status) {
		this.dashboard_status = dashboard_status;
	}
	
}
