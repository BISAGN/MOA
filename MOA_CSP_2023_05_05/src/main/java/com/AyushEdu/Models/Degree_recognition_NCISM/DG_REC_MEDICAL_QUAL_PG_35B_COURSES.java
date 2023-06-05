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
@Table(name = "dg_rec_medical_qual_pg_35b_courses", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_MEDICAL_QUAL_PG_35B_COURSES {

	private int id;
	private String pg_sub;
	private String nom_of_degree;
	private String abbr_of_degree;
	private String year_of_addmission;
	private String month_of_passing;
	private String issue_of_provisional;
	private Date date_of_latter;
	private String ref_of_latter;
	private int institute_status;
	private int university_status;
	private int ncism_status;
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPg_sub() {
		return pg_sub;
	}
	public void setPg_sub(String pg_sub) {
		this.pg_sub = pg_sub;
	}
	public String getNom_of_degree() {
		return nom_of_degree;
	}
	public void setNom_of_degree(String nom_of_degree) {
		this.nom_of_degree = nom_of_degree;
	}
	public String getAbbr_of_degree() {
		return abbr_of_degree;
	}
	public void setAbbr_of_degree(String abbr_of_degree) {
		this.abbr_of_degree = abbr_of_degree;
	}
	public String getMonth_of_passing() {
		return month_of_passing;
	}
	public void setMonth_of_passing(String month_of_passing) {
		this.month_of_passing = month_of_passing;
	}
	public String getIssue_of_provisional() {
		return issue_of_provisional;
	}
	public void setIssue_of_provisional(String issue_of_provisional) {
		this.issue_of_provisional = issue_of_provisional;
	}
	public Date getDate_of_latter() {
		return date_of_latter;
	}
	public void setDate_of_latter(Date date_of_latter) {
		this.date_of_latter = date_of_latter;
	}
	public String getRef_of_latter() {
		return ref_of_latter;
	}
	public void setRef_of_latter(String ref_of_latter) {
		this.ref_of_latter = ref_of_latter;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getYear_of_addmission() {
		return year_of_addmission;
	}
	public void setYear_of_addmission(String year_of_addmission) {
		this.year_of_addmission = year_of_addmission;
	}
	
}
