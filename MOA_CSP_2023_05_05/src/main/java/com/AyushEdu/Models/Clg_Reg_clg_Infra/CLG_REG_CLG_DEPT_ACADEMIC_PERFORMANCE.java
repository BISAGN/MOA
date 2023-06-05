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
@Table(name="clg_reg_clg_dept_academic_performance", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE {
	
	private int id;
	private int p_id;
	private String department;
	private String advance_teaching_plan;
	private String teaching_diary;
	private String journal_practical;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAdvance_teaching_plan() {
		return advance_teaching_plan;
	}
	public void setAdvance_teaching_plan(String advance_teaching_plan) {
		this.advance_teaching_plan = advance_teaching_plan;
	}
	public String getTeaching_diary() {
		return teaching_diary;
	}
	public void setTeaching_diary(String teaching_diary) {
		this.teaching_diary = teaching_diary;
	}
	public String getJournal_practical() {
		return journal_practical;
	}
	public void setJournal_practical(String journal_practical) {
		this.journal_practical = journal_practical;
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
