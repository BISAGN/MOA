package com.AyushEdu.Models.Curriculum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

@Entity
@Table(name = "edu_cc_tb_nch_teaching_hours", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_CC_TB_NCH_TEACHING_HOURS {

	private int id;
	private int system_id;
	private int degree_id;
	private int professional_id;
	private int course_id;
	private int theoretical_lecture;
	private int pract_tutor_semi_clinic_post;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	
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
	public int getTheoretical_lecture() {
		return theoretical_lecture;
	}
	public void setTheoretical_lecture(int theoretical_lecture) {
		this.theoretical_lecture = theoretical_lecture;
	}
	public int getPract_tutor_semi_clinic_post() {
		return pract_tutor_semi_clinic_post;
	}
	public void setPract_tutor_semi_clinic_post(int pract_tutor_semi_clinic_post) {
		this.pract_tutor_semi_clinic_post = pract_tutor_semi_clinic_post;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
