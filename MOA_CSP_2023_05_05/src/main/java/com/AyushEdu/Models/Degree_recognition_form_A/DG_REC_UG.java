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
@Table(name = "dg_rec_ug", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_UG {
	
	private int id;
	private String name_of_applicant_university;
	private String name_ug_course;
	private String abbre_undergraduate_course;
	
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int user_id;
	private int university_approved_status;
	private int council_approved_status;
	private int university_id;
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
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
}
