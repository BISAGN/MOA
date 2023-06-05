package com.AyushEdu.Models.Curriculum;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_cc_tb_nch_practical_learning_outcome_parent", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT {
	private int id;
	private int system_id;
	private int degree_id;
	private int professional_id;
	private int course_id;
	private int practical_id;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getPractical_id() {
		return practical_id;
	}
	public void setPractical_id(int practical_id) {
		this.practical_id = practical_id;
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
	
}
