package com.AyushEdu.Models.Registration;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_registration_dtl", uniqueConstraints = {@UniqueConstraint(columnNames = "recr_id") })
public class TB_REGISTRATION_DTL {
	
	
	
	private int recr_id;
	private String recr_name;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date recr_dob;
	private String recr_email;
	private String recr_mobileno;
	private String recr_pwd;
	private String recr_father_name;
	private String recr_mother_name;
	private String recr_aadhar_no;
	private int recr_father_title;
	private int recr_mother_title;
	private int recr_cadidate_title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entry_dt;
	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recr_id", unique = true, nullable = false)	
	public int getRecr_id() {
		return recr_id;
	}
	public void setRecr_id(int recr_id) {
		this.recr_id = recr_id;
	}
	public String getRecr_name() {
		return recr_name;
	}
	public void setRecr_name(String recr_name) {
		this.recr_name = recr_name;
	}
	public Date getRecr_dob() {
		return recr_dob;
	}
	public void setRecr_dob(Date recr_dob) {
		this.recr_dob = recr_dob;
	}
	public String getRecr_email() {
		return recr_email;
	}
	public void setRecr_email(String recr_email) {
		this.recr_email = recr_email;
	}
	public String getRecr_mobileno() {
		return recr_mobileno;
	}
	public void setRecr_mobileno(String recr_mobileno) {
		this.recr_mobileno = recr_mobileno;
	}
	public String getRecr_pwd() {
		return recr_pwd;
	}
	public void setRecr_pwd(String recr_pwd) {
		this.recr_pwd = recr_pwd;
	}
	public String getRecr_father_name() {
		return recr_father_name;
	}
	public void setRecr_father_name(String recr_father_name) {
		this.recr_father_name = recr_father_name;
	}
	public String getRecr_mother_name() {
		return recr_mother_name;
	}
	public void setRecr_mother_name(String recr_mother_name) {
		this.recr_mother_name = recr_mother_name;
	}
	public String getRecr_aadhar_no() {
		return recr_aadhar_no;
	}
	public void setRecr_aadhar_no(String recr_aadhar_no) {
		this.recr_aadhar_no = recr_aadhar_no;
	}
	public int getRecr_father_title() {
		return recr_father_title;
	}
	public void setRecr_father_title(int recr_father_title) {
		this.recr_father_title = recr_father_title;
	}
	public int getRecr_mother_title() {
		return recr_mother_title;
	}
	public void setRecr_mother_title(int recr_mother_title) {
		this.recr_mother_title = recr_mother_title;
	}
	public int getRecr_cadidate_title() {
		return recr_cadidate_title;
	}
	public void setRecr_cadidate_title(int recr_cadidate_title) {
		this.recr_cadidate_title = recr_cadidate_title;
	}
	public Date getEntry_dt() {
		return entry_dt;
	}
	public void setEntry_dt(Date entry_dt) {
		this.entry_dt = entry_dt;
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
	
}


