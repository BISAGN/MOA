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
@Table(name = "dg_rec_examiners_appointed_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_EXAMINERS_APPOINTED {
	private int id; 
	private String name_of_examiners;
	private String subject_examiners;
	private String year_examiners;
	private String qualification_examiners;
	private String teaching_experience;
	private int teacher_code; 
	private int reg_number; 
	private Date d_university_appointment; 
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int university_approved_status;
	private int council_approved_status;
	private String current_month_year;
	private int university_id;
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
	public String getName_of_examiners() {
		return name_of_examiners;
	}
	public void setName_of_examiners(String name_of_examiners) {
		this.name_of_examiners = name_of_examiners;
	}
	public String getSubject_examiners() {
		return subject_examiners;
	}
	public void setSubject_examiners(String subject_examiners) {
		this.subject_examiners = subject_examiners;
	}
	public String getTeaching_experience() {
		return teaching_experience;
	}
	public void setTeaching_experience(String teaching_experience) {
		this.teaching_experience = teaching_experience;
	}
	public int getTeacher_code() {
		return teacher_code;
	}
	public void setTeacher_code(int teacher_code) {
		this.teacher_code = teacher_code;
	}
	public int getReg_number() {
		return reg_number;
	}
	public void setReg_number(int reg_number) {
		this.reg_number = reg_number;
	}
	public Date getD_university_appointment() {
		return d_university_appointment;
	}
	public void setD_university_appointment(Date d_university_appointment) {
		this.d_university_appointment = d_university_appointment;
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
	public String getQualification_examiners() {
		return qualification_examiners;
	}
	public void setQualification_examiners(String qualification_examiners) {
		this.qualification_examiners = qualification_examiners;
	}
	public String getYear_examiners() {
		return year_examiners;
	}
	public void setYear_examiners(String year_examiners) {
		this.year_examiners = year_examiners;
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
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
