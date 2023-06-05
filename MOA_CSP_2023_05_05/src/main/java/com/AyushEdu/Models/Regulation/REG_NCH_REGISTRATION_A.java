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
@Table(name = "reg_nch_registration_a", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class REG_NCH_REGISTRATION_A {

	private int id;
	//private int registration;
	private String aayushid;
	private String name;
	private String email_id;
	private String institute_name;
	private int institute_state;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	//private String nrh_no; 
	private String regisration_state;
	//private String registration_district;
	private int user_id;
	private String aadhaar_no;
	private String abha_no;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getRegistration() {
//		return registration;
//	}
//	public void setRegistration(int registration) {
//		this.registration = registration;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	 
	public int getInstitute_state() {
		return institute_state;
	}
	public void setInstitute_state(int institute_state) {
		this.institute_state = institute_state;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAayushid() {
		return aayushid;
	}
	public void setAayushid(String aayushid) {
		this.aayushid = aayushid;
	}
//	public String getNrh_no() {
//		return nrh_no;
//	}
//	public void setNrh_no(String nrh_no) {
//		this.nrh_no = nrh_no;
//	}
	public String getRegisration_state() {
		return regisration_state;
	}
	public void setRegisration_state(String regisration_state) {
		this.regisration_state = regisration_state;
	}
	 
//	public String getRegistration_district() {
//		return registration_district;
//	}
//	public void setRegistration_district(String registration_district) {
//		this.registration_district = registration_district;
//	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAadhaar_no() {
		return aadhaar_no;
	}
	public void setAadhaar_no(String aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}
	public String getAbha_no() {
		return abha_no;
	}
	public void setAbha_no(String abha_no) {
		this.abha_no = abha_no;
	}
	
	
	
}
