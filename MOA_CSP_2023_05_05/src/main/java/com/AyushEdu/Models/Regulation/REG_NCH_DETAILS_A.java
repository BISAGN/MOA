package com.AyushEdu.Models.Regulation;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reg_nch_details_a", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class REG_NCH_DETAILS_A {
	
	private int id;
	private String ayush_id;
	private String name;
	private String dob;
	private String aadhar_card;
	private String email;
	private String mobile_no;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institude_id;
	private String admission_date;
	private int system;
	private int degree;
	private String enrollment_no;
	private String upload_date;
	private String gender;
	//private String semester;
	private String internship_completion_date;
	private int university_userid;
	private int institude_userid;
	private int reg_state;
	private String state_reg_num;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAyush_id() {
		return ayush_id;
	}
	public void setAyush_id(String ayush_id) {
		this.ayush_id = ayush_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getAdmission_date() {
		return admission_date;
	}
	public void setAdmission_date(String admission_date) {
		this.admission_date = admission_date;
	}
	public String getEnrollment_no() {
		return enrollment_no;
	}
	public void setEnrollment_no(String enrollment_no) {
		this.enrollment_no = enrollment_no;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
//	public String getSemester() {
//		return semester;
//	}
//	public void setSemester(String semester) {
//		this.semester = semester;
//	}
	public int getInstitude_id() {
		return institude_id;
	}
	public void setInstitude_id(int institude_id) {
		this.institude_id = institude_id;
	}
	public int getSystem() {
		return system;
	}
	public void setSystem(int system) {
		this.system = system;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getUniversity_userid() {
		return university_userid;
	}
	public void setUniversity_userid(int university_userid) {
		this.university_userid = university_userid;
	}
	public int getInstitude_userid() {
		return institude_userid;
	}
	public void setInstitude_userid(int institude_userid) {
		this.institude_userid = institude_userid;
	}
	public String getInternship_completion_date() {
		return internship_completion_date;
	}
	public void setInternship_completion_date(String internship_completion_date) {
		this.internship_completion_date = internship_completion_date;
	}
	public int getReg_state() {
		return reg_state;
	}
	public void setReg_state(int reg_state) {
		this.reg_state = reg_state;
	}
	public String getState_reg_num() {
		return state_reg_num;
	}
	public void setState_reg_num(String state_reg_num) {
		this.state_reg_num = state_reg_num;
	}
	
	
	

}
