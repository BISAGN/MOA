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
@Table(name="clg_reg_dept_pg_tours", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_DEPT_PG_TOURS {
	
	private String edu_tours_teach_pract_purpose;
	private String edu_tours_conduct_last_yr;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	
	public String getEdu_tours_teach_pract_purpose() {
		return edu_tours_teach_pract_purpose;
	}
	public void setEdu_tours_teach_pract_purpose(String edu_tours_teach_pract_purpose) {
		this.edu_tours_teach_pract_purpose = edu_tours_teach_pract_purpose;
	}
	public String getEdu_tours_conduct_last_yr() {
		return edu_tours_conduct_last_yr;
	}
	public void setEdu_tours_conduct_last_yr(String edu_tours_conduct_last_yr) {
		this.edu_tours_conduct_last_yr = edu_tours_conduct_last_yr;
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
	
}
