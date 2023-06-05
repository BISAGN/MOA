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
@Table(name = "dg_rec_medical_qua_pg_diploma_36b_courses", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES {
	

	private int id;
	private String pg_dip_subject;
	private String nomenclature_dg_dip;
	private String abbreviation_dg_dip;
	private String year_admi_first_batch_dip;;
	private String passing_year_of_finalyear_dip;
	private String year_provisional_certi_dip;
	private Date permission_lattter_dt;
	private String ref_permission_latter;
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
	public String getPg_dip_subject() {
		return pg_dip_subject;
	}
	public void setPg_dip_subject(String pg_dip_subject) {
		this.pg_dip_subject = pg_dip_subject;
	}
	public String getNomenclature_dg_dip() {
		return nomenclature_dg_dip;
	}
	public void setNomenclature_dg_dip(String nomenclature_dg_dip) {
		this.nomenclature_dg_dip = nomenclature_dg_dip;
	}
	public String getAbbreviation_dg_dip() {
		return abbreviation_dg_dip;
	}
	public void setAbbreviation_dg_dip(String abbreviation_dg_dip) {
		this.abbreviation_dg_dip = abbreviation_dg_dip;
	}
	public String getYear_admi_first_batch_dip() {
		return year_admi_first_batch_dip;
	}
	public void setYear_admi_first_batch_dip(String year_admi_first_batch_dip) {
		this.year_admi_first_batch_dip = year_admi_first_batch_dip;
	}
	public String getPassing_year_of_finalyear_dip() {
		return passing_year_of_finalyear_dip;
	}
	public void setPassing_year_of_finalyear_dip(String passing_year_of_finalyear_dip) {
		this.passing_year_of_finalyear_dip = passing_year_of_finalyear_dip;
	}
	public String getYear_provisional_certi_dip() {
		return year_provisional_certi_dip;
	}
	public void setYear_provisional_certi_dip(String year_provisional_certi_dip) {
		this.year_provisional_certi_dip = year_provisional_certi_dip;
	}
	public Date getPermission_lattter_dt() {
		return permission_lattter_dt;
	}
	public void setPermission_lattter_dt(Date permission_lattter_dt) {
		this.permission_lattter_dt = permission_lattter_dt;
	}
	public String getRef_permission_latter() {
		return ref_permission_latter;
	}
	public void setRef_permission_latter(String ref_permission_latter) {
		this.ref_permission_latter = ref_permission_latter;
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
