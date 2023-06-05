package com.AyushEdu.Models.Degree_recognition_NCISM;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_within_india_pg_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })


public class DG_REC_WITHIN_INDIA_PG {
	
	
	private int id ;
	private String aff_university_name ;
	private String reg_email ;
	private  String cont_name;
	private String cont_designation;
	private String cont_mobile_no;
	private String cont_email_id ;
	private String medical_system ;
	private String insti_name;
	private int status;
	private int user_id;
	private int institute_status;
	private int university_status;
	private int ncism_status;
	private String created_by;
	private Date created_date ;
	private String  modified_by ;
	private Date modified_date ;
	private String aff_university_address;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAff_university_name() {
		return aff_university_name;
	}
	public void setAff_university_name(String aff_university_name) {
		this.aff_university_name = aff_university_name;
	}
	public String getReg_email() {
		return reg_email;
	}
	public void setReg_email(String reg_email) {
		this.reg_email = reg_email;
	}
	public String getCont_name() {
		return cont_name;
	}
	public void setCont_name(String cont_name) {
		this.cont_name = cont_name;
	}
	public String getCont_designation() {
		return cont_designation;
	}
	public void setCont_designation(String cont_designation) {
		this.cont_designation = cont_designation;
	}
	
	public String getCont_mobile_no() {
		return cont_mobile_no;
	}
	public void setCont_mobile_no(String cont_mobile_no) {
		this.cont_mobile_no = cont_mobile_no;
	}
	public String getCont_email_id() {
		return cont_email_id;
	}
	public void setCont_email_id(String cont_email_id) {
		this.cont_email_id = cont_email_id;
	}
	
	public String getMedical_system() {
		return medical_system;
	}
	public void setMedical_system(String medical_system) {
		this.medical_system = medical_system;
	}
	public String getInsti_name() {
		return insti_name;
	}
	public void setInsti_name(String insti_name) {
		this.insti_name = insti_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getInstitute_status() {
		return institute_status;
	}
	public void setInstitute_status(int institute_status) {
		this.institute_status = institute_status;
	}
	
	public int getUniversity_status() {
		return university_status;
	}
	public void setUniversity_status(int university_status) {
		this.university_status = university_status;
	}
	public int getNcism_status() {
		return ncism_status;
	}
	public void setNcism_status(int ncism_status) {
		this.ncism_status = ncism_status;
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
	public String getAff_university_address() {
		return aff_university_address;
	}
	public void setAff_university_address(String aff_university_address) {
		this.aff_university_address = aff_university_address;
	}
}
