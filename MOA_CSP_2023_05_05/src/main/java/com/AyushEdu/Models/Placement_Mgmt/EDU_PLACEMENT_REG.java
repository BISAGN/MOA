package com.AyushEdu.Models.Placement_Mgmt;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_placement_reg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_PLACEMENT_REG {
	
	private int id;
	private String first_name;
	private String father_name;
	private String photo_path;
	private Date dob;
	private String gender;
	private String mo_no;
	private String alt_no;
	private String email_id;
	private String add_line1;
//	private String curr_add;
	private int state=0;
	private int per_district=0;
	private String pincode;
	private String gp_title;
	private String fm_name;
	private String fm_email;
	private String im_name;
	private String fi_email;
	private String im_designation;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int job_seekers;
	private String upload_cv;
	private int intership_dur;
	private String stipend;
	private String intern_hours_from;
	private String intern_hours_to;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMo_no() {
		return mo_no;
	}
	public void setMo_no(String mo_no) {
		this.mo_no = mo_no;
	}
	public String getAlt_no() {
		return alt_no;
	}
	public void setAlt_no(String alt_no) {
		this.alt_no = alt_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getAdd_line1() {
		return add_line1;
	}
	public void setAdd_line1(String add_line1) {
		this.add_line1 = add_line1;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPer_district() {
		return per_district;
	}
	public void setPer_district(int per_district) {
		this.per_district = per_district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getGp_title() {
		return gp_title;
	}
	public void setGp_title(String gp_title) {
		this.gp_title = gp_title;
	}
	public String getFm_name() {
		return fm_name;
	}
	public void setFm_name(String fm_name) {
		this.fm_name = fm_name;
	}
	public String getFm_email() {
		return fm_email;
	}
	public void setFm_email(String fm_email) {
		this.fm_email = fm_email;
	}
	public String getIm_name() {
		return im_name;
	}
	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}
	public String getFi_email() {
		return fi_email;
	}
	public void setFi_email(String fi_email) {
		this.fi_email = fi_email;
	}
	public String getIm_designation() {
		return im_designation;
	}
	public void setIm_designation(String im_designation) {
		this.im_designation = im_designation;
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
	public int getJob_seekers() {
		return job_seekers;
	}
	public void setJob_seekers(int job_seekers) {
		this.job_seekers = job_seekers;
	}
	public String getUpload_cv() {
		return upload_cv;
	}
	public void setUpload_cv(String upload_cv) {
		this.upload_cv = upload_cv;
	}
	public int getIntership_dur() {
		return intership_dur;
	}
	public void setIntership_dur(int intership_dur) {
		this.intership_dur = intership_dur;
	}
	public String getStipend() {
		return stipend;
	}
	public void setStipend(String stipend) {
		this.stipend = stipend;
	}
	public String getIntern_hours_from() {
		return intern_hours_from;
	}
	public void setIntern_hours_from(String intern_hours_from) {
		this.intern_hours_from = intern_hours_from;
	}
	public String getIntern_hours_to() {
		return intern_hours_to;
	}
	public void setIntern_hours_to(String intern_hours_to) {
		this.intern_hours_to = intern_hours_to;
	}

	
//	public String getCurr_add() {
//		return curr_add;
//	}
//	public void setCurr_add(String curr_add) {
//		this.curr_add = curr_add;
//	}
	

	

}
