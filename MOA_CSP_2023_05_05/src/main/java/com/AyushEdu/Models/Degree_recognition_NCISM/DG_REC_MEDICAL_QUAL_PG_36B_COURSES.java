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
@Table(name = "dg_rec_medical_qual_pg_36b_courses", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class DG_REC_MEDICAL_QUAL_PG_36B_COURSES {
	
	private int id;
	private String pg_subject;
	private String nom_degree;
	private String abb_degree;
	private String m_y_add_fir_bat;;
	private String m_y_exam_fir_bat;
	private String m_y_pro_cer;
	private Date pg_diploma_date;
	private String ref_letter_per;
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
	public String getPg_subject() {
		return pg_subject;
	}
	public void setPg_subject(String pg_subject) {
		this.pg_subject = pg_subject;
	}
	
	public String getM_y_add_fir_bat() {
		return m_y_add_fir_bat;
	}
	public void setM_y_add_fir_bat(String m_y_add_fir_bat) {
		this.m_y_add_fir_bat = m_y_add_fir_bat;
	}
	public String getM_y_exam_fir_bat() {
		return m_y_exam_fir_bat;
	}
	public void setM_y_exam_fir_bat(String m_y_exam_fir_bat) {
		this.m_y_exam_fir_bat = m_y_exam_fir_bat;
	}
	public String getM_y_pro_cer() {
		return m_y_pro_cer;
	}
	public void setM_y_pro_cer(String m_y_pro_cer) {
		this.m_y_pro_cer = m_y_pro_cer;
	}
	
	public String getNom_degree() {
		return nom_degree;
	}
	public void setNom_degree(String nom_degree) {
		this.nom_degree = nom_degree;
	}
	public String getAbb_degree() {
		return abb_degree;
	}
	public void setAbb_degree(String abb_degree) {
		this.abb_degree = abb_degree;
	}

	public Date getPg_diploma_date() {
		return pg_diploma_date;
	}
	public void setPg_diploma_date(Date pg_diploma_date) {
		this.pg_diploma_date = pg_diploma_date;
	}
	public String getRef_letter_per() {
		return ref_letter_per;
	}
	public void setRef_letter_per(String ref_letter_per) {
		this.ref_letter_per = ref_letter_per;
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
