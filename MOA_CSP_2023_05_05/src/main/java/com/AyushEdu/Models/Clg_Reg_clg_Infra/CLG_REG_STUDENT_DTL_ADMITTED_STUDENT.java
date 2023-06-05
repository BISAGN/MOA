package com.AyushEdu.Models.Clg_Reg_clg_Infra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clg_reg_student_dtl_admitted_student", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_STUDENT_DTL_ADMITTED_STUDENT {
	
	private int id;
	private int institute_id;
	private int year;
	private int current_year;
	private int govt_quota_ug;
	private int mang_quota_ug;
	private int govt_quota_pg;
	private int mang_quota_pg;
	private int court_order;
	private String last_student;
	private Date last_stu_add_date;
	private int final_status;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCurrent_year() {
		return current_year;
	}
	public void setCurrent_year(int current_year) {
		this.current_year = current_year;
	}
	public int getGovt_quota_ug() {
		return govt_quota_ug;
	}
	public void setGovt_quota_ug(int govt_quota_ug) {
		this.govt_quota_ug = govt_quota_ug;
	}
	public int getMang_quota_ug() {
		return mang_quota_ug;
	}
	public void setMang_quota_ug(int mang_quota_ug) {
		this.mang_quota_ug = mang_quota_ug;
	}
	public int getGovt_quota_pg() {
		return govt_quota_pg;
	}
	public void setGovt_quota_pg(int govt_quota_pg) {
		this.govt_quota_pg = govt_quota_pg;
	}
	public int getMang_quota_pg() {
		return mang_quota_pg;
	}
	public void setMang_quota_pg(int mang_quota_pg) {
		this.mang_quota_pg = mang_quota_pg;
	}
	public int getCourt_order() {
		return court_order;
	}
	public void setCourt_order(int court_order) {
		this.court_order = court_order;
	}
	public String getLast_student() {
		return last_student;
	}
	public void setLast_student(String last_student) {
		this.last_student = last_student;
	}
	public Date getLast_stu_add_date() {
		return last_stu_add_date;
	}
	public void setLast_stu_add_date(Date last_stu_add_date) {
		this.last_stu_add_date = last_stu_add_date;
	}
	public int getFinal_status() {
		return final_status;
	}
	public void setFinal_status(int final_status) {
		this.final_status = final_status;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}


}
