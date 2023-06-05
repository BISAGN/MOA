package com.AyushEdu.Models.Degree_recognition_form_C;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_appeal_form_c", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class APPEAL_FORM_C {
	
	private int id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String aggrieved_university_name;
	private String aggrieved_university_address;
	private String university_registrar_email_id;
	private String contact_person_name;
	private String contact_person_designation;
	private String contact_person_mobile_no;
	private String contact_person_email_id;
	private String institute_name;
	private String nomenclature_of_degree;
	private String abbreviation_of_degree ;
	private Date first_application_date;
	private Date denial_application_date;
	private String reason;
	private String prayer_of_the_university;
	private String document;
	private int university_status;
	private int council_status;
	private int user_id;
	private int univercity_id;
	private String current_month_year;

	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAggrieved_university_name() {
		return aggrieved_university_name;
	}
	public void setAggrieved_university_name(String aggrieved_university_name) {
		this.aggrieved_university_name = aggrieved_university_name;
	}
	public String getAggrieved_university_address() {
		return aggrieved_university_address;
	}
	public void setAggrieved_university_address(String aggrieved_university_address) {
		this.aggrieved_university_address = aggrieved_university_address;
	}
	public String getUniversity_registrar_email_id() {
		return university_registrar_email_id;
	}
	public void setUniversity_registrar_email_id(String university_registrar_email_id) {
		this.university_registrar_email_id = university_registrar_email_id;
	}
	public String getContact_person_name() {
		return contact_person_name;
	}
	public void setContact_person_name(String contact_person_name) {
		this.contact_person_name = contact_person_name;
	}
	public String getContact_person_designation() {
		return contact_person_designation;
	}
	public void setContact_person_designation(String contact_person_designation) {
		this.contact_person_designation = contact_person_designation;
	}
	public String getContact_person_email_id() {
		return contact_person_email_id;
	}
	public void setContact_person_email_id(String contact_person_email_id) {
		this.contact_person_email_id = contact_person_email_id;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public String getNomenclature_of_degree() {
		return nomenclature_of_degree;
	}
	public void setNomenclature_of_degree(String nomenclature_of_degree) {
		this.nomenclature_of_degree = nomenclature_of_degree;
	}
	public String getAbbreviation_of_degree() {
		return abbreviation_of_degree;
	}
	public void setAbbreviation_of_degree(String abbreviation_of_degree) {
		this.abbreviation_of_degree = abbreviation_of_degree;
	}
	public Date getFirst_application_date() {
		return first_application_date;
	}
	public void setFirst_application_date(Date first_application_date) {
		this.first_application_date = first_application_date;
	}
	public Date getDenial_application_date() {
		return denial_application_date;
	}
	public void setDenial_application_date(Date denial_application_date) {
		this.denial_application_date = denial_application_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPrayer_of_the_university() {
		return prayer_of_the_university;
	}
	public void setPrayer_of_the_university(String prayer_of_the_university) {
		this.prayer_of_the_university = prayer_of_the_university;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getUniversity_status() {
		return university_status;
	}
	public void setUniversity_status(int university_status) {
		this.university_status = university_status;
	}
	public int getCouncil_status() {
		return council_status;
	}
	public void setCouncil_status(int council_status) {
		this.council_status = council_status;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUnivercity_id() {
		return univercity_id;
	}
	public void setUnivercity_id(int univercity_id) {
		this.univercity_id = univercity_id;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
	public String getContact_person_mobile_no() {
		return contact_person_mobile_no;
	}
	public void setContact_person_mobile_no(String contact_person_mobile_no) {
		this.contact_person_mobile_no = contact_person_mobile_no;
	}
}


