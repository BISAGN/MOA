package com.AyushEdu.Models.Exp_Excel;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_faculty_nch", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_FACULTY_NCH {
	
	private int id;
	private String username;
	private String userid;
	private String aadhar_card;
	private String email;
	private String mobile_no;
	private String role;
	private int university_id;
	private int institute_id;
	private int state_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String teacher_code;
	private String ayush_id_ncism;
	private int status;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAadhar_card() {
		return aadhar_card;
	}
	public void setAadhar_card(String aadhar_card) {
		this.aadhar_card = aadhar_card;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	

	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTeacher_code() {
		return teacher_code;
	}
	public void setTeacher_code(String teacher_code) {
		this.teacher_code = teacher_code;
	}
	public String getAyush_id_ncism() {
		return ayush_id_ncism;
	}
	public void setAyush_id_ncism(String ayush_id_ncism) {
		this.ayush_id_ncism = ayush_id_ncism;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	
	
	
	

}
