package com.AyushEdu.Models.Registration;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


	

@Entity
@Table(name = "edu_reg_eform_nch", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_REG_EFORM_NCH {
	
	private int id;
	private String institute_id;
	private int name_of_institute;
	private int state_id;
	private String cand_name;
	private String mother_name;
	private String father_name;
	private Date dob;
	private String neet_application_no;
	private String neet_roll_no;
	private String neet_rank;
	private String neet_percentile;
	private String neet_marks;
	private int quota_id;
	private int counselling_authority;
	private int category_id;
	private String email;
	private String modified_by;
	private Date modified_date;
	private String created_by;
	private Date created_date;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(String institute_id) {
		this.institute_id = institute_id;
	}
	public int getName_of_institute() {
		return name_of_institute;
	}
	public void setName_of_institute(int name_of_institute) {
		this.name_of_institute = name_of_institute;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getCand_name() {
		return cand_name;
	}
	public void setCand_name(String cand_name) {
		this.cand_name = cand_name;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNeet_application_no() {
		return neet_application_no;
	}
	public void setNeet_application_no(String neet_application_no) {
		this.neet_application_no = neet_application_no;
	}
	public String getNeet_roll_no() {
		return neet_roll_no;
	}
	public void setNeet_roll_no(String neet_roll_no) {
		this.neet_roll_no = neet_roll_no;
	}
	public String getNeet_rank() {
		return neet_rank;
	}
	public void setNeet_rank(String neet_rank) {
		this.neet_rank = neet_rank;
	}
	public String getNeet_percentile() {
		return neet_percentile;
	}
	public void setNeet_percentile(String neet_percentile) {
		this.neet_percentile = neet_percentile;
	}
	public String getNeet_marks() {
		return neet_marks;
	}
	public void setNeet_marks(String neet_marks) {
		this.neet_marks = neet_marks;
	}
	public int getQuota_id() {
		return quota_id;
	}
	public void setQuota_id(int quota_id) {
		this.quota_id = quota_id;
	}
	public int getCounselling_authority() {
		return counselling_authority;
	}
	public void setCounselling_authority(int counselling_authority) {
		this.counselling_authority = counselling_authority;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
