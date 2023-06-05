package com.AyushEdu.Models.Clg_Reg_clg_Infra;

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
@Table(name="clg_reg_infra_college_council", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_COLLEGE_COUNCIL {
	
	private int id;
	private int council_check;
	private String council_document;
	private int college_website;
	private Date website_update_date;
	private int cctv_status;
	private String login_url;
	private String username;
	private String password;
	private int biometric_status;
	private String college_working_hours;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int institute_id;
	private int save_status;
	private int s_id;
	
	

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCouncil_check() {
		return council_check;
	}
	public void setCouncil_check(int council_check) {
		this.council_check = council_check;
	}
	public String getCouncil_document() {
		return council_document;
	}
	public void setCouncil_document(String council_document) {
		this.council_document = council_document;
	}
	public int getCollege_website() {
		return college_website;
	}
	public void setCollege_website(int college_website) {
		this.college_website = college_website;
	}
	public Date getWebsite_update_date() {
		return website_update_date;
	}
	public void setWebsite_update_date(Date website_update_date) {
		this.website_update_date = website_update_date;
	}
	public int getCctv_status() {
		return cctv_status;
	}
	public void setCctv_status(int cctv_status) {
		this.cctv_status = cctv_status;
	}
	public String getLogin_url() {
		return login_url;
	}
	public void setLogin_url(String login_url) {
		this.login_url = login_url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBiometric_status() {
		return biometric_status;
	}
	public void setBiometric_status(int biometric_status) {
		this.biometric_status = biometric_status;
	}
	public String getCollege_working_hours() {
		return college_working_hours;
	}
	public void setCollege_working_hours(String college_working_hours) {
		this.college_working_hours = college_working_hours;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getSave_status() {
		return save_status;
	}
	public void setSave_status(int save_status) {
		this.save_status = save_status;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

}
