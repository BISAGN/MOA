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
@Table(name = "reg_nch_practitioner_signup_details", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class REG_NCH_PRACTITIONER_Signup_Details {
	
	private int id;
	private String name;
	private Date dob;
	private String aadhar_card;
	private String email;
	private String mobile_no;
	private Date upload_date;
	private String gender;
	private Date internship_completion_date;
	private int reg_state;
	private String state_reg_num;
	private int status;
	private int system_id;
	private int university_id;
	private int institute_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private Date date_of_admission;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
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
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getInternship_completion_date() {
		return internship_completion_date;
	}
	public void setInternship_completion_date(Date internship_completion_date) {
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
	public int getSystem_id() {
		return system_id;
	}
	public void setSystem_id(int system_id) {
		this.system_id = system_id;
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
	public Date getDate_of_admission() {
		return date_of_admission;
	}
	public void setDate_of_admission(Date date_of_admission) {
		this.date_of_admission = date_of_admission;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	
	
}
