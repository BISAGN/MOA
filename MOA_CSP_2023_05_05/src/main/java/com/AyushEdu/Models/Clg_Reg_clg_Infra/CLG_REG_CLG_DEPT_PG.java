package com.AyushEdu.Models.Clg_Reg_clg_Infra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_clg_dept_pg", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_CLG_DEPT_PG {
	
	private int id;
	private int p_id;
	private String subject;
	private String theory;
	private String seminar;
	private String practical;
	private String case_presentation;
	private String journal_meeting;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	private int department_id;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTheory() {
		return theory;
	}
	public void setTheory(String theory) {
		this.theory = theory;
	}
	public String getSeminar() {
		return seminar;
	}
	public void setSeminar(String seminar) {
		this.seminar = seminar;
	}
	public String getPractical() {
		return practical;
	}
	public void setPractical(String practical) {
		this.practical = practical;
	}
	public String getCase_presentation() {
		return case_presentation;
	}
	public void setCase_presentation(String case_presentation) {
		this.case_presentation = case_presentation;
	}
	public String getJournal_meeting() {
		return journal_meeting;
	}
	public void setJournal_meeting(String journal_meeting) {
		this.journal_meeting = journal_meeting;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}	
	
	
}
