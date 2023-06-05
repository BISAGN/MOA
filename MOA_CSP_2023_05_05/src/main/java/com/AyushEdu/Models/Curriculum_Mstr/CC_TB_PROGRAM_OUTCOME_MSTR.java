package com.AyushEdu.Models.Curriculum_Mstr;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_cc_tb_program_outcome_mstr", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class CC_TB_PROGRAM_OUTCOME_MSTR {

	private int id;
	private String program_outcome;
	private String code;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int system_id;
	private int degree_id;
	private int professional_id;
	private int course_id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProgram_outcome() {
		return program_outcome;
	}
	public void setProgram_outcome(String program_outcome) {
		this.program_outcome = program_outcome;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getSystem_id() {
		return system_id;
	}
	public void setSystem_id(int system_id) {
		this.system_id = system_id;
	}
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	public int getProfessional_id() {
		return professional_id;
	}
	public void setProfessional_id(int professional_id) {
		this.professional_id = professional_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
}
