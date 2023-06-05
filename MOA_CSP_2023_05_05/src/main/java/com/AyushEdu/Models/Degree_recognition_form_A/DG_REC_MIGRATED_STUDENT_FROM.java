package com.AyushEdu.Models.Degree_recognition_form_A;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dg_rec_migrated_student_from_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_MIGRATED_STUDENT_FROM {

	private int id;
	private String name_of_institution;
	private String name_of_students_migrated;
	private Date dt_of_migration;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String professional_year;
	private String university_enroll_num;
	private String remarks_migrated;
	private int user_id;
	private int inst_status;
	private int university_approved_status;
	private int council_approved_status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int university_id;
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
	public String getName_of_institution() {
		return name_of_institution;
	}
	public void setName_of_institution(String name_of_institution) {
		this.name_of_institution = name_of_institution;
	}
	public String getName_of_students_migrated() {
		return name_of_students_migrated;
	}
	public void setName_of_students_migrated(String name_of_students_migrated) {
		this.name_of_students_migrated = name_of_students_migrated;
	}
	public Date getDt_of_migration() {
		return dt_of_migration;
	}
	public void setDt_of_migration(Date dt_of_migration) {
		this.dt_of_migration = dt_of_migration;
	}
	public String getProfessional_year() {
		return professional_year;
	}
	public void setProfessional_year(String professional_year) {
		this.professional_year = professional_year;
	}
	public String getUniversity_enroll_num() {
		return university_enroll_num;
	}
	public void setUniversity_enroll_num(String university_enroll_num) {
		this.university_enroll_num = university_enroll_num;
	}
	public String getRemarks_migrated() {
		return remarks_migrated;
	}
	public void setRemarks_migrated(String remarks_migrated) {
		this.remarks_migrated = remarks_migrated;
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
	public int getInst_status() {
		return inst_status;
	}
	public void setInst_status(int inst_status) {
		this.inst_status = inst_status;
	}
	@Override
	public String toString() {
		return "DG_REC_MIGRATED_STUDENT_FROM [CAST(dt_of_migration as 'dd/mm/yyyy') =" + dt_of_migration + "]";
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
	
}
