package com.AyushEdu.Models.Degree_recognition_Fellowship_Grant;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "dg_rec_fellow_ship_hom_out_india", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class DG_REC_FELLOW_SHIP_HOM_OUT_INDIA {
	
	private int id;
	private String country_id;
	private int university_name;
	private int college_id;
	private String abbreviation;
	private int medical_course_name;
	private Date validity_period;
	private int digital_code;
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
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public int getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(int university_name) {
		this.university_name = university_name;
	}
	public int getCollege_id() {
		return college_id;
	}
	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public int getMedical_course_name() {
		return medical_course_name;
	}
	public void setMedical_course_name(int medical_course_name) {
		this.medical_course_name = medical_course_name;
	}
	public Date getValidity_period() {
		return validity_period;
	}
	public void setValidity_period(Date validity_period) {
		this.validity_period = validity_period;
	}
	public int getDigital_code() {
		return digital_code;
	}
	public void setDigital_code(int digital_code) {
		this.digital_code = digital_code;
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

	

}
