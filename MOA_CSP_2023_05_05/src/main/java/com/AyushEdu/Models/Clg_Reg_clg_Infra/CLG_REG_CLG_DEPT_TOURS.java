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
@Table(name="clg_reg_clg_dept_tours", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_CLG_DEPT_TOURS {
	private int id;
	private String edu_tours_teach_pract_purpose_ug;
	private String edu_tours_teaching_pract_purpose_pg;
	private String edu_tours_pg;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEdu_tours_teach_pract_purpose_ug() {
		return edu_tours_teach_pract_purpose_ug;
	}
	public void setEdu_tours_teach_pract_purpose_ug(String edu_tours_teach_pract_purpose_ug) {
		this.edu_tours_teach_pract_purpose_ug = edu_tours_teach_pract_purpose_ug;
	}
	public String getEdu_tours_teaching_pract_purpose_pg() {
		return edu_tours_teaching_pract_purpose_pg;
	}
	public void setEdu_tours_teaching_pract_purpose_pg(String edu_tours_teaching_pract_purpose_pg) {
		this.edu_tours_teaching_pract_purpose_pg = edu_tours_teaching_pract_purpose_pg;
	}
	public String getEdu_tours_pg() {
		return edu_tours_pg;
	}
	public void setEdu_tours_pg(String edu_tours_pg) {
		this.edu_tours_pg = edu_tours_pg;
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
	
	
}
