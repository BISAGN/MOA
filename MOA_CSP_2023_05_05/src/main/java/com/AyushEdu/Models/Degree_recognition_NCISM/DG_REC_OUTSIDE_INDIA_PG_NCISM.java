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
@Table(name = "dg_rec_outside_india_pg_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class DG_REC_OUTSIDE_INDIA_PG_NCISM {
	
	private int id;
	private String inst_cod_coures_name;
	private String aprrove_courses_name;
	private String aff_uni_name;;
	private String university_address;
	private String registered_email_id;
	private String con_person_name;
	private int con_person_desg;
	private String con_person_mob_no;
	private String con_person_email_id;
	private int medical_stream;
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
	
	public String getInst_cod_coures_name() {
		return inst_cod_coures_name;
	}
	public void setInst_cod_coures_name(String inst_cod_coures_name) {
		this.inst_cod_coures_name = inst_cod_coures_name;
	}
	public String getAprrove_courses_name() {
		return aprrove_courses_name;
	}
	public void setAprrove_courses_name(String aprrove_courses_name) {
		this.aprrove_courses_name = aprrove_courses_name;
	}
	public String getAff_uni_name() {
		return aff_uni_name;
	}
	public void setAff_uni_name(String aff_uni_name) {
		this.aff_uni_name = aff_uni_name;
	}
	public String getUniversity_address() {
		return university_address;
	}
	public void setUniversity_address(String university_address) {
		this.university_address = university_address;
	}
	public String getRegistered_email_id() {
		return registered_email_id;
	}
	public void setRegistered_email_id(String registered_email_id) {
		this.registered_email_id = registered_email_id;
	}
	public String getCon_person_name() {
		return con_person_name;
	}
	public void setCon_person_name(String con_person_name) {
		this.con_person_name = con_person_name;
	}
	public int getCon_person_desg() {
		return con_person_desg;
	}
	public void setCon_person_desg(int con_person_desg) {
		this.con_person_desg = con_person_desg;
	}
	public String getCon_person_mob_no() {
		return con_person_mob_no;
	}
	public void setCon_person_mob_no(String con_person_mob_no) {
		this.con_person_mob_no = con_person_mob_no;
	}
	public String getCon_person_email_id() {
		return con_person_email_id;
	}
	public void setCon_person_email_id(String con_person_email_id) {
		this.con_person_email_id = con_person_email_id;
	}
	public int getMedical_stream() {
		return medical_stream;
	}
	public void setMedical_stream(int medical_stream) {
		this.medical_stream = medical_stream;
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


}
