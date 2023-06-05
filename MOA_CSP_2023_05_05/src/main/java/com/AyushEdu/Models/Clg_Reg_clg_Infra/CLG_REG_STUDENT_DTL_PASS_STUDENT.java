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
@Table(name="clg_reg_student_dtl_pass_student", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_STUDENT_DTL_PASS_STUDENT {
	
	private int id;
	private int institute_id;
	private int year;
	private int current_year;
	private int appeared_stu_ug;
	private int passed_stu_ug;
	private int appeared_stu_pg;
	private int passed_stu_pg;
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
	public int getAppeared_stu_ug() {
		return appeared_stu_ug;
	}
	public void setAppeared_stu_ug(int appeared_stu_ug) {
		this.appeared_stu_ug = appeared_stu_ug;
	}
	public int getPassed_stu_ug() {
		return passed_stu_ug;
	}
	public void setPassed_stu_ug(int passed_stu_ug) {
		this.passed_stu_ug = passed_stu_ug;
	}
	public int getAppeared_stu_pg() {
		return appeared_stu_pg;
	}
	public void setAppeared_stu_pg(int appeared_stu_pg) {
		this.appeared_stu_pg = appeared_stu_pg;
	}
	public int getPassed_stu_pg() {
		return passed_stu_pg;
	}
	public void setPassed_stu_pg(int passed_stu_pg) {
		this.passed_stu_pg = passed_stu_pg;
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
