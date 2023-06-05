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
@Table(name = "dg_rec_within_india_ug_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_WITHIN_INDIA_UG {

	private int id;
	private String university_name;
	private String email;
	private String contact_name;
	private String contact_designation;
	private String contact_email;
	private String contact_mobile;
	private String medical_stream;
	private String institute_name;
	private String nomenclature_degree;
	private String abbreviation_degree;
	private String university_address;
	private String first_batch_admission;
	private String awarded_degree;
	private String final_year_examination;
	private String completion_of_internship;
	private String provisional_certificate;
	private String examinaton_year;
	private Date commencement_dt;
	private String expected_month_year;
	private String continued_degree;
	private String last_batch_year;
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
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_designation() {
		return contact_designation;
	}
	public void setContact_designation(String contact_designation) {
		this.contact_designation = contact_designation;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_mobile() {
		return contact_mobile;
	}
	public void setContact_mobile(String contact_mobile) {
		this.contact_mobile = contact_mobile;
	}
	public String getMedical_stream() {
		return medical_stream;
	}
	public void setMedical_stream(String medical_stream) {
		this.medical_stream = medical_stream;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public String getNomenclature_degree() {
		return nomenclature_degree;
	}
	public void setNomenclature_degree(String nomenclature_degree) {
		this.nomenclature_degree = nomenclature_degree;
	}
	public String getAbbreviation_degree() {
		return abbreviation_degree;
	}
	public void setAbbreviation_degree(String abbreviation_degree) {
		this.abbreviation_degree = abbreviation_degree;
	}
	public String getUniversity_address() {
		return university_address;
	}
	public void setUniversity_address(String university_address) {
		this.university_address = university_address;
	}
	public String getFirst_batch_admission() {
		return first_batch_admission;
	}
	public void setFirst_batch_admission(String first_batch_admission) {
		this.first_batch_admission = first_batch_admission;
	}
	public String getAwarded_degree() {
		return awarded_degree;
	}
	public void setAwarded_degree(String awarded_degree) {
		this.awarded_degree = awarded_degree;
	}
	public String getFinal_year_examination() {
		return final_year_examination;
	}
	public void setFinal_year_examination(String final_year_examination) {
		this.final_year_examination = final_year_examination;
	}
	public String getCompletion_of_internship() {
		return completion_of_internship;
	}
	public void setCompletion_of_internship(String completion_of_internship) {
		this.completion_of_internship = completion_of_internship;
	}
	public String getProvisional_certificate() {
		return provisional_certificate;
	}
	public void setProvisional_certificate(String provisional_certificate) {
		this.provisional_certificate = provisional_certificate;
	}
	public String getExaminaton_year() {
		return examinaton_year;
	}
	public void setExaminaton_year(String examinaton_year) {
		this.examinaton_year = examinaton_year;
	}
	public Date getCommencement_dt() {
		return commencement_dt;
	}
	public void setCommencement_dt(Date commencement_dt) {
		this.commencement_dt = commencement_dt;
	}
	public String getExpected_month_year() {
		return expected_month_year;
	}
	public void setExpected_month_year(String expected_month_year) {
		this.expected_month_year = expected_month_year;
	}
	public String getContinued_degree() {
		return continued_degree;
	}
	public void setContinued_degree(String continued_degree) {
		this.continued_degree = continued_degree;
	}
	public String getLast_batch_year() {
		return last_batch_year;
	}
	public void setLast_batch_year(String last_batch_year) {
		this.last_batch_year = last_batch_year;
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
