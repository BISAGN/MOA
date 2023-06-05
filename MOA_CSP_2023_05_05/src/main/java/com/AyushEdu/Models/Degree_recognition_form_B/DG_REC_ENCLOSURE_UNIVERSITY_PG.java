package com.AyushEdu.Models.Degree_recognition_form_B;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "dg_rec_enclosure_university_pg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })


public class DG_REC_ENCLOSURE_UNIVERSITY_PG {
	
	private int id;
	private String upload_file;
	private Date en_date;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int university_id;
	private int  int_status;
	private int university_approved_status;
	private int council_approved_status;
	private String current_month_year;
	private int User_id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}
	public Date getEn_date() {
		return en_date;
	}
	public void setEn_date(Date en_date) {
		this.en_date = en_date;
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
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public int getInt_status() {
		return int_status;
	}
	public void setInt_status(int int_status) {
		this.int_status = int_status;
	}
	public int getUniversity_approved_status() {
		return university_approved_status;
	}
	public void setUniversity_approved_status(int university_approved_status) {
		this.university_approved_status = university_approved_status;
	}
	public int getCouncil_approved_status() {
		return council_approved_status;
	}
	public void setCouncil_approved_status(int council_approved_status) {
		this.council_approved_status = council_approved_status;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
	
}
