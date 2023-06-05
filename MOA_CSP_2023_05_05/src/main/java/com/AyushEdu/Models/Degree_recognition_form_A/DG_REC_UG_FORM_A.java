package com.AyushEdu.Models.Degree_recognition_form_A;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_ug_form_a_parent", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_UG_FORM_A {
	
	private int id;
	private String name_of_applicant_university;
	private String name_ug_course;
	private String abbre_undergraduate_course;
	private String institute_name;
	private String academic_year_applied_for;
	private String academic_file_upload;
	private String permission_from_central_gov;
	private int admission_intake;
	private int num_of_student_admitted;
	private String postal_address;
	private String email;
	private String website;
	private String remarks;

	
	
	private String country;
	private int state_id;
	private int district_id;
	private String city;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int user_id;
	private int university_id;
	private int university_approved_status;
	private int council_approved_status;
	private String current_month_year;
	private String reject_remarks;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_applicant_university() {
		return name_of_applicant_university;
	}
	public void setName_of_applicant_university(String name_of_applicant_university) {
		this.name_of_applicant_university = name_of_applicant_university;
	}
	public String getName_ug_course() {
		return name_ug_course;
	}
	public void setName_ug_course(String name_ug_course) {
		this.name_ug_course = name_ug_course;
	}
	public String getAbbre_undergraduate_course() {
		return abbre_undergraduate_course;
	}
	public void setAbbre_undergraduate_course(String abbre_undergraduate_course) {
		this.abbre_undergraduate_course = abbre_undergraduate_course;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public String getAcademic_year_applied_for() {
		return academic_year_applied_for;
	}
	public void setAcademic_year_applied_for(String academic_year_applied_for) {
		this.academic_year_applied_for = academic_year_applied_for;
	}
	public String getPermission_from_central_gov() {
		return permission_from_central_gov;
	}
	public void setPermission_from_central_gov(String permission_from_central_gov) {
		this.permission_from_central_gov = permission_from_central_gov;
	}
	public int getAdmission_intake() {
		return admission_intake;
	}
	public void setAdmission_intake(int admission_intake) {
		this.admission_intake = admission_intake;
	}
	public int getNum_of_student_admitted() {
		return num_of_student_admitted;
	}
	public void setNum_of_student_admitted(int num_of_student_admitted) {
		this.num_of_student_admitted = num_of_student_admitted;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal_address() {
		return postal_address;
	}
	public void setPostal_address(String postal_address) {
		this.postal_address = postal_address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public int getUniversity_approved_status() {
		return university_approved_status;
	}
	public void setUniversity_approved_status(int university_approved_status) {
		this.university_approved_status = university_approved_status;
	}
	public int getCouncil_approved_status() {
		return council_approved_status;
	}
	public void setCouncil_approved_status(int council_approved_status) {
		this.council_approved_status = council_approved_status;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getAcademic_file_upload() {
		return academic_file_upload;
	}
	public void setAcademic_file_upload(String academic_file_upload) {
		this.academic_file_upload = academic_file_upload;
	}
}
