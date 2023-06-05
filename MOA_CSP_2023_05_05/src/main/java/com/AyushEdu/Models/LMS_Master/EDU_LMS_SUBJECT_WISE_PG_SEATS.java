package com.AyushEdu.Models.LMS_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_subjects_wise_pg_seats", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_SUBJECT_WISE_PG_SEATS {

	private int id;
	private int institute_id;
	private int pg_subject;
	private String created_by;
	private Date created_date;
	private String modify_by;
	private Date modify_date;
	private int status;
	private int seat;
	private int degree;
	private int pg_dashboard_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	public int getPg_subject() {
		return pg_subject;
	}
	public void setPg_subject(int pg_subject) {
		this.pg_subject = pg_subject;
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
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getPg_dashboard_status() {
		return pg_dashboard_status;
	}
	public void setPg_dashboard_status(int pg_dashboard_status) {
		this.pg_dashboard_status = pg_dashboard_status;
	}
	
	

}
