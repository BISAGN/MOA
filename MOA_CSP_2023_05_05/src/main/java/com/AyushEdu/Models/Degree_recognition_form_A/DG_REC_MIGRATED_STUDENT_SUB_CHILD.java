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
@Table(name = "dg_rec_migrated_student_sub_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_MIGRATED_STUDENT_SUB_CHILD {

	private int id;
	private String name_of_inst;
	private String student_name_to_migrated;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date migrated_dt_to;
	private String professional_year_migrated;
	private String university_enrollment_number;
	private String remarks_form_d;
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
	public String getStudent_name_to_migrated() {
		return student_name_to_migrated;
	}
	public void setStudent_name_to_migrated(String student_name_to_migrated) {
		this.student_name_to_migrated = student_name_to_migrated;
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
	public String getName_of_inst() {
		return name_of_inst;
	}
	public void setName_of_inst(String name_of_inst) {
		this.name_of_inst = name_of_inst;
	}
	public String getProfessional_year_migrated() {
		return professional_year_migrated;
	}
	public void setProfessional_year_migrated(String professional_year_migrated) {
		this.professional_year_migrated = professional_year_migrated;
	}
	public String getUniversity_enrollment_number() {
		return university_enrollment_number;
	}
	public void setUniversity_enrollment_number(String university_enrollment_number) {
		this.university_enrollment_number = university_enrollment_number;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public String getRemarks_form_d() {
		return remarks_form_d;
	}
	public void setRemarks_form_d(String remarks_form_d) {
		this.remarks_form_d = remarks_form_d;
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
	public Date getMigrated_dt_to() {
		return migrated_dt_to;
	}
	public void setMigrated_dt_to(Date migrated_dt_to) {
		this.migrated_dt_to = migrated_dt_to;
	}
	@Override
	public String toString() {
		return "DG_REC_MIGRATED_STUDENT_SUB_CHILD [CAST(migrated_dt_to as 'dd/mm/yyyy') =" + migrated_dt_to + "]";
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
