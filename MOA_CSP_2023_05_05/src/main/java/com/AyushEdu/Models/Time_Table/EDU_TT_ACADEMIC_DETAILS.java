package com.AyushEdu.Models.Time_Table;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "edu_tt_academic_details", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_TT_ACADEMIC_DETAILS {

	
	private int id;
	private int professional;	
	private String academic_details;
	private int term;
	private int exam_type;
	private int exam_serial;
	private int institute_id;
	private Date starting_date;	
	private Date ending_date;
	private String modified_by;
	private Date modified_dt;
	private String created_by;
	private Date created_dt;
	private String created_role;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}
	public Date getStarting_date() {
		return starting_date;
	}
	public void setStarting_date(Date starting_date) {
		this.starting_date = starting_date;
	}
	public Date getEnding_date() {
		return ending_date;
	}
	public void setEnding_date(Date ending_date) {
		this.ending_date = ending_date;
	}
	public String getAcademic_details() {
		return academic_details;
	}
	public void setAcademic_details(String academic_details) {
		this.academic_details = academic_details;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getExam_type() {
		return exam_type;
	}
	public void setExam_type(int exam_type) {
		this.exam_type = exam_type;
	}
	public int getExam_serial() {
		return exam_serial;
	}
	public void setExam_serial(int exam_serial) {
		this.exam_serial = exam_serial;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_dt() {
		return modified_dt;
	}

	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getCreated_role() {
		return created_role;
	}
	public void setCreated_role(String created_role) {
		this.created_role = created_role;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
}
